package com.facebook.common.errorreporting;

import androidx.annotation.VisibleForTesting;
import com.facebook.acra.info.ExternalProcessInfo;
import com.facebook.common.errorreporting.ErrorReport;
import javax.annotation.Nullable;

public abstract class AbstractFbErrorReporter implements FbErrorReporter {
    @Override // com.facebook.common.errorreporting.FbErrorReporter
    public void report(ErrorReport.Module module, ErrorReport.Impact impact, String category, String message) {
        report(module, impact, category, message, null);
    }

    @Override // com.facebook.common.errorreporting.FbErrorReporter
    public void report(ErrorReport.Module module, ErrorReport.Impact impact, String category, String message, @Nullable Throwable cause) {
        int samplingRate;
        if (impact == ErrorReport.Impact.LEGACY_FAIL_HARDER) {
            samplingRate = 1;
        } else {
            samplingRate = 1000;
        }
        softReport(buildCategory(module, impact, category), message, cause, samplingRate);
    }

    @VisibleForTesting
    public static String buildCategory(ErrorReport.Module module, ErrorReport.Impact impact, String category) {
        return "PRODUCT_" + module.name() + "::" + "IMPACT_" + impact.name() + "::" + category;
    }

    @Override // com.facebook.common.errorreporting.FbErrorReporter
    public void softReport(String category, String message) {
        softReport(SoftError.newError(category, message));
    }

    @Override // com.facebook.common.errorreporting.FbErrorReporter
    public void softReport(String category, String message, @Nullable Throwable cause) {
        softReport(SoftError.newBuilder(category, message).setCause(cause).build());
    }

    @Override // com.facebook.common.errorreporting.FbErrorReporter
    public void softReport(String category, Throwable cause) {
        softReport(category, cause.getMessage(), cause);
    }

    @Override // com.facebook.common.errorreporting.FbErrorReporter
    public void softReport(String category, String message, int samplingFrequency) {
        softReport(SoftError.newError(category, message, samplingFrequency));
    }

    @Override // com.facebook.common.errorreporting.FbErrorReporter
    public void softReport(SoftError softError, @Nullable ExternalProcessInfo external) {
        softReport(softError);
    }

    @Override // com.facebook.common.errorreporting.FbErrorReporter
    public void softReport(String category, String message, @Nullable Throwable cause, int samplingFrequency) {
        softReport(SoftError.newBuilder(category, message).setCause(cause).setSamplingFrequency(samplingFrequency).build());
    }

    @Override // com.facebook.common.errorreporting.FbErrorReporter
    public void softReport(String category, Throwable cause, int samplingFrequency) {
        softReport(category, cause.getMessage(), cause, samplingFrequency);
    }

    @Override // com.facebook.common.errorreporting.FbErrorReporter
    public void softReportFailHarder(String category, String message) {
        report(ErrorReport.Module.LEGACY, ErrorReport.Impact.LEGACY_FAIL_HARDER, category, message);
    }

    @Override // com.facebook.common.errorreporting.FbErrorReporter
    public void softReportFailHarder(String category, String message, Throwable cause) {
        report(ErrorReport.Module.LEGACY, ErrorReport.Impact.LEGACY_FAIL_HARDER, category, message, cause);
    }

    @Override // com.facebook.common.errorreporting.FbErrorReporter
    public void softReportFailHarder(String category, Throwable cause) {
        report(ErrorReport.Module.LEGACY, ErrorReport.Impact.LEGACY_FAIL_HARDER, category, cause.getMessage() != null ? cause.getMessage() : "", cause);
    }
}
