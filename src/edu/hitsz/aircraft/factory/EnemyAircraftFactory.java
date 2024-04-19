package edu.hitsz.aircraft.factory;

import edu.hitsz.aircraft.AbstractAircraft;
import edu.hitsz.aircraft.AbstractEnemyAircraft;

public interface EnemyAircraftFactory {
    public abstract AbstractEnemyAircraft createAircraft();
}
