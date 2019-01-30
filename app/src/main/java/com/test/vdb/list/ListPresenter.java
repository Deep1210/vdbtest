package com.test.vdb.list;

/**
 * Created by deepanshurustagi on 1/29/19.
 */


import com.test.vdb.activity.MainActivity;
import com.test.vdb.list.pojo.GetListResponse;

import java.util.List;

public class ListPresenter implements ListManager.ListPresenterCallback{

    private ListManager listManager;
    EngageFragmentCallbackInterface engageFragmentCallbackInterface;

    public ListPresenter(EngageFragmentCallbackInterface engageFragmentCallbackInterface) {
        this.listManager = new ListManager(this);
        this.engageFragmentCallbackInterface=engageFragmentCallbackInterface;
    }

    public void getList(MainActivity context, int pageNo) {
        listManager.callGetListApi(context, pageNo);
    }


    @Override
    public void onCheckGetListSuccess(List<GetListResponse> getListResponse) {
        engageFragmentCallbackInterface.onGetListSuccess(getListResponse);
    }

    @Override
    public void onError(String errorMessage) {
        engageFragmentCallbackInterface.onError(errorMessage);
    }

    public interface EngageFragmentCallbackInterface{
        void onGetListSuccess(List<GetListResponse> getListResponse);
        void onError(String errorMessage);
    }
}

