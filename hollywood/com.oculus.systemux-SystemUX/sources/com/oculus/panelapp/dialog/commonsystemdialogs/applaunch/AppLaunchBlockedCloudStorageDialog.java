package com.oculus.panelapp.dialog.commonsystemdialogs.applaunch;

import android.content.Context;
import android.os.Handler;
import android.text.format.DateUtils;
import android.util.Log;
import com.oculus.assistant.service.api.OculusAssistantContract;
import com.oculus.cloudstoragehelper.CloudStorageCallback;
import com.oculus.cloudstoragehelper.CloudStorageHelper;
import com.oculus.cloudstoragehelper.CloudStorageResult;
import com.oculus.common.logutilities.LoggingUtil;
import com.oculus.os.AnalyticsEvent;
import com.oculus.os.UnifiedTelemetryLogger;
import com.oculus.panelapp.dialog.CommonDialog;
import com.oculus.panelapp.dialog.R;
import com.oculus.systemdialog.CommonSystemDialog;
import com.oculus.systemdialog.CommonSystemDialogActions;
import com.oculus.systemdialog.DialogButton;
import com.oculus.systemdialog.DialogButtonPrimary;
import com.oculus.systemdialog.DialogButtonText;
import com.oculus.systemdialog.DialogDefinitionCustom;
import com.oculus.systemdialog.DialogList;
import com.oculus.systemdialog.DialogListItem;
import com.oculus.systemdialog.DialogListType;
import com.oculus.systemdialog.DialogPrimaryButtonStyle;
import com.oculus.vrshell.util.HorizonUtil;
import java.util.Map;
import java.util.UUID;

public final class AppLaunchBlockedCloudStorageDialog extends CommonDialog {
    private static final long CALLBACK_TIMEOUT_MS = 5000;
    private static final int DATE_FLAGS = 65553;
    private static final String INTERNAL_ACTION_LAUNCH = "launch";
    private static final String INTERNAL_ACTION_USE_LOCAL = "use_local";
    private static final String INTERNAL_ACTION_USE_REMOTE = "use_remote";
    private static final String PARAM_LAUNCH_IMMEDIATELY_AFTER = "launch_immediately_after";
    private static final String PARAM_PACKAGE_NAME = "package_name";
    private static final String TAG = LoggingUtil.tag(AppLaunchBlockedCloudStorageDialog.class);
    private final String mAppId;
    private final String mAppPackage;
    private final CloudStorageCallbackImpl mCloudStorageCallback = new CloudStorageCallbackImpl();
    private boolean mCloudSyncIsResolution;
    private float mCloudSyncProgress;
    private CloudSyncStatus mCloudSyncStatus = CloudSyncStatus.NONE;
    private long mConflictDeviceDate;
    private long mConflictRemoteDate;
    private final Context mContext;
    private final String mDialogId;
    private final boolean mLaunchImmediatelyAfter;
    private final UnifiedTelemetryLogger mUnifiedTelemetryLogger;

    public AppLaunchBlockedCloudStorageDialog(Context context, Map<String, String> map) {
        this.mContext = context;
        this.mAppPackage = getStringParameterOrDefault(map, "package_name", null);
        this.mLaunchImmediatelyAfter = getBooleanParameterOrDefault(map, PARAM_LAUNCH_IMMEDIATELY_AFTER, false);
        String str = TAG;
        Log.d(str, "Target package = " + this.mAppPackage);
        String str2 = TAG;
        Log.d(str2, "Launch immediately after = " + Boolean.toString(this.mLaunchImmediatelyAfter));
        this.mUnifiedTelemetryLogger = UnifiedTelemetryLogger.getInstance(context);
        this.mAppId = HorizonUtil.getApplicationId(context, this.mAppPackage);
        this.mDialogId = UUID.randomUUID().toString();
        setDialogConfiguration();
        logDialogAction("configure");
        if (this.mAppId.isEmpty()) {
            actionInvalidArgument();
        } else {
            actionSync();
        }
    }

