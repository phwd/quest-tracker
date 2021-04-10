package defpackage;

import android.net.Uri;

/* renamed from: qC0  reason: default package and case insensitive filesystem */
/* compiled from: chromium-OculusBrowser.apk-stable-281887347 */
public class C4366qC0 implements AbstractC4707sC0 {

    /* renamed from: a  reason: collision with root package name */
    public AbstractC4707sC0 f11123a;
    public boolean b;

    public C4366qC0(AbstractC4707sC0 sc0) {
        this.f11123a = sc0;
    }

    @Override // defpackage.AbstractC4707sC0
    public void b(int i, Uri[] uriArr) {
        this.b = false;
        if (i == 3 || i == 2) {
            this.b = true;
        }
        this.f11123a.b(i, uriArr);
    }

    @Override // defpackage.AbstractC4707sC0
    public void c() {
        this.f11123a.c();
    }
}
