package com.example.mom;
import android.content.Context;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import com.google.android.material.tabs.TabLayout;

public class Tab_Fragment_3 extends Fragment {
    //객체 선언
    Button button4;
    TextView textView3;

    MainActivity activity;
    String sendData, receiveData;
    StudentDTO student3;

    Context context;

    @Override
    public void onAttach(@NonNull Context context) {
        super.onAttach(context);
        this.context = context;

        //메시지 송수신에 필요한 객체 초기화
        activity = (MainActivity) getActivity();
        sendData = "프래그먼트3에서 보낸 데이터입니다.";
        receiveData = "";
//        student3 = new StudentDTO("PARK", 31);

    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        ViewGroup rootView = (ViewGroup) inflater.inflate(R.layout.tabfragment3, container, false);

        //객체 초기화
        textView3 = rootView.findViewById(R.id.textView3);
        button4 = rootView.findViewById(R.id.button4);

        //텍스트뷰3에 프래그먼트 2에서 보낸 데이터 받기
        if(activity.mBundle != null) {
            Bundle bundle = activity.mBundle;
            receiveData = bundle.getString("sendData");
            StudentDTO student2 = (StudentDTO) bundle.getSerializable("student2");
//            String name = student2.getName();
//            int age = student2.getAge();
//            int index = bundle.getInt("index");
//
//            textView3.append(receiveData + "\n");
//            textView3.append("name : " + name + "\nage : " + age + "\nindex : " + index);
            activity.mBundle = null;
        }

        //버튼 4에 기능 추가 : 프래그먼트 1로 데이터 보내기
        button4.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Bundle bundle = new Bundle();
                bundle.putString("sendData", sendData);
                bundle.putSerializable("student3", student3);
                bundle.putInt("index", 2);

                activity.fragBtnClick(bundle);

                //첫 번째 탭을 선택
                TabLayout.Tab tab = activity.tabs.getTabAt(0);
                tab.select();
            }
        });

        return rootView;
    }
}