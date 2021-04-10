package X;

import com.facebook.assistant.oacr.OacrConstants;
import java.util.concurrent.TimeUnit;

/* renamed from: X.8l  reason: invalid class name and case insensitive filesystem */
public final class C00618l {
    public static final long A09 = TimeUnit.SECONDS.toMillis(3);
    public int A00;
    public long A01 = A09;
    public String A02;
    public String A03;
    public String A04;
    public String A05 = OacrConstants.AUTO_SPEECH_DOMAIN;
    public String A06;
    public boolean A07;
    public boolean A08;

    public final String toString() {
        StringBuilder sb = new StringBuilder("MultiPhrase: ");
        sb.append(this.A08);
        sb.append("\n");
        sb.append("AutoClose: ");
        sb.append(!this.A07);
        sb.append("\n");
        sb.append("AutoClose Timeout: ");
        sb.append(this.A01);
        sb.append("\n");
        sb.append("App Name: ");
        sb.append(this.A04);
        sb.append("\n");
        sb.append("Action Type: ");
        sb.append(this.A02);
        sb.append("\n");
        sb.append("Input Type: ");
        sb.append(this.A03);
        sb.append("\n");
        sb.append("Speech Domain: ");
        sb.append(this.A06);
        sb.append("\n");
        sb.append("Use Oacr: ");
        sb.append(false);
        sb.append("\n");
        sb.append("Is Voice Search: ");
        sb.append(false);
        sb.append("\n");
        sb.append("Service Name: ");
        sb.append(this.A05);
        sb.append("\n");
        return sb.toString();
    }
}
