package com.example.crashcourse.controller;


import com.example.crashcourse.entity.JournalEntry;
import com.example.crashcourse.service.JournalEntryService;
import org.bson.types.ObjectId;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

//specail componetns handles http request
@RestController
@RequestMapping("/journal")
//applies reuqest mapping on the whole class
public class JournalEntryControllerv2 {

    private Map<Long, JournalEntry> journalEntries = new HashMap<>();

    @Autowired
    private JournalEntryService journalEntryService;

    @GetMapping
    public List<JournalEntry> getAll() {
        return journalEntryService.getAllEntry();

    }

    @PostMapping
    //@requestbody - hey spring get the data from request and convert it into java object
    public JournalEntry createEntry(@RequestBody JournalEntry myEntry) {
        journalEntryService.saveEntry(myEntry);
        return myEntry;


    }

    //pathvariable
    //requestVariable

    @GetMapping("id/{id}")
    public JournalEntry getJournalEntryById(@PathVariable ObjectId id) {
        return journalEntries.get(id);

    }

    @DeleteMapping("id/{id}")
    public JournalEntry deleteJournalEntryById(@PathVariable ObjectId id) {
        return journalEntries.remove(id);
    }

    @PutMapping("id/{id}")
    public JournalEntry updateJournalById(@PathVariable ObjectId Id, @RequestBody JournalEntry newEntry) {

        JournalEntry oldEntry = journalEntryService.getJournalbyId(Id).orElse(null);
        if(oldEntry != null)
        {
            oldEntry.setTitle((newEntry.getTitle()!=null && !newEntry.getTitle().equals("") ? newEntry.getTitle() : oldEntry.getTitle()));
            oldEntry.setContent((newEntry.getContent()!=null && !newEntry.getContent().equals(""))? newEntry.getContent() : oldEntry.getContent());
        }
        journalEntryService.saveEntry(oldEntry);
        return oldEntry;
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
