package X;

import android.text.TextUtils;
import java.util.ArrayList;

/* renamed from: X.jr  reason: case insensitive filesystem */
public final class C0847jr extends DQ {
    public int A00;
    public final ArrayList A01;

    public static void A00(C0847jr jrVar, String str) {
        if (!jrVar.A03) {
            throw new IllegalStateException("Expected object to be mutable");
        } else if (TextUtils.isEmpty(str)) {
            throw new IllegalArgumentException(AnonymousClass08.A04("key=", str));
        }
    }

    public final C0846jq A04(String str) {
        DR dr = super.A01;
        C0846jq jqVar = (C0846jq) dr.A01.A18();
        if (jqVar == null) {
            jqVar = new C0846jq(dr.A00);
        }
        jqVar.A03(dr);
        A00(this, str);
        jqVar.A02();
        A01(this, str, jqVar);
        jqVar.A02();
        ((DQ) jqVar).A00 = this;
        return jqVar;
    }

    public final C0847jr A05(String str) {
        C0847jr A002 = super.A01.A00();
        A00(this, str);
        A002.A02();
        A01(this, str, A002);
        A002.A02();
        ((DQ) A002).A00 = this;
        return A002;
    }

    public final Object A06(int i) {
        if (i >= 0 && i < this.A00) {
            return this.A01.get((i << 1) + 1);
        }
        throw new ArrayIndexOutOfBoundsException(i);
    }

    public C0847jr(int i) {
        this.A01 = new ArrayList(i << 1);
    }

    public static void A01(C0847jr jrVar, String str, Object obj) {
        A00(jrVar, str);
        ArrayList arrayList = jrVar.A01;
        arrayList.add(str);
        arrayList.add(obj);
        jrVar.A00++;
    }
}
