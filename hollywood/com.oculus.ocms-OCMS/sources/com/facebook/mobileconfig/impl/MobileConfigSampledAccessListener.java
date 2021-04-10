package com.facebook.mobileconfig.impl;

import com.facebook.infer.annotation.Nullsafe;
import com.facebook.infer.annotation.ThreadSafe;
import javax.annotation.Nullable;

@Nullsafe(Nullsafe.Mode.LOCAL)
public interface MobileConfigSampledAccessListener {
    @ThreadSafe
    void onMobileConfigAccess(long j, int i, boolean z, @Nullable String str, @Nullable String str2, String str3, boolean z2);

    @ThreadSafe
    void setMarker(Marker marker);

    public enum Marker {
        LOGIN_CREATED_MANAGER_AND_TO_SCHEDULE_SYNC_FETCH(1),
        SYNC_FETCH_FAILED(2),
        SYNC_FETCH_OK(3),
        APP_UPGRADE_ASYNC_FETCH_SCHEDULE_OK(4),
        APP_UPGRADE_ASYNC_FETCH_SCHEDULE_FAILED(5),
        PERSISTENT_COMPONENT_SERIAL_STARTED(6),
        PERSISTENT_COMPONENT_PARALLEL_STARTED(7);
        
        private final int value;

        private Marker(int i) {
            this.value = i;
        }

        public int getValue() {
            return this.value;
        }
    }
}
