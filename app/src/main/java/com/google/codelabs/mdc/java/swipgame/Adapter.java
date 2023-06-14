package com.google.codelabs.mdc.java.swipgame;

import android.content.Context;
import android.os.Build;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;

import androidx.annotation.NonNull;
import androidx.annotation.RequiresApi;
import androidx.recyclerview.widget.RecyclerView;

import java.util.List;

public class Adapter extends RecyclerView.Adapter<Adapter.ViewHolder> {
    List<Modelclass> bars;
    Context context;

    public Adapter(List<Modelclass> bars, Context context) {
        this.bars = bars;
        this.context = context;
    }

    @NonNull
    @Override
    public Adapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view= LayoutInflater.from(parent.getContext()).inflate(R.layout.bars,parent,false);

        return new ViewHolder(view);
    }

    @RequiresApi(api = Build.VERSION_CODES.LOLLIPOP)
    @Override
    public void onBindViewHolder(@NonNull Adapter.ViewHolder holder, int position) {
        String color =  bars.get(position).getColor();

        if (color.equals("Yellow")){
            holder.liearlayout.setBackgroundTintList(context.getResources().getColorStateList(R.color.yellow));
        }
        else
        {
            holder.liearlayout.setBackgroundTintList(context.getResources().getColorStateList(R.color.red));
        }
    }

    @Override
    public int getItemCount() {
        return bars.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        LinearLayout liearlayout;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            liearlayout=itemView.findViewById(R.id.barlayout);
        }
    }
}
