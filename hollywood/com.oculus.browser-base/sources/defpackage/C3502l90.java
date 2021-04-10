package defpackage;

import android.database.DataSetObserver;

/* renamed from: l90  reason: default package and case insensitive filesystem */
/* compiled from: chromium-OculusBrowser.apk-stable-281887347 */
public class C3502l90 extends DataSetObserver {

    /* renamed from: a  reason: collision with root package name */
    public final /* synthetic */ AbstractC4186p90 f10326a;

    public C3502l90(AbstractC4186p90 p90) {
        this.f10326a = p90;
    }

    public void onChanged() {
        if (this.f10326a.b()) {
            this.f10326a.d();
        }
    }

    public void onInvalidated() {
        this.f10326a.dismiss();
    }
}
