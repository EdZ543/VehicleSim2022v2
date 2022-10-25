import greenfoot.*;  // (World, Actor, GreenfootImage, Greenfoot and MouseInfo)

/**
 * Write a description of class BloodSplatter here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */
public class BloodSplatter extends Effect
{
    private GreenfootSound squelchSound = new GreenfootSound("squelch.wav");
    
    public BloodSplatter() {
        super(200, 50);
        squelchSound.play();
    }
    
    public void addedToWorld(World w) {
        VehicleWorld vw = (VehicleWorld)w;
        image = drawBloodSplatter();
        setImage(image);
    }
    
    public static GreenfootImage drawBloodSplatter() {
        GreenfootImage ret = new GreenfootImage(75, 45);
        ret.setColor(new Color(79, 22, 13));
        ret.fillOval(0, 0, 75, 45);
        
        return ret;
    }
}
