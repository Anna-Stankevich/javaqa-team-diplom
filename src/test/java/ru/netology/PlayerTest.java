package ru.netology;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;

public class PlayerTest {

    private GameStore store = new GameStore();
    Game game1 = store.publishGame("Igra1", "Fights");
    Game game2 = store.publishGame("Igra2", "Fights");
    Game game3 = store.publishGame("Igra3", "Race");
    Game game4 = store.publishGame("Igra4", "Race");
    Game game5 = store.publishGame("Igra5", "MMORPG");
    Game game6 = store.publishGame("Igra6", "MMORPG");
    Game game7 = store.publishGame("Igra7", "Аркады");
    Game game8 = store.publishGame("Igra8", "Аркады");

    @Test
    public void shouldSumGenreIfOneGame() {

        Player player = new Player("Petya");
        player.installGame(game1);
        player.play(game1, 3);

        int expected = 3;
        int actual = player.sumGenre(game1.getGenre());
        assertEquals(expected, actual);
    }

    @Test
    public void shouldSumGenreIfTwoGame() {

        Player player = new Player("Petya");
        player.installGame(game1);
        player.installGame(game2);
        player.play(game1, 3);
        player.play(game2, 3);

        int expected = 6;
        int actual = player.sumGenre(game1.getGenre());
        assertEquals(expected, actual);
    }

    @Test
    public void shouldSumGenreIfThreeGames() {

        Player player = new Player("Petya");
        player.installGame(game1);
        player.play(game1, 3);
        player.installGame(game2);
        player.play(game2, 5);
        player.installGame(game3);
        player.play(game3, 5);

        int expected = 8;
        int actual = player.sumGenre(game1.getGenre());
        assertEquals(expected, actual);
    }

    @Test
    public void shouldExpIfGameNotInstall() {

        Player player = new Player("Petya");

        assertThrows(RuntimeException.class, () -> {
            player.play(game2, 5);
        });
    }

    @Test
    public void mostPlayerByGenreGamePlayed() {
        Player player = new Player("Petya");
        player.installGame(game1);
        player.installGame(game2);
        player.installGame(game3);

        player.play(game1, 6);
        player.play(game2, 5);
        player.play(game3, 3);


        Game expected = game1;
        Game actual = player.mostPlayerByGenre("Fights");

        assertEquals(expected, actual);
    }


    @Test
    public void shouldSumGenreIfGameReplay() {

        Player player = new Player("Petya");
        player.installGame(game1);
        player.installGame(game5);
        player.installGame(game4);

        player.play(game1, 3);
        player.play(game1, 3);


        int expected = 6;
        int actual = player.sumGenre("Fights");

        assertEquals(expected, actual);
    }

    @Test
    public void shouldSumGenreIfTwoGames() {

        Player player = new Player("Petya");
        player.installGame(game1);
        player.installGame(game2);
        player.installGame(game4);

        player.play(game1, 1);
        player.play(game2, 2);
        player.play(game4, 4);

        int expected = 3;
        int actual = player.sumGenre("Fights");

        assertEquals(expected, actual);
    }

}
