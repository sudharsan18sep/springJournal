package com.example.crashcourse.controller;


import com.example.crashcourse.entity.JournalEntry;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

//specail componetns handles http request
@RestController
@RequestMapping("/journal")
//applies reuqest mapping on the whole class
public class JournalEntryController {

    private Map<Long, JournalEntry> journalEntries = new HashMap<>();
    @GetMapping
    public List<JournalEntry> getAll(){
        return new ArrayList<>(journalEntries.values());

    }

    @PostMapping
    //@requestbody - hey spring get the data from request and convert it into java object
    public void createEntry(@RequestBody JournalEntry myEntry){

    }

    //pathvariable
    //requestVariable

    @GetMapping("id/{id}")
    public JournalEntry getJournalEntryById(@PathVariable Long id){
        return journalEntries.get(id);

    }

    @DeleteMapping("id/{id}")
    public JournalEntry deleteJournalEntryById(@PathVariable Long id){
        return journalEntries.remove(id);
    }

    @PutMapping("id/{id}")
    public JournalEntry updateJournalById(@PathVariable Long id, @RequestBody JournalEntry myEntry){
        return journalEntries.put(id, myEntry);
    }
}
