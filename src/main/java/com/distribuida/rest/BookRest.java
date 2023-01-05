package com.distribuida.rest;

import com.distribuida.db.Book;
import com.distribuida.servicios.BookService;
import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Inject;
import jakarta.ws.rs.*;
import jakarta.ws.rs.core.MediaType;
import jakarta.ws.rs.core.Response;

import java.util.List;

@ApplicationScoped
@Path("/books")
public class BookRest {
    @Inject private BookService bookService;

    @GET
    @Path("/{id}")
    @Produces(MediaType.APPLICATION_JSON)
    public Book findById(@PathParam("id") Integer id){
        return bookService.findById(id);
    }
    @GET
    @Produces(MediaType.APPLICATION_JSON)
    public List<Book> findAll () {

        return bookService.findAll();
    }
    @DELETE
    @Path("/{id}")
    public Response delete (@PathParam("id") Integer id){
        bookService.delete(id);

        return Response.status((Response.Status.OK) ).build();
    }
    @POST
    @Consumes(MediaType.APPLICATION_JSON)
    public Response create(Book b){

        bookService.create(b);

        return Response.status(Response.Status.CREATED).build();
    }
    @PUT @Path("/{id}")
    @Consumes(MediaType.APPLICATION_JSON)
    public Response update (Book b, @PathParam("id") Integer id){
        bookService.update(id,b);
        return Response.status((Response.Status.OK) ).build();
    }

}
