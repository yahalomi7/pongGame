package pong.multiplayer;

import pong.Ball;
import pong.Paddle;
import pong.Score;

import java.io.Serializable;

public class DataServer implements Serializable {

    private static final long serialVersionUID = 1L;

    Integer xBall;
    Integer yBall;
    Byte xVelocity;
    Byte yVelocity;
    Integer yPaddle;
    Byte scorePlayer1;
    Byte scorePlayer2;


    public DataServer(Ball ball, Paddle paddle, Score score) {
        this.xBall = ball.x;
        this.yBall = ball.y;
        this.xVelocity = (byte) ball.xVelocity;
        this.yVelocity = (byte) ball.yVelocity;
        this.yPaddle = paddle.y;
        this.scorePlayer1 = (byte) score.player1;
        this.scorePlayer2 = (byte) score.player2;
    }
}