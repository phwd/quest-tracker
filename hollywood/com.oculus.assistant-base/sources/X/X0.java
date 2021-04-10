package X;

import com.oculus.assistant.R;

public final class X0 {
    public C00859p A00;
    public C1307x1 A01;
    public Boolean A02;
    public Integer A03;
    public Integer A04;
    public String A05;
    public String A06;
    public String A07;
    public boolean A08;

    public X0() {
        this(null, null, null, null, 15, null);
    }

    public final boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (!(obj instanceof X0)) {
            return false;
        }
        X0 x0 = (X0) obj;
        return C0514bB.A05(this.A05, x0.A05) && C0514bB.A05(this.A06, x0.A06) && C0514bB.A05(this.A03, x0.A03) && C0514bB.A05(this.A07, x0.A07);
    }

    public final int hashCode() {
        String str = this.A05;
        int i = 0;
        int hashCode = (str != null ? str.hashCode() : 0) * 31;
        String str2 = this.A06;
        int hashCode2 = (hashCode + (str2 != null ? str2.hashCode() : 0)) * 31;
        Integer num = this.A03;
        int hashCode3 = (hashCode2 + (num != null ? num.hashCode() : 0)) * 31;
        String str3 = this.A07;
        if (str3 != null) {
            i = str3.hashCode();
        }
        return hashCode3 + i;
    }

    public final String toString() {
        StringBuilder sb = new StringBuilder("AttentionSystemBuilder(message=");
        sb.append(this.A05);
        sb.append(", state=");
        sb.append(this.A06);
        sb.append(", duration=");
        sb.append(this.A03);
        sb.append(", subMessage=");
        sb.append(this.A07);
        sb.append(")");
        return sb.toString();
    }

    public final void A00(int i) {
        C1307x1 x1Var = new C1307x1();
        x1Var.A01 = i;
        this.A01 = x1Var;
    }

    public final void A03(String str) {
        int i;
        this.A06 = str;
        if (str != null) {
            int hashCode = str.hashCode();
            if (hashCode != -1420487566) {
                if (hashCode != 2242516) {
                    if (hashCode == 1567879323 && str.equals("LISTENING")) {
                        i = R.drawable.ic_attn_listening;
                    } else {
                        return;
                    }
                } else if (str.equals("IDLE")) {
                    i = R.drawable.ic_attn_idle;
                } else {
                    return;
                }
            } else if (str.equals("TRANSCRIPTION")) {
                i = R.drawable.ic_attn_transcribing;
            } else {
                return;
            }
            A00(i);
        }
    }

    public final void A01(int i) {
        this.A05 = BX.A00().getString(i);
    }

    public final void A02(int i) {
        this.A07 = BX.A00().getString(i);
    }

    public /* synthetic */ X0(String str, String str2, Integer num, String str3, int i, b9 b9Var) {
        this.A05 = null;
        this.A06 = null;
        this.A03 = null;
        this.A07 = null;
        this.A08 = true;
    }
}
