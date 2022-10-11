import greenfoot.*;  // (World, Actor, GreenfootImage, Greenfoot and MouseInfo)

/**
 * The Bus subclass
 */
public class Bus extends Vehicle
{
    private int stopTimer = 0;
    private int capacity = 5;
    
    public Bus(VehicleSpawner origin){
        super (origin); // call the superclass' constructor first
        
        //Set up values for Bus
        maxSpeed = 1.5 + ((Math.random() * 10)/5);
        speed = maxSpeed;
        // because the Bus graphic is tall, offset it a up (this may result in some collision check issues)
        yOffset = 15;
    }

    /**
     * Act - do whatever the Bus wants to do. This method is called whenever
     * the 'Act' or 'Run' button gets pressed in the environment.
     */
    public void act()
    {
        if (capacity > 0) {
            checkHitPedestrian();
        }
        
        if (stopTimer == 0) {
            drive();
        } else {
            stopTimer--;
        }
        super.act();
    }

    public boolean checkHitPedestrian () {
        Pedestrian[] ps = {
            (Pedestrian)getOneObjectAtOffset((int)speed + getImage().getWidth()/2, 0, Pedestrian.class),
            (Pedestrian)getOneObjectAtOffset(0, getImage().getHeight()/2, Pedestrian.class),
            (Pedestrian)getOneObjectAtOffset(0, -getImage().getHeight()/2, Pedestrian.class),
        };
        
        for (int i = 0; i < ps.length; i++) {
            if (ps[i] != null && ps[i].isAwake()){
                stopTimer = 60;
                getWorld().removeObject(ps[i]);
                capacity--;
                return true;
            }
        }
        
        return false;
    }
}
