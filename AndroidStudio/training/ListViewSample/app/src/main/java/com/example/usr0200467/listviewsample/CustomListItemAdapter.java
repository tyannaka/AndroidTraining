package com.example.usr0200467.listviewsample;

import android.content.ClipData;
import android.content.Context;
import android.graphics.Bitmap;
import android.media.Image;
import android.util.LruCache;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.List;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.toolbox.ImageLoader;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.Volley;
import com.example.usr0200467.listviewsample.dummy.DummyContent;

import org.json.JSONObject;


public class CustomListItemAdapter extends ArrayAdapter<Weather.Detail>{
    private static final String TAG = CustomListItemAdapter.class.getSimpleName();
    private final CustomListItemAdapter self = this;

    private LayoutInflater mLayoutInflater;
    // シングルトン
    private RequestQueue mQueue;
    private ImageLoader mImageLoader;

    public CustomListItemAdapter(Context context, List<Weather.Detail> objects) {
        // 第2引数はtextViewResourceIdとされていますが、カスタムリストアイテムを使用する場合は特に意識する必要のない引数です
        super(context, 0, objects);
        // レイアウト生成に使用するインフレーター
        mLayoutInflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        mQueue = Volley.newRequestQueue(context);
        mImageLoader = new ImageLoader(mQueue, new LruCacheSample());



    }



    @Override
    public View getView(int position, View convertView, ViewGroup parent) {

//        ItemLayout itemLayout;
        View view = null;
        ViewHolder viewHolder;

        // ListViewに表示する分のレイアウトが生成されていない場合レイアウトを作成する
        if (convertView == null) {
            // レイアウトファイルからViewを生成する

            view = mLayoutInflater.inflate(R.layout.list, parent, false);
            viewHolder = new ViewHolder();
            viewHolder.text1 = (TextView) view.findViewById(R.id.id);
            viewHolder.text2 = (TextView) view.findViewById(R.id.content);
            viewHolder.imageView1 = (ImageView)view.findViewById(R.id.imageView1);
            view.setTag(viewHolder);

//            レイアウトファイルからViewを生成する
//            view = mLayoutInflater.inflate(R.layout.list, parent, false);
//
//            リストアイテムに対応するデータを取得する****************
//            String item = getItem(position);
//
//            各Viewに表示する情報を設定
//            TextView text1 = (TextView) view.findViewById(R.id.id);
//            text1.setText("Title:" + item);
//            TextView text2 = (TextView) view.findViewById(R.id.content);
//            text2.setText("SubTitle:" + item);
//            itemLayout = (ItemLayout) mLayoutInflater.inflate(R.layout.list_2, parent, false);

        } else {
            // レイアウトが存在する場合は再利用する
            view = convertView;
            viewHolder = (ViewHolder)view.getTag();
//            itemLayout = (ItemLayout) convertView;

        }

        //DummyContent.DummyItem dummyItem = getItem(position);
         // Weather.Detail.WeatherDetail weatherDetail = getItem(position);

         Weather.Detail dummyItem = getItem(position);

//         itemLayout.bindView(dummyItem);


        //view.getTag();
        // 各Viewに表示する情報を設定
//        TextView text1 = (TextView) view.findViewById(R.id.id);
//        text1.setText("Title:" + DummyItem.getId());
//        TextView text2 = (TextView) view.findViewById(R.id.content);
//        text2.setText("SubTitle:" + DummyItem.getContent());

        //TextView text1 = (TextView) view.findViewById(R.id.id);
        viewHolder.text1.setText("日付:" + dummyItem.getDt());
        //TextView text2 = (TextView) view.findViewById(R.id.content);
        viewHolder.text2.setText("最高気温:" + dummyItem.getTemp().getMax());

        ImageLoader.ImageListener listener = ImageLoader.getImageListener(viewHolder.imageView1,
                android.R.drawable.spinner_background,
                android.R.drawable.ic_dialog_alert
                );
        mImageLoader.get("http://openweathermap.org/img/w/" + dummyItem.getWeather().get(0).getIcon() + "png",listener);

        return view;
    }

    static class ViewHolder{
        // このView
        TextView text1;
        TextView text2;
        ImageView imageView1;

    }

    public class LruCacheSample implements ImageLoader.ImageCache

    {

        private LruCache<String, Bitmap> mMemoryCache;

        LruCacheSample(){
            int maxMemory = (int) (Runtime.getRuntime().maxMemory() / 1024);
            int cacheSize = maxMemory / 8;       // 最大メモリに依存
            // int cacheSize = 5 * 1024 * 1024;  // 5MB

            mMemoryCache = new LruCache<String, Bitmap>(cacheSize) {
                @Override
                protected int sizeOf(String key, Bitmap bitmap) {
                    // 使用キャッシュサイズ(KB単位)
                    return bitmap.getByteCount() / 1024;
                }
            };
        }

        // ImageCacheのインターフェイス実装
        @Override
        public Bitmap getBitmap(String url) {
            return mMemoryCache.get(url);
        }

        @Override
        public void putBitmap(String url, Bitmap bitmap) {
            mMemoryCache.put(url,bitmap);
        }
    }

}
