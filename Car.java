import greenfoot.*;  // (World, Actor, GreenfootImage, Greenfoot and MouseInfo)

/**
 * The Car subclass
 */
public class Car extends Vehicle
{
    
    public Car(VehicleSpawner origin, int lane) {
        super(origin, lane); // call the superclass' constructor
        maxSpeed = 2 + ((Math.random() * 30)/5);
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
     * When a Car hit's a Pedestrian, it should knock it over
     */
    public boolean checkHitPedestrian () {
        Pedestrian p = (Pedestrian)getOneObjectAtOffset((int)speed + getImage().getWidth()/2, 0, Pedestrian.class);
        
        // knocks down pedestrians if hitting one
        if (p != null && p.isAwake()){
            p.knockDown();
            return true;
        }
        return false;
    }
}
