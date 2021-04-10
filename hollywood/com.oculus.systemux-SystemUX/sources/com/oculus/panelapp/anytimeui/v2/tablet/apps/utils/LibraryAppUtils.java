package com.oculus.panelapp.anytimeui.v2.tablet.apps.utils;

import android.content.Context;
import android.content.pm.PackageManager;
import android.content.pm.PermissionInfo;
import android.content.res.Resources;
import android.graphics.drawable.Drawable;
import android.text.TextUtils;
import android.text.format.DateUtils;
import android.text.format.Formatter;
import android.util.Log;
import androidx.core.content.ContextCompat;
import com.oculus.appmanager.info.model.InstallerResult;
import com.oculus.appmanager.info.model.InstallerResultError;
import com.oculus.common.gatekeepers.Gatekeeper;
import com.oculus.common.logutilities.LoggingUtil;
import com.oculus.library.model.App;
import com.oculus.library.model.AppStatus;
import com.oculus.library.model.CloudStorageStatus;
import com.oculus.library.model.HeadTracking;
import com.oculus.library.model.Image;
import com.oculus.library.model.InstallerCallback;
import com.oculus.library.model.SupportedPlatform;
import com.oculus.ocui.OCTextUtils;
import com.oculus.panelapp.anytimeui.R;
import com.oculus.panelapp.anytimeui.v2.AnytimeUIPanelAppBase;
import com.oculus.panelapp.anytimeui.v2.tablet.apps.LibraryViewModel;
import com.oculus.panelapp.anytimeui.v2.tablet.apps.models.LibraryAppTileContextMenuItem;
import com.oculus.systemdialog.DialogButton;
import com.oculus.systemdialog.DialogButtonText;
import com.oculus.systemdialog.DialogDefinitionCustom;
import com.oculus.systemdialog.DialogList;
import com.oculus.systemdialog.DialogListItem;
import com.oculus.systemdialog.DialogListType;
import com.oculus.systemdialog.DialogResult;
import com.oculus.systemdialog.DialogResultHandler;
import com.oculus.tablet.logging.ClickEventButtonId;
import com.oculus.vrshell.SystemUXRoute;
import com.oculus.vrshell.util.HorizonUtil;
import java.util.ArrayList;
import java.util.List;

public class LibraryAppUtils {
    private static final String CANCEL_ACTION = "cancel";
    private static final String CONFIRM_ACTION = "confirm";
    private static final Image.ImageName[] IMAGE_TYPES = {Image.ImageName.SOURCE_TINY, Image.ImageName.SOURCE_MAIN, Image.ImageName.LANDSCAPE_SMALL, Image.ImageName.SOURCE_SQUARE};
    private static final String TAG = LoggingUtil.tag(LibraryAppUtils.class);
    private static final Long V18_ROLL_OUT_TIMESTAMP = 1593388800000L;

    public static Drawable getAppTileCTAIcon(Context context, App app) {
        if (isCloudStorageBusy(app)) {
            return ContextCompat.getDrawable(context, R.drawable.oc_icon_cloud_syncing_filled_24_d2d2d2);
        }
        switch (app.status) {
            case INSTALL_AVAILABLE:
            case INSTALLED:
                return ContextCompat.getDrawable(context, R.drawable.oc_icon_play_circle_filled_24_d2d2d2);
            case ENTITLED:
                return ContextCompat.getDrawable(context, R.drawable.oc_icon_sync_circle_filled_24_d2d2d2);
            case DOWNLOAD_QUEUED:
            case DOWNLOADING:
                return ContextCompat.getDrawable(context, R.drawable.oc_icon_close_circle_filled_24_d2d2d2);
            case INCOMPATIBLE:
                return ContextCompat.getDrawable(context, R.drawable.oc_icon_close_filled_24_d2d2d2);
            default:
                return null;
        }
    }

    public static String getAppTileCTAText(Context context, App app) {
        Resources resources = context.getResources();
        if (isCloudStorageBusy(app)) {
            return resources.getString(R.string.anytime_tablet_library_app_tile_cta_cloud_syncing);
        }
        switch (app.status) {
            case INSTALL_AVAILABLE:
            case INSTALLED:
                return resources.getString(R.string.anytime_tablet_library_app_tile_cta_open);
            case ENTITLED:
                return resources.getString(R.string.anytime_tablet_library_app_tile_cta_install);
            case DOWNLOAD_QUEUED:
            case DOWNLOADING:
                return resources.getString(R.string.anytime_tablet_library_app_tile_cta_cancel);
            case INCOMPATIBLE:
                return resources.getString(R.string.anytime_tablet_library_app_tile_cta_incompatible);
            default:
                return "";
        }
    }

