package com.sinau.belajarconstraintlayout;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;

import com.miguelcatalan.materialsearchview.MaterialSearchView;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import io.realm.Realm;
import io.realm.RealmConfiguration;

public class MainActivity extends AppCompatActivity {

    Realm realm;
    ArrayList<String> model;
    MyAdapter adapter;
    RecyclerView rv;
//    @BindView(R.id.title) EditText title;
    String[] b = {"1","2","3","4","5","6","7","8","9","10","11","12","13"};
        //,"7","8,","9","10","11","12","13","14","15","16","17","18","19","20","21","22","23","24","25","26","27"};
    private RealmHelper help;
    MaterialSearchView searchView;
    @BindView(R.id.toolbar) Toolbar toolbar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        ButterKnife.bind(this);
        setSupportActionBar(toolbar);

        String [] a = getResources().getStringArray(R.array.query_suggestions);
        String [] c = getResources().getStringArray(R.array.no);
        Model s=new Model();

        rv= (RecyclerView) findViewById(R.id.rv);
        rv.setLayoutManager(new LinearLayoutManager(this));

        Realm.init(getApplicationContext());
        RealmConfiguration realmConfiguration = new RealmConfiguration.Builder()
                .name(Realm.DEFAULT_REALM_NAME)
                .schemaVersion(0)
                .deleteRealmIfMigrationNeeded()
                .build();
        realm=Realm.getInstance(realmConfiguration);
        Log.i("TAG", "onCreate: "+realm.isEmpty());

        if (realm.isEmpty()){
            for (int b=0;b<a.length;b++){
                s.setName(a[b]);
                s.setNo(c[b]);

                //SAVE
                help=new RealmHelper(realm);
                help.save(s);
                //BIND
                model = help.retrieve();
                adapter = new MyAdapter(this, model);
                rv.setAdapter(adapter);
            }

        }else{

            RealmHelper helpo=new RealmHelper(realm);
            model = helpo.retrieve();
            adapter = new MyAdapter(this, model);
            rv.setAdapter(adapter);
            Log.i("MODELSIZE", "onCreate: "+model.size());
            Log.i("A", "onCreate: "+a.length);
            if (a.length>model.size()){
                //delete object Realm
                realm.executeTransaction(new Realm.Transaction() {
                    @Override
                    public void execute(Realm realm) {
                        realm.delete(Model.class);
                    }
                });
                //
                for (int b=0;b<a.length;b++){

                    s.setName(a[b]);
                    s.setNo(c[b]);
                //SAVE
                help=new RealmHelper(realm);
                help.save(s);
                //BIND
                model = help.retrieve();
                adapter = new MyAdapter(this, model);
                rv.setAdapter(adapter);}
            }else {
                a = null;
                c=null;
            }
        }
        Log.w("a", "a: "+a );

        searchView = (MaterialSearchView) findViewById(R.id.search_view);
        searchView.setOnQueryTextListener(new MaterialSearchView.OnQueryTextListener() {
            @Override
            public boolean onQueryTextSubmit(String query) {
                //Do some magic
                return false;
            }

            @Override
            public boolean onQueryTextChange(String newText) {
                newText = newText.toLowerCase();

                final List<String> filteredList = new ArrayList<>();
                for (int i = 0 ; i < model.size(); i++) {
                    final String text = model.get(i).toLowerCase();
                    if (text.contains(newText) ) {
                        filteredList.add(model.get(i));

                    }
                }

                rv.setHasFixedSize(true);
                rv.setLayoutManager(new LinearLayoutManager(MainActivity.this));
                adapter = new MyAdapter(MainActivity.this, (ArrayList<String>) filteredList);
                rv.setAdapter(adapter);
                adapter.notifyDataSetChanged();  // data set changed

                return true;
            }
        });

        searchView.setOnSearchViewListener(new MaterialSearchView.SearchViewListener() {
            @Override
            public void onSearchViewShown() {
                //Do some magic
            }

            @Override
            public void onSearchViewClosed() {
                //Do some magic
            }
        });
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.itemmenu, menu);

        MenuItem item = menu.findItem(R.id.action_search);
        searchView.setMenuItem(item);

        return true;
    }
}
