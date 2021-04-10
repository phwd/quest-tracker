package com.oculus.auth.device;

import android.content.Context;

public interface DeviceAuthTokenSubscriber {
    void onTokenRefresh(Context context, String str);
}
