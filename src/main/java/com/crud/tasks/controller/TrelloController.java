package com.crud.tasks.controller;

import com.crud.tasks.trello.client.TrelloClient;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/v1/trello")
public class TrelloController {

    @Autowired
    private TrelloClient trelloClient;

    @RequestMapping(method = RequestMethod.GET, value = "getTrelloBoards")
    public void getTrelloBoards() {
        trelloClient.getTrelloBoards().ifPresent(l -> l.stream()
                .filter(trelloBoardDto -> (trelloBoardDto.getName() != null) && (trelloBoardDto.getId() != null) && (trelloBoardDto.getName().length() > 0) && (trelloBoardDto.getId().length() > 0))
                .filter(trelloBoardDto -> trelloBoardDto.getName().contains("Kodilla"))
                .forEach(trelloBoardDto -> System.out.println(trelloBoardDto.getId() + " " + trelloBoardDto.getName())));
    }
}