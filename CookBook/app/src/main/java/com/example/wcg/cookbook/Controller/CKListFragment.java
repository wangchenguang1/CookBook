package com.example.wcg.cookbook.Controller;

import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.Toast;

import com.example.wcg.cookbook.CKApplication;
import com.example.wcg.cookbook.Http.HttpClient;
import com.example.wcg.cookbook.Model.Classfy.CKListContent;
import com.example.wcg.cookbook.Model.Classfy.CKListData;
import com.example.wcg.cookbook.Model.Classfy.ErrorModel;
import com.example.wcg.cookbook.R;
import com.example.wcg.cookbook.View.CKTopBar;
import com.handmark.pulltorefresh.library.PullToRefreshBase;
import com.handmark.pulltorefresh.library.PullToRefreshListView;
import com.nostra13.universalimageloader.core.listener.PauseOnScrollListener;

import java.util.ArrayList;
import java.util.HashMap;

/**
 * Created by wcg on 16/3/9.
 */
public class CKListFragment extends Fragment implements AdapterView.OnItemClickListener {

    private int page = 1;  //请求页码，默认为1
    private String id = ""; //列表id,默认为0
    public String passedId = "";  //传过来的列表id

    private CKTopBar ckTopBar;
    private CKListFragmentAdapter adapter;
    private ArrayList<CKListContent> arrayList;
    private PullToRefreshListView listView;

    public static CKListFragment newInstance(){

        CKListFragment fragment = new CKListFragment();
        return fragment;

    }

    public CKListFragment(){

    }

    public void setId(String id) {
        this.id = id;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        arrayList = new ArrayList<CKListContent>();
        adapter = new CKListFragmentAdapter( arrayList , this.getContext());


    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {

        View view = null;
        view =  inflater.inflate(R.layout.fragment_list, container, false);

        listView = (PullToRefreshListView) view.findViewById(R.id.listView);
        listView.setAdapter(adapter);
        listView.setOnItemClickListener(this);
        listView.setOnScrollListener(new PauseOnScrollListener(CKApplication.imageLoader, true,  true));

        listView.setOnRefreshListener(new PullToRefreshBase.OnRefreshListener2<ListView>() {
            @Override
            public void onPullDownToRefresh(PullToRefreshBase<ListView> refreshView) {
                //下拉刷新
                page = 1;
                request(id, page);
            }

            @Override
            public void onPullUpToRefresh(PullToRefreshBase<ListView> refreshView) {
//                上拉加载
                page = page + 1;
                request(id, page);

            }
        });

        return view;

    }


    //被隐藏和出现时调用
    @Override
    public void onHiddenChanged(boolean hidden) {
        super.onHiddenChanged(hidden);

        if (!hidden){
            if (!passedId.equals(id)){
                request(passedId, 1);
                id = passedId;
            }
        }
    }

    // 当前id，当前page
    public void request(String id, int page){

        HttpClient httpClient = HttpClient.getInstance();
        HashMap hashMap = new HashMap();
        hashMap.put("id", id);
        hashMap.put("page", page);
        hashMap.put("rows", "20");

        if (page == 1){
            arrayList.clear();
        } else {
        }

        httpClient.requestData(HttpClient.list_url, hashMap, CKListData.class, new HttpClient.CallBack() {

            @Override
            public void success(Object object) {
                System.out.println(object);
                CKListData ckListData = (CKListData)object;

                if (ckListData.getTngou() != null) {
                    arrayList.addAll(ckListData.getTngou());
                }
                adapter.notifyDataSetChanged();
                listView.onRefreshComplete();
            }
            @Override
            public void fail(ErrorModel errorModel) {
                listView.onRefreshComplete();
                Toast.makeText(getContext(), errorModel.getErrMsg(), Toast.LENGTH_SHORT).show();
            }
        });
    }

    @Override
    public void onItemClick(AdapterView<?> parent, View view, int position, long id) {

       CKListContent data =  arrayList.get(position  - 1);
        Intent intent = new Intent(getActivity(), CKDetailActivity.class );
        intent.putExtra("id", data.getId());
        getActivity().startActivity(intent);

    }
}
