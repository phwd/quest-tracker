package com.oculus.panelapp.anytimeui.v2.tablet.apps;

import android.content.Context;
import android.util.Log;
import com.oculus.appmanager.info.model.InstallerResult;
import com.oculus.appmanager.info.model.InstallerResultError;
import com.oculus.common.deviceconfig.DeviceConfigHelper;
import com.oculus.common.gatekeepers.Gatekeeper;
import com.oculus.common.logutilities.LoggingUtil;
import com.oculus.library.model.App;
import com.oculus.library.model.Image;
import com.oculus.library.model.InstallerCallback;
import com.oculus.notifications.NotificationConstants;
import com.oculus.notifications.NotificationSender;
import com.oculus.panelapp.anytimeui.R;
import com.oculus.panelapp.anytimeui.utils.DeviceUtils;
import com.oculus.panelapp.anytimeui.v2.AnytimeUIPanelAppBase;
import com.oculus.panelapp.anytimeui.v2.tablet.apps.models.LibrarySorter;
import com.oculus.panelapp.anytimeui.v2.tablet.apps.utils.LibraryStateHelper;
import com.oculus.panelapp.anytimeui.v2.tablet.apps.utils.LibraryUtils;
import com.oculus.systemdialog.DialogButton;
import com.oculus.systemdialog.DialogButtonText;
import com.oculus.systemdialog.DialogDefinitionCustom;
import com.oculus.systemdialog.DialogList;
import com.oculus.systemdialog.DialogListItem;
import com.oculus.systemdialog.DialogListItemImageType;
import com.oculus.systemdialog.DialogListTitle;
import com.oculus.systemdialog.DialogListType;
import com.oculus.systemdialog.DialogManager;
import com.oculus.systemdialog.DialogResult;
import com.oculus.systemdialog.DialogResultHandler;
import com.oculus.vrshell.util.DeviceProperties;
import com.oculus.vrshell.util.HorizonUtil;
import java.util.List;

public class LibraryAppMigration {
    private static final String CANCEL_ACTION = "cancel";
    private static final String CONFIRM_ACTION = "confirm";
    private static final String DIALOG_ID = "app_migration_dialog";
    private static final String DIALOG_LOW_STORAGE_ID = "app_migration_low_storage_dialog";
    private static final int MAX_NUMBER_DISPLAYED_APPS = 40;
    private static final String TAG = LoggingUtil.tag(LibraryAppMigration.class);
    private Context mContext;
    private DialogManager mDialogManager;
    private boolean mLowStorageWarningShown;
    private List<App> mMigrationCandidateApps;
    private AnytimeUIPanelAppBase mPanelApp;
    private LibraryStateHelper mStateHelper;

    public LibraryAppMigration(AnytimeUIPanelAppBase anytimeUIPanelAppBase, LibraryStateHelper libraryStateHelper) {
        this.mPanelApp = anytimeUIPanelAppBase;
        this.mContext = anytimeUIPanelAppBase.getContext();
        this.mStateHelper = libraryStateHelper;
        this.mDialogManager = anytimeUIPanelAppBase.getDialogManager();
    }

    public void filterAndSortApps(List<App> list, String str) {
        this.mMigrationCandidateApps = LibraryUtils.filterMigratableApps(list, str);
        LibraryUtils.sortAppsBySorter(this.mMigrationCandidateApps, LibrarySorter.MOST_RECENT);
        if (this.mMigrationCandidateApps.size() > 40) {
            List<App> list2 = this.mMigrationCandidateApps;
            list2.subList(40, list2.size()).clear();
        }
    }

    public void maybeLaunchAppMigrationDialog() {
        if (!hasMigrationCandidateApps()) {
            Log.d(TAG, "Not showing App Migration dialog, there are no apps to show in the dialog");
            this.mStateHelper.saveAppMigrationDialogShownState();
            this.mStateHelper.saveAppMigrationDialogActedState();
            return;
        }
        showAppMigrationDialog();
    }

    public boolean hasMigrationCandidateApps() {
        return this.mMigrationCandidateApps.size() > 0;
    }

