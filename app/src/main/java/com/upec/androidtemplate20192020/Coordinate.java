package com.upec.androidtemplate20192020;

public class Coordinate {
    private int x;
    private int y;

    public Coordinate(int x, int y){
        this.x = x;
        this.y = y;
    }

    public synchronized int getX() {
        return x;
    }

    public synchronized int getY() {
        return y;
    }

    public synchronized void setX(int x){this.x+=x;}
    public synchronized void setY(int y){this.y+=y;}

    public synchronized void IncY(){
           this.y = this.y + GameContentView.difficulty;

    }

    // BOUGER EN FONCTION TAILLE ECRAN //
}
