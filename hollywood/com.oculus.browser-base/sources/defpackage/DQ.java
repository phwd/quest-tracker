package defpackage;

import android.view.ActionMode;
import android.view.ViewStub;
import org.chromium.ui.base.WindowAndroid;

/* renamed from: DQ  reason: default package */
/* compiled from: chromium-OculusBrowser.apk-stable-281887347 */
public class DQ {

    /* renamed from: a  reason: collision with root package name */
    public BQ f7890a;
    public final ViewStub b;
    public final AbstractC0124Ca1 c;
    public final WindowAndroid d;
    public final ActionMode.Callback e;
    public final C1322Vq0 f = new C1322Vq0();

    public DQ(ViewStub viewStub, AbstractC0124Ca1 ca1, WindowAndroid windowAndroid, ActionMode.Callback callback) {
        this.b = viewStub;
        this.c = ca1;
        this.d = windowAndroid;
        this.e = callback;
    }

    public void a() {
        BQ bq = this.f7890a;
        if (bq != null) {
            bq.d(true);
        }
    }
}
