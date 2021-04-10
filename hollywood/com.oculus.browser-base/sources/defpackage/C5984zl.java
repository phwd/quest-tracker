package defpackage;

import com.google.android.gms.common.api.Status;
import java.util.Objects;

/* renamed from: zl  reason: default package and case insensitive filesystem */
/* compiled from: chromium-OculusBrowser.apk-stable-281887347 */
public final /* synthetic */ class C5984zl implements BM0 {

    /* renamed from: a  reason: collision with root package name */
    public final C0153Cl f11766a;
    public final String b;
    public final int c;

    public C5984zl(C0153Cl cl, String str, int i) {
        this.f11766a = cl;
        this.b = str;
        this.c = i;
    }

    @Override // defpackage.BM0
    public void a(AM0 am0) {
        C0153Cl cl = this.f11766a;
        String str = this.b;
        int i = this.c;
        Status status = (Status) am0;
        Objects.requireNonNull(cl);
        if (!status.x()) {
            AbstractC1220Ua0.a("CafMR", "Failed to send the message: " + status, new Object[0]);
            return;
        }
        cl.h(str, "app_message", null, i);
    }
}
