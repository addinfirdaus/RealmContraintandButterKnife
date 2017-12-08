package com.sinau.belajarconstraintlayout;

import io.realm.RealmObject;

/**
 * Created by addin on 08/12/17.
 */

public class Modelabc extends RealmObject {

        private String a;
    private String b;
    private String c;

        public String getA() {
            return a;
        }

        public void setA(String a) {
            this.a = a;
        }

        public String getB() {
            return b;
        }

        public void setB(String b) {
            this.b = b;
        }
    public String getC() {
        return c;
    }

    public void setC(String c) {
        this.c = c;
    }
}
