package com.facebook.common.errorreporting;

import com.facebook.acra.info.ExternalProcessInfo;
import com.facebook.common.errorreporting.ErrorReport;
import java.net.Proxy;
import javax.annotation.Nullable;
import javax.annotation.concurrent.ThreadSafe;

@ThreadSafe
public interface FbErrorReporter {
    void putCurrentUserId(String str);

    void putCustomData(String str, String str2);

    void putLazyCustomData(String str, FbCustomReportDataSupplier fbCustomReportDataSupplier);

    void removeCustomData(String str);

    void removeLazyCustomData(String str);

    void report(ErrorReport.Module module, ErrorReport.Impact impact, String str, String str2);

    void report(ErrorReport.Module module, ErrorReport.Impact impact, String str, String str2, Throwable th);

    void reportANRs();

    void reportJavascriptException(Throwable th);

    void reportLastActivity(String str);

    void reportRNException(String str, String str2);

    void reportRNException(String str, Throwable th);

    void reportStall(long j);

    void softReport(SoftError softError);

    void softReport(SoftError softError, @Nullable ExternalProcessInfo externalProcessInfo);

    void softReport(String str, String str2);

    void softReport(String str, String str2, int i);

    void softReport(String str, String str2, @Nullable Throwable th);

    void softReport(String str, String str2, Throwable th, int i);

    void softReport(String str, Throwable th);

    void softReport(String str, Throwable th, int i);

    void softReportDelayed(SoftError softError);

    @Deprecated
    void softReportFailHarder(String str, String str2);

    @Deprecated
    void softReportFailHarder(String str, String str2, Throwable th);

    @Deprecated
    void softReportFailHarder(String str, Throwable th);

    void strictModeReport(String str, String str2, String str3);

    void updateCrashReportProxy(Proxy proxy);
}
