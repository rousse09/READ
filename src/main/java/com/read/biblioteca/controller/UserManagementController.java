package com.read.biblioteca.controller;

import com.read.biblioteca.dto.ReqRes;
import com.read.biblioteca.model.Book;
import com.read.biblioteca.model.OurUsers;
import com.read.biblioteca.service.BookService;
import com.read.biblioteca.service.UsersManagementService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.*;

@RestController
public class UserManagementController {
    @Autowired
    private UsersManagementService usersManagementService;

    @PostMapping("/auth/register")
    public ResponseEntity<ReqRes> regeister(@RequestBody ReqRes reg){
        return ResponseEntity.ok(usersManagementService.register(reg));
    }

    @PostMapping("/auth/login")
    public ResponseEntity<ReqRes> login(@RequestBody ReqRes req){
        return ResponseEntity.ok(usersManagementService.login(req));
    }

    @PostMapping("/auth/refresh")
    public ResponseEntity<ReqRes> refreshToken(@RequestBody ReqRes req){
        return ResponseEntity.ok(usersManagementService.refreshToken(req));
    }

    @GetMapping("/admin/get-all-users")
    public ResponseEntity<ReqRes> getAllUsers(){
        return ResponseEntity.ok(usersManagementService.getAllUsers());

    }

    @GetMapping("/admin/get-users/{userId}")
    public ResponseEntity<ReqRes> getUSerByID(@PathVariable Integer userId){
        return ResponseEntity.ok(usersManagementService.getUsersById(userId));

    }

    @PutMapping("/admin/update/{userId}")
    public ResponseEntity<ReqRes> updateUser(@PathVariable Integer userId, @RequestBody OurUsers reqres){
        return ResponseEntity.ok(usersManagementService.updateUser(userId, reqres));
    }

    @GetMapping("/adminuser/get-profile")
    public ResponseEntity<ReqRes> getMyProfile(){
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        String email = authentication.getName();
        ReqRes response = usersManagementService.getMyInfo(email);
        return  ResponseEntity.status(response.getStatusCode()).body(response);
    }

    @DeleteMapping("/admin/delete/{userId}")
    public ResponseEntity<ReqRes> deleteUSer(@PathVariable Integer userId){
        return ResponseEntity.ok(usersManagementService.deleteUser(userId));
    }

    @Autowired
    private BookService bookService;

    // Agregar un libro
    @PostMapping("/bibliotecario/agregar-libro")
    public ReqRes agregarLibro(@RequestBody Book book) {
        return bookService.addBook(book);
    }

    // Editar un libro
    @PutMapping("/bibliotecario/editar-libro/{id}")
    public ReqRes editarLibro(@PathVariable Long id, @RequestBody Book book) {
        return bookService.updateBook(id, book);
    }

    // Eliminar un libro
    @DeleteMapping("/bibliotecario/eliminar-libro/{id}")
    public ReqRes eliminarLibro(@PathVariable Long id) {
        return bookService.deleteBook(id);
    }

    // Obtener un libro por su ID
    @GetMapping("/bibliotecario/libro/{id}")
    public ReqRes getBookById(@PathVariable Long id) {
        return bookService.getBookById(id);
    }

    // Obtener todos los libros
    @GetMapping("/bibliotecario/libros")
    public ReqRes getAllBooks() {
        return bookService.getAllBooks();
    }


}
