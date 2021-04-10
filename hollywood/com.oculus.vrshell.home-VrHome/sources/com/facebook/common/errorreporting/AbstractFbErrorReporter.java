package com.facebook.common.errorreporting;

import com.facebook.acra.info.ExternalProcessInfo;
import javax.annotation.Nullable;

public abstract class AbstractFbErrorReporter implements FbErrorReporter {
    public void softReport(SoftError softError, @Nullable ExternalProcessInfo external) {
        softReport(softError);
    }
}
