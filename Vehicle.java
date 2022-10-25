import greenfoot.*;  // (World, Actor, GreenfootImage, Greenfoot and MouseInfo)

/**
 * This is the superclass for Vehicles.
 * 
 */
public abstract class Vehicle extends SuperSmoothMover
{
    private GreenfootSound vroomSound = new GreenfootSound("vroom.mp3");
    
    protected double maxSpeed;
    protected double speed;
    protected int direction; // 1 = right, -1 = left
    protected boolean moving = true;
    protected int yOffset;
    protected VehicleSpawner origin;
    protected boolean swerving = false;
    protected double swerveTimer = 0;
    protected int lane;
    
    public Vehicle (VehicleSpawner origin, int lane) {
        this.origin = origin;
        this.lane = lane;
        
        if (origin.facesRightward()){
            direction = 1;
            
        } else {
            direction = -1;
            getImage().mirrorHorizontally();
        }
    }
    
    public void act () {
        if (getWorld() != null && checkEdge()){
            getWorld().removeObject(this);
        }
    }
    
    protected abstract boolean checkHitPedestrian ();
    
    public void addedToWorld (World w){
        setLocation (origin.getX() - (direction * 100), origin.getY() - yOffset);
    }

    /**
     * A method used by all Vehicles to check if they are at the edge
     */
    protected boolean checkEdge() {
        if (direction == 1){
            if (getX() > getWorld().getWidth() + 200){
                return true;
            }
        } else {
            if (getX() < -200){
                return true;
            }
        }
        return false;
    }

    /**
     * Method that deals with movement. Speed can be set by individual subclasses in their constructors
     */
    public void drive() 
    {
        // Ahead is a generic vehicle - we don't know what type BUT
        // since every Vehicle "promises" to have a getSpeed() method,
        // we can call that on any vehicle to find out it's speed
        Vehicle ahead = (Vehicle) getOneObjectAtOffset (direction * (int)(speed + getImage().getWidth()/2 + 4), 0, Vehicle.class);
        if (ahead == null)
        {
            speed = maxSpeed;
        } else {
            speed = ahead.getSpeed();
            if (lane != -1) {
                attemptLaneChange();
            }
        }
        
        move (speed * direction);
        
        if (swerving) {
            swerveTimer += 0.1;
            double swerveDistance = Math.sin(swerveTimer) * 10;
            setLocation(getX(), getY() + swerveDistance);
        }
        
        if (!getIntersectingObjects(BloodSplatter.class).isEmpty()) {
            lane = -1;
            if (Math.random() > 0.5) {
                setRotation(getRotation() + 1);
            } else {
                setRotation(getRotation() - 1);
            }
        }
    }  
    
    protected void attemptLaneChange() {
        if (lane == 0 || lane == 3) {
            if (laneClear("below")) changeLanes("below");
        } else if (lane == 1 || lane == 4) {
            if (laneClear("above")) changeLanes("above");
            else if (laneClear("below")) changeLanes("below");
        } else if (lane == 2 || lane == 5) {
            if (laneClear("above")) changeLanes("above");
        }
    }
    
    protected boolean laneClear(String direction) {
        VehicleChecker vc = new VehicleChecker(getImage().getWidth() + 50, getImage().getHeight());
        
        if (direction == "above") {
            getWorld().addObject(vc, getX(), getY() - 54);
        } else if (direction == "below") {
            getWorld().addObject(vc, getX(), getY() + 54);
        }
        
        boolean ret = !vc.isTouchingVehicle();
        getWorld().removeObject(vc);
        
        return ret;
    }
    
    protected void changeLanes(String direction) {
        if (direction == "above") {
            setLocation(getX(), getY() - 54);
            lane--;
        } else if (direction == "below") {
            setLocation(getX(), getY() + 54);
            lane++;
        }
        
        vroomSound.play();
    }

    /**
     * An accessor that can be used to get this Vehicle's speed. Used, for example, when a vehicle wants to see
     * if a faster vehicle is ahead in the lane.
     */
    public double getSpeed(){
        return speed;
    }
    
    public void startSwerving() {
        swerving = true;
        lane = -1;
    }
    
    public void stopSwerving() {
        swerving = false;
        swerveTimer = 0;
    }
}
