package of.account.bq.broadcastReceiver;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.util.Log;

import of.account.bq.AccountService;

public class MultiReceiver extends BroadcastReceiver {
    public final static String FINGERPRINT_START="ofilm.intent.action.FINGERPRINT_EVENT";
    public final static String FINGERPRINT_OVER="ofilm.intent.action.FINGERPRINT_QUIT";
    @Override
    public void onReceive(Context context, Intent intent) {
        // TODO: This method is called when the BroadcastReceiver is receiving
        // an Intent broadcast.
//       if(intent!=null){
//           intent.getStringExtra(intent.getAction());
//           intent.getStringExtra("tts");
//           Log.i("bq11",intent.getAction()+"//aaaaaaaa"+ intent.getStringExtra(intent.getAction())+"//"+intent.getStringExtra("tts"));
//       }
        if(intent==null||intent.getAction()==null){
            return;
        }

        if(intent.getAction().contentEquals(FINGERPRINT_START)){//指纹可以录入
         Intent startService=new Intent(context, AccountService.class);
         startService.setAction(FINGERPRINT_START);
            context.startService(startService);
        }
        else if(intent.getAction().contentEquals(FINGERPRINT_OVER)){//指纹不可以录入
            Intent startService=new Intent(context, AccountService.class);
            startService.setAction(FINGERPRINT_OVER);
            context.startService(startService);
        }
    }
}