    public static String getAppTileSubtitle(AnytimeUIPanelAppBase anytimeUIPanelAppBase, boolean z, boolean z2, App app) {
        Context context = anytimeUIPanelAppBase.getContext();
        Resources resources = context.getResources();
        if (isAppUnavailableDueToInternetLost(app, z)) {
            return resources.getString(R.string.anytime_tablet_library_app_tile_subtitle_internet_required);
        }
        if (isAppUnavailableDueToTrackingLost(app, z2)) {
            return resources.getString(R.string.anytime_tablet_library_app_tile_subtitle_tracking_required);
        }
        if (isUnofficial(app)) {
            return resources.getString(R.string.anytime_tablet_library_app_tile_subtitle_unofficial);
        }
        switch (app.status) {
            case INSTALL_AVAILABLE:
            case INSTALLED:
                if (app.versionCode < app.latestVersionCode) {
                    return resources.getString(R.string.anytime_tablet_library_app_tile_subtitle_update_available);
                }
                if (shouldIndicateNonCompliant(anytimeUIPanelAppBase, app)) {
                    return resources.getString(R.string.anytime_tablet_library_app_tile_subtitle_non_compliant);
                }
                if (app.recentActivityMs == 0) {
                    return "";
                }
                long currentTimeMillis = System.currentTimeMillis();
                if (currentTimeMillis - app.recentActivityMs < 60000) {
                    return resources.getString(R.string.anytime_tablet_library_app_tile_subtitle_just_now);
                }
                return DateUtils.getRelativeTimeSpanString(app.recentActivityMs, currentTimeMillis, 60000).toString();
            case ENTITLED:
                return resources.getString(R.string.anytime_tablet_library_app_tile_subtitle_not_installed);
            case DOWNLOAD_QUEUED:
                return resources.getString(R.string.anytime_tablet_library_app_tile_subtitle_queued);
            case DOWNLOADING:
                return String.format("%s/%s", Formatter.formatFileSize(context, app.downloadedSizeBytes), Formatter.formatFileSize(context, app.downloadSizeBytes));
            case INCOMPATIBLE:
                return resources.getString(R.string.anytime_tablet_library_app_tile_subtitle_incompatible);
            case INSTALLING:
                return resources.getString(R.string.anytime_tablet_library_app_tile_subtitle_installing);
            case UNINSTALLING:
                return resources.getString(R.string.anytime_tablet_library_app_tile_subtitle_uninstalling);
            default:
                return "";
        }
    }

    public static int getAppTileFooterHeight(Context context) {
        int fontSizeSetting = OCTextUtils.getFontSizeSetting();
        int dimension = (int) context.getResources().getDimension(R.dimen.anytime_tablet_library_app_tile_default_footer_height);
        return Math.max((((int) context.getResources().getDimension(R.dimen.anytime_tablet_library_app_tile_footer_delta)) * fontSizeSetting) + dimension, dimension);
    }

    public static boolean isUnofficial(App app) {
        return app.status == AppStatus.INSTALLED && !TextUtils.isEmpty(app.trustedBinaryStatus) && app.trustedBinaryStatus.equals("UNTRUSTED");
    }

    public static boolean shouldIndicateNonCompliant(AnytimeUIPanelAppBase anytimeUIPanelAppBase, App app) {
        return anytimeUIPanelAppBase.isGKEnabled(Gatekeeper.DUC_LIBRARY_UI_NOTICE) && app.status == AppStatus.INSTALLED && app.ducNonCompliant;
    }

    public static boolean shouldShowImageBadge(AnytimeUIPanelAppBase anytimeUIPanelAppBase, App app) {
        return isUnofficial(app) || shouldIndicateNonCompliant(anytimeUIPanelAppBase, app);
    }

    public static Drawable getAppTileImageBadgeSource(Context context, AnytimeUIPanelAppBase anytimeUIPanelAppBase, App app) {
        if (isUnofficial(app)) {
            return ContextCompat.getDrawable(context, R.drawable.oc_icon_sync_off_filled_24_d2d2d2);
        }
        if (shouldIndicateNonCompliant(anytimeUIPanelAppBase, app)) {
            return ContextCompat.getDrawable(context, R.drawable.oc_icon_error_filled_24_d2d2d2);
        }
        return null;
    }

    public static boolean shouldShowTextBadge(AnytimeUIPanelAppBase anytimeUIPanelAppBase, App app, boolean z) {
        if (shouldShowImageBadge(anytimeUIPanelAppBase, app)) {
            return false;
        }
        if (!TextUtils.isEmpty(app.isDemoOf) || app.isConcept || z) {
            return true;
        }
        return false;
    }

    public static String getAppTileTextBadgeText(Context context, App app, boolean z) {
        if (!TextUtils.isEmpty(app.isDemoOf)) {
            return context.getResources().getString(R.string.anytime_tablet_library_app_tile_badge_demo);
        }
        if (app.isConcept) {
            return context.getResources().getString(R.string.anytime_tablet_library_app_tile_badge_concept);
        }
        return z ? context.getResources().getString(R.string.anytime_tablet_library_app_tile_badge_prototype) : "";
    }

