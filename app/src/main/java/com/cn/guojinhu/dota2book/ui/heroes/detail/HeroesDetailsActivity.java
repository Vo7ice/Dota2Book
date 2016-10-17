package com.cn.guojinhu.dota2book.ui.heroes.detail;

import android.graphics.Bitmap;
import android.os.Bundle;
import android.support.v4.view.PagerAdapter;
import android.support.v4.view.ViewPager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.cn.guojinhu.dota2book.IService.IDota2HeroService;
import com.cn.guojinhu.dota2book.R;
import com.cn.guojinhu.dota2book.base.BaseActivity;
import com.cn.guojinhu.dota2book.bean.Hero;
import com.cn.guojinhu.dota2book.utils.ImageLoader;
import com.cn.guojinhu.dota2book.utils.JsonUtils;
import com.cn.guojinhu.dota2book.view.ZoomView;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import rx.Observable;
import rx.android.schedulers.AndroidSchedulers;
import rx.functions.Action1;
import rx.functions.Func1;
import rx.schedulers.Schedulers;

public class HeroesDetailsActivity extends BaseActivity  {


    private ViewPager mViewPager;

    private TextView mTextView;


    private List<Hero> mData=new ArrayList<>();

    private ViewPagerAdapter mAdapter;

    private int position;

    private int viewPagerWieht;
    private int viewPagerHeight;

    private ImageLoader mImageLoader =ImageLoader.getIntance();


    private IDota2HeroService service;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
//        requestWindowFeature(Window.FEATURE_NO_TITLE);
        setContentView(R.layout.activity_heroes_details);

        initViews();
        initData();
        initListener();

    }

    @Override
    public void initViews() {

        mAdapter=new ViewPagerAdapter();
        mViewPager= (ViewPager) findViewById(R.id.pager_view);
        mTextView= (TextView) findViewById(R.id.pager_text);

        mViewPager.setAdapter(mAdapter);

        setText(0,0);

    }

    @Override
    public void onWindowFocusChanged(boolean hasFocus) {
        super.onWindowFocusChanged(hasFocus);

    }

    @Override
    public void initListener() {

        mViewPager.setOnPageChangeListener(new ViewPager.OnPageChangeListener() {
            @Override
            public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {

            }

            @Override
            public void onPageSelected(int position) {

                setText(position+1,mData.size());
            }

            @Override
            public void onPageScrollStateChanged(int state) {

            }
        });
    }

    @Override
    public void initData() {

        position=getIntent().getIntExtra("position",0);

        getDataAll();
        mViewPager.setCurrentItem(position);

    }

    private void setText(int position,int size){
        if(0==size){
            mTextView.setText(0+"/"+size);
        }else{
            mTextView.setText(position+"/"+size);
        }
    }

    private void getDataAll(){

        Observable.just("")
                .flatMap(new Func1<String, Observable<Hero>>() {
                    @Override
                    public Observable<Hero> call(String s) {
                        List<Hero> list=null;
                        try {
                            list=JsonUtils.getHeroesFromAssets(HeroesDetailsActivity.this);
                        } catch (IOException e) {
                            e.printStackTrace();
                        }
                        if(null!=list){
                            final List<Hero> finalList = list;
                            runOnUiThread(new Runnable() {
                                @Override
                                public void run() {
                                    mData= finalList;
                                    mAdapter.notifyDataSetChanged();
                                }
                            });
                        }
                        return Observable.from(list);
                    }
                })


                .map(new Func1<Hero, Map<String,Bitmap>>() {
                    @Override
                    public Map<String,Bitmap> call(Hero hero) {
                        return mImageLoader.getMapByHoverLarge(hero.HoverLarge);
                    }
                })
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Action1<Map<String,Bitmap>>() {
                    @Override
                    public void call(Map<String,Bitmap> map) {
                        if(!map.isEmpty()){
                            ImageLoader.getIntance().putBitMapCache(map);
                            mAdapter.notifyDataSetChanged();
                        }
                    }
                });
    }

    private void getDataByHoverLarge(final String HoverLarge){

        Observable.just(HoverLarge)
                .map(new Func1<String, Map<String,Bitmap>>() {
                    @Override
                    public Map<String, Bitmap> call(String s) {
                        return mImageLoader.getMapByHoverLarge(HoverLarge);
                    }
                })
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Action1<Map<String,Bitmap>>() {
                    @Override
                    public void call(Map<String,Bitmap> map) {
                        if(null!=map){
                            ImageLoader.getIntance().putBitMapCache(map);
                            mAdapter.notifyDataSetChanged();
                        }
                    }
                });
    }

    class ViewPagerAdapter extends PagerAdapter{

        @Override
        public Object instantiateItem(ViewGroup container, int position) {
            ZoomView zoomView= (ZoomView) LayoutInflater.from(HeroesDetailsActivity.this).inflate(R.layout.item_heroes_details,null);

            setBitmap(zoomView,position);

            container.addView(zoomView);

            return zoomView;
        }

        @Override
        public int getCount() {
            return mData.size();
        }

        @Override
        public boolean isViewFromObject(View view, Object object) {
            return view==object;
        }

        @Override
        public void destroyItem(ViewGroup container, int position, Object object) {
            View view=(View)object;
            container.removeView(view);
        }
    }

    private void setBitmap(ZoomView zoomView,int position) {

        String sHoverLarge=mData.get(position).HoverLarge;
        Bitmap bitmap= ImageLoader.getIntance().getBitMapByKey(sHoverLarge);

        if(null!=bitmap){
            zoomView.setImageBitmap(bitmap);
        }else{
            getDataByHoverLarge(sHoverLarge);
        }
    }


}
