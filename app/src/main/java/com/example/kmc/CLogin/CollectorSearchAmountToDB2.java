package com.example.kmc.CLogin;

import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.util.Log;
import android.view.View;
import android.widget.ProgressBar;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.kmc.CollectorAdapters.myadapter4CollectorSearch2;
import com.example.kmc.CollectorAdapters.myadapter4CollectorSearch5;
import com.example.kmc.Individual;
import com.example.kmc.R;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.material.textfield.TextInputEditText;
import com.google.firebase.firestore.DocumentSnapshot;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.QuerySnapshot;

import java.util.ArrayList;
import java.util.List;
import java.util.Locale;

public class CollectorSearchAmountToDB2 extends AppCompatActivity {
    ArrayList<Individual> datalist;
    FirebaseFirestore db;
    RecyclerView recyclerView;
    public TextInputEditText searchBox;

    String district;
    ProgressBar progressBar;
    String searchText;
    String village;

    myadapter4CollectorSearch5 adapter;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_collector_search);
        recyclerView = (RecyclerView) findViewById(R.id.recyclerView);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        searchBox=findViewById(R.id.searchbox);
        datalist=new ArrayList<>();
        searchText="";
        Bundle extras = getIntent().getExtras();
        if (extras != null) {
            district= extras.getString("district");
           // village=extras.getString("village");
        }
        adapter=new myadapter4CollectorSearch5(datalist,district);
        recyclerView.setAdapter(adapter);
        db=FirebaseFirestore.getInstance();
        progressBar = (ProgressBar) findViewById(R.id.progressBar);
        searchBox.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {
            }

            @Override
            public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {

            }

            @Override
            public void afterTextChanged(Editable editable) {
                searchText=searchBox.getText().toString().toLowerCase(Locale.ROOT);
            }
        });

    }

    public void searchbutton(View view) {
        datalist.clear();
        progressBar.setVisibility(View.VISIBLE);
        Log.d("searchText",searchText);
        db.collection("individuals").orderBy("name").startAt(searchText.toLowerCase(Locale.ROOT)).endAt(searchText.toLowerCase(Locale.ROOT)+"\uf8ff").get()
                .addOnSuccessListener(new OnSuccessListener<QuerySnapshot>() {
                    @Override
                    public void onSuccess(QuerySnapshot queryDocumentSnapshots) {
                        List<DocumentSnapshot> list =queryDocumentSnapshots.getDocuments();
                        for(DocumentSnapshot d:list)
                        {
                            Individual obj=d.toObject(Individual.class);
                            if(obj.getDistrict().equalsIgnoreCase(district)) {
                                if (obj.getSpApproved2().equals("yes")) {
                                    if (!obj.getCtrApproved().equals("yes") && !obj.getCtrApproved().equals("no")) {
                                        datalist.add(obj);
                                    }
                                }
                            }
                        }
                        adapter.notifyDataSetChanged();
                        progressBar.setVisibility(View.GONE);
                    }
                });
    }
}