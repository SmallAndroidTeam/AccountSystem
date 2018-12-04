package of.account.bq.service;

import android.app.Service;
import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;
import android.content.ServiceConnection;
import android.os.IBinder;
import android.util.Log;

import com.ofilm_emirror.dms.dmsserver.DmsServiceAidlInterface;

public class AssociatingFaceService extends Service {
    public static DmsServiceAidlInterface mService;
    public static final String ACTION = "com.ofilm_emirror.dms.dmsserver.DmsServiceAidlInterface";
    public static final String PACKAGE = "com.ofilm_emirror.dms.dmsserver";
    private boolean isBinded = false;
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


    public void dofaceBind() {
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

        return super.onStartCommand(intent, flags, startId);
    }

}
