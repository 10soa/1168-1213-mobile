package com.example.andao_apk.Partage;

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

import com.example.andao_apk.R;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link PartageListeFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class PartageListeFragment extends Fragment {

    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;

    List<PartageClass> list;

    public PartageListeFragment() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment PartageListeFragment.
     */
    // TODO: Rename and change types and number of parameters
    public static PartageListeFragment newInstance(String param1, String param2) {
        PartageListeFragment fragment = new PartageListeFragment();
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
        return inflater.inflate(R.layout.fragment_partage_liste, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        datainitialise();
        RecyclerView recyclerView=view.findViewById(R.id.recyclerlistepartage);
        recyclerView.setLayoutManager(new LinearLayoutManager(getContext()));
        recyclerView.setHasFixedSize(true);
        PartageAdapter adapter=new PartageAdapter(getContext(),list);
        recyclerView.setLayoutManager(new GridLayoutManager(getContext(),1));
        recyclerView.setAdapter(adapter);
    }

    private void datainitialise() {
        list=new ArrayList<>();
        list.add(new PartageClass("id","RANDRIA","https://th.bing.com/th/id/OIP.lnPR__CRc0KmsAvI0G9a3gHaFj?w=260&h=195&c=7&r=0&o=5&dpr=1.3&pid=1.7","Vacance à Morondava",2,"Soa",new Date(2023,8,4),0));
        list.add(new PartageClass("id","RAKOTO","https://th.bing.com/th/id/OIP.99Jwf-TNa81XDL8IaKRDsQHaE7?w=277&h=184&c=7&r=0&o=5&dpr=1.3&pid=1.7","Vacance à Foulpointe",2,"Nick",new Date(2023,1,23),1));
        list.add(new PartageClass("id","RABARY","https://th.bing.com/th/id/OIP.GWm8HSPU9grj1caYQySbuAHaEf?w=297&h=180&c=7&r=0&o=5&dpr=1.3&pid=1.7","Vacance à Majunga",2,"Mathieu",new Date(2023,2,23),1));
    }
}