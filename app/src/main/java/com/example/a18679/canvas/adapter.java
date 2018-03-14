package com.example.a18679.canvas;

import android.content.Context;
import android.graphics.Bitmap;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.Map;

/**
 * Created by 18679 on 2017-4-19.
 */

public class adapter extends BaseAdapter {
    private ArrayList<Map<String,Object>> list;
    private LayoutInflater layoutInflater;
    public adapter(Context context, ArrayList<Map<String,Object>> list){
        this.list=list;
        layoutInflater=LayoutInflater.from(context);
    }
    public int getCount(){
        return this.list.size();
    }
    public View getView(int position,View view, ViewGroup viewGroup){
        Bufferview buffer;
        if(view==null){
            buffer=new Bufferview();
            view=layoutInflater.inflate(R.layout.listlayout,viewGroup,false);
            buffer.imageView=(ImageView)view.findViewById(R.id.imageview);
            buffer.textView=(TextView)view.findViewById(R.id.texview);
            view.setTag(buffer);
        }
        else {buffer=(Bufferview)view.getTag();}
        buffer.imageView.setImageBitmap((Bitmap) list.get(position).get("pic"));
        buffer.textView.setText(list.get(position).get("path").toString());
        Log.d("get","getView");
        return view;
    }
    public Object getItem(int position){
        return this.list.get(position);
    }
    public long getItemId(int id){
        return id;
    }
    public static class Bufferview{
        private TextView textView;
        private ImageView imageView;
    }
}
