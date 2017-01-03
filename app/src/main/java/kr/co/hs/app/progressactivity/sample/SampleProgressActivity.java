package kr.co.hs.app.progressactivity.sample;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.View;
import android.widget.Button;
import android.widget.LinearLayout;

import kr.co.hs.app.progressactivity.HsExpandLayoutActivity;
import kr.co.hs.app.progressactivity.HsProgressActivity;

/**
 * Created by Bae on 2017-01-03.
 */
public class SampleProgressActivity extends HsProgressActivity {

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setWeight(500, 300);
    }

    @Override
    protected void onExpandedContents(int resultCode) {

    }

    @Override
    protected void onCollapsedContents(int resultCode) {

    }

    @Override
    protected void onCreateBottom(@Nullable Bundle savedInstanceState, LinearLayout layoutBottom) {
        super.onCreateBottom(savedInstanceState, layoutBottom);
        setContentBottomLayout(R.layout.view_button);

        Button btn = (Button) findViewById(R.id.Button);
        btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                setTopWeightWithAnimation(100, 200);
            }
        });

    }
}
