package edu.hitsz.aircraft.strategy;

import edu.hitsz.aircraft.AbstractAircraft;
import edu.hitsz.bullet.BaseBullet;

import java.util.List;

public interface AbstractShootStrategy {
    public abstract List<BaseBullet> shoot(AbstractAircraft aircraft);

}
