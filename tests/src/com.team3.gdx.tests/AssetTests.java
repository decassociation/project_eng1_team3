package com.team3.gdx.tests;

import com.badlogic.gdx.Gdx;
import org.junit.Test;
import org.junit.runner.RunWith;

import static org.junit.Assert.assertTrue;

@RunWith(GdxTestRunner.class)
public class AssetTests {

    @Test
    public void testChefAssetsExist(){
        assertTrue("Cook 1 standard asset exists test", Gdx.files.internal("entities/cook_walk_1.png").exists());
        assertTrue("Cook 2 standard asset exists test", Gdx.files.internal("entities/cook_walk_2.png").exists());
        assertTrue("Cook 3 standard asset exists test", Gdx.files.internal("entities/cook_walk_3.png").exists());
        assertTrue("Cook 1 hand asset exists test", Gdx.files.internal("entities/cook_walk_hands_1.png").exists());
        assertTrue("Cook 2 hand asset exists test", Gdx.files.internal("entities/cook_walk_hands_1.png").exists());
        assertTrue("Cook 3 hand asset exists test", Gdx.files.internal("entities/cook_walk_hands_1.png").exists());
    }
    @Test
    public void testCustAssetsExist(){

        // Customer 0
        assertTrue("Customer 0 front", Gdx.files.internal("entities/cust0f.png").exists());
        assertTrue("Customer 0 back", Gdx.files.internal("entities/cust0b.png").exists());
        assertTrue("Customer 0 left", Gdx.files.internal("entities/cust0l.png").exists());
        assertTrue("Customer 0 right", Gdx.files.internal("entities/cust0r.png").exists());
        // Customer 1
        assertTrue("Customer 1 front", Gdx.files.internal("entities/cust1f.png").exists());
        assertTrue("Customer 1 back", Gdx.files.internal("entities/cust1b.png").exists());
        assertTrue("Customer 1 left", Gdx.files.internal("entities/cust1l.png").exists());
        assertTrue("Customer 2 right", Gdx.files.internal("entities/cust2r.png").exists());
        // Customer 2
        assertTrue("Customer 2 front", Gdx.files.internal("entities/cust2f.png").exists());
        assertTrue("Customer 2 back", Gdx.files.internal("entities/cust2b.png").exists());
        assertTrue("Customer 2 left", Gdx.files.internal("entities/cust2l.png").exists());
        assertTrue("Customer 2 right", Gdx.files.internal("entities/cust2r.png").exists());
        // Customer 3
        assertTrue("Customer 3 front", Gdx.files.internal("entities/cust3f.png").exists());
        assertTrue("Customer 3 back", Gdx.files.internal("entities/cust3b.png").exists());
        assertTrue("Customer 3 left", Gdx.files.internal("entities/cust3l.png").exists());
        assertTrue("Customer 3 right", Gdx.files.internal("entities/cust3r.png").exists());
        // Customer 4
        assertTrue("Customer 4 front", Gdx.files.internal("entities/cust4f.png").exists());
        assertTrue("Customer 4 back", Gdx.files.internal("entities/cust4b.png").exists());
        assertTrue("Customer 4 left", Gdx.files.internal("entities/cust4l.png").exists());
        assertTrue("Customer 4 right", Gdx.files.internal("entities/cust4r.png").exists());

    }

