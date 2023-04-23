package pong.multiplayer;

import pong.GameFrame;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.ServerSocket;
import java.net.Socket;

public class Server extends Thread {
    final static int PORT = 8888;
    Socket socket;
    ObjectOutputStream outputStream;
    ObjectInputStream inputStream;
    GameFrame gameFrame;
    boolean flag = true;

    public Server() throws InterruptedException {
        serverConnection();
        this.gameFrame = new GameFrame();
        start();
    }

    private void serverConnection() throws InterruptedException {

        try {
            // create a server socket
            try (ServerSocket serverSocket = new ServerSocket(PORT)) {

                // wait for a client to connect
                socket = serverSocket.accept();
            } catch (IOException e) {
                throw new RuntimeException(e);
            }

            // get the input and output streams
            outputStream = new ObjectOutputStream(socket.getOutputStream());
            inputStream = new ObjectInputStream(socket.getInputStream());

        } catch (IOException e) {
            throw new RuntimeException(e);
        }

        sleep(1000);
    }

    public void start() {
        write();
        read();
    }

    private void write() {

        Thread thread = new Thread(new Runnable() {
            @Override
            public void run() {
                while (true) {

                    // write a Data object to the client
                    DataServer dataServer = new DataServer(gameFrame.panel.ball, gameFrame.panel.paddle1, gameFrame.panel.score);
                    try {
                        outputStream.writeObject(dataServer);
                        System.out.println("Sent data to client: " + dataServer.yPaddle);
                    } catch (IOException ignored) {
                    }

                    // sleep
                    try {
                        sleep(50);
                    } catch (InterruptedException e) {
                        throw new RuntimeException(e);
                    }

                }
            }
        });
        thread.start();
    }

    private void read() {

        Thread thread = new Thread(new Runnable() {
            @Override
            public void run() {
                while (true) {

                    // read a Data object from the client
                    DataClient dataClient;
                    try {
                        dataClient = (DataClient) inputStream.readObject();
                        System.out.println("Received data from client: " + dataClient.yPaddle);
                        gameFrame.panel.paddle3.y = dataClient.yPaddle;
                    } catch (IOException | ClassNotFoundException ignored) {
                    }

                    // sleep
                    try {
                        sleep(50);
                    } catch (InterruptedException e) {
                        throw new RuntimeException(e);
                    }

                }
            }
        });
        thread.start();
    }

    public static void main(String[] args) throws InterruptedException {

        Server server = new Server();
    }
}