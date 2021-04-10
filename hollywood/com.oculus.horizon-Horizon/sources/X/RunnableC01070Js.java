package X;

import com.facebook.acra.CrashReportData;
import com.facebook.acra.constants.ErrorReportingConstants;

/* renamed from: X.0Js  reason: invalid class name and case insensitive filesystem */
public class RunnableC01070Js implements Runnable {
    public static final String __redex_internal_original_name = "com.facebook.common.errorreporting.FbErrorReporterImpl$1";
    public final /* synthetic */ AnonymousClass0JP A00;
    public final /* synthetic */ Integer A01;
    public final /* synthetic */ String A02;
    public final /* synthetic */ String A03;
    public final /* synthetic */ Throwable A04;

    public RunnableC01070Js(AnonymousClass0JP r1, String str, String str2, Integer num, Throwable th) {
        this.A00 = r1;
        this.A03 = str;
        this.A02 = str2;
        this.A01 = num;
        this.A04 = th;
    }

    public final void run() {
        try {
            CrashReportData crashReportData = new CrashReportData();
            crashReportData.put(ErrorReportingConstants.SOFT_ERROR_CATEGORY, this.A03);
            crashReportData.put(ErrorReportingConstants.SOFT_ERROR_MESSAGE, this.A02);
            crashReportData.put(ErrorReportingConstants.SOFT_ERROR_OCCURRENCE_COUNT, Integer.toString(this.A01.intValue()));
            this.A00.A05.get().handleException(this.A04, crashReportData);
        } catch (Throwable unused) {
        }
    }
}
