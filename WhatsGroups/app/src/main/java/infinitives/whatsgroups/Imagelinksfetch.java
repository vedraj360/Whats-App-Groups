package infinitives.whatsgroups;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;

import java.util.List;

import osmandroid.project_basics.Task;

/**
 * Created by Ved&Div on 10-01-2018.
 */

public class Imagelinksfetch extends RecyclerView.Adapter<Imagelinksfetch.ImageFatch>{

    Context context;
    List<Sheet1> imagelist;

    public Imagelinksfetch(Context context, List<Sheet1> imagelist) {
        this.context = context;
        this.imagelist = imagelist;
    }




    @Override
    public ImageFatch onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.imagefetch,parent,false);

        return new ImageFatch(view);
    }

    @Override
    public void onBindViewHolder(ImageFatch holder, int position) {

        Sheet1 listimage = imagelist.get(position);
        Glide.with(context).load(listimage.getLink()).into(holder.imageView);
        holder.textView.setText(listimage.getName());

        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Task.MoreApps(context,"infinitives");
            }
        });

    }

    @Override
    public int getItemCount() {
        return imagelist.size();
    }

    public class ImageFatch extends RecyclerView.ViewHolder {
        TextView textView;
        ImageView imageView;

        public ImageFatch(View itemView) {
            super(itemView);

            textView = itemView.findViewById(R.id.link);
            imageView = itemView.findViewById(R.id.image);
        }
    }

    @Override
    public int getItemViewType(int position) {
        if (position == 4) {
            return 2;
        } else {
            return 1;
        }
    }
}
