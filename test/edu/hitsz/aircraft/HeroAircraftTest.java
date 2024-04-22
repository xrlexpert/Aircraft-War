package edu.hitsz.aircraft;

import java.util.*;

import edu.hitsz.bullet.BaseBullet;
import org.junit.jupiter.api.*;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;

import static org.junit.jupiter.api.Assertions.*;
import static org.junit.jupiter.api.Assumptions.*;


class HeroAircraftTest {

    private  HeroAircraft heroAircraft = null;
    @BeforeEach
        //Get herocraft
    void setUp() {
        heroAircraft = HeroAircraft.getHeroAircraft();
    }
    @AfterEach
        //Destruct heroAircraft
    void tearDown() {
        heroAircraft = null;
    }

    @DisplayName("Test shoot method")
    @org.junit.jupiter.api.Test
    void shoot() {
        Assumptions.assumeTrue(heroAircraft != null);
        List<BaseBullet> bullets = heroAircraft.shoot();
        assertTrue(bullets.size() > 0);
        System.out.println("Shoot test pass");
    }
    @DisplayName("Test increaseHp method")
    @ParameterizedTest
    @CsvSource({
            "1030", "10",
    })

    void increaseHp(int increase ) {
        Assumptions.assumeTrue(increase >= 0);
        Assumptions.assumeTrue(heroAircraft != null);
        heroAircraft.hp = 30;
        int beforeHp = heroAircraft.getHp();
        heroAircraft.increaseHp(increase);
        assertEquals(Math.min(beforeHp + increase,heroAircraft.maxHp),heroAircraft.getHp());
        System.out.println("increaseHp test pass");
    }

    @DisplayName("Test decreaseHp method")
    @ParameterizedTest
    @CsvSource({
            "1030", "10",
    })
    void decreaseHp(int decrease) {
        Assumptions.assumeTrue(decrease >= 0);
        Assumptions.assumeTrue(heroAircraft != null);
        heroAircraft.hp = 30;
        int beforeHp = heroAircraft.getHp();
        heroAircraft.decreaseHp(decrease);
        assertEquals(Math.max(beforeHp - decrease,0),heroAircraft.getHp());
        System.out.println("decreaseHp test pass");
    }

}