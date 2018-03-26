package com.example.ashwingiri.stopwatch;

import android.support.v7.widget.RecyclerView;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;

/**
 * Created by Ashwin Giri on 12/28/2017.
 */

public class RecyclerAdapter extends RecyclerView.Adapter<RecyclerAdapter.DetailHolder>{
    Context mContext;
    ArrayList<details> arr;

    public RecyclerAdapter(Context mContext, ArrayList<details> arr) {
        this.mContext = mContext;
        this.arr = arr;
    }

    @Override
    public DetailHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        LayoutInflater li = (LayoutInflater) mContext.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        View itemView = li.inflate(viewType, parent, false);

        return new DetailHolder(itemView);
    }

    @Override
    public void onBindViewHolder(DetailHolder holder, final int position) {
        details details = arr.get(position);
        String s="Lap "+(position+1);
        holder.itemView.setOnClickListener(
                new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        Toast.makeText(mContext,"You clicked on Lap "+(position+1), Toast.LENGTH_SHORT).show();
                    }
                }
        );
        holder.tvLeft.setText(s);
        holder.tvRight.setText(details.getAns());
    }

    @Override
    public int getItemCount() {
        return arr.size();
    }

    @Override
    public int getItemViewType(int position) {
        return R.layout.list_view;
    }

    static class DetailHolder extends RecyclerView.ViewHolder {
        TextView tvRight,tvLeft;

        public DetailHolder(View itemView) {
            super(itemView);
            tvLeft=itemView.findViewById(R.id.tvLeft);
            tvRight= itemView.findViewById(R.id.tvRight);
        }
    }
}