    /* JADX INFO: Can't fix incorrect switch cases order, some code will duplicate */
    @Override // com.oculus.panelapp.dialog.CommonDialog
    public void onAction(String str, String[] strArr) {
        char c;
        switch (str.hashCode()) {
            case -1512031746:
                if (str.equals(INTERNAL_ACTION_USE_REMOTE)) {
                    c = 5;
                    break;
                }
                c = 65535;
                break;
            case -1367724422:
                if (str.equals("cancel")) {
                    c = 0;
                    break;
                }
                c = 65535;
                break;
            case -1109843021:
                if (str.equals(INTERNAL_ACTION_LAUNCH)) {
                    c = 3;
                    break;
                }
                c = 65535;
                break;
            case -567202649:
                if (str.equals(CommonSystemDialogActions.CONTINUE)) {
                    c = 1;
                    break;
                }
                c = 65535;
                break;
            case -192575821:
                if (str.equals(INTERNAL_ACTION_USE_LOCAL)) {
                    c = 4;
                    break;
                }
                c = 65535;
                break;
            case 17818629:
                if (str.equals(CommonSystemDialogActions.INVALID_ARGUMENT)) {
                    c = 2;
                    break;
                }
                c = 65535;
                break;
            default:
                c = 65535;
                break;
        }
        if (c == 0) {
            logDialogAction("cancel");
        } else if (c != 1) {
            if (c == 2) {
                return;
            }
            if (c == 3) {
                actionLaunch();
            } else if (c == 4) {
                actionResolve(CloudStorageHelper.ResolutionType.USE_LOCAL);
            } else if (c == 5) {
                actionResolve(CloudStorageHelper.ResolutionType.USE_REMOTE);
            }
        } else if (this.mAppPackage != null && this.mLaunchImmediatelyAfter) {
            this.mContext.startActivity(this.mContext.getPackageManager().getLaunchIntentForPackage(this.mAppPackage));
        }
    }

    @Override // com.oculus.panelapp.dialog.CommonDialog
    public boolean shouldSendActionToShell(String str, String[] strArr) {
        return !INTERNAL_ACTION_USE_LOCAL.equals(str) && !INTERNAL_ACTION_USE_REMOTE.equals(str) && !INTERNAL_ACTION_LAUNCH.equals(str);
    }

    private void setDialogConfiguration() {
        int i = AnonymousClass1.$SwitchMap$com$oculus$panelapp$dialog$commonsystemdialogs$applaunch$AppLaunchBlockedCloudStorageDialog$CloudSyncStatus[this.mCloudSyncStatus.ordinal()];
        if (i == 1) {
            setPendingDialogConfiguration(getBaseDefinition(this.mContext, ""));
        } else if (i == 2) {
            setPendingDialogConfiguration(getSyncingDefinition());
        } else if (i == 3) {
            setPendingDialogConfiguration(getFailureDefinition());
        } else if (i == 4) {
            setPendingDialogConfiguration(getConflictDefinition());
        }
    }

    private static DialogDefinitionCustom getBaseDefinition(Context context, String str) {
        return new DialogDefinitionCustom(CommonSystemDialog.APP_LAUNCH_BLOCKED_CLOUD_STORAGE.getDialogId(), context.getResources().getString(R.string.app_launch_blocked_cloud_storage_dialog_title), str).setControllerBackButton(new DialogButton("close"));
    }

    private DialogDefinitionCustom getSyncingDefinition() {
        Context context = this.mContext;
        return getBaseDefinition(context, context.getResources().getString(this.mCloudSyncIsResolution ? R.string.app_launch_blocked_cloud_storage_dialog_text_resolving : R.string.app_launch_blocked_cloud_storage_dialog_description_syncing)).setProgressBar(this.mCloudSyncProgress).setTertiaryButton(new DialogButtonText("cancel", this.mContext.getResources().getString(R.string.app_launch_blocked_cloud_storage_dialog_button_cancel)));
    }

    private DialogDefinitionCustom getFailureDefinition() {
        Context context = this.mContext;
        return getBaseDefinition(context, context.getResources().getString(R.string.app_launch_blocked_cloud_storage_dialog_text_failure)).setPrimaryButton(new DialogButtonText(INTERNAL_ACTION_LAUNCH, this.mContext.getResources().getString(R.string.app_launch_blocked_cloud_storage_dialog_button_launch))).setSecondaryButton(new DialogButtonText("cancel", this.mContext.getResources().getString(R.string.app_launch_blocked_cloud_storage_dialog_button_cancel)));
    }

