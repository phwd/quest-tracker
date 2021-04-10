package defpackage;

import android.content.Context;
import android.os.Bundle;
import android.os.UserManager;

/* renamed from: QQ  reason: default package */
/* compiled from: chromium-OculusBrowser.apk-stable-281887347 */
public class QQ extends AbstractC2032cb {
    public final /* synthetic */ Context i;
    public final /* synthetic */ long j;
    public final /* synthetic */ RQ k;

    public QQ(RQ rq, Context context, long j2) {
        this.k = rq;
        this.i = context;
        this.j = j2;
    }

    @Override // defpackage.AbstractC2032cb
    public Object c() {
        Bundle bundle;
        try {
            bundle = ((UserManager) this.i.getSystemService("user")).getApplicationRestrictions(this.i.getPackageName());
        } catch (SecurityException unused) {
            bundle = new Bundle();
        }
        return Boolean.valueOf(!bundle.isEmpty());
    }

    @Override // defpackage.AbstractC2032cb
    public void k(Object obj) {
        this.k.a(((Boolean) obj).booleanValue(), this.j);
    }
}
