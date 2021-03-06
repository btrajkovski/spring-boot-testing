package com.north47.springboottesting;

import com.north47.springboottesting.models.Book;
import com.north47.springboottesting.repository.BookRepository;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.test.context.junit4.SpringRunner;

import static org.assertj.core.api.Assertions.assertThat;

@RunWith(SpringRunner.class)
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
public class SpringBootTestingApplicationTests {

    @Autowired
    private TestRestTemplate restTemplate;

    @Autowired
    private BookRepository bookRepository;

    private Book defaultBook;

    @Before
    public void setup() {
        defaultBook = new Book(null, "Asimov", "Foundation", 350);
    }

    @Test
    public void testShouldReturnCreatedWhenValidBook() {
        ResponseEntity<Book> bookResponseEntity = this.restTemplate.postForEntity("/books", defaultBook, Book.class);

        assertThat(bookResponseEntity.getStatusCode()).isEqualTo(HttpStatus.CREATED);
        assertThat(bookResponseEntity.getBody().getId()).isNotNull();
        assertThat(bookRepository.findById(1L)).isPresent();
    }

    @Test
    public void testShouldFindBooksWhenExists() throws Exception {
        Book savedBook = bookRepository.save(defaultBook);

        ResponseEntity<Book> bookResponseEntity = this.restTemplate.getForEntity("/books/" + savedBook.getId(), Book.class);

        assertThat(bookResponseEntity.getStatusCode()).isEqualTo(HttpStatus.OK);
        assertThat(bookResponseEntity.getBody().getId()).isEqualTo(savedBook.getId());
    }

    @Test
    public void testShouldReturn404WhenBookMissing() {
        Long nonExistingId = 999L;
        ResponseEntity<Book> bookResponseEntity = this.restTemplate.getForEntity("/books/" + nonExistingId, Book.class);

        assertThat(bookResponseEntity.getStatusCode()).isEqualTo(HttpStatus.NOT_FOUND);
    }

    @Test
    public void testShouldReturn400WhenInvalidIdFormat() {
        // given
        String invalidIdFormat = "1234d";

        // when
        ResponseEntity<Book> responseEntity = this.restTemplate.getForEntity("/books/" + invalidIdFormat, Book.class);

        // then
        assertThat(responseEntity.getStatusCode()).isEqualTo(HttpStatus.BAD_REQUEST);
    }
}
