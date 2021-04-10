package defpackage;

import android.os.Bundle;
import android.os.Parcel;

/* renamed from: qA1  reason: default package and case insensitive filesystem */
/* compiled from: chromium-OculusBrowser.apk-stable-281887347 */
public abstract class AbstractBinderC4361qA1 extends AbstractBinderC2994iA1 implements AbstractC4019oA1 {
    public AbstractBinderC4361qA1() {
        super("com.google.android.play.core.appupdate.protocol.IAppUpdateServiceCallback");
    }

    @Override // defpackage.AbstractBinderC2994iA1
    public final boolean c(int i, Parcel parcel) {
        if (i == 2) {
            t0((Bundle) AbstractC2481fA1.a(parcel, Bundle.CREATOR));
            return true;
        } else if (i != 3) {
            return false;
        } else {
            v((Bundle) AbstractC2481fA1.a(parcel, Bundle.CREATOR));
            return true;
        }
    }
}
