package com.team3.gdx.tests;

import com.badlogic.gdx.math.Vector2;
import com.team3gdx.game.station.Station;
import com.team3gdx.game.station.StationManager;
import org.junit.Test;
import org.junit.runner.RunWith;

import static org.junit.Assert.*;

@RunWith(GdxTestRunner.class)
public class StationTests {

    @Test
    public void testCreateStation() {
        //All stations are child classes of Station
        Vector2 pos = new Vector2(0,0);
        Station station = new Station(pos, 0, true, null, null);
        StationManager.stations.put(pos, station);
        assertTrue("Station should be instantiated at position 0,0",
                StationManager.stations.get(pos) == station);
    }
}
