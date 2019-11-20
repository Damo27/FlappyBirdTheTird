package com.damionj.flappybirdthetird;

import android.content.Context;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Typeface;
import android.view.MotionEvent;
import android.view.View;

import java.util.ArrayList;
import java.util.List;

public class GameView extends View
{
    Bitmap background, life_bm, ghost_bm;
    Bitmap[] bird = new Bitmap[2];
    iLevel level = new LevelOne();
    Paint score, levelPaint, bluePaint, blackPaint, goldPaint;
    int levelCount = 1;
    int levelScore;
    int setScore = 0;
    int birdSpeed;
    int birdY = 0;
    int birdX = 20;
    int dotX = 0;
    int dotXNeg = 0;
    int dotY = 400;
    int dotSpeed;
    int goldX;
    int goldY, goldY_dir;
    int ballSpeed;
    int maxBirdY;
    int minBirdY;
    int lifeCount = 3;
    int numBlackBalls;
    int canvasWidth;
    int canvasHeight;
    int life_x,life_x_dir;
    int life_y, life_y_dir;
    int ghost_x,ghost_x_dir;
    int ghost_y, ghost_y_dir;
    Bitmap[] life = new Bitmap[2];
    boolean touched = false;
    boolean lifeNotCaught = true, goldBallNotCaught = true, ghostNotCaught = true;
    List<BlackBall> blackBalls = new ArrayList<>();



    public GameView(Context context)
    {
        super(context);

        bird[0] = BitmapFactory.decodeResource(getResources(), R.drawable.wingsdowntrans);
        bird[1] = BitmapFactory.decodeResource(getResources(), R.drawable.wingsuptrans);
        life[0] = BitmapFactory.decodeResource(getResources(), R.drawable.smallalivetrans);
        life[1] = BitmapFactory.decodeResource(getResources(), R.drawable.smalldeadtrans);

        score = new Paint();
        score.setColor(Color.BLACK);
        score.setTypeface(Typeface.DEFAULT_BOLD);
        score.setTextSize(64);
        score.setAntiAlias(true);

        levelPaint = new Paint();
        levelPaint.setColor(Color.GRAY);
        levelPaint.setTypeface(Typeface.DEFAULT);
        levelPaint.setTextSize(64);
        levelPaint.setTextAlign(Paint.Align.CENTER);
        levelPaint.setAntiAlias(true);

        bluePaint = new Paint();
        bluePaint.setColor(Color.BLUE);
        blackPaint = new Paint();
        blackPaint.setColor(Color.BLACK);
        goldPaint = new Paint();
        goldPaint.setColor(Color.YELLOW);

        birdSpeed = level.getBirdSpeed();
        ballSpeed = level.getBallSpeed();
        dotSpeed = level.getDotSpeed();
        background = BitmapFactory.decodeResource(getResources(), level.getLevelBackground());
        levelScore = level.levelScore();
        numBlackBalls = level.getNumberOfBalls();
        level.isGhost();
        level.isGoldBall();
        blackBalls.add(new BlackBall(ballSpeed, 0, 0, minBirdY, maxBirdY, level.ballSize()));
    }

    @Override
    protected void onDraw(Canvas canvas)
    {
        super.onDraw(canvas);

        canvasWidth = canvas.getWidth();
        canvasHeight = canvas.getHeight();

        canvas.drawBitmap(background, 0, 0, null);
        canvas.drawText("Score : " + setScore, 20,60,score);
        canvas.drawText("Level : " + levelCount,canvasWidth/2,60,levelPaint);

        //region[Draw and move the Bird]
        minBirdY = bird[0].getHeight();
        maxBirdY = canvasHeight - bird[0].getHeight()*3;

        birdY += birdSpeed;
        if(birdY > maxBirdY) birdY = maxBirdY;
        if(birdY < minBirdY) birdY = minBirdY;
        birdSpeed += 2;

        if(touched)
        {
            canvas.drawBitmap(bird[0], birdX, birdY, null);
            touched = false;
        }
        else
            {
                canvas.drawBitmap(bird[1], birdX, birdY, null);
            }
            //endregion

        //region[Draw and move the Blue Dot]
        dotX = canvasWidth - dotXNeg;
        dotXNeg = dotXNeg + dotSpeed;

        if(dotX < 0)
        {
            refreshBlueDot();
        }

        canvas.drawCircle(dotX,dotY, 40, bluePaint);

        if(isHit(dotX, dotY))
        {
            addToScore(20);
            refreshBlueDot();
        }
        //endregion

        //region[Draw and move Black Dots]
        for (BlackBall ball:blackBalls)
        {
            drawBlackDot(ball, canvas);
        }
        //endregion

        //region[Draw and move the Extra Life]
        if(level.hasLife() && lifeNotCaught)
        {
            life_bm = BitmapFactory.decodeResource(getResources(),R.drawable.smallalivetrans);

            if(life_x >= canvasWidth - life_bm.getWidth())
            {
                life_x_dir = -15;
            }
            if(life_x <= 0)
            {
                life_x_dir = 15;
            }
            if(life_y >= canvasHeight - life_bm.getHeight())
            {
                life_y_dir = -15;
            }
            if(life_y <= 0)
            {
                life_y_dir = 15;
            }

            life_x += life_x_dir;
            life_y += life_y_dir;

            canvas.drawBitmap(life_bm, life_x, life_y,null);

            if(isHit(life_x, life_y))
            {
                lifeCount++;
                lifeNotCaught = false;
            }
        }
        //endregion

        //region[Draw and move the Ghost]
        if(level.isGhost() && ghostNotCaught)
        {
            ghost_bm = BitmapFactory.decodeResource(getResources(),R.drawable.smalltroubletrans);

            if(ghost_x >= canvasWidth - ghost_bm.getWidth())
            {
                ghost_x_dir = -15;
            }
            if(ghost_x <= 0)
            {
                ghost_x_dir = 15;
            }
            if(ghost_y >= canvasHeight - ghost_bm.getHeight())
            {
                ghost_y_dir = -15;
            }
            if(ghost_y <= 0)
            {
                ghost_y_dir = 15;
            }

            ghost_x += ghost_x_dir;
            ghost_y += ghost_y_dir;

            canvas.drawBitmap(ghost_bm, ghost_x, ghost_y,null);

            if(isHit(ghost_x, ghost_y))
            {
                lifeCount = 0;
                Intent intent = new Intent(getContext(), GameOver.class);
                intent.putExtra("Score", setScore);
                getContext().startActivity(intent);
                ghostNotCaught = false;
            }
        }
        //endregion

        //region[Draw and move the GoldBall]
        if(level.isGoldBall() && goldBallNotCaught)
        {

            if(goldX <= 0)
            {
                goldX = canvasWidth;
            }
            if(goldY >= canvasHeight)
            {
                goldY_dir = -60;
            }
            if(goldY <= 0)
            {
                goldY_dir = 60;
            }

            goldX -= 10;
            goldY += goldY_dir;

            canvas.drawCircle(goldX,goldY, 20, goldPaint);

            if(isHit(goldX, goldY))
            {
                addToScore(100);
                goldBallNotCaught = false;
            }
        }
        //endregion
    }

