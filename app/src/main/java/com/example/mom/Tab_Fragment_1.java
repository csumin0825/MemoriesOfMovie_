package com.example.mom;

import android.app.Activity;
import android.app.DatePickerDialog;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.RatingBar;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import com.google.android.material.tabs.TabLayout;

import java.io.Serializable;

public class Tab_Fragment_1 extends Fragment {
    //객체 선언
    Button button2, datePickBtn;
    TextView dateText;

    MainActivity activity;
    String sendData, receiveData;
    MovieItem movieItem1;

    Context context;

    int mYear, mMonth, mDay;
    RatingBar ratingStar;
    EditText titleText, contentText;

    //onAttach : 프래그먼트와 액티비티가 연결될때 호출되는 메서드
    //onDetach : 프래그먼트와 액티비티의 연결이 끊길때 호출되는 메서드
    @Override
    public void onAttach(@NonNull Context context) {
        super.onAttach(context);
        this.context = context;

        //메시지 송수신에 필요한 객체 초기화
        activity = (MainActivity) getActivity();
        sendData = "프래그먼트1에서 보낸 데이터입니다.";
        receiveData = "";
//        student1 = new StudentDTO("KIM", 25);
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        //XML, java 연결
        //XML이 메인에 직접 붙으면 true, 프래그먼트에 붙으면 false
        ViewGroup rootView = (ViewGroup) inflater.inflate(R.layout.tabfragment1, container, false);

        //객체 초기화
        dateText = rootView.findViewById(R.id.textView1);
        button2 = rootView.findViewById(R.id.button2);
        datePickBtn= rootView.findViewById(R.id.datePicker);
        titleText= rootView.findViewById(R.id.titleText);
        ratingStar= rootView.findViewById(R.id.ratingStar);
        contentText = rootView.findViewById(R.id.contentText);



        DatePickerDialog.OnDateSetListener dateSetListener = new DatePickerDialog.OnDateSetListener() {
            @Override
            public void onDateSet(DatePicker view, int year, int month, int dayOfMonth) { //날짜 선택 후 실행되는 메서드
                mYear = year;
                mMonth = month;
                mDay = dayOfMonth;
                Toast.makeText(getActivity().getApplicationContext(), year+"/"+month+"/"+dayOfMonth, Toast.LENGTH_SHORT).show();
                dateText.setText(": "+year+"/"+month+"/"+dayOfMonth);
            }
        };

        datePickBtn.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v){ // 버튼 클릭시
                Toast.makeText(getActivity().getApplicationContext(), "짧게 HELLO", Toast.LENGTH_SHORT).show();

                DatePickerDialog datePickerDialog = new DatePickerDialog(getContext(), dateSetListener, 2022, 11, 29);
                datePickerDialog.show();
            }
        });

        //텍스트뷰1에 프래그먼트 3에서 보낸 데이터 받기
        if(activity.mBundle != null) {
            Bundle bundle = activity.mBundle;
            receiveData = bundle.getString("sendData");
            StudentDTO student3 = (StudentDTO) bundle.getSerializable("student3");
            String title = student3.getTitle();
            int year = student3.getYear();

            int index = bundle.getInt("index");

            activity.mBundle = null;
        }

        //버튼2에 기능 추가 : 프래그먼트 2로 데이터 보내기
        button2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String title = titleText.getText().toString();
                float rate = ratingStar.getRating();
                String content = contentText.getText().toString();
                movieItem1 = new MovieItem(title,mYear,mMonth,mDay,rate,content);


                Bundle bundle = new Bundle();
                bundle.putString("sendData", sendData);
                //bundle.putSerializable() : 객체를 보낼때 사용함
                bundle.putSerializable("movieItem1", movieItem1);
                bundle.putInt("index", 0);

                activity.fragBtnClick(bundle);

                //두 번째 탭을 선택(첫 번째 탭은 index가 0)
                TabLayout.Tab tab = activity.tabs.getTabAt(1);
                tab.select();
            }
        });

        return rootView;
    }
}