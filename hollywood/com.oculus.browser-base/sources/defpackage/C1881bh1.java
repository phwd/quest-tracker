package defpackage;

import android.os.Process;

/* renamed from: bh1  reason: default package and case insensitive filesystem */
/* compiled from: chromium-OculusBrowser.apk-stable-281887347 */
public class C1881bh1 {

    /* renamed from: a  reason: collision with root package name */
    public final long f9557a = ((long) Process.myTid());
    public boolean b;

    public void a() {
        if (this.b) {
            throw new IllegalStateException("Operation is not allowed after destroy().");
        }
    }
}
