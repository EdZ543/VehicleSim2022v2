import greenfoot.*;  // (World, Actor, GreenfootImage, Greenfoot and MouseInfo)

/**
 * Write a description of class LiminalHouse here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */
public class LiminalHouse extends Actor
{
    private int spawnOffset = 10;
    
    /**
     * Act - do whatever the LiminalHouse wants to do. This method is called whenever
     * the 'Act' or 'Run' button gets pressed in the environment.
     */
    public void act()
    {
        // Chance to spawn a Researcher
        if (Greenfoot.getRandomNumber (1000) == 0){
            getWorld().addObject (new Researcher (1), getX(), getY() + spawnOffset);
        }
    }
}