    @Test
    public void testAudioAssetsExist() {
        assertTrue("testmusic in music", Gdx.files.internal("audio/music/testMusic.mp3").exists());

        //music things
        assertTrue("GameMusic.mp3 in 'uielements?'", Gdx.files.internal("uielements/GameMusic.mp3").exists());
        assertTrue("GameMusic.ogg in 'uielements?'", Gdx.files.internal("uielements/GameMusic.ogg").exists());
        assertTrue("MainScreenMusic.ogg in 'uielements?'", Gdx.files.internal("uielements/GameMusic.ogg").exists());
        assertTrue("music.mp3 in 'uielements?'", Gdx.files.internal("uielements/music.mp3").exists());

        //sound effect things
        assertTrue("cash-register-opening.mp3 in audio/soundFX", Gdx.files.internal("audio/soundFX/cash-register-opening.mp3").exists());
        assertTrue("chopping.mp3 in audio/soundFX", Gdx.files.internal("audio/soundFX/chopping.mp3").exists());
        assertTrue("frying.mp3 in audio/soundFX", Gdx.files.internal("audio/soundFX/frying.mp3").exists());
        assertTrue("money-collect.mp3 in audio/soundFX", Gdx.files.internal("audio/soundFX/money-collect.mp3").exists());
        assertTrue("testSoundFX.mp3 in audio/soundFX", Gdx.files.internal("audio/soundFX/testSoundFX.mp3").exists());
        assertTrue("timer-bell-ring.mp3 in audio/soundFX", Gdx.files.internal("audio/soundFX/timer-bell-ring.mp3").exists());

    }


    @Test
    public void testItemAssets(){

        assertTrue("burger.png in items", Gdx.files.internal("items/burger.png").exists());
        assertTrue("burger_bun.png in items", Gdx.files.internal("items/burger_bun.png").exists());
        assertTrue("burger_bun_burned.png in items", Gdx.files.internal("items/burger_bun_burned.png").exists());
        assertTrue("burger_bun_cooked.png in items", Gdx.files.internal("items/burger_bun_cooked.png").exists());
        assertTrue("burger_burned.png in items", Gdx.files.internal("items/burger_burned.png").exists());
        assertTrue("burger_cooked.png in items", Gdx.files.internal("items/burger_cooked.png").exists());
        assertTrue("lettuce.png in items", Gdx.files.internal("items/lettuce.png").exists());
        assertTrue("lettuce_chopped.png in items", Gdx.files.internal("items/lettuce_chopped.png").exists());
        assertTrue("meat.png in items", Gdx.files.internal("items/meat.png").exists());
        assertTrue("meat overcooked.png in items", Gdx.files.internal("items/meat overcooked.png").exists());
        assertTrue("meat undercooked.png in items", Gdx.files.internal("items/meat undercooked.png").exists());
        assertTrue("nil.png in items", Gdx.files.internal("items/nil.png").exists());
        assertTrue("onion.png in items", Gdx.files.internal("items/onion.png").exists());
        assertTrue("onion_chopped.png in items", Gdx.files.internal("items/onion_chopped.png").exists());
        assertTrue("patty.png in items", Gdx.files.internal("items/patty.png").exists());
        assertTrue("patty_burned.png in items", Gdx.files.internal("items/patty_burned.png").exists());
        assertTrue("patty_cooked.png in items", Gdx.files.internal("items/patty_cooked.png").exists());
        assertTrue("salad.png in items", Gdx.files.internal("items/salad.png").exists());
        assertTrue("tomato.png in items", Gdx.files.internal("items/tomato.png").exists());
        assertTrue("tomato_burned.png in items", Gdx.files.internal("items/tomato_burned.png").exists());
        assertTrue("tomato_chopped.png in items", Gdx.files.internal("items/tomato_chopped.png").exists());
        assertTrue("tomato_cooked.png in items", Gdx.files.internal("items/tomato_cooked.png").exists());
        assertTrue("unformed_patty.png in items", Gdx.files.internal("items/unformed_patty.png").exists());







    }

    @Test
    public void testLeaderboardData(){

        assertTrue("playerData.txt in leaderboarddata", Gdx.files.internal("leaderboarddata/playerData.txt").exists());

    }

