package gveliz.springframework.spring5webapp.bootstrap;

import gveliz.springframework.spring5webapp.model.Author;
import gveliz.springframework.spring5webapp.model.Book;
import gveliz.springframework.spring5webapp.model.Publisher;
import gveliz.springframework.spring5webapp.repositories.AuthorRepository;
import gveliz.springframework.spring5webapp.repositories.BookRepository;
import gveliz.springframework.spring5webapp.repositories.PublisherRepository;
import org.springframework.context.ApplicationListener;
import org.springframework.context.event.ContextRefreshedEvent;
import org.springframework.stereotype.Component;

@Component
public class DevBootstrap implements ApplicationListener<ContextRefreshedEvent> {

    private AuthorRepository authorRepository;
    private BookRepository bookRepository;
    private PublisherRepository publisherRepository;

    public DevBootstrap(AuthorRepository authorRepository, BookRepository bookRepository,PublisherRepository publisherRepository) {
        this.authorRepository = authorRepository;
        this.bookRepository = bookRepository;
        this.publisherRepository = publisherRepository;
    }

    @Override
    public void onApplicationEvent(ContextRefreshedEvent contextRefreshedEvent) {
        initData();
    }

    private void initData(){

        Publisher p1 = new Publisher("Harper Collins","over there");
        publisherRepository.save(p1);

        //eric
        Author eric = new Author("Eric","Evans");
        Book ddd = new Book("Domain Driven Design","123",p1);
        eric.getBooks().add(ddd);
        ddd.getAuthors().add(eric);

        authorRepository.save(eric);
        bookRepository.save(ddd);

        //rod
        Author rod = new Author("Rod","Johnson");
        //Publisher p2 = new Publisher("Worx","over here");
        Book noEJB = new Book("J2EE Development without EJB","23444",p1);
        rod.getBooks().add(noEJB);
        noEJB.getAuthors().add(rod);

        authorRepository.save(rod);
        bookRepository.save(noEJB);
//      publisherRepository.save(p2);
    }

}
