import instance from "../axios-base-routes/axios";

export async function getBooks(): Promise<Book[]> {
  const response = await instance.get("/books/list");
  // console.log(response.data);
  return response.data;
}

export async function getBookById(id: number): Promise<Book>  {
  const response = await instance.get(`/books/${id}`);
  // console.log(response.data);
  return response.data;
}

export async function addBook(book: BookDto): Promise<Book> {
  const response = await instance.post("/books/add", book);
  console.log(response.data);
  return response.data;
}

export async function editBook(book: BookDto, id: number): Promise<Book> {
  const response = await instance.put(`/books/edit/${id}`, book);
  console.log(response.data);
  return response.data;
}

export async function deleteBook(id: number): Promise<any> {
  const response = await instance.delete(`/books/delete/${id}`);
  // console.log(response.data);
  return response.data;
}

export async function rentBook(id: number): Promise<Book> {
  const response = await instance.put(`/books/rent/${id}`);
  console.log(response.data);
  return response.data;
}

export async function getAuthors(): Promise<Author[]>{
  const response = await instance.get("/authors/list");
  const data = response.data;
  // console.log(data);
  return data;
}

export async function getAuthorById(id: number): Promise<Author> {
  const response = await instance.get(`/authors/${id}`);
  const data = response.data;
  return data;
}

export async function getBookCategories(): Promise<string[]> {

  const response = await instance.get("/book-categories/list");
  const data = response.data;
  // console.log(data);
  return data;
}

