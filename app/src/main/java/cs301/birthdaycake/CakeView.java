package cs301.birthdaycake;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.RectF;
import android.util.AttributeSet;
import android.util.Log;
import android.view.MotionEvent;
import android.view.SurfaceView;
import android.view.View;

public class CakeView extends SurfaceView {

    /* These are the paints we'll use to draw the birthday cake below */
    Paint cakePaint = new Paint();
    Paint frostingPaint = new Paint();
    Paint candlePaint = new Paint();
    Paint outerFlamePaint = new Paint();
    Paint innerFlamePaint = new Paint();
    Paint wickPaint = new Paint();
    Paint textPaint = new Paint();

    //balloon
    //float x;
    //float y;
    Paint baloonPaint = new Paint();
    Paint stickPaint = new Paint();

    /* These constants define the dimensions of the cake.  While defining constants for things
        like this is good practice, we could be calculating these better by detecting
        and adapting to different tablets' screen sizes and resolutions.  I've deliberately
        stuck with hard-coded values here to ease the introduction for CS371 students.
     */
    public static final float cakeTop = 400.0f;
    public static final float cakeLeft = 100.0f;
    public static final float cakeWidth = 1200.0f;
    public static final float layerHeight = 200.0f;
    public static final float frostHeight = 50.0f;
    public static final float candleHeight = 300.0f;
    public static final float candleWidth = 80.0f;
    public static final float wickHeight = 30.0f;
    public static final float wickWidth = 6.0f;
    public static final float outerFlameRadius = 30.0f;
    public static final float innerFlameRadius = 15.0f;

    private CakeModel cakeViewModel;

    Paint green = new Paint();
    Paint red = new Paint();

    /**
     * ctor must be overridden here as per standard Java inheritance practice.  We need it
     * anyway to initialize the member variables
     */
    public CakeView(Context context, AttributeSet attrs) {
        super(context, attrs);

        cakeViewModel = new CakeModel();

        //This is essential or your onDraw method won't get called
        setWillNotDraw(false);

        //Setup our palette
        cakePaint.setColor(Color.BLUE);  //violet-red
        cakePaint.setStyle(Paint.Style.FILL);
        frostingPaint.setColor(0xFFFFFACD);  //pale yellow
        frostingPaint.setStyle(Paint.Style.FILL);
        candlePaint.setColor(0xFF32CD32);  //lime green
        candlePaint.setStyle(Paint.Style.FILL);
        outerFlamePaint.setColor(0xFFFFD700);  //gold yellow
        outerFlamePaint.setStyle(Paint.Style.FILL);
        innerFlamePaint.setColor(0xFFFFA500);  //orange
        innerFlamePaint.setStyle(Paint.Style.FILL);
        wickPaint.setColor(Color.BLACK);
        wickPaint.setStyle(Paint.Style.FILL);

        textPaint.setColor(Color.RED);
        textPaint.setTextSize(78);

        // Balloon
        baloonPaint.setColor(Color.BLUE);
        stickPaint.setColor(Color.GRAY);
        green.setColor(Color.GREEN);
        red.setColor(Color.RED);


        //checkered board


        setBackgroundColor(Color.WHITE);  //better than black default
    }

    /**
     * draws a candle at a specified position.  Important:  the left, bottom coordinates specify
     * the position of the bottom left corner of the candle
     */
    public void drawCandle(Canvas canvas, float left, float bottom) {

        if(cakeViewModel.hasCandles) {
            canvas.drawRect(left, bottom - candleHeight, left + candleWidth, bottom, candlePaint);
            if (cakeViewModel.candlesLit) {
                //draw the outer flame
                float flameCenterX = left + candleWidth / 2;
                float flameCenterY = bottom - wickHeight - candleHeight - outerFlameRadius / 3;
                canvas.drawCircle(flameCenterX, flameCenterY, outerFlameRadius, outerFlamePaint);

                //draw the inner flame
                flameCenterY += outerFlameRadius / 3;
                canvas.drawCircle(flameCenterX, flameCenterY, innerFlameRadius, innerFlamePaint);
            }

            //draw the wick
            float wickLeft = left + candleWidth / 2 - wickWidth / 2;
            float wickTop = bottom - wickHeight - candleHeight;
            canvas.drawRect(wickLeft, wickTop, wickLeft + wickWidth, wickTop + wickHeight, wickPaint);
        }
    }

