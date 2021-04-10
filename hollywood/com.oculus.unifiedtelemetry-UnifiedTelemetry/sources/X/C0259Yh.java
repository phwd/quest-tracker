package X;

import com.facebook.infer.annotation.NullsafeStrict;
import javax.annotation.Nullable;

@NullsafeStrict
/* renamed from: X.Yh  reason: case insensitive filesystem */
public final class C0259Yh extends GK {
    public static Fe A01;
    public static C0259Yh A02;
    @Nullable
    public YE A00;

    @Override // X.GK
    public final boolean A08() {
        return false;
    }

    @Override // X.GK
    public final YE A06() {
        YE ye = this.A00;
        if (ye != null) {
            return ye;
        }
        YE A022 = A01.A0C.A02();
        this.A00 = A022;
        return A022;
    }

    @Override // X.GK
    public final void A07() {
        StackTraceElement[] stackTrace = Thread.currentThread().getStackTrace();
        StringBuilder sb = new StringBuilder();
        for (StackTraceElement stackTraceElement : stackTrace) {
            sb.append("SampledOutEvent is logged: ");
            sb.append(stackTraceElement.toString());
            sb.append("\n");
        }
        String obj = sb.toString();
        if (Mu.A01.A3F(6)) {
            Mu.A01.A5z("SampledOutEventBuilder", obj);
        }
        this.A00 = null;
    }

    @Override // X.GK
    public final GK A01(long j) {
        return this;
    }

    @Override // X.GK
    public final GK A02(String str, @Nullable Boolean bool) {
        return this;
    }

    @Override // X.GK
    public final GK A03(String str, @Nullable Number number) {
        return this;
    }

    @Override // X.GK
    public final GK A04(String str, String str2) {
        return this;
    }

    @Override // X.GK
    public final GK A05(String str, @Nullable String str2) {
        return this;
    }
}
