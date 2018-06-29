package utils;

import android.app.Dialog;
import android.content.Context;
import android.view.View;
import com.example.hs.jiankangli_example1.R;

/**
 * Created by 李浩 on 2016/12/7.
 */
public class give_up_push {

    public static Dialog show_Dialog(Context context, View.OnClickListener myOnListener){
        Dialog mDialog = new Dialog(context, R.style.myDialogTheme2);
        mDialog.setContentView(R.layout.give_up_push_layout);
        mDialog.setCanceledOnTouchOutside(false);
        mDialog.findViewById(R.id.tv_cancle_id).setOnClickListener(myOnListener);
        mDialog.findViewById(R.id.tv_close_id).setOnClickListener(myOnListener);
        return mDialog;
    }
}
