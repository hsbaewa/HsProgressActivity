package kr.co.hs.app.progressactivity;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.ViewGroup;

/**
 * 생성된 시간 2017-01-05, Bae 에 의해 생성됨
 * 프로젝트 이름 : MobileFilter4
 * 패키지명 : kr.co.hs.app.progressactivity
 */

public abstract class HsExpandableContentsFragment extends HsExpandLayoutFragment {


    @Override
    public void onCreateView(@Nullable ViewGroup viewGroup, @Nullable Bundle bundle) {
        super.onCreateView(viewGroup, bundle);
    }

    protected void expandContents(){
        setTopWeightWithAnimation(RC_EXPAND_CONTENTS, 200);
    }

    protected void collapseContents(){
        setTopWeightWithAnimation(RC_COLLAPSE_CONTENTS, 500);
    }

    protected abstract void onExpandedContents(int resultCode);
    protected abstract void onCollapsedContents(int resultCode);

    @Override
    public void onResultAnimation(int requestCode, int resultCode) {
        super.onResultAnimation(requestCode, resultCode);
        switch (requestCode){
            case RC_EXPAND_CONTENTS:{
                onExpandedContents(resultCode);
                break;
            }
            case RC_COLLAPSE_CONTENTS:{
                onCollapsedContents(resultCode);
                break;
            }
        }
    }


}
