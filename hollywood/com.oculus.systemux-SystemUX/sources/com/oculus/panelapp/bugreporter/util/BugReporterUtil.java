package com.oculus.panelapp.bugreporter.util;

import android.content.Context;
import android.net.Uri;
import android.text.TextUtils;
import android.util.Log;
import androidx.annotation.VisibleForTesting;
import com.oculus.assistant.service.api.panel.AssistantComponentContract;
import com.oculus.common.deviceconfig.DeviceConfigHelper;
import com.oculus.common.gatekeepers.Gatekeeper;
import com.oculus.common.logutilities.LoggingUtil;
import com.oculus.notifications.NotificationSender;
import com.oculus.os.BugReportBuilder;
import com.oculus.os.BugReportClient;
import com.oculus.panelapp.bugreporter.BugReporterPanelApp;
import com.oculus.panelapp.bugreporter.BugReporterViewModel;
import com.oculus.panelapp.bugreporter.DataPermissionViewModel;
import com.oculus.panelapp.bugreporter.DescriptionViewModel;
import com.oculus.panelapp.bugreporter.MediaViewModel;
import com.oculus.panelapp.bugreporter.R;
import com.oculus.panelapp.bugreporter.common.ClickEventButtonId;
import com.oculus.vrshell.util.HorizonUtil;
import com.oculus.vrshell.util.ThreadExecutor;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.concurrent.Callable;

public class BugReporterUtil {
    private static final String BUG_REPORTER_BUTTON_CLICK_EVENT = "oculus_systemux_bug_reporter_button_press";
    private static final String BUG_REPORT_BUTTON_ID_EXTRA = "button_id";
    private static final String TAG = LoggingUtil.tag(BugReporterUtil.class);
    private BugReportClient mBugReportClient;
    private BugReporterViewModel mBugReporterViewModel;
    private Context mContext;
    private DataPermissionViewModel mDataPermissionViewModel;
    private DescriptionViewModel mDescriptionViewModel;
    private MediaViewModel mMediaViewModel;
    private BugReporterPanelApp mPanelApp;

    public BugReporterUtil(Context context, BugReporterPanelApp bugReporterPanelApp) {
        this.mContext = context;
        this.mPanelApp = bugReporterPanelApp;
        this.mBugReportClient = new BugReportClient(context, true);
    }

    public void submitReport() {
        ThreadExecutor.getInstance().execute(new Callable() {
            /* class com.oculus.panelapp.bugreporter.util.$$Lambda$BugReporterUtil$6DATTAZnZvp7tDLahOdMxQI6ng8 */

            @Override // java.util.concurrent.Callable
            public final Object call() {
                return BugReporterUtil.this.lambda$submitReport$0$BugReporterUtil();
            }
        });
    }

    public /* synthetic */ Object lambda$submitReport$0$BugReporterUtil() throws Exception {
        DescriptionViewModel descriptionViewModel = getDescriptionViewModel();
        MediaViewModel mediaViewModel = getMediaViewModel();
        DataPermissionViewModel dataPermissionViewModel = getDataPermissionViewModel();
        boolean submitReportSync = submitReportSync(this.mContext, this.mBugReportClient, descriptionViewModel, mediaViewModel, dataPermissionViewModel, getReturnComponent(), getEntrySource(), new BugReportBuilder());
        if (!submitReportSync && !isPublicUser()) {
            notifySubmissionError();
        }
        logButtonClick(ClickEventButtonId.BUG_REPORT_SUBMITTED, "success", Boolean.toString(submitReportSync), "category", descriptionViewModel.getBugCategory().name(), "include_screenshot", Boolean.toString(descriptionViewModel.getIncludeScreenshot()), "include_logs", Boolean.toString(dataPermissionViewModel.getAttachLogs()));
        return null;
    }

