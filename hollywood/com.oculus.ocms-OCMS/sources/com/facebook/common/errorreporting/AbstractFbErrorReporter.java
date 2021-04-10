package com.facebook.common.errorreporting;

import androidx.annotation.VisibleForTesting;
import com.facebook.acra.info.ExternalProcessInfo;
import com.facebook.common.errorreporting.ErrorReport;
import javax.annotation.Nullable;

public abstract class AbstractFbErrorReporter implements FbErrorReporter {
    @Override // com.facebook.common.errorreporting.FbErrorReporter
    public void report(ErrorReport.Impact impact, String str, String str2) {
        report(impact, str, str2, null);
    }

    @Override // com.facebook.common.errorreporting.FbErrorReporter
    public void report(ErrorReport.Impact impact, String str, String str2, @Nullable Throwable th) {
        softReport(buildCategory(impact, str), str2, th, impact == ErrorReport.Impact.LEGACY_FAIL_HARDER ? 1 : 1000);
    }

    @VisibleForTesting
    public static String buildCategory(ErrorReport.Impact impact, String str) {
        return "IMPACT_" + impact.name() + "::" + str;
    }

    @Override // com.facebook.common.errorreporting.FbErrorReporter
    public void softReport(String str, String str2) {
        softReport(SoftError.newError(str, str2));
    }

    @Override // com.facebook.common.errorreporting.FbErrorReporter
    public void softReport(String str, String str2, @Nullable Throwable th) {
        softReport(SoftError.newBuilder(str, str2).setCause(th).build());
    }

    @Override // com.facebook.common.errorreporting.FbErrorReporter
    public void softReport(String str, Throwable th) {
        softReport(str, th.getMessage(), th);
    }

    @Override // com.facebook.common.errorreporting.FbErrorReporter
    public void softReport(String str, String str2, int i) {
        softReport(SoftError.newError(str, str2, i));
    }

    @Override // com.facebook.common.errorreporting.FbErrorReporter
    public void softReport(SoftError softError, @Nullable ExternalProcessInfo externalProcessInfo) {
        softReport(softError);
    }

    @Override // com.facebook.common.errorreporting.FbErrorReporter
    public void softReport(String str, String str2, @Nullable Throwable th, int i) {
        softReport(SoftError.newBuilder(str, str2).setCause(th).setSamplingFrequency(i).build());
    }

    @Override // com.facebook.common.errorreporting.FbErrorReporter
    public void softReport(String str, Throwable th, int i) {
        softReport(str, th.getMessage(), th, i);
    }

    @Override // com.facebook.common.errorreporting.FbErrorReporter
    public void softReportFailHarder(String str, String str2) {
        report(ErrorReport.Impact.LEGACY_FAIL_HARDER, str, str2);
    }

    @Override // com.facebook.common.errorreporting.FbErrorReporter
    public void softReportFailHarder(String str, String str2, Throwable th) {
        report(ErrorReport.Impact.LEGACY_FAIL_HARDER, str, str2, th);
    }

    @Override // com.facebook.common.errorreporting.FbErrorReporter
    public void softReportFailHarder(String str, Throwable th) {
        report(ErrorReport.Impact.LEGACY_FAIL_HARDER, str, th.getMessage() != null ? th.getMessage() : "", th);
    }
}
