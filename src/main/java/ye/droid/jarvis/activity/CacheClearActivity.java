package ye.droid.jarvis.activity;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;

import ye.droid.jarvis.R;

/**
 * Created by ye on 2017/5/20.
 */

public class CacheClearActivity extends PerfectActivity {
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_cacheclear);
    }

    @Override
    public void onBackPressed() {
        super.onBackPressed();
        preAnim();
    }
}
