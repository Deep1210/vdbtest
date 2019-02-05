package com.test.vdb.activity;

import android.content.DialogInterface;
import android.os.Handler;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.widget.Toast;

import com.test.vdb.R;
import com.test.vdb.custom.CustomProgressDialog;
import com.test.vdb.list.ListAdapter;
import com.test.vdb.list.ListPresenter;
import com.test.vdb.list.OnLoadMoreListener;
import com.test.vdb.list.pojo.GetListResponse;
import com.test.vdb.realm.RealmController;
import com.test.vdb.utils.AppConstants;
import com.test.vdb.utils.SharedPrefHolder;

import java.util.ArrayList;
import java.util.List;

import io.realm.Realm;

public class MainActivity extends AppCompatActivity implements ListPresenter.EngageFragmentCallbackInterface {

    private int end = 1;
    private Handler handler;
    private Realm realm;
    private RecyclerView rvData;
    private ListAdapter adapter;
    private CustomProgressDialog progressDialog;
    private ArrayList<GetListResponse> listResponse = new ArrayList<>();
    private int count;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        intializeView();

        setRecyclerView();

        getList(end,1);
    }



    private void intializeView() {
        rvData = (RecyclerView)findViewById(R.id.rv_data);
        handler = new Handler();
        this.realm = RealmController.with(this).getRealm();
        progressDialog = new  CustomProgressDialog(this);
    }

    private void showLoader(){
        progressDialog.show();
    }

    private void hideLoader(){
        progressDialog.dismiss();
    }

    private void setRecyclerView() {
        rvData.hasFixedSize();
        rvData.setLayoutManager(new LinearLayoutManager(this));
        adapter = new ListAdapter(listResponse,rvData,this);
        rvData.setAdapter(adapter);
        adapter.setOnLoadMoreListener(new OnLoadMoreListener() {
            @Override
            public void onLoadMore() {
                if(count==15) {

                    listResponse.add(null);
                    adapter.notifyItemInserted(listResponse.size() - 1);
                    //   remove progress item

                    handler.postDelayed(new Runnable() {
                        @Override
                        public void run() {
                            //   remove progress item

                            listResponse.remove(listResponse.size() - 1);
                            //add items one by one
                            end = end + 1;
                            getList(end, 2);
                            //or you can add all at once but do not forget to call mAdapter.notifyDataSetChanged();
                        }
                    }, 2000);//
                }
                    // or you can add all at once but do not forget to call mAdapter.notifyDataSetChanged();

            }
        });
    }

    private void getList(int pageNo,int from) {
        AlertDialog.Builder alertDialogBuilder = new AlertDialog.Builder(this);
        alertDialogBuilder.setMessage("You are not connected to internet.");
                alertDialogBuilder.setPositiveButton("ok",
                        new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface arg0, int arg1) {
                                finish();
                            }
                        });


        AlertDialog alertDialog = alertDialogBuilder.create();
        if(AppConstants.isNetworkConnected(MainActivity.this)) {
            if(from==1) {
                showLoader();
                listResponse.clear();
                SharedPrefHolder.getInstance(MainActivity.this).setAuthToken(true);
            }
            new ListPresenter(this).getList(MainActivity.this, pageNo);

        }else{
            if(SharedPrefHolder.getInstance(MainActivity.this).getAuthToken()){
                listResponse.clear();
                listResponse.addAll(RealmController.with(this).getList());


            }else{
                alertDialog.show();
            }

        }

    }


    @Override
    public void onGetListSuccess(List<GetListResponse> getListResponse) {
        if(getListResponse!=null) {
            hideLoader();
            count = getListResponse.size();
            Log.e("Count : ",count+"");
            listResponse.addAll(getListResponse);
            adapter.notifyDataSetChanged();
            adapter.setLoaded();
            RealmController.with(this).clearAll();
            for (GetListResponse b : listResponse) {
                // Persist your data easily

                realm.beginTransaction();
                realm.copyToRealm(b);
                realm.commitTransaction();
            }

        }
    }

    @Override
    public void onError(String errorMessage) {

    }
}
