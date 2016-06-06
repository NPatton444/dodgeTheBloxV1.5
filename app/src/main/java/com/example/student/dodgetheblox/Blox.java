package com.example.student.dodgetheblox;

/**
 * Created by student on 2016-05-26.
 */


public class Blox {
    int x, y, width, height, speed;

    public Blox(int _x, int _y, int _width, int _height, int _speed){
        x = _x;
        y = _y;
        width = _width;
        height = _height;
        speed = _speed;
    }

    public void Move(Blox b){
        b.y += b.speed;
    }

    public boolean Collision(Player p, Blox b){
        if(b.x > p.x && b.x < p.x + p.width && b.y + b.height > p.y && b.y + b.height < p.y + p.height) {
            return true;
        }
        else if(b.x + b.width > p.x && b.x + b.width < p.x +p.width && b.y > p.y && b.y < p.y + p.height){
            return true;
        }
        else{
            return false;
        }
    }
}
