package ru.netology;

import static org.junit.jupiter.api.Assertions.*;
import static org.junit.jupiter.api.Assertions.assertTrue;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.BeforeEach;

public class GameStoreTest {

    GameStore store;

    @BeforeEach
    public void beforeEach() {
        this.store = new GameStore();
    }

    //Тесты по созданию объекта игры в каталоге;

    @Test
    public void shouldAddGame() {
        Game game = store.publishGame("Нетология Баттл Онлайн", "Аркады");

        assertTrue(store.containsGame(game));
    }

    @Test
    public void GameRemembersOwnStore() {
        Game game = store.publishGame("Нетология Баттл Онлайн", "Аркады");
        Game expect = new Game("Нетология Баттл Онлайн", "Аркады", store);

        assertEquals(expect, game);
    }


    @Test
    public void GameNotInStore() {
        Game game = new Game("another", "another", store);

        assertFalse(store.containsGame(game));
    }

    //Тесты по информация о проигранном времени игроками(в том числе статистика);

    @Test
    public void CheckMethodGetMostPlayer_WithoutValue() {
        assertNull(store.getMostPlayer());
    }

    @Test
    public void CheckMethodGetMostPlayer_WithValue() {
        store.addPlayTime("Вася", 2);
        store.addPlayTime("Петя", 5);
        store.addPlayTime("Оля", 1);
        assertEquals("Петя", store.getMostPlayer());
    }

    @Test
    public void CheckMethodGetMostPlayer_CheckSumPastValue() {
        store.addPlayTime("Вася", 5);
        store.addPlayTime("Петя", 2);
        store.addPlayTime("Вася", 1);
        store.addPlayTime("Петя", 5);
        assertEquals("Петя", store.getMostPlayer());
    }

    @Test
    public void CheckMethodGetSumPlayedTime_WithoutValue() {
        assertEquals(0, store.getSumPlayedTime());
    }

    @Test
    public void CheckMethodGetSumPlayedTime_WithValue() {
        store.addPlayTime("Вася", 2);
        store.addPlayTime("Петя", 5);
        store.addPlayTime("Оля", 1);
        assertEquals(8, store.getSumPlayedTime());
    }

    @Test
    public void CheckMethodGetSumPlayedTime_CheckSumPastValue() {
        store.addPlayTime("Вася", 2);
        store.addPlayTime("Петя", 5);
        store.addPlayTime("Вася", 3);
        store.addPlayTime("Петя", 2);
        assertEquals(12, store.getSumPlayedTime());

    }

}
