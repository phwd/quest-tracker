package X;

import com.facebook.debug.tracer.Tracer;
import java.util.HashMap;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;

/* renamed from: X.8J  reason: invalid class name */
public final class AnonymousClass8J {
    public C0723g5 A00;
    public String A01;
    public String A02 = null;
    public List A03;
    public boolean A04;
    public boolean A05 = false;
    public final AnonymousClass8c A06;
    public final Map A07 = new HashMap();
    public final Map A08 = new HashMap();

    /* JADX WARNING: Code restructure failed: missing block: B:13:0x0049, code lost:
        X.C0139Dd.A0F("AssistantClientAppRegistry", "Triggering on action for %s", ((com.facebook.hyperthrift.HyperThriftBase) r13.A00(0)).A00(4));
        r2 = new X.AnonymousClass8G();
        r3 = (com.facebook.hyperthrift.HyperThriftBase) r13.A00(0);
        r2.A05 = (java.lang.String) r3.A00(4);
        r2.A06 = (java.lang.String) r3.A00(0);
        r2.A04 = (java.lang.String) r3.A00(1);
        r2.A02 = (java.lang.String) r3.A00(2);
        r0 = (java.lang.Number) r3.A00(3);
     */
    /* JADX WARNING: Code restructure failed: missing block: B:14:0x0090, code lost:
        if (r0 == null) goto L_0x0129;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:15:0x0092, code lost:
        r0 = r0.doubleValue();
     */
    /* JADX WARNING: Code restructure failed: missing block: B:16:0x0096, code lost:
        r2.A00 = r0;
        r2.A08 = (byte[]) r3.A00(6);
        r2.A03 = (java.lang.String) r3.A00(9);
        r0 = (java.util.List) r3.A00(5);
     */
    /* JADX WARNING: Code restructure failed: missing block: B:17:0x00b2, code lost:
        if (r0 == null) goto L_0x012d;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:18:0x00b4, code lost:
        r11 = r0.iterator();
     */
    /* JADX WARNING: Code restructure failed: missing block: B:20:0x00bc, code lost:
        if (r11.hasNext() == false) goto L_0x012d;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:21:0x00be, code lost:
        r7 = (com.facebook.hyperthrift.HyperThriftBase) r11.next();
        r4 = new java.util.ArrayList();
        r10 = ((java.util.List) r7.A00(1)).iterator();
     */
    /* JADX WARNING: Code restructure failed: missing block: B:23:0x00dc, code lost:
        if (r10.hasNext() == false) goto L_0x0107;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:24:0x00de, code lost:
        r0 = (com.facebook.hyperthrift.HyperThriftBase) r10.next();
     */
    /* JADX WARNING: Code restructure failed: missing block: B:25:0x00e4, code lost:
        if (r0 == null) goto L_0x00fb;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:26:0x00e6, code lost:
        r4.add(new X.AnonymousClass8Y((java.lang.String) r0.A00(0), (java.lang.Double) r0.A00(1)));
     */
    /* JADX WARNING: Code restructure failed: missing block: B:27:0x00fb, code lost:
        X.C0139Dd.A0O("AssistantApplicationTriggerResponse", "Possible slot value is null for slot \"%s\" of \"response\" %s", r7, r3);
     */
    /* JADX WARNING: Code restructure failed: missing block: B:29:0x0111, code lost:
        if (android.text.TextUtils.isEmpty((java.lang.String) r7.A00(0)) != false) goto L_0x011d;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:30:0x0113, code lost:
        r2.A07.put(r7.A00(0), r4);
     */
    /* JADX WARNING: Code restructure failed: missing block: B:31:0x011d, code lost:
        X.C0139Dd.A0O("AssistantApplicationTriggerResponse", "Slot name is null for slot \"%s\" of \"response\" %s", r7, r3);
     */
    /* JADX WARNING: Code restructure failed: missing block: B:32:0x0129, code lost:
        r0 = 0.0d;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:33:0x012d, code lost:
        r2.A01 = r12;
        r4 = new X.AnonymousClass8H(r2);
        r2 = X.C00799i.A00;
        r2.logAppCommmandEvent(r4.A05, r4.A04);
        r0 = r6.A01;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:34:0x0140, code lost:
        if (r0 != null) goto L_0x0159;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:35:0x0142, code lost:
        r2.logError(X.AnonymousClass08.A04("Matched trigger did not have a callback:", (java.lang.String) ((com.facebook.hyperthrift.HyperThriftBase) r13.A00(0)).A00(4)));
     */
    /* JADX WARNING: Code restructure failed: missing block: B:36:0x0158, code lost:
        return false;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:37:0x0159, code lost:
        r0.A1I(r6, r4);
     */
    /* JADX WARNING: Code restructure failed: missing block: B:38:0x015d, code lost:
        return true;
     */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public static boolean A01(X.AnonymousClass8J r11, java.lang.String r12, com.facebook.messenger.assistant.thrift.ApplicationVoiceCommandResponseAction r13) {
        /*
        // Method dump skipped, instructions count: 353
        */
        throw new UnsupportedOperationException("Method not decompiled: X.AnonymousClass8J.A01(X.8J, java.lang.String, com.facebook.messenger.assistant.thrift.ApplicationVoiceCommandResponseAction):boolean");
    }

