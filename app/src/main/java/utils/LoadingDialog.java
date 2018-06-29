package utils;

import android.app.Dialog;
import android.content.Context;
import android.os.Bundle;
import com.example.hs.jiankangli_example1.R;


public class LoadingDialog extends Dialog {

	public LoadingDialog(Context context) {
		super(context, R.style.myDialogTheme2);
	}
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.loading_layout);
	}
}

