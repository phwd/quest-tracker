package com.facebook.common.errorreporting;

import javax.annotation.Nullable;

public interface FbCustomReportDataSupplier {
    String getCustomData(@Nullable Throwable th);

    String getFieldName();
}
