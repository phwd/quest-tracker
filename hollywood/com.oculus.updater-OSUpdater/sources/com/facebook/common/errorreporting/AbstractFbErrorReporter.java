package com.facebook.common.errorreporting;

import com.facebook.acra.info.ExternalProcessInfo;
import javax.annotation.Nullable;

public abstract class AbstractFbErrorReporter implements FbErrorReporter {
    @Override // com.facebook.common.errorreporting.FbErrorReporter
    public void softReport(String str, String str2) {
        softReport(SoftError.newError(str, str2));
    }

    @Override // com.facebook.common.errorreporting.FbErrorReporter
    public void softReport(String str, String str2, int i) {
        softReport(SoftError.newError(str, str2, i));
    }

    public void softReport(SoftError softError, @Nullable ExternalProcessInfo externalProcessInfo) {
        softReport(softError);
    }
}