    /**
     * onDraw is like "paint" in a regular Java program.  While a Canvas is
     * conceptually similar to a Graphics in javax.swing, the implementation has
     * many subtle differences.  Show care and read the documentation.
     *
     * This method will draw a birthday cake
     */
    @Override
    public void onDraw(Canvas canvas)
    {
        //top and bottom are used to keep a running tally as we progress down the cake layers
        float top = cakeTop;
        float bottom = cakeTop + frostHeight;
        if (cakeViewModel.hasFrosting) {
            //Frosting on top
            canvas.drawRect(cakeLeft, top, cakeLeft + cakeWidth, bottom, frostingPaint);
            top += frostHeight;
            bottom += layerHeight;

            //Then a cake layer
            canvas.drawRect(cakeLeft, top, cakeLeft + cakeWidth, bottom, cakePaint);
            top += layerHeight;
            bottom += frostHeight;

            //Then a second frosting layer
            canvas.drawRect(cakeLeft, top, cakeLeft + cakeWidth, bottom, frostingPaint);
            top += frostHeight;
            bottom += layerHeight;

            //Then a second cake layer
            canvas.drawRect(cakeLeft, top, cakeLeft + cakeWidth, bottom, cakePaint);
        }
        else{
            //Frosting on top
            canvas.drawRect(cakeLeft, top, cakeLeft + cakeWidth, bottom, cakePaint);
            top += frostHeight;
            bottom += layerHeight;

            //Then a cake layer
            canvas.drawRect(cakeLeft, top, cakeLeft + cakeWidth, bottom, cakePaint);
            top += layerHeight;
            bottom += frostHeight;

            //Then a second frosting layer
            canvas.drawRect(cakeLeft, top, cakeLeft + cakeWidth, bottom, cakePaint);
            top += frostHeight;
            bottom += layerHeight;
            //Then a second cake layer
            canvas.drawRect(cakeLeft, top, cakeLeft + cakeWidth, bottom, cakePaint);
        }


        for (int i = 1; i <= cakeViewModel.numCandles; i++){
            drawCandle(canvas, cakeLeft + i*cakeWidth/(cakeViewModel.numCandles + 1) - candleWidth/2, cakeTop);
        }

        //Draw balloon
        RectF stick = new RectF(cakeViewModel.x-10,cakeViewModel.y,cakeViewModel.x+10,cakeViewModel.y+300);
        RectF oval = new RectF(cakeViewModel.x-50,cakeViewModel.y-100,cakeViewModel.x+50,cakeViewModel.y+100);
        if(!(cakeViewModel.x == -1 || cakeViewModel.y == -1))
        {
            canvas.drawRect(stick,stickPaint);
            canvas.drawOval(oval,baloonPaint);
            canvas.drawText(cakeViewModel.x + ", " + cakeViewModel.y, 1476.f, 709.f, textPaint);

        }





        if(cakeViewModel.x != -1 && cakeViewModel.y != -1) {

            //bottom left
            canvas.drawRect(cakeViewModel.x - 100, cakeViewModel.y + 100,
                    cakeViewModel.x , cakeViewModel.y, red);
            //bottom right
            canvas.drawRect(cakeViewModel.x, cakeViewModel.y + 100,
                    cakeViewModel.x + 100, cakeViewModel.y, green);
            //top left
            canvas.drawRect(cakeViewModel.x - 100, cakeViewModel.y,
                    cakeViewModel.x, cakeViewModel.y - 100, green);
            //top right
            canvas.drawRect(cakeViewModel.x + 100, cakeViewModel.y,
                    cakeViewModel.x , cakeViewModel.y - 100, red);
        }
    }//onDraw

    public CakeModel getCakeModel(){
        return cakeViewModel;
    }

}//class CakeView

