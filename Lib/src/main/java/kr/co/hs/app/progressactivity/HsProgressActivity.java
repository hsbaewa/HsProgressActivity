package kr.co.hs.app.progressactivity;


import android.os.Bundle;
import android.support.annotation.Nullable;
import android.widget.LinearLayout;

import kr.co.hs.widget.hscircularprogressbar.HsCircularProgressBar;

/**
 * Created by Bae on 2017-01-03.
 */
public abstract class HsProgressActivity extends HsExpandLayoutActivity {
    protected static final int RC_EXPAND_CONTENTS = 10001;
    protected static final int RC_COLLAPSE_CONTENTS = 10002;

    private HsCircularProgressBar mCircularProgressBar;
    private LinearLayout mLinearLayoutProgressCenter;

    private boolean isExpandContents = false;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setWeight(500, 300);

        isExpandContents = false;
    }

    @Override
    protected void onCreateTop(@Nullable Bundle savedInstanceState, LinearLayout layoutTop) {
        super.onCreateTop(savedInstanceState, layoutTop);
        setContentTopLayout(R.layout.layout_progress);

        mCircularProgressBar = (HsCircularProgressBar) findViewById(R.id.CircularProgressBar);
        mLinearLayoutProgressCenter = (LinearLayout) findViewById(R.id.LinearLayoutProgressCenter);
    }

    protected void expandContents(){
        setTopWeightWithAnimation(RC_EXPAND_CONTENTS, 200);
    }

    protected void collapseContents(){
        setTopWeightWithAnimation(RC_COLLAPSE_CONTENTS, 500);
    }

    protected HsCircularProgressBar getCircularProgressBar(){
        return mCircularProgressBar;
    }

    protected LinearLayout getLayoutProgressCenter(){
        return mLinearLayoutProgressCenter;
    }

    protected boolean isExpandContents(){
        return isExpandContents;
    }

    @Override
    public void onResultAnimation(int requestCode, int resultCode) {
        switch (requestCode){
            case RC_EXPAND_CONTENTS:{
                isExpandContents = true;
                onExpandedContents(resultCode);
                break;
            }
            case RC_COLLAPSE_CONTENTS:{
                isExpandContents = false;
                onCollapsedContents(resultCode);
                break;
            }
        }
    }

    protected abstract void onExpandedContents(int resultCode);
    protected abstract void onCollapsedContents(int resultCode);
}
