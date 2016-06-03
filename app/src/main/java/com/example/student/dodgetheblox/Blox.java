package com.example.student.dodgetheblox;

/**
 * Created by student on 2016-05-26.
 */


public class Blox {
    int x, y, speed, size;

    public Blox(int _x, int _y, int _speed, int _size){
        x = _x;
        y = _y;
        speed = _speed;
        size = _size;
    }

    public void Move(int y, int speed){
        y += speed;
    }

    public boolean Collision(Player p, Blox b){
        if(b.x > p.x && b.x < p.x + p.width && b.y + b.size > p.y && b.y + b.size < p.y + p.height) {
            return true;
        }
        else if(b.x + b.size > p.x && b.x + b.size < p.x +p.width && b.y > p.y && b.y < p.y + p.height){
            return true;
        }
        else{
            return false;
        }
    }
}
