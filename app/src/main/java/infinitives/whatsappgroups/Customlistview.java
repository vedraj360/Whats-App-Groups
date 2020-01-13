package infinitives.whatsappgroups;

import android.content.Context;
import android.content.Intent;
import android.graphics.drawable.Drawable;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;

import java.nio.file.attribute.PosixFileAttributes;
import java.util.ArrayList;
import java.util.List;


/**
 * Created by Ved&Div on 30-12-2017.
 */

public class Customlistview extends RecyclerView.Adapter<Customlistview.Listview> {

    List<Grpclass> list;
    Context context;
    int[] icons;
    String Links[];

    public Customlistview(List<Grpclass> list, Context context, int[] icons, String[] links) {
        this.list = list;
        this.context = context;
        this.icons = icons;
        Links = links;
    }

    @Override
    public Listview onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.customlist, parent, false);
        return new Listview(view);
    }

    @Override
    public void onBindViewHolder(Listview holder, final int position) {

        Grpclass grpclass = list.get(position);
        holder.textView.setText(grpclass.getGroup());
        try {
            Glide.with(context).load(icons[position]).into(holder.imageView);
        } catch (Exception e) {

        }
        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                changeAct(Links,position);
            }
        });

    }

    @Override
    public int getItemCount() {
        return list.size();
    }

    public class Listview extends RecyclerView.ViewHolder {
        TextView textView;
        ImageView imageView;

        public Listview(View itemView) {
            super(itemView);

            textView = itemView.findViewById(R.id.text);
            imageView = itemView.findViewById(R.id.icons);
        }
    }

    private void changeAct(String links[], int position) {
        Intent intent = new Intent(context, JsonFetch.class);
        switch (position) {
            case 0:

                intent.putExtra("0", links[0]);
               context.startActivity(intent);
                break;
            case 1:
                intent.putExtra("0", links[1]);
                 context.startActivity(intent);
                break;
            case 2:
                intent.putExtra("0", links[2]);
                 context.startActivity(intent);
                break;
            case 3:
                intent.putExtra("0", links[3]);
                 context.startActivity(intent);
                break;
            case 4:
                intent.putExtra("0", links[4]);
                 context.startActivity(intent);
                break;
            case 5:
                intent.putExtra("0", links[5]);
                 context.startActivity(intent);
                break;
            case 6:
                intent.putExtra("0", links[6]);
                 context.startActivity(intent);
                break;
            case 7:
                intent.putExtra("0", links[7]);
                 context.startActivity(intent);
                break;
            case 8:
                intent.putExtra("0", links[8]);
                 context.startActivity(intent);
                break;
            case 9:
                intent.putExtra("0", links[9]);
                 context.startActivity(intent);
                break;
            case 10:
                intent.putExtra("0", links[10]);
                 context.startActivity(intent);
                break;

        }
    }
}