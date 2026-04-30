package com.example.crashcourse.service;

import com.example.crashcourse.entity.JournalEntry;
import com.example.crashcourse.entity.User;
import com.example.crashcourse.repository.JournalEntryRepository;
import org.bson.types.ObjectId;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@Component
public class JournalEntryService {

    //business layer

//DI
    @Autowired
    private JournalEntryRepository journalEntryRepository;

    @Autowired
    private UserService userService;

    @Transactional
    //we achieve atomicity and also isolation too
    public void saveEntry(JournalEntry journalEntry, String username){
        try {
            User user = userService.findByUsername(username);

            JournalEntry journalEntryRef = journalEntryRepository.save(journalEntry);
            user.getJournalEntries().add(journalEntryRef);
            userService.saveUser(user);

        }catch (Exception e)
        {
            System.out.println(e);
        }

    }

    public void saveEntry(JournalEntry journalEntry){
        journalEntryRepository.save(journalEntry);
    }

    public List<JournalEntry> getAllEntry(){
        return journalEntryRepository.findAll();
    }

    public Optional<JournalEntry> getJournalbyId(ObjectId id){

        return journalEntryRepository.findById(id);

    }

    public void deleteById(ObjectId Id, String username){
        User user = userService.findByUsername(username);
        user.getJournalEntries().removeIf(x -> x.getId().equals(Id));
        userService.saveUser(user);
        journalEntryRepository.deleteById(Id);
    }


}
