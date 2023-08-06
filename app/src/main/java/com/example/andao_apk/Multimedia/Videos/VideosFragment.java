package com.example.andao_apk.Multimedia.Videos;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.andao_apk.R;

import java.util.Vector;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link VideosFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class VideosFragment extends Fragment {

    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;

    private RecyclerView recyclerView;

    private Vector<VideosClass> liste=new Vector<>();

    public VideosFragment() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment VideosFragment.
     */
    // TODO: Rename and change types and number of parameters
    public static VideosFragment newInstance(String param1, String param2) {
        VideosFragment fragment = new VideosFragment();
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
        return inflater.inflate(R.layout.fragment_videos, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        datainitialise();
        VideosAdapter adapter=new VideosAdapter(liste,getContext());
        recyclerView=view.findViewById(R.id.recyclervideos);
        recyclerView.setLayoutManager(new LinearLayoutManager(getContext()));
        recyclerView.setHasFixedSize(true);
        recyclerView.setAdapter(adapter);
    }

    private void datainitialise() {
        liste.add(new VideosClass("<iframe width=\"400\" height=\"290\" src=\"https://www.youtube.com/embed/kt2D7xl06mk\" title=\"VIDEO 10s\" frameborder=\"0\" allow=\"accelerometer; autoplay; clipboard-write; encrypted-media; gyroscope; picture-in-picture; web-share\" allowfullscreen></iframe>"));
        liste.add(new VideosClass("<iframe width=\"400\" height=\"290\" src=\"https://www.youtube.com/embed/6dcsXzngGhU\" title=\"LA MARTINIQUE : 10 CHOSES QUI LA RENDENT UNIQUE !\" frameborder=\"0\" allow=\"accelerometer; autoplay; clipboard-write; encrypted-media; gyroscope; picture-in-picture; web-share\" allowfullscreen></iframe>"));
        liste.add(new VideosClass("<iframe width=\"400\" height=\"290\" src=\"https://www.youtube.com/embed/kt2D7xl06mk\" title=\"VIDEO 10s\" frameborder=\"0\" allow=\"accelerometer; autoplay; clipboard-write; encrypted-media; gyroscope; picture-in-picture; web-share\" allowfullscreen></iframe>"));
        liste.add(new VideosClass("<iframe width=\"400\" height=\"290\" src=\"https://www.youtube.com/embed/6dcsXzngGhU\" title=\"LA MARTINIQUE : 10 CHOSES QUI LA RENDENT UNIQUE !\" frameborder=\"0\" allow=\"accelerometer; autoplay; clipboard-write; encrypted-media; gyroscope; picture-in-picture; web-share\" allowfullscreen></iframe>"));
        liste.add(new VideosClass("<iframe width=\"400\" height=\"290\" src=\"https://www.youtube.com/embed/kt2D7xl06mk\" title=\"VIDEO 10s\" frameborder=\"0\" allow=\"accelerometer; autoplay; clipboard-write; encrypted-media; gyroscope; picture-in-picture; web-share\" allowfullscreen></iframe>"));
        liste.add(new VideosClass("<iframe width=\"400\" height=\"290\" src=\"https://www.youtube.com/embed/6dcsXzngGhU\" title=\"LA MARTINIQUE : 10 CHOSES QUI LA RENDENT UNIQUE !\" frameborder=\"0\" allow=\"accelerometer; autoplay; clipboard-write; encrypted-media; gyroscope; picture-in-picture; web-share\" allowfullscreen></iframe>"));
    }
}