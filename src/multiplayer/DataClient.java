package pong.multiplayer;


import pong.Paddle;

public class DataClient {
    Integer yPaddle;

    public DataClient(Paddle paddle) {
        this.yPaddle = paddle.y;
    }
}
