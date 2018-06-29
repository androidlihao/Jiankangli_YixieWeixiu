package com.example.hs.jiankangli_example1;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Base64;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.Toast;

import com.andview.refreshview.XRefreshView;
import com.example.hs.jiankangli_example1.wxapi.Seek2Activity;

import org.json.JSONException;
import org.json.JSONObject;
import org.xutils.common.Callback;
import org.xutils.http.RequestParams;
import org.xutils.x;

import java.util.LinkedList;
import java.util.List;

import Adapter_package.fix_helper_adapter;
import Inter.Globle;
import bean.fix_helper_bean;
import utils.JavaScriptObject;
import utils.Statubars;

public class fix_helper_Activity extends AppCompatActivity  implements View.OnClickListener{

    private ListView listView;
    private XRefreshView xrefreshview_id;
    private LinkedList<fix_helper_bean.BodyBean.DataBean> data=new LinkedList<>();
    private fix_helper_adapter adapter;
    private String title=new String();
    private View vHead;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        new Statubars().setStatubars(this,getWindow(),"zhaose",getResources().getColor(R.color.statue));
        setContentView(R.layout.fix_helper_layout);
        //初始化界面控件实例
        initView();
        aboutListView();
    }

    private void aboutListView() {
        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                //跳转到PDF界面
                Intent intent=new Intent(parent.getContext(),My_activity.class);
                intent.putExtra("my","fixHelper");
                intent.putExtra("repairManualId",data.get(position).getRepairManualId()+"");
                startActivity(intent);
            }
        });
    }

    public static long lastRefreshTime;
    public static int flag=0;
    public int pageNo=0;
    private void initView() {
        findViewById(R.id.sets_back_id).setOnClickListener(this);
        findViewById(R.id.search_button).setOnClickListener(this);
        listView = (ListView) findViewById(R.id.lv_id);
        //准备适配器
        adapter = new fix_helper_adapter(data, R.layout.item_fix_hleper_listview);
        //绑定适配器
        listView.setAdapter(adapter);
        vHead = View.inflate(this, R.layout.item_headview, null);
        xrefreshview_id = (XRefreshView) findViewById(R.id.xrefreshview_id);
        xrefreshview_id.setPullLoadEnable(true);
        xrefreshview_id.setPullRefreshEnable(true);
        // 设置上次刷新的时间
        xrefreshview_id.restoreLastRefreshTime(lastRefreshTime);
        // 设置时候可以自动刷新
        xrefreshview_id.setAutoRefresh(true);
        xrefreshview_id.setPinnedTime(500);
        xrefreshview_id.enableReleaseToLoadMore(true);
        xrefreshview_id.enablePullUpWhenLoadCompleted(true);
        //准备数据源，请求网络得到数据源
        xrefreshview_id.setXRefreshViewListener(new XRefreshView.SimpleXRefreshListener(){
            @Override
            public void onRefresh(boolean isPullDown) {
                super.onRefresh(isPullDown);
                //进入页面开始执行刷新任务
                flag=0;
                pageNo=pageNo+1;
                //下拉刷新
                getHttpInfo(pageNo);
            }

            @Override
            public void onLoadMore(boolean isSilence) {
                super.onLoadMore(isSilence);
                flag=1;
                pageNo=pageNo+1;
                //加载
                getHttpInfo(pageNo);
            }
        });

    }
    private static String URl= Globle.TEST_URL+"/api/repairManual/manualList";
    public void getHttpInfo(int pageNo){
        JSONObject jsonObject=new JSONObject();
        try {
            jsonObject.put("memberId",new JavaScriptObject(this).getMemberid(""));
            jsonObject.put("pageNo",pageNo);
            jsonObject.put("pageSize",10);
            jsonObject.put("title",title);
            Log.i("TAG", "getHttpInfo: "+jsonObject);
            RequestParams params=new RequestParams(URl);
            if (jsonObject!=null){
                String jsonString = Base64.encodeToString(jsonObject.toString().getBytes(),Base64.DEFAULT);//base64加密
                params.setBodyContent(jsonString);
            }
            x.http().post(params, new Callback.CacheCallback<String>() {

                @Override
                public void onSuccess(final String result) {
                    //刷新成功
                    fix_helper_bean fixhelperbean= com.alibaba.fastjson.JSONObject.parseObject(result,fix_helper_bean.class);
                    List<fix_helper_bean.BodyBean.DataBean> listdata = fixhelperbean.getBody().getData();
                    if (listdata.size()==0){
                        Toast.makeText(fix_helper_Activity.this, "没有更多数据！", Toast.LENGTH_SHORT).show();
                    }
                    //判断是上拉刷新还是下拉加载    你
                    switch (flag){
                        case 0:
                            for (int i=listdata.size();i>0;i--){
                                 data.addFirst(listdata.get(i-1));
                            }
                            xrefreshview_id.stopRefresh();
                            break;
                        case 1:
                            data.addAll(listdata);
                            xrefreshview_id.setHideFooterWhenComplete(true);
                            xrefreshview_id.stopLoadMore();
                            break;
                    }
                    adapter.notifyDataSetChanged();
                }

                @Override
                public void onError(Throwable ex, boolean isOnCallback) {
                    Toast.makeText(fix_helper_Activity.this, "网络或服务器异常！", Toast.LENGTH_SHORT).show();
                    //加载失败
                    switch (flag){
                        case 0:
                            xrefreshview_id.stopRefresh(false);
                            break;
                        case 1:
                            xrefreshview_id.stopLoadMore(false);
                            break;
                    }

                }

                @Override
                public void onCancelled(CancelledException cex) {

                }

                @Override
                public void onFinished() {
                    //刷新完成
                    lastRefreshTime = xrefreshview_id.getLastRefreshTime();
                    listView.setHeaderDividersEnabled(false);
                    listView.removeHeaderView(vHead);
                    if (data.size()==0){
                        listView.addHeaderView(vHead,null,false);
                    }
                }

                @Override
                public boolean onCache(String result) {
                    return false;
                }
            });
        } catch (JSONException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.sets_back_id:
                finish();
                break;
            case R.id.search_button:
                //跳转到搜索界面
                Intent intent=new Intent(this, Seek2Activity.class);
                startActivity(intent);
                break;
        }
    }

    @Override
    protected void onNewIntent(Intent intent) {
        title=intent.getStringExtra("title")==null?"":intent.getStringExtra("title");
        pageNo=0;
        data.clear();//将数据源清空，从新请求
        xrefreshview_id.startRefresh();
        Log.i("TAG", "onNewIntent: "+title);
        super.onNewIntent(intent);
    }
}
