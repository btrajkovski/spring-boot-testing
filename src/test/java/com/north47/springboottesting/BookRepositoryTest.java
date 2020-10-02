package com.north47.springboottesting;

import com.north47.springboottesting.models.Author;
import com.north47.springboottesting.models.Book;
import com.north47.springboottesting.repository.BookRepository;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.autoconfigure.orm.jpa.TestEntityManager;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.Collections;
import java.util.Optional;

import static org.assertj.core.api.Assertions.assertThat;

@RunWith(SpringRunner.class)
@DataJpaTest
public class BookRepositoryTest {
    @Autowired
    private TestEntityManager entityManager;

    @Autowired
    private BookRepository repository;

    private Book defaultBook;
    private Author author = new Author( "Isaak", "Asimov");

    @Before
    public void setup() {
        defaultBook = new Book(null, author, "Foundation", 350);
        author.addBook(defaultBook);
    }

    @Test
    public void testShouldPersistBooksWithAuthor() {
        Book savedBook = repository.save(defaultBook);

        assertThat(savedBook.getId()).isNotNull();
        assertThat(savedBook.getAuthor().getId()).isNotNull();
        assertThat(entityManager.find(Book.class, savedBook.getId())).isNotNull();
    }

    @Test
    public void testShouldFindByIdWhenBookExists() {
        Book savedBook = entityManager.persistAndFlush(defaultBook);

        assertThat(repository.findById(savedBook.getId())).isEqualTo(Optional.of(savedBook));
    }

    @Test
    public void testFindByIdShouldReturnEmptyWhenBookNotFound() {
        long nonExistingID = 47L;

        assertThat(repository.findById(nonExistingID)).isEqualTo(Optional.empty());
    }
}
