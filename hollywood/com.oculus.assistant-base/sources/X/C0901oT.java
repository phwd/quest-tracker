package X;

import android.os.RemoteException;
import com.google.common.base.Function;

/* renamed from: X.oT  reason: case insensitive filesystem */
public final class C0901oT implements Function {
    public final /* synthetic */ C0904oW A00;
    public final /* synthetic */ C0912of A01;

    public C0901oT(C0904oW oWVar, C0912of ofVar) {
        this.A00 = oWVar;
        this.A01 = ofVar;
    }

    @Override // com.google.common.base.Function
    public final Object apply(Object obj) {
        H5 h5 = (H5) obj;
        try {
            h5.A00.A1P("assistant_smart_keyboard_executor");
            h5.A00();
            return null;
        } catch (RemoteException e) {
            C0139Dd.A07(C0904oW.class, e, "Failed to cancel %s", "assistant_smart_keyboard_executor");
            throw new RuntimeException(e);
        } catch (Throwable th) {
            h5.A00();
            throw th;
        }
    }
}
