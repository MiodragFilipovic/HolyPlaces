import "./App.css";
import { ChakraProvider } from "@chakra-ui/react";
import Restaurant from "./components/Restaurant";
import Restaurants from "./components/Restaurants";

function App() {
  return (
    <ChakraProvider>
      <div className="App">
        <Restaurants></Restaurants>
      </div>
    </ChakraProvider>
  );
}

export default App;
