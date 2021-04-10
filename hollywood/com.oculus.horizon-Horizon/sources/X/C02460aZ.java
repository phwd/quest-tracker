package X;

import com.facebook.infer.annotation.Nullsafe;

@Nullsafe(Nullsafe.Mode.LOCAL)
/* renamed from: X.0aZ  reason: invalid class name and case insensitive filesystem */
public final class C02460aZ {
    public String A00 = "";
    public String A01 = "";
    public String A02 = "";
    public String A03 = "";
    public String A04 = "";
    public String A05 = "";
    public String A06 = "";

    public final String toString() {
        StringBuilder sb = new StringBuilder("FbnsNotificationMessage{mToken'=");
        sb.append(this.A06);
        sb.append('\'');
        sb.append(", mConnectionKey='");
        sb.append(this.A01);
        sb.append('\'');
        sb.append(", mPackageName='");
        sb.append(this.A04);
        sb.append('\'');
        sb.append(", mCollapseKey='");
        sb.append(this.A00);
        sb.append('\'');
        sb.append(", mPayload='");
        sb.append(this.A05);
        sb.append('\'');
        sb.append(", mNotifId='");
        sb.append(this.A03);
        sb.append('\'');
        sb.append(", mIsBuffered='");
        sb.append(this.A02);
        sb.append('\'');
        sb.append('}');
        return sb.toString();
    }
}
