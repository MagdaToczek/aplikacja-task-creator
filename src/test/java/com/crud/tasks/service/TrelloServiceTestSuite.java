package com.crud.tasks.service;

import com.crud.tasks.config.AdminConfig;
import com.crud.tasks.domain.*;
import com.crud.tasks.trello.client.TrelloClient;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.runners.MockitoJUnitRunner;

import java.util.ArrayList;
import java.util.List;

import static org.mockito.Mockito.when;

@RunWith(MockitoJUnitRunner.class)
public class TrelloServiceTestSuite {
    @InjectMocks
    private TrelloService trelloService;

    @Mock
    private TrelloClient trelloClient;

    @Mock
    private SimpleEmailService emailService;

    @Mock
    private AdminConfig adminConfig;

    @Test
    public void shouldFetchAllBoards() {
        //Given
        List<TrelloListDto> trelloLists = new ArrayList<>();
        trelloLists.add(new TrelloListDto("1", "test_list", false));
        List<TrelloBoardDto> trelloBoads = new ArrayList<>();
        trelloBoads.add(new TrelloBoardDto("my_task","1",  trelloLists));
        when(trelloClient.getTrelloBoards()).thenReturn(trelloBoads);
        //When
        List<TrelloBoardDto> trelloBoardDtos = trelloService.fetchTrelloBoards();
        //Then
        Assert.assertEquals(1, trelloBoardDtos.size());
        Assert.assertEquals("my_task", trelloBoardDtos.get(0).getName());
        Assert.assertEquals("1", trelloBoardDtos.get(0).getId());
        Assert.assertEquals(trelloLists, trelloBoardDtos.get(0).getLists());
    }

    @Test
    public void shouldCreateTrelloCard() {
        //Given
        Trello trello = new Trello(1,2);
        TrelloCardDto trelloCardDto = new TrelloCardDto("test name", "test description", "test pos", "test id");
        CreatedTrelloCardDto createdTrelloCardDto = new CreatedTrelloCardDto("1", "name", "short url", new Badge(1, new AttachmentByType(new Trello())));
        when(trelloClient.createNewCard(trelloCardDto)).thenReturn(createdTrelloCardDto);
        //When
        CreatedTrelloCardDto createdCardDto = trelloService.createTrelloCard(trelloCardDto);
        //Then
        Assert.assertEquals("1", createdCardDto.getId());
        Assert.assertEquals("name",createdCardDto.getName());
        Assert.assertEquals("short url", createdCardDto.getShortUrl());
        Assert.assertEquals(1, createdCardDto.getBadges().getVotes());
        Assert.assertEquals(0, createdCardDto.getBadges().getAttachments().getTrello().getBoard());
        Assert.assertEquals(0, createdCardDto.getBadges().getAttachments().getTrello().getCard());
    }
}
