package com.oculus.horizon.vrbugreporter;

import X.AnonymousClass0J2;
import X.AnonymousClass0NO;
import android.app.job.JobInfo;
import android.app.job.JobScheduler;
import android.content.ComponentName;
import com.facebook.ultralight.Eager;
import com.facebook.ultralight.Inject;
import com.google.common.collect.ImmutableSet;
import com.google.inject.name.Named;
import com.oculus.horizon.platform.PresenceManager;
import com.oculus.horizon.service.OVRModule;
import com.oculus.os.LogCollectorClient;
import com.oculus.os.SettingsManager;
import com.oculus.security.basecomponent.OculusPublicIntentService;
import com.oculus.util.constants.OculusConstants;
import com.oculus.util.device.DeviceUtils;
import java.io.File;
import java.util.Set;
import java.util.concurrent.ScheduledThreadPoolExecutor;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;
import javax.inject.Provider;

public class BugReporterService extends OculusPublicIntentService {
    public static final String DEFAULT_APP_ID = "1481000308606657";
    public static final String EXTRA_ADD_VISION_LOGS = "AddVisionLogs";
    public static final String EXTRA_BUG_REPORT_AUDIO_CLIP_PATH = "AudioClipFile";
    public static final String EXTRA_BUG_REPORT_DETAILS = "BugReportDetails";
    public static final String EXTRA_BUG_REPORT_ID = "BugReportId";
    public static final String INTENT_ADD_DETAILS = "com.oculus.horizon.vrbugreporter.action.ADD_DETAILS";
    public static final String INTENT_REQUEST = "com.oculus.horizon.vrbugreporter.action.REQUEST";
    public static final int JOB_ID_BUG_REPORTER_CLEANER = 4096;
    public static final int JOB_ID_BUG_REPORTER_UPLOADER = 4097;
    public static final String SETTINGS_KEY_ADD_VISION_LOGS = "bug_report_add_vision_logs";
    public static final String SHELL_HOME_URI_KEY = "uri";
    public static final String TAG = "BugReporterService";
    public static final Set<String> sFirstPartyAppIDsForFlytrap = ImmutableSet.A07(OculusConstants.OCULUS_360_PHOTOS_APP_ID, OculusConstants.OCULUS_BROWSER_GO_GEAR_APP_ID, OculusConstants.OCULUS_BROWSER_QUEST_APP_ID, OculusConstants.OCULUS_GALLERY_APP_ID, OculusConstants.OCULUS_VIDEO_APP_ID, OculusConstants.SNAP_VR_APP_ID, "1481000308606657");
    @Inject
    @Eager
    public DeviceUtils mDeviceUtils;
    public ThreadPoolExecutor mExecutor = new ScheduledThreadPoolExecutor(1);
    @Inject
    @Named(OVRModule.ENABLE_VR_BUGREPORTER)
    public Provider<Boolean> mIsOculusVrBugReporterEnabledGK;
    @Inject
    @Eager
    public PresenceManager mPresenceManager;

    public BugReporterService() {
        super(BugReporterService.class.getName());
    }

    public static void A01(BugReporterService bugReporterService, String str, File file) {
        if (new LogCollectorClient().collectLogArchive(bugReporterService.getApplicationContext(), file).isError()) {
            AnonymousClass0NO.A0E(TAG, "could not collect data from log collector for report id %s", str);
        }
    }

    public static void A02(BugReporterService bugReporterService, boolean z) {
        new SettingsManager(bugReporterService).setBoolean(SETTINGS_KEY_ADD_VISION_LOGS, z);
    }

    /* JADX DEBUG: Multi-variable search result rejected for r0v81, resolved type: java.io.FileOutputStream */
    /* JADX DEBUG: Multi-variable search result rejected for r0v82, resolved type: java.nio.file.SecureDirectoryStream */
    /* JADX WARN: Multi-variable type inference failed */
    /* JADX WARNING: Can't wrap try/catch for region: R(7:128|129|(1:131)|132|133|138|184) */
    /* JADX WARNING: Failed to process nested try/catch */
    /* JADX WARNING: Missing exception handler attribute for start block: B:132:0x0296 */
    /* JADX WARNING: Removed duplicated region for block: B:111:0x0256 A[SYNTHETIC, Splitter:B:111:0x0256] */
    @Override // X.AnonymousClass1U8
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public final void A03(@javax.annotation.Nullable final android.content.Intent r27) {
        /*
        // Method dump skipped, instructions count: 1019
        */
        throw new UnsupportedOperationException("Method not decompiled: com.oculus.horizon.vrbugreporter.BugReporterService.A03(android.content.Intent):void");
    }

    public static void A00(BugReporterService bugReporterService, BugReport bugReport) {
        bugReport.A05();
        ((JobScheduler) bugReporterService.getSystemService("jobscheduler")).schedule(new JobInfo.Builder(JOB_ID_BUG_REPORTER_UPLOADER, new ComponentName(bugReporterService, BugReporterUploaderService.class)).setRequiredNetworkType(2).setPersisted(true).build());
    }

    @Override // X.AnonymousClass1U8, com.oculus.security.basecomponent.OculusPublicIntentService
    public final void onCreate() {
        super.onCreate();
        AnonymousClass0J2 r1 = AnonymousClass0J2.get(this);
        this.mIsOculusVrBugReporterEnabledGK = OVRModule._UL__ULSEP_javax_inject_Provider_ULLT_java_lang_Boolean_ULGT__ULSEP_com_google_inject_name_Named_ULUNDERSCORE_oculus_ULUNDERSCORE_vrbugreporter_ULUNDERSCORE_enabled_ULSEP_ACCESS_METHOD(r1);
        this.mPresenceManager = PresenceManager._UL__ULSEP_com_oculus_horizon_platform_PresenceManager_ULSEP_ACCESS_METHOD(r1);
        this.mDeviceUtils = DeviceUtils.A00(r1);
        ((JobScheduler) getSystemService("jobscheduler")).schedule(new JobInfo.Builder(4096, new ComponentName(this, BugReporterCleanerService.class)).setPeriodic(TimeUnit.DAYS.toMillis(1)).setRequiresCharging(true).setRequiresDeviceIdle(true).setPersisted(true).build());
    }
}
