package com.example.student.dodgetheblox;

/**
 * Created by student on 2016-05-26.
 */
public class Player {
    int x, y, speed, height, width, direction;

    public Player(int _x, int _y, int _speed, int _direction, int _width, int _height){
        x = _x;
        y = _y;
        speed = _speed;
        direction = _direction;
        height = _height;
        width = _width;
    }

    public void Move(int x, int direction, int speed){
        if(direction == 1){
            x -= speed;
        }

        if(direction == 2){
            x += speed;
        }
    }
}
