package com.example.andao_apk.Partage;

import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ProgressBar;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.example.andao_apk.Constante.Constante;
import com.example.andao_apk.Constante.Session;
import com.example.andao_apk.R;
import com.example.andao_apk.Utilisateur.LoginActivity;
import com.example.andao_apk.Utilisateur.UserShareActivity;
import com.google.android.material.floatingactionbutton.FloatingActionButton;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.text.ParseException;
import java.text.SimpleDateFormat;
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

    private ProgressBar progressBar;
    private RequestQueue requestQueue;


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
        progressBar = view.findViewById(R.id.progressBar_usershare);
        RecyclerView recyclerView = view.findViewById(R.id.recyclerlistepartage);
        recyclerView.setLayoutManager(new LinearLayoutManager(getContext()));
        recyclerView.setHasFixedSize(true);

        list = new ArrayList<>();

        datainitialise(recyclerView);

        PartageAdapter adapter = new PartageAdapter(getContext(), list);
        recyclerView.setAdapter(adapter);

        FloatingActionButton fab = view.findViewById(R.id.floatingActionButton);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String session=Session.getInstance().getMyValue();
                System.out.println("Sess : "+session);
                if(session!=null && !session.isEmpty()){
                    System.out.println("Session partage 1 : "+session);
                    Intent intent = new Intent(getContext(), UserShareActivity.class);
                    startActivity(intent);

                }else{
                    System.out.println("Session partage 2");
                    Intent intent = new Intent(getContext(), LoginActivity.class);
                    startActivity(intent);
                }
            }
        });
    }


    private void datainitialise(RecyclerView recyclerView) {
        list = new ArrayList<>();
        requestQueue = Volley.newRequestQueue(getContext());
        String url = Constante.api_url + "Utilisateur/recherchePartage/0/180";
        StringRequest stringRequest = new StringRequest(Request.Method.GET, url, new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {
                try {
                    JSONObject jsonResponse = new JSONObject(response);
                    int status = jsonResponse.getInt("status");
                    if (status == 200) {
                        JSONObject dataObject = jsonResponse.getJSONObject("data");
                        JSONArray partageArray = dataObject.getJSONArray("partage");

                        for (int i = 0; i < partageArray.length(); i++) {
                            JSONObject partageItem = partageArray.getJSONObject(i);
                            JSONObject partageObject = partageItem.getJSONObject("partage");

                            String description = partageObject.getString("description");
                            String libelle = partageObject.getString("libelle");
                            String image = partageObject.getString("image");
                            int note = partageObject.getInt("note");
                            System.out.println("reo ny libelle = "+libelle);
                            String localisation = partageObject.getString("localisation");
                            String datePublication = partageObject.getString("date_publication");
                            String id = partageObject.getString("_id");

                            PartageClass partageClass = new PartageClass();
                            partageClass.setNom(partageItem.getString("nom"));
                            partageClass.setSexe(partageItem.getInt("sexe"));
                            partageClass.setPrenom(partageItem.getString("prenom"));
                            partageClass.setDescription(description);
                            partageClass.setLibelle(libelle);
                            partageClass.setImage(image);
                            partageClass.setNote(note);
                            partageClass.setLocalisation(localisation);
                            String pattern = "yyyy-MM-dd'T'HH:mm:ss.SSS'Z'";

                            SimpleDateFormat dateFormat = new SimpleDateFormat(pattern);
                            try {
                                Date date = dateFormat.parse(datePublication);
                                partageClass.setDatePublication(date);
                            } catch (ParseException e) {
                                e.printStackTrace();
                            }
                            partageClass.set_id(id);
                            list.add(partageClass);
                        }
                        System.out.println(list.size());
                        PartageAdapter adapter = new PartageAdapter(getContext(), list);
                        recyclerView.setLayoutManager(new GridLayoutManager(getContext(),1));
                        recyclerView.setAdapter(adapter);

                        progressBar.setVisibility(View.GONE);
                    }
                } catch (JSONException e) {
                    e.printStackTrace();
                }
            }
        },
        new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                progressBar.setVisibility(View.GONE);
            }
        });
        requestQueue.add(stringRequest);
    }
}