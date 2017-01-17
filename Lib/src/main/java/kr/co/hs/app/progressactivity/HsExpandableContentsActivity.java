package kr.co.hs.app.progressactivity;

import android.os.Bundle;
import android.support.annotation.Nullable;


/**
 * 생성된 시간 2017-01-05, Bae 에 의해 생성됨
 * 프로젝트 이름 : MobileFilter4
 * 패키지명 : kr.co.hs.app.progressactivity
 */

public abstract class HsExpandableContentsActivity extends HsExpandLayoutActivity {
    protected static final int RC_EXPAND_CONTENTS = 10001;
    protected static final int RC_COLLAPSE_CONTENTS = 10002;


    private boolean isExpandContents = false;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        isExpandContents = false;
    }

    protected void expandContents(){
        setTopWeightWithAnimation(RC_EXPAND_CONTENTS, 200);
    }

    protected void collapseContents(){
        setTopWeightWithAnimation(RC_COLLAPSE_CONTENTS, 500);
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
