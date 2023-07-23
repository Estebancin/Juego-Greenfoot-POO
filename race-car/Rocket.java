import greenfoot.*;  // (World, Actor, GreenfootImage, Greenfoot and MouseInfo)

/**
 * Write a description of class rocket here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */
public class Rocket extends Actor
{
    private int speed = 10; // Velocidad del cohete
    private boolean movingRight; // Bandera para indicar la dirección del movimiento
    private int appearProbability = 3; // Probabilidad de aparición 
    
    /**
     * Act - do whatever the rocket wants to do. This method is called whenever
     * the 'Act' or 'Run' button gets pressed in the environment.
     */
    public void act()
     {
        // Corregir la llave de apertura innecesaria en este lugar
        if (Greenfoot.getRandomNumber(appearProbability) == 0) {
            if (movingRight) {
                setLocation(getX() + speed, getY());
                if (getX() > getWorld().getWidth()) {
                    getWorld().removeObject(this);
                }
            } else {
                setLocation(getX() - speed, getY());
                if (getX() < 0) {
                    getWorld().removeObject(this);
                }
            }
        }
        if (getOneIntersectingObject(GreenCar.class) != null) {
            Greenfoot.stop(); // Detener el juego
        }
    }
    public Rocket() {
        // Inicializar la dirección de movimiento al azar
        movingRight = Greenfoot.getRandomNumber(2) == 0;
        
        
        setImage("rocket.png");
    }
}   
    
    
     

 