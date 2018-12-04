package of.account.bq.broadcastReceiver;

import android.content.BroadcastReceiver;
import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;

import of.account.bq.activity.MainActivity;
import of.account.bq.service.AccountService;

import ofilm.mcuipc.lib.manager.PeripheralManager;
import ofilm.mcuipc.lib.manager.EnvironmentPeripheralManager;
import ofilm.mcuipc.lib.manager.PeripheralManager.EventCallback;

public class FingerPrintReceiver extends BroadcastReceiver {
    public final static String FINGERPRINT_START="ofilm.intent.action.FINGER_PRINT";
    @Override
    public void onReceive(Context context, Intent intent) {
        if(intent==null||intent.getAction()==null){
            return;
        }
        if(FINGERPRINT_START.equals(intent.getAction())){
            String status = intent.getStringExtra(EnvironmentPeripheralManager.BR_EVENT_ACTION);
            if(EnvironmentPeripheralManager.BR_EVENT_ACTION_START.equals(status)){//心率检测开始，则开启心率检测服务
                //do register aidl
                Intent service= new Intent(context, AccountService.class);
                service.setAction(EnvironmentPeripheralManager.BR_EVENT_ACTION_START);
                context.startService(service);
            }else if(EnvironmentPeripheralManager.BR_EVENT_ACTION_STOP.equals(status)){//心率检测结束，则关闭心率检测服务
                //do unregister aidl
                Intent overHeartService= new Intent(context, AccountService.class);
                overHeartService.setAction(EnvironmentPeripheralManager.BR_EVENT_ACTION_STOP);
                context.startService(overHeartService);
                Intent service= new Intent(context, AccountService.class);
                context.stopService(service);
            }
        }
    }
}
