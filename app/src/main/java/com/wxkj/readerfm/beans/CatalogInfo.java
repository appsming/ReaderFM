package com.wxkj.readerfm.beans;

import java.io.Serializable;
import java.util.ArrayList;

/**
 * Created by taosong on 17/8/4.
 */

public class CatalogInfo extends BaseResposeInfo {


    private ArrayList<CatalogBean> data;

    public ArrayList<CatalogBean> getData() {
        return data;
    }

    public void setData(ArrayList<CatalogBean> data) {
        this.data = data;
    }

   public class CatalogBean implements Serializable{


        private String _id,name;

        public String get_id() {
            return _id;
        }

        public void set_id(String _id) {
            this._id = _id;
        }

        public String getName() {
            return name;
        }

        public void setName(String name) {
            this.name = name;
        }


    }


}
