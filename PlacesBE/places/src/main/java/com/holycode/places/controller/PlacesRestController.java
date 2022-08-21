package com.holycode.places.controller;

import java.util.ArrayList;
import java.util.List;

import org.json.JSONArray;
import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

import com.holycode.places.model.Place;
import com.holycode.places.service.PlacesService;

@RestController
@RequestMapping(path = "/places")
public class PlacesRestController {

	private List<String> placeIDs;
	private final String apiURL = "https://storage.googleapis.com/coding-session-rest-api";

	private final PlacesService placesService;

	@Autowired
	public PlacesRestController(PlacesService placesService) {
		this.placesService = placesService;
		this.placeIDs = new ArrayList<>();
		placeIDs.add("ohGSnJtMIC5nPfYRi_HTAg");
		placeIDs.add("GXvPAor1ifNfpF0U5PTG0w");
	}

	@GetMapping
	@CrossOrigin(origins = "http://localhost:3000")
	public @ResponseBody ResponseEntity<Object> getPlaces() {
		RestTemplate restTemplate = new RestTemplate();
		JSONArray responseJSON = new JSONArray();

		for (String placeID : placeIDs) {
			String json = restTemplate.getForObject(apiURL + "/" + placeID, String.class);
			JSONObject placeJSON = new JSONObject(json);
			JSONObject placeForUI = placesService.prepareData(placeJSON);
			responseJSON.put(placeForUI);
		}

		return ResponseEntity.status(HttpStatus.OK).body(responseJSON.toString());
	}
}
