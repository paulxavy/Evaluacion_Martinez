package com.distribuida.servicios;

import com.distribuida.db.Book;

import java.util.List;

public interface BookService {
    Book findById(Integer id);
    List<Book> findAll();
    void delete(Integer id);
    void create(Book b);
    void update(Integer id, Book obj);
}
