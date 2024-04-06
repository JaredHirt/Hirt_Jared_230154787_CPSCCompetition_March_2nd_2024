import javax.swing.*;
import java.awt.*;
import java.io.File;

public class Main {
    public static void main(String[] args) {
        SwingUtilities.invokeLater(
                () -> startGUI(args)
        );
    }
    public static void startGUI(String[] args){
        TextFrame frame = new TextFrame();
        EditorPane pane = new EditorPane(frame);
        FileSelector menuBar = new FileSelector();

        try{
            File file = new File(args[0]);
            if(file.exists())
                pane.openFile(file);
        } catch(Exception e){}

        frame.add(pane);

    }
}