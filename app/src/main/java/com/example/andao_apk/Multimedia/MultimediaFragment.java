package com.example.andao_apk.Multimedia;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.andao_apk.R;

import java.util.ArrayList;
import java.util.List;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link MultimediaFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class MultimediaFragment extends Fragment {

    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;

    List<MultimediaClass> list;

    public MultimediaFragment() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment MultimediaFragment.
     */
    // TODO: Rename and change types and number of parameters
    public static MultimediaFragment newInstance(String param1, String param2) {
        MultimediaFragment fragment = new MultimediaFragment();
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
        return inflater.inflate(R.layout.fragment_multimedia, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        datainitialise();
        RecyclerView recyclerView=view.findViewById(R.id.recyclermultimedia);
        recyclerView.setLayoutManager(new LinearLayoutManager(getContext()));
        recyclerView.setHasFixedSize(true);
        MultimediaAdapter adapter=new MultimediaAdapter(getContext(),list);
        recyclerView.setLayoutManager(new GridLayoutManager(getContext(),3));
        recyclerView.setAdapter(adapter);
    }
    private void datainitialise() {
        list=new ArrayList<>();
        list.add(new MultimediaClass("https//vidyVarotra.mg","https://staticvv-c398.kxcdn.com/pubVV/alu%20261%20-%20458.jpg"));
        list.add(new MultimediaClass("https//vidyVarotra.mg","https://staticvv-c398.kxcdn.com/IMGSITE/image/site/original/20230621125702-1.jpg"));
        list.add(new MultimediaClass("https//vidyVarotra.mg","https://staticvv-c398.kxcdn.com/pubVV/alu%20261%20-%20458.jpg"));
        list.add(new MultimediaClass("https//vidyVarotra.mg","https://staticvv-c398.kxcdn.com/IMGSITE/image/site/original/20230621125702-1.jpg"));
        list.add(new MultimediaClass("https//vidyVarotra.mg","https://staticvv-c398.kxcdn.com/IMGSITE/image/site/original/20230718124115-1.jpg"));
        list.add(new MultimediaClass("https//vidyVarotra.mg","https://staticvv-c398.kxcdn.com/pubVV/alu%20261%20-%20458.jpg"));
        list.add(new MultimediaClass("https//vidyVarotra.mg","https://staticvv-c398.kxcdn.com/IMGSITE/image/site/original/20230621125702-1.jpg"));
        list.add(new MultimediaClass("https//vidyVarotra.mg","https://staticvv-c398.kxcdn.com/pubVV/alu%20261%20-%20458.jpg"));
        list.add(new MultimediaClass("https//vidyVarotra.mg","https://staticvv-c398.kxcdn.com/pubVV/alu%20261%20-%20458.jpg"));
        list.add(new MultimediaClass("https//vidyVarotra.mg","https://staticvv-c398.kxcdn.com/IMGSITE/image/site/original/20230718124115-1.jpg"));
        list.add(new MultimediaClass("https//vidyVarotra.mg","https://staticvv-c398.kxcdn.com/IMGSITE/image/site/original/20230718124115-1.jpg"));
        list.add(new MultimediaClass("https//vidyVarotra.mg","https://staticvv-c398.kxcdn.com/IMGSITE/image/site/original/20230621125702-1.jpg"));
        list.add(new MultimediaClass("https//vidyVarotra.mg","https://staticvv-c398.kxcdn.com/IMGSITE/image/site/original/20230718124115-1.jpg"));
        list.add(new MultimediaClass("https//vidyVarotra.mg","https://staticvv-c398.kxcdn.com/IMGSITE/image/site/original/20230621125702-1.jpg"));
        list.add(new MultimediaClass("https//vidyVarotra.mg","https://staticvv-c398.kxcdn.com/IMGSITE/image/site/original/20230718124115-1.jpg"));
        list.add(new MultimediaClass("https//vidyVarotra.mg","https://staticvv-c398.kxcdn.com/IMGSITE/image/site/original/20230718124115-1.jpg"));
        list.add(new MultimediaClass("https//vidyVarotra.mg","https://staticvv-c398.kxcdn.com/IMGSITE/image/site/original/20230621125702-1.jpg"));


    }
}