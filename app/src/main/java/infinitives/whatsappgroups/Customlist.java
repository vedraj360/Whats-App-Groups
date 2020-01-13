package infinitives.whatsappgroups;

import android.annotation.SuppressLint;
import android.app.Activity;
import android.app.AlertDialog;
import android.app.Dialog;
import android.content.ActivityNotFoundException;
import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;
import android.graphics.Color;
import android.net.Uri;
import android.support.annotation.Nullable;
import android.support.v7.widget.RecyclerView;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import com.rey.material.app.DatePickerDialog;
import com.shashank.sony.fancydialoglib.Animation;
import com.shashank.sony.fancydialoglib.FancyAlertDialog;
import com.shashank.sony.fancydialoglib.FancyAlertDialogListener;
import com.shashank.sony.fancydialoglib.Icon;
import com.yarolegovich.lovelydialog.LovelyInfoDialog;
import com.yarolegovich.lovelydialog.LovelyStandardDialog;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Ved&Div on 30-12-2017.
 */

public class Customlist extends RecyclerView.Adapter<Customlist.MyViewHolder> {



    List<Sheet1> sheet1 = new ArrayList<>();
    Context context;
    FancyAlertDialog fancyAlertDialog;
    LovelyStandardDialog lovelyStandardDialog;


    public Customlist(List<Sheet1> sheet1, Context context) {
        this.sheet1 = sheet1;
        this.context = context;
    }

    @Override
    public MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
      View view = LayoutInflater.from(context).inflate(R.layout.fetchviewlist,parent,false);

      return new MyViewHolder(view);
    }

    @Override
    public void onBindViewHolder(MyViewHolder holder, final int position) {

     final   Sheet1 s1 = sheet1.get(position);
       // holder.t.setText(s1.getLink());
        holder.t.setText(s1.getName());
       // Toast.makeText(context, "" +s1.getLink(), Toast.LENGTH_SHORT).show();
        holder.itemView.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                Toast.makeText(context, "" +s1.getLink(), Toast.LENGTH_SHORT).show();
                new LovelyStandardDialog(context)
                        .setTopColorRes(R.color.green)
                        .setButtonsColorRes(R.color.bgBottomNavigation)
                        .setIcon(R.drawable.rtick)
                        .setTopTitleColor(R.color.green)
                        .setTitle("Group Name: \n" +s1.getName())
                        .setMessage("")
                        .setPositiveButton("Join Group", new View.OnClickListener() {
                            @Override
                            public void onClick(View v) {
                                try {
                                    context.startActivity(new Intent(Intent.ACTION_VIEW, Uri.parse(s1.getLink())));

                                }catch (Exception e)
                                {
                                    Toast.makeText(context, "Error Occurred! Try Again Later", Toast.LENGTH_SHORT).show();
                                }

                            }
                        })
                        .setNeutralButton("Share Group", new View.OnClickListener() {
                            @Override
                            public void onClick(View v) {
                                final ComponentName name = new ComponentName("com.whatsapp", "com.whatsapp.ContactPicker");
                                Intent oShareIntent = new Intent();
                                oShareIntent.setComponent(name);
                                oShareIntent.setType("text/plain");
                                oShareIntent.putExtra(Intent.EXTRA_TEXT, s1.getLink());
                                context.startActivity(oShareIntent);
                            }
                        })
                        .show();
            }
        });
    }

    @Override
    public int getItemCount() {
        return sheet1.size();
    }

    public static class MyViewHolder extends RecyclerView.ViewHolder {
        TextView t;

        public MyViewHolder(View itemView) {
            super(itemView);

            t = itemView.findViewById(R.id.text1);
        }
    }

    public void Dialog()
    {



        /*fancyAlertDialog = new FancyAlertDialog.Builder(jsonFetch).setTitle("Success")
                .setMessage("Group Successfully Added").setAnimation(Animation.SIDE).
                        setPositiveBtnText("OK").setNegativeBtnText("Rate Us").
                        setIcon(R.drawable.rtick, Icon.Visible).
                        setPositiveBtnBackground(Color.parseColor("#69d262")).
                        OnNegativeClicked(new FancyAlertDialogListener() {
                            @Override
                            public void OnClick() {
                                try {
                                    Uri marketUri = Uri.parse("market://details?id=" + "infinitives.whatsappgroups");
                                    Intent marketIntent = new Intent(Intent.ACTION_VIEW, marketUri);
                                   context.startActivity(marketIntent);
                                } catch (ActivityNotFoundException e) {
                                    Uri marketUri = Uri.parse("https://play.google.com/store/apps/details?id=" + "infinitives.whatsappgroups");
                                    Intent marketIntent = new Intent(Intent.ACTION_VIEW, marketUri);
                                    context.startActivity(marketIntent);
                                }
                            }
                        }).
                        setBackgroundColor(Color.parseColor("#69d262")).build();
                        */
    }

}