    public static boolean shouldShowAttention(App app) {
        return app.status == AppStatus.INSTALLED && (app.versionCode < app.latestVersionCode || app.isUnseen);
    }

    public static boolean shouldForceHoverOverlay(App app) {
        if (isCloudStorageBusy(app)) {
            return true;
        }
        switch (app.status) {
            case DOWNLOAD_QUEUED:
            case DOWNLOADING:
            case INCOMPATIBLE:
            case INSTALLING:
            case UNINSTALLING:
                return true;
            default:
                return false;
        }
    }

    public static int getAppTileProgress(App app) {
        if (app.status == AppStatus.INSTALLING) {
            return 100;
        }
        if (app.status != AppStatus.DOWNLOADING || app.downloadSizeBytes == 0) {
            return 0;
        }
        return (int) ((app.downloadedSizeBytes * 100) / app.downloadSizeBytes);
    }

    public static boolean shouldShowProgressBar(App app) {
        return app.status == AppStatus.INSTALLING || (app.status == AppStatus.DOWNLOADING && app.downloadSizeBytes != 0);
    }

    public static boolean isCloudStorageBusy(App app) {
        CloudStorageStatus cloudStorageStatus = app.cloudStorageStatus;
        return cloudStorageStatus == CloudStorageStatus.UPLOAD_SYNCING || cloudStorageStatus == CloudStorageStatus.DOWNLOAD_SYNCING;
    }

    public static void doStatusBasedAppAction(App app, int i, AnytimeUIPanelAppBase anytimeUIPanelAppBase, boolean z, LibraryLogger libraryLogger) {
        if (!isCloudStorageBusy(app)) {
            libraryLogger.logAppClick(app, i);
            int i2 = AnonymousClass1.$SwitchMap$com$oculus$library$model$AppStatus[app.status.ordinal()];
            boolean z2 = false;
            if (i2 != 1) {
                if (i2 != 2) {
                    if (i2 != 3) {
                        if (i2 == 4 || i2 == 5) {
                            Log.d(TAG, String.format("User clicked app tile to cancel downloading %s", app.displayName));
                            HorizonUtil.cancelDownloadPackage(anytimeUIPanelAppBase.getContext(), app.packageName);
                            return;
                        }
                        return;
                    }
                } else if (isAppUnavailableDueToInternetLost(app, z)) {
                    anytimeUIPanelAppBase.actionNavigate(SystemUXRoute.WIFI, "");
                    return;
                } else {
                    Log.d(TAG, String.format("User clicked app tile to launch %s", app.displayName));
                    launchApp(anytimeUIPanelAppBase, app);
                    return;
                }
            }
            if (isAppUnavailableDueToInternetLost(app, z)) {
                anytimeUIPanelAppBase.actionNavigate(SystemUXRoute.WIFI, "");
                return;
            }
            libraryLogger.logAppInstall(app, i);
            Log.d(TAG, String.format("User clicked app tile to download and install %s", app.displayName));
            if (app.status == AppStatus.INSTALL_AVAILABLE) {
                z2 = true;
            }
            if (app.latestTargetSdkVersion >= 23) {
                executeAppInstall(anytimeUIPanelAppBase, app.packageName, z2);
                return;
            }
            Log.d(TAG, "App target SDK is below 23 (Android 6), launching permission dialog.");
            showPermissionsInstallDialog(anytimeUIPanelAppBase, app, z2);
        }
    }

    private static void launchApp(AnytimeUIPanelAppBase anytimeUIPanelAppBase, App app) {
        launchAppByPackageName(anytimeUIPanelAppBase, app.packageName);
    }

    private static void launchAppByPackageName(AnytimeUIPanelAppBase anytimeUIPanelAppBase, String str) {
        anytimeUIPanelAppBase.actionNavigate(str, "");
    }

    public static void uninstallApp(Context context, String str) {
        Log.d(TAG, String.format("Uninstalling %s", str));
        HorizonUtil.uninstallPackage(context, str);
    }

    public static boolean isAppAvailable(boolean z, boolean z2, App app) {
        return !isAppUnavailableDueToInternetLost(app, z) && !isAppUnavailableDueToTrackingLost(app, z2);
    }

    public static boolean isAppUnavailableDueToInternetLost(App app, boolean z) {
        return (((app.status == AppStatus.INSTALLED || app.status == AppStatus.INSTALL_AVAILABLE) && "REQUIRED".equals(app.internetConnection)) || app.status == AppStatus.ENTITLED) && !z;
    }

    public static boolean isAppUnavailableDueToTrackingLost(App app, boolean z) {
        return (app.status == AppStatus.INSTALLED || app.status == AppStatus.INSTALL_AVAILABLE) && app.headTracking == HeadTracking.REQUIRE_6DOF && !z;
    }

