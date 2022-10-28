package com.example.udprcu;

import static com.example.udprcu.KeyEnum.*;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Color;
import android.graphics.PorterDuff;
import android.util.AttributeSet;
import android.util.Log;
import android.view.MotionEvent;
import android.view.View;
import android.widget.ImageButton;
public class RcuButton extends ImageButton implements View.OnClickListener, View.OnTouchListener {
    private int m_rcuKey;
    Data data;
    MainActivity mainActivity = new MainActivity();
    public RcuButton(Context context) {
        super(context);
    }

    public RcuButton(Context context, AttributeSet attrs) {
        super(context, attrs);
    }

    public RcuButton(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
    }

    public RcuButton(Context context, AttributeSet attrs, int defStyleAttr, int defStyleRes) {
        super(context, attrs, defStyleAttr, defStyleRes);
    }

    @Override
    public void onClick(View view) {
        Log.d("buttonClick", "clicked: " + m_rcuKey);
        Data data1 = new Data("ITK-NGXXXX000001",0,m_rcuKey,1,1,0,0,0,"DasErste");
    }

    public void setOnClickListener(int rcuKey) {
        super.setOnClickListener(this);
        this.m_rcuKey = rcuKey;
    }
    public void setOnTouchListener(int rcuKey) {
        super.setOnTouchListener(this);
        this.m_rcuKey = rcuKey;
    }

    private void clearColorFilter(View view){
        view.getBackground().clearColorFilter();
        view.invalidate();

    }
    private void setColorFilter(View view){
        view.getBackground().setColorFilter(0xff86838a, PorterDuff.Mode.SRC_ATOP);
        view.invalidate();
    }

    @Override
    public boolean onTouch(View view, MotionEvent motionEvent) {

        switch (motionEvent.getAction())
        {
            case MotionEvent.ACTION_DOWN:
                Data keyData = null;
                if(m_rcuKey != REM_KEY_DIRECT.getValue()) {
                    keyData = new Data("ITK-NGXXXX000001", 0, m_rcuKey, 1, 1, 0, 0, 0, "DasErste");
                }else{
                    Bitmap bitmap;
                    bitmap = BitmapFactory.decodeResource(getResources(), R.drawable.ok5);
                    float iX=motionEvent.getX();
                    float iY=motionEvent.getY();
                    Log.d("iX", ""+ iX);
                    Log.d("iY", ""+ iY);
                    keyData = new Data("",0,0,0,0,0,0,0,"");
                    if (iX >= 180 & iY <= 130 & iX <= 320 & iY > 10) {
                        Log.d("action", "UP");
                        keyData = new Data("ITK-NGXXXX000001",0, REM_KEY_UP.getValue(),1,1,0,0,0,"DasErste");
                    }
                    if (iX >= 360 & iY <= 360 & iX <= 500 & iY >= 140) {
                        Log.d("action", "RIGHT");
                        keyData = new Data("ITK-NGXXXX000001",0, REM_KEY_RIGHT.getValue(),1,1,0,0,0,"DasErste");
                    }
                    if (iX >= 150 & iY <= 460 & iX <= 350 & iY >= 340) {
                        Log.d("action", "DOWN");
                        keyData = new Data("ITK-NGXXXX000001",0, REM_KEY_DOWN.getValue(),1,1,0,0,0,"DasErste");
                    }
                    if (iX >= 5 & iY <= 280 & iX <= 130 & iY >= 140) {
                        Log.d("action", "LEFT");
                        keyData = new Data("ITK-NGXXXX000001",0, REM_KEY_LEFT.getValue(),1,1,0,0,0,"DasErste");
                    }
                    if (iX >= 180 & iY <= 289 & iX <= 280 & iY >= 190) {
                        Log.d("action", "OK");
                        keyData = new Data("ITK-NGXXXX000001",0, REM_KEY_OK.getValue(),1,1,0,0,0,"DasErste");
                    }
                }

                byte [] dataArray = data.validateData(keyData);
                mainActivity.sendText(dataArray);
                setColorFilter(view);
                break;
            case MotionEvent.ACTION_CANCEL:
            case MotionEvent.ACTION_UP:
                clearColorFilter(view);
                break;
        }
        return false;
    }

}
