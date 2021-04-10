package com.oculus.horizon.abuse_prevention;

import X.AnonymousClass0J2;
import X.AnonymousClass117;
import android.annotation.TargetApi;
import com.facebook.ultralight.Eager;
import com.oculus.horizon.logging.OculusLogger;
import com.oculus.security.basecomponent.OculusPublicIntentService;
import com.oculus.util.vr.VRUtils;
import javax.inject.Inject;

@TargetApi(21)
public class VideoUploaderService extends OculusPublicIntentService {
    public static final String KEY_RECORDING_UUID = "recording_uuid";
    public static final String KEY_REPORT_ID = "report_id";
    public static final String TAG = "VideoUploaderService";
    @Inject
    @Eager
    public OculusLogger mOculusLogger;
    @Inject
    @Eager
    public VRUtils mVRUtils;
    @Inject
    @Eager
    public VideoUploaderServiceManager mVideoUploaderServiceManager;

    public VideoUploaderService() {
        super(TAG);
    }

    @Override // X.AnonymousClass1U8, com.oculus.security.basecomponent.OculusPublicIntentService
    public final void onCreate() {
        super.onCreate();
        AnonymousClass0J2 r1 = AnonymousClass0J2.get(this);
        this.mVideoUploaderServiceManager = (VideoUploaderServiceManager) AnonymousClass117.A00(61, r1);
        this.mOculusLogger = (OculusLogger) AnonymousClass117.A00(574, r1);
        this.mVRUtils = (VRUtils) AnonymousClass117.A00(181, r1);
    }
}