    public static boolean hasContextMenu(App app, LibraryViewModel libraryViewModel) {
        if (LibraryFakeAppUtils.isFakeApp(app.packageName)) {
            return false;
        }
        if (app.platform == SupportedPlatform.ANDROID_6DOF || !TextUtils.isEmpty(app.isDemoOf) || app.status == AppStatus.INSTALLED || libraryViewModel.getAppsWithAchievements().getOrDefault(app.packageName, false).booleanValue() || libraryViewModel.getAppsWithLeaderboards().getOrDefault(app.packageName, false).booleanValue()) {
            return true;
        }
        return false;
    }

    public static List<LibraryAppTileContextMenuItem> getAppTileContextMenuItems(App app, LibraryViewModel libraryViewModel) {
        ArrayList arrayList = new ArrayList();
        if (app.platform == SupportedPlatform.ANDROID_6DOF) {
            arrayList.add(LibraryAppTileContextMenuItem.DETAILS);
        }
        if (!TextUtils.isEmpty(app.isDemoOf)) {
            arrayList.add(LibraryAppTileContextMenuItem.FULL_VERSION);
        }
        if (libraryViewModel.getAppsWithAchievements().getOrDefault(app.packageName, false).booleanValue()) {
            arrayList.add(LibraryAppTileContextMenuItem.ACHIEVEMENTS);
        }
        if (libraryViewModel.getAppsWithLeaderboards().getOrDefault(app.packageName, false).booleanValue()) {
            arrayList.add(LibraryAppTileContextMenuItem.LEADERBOARDS);
        }
        if (app.status == AppStatus.INSTALLED) {
            arrayList.add(LibraryAppTileContextMenuItem.PERMISSIONS);
        }
        if (app.status == AppStatus.INSTALLED && !LibraryUtils.UNINSTALL_RESTRICTED_PACKAGE_NAMES.contains(app.packageName)) {
            arrayList.add(LibraryAppTileContextMenuItem.UNINSTALL);
        }
        return arrayList;
    }

    public static void onContextMenuItemClicked(Context context, AnytimeUIPanelAppBase anytimeUIPanelAppBase, LibraryLogger libraryLogger, App app, LibraryAppTileContextMenuItem libraryAppTileContextMenuItem, int i) {
        libraryLogger.logContextMenuClick(app, libraryAppTileContextMenuItem.getLoggingLabel(), i);
        switch (libraryAppTileContextMenuItem) {
            case DETAILS:
                SystemUXRoute systemUXRoute = SystemUXRoute.STORE;
                anytimeUIPanelAppBase.actionNavigate(systemUXRoute, "/item/" + app.id);
                return;
            case FULL_VERSION:
                SystemUXRoute systemUXRoute2 = SystemUXRoute.STORE;
                anytimeUIPanelAppBase.actionNavigate(systemUXRoute2, "/item/" + app.isDemoOf);
                return;
            case ACHIEVEMENTS:
                anytimeUIPanelAppBase.actionNavigate(SystemUXRoute.GAMING_ACTIVITY, String.format("/?section=ACHIEVEMENTS&appID=%s&ref=library", app.id));
                return;
            case LEADERBOARDS:
                anytimeUIPanelAppBase.actionNavigate(SystemUXRoute.GAMING_ACTIVITY, String.format("/?section=LEADERBOARDS&appID=%s&ref=library", app.id));
                return;
            case PERMISSIONS:
                anytimeUIPanelAppBase.actionNavigate("systemux://permissions/" + app.packageName, "/");
                return;
            case UNINSTALL:
                showUninstallDialog(context, anytimeUIPanelAppBase, app.displayName, app.packageName);
                return;
            default:
                return;
        }
    }

    public static boolean shouldShowContextMenu(boolean z, boolean z2, boolean z3, App app) {
        return z && (z2 || z3 || shouldForceHoverOverlay(app));
    }

    private static void showPermissionsInstallDialog(AnytimeUIPanelAppBase anytimeUIPanelAppBase, App app, boolean z) {
        showPermissionsDialog(anytimeUIPanelAppBase, app, R.string.anytime_tablet_library_permission_install_dialog_body, R.string.anytime_tablet_library_permission_install_dialog_confirm, new DialogResultHandler(app, z) {
            /* class com.oculus.panelapp.anytimeui.v2.tablet.apps.utils.$$Lambda$LibraryAppUtils$vwUkuJ8eFpynJUUh5hQKrEsYhRE */
            private final /* synthetic */ App f$1;
            private final /* synthetic */ boolean f$2;

            {
                this.f$1 = r2;
                this.f$2 = r3;
            }

            @Override // com.oculus.systemdialog.DialogResultHandler
            public final boolean handleDialogResult(DialogResult dialogResult) {
                return LibraryAppUtils.lambda$showPermissionsInstallDialog$147(AnytimeUIPanelAppBase.this, this.f$1, this.f$2, dialogResult);
            }
        });
    }

