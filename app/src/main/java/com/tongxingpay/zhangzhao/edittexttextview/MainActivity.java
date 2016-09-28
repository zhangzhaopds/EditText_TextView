package com.tongxingpay.zhangzhao.edittexttextview;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.method.HideReturnsTransformationMethod;
import android.text.method.PasswordTransformationMethod;
import android.util.Log;
import android.view.KeyEvent;
import android.view.View;
import android.view.inputmethod.InputMethodManager;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {

    private EditText inputView;
    private TextView showView;
    private CheckBox checkView;
    private Button openKeyBoard;
    private Button closeKeyBoard;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        inputView = (EditText)findViewById(R.id.main_editText);
        showView = (TextView)findViewById(R.id.main_showTextView);
        checkView = (CheckBox)findViewById(R.id.main_checkbox);
        openKeyBoard = (Button)findViewById(R.id.main_openKeyboard);
        closeKeyBoard = (Button)findViewById(R.id.main_closeKeyboard);

        inputView.setOnKeyListener(new View.OnKeyListener() {
            @Override
            public boolean onKey(View v, int keyCode, KeyEvent event) {
                showView.setText(inputView.getText().toString());

                Log.i("MainActivity", "onKey: " + keyCode);
                // 监听enter点击事件
                if (keyCode == event.KEYCODE_ENTER && event.getAction() == event.ACTION_UP) {
                    Log.i("MainActivity", "onKey: 点击enter,松手");
                }
                return false;
            }
        });

        // 开启/关闭密文模式
        checkView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (checkView.isChecked()) {
                    inputView.setTransformationMethod(PasswordTransformationMethod.getInstance());
                } else {
                    inputView.setTransformationMethod(HideReturnsTransformationMethod.getInstance());
                }
            }
        });

        // 开启键盘
        openKeyBoard.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                InputMethodManager keyManager = (InputMethodManager)getSystemService(INPUT_METHOD_SERVICE);
                keyManager.showSoftInput(inputView, 0);
            }
        });

        // 关闭键盘
        closeKeyBoard.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                InputMethodManager keyManager = (InputMethodManager)getSystemService(INPUT_METHOD_SERVICE);
                keyManager.hideSoftInputFromWindow(inputView.getWindowToken(), 0);
            }
        });
    }
}
