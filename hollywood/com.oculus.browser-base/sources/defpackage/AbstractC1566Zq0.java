package defpackage;

import android.content.Context;
import org.chromium.ui.base.WindowAndroid;

/* renamed from: Zq0  reason: default package and case insensitive filesystem */
/* compiled from: chromium-OculusBrowser.apk-stable-281887347 */
public abstract class AbstractC1566Zq0 extends C3623lt {
    public String k;
    public int l;

    public AbstractC1566Zq0(Context context, AbstractC3226ja1 ja1, WindowAndroid windowAndroid, boolean z, C1280Va va) {
        super(context, ja1, windowAndroid, null, null, z, null, va);
    }

    @Override // defpackage.C3623lt
    public C61 g() {
        String str = this.k;
        if (str == null) {
            return new C0986Qd1(null, null, null, null, null, null);
        }
        return new UI0(str, this.l);
    }

    public boolean k() {
        int i;
        return this.k != null && ((i = this.l) == 3 || i == 2);
    }
}
