package X;

import com.facebook.common.util.TriState;
import com.oculus.debug.DebugMode;
import com.oculus.horizon.service.AssetBundleUtil;
import com.oculus.perflogs.impl.fbquicklog.PerfLogsHelper;
import java.util.ArrayList;
import java.util.Locale;

/* renamed from: X.1tz  reason: invalid class name and case insensitive filesystem */
public class RunnableC10591tz implements Runnable {
    public static final String __redex_internal_original_name = "com.facebook.quicklog.QuickPerformanceLoggerImpl$3";
    public final /* synthetic */ RunnableC10551tn A00;
    public final /* synthetic */ AnonymousClass1tm A01;

    public RunnableC10591tz(AnonymousClass1tm r1, RunnableC10551tn r2) {
        this.A01 = r1;
        this.A00 = r2;
    }

    public final void run() {
        AbstractC10621u9[] r4;
        String str;
        String format;
        AnonymousClass1tm r6 = this.A01;
        RunnableC10551tn r5 = this.A00;
        AbstractC10701uq[] r42 = r6.mEventDecorators;
        if ((r42 == null || r5.A08 == 0 || 0 >= r42.length) && ((r4 = r6.mDataProviders) == null || r5.A08 == 0 || 0 >= r4.length)) {
            if (r6.A0K.A01 != null) {
                r5.getMarkerId();
            }
            if (r5.A4u()) {
                TriState valueOf = TriState.valueOf((Boolean) null);
                if (valueOf == TriState.YES) {
                    r5 = null;
                    r6.A00 = r5;
                    return;
                } else if (valueOf == TriState.UNSET) {
                    r6.A00 = null;
                    return;
                }
            }
            if (r5.A0F && !(!((DebugMode) AnonymousClass0J2.A03(1, 272, PerfLogsHelper.this._UL_mInjectionContext)).A02())) {
                r5.A0C = r6.A0A.get();
                if (AnonymousClass1tm.A0J(r6)) {
                    if (r6.A0I == TriState.UNSET) {
                        r6.A0I = TriState.NO;
                    }
                    if (r6.A0I.asBoolean(false)) {
                        StringBuilder sb = new StringBuilder("QPLSent - ");
                        sb.append("{\"id\":");
                        sb.append(r5.getMarkerId());
                        sb.append(",");
                        sb.append("\"event\":\"");
                        AnonymousClass0TF r7 = r6.A06;
                        sb.append(r7.A3o(r5.getMarkerId()));
                        sb.append("\",");
                        sb.append("\"action\":\"");
                        sb.append(r7.A2w(r5.A2v()));
                        sb.append("\",");
                        sb.append("\"timestamp\":");
                        sb.append(r5.A4Y());
                        sb.append(",");
                        sb.append("\"duration\":");
                        sb.append(r5.A3L());
                        sb.append(",");
                        AnonymousClass1tm.A0G("tags", sb, r5.A4X());
                        sb.append(",");
                        AnonymousClass1tm.A0G("extra", sb, r5.A3P());
                        if (!r5.A3r().A00.isEmpty()) {
                            sb.append(",");
                            AnonymousClass1tm.A0H(AssetBundleUtil.EXTRA_ASSET_METADATA, sb, r5.A3r().A00());
                        }
                        AnonymousClass0T0 A4A = r5.A4A();
                        if (A4A != null) {
                            sb.append(",");
                            sb.append("\"points\":[");
                            A4A.A00(new AnonymousClass1tv(r6, sb));
                            sb.append("]");
                        }
                        sb.append("}");
                        format = sb.toString();
                    } else {
                        StringBuilder sb2 = new StringBuilder();
                        AnonymousClass0T0 A4A2 = r5.A4A();
                        if (A4A2 != null) {
                            A4A2.A00(new C10581tw(r6, sb2));
                            sb2.append(' ');
                        }
                        if (!r5.A3P().isEmpty()) {
                            String str2 = null;
                            int i = 0;
                            for (String str3 : r5.A3P()) {
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
                        if (!r5.A4X().isEmpty()) {
                            sb2.append(" ");
                            StringBuilder sb3 = new StringBuilder();
                            ArrayList<String> arrayList = r5.A0K;
                            int size = arrayList.size();
                            for (String str4 : arrayList) {
                                sb3.append(str4);
                                if (size > 1) {
                                    sb3.append(",");
                                }
                                size--;
                            }
                            sb2.append(sb3.toString());
                        }
                        if (r5.A3r() != null) {
                            sb2.append(" metadata=");
                            sb2.append(r5.A3r().A00());
                        }
                        Locale locale = Locale.US;
                        Object[] objArr = new Object[7];
                        objArr[0] = "QPLSent - ";
                        AnonymousClass0TF r1 = r6.A06;
                        objArr[1] = r1.A3o(r5.A02);
                        objArr[2] = r1.A2w(r5.A0E);
                        objArr[3] = Integer.valueOf(r5.A3L());
                        if (r5.A0G) {
                            str = "always_on";
                        } else {
                            str = "random_sampling";
                        }
                        objArr[4] = str;
                        objArr[5] = Integer.valueOf(r5.A05);
                        objArr[6] = sb2.toString();
                        format = String.format(locale, "%s %s %s %d[ms] %s (1:%d) %s", objArr);
                    }
                    AnonymousClass1tm.A0E(r6, 4, format);
                }
                r5.run();
                r6.A00 = r5;
                return;
            }
            return;
        }
        throw null;
    }
}
