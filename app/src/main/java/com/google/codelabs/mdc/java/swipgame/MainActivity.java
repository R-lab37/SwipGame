package com.google.codelabs.mdc.java.swipgame;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.ItemTouchHelper;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.DialogInterface;
import android.os.Bundle;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class MainActivity extends AppCompatActivity {

private RecyclerView recyclerView;
private List<Modelclass> barsColor;
AlertDialog.Builder alertDialog;
private Adapter adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        barsColor=new ArrayList<>();
        Random random=new Random();
        for(int i=0;i<100;i++)
        {
            int n =random.nextInt(2);

            if (n ==0){
                barsColor.add(new Modelclass("Yellow"));

            }else{
                barsColor.add(new Modelclass("Red"));
            }
        }

        recyclerView=findViewById(R.id.recyclerview);
        adapter=new Adapter(barsColor,this);
        recyclerView.setAdapter(adapter);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        ItemTouchHelper itemTouchHelper = new ItemTouchHelper(simpleCallback);
        itemTouchHelper.attachToRecyclerView(recyclerView);

        adapter.notifyDataSetChanged();
    }
    ItemTouchHelper.SimpleCallback simpleCallback=  new ItemTouchHelper.SimpleCallback(0,ItemTouchHelper.RIGHT|ItemTouchHelper.LEFT) {
        @Override
        public boolean onMove(@NonNull RecyclerView recyclerView, @NonNull RecyclerView.ViewHolder viewHolder, @NonNull RecyclerView.ViewHolder target) {
            return false;
        }

        @Override
        public void onSwiped(@NonNull RecyclerView.ViewHolder viewHolder, int direction) {
        int position  =viewHolder.getPosition();
        switch (direction){
            case ItemTouchHelper.LEFT:{
                if ((barsColor.get(position).getColor()).equals("Red")){
                    barsColor.remove(position);
                    adapter.notifyDataSetChanged();
                }else{
                    endtheGame();
                    adapter.notifyDataSetChanged();
                    alertDialog.show();
                }
                break;

            }

            case ItemTouchHelper.RIGHT:{
                if ((barsColor.get(position).getColor()).equals("Yellow")){
                    barsColor.remove(position);
                    adapter.notifyDataSetChanged();
                }else{
                    endtheGame();
                    adapter.notifyDataSetChanged();
                    alertDialog.show();

                }
                break;
            }
          }
        }
    };


    private void endtheGame() {
     alertDialog=  new AlertDialog.Builder(this);
     alertDialog.setMessage("Oops! wrong side ! Try again! ").setPositiveButton("try again", new DialogInterface.OnClickListener() {
         @Override
         public void onClick(DialogInterface dialog, int which) {
             Toast.makeText(MainActivity.this, " Try Again!", Toast.LENGTH_SHORT).show();
         }
     }).setNegativeButton("Later", new DialogInterface.OnClickListener() {
         @Override
         public void onClick(DialogInterface dialog, int which) {
             Toast.makeText(MainActivity.this, "Later!", Toast.LENGTH_SHORT).show();
         }
     });


    }
}