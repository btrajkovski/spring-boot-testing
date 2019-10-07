package com.north47.springboottesting;

import com.north47.springboottesting.models.Book;
import com.north47.springboottesting.repository.BookRepository;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/books")
public class BookController {
    private BookRepository bookRepository;

    public BookController(BookRepository bookRepository) {
        this.bookRepository = bookRepository;
    }

    @GetMapping("/{id}")
    public ResponseEntity<Book> findBookById(@PathVariable Long id) {
        return bookRepository.findById(id)
                .map(ResponseEntity::ok)
                .orElse(new ResponseEntity<>(HttpStatus.NOT_FOUND));
    }

    @GetMapping("/test")
    public ResponseEntity<Book> testMethod(@PathVariable Long id) {
        Integer BOJAN = 1;
        System.out.println(BOJAN);
        throw new NullPointerException();
    }

    @PostMapping
    public ResponseEntity<Book> addBook(@RequestBody Book book) {
        if (book.getId() != null) {
            return ResponseEntity.badRequest().build();
        }

        return ResponseEntity.status(HttpStatus.CREATED)
                .body(bookRepository.save(book));
    }
}
