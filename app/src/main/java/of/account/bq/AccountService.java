package of.account.bq;

import android.app.Service;
import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;
import android.content.ServiceConnection;
import android.os.IBinder;
import android.util.Log;

import com.ofilm_emirror.dms.dmsserver.DmsServiceAidlInterface;

import of.account.bq.broadcastReceiver.MultiReceiver;

public class AccountService extends Service {
    public static DmsServiceAidlInterface mService;
    public static final String ACTION = "com.ofilm_emirror.dms.dmsserver.DmsServiceAidlInterface";
    public static final String PACKAGE = "com.ofilm_emirror.dms.dmsserver";
    private boolean isBinded = false;
    private static FINGERPRINT fingerprint;
    private ServiceConnection conn = new ServiceConnection() {
        @Override
        public void onServiceDisconnected(ComponentName name) {
            isBinded = false;
            mService = null;
        }
        @Override
        public void onServiceConnected(ComponentName name, IBinder service) {
            mService = DmsServiceAidlInterface.Stub.asInterface(service);
            Log.i("s", "sssssssssssssssssssssssssssssssss");
            isBinded = true;

        }
    };

    public static void setFingerprint(FINGERPRINT fingerprint) {
        AccountService.fingerprint = fingerprint;
    }

    public void doBind() {
        Intent intent = new Intent(ACTION);
        intent.setAction(ACTION);
        intent.setPackage(PACKAGE);
          bindService(intent, conn, Context.BIND_AUTO_CREATE);

    }

    public void doUnbind() {
        if (isBinded) {
            unbindService(conn);
            mService = null;
            isBinded = false;
        }

    }
    public AccountService() {
    }

    @Override
    public IBinder onBind(Intent intent) {
        // TODO: Return the communication channel to the service.
        throw new UnsupportedOperationException("Not yet implemented");
    }


    @Override
    public int onStartCommand(Intent intent, int flags, int startId) {
        if(intent==null||intent.getAction()==null){
            return super.onStartCommand(intent, flags, startId);
        }
        if(intent.getAction().contentEquals(MultiReceiver.FINGERPRINT_START)){
            Log.i("kfjskd", "onStartCommand: "+"指纹可以录入");
            doBind();

        }else if(intent.getAction().contentEquals(MultiReceiver.FINGERPRINT_OVER)){

            if(fingerprint!=null){
                fingerprint.sendId(11);
            }
            doUnbind();
            Log.i("kfjskd", "onStartCommand: "+"指纹录入结束");
        }
        return super.onStartCommand(intent, flags, startId);
    }

    public interface FINGERPRINT{
        void sendId(int id);
    }

}
