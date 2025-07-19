package com.application.courselibrary.service;

import com.application.courselibrary.entity.Publisher;
import com.application.courselibrary.repository.PublisherRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class PublisherService {

    @Autowired
    private PublisherRepository publisherRepository;

    public List<Publisher> findAllPublishers() {
        return publisherRepository.findAll();
    }

    public Publisher findPublisherById(Long id) {
        return publisherRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Publisher not found"));
    }

    public void createPublisher(Publisher publisher) {
        publisherRepository.save(publisher);
    }

    public void updatePublisher(Publisher publisher) {
        publisherRepository.save(publisher);
    }

    public void deletePublisher(Long id) {
        Publisher publisher = findPublisherById(id);
        publisherRepository.deleteById(publisher.getId());
    }
}
