package X;

import com.facebook.common.perftest.base.PerfTestConfigBase;
import com.facebook.common.util.TriState;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Locale;

/* renamed from: X.Ir  reason: case insensitive filesystem */
public final class RunnableC0184Ir implements Runnable {
    public static final String __redex_internal_original_name = "com.facebook.quicklog.QuickPerformanceLoggerImpl$3";
    public final /* synthetic */ RunnableC0929p4 A00;
    public final /* synthetic */ YE A01;

    public RunnableC0184Ir(YE ye, RunnableC0929p4 p4Var) {
        this.A01 = ye;
        this.A00 = p4Var;
    }

    public final void run() {
        String str;
        String format;
        Object obj;
        Object obj2;
        YE ye = this.A01;
        RunnableC0929p4 p4Var = this.A00;
        IQ[] iqArr = ye.mEventDecorators;
        C0915oq oqVar = null;
        if (iqArr == null || !p4Var.A3A() || 0 >= iqArr.length) {
            IP[] ipArr = ye.mDataProviders;
            if (ipArr != null && p4Var.A3A()) {
                C0916or orVar = ye.A03;
                if (orVar != null && (orVar instanceof C0649dt)) {
                    oqVar = ((C0649dt) orVar).A00;
                }
                for (IP ip : ipArr) {
                    if ((ip.A2n() & p4Var.A08) != 0 && (oqVar == null || ip.A3S(oqVar))) {
                        long A2n = ip.A2n();
                        Ix ix = p4Var.A0E;
                        if (ix == null) {
                            obj = null;
                        } else {
                            obj = ix.get(Long.numberOfTrailingZeros(A2n));
                        }
                        Ix ix2 = p4Var.A0F;
                        if (ix2 == null) {
                            obj2 = null;
                        } else {
                            obj2 = ix2.get(Long.numberOfTrailingZeros(A2n));
                        }
                        Ic A2f = p4Var.A2f();
                        String A2m = ip.A2m();
                        ArrayList arrayList = A2f.A01;
                        int size = arrayList.size() - 1;
                        if (size >= 0 && arrayList.get(size) != null) {
                            arrayList.remove(size);
                        }
                        A2f.A01.add(A2m);
                        ip.A1d(p4Var, ip.A2w().cast(obj), ip.A2t().cast(obj2));
                    }
                }
            }
            if (ye.A0L.A01 != null) {
                p4Var.getMarkerId();
            }
            if (p4Var.A0H && !PerfTestConfigBase.A00) {
                p4Var.A0C = (IT) ye.A08.get();
                if (YE.A0J(ye)) {
                    if (ye.A0J == TriState.UNSET) {
                        ye.A0J = TriState.NO;
                    }
                    if (ye.A0J.asBoolean(false)) {
                        StringBuilder sb = new StringBuilder("QPLSent - ");
                        sb.append("{\"id\":");
                        sb.append(p4Var.getMarkerId());
                        sb.append(",");
                        sb.append("\"event\":\"");
                        C0925p0 p0Var = ye.A05;
                        sb.append(p0Var.A01(p4Var.getMarkerId()));
                        sb.append("\",");
                        sb.append("\"action\":\"");
                        sb.append(p0Var.A00(p4Var.A2G()));
                        sb.append("\",");
                        sb.append("\"timestamp\":");
                        sb.append(p4Var.A32());
                        sb.append(",");
                        sb.append("\"duration\":");
                        sb.append(p4Var.A2N());
                        sb.append(",");
                        YE.A0G("tags", sb, p4Var.A30());
                        sb.append(",");
                        YE.A0G("extra", sb, p4Var.A2R());
                        if (!p4Var.A2f().A00.isEmpty()) {
                            sb.append(",");
                            Ic A2f2 = p4Var.A2f();
                            HashMap hashMap = new HashMap();
                            A2f2.A01(new C0922ox(A2f2, hashMap));
                            YE.A0H("metadata", sb, hashMap);
                        }
                        IW A2k = p4Var.A2k();
                        if (A2k != null) {
                            sb.append(",");
                            sb.append("\"points\":[");
                            A2k.A00(new C0934p9(ye, sb));
                            sb.append("]");
                        }
                        sb.append("}");
                        format = sb.toString();
                    } else {
                        StringBuilder sb2 = new StringBuilder();
                        IW A2k2 = p4Var.A2k();
                        if (A2k2 != null) {
                            A2k2.A00(new C0932p7(ye, sb2));
                            sb2.append(' ');
                        }
                        if (!p4Var.A2R().isEmpty()) {
                            String str2 = null;
                            int i = 0;
                            for (String str3 : p4Var.A2R()) {
                                i++;
                                if (i % 2 == 0) {
                                    sb2.append(", ");
                                    sb2.append(str2);
                                    sb2.append("=");
                                    sb2.append(str3);
                                } else {
                                    str2 = str3;
                                }
                            }
                        }
                        if (!p4Var.A30().isEmpty()) {
                            sb2.append(" ");
                            sb2.append(p4Var.A31());
                        }
                        if (p4Var.A2f() != null) {
                            sb2.append(" metadata=");
                            Ic A2f3 = p4Var.A2f();
                            HashMap hashMap2 = new HashMap();
                            A2f3.A01(new C0922ox(A2f3, hashMap2));
                            sb2.append(hashMap2);
                        }
                        Locale locale = Locale.US;
                        Object[] objArr = new Object[7];
                        objArr[0] = "QPLSent - ";
                        C0925p0 p0Var2 = ye.A05;
                        objArr[1] = p0Var2.A01(p4Var.A02);
                        objArr[2] = p0Var2.A00(p4Var.A0G);
                        objArr[3] = Integer.valueOf(p4Var.A2N());
                        boolean A3Z = p4Var.A3Z();
                        boolean A3O = p4Var.A3O();
                        if (A3Z) {
                            str = "missing_config";
                        } else if (A3O) {
                            str = "always_on";
                        } else {
                            str = "random_sampling";
                        }
                        objArr[4] = str;
                        objArr[5] = Integer.valueOf(p4Var.A2q());
                        objArr[6] = sb2.toString();
                        format = String.format(locale, "%s %s %s %d[ms] %s (1:%d) %s", objArr);
                    }
                    YE.A0E(ye, 4, format);
                }
                p4Var.run();
                ye.A00 = p4Var;
                return;
            }
            return;
        }
        throw new NullPointerException("metadataCategory");
    }
}
