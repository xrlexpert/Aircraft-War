package edu.hitsz.application.game;

import edu.hitsz.application.HeroController;
import edu.hitsz.application.ImageManager;
import edu.hitsz.application.Main;
import edu.hitsz.bullet.EnemyBullet;
import edu.hitsz.observer.Observer;
import edu.hitsz.scores.ScoreDaoImpl;
import edu.hitsz.aircraft.*;
import edu.hitsz.aircraft.factory.*;
import edu.hitsz.bullet.BaseBullet;
import edu.hitsz.basic.AbstractFlyingObject;
import edu.hitsz.aircraft.factory.EnemyAircraftFactory;
import edu.hitsz.component.UserNameinputBox;
import edu.hitsz.supply.BaseItem;
import edu.hitsz.supply.Bomb;
import edu.hitsz.thread.music.BackGroundMusicThread;
import edu.hitsz.thread.music.BossMusicThread;
import edu.hitsz.thread.music.MusicConfig;
import edu.hitsz.thread.music.MusicThread;

import javax.swing.*;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.util.*;
import java.util.List;
import java.util.concurrent.*;

/**
 * 游戏主面板，游戏启动
 *
 * @author hitsz
 */
public abstract class Game extends JPanel {

    private int backGroundTop = 0;
    public static ScoreDaoImpl scoreDao = new ScoreDaoImpl();
    /**
     * 游戏结束标志
     */
    public  static boolean gameOverFlag = false;
    public static boolean bossFlag = false;


    /**
     * Scheduled 线程池，用于定时任务调度
     * 关于alibaba code guide：可命名的 ThreadFactory 一般需要第三方包
     * apache 第三方库： org.apache.commons.lang3.concurrent.BasicThreadFactory
     */
    public static final ScheduledExecutorService executorService = new ScheduledThreadPoolExecutor(30);
    public static int score = 0;




    protected final HeroAircraft heroAircraft;
    protected final List<AbstractEnemyAircraft> enemyAircrafts;
    protected final List<BaseBullet> heroBullets;
    protected final List<BaseBullet> enemyBullets;
    protected final List<BaseItem> items;

    /**
     * 时间间隔(ms)，控制刷新频率
     */
    protected int timeInterval = 40;

    /**
     * 屏幕中出现的敌机最大数量
     */
    protected int enemyMaxNumber = 5;

    /**
     * 当前时刻
     */
    protected int time = 0;

    /**
     * 周期（ms)
     * 指示子弹的发射、敌机的产生频率
     */
    protected int cycleDuration = 600;
    protected int cycleTime = 0;

    /**
     * 困难提升的周期（ms)
     *
     * */
    protected int diffCycleDuration = 600;
    protected int diffCycleTime = 0;
    protected int bossScoreThreshold = 300;
    protected double ratioOfEliteEnemy = 0.75;
    protected int cntOfMeetingBoss = 0;






    public Game() {
        heroAircraft = HeroAircraft.getHeroAircraft();
        enemyAircrafts = new LinkedList<>();
        heroBullets = new LinkedList<>();
        enemyBullets = new LinkedList<>();
        items = new LinkedList<>();

        time = 0;
        cycleTime = 0;
        diffCycleTime = 0;
        cntOfMeetingBoss = 1;




        //启动英雄机鼠标监听
        new HeroController(this, heroAircraft);

    }

    /**
     * 游戏启动入口，执行游戏逻辑
     */
    public void action() {
        // 定时任务：绘制、对象产生、碰撞判定、击毁及结束判定
        Runnable task = () -> {

            time += timeInterval;

            // 周期性执行（控制频率）
            if (timeCountAndNewCycleJudge()) {
                System.out.println(time);
                 //新敌机产生
                createEnemyAircraft();

                //产生boss机
                createBossAircraft();

                // 飞机射出子弹
                shootAction();
            }
            //随时间的推移增加难度
            if(diffTimeCountAndChangeJudge()){
                increaseDifficulty();
            }

            // 子弹移动
            bulletsMoveAction();

            // 飞机移动
            aircraftsMoveAction();

            // 撞击检测
            crashCheckAction();

            // 后处理
            postProcessAction();

            //每个时刻重绘界面
            repaint();

            // 游戏结束检查英雄机是否存活
           checkIfGameOver();
        };

        /**
         * 以固定延迟时间进行执行
         * 本次任务执行完成后，需要延迟设定的延迟时间，才会执行新的任务
         */
        executorService.scheduleWithFixedDelay(task, timeInterval, timeInterval, TimeUnit.MILLISECONDS);

        //如果音效打开则开启音乐相关线程
        if(GameConfig.musicFlag){
            BackGroundMusicThread backGroundMusicThread = new BackGroundMusicThread();
            BossMusicThread bossMusicThread = new BossMusicThread();
            executorService.execute(backGroundMusicThread);
            executorService.execute(bossMusicThread);
        }

    }

