import { useState } from "react";
import reactLogo from "./assets/react.svg";

// import './App.css'

import { getBooks } from "../../api-calls/api";
import NavBar from "../NavBar/NavBar";
import { Route, Routes } from "react-router-dom";


import BooksList from "../Books/BooksList/BooksList";

function App() {
  const [count, setCount] = useState(0);

  return (
    <>
      <NavBar />
      <div className="container">
        <Routes>
          <Route path="/" element={<BooksList/>} />
          <Route path="/books" element={<BooksList/>} />
        </Routes>
      </div>
    </>
  );
}

export default App;
