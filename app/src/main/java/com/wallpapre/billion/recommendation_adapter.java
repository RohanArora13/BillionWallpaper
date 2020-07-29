package com.wallpapre.billion;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;

public class recommendation_adapter extends RecyclerView.Adapter<recommendation_adapter.ViewHolder> {

    private Context context;
    private ArrayList<String> listitems;

    public recommendation_adapter(Context context, ArrayList<String> listitems) {
        this.context = context;
        this.listitems = listitems;
    }
    

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.recommendation_single_item, parent, false);
        ViewHolder viewHolder = new ViewHolder(v);
        return viewHolder;
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {

        holder.itemView.setTag(listitems.get(position));

       // listitems pu = listitems.get(position);

        holder.rec_button.setText(listitems.get(position).toString());

    }

    @Override
    public int getItemCount() {
        return listitems.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder{

        public Button rec_button;

        public ViewHolder(View itemView) {
            super(itemView);

            rec_button = (Button) itemView.findViewById(R.id.button);

            rec_button.setOnClickListener(new Button.OnClickListener() {
                @Override
                public void onClick(View view) {
                    ((MainActivity)context).onRecommendationClick(rec_button.getText().toString());
                   // listitems cpu = (listitems) view.getTag();
                    //Toast.makeText(view.getContext(), cpu.getPersonName()+" is "+ cpu.getJobProfile(), Toast.LENGTH_SHORT).show();

                }
            });

        }
    }

}
