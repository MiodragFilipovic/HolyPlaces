import { React } from "react";
import { Box } from "@chakra-ui/react";

import WorkingHours from "./WorkingHours";
import Ratings from "./Ratings";
import Tags from "./Tags";

export default function Restaurant({ restaurantData }) {
  return (
    <Box
      maxW="sm"
      minH={190}
      borderWidth="1px"
      borderRadius="lg"
      overflow="hidden"
      m={5}
      bgColor="white"
    >
      <Box p="6">
        <Box
          mt="1"
          fontWeight="semibold"
          as="h4"
          lineHeight="tight"
          noOfLines={1}
        >
          {restaurantData?.displayed_what}
        </Box>
        <Box
          display="flex"
          flexDirection="row"
          alignItems="flex-end"
          mt="1"
          fontWeight="normal"
          as="h5"
          lineHeight="tight"
          noOfLines={1}
        >
          {restaurantData?.displayed_where}
        </Box>
        <Tags categories={restaurantData?.categories}></Tags>

        <Box
          display="flex"
          mt="2"
          alignItems="center"
          justifyContent="space-between"
        >
          <Ratings
            feedback_summary={restaurantData?.feedback_summary}
          ></Ratings>
          <WorkingHours restaurantData={restaurantData}></WorkingHours>
        </Box>
      </Box>
    </Box>
  );
}
