package infinitives.whatsappgroups;

import android.content.ActivityNotFoundException;
import android.content.Context;
import android.content.Intent;
import android.graphics.Color;
import android.net.Uri;
import android.os.Bundle;
import android.support.design.widget.CoordinatorLayout;
import android.support.design.widget.Snackbar;
import android.support.v4.app.Fragment;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.android.volley.AuthFailureError;
import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.rengwuxian.materialedittext.MaterialEditText;
import com.rey.material.widget.SnackBar;
import com.shashank.sony.fancydialoglib.Animation;
import com.shashank.sony.fancydialoglib.FancyAlertDialog;
import com.shashank.sony.fancydialoglib.FancyAlertDialogListener;
import com.shashank.sony.fancydialoglib.Icon;

import java.util.Arrays;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.regex.Matcher;
import java.util.regex.Pattern;


/**
 * A simple {@link Fragment} subclass.
 * Activities that contain this fragment must implement the
 * {@link OnFragmentInteractionListener} interface
 * to handle interaction events.
 * Use the {@link addgroups#newInstance} factory method to
 * create an instance of this fragment.
 */
public class addgroups extends Fragment implements com.rey.material.widget.Spinner.OnItemSelectedListener {
    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";
    private static final String Url = "https://script.google.com/macros/s/AKfycbzSZPMS6MncxledrCTtskycxa6dO-pN8G2JKK8RT-wXaL1qSxM/exec";
    private String GroupsTitles[] = {"Groups List:", "ADULTS 18+", "LOVE", "SPORTS", "GAMING", "HACKING", "NEWS", "PROMOTIONS", "NATURE", "PROGRAMMING", "NON-VEG JOKES", "VEG JOKES"};
    private List<String> Grouplist;
    private FancyAlertDialog fancyAlertDialog;
    Snackbar snackbar;
    CoordinatorLayout coordinatorLayout;


    com.rey.material.widget.Spinner spinner;
    MaterialEditText grpname, link;
    Button send;
    TextView textView;
    private static String ID, lin, gname, selected;
    int check = 0;

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;

    private OnFragmentInteractionListener mListener;

    public addgroups() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment addgroups.
     */
    // TODO: Rename and change types and number of parameters
    public static addgroups newInstance(String param1, String param2) {
        addgroups fragment = new addgroups();
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
        View view = inflater.inflate(R.layout.fragment_addgroups, container, false);

        spinner = view.findViewById(R.id.spinner);
        grpname = view.findViewById(R.id.gname);
        link = view.findViewById(R.id.linkn);
        send = view.findViewById(R.id.send);
        coordinatorLayout = view.findViewById(R.id.coordinator);


        spinner();

        send.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                gname = grpname.getText().toString();
                lin = link.getText().toString();
                Pattern p = Pattern.compile("https?\\:\\/\\/(www\\.)?chat(\\.)?whatsapp(\\.com)?\\/.*(\\?v=|\\/v\\/)?[a-zA-Z0-9_\\-]+$");
                Matcher matcher = p.matcher(lin);

                if (TextUtils.isEmpty(gname) && TextUtils.isEmpty(lin)) {
                    grpname.setError("Enter The Group Name");
                    link.setError("Enter The Group Link");
                } else if (check == 1) {
                    Toast.makeText(getContext(), "Select Category", Toast.LENGTH_SHORT).show();
                    check = 0;
                } else if (matcher.matches()) {
                    sendData();
                } else {
                    snackbar = Snackbar.make(coordinatorLayout, "", Snackbar.LENGTH_INDEFINITE).
                            setAction("OK", new View.OnClickListener() {
                                @Override
                                public void onClick(View v) {

                                }
                            }).setActionTextColor(Color.WHITE);
                    View sv = snackbar.getView();
                    TextView textView = sv.findViewById(android.support.design.R.id.snackbar_text);
                    sv.setBackgroundColor(Color.parseColor("#69d262"));
                    textView.setText("Link Is Not Valid Check link and Try Again");
                    textView.setTextSize(14);
                    snackbar.show();

                }


            }
        });


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
    public void onItemSelected(com.rey.material.widget.Spinner parent, View view, int position, long id) {
        selected = parent.getSelectedItem().toString();

        if (selected.equals("")) {
            check = 1;
        } else if (selected.equals("ADULTS 18+")) {
            ID = "1V93qh6Hyi0_FzEfNQysb5nZftGz3JElPE7kXStqsTdE";

        } else if (selected.equals("LOVE")) {
            ID = "1NGlEopdvI6UROB9fZan4hgpR55pX8hrO0X8R1FIIE9I";

        } else if (selected.equals("SPORTS")) {
            ID = "103h18CBYA8Qwp9k0zst4cASnA-SGdfEAv0juDubNmkM";

        } else if (selected.equals("GAMING")) {
            ID = "1a9IHSIueKUgbi4OQm7jnctBUoVhVfgT9FM_Hb8xo_hY";

        } else if (selected.equals("HACKING")) {
            ID = "16bLxQ7rPbfUZ2wJIZ4LOJ6pHFRRoeCoP8X1rJSDg_hs";

        } else if (selected.equals("NEWS")) {
            ID = "1eRhFbUsRH8nURu0a4rwciMIm6_Pm3P3luHmNOcXCTrQ";

        } else if (selected.equals("PROMOTIONS")) {
            ID = "1jcD2IvW0tAP1Gqv9cvNS-TZwM98pc2xoyhBUQsO-49Q";

        } else if (selected.equals("NATURE")) {
            ID = "1JGECV8E0tkxyPIGYYask05s51hi7KyNwgcGSNPOv8D8";

        } else if (selected.equals("PROGRAMMING")) {
            ID = "1m5LsWo2Invv3okb869aRDTW4GMUxdyazWmvIoW35S7o";

        } else if (selected.equals("NON-VEG JOKES")) {
            ID = "1tUzyJrXrnB5Z8LoyZ3mJEh5Odod4eerbhD9M415wZtg";

        } else if (selected.equals("VEG JOKES")) {
            ID = "1qtwmrAfOmGFnLCl-LGoJwaP62eYotDNmAn3Y4VwtGZ4";

        }


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

    public void sendData() {

        RequestQueue requestQueue = Volley.newRequestQueue(getContext());
        StringRequest stringRequest = new StringRequest(Request.Method.POST, Url, new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {

                fancyAlertDialog = new FancyAlertDialog.Builder(getActivity()).setTitle("Success")
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
                                            startActivity(marketIntent);
                                        } catch (ActivityNotFoundException e) {
                                            Uri marketUri = Uri.parse("https://play.google.com/store/apps/details?id=" + "infinitives.whatsappgroups");
                                            Intent marketIntent = new Intent(Intent.ACTION_VIEW, marketUri);
                                            startActivity(marketIntent);
                                        }
                                    }
                                }).
                                setBackgroundColor(Color.parseColor("#69d262")).build();
                

            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {

            }
        }) {
            @Override
            protected Map<String, String> getParams() throws AuthFailureError {
                Map<String, String> map = new HashMap<String, String>();
                map.put("name", gname);
                map.put("link", lin);
                map.put("id", ID);
                return map;
            }
        };
        requestQueue.add(stringRequest);
    }

    public void spinner() {

        Grouplist = new LinkedList<>(Arrays.asList(GroupsTitles));
        ArrayAdapter<String> adapter = new ArrayAdapter<String>(getContext(),
                android.R.layout.simple_spinner_item, Grouplist);
        spinner.setAdapter(adapter);
        spinner.setOnItemSelectedListener(this);

    }
}

