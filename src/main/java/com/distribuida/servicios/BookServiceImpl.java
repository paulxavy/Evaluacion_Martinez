package com.distribuida.servicios;

import com.distribuida.db.Book;
import jakarta.annotation.PostConstruct;
import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Inject;
import org.jdbi.v3.core.Jdbi;
import javax.sql.DataSource;
import java.util.*;

@ApplicationScoped
public class BookServiceImpl implements BookService {
    @Inject
    private DataSource dataSource;
    private Jdbi jdbi;

    @PostConstruct
    public void init() {
        jdbi = Jdbi.create(dataSource);
    }


    public Book findById(Integer id) {
        Optional<Book> book;
        try (var handle = jdbi.open()) {
            book = handle.createQuery("SELECT * FROM books WHERE id=:id")
                    .bind("id", id)
                    .map((rs, ctx) -> Book
                            .builder()
                            .id(rs.getInt("id"))
                            .author(rs.getString("author"))
                            .isbn(rs.getString("isbn"))
                            .title(rs.getString("title"))
                            .price(rs.getDouble("price"))
                            .build())
                    .findOne();
        }

        return book
                .orElse(null);
    }

    @Override
    public List<Book> findAll() {
        List<Book> books = new ArrayList<>();
        try (var handle = jdbi.open()) {
            books = handle.createQuery("SELECT * FROM books")
                    .map((rs, ctx) -> Book
                            .builder()
                            .id(rs.getInt("id"))
                            .author(rs.getString("author"))
                            .title(rs.getString("title"))
                            .isbn(rs.getString("isbn"))
                            .price(rs.getDouble("price"))
                            .build())
                    .list();
        }
        ;
        return books;
    }

    @Override
    public void delete(Integer id) {
        try (var handle = jdbi.open()) {
            handle.createUpdate("DELETE FROM books WHERE id=:id")
                    .bind("id", id)
                    .execute();
        }
    }

    @Override
    public void create(Book b) {
        try (var handle = jdbi.open()) {
            handle.createUpdate("INSERT INTO books(isbn,title,author,price) values (:isbn,:title,:author,:price)")
                    .bind("isbn", b.getIsbn())
                    .bind("title", b.getTitle())
                    .bind("author", b.getAuthor())
                    .bind("price", b.getPrice())
                    .execute();
        }

    }

    @Override
    public void update(Integer id, Book b) {
        try (var handle = jdbi.open()) {
            handle.createUpdate("UPDATE books set isbn=:isbn,title=:title,author=:author,price=:price WHERE id=:id")
                    .bind("isbn", b.getIsbn())
                    .bind("title", b.getTitle())
                    .bind("author", b.getAuthor())
                    .bind("price", b.getPrice())
                    .bind("id", id)
                    .execute();
        }

    }

}

