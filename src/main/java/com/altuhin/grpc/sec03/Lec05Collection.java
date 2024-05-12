package com.altuhin.grpc.sec03;


import com.altuhin.models.sec03.Book;
import com.altuhin.models.sec03.Library;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.List;


public class Lec05Collection {
    private static final Logger log = LoggerFactory.getLogger(Lec05Collection.class);

    public static void main(String[] args) {

        //create books
        Book book1 = Book.newBuilder()
                .setTitle("Introduction to Java")
                .setAuthor("Herbert Schieldt")
                .setPublicationYear("2003")
                .build();

        Book book2 = Book.newBuilder()
                .setTitle("Structure Programming with C/C++")
                .setAuthor("E Balagurur Shwami")
                .setPublicationYear("1997")
                .build();

        Book book3 = Book.newBuilder()
                .setTitle("Design and Analysis of Algorithm")
                .setAuthor("Guy Even")
                .setPublicationYear("2012")
                .build();

        Book book4 = Book.newBuilder()
                .setTitle("Java")
                .setAuthor("Daniel Liang")
                .setPublicationYear("2000")
                .build();

        Library library = Library.newBuilder()
                .setName("Eastern University library")
       /*         .addBooks(book1)
                .addBooks(book2)
                .addBooks(book3)
                .addBooks(book4)*/
                .addAllBooks(List.of(book1, book2, book3, book4))
                .build();
        log.info("library: {}", library);
//        log.info("student: {}", student);

    }
}
