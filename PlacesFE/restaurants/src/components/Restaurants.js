import { Box, Center, Text } from "@chakra-ui/react";
import React, { useEffect, useState } from "react";
import Restaurant from "./Restaurant";
import { getDefaultNormalizer } from "@testing-library/react";
import axios from "axios";

export default function Restaurants() {
  const [restaurants, setRestaurants] = useState([]);

  async function getData() {
    const data = await axios.get(`http://localhost:8080/places`).then((res) => {
      const places = res.data;
      setRestaurants(places);
    });
  }

  useEffect(() => {
    getData();
  }, []);

  return (
    <Box>
      <Box display="flex" justifyContent="center" marginBottom={20}>
        <Text fontSize="6xl" color="white">
          Restaurants
        </Text>
      </Box>
      <Box
        display="flex"
        justifyContent="center"
        alignItems="center"
        flexDirection="column"
      >
        {restaurants?.map((restaurant) => {
          return (
            <Restaurant
              key={restaurant.id}
              restaurantData={restaurant}
            ></Restaurant>
          );
        })}
      </Box>
    </Box>
  );
}
