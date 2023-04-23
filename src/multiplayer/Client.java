/*package pong.multiplayer;



import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.Socket;

public class Client extends Thread {

    private String HOST = "localhost";
    static final int PORT = Server.PORT;
    Socket socket;
    ObjectInputStream inputStream;
    ObjectOutputStream outputStream;
    GameFrame gameFrame;


    public Client(String ipAddress) throws Exception {
        HOST = ipAddress;
        clientConnection();
        this.gameFrame = new GameFrame();
        start();
    }

    private void clientConnection() throws Exception {

        try {
            // create a client socket
            socket = new Socket(HOST, PORT);

            // get the input and output streams
            inputStream = new ObjectInputStream(socket.getInputStream());
            outputStream = new ObjectOutputStream(socket.getOutputStream());

        } catch (IOException e) {
            throw new RuntimeException(e);
        }

        sleep(1000);
    }

    public void start() {
        read();
        write();
    }

    private void write() {

        Thread thread = new Thread(new Runnable() {
            @Override
            public void run() {
                while (true) {

                    // write a Data object to the server
                    DataClient dataClient = new DataClient(gameFrame.panel.paddle1);
                    try {
                        outputStream.writeObject(dataClient);
                        System.out.println("Sent data to server: " + dataClient.yPaddle);
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

                    // read a Data object from the server
                    DataServer dataServer = null;
                    try {
                        dataServer = (DataServer) inputStream.readObject();
                        System.out.println("Received data from server: " + dataServer.yPaddle);
                        gameFrame.panel.score.player1 = dataServer.scorePlayer1;
                        gameFrame.panel.score.player2 = dataServer.scorePlayer2;
                        gameFrame.panel.paddle2.y = dataServer.yPaddle;
                        gameFrame.panel.ball1.x = dataServer.xBall;
                        gameFrame.panel.ball1.y = dataServer.yBall;
                        gameFrame.panel.ball1.xVelocity = dataServer.xVelocity;
                        gameFrame.panel.ball1.yVelocity = dataServer.yVelocity;
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


    public static void main(String[] args) throws Exception {

        Client client = new Client("localhost");
    }
}*/