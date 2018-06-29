package com.example.hs.jiankangli_example1;

import android.content.Context;
import android.content.Intent;
import android.support.v4.view.PagerAdapter;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Base64;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import android.widget.AbsListView;
import android.widget.AdapterView;
import android.widget.BaseAdapter;
import android.widget.GridView;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.bm.library.PhotoView;
import com.bumptech.glide.Glide;
import com.youth.banner.Banner;
import com.youth.banner.BannerConfig;
import com.youth.banner.listener.OnBannerListener;
import com.youth.banner.loader.ImageLoader;
import com.zhy.view.flowlayout.FlowLayout;
import com.zhy.view.flowlayout.TagAdapter;
import com.zhy.view.flowlayout.TagFlowLayout;

import org.json.JSONException;
import org.json.JSONObject;
import org.xutils.common.Callback;
import org.xutils.http.RequestParams;
import org.xutils.x;

import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;
import java.util.Set;

import Adapter_package.hor_parts_lists_adapter;
import Inter.Globle;
import Inter.get_net_Info;
import MyView.HorizontalListView;
import MyView.slidedetails.SlideDetailsLayout;
import bean.PartsDetails;
import bean.Recommend;
import utils.JavaScriptObject;
import utils.RequestNet;
import utils.Statubars;

public class PartsDetailsActivity extends AppCompatActivity implements View.OnClickListener , get_net_Info {

