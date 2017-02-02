package GUI;

import controller.Controller;
import domain.ServResponse;

import javax.swing.*;
import java.awt.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.io.IOException;

/**
 * Created by admin on 01.02.2017.
 */

public class MainForm extends  JFrame{
    private JButton downloadFtp;
    private JPanel buttonsPanel;
    private JButton unzipFiles;
    private JButton parseXML;
    private JTextField cFtptestTextField;
    private JLabel label;
    private Controller control;

    private void ShowResult(ServResponse sr) {
            if (sr.getSuccess())
            {
                javax.swing.JOptionPane.showMessageDialog(this,"Процесс выполнен. "+sr.getComment());
            }
            else
            {
                javax.swing.JOptionPane.showMessageDialog(this,"Процесс не выполнен. "+sr.getComment());
            }
        }

    public MainForm() {
        super("MyTest");
       control = new Controller();
        Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();
        double width = screenSize.getWidth();
        double height = screenSize.getHeight();
        setBounds((int)width/2, (int)height/2, 400, 400);



        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        add(buttonsPanel, BorderLayout.CENTER);

        downloadFtp.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                super.mouseClicked(e);
                try {
                    control.setWorkDir(cFtptestTextField.getText());
                    ShowResult(control.downloadFTP1());
                } catch (IOException e1) {
                    e1.printStackTrace();
                }

            }
        });
        unzipFiles.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                super.mouseClicked(e);
                control.setWorkDir(cFtptestTextField.getText());
             ShowResult(control.unzipFiles());
            }
        });

        parseXML.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                super.mouseClicked(e);
                control.setWorkDir(cFtptestTextField.getText());
                ShowResult(control.XMLParseAndSave());
            }
        });
    }

    public static void main(String[] args) {
        MainForm app = new MainForm();
        app.setVisible(true);

    }

}
