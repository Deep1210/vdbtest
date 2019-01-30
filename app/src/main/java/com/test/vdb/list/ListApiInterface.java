package com.test.vdb.list;

import android.support.v7.app.AppCompatActivity;


import com.test.vdb.list.pojo.GetListResponse;
import com.test.vdb.utils.AppConstants;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Header;
import retrofit2.http.Path;
import retrofit2.http.Query;

/**
 * Created by deepanshurustagi on 1/29/19.
 */

public interface ListApiInterface {

    @GET(AppConstants.GET_LIST)
    Call<List<GetListResponse>> getList(@Query("page") int pageNo, @Query("per_page") int per_page);

}
