import java.awt.*;

abstract public class GameCollision {

    int xx, yy, yDim, xDim;
    Image image;

    public int getX()
    {
        return this.xx;
    }
    public int getY()
    {
        return this.yy;
    }
    public int getXX()
    {
        return this.xDim;
    }
    public int getYY()
    {
        return this.yDim;
    }
    public Image getImg()
    {
        return this.image;
    }

    public GameCollision(Image img, int xAxis, int yAxis) {
        this.image = img;
        this.xx = xAxis;
        this.yy = yAxis;
    }

}