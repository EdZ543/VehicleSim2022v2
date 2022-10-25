import greenfoot.*;  // (World, Actor, GreenfootImage, Greenfoot and MouseInfo)
import java.util.ArrayList;

/**
 * Write a description of class BloodMoon here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */
public class BloodMoon extends Effect
{   
    private static boolean happening = false;
    private GreenfootSound ominousSound = new GreenfootSound("spoopy.wav");
    
    public BloodMoon(int duration) {
        super(duration, 90);
        
        // static variable to make sure two blood moons don't happen simultaneously
        happening = true;
        
        // play sound when blood moon starts
        ominousSound.play();
    }
    
    public void addedToWorld(World w) {
        // set image
        VehicleWorld vw = (VehicleWorld)w;
        image = drawBloodMoon(vw.getWidth(), vw.getHeight(), 100, 690, 10);
        setImage(image);
        
        // make entities faster
        for (Entity e : vw.getObjects(Entity.class)) {
            e.scaleSpeed(3);
        }
        
        // make vehicles swerve in sine wave pattern
        for (Vehicle v : vw.getObjects(Vehicle.class)) {
            v.startSwerving();
        }
    }
    
    public void act() {
        // undo effects on world
        if (fadeDuration == 0) {
            VehicleWorld vw = (VehicleWorld)getWorld();
            
            for (Entity e : vw.getObjects(Entity.class)) {
                e.resetSpeed();
            }
        
            for (Vehicle v : vw.getObjects(Vehicle.class)) {
                v.stopSwerving();
            }
            
            happening = false;
        }
        
        super.act();
    }
    
    public static GreenfootImage drawBloodMoon(int width, int height, int transparency, int moonX, int moonY) {
        GreenfootImage ret = new GreenfootImage(width, height);
        
        GreenfootImage moon = new GreenfootImage("moon.png");
        ret.drawImage(moon, moonX, moonY);
        
        ret.setColor(new Color(255, 0, 0, transparency));
        ret.fill();
        
        return ret;
    }
    
    public static boolean happening() {
        return happening;
    }
}
