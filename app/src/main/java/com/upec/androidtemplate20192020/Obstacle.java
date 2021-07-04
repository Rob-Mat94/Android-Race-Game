package com.upec.androidtemplate20192020;

import android.app.Activity;
import android.content.Intent;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class Obstacle implements Runnable{

    private List<Coordinate> obstacles = new ArrayList<>();
    private  int width;
    private int height;
    private int compteur = 20;
    private Car car;



    public Obstacle(int width, int height,Car car){
        this.width = width;
        this.height = height;
        this.car = car;
    }

    @Override
    public void run() {

        while(!MainActivity.getStop()){
                AddObstacle();
            try
            {
                int time = 8000 - GameContentView.difficulty*1000;
                if(time <= 0)
                    time = 1000;
                Thread.sleep(time);
                compteur++;
                obstacles = new ArrayList<>();
            }catch (InterruptedException e){
                e.printStackTrace();
            }
        }
    }

    public synchronized List<Coordinate> getObstacles(){
        return this.obstacles;
    }

    private synchronized void AddObstacle(){
        Random r = new Random();
        for(int i = 0; i < 10 + GameContentView.difficulty; i++){
            obstacles.add(new Coordinate(r.nextInt(width), r.nextInt(height/6)));
        }
    }

    public synchronized void StartMove(){
        new Thread(new Runnable() {
            @Override
            public void run() {
                while(!MainActivity.getStop()){
                    for(Coordinate coord : obstacles){
                        coord.IncY();
                        /* vérification */
                        if(car.getCarView().contains(coord.getX(),coord.getY()) || car.getCarView().contains(coord.getX(),coord.getY()+GameContentView.ObstacleRadius) ||
                                car.getCarView().contains(coord.getX() + GameContentView.ObstacleRadius,coord.getY()) || car.getCarView().contains(coord.getX() - GameContentView.ObstacleRadius,coord.getY())){
                            MainActivity.setStop();
                        }
                    }
                    try{
                        Thread.sleep(10);
                    }catch (InterruptedException e){
                        e.printStackTrace();
                    }
                }

            }
        }).start();
    }
}
