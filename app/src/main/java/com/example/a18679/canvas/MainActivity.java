package com.example.a18679.canvas;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.media.MediaPlayer;
import android.os.Bundle;
import android.os.Environment;
import android.util.Log;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.SubMenu;
import android.view.View;
import android.widget.Button;
import android.widget.PopupMenu;
import android.widget.Toast;

import java.io.IOException;

public class MainActivity extends Activity {

    canvas draw;
    Button b3=null;
    MediaPlayer mediaPlayer;
    int flag=0;
    int shape=0;
    String str= Environment.getExternalStorageDirectory().getPath();
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Button b=(Button)findViewById(R.id.button);
        mediaPlayer=MediaPlayer.create(this,R.raw.aa);
        mediaPlayer.setLooping(true);
        final Button b1=(Button)findViewById(R.id.button1);
        final Button b2=(Button)findViewById(R.id.button2);
        b3=(Button)findViewById(R.id.button3);
        final Button b4=(Button)findViewById(R.id.button4);
        draw=(canvas)findViewById(R.id.drawcanvas);
        b.setOnClickListener(new View.OnClickListener() {
            public void onClick(View view) {
                int color=draw.paint.getColor();
                draw.Reset(color,shape);
            }
        });
        b1.setOnClickListener(new View.OnClickListener() {
            public void onClick(View view) {
               showPopupMenu(b1);
            }
        });
        b2.setOnClickListener(new View.OnClickListener() {
            public void onClick(View view) {
                showPopupMenu1(b2);
            }
        });
        b3.setOnClickListener(new View.OnClickListener() {
            public void onClick(View view) {
                if(b3.getText().equals("橡皮擦")){b3.setText("画笔");draw.rep();b1.setClickable(true);b1.setAlpha(1);b4.setClickable(true);b4.setAlpha(1);}
                else{b3.setText("橡皮擦");draw.clean();b1.setClickable(false);b1.setAlpha(0.5f);b4.setClickable(false);b4.setAlpha(0.5f);};
            }
        });
        b4.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                showPopupMenu3(b4);
            }
        });
    }
    public boolean onCreateOptionsMenu(Menu menu){
        MenuInflater inflater=new MenuInflater(this);
        inflater.inflate(R.menu.menu,menu);
        return super.onCreateOptionsMenu(menu);
    }
    public boolean onOptionsItemSelected(MenuItem item){
        if(item.getItemId()==R.id.open_pic){
            Intent intent=new Intent(MainActivity.this,Main22Activity.class);
            startActivity(intent);
        }
        if(item.getItemId()==R.id.save){
            try{save(str);
            }catch (IOException e){
                Log.d("get","error");
            }
           Toast.makeText(MainActivity.this,"保存成功",Toast.LENGTH_SHORT).show();
        }
        if(item.getItemId()==R.id.stop){
            if(flag==1){mediaPlayer.pause();item.setTitle("打开背景音乐");flag=0;}
            else{mediaPlayer.start();flag=1;item.setTitle("关闭背景音乐");}
        }
        if(item.getGroupId()==R.id.change) {
            if (item.getItemId() == R.id.blue) {
                dialog(0);
            }
            if (item.getItemId() == R.id.green) {
                dialog(1);
            }
            if (item.getItemId() == R.id.red) {
                dialog(2);
            }
            if (item.getItemId() == R.id.white) {
                dialog(3);
            }
            if (item.getItemId() == R.id.black) {
                dialog(4);
            }
        }
        return true;
    }
    public void showPopupMenu(View view){
        PopupMenu popupMenu=new PopupMenu(this,view);
        popupMenu.getMenuInflater().inflate(R.menu.menu1,popupMenu.getMenu());
        popupMenu.setOnMenuItemClickListener(new PopupMenu.OnMenuItemClickListener() {
            public boolean onMenuItemClick(MenuItem item) {
                if(item.getTitle().equals("栗色")){draw.paint.setColor(getResources().getColor(R.color.栗色,getTheme()));draw.shaderflag=0;draw.paint.setShader(null);}
                if(item.getTitle().equals("金色")){draw.paint.setColor(getResources().getColor(R.color.金,getTheme()));draw.shaderflag=0;draw.paint.setShader(null);}
                if(item.getTitle().equals("绿色")){draw.paint.setColor(getResources().getColor(R.color.绿,getTheme()));draw.shaderflag=0;draw.paint.setShader(null);}
                if(item.getTitle().equals("蓝色")){draw.paint.setColor(getResources().getColor(R.color.蓝,getTheme()));draw.shaderflag=0;draw.paint.setShader(null);}
                if(item.getTitle().equals("猩红色")){draw.paint.setColor(getResources().getColor(R.color.猩红,getTheme()));draw.shaderflag=0;draw.paint.setShader(null);}
                if(item.getTitle().equals("黑色")){draw.paint.setColor(getResources().getColor(R.color.黑,getTheme()));draw.shaderflag=0;draw.paint.setShader(null);}
                if(item.getTitle().equals("银白色")){draw.paint.setColor(getResources().getColor(R.color.银白色,getTheme()));draw.shaderflag=0;draw.paint.setShader(null);}
                if(item.getTitle().equals("红色")){draw.paint.setColor(getResources().getColor(R.color.红,getTheme()));draw.shaderflag=0;draw.paint.setShader(null);}
                if(item.getTitle().equals("彩色")){draw.setPaint();}
                return true;
            }
        });
        popupMenu.show();
    }
    public void showPopupMenu1(View view){
        PopupMenu popupMenu=new PopupMenu(this,view);
        popupMenu.getMenuInflater().inflate(R.menu.paintmenu,popupMenu.getMenu());
        popupMenu.setOnMenuItemClickListener(new PopupMenu.OnMenuItemClickListener() {
            public boolean onMenuItemClick(MenuItem item) {
                if(item.getTitle().equals("10")){draw.set(10);}
                if(item.getTitle().equals("15")){draw.set(15);}
                if(item.getTitle().equals("20")){draw.set(20);}
                if(item.getTitle().equals("25")){draw.set(25);}
                if(item.getTitle().equals("30")){draw.set(30);}
                if(item.getTitle().equals("40")){draw.set(40);}
                return true;
            }
        });
        popupMenu.show();
    }
    public void save(String s)throws IOException{
        draw.save(s);
    }
    public void dialog(final int num){
        AlertDialog.Builder Dialog=new AlertDialog.Builder(MainActivity.this);
        Dialog.setTitle("提醒");
        Dialog.setIcon(R.drawable.a3);
        Dialog.setMessage("此刻改变背景将会清空画布！！！");
        Dialog.setPositiveButton("确定", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialogInterface, int i) {
                int m=1;
                if(b3.getText().equals("画笔")){m=0;}
                int color=draw.paint.getColor();
                draw.change(num,color,m);
            }
        });
        Dialog.setNegativeButton("取消", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialogInterface, int i) {

            }
        });
        Dialog.show();
    }
    public void showPopupMenu3(View view){
        PopupMenu popupMenu=new PopupMenu(this,view);
        popupMenu.getMenuInflater().inflate(R.menu.shape,popupMenu.getMenu());
        popupMenu.setOnMenuItemClickListener(new PopupMenu.OnMenuItemClickListener() {
            @Override
            public boolean onMenuItemClick(MenuItem item) {
                if(item.getTitle().equals("直线")){shape=2;draw.line();}
                if(item.getTitle().equals("圆")){shape=3;draw.cricle();}
                if(item.getTitle().equals("椭圆")){shape=4;draw.oval();}
                if(item.getTitle().equals("矩形")){shape=5;draw.Rect();}
                if(item.getTitle().equals("无")){shape=0;draw.none();}
                return true;
            }
        });
        popupMenu.show();
    }
}
