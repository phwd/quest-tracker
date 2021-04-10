package defpackage;

import android.app.Activity;
import android.nfc.FormatException;
import android.nfc.NdefMessage;
import android.nfc.NfcAdapter;
import android.nfc.NfcManager;
import android.nfc.TagLostException;
import android.os.Process;
import android.os.Vibrator;
import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import org.chromium.base.ContextUtils;
import org.chromium.device.nfc.NfcDelegate;

/* renamed from: Ao0  reason: default package and case insensitive filesystem */
/* compiled from: chromium-OculusBrowser.apk-stable-281887347 */
public class C0038Ao0 implements AbstractC3951no0 {
    public final int F;
    public final NfcDelegate G;
    public LN0 H;
    public final NfcManager I;

    /* renamed from: J  reason: collision with root package name */
    public final NfcAdapter f7698J;
    public Activity K;
    public final boolean L;
    public boolean M;
    public C5994zo0 N;
    public C5824yo0 O;
    public C1196To0 P;
    public AbstractC4464qo0 Q;
    public final List R = new ArrayList();
    public Vibrator S;

    public C0038Ao0(int i, NfcDelegate nfcDelegate, B30 b30) {
        this.F = i;
        this.G = nfcDelegate;
        this.M = false;
        if (b30 != null) {
            this.H = AbstractC3099ip0.f10164a.b(this, b30);
        }
        boolean z = ContextUtils.getApplicationContext().checkPermission("android.permission.NFC", Process.myPid(), Process.myUid()) == 0;
        this.L = z;
        nfcDelegate.b(i, new C5654xo0(this));
        if (!z) {
            AbstractC1220Ua0.f("NfcImpl", "NFC operations are not permitted.", new Object[0]);
            this.f7698J = null;
            this.I = null;
        } else {
            NfcManager nfcManager = (NfcManager) ContextUtils.getApplicationContext().getSystemService("nfc");
            this.I = nfcManager;
            if (nfcManager == null) {
                AbstractC1220Ua0.f("NfcImpl", "NFC is not supported.", new Object[0]);
                this.f7698J = null;
            } else {
                this.f7698J = nfcManager.getDefaultAdapter();
            }
        }
        this.S = (Vibrator) ContextUtils.getApplicationContext().getSystemService("vibrator");
    }

    @Override // defpackage.AbstractC3951no0
    public void E(AbstractC4464qo0 qo0) {
        this.Q = qo0;
    }

    @Override // defpackage.AbstractC3951no0
    public void I(int i) {
        if (this.R.contains(Integer.valueOf(i))) {
            List list = this.R;
            list.remove(list.indexOf(Integer.valueOf(i)));
            if (this.O == null && this.R.size() == 0) {
                i0();
            }
        }
    }

    @Override // defpackage.AbstractC3951no0
    public void J(int i, C2587fp0 fp0) {
        if (f0(fp0)) {
            if (this.R.contains(Integer.valueOf(i))) {
                fp0.a(h0(2, "Cannot start because the received scan request is duplicate."));
                return;
            }
            this.R.add(Integer.valueOf(i));
            fp0.a(null);
            j0();
            o0();
        }
    }

    @Override // defpackage.AbstractC3951no0
    public void M() {
        g0(h0(4, "The push operation is already cancelled."));
    }

    @Override // defpackage.AbstractC3951no0
    public void S(C3264jn0 jn0, C3777mn0 mn0, C1733ap0 ap0) {
        C3606ln0[] ln0Arr;
        String str;
        if (f0(ap0)) {
            if (this.M) {
                ap0.a(h0(4, "Cannot push the message because NFC operations are suspended."));
            }
            boolean z = false;
            if (jn0 != null && (ln0Arr = jn0.d) != null && ln0Arr.length != 0) {
                int i = 0;
                while (true) {
                    C3606ln0[] ln0Arr2 = jn0.d;
                    if (i >= ln0Arr2.length) {
                        z = true;
                        break;
                    }
                    C3606ln0 ln0 = ln0Arr2[i];
                    if (!(ln0 != null && (ln0.e.equals("empty") || (ln0.j != null && (!ln0.e.equals("mime") ? ln0.f == null : (str = ln0.f) != null && !str.isEmpty()))))) {
                        break;
                    }
                    i++;
                }
            }
            if (!z) {
                ap0.a(h0(3, "Cannot push the message because it's invalid."));
                return;
            }
            C5824yo0 yo0 = this.O;
            if (yo0 != null) {
                C3093in0 h0 = h0(4, "Push is cancelled due to a new push request.");
                C1733ap0 ap02 = yo0.c;
                if (ap02 != null) {
                    ap02.a(h0);
                }
            }
            this.O = new C5824yo0(jn0, mn0, ap0);
            j0();
            n0();
        }
    }

    @Override // defpackage.AbstractC0543Ix
    public void Y(C5475wl0 wl0) {
    }

    @Override // java.io.Closeable, defpackage.AbstractC3313k30, java.lang.AutoCloseable
    public void close() {
        this.G.a(this.F);
        i0();
    }

    public final boolean f0(AbstractC1494Yl yl) {
        C3093in0 in0;
        NfcAdapter nfcAdapter;
        if (!this.L || this.K == null) {
            in0 = h0(0, "The operation is not allowed.");
        } else if (this.I == null || (nfcAdapter = this.f7698J) == null) {
            in0 = h0(1, "NFC is not supported.");
        } else {
            in0 = !nfcAdapter.isEnabled() ? h0(2, "NFC setting is disabled.") : null;
        }
        if (in0 == null) {
            return true;
        }
        yl.a(in0);
        return false;
    }

