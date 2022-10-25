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
            if (lifeTimer % 10 == 0) {
                targetNearest();
            }
            lifeTimer++;
            
            if (isSuitableTarget(currentTarget)) {
                moveTowards(currentTarget, speed);
            }
            
            if (isSuitableTarget(currentTarget) && distanceTo(currentTarget) <= armRadius) {
                if (currentTarget instanceof Researcher) {
                    Researcher r = (Researcher)currentTarget;
                    if (r.scooting()) {
                        knockDown();
                    } else {
                        r.knockDown();
                    }
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
    
    private boolean isSuitableTarget(Pedestrian p) {
        if (p == null || p.getWorld() == null) {
            return false;
        }
        
        if (p instanceof Researcher) {
            Researcher r = (Researcher)p;
            return r.isAwake() && !r.walkingToTarget();
        } else if (p instanceof Entity) {
            return !p.isAwake();
        }
        
        return false;
    }
}