    //***********************
    //      Action 各部分
    //***********************

    private boolean timeCountAndNewCycleJudge() {
        cycleTime += timeInterval;
        if (cycleTime >= cycleDuration && cycleTime - timeInterval < cycleTime) {
            // 跨越到新的周期
            cycleTime %= cycleDuration;
            return true;
        } else return false;

    }
    protected boolean diffTimeCountAndChangeJudge() {
        diffCycleTime += timeInterval;
        if(diffCycleTime >= diffCycleDuration && diffCycleTime - timeInterval < diffCycleTime){
            diffCycleTime %= diffCycleDuration;
            return true;
        }
        else return false;
    }
    private void createEnemyAircraft(){
        if (enemyAircrafts.size() < enemyMaxNumber) {
            Random rand = new Random();
            int randomNum = rand.nextInt(100);
            EnemyAircraftFactory factory;
            AbstractEnemyAircraft enemyAircraft;
            if(randomNum >= 100 * ratioOfEliteEnemy) {
                factory = new MobEnemyFactory();
                enemyAircraft = factory.createAircraft();
            }
            else if(randomNum >= 100 * ratioOfEliteEnemy * 1/5 && randomNum < 100 * ratioOfEliteEnemy ){
                factory = new EliteEnemyFactory();
                enemyAircraft = factory.createAircraft();
            }
            else{
                factory = new ElitePlusEnemyFactory();
                enemyAircraft = factory.createAircraft();
            }

            enemyAircrafts.add(enemyAircraft);
        }
    }
    abstract protected void createBossAircraft();
    abstract protected void increaseDifficulty();

    private void shootAction() {
        // TODO 敌机射击
       for(AbstractAircraft enemyAircraft:enemyAircrafts){
           enemyBullets.addAll(enemyAircraft.shoot());
       }
        // 英雄射击
        heroBullets.addAll(heroAircraft.shoot());
        Game.executorService.execute(new MusicThread(MusicConfig.BULLET_MUSIC,false));
    }

    private void bulletsMoveAction() {
        for (BaseBullet bullet : heroBullets) {
            bullet.forward();
        }
        for (BaseBullet bullet : enemyBullets) {
            bullet.forward();
        }
    }

    private void aircraftsMoveAction() {
        for (AbstractAircraft enemyAircraft : enemyAircrafts) {
            enemyAircraft.forward();
        }
        for(AbstractFlyingObject item : items){
            item.forward();
        }
    }


