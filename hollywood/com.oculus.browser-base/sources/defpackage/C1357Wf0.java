package defpackage;

import android.util.Log;

/* renamed from: Wf0  reason: default package and case insensitive filesystem */
/* compiled from: chromium-OculusBrowser.apk-stable-281887347 */
public class C1357Wf0 {

    /* renamed from: a  reason: collision with root package name */
    public final Object f9164a;
    public final int b;

    public C1357Wf0(C1540Zf0 zf0, Object obj) {
        this.f9164a = obj;
        if (obj instanceof String) {
            this.b = 1;
        } else if (obj instanceof C2392eh0) {
            this.b = 2;
        } else {
            this.b = 0;
            Log.w("RecyclerAdapter", "Wrong type of data passed to Item constructor");
        }
    }
}
