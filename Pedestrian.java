import greenfoot.*;  // (World, Actor, GreenfootImage, Greenfoot and MouseInfo)

/**
 * A Pedestrian that tries to walk across the street
 */
public abstract class Pedestrian extends SuperSmoothMover
{
    protected double speed;
    protected double normalSpeed;
    protected boolean awake;
    
    public Pedestrian(double normalSpeed) {
        // start as awake 
        awake = true;
        
        // set speed
        this.normalSpeed = normalSpeed;
        speed = normalSpeed;
    }

    /**
     * Method to cause this Pedestrian to become knocked down - stop moving, turn onto side
     */
    public abstract void knockDown ();

    /**
     * Method to allow a downed Pedestrian to be healed
     */
    public void healMe () {
        speed = normalSpeed;
        setRotation (0);
        awake = true;
    }
    
    public boolean isAwake () {
        return awake;
    }
}
