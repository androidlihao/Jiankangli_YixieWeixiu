package com.example.hs.jiankangli_example1;

import android.app.Dialog;
import android.content.Intent;
import android.os.Handler;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Base64;
import android.util.Log;
import android.view.Display;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.Toast;

import com.alibaba.fastjson.JSONObject;
import com.andview.refreshview.XRefreshView;
import com.example.hs.jiankangli_example1.seek.seek_activity;
import com.example.hs.jiankangli_example1.wxapi.Seek2Activity;
import com.yuyh.library.imgsel.widget.CustomViewPager;

import org.xutils.common.Callback;
import org.xutils.http.RequestParams;
import org.xutils.x;

import java.util.LinkedList;
import java.util.List;

import Adapter_package.parts_lists_adapter;
import Inter.Globle;
import bean.PartsDetailsList;
import utils.JavaScriptObject;
import utils.Statubars;


public class PartsActivity extends AppCompatActivity implements View.OnClickListener{

    private View have_want_id;
    private View sets_back_id;
    private XRefreshView xrefreshview_id;
    public static long lastRefreshTime;
    private ListView lv_id;
    private static String URi=Globle.TEST_URL+"/api/part/partList";
    private LinkedList list;
    private parts_lists_adapter adapter;
    private Dialog dialog;
    private View ll_havePart_id;
    private View ll_wantPart_id;
    private View fabu_exit_id;
    private View search_button;
    private View vHead;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        new Statubars().setStatubars(this,getWindow(),"zhaose",getResources().getColor(R.color.statue));
        setContentView(R.layout.activity_parts);
        if (getIntent().getStringExtra("manufacturer_id")!=null){
            manufacturerId=getIntent().getStringExtra("manufacturer_  id");
        }
        if (getIntent().getStringExtra("product_categories_id")!=null){
            productCategoriesId=getIntent().getStringExtra("product_categories_id");
        }
        name=getIntent().getStringExtra("name");
        pageNo=0;
        //初始化界面控件实例
        initView();
        initXRefreshView();
        SetOnClickListener();
        Log.i("TAG", "onCreate: ");
        //请求网络
    }

    @Override
    protected void onNewIntent(Intent intent) {
        if (intent.getStringExtra("manufacturer_id")!=null){
            manufacturerId=intent.getStringExtra("manufacturer_id");
        }
        if (intent.getStringExtra("product_categories_id")!=null){
            productCategoriesId=intent.getStringExtra("product_categories_id");
        }
        name=intent.getStringExtra("name");
        pageNo=0;
        list.clear();//将数据源清空，从新请求
        xrefreshview_id.startRefresh();
        super.onNewIntent(intent);
    }

    private void initXRefreshView() {
        //初始化刷新控件
        xrefreshview_id.setPullLoadEnable(true);
        xrefreshview_id.setPullRefreshEnable(true);
        // 设置上次刷新的时间
        xrefreshview_id.restoreLastRefreshTime(lastRefreshTime);
        // 设置时候可以自动刷新
        xrefreshview_id.setAutoRefresh(true);
        xrefreshview_id.setPinnedTime(500);
        xrefreshview_id.enableReleaseToLoadMore(true);
        xrefreshview_id.enablePullUpWhenLoadCompleted(true);
        xrefreshview_id.setXRefreshViewListener(new XRefreshView.SimpleXRefreshListener() {
            @Override
            public void onRefresh(boolean isPullDown) {
                super.onRefresh(isPullDown);
                flag=0;
                pageNo=pageNo+1;
                getHttpsInfo(pageNo);
            }

            @Override
            public void onLoadMore(boolean isSilence) {
                super.onLoadMore(isSilence);
                flag=1;
                pageNo=pageNo+1;
                getHttpsInfo(pageNo);
            }
        });

    }

    private void SetOnClickListener() {
        have_want_id.setOnClickListener(this);
        sets_back_id.setOnClickListener(this);
        ll_havePart_id.setOnClickListener(this);
        ll_wantPart_id.setOnClickListener(this);
        fabu_exit_id.setOnClickListener(this);
        search_button.setOnClickListener(this);
    }

    private void initView() {
        vHead = View.inflate(PartsActivity.this, R.layout.item_headview, null);
        search_button = findViewById(R.id.search_button);
        dialog = new Dialog(this, R.style.Transparent);
        dialog.setContentView(R.layout.have_want_layout);  //设置dialog的布局
        Display d = getWindowManager().getDefaultDisplay();  //为获取屏幕宽、高
        android.view.WindowManager.LayoutParams p = dialog.getWindow().getAttributes();  //获取对话框当前的参数值
        p.height = (d.getHeight());   //高度设置为屏幕的高度
        p.width = (d.getWidth());    //宽度设置为屏幕的宽度
        dialog.getWindow().setAttributes(p);//设置生效
        ll_havePart_id = dialog.findViewById(R.id.ll_HavePart_id);
        ll_wantPart_id = dialog.findViewById(R.id.ll_WantPart_id);
        fabu_exit_id = dialog.findViewById(R.id.fabu_exit_id);
        have_want_id = findViewById(R.id.have_want_id);
        sets_back_id = findViewById(R.id.sets_back_id);
        xrefreshview_id = (XRefreshView) findViewById(R.id.xrefreshview_id);
        lv_id = (ListView) findViewById(R.id.lv_id);
        //准备数据源  模拟数据
        list = new LinkedList<>();
        //准备适配器
        adapter = new parts_lists_adapter(list,R.layout.item_parts_layout);
        //绑定适配器
        lv_id.setAdapter(adapter);
        //设置监听器
        lv_id.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                PartsDetailsList.BodyBean.DataBean partsDetails= (PartsDetailsList.BodyBean.DataBean) list.get(position);
                Intent intent=new Intent(PartsActivity.this,PartsDetailsActivity.class);
                intent.putExtra("partId",partsDetails.getPartId());
                startActivity(intent);
            }
        });
    }
    private int pageNo=0;
    private int  pageSize=8;
    private String  name;
    private String  manufacturerId;
    private String  productCategoriesId;
    public  int flag=0;
    public void getHttpsInfo(int pageNo){
        JSONObject jsonObject=new JSONObject();
        jsonObject.put("memberId",new JavaScriptObject(this).getMemberid(""));
        jsonObject.put("pageNo",pageNo);
        jsonObject.put("pageSize",pageSize);
        jsonObject.put("name",name);
        jsonObject.put("manufacturerId",manufacturerId);
        jsonObject.put("productCategoriesId",productCategoriesId);
        Log.i("TAG", "getHttpsInfo: "+jsonObject);
        RequestParams params=new RequestParams(URi);
        if (jsonObject!=null){
            String jsonString = Base64.encodeToString(jsonObject.toString().getBytes(),Base64.DEFAULT);//base64加密
            params.setBodyContent(jsonString);
        }
        x.http().post(params, new Callback.CommonCallback<String>() {
            @Override
            public void onSuccess(String result) {
                PartsDetailsList partsDetails=  JSONObject.parseObject(result, PartsDetailsList.class);
                List data=partsDetails.getBody().getData();
                switch (flag){
                    case 0:
                        for (int i=data.size();i>0;i--){
                            list.addFirst(data.get(i-1));
                        }
                        Stop("Refresh");
                        break;
                    case 1:
                        list.addAll(partsDetails.getBody().getData());
                        xrefreshview_id.setHideFooterWhenComplete(true);
                        Stop("loadMore");
                        break;
                }
                if (data.size()==0){
                    Toast.makeText(getApplicationContext(), "没有更多数据！", Toast.LENGTH_SHORT).show();
                }
                adapter.notifyDataSetChanged();
            }

            @Override
            public void onError(Throwable ex, boolean isOnCallback) {
                Toast.makeText(getApplicationContext(), "网络或服务器异常！", Toast.LENGTH_SHORT).show();
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
                lastRefreshTime = xrefreshview_id.getLastRefreshTime();
                lv_id.setHeaderDividersEnabled(false);
                lv_id.removeHeaderView(vHead);
                if (list.size()==0){
                    lv_id.addHeaderView(vHead,null,false);
                }
            }
        });
    }
    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.have_want_id:
                //弹出弹窗
                dialog.show();
                break;
            case R.id.sets_back_id:
                finish();
                break;
            case R.id.ll_HavePart_id:
                Intent intent=new Intent(this,IHavaPartActivity.class);
                intent.putExtra("type","2");
                startActivity(intent);
                dialog.dismiss();
                break;
            case R.id.ll_WantPart_id:
                Intent intent1=new Intent(this,IHavaPartActivity.class);
                intent1.putExtra("type","3");
                startActivity(intent1);
                dialog.dismiss();
                break;
            case R.id.fabu_exit_id:
                dialog.dismiss();
                break;
            case R.id.search_button:
                Intent intent3=new Intent(this, seek_activity.class);
                intent3.putExtra("tags","3");
                startActivity(intent3);
                break;
        }
    }
    public void Stop(String string){
        switch (string){
            case "loadMore":
                new Handler().postDelayed(new Runnable() {
                    @Override
                    public void run() {
                        xrefreshview_id.stopLoadMore();
                    }
                }, 1000);
                break;
            case "Refresh":
                new Handler().postDelayed(new Runnable() {
                    @Override
                    public void run() {
                        xrefreshview_id.stopRefresh();
                    }
                }, 1000);
                break;
        }
    }
}
