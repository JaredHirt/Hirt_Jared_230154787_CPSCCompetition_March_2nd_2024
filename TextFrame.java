import javax.swing.*;

public class TextFrame extends JFrame {
    public TextFrame(){
        super();
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setSize(600, 600);
        setTitle("File Not Saved");
        JFileChooser fileChooser = new JFileChooser();
        setVisible(true);
    }


}
