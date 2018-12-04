package of.account.bq.service;

import android.app.Service;
import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;
import android.content.ServiceConnection;
import android.content.pm.PackageManager;
import android.os.IBinder;
import android.util.Log;

import com.ofilm_emirror.dms.dmsserver.DmsServiceAidlInterface;

import of.account.bq.broadcastReceiver.FingerPrintReceiver;
import ofilm.mcuipc.lib.manager.PeripheralManager;
import ofilm.mcuipc.lib.manager.EnvironmentPeripheralManager;
import ofilm.mcuipc.lib.manager.PeripheralManager.EventCallback;
import ofilm.mcuipc.lib.McuIpcLibUtils;
import ofilm.mcuipc.lib.IpcNotConnectException;

public class AccountService extends Service {
    public static DmsServiceAidlInterface mService;
    public static final String ACTION = "com.ofilm_emirror.dms.dmsserver.DmsServiceAidlInterface";
    public static final String PACKAGE = "com.ofilm_emirror.dms.dmsserver";
    private boolean isBinded = false;
    private boolean canCheckHeart=false;//判断此时能否进行心率检测
    public  final  static String   GET_FIGERPRINT_STATUS="ofilm.intent.action.FIGERPRINT_STATUS";//开始获取检测心率的数据
    private static TransmissionData mTransmissionData;
    private EnvironmentPeripheralManager mEPM;
    private PeripheralManager mPeripheral;
    private boolean getData=false;//是否应该获取数据
    private final EventCallback mCallback =
            new EventCallback() {
                @Override
                public void onDataEvent(int model, byte[] data) {
                    switch (model) {
                        case EnvironmentPeripheralManager.FINGER_PRINT:
                            if(getData){
                                if(canCheckHeart){//判断此时能否进行心率检测
                                    if(mTransmissionData!=null) {
                                        Log.i("bq11", "onStartCommand: 开始指纹识别数据");
                                        //调用aidl获取数据
                                        verifyData(data);

                                    }
                                }else{//
                                    if(mTransmissionData!=null) {

                                    }
                                }
                            }
                            break;
                        default:
                    }
                }
                @Override
                public void  onConfigEvent(int cmd, byte[] data) {
                }
            };

    private void verifyData(byte[] data){
        Log.d("bq11", "verifyData: start");
        int byte1 = McuIpcLibUtils.byteToInt(data[0]);
        if(byte1 == 0 ){
            Log.d("bq11", "verifyData: ret null. because no power");
            return ;
        }else{
            if(mTransmissionData!=null)
                mTransmissionData.transmitData(byte1);
        }
    }

    public static void setmTransmissionData(TransmissionData mTransmissionData1) {
        AccountService.mTransmissionData = mTransmissionData1;
    }

    private final ServiceConnection mConnectionCallback =
            new ServiceConnection () {
                public void onServiceConnected(ComponentName name, IBinder service) {
                    synchronized (this) {
                        try {
                            initManager((EnvironmentPeripheralManager) mPeripheral.getManager(
                                    EnvironmentPeripheralManager.MANAGER_NAME));
                        } catch (IpcNotConnectException e) {

                        }
                    }
                }

                public void onServiceDisconnected(ComponentName name) {

                }
            };

    @Override
    public void onCreate() {
        super.onCreate();
        if (getPackageManager().hasSystemFeature(PackageManager.FEATURE_AUTOMOTIVE)) {
            mPeripheral = PeripheralManager.createMe(this, mConnectionCallback);
            mPeripheral.connect();
        }
    }

    @Override
    public void onDestroy() {//关闭服务执行的方法，保存数据后才关闭
        unRegister();
        Log.i("bq11", "onStartCommand: 收到指纹关闭广播");
        super.onDestroy();
    }

    //init aidl
    private void initManager(EnvironmentPeripheralManager epm) {
        mEPM = epm;
        // Log.d(TAG, "initManager start");
        try {
            mEPM.registerCallback(mCallback, EnvironmentPeripheralManager.FINGER_PRINT);
            //  Log.d(TAG, "registerCallback end");
        } catch (IpcNotConnectException e) {
        }

    }

    //get data
    public byte[] getData(int model) {
        if (mEPM != null) {
            try {
                return mEPM.readLastData(model);
            } catch (IpcNotConnectException e) {

            }
        }
        return null;
    }

    //unregister aidl
    private void unRegister(){
        if (mEPM != null) {
            try {
                mEPM.unregisterCallback(mCallback,EnvironmentPeripheralManager.FINGER_PRINT);
            } catch (IpcNotConnectException e) {

            }

        }
    }

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
        if(intent.getAction().equals(EnvironmentPeripheralManager.BR_EVENT_ACTION_START)){
            canCheckHeart=true;
            Log.i("bq11", "onStartCommand: 收到指纹录入广播");
        }else  if(intent.getAction().equals(EnvironmentPeripheralManager.BR_EVENT_ACTION_STOP)){
            canCheckHeart=false;
            getData=false;
            unRegister();
        }  else if(intent.getAction().equals(GET_FIGERPRINT_STATUS)){
            getData=true;
        }
        else{
            return super.onStartCommand(intent, flags, startId);
        }
        return super.onStartCommand(intent, flags, startId);
    }

    public interface  TransmissionData{
        void  transmitData(int data);//传输数据
    }

}
