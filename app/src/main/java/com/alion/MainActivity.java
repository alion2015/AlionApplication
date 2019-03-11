package com.alion;

import android.annotation.TargetApi;
import android.database.ContentObserver;
import android.net.Uri;
import android.os.Build;
import android.os.Handler;
import android.os.Message;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import com.alion.myapplication.R;

/**
 * ContentObserver 可以用来监听任何数据的变化
 */
public class MainActivity extends AppCompatActivity {

    private String TAG = "MainActivity";
    private ContentObserver observer;
    private Uri uri = Uri.parse("content://.test.a");  ;
    private Button mButton;
    float percent = 0;
    MyText myText;
    @TargetApi(Build.VERSION_CODES.LOLLIPOP)
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        myText = (MyText)findViewById(R.id.mytext1);

       mButton = (Button)findViewById(R.id.button);
        mButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                myHandler.sendEmptyMessageDelayed(0,200);
            }
        });
        /* SignCheck signCheck = new SignCheck(this,"72:5F:E6:2B:53:7B:3C:5B:C9:2C:FC:AC:D2:EC:63:B7:23:9B:06:06");
        if(signCheck.check()) {
            //TODO 签名正常
        }else                {
            //TODO 签名不正确
            new AlertDialog.Builder(this).setMessage("请前往官方渠道下载正版 app， http://.....").setPositiveButton("确定", new DialogInterface.OnClickListener() {
                @Override
                public void onClick(DialogInterface dialog, int which) {
                    System.exit(0);
                }
            }).show();
        }

        ViewOutlineProvider viewOutlineProvider1 =
                new ViewOutlineProvider() {

                    @Override
                    public void getOutline(View view, Outline outline) {
                        //修改outline为特定形状
                        outline.setRoundRect(0, 0,
                                view.getWidth(),
                                view.getHeight(), 90);
                    }
                };
        mButton.setOutlineProvider(viewOutlineProvider1);*/
       /* findViewById(R.id.button).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                new TimeCount(500000, 1000, new TimeCount.TimerCallback() {
                @Override
                public void onFinish() {
                    Log.i(TAG, "onFinish" + 1);
                }

                @Override
                public void onTick(long millisUntilFinished) {
                    Log.i(TAG, "onTick" + (1-millisUntilFinished/5000f));

                    sendBroadcast(new Intent("com.alion.timeclick"));
                }
            }).start();
            }
        });*/
    }
    Handler myHandler = new Handler(){
        @Override
        public void handleMessage(Message msg) {
            percent+=0.001;
            myText.setStr("测试代码",percent);
            myHandler.sendEmptyMessageDelayed(0,60);
            super.handleMessage(msg);
        }
    };
}
