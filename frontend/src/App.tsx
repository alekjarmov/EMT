import { useState } from "react";
import reactLogo from "./assets/react.svg";

// import './App.css'

import { getBooks } from "./api-calls/api";
import NavBar from "./components/NavBar/NavBar";

function App() {
  const [count, setCount] = useState(0);

  return (
    <>
      <NavBar></NavBar>
      <div className="container">
        
      </div>
    </>
  );
}

export default App;
