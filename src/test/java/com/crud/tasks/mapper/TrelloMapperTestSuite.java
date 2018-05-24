package com.crud.tasks.mapper;

import com.crud.tasks.domain.*;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.ArrayList;
import java.util.List;

@RunWith(SpringRunner.class)
@SpringBootTest
public class TrelloMapperTestSuite {
    @Autowired
    private TrelloMapper trelloMapper;

    @Test
    public void testMapToBoards() {
        //Given
        List<TrelloBoardDto> boardDtos = new ArrayList<>();
        boardDtos.add(new TrelloBoardDto("new test board", "01", new ArrayList<>()));
        List<TrelloBoard> boards = new ArrayList<>();
        boards.add(new TrelloBoard("01", "new test board", new ArrayList<>()));
        //When
        List<TrelloBoard> mappedBoardDtos = trelloMapper.mapToBoards(boardDtos);
        //Then
        Assert.assertEquals(boards.get(0).getName(), mappedBoardDtos.get(0).getName());
        Assert.assertEquals(boards.get(0).getId(), mappedBoardDtos.get(0).getId());
        Assert.assertEquals(boards.get(0).getLists(), mappedBoardDtos.get(0).getLists());
    }

    @Test
    public void testMapToBoardsDto() {
        //Given
        List<TrelloBoardDto> boardDtos = new ArrayList<>();
        boardDtos.add(new TrelloBoardDto("new test board", "01", new ArrayList<>()));
        List<TrelloBoard> boards = new ArrayList<>();
        boards.add(new TrelloBoard("01", "new test board", new ArrayList<>()));
        //When
        List<TrelloBoardDto> mappedBoards = trelloMapper.mapToBoardsDto(boards);
        //Then
        Assert.assertEquals(boardDtos.get(0).getName(), mappedBoards.get(0).getName());
        Assert.assertEquals(boardDtos.get(0).getId(), mappedBoards.get(0).getId());
        Assert.assertEquals(boardDtos.get(0).getLists(), mappedBoards.get(0).getLists());
    }

    @Test
    public void testMapToList() {
        //Given
        List<TrelloListDto> listDtos = new ArrayList<>();
        listDtos.add(new TrelloListDto("01", "new test list", true));
        List<TrelloList> lists = new ArrayList<>();
        lists.add(new TrelloList("01", "new test list", true));
        //When
        List<TrelloList> mappedListDtos = trelloMapper.mapToList(listDtos);
        //Then
        Assert.assertEquals(lists.get(0).getId(), mappedListDtos.get(0).getId());
        Assert.assertEquals(lists.get(0).getName(), mappedListDtos.get(0).getName());
        Assert.assertEquals(lists.get(0).isClosed(), mappedListDtos.get(0).isClosed());
    }

    @Test
    public void testMapToListDto() {
        //Given
        List<TrelloListDto> listDtos = new ArrayList<>();
        listDtos.add(new TrelloListDto("01", "new test list", true));
        List<TrelloList> lists = new ArrayList<>();
        lists.add(new TrelloList("01", "new test list", true));
        //When
        List<TrelloListDto> mappedLists = trelloMapper.mapToListDto(lists);
        //Then
        Assert.assertEquals(listDtos.get(0).getId(), mappedLists.get(0).getId());
        Assert.assertEquals(listDtos.get(0).getName(), mappedLists.get(0).getName());
        Assert.assertEquals(listDtos.get(0).isClosed(), mappedLists.get(0).isClosed());
    }

    @Test
    public void testMapToCardDto() {
        //Given
        TrelloCard trelloCard = new TrelloCard("test name", "test description", "test pos", "test id");
        TrelloCardDto trelloCardDto = new TrelloCardDto("test name", "test description", "test pos", "test id");
        //When
        TrelloCardDto mappedCard = trelloMapper.mapToCardDto(trelloCard);
        //Then
        Assert.assertEquals(trelloCardDto.getDescription(), mappedCard.getDescription());
        Assert.assertEquals(trelloCardDto.getListId(), mappedCard.getListId());
        Assert.assertEquals(trelloCardDto.getName(), mappedCard.getName());
        Assert.assertEquals(trelloCardDto.getPos(), mappedCard.getPos());
    }

    @Test
    public void testMapToCard() {
        //Given
        TrelloCard trelloCard = new TrelloCard("test name", "test description", "test pos", "test id");
        TrelloCardDto trelloCardDto = new TrelloCardDto("test name", "test description", "test pos", "test id");
        //When
        TrelloCard mappedCardDto = trelloMapper.mapToCard(trelloCardDto);
        //Then
        Assert.assertEquals(trelloCard.getDescription(), mappedCardDto.getDescription());
        Assert.assertEquals(trelloCard.getListId(), mappedCardDto.getListId());
        Assert.assertEquals(trelloCard.getName(), mappedCardDto.getName());
        Assert.assertEquals(trelloCard.getPos(), mappedCardDto.getPos());
    }
}
