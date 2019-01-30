package com.test.vdb.list;

import com.google.gson.Gson;
import com.test.vdb.activity.MainActivity;
import com.test.vdb.retrofit.ErrorBodyResponse;
import com.test.vdb.retrofit.NotFoundResponse;
import com.test.vdb.retrofit.ServiceGenerator;
import com.test.vdb.list.pojo.GetListResponse;


import java.io.IOException;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

/**
 * Created by deepanshurustagi on 1/29/19.
 */

public class ListManager {
    ListPresenterCallback listPresenterCallback;
    Gson gson = new Gson();

    public ListManager(ListPresenterCallback listPresenterCallback) {
        this.listPresenterCallback = listPresenterCallback;
    }

    public void callGetListApi(final MainActivity context, int pageNo) {
        ListApiInterface listApiInterface= ServiceGenerator.getClient().create(ListApiInterface.class);
        Call<List<GetListResponse>> call = listApiInterface.getList(1,pageNo);
        call.enqueue(new Callback<List<GetListResponse>>() {

            @Override
            public void onResponse(Call<List<GetListResponse>>call, Response<List<GetListResponse>> response) {
                if (response.isSuccessful()) {
                    listPresenterCallback.onCheckGetListSuccess(response.body());
                } else {
                    if(response.code() == 400) {
                        ErrorBodyResponse message = gson.fromJson(response.errorBody().charStream(), ErrorBodyResponse.class);
                        listPresenterCallback.onError(message.getError().get(0));
                    }
                    else if (response.code() == 401) {
                        NotFoundResponse message = gson.fromJson(response.errorBody().charStream(), NotFoundResponse.class);
                        listPresenterCallback.onError(message.getDetail());


                    }
                }
            }

            @Override
            public void onFailure(Call<List<GetListResponse>> call, Throwable t) {

                if (t instanceof IOException) {

                }
            }
        });

    }




    public interface ListPresenterCallback {
        void onCheckGetListSuccess(List<GetListResponse> getListResponse);
        void onError(String errorMessage);
    }
}
