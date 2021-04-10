package oculus.internal;

import android.os.IBinder;
import android.os.RemoteException;
import android.os.ServiceManager;
import android.util.Log;
import oculus.internal.IGatekeeperService;

public class Gatekeeper implements IBinder.DeathRecipient {
    private IGatekeeperService mService;
    private final int mType;

    public Gatekeeper(int i) {
        this.mType = i;
        ensureServiceConnected();
    }

    public boolean isEnabled() {
        return isEnabled(false);
    }

    public boolean isEnabled(boolean z) {
        try {
            ensureServiceConnected();
            return this.mService.getGatekeeperDef(this.mType, z);
        } catch (Exception unused) {
            Log.e("Gatekeeper", "Failed to register gatekeeper: " + this.mType);
            return z;
        }
    }

    public void binderDied() {
        Log.d("Gatekeeper", "Remote service died, resetting mService");
        this.mService = null;
    }

    private void ensureServiceConnected() {
        if (this.mService == null) {
            IBinder service = ServiceManager.getService("GatekeeperService");
            this.mService = IGatekeeperService.Stub.asInterface(service);
            if (this.mService == null) {
                Log.wtf("Gatekeeper", "Failed to get GatekeeperService");
                return;
            }
            try {
                service.linkToDeath(this, 0);
            } catch (RemoteException e) {
                Log.e("Gatekeeper", "linkToDeath failed", e);
            }
        }
    }
}
