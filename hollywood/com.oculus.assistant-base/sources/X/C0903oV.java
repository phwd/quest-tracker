package X;

import android.os.RemoteException;
import com.google.common.base.Function;

/* renamed from: X.oV  reason: case insensitive filesystem */
public final class C0903oV implements Function {
    public final /* synthetic */ C0904oW A00;

    public C0903oV(C0904oW oWVar) {
        this.A00 = oWVar;
    }

    @Override // com.google.common.base.Function
    public final Object apply(Object obj) {
        try {
            ((H5) obj).A00.stop();
            this.A00.A01.A01();
            return null;
        } catch (RemoteException e) {
            C0139Dd.A07(C0904oW.class, e, "Failed to stop", new Object[0]);
            throw new RuntimeException(e);
        } catch (Throwable th) {
            this.A00.A01.A01();
            throw th;
        }
    }
}
