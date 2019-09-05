import java.awt.*;

public class Pop extends GameCollision {
    private double yySpeed = 5;
    private double xxSpeed = 3;

    public void update() {
        yySpeed += .1;
        xx += (int) Math.round(xxSpeed);
        yy += (int) Math.round(yySpeed);
        if (xx < 0 || xx > RainbowReef.gWth - 30) {
            xxSpeed = -xxSpeed;
        }
        if (yy < 0 || yy > RainbowReef.gHt - 30) {
            yySpeed = -yySpeed;
        }
        //xx == -xx
        //yy == -yy
    }
        public Pop(Image img, int x, int y) {
        super(img, x, y);
        xDim = img.getWidth(null);
        yDim = img.getHeight(null);
    }
    public void YaxisSpeed(double verticalSpeed) {
        yySpeed = verticalSpeed;
        yySpeed -= 0.35;
    }
    public double getYaxisSpeed() {
        return yySpeed;
    }
    public Rectangle getRect() {
        return new Rectangle(xx, yy, getXX(), getYY());
    }

    }

