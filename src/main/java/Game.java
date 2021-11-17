import javax.swing.*;
import java.awt.*;
import java.awt.event.MouseEvent;
import java.util.Arrays;

public class Game extends JComponent {

    private static int cols, rows, tileSize;
    int[][] field;
    int[] selectedTail = new int[3];
    boolean win = false;
    int[] aForSort = new int[cols * rows];
    boolean test = false;
    int[] eArray;

    public Game(int cols, int rows, int tileSize) {
        enableEvents(AWTEvent.MOUSE_EVENT_MASK);
        Game.cols = cols;
        Game.rows = rows;
        Game.tileSize = tileSize;
        this.field = new int[cols][rows];

        initGame();
    }

    private void initGame() {

        if (win) {
            return;
        }

        int[] a = new int[cols * rows];
        int i = 0;

        getEArray();

        if (!test) {

            while (i != cols * rows - 1) {

                aForSort = Arrays.copyOf(a, a.length);
                Arrays.sort(aForSort);
                int intRnd = rnd((cols * rows) - 1);
                int idx = Arrays.binarySearch(aForSort, intRnd);

                if (idx < 0) {
                    a[i] = intRnd;
                    i++;
                }

                aForSort = Arrays.copyOf(a, a.length);
                Arrays.sort(aForSort);
            }
        } else {
            //test
            for (i = 1; i < cols*rows; i++) {
                a[i - 1] = i;
            }
            aForSort = Arrays.copyOf(a, a.length);
            Arrays.sort(aForSort);
        }

        int k = 0;
        for (i = 0; i < cols; i++) {
            for (int j = 0; j < rows; j++) {
                field[i][j] = a[k];
                k++;
            }
        }

        System.out.println("First a = " + Arrays.toString(a));
        System.out.println("Sort = " + Arrays.toString(aForSort));
        System.out.println("Field = " + Arrays.deepToString(field));
    }

    @Override
    protected void processMouseEvent(MouseEvent mouseEvent) {
        super.processMouseEvent(mouseEvent);
        if (mouseEvent.getButton() == MouseEvent.BUTTON1) {
            if (!win) {
                checkTail(mouseEvent.getX(), mouseEvent.getY());
                repaint();
            }else {
                win = false;
                initGame();
            }
        }
    }

    private void checkTail(int x, int y) {
        getSelectedTail(x, y);
        changeTailPosition();
        checkWin();
    }

    private void getEArray(){

        eArray = new int[cols*rows];

        for (int i=0; i<cols*rows; i++){
            eArray[i] = i+1;
        }
        eArray[eArray.length-1] = 0;
    }

    private void checkWin() {
        int[] testArray = new int[cols*rows];
        int k=0;
        for (int i = 0; i < cols; i++) {
            for (int j = 0; j < rows; j++) {
                testArray[k] = field[i][j];
                k++;
            }
        }

        if(Arrays.equals(testArray,eArray)){
            win = true;
        }
    }

    private void changeTailPosition() {

        System.out.println("changeTailPosition x = " + selectedTail[0] + " y = " + selectedTail[1] + " z = " + selectedTail[2]);

        if (selectedTail[2] != 0) {
            int testX = selectedTail[0];
            int testY = selectedTail[1];

            try {
                if (field[testX - 1][testY] == 0) {
                    field[testX - 1][testY] = selectedTail[2];
                    field[testX][testY] = 0;
                }
            } catch (Exception e) {
            }

            try {
                if (field[testX + 1][testY] == 0) {
                    field[testX + 1][testY] = selectedTail[2];
                    field[testX][testY] = 0;
                }

            } catch (Exception e) {
            }

            try {
                if (field[testX][testY - 1] == 0) {
                    field[testX][testY - 1] = selectedTail[2];
                    field[testX][testY] = 0;
                }

            } catch (Exception e) {
            }

            try {
                if (field[testX][testY + 1] == 0) {
                    field[testX][testY + 1] = selectedTail[2];
                    field[testX][testY] = 0;
                }

            } catch (Exception e) {
            }
        }
        selectedTail[2] = 0;
    }


    private void getSelectedTail(int x, int y) {

        double dx = (((double) x) / tileSize) - Math.floor(((double) x) / tileSize);
        double dy = (((double) y) / tileSize) - Math.floor(((double) y) / tileSize);
        if (dx > 0.9 || dx < 0.1 || dy > 0.9 || dy < 0.1) {
            System.out.println("q");
        } else {
            selectedTail[0] = Math.round(y / tileSize);
            selectedTail[1] = Math.round(x / tileSize);
            selectedTail[2] = field[Math.round(y / tileSize)][Math.round(x / tileSize)];
        }
    }

    @Override
    protected void paintComponent(Graphics graphics) {

        Graphics2D g2 = (Graphics2D) graphics;
        g2.setFont(new Font("default", Font.BOLD, 16));

        if(!win) {
            int z = 1;
            for (int y = 0; y < rows; y++) {
                for (int x = 0; x < cols; x++) {

                    if (field[x][y] != 0) {
                        g2.drawImage(getImg(), y * tileSize, x * tileSize, null);
                        g2.drawString("" + field[x][y], y * tileSize + 25, x * tileSize + 34);
                    }
                }
            }
        }else {
            int z = 1;
            for (int y = 0; y < rows; y++) {
                for (int x = 0; x < cols; x++) {

                    if (field[x][y] != 0) {
                        g2.drawImage(getImg(), y * tileSize, x * tileSize, null);
                        g2.drawString("" + field[x][y], y * tileSize + 25, x * tileSize + 34);
                    }
                }
            }
            g2.setFont(new Font("default", Font.BOLD, 50));
            g2.setColor(Color.RED);
            g2.drawString("WIN", (rows * tileSize)/2 - 48 , (cols * tileSize)/2);
        }
    }

    private int rnd(int max) {
        return (int) (Math.random() * max + 1);
    }

    public Image getImg() {

        try {
            ImageIcon img = new ImageIcon(this.getClass().getResource("/key.png"));
            Image key = img.getImage();
            return key;
        } catch (Exception e) {
            System.out.println(e);
            return null;
        }
    }
}
