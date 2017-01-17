package kr.co.hs.app.progressactivity;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.widget.LinearLayout;

/**
 * 생성된 시간 2017-01-05, Bae 에 의해 생성됨
 * 프로젝트 이름 : MobileFilter4
 * 패키지명 : kr.co.hs.app.progressactivity
 */

public interface IHsExpandLayout {

    void setContentBottomLayout(int layout);

    void setContentTopLayout(int layout);

    void setWeight(int top, int content);

    int getCurrentTopWeight();

    int getCurrentBottomWeight();

    void setTopWeightWithAnimation(int requestCode, int toWeight);

    void setTopWeightWithAnimation(int requestCode, int toWeight, long duration);
}
