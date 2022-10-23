import greenfoot.*;  // (World, Actor, GreenfootImage, Greenfoot and MouseInfo)

/**
 * Entity. Every interval, it finds the nearest researcher and chases after them.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */
public class Entity extends Pedestrian
{
    private Pedestrian currentTarget = null;
    private int lifeTimer = 0;
    private int viewRadius = 600;
    private int armRadius = 30;
    private int speedIncrease = 2;
    
    public Entity() {
        super(Math.random() * 2 + 1);
    }
    
    /**
     * Act - do whatever the Entity wants to do. This method is called whenever
     * the 'Act' or 'Run' button gets pressed in the environment.
     */
    public void act()
    {
        if (lifeTimer % 10 == 0) {
            targetNearest();
        }
        lifeTimer++;
        
        if (isSuitableTarget(currentTarget)) {
            moveTowards(currentTarget, speed);
        }
        
        if (isSuitableTarget(currentTarget) && distanceTo(currentTarget) <= armRadius) {
            if (currentTarget instanceof Researcher) {
                currentTarget.knockDown();
            } else if (currentTarget instanceof Entity) {
                getWorld().removeObject(currentTarget);
                normalSpeed += speedIncrease;
                speed = normalSpeed;
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
        boolean exists = p != null && p.getWorld() != null;
        boolean rightType = (p instanceof Researcher && p.isAwake()) || (p instanceof Entity && !p.isAwake());
        return exists && rightType;
    }
    
    public void knockDown() {
        speed = 0;
        setRotation (90);
        awake = false;
    }
}
