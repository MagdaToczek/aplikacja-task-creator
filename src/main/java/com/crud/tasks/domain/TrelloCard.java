package com.crud.tasks.domain;

import lombok.AllArgsConstructor;
import lombok.Getter;

import java.security.SecureRandom;

@Getter
@AllArgsConstructor
public class TrelloCard {
    private String name;
    private String description;
    private String pos;
    private String listId;


}
