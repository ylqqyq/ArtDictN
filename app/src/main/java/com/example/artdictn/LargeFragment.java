package com.example.artdictn;

import android.graphics.Bitmap;
import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link LargeFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class LargeFragment extends Fragment implements NetworkingService.networkingListener{

    ImageView img_large;

//    // TODO: Rename parameter arguments, choose names that match
//    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
//    private static final String ARG_PARAM1 = "param1";
//    private static final String ARG_PARAM2 = "param2";

    // TODO: Rename and change types of parameters
    private String img_url;


    public LargeFragment() {
        // Required empty public constructor
    }


    public static LargeFragment newInstance(String img_url) {
        LargeFragment fragment = new LargeFragment();
        Bundle args = new Bundle();
        args.putString("img_url", img_url);
        fragment.setArguments(args);
        return fragment;
    }

//    @Override
//    public void onCreate(Bundle savedInstanceState) {
//        super.onCreate(savedInstanceState);
//        if (getArguments() != null) {
//            mParam1 = getArguments().getString(ARG_PARAM1);
//            mParam2 = getArguments().getString(ARG_PARAM2);
//        }
//    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View v = inflater.inflate((R.layout.fragment_large),container,false);
         img_large = v.findViewById(R.id.image_large);

        // Inflate the layout for this fragment
        return v;
    }

    @Override
    public void APIlistener(String jsonString) {

    }

    @Override
    public void APIImgListener(Bitmap image) {
        img_large.setImageBitmap(image);

    }
}