    static /* synthetic */ boolean lambda$showPermissionsInstallDialog$147(AnytimeUIPanelAppBase anytimeUIPanelAppBase, App app, boolean z, DialogResult dialogResult) {
        if (!dialogResult.getDialogAction().equals("confirm")) {
            return true;
        }
        executeAppInstall(anytimeUIPanelAppBase, app.packageName, z);
        return true;
    }

    private static void showPermissionsDialog(AnytimeUIPanelAppBase anytimeUIPanelAppBase, App app, int i, int i2, DialogResultHandler dialogResultHandler) {
        String str;
        PackageManager.NameNotFoundException e;
        Resources resources = anytimeUIPanelAppBase.getContext().getResources();
        String string = resources.getString(R.string.anytime_tablet_library_permission_install_dialog_title);
        String string2 = resources.getString(i);
        String string3 = resources.getString(i2);
        String string4 = resources.getString(R.string.anytime_tablet_library_permission_install_dialog_cancel);
        DialogDefinitionCustom dialogDefinitionCustom = new DialogDefinitionCustom("anytime_tablet_library_permissions_dialog", string, string2);
        dialogDefinitionCustom.setPrimaryButton(new DialogButtonText("confirm", string3));
        dialogDefinitionCustom.setSecondaryButton(new DialogButtonText("cancel", string4));
        dialogDefinitionCustom.setControllerBackButton(new DialogButton("cancel"));
        DialogList dialogList = new DialogList(DialogListType.NO_SELECT);
        PackageManager packageManager = anytimeUIPanelAppBase.getContext().getPackageManager();
        for (String str2 : app.latestPermissions) {
            String str3 = null;
            try {
                PermissionInfo permissionInfo = packageManager.getPermissionInfo(str2, 0);
                str = permissionInfo.name;
                try {
                    str3 = resources.getString(R.string.anytime_tablet_library_permission_install_dialog_item_description, permissionInfo.loadLabel(packageManager));
                } catch (PackageManager.NameNotFoundException e2) {
                    e = e2;
                }
            } catch (PackageManager.NameNotFoundException e3) {
                e = e3;
                str = str2;
                Log.e(TAG, String.format("Permission Name not found in Android permission list, showing the permission's full name instead: %s", str2), e);
                dialogList.addListItem(new DialogListItem(str2, str, str3, null, null));
            }
            dialogList.addListItem(new DialogListItem(str2, str, str3, null, null));
        }
        dialogDefinitionCustom.setList(dialogList);
        dialogDefinitionCustom.setDialogResultHandler(dialogResultHandler);
        anytimeUIPanelAppBase.getDialogManager().showDialog(dialogDefinitionCustom);
    }

    public static void executeAppInstall(AnytimeUIPanelAppBase anytimeUIPanelAppBase, String str, boolean z) {
        Log.d(TAG, String.format("Installing app %s", str));
        HorizonUtil.downloadAndInstallPackage(anytimeUIPanelAppBase.getContext(), str, new InstallerCallback(z, str) {
            /* class com.oculus.panelapp.anytimeui.v2.tablet.apps.utils.$$Lambda$LibraryAppUtils$BxYdiyngcTQPJiBDXBaKvycyQyo */
            private final /* synthetic */ boolean f$1;
            private final /* synthetic */ String f$2;

            {
                this.f$1 = r2;
                this.f$2 = r3;
            }

            @Override // com.oculus.library.model.InstallerCallback
            public final void onInstallerResult(InstallerResult installerResult) {
                LibraryAppUtils.lambda$executeAppInstall$148(AnytimeUIPanelAppBase.this, this.f$1, this.f$2, installerResult);
            }
        });
    }

    static /* synthetic */ void lambda$executeAppInstall$148(AnytimeUIPanelAppBase anytimeUIPanelAppBase, boolean z, String str, InstallerResult installerResult) {
        if (!installerResult.isSuccess()) {
            if (AnonymousClass1.$SwitchMap$com$oculus$appmanager$info$model$InstallerResultError[installerResult.error.ordinal()] != 1) {
                String str2 = TAG;
                Log.w(str2, "Unhandled download error " + installerResult.error.name());
                return;
            }
            anytimeUIPanelAppBase.actionNavigate(SystemUXRoute.APP_DOWNLOAD_FAILURE_LOW_STORAGE, "");
        } else if (z) {
            Log.d(TAG, String.format("Attempting to post-install launch package %s", str));
            launchAppByPackageName(anytimeUIPanelAppBase, str);
        }
    }

