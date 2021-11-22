import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class SettingForm extends JFrame {

    private static int cols, rows, tileSize;
    static JFrame frame = new JFrame("Settings");
    JPanel panelButton = new JPanel();
    JPanel panelSetting = new JPanel();
    boolean testEnable = false;
    String textRows, textCols, textTileSize;
    JTextField textFieldRows, textFieldCols, textFieldTileSize;

    public SettingForm(int cols, int rows, int tileSize) {
        this.cols = cols;
        this.rows = rows;
        this.tileSize = tileSize;

        init();
    }

    public void init(){

        frame.setSize(rows * tileSize + 16, cols * tileSize + 80);
        frame.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        frame.setLocationRelativeTo(null);
        frame.setResizable(true);

        //label.setText("2222");

        JButton buttonSave = new JButton("Save");
        buttonSave.addActionListener(new ActionListener() {

            public void actionPerformed(ActionEvent e) {
                Main.flagSetting = false;
            }
        });

        JButton buttonCancel = new JButton("Cancel");
        buttonCancel.addActionListener(new ActionListener() {

            public void actionPerformed(ActionEvent e) {
                Main.flagSetting = false;
                Main.mainFrame.setVisible(true);
                frame.setVisible(false);
            }
        });

        JCheckBox testCheckBox = new JCheckBox("Test Enable");
        testCheckBox.addActionListener(new ActionListener() {

            public void actionPerformed(ActionEvent e) {
                testEnable = testCheckBox.isSelected();
            }
        });

        textFieldRows = new JTextField();
        textFieldCols = new JTextField();
        textFieldTileSize = new JTextField();


        panelButton.add(buttonSave);
        panelButton.add(buttonCancel);
        panelButton.setSize(cols * tileSize + 16, 30);
        //panel.add(label, BorderLayout.PAGE_START);

        panelSetting.add(testCheckBox,BorderLayout.WEST);
        panelSetting.setSize(rows * tileSize + 16, cols * tileSize + 80);

        frame.add(panelSetting, BorderLayout.PAGE_START);
        frame.add(panelButton,BorderLayout.PAGE_END);

    }
}
