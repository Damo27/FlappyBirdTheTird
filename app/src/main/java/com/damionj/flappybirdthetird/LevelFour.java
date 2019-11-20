package com.damionj.flappybirdthetird;

public class LevelFour implements iLevel
{


    @Override
    public int getLevelBackground() {
        return R.drawable.backgroundfour;
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
        return 35;
    }

    @Override
    public int getNumberOfBalls() {
        return 2;
    }

    @Override
    public int levelScore() {
        return 600;
    }

    @Override
    public int ballSize() {
        return 35;
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
        return true;
    }
}
