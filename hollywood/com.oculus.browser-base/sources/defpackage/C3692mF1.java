package defpackage;

import com.google.android.gms.cast.ApplicationMetadata;
import com.google.android.gms.common.api.Status;

/* renamed from: mF1  reason: default package and case insensitive filesystem */
/* compiled from: chromium-OculusBrowser.apk-stable-281887347 */
public final class C3692mF1 implements AbstractC1008Qm {
    public final Status F;
    public final ApplicationMetadata G;
    public final String H;
    public final String I;

    /* renamed from: J  reason: collision with root package name */
    public final boolean f10409J;

    public C3692mF1(Status status) {
        this.F = status;
        this.G = null;
        this.H = null;
        this.I = null;
        this.f10409J = false;
    }

    @Override // defpackage.AM0
    public final Status b() {
        return this.F;
    }

    @Override // defpackage.AbstractC1008Qm
    public final boolean c() {
        return this.f10409J;
    }

    @Override // defpackage.AbstractC1008Qm
    public final String h() {
        return this.H;
    }

    @Override // defpackage.AbstractC1008Qm
    public final String i() {
        return this.I;
    }

    @Override // defpackage.AbstractC1008Qm
    public final ApplicationMetadata t() {
        return this.G;
    }

    public C3692mF1(Status status, ApplicationMetadata applicationMetadata, String str, String str2, boolean z) {
        this.F = status;
        this.G = applicationMetadata;
        this.H = str;
        this.I = str2;
        this.f10409J = z;
    }
}
