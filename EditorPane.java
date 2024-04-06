import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.InputEvent;
import java.awt.event.KeyEvent;
import java.awt.print.PrinterException;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.PrintWriter;

public class EditorPane extends JComponent {
    TextFrame frame;
    File file;
    TextEditor editor;
    public EditorPane (TextFrame frame){
        super();
        this.frame = frame;
        setLayout(new BorderLayout());
        FileSelector menubar = new FileSelector();
        add(menubar, BorderLayout.NORTH);
        JMenuItem filePicker = new JMenuItem("File");
        filePicker.addActionListener(new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent e) {
                JFileChooser fileChooser = new JFileChooser();

                switch (fileChooser.showOpenDialog(frame)) {
                    case JFileChooser.APPROVE_OPTION:
                        openFile(fileChooser.getSelectedFile());
                        break;
                }

                }
        }
        );

        JMenuItem saver = new JMenuItem("Save");
        saver.addActionListener( ae -> saveFile());

        KeyStroke key = KeyStroke.getKeyStroke(
                KeyEvent.VK_S, InputEvent.CTRL_MASK
        );
        saver.setAccelerator(key);


        JMenuItem saveAs = new JMenuItem("Save As");
        saveAs.addActionListener( ae -> saveFileAs());




        this.editor = new TextEditor();
        add(editor);

        JMenuItem print = new JMenuItem("Print");
        print.addActionListener(
                ae ->{
                    try {
                        boolean done = editor.print();
                        if(done){
                            System.out.println("Done Printing");
                        }
                        else {
                            System.out.println("Error While Printing");
                        }
                    } catch (PrinterException e) {
                        throw new RuntimeException(e);
                    }
                }
        );





        menubar.add(filePicker);
        menubar.add(saver);
        menubar.add(saveAs);

        menubar.add(print);



    }


    public void openFile(File f){
        file = f;
        remove(editor);
        this.editor = new TextEditor(f);
        add(editor);
        frame.setTitle(f.getName());
        repaint();
        revalidate();
    }
    private void saveFile(){
        if(file == null) {
            saveFileAs();
            return;
        }


        try {
            PrintWriter writer = new PrintWriter(file);
            writer.print(editor.getText());
            writer.close();
            } catch (FileNotFoundException e) {
                throw new RuntimeException(e);
            }
    }
    private void saveFileAs(){
            JFileChooser fileChooser = new JFileChooser();
            fileChooser.showSaveDialog(this);
            file = fileChooser.getSelectedFile();
            frame.setTitle(file.getName());

        try {
            PrintWriter writer = new PrintWriter(file);
            writer.print(editor.getText());
            writer.close();
        } catch (FileNotFoundException e) {
            throw new RuntimeException(e);
        }


    }



}
