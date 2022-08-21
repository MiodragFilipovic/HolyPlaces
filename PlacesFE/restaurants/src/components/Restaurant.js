import { React, useState } from "react";
import { Badge } from "@chakra-ui/react";
import { Box } from "@chakra-ui/react";
import { StarIcon } from "@chakra-ui/icons";

import { Icon } from "@chakra-ui/react";
import { MdStarHalf, MdStar } from "react-icons/md";
import WorkingHours from "./WorkingHours";

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
          <Box>
            {restaurantData.categories.map((cetegory) => {
              return (
                <Badge
                  key={cetegory.name}
                  borderRadius="full"
                  px="1"
                  colorScheme="teal"
                  m={1}
                >
                  {cetegory.name}
                </Badge>
              );
            })}
          </Box>

          <Box
            display="flex"
            mt="2"
            alignItems="center"
            justifyContent="space-between"
          >
            {Array(5)
              .fill("")
              .map((_, i) => {
                if (i <= restaurantData.feedback_summary.average_rating - 1) {
                  return <Icon key={i} as={MdStar} color={"teal.500"} />;
                } else {
                  if (restaurantData.feedback_summary.average_rating - i > 0) {
                    return <Icon key={i} as={MdStarHalf} color={"teal.500"} />;
                  } else {
                    return <Icon key={i} as={MdStar} color={"gray.500"} />;
                  }
                }
              })}
            <Box as="span" ml="2" color="gray.600" fontSize="sm">
              {parseFloat(
                restaurantData.feedback_summary.average_rating
              ).toFixed(2)}{" "}
              ({restaurantData.feedback_summary.ratings_count} reviews)
            </Box>
            <WorkingHours restaurantData={restaurantData}></WorkingHours>
          </Box>
        </Box>
      </Box>
  );
}
