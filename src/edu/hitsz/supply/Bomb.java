package edu.hitsz.supply;

import edu.hitsz.aircraft.HeroAircraft;
import edu.hitsz.application.Main;
import edu.hitsz.observer.Observer;
import edu.hitsz.thread.music.SupplyMusic;

import javax.security.auth.Subject;
import java.util.LinkedList;
import java.util.List;

public class Bomb extends BaseItem  {
    private final List<Observer> observers = new LinkedList<>();
    public Bomb(int locationX, int locationY, int speedX, int speedY){
        super(locationX, locationY, speedX, speedY);
    }
    @Override
    public void forward() {
        super.forward();
        if (locationY >= Main.WINDOW_HEIGHT ) {
            vanish();
        }
    }

    @Override
    public void work(HeroAircraft heroAircraft) {
        System.out.println("BombSupply active!");
        notifyObservers();
        SupplyMusic.music(0);
        SupplyMusic.music(1);
    }

    public void registerObserver(Observer observer) {
        observers.add(observer);

    }
    public void removeObserver(Observer observer) {
        for(Observer observerInList: observers){
            if(observerInList == observer){
                observers.remove(observerInList);
            }
        }

    }

    public void notifyObservers() {
        for(Observer observer : observers){
            observer.update();
        }

    }
}
