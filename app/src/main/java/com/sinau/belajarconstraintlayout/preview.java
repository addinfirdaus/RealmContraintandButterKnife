package com.sinau.belajarconstraintlayout;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.widget.Toast;

import com.miguelcatalan.materialsearchview.MaterialSearchView;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import io.realm.Realm;
import io.realm.RealmConfiguration;
import io.realm.RealmResults;

public class preview extends AppCompatActivity {

    private int index;
    Realm realm;
    ArrayList<String> modelabc;
    private RealmHelper help;
    MaterialSearchView searchView;
    @BindView(R.id.toolbar) Toolbar toolbar;
    private String[] a ;
    private String[] b ;
    private String[] c ;
    private Modelabc abc = new Modelabc();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_preview);

        a = getResources().getStringArray(R.array.a);
        b = getResources().getStringArray(R.array.b);
        c = getResources().getStringArray(R.array.c);

        ButterKnife.bind(this);
        setSupportActionBar(toolbar);

        index = getIntent().getIntExtra("index",0);
        Toast.makeText(this, ""+index, Toast.LENGTH_SHORT).show();

        Realm.init(getApplicationContext());
        RealmConfiguration realmConfiguration = new RealmConfiguration.Builder()
                .name("oke")
                .schemaVersion(42)
                .deleteRealmIfMigrationNeeded()
                .build();
        realm=Realm.getInstance(realmConfiguration);

//        Realm.init(this);
//        realm = Realm.getDefaultInstance();
//        RealmConfiguration config = new RealmConfiguration.Builder().build();

        if (realm.isEmpty()){
        //SAVE TO REALM
//        realm.beginTransaction();
//        Modelabc abc = realm.createObject(Modelabc.class);
        for (int i=0;i<a.length;i++) {
            abc.setA(a[i]);
            abc.setB(b[i]);
            abc.setC(c[i]);

            help=new RealmHelper(realm);
            help.simpan(abc);
            //BIND

//            realm.commitTransaction();
//            realm.close();

        }
        }
        else{
//            ArrayList<String> modelNames=new ArrayList<>();
//            RealmResults<Model> spacecrafts=realm.where(Model.class).findAll();
//            for(Model s:spacecrafts)
//            {
//                modelNames.add(s.getName());
//
//            }
            RealmResults<Modelabc> results = realm.where(Modelabc.class).findAll();
            for (int j=0;j<results.size();j++){
            }
            Log.i("GETA", "viewRecord: "+results.get(index).getC());

        }



    }

    public void viewRecord(){
        RealmResults<Modelabc> results = realm.where(Modelabc.class).findAll();
        for (int j=0;j<results.size();j++){
            Log.i("GETA", "viewRecord: "+results);
//            Toast.makeText(this, ""+results.get(1).getA(), Toast.LENGTH_SHORT).show();
        }

//        for(Modelabc a : results){
//            Toast.makeText(this, ""+, Toast.LENGTH_SHORT).show();
//        }
    }
}