    @Test
    public void testMapAssetsExist(){

        // Files in map
        assertTrue("2layers.PNG in map", Gdx.files.internal("map/2layers.PNG").exists());
        assertTrue("ENG1_tiles.tsx in map", Gdx.files.internal("map/ENG1_tiles.tsx").exists());
        assertTrue("ExampleBottomLayer.PNG in map", Gdx.files.internal("map/ExampleBottomLayer.PNG").exists());
        assertTrue("ExampleTopLayer.PNG in map", Gdx.files.internal("map/ExampleTopLayer.PNG").exists());
        assertTrue("tilemapinstructions.txt in map", Gdx.files.internal("map/tilemapinstructions.txt").exists());
        assertTrue("untitled.tmx in map", Gdx.files.internal("map/untitled.tmx").exists());

        // Files in map/art_map
        assertTrue("art_tileset.tsx in map/art_map", Gdx.files.internal("map/art_map/art_tileset.tsx").exists());
        assertTrue("art_version.tiled-project in map/art_map", Gdx.files.internal("map/art_map/art_version.tiled-project").exists());
        assertTrue("art_version.tiled-session in map/art_map", Gdx.files.internal("map/art_map/art_version.tiled-session").exists());
        assertTrue("customertest.tmx in map/art_map", Gdx.files.internal("map/art_map/customertest.tmx").exists());

        // Files in map/art_map/art_images
        assertTrue("bunsbox.png in map/art_map/art_images", Gdx.files.internal("map/art_map/art_images/bunsbox.png").exists());
        assertTrue("burgercrafter.png in map/art_map/art_images", Gdx.files.internal("map/art_map/art_images/burgercrafter.png").exists());
        assertTrue("chef1 - back.png in map/art_map/art_images", Gdx.files.internal("map/art_map/art_images/chef1 - back.png").exists());
        assertTrue("chef1 - back - hold up.png in map/art_map/art_images", Gdx.files.internal("map/art_map/art_images/chef1 - back - hold up.png").exists());
        assertTrue("chef1 - front.png in map/art_map/art_images", Gdx.files.internal("map/art_map/art_images/chef1 - front.png").exists());
        assertTrue("chef1 - front - hold up.png in map/art_map/art_images", Gdx.files.internal("map/art_map/art_images/chef1 - front - hold up.png").exists());
        assertTrue("chef 1 - facing left.png in map/art_map/art_images", Gdx.files.internal("map/art_map/art_images/chef 1 - facing left.png").exists());
        assertTrue("chef 1 - facing left - hold up..png in map/art_map/art_images", Gdx.files.internal("map/art_map/art_images/chef 1 - facing left - hold up..png").exists());
        assertTrue("chef 1 - facing right.png in map/art_map/art_images", Gdx.files.internal("map/art_map/art_images/chef 1 - facing right.png").exists());
        assertTrue("chef 1 - facing right - hold up.png in map/art_map/art_images", Gdx.files.internal("map/art_map/art_images/chef 1 - facing right - hold up.png").exists());
        assertTrue("chef2 - back.png in map/art_map/art_images", Gdx.files.internal("map/art_map/art_images/chef2 - back.png").exists());
        assertTrue("chef2 - back - hold up.png in map/art_map/art_images", Gdx.files.internal("map/art_map/art_images/chef2 - back - hold up.png").exists());
        assertTrue("chef2 - front.png in map/art_map/art_images", Gdx.files.internal("map/art_map/art_images/chef2 - front.png").exists());
        assertTrue("chef2 - front - hold up.png in map/art_map/art_images", Gdx.files.internal("map/art_map/art_images/chef2 - front - hold up.png").exists());
        assertTrue("chef 2 - facing left.png in map/art_map/art_images", Gdx.files.internal("map/art_map/art_images/chef 2 - facing left.png").exists());
        assertTrue("chef 2 - facing left - hold up..png in map/art_map/art_images", Gdx.files.internal("map/art_map/art_images/chef 2 - facing left - hold up.png").exists());
        assertTrue("chef 2 - facing right.png in map/art_map/art_images", Gdx.files.internal("map/art_map/art_images/chef 2 - facing right.png").exists());
        assertTrue("chef 2 - facing right - hold up.png in map/art_map/art_images", Gdx.files.internal("map/art_map/art_images/chef 2 - facing right - hold up.png").exists());
        assertTrue("chef3 - back.png in map/art_map/art_images", Gdx.files.internal("map/art_map/art_images/chef3 - back.png").exists());
        assertTrue("chef3 - back - hold up.png in map/art_map/art_images", Gdx.files.internal("map/art_map/art_images/chef3 - back - hold up.png").exists());
        assertTrue("chef3 - front.png in map/art_map/art_images", Gdx.files.internal("map/art_map/art_images/chef3 - front.png").exists());
        assertTrue("chef3 - front - hold up.png in map/art_map/art_images", Gdx.files.internal("map/art_map/art_images/chef3 - front - hold up.png").exists());
        assertTrue("chef 3 - facing left.png in map/art_map/art_images", Gdx.files.internal("map/art_map/art_images/chef 3 - facing left.png").exists());
        assertTrue("chef 3 - facing left - hold up..png in map/art_map/art_images", Gdx.files.internal("map/art_map/art_images/chef 3 - facing left - hold up.png").exists());
        assertTrue("chef 3 - facing right.png in map/art_map/art_images", Gdx.files.internal("map/art_map/art_images/chef 3 - facing right.png").exists());
        assertTrue("chef 3 - facing right - hold up.png in map/art_map/art_images", Gdx.files.internal("map/art_map/art_images/chef 3 - facing right - hold up.png").exists());
        assertTrue("chopping.png in map/art_map/art_images", Gdx.files.internal("map/art_map/art_images/chopping.png").exists());
        assertTrue("cnr.png in map/art_map/art_images", Gdx.files.internal("map/art_map/art_images/cnr.png").exists());
        assertTrue("ctrfr.png in map/art_map/art_images", Gdx.files.internal("map/art_map/art_images/ctrfr.png").exists());
        assertTrue("ctrtl.png in map/art_map/art_images", Gdx.files.internal("map/art_map/art_images/ctrtl.png").exists());
        assertTrue("ctrtr.png in map/art_map/art_images", Gdx.files.internal("map/art_map/art_images/ctrtr.png").exists());
        assertTrue("customer tile 1.png in map/art_map/art_images", Gdx.files.internal("map/art_map/art_images/customer tile 1.png").exists());
        assertTrue("customer tile 2.png in map/art_map/art_images", Gdx.files.internal("map/art_map/art_images/customer tile 2.png").exists());
        assertTrue("customer tile 3.png in map/art_map/art_images", Gdx.files.internal("map/art_map/art_images/customer tile 3.png").exists());
        assertTrue("Double Counter.png in map/art_map/art_images", Gdx.files.internal("map/art_map/art_images/Double Counter.png").exists());
        assertTrue("Double Counter and chopping bar..png in map/art_map/art_images", Gdx.files.internal("map/art_map/art_images/Double Counter and chopping bar..png").exists());
        assertTrue("Double Counter and Salad bar..png in map/art_map/art_images", Gdx.files.internal("map/art_map/art_images/Double Counter and Salad bar..png").exists());
        assertTrue("Double Counter burger bar.png in map/art_map/art_images", Gdx.files.internal("map/art_map/art_images/Double Counter burger bar.png").exists());
        assertTrue("Double Counter w burger crate.png in map/art_map/art_images", Gdx.files.internal("map/art_map/art_images/Double Counter w burger crate.png").exists());
        assertTrue("Double Counter w food crate.png in map/art_map/art_images", Gdx.files.internal("map/art_map/art_images/Double Counter w food crate.png").exists());
        assertTrue("emptygrill.png in map/art_map/art_images", Gdx.files.internal("map/art_map/art_images/emptygrill.png").exists());
        assertTrue("Floor tile.png in map/art_map/art_images", Gdx.files.internal("map/art_map/art_images/Floor tile.png").exists());
        assertTrue("Front desk counter.png in map/art_map/art_images", Gdx.files.internal("map/art_map/art_images/Front desk counter.png").exists());
        assertTrue("lettuce.png in map/art_map/art_images", Gdx.files.internal("map/art_map/art_images/lettuce.png").exists());
        assertTrue("linectr.png in map/art_map/art_images", Gdx.files.internal("map/art_map/art_images/linectr.png").exists());
        assertTrue("onioncrate.png in map/art_map/art_images", Gdx.files.internal("map/art_map/art_images/onioncrate.png").exists());
        assertTrue("Oven (clean).png in map/art_map/art_images", Gdx.files.internal("map/art_map/art_images/Oven (clean).png").exists());
        assertTrue("Oven (messy).png in map/art_map/art_images", Gdx.files.internal("map/art_map/art_images/Oven (messy).png").exists());
        assertTrue("ovenbotcln.png in map/art_map/art_images", Gdx.files.internal("map/art_map/art_images/ovenbotcln.png").exists());
        assertTrue("ovenbotmes.png in map/art_map/art_images", Gdx.files.internal("map/art_map/art_images/ovenbotmes.png").exists());
        assertTrue("oventopmes.png in map/art_map/art_images", Gdx.files.internal("map/art_map/art_images/oventopmes.png").exists());
        assertTrue("ovtopcln.png in map/art_map/art_images", Gdx.files.internal("map/art_map/art_images/ovtopcln.png").exists());
        assertTrue("pattypile.png in map/art_map/art_images", Gdx.files.internal("map/art_map/art_images/pattypile.png").exists());
        assertTrue("pln.png in map/art_map/art_images", Gdx.files.internal("map/art_map/art_images/pln.png").exists());
        assertTrue("register.png in map/art_map/art_images", Gdx.files.internal("map/art_map/art_images/register.png").exists());
        assertTrue("Salad_top.png in map/art_map/art_images", Gdx.files.internal("map/art_map/art_images/Salad_top.png").exists());
        assertTrue("Single Counter.png in map/art_map/art_images", Gdx.files.internal("map/art_map/art_images/Single Counter.png").exists());
        assertTrue("Single Counter Sideways.png in map/art_map/art_images", Gdx.files.internal("map/art_map/art_images/Single Counter Sideways.png").exists());
        assertTrue("tomato.png in map/art_map/art_images", Gdx.files.internal("map/art_map/art_images/tomato.png").exists());
        assertTrue("Top Counter.png in map/art_map/art_images", Gdx.files.internal("map/art_map/art_images/Top Counter.png").exists());
        assertTrue("Top Oven.png in map/art_map/art_images", Gdx.files.internal("map/art_map/art_images/Top Oven.png").exists());
        assertTrue("Trash can.png in map/art_map/art_images", Gdx.files.internal("map/art_map/art_images/Trash can.png").exists());

        // map/old files
        assertTrue("64pxshittytiles.png in map/old", Gdx.files.internal("map/old/64pxshittytiles.png").exists());
        assertTrue("64pxshittytiles.tsx in map/old", Gdx.files.internal("map/old/64pxshittytiles.tsx").exists());
        assertTrue("test.tmx in map/old", Gdx.files.internal("map/old/test.tmx").exists());
        assertTrue("test (1).tmx in map/old", Gdx.files.internal("map/old/test (1).tmx").exists());
    }

