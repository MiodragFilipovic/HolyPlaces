import React from "react";
import { Box } from "@chakra-ui/react";
import { Icon } from "@chakra-ui/react";
import { MdStarHalf, MdStar } from "react-icons/md";

export default function Ratings({ feedback_summary }) {
  return (
    <Box display="flex" alignItems="center" justifyContent="center">
      <Box display="flex">
        {Array(5)
          .fill("")
          .map((_, i) => {
            if (i <= feedback_summary.average_rating - 1) {
              return <Icon key={i} as={MdStar} color={"yellow.300"} />;
            } else {
              if (feedback_summary.average_rating - i > 0) {
                return <Icon key={i} as={MdStarHalf} color={"yellow.300"} />;
              } else {
                return <Icon key={i} as={MdStar} color={"gray.500"} />;
              }
            }
          })}
      </Box>
      <Box as="span" ml="2" color="gray.600" fontSize="sm">
        {parseFloat(feedback_summary.average_rating).toFixed(2)} (
        {feedback_summary.ratings_count} reviews)
      </Box>
    </Box>
  );
}
