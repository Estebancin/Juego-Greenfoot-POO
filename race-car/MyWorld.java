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
            
            private GreenfootImage backgroundImage;
            private int backgroundSpeed = 1;
            
        
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
                 //Crea y agrega el fondo al mundo
                Background background = new Background("./fondo.png");
                addObject(background, getWidth() / 2, getHeight() / 2);
                
                xHistory = new ArrayList<>();
                xPositions = getXPositions();
                
                greenCar = new GreenCar(this);
                redCars = new ArrayList<>();
                
                addObject(greenCar, greenCar.getX(), greenCar.getY());
                addObject(new Score(this), 85, 10);
                addRedCar();

                backgroundMusic1 = new GreenfootSound("./pista 2.mp3");
                backgroundMusic2 = new GreenfootSound("./inicio.mp3");
    
    
    
                backgroundMusic1.setVolume(70); // Ajustar el volumen (0-100)
                backgroundMusic1.play();
                backgroundMusic2.setVolume(60);
                
                // Reproducir la primera pista de música
                isPlayingFirstMusic = true;
                for (Object obj : getObjects(null)) {
                    Actor actor = (Actor) obj;
                    actor.act();
                }
            }
    public class Background extends Actor {
    private GreenfootImage backgroundImage;
    private int backgroundSpeed = 1; // Velocidad de movimiento del fondo

    public Background(String imagePath) {
        backgroundImage = new GreenfootImage(imagePath);
        setImage(backgroundImage);
    }
            public void act() {
            super.act();
            int newY = getY() + backgroundSpeed;
            int worldHeight = getWorld().getHeight();
            
            if (newY >= worldHeight) {
                // Mover el fondo al inicio, justo arriba del mundo
                newY = -backgroundImage.getHeight() + (newY - worldHeight);
            }
            setLocation(getX(), newY);
    
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

}



