import javax.imageio.ImageIO;
import javax.swing.*;
import java.io.File;
import java.util.ArrayList;
import java.awt.*;
import java.awt.event.WindowAdapter;
import java.awt.image.BufferedImage;
import java.awt.event.KeyEvent;

public class RainbowReef extends JPanel {
    public static final int gHt = 500, gWth = 650;
    private final int FPS = 60;
    private int pops, score;
    private Katch Shell;
    private JFrame JFrame;
    private Graphics2D Graphics2D;
    private BufferedImage BufferedImage;

    private Image bg, POP, KATCH, blk_p, blk_y,
            blk_r, blk_g, blk_t, blk_b, BigLeg, blk_life, solid;

    private EventHandler ReefEvents;

    boolean CurrentGame;

    Sounds sounds;
    private String music = "resources/Music.wav";
    private String click = "resources/Sound_click.wav";

    ArrayList<Pop> Pops = new ArrayList<>();
    ArrayList<GameBlocks> Blocks = new ArrayList<>();
    ArrayList<GameBlocks> bigleg = new ArrayList<>();
    ArrayList<GameBlocks> PowerUp = new ArrayList<>();
    ArrayList<GameBlocks> SOLID = new ArrayList<>();

    public static void main(String[] args) {
        RainbowReef reef = new RainbowReef();
        reef.fetchResources();
        reef.begin();


    }
    private void begin()
    {
        CurrentGame = false;
        this.Level_1();
        this.run();

    }
    private void fetchResources() {
        try{
            bg = ImageIO.read(new File("resources/Background2.bmp"));
            KATCH = ImageIO.read(new File("resources/Katch.png"));
            POP = ImageIO.read(new File("resources/Pop.png"));
            BigLeg = ImageIO.read(new File("resources/Bigleg.png"));
            blk_p = ImageIO.read(new File("resources/Block1.gif"));
            blk_y = ImageIO.read(new File("resources/Block2.gif"));
            blk_r = ImageIO.read(new File("resources/Block3.gif"));
            blk_g= ImageIO.read(new File("resources/Block4.gif"));
            blk_t = ImageIO.read(new File("resources/Block5.gif"));
            blk_b = ImageIO.read(new File("resources/Block6.gif"));
            blk_life = ImageIO.read(new File("resources/Block_life.gif"));
            solid = ImageIO.read(new File("resources/Block_solid.gif"));
            //needs resources in same folder with jar to run
            sounds.WAV(music);

        } catch (Exception e) {
            System.out.print(e.getStackTrace() + "Loading Error");
        }
        JFrame = new JFrame();
        JFrame.addWindowListener(new WindowAdapter(){});
        JFrame.setTitle("Rainbow Reef");
        JFrame.add(this);
        JFrame.setVisible(true);
        JFrame.pack();
        JFrame.setSize(gWth, gHt);
        JFrame.setResizable(false);
        JFrame.getContentPane().setFocusable(true);
        JFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

    }

