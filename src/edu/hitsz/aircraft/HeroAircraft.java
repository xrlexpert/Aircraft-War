package edu.hitsz.aircraft;

import edu.hitsz.aircraft.strategy.DirectShootStrategy;
import edu.hitsz.application.game.GameConfig;
import edu.hitsz.application.ImageManager;
import edu.hitsz.application.Main;

/**
 * 英雄飞机，游戏玩家操控
 * @author hitsz
 */
public class HeroAircraft extends AbstractAircraft {

    /**
     * @param locationX 英雄机位置x坐标
     * @param locationY 英雄机位置y坐标
     * @param speedX 英雄机射出的子弹的基准速度（英雄机无特定速度）
     * @param speedY 英雄机射出的子弹的基准速度（英雄机无特定速度）
     * @param hp    初始生命值
     */
    public static int hero_basicHp = 2000;

    public static void setHero_basicFire(int hero_basicFire) {
        HeroAircraft.hero_basicFire = hero_basicFire;
        if(hero_basicFire > heroaircraft.getShootNum()){
            heroaircraft.setShootNum(hero_basicFire);
        }

    }

    public static int hero_basicFire = 1;


    private volatile static HeroAircraft heroaircraft;
    public static HeroAircraft getHeroAircraft(){
        if(heroaircraft == null){
            synchronized(HeroAircraft.class){
                if(heroaircraft == null){
                    heroaircraft =  new HeroAircraft(
                            Main.WINDOW_WIDTH / 2,
                            Main.WINDOW_HEIGHT - ImageManager.HERO_IMAGE.getHeight() ,
                            0, 0, hero_basicHp);
                }
            }
        }
        return heroaircraft;
    }
    private HeroAircraft(int locationX, int locationY, int speedX, int speedY, int hp) {
        super(locationX, locationY, speedX, speedY, hp);
        this.shootStrategy = new DirectShootStrategy();
        this.shootNum = 1;
        this.direction  = -1;
        this.power = 30;
    }

    @Override
    public void forward() {
        // 英雄机由鼠标控制，不通过forward函数移动
    }

    public void increaseHp(int increase) {
        hp += increase;
        if (hp >= maxHp) {
            hp = maxHp;
        }
    }

}
