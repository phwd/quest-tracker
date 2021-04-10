package oculus.internal;

import android.net.wifi.IWifiManager;
import android.os.RemoteException;

public class WifiManagerAdapter implements WifiManagerAdapterInterface {
    @Override // oculus.internal.WifiManagerAdapterInterface
    public boolean setWifiEnabled(IWifiManager wifiMgr, String packageName, boolean enabled) throws RemoteException {
        return wifiMgr.setWifiEnabled(packageName, enabled);
    }
}
