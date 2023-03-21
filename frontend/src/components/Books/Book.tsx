import { Link } from "react-router-dom";

import { deleteBook, rentBook } from "../../api-calls/api";

export default function Book({
  book,
  refreshDeleteState,
  refreshRentState,
}: {
  book: Book;
  refreshDeleteState: (book: Book) => void;
  refreshRentState: (book: Book) => void;
}) {
  const handleDeleteClick = () => {
    const id: number = book.id!;
    deleteBook(id);
    refreshDeleteState(book);
  };

  const handleRentClick = () => {
    if (book.availableCopies === 0) {
      alert("No copies available");
      return;
    }

    const id: number = book.id!;
    rentBook(id);
    refreshRentState(book);
  };

  return (
    <tr>
      <td>{book.name}</td>
      <td>{book.category}</td>
      <td>
        {book.author.name} {book.author.surname}
      </td>
      <td>{book.availableCopies}</td>
      <td className="text-center">
        <div style={{ display: "flex", flexDirection: "row", gap: "0.5rem", alignItems:"center" }}>
          <Link to={`/books/edit/${book.id!}`} className="btn btn-primary">
            Edit
          </Link>
          <button className="btn btn-danger" onClick={handleDeleteClick}>
            Delete
          </button>
          <button className="btn btn-outline-info" onClick={handleRentClick}>
            Mark as taken
          </button>
        </div>
      </td>
    </tr>
  );
}
