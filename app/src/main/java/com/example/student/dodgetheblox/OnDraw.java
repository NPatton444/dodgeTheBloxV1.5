package com.example.student.dodgetheblox;

import android.content.Context;
import android.content.res.Resources;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.view.View;

/**
 * Created by student on 2016-06-03.
 */
public class OnDraw extends View {

    Bitmap player;
    int x, y;
    Paint paint;

    public OnDraw(Context context){
        super(context);
        player = BitmapFactory.decodeResource(getResources(), R.drawable.playership);
        x = 0;
        y = 0;
    }

    @Override
    protected void onDraw(Canvas c) {
        super.onDraw(c);

        paint.setStyle(Paint.Style.FILL);

        c.drawBitmap(player, x, y, new Paint());

        invalidate();
    }
}
