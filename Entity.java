import greenfoot.*;  // (World, Actor, GreenfootImage, Greenfoot and MouseInfo)

/**
 * Write a description of class Entity here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */
public class Entity extends Pedestrian
{
    public Entity(int direction) {
        super(direction);
        
        // choose a random speed
        maxSpeed = Math.random() * 2 + 1;
        speed = maxSpeed;
    }
    
    /**
     * Act - do whatever the Entity wants to do. This method is called whenever
     * the 'Act' or 'Run' button gets pressed in the environment.
     */
    public void act()
    {
        // Add your action code here.
    }
    
    public void knockDown () {
        speed = 0;
        setRotation (90);
        awake = false;
    }
}
