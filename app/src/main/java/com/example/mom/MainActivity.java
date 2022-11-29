package com.example.mom;

import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.fragment.app.Fragment;

import android.os.Bundle;
import android.util.Log;

import com.google.android.material.tabs.TabLayout;

public class MainActivity extends AppCompatActivity {
    //로그캣 사용 준비
    private static final String TAG = "MainActivity";

    //객체 선언
    Toolbar toolbar;
    TabLayout tabs;
    Tab_Fragment_1 fragment1;
    Tab_Fragment_2 fragment2;
    Tab_Fragment_3 fragment3;

    Fragment selected = null;

    Bundle mBundle; //main bundle

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        //툴바 설정
//        toolbar = findViewById(R.id.toolbar);
//      setSupportActionBar(toolbar);

        //액션바 설정
        ActionBar actionBar = getSupportActionBar();
        actionBar.setDisplayShowTitleEnabled(false);
        actionBar.hide();

        //프래그먼트 초기화
        fragment1 = new Tab_Fragment_1();
        fragment2 = new Tab_Fragment_2();
        fragment3 = new Tab_Fragment_3();

        //프래그먼트 넣기(바꾸기)
        getSupportFragmentManager().beginTransaction().replace(R.id.contatiner, fragment1).commit();

        //탭 만들기 (동적)
        tabs = findViewById(R.id.tabs);
        tabs.addTab(tabs.newTab().setText("리뷰 작성"));
        tabs.addTab(tabs.newTab().setText("나의 리뷰"));
        tabs.addTab(tabs.newTab().setText("일정"));

        //탭이 선택되었을때 작동하는 메서드
        tabs.setOnTabSelectedListener(new TabLayout.OnTabSelectedListener() {
            @Override
            public void onTabSelected(TabLayout.Tab tab) {
                int position = tab.getPosition();
                Log.d(TAG, "선택된 탭 : " + position);

                if (position == 0) {
                    selected = fragment1;
                } else if (position == 1) {
                    selected = fragment2;
                } else if (position == 2) {
                    selected = fragment3;
                }

                //선택된 프래그먼트로 바꾸기
                getSupportFragmentManager().beginTransaction().replace(R.id.contatiner, selected).commit();
            }

            @Override
            public void onTabUnselected(TabLayout.Tab tab) {
            }
            @Override
            public void onTabReselected(TabLayout.Tab tab) {

            }
        });
    }//onCreate()

    public void fragBtnClick(Bundle bundle) {
        this.mBundle = bundle;
    } //fragBtnClcick()

} //MainActivity