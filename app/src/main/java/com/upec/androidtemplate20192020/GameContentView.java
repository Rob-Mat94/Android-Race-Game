package com.upec.androidtemplate20192020;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.hardware.Sensor;
import android.hardware.SensorEvent;
import android.hardware.SensorEventListener;
import android.util.AttributeSet;
import android.view.View;


/**************************************/

public class GameContentView extends View implements SensorEventListener {

    private static int width;
    private static int height;
    private Car car;
    private Obstacle obstacle;
    private Paint paint = new Paint(Paint.ANTI_ALIAS_FLAG);
    private Timer timer;

    private Activity main;

    public static int ObstacleRadius; /////

    public static int difficulty = 1;

    public GameContentView(Context context, AttributeSet as){
        super(context,as);
    }

    public GameContentView(Context context){
        super(context);
    }

    @Override
    public void onSensorChanged(SensorEvent sensorEvent) {
        float y = sensorEvent.values[1];
        car.MoveCar(y*6);
        LaunchScoreBoard();
        this.invalidate();
    }

    private void LaunchScoreBoard(){
        if(MainActivity.getStop()){
            Intent intent = new Intent(main,Main2Activity.class);
            intent.putExtra("SCORE",timer.getTimer());
            difficulty = 1;
            main.startActivity(intent);
        }
    }
    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);

        this.DrawCar(canvas,paint);
        this.DrawScore(canvas,paint);
        this.DrawObstacle(canvas,paint);
    }

    public void DrawScore(Canvas canvas, Paint paint){
        paint.setTextSize(width/40);
        canvas.drawText("Score : "+ Integer.toString(timer.getTimer()),width/18,height/10,paint);
    }

    public void DrawObstacle(Canvas canvas,Paint paint){
        for(Coordinate coord : obstacle.getObstacles()){
            paint.setColor(Color.RED);
            canvas.drawCircle(coord.getX(),coord.getY(),ObstacleRadius,paint);
        }
    }

    public void DrawCar(Canvas canvas, Paint paint){
        /* dessin voiture */
        paint.setColor(Color.BLACK);
        canvas.drawRect(car.getCarView(),paint);
    }

    public void setStart(int width, int height){
        this.width = width;
        this.height = height;
        ObstacleRadius = width/60;
        car = new Car(15*this.width/31,4*this.height/6,16*this.width/31 ,5*this.height/6,this.width,this.height);
        obstacle = new Obstacle(width,height, car);
        /* Création des obstacles */
        new Thread(obstacle).start();
        /*Timer*/
        timer = new Timer();
        timer.start();
        /* Mouvement des obstacles */
        obstacle.StartMove();

    }


    public void setMainActivity(Activity activity){
        this.main = activity;
    }

    @Override
    public void onAccuracyChanged(Sensor sensor, int i) {

    }


}
