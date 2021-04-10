package defpackage;

import androidx.preference.Preference;

/* renamed from: dS  reason: default package and case insensitive filesystem */
/* compiled from: chromium-OculusBrowser.apk-stable-281887347 */
public abstract class AbstractC2184dS extends AbstractC1467Yb0 {

    /* renamed from: a  reason: collision with root package name */
    public final AbstractC1528Zb0 f9782a;

    public AbstractC2184dS(AbstractC1528Zb0 zb0) {
        this.f9782a = zb0;
    }

    @Override // defpackage.AbstractC1528Zb0
    public boolean a(Preference preference) {
        return this.f9782a.a(preference);
    }

    @Override // defpackage.AbstractC1467Yb0, defpackage.AbstractC1528Zb0
    public boolean b(Preference preference) {
        return this.f9782a.b(preference);
    }

    @Override // defpackage.AbstractC1528Zb0
    public boolean c() {
        return this.f9782a.c();
    }
}
