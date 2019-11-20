package com.damionj.flappybirdthetird;

//Black ball class allows for multiple objects of Black Balls to be created, the object will then
// track its own size, speed and coordinates.
public class BlackBall
{
    public BlackBall(int speed, int x, int xNeg, int min, int max, int size)
    {
        this.speed = speed;
        this.x = x;
        this.xNeg = xNeg;
        setY(min, max);
        this.size = size;
    }

    private int speed;
    private int size;
    private int x, y, xNeg;

    public int getSize() {
        return size;
    }

    public void setSize(int size) {
        this.size = size;
    }

    public int getSpeed() {
        return speed;
    }

    public void setSpeed(int speed) {
        this.speed = speed;
    }

    public int getX() {
        return x;
    }

    public void setX(int x) {
        this.x = x;
    }

    public int getY() {
        return y;
    }

    public void setY(int min, int max) {
        this.y = (int)Math.floor(Math.random()*(max - min)+min);
    }

    public int getxNeg() {
        return xNeg;
    }

    public void setxNeg(int xNeg) {
        this.xNeg = xNeg;
    }

}
