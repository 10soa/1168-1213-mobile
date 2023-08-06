package com.example.andao_apk.Multimedia;

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
import com.example.andao_apk.R;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

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

    private ProgressBar progressBar;
    private RequestQueue requestQueue;

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
        progressBar = view.findViewById(R.id.progressBar_photos);
        RecyclerView recyclerView = view.findViewById(R.id.recyclermultimedia);
        recyclerView.setLayoutManager(new LinearLayoutManager(getContext()));
        recyclerView.setHasFixedSize(true);
        datainitialise(recyclerView);
    }

    private void datainitialise(RecyclerView recyclerView) {
        list = new ArrayList<>();
        requestQueue = Volley.newRequestQueue(getContext());
        String url = Constante.api_url + "Categorie/multimedia/photos/0/180";
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
                            JSONArray imagesArray = articleObject.getJSONArray("images");
                            for (int j=0;j<imagesArray.length();j++) {
                                JSONObject imageObject = imagesArray.getJSONObject(j);
                                MultimediaClass multimedia = new MultimediaClass();
                                multimedia.setLien(articleObject.getString("libelle"));
                                multimedia.setImage(imageObject.getString("lien"));
                                multimedia.setId(imageObject.getString("_id"));
                                list.add(multimedia);
                            }
                        }
                        System.out.println(list.size());
                        MultimediaAdapter adapter = new MultimediaAdapter(getContext(), list);
                        recyclerView.setLayoutManager(new GridLayoutManager(getContext(), 3));
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
