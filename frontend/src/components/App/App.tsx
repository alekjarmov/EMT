import { useState } from "react";
import reactLogo from "./assets/react.svg";

// import './App.css'

import { getBooks } from "../../api-calls/api";
import NavBar from "../NavBar/NavBar";
import { Route, Routes } from "react-router-dom";


import BooksList from "../Books/BooksList";
import Categories from "../Categories/Categories";
import BookForm from "../Books/BookForm";
function App() {
  const [count, setCount] = useState(0);
  const [books, setBooks] = useState([]);

  return (
    <>
      <NavBar />
      <div className="container">
        <Routes>
          <Route path="/" element={<BooksList/>} />
          <Route path="/books" element={<BooksList/>} />
          <Route path="/categories" element={<Categories/>} />
          <Route path="/books/add" element={<BookForm/>} />
          <Route path="/books/edit/:bookId" element={<BookForm/>} />
        </Routes>
      </div>
    </>
  );
}

export default App;
