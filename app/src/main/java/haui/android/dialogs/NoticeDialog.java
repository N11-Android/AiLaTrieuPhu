package haui.android.dialogs;

import android.app.Dialog;
import android.content.Context;
import android.os.Bundle;
import android.view.View;
import android.view.Window;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;

import haui.android.R;
import haui.android.fragments.HomeFragment;


public class NoticeDialog extends Dialog implements View.OnClickListener {
    private Button btnCancle;
    private Button btnOk;
    private TextView tvNotice;

    public NoticeDialog(Context context) {
        super(context);
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        setCancelable(true);
        setContentView(R.layout.notice_dialog);

        btnCancle = (Button) findViewById(R.id.btn_score_cancel);
        btnOk = (Button) findViewById(R.id.btn_ok);
        tvNotice = (TextView) findViewById(R.id.tv_notice);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        getWindow().setLayout(
                LinearLayout.LayoutParams.MATCH_PARENT,
                LinearLayout.LayoutParams.WRAP_CONTENT);
    }

    public void setNotification(String notification, String textOk, String textCancle, View.OnClickListener onClickListener) {

        btnOk.setText(textOk);
        tvNotice.setText(notification);
        btnCancle.setText(textCancle);
        btnOk.setOnClickListener(onClickListener);
        btnCancle.setOnClickListener(onClickListener);

        if(textCancle == null) btnCancle.setVisibility(View.GONE);
    }

    @Override
    public void onClick(View v) {
        if(v.getId() == R.id.btn_score_cancel){
            Thread.currentThread().interrupt();
            dismiss();
        }
    }
}
