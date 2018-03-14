package com.example.a18679.canvas;

import android.content.Context;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.LinearGradient;
import android.graphics.Paint;
import android.graphics.Path;
import android.graphics.PorterDuff;
import android.graphics.PorterDuffXfermode;
import android.graphics.RectF;
import android.graphics.Shader;
import android.media.MediaPlayer;
import android.net.Uri;
import android.util.AttributeSet;
import android.view.MotionEvent;
import android.view.View;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.Random;

public class canvas extends View {
    MediaPlayer mediaPlayer;
    int kflag=0;
    Path path=new Path();
    Paint paint=new Paint();
    Bitmap bitmap=null;
    Canvas canv=null;
    float maxx;
    float maxy;
    int colors[]={Color.BLUE,Color.GREEN,Color.RED,Color.WHITE,Color.BLACK};
    int i=3;
    int shaderflag=0;
    int clickflag=0;
    int direct=4;
    float xstart,xend,ystart,yend;
    float x;
    float y;
    float px;
    float py;
    int w;
    int  h;
    int flag=0;
    int color;
    int resetcolor=Color.BLACK;
    int resetflag=0;
    public canvas(Context context,AttributeSet attributeSet){
        super(context,attributeSet);
        w=context.getResources().getDisplayMetrics().widthPixels;
        h=context.getResources().getDisplayMetrics().heightPixels;
        bitmap=bit(i);
        paint.setStrokeWidth(10);
        paint.setAntiAlias(true);
        canv=new Canvas();
        canv.setBitmap(bitmap);
        paint.setStyle(Paint.Style.STROKE);
    }
    public void onDraw(Canvas canvas){
        super.onDraw(canvas);
        maxx=this.getWidth();
        maxy=this.getHeight();
        direct=dir();
        if(flag==0){
        canvas.drawBitmap(bitmap,0,0,paint);
        canvas.saveLayer(0,0,w,h,paint,Canvas.ALL_SAVE_FLAG);
        canvas.drawPath(path,paint);
        if(xstart==x&&ystart==y){canvas.drawPoint(x,y,paint);}
        canvas.restore();
        }
        else if(flag==1){
            paint.setXfermode(new PorterDuffXfermode(PorterDuff.Mode.SRC));
            paint.setColor(colors[i]);
            paint.setStyle(Paint.Style.FILL);
            canv.drawRect(0,0,w,h,paint);
            paint.setStyle(Paint.Style.STROKE);
            paint.setXfermode(null);
            paint.setColor(resetcolor);
            paint.setStrokeWidth(10);
            flag=resetflag;}
        else if(flag==2){
            canvas.drawBitmap(bitmap,0,0,paint);
            canvas.saveLayer(0,0,w,h,paint,Canvas.ALL_SAVE_FLAG);
            if(clickflag==1){
            canvas.drawLine(xstart,ystart,xend,yend,paint);}
            canvas.restore();
            }
        else if(flag==3){
            double x=Math.abs(xstart-xend);
            double y=Math.abs(ystart-yend);
            float radiu=(float)Math.hypot(x,y);
            canvas.drawBitmap(bitmap,0,0,paint);
            canvas.saveLayer(0,0,w,h,paint,Canvas.ALL_SAVE_FLAG);
            if(clickflag==1){canvas.drawCircle(xstart,ystart,radiu,paint);}
            canvas.restore();}
        else if(flag==4){
            canvas.drawBitmap(bitmap,0,0,paint);
            canvas.saveLayer(0,0,w,h,paint,Canvas.ALL_SAVE_FLAG);
            if(clickflag==1){
                switch(direct){
                    case 4:RectF rectF4=new RectF(xstart,ystart,xend,yend);canvas.drawOval(rectF4,paint);
                    case 3:RectF rectF3=new RectF(xend,ystart,xstart,yend);canvas.drawOval(rectF3,paint);
                    case 2:RectF rectF2=new RectF(xend,yend,xstart,ystart);canvas.drawOval(rectF2,paint);
                    case 1:RectF rectF1=new RectF(xstart,yend,xend,ystart);canvas.drawOval(rectF1,paint);
                }
            }
            canvas.restore();
        }
        else if(flag==5){
            canvas.drawBitmap(bitmap,0,0,paint);
            canvas.saveLayer(0,0,w,h,paint,Canvas.ALL_SAVE_FLAG);
            if(clickflag==1){
                switch(direct){
                    case 4:canvas.drawRect(xstart,ystart,xend,yend,paint);
                    case 3:canvas.drawRect(xend,ystart,xstart,yend,paint);
                    case 2:canvas.drawRect(xend,yend,xstart,ystart,paint);
                    case 1:canvas.drawRect(xstart,yend,xend,ystart,paint);
                }
            }
            canvas.restore();
        };
    }
    public boolean onTouchEvent(MotionEvent event){
        x=event.getX();
        y=event.getY();
        switch(event.getAction()){
            case MotionEvent.ACTION_DOWN:
                if(x>maxx){x=maxx;}
                if(x<0){x=0;}
                if(y>maxy){y=maxy;}
                if(y<0){y=0;}
                xstart=x;
                ystart=y;
                path.moveTo(x,y);
                px=x;
                py=y;
                break;
            case MotionEvent.ACTION_MOVE:
                if(x>maxx){x=maxx;}
                if(x<0){x=0;}
                if(y>maxy){y=maxy;}
                if(y<0){y=0;}
                path.quadTo(px,py,(px+x)/2,(py+y)/2);
                xend=x;
                yend=y;
                px=x;
                py=y;
                clickflag=1;
                //if(kflag==0){mediaPlayer1.start();}
                if(kflag==1){mediaPlayer.start();}
                break;
            case MotionEvent.ACTION_UP:
                if(kflag==1){mediaPlayer.pause();}
                //if(kflag==0){mediaPlayer1.pause();}
                switch(flag){
                    case 0:canv.drawPath(path,paint);if(xstart==x&&ystart==y){canv.drawPoint(x,y,paint);};break;
                    case 2:if(clickflag==1){canv.drawLine(xstart,ystart,xend,yend,paint);}break;
                    case 3:if(clickflag==1){
                        double x=Math.abs(xstart-xend);
                        double y=Math.abs(ystart-yend);
                        float radiu=(float)Math.hypot(x,y);
                        canv.drawCircle(xstart,ystart,radiu,paint);
                    }break;
                    case 4:if(clickflag==1){RectF rectF=new RectF(xstart,ystart,xend,yend);canv.drawOval(rectF,paint);}break;
                    case 5:if(clickflag==1){canv.drawRect(xstart,ystart,xend,yend,paint);}break;
                }
                clickflag=0;
                path.reset();
                break;
        }
        invalidate();
        return true;
    }
    public void Reset(int color,int resetf){
        path.reset();
        paint.setShader(null);
        flag=1;
        resetcolor=color;
        resetflag=resetf;
        invalidate();
    }
    public void set(float a){
        paint.setStrokeWidth(a);
    }
    public void save(String s)throws IOException{
        String base="qwertyu123i4o5p6a7s8d9f0ghjklmnbvcxz";
        StringBuffer buffer=new StringBuffer();
        Random random=new Random();
        for(int i=1;i<=10;i++){
            int num=random.nextInt(base.length());
            buffer.append(base.charAt(num));
        }
        String s1=s+"/picture";
        String str=s+"/picture/";
        File files=new File(s1);
        if(!files.exists()){files.mkdir();};
        File file=new File(str+buffer+".png");
        file.createNewFile();
        FileOutputStream outputStream=new FileOutputStream(file);
        bitmap.compress(Bitmap.CompressFormat.PNG,50,outputStream);
        outputStream.flush();
        outputStream.close();
        Intent intent=new Intent(Intent.ACTION_MEDIA_SCANNER_SCAN_FILE);
        Uri uri=Uri.fromFile(file);
        intent.setData(uri);
        getContext().sendBroadcast(intent);
    }
    public void clean(){
        path.reset();
        mediaPlayer=MediaPlayer.create(getContext(),R.raw.a3);
        mediaPlayer.setLooping(true);
        color=paint.getColor();
        shaderflag=flag;
        kflag=1;
        flag=0;
        paint.setXfermode(new PorterDuffXfermode(PorterDuff.Mode.SRC_OVER));
        paint.setShader(null);
        paint.setColor(colors[i]);
        paint.setStrokeWidth(40);
    }
    public void rep(){
        paint.setXfermode(null);
        flag=shaderflag;
        kflag=0;
        mediaPlayer.release();
        if(shaderflag==1){setPaint();}
        paint.setColor(color);
        paint.setStrokeWidth(10);
    }
    public void change(int k,int j,int m){
        i=k;
        bitmap=bit(i);
        canv.setBitmap(bitmap);
        if(m==1){paint.setColor(colors[i]);}
        else{paint.setColor(j);};
        invalidate();
    }
    public Bitmap bit(int i){
        this.setBackgroundColor(colors[i]);
        Paint paint1=new Paint();
        paint1.setColor(colors[i]);
        paint1.setStyle(Paint.Style.FILL);
        Bitmap bitmap1=Bitmap.createBitmap(w,h, Bitmap.Config.ARGB_8888);
        Canvas canvas=new Canvas(bitmap1);
        paint1.setXfermode(new PorterDuffXfermode(PorterDuff.Mode.SRC));
        canvas.drawRect(0,0,w,h,paint1);
        return bitmap1;
    }
    public void setPaint(){
        Shader shader=new LinearGradient(0,0,w,h,new int[]{Color.BLUE,Color.GREEN,Color.RED,Color.BLACK,Color.CYAN},null,Shader.TileMode.MIRROR);
        paint.setShader(shader);
        shaderflag=1;
    }
    public void line(){flag=2;}
    public void cricle(){flag=3;}
    public void oval(){flag=4;}
    public void Rect(){flag=5;}
    public void none(){flag=0;}
    public int dir(){
        int i=4;
        if(xend>xstart&&yend<ystart){i=1;};
        if(xend<xstart&&yend<ystart){i=2;};
        if(xend<xstart&&yend>ystart){i=3;};
        return i;
    }
}
