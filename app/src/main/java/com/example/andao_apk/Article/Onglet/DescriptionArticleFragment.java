package com.example.andao_apk.Article.Onglet;

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
import android.widget.TextView;

import com.example.andao_apk.Article.ArticleAdapter;
import com.example.andao_apk.R;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link DescriptionArticleFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class DescriptionArticleFragment extends Fragment {

    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;

    private String site;

    private String description;

    private String localisation;

    TextView descriptionv;
    TextView local;
    TextView siteview;


    public DescriptionArticleFragment(String site,String description,String localisation) {
        this.site = site;
        this.description = description;
        this.localisation = localisation;
    }

    public DescriptionArticleFragment(){}

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment DescriptionArticleFragment.
     */
    // TODO: Rename and change types and number of parameters
    public static DescriptionArticleFragment newInstance(String param1, String param2) {
        DescriptionArticleFragment fragment = new DescriptionArticleFragment();
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
        View rootView = inflater.inflate(R.layout.fragment_description_article, container, false);
        descriptionv = rootView.findViewById(R.id.fiche_description);
        local = rootView.findViewById(R.id.fiche_localisation);
        siteview = rootView.findViewById(R.id.fiche_site);
        siteview.setText(site);
        descriptionv.setText(description);
        local.setText(localisation);
        return rootView;
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {

       /* super.onViewCreated(view, savedInstanceState);
        RecyclerView recyclerView=view.findViewById(R.id.recyclerlistearticle);
        recyclerView.setLayoutManager(new LinearLayoutManager(getContext()));
        recyclerView.setHasFixedSize(true);
        ArticleAdapter adapter=new ArticleAdapter(getContext(),list);
        recyclerView.setLayoutManager(new GridLayoutManager(getContext(),1));
        recyclerView.setAdapter(adapter);*/
    }
}