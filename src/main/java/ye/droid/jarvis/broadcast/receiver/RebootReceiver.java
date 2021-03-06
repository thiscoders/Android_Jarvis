package ye.droid.jarvis.broadcast.receiver;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.telephony.SmsManager;
import android.telephony.TelephonyManager;
import android.util.Log;
import android.widget.Toast;

import ye.droid.jarvis.service.burglars.SmsListenerService;
import ye.droid.jarvis.utils.ConstantValues;
import ye.droid.jarvis.utils.SharedPreferencesUtils;

/**
 * SIM卡变更报警
 * Created by ye on 2017/5/22.
 */

public class RebootReceiver extends BroadcastReceiver {
    private final String TAG = RebootReceiver.class.getSimpleName();
    private final String ACTION = "android.intent.action.BOOT_COMPLETED";

    @Override
    public void onReceive(Context context, Intent intent) {
        //检测是否是手机重启广播
        if (intent.getAction().equals(ACTION)) {
            //获取TelephonyManager
            TelephonyManager manager = (TelephonyManager) context.getSystemService(Context.TELEPHONY_SERVICE);
            //动态获取当前SIM卡序列号
            String dontSimNum = manager.getSimSerialNumber();
            //获取共享参数保存的SIM卡序列号
            String safeSimNum = SharedPreferencesUtils.getString(context, ConstantValues.SIM_NUMBER, "");
            //如果上述两个序列号不相同，那么说明手机的SIM卡已经被更换，那么就向安全号码发送SIM卡变更短信
            if (!dontSimNum.equals(safeSimNum)) {
                SmsManager smsManager = SmsManager.getDefault();
                String phone = SharedPreferencesUtils.getString(context, ConstantValues.CONTACT_PHONEV2, "");
                //发送短信
                smsManager.sendTextMessage(phone, null, "The sim card of your phone is changed! new sim serial number is " + dontSimNum, null, null);
            }else {
                Toast.makeText(context, "手机重启完成！Sim卡未变更！", Toast.LENGTH_SHORT).show();
                return;
            }
            //开启短信监听
            Intent smsListener = new Intent(context, SmsListenerService.class);
            context.startService(smsListener);
            Toast.makeText(context, "手机重启完成！检测到Sim卡变更，安全短信监听已经开启！", Toast.LENGTH_SHORT).show();
        } else {
            Toast.makeText(context, "这个广播不是手机重启广播！", Toast.LENGTH_SHORT).show();
        }
    }
}
