package com.damionj.flappybirdthetird;

public class LevelThree implements iLevel
{


    @Override
    public int getLevelBackground() {
        return R.drawable.backgroundthree;
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
        return 15;
    }

    @Override
    public int getNumberOfBalls() {
        return 2;
    }

    @Override
    public int levelScore() {
        return 350;
    }

    @Override
    public int ballSize() {
        return 80;
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
