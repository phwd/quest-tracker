package X;

import android.os.RemoteException;
import com.google.common.base.Function;

/* renamed from: X.oU  reason: case insensitive filesystem */
public final class C0902oU implements Function {
    public final /* synthetic */ C0904oW A00;

    public C0902oU(C0904oW oWVar) {
        this.A00 = oWVar;
    }

    @Override // com.google.common.base.Function
    public final Object apply(Object obj) {
        H5 h5 = (H5) obj;
        try {
            h5.A00.run();
            h5.A00();
            return null;
        } catch (RemoteException e) {
            C0139Dd.A07(C0904oW.class, e, "Failed to run", new Object[0]);
            throw new RuntimeException(e);
        } catch (Throwable th) {
            h5.A00();
            throw th;
        }
    }
}
