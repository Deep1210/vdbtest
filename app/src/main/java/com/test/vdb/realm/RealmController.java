package com.test.vdb.realm;


import android.app.Activity;
import android.app.Application;
import android.support.v4.app.Fragment;

import com.test.vdb.list.pojo.GetListResponse;


import io.realm.Realm;
import io.realm.RealmResults;



public class RealmController {

    private static RealmController instance;
    private final Realm realm;

    public RealmController(Application application) {
        realm = Realm.getDefaultInstance();
    }

    public static RealmController with(Fragment fragment) {

        if (instance == null) {
            instance = new RealmController(fragment.getActivity().getApplication());
        }
        return instance;
    }

    public static RealmController with(Activity activity) {

        if (instance == null) {
            instance = new RealmController(activity.getApplication());
        }
        return instance;
    }

    public static RealmController with(Application application) {

        if (instance == null) {
            instance = new RealmController(application);
        }
        return instance;
    }

    public static RealmController getInstance() {

        return instance;
    }

    public Realm getRealm() {

        return realm;
    }



    //clear all objects from Book.class
    public void clearAll() {

        realm.beginTransaction();
        realm.deleteAll();
        realm.commitTransaction();
    }

    //find all objects in the Book.class
    public RealmResults<GetListResponse> getList() {

        return realm.where(GetListResponse.class).findAll();
    }






}
