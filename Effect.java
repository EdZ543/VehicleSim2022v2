import greenfoot.*;  // (World, Actor, GreenfootImage, Greenfoot and MouseInfo)

/**
 * Write a description of class Effect here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */
public class Effect extends Actor
{
    protected GreenfootImage image;
    protected int duration;
    protected int fadeDuration;
    protected double fadeAmount;
    protected double preciseTransparency = 255;
    
    public Effect(int duration, int fadeDuration) {
        this.duration = duration;
        this.fadeDuration = fadeDuration;
        
        // calculate the amount the transparency must decrease by each act when fading
        fadeAmount = 255 / (double)fadeDuration;
    }
    
    public void act() {
        if (duration > 0) {
            duration--;
        } else if (fadeDuration > 0) {
            preciseTransparency -= fadeAmount;
            image.setTransparency((int)preciseTransparency);
            fadeDuration--;
        } else {
            getWorld().removeObject(this);
        }
    }
}
