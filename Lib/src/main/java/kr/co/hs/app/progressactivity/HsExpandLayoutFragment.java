package kr.co.hs.app.progressactivity;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.Interpolator;
import android.widget.LinearLayout;

import kr.co.hs.app.HsFragment;
import kr.co.hs.app.progressactivity.animation.HsExpandWeightAnimation;

/**
 * 생성된 시간 2017-01-05, Bae 에 의해 생성됨
 * 프로젝트 이름 : MobileFilter4
 * 패키지명 : kr.co.hs.app.progressactivity
 */

public abstract class HsExpandLayoutFragment extends HsFragment implements IHsExpandLayout, HsExpandWeightAnimation.OnExpandAnimationListener{
    protected static final int RC_EXPAND_CONTENTS = 10001;
    protected static final int RC_COLLAPSE_CONTENTS = 10002;

    private boolean isExpandContents = false;

    private LinearLayout mLayoutTop;
    private LinearLayout mLayoutContents;

    private int topWeight;
    private int contentsWeight;

    private HsExpandWeightAnimation mExpandWeightAnimation;

    @Override
    public void onCreateView(@Nullable ViewGroup viewGroup, @Nullable Bundle bundle) {
        setContentView(R.layout.fragment_kr_co_hs_app_progress);

        isExpandContents = false;

        mLayoutTop = (LinearLayout) findViewById(R.id.LinearLayoutTop);
        onCreateTopView(viewGroup, bundle, mLayoutTop);
        mLayoutContents = (LinearLayout) findViewById(R.id.LinearLayoutContents);
        onCreateBottomView(viewGroup, bundle, mLayoutContents);

        //LayoutTop에 확장 에니메이션 적용
        mExpandWeightAnimation = new HsExpandWeightAnimation(mLayoutTop);
        mExpandWeightAnimation.setDuration(300);
        mExpandWeightAnimation.setOnExpandAnimationListener(this);
    }

    public void onCreateTopView(ViewGroup viewGroup, Bundle bundle, LinearLayout layoutTop){

    }

    public void onCreateBottomView(ViewGroup viewGroup, Bundle bundle, LinearLayout layoutTop){

    }

    @Override
    public void setContentBottomLayout(int layout) {
        View view = LayoutInflater.from(getContext()).inflate(layout, null);
        ViewGroup.LayoutParams params = new ViewGroup.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.MATCH_PARENT);
        mLayoutContents.addView(view, params);
    }

    @Override
    public void setContentTopLayout(int layout) {
        View view = LayoutInflater.from(getContext()).inflate(layout, null);
        ViewGroup.LayoutParams params = new ViewGroup.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.MATCH_PARENT);
        mLayoutTop.addView(view, params);
    }


    @Override
    public void setWeight(int top, int content) {
        setTopWeight(top);
        setContentWeight(content);
    }

    @Override
    public int getCurrentTopWeight() {
        return topWeight;
    }

    @Override
    public int getCurrentBottomWeight() {
        return contentsWeight;
    }

    @Override
    public void setTopWeightWithAnimation(int requestCode, int toWeight) {
        if(!mExpandWeightAnimation.isAnimating()){
            mExpandWeightAnimation.setAnimation(requestCode, getCurrentTopWeight(), toWeight);
            this.topWeight = toWeight;
            mLayoutTop.startAnimation(mExpandWeightAnimation);
        }
    }

    @Override
    public void setTopWeightWithAnimation(int requestCode, int toWeight, long duration) {
        if(!mExpandWeightAnimation.isAnimating()){
            mExpandWeightAnimation.setAnimation(requestCode, getCurrentTopWeight(), toWeight);
            mExpandWeightAnimation.setDuration(duration);
            this.topWeight = toWeight;
            mLayoutTop.startAnimation(mExpandWeightAnimation);
        }
    }
    public void setTopWeightWithAnimation(int requestCode, int toWeight, long duration, Interpolator interpolator) {
        mExpandWeightAnimation.setInterpolator(interpolator);
        setTopWeightWithAnimation(requestCode, toWeight, duration);
    }
    public void setTopWeightForce(int requestCode, int topWeight){
        setWeight(topWeight, getCurrentBottomWeight());
        isExpandContents = true;
    }

    private void setTopWeight(int weight){
        mLayoutTop.setLayoutParams(new LinearLayout.LayoutParams(LinearLayout.LayoutParams.MATCH_PARENT, 0, weight));
        this.topWeight = weight;
    }

    private void setContentWeight(int weight){
        mLayoutContents.setLayoutParams(new LinearLayout.LayoutParams(LinearLayout.LayoutParams.MATCH_PARENT, 0, weight));
        this.contentsWeight = weight;
    }

    public LinearLayout getLayoutContents() {
        return mLayoutContents;
    }

    public LinearLayout getLayoutTop() {
        return mLayoutTop;
    }

    protected boolean isExpandContents(){
        return isExpandContents;
    }

    @Override
    public void onResultAnimation(int requestCode, int resultCode) {
        switch (requestCode){
            case RC_EXPAND_CONTENTS:{
                isExpandContents = true;
                break;
            }
            case RC_COLLAPSE_CONTENTS:{
                isExpandContents = false;
                break;
            }
        }
    }
}
