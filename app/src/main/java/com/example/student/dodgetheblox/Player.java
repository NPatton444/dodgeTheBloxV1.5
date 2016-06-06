package com.example.student.dodgetheblox;

/**
 * Created by student on 2016-05-26.
 */
public class Player {
    float x, y, speed;
    int height, width;

    public Player(float _x, float _y, float _speed, int _width, int _height){
        x = _x;
        y = _y;
        speed = _speed;
        height = _height;
        width = _width;
    }

    public void Move(Player p){
        p.x -= p.speed;
    }
}
