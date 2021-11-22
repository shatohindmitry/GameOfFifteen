import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class Main extends JFrame {

    private static int COLS = 4;
    private static int ROWS = 4;
    private static int TileSize = 64;
    static JLabel label = new JLabel("");
    static boolean flagSetting = false;
    static JFrame mainFrame = new JFrame("Game of Fifteen");
    SettingForm settingForm = new SettingForm(COLS, ROWS, TileSize);
    JMenuBar menuBar = new JMenuBar();
    Game game;
    ImageIcon imageIconSetting;
    JMenu settings;
    JMenuItem setting;
    JPanel panelMain = new JPanel();

    JPanel panelButton = new JPanel();
    JPanel panelSetting = new JPanel();
    boolean testEnable = false;
    JTextField textFieldRows = new JTextField("ROWS");
    JTextField textFieldCols = new JTextField("COLS");
    JTextField textFieldTileSize = new JTextField("TileSize");
    JButton buttonSave = new JButton("Save");
    JButton buttonCancel = new JButton("Cancel");
    JCheckBox testCheckBox = new JCheckBox("Test Enable");

    public Main() {
        imageIconSetting = getImageIcon("/settings.gif");
        settings = new JMenu("Settings");
        setting = new JMenuItem("Settings");
        getNewGame();
        drawField();
        init();

    }

    public void init() {

        if (!flagSetting) {
            drawGameField();
        } else {
            drawSettingField();
        }
        repaint();
        revalidate();
    }

    private void drawGameField() {

        mainFrame.remove(panelButton);
        mainFrame.remove(panelSetting);
        //panelMain.setSize(new Dimension(300,100));
        //panelMain.add(game);
        //mainFrame.add(panelMain);
        mainFrame.add(game, BorderLayout.CENTER);
        mainFrame.setSize(ROWS * TileSize + 16, COLS * TileSize + 80);
    }

    private void getNewGame() {
        game = new Game(COLS, ROWS, TileSize);
    }

    private void drawSettingField() {
        mainFrame.setSize(ROWS * TileSize + 17, COLS * TileSize + 80);
        mainFrame.remove(game);

        buttonSave.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent event) {
                flagSetting = false;
                try {
                    ROWS = Integer.parseInt(textFieldRows.getText());
                    COLS = Integer.parseInt(textFieldCols.getText());
                    //TileSize = Integer.parseInt(textFieldTileSize.getText());

                    getNewGame();
                    init();

                }catch (Exception exception){
                    init();
                }
            }
        });

        buttonCancel.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                flagSetting = false;
                init();
            }
        });


        testCheckBox.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                Game.test = testCheckBox.isSelected();
            }
        });


        panelButton.add(buttonSave, BorderLayout.EAST);
        panelButton.add(buttonCancel, BorderLayout.EAST);
        panelButton.setSize(COLS * TileSize + 16, 30);
        //panel.add(label, BorderLayout.PAGE_START);

        panelSetting.add(testCheckBox, BorderLayout.WEST);
        panelSetting.add(textFieldCols);
        panelSetting.add(textFieldRows);
        panelSetting.add(textFieldTileSize);
        panelSetting.setSize(ROWS * TileSize + 16, COLS * TileSize + 80);

        mainFrame.add(panelSetting, BorderLayout.PAGE_START);
        mainFrame.add(panelButton, BorderLayout.PAGE_END);

    }

    private void drawField() {

        mainFrame.setSize(ROWS * TileSize + 16, COLS * TileSize + 80);
        mainFrame.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        mainFrame.setLocationRelativeTo(null);
        mainFrame.setResizable(false);
        mainFrame.setLayout(new BorderLayout());


        menuBar.add(setting);
        setJMenuBar(menuBar);
        menuBar.setSize(COLS * TileSize + 16, 30);
        mainFrame.add(menuBar, BorderLayout.PAGE_START,0);

        setting.addActionListener(new ActionListener() {

            public void actionPerformed(ActionEvent e) {
                if (flagSetting) {
                    flagSetting = false;
                } else {
                    flagSetting = true;
                }
                init();
            }
        });

        label.setSize(COLS * TileSize + 16, 30);

        mainFrame.add(label, BorderLayout.PAGE_END);
        mainFrame.setVisible(true);
    }

    private ImageIcon getImageIcon(String path) {
        try {
            return new ImageIcon(this.getClass().getResource("" + path));
        } catch (Exception e) {
            System.out.println(e);
            return new ImageIcon();
        }
    }

    public static void main(String[] args) {
        new Main();
    }
}