    public static boolean shouldShowAppMigrationDialog(LibraryStateHelper libraryStateHelper, boolean z, boolean z2, boolean z3, boolean z4) {
        Log.d(TAG, "Verifying if App Migration Dialog should be shown.");
        if (z) {
            Log.d(TAG, "Not showing App Migration dialog, Enterprise Mode is enabled");
            return false;
        } else if (z2) {
            Log.d(TAG, "Not showing App Migration dialog, Shell Automation is enabled for E2E Testing");
            return false;
        } else if (!DeviceProperties.supportsAppMigration()) {
            Log.d(TAG, "Not showing App Migration dialog, current Device is not supported");
            return false;
        } else if (!z4) {
            Log.d(TAG, "Not showing App Migration dialog, User is not the Main user of the device");
            return false;
        } else if (z3 && !libraryStateHelper.loadAppMigrationDialogShownState()) {
            Log.d(TAG, "Not showing App Migration dialog, not first time wearing the device");
            return false;
        } else if (!libraryStateHelper.loadAppMigrationDialogShownState() || !libraryStateHelper.loadAppMigrationDialogActionState()) {
            if (!libraryStateHelper.loadAppMigrationDialogShownState()) {
                Log.d(TAG, "Showing App Migration dialog, first time showing it");
            } else if (!libraryStateHelper.loadAppMigrationDialogActionState()) {
                Log.d(TAG, "Showing App Migration dialog, not the first time showing it, but user has yet to act on it");
            }
            libraryStateHelper.saveAppMigrationDialogShownState();
            return true;
        } else {
            Log.d(TAG, "Not showing App Migration dialog, Dialog has been shown once already");
            return false;
        }
    }

    public static boolean shouldDownloadMigrationAppsStoredForLaterDownload(LibraryStateHelper libraryStateHelper) {
        return libraryStateHelper.hasMigrationAppsForLaterDownload();
    }

    private void showAppMigrationDialog() {
        Log.d(TAG, "Initializing App Migration dialog");
        Log.d(TAG, String.format("Total apps to show: %d", Integer.valueOf(this.mMigrationCandidateApps.size())));
        DialogDefinitionCustom dialogDefinitionCustom = new DialogDefinitionCustom(DIALOG_ID, this.mContext.getResources().getString(R.string.anytime_library_app_migration_dialog_title), this.mContext.getResources().getString(R.string.anytime_library_app_migration_dialog_description));
        dialogDefinitionCustom.setSecondaryButton(new DialogButtonText("cancel", this.mContext.getResources().getString(R.string.anytime_library_app_migration_dialog_decline_button)));
        dialogDefinitionCustom.setControllerBackButton(new DialogButton("cancel"));
        DialogList dialogList = new DialogList(DialogListType.MULTI_SELECT);
        for (App app : this.mMigrationCandidateApps) {
            Image image = app.images.get(Image.ImageName.SOURCE_SQUARE);
            DialogListItem dialogListItem = new DialogListItem(app.packageName, app.displayName, null, image == null ? null : image.uri, DialogListItemImageType.AVATAR);
            dialogListItem.setPreselected(true);
            dialogList.addListItem(dialogListItem);
        }
        dialogList.setShouldSendSelectionChangeAction(true);
        dialogList.setTitle(new DialogListTitle(this.mContext.getResources().getString(R.string.anytime_library_app_migration_dialog_list_title), false));
        dialogDefinitionCustom.setList(dialogList);
        dialogDefinitionCustom.setPrimaryButton(new DialogButtonText("confirm", getOkButtonString(this.mMigrationCandidateApps.size())));
        dialogDefinitionCustom.setDialogResultHandler(new DialogResultHandler(dialogDefinitionCustom) {
            /* class com.oculus.panelapp.anytimeui.v2.tablet.apps.$$Lambda$LibraryAppMigration$D_oe4jMsTe7F1oBSjS01ljn4PUE */
            private final /* synthetic */ DialogDefinitionCustom f$1;

            {
                this.f$1 = r2;
            }

            @Override // com.oculus.systemdialog.DialogResultHandler
            public final boolean handleDialogResult(DialogResult dialogResult) {
                return LibraryAppMigration.this.lambda$showAppMigrationDialog$111$LibraryAppMigration(this.f$1, dialogResult);
            }
        });
        Log.d(TAG, "Finished setting up the App Migration dialog. Launching the dialog now");
        this.mDialogManager.showDialog(dialogDefinitionCustom);
    }

