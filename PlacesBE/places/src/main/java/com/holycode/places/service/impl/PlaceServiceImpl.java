package com.holycode.places.service.impl;

import java.time.LocalTime;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;
import org.springframework.stereotype.Service;

import com.holycode.places.model.Day;
import com.holycode.places.model.Days;
import com.holycode.places.model.WorkingHour;
import com.holycode.places.model.WorkingHourGroup;
import com.holycode.places.model.WorkingSchedule;
import com.holycode.places.service.PlacesService;

@Service
public class PlaceServiceImpl implements PlacesService {

	@Override
	public JSONObject prepareData(JSONObject placeJSON) {
		JSONObject placeForUI = new JSONObject();
		placeForUI.put("id", ((JSONObject) placeJSON.getJSONArray("addresses").get(0)).get("address_id"));
		placeForUI.put("displayed_what", placeJSON.get("displayed_what"));
		placeForUI.put("displayed_where", placeJSON.get("displayed_where"));
		JSONObject feedbackSummary = new JSONObject();
		feedbackSummary.put("ratings_count",
				((JSONObject) placeJSON.getJSONObject("place_feedback_summary")).get("ratings_count"));
		feedbackSummary.put("average_rating",
				((JSONObject) placeJSON.getJSONObject("place_feedback_summary")).get("average_rating"));
		placeForUI.put("feedback_summary", feedbackSummary);
		JSONObject openingHours = (JSONObject) placeJSON.get("opening_hours");
		placeForUI.put("opening_hours", groupOpeningHours(openingHours));
		placeForUI.put("categories", getCategories(placeJSON));

		return placeForUI;

	}

	private JSONArray getCategories(JSONObject placeJSON) {
		JSONArray addresses = (JSONArray) placeJSON.getJSONArray("addresses");
		JSONArray categoriesJSON = new JSONArray();
		
		for (int i = 0; i < addresses.length(); i++) {
			JSONObject address = addresses.getJSONObject(i);
			JSONObject business = address.getJSONObject("business");
			JSONArray categories = business.getJSONArray("categories");
			
			for (int j = 0; j < categories.length(); j++) {
				JSONObject category = categories.getJSONObject(j);
				String categoryName = (String) category.getJSONObject("name").get("en");
				JSONObject categoryJson = new JSONObject();
				categoryJson.put("name", categoryName);
				categoriesJSON.put(categoryJson);
			}
		}
		return categoriesJSON;
	}

	private JSONArray groupOpeningHours(JSONObject openingHours) {
		List<Day> days = new ArrayList<>();
		Arrays.asList(Days.values()).forEach(day -> {
			JSONArray daysJSON;
			try {
				daysJSON = (JSONArray) openingHours.getJSONObject("days").getJSONArray(day.toString());
				days.add(createWorkingDay(day, daysJSON));
			} catch (JSONException e) {
				Day workingDay = new Day(day);
				workingDay.addWorkingHour(new WorkingHour(null, null, false));
				days.add(workingDay);
			}
		});
		return createWorkingSchedule(days);
	}

	private Day createWorkingDay(Days day, JSONArray daysJSON) {
		Day workingDay = new Day(day);
		for (int i = 0; i < daysJSON.length(); i++) {
			JSONObject dayWorkingHour = daysJSON.getJSONObject(i);
			String start = dayWorkingHour.getString("start");
			String startHour = start.split(":")[0];
			String startMinute = start.split(":")[1];
			String end = dayWorkingHour.getString("end");
			String endHour = end.split(":")[0];
			String endMinute = end.split(":")[1];
			String type = dayWorkingHour.getString("type");
			workingDay.addWorkingHour(
					new WorkingHour(LocalTime.of(Integer.valueOf(startHour), Integer.valueOf(startMinute)),
							LocalTime.of(Integer.valueOf(endHour), Integer.valueOf(endMinute)),
							type.equals("OPEN") ? true : false));
		}
		return workingDay;
	}

	private JSONArray createWorkingSchedule(List<Day> days) {
		WorkingSchedule schedule = new WorkingSchedule();
		WorkingHourGroup whg = new WorkingHourGroup(days.get(0).getDay().toString(), days.get(0).getWorkingHours());
		for (int i = 0; i < days.size() - 1; i++) {
			if (days.get(i).equals(days.get(i + 1))) {
				whg.addDayToInterval(days.get(i + 1).getDay().toString());
				if (i == days.size() - 2) {
					schedule.addWorkingHoursGroup(whg);
				}
			} else {
				schedule.addWorkingHoursGroup(whg);
				whg = new WorkingHourGroup(days.get(i + 1).getDay().toString(), days.get(i + 1).getWorkingHours());
				if (i == days.size() - 2) {
					whg = new WorkingHourGroup(days.get(i + 1).getDay().toString(), days.get(i + 1).getWorkingHours());
					schedule.addWorkingHoursGroup(whg);
				}
			}
		}
		JSONArray scheduleJSON = createScheduleJSON(schedule);
		return scheduleJSON;
	}

	private JSONArray createScheduleJSON(WorkingSchedule schedule) {
		JSONArray scheduleJSON = new JSONArray();
		for (WorkingHourGroup workingHourGroup : schedule.getWorkingHourGroups()) {
			workingHourGroup.trimInterval();
			JSONObject workingHourGroupJSON = new JSONObject();
			workingHourGroupJSON.put("interval", workingHourGroup.getInterval());
			JSONArray workingHoursJSON = new JSONArray();
			for (WorkingHour wh : workingHourGroup.getWorkingHours()) {
				JSONObject whJSON = new JSONObject();
				whJSON.put("start", wh.getStartHour() != null ? wh.getStartHour().toString() : "");
				whJSON.put("end", wh.getEndHour() != null ? wh.getEndHour().toString() : "");
				whJSON.put("type", wh.isOpen() ? "OPEN" : "CLOSED");
				workingHoursJSON.put(whJSON);
			}
			workingHourGroupJSON.put("workingHours", workingHoursJSON);
			scheduleJSON.put(workingHourGroupJSON);
		}
		return scheduleJSON;
	}

}
