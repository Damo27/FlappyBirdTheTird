package com.damionj.flappybirdthetird;


public class LevelTwo implements iLevel
{


    @Override
    public int getLevelBackground() {
        return R.drawable.backgroundtwo;
    }

    @Override
    public int getBirdSpeed() {
        return 10;
    }

    @Override
    public int getDotSpeed() {
        return 15;
    }

    @Override
    public int getBallSpeed() {
        return 30;
    }

    @Override
    public int getNumberOfBalls() {
        return 2;
    }

    @Override
    public int levelScore() {
        return 200;
    }

    @Override
    public int ballSize() {
        return 40;
    }

    @Override
    public boolean isGoldBall() {
        return false;
    }

    @Override
    public boolean isGhost() {
        return false;
    }

    @Override
    public boolean hasLife() {
        return false;
    }
}