    public static void showUninstallDialog(Context context, AnytimeUIPanelAppBase anytimeUIPanelAppBase, String str, String str2) {
        Resources resources = context.getResources();
        String string = resources.getString(R.string.anytime_tablet_library_uninstall_dialog_title, str);
        String string2 = resources.getString(R.string.anytime_tablet_library_uninstall_dialog_body);
        String string3 = resources.getString(R.string.anytime_tablet_library_uninstall_dialog_confirm);
        String string4 = resources.getString(R.string.anytime_tablet_library_uninstall_dialog_cancel);
        DialogDefinitionCustom dialogDefinitionCustom = new DialogDefinitionCustom("anytime_tablet_library_uninstall_dialog", string, string2);
        dialogDefinitionCustom.setPrimaryButton(new DialogButtonText("confirm", string3));
        dialogDefinitionCustom.setSecondaryButton(new DialogButtonText("cancel", string4));
        dialogDefinitionCustom.setControllerBackButton(new DialogButton("cancel"));
        dialogDefinitionCustom.setDialogResultHandler(new DialogResultHandler("confirm", context, str2) {
            /* class com.oculus.panelapp.anytimeui.v2.tablet.apps.utils.$$Lambda$LibraryAppUtils$PEPnWKfM3YE7JnWwQjMPonjro4g */
            private final /* synthetic */ String f$0;
            private final /* synthetic */ Context f$1;
            private final /* synthetic */ String f$2;

            {
                this.f$0 = r1;
                this.f$1 = r2;
                this.f$2 = r3;
            }

            @Override // com.oculus.systemdialog.DialogResultHandler
            public final boolean handleDialogResult(DialogResult dialogResult) {
                return LibraryAppUtils.lambda$showUninstallDialog$149(this.f$0, this.f$1, this.f$2, dialogResult);
            }
        });
        anytimeUIPanelAppBase.getDialogManager().showDialog(dialogDefinitionCustom);
    }

    static /* synthetic */ boolean lambda$showUninstallDialog$149(String str, Context context, String str2, DialogResult dialogResult) {
        if (!dialogResult.getDialogAction().equals(str)) {
            return true;
        }
        uninstallApp(context, str2);
        return true;
    }

    public static String getPreferredAppTileImageUri(App app) {
        Image image = null;
        for (Image.ImageName imageName : IMAGE_TYPES) {
            image = app.images.get(imageName);
            if (image != null) {
                break;
            }
        }
        if (image != null) {
            return image.uri;
        }
        return null;
    }

    public static boolean isSystemAppReadyToLaunch(App app) {
        return app.status == AppStatus.INSTALLED;
    }

    public static boolean isSystemAppDownloadingUpdate(App app) {
        return app.status == AppStatus.DOWNLOAD_QUEUED || app.status == AppStatus.DOWNLOADING;
    }

    public static boolean isSystemAppUpdating(App app) {
        return app.status == AppStatus.DOWNLOAD_QUEUED || app.status == AppStatus.DOWNLOADING || app.status == AppStatus.INSTALLING;
    }

    public static int getSystemAppUpdateProgress(App app) {
        if (app.status == AppStatus.DOWNLOAD_QUEUED) {
            return 0;
        }
        if (app.status == AppStatus.INSTALLING) {
            return 100;
        }
        if (app.status != AppStatus.DOWNLOADING || app.downloadSizeBytes == 0) {
            return 0;
        }
        return (int) ((app.downloadedSizeBytes * 100) / app.downloadSizeBytes);
    }

    public static boolean shouldShowSystemAppUpdateDot(App app) {
        return app.status == AppStatus.INSTALLED && app.versionCode < app.latestVersionCode;
    }

    public static void doStatusBasedSystemAppAction(App app, AnytimeUIPanelAppBase anytimeUIPanelAppBase, boolean z) {
        doStatusBasedSystemAppAction(app, anytimeUIPanelAppBase, null, z);
    }

    public static void doStatusBasedSystemAppAction(App app, AnytimeUIPanelAppBase anytimeUIPanelAppBase, SystemUXRoute systemUXRoute, boolean z) {
        if (app != null && app.status != null) {
            int i = AnonymousClass1.$SwitchMap$com$oculus$library$model$AppStatus[app.status.ordinal()];
            if (i != 2) {
                if (i != 3) {
                    if (i == 4 || i == 5) {
                        HorizonUtil.cancelDownloadPackage(anytimeUIPanelAppBase.getContext(), app.packageName);
                    }
                } else if (!z) {
                    anytimeUIPanelAppBase.actionNavigate(SystemUXRoute.WIFI, "");
                } else {
                    HorizonUtil.downloadAndInstallPackage(anytimeUIPanelAppBase.getContext(), app.packageName, new InstallerCallback() {
                        /* class com.oculus.panelapp.anytimeui.v2.tablet.apps.utils.$$Lambda$LibraryAppUtils$C4AuWOaVyTLU_AQGn9VolT2W6m0 */

                        @Override // com.oculus.library.model.InstallerCallback
                        public final void onInstallerResult(InstallerResult installerResult) {
                            LibraryAppUtils.lambda$doStatusBasedSystemAppAction$150(AnytimeUIPanelAppBase.this, installerResult);
                        }
                    });
                }
            } else if (systemUXRoute != null) {
                anytimeUIPanelAppBase.actionNavigate(systemUXRoute, "");
            } else {
                anytimeUIPanelAppBase.actionNavigate(app.packageName, "");
            }
        }
    }

