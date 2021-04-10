package defpackage;

import android.content.res.Resources;
import org.chromium.chrome.browser.profiles.Profile;
import org.chromium.chrome.browser.tab.Tab;

/* renamed from: kQ0  reason: default package and case insensitive filesystem */
/* compiled from: chromium-OculusBrowser.apk-stable-281887347 */
public class C3376kQ0 extends AbstractC4422qa0 {

    /* renamed from: a  reason: collision with root package name */
    public final int f10276a;
    public Tab b;

    public C3376kQ0(Resources resources) {
        this.f10276a = AbstractC2934hr.b(resources, a());
    }

    @Override // defpackage.AbstractC4422qa0
    public boolean a() {
        Tab tab = this.b;
        if (tab == null) {
            return false;
        }
        return tab.a();
    }

    @Override // defpackage.AbstractC4422qa0
    public Profile b() {
        Tab tab = this.b;
        if (tab != null) {
            return Profile.a(tab.l());
        }
        return null;
    }

    @Override // defpackage.AbstractC4422qa0
    public int c() {
        return this.f10276a;
    }

    @Override // defpackage.AbstractC4422qa0
    public Tab d() {
        return this.b;
    }

    @Override // defpackage.AbstractC4422qa0
    public AbstractC1377Wn0 e() {
        return AbstractC1377Wn0.f9173a;
    }

    @Override // defpackage.AbstractC4422qa0
    public boolean g() {
        return false;
    }

    @Override // defpackage.AbstractC4422qa0
    public Pq1 h() {
        return Pq1.c;
    }

    @Override // defpackage.AbstractC4422qa0
    public String i() {
        return AbstractC3378kR0.c;
    }

    @Override // defpackage.AbstractC4422qa0
    public void k(AbstractC4592ra0 ra0) {
    }

    @Override // defpackage.AbstractC4422qa0
    public int l(boolean z) {
        return 16;
    }

    @Override // defpackage.AbstractC4422qa0
    public int m() {
        return 0;
    }

    @Override // defpackage.AbstractC4422qa0
    public int n() {
        return 0;
    }

    @Override // defpackage.AbstractC4422qa0
    public int o(boolean z) {
        return 0;
    }

    @Override // defpackage.AbstractC4422qa0
    public int p() {
        return 0;
    }

    @Override // defpackage.AbstractC4422qa0
    public String q() {
        return "";
    }

    @Override // defpackage.AbstractC4422qa0
    public boolean r() {
        return this.b != null;
    }

    @Override // defpackage.AbstractC4422qa0
    public boolean s() {
        return false;
    }

    @Override // defpackage.AbstractC4422qa0
    public boolean t() {
        return false;
    }

    @Override // defpackage.AbstractC4422qa0
    public boolean v() {
        return false;
    }

    @Override // defpackage.AbstractC4422qa0
    public void w(AbstractC4592ra0 ra0) {
    }
}
