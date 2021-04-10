package defpackage;

import android.content.Context;
import android.os.Bundle;
import android.view.View;
import android.view.ViewGroup;

/* renamed from: h8  reason: default package and case insensitive filesystem */
/* compiled from: chromium-OculusBrowser.apk-stable-281887347 */
public final class C2815h8 {

    /* renamed from: a  reason: collision with root package name */
    public int f10049a;
    public int b;
    public int c;
    public int d;
    public ViewGroup e;
    public View f;
    public View g;
    public C4616ri0 h;
    public C1614a90 i;
    public Context j;
    public boolean k;
    public boolean l;
    public boolean m;
    public boolean n;
    public boolean o = false;
    public boolean p;
    public Bundle q;

    public C2815h8(int i2) {
        this.f10049a = i2;
    }

    public void a(C4616ri0 ri0) {
        C1614a90 a90;
        C4616ri0 ri02 = this.h;
        if (ri0 != ri02) {
            if (ri02 != null) {
                ri02.t(this.i);
            }
            this.h = ri0;
            if (ri0 != null && (a90 = this.i) != null) {
                ri0.b(a90, ri0.b);
            }
        }
    }
}
