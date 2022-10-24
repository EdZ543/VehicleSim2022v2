import greenfoot.*;  // (World, Actor, GreenfootImage, Greenfoot and MouseInfo)
import java.util.Random;

/**
 * Write a description of class Researcher here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */
public class Researcher extends Pedestrian
{
    private int direction;
    private int targetX;
    private boolean walkingToTarget = true;
    Random rand = new Random();
    private boolean scooting = false;
    
    public Researcher(int direction) {
        super(Math.random() + 1);
        
        this.direction = direction;
    }
    
    /**
     * Act - do whatever the Researcher wants to do. This method is called whenever
     * the 'Act' or 'Run' button gets pressed in the environment.
     */
    public void act()
    {
        if (awake){
            if (walkingToTarget) {
                walkToTarget();            
            } else if (getOneObjectAtOffset(0, (int)(direction * getImage().getHeight()/2 + (int)(direction * speed)), Vehicle.class) == null){
                setLocation (getX(), getY() + (int)(speed*direction));
            }
            
            if (direction == -1 && getY() < 100){
                getWorld().removeObject(this);
            } else if (direction == 1 && getY() > 550){
                getWorld().removeObject(this);
            }
        }
    }
    
    protected void addedToWorld(World world) {
        // choose a target to walk to before crossing
        targetX = rand.nextInt(world.getWidth());
    }
    
    private void walkToTarget() {
        if (Math.abs(getX() - targetX) < speed) {
            setLocation(targetX, getY());
            walkingToTarget = false;
        } else if (getX() < targetX) {
            setLocation (getX() + (int)(speed), getY());
        } else {
            setLocation (getX() - (int)(speed), getY());
        }  
    }
    
    public boolean walkingToTarget() {
        return walkingToTarget;
    }
    
    public void mountScooter() {
        scooting = true;
            
        // alter image to put scooter
        GreenfootImage temp = new GreenfootImage(90, 90);
        temp.drawImage(new GreenfootImage("researcher.png"), 45, 45);
        temp.drawImage(new GreenfootImage("scooter.png"), 45, 45);
        setImage(temp);
    }
    
    public boolean scooting() {
        return scooting;
    }
}
