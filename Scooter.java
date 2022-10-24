import greenfoot.*;  // (World, Actor, GreenfootImage, Greenfoot and MouseInfo)

/**
 * Write a description of class Scooter here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */
public class Scooter extends Vehicle
{
    public Scooter(VehicleSpawner origin) {
        super(origin); // call the superclass' constructor
        maxSpeed = 1.5 + ((Math.random() * 30)/5);
        speed = maxSpeed;
        yOffset = 0;
    }

    public void act()
    {
        drive(); 
        checkHitPedestrian();
        super.act();
    }
    
    /**
     * When a Scooter hit's a Pedestrian, it should knock it over, unless it's a Researcher
     */
    public boolean checkHitPedestrian () {
        Pedestrian p = (Pedestrian)getOneObjectAtOffset((int)speed + getImage().getWidth()/2, 0, Pedestrian.class);
        
        if (p != null){
            if (p instanceof Researcher && p.isAwake()) {
                Researcher r = (Researcher)p;
                r.mountScooter();
                getWorld().removeObject(this);
            } else {
                p.knockDown();
            }    
            
            return true;
        }
        return false;
    }
}
