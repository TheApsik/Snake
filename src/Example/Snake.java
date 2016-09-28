package Example;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.util.Random;
import java.util.Timer;
import java.util.TimerTask;

/**
 * Created by uczen on 06.09.2016.
 */
public class Snake extends TestFrame{
    private int lengthSnake = 3;
    private Area[][] board;
    private int[][] boardInt;
    private int headX;
    private int headY;
    private int sizeMap;
    private Timer timer;

    public Snake(int sizeMap) {
        super(700,700,sizeMap);
        this.sizeMap = sizeMap;
        this.boardInt = new int[sizeMap][sizeMap];
        this.board = new Area[sizeMap][sizeMap];
        for(int i = 0; i<sizeMap; i++){
            for(int a = 0; a<sizeMap; a++){
                if(i == 0 || a == 0 || a == sizeMap-1 || i == sizeMap-1) {
                    this.board[i][a] = Area.WALL;
                    setColor(i,a,sizeMap, Area.WALL.getColor());
                    continue;
                }
                if(i == sizeMap/2) {
                    if (a > 1 && a < 6) {
                        if (a == 5) {
                            this.board[i][a] = Area.HEAD;
                            headX = i;
                            headY = a;
                            setColor(i,a,sizeMap, Area.HEAD.getColor());
                            continue;
                        }
                        this.board[i][a] = Area.BODY;
                        moveBody();
                        this.boardInt[i][a] = 1;
                        setColor(i,a,sizeMap, Area.BODY.getColor());
                        continue;
                    }
                }
                this.board[i][a] = Area.AIR;
                setColor(i,a,sizeMap, Area.AIR.getColor());
            }
        }

        //for( int as = 0; as < 100; as++)
            putApple();

        timer = new Timer();

        timer.schedule( new TimerTask() {
            public void run() {
                move();
            }
        }, 0, 100);
    }

    private void move(){
        changeDirectory = true;
        moveBody();
        this.boardInt[headX][headY] = 1;
        setColor(headX,headY,sizeMap, Area.BODY.getColor());
        this.board[headX][headY] = Area.BODY;
        switch (direction){
            case TOP:
                --headX;
                break;
            case RIGHT:
                ++headY;
                break;
            case LEFT:
                --headY;
                break;
            case BOTTOM:
                ++headX;
                break;
        }

        switch (this.board[headX][headY]){
            case WALL:
            case BODY:
                timer.cancel();
                System.out.println("KONIEC GRY! Twoja liczba punktów to: " + (lengthSnake - 3));
                break;
            case APPLE:
                lengthSnake++;
                putApple();
                break;
        }
        this.board[headX][headY] = Area.HEAD;
        setColor(headX,headY,sizeMap, Area.HEAD.getColor());
    }

    private void putApple(){
        Random rand = new Random();
        int x = rand.nextInt(sizeMap);
        int y = rand.nextInt(sizeMap);

        if(this.board[x][y] == Area.AIR){
            this.board[x][y] = Area.APPLE;
            setColor(x,y,sizeMap, Area.APPLE.getColor());
        }
        else
            putApple();
    }

    private void moveBody(){
        for(int x =0; x<sizeMap; x++){
            for(int y =0; y<sizeMap; y++){
                if(board[x][y] == Area.BODY) {
                    boardInt[x][y]++;
                    if (boardInt[x][y] > lengthSnake) {
                        board[x][y] = Area.AIR;
                        setColor(x,y,sizeMap, Area.AIR.getColor());
                        boardInt[x][y] = 0;
                    }
                }
            }
        }
    }

    private enum Area{
        BODY(0,180,0),
        HEAD(0,200,0),
        WALL(50, 50, 150),
        APPLE(255, 0, 0),
        AIR(255,255,255);

        private Color color;

        Area(int r, int g, int b){
            this.color = new Color(r,g,b);
        }

        public Color getColor(){
            return color;
        }
    }
}
