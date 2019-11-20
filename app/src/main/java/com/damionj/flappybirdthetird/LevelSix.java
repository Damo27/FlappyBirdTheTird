package com.damionj.flappybirdthetird;

public class LevelSix implements iLevel
{


    @Override
    public int getLevelBackground() {
        return R.drawable.treebackground;
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
        return 1000;
    }

    @Override
    public int ballSize() {
        return 90;
    }

    @Override
    public boolean isGoldBall() {
        return true;
    }

    @Override
    public boolean isGhost() {
        return true;
    }

    @Override
    public boolean hasLife() {
        return true;
    }
}
