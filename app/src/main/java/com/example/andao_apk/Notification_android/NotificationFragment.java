package com.example.andao_apk.Notification_android;

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

import java.util.ArrayList;

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
        datainitialise();
        recyclerView=view.findViewById(R.id.recyclerview);
        recyclerView.setLayoutManager(new LinearLayoutManager(getContext()));
        recyclerView.setHasFixedSize(true);
        NotificationAdapter adapter=new NotificationAdapter(getContext(),newsArrayList);
        recyclerView.setAdapter(adapter);
        adapter.notifyDataSetChanged();

    }

    private void datainitialise() {
        newsArrayList = new ArrayList<>();

        image=new int[]{
                R.drawable.baseline_notification_add_24,
                R.drawable.baseline_notification_add_24,
                R.drawable.baseline_notification_add_24,
                R.drawable.baseline_notification_add_24,
                R.drawable.baseline_notification_add_24,
                R.drawable.baseline_notification_add_24,
        };

        titre=new String[]{
                getString(R.string.title_notification),
                getString(R.string.title_notification),
                getString(R.string.title_notification),
                getString(R.string.title_notification),
                getString(R.string.title_notification),
                getString(R.string.title_notification),
        };

        description=new String[]{
                getString(R.string.content_notification),
                getString(R.string.content_notification),
                getString(R.string.content_notification),
                getString(R.string.content_notification),
                getString(R.string.content_notification),
                getString(R.string.content_notification),
        };

        for(int i=0;i<description.length;i++){
            NotificationClass multimedia=new NotificationClass(image[i],titre[i],description[i]);
            newsArrayList.add(multimedia);
        }
    }
}