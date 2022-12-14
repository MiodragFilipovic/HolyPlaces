import React from "react";

import { Box } from "@chakra-ui/react";
import {
  Popover,
  PopoverTrigger,
  PopoverContent,
  PopoverHeader,
  PopoverBody,
  PopoverArrow,
  PopoverCloseButton,
} from "@chakra-ui/react";

import { IconButton } from "@chakra-ui/react";
import { TimeIcon } from "@chakra-ui/icons";

export default function WorkingHours({ restaurantData }) {
  return (
    <Box>
      <Popover minW={200}>
        <PopoverTrigger>
          <IconButton
            variant="ghost"
            colorScheme="teal"
            aria-label="Search database"
            icon={<TimeIcon />}
          />
        </PopoverTrigger>
        <PopoverContent>
          <PopoverArrow />
          <PopoverCloseButton />
          <PopoverHeader>Opening hours</PopoverHeader>
          <PopoverBody>
            <Box>
              {restaurantData?.opening_hours.map((workingHour) => {
                const wh = workingHour.interval.split("-");
                let sWH = "";
                if(wh.length > 1) {
                  sWH = wh[0] + "-" +wh[wh.length-1]
                }else{
                  sWH = wh[0];
                }
                return (
                  <Box
                    display="flex"
                    key={workingHour.interval}
                    justifyContent="space-between"
                  >
                    <Box m={2}>
                      <h3 style={{ textTransform: "uppercase" }}>
                        {sWH}
                      </h3>
                    </Box>
                    <Box display="flex">
                      {workingHour.workingHours.map((hour) => {
                        if (hour.type === "CLOSED") {
                          return (
                            <Box key={hour.start + hour.end} m={2}>
                              <h3>CLOSED</h3>
                            </Box>
                          );
                        } else {
                          return (
                            <Box m={2} key={hour.start + hour.end}>
                              <h3>
                                {hour.start} - {hour.end}
                              </h3>
                            </Box>
                          );
                        }
                      })}
                    </Box>
                  </Box>
                );
              })}
            </Box>
          </PopoverBody>
        </PopoverContent>
      </Popover>
    </Box>
  );
}
