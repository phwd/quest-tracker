package X;

import android.content.ComponentName;
import android.content.ServiceConnection;
import android.os.Bundle;
import android.os.IBinder;
import android.os.RemoteException;
import com.oculus.aidl.OVRServiceInterface;
import java.util.Random;
import java.util.concurrent.atomic.AtomicBoolean;

public final class Y8 implements ServiceConnection {
    public final /* synthetic */ Y9 A00;

    public Y8(Y9 y9) {
        this.A00 = y9;
    }

    public final void onServiceConnected(ComponentName componentName, IBinder iBinder) {
        Y9 y9 = this.A00;
        synchronized (y9) {
            C0139Dd.A09("OVRServiceClient", "Connected to OVRService");
            y9.A04.set(false);
            y9.A01 = OVRServiceInterface.Stub.asInterface(iBinder);
            if (y9.A03.get()) {
                y9.A01();
            } else if (y9.A01 == null) {
                C00799i.A00.logError("OVRServiceInterface null");
            } else if (W0.A00().A00.getBoolean("disable_noise_supression", false)) {
                AtomicBoolean atomicBoolean = y9.A05;
                if (atomicBoolean.get()) {
                    C0139Dd.A09("OVRServiceClient", "noise suppression already disabled");
                    C00799i.A00.setFlags("ns_off");
                } else {
                    C0139Dd.A09("OVRServiceClient", "disabling noise suppression");
                    Bundle bundle = new Bundle();
                    bundle.putInt("sdk_major_version", 1);
                    bundle.putInt("sdk_minor_version", 1);
                    bundle.putString("app_id", y9.A02);
                    long nextLong = new Random().nextLong();
                    y9.A00 = nextLong;
                    bundle.putLong("microphone_uid", nextLong);
                    try {
                        y9.A01.getClass().getMethod("sharedMicrophoneDisableNoiseSuppressor", Bundle.class);
                        y9.A01.sharedMicrophoneDisableNoiseSuppressor(bundle);
                        atomicBoolean.set(true);
                        C00799i.A00.setFlags("ns_off");
                    } catch (RemoteException | NoSuchMethodException e) {
                        C0139Dd.A0L("OVRServiceClient", "error calling sharedMicrophoneDisableNoiseSuppressor: ", e);
                    }
                }
            } else {
                C0139Dd.A09("OVRServiceClient", "not disabling noise suppression");
            }
        }
    }

    public final void onServiceDisconnected(ComponentName componentName) {
        C0139Dd.A09("OVRServiceClient", "Disconnected to OVRService");
        Y9 y9 = this.A00;
        y9.A01 = null;
        y9.A04.set(false);
        y9.A03.set(true);
    }
}
