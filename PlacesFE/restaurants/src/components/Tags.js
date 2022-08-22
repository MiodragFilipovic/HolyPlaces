import React from "react";
import { Badge } from "@chakra-ui/react";
import { Box } from "@chakra-ui/react";

export default function Tags({ categories }) {
  return (
    <Box>
      {categories.map((cetegory) => {
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
  );
}
