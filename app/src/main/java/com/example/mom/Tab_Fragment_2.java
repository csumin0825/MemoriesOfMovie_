package com.example.mom;

import android.app.Activity;
import android.content.Context;
import android.media.session.PlaybackState;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.CursorAdapter;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import com.google.android.material.tabs.TabLayout;

import java.util.ArrayList;
import java.util.List;

public class Tab_Fragment_2 extends Fragment {
    //객체 선언
    Button button3;
    TextView textView2;

    MainActivity activity;
    String sendData, receiveData;
    StudentDTO student2;

    Context context;

    ArrayList<MovieItem> movieList;
    ListView customListView;

    private static CustomAdapter customAdapter;

    //onAttach : 프래그먼트와 액티비티가 연결될때 호출되는 메서드
    //onDetach : 프래그먼트와 액티비티의 연결이 끊길때 호출되는 메서드
    @Override
    public void onAttach(@NonNull Context context) {
        super.onAttach(context);
        this.context = context;

        //메시지 송수신에 필요한 객체 초기화
        activity = (MainActivity) getActivity();
        sendData = "프래그먼트2에서 보낸 데이터입니다.";
        receiveData = "";
//        student2 = new StudentDTO("LEE", 22);
    }


    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        //XML, java 연결
        //XML이 메인에 직접 붙으면 true, 프래그먼트에 붙으면 false
        ViewGroup rootView = (ViewGroup) inflater.inflate(R.layout.tabfragment2, container, false);
//        ListView listView = rootView.findViewById(R.id.listview);
//        List<String> list = new ArrayList();

        movieList = new ArrayList<>();
//        movieList.add(new MovieItem("1",2022,12,21,3,"1번"));

        customListView = (ListView) rootView.findViewById(R.id.listview);
        customAdapter = new CustomAdapter(getContext(),movieList);
        customListView.setAdapter(customAdapter);

//        customListView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
//            @Override
//            public void onItemClick(AdapterView<?> parent, View view, int position, long l) {
//                //각 아이템을 분간 할 수 있는 position과 뷰
//                String selectedItem = (String) view.findViewById(R.id.movieTitle).getTag().toString();
//                Toast.makeText(getContext(), "Clicked: " + position +" " + selectedItem, Toast.LENGTH_SHORT).show();
//            }
//        });

        //객체 초기화
        textView2 = rootView.findViewById(R.id.textView2);
        button3 = rootView.findViewById(R.id.button3);

        //텍스트뷰2에 프래그먼트 1에서 보낸 데이터 받기
        if(activity.mBundle != null) {
            Bundle bundle = activity.mBundle;
//            receiveData = bundle.getString("sendData");
            StudentDTO student1 = (StudentDTO) bundle.getSerializable("student1");
            String title = student1.getTitle();
            int mYear = student1.getYear();
            int mMonth = student1.getMonth();
            int mDay = student1.getDay();
            float rate = student1.getRate();
            String content = student1.getContent();
//            list.add(new MovieItem(title,mYear,mMonth,mDay,rate,content));
             movieList.add(new MovieItem(title,mYear,mMonth,mDay,rate,content));

            int index = bundle.getInt("index");

//            textView2.append(receiveData + "\n");
            textView2.append("title : " + title + "\nyear : " + mYear + "\nmonth : " + mMonth);
            textView2.append("\nday : " + mDay + "\nrate : " + rate + "\ncontent : " + content);
            activity.mBundle = null;

        }


//        ArrayAdapter<String> adapter = new ArrayAdapter<String>(getActivity().getApplicationContext(), android.R.layout.simple_list_item_1,list);
//        listView.setAdapter(adapter);

        //버튼 3에 기능 추가 : 프래그먼트 3으로 데이터 보내기
        button3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Bundle bundle = new Bundle();
//                bundle.putString("sendData", sendData);
                //bundle.putSerializable() : 객체를 보낼때 사용함
                bundle.putSerializable("student2", student2);
                bundle.putInt("index", 1);

                activity.fragBtnClick(bundle);

                //세 번째 탭을 선택(두 번째 탭은 index가 1)
                TabLayout.Tab tab = activity.tabs.getTabAt(2);
                tab.select();
            }
        });

        return rootView;
    }

    public void InitializeMovieData(){
        movieList = new ArrayList<MovieItem>();
        movieList.add(new MovieItem("HI",2022,12,25,3,"THIS"));
    }
}