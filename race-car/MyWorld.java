    import greenfoot.*;  // (World, Actor, GreenfootImage, Greenfoot and MouseInfo)
    import java.util.List;
    import java.util.ArrayList;
    import java.util.Random;
    import greenfoot.GreenfootSound;
    
    /**
     * Write a description of class MyWorld here.
     * 
     * @author (your name) 
     * @version (a version number or a date)
     */
    public class MyWorld extends World
    {
        private GreenfootSound backgroundMusic1;
        private GreenfootSound backgroundMusic2;
        private boolean isPlayingFirstMusic;
    
        private static final Random _rand = new Random(40L);
        GreenCar greenCar;
        List<RedCar> redCars;
        List<Integer> xPositions;
        List<Integer> xHistory;
        long numRedCarsAdded = 0L;
        int maxRedCars = 5;
        /**
         * Constructor for objects of class MyWorld.
         * 
         */
        public MyWorld()
        {    
            // Create a new world with 600x400 cells with a cell size of 1x1 pixels.
            super(1080, 600, 1); 
            Greenfoot.setSpeed(65);
            
            xHistory = new ArrayList<>();
            xPositions = getXPositions();
            
            greenCar = new GreenCar(this);
            redCars = new ArrayList<>();
            
            addObject(greenCar, greenCar.getX(), greenCar.getY());
            addObject(new Score(this), 85, 10);
            addRedCar();
            
            addRockets();
            backgroundMusic1 = new GreenfootSound("./pista 2.mp3");
            backgroundMusic2 = new GreenfootSound("./inicio.mp3");



            backgroundMusic1.setVolume(70); // Ajustar el volumen (0-100)
            backgroundMusic1.play();
            backgroundMusic2.setVolume(60);
            
            // Reproducir la primera pista de música
            isPlayingFirstMusic = true;
        }
        
        public void act() {
        super.act();
        if (redCars.size() < maxRedCars) {
            double p = _rand.nextDouble();
            double t = (numRedCarsAdded < 5) ? 0.995d : 0.99d;
            if (p > t) {
                addRedCar();
            }
        }
        if (redCars.size() < maxRedCars) {
            double p = _rand.nextDouble();
            double t = (numRedCarsAdded < 5) ? 0.995d : 0.99d;
            if (p > t) {
                addRedCar();
            }
        }
    
        // Controlar el cambio de pista de música
        if (!backgroundMusic1.isPlaying() && isPlayingFirstMusic) {
            backgroundMusic2.playLoop();
            isPlayingFirstMusic = false;
        }
    }

        
        private void addRedCar() {
            RedCar redCar = new RedCar(getRandomXPosition());
            redCars.add(redCar);
            addObject(redCar, redCar.getX(), redCar.getY());
            numRedCarsAdded += 1L;
            if (numRedCarsAdded % 50 == 0) {
                maxRedCars += 1;
            }
        }
        private void addRockets() {
            for (int i = 0; i < 5; i++) {
                int randomY = Greenfoot.getRandomNumber(getHeight());
                addObject(new Rocket(), getWidth(), randomY);
            }
        }
    
        public void removeRedCar(RedCar redCar) {
            redCars.remove(redCar);
            removeObject(redCar);
        }
        
        public long getScore() { return numRedCarsAdded; }
        
        public int getLevel() { return maxRedCars - 5 + 1; }
        
        private int getRandomXPosition() {
            int numPositions = xPositions.size();
            int index = -1;
            while (true) {
                index = _rand.nextInt(numPositions);
                
                if (!xHistory.contains(index)) {
                    xHistory.add(index);
                    break;
                }
            }
            
            if (xHistory.size() > 5) {
                xHistory.remove(0);
            }
            
            return xPositions.get(index);
        }
        
        private List<Integer> getXPositions() {
            int carWidth = (new RedCar()).getWidth();
            int gameWidth = getWidth();
            List<Integer> positions = new ArrayList<>();
            for (int i = 5; i < gameWidth; i += carWidth) {
                positions.add(i);
            }
            return positions;
        }
        public void stopped() {
            // Detener ambas pistas cuando se detiene el juego
            backgroundMusic1.stop();
            backgroundMusic2.stop();
            super.stopped();
        }
     // Clase Rocket
    public class Rocket extends Actor
    {
        private int speed = 5; // Velocidad del cohete
        private boolean movingRight; // Bandera para indicar la dirección del movimiento
        private int appearProbability = 2; // Probabilidad de aparición 
        
        public void act()
        {
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
        }
        
        public Rocket() {
            movingRight = Greenfoot.getRandomNumber(2) == 0;
            setImage("rocket.png"); 
        }
        
}
    }