    /**
     * 碰撞检测：
     * 1. 敌机攻击英雄
     * 2. 英雄攻击/撞击敌机
     * 3. 英雄获得补给
     */
    private void crashCheckAction() {
        // TODO 敌机子弹攻击英雄
        for(BaseBullet bullet : enemyBullets){
            if(heroAircraft.crash(bullet)){
                heroAircraft.decreaseHp(bullet.getPower());
                bullet.vanish();
                System.out.println(heroAircraft.getHp());
                if(heroAircraft.notValid()){
                    gameOverFlag = true;
                }
            }
        }


        // 英雄子弹攻击敌机
        for (BaseBullet bullet : heroBullets) {
            if (bullet.notValid()) {
                continue;
            }
            for (AbstractEnemyAircraft enemyAircraft : enemyAircrafts) {
                if (enemyAircraft.notValid()) {
                    // 已被其他子弹击毁的敌机，不再检测
                    // 避免多个子弹重复击毁同一敌机的判定
                    continue;
                }
                if (enemyAircraft.crash(bullet)) {
                    // 敌机撞击到英雄机子弹
                    // 敌机损失一定生命值
                    Game.executorService.execute(new MusicThread(MusicConfig.BULLET_HIT_MUSIC,false));
                    if(enemyAircraft instanceof BossEnemy){
                        if(enemyAircraft.getHp() <= bullet.getPower()){
                            bossFlag = false;
                        }
                        enemyAircraft.decreaseHp(bullet.getPower());
                    }
                    else{
                        enemyAircraft.decreaseHp(bullet.getPower());
                    }

                    bullet.vanish();
                    if (enemyAircraft.notValid()) {
                        // TODO 获得分数，产生道具补给
                        score += 10;
                        if(enemyAircraft instanceof BossEnemy){
                            score += 100;
                        }
                        items.addAll(enemyAircraft.createItems());
                    }
                }
                // 英雄机 与 敌机 相撞，均损毁
                if (enemyAircraft.crash(heroAircraft) || heroAircraft.crash(enemyAircraft)) {
                    enemyAircraft.vanish();
                    System.out.println(enemyAircraft.getClass());
                    heroAircraft.decreaseHp(Integer.MAX_VALUE);
                }
            }
        }

        // Todo: 我方获得道具，道具生效
        for(BaseItem item : items){
            if(heroAircraft.crash(item)){
                if(item instanceof Bomb bomb){
                    for(AbstractEnemyAircraft enemyAircraft:enemyAircrafts){
                        if(enemyAircraft instanceof Observer){
                            bomb.registerObserver((Observer)enemyAircraft);
                        }
                    }
                    for(BaseBullet enemyBullet:enemyBullets){
                        bomb.registerObserver((EnemyBullet)enemyBullet);
                    }
                    bomb.work(heroAircraft);
                }
                else{
                    item.work(heroAircraft);
                }

                item.vanish();
            }
        }

    }
    //检查是否游戏结束
    public void checkIfGameOver(){
        if (heroAircraft.getHp() <= 0){
            Main.currentScore = score;
            gameOverFlag = true;
            System.out.println("Game Over!");
            Game.executorService.shutdown();
            Thread thread = new Thread(new MusicThread(MusicConfig.GAME_OVER_MUSIC,false));
            thread.start();
            UserNameinputBox userNameinputBox = new UserNameinputBox();
            Main.CARD_PANEL.add(userNameinputBox.getMainPanel(),"UserNameinputBox");
            Main.CARD_LAYOUT.show(Main.CARD_PANEL,"UserNameinputBox");
            Main.FRAME.add(Main.CARD_PANEL);
            Main.FRAME.setVisible(true);
        }
    }


    /**
     * 后处理：
     * 1. 删除无效的子弹
     * 2. 删除无效的敌机
     * <p>
     * 无效的原因可能是撞击或者飞出边界
     */
    private void postProcessAction() {
        enemyBullets.removeIf(AbstractFlyingObject::notValid);
        heroBullets.removeIf(AbstractFlyingObject::notValid);
        enemyAircrafts.removeIf(AbstractFlyingObject::notValid);
        items.removeIf(AbstractFlyingObject::notValid);
    }


    //***********************
    //      Paint 各部分
    //***********************

    /**
     * 重写paint方法
     * 通过重复调用paint方法，实现游戏动画
     *
     * @param  g
     */
    @Override
    public void paint(Graphics g) {
        super.paint(g);

        // 绘制背景,图片滚动
        g.drawImage(ImageManager.BACKGROUND_IMAGE, 0, this.backGroundTop - Main.WINDOW_HEIGHT, null);
        g.drawImage(ImageManager.BACKGROUND_IMAGE, 0, this.backGroundTop, null);
        this.backGroundTop += 1;
        if (this.backGroundTop == Main.WINDOW_HEIGHT) {
            this.backGroundTop = 0;
        }

        // 先绘制子弹，后绘制飞机
        // 这样子弹显示在飞机的下层
        paintImageWithPositionRevised(g, enemyBullets);
        paintImageWithPositionRevised(g, heroBullets);

        paintImageWithPositionRevised(g, enemyAircrafts);
        paintImageWithPositionRevised(g,items);

        g.drawImage(ImageManager.HERO_IMAGE, heroAircraft.getLocationX() - ImageManager.HERO_IMAGE.getWidth() / 2,
                heroAircraft.getLocationY() - ImageManager.HERO_IMAGE.getHeight() / 2, null);

        //绘制得分和生命值
        paintScoreAndLife(g);

    }

    private void paintImageWithPositionRevised(Graphics g, List<? extends AbstractFlyingObject> objects) {
        if (objects.size() == 0) {
            return;
        }

        for (AbstractFlyingObject object : objects) {
            BufferedImage image = object.getImage();
            assert image != null : objects.getClass().getName() + " has no image! ";
            g.drawImage(image, object.getLocationX() - image.getWidth() / 2,
                    object.getLocationY() - image.getHeight() / 2, null);
        }
    }

    private void paintScoreAndLife(Graphics g) {
        int x = 10;
        int y = 25;
        g.setColor(new Color(16711680));
        g.setFont(new Font("SansSerif", Font.BOLD, 22));
        g.drawString("SCORE:" + this.score, x, y);
        y = y + 20;
        g.drawString("LIFE:" + this.heroAircraft.getHp(), x, y);
    }


}
