package X;

import android.net.Uri;
import java.io.File;
import java.util.Arrays;
import javax.annotation.Nullable;
import javax.annotation.concurrent.Immutable;

@Immutable
/* renamed from: X.1pd  reason: invalid class name and case insensitive filesystem */
public final class C09811pd {
    public static final AnonymousClass0KN<C09811pd, Uri> A0G = new C10481sv();
    @Nullable
    public File A00;
    public final int A01;
    public final int A02;
    public final Uri A03;
    @Nullable
    public final AnonymousClass1jz A04;
    public final AnonymousClass1r8 A05;
    public final EnumC10221rl A06;
    public final AnonymousClass1pN A07;
    @Nullable
    public final AnonymousClass1tF A08;
    public final AnonymousClass1sR A09;
    public final AnonymousClass1pG A0A;
    @Nullable
    public final Boolean A0B;
    public final boolean A0C;
    public final boolean A0D;
    public final boolean A0E;
    public final boolean A0F;

    public final synchronized File A00() {
        File file;
        file = this.A00;
        if (file == null) {
            file = new File(this.A03.getPath());
            this.A00 = file;
        }
        return file;
    }

    public final int hashCode() {
        return Arrays.hashCode(new Object[]{this.A09, this.A03, Boolean.valueOf(this.A0E), this.A04, this.A06, this.A0A, Boolean.valueOf(this.A0C), Boolean.valueOf(this.A0D), this.A05, this.A0B, null, this.A07, null, null, Integer.valueOf(this.A01)});
    }

    public final boolean equals(@Nullable Object obj) {
        if (!(obj instanceof C09811pd)) {
            return false;
        }
        C09811pd r4 = (C09811pd) obj;
        if (this.A0E != r4.A0E || this.A0C != r4.A0C || this.A0D != r4.A0D || !AnonymousClass0KT.A01(this.A03, r4.A03) || !AnonymousClass0KT.A01(this.A09, r4.A09) || !AnonymousClass0KT.A01(this.A00, r4.A00) || !AnonymousClass0KT.A01(this.A04, r4.A04) || !AnonymousClass0KT.A01(this.A05, r4.A05) || !AnonymousClass0KT.A01(this.A06, r4.A06) || !AnonymousClass0KT.A01(this.A0A, r4.A0A) || !AnonymousClass0KT.A01(this.A0B, r4.A0B) || !AnonymousClass0KT.A01(null, null) || !AnonymousClass0KT.A01(this.A07, r4.A07) || this.A01 != r4.A01) {
            return false;
        }
        return true;
    }

    /* JADX WARNING: Code restructure failed: missing block: B:28:0x00a3, code lost:
        if (r0.startsWith("video/") == false) goto L_0x00a5;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:43:0x00ea, code lost:
        if ("android.resource".equals(r2.getScheme()) == false) goto L_0x00ec;
     */
    /* JADX WARNING: Removed duplicated region for block: B:10:0x003f  */
    /* JADX WARNING: Removed duplicated region for block: B:7:0x0026  */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public C09811pd(X.AnonymousClass1pj r4) {
        /*
        // Method dump skipped, instructions count: 239
        */
        throw new UnsupportedOperationException("Method not decompiled: X.C09811pd.<init>(X.1pj):void");
    }

    public final String toString() {
        AnonymousClass0KS A002 = AnonymousClass0KT.A00(this);
        AnonymousClass0KS.A00(A002, "uri", this.A03);
        AnonymousClass0KS.A00(A002, "cacheChoice", this.A09);
        AnonymousClass0KS.A00(A002, "decodeOptions", this.A05);
        AnonymousClass0KS.A00(A002, "postprocessor", null);
        AnonymousClass0KS.A00(A002, "priority", this.A06);
        AnonymousClass0KS.A00(A002, "resizeOptions", null);
        AnonymousClass0KS.A00(A002, "rotationOptions", this.A07);
        AnonymousClass0KS.A00(A002, "bytesRange", this.A04);
        AnonymousClass0KS.A00(A002, "resizingAllowedOverride", null);
        AnonymousClass0KS.A00(A002, "progressiveRenderingEnabled", String.valueOf(this.A0F));
        AnonymousClass0KS.A00(A002, "localThumbnailPreviewsEnabled", String.valueOf(this.A0E));
        AnonymousClass0KS.A00(A002, "lowestPermittedRequestLevel", this.A0A);
        AnonymousClass0KS.A00(A002, "isDiskCacheEnabled", String.valueOf(this.A0C));
        AnonymousClass0KS.A00(A002, "isMemoryCacheEnabled", String.valueOf(this.A0D));
        AnonymousClass0KS.A00(A002, "decodePrefetches", this.A0B);
        AnonymousClass0KS.A00(A002, "delayMs", String.valueOf(this.A01));
        return A002.toString();
    }
}
