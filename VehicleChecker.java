import greenfoot.*;  // (World, Actor, GreenfootImage, Greenfoot and MouseInfo)

/**
 * Used to check whether an area is clear of vehicles, for lane changing
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */
public class VehicleChecker extends Actor
{
    private GreenfootImage image;
    private boolean visible = false;
    
    public VehicleChecker (int width, int height)
    {
        image = new GreenfootImage(width, height);
        
        // draws rectangle for debugging
        if (visible) {
            image.setColor(new Color(255, 0, 0, 128));
            image.fillRect(0, 0, width, height);
        }
        
        setImage(image);
    }
    
    public boolean isTouchingVehicle () {
        return isTouching(Vehicle.class);
    }
}