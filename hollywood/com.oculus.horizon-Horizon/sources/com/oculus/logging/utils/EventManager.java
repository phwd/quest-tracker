package com.oculus.logging.utils;

import javax.annotation.Nullable;

public interface EventManager {
    Event A22(String str);

    Event A23(String str, @Nullable String str2, boolean z);

    FunnelData A24();

    void A2Q(String str);

    void A84(String str, int i);

    void A8F(String str, String str2, @Nullable String str3, FunnelData funnelData);

    void A9H(String str);
}
