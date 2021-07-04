package com.upec.androidtemplate20192020;

public class Timer extends Thread {

    private int timer = 0;


    @Override
    public void run() {
        while(!MainActivity.getStop()){
            try{
                timer ++;
                if(timer % 5 == 0){
                   GameContentView.difficulty++;
                }
                Thread.sleep(1000);
            }
            catch (InterruptedException e){
                e.printStackTrace();
            }
        }
    }

    public synchronized int getTimer(){
        return timer;
    }
}
