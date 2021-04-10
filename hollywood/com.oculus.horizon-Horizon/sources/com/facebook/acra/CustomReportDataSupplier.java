package com.facebook.acra;

import javax.annotation.Nullable;

public interface CustomReportDataSupplier {
    @Nullable
    String getCustomData(Throwable th);
}
