package defpackage;

import android.net.Uri;
import org.chromium.chrome.browser.password_manager.settings.ExportErrorDialogFragment;
import org.chromium.chrome.browser.password_manager.settings.ExportWarningDialogFragment;
import org.chromium.chrome.browser.password_manager.settings.ProgressBarDialogFragment;

/* renamed from: SM  reason: default package */
/* compiled from: chromium-OculusBrowser.apk-stable-281887347 */
public class SM {

    /* renamed from: a  reason: collision with root package name */
    public int f8891a;
    public Uri b;
    public Integer c;
    public final RE d = new RE(null);
    public KM e;
    public ExportWarningDialogFragment f;
    public C0058Ay0 g;

    public final void a() {
        ExportWarningDialogFragment exportWarningDialogFragment = new ExportWarningDialogFragment();
        this.f = exportWarningDialogFragment;
        exportWarningDialogFragment.M0 = new PM(this);
        exportWarningDialogFragment.k1(this.g.f7709a.W, null);
    }

    public void b(int i, String str, int i2, int i3) {
        this.d.a(new OM(this, i, str, i2, i3));
    }

    public final void c() {
        ExportErrorDialogFragment exportErrorDialogFragment = new ExportErrorDialogFragment();
        KM km = this.e;
        int i = km.f8361a;
        exportErrorDialogFragment.N0 = km;
        this.e = null;
        exportErrorDialogFragment.M0 = new RM(this, i);
        exportErrorDialogFragment.k1(this.g.f7709a.W, null);
    }

    public final void d() {
        if (this.f8891a == 2) {
            if (this.c == null) {
                ProgressBarDialogFragment progressBarDialogFragment = new ProgressBarDialogFragment();
                progressBarDialogFragment.M0 = new QM(this);
                RE re = this.d;
                KS ks = this.g.f7709a.W;
                re.f8819a = progressBarDialogFragment;
                progressBarDialogFragment.k1(ks, null);
                RunnableC5266vX0 vx0 = new RunnableC5266vX0(2, new PE(re));
                re.c = vx0;
                C0209Di1 di1 = (C0209Di1) re.b;
                di1.f7905a.postDelayed(vx0, di1.b);
                return;
            }
            this.d.a(new NM(this));
        }
    }
}
