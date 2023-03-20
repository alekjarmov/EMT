import { Link } from "react-router-dom";

import { deleteBook, rentBook } from "../../../api-calls/api";

export default function Book({ book, refreshState }: { book: Book, refreshState: (book: Book) => void }) {
  const deleteBookClick = () => {
    const id: number = book.id!;
    deleteBook(id);
    refreshState(book);
  };

  return (
    <tr>
      <td>{book.name}</td>
      <td>{book.category}</td>
      <td>
        {book.author.name} {book.author.surname}
      </td>
      <td>{book.availableCopies}</td>
      <td>
        <Link to={`books/edit/${book.id!}`}></Link>
        <button className="btn btn-danger" onClick={deleteBookClick}>
          Delete
        </button>
      </td>
    </tr>
  );
}