    private DialogDefinitionCustom getConflictDefinition() {
        DialogList dialogList = new DialogList(DialogListType.NO_SELECT);
        boolean z = this.mConflictDeviceDate > this.mConflictRemoteDate;
        dialogList.addListItem(getConflictListItem("local", z, z ? R.string.app_launch_blocked_cloud_storage_dialog_label_local_newer : R.string.app_launch_blocked_cloud_storage_dialog_label_local, this.mConflictDeviceDate));
        dialogList.addListItem(getConflictListItem("remote", !z, z ? R.string.app_launch_blocked_cloud_storage_dialog_label_remote : R.string.app_launch_blocked_cloud_storage_dialog_label_remote_newer, this.mConflictRemoteDate));
        Context context = this.mContext;
        return getBaseDefinition(context, context.getResources().getString(R.string.app_launch_blocked_cloud_storage_dialog_text_conflict)).setList(dialogList).setPrimaryButton(new DialogButtonPrimary(INTERNAL_ACTION_USE_LOCAL, this.mContext.getResources().getString(R.string.app_launch_blocked_cloud_storage_dialog_button_use_local), DialogPrimaryButtonStyle.SECONDARY)).setSecondaryButton(new DialogButtonText(INTERNAL_ACTION_USE_REMOTE, this.mContext.getResources().getString(R.string.app_launch_blocked_cloud_storage_dialog_button_use_remote))).setTertiaryButton(new DialogButtonText("cancel", this.mContext.getResources().getString(R.string.app_launch_blocked_cloud_storage_dialog_button_cancel)));
    }

    private DialogListItem getConflictListItem(String str, boolean z, int i, long j) {
        String str2;
        if (j != -1) {
            str2 = this.mContext.getResources().getString(R.string.app_launch_blocked_cloud_storage_dialog_text_last_updated, DateUtils.formatDateTime(this.mContext, j * 1000, DATE_FLAGS));
        } else {
            str2 = this.mContext.getResources().getString(R.string.app_launch_blocked_cloud_storage_dialog_text_last_updated_unknown);
        }
        return new DialogListItem(str, this.mContext.getResources().getString(i), str2, null, null);
    }

    /* access modifiers changed from: package-private */
    /* renamed from: com.oculus.panelapp.dialog.commonsystemdialogs.applaunch.AppLaunchBlockedCloudStorageDialog$1  reason: invalid class name */
    public static /* synthetic */ class AnonymousClass1 {
        static final /* synthetic */ int[] $SwitchMap$com$oculus$cloudstoragehelper$CloudStorageResult$SyncResultType = new int[CloudStorageResult.SyncResultType.values().length];
        static final /* synthetic */ int[] $SwitchMap$com$oculus$panelapp$dialog$commonsystemdialogs$applaunch$AppLaunchBlockedCloudStorageDialog$CloudSyncStatus = new int[CloudSyncStatus.values().length];

        /* JADX WARNING: Can't wrap try/catch for region: R(20:0|1|2|3|(2:5|6)|7|(2:9|10)|11|13|14|15|17|18|19|20|21|22|23|24|26) */
        /* JADX WARNING: Can't wrap try/catch for region: R(21:0|1|2|3|5|6|7|(2:9|10)|11|13|14|15|17|18|19|20|21|22|23|24|26) */
        /* JADX WARNING: Failed to process nested try/catch */
        /* JADX WARNING: Missing exception handler attribute for start block: B:19:0x0048 */
        /* JADX WARNING: Missing exception handler attribute for start block: B:21:0x0052 */
        /* JADX WARNING: Missing exception handler attribute for start block: B:23:0x005c */
        static {
            /*
            // Method dump skipped, instructions count: 103
            */
            throw new UnsupportedOperationException("Method not decompiled: com.oculus.panelapp.dialog.commonsystemdialogs.applaunch.AppLaunchBlockedCloudStorageDialog.AnonymousClass1.<clinit>():void");
        }
    }

    /* access modifiers changed from: private */
    /* access modifiers changed from: public */
    private void onRecvCloudStorageUpdate(CloudStorageResult cloudStorageResult) {
        int i = AnonymousClass1.$SwitchMap$com$oculus$cloudstoragehelper$CloudStorageResult$SyncResultType[cloudStorageResult.resultType.ordinal()];
        if (i == 1) {
            this.mCloudSyncProgress = Math.max(Math.min(cloudStorageResult.progress, 1.0f), 0.0f);
            setDialogConfiguration();
        } else if (i == 2) {
            this.mCloudSyncProgress = 1.0f;
            setDialogConfiguration();
            actionLaunch();
        } else if (i == 3) {
            actionFailure();
        } else if (i != 4) {
            String str = TAG;
            Log.w(str, "Unexpected result type received: " + cloudStorageResult.resultType.name());
        } else {
            actionConflict(cloudStorageResult.conflictLocalTimestamp, cloudStorageResult.conflictRemoteTimestamp);
        }
    }

    private void actionInvalidArgument() {
        logDialogAction(CommonSystemDialogActions.INVALID_ARGUMENT);
        setPendingSyntheticButtonClick(new DialogButton(CommonSystemDialogActions.INVALID_ARGUMENT));
    }

