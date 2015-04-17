package com.example.usr0200467.listviewsample;

import android.content.Context;
import android.util.AttributeSet;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.example.usr0200467.listviewsample.dummy.DummyContent;

public class ItemLayout extends RelativeLayout {
    private static final String TAG = ItemLayout.class.getSimpleName();
    private final ItemLayout self = this;

    TextView id;
    TextView content;
    ImageView imageView1;

    public ItemLayout(Context context, AttributeSet attrs) {
        super(context, attrs);
    }

    @Override
    protected void onFinishInflate() {
        super.onFinishInflate();

        id = (TextView)findViewById(R.id.id);
        content = (TextView)findViewById(R.id.content);
        imageView1 = (ImageView) findViewById(R.id.imageView1);

    }


    public  void  bindView(Weather.Detail dummyItem){

        id.setText("日付:" + dummyItem.getDt());
        //dt getTemp().getMax()
        //TextView text2 = (TextView) view.findViewById(R.id.content);
        content.setText("最高気温:" + dummyItem.getTemp().getMax());
    }

}
