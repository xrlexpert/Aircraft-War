package edu.hitsz.application;


import edu.hitsz.aircraft.EliteEnemy;
import edu.hitsz.aircraft.ElitePlusEnemy;
import edu.hitsz.aircraft.BossEnemy;
import edu.hitsz.aircraft.HeroAircraft;
import edu.hitsz.aircraft.MobEnemy;
import edu.hitsz.application.game.GameConfig;
import edu.hitsz.bullet.EnemyBullet;
import edu.hitsz.bullet.HeroBullet;
import edu.hitsz.supply.Blood;
import edu.hitsz.supply.Bomb;
import edu.hitsz.supply.Fire;
import edu.hitsz.supply.FirePlus;

import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

/**
 * 综合管理图片的加载，访问
 * 提供图片的静态访问方法
 *
 * @author hitsz
 */
public class ImageManager {

    /**
     * 类名-图片 映射，存储各基类的图片 <br>
     * 可使用 CLASSNAME_IMAGE_MAP.get( obj.getClass().getName() ) 获得 obj 所属基类对应的图片
     */
    private static final Map<String, BufferedImage> CLASSNAME_IMAGE_MAP = new HashMap<>();

    public static BufferedImage BACKGROUND_IMAGE;
    public static BufferedImage HERO_IMAGE;
    public static BufferedImage HERO_BULLET_IMAGE;
    public static BufferedImage ENEMY_BULLET_IMAGE;
    public static BufferedImage MOB_ENEMY_IMAGE;

    public static BufferedImage ELITE_ENEMY_IMAGE;
    public static BufferedImage ELITEPLUS_ENEMY_IMAGE;
    public static BufferedImage BOSS_ENEMY_IMAGE;


    public static BufferedImage BLOOD_IMAGE;
    public static BufferedImage BOMB_IMAGE;
    public static BufferedImage FIRE_IMAGE;
    public static BufferedImage FIREPLUS_IMAGE;
    public static final String easyGameBackGround = "src/images/bg.jpg";
    public static final String mediumGameBackGround = "src/images/bg2.jpg";
    public static final String hardGameBackGround = "src/images/bg3.jpg";





    static {
        try {
            if(GameConfig.gameMode == 0){
                BACKGROUND_IMAGE = ImageIO.read(new FileInputStream(easyGameBackGround));
            }
            else if(GameConfig.gameMode == 1){
                BACKGROUND_IMAGE = ImageIO.read(new FileInputStream(mediumGameBackGround));
            }
            else{
                BACKGROUND_IMAGE = ImageIO.read(new FileInputStream(hardGameBackGround));
            }
            HERO_IMAGE = ImageIO.read(new FileInputStream("src/images/hero.png"));
            MOB_ENEMY_IMAGE = ImageIO.read(new FileInputStream("src/images/mob.png"));
            ELITE_ENEMY_IMAGE = ImageIO.read(new FileInputStream("src/images/elite.png"));
            ELITEPLUS_ENEMY_IMAGE = ImageIO.read(new FileInputStream("src/images/elitePlus.png"));
            HERO_BULLET_IMAGE = ImageIO.read(new FileInputStream("src/images/bullet_hero.png"));
            ENEMY_BULLET_IMAGE = ImageIO.read(new FileInputStream("src/images/bullet_enemy.png"));
            BOSS_ENEMY_IMAGE = ImageIO.read(new FileInputStream("src/images/boss.png"));
            BLOOD_IMAGE = ImageIO.read(new FileInputStream("src/images/prop_blood.png"));
            BOMB_IMAGE = ImageIO.read(new FileInputStream("src/images/prop_bomb.png"));
            FIRE_IMAGE = ImageIO.read(new FileInputStream("src/images/prop_fire.png"));
            FIREPLUS_IMAGE = ImageIO.read(new FileInputStream("src/images/prop_firePlus.png"));

            CLASSNAME_IMAGE_MAP.put(HeroAircraft.class.getName(), HERO_IMAGE);
            CLASSNAME_IMAGE_MAP.put(MobEnemy.class.getName(), MOB_ENEMY_IMAGE);
            CLASSNAME_IMAGE_MAP.put(EliteEnemy.class.getName(),ELITE_ENEMY_IMAGE);
            CLASSNAME_IMAGE_MAP.put(ElitePlusEnemy.class.getName(),ELITEPLUS_ENEMY_IMAGE);
            CLASSNAME_IMAGE_MAP.put(BossEnemy.class.getName(),BOSS_ENEMY_IMAGE);
            CLASSNAME_IMAGE_MAP.put(HeroBullet.class.getName(), HERO_BULLET_IMAGE);
            CLASSNAME_IMAGE_MAP.put(EnemyBullet.class.getName(), ENEMY_BULLET_IMAGE);
            CLASSNAME_IMAGE_MAP.put(Blood.class.getName(),BLOOD_IMAGE);
            CLASSNAME_IMAGE_MAP.put(Bomb.class.getName(),BOMB_IMAGE);
            CLASSNAME_IMAGE_MAP.put(Fire.class.getName(),FIRE_IMAGE);
            CLASSNAME_IMAGE_MAP.put(FirePlus.class.getName(),FIREPLUS_IMAGE);

        } catch (IOException e) {
            e.printStackTrace();
            System.exit(-1);
        }
    }

    public static BufferedImage get(String className){
        return CLASSNAME_IMAGE_MAP.get(className);
    }

    public static BufferedImage get(Object obj){
        if (obj == null){
            return null;
        }
        return get(obj.getClass().getName());
    }

}
