package com.facebook.mobileconfig.ota;

import javax.annotation.Nullable;

public interface MobileConfigOTAUtil {
    @Nullable
    String getAddedParamsPath();

    @Nullable
    String getMergedParamsMapPath();

    @Nullable
    String getOneQueryHashPath();

    @Nullable
    String getRNParamsPath();
}
