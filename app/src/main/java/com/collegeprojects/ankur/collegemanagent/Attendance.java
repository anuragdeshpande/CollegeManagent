package com.collegeprojects.ankur.collegemanagent;

import android.app.ProgressDialog;
import android.content.Context;
import android.graphics.Color;
import android.graphics.PorterDuff;
import android.net.Uri;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.TableLayout;
import android.widget.TableRow;
import android.widget.TextView;

import org.apache.http.client.HttpClient;
import org.apache.http.impl.client.DefaultHttpClient;


/**
 * A simple {@link Fragment} subclass.
 * Activities that contain this fragment must implement the
 * {@link Attendance.OnFragmentInteractionListener} interface
 * to handle interaction events.
 * Use the {@link Attendance#newInstance} factory method to
 * create an instance of this fragment.
 */
public class Attendance extends Fragment {
    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    Context context;

    private OnFragmentInteractionListener mListener;

    public Attendance() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment Attendance.
     */
    // TODO: Rename and change types and number of parameters
    public static Attendance newInstance(String param1, String param2) {
        Attendance fragment = new Attendance();
        Bundle args = new Bundle();
        args.putString(ARG_PARAM1, param1);
        args.putString(ARG_PARAM2, param2);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        String mParam1;
        String mParam2;
        if (getArguments() != null) {
            mParam1 = getArguments().getString(ARG_PARAM1);
            mParam2 = getArguments().getString(ARG_PARAM2);
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_attendance, container, false);
        Context context = view.getContext();
        TextView className = view.findViewById(R.id.id_text_attendace_title);
        className.setText(getString(R.string.constant_mark_attendance).concat(" ").concat(getString(R.string.className)));
        final CheckBox markAll = view.findViewById(R.id.id_checkbox_markfullAttendance);
        TableLayout tableLayout = view.findViewById(R.id.id_tablelayout_attendanceList);
        int buttonCount = 0;
        TableRow row = new TableRow(context);
        for(int i=501 ; i<=565 ; i++){
            if( buttonCount == 0 ) {
                row = new TableRow(context);
                row.setGravity(Gravity.CENTER_HORIZONTAL);
            }
            String buttonName = i + "";
            Button button = new Button(context);
            buttonCount++;
            button.setTag("unClicked");
            button.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    Button clickedButton = view.findViewById(view.getId());
                    String tag = (String) clickedButton.getTag();
                    if(tag.equals("unClicked")){
                        clickedButton.getBackground().setColorFilter(getResources().getColor(R.color.colorPrimary), PorterDuff.Mode.MULTIPLY);
                        clickedButton.setTextColor(Color.WHITE);
                        clickedButton.setTag("Clicked");
                    } else {
                        clickedButton.getBackground().clearColorFilter();
                        clickedButton.setTextColor(Color.BLACK);
                        clickedButton.setTag("unClicked");
                    }


                }
            });
            button.setText(buttonName);
            button.setId(i);
            row.addView(button);
            if(buttonCount == 4 ){
                buttonCount = 0;
                tableLayout.addView(row);
            }
        }


        context = view.getContext();
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

    private class RestOperation extends AsyncTask<String, Void, Void>{

        final HttpClient httpClient = new DefaultHttpClient();
        String content;
        String error;
        ProgressDialog progressDialog = new ProgressDialog(context);
        String data;


        @Override
        protected Void doInBackground(String... strings) {
            return null;
        }
    }

}