    public /* synthetic */ boolean lambda$showAppMigrationDialog$111$LibraryAppMigration(DialogDefinitionCustom dialogDefinitionCustom, DialogResult dialogResult) {
        String dialogAction = dialogResult.getDialogAction();
        boolean z = false;
        if (dialogAction.equals(DialogList.DIALOG_LIST_SELECTION_CHANGE_ACTION)) {
            Log.d(TAG, String.format("Selected apps count changed: %d", Integer.valueOf(dialogResult.getDialogSelectedListItemIds().size())));
            DialogButtonText dialogButtonText = new DialogButtonText("confirm", getOkButtonString(dialogResult.getDialogSelectedListItemIds().size()));
            if (dialogResult.getDialogSelectedListItemIds().size() <= 0) {
                z = true;
            }
            dialogDefinitionCustom.setPrimaryButton(dialogButtonText.setDisabled(z));
            this.mDialogManager.showDialog(dialogDefinitionCustom);
        } else if (dialogAction.equals("confirm")) {
            Log.d(TAG, String.format("Confirmed action: Install %d apps", Integer.valueOf(dialogResult.getDialogSelectedListItemIds().size())));
            if (DeviceUtils.isWifiConnected(this.mContext)) {
                Log.d(TAG, String.format("Installing apps for migration %d", Integer.valueOf(dialogResult.getDialogSelectedListItemIds().size())));
                installSelectedApps(dialogResult.getDialogSelectedListItemIds());
            } else {
                Log.d(TAG, "No internet connection, saving the selected apps for later download");
                this.mStateHelper.saveMigrationAppsForLaterDownload(dialogResult.getDialogSelectedListItemIds());
            }
            logResultAction("ok", dialogResult.getDialogSelectedListItemIds().size());
            this.mStateHelper.saveAppMigrationDialogActedState();
        } else if (dialogAction.equals("cancel")) {
            logResultAction("cancel", 0);
            this.mStateHelper.saveAppMigrationDialogActedState();
        }
        return true;
    }

    public void fetchAndDownloadAppsStoredForLater() {
        if (DeviceUtils.isWifiConnected(this.mContext)) {
            installSelectedApps(this.mStateHelper.loadMigrationAppsForLaterDownload());
        }
    }

    private String getOkButtonString(int i) {
        if (i == 0) {
            return this.mContext.getResources().getString(R.string.anytime_library_app_migration_dialog_accept_button_none_apps);
        }
        return this.mContext.getResources().getString(R.string.anytime_library_app_migration_dialog_accept_button_some_apps, Integer.valueOf(i));
    }

    private void installSelectedApps(List<String> list) {
        if (!(list == null || list.isEmpty())) {
            Log.d(TAG, String.format("Initiating download of %d apps for migration", Integer.valueOf(list.size())));
            this.mLowStorageWarningShown = false;
            for (String str : list) {
                Log.d(TAG, String.format("Installing app %s", str));
                HorizonUtil.downloadAndInstallPackage(this.mContext, str, new InstallerCallback() {
                    /* class com.oculus.panelapp.anytimeui.v2.tablet.apps.$$Lambda$LibraryAppMigration$HScjnWn1aAI0H61XoQNvWh4Jtc */

                    @Override // com.oculus.library.model.InstallerCallback
                    public final void onInstallerResult(InstallerResult installerResult) {
                        LibraryAppMigration.this.lambda$installSelectedApps$112$LibraryAppMigration(installerResult);
                    }
                });
            }
        }
    }

    /* renamed from: com.oculus.panelapp.anytimeui.v2.tablet.apps.LibraryAppMigration$1  reason: invalid class name */
    static /* synthetic */ class AnonymousClass1 {
        static final /* synthetic */ int[] $SwitchMap$com$oculus$appmanager$info$model$InstallerResultError = new int[InstallerResultError.values().length];

        static {
            try {
                $SwitchMap$com$oculus$appmanager$info$model$InstallerResultError[InstallerResultError.LOW_STORAGE.ordinal()] = 1;
            } catch (NoSuchFieldError unused) {
            }
        }
    }

    public /* synthetic */ void lambda$installSelectedApps$112$LibraryAppMigration(InstallerResult installerResult) {
        if (installerResult.isSuccess()) {
            return;
        }
        if (AnonymousClass1.$SwitchMap$com$oculus$appmanager$info$model$InstallerResultError[installerResult.error.ordinal()] != 1) {
            String str = TAG;
            Log.w(str, "Unhandled download error " + installerResult.error.name());
            return;
        }
        showLowStorageWarning();
    }

    private void showLowStorageWarning() {
        if (!this.mLowStorageWarningShown) {
            this.mLowStorageWarningShown = true;
            NotificationSender.build(DIALOG_LOW_STORAGE_ID, this.mContext.getResources().getString(R.string.anytime_library_app_migration_low_storage_toast_title), this.mContext.getResources().getString(R.string.anytime_library_app_migration_low_storage_toast_description), R.drawable.ic_notif_alert).setIconImageType(NotificationConstants.LargeImageType.INVALID).send(this.mContext);
        }
    }

    private void logResultAction(String str, int i) {
        this.mPanelApp.rawLogEvent("oculus_auiv2_library_app_migration", "result_action", str, "apps_total", String.valueOf(this.mMigrationCandidateApps.size()), "apps_installed", String.valueOf(i));
    }

    public static boolean hasMigrationCandidateApps(Context context) {
        return LibraryUtils.filterMigratableApps(HorizonUtil.getApplications(context), DeviceConfigHelper.getString(context, Gatekeeper.LIBRARY_PROTOTYPE_ORGANIZATION_ID)).size() > 0;
    }
}