    @VisibleForTesting
    static boolean submitReportSync(Context context, BugReportClient bugReportClient, DescriptionViewModel descriptionViewModel, MediaViewModel mediaViewModel, DataPermissionViewModel dataPermissionViewModel, String str, String str2, BugReportBuilder bugReportBuilder) {
        List<String> list;
        if (TextUtils.isEmpty(str)) {
            str = "com.oculus.vrshell";
        }
        String applicationId = HorizonUtil.getApplicationId(context, str);
        if (descriptionViewModel.getHasPreselectedPhoto()) {
            list = Collections.singletonList(descriptionViewModel.getPreselectedPhoto().getPath());
        } else {
            list = new ArrayList(mediaViewModel.getSelectedFiles());
        }
        boolean submitBugReport = bugReportClient.submitBugReport(bugReportBuilder.setAppId(applicationId).setCategoryId(String.valueOf(descriptionViewModel.getBugCategory().getId())).setDescription(descriptionViewModel.getDescriptionText()).setEarlyCollectLogs(true).setEntrySource(str2).setAttachLogs(dataPermissionViewModel.getAttachLogs()).setExtraMedia(list).setSourceApp(str).setScreenshot(descriptionViewModel.getIncludeScreenshot() ? descriptionViewModel.getScreenshotBitmap() : null).build());
        String str3 = TAG;
        Log.d(str3, "Bug report submission succeeded? " + submitBugReport);
        return submitBugReport;
    }

    public void cancelReport() {
        ThreadExecutor.getInstance().execute(new Callable() {
            /* class com.oculus.panelapp.bugreporter.util.$$Lambda$BugReporterUtil$i5dHgZWg_ih7br1d_xfUH30Jc8 */

            @Override // java.util.concurrent.Callable
            public final Object call() {
                return BugReporterUtil.this.lambda$cancelReport$1$BugReporterUtil();
            }
        });
    }

    public /* synthetic */ Object lambda$cancelReport$1$BugReporterUtil() throws Exception {
        try {
            boolean cancelBugReport = this.mBugReportClient.cancelBugReport(new BugReportBuilder().build());
            String str = TAG;
            Log.d(str, "Bug report cancel succeeded? " + cancelBugReport);
            return null;
        } catch (Exception e) {
            Log.d(TAG, "Encountered exception cancelling report", e);
            return null;
        }
    }

    private void notifySubmissionError() {
        NotificationSender.build("oculus_mobile_system_ux_bug_report_submission_failure", this.mContext.getResources().getString(R.string.bug_report_data_submission_error_notification_title), this.mContext.getResources().getString(R.string.bug_report_data_submission_error_notification_text), R.drawable.ic_notif_alert).send(this.mContext);
    }

    public boolean isPublicUser() {
        return !DeviceConfigHelper.getBoolean(this.mContext, Gatekeeper.IS_TRUSTED_USER);
    }

    public DataPermissionViewModel getDataPermissionViewModel() {
        if (this.mDataPermissionViewModel == null) {
            this.mDataPermissionViewModel = new DataPermissionViewModel(this);
        }
        return this.mDataPermissionViewModel;
    }

    public DescriptionViewModel getDescriptionViewModel() {
        if (this.mDescriptionViewModel == null) {
            this.mDescriptionViewModel = new DescriptionViewModel(this.mContext, this);
        }
        return this.mDescriptionViewModel;
    }

    public MediaViewModel getMediaViewModel() {
        if (this.mMediaViewModel == null) {
            this.mMediaViewModel = new MediaViewModel(this.mContext, this);
        }
        return this.mMediaViewModel;
    }

    public BugReporterViewModel getBugReporterViewModel() {
        if (this.mBugReporterViewModel == null) {
            this.mBugReporterViewModel = new BugReporterViewModel(this.mPanelApp, this);
        }
        return this.mBugReporterViewModel;
    }

    private Uri getEnvironmentUri() {
        String environmentArg = this.mPanelApp.getEnvironmentArg(AssistantComponentContract.Components.RemoteImageViewComponent.URI);
        if (environmentArg == null) {
            environmentArg = "";
        }
        return Uri.parse(environmentArg);
    }

    public String getReturnComponent() {
        return getEnvironmentUri().getQueryParameter("returnComponent");
    }

    public String getActiveComponent() {
        return getEnvironmentUri().getQueryParameter("activeComponent");
    }

    public String getEntrySource() {
        return getEnvironmentUri().getQueryParameter("entrySource");
    }

    public String getPreselectedPhoto() {
        return getEnvironmentUri().getQueryParameter("photos");
    }

    public void logButtonClick(ClickEventButtonId clickEventButtonId, String... strArr) {
        ArrayList arrayList = new ArrayList(Arrays.asList(strArr));
        arrayList.add("button_id");
        arrayList.add(clickEventButtonId.getTelemetryButtonId());
        this.mPanelApp.getLogger().rawLogEvent(BUG_REPORTER_BUTTON_CLICK_EVENT, (String[]) arrayList.toArray(new String[0]));
    }
}
