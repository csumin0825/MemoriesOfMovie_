package com.example.mom;

import java.io.Serializable;

public class MovieItem implements Serializable {
    String title,content;
    int mYear, mMonth, mDay;
    float rate;

    public MovieItem(String title,int mYear,int mMonth,int mDay, float rate, String content){
        this.title = title;
        this.mYear = mYear;
        this.mMonth = mMonth;
        this.mDay = mDay;
        this.rate = rate;
        this.content = content;
    }

    public String getTitle(){
        return this.title;
    }
    public int getYear(){return this.mYear;}
    public int getMonth(){return this.mMonth;}
    public int getDay(){return this.mDay;}
    public float getRate(){return this.rate;}
    public String getRateString(){return Float.toString(this.rate);}
    public String getContent(){return this.content;}
}
