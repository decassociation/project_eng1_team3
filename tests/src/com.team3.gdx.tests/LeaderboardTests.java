package com.team3.gdx.tests;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.files.FileHandle;
import com.team3gdx.game.screen.LeaderBoard;
import org.junit.Assert;
import org.junit.Test;

import static org.junit.Assert.assertTrue;

public class LeaderboardTests {

    private FileHandle file = Gdx.files.local("leaderboarddata/playerData.txt");

    @Test
    public void testAdd(){
        // back data up and wipe file - leaderboard file starts with an 's'
        String backup = file.readString();
        file.writeString("s", false);

        LeaderBoard leaderBoard = new LeaderBoard(null, null);
        leaderBoard.addLeaderBoardData("player name", 1);
        assertTrue(file.readString() == "s\nplayer name;1");

        // restore backup
        file.writeString(backup, false);
    }
}
