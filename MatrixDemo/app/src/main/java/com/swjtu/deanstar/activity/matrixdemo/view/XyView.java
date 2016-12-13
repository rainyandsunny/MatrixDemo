package com.swjtu.deanstar.activity.matrixdemo.view;

import android.content.Context;
import android.content.res.Resources;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.Matrix;
import android.graphics.Paint;
import android.util.AttributeSet;
import android.view.View;

import com.swjtu.deanstar.activity.matrixdemo.R;

/**
 * Created by yhp5210 on 2016/12/13.
 */

public class XyView extends View {

    private static final int WIDTH = 200;
    private static final int HEIGHT = 200;
    private Paint mPaint;
    private int mWidth,mHeight;
    private Matrix mMatrix;
    private Bitmap mBitmap;
    private Context mContext;

    public XyView(Context context) {
        super(context);
        mContext = context;
        init();
    }
    public XyView(Context context, AttributeSet attrs) {
        super(context, attrs);
        mContext = context;
        init();
    }
    public XyView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
    }

    public void init(){
        mPaint = new Paint();
        Resources res = mContext.getResources();
        mBitmap = BitmapFactory.decodeResource(res, R.drawable.bg,null);

    }
    @Override
    protected void onDraw(Canvas canvas) {

        super.onDraw(canvas);
        mWidth = getWidth();
        mHeight = getHeight();
        canvas.translate(mWidth/2,mHeight/2);
        mPaint.setStrokeWidth(1);
        // 绘制X轴
        canvas.drawLine(-mWidth/2*0.8f,0,mWidth/2*0.8f,0,mPaint);
        // 绘制Y轴
        canvas.drawLine(0,-mHeight/2*0.8f,0,mHeight/2*0.8f,mPaint);
        mPaint.setStrokeWidth(3);
        canvas.drawLines(new float[]{
                mWidth/2*0.8f,0,mWidth/2*0.8f*0.95f,-mWidth/2*0.8f*0.05f,
                mWidth/2*0.8f,0,mWidth/2*0.8f*0.95f,mWidth/2*0.8f*0.05f
        },mPaint);
        // 绘制Y轴箭头
        canvas.drawLines(new float[]{
                0,mHeight/2*0.8f,mWidth/2*0.8f*0.05f,mHeight/2*0.8f-mWidth/2*0.8f*0.05f,
                0,mHeight/2*0.8f,-mWidth/2*0.8f*0.05f,mHeight/2*0.8f-mWidth/2*0.8f*0.05f,
        },mPaint);
        mMatrix = new Matrix();
       // mMatrix.setValues(new float[]{1, 0, 0, 0, 1, 0, 0, 0,1f});
        //mMatrix.setSkew(0.0f,0.5f);
        //mMatrix.setScale(0.5f,0.5f);
        //mMatrix.setRotate(90);
       // mMatrix.setTranslate(-200,0);
        canvas.drawBitmap(mBitmap,mMatrix,null);

    }

    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {

        setMeasuredDimension(getSize(widthMeasureSpec,WIDTH),getSize(heightMeasureSpec,HEIGHT));
    }

    private int getSize(int sizeMeasureSpec,int minSize){

        int mode = MeasureSpec.getMode(sizeMeasureSpec);
        int result = MeasureSpec.getSize(sizeMeasureSpec);
        switch(mode){

            case MeasureSpec.EXACTLY:{

                return result;
            }
            case MeasureSpec.AT_MOST:{

                result = Math.min(result,minSize);
            }break;
            default:{
                result = minSize;
            }

        }
        return result;

    }
}
