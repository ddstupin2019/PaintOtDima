package com.example.newpaint;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.app.Activity;
import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.os.Bundle;
import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.graphics.Path;
import android.util.Log;
import android.view.MotionEvent;
import android.view.SurfaceHolder;
import android.view.View;
import android.widget.Toast;

import java.util.ArrayList;

public class MainActivity extends Activity {
    Float x, y, r=30f;
    int z=4;
    Paint paint;
    Boolean f=false, l=false;
    ArrayList<Float> xb = new ArrayList<>();
    ArrayList<Float> yb = new ArrayList<>();
    ArrayList<Integer> zb = new ArrayList<>();
    ArrayList<Float> rb = new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(new DrawView(this));
    }

    class DrawView extends View {

        public DrawView(Context context) {
            super(context);
        }

        @Override
        protected void onDraw(Canvas canvas) {
            paint = new Paint();
            paint.setColor(Color.YELLOW);
            canvas.drawCircle(100, 100, 90, paint);
            paint.setColor(Color.RED);
            canvas.drawCircle(300, 100, 90, paint);
            paint.setColor(Color.BLUE);
            canvas.drawCircle(500, 100, 90, paint);
            paint.setColor(Color.BLACK);
            paint.setStrokeWidth(60);
            canvas.drawLine(700, 40, 700,170, paint);
            paint.setStrokeWidth(120);
            canvas.drawLine(900, 40, 900,170, paint);
            paint.setStrokeWidth(180);
            canvas.drawLine(1100, 40, 1100,170, paint);
            canvas.drawLine(1300, 40, 1300,140, paint);
            paint.setColor(Color.BLUE);
            canvas.drawLine(1300, 140, 1300,170, paint);
            String g = z+"";
            Log.d("f",g);
            for (int i =0;i<xb.size();i++){
                switch (zb.get(i)) {
                    case 0:
                        paint.setColor(Color.YELLOW);
                        canvas.drawCircle(xb.get(i), yb.get(i), rb.get(i), paint);
                        break;
                    case 1:
                        paint.setColor(Color.RED);
                        Log.d("f", "red");
                        canvas.drawCircle(xb.get(i), yb.get(i), rb.get(i), paint);
                        break;
                    case 2:
                        paint.setColor(Color.BLUE);
                        canvas.drawCircle(xb.get(i), yb.get(i), rb.get(i), paint);
                        break;
                }
            }
            if (l==false) {
                switch (z) {
                    case 0:
                        paint.setColor(Color.YELLOW);
                        canvas.drawCircle(x, y, r, paint);
                        break;
                    case 1:
                        paint.setColor(Color.RED);
                        Log.d("f", "red");
                        canvas.drawCircle(x, y, r, paint);
                        break;
                    case 2:
                        paint.setColor(Color.BLUE);
                        canvas.drawCircle(x, y, r, paint);
                        break;
                    case 4:
                        break;
                }

            }
        }

        @Override
        public boolean onTouchEvent(MotionEvent event) {
            x = event.getX();
            y = event.getY();
            String h = x+" "+y;
            Log.d("sd", h);
            if (f==false){
                f=true;
                z=0;
            }
            switch (event.getAction()) {
                case MotionEvent.ACTION_DOWN: // нажатие
                    if ((x-100)*(x-100)+(y-100)*(y-100)<=8100) {
                        z=0;
                        Log.d("f","yellow");
                        break;
                    }
                    else if ((x-300)*(x-300)+(y-100)*(y-100)<=8100){
                        z=1;
                        l=false;
                        String g = z+"";
                        Log.d("f",g);
                        break;
                }
                    else if ((x-500)*(x-500)+(y-100)*(y-100)<=8100){
                        z=2;
                        l=false;
                        Log.d("f","blue");
                        break;
                    }
                    else if (x<730 && x>670 && y<170 && y>40){
                        Log.d("sd", "fdf");
                        r=30f;
                        l=false;
                        break;
                    }
                    else if (x<960 && x>840 && y<170 && y>40){
                        Log.d("sd", "fdf");
                        r=60f;
                        l=false;
                        break;
                    }
                    else if (x<1190 && x>1010 && y<170 && y>40){
                        Log.d("sd", "fdf");
                        r=90f;
                        l=false;
                        break;
                    }
                    else if (x<1390 && x>1210 && y<170 && y>40) {
                        Log.d("sd", "fdfl");
                        l=true;
                        break;
                    }
                    else {
                        if (l==false) {
                            xb.add(x);
                            yb.add(y);
                            zb.add(z);
                            rb.add(r);
                        }
                        else{
                            for (int i =0;i<xb.size();i++){
                                if ((x-xb.get(i))*(x-xb.get(i))+(y-yb.get(i))*
                                        (y-yb.get(i))<= rb.get(i)*rb.get(i)){
                                    xb.remove(i);
                                    yb.remove(i);
                                    rb.remove(i);
                                    zb.add(z);
                                    break;
                                }
                            }
                        }
                    invalidate();
                        break;
                    }
                case MotionEvent.ACTION_MOVE: // движение
                    if (l==false) {
                        xb.add(x);
                        yb.add(y);
                        zb.add(z);
                        rb.add(r);
                    }
                    else{
                        for (int i =0;i<xb.size();i++){
                            if ((x-xb.get(i))*(x-xb.get(i))+(y-yb.get(i))*
                                    (y-yb.get(i))<= rb.get(i)*rb.get(i)){
                                xb.remove(i);
                                yb.remove(i);
                                rb.remove(i);
                                break;
                            }
                        }
                    }
                    invalidate();
                case MotionEvent.ACTION_UP: // отпускание
                case MotionEvent.ACTION_CANCEL:
                    break;
            }
            return true;
        }

    }
}