    /* access modifiers changed from: package-private */
    /* renamed from: com.oculus.panelapp.anytimeui.v2.tablet.apps.utils.LibraryAppUtils$1  reason: invalid class name */
    public static /* synthetic */ class AnonymousClass1 {
        static final /* synthetic */ int[] $SwitchMap$com$oculus$appmanager$info$model$InstallerResultError = new int[InstallerResultError.values().length];

        /* JADX WARNING: Can't wrap try/catch for region: R(32:0|(2:1|2)|3|5|6|7|(2:9|10)|11|(2:13|14)|15|17|18|19|(2:21|22)|23|(2:25|26)|27|29|30|31|32|33|34|35|36|37|38|39|40|41|42|(3:43|44|46)) */
        /* JADX WARNING: Can't wrap try/catch for region: R(34:0|(2:1|2)|3|5|6|7|(2:9|10)|11|13|14|15|17|18|19|(2:21|22)|23|25|26|27|29|30|31|32|33|34|35|36|37|38|39|40|41|42|(3:43|44|46)) */
        /* JADX WARNING: Can't wrap try/catch for region: R(39:0|1|2|3|5|6|7|9|10|11|13|14|15|17|18|19|21|22|23|25|26|27|29|30|31|32|33|34|35|36|37|38|39|40|41|42|43|44|46) */
        /* JADX WARNING: Failed to process nested try/catch */
        /* JADX WARNING: Missing exception handler attribute for start block: B:31:0x0071 */
        /* JADX WARNING: Missing exception handler attribute for start block: B:33:0x007b */
        /* JADX WARNING: Missing exception handler attribute for start block: B:35:0x0085 */
        /* JADX WARNING: Missing exception handler attribute for start block: B:37:0x008f */
        /* JADX WARNING: Missing exception handler attribute for start block: B:39:0x0099 */
        /* JADX WARNING: Missing exception handler attribute for start block: B:41:0x00a3 */
        /* JADX WARNING: Missing exception handler attribute for start block: B:43:0x00ae */
        static {
            /*
            // Method dump skipped, instructions count: 187
            */
            throw new UnsupportedOperationException("Method not decompiled: com.oculus.panelapp.anytimeui.v2.tablet.apps.utils.LibraryAppUtils.AnonymousClass1.<clinit>():void");
        }
    }

    static /* synthetic */ void lambda$doStatusBasedSystemAppAction$150(AnytimeUIPanelAppBase anytimeUIPanelAppBase, InstallerResult installerResult) {
        if (installerResult.isSuccess()) {
            return;
        }
        if (AnonymousClass1.$SwitchMap$com$oculus$appmanager$info$model$InstallerResultError[installerResult.error.ordinal()] != 1) {
            String str = TAG;
            Log.w(str, "Unhandled download error " + installerResult.error.name());
            return;
        }
        anytimeUIPanelAppBase.actionNavigate(SystemUXRoute.APP_DOWNLOAD_FAILURE_LOW_STORAGE, "");
    }

    public static boolean isAppPrototype(App app, String str) {
        return !TextUtils.isEmpty(str) && str.equals(app.applicationOrganizationId);
    }

    public static App adjustApp(Context context, App app, boolean z) {
        if (!z && app.recentActivityMs >= 9999999999L) {
            return app;
        }
        App.Builder builder = new App.Builder(app);
        if (z) {
            builder.withImages(getOverrideSystemAppImage(context, app.packageName, app.images));
        }
        if (app.recentActivityMs < 9999999999L) {
            builder.withRecentActivityMs(app.recentActivityMs * 1000);
        }
        return builder.build();
    }

