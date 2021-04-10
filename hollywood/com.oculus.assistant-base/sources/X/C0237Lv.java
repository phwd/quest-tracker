package X;

import com.facebook.assistant.oacr.OacrConstants;

/* renamed from: X.Lv  reason: case insensitive filesystem */
public final class C0237Lv {
    public long A00;
    public long A01;
    public long A02;
    public final String A03;
    public final String A04;
    public final String A05;
    public final String A06;
    public final String A07;

    public final boolean equals(Object obj) {
        if (!(obj instanceof C0237Lv)) {
            return false;
        }
        C0237Lv lv = (C0237Lv) obj;
        if (!this.A04.equals(lv.A04) || !this.A07.equals(lv.A07) || !this.A05.equals(lv.A05) || !this.A03.equals(lv.A03) || !this.A06.equals(lv.A06) || !OacrConstants.AUTO_SPEECH_DOMAIN.equals(OacrConstants.AUTO_SPEECH_DOMAIN) || this.A02 != lv.A02 || this.A01 != lv.A01 || this.A00 != lv.A00) {
            return false;
        }
        return true;
    }

    public final int hashCode() {
        return this.A04.hashCode() + this.A07.hashCode() + this.A05.hashCode() + this.A03.hashCode() + this.A06.hashCode() + OacrConstants.AUTO_SPEECH_DOMAIN.hashCode() + Long.valueOf(this.A02).hashCode() + Long.valueOf(this.A01).hashCode() + Long.valueOf(this.A00).hashCode();
    }

    public C0237Lv(String str, String str2, String str3, String str4, String str5, long j, long j2, long j3) {
        this.A04 = str;
        this.A07 = str2;
        this.A05 = str3;
        this.A03 = str4;
        this.A06 = str5;
        this.A02 = j;
        this.A01 = j2;
        this.A00 = j3;
    }
}