    public final synchronized void A02(String str, byte[] bArr) {
        Tracer.A01("provideCustomInputForTrigger");
        try {
            Map map = this.A08;
            if (map.containsKey(str)) {
                AnonymousClass8F r2 = (AnonymousClass8F) map.get(str);
                AnonymousClass8E r1 = new AnonymousClass8E();
                r1.A03 = r2.A03;
                r1.A04 = r2.A04;
                r1.A02 = r2.A02;
                r1.A01 = r2.A01;
                r1.A05 = r2.A05;
                r1.A00 = r2.A00;
                r1.A05 = bArr;
                map.put(str, r1.A00());
                A00(this);
            } else {
                throw new IllegalArgumentException(AnonymousClass08.A04("No command registered with key: ", str));
            }
        } finally {
            Tracer.A00();
        }
    }

    public final synchronized void A03(List list) {
        Iterator it = list.iterator();
        while (it.hasNext()) {
            AnonymousClass8F r2 = (AnonymousClass8F) it.next();
            this.A08.put(r2.A03, r2);
        }
        A00(this);
    }

    public final synchronized boolean A04() {
        if (!this.A05) {
            for (Map.Entry entry : this.A08.entrySet()) {
                AnonymousClass8F r0 = (AnonymousClass8F) entry.getValue();
                if (r0 == null || r0.A00 != AnonymousClass8D.BACKGROUND) {
                }
            }
            return false;
        }
        return true;
    }

    /* JADX DEBUG: Failed to insert an additional move for type inference into block B:73:0x005a */
    /* JADX WARN: Multi-variable type inference failed */
    /* JADX WARN: Type inference failed for: r6v0, types: [java.util.Map] */
    /* JADX WARN: Type inference failed for: r6v4, types: [java.util.Map] */
    /* JADX WARN: Type inference failed for: r6v5, types: [java.util.AbstractMap, java.util.HashMap] */
    /* JADX WARNING: Removed duplicated region for block: B:61:0x019c  */
    /* JADX WARNING: Unknown variable types count: 1 */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public static void A00(X.AnonymousClass8J r12) {
        /*
        // Method dump skipped, instructions count: 460
        */
        throw new UnsupportedOperationException("Method not decompiled: X.AnonymousClass8J.A00(X.8J):void");
    }

    public AnonymousClass8J(AnonymousClass8c r2, String str, String str2) {
        this.A06 = r2;
        if (str != null) {
            this.A01 = str;
            this.A03 = new LinkedList();
            this.A02 = str2;
            return;
        }
        throw null;
    }
}