    public void checkCollisions() {
        for(int i = 0; i < Pops.size(); i++) {
            Rectangle ball = Pops.get(i).getRect();
            Rectangle paddle = Shell.getRect();

            if(ball.intersects(paddle)) {
                Pops.get(i).YaxisSpeed(-Pops.get(i).getYaxisSpeed());
                //sounds.WAV(click);
                //System.out.println("Check Katch");
            }

            for(int j = 0; j < Blocks.size(); j++) {
                Rectangle bricks = Blocks.get(j).GetRectangle();

                if(ball.intersects(bricks)) {
                    Pops.get(i).YaxisSpeed(-Pops.get(i).getYaxisSpeed());
                    this.score += Blocks.get(j).getScore();
                    Blocks.remove(j);
                    //sounds.WAV(click);
                    //System.out.println("Check Pop");
                }
            }
            for(int j = 0; j < SOLID.size(); j++) {
                Rectangle bricks = SOLID.get(j).GetRectangle();

                if(ball.intersects(bricks)) {
                    Pops.get(i).YaxisSpeed(-Pops.get(i).getYaxisSpeed());
                    //sounds.WAV(click);
                    //System.out.println("Check Pop");
                }
            }

            for(int ii = 0; ii < PowerUp.size(); ii++) {
                Rectangle brickRect = PowerUp.get(ii).GetRectangle();

                if(ball.intersects(brickRect)) {
                    Pops.get(i).YaxisSpeed(-Pops.get(i).getYaxisSpeed());
                    this.score += PowerUp.get(ii).getScore();
                    PowerUp.remove(ii);
                    pops += 1;
                    //System.out.println("Check test");
                }
            }
            for(int k = 0; k < bigleg.size(); k++) {
                Rectangle rect = bigleg.get(k).GetRectangle();

                if(ball.intersects(rect)) {
                    Pops.get(i).YaxisSpeed(-Pops.get(i).getYaxisSpeed());
                    bigleg.remove(k);
                    //sounds.WAV(click);
                    //pops = 0;
                    //System.out.println("Check BigLeg");
                }
            }

        }
    }
    @Override
    public void paintComponent(Graphics g) {

        if (BufferedImage == null) {
            BufferedImage = (BufferedImage) createImage(gWth, gHt);
        }
        Graphics2D tmp = (Graphics2D) g;
        Graphics2D = BufferedImage.createGraphics();
        super.paintComponent(tmp);

        Graphics2D.drawImage(bg, 0, 0, this);
        for (int i = 0; i < Blocks.size(); i++) {
            Graphics2D.drawImage(Blocks.get(i).getImg(), Blocks.get(i).getX(), Blocks.get(i).getY(), this);
        }
        for (int i = 0; i < SOLID.size(); i++) {
            Graphics2D.drawImage(SOLID.get(i).getImg(), SOLID.get(i).getX(), SOLID.get(i).getY(), this);
        }

        for (int i = 0; i < Pops.size(); i++) {
            Graphics2D.drawImage(Pops.get(i).getImg(), Pops.get(i).getX(), Pops.get(i).getY(), this);
        }

        for (int i = 0; i < bigleg.size(); i++) {
            Graphics2D.drawImage(bigleg.get(i).getImg(), bigleg.get(i).getX(), bigleg.get(i).getY(), this);
        }
        for (int i = 0; i < PowerUp.size(); i++) {
            Graphics2D.drawImage(PowerUp.get(i).getImg(), PowerUp.get(i).getX(), PowerUp.get(i).getY(), this);
        }

        Graphics2D.drawImage(Shell.getImg(), Shell.getX(), Shell.getY(), this);
        tmp.drawImage(BufferedImage, 0, 0, this);

        tmp.setFont((new Font("Courier", Font.BOLD, 10)));
        String s = "Score: " + this.score;
        tmp.setColor(Color.BLACK);
        tmp.drawString(s, 565, 440);
        tmp.setFont((new Font("Courier", Font.BOLD, 10)));
        s = "Pops: " + this.pops;
        tmp.setColor(Color.BLACK);
        tmp.drawString(s, 565, 450);

        if (bigleg.isEmpty()) {
            tmp.setFont((new Font("Courier", Font.BOLD, 25)));
            tmp.setColor(Color.BLACK);
            tmp.drawString("" + "gg ez", 280, 250);
            tmp.setFont((new Font("Courier", Font.PLAIN, 15)));
            tmp.setColor(Color.BLACK);
            tmp.drawString("Score: " + this.score, 285, 270);
        }
        if (pops == 0 && !bigleg.isEmpty()) {
            tmp.setFont((new Font("Courier", Font.BOLD, 25)));
            tmp.setColor(Color.BLACK);
            tmp.drawString("" + "Game Over", 250, 250);
            tmp.setFont((new Font("Courier", Font.PLAIN, 15)));
            tmp.setColor(Color.BLACK);
            tmp.drawString("Score: " + this.score, 285, 270);
        }
    }

    private void run() {
        long current;
        long Time = 1000000000 / FPS;
        //while ((pops > 0 || octopus.isEmpty())) {
        while (pops > 0) {
            current = System.nanoTime();
            UpdateGame();
            repaint();
            if ((current - System.nanoTime() + Time) > 0) {
                try {
                    Thread.sleep((current - System.nanoTime() + Time) / 1000000);
                } catch (InterruptedException ex) {
                    Thread.currentThread().interrupt();
                }
            }
        }
    }

