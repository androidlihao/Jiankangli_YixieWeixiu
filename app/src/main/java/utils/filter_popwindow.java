package utils;

import android.app.Activity;
import android.graphics.drawable.ColorDrawable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.PopupWindow;
import android.widget.TextView;

import com.example.hs.jiankangli_example1.R;

public  class filter_popwindow extends PopupWindow {
    private View mainView;
    private TextView tv_knowledg_id, tv_ask_id,tv_news_id,tv_all_kind_id;

    public filter_popwindow(Activity paramActivity, View.OnClickListener paramOnClickListener){
        super(paramActivity);
        //窗口布局
        mainView = LayoutInflater.from(paramActivity).inflate(R.layout.filter_layout, null);
        //全部
        tv_all_kind_id = (TextView) mainView.findViewById(R.id.tv_all_kind_id);
        //知识库
        tv_knowledg_id = (TextView)mainView.findViewById(R.id.tv_knowledg_id);
        //技术问答
        tv_ask_id = (TextView)mainView.findViewById(R.id.tv_ask_id);
        //信息发布
        //tv_push_id = (TextView) mainView.findViewById(R.id.tv_push_id);
        //新闻资讯
        tv_news_id = (TextView) mainView.findViewById(R.id.tv_news_id);
        //设置每个子布局的事件监听器
        if (paramOnClickListener != null){
            tv_news_id.setOnClickListener(paramOnClickListener);
            //tv_push_id.setOnClickListener(paramOnClickListener);
            tv_ask_id.setOnClickListener(paramOnClickListener);
            tv_knowledg_id.setOnClickListener(paramOnClickListener);
            tv_all_kind_id.setOnClickListener(paramOnClickListener);
        }
        setContentView(mainView);
        //设置宽度
        setWidth(ViewGroup.LayoutParams.WRAP_CONTENT);
        //设置高度
        setHeight(ViewGroup.LayoutParams.WRAP_CONTENT);
        //设置显示隐藏动画
        //setAnimationStyle(R.anim);
        //设置背景透明
        setBackgroundDrawable(new ColorDrawable(0));
    }
}