package com.damionj.flappybirdthetird;

public class LevelOne  implements iLevel
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
        return 25;
    }

    @Override
    public int getNumberOfBalls() {
        return 1;
    }

    @Override
    public int levelScore() {
        return 100;
    }

    @Override
    public int ballSize() {
        return 60;
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
