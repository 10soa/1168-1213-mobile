package com.example.andao_apk.Article;

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

import com.example.andao_apk.Categorie.CategorieAdapter;
import com.example.andao_apk.Categorie.CategorieClass;
import com.example.andao_apk.R;

import java.util.ArrayList;
import java.util.List;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link ArticleListeFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class ArticleListeFragment extends Fragment {

    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;

    private List<ArticleClass> list;

    public ArticleListeFragment(){}

    public ArticleListeFragment(List<ArticleClass> list) {
        this.list = list;
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment ArticleListeFragment.
     */
    // TODO: Rename and change types and number of parameters
    public static ArticleListeFragment newInstance(String param1, String param2) {
        ArticleListeFragment fragment = new ArticleListeFragment();
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
        return inflater.inflate(R.layout.fragment_article_liste, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        RecyclerView recyclerView=view.findViewById(R.id.recyclerlistearticle);
        recyclerView.setLayoutManager(new LinearLayoutManager(getContext()));
        recyclerView.setHasFixedSize(true);
        ArticleAdapter adapter=new ArticleAdapter(getContext(),list);
        recyclerView.setLayoutManager(new GridLayoutManager(getContext(),1));
        recyclerView.setAdapter(adapter);
    }

    private void datainitialise() {
        list=new ArrayList<>();
        list.add(new ArticleClass("_id","Destination incontournable",R.drawable.destination_1,"lien","Parc national de l'Isalo","Une fenêtre ouverte sur la biodiversité du Sud Malgache","Situé dans le sud-ouest de Madagascar, ce parc national est réputé pour ses formations rocheuses spectaculaires, ses canyons profonds, ses piscines naturelles et sa faune variée.",R.drawable.photo));
        list.add(new ArticleClass("_id","Faunes et Flores",R.drawable.fauneflore,"lien","Parc national de Ranomafana","Ranomafana... ou un écrin de verdure","Un parc tropical luxuriant dans les Hautes Terres, abritant une grande variété de lémuriens, de caméléons et d'oiseaux",R.drawable.photos2jpg));
        list.add(new ArticleClass("_id","Destination incontournable",R.drawable.destination_1,"lien","Parc national de Ranomafana","Ranomafana... ou un écrin de verdure","Un parc tropical luxuriant dans les Hautes Terres, abritant une grande variété de lémuriens, de caméléons et d'oiseaux",R.drawable.photos2jpg));
        list.add(new ArticleClass("_id","Gastronomie malgache",R.drawable.gastronomie,"lien","Parc national de Ranomafana","Ranomafana... ou un écrin de verdure","Un parc tropical luxuriant dans les Hautes Terres, abritant une grande variété de lémuriens, de caméléons et d'oiseaux",R.drawable.photo));
        list.add(new ArticleClass("_id","Hotels et activités",R.drawable.hotel_activite,"lien","Parc national de Ranomafana",null,"Un parc tropical luxuriant dans les Hautes Terres, abritant une grande variété de lémuriens, de caméléons et d'oiseaux",R.drawable.photos2jpg));
    }
}