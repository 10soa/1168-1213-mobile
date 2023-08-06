package com.example.andao_apk.Notification_android;

import android.os.Bundle;
import android.util.Log;
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

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link NotificationFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class NotificationFragment extends Fragment {

    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;
    private ArrayList<NotificationClass> newsArrayList;
    private int[] image;
    private String[] titre;
    private String[] description;
    private RecyclerView recyclerView;

    private RequestQueue requestQueue;

    private ProgressBar progressBar;

    public NotificationFragment() {
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
    public static NotificationFragment newInstance(String param1, String param2) {
        NotificationFragment fragment = new NotificationFragment();
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
        return inflater.inflate(R.layout.fragment_notification, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        progressBar = view.findViewById(R.id.progressBar_notif);
        recyclerView = view.findViewById(R.id.recyclerview);

        progressBar.setVisibility(View.VISIBLE);
        datainitialise();
    }

    private void datainitialise() {
        newsArrayList = new ArrayList<>();
        requestQueue = Volley.newRequestQueue(getContext());
        String url = Constante.api_url + "Notification/allNotification";
        StringRequest stringRequest = new StringRequest(Request.Method.GET, url, new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {
                try {
                    JSONObject jsonResponse = new JSONObject(response);
                    int status = jsonResponse.getInt("status");
                    System.out.println(status);
                    if (status == 200) {
                        JSONArray dataArray = jsonResponse.getJSONArray("data");
                        System.out.println(dataArray.length());
                        for (int i = 0; i < dataArray.length(); i++) {
                            JSONObject item = dataArray.getJSONObject(i);
                            String date = item.getString("date");
                            NotificationClass liste = new NotificationClass();
                            liste.setId(item.getString("_id"));
                            liste.setArticle_id(item.getString("article_id"));
                            liste.setImage(R.drawable.baseline_notification_add_24);
                            liste.setDescription(item.getString("description"));
                            liste.setLibelle(item.getString("libelle"));
                            liste.setDate(extractDateFromString(date));
                            System.out.println(liste.getDate() + "/" + liste.getDescription());
                            newsArrayList.add(liste);
                        }

                        // Une fois les données récupérées, créez le RecyclerView et l'adaptateur
                        recyclerView.setLayoutManager(new LinearLayoutManager(getContext()));
                        recyclerView.setHasFixedSize(true);
                        NotificationAdapter adapter = new NotificationAdapter(getContext(), newsArrayList);
                        recyclerView.setAdapter(adapter);
                        adapter.notifyDataSetChanged();

                        // Masquer la barre de progression
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
                        Log.d("JSONN ", error.toString());
                        // En cas d'erreur, masquer la barre de progression également
                        progressBar.setVisibility(View.GONE);
                    }
                });
        requestQueue.add(stringRequest);
    }


    private Date extractDateFromString(String dateString) {
        SimpleDateFormat inputFormat = new SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss.SSS'Z'");
        SimpleDateFormat outputFormat = new SimpleDateFormat("yyyy-MM-dd");
        try {
            Date date = inputFormat.parse(dateString);
            String formattedDate = outputFormat.format(date);
            return outputFormat.parse(formattedDate);
        } catch (ParseException e) {
            e.printStackTrace();
            return null;
        }
    }
}