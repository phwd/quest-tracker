package defpackage;

import android.view.View;
import java.util.concurrent.atomic.AtomicInteger;

/* renamed from: uu1  reason: default package and case insensitive filesystem */
/* compiled from: chromium-OculusBrowser.apk-stable-281887347 */
public class C5163uu1 {

    /* renamed from: a  reason: collision with root package name */
    public final View f11445a;
    public int b;
    public int c;
    public int d;
    public int e;

    public C5163uu1(View view) {
        this.f11445a = view;
    }

    public void a() {
        View view = this.f11445a;
        int top = this.d - (view.getTop() - this.b);
        AtomicInteger atomicInteger = AbstractC1920bu1.f9571a;
        view.offsetTopAndBottom(top);
        View view2 = this.f11445a;
        view2.offsetLeftAndRight(this.e - (view2.getLeft() - this.c));
    }
}
