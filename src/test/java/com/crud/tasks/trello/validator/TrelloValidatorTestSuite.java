package com.crud.tasks.trello.validator;

import com.crud.tasks.domain.*;
import org.junit.Assert;
import org.junit.Ignore;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.runners.MockitoJUnitRunner;

import java.util.ArrayList;
import java.util.List;

@RunWith(MockitoJUnitRunner.class)
public class TrelloValidatorTestSuite {
    @InjectMocks
    TrelloValidator trelloValidator;

    @Ignore
    @Test
    public void testValidateCardDuringTest() {
        //Given
        TrelloCard trelloCard = new TrelloCard("test card", "desc", "pos", "id");
        //When & Then
        trelloValidator.validateCard(trelloCard);
        //Assert.assertThat(logger.getLoggingEvents(), is(asList(info("Someone is testing my application!"))));
    }

    @Test
    public void testValidteTrelloBoardsInTest() {
        //Given
        List<TrelloList> trelloLists = new ArrayList<>();
        trelloLists.add(new TrelloList("1", "my_list", false));
        List<TrelloBoard> trelloBoards = new ArrayList<>();
        trelloBoards.add(new TrelloBoard("1","test",  trelloLists));
        //When
        List<TrelloBoard> testedTrelloBoards = trelloValidator.validateTrelloBoards(trelloBoards);
        //Then
        Assert.assertEquals(0, testedTrelloBoards.size());
    }

    @Test
    public void testValidteTrelloBoardsWithoutTest() {
        //Given
        List<TrelloList> trelloLists = new ArrayList<>();
        trelloLists.add(new TrelloList("1", "my_list", false));
        List<TrelloBoard> trelloBoards = new ArrayList<>();
        trelloBoards.add(new TrelloBoard("1","my_board",  trelloLists));
        //When
        List<TrelloBoard> testedTrelloBoards = trelloValidator.validateTrelloBoards(trelloBoards);
        //Then
        Assert.assertEquals(1, testedTrelloBoards.size());
        Assert.assertEquals("my_board", testedTrelloBoards.get(0).getName());
        Assert.assertEquals("1", testedTrelloBoards.get(0).getId());
        Assert.assertEquals(1, testedTrelloBoards.get(0).getLists().size());
        Assert.assertEquals("1", testedTrelloBoards.get(0).getLists().get(0).getId());
        Assert.assertEquals("my_list", testedTrelloBoards.get(0).getLists().get(0).getName());
        Assert.assertFalse(testedTrelloBoards.get(0).getLists().get(0).isClosed());
    }
}
