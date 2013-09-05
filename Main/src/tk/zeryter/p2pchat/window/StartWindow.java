package tk.zeryter.p2pchat.window;

import tk.zeryter.p2pchat.Vairables;

import javax.swing.*;
import javax.swing.border.BevelBorder;
import java.awt.*;
import java.awt.event.*;

public class StartWindow implements Runnable, ActionListener {

    JFrame frame = new JFrame();

    public void run() {

        frame.setSize(400,150);
        frame.setTitle("P2P-CHAT by Owen Holloway GYC (ZerytSoft)");
        frame.setVisible(true);
        frame.addWindowListener(new StartWindowMonitor());
        frame.setResizable(false);

        Container c = new Container();
        frame.add(c);
        c.setBounds(0, 0, frame.getWidth(), frame.getHeight());

        gui_init(c);
        gui_draw(c);

    }

    //GUI widgets

    JButton startChatting;
    JTextArea uName,uNamePrompt, port, defaultPortPrompt, portPrompt;
    JCheckBox defaultPort;

    void gui_init(Container c) {

        startChatting = new JButton("start chatting");
        startChatting.addActionListener(this);
        c.add(startChatting);

        uName = new JTextArea();
        uName.setBorder(new BevelBorder(1));
        c.add(uName);
        uNamePrompt = new JTextArea();
        uNamePrompt.setEditable(false);
        uNamePrompt.setText("User Name:");
        uNamePrompt.setBackground(Color.GRAY);
        c.add(uNamePrompt);

        port = new JTextArea();
        port.setEditable(false);
        c.add(port);
        portPrompt = new JTextArea();
        portPrompt.setEditable(false);
        portPrompt.setText("Port: ");
        portPrompt.setBackground(Color.GRAY);
        c.add(portPrompt);
        defaultPortPrompt = new JTextArea();
        defaultPortPrompt.setEditable(false);
        defaultPortPrompt.setText("Default port");
        defaultPortPrompt.setBackground(Color.GRAY);
        c.add(defaultPortPrompt);

        defaultPort = new JCheckBox();
        c.add(defaultPort);

    }

    void gui_draw(Container c) {

        c.setBounds(0,0,frame.getWidth(),frame.getHeight());

        startChatting.setBounds(280,80,100,30);

        uName.setBounds(110,10,270,20);
        uNamePrompt.setBounds(10,10,100,20);

        defaultPort.setBounds(110,50,20,20);
        defaultPortPrompt.setBounds(10, 50, 100, 20);

        portPrompt.setBounds(10, 90, 100, 20);

    }

    public void actionPerformed(ActionEvent e) {

        if (e.getSource() == startChatting) {
            if (uName.getText().equals("")) {
                startChatting.setBackground(Color.red);
                startChatting.setText("NO USER");
                uName.setBackground(Color.red);
            } else {
                Vairables.userName = uName.getText();
                System.out.println("Set username: " + Vairables.userName);
                frame.dispose();
                new Thread(Vairables.mainWindow).start();
            }
        }

    }
}

class StartWindowMonitor extends WindowAdapter implements WindowListener {

    public void windowClosing(WindowEvent e) {
        System.exit(0);
    }
}

