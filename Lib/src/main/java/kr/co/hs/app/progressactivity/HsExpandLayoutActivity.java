package kr.co.hs.app.progressactivity;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.design.widget.AppBarLayout;
import android.support.v7.widget.Toolbar;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;

import kr.co.hs.app.HsActivity;
import kr.co.hs.app.progressactivity.animation.HsExpandWeightAnimation;

/**
 * Created by Bae on 2017-01-02.
 */
public abstract class HsExpandLayoutActivity extends HsActivity implements HsExpandWeightAnimation.OnExpandAnimationListener {

    private Toolbar mToolbar;
    private AppBarLayout mAppBarLayout;
    private LinearLayout mLayoutTop;
    private LinearLayout mLayoutContents;

    private int topWeight;
    private int contentsWeight;


    private HsExpandWeightAnimation mExpandWeightAnimation;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_kr_co_hs_app_progress);

        mToolbar = (Toolbar) findViewById(R.id.Toolbar);
        mAppBarLayout = (AppBarLayout) findViewById(R.id.AppBarLayout);

        if(mToolbar != null)
            setSupportActionBar(mToolbar);


        mLayoutTop = (LinearLayout) findViewById(R.id.LinearLayoutTop);
        onCreateTop(savedInstanceState, mLayoutTop);
        mLayoutContents = (LinearLayout) findViewById(R.id.LinearLayoutContents);
        onCreateBottom(savedInstanceState, mLayoutContents);

        //LayoutTop에 확장 에니메이션 적용
        mExpandWeightAnimation = new HsExpandWeightAnimation(mLayoutTop);
        mExpandWeightAnimation.setDuration(300);
        mExpandWeightAnimation.setOnExpandAnimationListener(this);

//        setWeight(500, 300);
    }

    protected void setContentBottomLayout(int layout){
        View view = LayoutInflater.from(getContext()).inflate(layout, null);
        ViewGroup.LayoutParams params = new ViewGroup.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.MATCH_PARENT);
        mLayoutContents.addView(view, params);
    }

    protected void setContentTopLayout(int layout){
        View view = LayoutInflater.from(getContext()).inflate(layout, null);
        ViewGroup.LayoutParams params = new ViewGroup.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.MATCH_PARENT);
        mLayoutTop.addView(view, params);
    }

    protected void onCreateTop(@Nullable Bundle savedInstanceState, LinearLayout layoutTop){

    }

    protected void onCreateBottom(@Nullable Bundle savedInstanceState, LinearLayout layoutBottom){

    }

    protected void setWeight(int top, int content){
        setTopWeight(top);
        setContentWeight(content);
    }

    protected int getCurrentTopWeight(){
        return topWeight;
    }

    protected int getCurrentBottomWeight(){
        return contentsWeight;
    }

    protected void setTopWeightWithAnimation(int requestCode, int toWeight){
        mExpandWeightAnimation.setAnimation(requestCode, getCurrentTopWeight(), toWeight);
        this.topWeight = toWeight;
        mLayoutTop.startAnimation(mExpandWeightAnimation);
    }

    protected void setTopWeightWithAnimation(int requestCode, int toWeight, long duration){
        mExpandWeightAnimation.setAnimation(requestCode, getCurrentTopWeight(), toWeight);
        mExpandWeightAnimation.setDuration(duration);
        this.topWeight = toWeight;
        mLayoutTop.startAnimation(mExpandWeightAnimation);
    }


    private void setTopWeight(int weight){
        mLayoutTop.setLayoutParams(new LinearLayout.LayoutParams(LinearLayout.LayoutParams.MATCH_PARENT, 0, weight));
        this.topWeight = weight;
    }
    private void setContentWeight(int weight){
        mLayoutContents.setLayoutParams(new LinearLayout.LayoutParams(LinearLayout.LayoutParams.MATCH_PARENT, 0, weight));
        this.contentsWeight = weight;
    }
}
