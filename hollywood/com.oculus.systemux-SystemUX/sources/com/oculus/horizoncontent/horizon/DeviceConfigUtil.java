package com.oculus.horizoncontent.horizon;

import android.content.Context;
import com.oculus.common.deviceconfig.DeviceConfigHelper;
import com.oculus.horizoncontent.horizon.DeviceConfigUtil;

public class DeviceConfigUtil {

    public static abstract class GetDeviceBooleanCallback {
        public abstract void onValue(boolean z);
    }

    public static void getDeviceBooleanAsync(Context context, String str, GetDeviceBooleanCallback getDeviceBooleanCallback) {
        DeviceConfigHelper.runCallbackOnceSubscribeCompleted(new DeviceConfigHelper.DeviceConfigHelperSubscribeCompletedCallback(context, str, getDeviceBooleanCallback) {
            /* class com.oculus.horizoncontent.horizon.$$Lambda$DeviceConfigUtil$RawkrFGVQzORHkpfTINeAQQO92Q */
            private final /* synthetic */ Context f$0;
            private final /* synthetic */ String f$1;
            private final /* synthetic */ DeviceConfigUtil.GetDeviceBooleanCallback f$2;

            {
                this.f$0 = r1;
                this.f$1 = r2;
                this.f$2 = r3;
            }

            @Override // com.oculus.common.deviceconfig.DeviceConfigHelper.DeviceConfigHelperSubscribeCompletedCallback
            public final void call() {
                DeviceConfigUtil.lambda$getDeviceBooleanAsync$2(this.f$0, this.f$1, this.f$2);
            }
        });
    }
}
