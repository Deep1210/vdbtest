package com.test.vdb.list;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.EditText;
import android.widget.ProgressBar;
import android.widget.RadioButton;
import android.widget.TextView;


import com.test.vdb.R;
import com.test.vdb.list.pojo.GetListResponse;

import java.util.ArrayList;
import java.util.List;

public class ListAdapter extends RecyclerView.Adapter{


    private Context context;

    private List<GetListResponse> listResponse;

    private final int VIEW_ITEM = 1;
    private final int VIEW_PROG = 0;

    private int visibleThreshold = 5;
    private int lastVisibleItem, totalItemCount;
    private boolean loading;
    private OnLoadMoreListener onLoadMoreListener;

    public ListAdapter(List<GetListResponse> listResponse, RecyclerView recyclerView,Context context) {
        this.listResponse = listResponse;
        this.context = context;
        if (recyclerView.getLayoutManager() instanceof LinearLayoutManager) {

            final LinearLayoutManager linearLayoutManager = (LinearLayoutManager) recyclerView
                    .getLayoutManager();


            recyclerView
                    .addOnScrollListener(new RecyclerView.OnScrollListener() {
                        @Override
                        public void onScrolled(RecyclerView recyclerView,
                                               int dx, int dy) {
                            super.onScrolled(recyclerView, dx, dy);

                            totalItemCount = linearLayoutManager.getItemCount();
                            lastVisibleItem = linearLayoutManager
                                    .findLastVisibleItemPosition();
                            if (!loading
                                    && totalItemCount <= (lastVisibleItem + visibleThreshold)) {
                                // End has been reached
                                // Do something
                                if (onLoadMoreListener != null) {
                                    onLoadMoreListener.onLoadMore();
                                }
                                loading = true;
                            }
                        }
                    });
        }
    }

    @NonNull
    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {

        RecyclerView.ViewHolder vh;
        if (viewType == VIEW_ITEM) {
            View v = LayoutInflater.from(parent.getContext()).inflate(
                    R.layout.layout_list_item, parent, false);

            vh = new CBViewHolder(v);
        } else {
            View v = LayoutInflater.from(parent.getContext()).inflate(
                    R.layout.progress_item, parent, false);

            vh = new ProgressViewHolder(v);
        }
        return vh;



    }

    @Override
    public int getItemViewType(int position) {
        return listResponse.get(position) != null ? VIEW_ITEM : VIEW_PROG;
    }

    public void setLoaded() {
        loading = false;
    }

    public void setOnLoadMoreListener(OnLoadMoreListener onLoadMoreListener) {
        this.onLoadMoreListener = onLoadMoreListener;
    }



    @Override
    public void onBindViewHolder(@NonNull final RecyclerView.ViewHolder holder, final int position) {
        if (holder instanceof CBViewHolder) {
            ((CBViewHolder) holder).tvName.setText(listResponse.get(position).getName());
            ((CBViewHolder) holder).tvDis.setText(listResponse.get(position).getDescription());
            ((CBViewHolder) holder).tvLanguage.setText(listResponse.get(position).getLanguage());
            ((CBViewHolder) holder).tvIssue.setText(String.valueOf(listResponse.get(position).getOpen_issues_count()));
            ((CBViewHolder) holder).tvUser.setText(String.valueOf(listResponse.get(position).getWatchers()));
        }else{
            ((ProgressViewHolder) holder).progressBar.setIndeterminate(true);
        }
    }

    @Override
    public int getItemCount() {
        return listResponse.size();
    }

    public static class ProgressViewHolder extends RecyclerView.ViewHolder {
        private ProgressBar progressBar;

        public ProgressViewHolder(View v) {
            super(v);
            progressBar = (ProgressBar) v.findViewById(R.id.progressBar1);
        }
    }


    class CBViewHolder extends RecyclerView.ViewHolder {
        TextView tvName;
        TextView tvDis;
        TextView tvLanguage;
        TextView tvIssue;
        TextView tvUser;
        public CBViewHolder(View itemView) {
            super(itemView);
            tvName = (TextView)itemView.findViewById(R.id.tv_name);
            tvDis = (TextView)itemView.findViewById(R.id.tv_dis);
            tvLanguage = (TextView)itemView.findViewById(R.id.tv_language);
            tvIssue = (TextView)itemView.findViewById(R.id.tv_issue);
            tvUser = (TextView)itemView.findViewById(R.id.tv_user);

        }

    }

}
