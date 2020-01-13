package infinitives.whatsgroups;

import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;
import android.graphics.Color;
import android.net.Uri;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.ads.AdListener;
import com.google.android.gms.ads.AdRequest;
import com.google.android.gms.ads.InterstitialAd;
import com.shashank.sony.fancydialoglib.Animation;
import com.shashank.sony.fancydialoglib.FancyAlertDialog;
import com.shashank.sony.fancydialoglib.FancyAlertDialogListener;
import com.shashank.sony.fancydialoglib.Icon;
import java.util.ArrayList;
import java.util.List;


/**
 * Created by Ved&Div on 30-12-2017.
 */

public class Customlist extends RecyclerView.Adapter<Customlist.MyViewHolder> {


    List<Sheet1> sheet1 = new ArrayList<>();
    Context context;
    FancyAlertDialog.Builder fancyAlertDialog;
    Boolean show = false;
    private InterstitialAd interstitialAd1;

    public Customlist(List<Sheet1> sheet1, Context context, FancyAlertDialog.Builder fancyAlertDialog) {
        this.sheet1 = sheet1;
        this.context = context;
        this.fancyAlertDialog = fancyAlertDialog;
    }

    @Override
    public MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.fetchviewlist, parent, false);

        return new MyViewHolder(view);
    }

    @Override
    public void onBindViewHolder(MyViewHolder holder, final int position) {

        final Sheet1 s1 = sheet1.get(position);
        // holder.t.setText(s1.getLink());
        holder.t.setText(s1.getName());
        // Toast.makeText(context, "" +s1.getLink(), Toast.LENGTH_SHORT).show();
        holder.itemView.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {

                ShowAds(interstitialAd1);
                Dialog(s1);
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

    public void Dialog(final Sheet1 getsheet) {


        fancyAlertDialog
                .setTitle("Group Name:")
                .setMessage(getsheet.getName())
                .setAnimation(Animation.SIDE).
                setPositiveBtnText("OK").setNegativeBtnText("Share").
                setIcon(R.drawable.rtick, Icon.Visible).
                isCancellable(true).
                setPositiveBtnBackground(Color.parseColor("#69d262")).
                OnPositiveClicked(new FancyAlertDialogListener() {
                    @Override
                    public void OnClick() {

                        try {
                            context.startActivity(new Intent(Intent.ACTION_VIEW, Uri.parse(getsheet.getLink())));

                        } catch (Exception e) {
                            Toast.makeText(context, "Error Occurred! Try Again Later", Toast.LENGTH_SHORT).show();
                        }

                    }
                }).
                OnNegativeClicked(new FancyAlertDialogListener() {
                    @Override
                    public void OnClick() {

                        try {
                            final ComponentName name = new ComponentName("com.whatsapp", "com.whatsapp.ContactPicker");
                            Intent oShareIntent = new Intent();
                            oShareIntent.setComponent(name);
                            oShareIntent.setType("text/plain");
                            oShareIntent.putExtra(Intent.EXTRA_TEXT, "One Of the Amazing Group From WhatsGroup App Join Now."
                                    + getsheet.getLink());
                            context.startActivity(oShareIntent);
                        } catch (Exception e) {
                            Toast.makeText(context, "Whats App Not Found!", Toast.LENGTH_SHORT).show();
                            Log.d("Error", String.valueOf(e));
                        }

                    }
                }).
                setBackgroundColor(Color.parseColor("#69d262")).build();

    }

    public void ShowAds(InterstitialAd interstitial1) {


        interstitial1 = new InterstitialAd(context);
        interstitial1.setAdUnitId("ca-app-pub-3940256099942544/1033173712");

        interstitial1.loadAd(new AdRequest.Builder().addTestDevice("754DB6521943676637AE86202C5ACE52").build());

        final InterstitialAd finalInterstitial = interstitial1;

        interstitial1.setAdListener(new AdListener() {
            @Override
            public void onAdLoaded() {
                if (finalInterstitial.isLoaded()) {
                    finalInterstitial.show();
                }
            }

            @Override
            public void onAdLeftApplication() {

                show = true;
                Log.d("l", String.valueOf(show));
            }

            @Override
            public void onAdClosed() {

                if(!show)
                {
                   onAdLoaded();
                   show = true;
                }
                Log.d("t", String.valueOf(show));
            }

        });


    }

}
