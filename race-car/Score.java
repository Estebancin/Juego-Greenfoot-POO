import greenfoot.*;  // (World, Actor, GreenfootImage, Greenfoot and MouseInfo)

/**
 * Write a description of class Score here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */
public class Score extends Actor
{
    private MyWorld world;
    private int prevLevel;
    
    public Score(MyWorld world) {
        this.world = world;
        prevLevel = world.getLevel();
        updateScore();
    }
    
    /**
     * Act - do whatever the Score wants to do. This method is called whenever
     * the 'Act' or 'Run' button gets pressed in the environment.
     */
    public void act() 
    {
        updateScore();
        checkLevelUp();
    }
    
    private void updateScore() {
        String s = "Level " + world.getLevel() + ", Score: " + world.getScore();
        GreenfootImage img = new GreenfootImage(s, 24, Color.GREEN, Color.BLACK);
        setImage(img);
    }
    
    private void checkLevelUp() {
        int currentLevel = world.getLevel();
        if (prevLevel != currentLevel) {
            String levelUpMessage = "Level Up: " + currentLevel;
            showLevelUpMessage(levelUpMessage);
            prevLevel = currentLevel;
        }
    }
    
    private void showLevelUpMessage(String message) {
        getWorld().showText(message, getWorld().getWidth() / 2, getWorld().getHeight() / 2);
        Greenfoot.delay(100);
        getWorld().showText("", getWorld().getWidth() / 2, getWorld().getHeight() / 2);
    }
    }    

