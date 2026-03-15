package com.example.crashcourse.repository;

import com.example.crashcourse.entity.JournalEntry;
import org.bson.types.ObjectId;
import org.springframework.data.mongodb.repository.MongoRepository;

//controller -> service -> repositroy
public interface JournalEntryRepository extends MongoRepository<JournalEntry, ObjectId> {
    //MongoRepository - > interface given by mongodb has predifened CRUD operations
    //<Enitity, ID type >

}
