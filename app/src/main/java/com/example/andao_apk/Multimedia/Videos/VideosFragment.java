package com.example.andao_apk.Multimedia.Videos;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ProgressBar;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.example.andao_apk.Constante.Constante;
import com.example.andao_apk.R;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.List;
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

    private Vector<VideosClass> liste;
    private ProgressBar progressBar;
    private RequestQueue requestQueue;

    public VideosFragment(List<VideosClass> list) {
        // Required empty public constructor
        this.liste = new Vector<>();
        for(VideosClass video : list){
            liste.add(video);
        }
    }

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
        progressBar = view.findViewById(R.id.progressBar_videos);
        VideosAdapter adapter=new VideosAdapter(liste,getContext());
        recyclerView=view.findViewById(R.id.recyclervideos);
        recyclerView.setLayoutManager(new LinearLayoutManager(getContext()));
        recyclerView.setHasFixedSize(true);
        if(liste == null || liste.size() == 0 ){
            datainitialise(recyclerView);
        }
        else{
            recyclerView.setAdapter(adapter);
            progressBar.setVisibility(View.GONE);
        }
    }


    private void datainitialise(RecyclerView recyclerView) {
        liste = new Vector<>();
        requestQueue = Volley.newRequestQueue(getContext());
        String url = Constante.api_url + "Categorie/multimedia/videos/0/180";
        StringRequest stringRequest = new StringRequest(Request.Method.GET, url, new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {
                try {
                    JSONObject jsonResponse = new JSONObject(response);
                    int status = jsonResponse.getInt("status");
                    if (status == 200) {
                        JSONObject dataObject = jsonResponse.getJSONObject("data");
                        JSONArray multimediaArray = dataObject.getJSONArray("multimedia");
                        for (int i = 0; i < multimediaArray.length(); i++) {
                            JSONObject multimediaItem = multimediaArray.getJSONObject(i);
                            JSONObject articleObject = multimediaItem.getJSONObject("article");
                            JSONArray imagesArray = articleObject.getJSONArray("videos");
                            for (int j=0;j<imagesArray.length();j++) {
                                JSONObject imageObject = imagesArray.getJSONObject(j);
                                VideosClass multimedia = new VideosClass();
                                multimedia.setLibelle(articleObject.getString("libelle"));
                                multimedia.setLien(imageObject.getString("lien"));
                                liste.add(multimedia);
                            }
                        }
                        System.out.println(liste.size());
                        VideosAdapter adapter = new VideosAdapter(liste,getContext());
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