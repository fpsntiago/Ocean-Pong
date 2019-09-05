import java.awt.*;

public class GameBlocks extends GameCollision {
    private int Score;
    public int getScore(){
        return Score;
    }

    public Rectangle GetRectangle(){return new Rectangle(xx, yy, getXX(), getYY());
    }

    public GameBlocks(Image gif, int xx, int yy, int score) {
        super(gif, xx, yy);
        this.Score = score;
        xDim = gif.getWidth(null);
        yDim = gif.getHeight(null);
    }
    //public int keepScore(){
    //    return Score;
    //}
}