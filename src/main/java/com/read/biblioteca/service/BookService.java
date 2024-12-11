package com.read.biblioteca.service;

import com.read.biblioteca.dto.ReqRes;
import com.read.biblioteca.model.Book;
import com.read.biblioteca.repository.BookRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class BookService {

    @Autowired
    private BookRepo bookRepo;

    // Agregar un libro
    public ReqRes addBook(Book book) {
        ReqRes response = new ReqRes();
        try {
            Book savedBook = bookRepo.save(book);
            response.setStatusCode(200);
            response.setMessage("Libro agregado exitosamente");
            response.setOurBook(savedBook);
        } catch (Exception e) {
            response.setStatusCode(500);
            response.setMessage("Error al agregar libro: " + e.getMessage());
        }
        return response;
    }

    // Actualizar un libro
    public ReqRes updateBook(Long id, Book book) {
        ReqRes response = new ReqRes();
        try {
            Optional<Book> existingBook = bookRepo.findById(id);
            if (existingBook.isPresent()) {
                Book updatedBook = existingBook.get();
                updatedBook.setCodigo(book.getCodigo());
                updatedBook.setTitulo(book.getTitulo());
                updatedBook.setAutor(book.getAutor());
                updatedBook.setIsbn(book.getIsbn());
                updatedBook.setNumPaginas(book.getNumPaginas());
                updatedBook.setAnioPublicacion(book.getAnioPublicacion());
                updatedBook.setDescripcion(book.getDescripcion());
                updatedBook.setEstado(book.getEstado());
                updatedBook.setImagen(book.getImagen());
                updatedBook.setCategoria(book.getCategoria());
                updatedBook.setIdioma(book.getIdioma());
                updatedBook.setEditorial(book.getEditorial());
                updatedBook.setStock(book.getStock());

                Book savedBook = bookRepo.save(updatedBook);
                response.setStatusCode(200);
                response.setMessage("Libro actualizado exitosamente");
                response.setOurBook(savedBook);
            } else {
                response.setStatusCode(404);
                response.setMessage("Libro no encontrado");
            }
        } catch (Exception e) {
            response.setStatusCode(500);
            response.setMessage("Error al actualizar libro: " + e.getMessage());
        }
        return response;
    }

    // Eliminar un libro
    public ReqRes deleteBook(Long id) {
        ReqRes response = new ReqRes();
        try {
            Optional<Book> book = bookRepo.findById(id);
            if (book.isPresent()) {
                bookRepo.deleteById(id);
                response.setStatusCode(200);
                response.setMessage("Libro eliminado exitosamente");
            } else {
                response.setStatusCode(404);
                response.setMessage("Libro no encontrado");
            }
        } catch (Exception e) {
            response.setStatusCode(500);
            response.setMessage("Error al eliminar libro: " + e.getMessage());
        }
        return response;
    }

    // Obtener un libro por ID
    public ReqRes getBookById(Long id) {
        ReqRes response = new ReqRes();
        try {
            Optional<Book> book = bookRepo.findById(id);
            if (book.isPresent()) {
                response.setStatusCode(200);
                response.setOurBook(book.get());
                response.setMessage("Libro encontrado");
            } else {
                response.setStatusCode(404);
                response.setMessage("Libro no encontrado");
            }
        } catch (Exception e) {
            response.setStatusCode(500);
            response.setMessage("Error al obtener libro: " + e.getMessage());
        }
        return response;
    }

    // Obtener todos los libros
    public ReqRes getAllBooks() {
        ReqRes reqRes = new ReqRes();
        try {
            // Convierte el Iterable a List
            List<Book> result = (List<Book>) bookRepo.findAll();
            reqRes.setBooks(result);
            reqRes.setStatusCode(200);
            reqRes.setMessage("Books fetched successfully");
        } catch (Exception e) {
            reqRes.setStatusCode(500);
            reqRes.setMessage("Error occurred: " + e.getMessage());
        }
        return reqRes;
    }
    
}