    /* JADX WARNING: Removed duplicated region for block: B:12:0x0027  */
    /* JADX WARNING: Removed duplicated region for block: B:15:0x0035  */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public static java.util.Map<com.oculus.library.model.Image.ImageName, com.oculus.library.model.Image> getOverrideSystemAppImage(android.content.Context r3, java.lang.String r4, java.util.Map<com.oculus.library.model.Image.ImageName, com.oculus.library.model.Image> r5) {
        /*
            int r0 = r4.hashCode()
            r1 = -1562378864(0xffffffffa2dffd90, float:-6.071274E-18)
            r2 = 1
            if (r0 == r1) goto L_0x001a
            r1 = 1060068122(0x3f2f5b1a, float:0.68498385)
            if (r0 == r1) goto L_0x0010
            goto L_0x0024
        L_0x0010:
            java.lang.String r0 = "com.oculus.tv"
            boolean r4 = r4.equals(r0)
            if (r4 == 0) goto L_0x0024
            r4 = r2
            goto L_0x0025
        L_0x001a:
            java.lang.String r0 = "com.oculus.browser"
            boolean r4 = r4.equals(r0)
            if (r4 == 0) goto L_0x0024
            r4 = 0
            goto L_0x0025
        L_0x0024:
            r4 = -1
        L_0x0025:
            if (r4 == 0) goto L_0x0035
            if (r4 == r2) goto L_0x002a
            return r5
        L_0x002a:
            android.content.res.Resources r3 = r3.getResources()
            int r4 = com.oculus.panelapp.anytimeui.R.drawable.app_tile_tv
            java.lang.String r3 = com.oculus.panelapp.anytimeui.v2.tablet.apps.utils.LibraryFakeAppUtils.getDrawableUri(r3, r4)
            goto L_0x003f
        L_0x0035:
            android.content.res.Resources r3 = r3.getResources()
            int r4 = com.oculus.panelapp.anytimeui.R.drawable.app_tile_browser
            java.lang.String r3 = com.oculus.panelapp.anytimeui.v2.tablet.apps.utils.LibraryFakeAppUtils.getDrawableUri(r3, r4)
        L_0x003f:
            com.oculus.library.model.Image r4 = new com.oculus.library.model.Image
            com.oculus.library.model.Image$ImageName r5 = com.oculus.library.model.Image.ImageName.LANDSCAPE_SMALL
            r0 = 171(0xab, float:2.4E-43)
            r1 = 96
            r4.<init>(r5, r3, r0, r1)
            java.util.HashMap r3 = new java.util.HashMap
            r3.<init>()
            com.oculus.library.model.Image$ImageName r5 = com.oculus.library.model.Image.ImageName.LANDSCAPE_SMALL
            r3.put(r5, r4)
            return r3
        */
        throw new UnsupportedOperationException("Method not decompiled: com.oculus.panelapp.anytimeui.v2.tablet.apps.utils.LibraryAppUtils.getOverrideSystemAppImage(android.content.Context, java.lang.String, java.util.Map):java.util.Map");
    }

    /* JADX INFO: Can't fix incorrect switch cases order, some code will duplicate */
    public static void maybeLogSystemAppClick(AnytimeUIPanelAppBase anytimeUIPanelAppBase, LibraryLogger libraryLogger, String str, int i) {
        char c;
        switch (str.hashCode()) {
            case -1769000967:
                if (str.equals(LibraryFakeAppUtils.CAMERA_ROLL_PACKAGE_NAME)) {
                    c = 3;
                    break;
                }
                c = 65535;
                break;
            case -1581178321:
                if (str.equals(LibraryFakeAppUtils.STORE_PACKAGE_NAME)) {
                    c = 1;
                    break;
                }
                c = 65535;
                break;
            case -1562378864:
                if (str.equals("com.oculus.browser")) {
                    c = 4;
                    break;
                }
                c = 65535;
                break;
            case 1060068122:
                if (str.equals("com.oculus.tv")) {
                    c = 5;
                    break;
                }
                c = 65535;
                break;
            case 1851939697:
                if (str.equals(LibraryFakeAppUtils.HOME_PACKAGE_NAME)) {
                    c = 0;
                    break;
                }
                c = 65535;
                break;
            case 1949021219:
                if (str.equals(LibraryFakeAppUtils.SOCIAL_PACKAGE_NAME)) {
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
            anytimeUIPanelAppBase.logButtonClick(ClickEventButtonId.AUIV2_LIBRARY_HOME);
            libraryLogger.logAppClick(str, "INSTALLED", anytimeUIPanelAppBase.getContext().getResources().getString(R.string.anytime_tablet_library_home_button_v2), i);
        } else if (c == 1) {
            anytimeUIPanelAppBase.logButtonClick(ClickEventButtonId.AUIV2_LIBRARY_STORE);
            libraryLogger.logAppClick(str, "INSTALLED", anytimeUIPanelAppBase.getContext().getResources().getString(R.string.anytime_tablet_library_store_button_v2), i);
        } else if (c == 2) {
            anytimeUIPanelAppBase.logButtonClick(ClickEventButtonId.AUIV2_LIBRARY_SOCIAL);
            libraryLogger.logAppClick(str, "INSTALLED", anytimeUIPanelAppBase.getContext().getResources().getString(R.string.anytime_tablet_library_social_button_v2), i);
        } else if (c == 3) {
            anytimeUIPanelAppBase.logButtonClick(ClickEventButtonId.AUIV2_LIBRARY_CAMERA_ROLL);
            libraryLogger.logAppClick(str, "INSTALLED", anytimeUIPanelAppBase.getContext().getResources().getString(R.string.anytime_tablet_library_camera_roll_button_v2), i);
        } else if (c == 4) {
            anytimeUIPanelAppBase.logButtonClick(ClickEventButtonId.AUIV2_LIBRARY_BROWSER);
        } else if (c == 5) {
            anytimeUIPanelAppBase.logButtonClick(ClickEventButtonId.AUIV2_LIBRARY_TV);
        }
    }
}
