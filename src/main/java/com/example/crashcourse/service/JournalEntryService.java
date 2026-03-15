package com.example.crashcourse.service;

import com.example.crashcourse.entity.JournalEntry;
import com.example.crashcourse.repository.JournalEntryRepository;
import org.bson.types.ObjectId;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Optional;

@Component
public class JournalEntryService {

    //business layer

//DI
    @Autowired
    private JournalEntryRepository journalEntryRepository;

    public void saveEntry(JournalEntry journalEntry){
        journalEntryRepository.save(journalEntry);
    }

    public List<JournalEntry> getAllEntry(){
        return journalEntryRepository.findAll();
    }

    public Optional<JournalEntry> getJournalbyId(ObjectId id){

        return journalEntryRepository.findById(id);

    }

    public void deleteById(ObjectId Id){
        journalEntryRepository.deleteById(Id);
    }


}
