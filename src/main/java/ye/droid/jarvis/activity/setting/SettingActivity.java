package ye.droid.jarvis.activity.setting;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.view.View;

import ye.droid.jarvis.R;
import ye.droid.jarvis.cvs.ChangeItem;
import ye.droid.jarvis.utils.ConstantValues;
import ye.droid.jarvis.utils.SharedPreferencesUtils;

/**
 * Created by ye on 2017/5/9.
 */

public class SettingActivity extends AppCompatActivity {
    private ChangeItem ct_auto_update;
    private ChangeItem ct_about_app;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_setting);
        initUI();
        initData();
    }

    private void initUI() {
        ct_auto_update = (ChangeItem) findViewById(R.id.ct_auto_update);
        ct_about_app = (ChangeItem) findViewById(R.id.ct_about_app);
    }

    private void initData() {
        //1.还原自动更新状态
        boolean isUpdate = SharedPreferencesUtils.getBoolean(this, ConstantValues.AUTO_UPDATE, true);//自动更新默认开启
        ct_auto_update.setCheck(isUpdate);
        //2.隐藏关于软件控件的switch
        ct_about_app.setSwitchVisible(false);
    }

    /**
     * 自动更新触发事件
     *
     * @param view
     */
    public void setAutoUpdate(View view) {
        boolean isUpdate = SharedPreferencesUtils.getBoolean(this, ConstantValues.AUTO_UPDATE, true); //自动更新默认开启
        SharedPreferencesUtils.putBoolean(this, ConstantValues.AUTO_UPDATE, !isUpdate);
        ct_auto_update.setCheck(!isUpdate);
    }

    /**
     * 软件的详细信息
     *
     * @param view
     */
    public void showAboutInfo(View view) {
        Intent intent = new Intent(this, AboutActivity.class);
        startActivity(intent);
        overridePendingTransition(R.anim.next_in_anim, R.anim.next_out_anim);//开启下一页动画
    }

    @Override
    public void onBackPressed() {
        super.onBackPressed();
        overridePendingTransition(R.anim.pre_in_anim, R.anim.pre_out_anim);
    }
}
