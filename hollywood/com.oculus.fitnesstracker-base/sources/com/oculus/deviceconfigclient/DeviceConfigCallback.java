package com.oculus.deviceconfigclient;

import com.facebook.infer.annotation.Nullsafe;

@Nullsafe(Nullsafe.Mode.LOCAL)
public class DeviceConfigCallback {
    /* access modifiers changed from: protected */
    public boolean enableAutoPrefetch() {
        return true;
    }

    /* access modifiers changed from: protected */
    public void onFailure(String str) {
    }

    /* access modifiers changed from: protected */
    public void onPrefetched(String[] strArr) {
    }

    /* access modifiers changed from: protected */
    public void onSuccess() {
    }

    /* JADX INFO: Failed to restore enum class, 'enum' modifier removed */
    static final class ParamChangedResult extends Enum<ParamChangedResult> {
        private static final /* synthetic */ int[] $VALUES$67f9076b = {UpdateNow$4778ad50, UpdateAtNextStart$4778ad50};
        public static final int UpdateAtNextStart$4778ad50 = 2;
        public static final int UpdateNow$4778ad50 = 1;

        public static int[] values$656a1b6a() {
            return (int[]) $VALUES$67f9076b.clone();
        }
    }
}
