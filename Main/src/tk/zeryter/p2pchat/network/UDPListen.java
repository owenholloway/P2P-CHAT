package tk.zeryter.p2pchat.network;

import java.net.DatagramPacket;
import java.net.DatagramSocket;

public class UDPListen {

    boolean running = true;

    int port, PACKETSIZE;

    public void listenString(int port, int PACKETSIZE) {

        String receivedData;

        try {
            // Convert the argument to ensure that is it valid
            System.out.println("Starting up on port: " + port);
            // Construct the socket
            DatagramSocket socket = new DatagramSocket(port);

            System.out.println("Ready to recieve on " + port + " ....");

            int discoveryCount = 0;

            while (running) {

                // Create a packet
                DatagramPacket packet = new DatagramPacket(new byte[PACKETSIZE], PACKETSIZE);

                // Receive a packet (blocking)
                socket.receive(packet);

                // Print the packet
                System.out.println(packet.getAddress() + " " + packet.getPort() + ": " + new String(packet.getData()));

                receivedData = new String(packet.getData());

                char receivedChar[];
                int charsRecived;

                String data = "";

                charsRecived = receivedData.length();

                receivedChar = new char[charsRecived];

                for (int i = 0; i < charsRecived; i++) {
                    receivedChar[i] = receivedData.charAt(i);
                }

                for (int i = 2; i < charsRecived; i++) {
                    data = data + receivedChar[i];
                }

                switch (receivedChar[0]) {

                    case 'u':

                        System.out.println("User:" + data);

                        break;

                }

            }
        } catch (Exception e) {
            System.out.println(e);
        }
    }

    public void run() {

        listenString(port, PACKETSIZE);
    }

    public void setPort(int port){
        this.port = port;

    }

    public void setPACKETSIZE(int packetsize) {
        this.PACKETSIZE = packetsize;
    }

    public void setRunning(boolean running) {
        this.running = running;
    }

}
