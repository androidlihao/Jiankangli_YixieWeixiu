package utils;

import android.content.Context;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;

import com.bumptech.glide.Glide;
import com.example.hs.jiankangli_example1.R;
import com.yuyh.library.imgsel.ImageLoader;
import com.zhy.autolayout.AutoFrameLayout;

import java.util.LinkedList;

/**
 * Created by 李浩 on 2016/10/17.
 */
//自定义适配器
public class addAdapter extends BaseAdapter {
    //上下文对象
    private Context context;
    //用来接收数据源
    private  LinkedList<String> urlList;

    private View view;
    //得到数据源
    public addAdapter(Context context, LinkedList<String> urlList){
        this.context = context;
        this.urlList=urlList;
    }
    public int getCount() {
        Log.i("TAG", "getCount: "+urlList.size());
        return urlList.size();//子项的个数
    }
    public Object getItem(int item) {
        return item;
    }
    public long getItemId(int id) {
        return id;
    }

    @Override
    public View getView(final int i, View convertView, final ViewGroup parent) {
        LayoutInflater inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        view = inflater.inflate(R.layout.addimageview_layout, null);
        ImageView imageView= (ImageView) view.findViewById(R.id.iv_ids);
        AutoFrameLayout iv_delte_id= (AutoFrameLayout) view.findViewById(R.id.iv_delte_id);
        imageView.setScaleType(ImageView.ScaleType.CENTER_CROP);//让图片铺满整张图
        //然后从数据源中取值复制
        iv_delte_id.setVisibility(View.GONE);
        if(!urlList.get(i).equals("tianjia")){
            loader.displayImage(context,urlList.get(i),imageView);//当数据源的第i项不是tianjia的时候，此时的ImageView为背景图
        }else if(urlList.get(i).equals("tianjia"))//当数据源的第i项是tianjia的时候，此时的imageView是添加
        {
            imageView.setImageDrawable(context.getResources().getDrawable(R.mipmap.tianjiatupian23x));
        }
        return view;//返回已经填充好了的imageview
    }
    // 自定义图片加载器
    private ImageLoader loader = new ImageLoader() {
        @Override
        public void displayImage(Context context, String path, ImageView imageView) {
            // TODO 在这边可以自定义图片加载库来加载ImageView，例如Glide、Picasso、ImageLoader等
            Glide.with(context).load(path).into(imageView);
        }
    };
}

