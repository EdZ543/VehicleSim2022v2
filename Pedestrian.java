import greenfoot.*;  // (World, Actor, GreenfootImage, Greenfoot and MouseInfo)

/**
 * A Pedestrian that tries to walk across the street
 */
public abstract class Pedestrian extends SuperSmoothMover
{
    protected double speed;
    protected double maxSpeed;
    protected int direction;
    protected boolean awake;
    
    public Pedestrian(int direction) {
        // start as awake 
        awake = true;
        this.direction = direction;
    }

    /**
     * Method to cause this Pedestrian to become knocked down - stop moving, turn onto side
     */
    public abstract void knockDown ();

    /**
     * Method to allow a downed Pedestrian to be healed
     */
    public void healMe () {
        speed = maxSpeed;
        setRotation (0);
        awake = true;
    }
    
    public boolean isAwake () {
        return awake;
    }
}
