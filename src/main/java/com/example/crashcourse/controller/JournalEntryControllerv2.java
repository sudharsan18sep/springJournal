package com.example.crashcourse.controller;


import com.example.crashcourse.entity.JournalEntry;
import com.example.crashcourse.service.JournalEntryService;
import org.bson.types.ObjectId;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDateTime;
import java.util.*;

//specail componetns handles http request
@RestController
@RequestMapping("/journal")
//applies reuqest mapping on the whole class
public class JournalEntryControllerv2 {

    private Map<Long, JournalEntry> journalEntries = new HashMap<>();

    @Autowired
    private JournalEntryService journalEntryService;

    @GetMapping
    public ResponseEntity<?> getAll() {
        List<JournalEntry> all= journalEntryService.getAllEntry();
        if(all !=null && !all.isEmpty()){
            return  new ResponseEntity<>(all, HttpStatus.OK);
        }

        return new ResponseEntity<>(HttpStatus.NOT_FOUND);

    }

    @PostMapping
    //@requestbody - hey spring get the data from request and convert it into java object
    public ResponseEntity<JournalEntry> createEntry(@RequestBody JournalEntry myEntry) {

        try {
            myEntry.setDate(LocalDateTime.now());
            journalEntryService.saveEntry(myEntry);
            return new ResponseEntity<>(myEntry, HttpStatus.CREATED);
        }
        catch (Exception e){
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }

    }

    //pathvariable
    //requestVariable

    @GetMapping("id/{id}")
    public ResponseEntity<JournalEntry> getJournalEntryById(@PathVariable ObjectId id) {
        Optional<JournalEntry> journalEntryOptional = journalEntryService.getJournalbyId(id);
        if(journalEntryOptional.isPresent()){
            return new ResponseEntity<>(journalEntryOptional.get(), HttpStatus.OK);
        }
        return new ResponseEntity<>(HttpStatus.NOT_FOUND);

    }
    //? -> wildcard pattern in future we can return any class object
    @DeleteMapping("id/{id}")
    public ResponseEntity<?> deleteJournalEntryById(@PathVariable ObjectId id) {
        journalEntries.remove(id);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }

    @PutMapping("id/{id}")
    public ResponseEntity<JournalEntry> updateJournalById(@PathVariable ObjectId Id, @RequestBody JournalEntry newEntry) {

        JournalEntry oldEntry = journalEntryService.getJournalbyId(Id).orElse(null);
        if(oldEntry != null)
        {
            oldEntry.setTitle((newEntry.getTitle()!=null && !newEntry.getTitle().equals("") ? newEntry.getTitle() : oldEntry.getTitle()));
            oldEntry.setContent((newEntry.getContent()!=null && !newEntry.getContent().equals(""))? newEntry.getContent() : oldEntry.getContent());
            journalEntryService.saveEntry(oldEntry);
            return new ResponseEntity<>(oldEntry, HttpStatus.OK);
        }
       return new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }


    //ORM - object relational mapping java object to database tables
    //JPA java persistence API - ORM tools/ persistence provider like hibernate, eclipseLink
    //springdatajpa - built on top of JPA
    //but jpa is not used in MongoDB since it works only on relational database but
    //mongobb is a collection which has no predefined schema
    //so we use SpringData Mongodb dependency
    //Query Method DSL and Criteria API two ways to interact with database using spring data Jpa
    //
}
