import javax.swing.*;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.PrintWriter;
import java.util.Scanner;

public class TextEditor extends JTextPane {
    public TextEditor(){
        super();
    }

    public TextEditor(File f){
        super();
        try {
            String text = "";
            Scanner scanner = new Scanner(f);
            while(scanner.hasNext()){
                text = text  + scanner.nextLine() + "\n";
            }
            setText(text);

        } catch (FileNotFoundException e) {
            throw new RuntimeException(e);
        }

    }



}
