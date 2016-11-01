package com.cn.guojinhu.dota2book.ui.heroes.gallery;

import android.graphics.Bitmap;
import android.os.Bundle;
import android.support.v4.view.PagerAdapter;
import android.support.v4.view.ViewPager;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import android.widget.TextView;

import com.cn.guojinhu.dota2book.R;
import com.cn.guojinhu.dota2book.base.BaseActivity;
import com.cn.guojinhu.dota2book.bean.Hero;
import com.cn.guojinhu.dota2book.utils.ImageLoader;
import com.cn.guojinhu.dota2book.utils.JsonUtils;
import com.cn.guojinhu.dota2book.view.ZoomView;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import rx.Observable;
import rx.android.schedulers.AndroidSchedulers;
import rx.functions.Action1;
import rx.functions.Func1;
import rx.schedulers.Schedulers;

public class HeroesGalleryActivity extends BaseActivity  {


    private ViewPager mViewPager;

    private TextView mTextView;


    private List<Hero> mData=new ArrayList<>();

    private ViewPagerAdapter mAdapter;

    private int position;

    private ImageLoader mImageLoader =ImageLoader.getIntance();



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_heroes_details);
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

        mViewPager.addOnPageChangeListener(new ViewPager.OnPageChangeListener() {
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
    }

    private void setText(int position,int size){
        if(0==size){
            mTextView.setText(0+"/"+size);
        }else{
            mTextView.setText(position+"/"+size);
        }
    }

    private void getDataAll(){

        try {
            Observable.from(JsonUtils.getHeroesFromAssets(HeroesGalleryActivity.this))
                    .map(new Func1<Hero, Hero>() {
                        @Override
                        public Hero call(Hero hero) {
                            Log.i("hqq","map  "+hero.toString());
                            return mImageLoader.getBitmapByHoverLarge(hero);
                        }
                    })
                    .subscribeOn(Schedulers.io())
                    .observeOn(AndroidSchedulers.mainThread())
                    .subscribe(new Action1<Hero>() {

                        @Override
                        public void call(Hero hero) {
                            Log.i("hqq",mData.size()+"\t"+hero.cname);

                            setText(position,mData.size());

                            if(!mData.contains(hero)){
                                mData.add(hero);
                                mAdapter.notifyDataSetChanged();
                            }

                            if(mData.size()>position){
                                mViewPager.setCurrentItem(position);
                                mAdapter.notifyDataSetChanged();
                            }


                        }
                    });
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private void getDataByHoverLarge(Hero hero){

        Observable.just(hero)
                .map(new Func1<Hero, Hero>() {
                    @Override
                    public Hero call(Hero hero) {
                        return mImageLoader.getBitmapByHoverLarge(hero);
                    }
                })
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Action1<Hero>() {
                    @Override
                    public void call(Hero hero) {
                        mAdapter.notifyDataSetChanged();
                    }
                });
    }

    class ViewPagerAdapter extends PagerAdapter{

        @Override
        public Object instantiateItem(ViewGroup container, int position) {
            ZoomView zoomView= (ZoomView) LayoutInflater.from(HeroesGalleryActivity.this).inflate(R.layout.item_heroes_details,null);

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

        Hero hero=mData.get(position);
        Bitmap bitmap= ImageLoader.getIntance().getBitmapByKey(hero.HoverLarge);

        Log.i("hqq",(bitmap!=null)+"");
        if(null!=bitmap){
            zoomView.setImageBitmap(bitmap);
        }else{
            getDataByHoverLarge(hero);
        }
    }
}
