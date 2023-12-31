package com.example.andao_apk.Categorie;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.andao_apk.Multimedia.MultimediaAdapter;
import com.example.andao_apk.Multimedia.MultimediaClass;
import com.example.andao_apk.R;

import java.util.ArrayList;
import java.util.List;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link CategorieFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class CategorieFragment extends Fragment {

    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;

    List<CategorieClass> list;

    public CategorieFragment() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment CategorieFragment.
     */
    // TODO: Rename and change types and number of parameters
    public static CategorieFragment newInstance(String param1, String param2) {
        CategorieFragment fragment = new CategorieFragment();
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
        return inflater.inflate(R.layout.fragment_categorie, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        datainitialise();
        RecyclerView recyclerView=view.findViewById(R.id.recyclercategorie);
        recyclerView.setLayoutManager(new LinearLayoutManager(getContext()));
        recyclerView.setHasFixedSize(true);
        CategorieAdapter adapter=new CategorieAdapter(getContext(),list);
        recyclerView.setLayoutManager(new GridLayoutManager(getContext(),2));
        recyclerView.setAdapter(adapter);
    }

    private void datainitialise() {
        list=new ArrayList<>();
        list.add(new CategorieClass("64c686179f266a02ec39a9d9",R.drawable.destination,"Destination incoutournables"));
        list.add(new CategorieClass("64c686179f266a02ec39a9da",R.drawable.gastronomie,"Gastronomie malgache"));
        list.add(new CategorieClass("64c686179f266a02ec39a9db",R.drawable.hotel_activite,"Hotels et Activités"));
        list.add(new CategorieClass("64c686179f266a02ec39a9dc",R.drawable.culture,"Cultures et traditions"));
        list.add(new CategorieClass("64c686179f266a02ec39a9dd",R.drawable.festival,"Festivals et événements culturels"));
        list.add(new CategorieClass("64c686179f266a02ec39a9de",R.drawable.fauneflore,"Faunes et Flore"));
    }
}