    private void UpdateGame() {
        Shell.move();
        if(Pops.isEmpty()) {
            Pops.add(new Pop(POP, 200, 240));
        }
        for(int i = 0; i < Pops.size(); i++)
        {
            checkCollisions();
            Pops.get(i).update();
            if(Pops.get(i).getY() > gHt-35) {
                Pops.remove(i);
                i--;
                if(Pops.isEmpty())
                    pops--;
            }
        }
    }
    private void Level_1() {

        this.score = 0;
        this.pops = 3;

        Shell = new Katch(KATCH, 280, 420, KeyEvent.VK_LEFT, KeyEvent.VK_RIGHT);

        ReefEvents = new EventHandler();
        ReefEvents.addObserver(Shell);
        SetControls Controls = new SetControls(ReefEvents);

        JFrame.getContentPane().requestFocusInWindow();
        JFrame.getContentPane().addKeyListener(Controls);

        bigleg.add(new GameBlocks(BigLeg, 180, 0, 1000));
        bigleg.add(new GameBlocks(BigLeg, 380, 0, 1000));

        for(int i = 0; i < 4; i ++) {
            Blocks.add(new GameBlocks(blk_r, 20 + 40 * i, 0, 125));
        }
        for(int i = 6; i < 9; i ++) {
            Blocks.add(new GameBlocks( blk_b, 20 + 40 * i, 0, 125));
        }
        for(int i = 11; i < 15; i ++) {
            Blocks.add(new GameBlocks( blk_b, 20 + 40 * i, 0, 125));
        }
        for(int i = 0; i < 4; i ++) {
            Blocks.add(new GameBlocks(blk_p, 20 + 40 * i, 20, 100));
        }
        for(int i = 6; i < 9; i ++) {
            Blocks.add(new GameBlocks( blk_g, 20 + 40 * i, 20, 100));
        }
        for(int i = 11; i < 15; i ++) {
            Blocks.add(new GameBlocks( blk_p, 20 + 40 * i, 20, 100));
        }
        for(int i = 0; i < 4; i ++) {
            Blocks.add(new GameBlocks( blk_r, 20 + 40 * i, 40, 90));
        }
        for(int i = 6; i < 9; i ++) {
            Blocks.add(new GameBlocks( blk_t, 20 + 40 * i, 40, 90));
        }
        for(int i = 11; i < 15; i ++) {
            Blocks.add(new GameBlocks( blk_r, 20 + 40 * i, 40, 90));
        }
        for(int i = 0; i < 4; i ++) {
            Blocks.add(new GameBlocks( blk_g, 20 + 40 * i, 60, 75));
        }
        for(int i = 6; i < 9; i ++) {
            Blocks.add(new GameBlocks( blk_y, 20 + 40 * i, 60, 75));
        }
        for(int i = 11; i < 15; i ++) {
            Blocks.add(new GameBlocks( blk_g, 20 + 40 * i, 60, 75));
        }
        for(int i = 0; i < 2; i ++) {
            Blocks.add(new GameBlocks( blk_r, 20 + 40 * i, 80, 50));
        }
        for(int i = 3; i < 12; i ++) {
            Blocks.add(new GameBlocks( blk_r, 20 + 40 * i, 80, 50));
        }
        for(int i = 13; i < 15; i ++) {
            Blocks.add(new GameBlocks( blk_r, 20 + 40 * i, 80, 50));
        }
        for(int i = 2; i < 3; i ++) {
            PowerUp.add(new GameBlocks(blk_life, 20 + 40 * i, 80, 50));
        }
        for(int i = 12; i < 13; i ++) {
            PowerUp.add(new GameBlocks(blk_life, 20 + 40 * i, 80, 50));
        }
        for(int i = 0; i < 2; i ++) {
            Blocks.add(new GameBlocks( blk_b, 20 + 40 * i, 100, 20));
        }
        for(int i = 2; i < 3; i ++) {
            SOLID.add(new GameBlocks(solid, 20 + 40 * i, 100, 5));
        }
        for(int i = 3; i < 12; i ++) {
            Blocks.add(new GameBlocks( blk_b, 20 + 40 * i, 100, 20));
        }
        for(int i = 12; i < 13; i ++) {
            SOLID.add(new GameBlocks(solid, 20 + 40 * i, 100, 5));
        }
        for(int i = 13; i < 15; i ++) {
            Blocks.add(new GameBlocks( blk_b, 20 + 40 * i, 100, 20));
        }
        for(int i = 0; i < 15; i ++) {
            Blocks.add(new GameBlocks( blk_y, 20 + 40 * i, 120, 15));
        }
        for(int i = 8; i < 15; i ++) {
            Blocks.add(new GameBlocks( blk_r, 20 + 40 * i, 140, 5));
        }
        for(int i = 7; i < 8; i ++) {
            PowerUp.add(new GameBlocks(blk_life, 20 + 40 * i, 140, 50));
        }
        for(int i = 0; i < 7; i ++) {
            Blocks.add(new GameBlocks( blk_r, 20 + 40 * i, 140, 5));
        }
        for(int i = 0; i < 15; i ++) {
            Blocks.add(new GameBlocks( blk_t, 20 + 40 * i, 160, 10));
        }

        for(int i = 8; i < 15; i ++) {
            Blocks.add(new GameBlocks( blk_g, 20 + 40 * i, 180, 5));
        }
        for(int i = 7; i < 8; i ++) {
            PowerUp.add(new GameBlocks(blk_life, 20 + 40 * i, 180, 50));
        }
        for(int i = 0; i < 7; i ++) {
            Blocks.add(new GameBlocks( blk_g, 20 + 40 * i, 180, 5));
        }
        for(int i = 0; i < 15; i ++) {
            Blocks.add(new GameBlocks( blk_b, 20 + 40 * i, 200, 5));
        }
        for(int i = 2; i < 4; i ++) {
            SOLID.add(new GameBlocks(solid, 20 + 40 * i, 230, 5));
        }
        for(int i = 12; i < 14; i ++) {
            SOLID.add(new GameBlocks(solid, 20 + 40 * i, 230, 5));
        }
        Pops.add(new Pop(POP, 200, 240));
        //sounds.WAV(music);
    }
}