    //Method to move the bird if the screen is touched
    @Override
    public boolean onTouchEvent(MotionEvent event)
    {
        if(event.getAction() == MotionEvent.ACTION_DOWN)
        {
            birdSpeed = -20;
            touched = true;
        }
        return true;
    }

    //Method to determine if the bird touches an object on the canvas
    public boolean isHit(int x, int y)
    {
        if(birdX < x && x < (birdX + bird[0].getWidth())&& birdY < y && y < birdY + bird[0].getHeight())
        {
            return true;
        }
        return false;
    }

    //Method to refresh the blue dot
    public void refreshBlueDot()
    {
        dotY = (int)Math.floor(Math.random()*(maxBirdY - minBirdY)+minBirdY);
        dotX = 0;
        dotXNeg = 0;
    }

    //Method to draw the Black balls
    public void drawBlackDot(BlackBall ball, Canvas canvas)
    {

        if(ball.getX() < (0 - (ball.getSize()*2)))
        {
            ball.setX(0);
            ball.setxNeg(0);
            ball.setY(minBirdY, maxBirdY);
        }

        ball.setX(canvasWidth - ball.getxNeg());
        ball.setxNeg(ball.getxNeg() + ball.getSpeed());

        canvas.drawCircle(ball.getX(),ball.getY(), ball.getSize(), blackPaint);


        if(isHit(ball.getX(), ball.getY()))
        {
            lifeCount--;
            if(lifeCount <= 0)
            {
                Intent intent = new Intent(getContext(), GameOver.class);
                intent.putExtra("Score", setScore);
                getContext().startActivity(intent);
            }
            ball.setX(0);
            ball.setxNeg(0);
            ball.setY(minBirdY, maxBirdY);
        }

        switch(lifeCount)
        {
            case 0:
            {
                break;
            }
            case 1:
            {
                canvas.drawBitmap(life[0], canvasWidth -100,0,null);
                canvas.drawBitmap(life[1], canvasWidth- 200,0,null);
                canvas.drawBitmap(life[1], canvasWidth -300,0,null);
                break;
            }
            case 2:
            {
                canvas.drawBitmap(life[0], canvasWidth -100,0,null);
                canvas.drawBitmap(life[0], canvasWidth- 200,0,null);
                canvas.drawBitmap(life[1], canvasWidth -300,0,null);
                break;
            }
            case 3:
            {
                canvas.drawBitmap(life[0], canvasWidth -100,0,null);
                canvas.drawBitmap(life[0], canvasWidth- 200,0,null);
                canvas.drawBitmap(life[0], canvasWidth -300,0,null);
                break;
            }
            default:
            {
                canvas.drawBitmap(life[0], canvasWidth -100,0,null);
                canvas.drawBitmap(life[0], canvasWidth- 200,0,null);
                canvas.drawBitmap(life[0], canvasWidth -300,0,null);
                lifeCount = 3;
                break;
            }
        }
    }

    //Method adds to score and scales to next level
    public void addToScore(int scoreToAdd)
    {
        setScore += scoreToAdd;
        if(setScore >= levelScore)
        {
            levelCount++;

            switch(levelCount)
            {
                case 1:
                {
                    level = new LevelOne();
                    break;
                }
                case 2:
                {
                    level = new LevelTwo();
                    break;
                }
                case 3:
                {
                    level = new LevelThree();
                    break;
                }
                case 4:
                {
                    level = new LevelFour();
                    break;
                }
                case 5:
                {
                    level = new LevelFive();
                    break;
                }
                case 6:
                {
                    level = new LevelSix();
                    break;
                }
                case 7:
                {
                    level = new LevelSeven();
                    break;
                }
                default:
                {
                    level = new LevelFinal();
                    break;
                }
            }
            birdSpeed = level.getBirdSpeed();
            ballSpeed = level.getBallSpeed();
            dotSpeed = level.getDotSpeed();
            background = BitmapFactory.decodeResource(getResources(), level.getLevelBackground());
            levelScore = level.levelScore();
            numBlackBalls =level.getNumberOfBalls();
            level.isGhost();
            level.isGoldBall();
            blackBalls.add(new BlackBall(ballSpeed, 0, 0, minBirdY, maxBirdY, level.ballSize()));
            lifeNotCaught = true;
            goldBallNotCaught = true;
        }
    }
}