    private Banner banner;
    private HorizontalListView horizontal_lv_id;
    private View sets_back_id;
    private String partsDetailsURL= Globle.TEST_URL+"/api/part/getPartInfo";
    private int partId;
    private List<String> imageList;
    private List<PartsDetails.BodyBean.DataBean.PartImListBean> partImList;
    private TextView tv_price_id;
    private TextView tv_model_id;
    private TextView tv_models_id;
    private TagFlowLayout partsWarranty_flowlayout;
    private TagFlowLayout Partsquality_flowlayout;
    private List<PartsDetails.BodyBean.DataBean.PartWarrantyListBean> partWarrantyList;
    private TextView tv_inventory_id;
    private TextView tv_inventory2_id;
    private Recommend recommend;
    private PartsDetails.BodyBean.DataBean partData;
    private TextView tv_consult_id;
    private TextView title_id;
    private TextView tv_description_id;
    private TextView lv_shipeijixing_id;
    GridView gv;
    private TextView tv_yema;
    private ViewPager viewpager;
    View mParent;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        new Statubars().setStatubars(this,getWindow(),"zhaose",getResources().getColor(R.color.statue));
        setContentView(R.layout.activity_parts_details);
        Intent intent=getIntent();
        partId = intent.getIntExtra("partId",0);
        initView();
        setOnclickListener();
        onStartget();
    }

    private void aboutbigPic() {
        mParent = findViewById(R.id.parent);
        tv_yema = (TextView) findViewById(R.id.tv_yema);
        viewpager = (ViewPager) findViewById(R.id.viewpager);
        gv = (GridView) findViewById(R.id.gv);
        gv.setAdapter(new BaseAdapter() {
            @Override
            public int getCount() {
                return imageList.size();
            }

            @Override
            public Object getItem(int position) {
                return null;
            }

            @Override
            public long getItemId(int position) {
                return 0;
            }

            @Override
            public View getView(int position, View convertView, ViewGroup parent) {
                PhotoView p = new PhotoView(PartsDetailsActivity.this);
                p.setLayoutParams(new AbsListView.LayoutParams((int) (getResources().getDisplayMetrics().density * 100), (int) (getResources().getDisplayMetrics().density * 100)));
                p.setScaleType(ImageView.ScaleType.CENTER_CROP);
                Glide.with(parent.getContext()).load(imageList.get(position)).into(p);
                // 把PhotoView当普通的控件把触摸功能关掉
                p.disenable();
                return p;
            }
        });
        gv.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                mParent.setVisibility(View.VISIBLE);
                new Statubars().setStatubars(parent.getContext(),getWindow(),"zhaose",getResources().getColor(R.color.black));
                tv_yema.setText((1+position)+"/"+imageList.size());
                viewpager.setCurrentItem(position,false);
            }
        });
        viewpager.setAdapter(new PagerAdapter() {
            @Override
            public int getCount() {
                return imageList.size();
            }

            @Override
            public boolean isViewFromObject(View view, Object object) {
                return view==object;
            }
            @Override
            public Object instantiateItem(final ViewGroup container, int position) {
                PhotoView view = new PhotoView(PartsDetailsActivity.this);
                view.enable();
                view.setScaleType(ImageView.ScaleType.FIT_CENTER);

                Glide.with(container.getContext()).load(imageList.get(position)).into(view);
                view.setOnClickListener(new View.OnClickListener() {

                    @Override
                    public void onClick(View v) {
                        mParent.setVisibility(View.GONE);
                        new Statubars().setStatubars(container.getContext(),getWindow(),"zhaose",getResources().getColor(R.color.statue));
                        banner.startAutoPlay();
                    }
                });
                container.addView(view);
                return view;
            }

            @Override
            public void destroyItem(ViewGroup container, int position, Object object) {
                container.removeView((View) object);
            }
        });
        viewpager.setOnPageChangeListener(new ViewPager.OnPageChangeListener() {
            @Override
            public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {

            }

            @Override
            public void onPageSelected(int position) {
                tv_yema.setText((1+position)+"/"+imageList.size());
            }

            @Override
            public void onPageScrollStateChanged(int state) {

            }
        });
    }


    public void onStartget() {
        //开始请求商品详情信息
        try {
            org.json.JSONObject jsonObject= new org.json.JSONObject();
            jsonObject.put("memberId",new JavaScriptObject(getApplicationContext()).getMemberid("")+"");
            jsonObject.put("partId",partId);
            RequestParams params=new RequestParams(partsDetailsURL);
            if (jsonObject!=null){
                String jsonString = Base64.encodeToString(jsonObject.toString().getBytes(),Base64.NO_WRAP);//base64加密
                params.setBodyContent(jsonString);
            }
            x.http().post(params, new Callback.CommonCallback<String>() {
                @Override
                public void onSuccess(String result) {
                    Log.i("TAG", "result: "+result);
                    //得到详情数据
                    PartsDetails partsDetails = com.alibaba.fastjson.JSONObject.parseObject(result, PartsDetails.class);
                    if(partsDetails.getBody()!=null&&partsDetails.getBody().getData()!=null){
                        partData = partsDetails.getBody().getData();
                    }else{
                        return;
                    }
                    tv_price_id.setText(partData.getMoneyUnit()+"  "+partData.getPrice());
                    lv_shipeijixing_id.setText(partData.getRemark());
                    tv_model_id.setText(partData.getManufacturerName()+" "+partData.getName());
                    tv_models_id.setText(partData.getManufacturerName()+" "+partData.getName());
                    tv_inventory_id.setText(partData.getInventory()+"台");
                    tv_inventory2_id.setText("库存量  "+partData.getInventory()+"台");
                    tv_description_id.setText(partData.getDescription());
                    partImList = partData.getPartImList();
                    for (int i=0;i<partImList.size();i++){
                        imageList.add(partImList.get(i).getImagePath());
                    }
                    banner.setImages(imageList);
                    banner.start();
                    aboutbigPic();
                    partWarrantyList =partData.getPartWarrantyList();
                    List<String> stringList=new LinkedList<>();
                    for (int i = 0; i< partWarrantyList.size(); i++){
                        if (partWarrantyList.get(i).getNum()==0){
                            stringList.add("无");
                        }else{
                            stringList.add(partWarrantyList.get(i).getNum()+partWarrantyList.get(i).getTypeName());
                        }
                    }
                    int Quality= partData.getQuality();
                    List<String> stringList1=new LinkedList<String>();
                    switch (Quality){
                        case 1:
                            stringList1.add("全新");
                            break;
                        case 2:
                            stringList1.add("二手");
                            break;
                    }

                    aboutTabFlowLyaout(partsWarranty_flowlayout,stringList);
                    aboutTabFlowLyaout(Partsquality_flowlayout,stringList1);

                }

                @Override
                public void onError(Throwable ex, boolean isOnCallback) {

                }

                @Override
                public void onCancelled(CancelledException cex) {

                }

                @Override
                public void onFinished() {
                    //请求为您推荐接口
                    try {
                        JSONObject jsonObject1=new JSONObject();
                        jsonObject1.put("memberId",new JavaScriptObject(getApplicationContext()).getMemberid("")+"");
                        jsonObject1.put("manufacturerId",partData.getManufacturerId());
                        jsonObject1.put("productCategoriesId",partData.getProductCategoriesId());
                        jsonObject1.put("partId",partId);
                        Log.i("TAG", "onFinished: "+jsonObject1);
                        RequestNet.queryServer(jsonObject1,Globle.TEST_URL+"/api/part/getRecommendList",PartsDetailsActivity.this,"PartsDetailsActivity");
                    }catch (Exception e){
                        e.printStackTrace();
                    }
                }
            });

        } catch (JSONException e) {
            e.printStackTrace();
        }
        super.onStart();
    }
    private void aboutTabFlowLyaout(final TagFlowLayout tagFlowLayout, List<String> stringList){
        tagFlowLayout.setAdapter(new TagAdapter<String>(stringList)
        {
            @Override
            public View getView(FlowLayout parent, int position, String s)
            {
                View convertView = LayoutInflater.from(PartsDetailsActivity.this).inflate(R.layout.tag_tview, null);
                TextView tv = (TextView) convertView.findViewById(R.id.flowlayout_tv);
                tv.setText(s);
                return tv;
            }
        });
        tagFlowLayout.setMaxSelectCount(1);//单选模式
        partsWarranty_flowlayout.setOnSelectListener(new TagFlowLayout.OnSelectListener() {
            @Override
            public void onSelected(Set<Integer> selectPosSet) {
                //判断selectPosSet的值可以判断是否取消
                if (selectPosSet.size()==0){
                    //说明取消了值
                }else{
                    Iterator<Integer> it = selectPosSet.iterator();
                    if (it.hasNext()){
                        tv_price_id.setText("¥  "+partWarrantyList.get(it.next()).getPrice());//更新价格
                    }
                }
            }
        });
    }
    private void setOnclickListener() {
        sets_back_id.setOnClickListener(this);
        tv_consult_id.setOnClickListener(this);
    }

    private void initView() {
        lv_shipeijixing_id = (TextView) findViewById(R.id.tv_shipeijixing_id);
        tv_inventory_id = (TextView) findViewById(R.id.tv_inventory_id);
        tv_inventory2_id = (TextView) findViewById(R.id.tv_inventory2_id);
        tv_price_id = (TextView) findViewById(R.id.tv_price_id);
        tv_model_id = (TextView) findViewById(R.id.tv_model_id);
        tv_models_id = (TextView) findViewById(R.id.tv_models_id);
        tv_consult_id = (TextView) findViewById(R.id.tv_consult_id);
        title_id = (TextView) findViewById(R.id.title_id);
        tv_description_id = (TextView) findViewById( R.id.tv_description_id);
        SlideDetailsLayout slidedetails= (SlideDetailsLayout) findViewById(R.id.slidedetails);
        slidedetails.setOnSlideDetailsListener(new  SlideDetailsLayout.OnSlideDetailsListener() {
            @Override
            public void onStatucChanged(SlideDetailsLayout.Status status) {
                if (status== SlideDetailsLayout.Status.OPEN){
                    title_id.setText("图文详情");
                }else{
                    title_id.setText("配件详情");
                }
            }
        });
        //质保按钮
        partsWarranty_flowlayout = (TagFlowLayout) findViewById(R.id.PartsWarranty_flowlayout);
        Partsquality_flowlayout = (TagFlowLayout) findViewById   (R.id.Partsquality_flowlayout);

        sets_back_id = findViewById(R.id.sets_back_id);
        horizontal_lv_id = (HorizontalListView) findViewById(R.id.horizontal_lv_id);
        banner = (Banner) findViewById(R.id.banner);
        //设置图片加载器
        banner.setImageLoader(new GlideImageLoader());
        imageList = new LinkedList<>();
        //设置图片集合
        banner.updateBannerStyle(BannerConfig.NUM_INDICATOR);
        banner.setIndicatorGravity(BannerConfig.RIGHT);
        banner.setDelayTime(2000);
        //banner设置方法全部调用完毕时最后调用
        banner.start();
        banner.setOnBannerListener(new OnBannerListener() {
            @Override
            public void OnBannerClick(int position) {
                mParent.setVisibility(View.VISIBLE);
                new Statubars().setStatubars(banner.getContext(),getWindow(),"zhaose",getResources().getColor(R.color.black));
                tv_yema.setText((1+position)+"/"+imageList.size());
                viewpager.setCurrentItem(position,false);
                banner.stopAutoPlay();//停止自动滑动
            }

        });
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.sets_back_id:
                finish();
                break;
            case R.id.tv_consult_id:
                Intent intent=new Intent(PartsDetailsActivity.this,consultActivity.class);
                intent.putExtra("partId",partId+"");
                startActivity(intent);
                break;
        }
    }
    @Override
    public void onBackPressed() {
        if (mParent.getVisibility()==View.VISIBLE){
            mParent.setVisibility(View.GONE);
            new Statubars().setStatubars(this,getWindow(),"zhaose",getResources().getColor(R.color.statue));
            banner.startAutoPlay();
            return;
        }else{
            finish();
        }
        super.onBackPressed();
    }
    @Override
    public void getinfo(String str) {
          recommend = com.alibaba.fastjson.JSONObject.parseObject(str, Recommend.class);
        List list=new LinkedList();
        if (recommend.getBody()!=null&& recommend.getBody().getData()!=null){
            list.addAll(recommend.getBody().getData());
        }
        //准备适配器
        hor_parts_lists_adapter adapter =new hor_parts_lists_adapter(list,R.layout.item_parts_recommend_layout);
        //绑定适配器
        horizontal_lv_id.setAdapter(adapter);
        horizontal_lv_id.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Intent intent=new Intent(PartsDetailsActivity.this,PartsDetailsActivity.class);
                intent.putExtra("partId", recommend.getBody().getData().get(position).getPartId());
                startActivity(intent);
            }
        });
    }

    public class GlideImageLoader extends ImageLoader {
        @Override
        public void displayImage(Context context, Object path, ImageView imageView) {
            /**
             注意：
             1.图片加载器由自己选择，这里不限制，只是提供几种使用方法
             2.返回的图片路径为Object类型，由于不能确定你到底使用的那种图片加载器，
             传输的到的是什么格式，那么这种就使用Object接收和返回，你只需要强转成你传输的类型就行，
             切记不要胡乱强转！
             */
            //Glide 加载图片简单用法
            Glide.with(context).load(path).into(imageView);

        }
    }
}
