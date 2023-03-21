import {
  getAuthors,
  getBookCategories,
  editBook,
  addBook,
  getBookById,
} from "../../api-calls/api";
import { useState, useEffect } from "react";
import { useNavigate, useParams } from "react-router-dom";

export function AuthorDropDown({ authors, defaultAuthor }: { authors: Author[], defaultAuthor?: number}) {
    const [defaultId, setDefaultId] = useState<number>(defaultAuthor ? defaultAuthor : 0);
    useEffect(() => {
        setDefaultId(defaultAuthor ? defaultAuthor : 0);
    }, [defaultAuthor]);
  return (
    <select name="author" className="form-control" value={defaultId} onChange={(e) => {setDefaultId(parseInt(e.target.value)) } }>
      {authors.map((author) => (
        <option key={author.id} value={author.id}>
          {author.name}
        </option>
      ))}
    </select>
  );
}

export function CategortDropDown({ categories, defaultCategory }: { categories: string[], defaultCategory?: string }) {
    const [defaultCat, setDefaultCat] = useState<string>(defaultCategory ? defaultCategory : "");
    useEffect(() => {
        setDefaultCat(defaultCategory ? defaultCategory : "");
    }, [defaultCategory]);
    
  return (
    <select name="category" className="form-control" value={defaultCat} onChange={(e) => setDefaultCat(e.target.value)}>
      {categories.map((category) => (
        <option key={category} value={category}>
          {category}
        </option>
      ))}
    </select>
  );
}

// a form used for adding/editing a book
export default function BookForm() {
  const [authors, setAuthors] = useState<Author[]>([]);
  const [categories, setCategories] = useState<string[]>([]);
  const [book, setBook] = useState<Book | undefined>(undefined);
  const [availableCopies, setAvailableCopies] = useState<number>(0);
  const navigate = useNavigate();
  let { bookId } = useParams();
  let id = bookId ? parseInt(bookId) : undefined;
  useEffect(() => {
    getAuthors().then((authors) => setAuthors(authors));
    getBookCategories().then((categories) => setCategories(categories));
    if (Boolean(id)) {
        getBookById(id!).then((book) => {setBook(book); setAvailableCopies(book.availableCopies);});
        
    }
  }, []);

  function handleSubmit(event: React.FormEvent<HTMLFormElement>) {
    event.preventDefault();
    const form = event.currentTarget;
    let formData: FormData = new FormData(form);
    console.log(form);
    console.log(FormData);
    console.log(formData.get("name"));
    const authorId = parseInt(formData.get("author") as string);

    const author: Author = authors.find((author) => author.id === authorId)!;
    const availableCopies = parseInt(formData.get("availableCopies") as string);

    const newBook: BookDto = {
      name: formData.get("name") as string,
      authorId: authorId,
      category: formData.get("category") as string,
      availableCopies: availableCopies,
    };

    console.log(newBook);
    if (Boolean(book)) {
      editBook(newBook, book!.id!).then(() => navigate("/books"));
    } else {
      addBook(newBook).then(() => navigate("/books"));
    }
  }

  return (
    <>
      {Boolean(book) ? <h1>Edit Book</h1> : <h1>Add Book</h1>}
      <form onSubmit={handleSubmit}>
        <div className="form-group col-6">
          <div className="form-group row">
            <label htmlFor="nameInput" className="col-sm-2 col-form-label">
              Name:
            </label>
            <div className="col-sm-10">
              <input
                type="text"
                className="form-control"
                id="nameInput"
                name="name"
                defaultValue={book?.name}
              />
            </div>
          </div>

          <div className="form-group row">
            <label htmlFor="categoryInput" className="col-sm-2 col-form-label">
              Category:
            </label>
            <div className="col-sm-10">
              <CategortDropDown categories={categories} defaultCategory={book?.category} />
            </div>
          </div>

          <div className="form-group row">
            <label htmlFor="inputPassword" className="col-sm-2 col-form-label">
              Author:
            </label>
            <div className="col-sm-10">
              <AuthorDropDown authors={authors} defaultAuthor={book?.author.id}/>
            </div>
          </div>
          
          <div className="form-group row">
            <label htmlFor="copiesInput" className="col-sm-2 col-form-label">
              Copies:
            </label>
            
            <div className="col-sm-10">
                
              <input
                type="number"
                min="0"
                className="form-control"
                id="copiesInput"
                name="availableCopies"
                value={availableCopies || 0}
                onChange = {(e) => {setAvailableCopies(parseInt(e.target.value))} }
              />
            </div>
          </div>
        </div>

        <div>
          <button type="submit" className="btn btn-primary">
            Submit
          </button>
        </div>
      </form>
    </>
  );
}
