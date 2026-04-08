package com.example.crashcourse.entity;

import lombok.Data;
import lombok.NoArgsConstructor;
import org.bson.types.ObjectId;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.index.Indexed;
import org.springframework.data.mongodb.core.mapping.DBRef;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.lang.NonNull;

import java.util.ArrayList;
import java.util.List;

//user can see the journal entries of the user
@Document(collection = "users")
@Data
@NoArgsConstructor
public class User {
    @Id
    private ObjectId userId;
    //to make username unique use indexing
    @Indexed(unique = true)
    @NonNull //cannot be null part of lambok during setter call null point is checked
    private String username;
    @NonNull
    private String password;

    //to link journalentry and users collection linking use DBRef
    @DBRef  //inside users collection a reference of journal entry is created
    private List<JournalEntry> journalEntries = new ArrayList<>(); //acts as foreign key
}
