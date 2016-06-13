package com.example.student.dodgetheblox;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.view.View;

/**
 * Created by student on 2016-06-03.
 */
public class OnDraw extends View {

    //Player bitmaps
    Bitmap player;
    Bitmap blox;

    public OnDraw(Context context){
        super(context);

        player = BitmapFactory.decodeResource(getResources(), R.drawable.playership);
        blox = BitmapFactory.decodeResource(getResources(), R.drawable.asteroidimage);
    }

    @Override
    public void onDraw(Canvas canvas) {
        super.onDraw(canvas);

        //Draw Blocks
        for(Blox b : GameActivity.bloxList) {
            canvas.drawBitmap(blox, b.x, b.y, new Paint());
        }

        //Draw Player
        canvas.drawBitmap(player, GameActivity.p.x, GameActivity.p.y, new Paint());

        //Set Score text to screen
        Paint writePaint = new Paint();
        writePaint.setColor(Color.WHITE);
        writePaint.setStyle(Paint.Style.FILL);
        writePaint.setTextSize(100);

        canvas.drawRect(0, 0, canvas.getWidth(), 250, new Paint());

        canvas.drawText("Score: " + String.valueOf(GameActivity.score), 50, 150, writePaint);

        invalidate();
    }
}

