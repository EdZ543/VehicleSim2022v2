import greenfoot.*;  // (World, Actor, GreenfootImage, Greenfoot and MouseInfo)

/**
 * Entity. Every interval, it finds the nearest researcher and chases after them.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */
public class Entity extends Pedestrian
{
    private GreenfootSound eatSound = new GreenfootSound("munch.wav");
    private Pedestrian currentTarget = null;
    private int lifeTimer = 0;
    private int viewRadius = 600;
    private int armRadius = 30;
    private double speedIncrease = 1;
    
    public Entity() {
        super(Math.random() + 0.2);
    }
    
    /**
     * Act - do whatever the Entity wants to do. This method is called whenever
     * the 'Act' or 'Run' button gets pressed in the environment.
     */
    public void act()
    {
        if (awake) {
            // updates target periodically to prevent lag
            if (lifeTimer % 10 == 0) {
                targetNearest();
            }
            lifeTimer++;
            
            // moves towards target
            if (isSuitableTarget(currentTarget)) {
                moveTowards(currentTarget, speed);
            }
            
            // do something if it reaches the target
            if (isSuitableTarget(currentTarget) && distanceTo(currentTarget) <= armRadius) {
                // if researcher, knock it down
                // however, if they are riding a scooter, the entity gets knocked down
                if (currentTarget instanceof Researcher) {
                    Researcher r = (Researcher)currentTarget;
                    if (r.scooting()) {
                        knockDown();
                    } else {
                        r.knockDown();
                    }
                // if another entity who is dead, eat it to gain speed
                } else if (currentTarget instanceof Entity) {
                    getWorld().removeObject(currentTarget);
                    normalSpeed += speedIncrease;
                    speed += speedIncrease;
                    eatSound.play();
                }
            }
        }
    }
    
    private void targetNearest() {
        // targets the nearest eligible actor
        for (Pedestrian p : getObjectsInRange(viewRadius, Pedestrian.class)) {
            if (isSuitableTarget(currentTarget)) {
                if (isSuitableTarget(p) && distanceTo(p) < distanceTo(currentTarget)) {
                    currentTarget = p;
                }
            } else {
                currentTarget = p;
            }
        }
    }
    
    // checks if a pedestrian is a suitable target
    private boolean isSuitableTarget(Pedestrian p) {
        // if the entity doesn't exist, not suitable
        if (p == null || p.getWorld() == null) {
            return false;
        }
        
        // suitable targets are alive researchers who are walking across the street, and other dead entities
        if (p instanceof Researcher) {
            Researcher r = (Researcher)p;
            return r.isAwake() && !r.walkingToTarget();
        } else if (p instanceof Entity) {
            return !p.isAwake();
        }
        
        return false;
    }
}
