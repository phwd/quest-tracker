package org.chromium.components.media_router;

import java.util.Objects;

/* compiled from: chromium-OculusBrowser.apk-stable-281887347 */
public class FlingingControllerBridge {

    /* renamed from: a  reason: collision with root package name */
    public final AbstractC2352eR f10851a;
    public long b;

    public FlingingControllerBridge(AbstractC2352eR eRVar) {
        this.f10851a = eRVar;
    }

    public void addNativeFlingingController(long j) {
        this.b = j;
        ((C3377kR) this.f10851a).d = this;
    }

    public void clearNativeFlingingController() {
        ((C3377kR) this.f10851a).d = null;
        this.b = 0;
    }

    public long getApproximateCurrentTime() {
        N21 n21 = ((C3377kR) this.f10851a).f10277a;
        if (n21.c == 0) {
            return 0;
        }
        if (!n21.d) {
            return Math.max(n21.b, 0L);
        }
        long currentTimeMillis = n21.b + ((long) (n21.e * ((double) (System.currentTimeMillis() - n21.c))));
        long j = n21.f8523a;
        if (j >= 0) {
            currentTimeMillis = Math.min(currentTimeMillis, j);
        }
        return Math.max(currentTimeMillis, 0L);
    }

    public void pause() {
        C3377kR kRVar = (C3377kR) this.f10851a;
        Objects.requireNonNull(kRVar);
        if (kRVar.b.h()) {
            kRVar.b.e().n().b(new C2694gR(kRVar));
        }
    }

    public void play() {
        C3377kR kRVar = (C3377kR) this.f10851a;
        Objects.requireNonNull(kRVar);
        if (kRVar.b.h()) {
            if (!kRVar.e) {
                kRVar.b(0);
            } else {
                kRVar.b.e().o().b(new C2523fR(kRVar));
            }
        }
    }

    public void seek(long j) {
        C3377kR kRVar = (C3377kR) this.f10851a;
        Objects.requireNonNull(kRVar);
        if (kRVar.b.h()) {
            if (!kRVar.e) {
                kRVar.b(j);
                return;
            }
            kRVar.b.e().q(j).b(new C3206jR(kRVar));
            N21 n21 = kRVar.f10277a;
            n21.d = false;
            n21.b = j;
            n21.c = System.currentTimeMillis();
        }
    }

    public void setMute(boolean z) {
        DB0 db0;
        C3377kR kRVar = (C3377kR) this.f10851a;
        Objects.requireNonNull(kRVar);
        if (kRVar.b.h()) {
            ML0 e = kRVar.b.e();
            Objects.requireNonNull(e);
            SE0.e("Must be called from the main thread.");
            if (!e.w()) {
                db0 = ML0.s(17, null);
            } else {
                UD1 ud1 = new UD1(e, e.g, z, null);
                e.t(ud1);
                db0 = ud1;
            }
            db0.b(new C2865hR(kRVar));
        }
    }

    public void setVolume(float f) {
        DB0 db0;
        C3377kR kRVar = (C3377kR) this.f10851a;
        Objects.requireNonNull(kRVar);
        double d = (double) f;
        if (kRVar.b.h()) {
            ML0 e = kRVar.b.e();
            Objects.requireNonNull(e);
            SE0.e("Must be called from the main thread.");
            if (!e.w()) {
                db0 = ML0.s(17, null);
            } else {
                LD1 ld1 = new LD1(e, e.g, d, null);
                e.t(ld1);
                db0 = ld1;
            }
            db0.b(new C3036iR(kRVar));
        }
    }
}
