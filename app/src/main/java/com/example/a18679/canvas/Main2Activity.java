package com.example.a18679.canvas;

import android.app.Activity;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Bundle;
import android.view.Window;
import android.widget.ImageView;

public class Main2Activity extends Activity {
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        setContentView(R.layout.layout);
        Intent intent=getIntent();
        Bundle bundle=intent.getExtras();
        String str=bundle.getString("paths");
        Bitmap bitmap= BitmapFactory.decodeFile(str);
        ImageView imageView=(ImageView)findViewById(R.id.imageview1);
        imageView.setImageBitmap(bitmap);
    }
    }