    public final void g0(C3093in0 in0) {
        C5824yo0 yo0 = this.O;
        if (yo0 != null) {
            C1733ap0 ap0 = yo0.c;
            if (ap0 != null) {
                ap0.a(in0);
            }
            this.O = null;
            if (this.R.size() == 0) {
                i0();
            }
        }
    }

    public final C3093in0 h0(int i, String str) {
        C3093in0 in0 = new C3093in0();
        in0.d = i;
        in0.e = str;
        return in0;
    }

    public final void i0() {
        if (this.N != null) {
            this.N = null;
            Activity activity = this.K;
            if (activity != null && this.f7698J != null && !activity.isDestroyed()) {
                this.f7698J.disableReaderMode(this.K);
            }
        }
    }

    public final void j0() {
        if (this.N == null && this.K != null && this.f7698J != null) {
            if (this.O != null || this.R.size() != 0) {
                C5994zo0 zo0 = new C5994zo0(this);
                this.N = zo0;
                this.f7698J.enableReaderMode(this.K, zo0, 271, null);
            }
        }
    }

    public final void k0(C3093in0 in0) {
        if (this.R.size() != 0) {
            ((C5144uo0) this.Q).f0(in0);
        }
    }

    public final void l0(C3264jn0 jn0) {
        if (this.R.size() != 0) {
            int[] iArr = new int[this.R.size()];
            for (int i = 0; i < this.R.size(); i++) {
                iArr[i] = ((Integer) this.R.get(i)).intValue();
            }
            ((C5144uo0) this.Q).g0(iArr, this.P.d, jn0);
        }
    }

    public final void m0(C3093in0 in0) {
        g0(null);
    }

    public final void n0() {
        boolean z;
        C1196To0 to0 = this.P;
        if (to0 != null && this.O != null) {
            try {
                to0.a();
                z = false;
            } catch (IOException unused) {
                z = to0.c;
            }
            if (z) {
                this.P = null;
                return;
            }
            try {
                this.P.a();
                if (this.O.b.d || this.P.b.c()) {
                    this.P.b.b(AbstractC3435kn0.f(this.O.f11699a));
                    m0(null);
                    return;
                }
                AbstractC1220Ua0.f("NfcImpl", "Cannot overwrite the NFC tag due to existing data on it.", new Object[0]);
                g0(h0(0, "NDEFWriteOptions#overwrite does not allow overwrite."));
                this.P = null;
            } catch (I30 unused2) {
                AbstractC1220Ua0.f("NfcImpl", "Cannot write data to NFC tag. Invalid NdefMessage.", new Object[0]);
                g0(h0(3, "Cannot push the message because it's invalid."));
                this.P = null;
            } catch (TagLostException e) {
                StringBuilder i = AbstractC2531fV.i("Cannot write data to NFC tag. Tag is lost: ");
                i.append(e.getMessage());
                AbstractC1220Ua0.f("NfcImpl", i.toString(), new Object[0]);
                g0(h0(5, "Failed to write because the tag is lost: " + e.getMessage()));
                this.P = null;
            } catch (FormatException | IOException | IllegalStateException e2) {
                StringBuilder i2 = AbstractC2531fV.i("Cannot write data to NFC tag: ");
                i2.append(e2.getMessage());
                AbstractC1220Ua0.f("NfcImpl", i2.toString(), new Object[0]);
                g0(h0(5, "Failed to write due to an IO error: " + e2.getMessage()));
                this.P = null;
            }
        }
    }

    public final void o0() {
        boolean z;
        if (this.P != null && this.Q != null && this.R.size() != 0 && !this.M) {
            C1196To0 to0 = this.P;
            Objects.requireNonNull(to0);
            try {
                to0.a();
                z = false;
            } catch (IOException unused) {
                z = to0.c;
            }
            if (z) {
                this.P = null;
                return;
            }
            try {
                this.P.a();
                NdefMessage a2 = this.P.b.a();
                if (a2 == null) {
                    C3264jn0 jn0 = new C3264jn0();
                    jn0.d = new C3606ln0[0];
                    l0(jn0);
                    return;
                }
                l0(AbstractC3435kn0.e(a2));
            } catch (UnsupportedEncodingException e) {
                StringBuilder i = AbstractC2531fV.i("Cannot read data from NFC tag. Cannot convert to NdefMessage:");
                i.append(e.getMessage());
                AbstractC1220Ua0.f("NfcImpl", i.toString(), new Object[0]);
                k0(h0(3, "Failed to decode the NdefMessage read from the tag: " + e.getMessage()));
            } catch (TagLostException e2) {
                StringBuilder i2 = AbstractC2531fV.i("Cannot read data from NFC tag. Tag is lost: ");
                i2.append(e2.getMessage());
                AbstractC1220Ua0.f("NfcImpl", i2.toString(), new Object[0]);
                k0(h0(5, "Failed to read because the tag is lost: " + e2.getMessage()));
            } catch (FormatException | IOException | IllegalStateException e3) {
                StringBuilder i3 = AbstractC2531fV.i("Cannot read data from NFC tag. IO_ERROR: ");
                i3.append(e3.getMessage());
                AbstractC1220Ua0.f("NfcImpl", i3.toString(), new Object[0]);
                k0(h0(5, "Failed to read due to an IO error: " + e3.getMessage()));
            }
        }
    }
}
