import javax.swing.*;
import java.awt.*;

public class Main extends JFrame {

    private static int COLS = 4;
    private static int ROWS = COLS;
    private static int TileSize = 64;
    private JTextArea[][] grids;

    public Main() {
        grids = new JTextArea[ROWS][COLS];
        init();
    }

    public void init() {
        JFrame window = new JFrame("Game of Fifteen");
        window.setSize(ROWS * TileSize + 16, COLS * TileSize + 40);
        window.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
//        window.setLayout(new GridLayout(ROWS, COLS, 64, 64));

//        for (int i = 0; i < ROWS; i++) {
//            for (int j = 0; j < COLS; j++) {
//                grids[i][j] = new JTextArea(20, 20);
//                grids[i][j].setBackground(Color.WHITE);
//                grids[i][j].setEditable(false);
//                window.add(grids[i][j]);
//            }
//        }
        window.setLayout(new BorderLayout());
        window.setLocationRelativeTo(null);

        window.setResizable(false);
        Game game = new Game(COLS, ROWS, TileSize);
        window.add(game);
        window.setVisible(true);
    }

    public static void main(String[] args) {
        Main m = new Main();
    }

}
