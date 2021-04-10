package X;

import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;
import android.content.ServiceConnection;
import android.os.Bundle;
import android.os.RemoteException;
import com.oculus.aidl.OVRServiceInterface;
import java.util.concurrent.atomic.AtomicBoolean;

public final class Y9 {
    public long A00;
    public OVRServiceInterface A01;
    public final String A02;
    public final AtomicBoolean A03 = new AtomicBoolean(false);
    public final AtomicBoolean A04 = new AtomicBoolean(false);
    public final AtomicBoolean A05 = new AtomicBoolean(false);
    public final Context A06;
    public final ServiceConnection A07 = new Y8(this);

    public final synchronized void A00() {
        if (this.A01 == null) {
            AtomicBoolean atomicBoolean = this.A04;
            if (!atomicBoolean.get()) {
                atomicBoolean.set(true);
                this.A03.set(false);
                try {
                    Intent intent = new Intent();
                    intent.setComponent(new ComponentName("com.oculus.horizon", "com.oculus.horizon.service.OVRService"));
                    this.A06.bindService(intent, this.A07, 1);
                } catch (SecurityException e) {
                    C0139Dd.A0L("OVRServiceClient", "Failed to bind to OVRService", e);
                }
            }
        }
    }

    public final synchronized void A01() {
        AtomicBoolean atomicBoolean = this.A03;
        if (!atomicBoolean.get()) {
            atomicBoolean.set(true);
            if (this.A01 != null) {
                AtomicBoolean atomicBoolean2 = this.A05;
                if (atomicBoolean2.get()) {
                    Bundle bundle = new Bundle();
                    bundle.putInt("sdk_major_version", 1);
                    bundle.putInt("sdk_minor_version", 1);
                    bundle.putString("app_id", this.A02);
                    bundle.putLong("microphone_uid", this.A00);
                    try {
                        this.A01.getClass().getMethod("sharedMicrophoneEnableNoiseSuppressor", Bundle.class);
                        this.A01.sharedMicrophoneEnableNoiseSuppressor(bundle);
                        atomicBoolean2.set(false);
                    } catch (RemoteException | NoSuchMethodException e) {
                        C0139Dd.A0L("OVRServiceClient", "error calling sharedMicrophoneEnableNoiseSuppressor: ", e);
                    }
                }
                this.A06.unbindService(this.A07);
                this.A01 = null;
                C0139Dd.A09("OVRServiceClient", "Disconnected from OVRService");
            }
        }
    }

    public Y9(Context context) {
        this.A06 = context;
        this.A02 = "2270234229757886";
    }

    public final String toString() {
        StringBuilder sb = new StringBuilder("OVRServiceClient{, mIsConnecting=");
        sb.append(this.A04.get());
        sb.append(", mDisconnnected=");
        sb.append(this.A03.get());
        sb.append(", mNoiseSuppressionDisabled=");
        sb.append(this.A05.get());
        sb.append('}');
        return sb.toString();
    }
}
