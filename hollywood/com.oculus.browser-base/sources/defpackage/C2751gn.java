package defpackage;

import com.google.android.gms.cast.ApplicationMetadata;
import java.util.HashSet;
import java.util.Iterator;

/* renamed from: gn  reason: default package and case insensitive filesystem */
/* compiled from: chromium-OculusBrowser.apk-stable-281887347 */
public final class C2751gn extends AbstractC1252Um {

    /* renamed from: a  reason: collision with root package name */
    public final /* synthetic */ C2922hn f10020a;

    public C2751gn(C2922hn hnVar, VE1 ve1) {
        this.f10020a = hnVar;
    }

    @Override // defpackage.AbstractC1252Um
    public final void a(int i) {
        Iterator it = new HashSet(this.f10020a.f).iterator();
        while (it.hasNext()) {
            ((AbstractC1252Um) it.next()).a(i);
        }
    }

    @Override // defpackage.AbstractC1252Um
    public final void b(int i) {
        C2922hn.l(this.f10020a, i);
        this.f10020a.b(i);
        Iterator it = new HashSet(this.f10020a.f).iterator();
        while (it.hasNext()) {
            ((AbstractC1252Um) it.next()).b(i);
        }
    }

    @Override // defpackage.AbstractC1252Um
    public final void c(ApplicationMetadata applicationMetadata) {
        Iterator it = new HashSet(this.f10020a.f).iterator();
        while (it.hasNext()) {
            ((AbstractC1252Um) it.next()).c(applicationMetadata);
        }
    }

    @Override // defpackage.AbstractC1252Um
    public final void d() {
        Iterator it = new HashSet(this.f10020a.f).iterator();
        while (it.hasNext()) {
            ((AbstractC1252Um) it.next()).d();
        }
    }

    @Override // defpackage.AbstractC1252Um
    public final void e(int i) {
        Iterator it = new HashSet(this.f10020a.f).iterator();
        while (it.hasNext()) {
            ((AbstractC1252Um) it.next()).e(i);
        }
    }

    @Override // defpackage.AbstractC1252Um
    public final void f() {
        Iterator it = new HashSet(this.f10020a.f).iterator();
        while (it.hasNext()) {
            ((AbstractC1252Um) it.next()).f();
        }
    }
}
