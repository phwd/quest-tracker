package X;

import android.os.RemoteException;
import com.facebook.papaya.IPapayaCallback;
import com.facebook.papaya.client.ICallback;

/* renamed from: X.oY  reason: case insensitive filesystem */
public final class C0906oY implements ICallback {
    public final /* synthetic */ IPapayaCallback A00;

    public C0906oY(IPapayaCallback iPapayaCallback) {
        this.A00 = iPapayaCallback;
    }

    @Override // com.facebook.papaya.client.ICallback
    public final void onExecutorComplete(String str) {
        try {
            this.A00.onExecutorComplete(str);
        } catch (RemoteException e) {
            C0139Dd.A07(Gv.class, e, "Failed to executed completion callback", new Object[0]);
        }
    }
}
