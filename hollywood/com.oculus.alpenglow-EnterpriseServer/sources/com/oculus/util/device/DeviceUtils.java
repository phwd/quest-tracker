package com.oculus.util.device;

import X.AbstractC02990bJ;
import X.AnonymousClass006;
import X.AnonymousClass0J8;
import X.C01340Gg;
import android.annotation.TargetApi;
import android.content.Context;
import android.os.Environment;
import com.facebook.inject.UnsafeContextInjection;
import com.facebook.ultralight.Dependencies;
import com.facebook.ultralight.Eager;
import com.google.common.collect.ImmutableSet;
import javax.inject.Inject;

@Dependencies({"_UL__ULSEP_android_content_Context_ULSEP_com_facebook_inject_UnsafeContextInjection_ULSEP_BINDING_ID"})
@TargetApi(21)
public class DeviceUtils {
    public static final ImmutableSet<String> ALL_MODELS;
    public static final int DEFAULT_THRESHOLD_MAX_BYTES = 524288000;
    public static final int DEFAULT_THRESHOLD_PERCENTAGE = 10;
    public static final String EXTERNAL_STORAGE_FILE_PATH;
    public static final String FAKE_MODEL_PLACEHOLDER = "fake_placeholder";
    public static final ImmutableSet<String> JAPAN_MODELS;
    public static final String MODEL_DEBUG_FILE_PATH;
    public static final ImmutableSet<String> NOTE4_MODELS;
    public static final String OCULUS_DIR = "Oculus";
    public static final String STORAGE_THRESHOLD_MAX_BYTES = "sys_storage_threshold_max_bytes";
    public static final String STORAGE_THRESHOLD_PERCENTAGE = "sys_storage_threshold_percentage";
    public static final String TAG = "DeviceUtils";
    public static String mFakeModel;
    @UnsafeContextInjection
    @Inject
    @Eager
    public final Context mContext;

    static {
        String path = Environment.getExternalStorageDirectory().getPath();
        EXTERNAL_STORAGE_FILE_PATH = path;
        MODEL_DEBUG_FILE_PATH = AnonymousClass006.A05(path, "/oculus_model.test");
        AnonymousClass0J8 r1 = new AnonymousClass0J8();
        r1.A01("SM-N910", "SM-N916", "SC-05G", "SC-04G", "SCV31", "404SC", "SM-G920", "SM-G925");
        ALL_MODELS = r1.build();
        AnonymousClass0J8 r12 = new AnonymousClass0J8();
        r12.A01("SM-N910", "SM-N916");
        NOTE4_MODELS = r12.build();
        AnonymousClass0J8 r13 = new AnonymousClass0J8();
        r13.A01("SC-05G", "SC-04G", "SCV31", "404SC");
        JAPAN_MODELS = r13.build();
    }

    @Inject
    public DeviceUtils(AbstractC02990bJ r2) {
        this.mContext = C01340Gg.A02(r2);
    }
}
