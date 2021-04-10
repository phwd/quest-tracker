package defpackage;

import android.telephony.TelephonyManager;
import java.util.List;
import org.chromium.base.Callback;

/* renamed from: q7  reason: default package and case insensitive filesystem */
/* compiled from: chromium-OculusBrowser.apk-stable-281887347 */
public class C4350q7 extends TelephonyManager.CellInfoCallback {

    /* renamed from: a  reason: collision with root package name */
    public final /* synthetic */ Callback f11117a;

    public C4350q7(Callback callback) {
        this.f11117a = callback;
    }

    @Override // android.telephony.TelephonyManager.CellInfoCallback
    public void onCellInfo(List list) {
        this.f11117a.onResult(list);
    }
}