    private void actionSync() {
        logDialogAction("sync");
        this.mCloudSyncStatus = CloudSyncStatus.SYNCING;
        this.mCloudSyncProgress = 0.0f;
        this.mCloudSyncIsResolution = false;
        setDialogConfiguration();
        this.mCloudStorageCallback.startTimeout();
        CloudStorageHelper.runLaunchSync(this.mContext, this.mAppId, this.mCloudStorageCallback);
    }

    private void actionFailure() {
        logDialogAction("failure");
        this.mCloudSyncStatus = CloudSyncStatus.FAILURE;
        setDialogConfiguration();
    }

    private void actionConflict(long j, long j2) {
        logDialogAction("conflict");
        this.mCloudSyncStatus = CloudSyncStatus.CONFLICT;
        this.mConflictDeviceDate = j;
        this.mConflictRemoteDate = j2;
        setDialogConfiguration();
    }

    private void actionResolve(CloudStorageHelper.ResolutionType resolutionType) {
        logDialogAction(resolutionType.name().toLowerCase());
        this.mCloudSyncStatus = CloudSyncStatus.SYNCING;
        this.mCloudSyncProgress = 0.0f;
        this.mCloudSyncIsResolution = true;
        setDialogConfiguration();
        this.mCloudStorageCallback.startTimeout();
        CloudStorageHelper.runConflictResolve(this.mContext, this.mAppId, resolutionType, this.mCloudStorageCallback);
    }

    private void actionLaunch() {
        logDialogAction(INTERNAL_ACTION_LAUNCH);
        setPendingSyntheticButtonClick(new DialogButton(CommonSystemDialogActions.CONTINUE));
    }

    private void logDialogAction(String str) {
        AnalyticsEvent analyticsEvent = new AnalyticsEvent("oculus_vrshell_cloud_storage_dialog");
        analyticsEvent.setExtra("action", str);
        analyticsEvent.setExtra(OculusAssistantContract.EXTRA_DIALOG_ID, this.mDialogId);
        analyticsEvent.setExtra("dialog_view", this.mCloudSyncStatus.getAnalyticsName());
        analyticsEvent.setExtra("app_item_id", this.mAppId.isEmpty() ? "0" : this.mAppId);
        analyticsEvent.setExtra("app_package_name", this.mAppPackage.isEmpty() ? "package" : this.mAppPackage);
        this.mUnifiedTelemetryLogger.reportEvent(analyticsEvent, false);
    }

    /* access modifiers changed from: private */
    public enum CloudSyncStatus {
        NONE("none"),
        SYNCING("SyncView"),
        FAILURE("FailureView"),
        CONFLICT("ConflictView");
        
        private final String mAnalyticsName;

        private CloudSyncStatus(String str) {
            this.mAnalyticsName = str;
        }

        public String getAnalyticsName() {
            return this.mAnalyticsName;
        }
    }

    /* access modifiers changed from: private */
    public class CloudStorageCallbackImpl implements CloudStorageCallback {
        private final Handler mHandler = new Handler();
        private Runnable mTimeout;

        public CloudStorageCallbackImpl() {
        }

        public void startTimeout() {
            this.mTimeout = new Runnable() {
                /* class com.oculus.panelapp.dialog.commonsystemdialogs.applaunch.AppLaunchBlockedCloudStorageDialog.CloudStorageCallbackImpl.AnonymousClass1 */

                public void run() {
                    CloudStorageCallbackImpl.this.onResult(CloudStorageResult.forFailure("No response received from service (timeout)."));
                }
            };
            this.mHandler.postDelayed(this.mTimeout, 5000);
        }

        @Override // com.oculus.cloudstoragehelper.CloudStorageCallback
        public void onResult(final CloudStorageResult cloudStorageResult) {
            String str = AppLaunchBlockedCloudStorageDialog.TAG;
            Log.d(str, "Received cloud storage sync result: " + cloudStorageResult);
            this.mHandler.post(new Runnable() {
                /* class com.oculus.panelapp.dialog.commonsystemdialogs.applaunch.AppLaunchBlockedCloudStorageDialog.CloudStorageCallbackImpl.AnonymousClass2 */

                public void run() {
                    if (CloudStorageCallbackImpl.this.mTimeout != null) {
                        CloudStorageCallbackImpl.this.mHandler.removeCallbacks(CloudStorageCallbackImpl.this.mTimeout);
                        CloudStorageCallbackImpl.this.mTimeout = null;
                    }
                    AppLaunchBlockedCloudStorageDialog.this.onRecvCloudStorageUpdate(cloudStorageResult);
                }
            });
        }
    }
}
