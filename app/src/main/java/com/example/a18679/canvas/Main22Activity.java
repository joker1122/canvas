package com.example.a18679.canvas;

import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Bundle;
import android.app.Activity;
import android.os.Environment;
import android.util.Log;
import android.view.ContextMenu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListAdapter;
import android.widget.ListView;
import android.widget.TextView;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;


public class Main22Activity extends Activity {
    ArrayList<Map<String,Object>> list=null;
    String pict[]={".png",".3gp"};
    String paths=null;
    ListView listView=null;
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.piclayout);
        listView=(ListView)findViewById(R.id.pic_list);
        list=new ArrayList<Map<String, Object>>();
        try{
            getmessage();
        }catch (IOException e){
            Log.d("get","error");
        }
        if(!list.isEmpty()){
        ListAdapter adapters=new adapter(this,list);
        listView.setAdapter(adapters);}
        listView.setOnItemLongClickListener(new AdapterView.OnItemLongClickListener() {
            @Override
            public boolean onItemLongClick(AdapterView<?> adapterView, View view, int i, long l) {
                registerForContextMenu(listView);
                TextView text=(TextView)view.findViewById(R.id.texview);
                paths=text.getText().toString();
                return false;
            }
        });
        Log.d("get","end");
    }
    public void getmessage()throws IOException{
        String str= Environment.getExternalStorageDirectory().getPath()+"/picture";
        File[]file1=null;
        File file=new File(str);
        if(file.exists()){
        file1=file.listFiles();}
        if(file1!=null){
        for(File i:file1){
            if(ispic(i.getPath())){
                Bitmap bitmap= BitmapFactory.decodeFile(i.getPath());
                String s=i.getPath();
                Map<String,Object>map=new HashMap<>();
                map.put("pic",bitmap);
                map.put("path",s);
                list.add(map);
                Log.d("get","map");
            }
        }
        }
    }
    public boolean ispic(String s){
        for(String i:pict){
            if(s.contains(i))
                return true;
        }
        return false;
    }
    public void onCreateContextMenu(ContextMenu contextMenu, View view, ContextMenu.ContextMenuInfo info){
        MenuInflater inflater=new MenuInflater(Main22Activity.this);
        inflater.inflate(R.menu.picmenu,contextMenu);
    }
    public boolean onContextItemSelected(MenuItem item){
        if(item.getTitle().equals("打开")){
            Intent intent=new Intent(Main22Activity.this,Main2Activity.class);
//            intent.setAction(Intent.ACTION_VIEW);
            Bundle bundle=new Bundle();
            bundle.putCharSequence("paths",paths);
            intent.putExtras(bundle);
            startActivity(intent);
        }
        if(item.getTitle().equals("删除")){
            File file=new File(paths);
            file.delete();
            list.clear();
            try{
                getmessage();
            }catch (IOException e){
                Log.d("get","error");
            }
            if(!list.isEmpty()){
                ListAdapter adapters=new adapter(this,list);
                listView.setAdapter(adapters);}
        }
        return true;
    }
}
