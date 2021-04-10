package defpackage;

import android.os.Handler;
import android.os.Message;
import com.oculus.os.PackageMetadata;
import java.lang.ref.WeakReference;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

/* renamed from: Rg0  reason: default package and case insensitive filesystem */
/* compiled from: chromium-OculusBrowser.apk-stable-281887347 */
public final class HandlerC1055Rg0 extends Handler {

    /* renamed from: a  reason: collision with root package name */
    public final ArrayList f8845a = new ArrayList();
    public final List b = new ArrayList();
    public final /* synthetic */ C1543Zg0 c;

    public HandlerC1055Rg0(C1543Zg0 zg0) {
        this.c = zg0;
    }

    public final void a(C0811Ng0 ng0, int i, Object obj, int i2) {
        C3246jh0 jh0 = ng0.f8566a;
        AbstractC0750Mg0 mg0 = ng0.b;
        int i3 = 65280 & i;
        if (i3 == 256) {
            C2392eh0 eh0 = (i == 264 || i == 262) ? (C2392eh0) ((C1754aw0) obj).b : (C2392eh0) obj;
            if (i == 264 || i == 262) {
                C2392eh0 eh02 = (C2392eh0) ((C1754aw0) obj).f9500a;
            }
            if (eh0 != null) {
                if ((ng0.d & 2) != 0 || eh0.i(ng0.c)) {
                    switch (i) {
                        case PackageMetadata.Signature.Algorithm.RSASSA_PSS_SHA_256 /*{ENCODED_INT: 257}*/:
                            mg0.d(jh0, eh0);
                            return;
                        case PackageMetadata.Signature.Algorithm.RSASSA_PSS_SHA_512 /*{ENCODED_INT: 258}*/:
                            mg0.f(jh0, eh0);
                            return;
                        case PackageMetadata.Signature.Algorithm.RSASSA_PKCS1_5_SHA_256 /*{ENCODED_INT: 259}*/:
                            mg0.e(jh0, eh0);
                            return;
                        case PackageMetadata.Signature.Algorithm.RSASSA_PKCS1_5_SHA_512 /*{ENCODED_INT: 260}*/:
                            mg0.j(jh0, eh0);
                            return;
                        case 261:
                            Objects.requireNonNull(mg0);
                            return;
                        case 262:
                            mg0.g(jh0, eh0);
                            return;
                        case 263:
                            mg0.i(jh0, eh0, i2);
                            return;
                        case 264:
                            mg0.g(jh0, eh0);
                            return;
                        default:
                            return;
                    }
                }
            }
        } else if (i3 == 512) {
            C2051ch0 ch0 = (C2051ch0) obj;
            switch (i) {
                case PackageMetadata.Signature.Algorithm.ECDSA_SHA_256 /*{ENCODED_INT: 513}*/:
                    mg0.a(jh0, ch0);
                    return;
                case PackageMetadata.Signature.Algorithm.ECDSA_SHA_512 /*{ENCODED_INT: 514}*/:
                    mg0.c(jh0, ch0);
                    return;
                case 515:
                    mg0.b(jh0, ch0);
                    return;
                default:
                    return;
            }
        }
    }

    public void b(int i, Object obj) {
        obtainMessage(i, obj).sendToTarget();
    }

    public void c(int i, Object obj, int i2) {
        Message obtainMessage = obtainMessage(i, obj);
        obtainMessage.arg1 = i2;
        obtainMessage.sendToTarget();
    }

    public void handleMessage(Message message) {
        int o;
        int i = message.what;
        Object obj = message.obj;
        int i2 = message.arg1;
        if (i == 259 && this.c.g().c.equals(((C2392eh0) obj).c)) {
            this.c.q(true);
        }
        if (i == 262) {
            C2392eh0 eh0 = (C2392eh0) ((C1754aw0) obj).b;
            this.c.l.k(eh0);
            if (this.c.p != null && eh0.e()) {
                for (C2392eh0 eh02 : this.b) {
                    this.c.l.j(eh02);
                }
                this.b.clear();
            }
        } else if (i != 264) {
            switch (i) {
                case PackageMetadata.Signature.Algorithm.RSASSA_PSS_SHA_256 /*{ENCODED_INT: 257}*/:
                    this.c.l.i((C2392eh0) obj);
                    break;
                case PackageMetadata.Signature.Algorithm.RSASSA_PSS_SHA_512 /*{ENCODED_INT: 258}*/:
                    this.c.l.j((C2392eh0) obj);
                    break;
                case PackageMetadata.Signature.Algorithm.RSASSA_PKCS1_5_SHA_256 /*{ENCODED_INT: 259}*/:
                    C2392eh0 eh03 = (C2392eh0) obj;
                    J51 j51 = (J51) this.c.l;
                    Objects.requireNonNull(j51);
                    if (eh03.d() != j51 && (o = j51.o(eh03)) >= 0) {
                        j51.x((I51) j51.t.get(o));
                        break;
                    }
            }
        } else {
            C2392eh0 eh04 = (C2392eh0) ((C1754aw0) obj).b;
            this.b.add(eh04);
            this.c.l.i(eh04);
            this.c.l.k(eh04);
        }
        try {
            int size = this.c.d.size();
            while (true) {
                size--;
                if (size >= 0) {
                    C3246jh0 jh0 = (C3246jh0) ((WeakReference) this.c.d.get(size)).get();
                    if (jh0 == null) {
                        this.c.d.remove(size);
                    } else {
                        this.f8845a.addAll(jh0.c);
                    }
                } else {
                    int size2 = this.f8845a.size();
                    for (int i3 = 0; i3 < size2; i3++) {
                        a((C0811Ng0) this.f8845a.get(i3), i, obj, i2);
                    }
                    return;
                }
            }
        } finally {
            this.f8845a.clear();
        }
    }
}
