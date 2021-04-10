package X;

import android.os.Handler;
import java.util.HashMap;
import javax.annotation.Nullable;

/* renamed from: X.1xu  reason: invalid class name */
public final class AnonymousClass1xu {
    public static final AnonymousClass1zF A0G = new AnonymousClass1yP();
    public double A00 = 1.0d;
    @Nullable
    public C11221xv A01;
    @Nullable
    public AnonymousClass1X9 A02;
    public C11191xr A03;
    @Nullable
    public AnonymousClass1yI A04;
    public HashMap<AnonymousClass1yA, AnonymousClass1yQ> A05;
    public final Handler A06;
    public final AbstractC11091xe A07;
    public final AnonymousClass1z3 A08;
    public final Runnable A09 = new AnonymousClass1yF(this);
    public volatile long A0A;
    public volatile boolean A0B;
    public volatile boolean A0C;
    public volatile boolean A0D;
    public volatile boolean A0E;
    public volatile boolean A0F;

    private void A00(AnonymousClass1zF r6, int i, String str, String str2, @Nullable Exception exc) {
        C11081xd r2 = new C11081xd(i, str, str2, exc);
        r2.A00("muxer_first_samples_written", String.format(null, "v%b_a%b", Boolean.valueOf(this.A0D), Boolean.valueOf(this.A0C)));
        r2.A00("muxer_has_started", String.valueOf(this.A0E));
        AnonymousClass1z7.A01(r6, this.A06, r2);
    }

    public static void A01(@Nullable AnonymousClass1xu r5, AnonymousClass1yI r6, int i, @Nullable String str, Exception exc) {
        C11081xd r2 = new C11081xd(i, str, exc);
        r2.A00("muxer_first_samples_written", String.format(null, "v%b_a%b", Boolean.valueOf(r5.A0D), Boolean.valueOf(r5.A0C)));
        r2.A00("muxer_has_started", String.valueOf(r5.A0E));
        if (r6 != null) {
            r5.A06.post(new AnonymousClass1yC(r5, r6, r2));
        }
    }

    /* JADX WARNING: Removed duplicated region for block: B:35:0x006e A[Catch:{ Exception -> 0x004f, all -> 0x00ad }] */
    /* JADX WARNING: Removed duplicated region for block: B:46:0x0090 A[Catch:{ Exception -> 0x004f, all -> 0x00ad }] */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public final void A02(X.AnonymousClass1zF r19) {
        /*
        // Method dump skipped, instructions count: 204
        */
        throw new UnsupportedOperationException("Method not decompiled: X.AnonymousClass1xu.A02(X.1zF):void");
    }

    /* JADX WARNING: Removed duplicated region for block: B:12:0x002c  */
    /* JADX WARNING: Removed duplicated region for block: B:8:0x001e  */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public final void A03(X.AnonymousClass1yA r22, java.nio.ByteBuffer r23, android.media.MediaCodec.BufferInfo r24) {
        /*
        // Method dump skipped, instructions count: 466
        */
        throw new UnsupportedOperationException("Method not decompiled: X.AnonymousClass1xu.A03(X.1yA, java.nio.ByteBuffer, android.media.MediaCodec$BufferInfo):void");
    }

    public AnonymousClass1xu(Handler handler, AbstractC11091xe r4, AnonymousClass1z3 r5, @Nullable AnonymousClass1X9 r6) {
        this.A06 = handler;
        this.A07 = r4;
        this.A08 = r5;
        this.A02 = r6;
    }
}
