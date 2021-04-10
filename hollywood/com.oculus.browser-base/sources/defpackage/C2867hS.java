package defpackage;

import android.view.View;

/* renamed from: hS  reason: default package and case insensitive filesystem */
/* compiled from: chromium-OculusBrowser.apk-stable-281887347 */
public class C2867hS extends AbstractC5255vS {

    /* renamed from: a  reason: collision with root package name */
    public final /* synthetic */ AbstractComponentCallbacksC3550lS f10070a;

    public C2867hS(AbstractComponentCallbacksC3550lS lSVar) {
        this.f10070a = lSVar;
    }

    @Override // defpackage.AbstractC5255vS
    public View a(int i) {
        View view = this.f10070a.k0;
        if (view != null) {
            return view.findViewById(i);
        }
        throw new IllegalStateException("Fragment " + this + " does not have a view");
    }

    @Override // defpackage.AbstractC5255vS
    public boolean b() {
        return this.f10070a.k0 != null;
    }
}
