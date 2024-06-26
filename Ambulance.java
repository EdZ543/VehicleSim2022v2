import greenfoot.*;  // (World, Actor, GreenfootImage, Greenfoot and MouseInfo)

/**
 * The Ambulance subclass
 */
public class Ambulance extends Vehicle
{
    private GreenfootSound sirenSound = new GreenfootSound("siren.wav");
    
    public Ambulance(VehicleSpawner origin, int lane){
        super (origin, lane); // call the superclass' constructor first
        
        maxSpeed = 2.5;
        speed = maxSpeed;
    }

    /**
     * Act - do whatever the Ambulance wants to do. This method is called whenever
     * the 'Act' or 'Run' button gets pressed in the environment.
     */
    public void act()
    {
        drive();
        checkHitPedestrian();
        super.act();
    }

    public boolean checkHitPedestrian () {
        Pedestrian p = (Pedestrian)getOneObjectAtOffset((int)speed + getImage().getWidth()/2, 0, Pedestrian.class);
        
        // if hit a pedestrian, heal it and play siren
        if (p != null && !p.isAwake()){
            p.healMe();
            sirenSound.play();
            return true;
        }
        return false;
    }
}
