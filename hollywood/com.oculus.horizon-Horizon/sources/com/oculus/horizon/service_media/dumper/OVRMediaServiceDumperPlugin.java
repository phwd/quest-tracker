package com.oculus.horizon.service_media.dumper;

import X.AbstractC06640p5;
import X.AnonymousClass0dM;
import X.C003108z;
import android.content.Context;
import android.os.ResultReceiver;
import com.facebook.inject.UnsafeContextInjection;
import com.facebook.ultralight.Dependencies;
import com.facebook.ultralight.Eager;
import com.facebook.ultralight.Inject;
import java.io.PrintStream;
import java.util.concurrent.CountDownLatch;

@Dependencies({"_UL__ULSEP_android_content_Context_ULSEP_com_facebook_inject_UnsafeContextInjection_ULSEP_BINDING_ID"})
public class OVRMediaServiceDumperPlugin implements AnonymousClass0dM {
    public static final String CMD_VRCAST_DISCOVER = "vrcast_discover";
    public static final String CMD_VRCAST_START = "vrcast_start";
    public static final String CMD_VRCAST_STATUS = "vrcast_status";
    public static final String CMD_VRCAST_STOP = "vrcast_stop";
    public static final String HORIZON_COMPONENT_NAME = "com.oculus.horizon.service_media.OVRMediaService";
    public static final String HORIZON_PACKAGE_NAME = "com.oculus.horizon";
    public static final String KEY_MESSAGE_TYPE = "message_type";
    public static final String NAME = "ovrmediaservice";
    @UnsafeContextInjection
    @Inject
    @Eager
    public final Context mContext;

    /* renamed from: com.oculus.horizon.service_media.dumper.OVRMediaServiceDumperPlugin$1  reason: invalid class name */
    public class AnonymousClass1 extends ResultReceiver {
        public final /* synthetic */ OVRMediaServiceDumperPlugin this$0;
        public final /* synthetic */ CountDownLatch val$latch;
        public final /* synthetic */ PrintStream val$out;
    }

    /* renamed from: com.oculus.horizon.service_media.dumper.OVRMediaServiceDumperPlugin$2  reason: invalid class name */
    public class AnonymousClass2 extends ResultReceiver {
        public final /* synthetic */ OVRMediaServiceDumperPlugin this$0;
        public final /* synthetic */ CountDownLatch val$latch;
        public final /* synthetic */ PrintStream val$out;
    }

    @Inject
    public OVRMediaServiceDumperPlugin(AbstractC06640p5 r2) {
        this.mContext = C003108z.A02(r2);
    }
}
