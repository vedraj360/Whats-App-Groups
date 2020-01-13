package infinitives.whatsgroups;

import android.app.ActivityManager;
import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.os.Handler;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.Toast;

import com.google.android.gms.ads.AdListener;
import com.google.android.gms.ads.AdRequest;
import com.google.android.gms.ads.InterstitialAd;

import java.util.ArrayList;


/**
 * A simple {@link Fragment} subclass.
 * Activities that contain this fragment must implement the
 * {@link OnFragmentInteractionListener} interface
 * to handle interaction events.
 * Use the {@link Groups#newInstance} factory method to
 * create an instance of this fragment.
 */
public class Groups extends Fragment {
    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = Groups.class.getSimpleName();
    private static final String ARG_PARAM2 = "https://script.google.com/macros/s/AKfycbxOLElujQcy1-ZUer1KgEvK16gkTLUqYftApjNCM_IRTL3HSuDk/exec?id=16bLxQ7rPbfUZ2wJIZ4LOJ6pHFRRoeCoP8X1rJSDg_hs&sheet=Sheet1";
    private RecyclerView recyclerView;
    String links[] = {
            "https://script.google.com/macros/s/AKfycbxOLElujQcy1-ZUer1KgEvK16gkTLUqYftApjNCM_IRTL3HSuDk/exec?id=1V93qh6Hyi0_FzEfNQysb5nZftGz3JElPE7kXStqsTdE&sheet=Sheet1",
            "https://script.google.com/macros/s/AKfycbxOLElujQcy1-ZUer1KgEvK16gkTLUqYftApjNCM_IRTL3HSuDk/exec?id=1NGlEopdvI6UROB9fZan4hgpR55pX8hrO0X8R1FIIE9I&sheet=Sheet1",
            "https://script.google.com/macros/s/AKfycbxOLElujQcy1-ZUer1KgEvK16gkTLUqYftApjNCM_IRTL3HSuDk/exec?id=103h18CBYA8Qwp9k0zst4cASnA-SGdfEAv0juDubNmkM&sheet=Sheet1",
            "https://script.google.com/macros/s/AKfycbxOLElujQcy1-ZUer1KgEvK16gkTLUqYftApjNCM_IRTL3HSuDk/exec?id=1a9IHSIueKUgbi4OQm7jnctBUoVhVfgT9FM_Hb8xo_hY&sheet=Sheet1",
            "https://script.google.com/macros/s/AKfycbxOLElujQcy1-ZUer1KgEvK16gkTLUqYftApjNCM_IRTL3HSuDk/exec?id=16bLxQ7rPbfUZ2wJIZ4LOJ6pHFRRoeCoP8X1rJSDg_hs&sheet=Sheet1",
            "https://script.google.com/macros/s/AKfycbxOLElujQcy1-ZUer1KgEvK16gkTLUqYftApjNCM_IRTL3HSuDk/exec?id=1eRhFbUsRH8nURu0a4rwciMIm6_Pm3P3luHmNOcXCTrQ&sheet=Sheet1",
            "https://script.google.com/macros/s/AKfycbxOLElujQcy1-ZUer1KgEvK16gkTLUqYftApjNCM_IRTL3HSuDk/exec?id=1jcD2IvW0tAP1Gqv9cvNS-TZwM98pc2xoyhBUQsO-49Q&sheet=Sheet1",
            "https://script.google.com/macros/s/AKfycbxOLElujQcy1-ZUer1KgEvK16gkTLUqYftApjNCM_IRTL3HSuDk/exec?id=1JGECV8E0tkxyPIGYYask05s51hi7KyNwgcGSNPOv8D8&sheet=Sheet1",
            "https://script.google.com/macros/s/AKfycbxOLElujQcy1-ZUer1KgEvK16gkTLUqYftApjNCM_IRTL3HSuDk/exec?id=1m5LsWo2Invv3okb869aRDTW4GMUxdyazWmvIoW35S7o&sheet=Sheet1",
            "https://script.google.com/macros/s/AKfycbxOLElujQcy1-ZUer1KgEvK16gkTLUqYftApjNCM_IRTL3HSuDk/exec?id=1tUzyJrXrnB5Z8LoyZ3mJEh5Odod4eerbhD9M415wZtg&sheet=Sheet1",
            "https://script.google.com/macros/s/AKfycbxOLElujQcy1-ZUer1KgEvK16gkTLUqYftApjNCM_IRTL3HSuDk/exec?id=1qtwmrAfOmGFnLCl-LGoJwaP62eYotDNmAn3Y4VwtGZ4&sheet=Sheet1"};
    int Icons[] = {R.drawable.et, R.drawable.heart, R.drawable.running, R.drawable.vg, R.drawable.hacker,
            R.drawable.newspaper, R.drawable.megaphone, R.drawable.leaves, R.drawable.pro,
            R.drawable.njoke, R.drawable.joke};

    private InterstitialAd interstitialAd1;
    private static Boolean valid = true;
    private static int count = 0;
    private String GroupsTitles[] = {"ADULTS 18+", "LOVE", "SPORTS", "GAMING", "HACKING", "NEWS", "PROMOTIONS", "NATURE", "PROGRAMMING", "NON-VEG JOKES", "VEG JOKES"};
    ArrayList<Grpclass> grp = new ArrayList<>();
    private RecyclerView recycler;

    private Handler handler;
    private Runnable runnable;

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;

    private OnFragmentInteractionListener mListener;

    public Groups() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment Groups.
     */
    // TODO: Rename and change types and number of parameters
    public static Groups newInstance(String param1, String param2) {
        Groups fragment = new Groups();
        Bundle args = new Bundle();
        // args.putString(ARG_PARAM1, param1);
        // args.putString(ARG_PARAM2, param2);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
     /*   if (getArguments() != null) {
            mParam1 = getArguments().getString(ARG_PARAM1);
            mParam2 = getArguments().getString(ARG_PARAM2);
        }
*/
    }

    @Override
    public View onCreateView(final LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_groups, container, false);
        // listView = view.findViewById(R.id.listview);
        recycler = view.findViewById(R.id.recycleView);
        final Customlistview customlistview = new Customlistview(grp, getActivity(), Icons, links);
        //ShowAds(interstitialAd1);
        grp.clear();
        customlistview.notifyDataSetChanged();

        GridLayoutManager gridLayoutManager = new GridLayoutManager(getContext(), 2);
        gridLayoutManager.setSpanSizeLookup(new GridLayoutManager.SpanSizeLookup() {
            @Override
            public int getSpanSize(int position) {
                return customlistview.getItemViewType(position) == 2 ? 2 : 1;
            }
        });
        recycler.setLayoutManager(gridLayoutManager);
        recycler.setAdapter(customlistview);

        for (int i = 0; i < GroupsTitles.length; i++) {
            grp.add(new Grpclass(GroupsTitles[i]));
        }

        handler = new Handler();
        runnable = new Runnable() {
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

        return view;

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
        handler.postDelayed(runnable,60000);
        valid = true;
        Log.d("Act", "onResume");
    }

    @Override
    public void onPause() {
        super.onPause();
        valid = false;
        count = 0;
        handler.handleMessage(null);
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
