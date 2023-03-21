import { Link } from "react-router-dom";
import Book from "./Book";
import { useEffect, useState } from "react";
//this component is used to display all the books which we get from the api

import { getBooks, getBookPage } from "../../api-calls/api";

export default function BooksList() {
  const [books, setBooks] = useState<Book[]>([]);
  const [pageData, setPageData] = useState<PageData>({ page: 0, size: 5 });
  const [pageResponse, setPageResponse] = useState<Omit<PageResponse<Book>, "content">>(
    { first: true, last: true, totalPages: 0 }
  );

  function refreshServerStateVariables() {
    getBookPage(pageData).then((data) => {
      console.log(data);
      setBooks(data.content);
      setPageResponse({ first: data.first, last: data.last, totalPages: data.totalPages });
      console.log(books);
    });
  }

  useEffect(() => {
    refreshServerStateVariables();
  }, [pageData]);

  //refresh the state after deleting a book
  const deleteBook = (bookOld: Book) => {
    // setBooks(books.filter((book) => book.id !== bookOld.id));
    refreshServerStateVariables();
    if (books.length === 1 && pageResponse.last) {
      setPageData({ ...pageData, page: pageData.page - 1 });
    }
  };

  const rentBook = (bookOld: Book) => {
    setBooks(
      books.map((book) => {
        if (book.id === bookOld.id) {
          book.availableCopies--;
        }
        return book;
      })
    );
  };
  function handleNextClick() {
    if (pageResponse.last) return;
    setPageData({ ...pageData, page: pageData.page + 1 });
  }
  function handlePreviousClick() {
    if (pageResponse.first) return;
    setPageData({ ...pageData, page: pageData.page - 1 });
  }
  function addEmptyRows(){
    let emptyRows = [];
    for (let i = 0; i < pageData.size - books.length; i++) {
      emptyRows.push(<tr key={i}><td colSpan={5}>&nbsp;</td></tr>);
    }
    return emptyRows;
  }
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
              {books.map((book) => (
                <Book
                  key={book.id}
                  book={book}
                  refreshDeleteState={deleteBook}
                  refreshRentState={rentBook}
                />
              ))}
                {addEmptyRows()}
            </tbody>
          </table>
        </div>
        <div className="col mb-3">
          <div className="row">
            <div className="col-sm-4 col-md-4">
              <Link className={"btn btn-block btn-dark"} to={"/books/add"}>
                Add new book
              </Link>
            </div>
            <div className="col-sm-2 col-md-2">
                {pageData.page + 1} / {pageResponse.totalPages}
            </div>
            <div className="col-sm-2 col-md-2">
              <button
                className={"btn btn-block btn-dark"}
                onClick={handlePreviousClick}
              >
                &laquo;
              </button>
            </div>
            <div className="col-sm-2 col-md-2">
              <button
                className={"btn btn-block btn-dark"}
                onClick={handleNextClick}
              >
                &raquo;
              </button>
            </div>
          </div>
        </div>
      </div>
    </div>
  );
}
