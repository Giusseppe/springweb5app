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
        //eric
        Author eric = new Author("Eric","Evans");
        Publisher p1 = new Publisher("Harper Collins","over there");
        Book ddd = new Book("Domain Driven Design","123");
        eric.getBooks().add(ddd);
        ddd.getAuthors().add(eric);
        ddd.getPublishers().add(p1);

        authorRepository.save(eric);
        bookRepository.save(ddd);
        publisherRepository.save(p1);

        //rod
        Author rod = new Author("Rod","Johnson");
        Publisher p2 = new Publisher("Worx","over here");
        Book noEJB = new Book("J2EE Development without EJB","23444");
        noEJB.getPublishers().add(p2);
        rod.getBooks().add(noEJB);

        authorRepository.save(rod);
        bookRepository.save(noEJB);
        publisherRepository.save(p2);
    }

}
