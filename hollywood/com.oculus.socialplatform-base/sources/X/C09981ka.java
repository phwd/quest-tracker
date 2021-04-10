package X;

import android.util.Log;
import com.facebook.fresco.ui.common.ControllerListener2;
import com.facebook.infer.annotation.Nullsafe;
import java.util.ArrayList;
import java.util.List;

@Nullsafe(Nullsafe.Mode.STRICT)
/* renamed from: X.1ka  reason: invalid class name and case insensitive filesystem */
public final class C09981ka<I> extends AnonymousClass1ko<I> {
    public final List<ControllerListener2<I>> A00 = new ArrayList(2);

    public static synchronized void A00(C09981ka r1, String str, Throwable th) {
        synchronized (r1) {
            Log.e("FwdControllerListener2", str, th);
        }
    }
}
