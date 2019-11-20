package com.damionj.flappybirdthetird;

public class LevelSeven implements iLevel
{


    @Override
    public int getLevelBackground() {
        return R.drawable.backgroundseven;
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
        return 20;
    }

    @Override
    public int getNumberOfBalls() {
        return 2;
    }

    @Override
    public int levelScore() {
        return 1500;
    }

    @Override
    public int ballSize() {
        return 50;
    }

    @Override
    public boolean isGoldBall() {
        return true;
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
