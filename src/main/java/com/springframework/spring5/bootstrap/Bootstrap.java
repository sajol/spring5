package com.springframework.spring5.bootstrap;

import com.springframework.spring5.model.Author;
import com.springframework.spring5.model.Book;
import com.springframework.spring5.repositories.AuthorRepository;
import com.springframework.spring5.repositories.BookRepository;
import org.springframework.context.ApplicationListener;
import org.springframework.context.event.ContextRefreshedEvent;
import org.springframework.stereotype.Component;

/**
 * Author: JT
 * Date: 12/20/17
 */
@Component
public class Bootstrap implements ApplicationListener<ContextRefreshedEvent> {

    AuthorRepository authorRepository;
    BookRepository bookRepository;

    public Bootstrap(AuthorRepository authorRepository, BookRepository bookRepository) {
        this.authorRepository = authorRepository;
        this.bookRepository = bookRepository;
    }

    @Override
    public void onApplicationEvent(ContextRefreshedEvent contextRefreshedEvent) {
        initData();
    }

    private void initData() {
        Author eric = new Author("Eric", "Evans");
        Book domainDrivenDesign = new Book("Domain Driven Design", "1234", "Harper Collins");
        eric.getBooks().add(domainDrivenDesign);
        domainDrivenDesign.getAuthors().add(eric);

        authorRepository.save(eric);
        bookRepository.save(domainDrivenDesign);

        Author rod = new Author("Rod", "Johnson");
        Book noEjb = new Book("J2EE Development Without EJB", "23444", "Wrox");
        rod.getBooks().add(noEjb);
        noEjb.getAuthors().add(rod);

        authorRepository.save(rod);
        bookRepository.save(noEjb);
    }
}
