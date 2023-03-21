import { Link } from 'react-router-dom';
import Book from './Book';
import { useEffect, useState } from "react";
//this component is used to display all the books which we get from the api

import { getBooks } from "../../api-calls/api";

export default function  BooksList(){
    const [books, setBooks] = useState<Book[]>([]);
    useEffect(() => {
        getBooks().then((books) => setBooks(books));
    }, []);

    //refresh the state after deleting a book
    const deleteBook = (bookOld: Book) => {
        setBooks(books.filter((book) => book.id !== bookOld.id));
    };

    const rentBook = (bookOld: Book) => {
        setBooks(books.map((book)=>{
            if(book.id === bookOld.id){
                book.availableCopies--;
            }
            return book;
        }));
    };

    return (
        <div className={"container mm-4 mt-5"}>
        <div className={"row"}>
            <div className={"table-responsive"}>
                <table className={"table table-striped"}>
                    <thead>
                    <tr>
                        <th scope={"col"}>Title</th>
                        <th scope={"col"}>Category</th>
                        <th scope={"col"}>Author</th>
                        <th scope={"col"}>Available copies</th>
                        <th scope={"col"}>Actions</th>
                    </tr>
                    </thead>
                    <tbody>
                     { books.map((book) => <Book key={book.id} book={book} refreshDeleteState={deleteBook} refreshRentState = {rentBook}/>) }
                    </tbody>
                </table>
            </div>
            <div className="col mb-3">
                <div className="row">
                    <div className="col-sm-12 col-md-12">
                        <Link className={"btn btn-block btn-dark"} to={"/books/add"}>Add new book</Link>
                    </div>
                </div>
            </div>
        </div>
    </div>
    );
}

