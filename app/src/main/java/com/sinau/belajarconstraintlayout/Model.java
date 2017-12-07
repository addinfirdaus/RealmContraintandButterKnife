package com.sinau.belajarconstraintlayout;

import io.realm.RealmObject;

/**
 * Created by addin on 07/12/17.
 */

public class Model extends RealmObject {
    private String name;
    private String no;

//    public Model(String name, String no) {
//        this.name = name;
//        this.no = no;
//    }

    public String getName() {
        return name;
    }
    public void setName(String name) {
        this.name = name;
    }
    public String getNo() {
        return no;
    }
    public void setNo(String no) {
        this.no = no;
    }
}
