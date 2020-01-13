package infinitives.whatsgroups;

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
import com.google.android.gms.ads.AdListener;
import com.google.android.gms.ads.AdRequest;
import com.google.android.gms.ads.InterstitialAd;

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
    private String GroupsTitles[] = {"ADULTS 18+", "LOVE", "SPORTS", "GAMING", "HACKING", "NEWS", "PROMOTIONS", "NATURE", "PROGRAMMING", "NON-VEG JOKES", "VEG JOKES"};


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

    @Override
    public int getItemViewType(int position) {
        if (position == 10)
            return 2;
            else
                return 1;
    }

    private void changeAct(String links[], int position) {
        Intent intent = new Intent(context, JsonFetch.class);
        switch (position) {
            case 0:

                intent.putExtra("0", links[0]);
                intent.putExtra("1",GroupsTitles[0]);
               context.startActivity(intent);
                break;
            case 1:
                intent.putExtra("0", links[1]);
                intent.putExtra("1",GroupsTitles[1]);
                 context.startActivity(intent);
                break;
            case 2:
                intent.putExtra("0", links[2]);
                intent.putExtra("1",GroupsTitles[2]);
                 context.startActivity(intent);
                break;
            case 3:
                intent.putExtra("0", links[3]);
                intent.putExtra("1",GroupsTitles[3]);
                 context.startActivity(intent);
                break;
            case 4:
                intent.putExtra("0", links[4]);
                intent.putExtra("1",GroupsTitles[4]);
                 context.startActivity(intent);
                break;
            case 5:
                intent.putExtra("0", links[5]);
                intent.putExtra("1",GroupsTitles[5]);
                 context.startActivity(intent);
                break;
            case 6:
                intent.putExtra("0", links[6]);
                intent.putExtra("1",GroupsTitles[6]);
                 context.startActivity(intent);
                break;
            case 7:
                intent.putExtra("0", links[7]);
                intent.putExtra("1",GroupsTitles[7]);
                 context.startActivity(intent);
                break;
            case 8:
                intent.putExtra("0", links[8]);
                intent.putExtra("1",GroupsTitles[8]);
                 context.startActivity(intent);
                break;
            case 9:
                intent.putExtra("0", links[9]);
                intent.putExtra("1",GroupsTitles[9]);
                 context.startActivity(intent);
                break;
            case 10:
                intent.putExtra("0", links[10]);
                intent.putExtra("1",GroupsTitles[10]);
                 context.startActivity(intent);
                break;

        }
    }


}