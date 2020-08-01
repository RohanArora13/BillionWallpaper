package com.wallpapre.billion;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.request.RequestOptions;

import java.util.ArrayList;

public class images_adapter extends RecyclerView.Adapter<images_adapter.ViewHolder> {

    private Context context;
    private ArrayList<String> listitems;

    public images_adapter(Context context, ArrayList<String> listitems) {
        this.context = context;
        this.listitems = listitems;
    }


    @NonNull
    @Override
    public images_adapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.single_image, parent, false);
        images_adapter.ViewHolder viewHolder = new images_adapter.ViewHolder(v);
        return viewHolder;
    }

    @Override
    public void onBindViewHolder(images_adapter.ViewHolder holder, int position) {

        holder.itemView.setTag(listitems.get(position));

       // holder.rec_button.setText(listitems.get(position).toString());

        Glide.with(context)
                .load(listitems.get(position))
                .apply(new RequestOptions()
                        .placeholder(R.drawable.search)
                        .error(R.drawable.search))
                .into(holder.single_image);

    }

    @Override
    public int getItemCount() {
        return listitems.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder{

        public ImageView single_image;

        public ViewHolder(View itemView) {
            super(itemView);

            single_image = (ImageView) itemView.findViewById(R.id.single_image_view);


//            rec_button.setOnClickListener(new Button.OnClickListener() {
//                @Override
//                public void onClick(View view) {
//
//                    ((MainActivity)context).onRecommendationClick(rec_button.getText().toString());
//                    // listitems cpu = (listitems) view.getTag();
//                    //Toast.makeText(view.getContext(), cpu.getPersonName()+" is "+ cpu.getJobProfile(), Toast.LENGTH_SHORT).show();
//
//                }
//            });

        }
    }

}
