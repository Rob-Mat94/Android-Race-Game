package com.upec.androidtemplate20192020;


import android.graphics.Rect;

public class Car {

    private float x;
    private float y;
    private float x2;
    private float y2;
    private int width;
    private int height;


    public Car(float x, float y, float x2, float y2, int width, int height) {
        this.x = x;
        this.y = y;
        this.x2 = x2;
        this.y2 = y2;
        this.width = width;
        this.height = height;
    }

    public float getX() {
        return x;
    }

    public float getY() {
        return y;
    }

    public float getX2() {
        return x2;
    }

    public float getY2() {
        return y2;
    }

    public void setX(float x) {
        this.x = x;
    }

    public void setY(float y) {
        this.y = y;
    }

    public void setX2(float x2) {
        this.x2 = x2;
    }

    public void setY2(float y2) {
        this.y2 = y2;
    }

    public void MoveCar(float x) {
        if (this.x < 0) {
            this.x = 0;
            this.x2 = this.width / 31;

        } else if (this.x2 > width) {
            this.x2 = width;
            this.x = 30 * x2 / 31;

        } else {
            setX(getX() + x);
            setX2(getX2() + x);
        }
    }

    public Rect getCarView(){
        return new Rect((int)this.x,(int)this.y,(int)this.x2,(int)this.y2);
    }
}