package kr.co.hs.app.progressactivity;


import android.os.Bundle;
import android.support.annotation.Nullable;
import android.widget.LinearLayout;

import kr.co.hs.widget.hscircularprogressbar.HsCircularProgressBar;

/**
 * Created by Bae on 2017-01-03.
 */
public abstract class HsProgressActivity extends HsExpandableContentsActivity {
    private HsCircularProgressBar mCircularProgressBar;
    private LinearLayout mLinearLayoutProgressCenter;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setWeight(500, 300);
    }

    @Override
    public void onCreateTop(@Nullable Bundle savedInstanceState, LinearLayout layoutTop) {
        super.onCreateTop(savedInstanceState, layoutTop);
        setContentTopLayout(R.layout.layout_progress);

        mCircularProgressBar = (HsCircularProgressBar) findViewById(R.id.CircularProgressBar);
        mLinearLayoutProgressCenter = (LinearLayout) findViewById(R.id.LinearLayoutProgressCenter);
    }

    protected HsCircularProgressBar getCircularProgressBar(){
        return mCircularProgressBar;
    }

    protected LinearLayout getLayoutProgressCenter(){
        return mLinearLayoutProgressCenter;
    }
}
