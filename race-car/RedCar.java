import greenfoot.*;
import java.util.Random;

public class RedCar extends Actor {
    private static final Random _rand = new Random(40L);

    private int x;
    private int y;
    private int speed;
    private int direction; // 0: derecha, 1: arriba, 2: izquierda, 3: abajo

    public RedCar() {
        x = 0;
        y = 0;
        speed = 2; // Puedes ajustar la velocidad a tu preferencia
        direction = _rand.nextInt(4); // Genera un número aleatorio entre 0 y 3 para la dirección inicial
    }

    public RedCar(int x) {
        this.x = x;
        this.y = getImage().getHeight() / 2;
        speed = 2; // Puedes ajustar la velocidad a tu preferencia
        direction = _rand.nextInt(4); // Genera un número aleatorio entre 0 y 3 para la dirección inicial
    }

    public RedCar(World world) {
        int wWidth = world.getWidth();
        int wHeight = world.getHeight();
        int iHeight = getImage().getHeight();

        x = _rand.nextInt(wWidth);
        y = _rand.nextInt(wHeight);
        speed = 2; // Puedes ajustar la velocidad a tu preferencia
        direction = _rand.nextInt(4); // Genera un número aleatorio entre 0 y 3 para la dirección inicial
    }

    /**
     * Act - do whatever the RedCar wants to do. This method is called whenever
     * the 'Act' or 'Run' button gets pressed in the environment.
     */
    public void act() {
        moveRandomly();
        if (isAtEdge()) {
            ((MyWorld) getWorld()).removeRedCar(this);
        }
    }

    private void moveRandomly() {
        switch (direction) {
            case 0:
                x += speed;
                break;
            case 1:
                y -= speed;
                break;
            case 2:
                x -= speed;
                break;
            case 3:
                y += speed;
                break;
        }

        // Cambia la dirección aleatoriamente en cada paso
        if (_rand.nextInt(100) < 5) { // Probabilidad del 5% de cambiar de dirección
            direction = _rand.nextInt(4);
        }

        setLocation(x, y);
    }

    public int getX() { return x; }
    public int getY() { return y; }
    public int getWidth() { return getImage().getWidth(); }
    public int getHeight() { return getImage().getHeight(); }
}

