package com.damionj.flappybirdthetird;


//Interface contains methods for retrieving level specific fields
public interface iLevel
{
    int getLevelBackground();
    int getBirdSpeed();
    int getDotSpeed();
    int getBallSpeed();
    int getNumberOfBalls();
    int levelScore();
    int ballSize();
    boolean isGoldBall();
    boolean isGhost();
    boolean hasLife();
}
