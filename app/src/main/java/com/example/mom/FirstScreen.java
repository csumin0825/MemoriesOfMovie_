package com.example.mom;


import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.view.MotionEvent;
import android.widget.Toast;

public class FirstScreen extends Activity {
    public void onCreate(Bundle savedInstanceState){
        super.onCreate(savedInstanceState);
        setContentView(R.layout.firstscreen);
    }
    public boolean onTouchEvent(MotionEvent event) {
        if (event.getAction() == MotionEvent.ACTION_UP) {
            new Handler().postDelayed(new Runnable()
            {
                @Override
                public void run(){//딜레이 후 시작할 코드 작성
                    Toast.makeText(getApplicationContext(), "짧게 출력 Hello World!", Toast.LENGTH_SHORT).show();
                    Intent intent = new Intent(getApplicationContext(), MainActivity.class);
                    startActivity(intent);
                }
            }, 1000);
        }return true;
    }
}
