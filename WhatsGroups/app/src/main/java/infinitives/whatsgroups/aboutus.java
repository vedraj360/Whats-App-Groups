package infinitives.whatsgroups;

import android.content.Context;
import android.content.Intent;
import android.content.res.Configuration;
import android.net.Uri;
import android.os.Bundle;
import android.os.Handler;
import android.support.v4.app.Fragment;
import android.support.v7.app.AppCompatDelegate;
import android.util.Log;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.Toast;

import com.google.android.gms.ads.AdListener;
import com.google.android.gms.ads.AdRequest;
import com.google.android.gms.ads.InterstitialAd;

import java.util.Calendar;

import mehdi.sakout.aboutpage.AboutPage;
import mehdi.sakout.aboutpage.Element;
import osmandroid.project_basics.Task;


/**
 * A simple {@link Fragment} subclass.
 * Activities that contain this fragment must implement the
 * {@link OnFragmentInteractionListener} interface
 * to handle interaction events.
 * Use the {@link aboutus#newInstance} factory method to
 * create an instance of this fragment.
 */
public class aboutus extends Fragment {
    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";
   private static final String url = "http://infinitivesapp.blogspot.in/2018/01/privacy-policy.html";
    private InterstitialAd interstitialAd1;
    private static Boolean valid = true;
    private static int count = 0;

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;

    private OnFragmentInteractionListener mListener;

    public aboutus() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment aboutus.
     */
    // TODO: Rename and change types and number of parameters
    public static aboutus newInstance(String param1, String param2) {
        aboutus fragment = new aboutus();
        Bundle args = new Bundle();
        args.putString(ARG_PARAM1, param1);
        args.putString(ARG_PARAM2, param2);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
            mParam1 = getArguments().getString(ARG_PARAM1);
            mParam2 = getArguments().getString(ARG_PARAM2);
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
      View view = inflater.inflate(R.layout.fragment_aboutus, container, false);
        simulateDayNight(/* DAY */ 0);
        Element adsElement = new Element();
        adsElement.setTitle("Infinitives");

        final Handler handler = new Handler();
        final Runnable runnable = new Runnable() {
            @Override
            public void run() {
                if (valid) {
                    count++;
                    try {
                        Log.d("time", String.valueOf(valid));
                        handler.postDelayed(this, 60000
                        );
                    } catch (Exception e) {

                    }
                }
                else {
                    handler.hasMessages(0);
                }


            }
        };
        handler.postDelayed(runnable, 60000);

        View aboutPage = new AboutPage(getContext())
                .isRTL(false)
                .setImage(R.drawable.infi)
                .addItem(new Element().setTitle("Version 1.0"))
                .addItem(adsElement)
                .addGroup("Connect with us")
                .setDescription("We Are Extreme Coders And Programmers.")
                .addEmail("infinitivesapp@gmail.com")
                .addWebsite("http://infinitivesapp.blogspot.in")
                .addPlayStore("infinitives.whatsgroups")
                .addItem(PrivacyPolicy())
                .create();


      return aboutPage;
    }

    // TODO: Rename method, update argument and hook method into UI event
    public void onButtonPressed(Uri uri) {
        if (mListener != null) {
            mListener.onFragmentInteraction(uri);
        }
    }

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        if (context instanceof OnFragmentInteractionListener) {
            mListener = (OnFragmentInteractionListener) context;
        } else {
            throw new RuntimeException(context.toString()
                    + " must implement OnFragmentInteractionListener");
        }
    }

    @Override
    public void onDetach() {
        super.onDetach();
        mListener = null;
    }

    @Override
    public void onResume() {
        super.onResume();
        valid = true;
        Log.d("Act", "onResume");
    }

    @Override
    public void onPause() {
        super.onPause();
        valid = false;
        count = 0;
        Log.d("Act", "OnPuse");
    }


    /**
     * This interface must be implemented by activities that contain this
     * fragment to allow an interaction in this fragment to be communicated
     * to the activity and potentially other fragments contained in that
     * activity.
     * <p>
     * See the Android Training lesson <a href=
     * "http://developer.android.com/training/basics/fragments/communicating.html"
     * >Communicating with Other Fragments</a> for more information.
     */
    public interface OnFragmentInteractionListener {
        // TODO: Update argument type and name
        void onFragmentInteraction(Uri uri);
    }

    Element PrivacyPolicy(){
        String s = "<a href=\"https://icons8.com/icon/7967/Privacy\">Privacy and other flat icons</a>";
        Element privacypolicy = new Element();
        final String copyrights = String.format(getString(R.string.privacypolicy), Calendar.getInstance().get(Calendar.YEAR));
        privacypolicy.setTitle(copyrights);
       // privacypolicy.setIconDrawable(R.drawable.privacy);
        privacypolicy.setGravity(Gravity.CENTER);
        privacypolicy.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(Intent.ACTION_VIEW, Uri.parse(url)));

            }
        });
        return privacypolicy;
    }

    void simulateDayNight(int currentSetting) {
        final int DAY = 0;
        final int NIGHT = 1;
        final int FOLLOW_SYSTEM = 3;

        int currentNightMode = getResources().getConfiguration().uiMode
                & Configuration.UI_MODE_NIGHT_MASK;
        if (currentSetting == DAY && currentNightMode != Configuration.UI_MODE_NIGHT_NO) {
            AppCompatDelegate.setDefaultNightMode(
                    AppCompatDelegate.MODE_NIGHT_NO);
        } else if (currentSetting == NIGHT && currentNightMode != Configuration.UI_MODE_NIGHT_YES) {
            AppCompatDelegate.setDefaultNightMode(
                    AppCompatDelegate.MODE_NIGHT_YES);
        } else if (currentSetting == FOLLOW_SYSTEM) {
            AppCompatDelegate.setDefaultNightMode(
                    AppCompatDelegate.MODE_NIGHT_FOLLOW_SYSTEM);
        }
    }

    public void ShowAds(InterstitialAd interstitial1) {
        interstitial1 = new InterstitialAd(getContext());
        interstitial1.setAdUnitId("ca-app-pub-1361359682897035/3987738327");

        interstitial1.loadAd(new AdRequest.Builder().build());

        final InterstitialAd finalInterstitial = interstitial1;
        interstitial1.setAdListener(new AdListener() {
            @Override
            public void onAdLoaded() {
                if (finalInterstitial.isLoaded()) {
                    finalInterstitial.show();
                }
            }
        });


    }
}
