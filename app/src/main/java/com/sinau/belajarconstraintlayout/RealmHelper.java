package com.sinau.belajarconstraintlayout;

import java.util.ArrayList;

import io.realm.Realm;
import io.realm.RealmResults;

/**
 * Created by addin on 07/12/17.
 */

class RealmHelper {

    Realm realm;
    public RealmHelper(Realm realm) {
        this.realm = realm;
    }
    //WRITE
    public void save(final Model spacecraft)
    {
        realm.executeTransaction(new Realm.Transaction() {
            @Override
            public void execute(Realm realm) {
                Model s=realm.copyToRealm(spacecraft);
            }
        });
    }
    //READ
    public ArrayList<String> retrieve()
    {
        ArrayList<String> modelNames=new ArrayList<>();
        RealmResults<Model> spacecrafts=realm.where(Model.class).findAll();
        for(Model s:spacecrafts)
        {
            modelNames.add(s.getName());

        }
        return modelNames;
    }

    public void simpan(final Modelabc abc) {
        realm.executeTransaction(new Realm.Transaction() {
            @Override
            public void execute(Realm realm) {
                Modelabc sabc = realm.copyToRealm(abc);
            }
        });
    }

}
