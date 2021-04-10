package oculus.internal;

import android.net.wifi.IWifiManager;
import android.os.RemoteException;

public interface WifiManagerAdapterInterface {
    boolean setWifiEnabled(IWifiManager iWifiManager, String str, boolean z) throws RemoteException;
}
