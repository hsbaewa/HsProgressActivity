package kr.co.hs.app.progressactivity.animation;

import android.view.View;
import android.view.animation.Animation;
import android.view.animation.Transformation;
import android.widget.LinearLayout;

import kr.co.hs.util.Logger;

/**
 * Created by Bae on 2017-01-03.
 */
public class HsExpandWeightAnimation extends Animation {
    private final View mContent;
    private float mStartWeight;
    private float mDeltaWeight;

    private OnExpandAnimationListener mOnExpandAnimationListener;
    private float currentInterpolatedTime = 1;
    private int requestCode = 0;
    private boolean isAnimating = false;

    public HsExpandWeightAnimation(View content, float startWeight, float endWeight) {
        this.mContent = content;
        mStartWeight = startWeight;
        mDeltaWeight = endWeight - startWeight;
    }

    public HsExpandWeightAnimation(View content) {
        this.mContent = content;
    }

    @Override
    protected void applyTransformation(float interpolatedTime, Transformation t) {
        LinearLayout.LayoutParams lp = (LinearLayout.LayoutParams) mContent.getLayoutParams();
        float f = mStartWeight + (mDeltaWeight * interpolatedTime);
//        Logger.d("applyTransformation", "interpolatedTime : "+interpolatedTime);
        Logger.d("applyTransformation", "start : "+mStartWeight+", delta : "+mDeltaWeight+", interpoatedTime : "+interpolatedTime);
        lp.weight = f;
        mContent.setLayoutParams(lp);
        if(currentInterpolatedTime != interpolatedTime){
            currentInterpolatedTime = interpolatedTime;
            if(this.mOnExpandAnimationListener != null){
                if(currentInterpolatedTime == 0){
                    isAnimating = true;
                    this.mOnExpandAnimationListener.onResultAnimation(requestCode, OnExpandAnimationListener.RESULT_START);
                }else if(currentInterpolatedTime == 1){
                    isAnimating = false;
                    this.mOnExpandAnimationListener.onResultAnimation(requestCode, OnExpandAnimationListener.RESULT_FINISH);
                }
            }
        }
    }

    public void setAnimation(int requestCode, float startWeight, float endWeight){
        this.requestCode = requestCode;
        this.mStartWeight = startWeight;
        this.mDeltaWeight = endWeight - startWeight;
    }

    public boolean isAnimating() {
        return isAnimating;
    }

    @Override
    public boolean willChangeBounds() {
        return true;
    }

    public void setOnExpandAnimationListener(OnExpandAnimationListener mOnExpandAnimationListener){
        this.mOnExpandAnimationListener = mOnExpandAnimationListener;
    }

    public interface OnExpandAnimationListener{
        int RESULT_START = 100;
        int RESULT_FINISH = 200;
        void onResultAnimation(int requestCode, int resultCode);
    }
}