    @Test
    public void testParticlesAssetsExist(){
        assertTrue("flame.png", Gdx.files.internal("particles/flame.png").exists());
        assertTrue("flames.party", Gdx.files.internal("particles/flames.party").exists());
        assertTrue("smoke.png", Gdx.files.internal("particles/smoke.png").exists());
        assertTrue("smokes.party", Gdx.files.internal("particles/smokes.party").exists());
    }

    @Test
    public void testUIElementsExist(){
        assertTrue("audio.png in uielements", Gdx.files.internal("uielements/audio.png").exists());
        assertTrue("audio2.png in uielements", Gdx.files.internal("uielements/audio2.png").exists());
        assertTrue("background.png in uielements", Gdx.files.internal("uielements/background.png").exists());
        assertTrue("background1.png in uielements", Gdx.files.internal("uielements/background1.png").exists());
        assertTrue("badlogic.jpg in uielements", Gdx.files.internal("uielements/badlogic.jpg").exists());
        assertTrue("Eng1sound.wav in uielements", Gdx.files.internal("uielements/Eng1sound.wav").exists());
        assertTrue("ESC.jpg in uielements", Gdx.files.internal("uielements/ESC.jpg").exists());
        assertTrue("exitgame.png in uielements", Gdx.files.internal("uielements/exitgame.png").exists());
        assertTrue("exitmenu.png in uielements", Gdx.files.internal("uielements/exitmenu.png").exists());
        assertTrue("font.fnt in uielements", Gdx.files.internal("uielements/font.fnt").exists());
        assertTrue("font.png in uielements", Gdx.files.internal("uielements/font.png").exists());
        assertTrue("GameMusic.mp3 in uielements", Gdx.files.internal("uielements/GameMusic.mp3").exists());
        assertTrue("GameMusic.ogg in uielements", Gdx.files.internal("uielements/GameMusic.ogg").exists());
        assertTrue("leaderBoard.jpg in uielements", Gdx.files.internal("uielements/leaderBoard.jpg").exists());
        assertTrue("LeaderBoard.png in uielements", Gdx.files.internal("uielements/LeaderBoard.png").exists());
        assertTrue("leaderboard .jpg in uielements", Gdx.files.internal("uielements/leaderboard .jpg").exists());
        assertTrue("leaderboard1.png in uielements", Gdx.files.internal("uielements/leaderboard1.png").exists());
        assertTrue("line.jpg in uielements", Gdx.files.internal("uielements/line.jpg").exists());
        assertTrue("Main.jpg in uielements", Gdx.files.internal("uielements/Main.jpg").exists());
        assertTrue("MainScreenBackground.jpg in uielements", Gdx.files.internal("uielements/MainScreenBackground.jpg").exists());
        assertTrue("MainScreenMusic.ogg in uielements", Gdx.files.internal("uielements/MainScreenMusic.ogg").exists());
        assertTrue("MENU.jpg in uielements", Gdx.files.internal("uielements/MENU.jpg").exists());
        assertTrue("music.mp3 in uielements", Gdx.files.internal("uielements/music.mp3").exists());
        assertTrue("newgame.png in uielements", Gdx.files.internal("uielements/newgame.png").exists());
        assertTrue("resume.jpg in uielements", Gdx.files.internal("uielements/resume.jpg").exists());
        assertTrue("resume.png in uielements", Gdx.files.internal("uielements/resume.png").exists());
        assertTrue("settings.png in uielements", Gdx.files.internal("uielements/settings.png").exists());
        assertTrue("startButton.jpg in uielements", Gdx.files.internal("uielements/startButton.jpg").exists());
        assertTrue("testsound.mp3 in uielements", Gdx.files.internal("uielements/testsound.mp3").exists());
        assertTrue("vButton.jpg in uielements", Gdx.files.internal("uielements/vButton.jpg").exists());
        assertTrue("vControl.jpg in uielements", Gdx.files.internal("uielements/vControl.jpg").exists());
        assertTrue("vControl.png in uielements", Gdx.files.internal("uielements/vControl.png").exists());
        assertTrue("downArrow.png in uielements", Gdx.files.internal("uielements/downArrow.png").exists());
        assertTrue("upArrow.png in uielements", Gdx.files.internal("uielements/upArrow.png").exists());
        assertTrue("go.png in uielements", Gdx.files.internal("uielements/go.png").exists());

    }




}