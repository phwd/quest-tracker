package com.oculus.panelapp.anytimeui.v2.tablet.apps.utils;

import android.content.Context;
import android.content.res.Resources;
import android.net.Uri;
import com.facebook.common.util.UriUtil;
import com.oculus.library.model.App;
import com.oculus.library.model.AppStatus;
import com.oculus.library.model.Category;
import com.oculus.library.model.ComfortRating;
import com.oculus.library.model.GrantReason;
import com.oculus.library.model.HeadTracking;
import com.oculus.library.model.Image;
import com.oculus.library.model.MicrophoneUsage;
import com.oculus.library.model.SupportedPlatform;
import com.oculus.panelapp.anytimeui.R;
import com.oculus.vrshell.SystemUXRoute;
import com.oculus.vrshell.util.DeviceProperties;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;

public class LibraryFakeAppUtils {
    public static final String CAMERA_ROLL_PACKAGE_NAME = "camera_roll_system_app";
    private static final List<String> FAKE_APP_PACKAGE_NAMES = new ArrayList(Arrays.asList(HOME_PACKAGE_NAME, STORE_PACKAGE_NAME, SOCIAL_PACKAGE_NAME, CAMERA_ROLL_PACKAGE_NAME, HANDS_TUTORIAL_ENTITLEMENT_PACKAGE_NAME, FIT_AND_FOCUS_ENTITLEMENT_PACKAGE_NAME));
    private static final String FAKE_ENTITLEMENT_HASH = "faceb00c";
    public static final String FIT_AND_FOCUS_ENTITLEMENT_PACKAGE_NAME = "fit_guide_entitlement";
    public static final String HANDS_TUTORIAL_ENTITLEMENT_PACKAGE_NAME = "hands_guide_entitlement";
    public static final String HOME_PACKAGE_NAME = "home_system_app";
    public static final String SOCIAL_PACKAGE_NAME = "social_system_app";
    public static final String STORE_PACKAGE_NAME = "store_system_app";

    public static boolean isFakeApp(String str) {
        return FAKE_APP_PACKAGE_NAMES.contains(str);
    }

    /* JADX INFO: Can't fix incorrect switch cases order, some code will duplicate */
    public static SystemUXRoute getSystemUXRouteForFakeApp(String str) {
        char c;
        switch (str.hashCode()) {
            case -1769000967:
                if (str.equals(CAMERA_ROLL_PACKAGE_NAME)) {
                    c = 5;
                    break;
                }
                c = 65535;
                break;
            case -1581178321:
                if (str.equals(STORE_PACKAGE_NAME)) {
                    c = 1;
                    break;
                }
                c = 65535;
                break;
            case -160211012:
                if (str.equals(FIT_AND_FOCUS_ENTITLEMENT_PACKAGE_NAME)) {
                    c = 4;
                    break;
                }
                c = 65535;
                break;
            case 607744719:
                if (str.equals(HANDS_TUTORIAL_ENTITLEMENT_PACKAGE_NAME)) {
                    c = 3;
                    break;
                }
                c = 65535;
                break;
            case 1851939697:
                if (str.equals(HOME_PACKAGE_NAME)) {
                    c = 0;
                    break;
                }
                c = 65535;
                break;
            case 1949021219:
                if (str.equals(SOCIAL_PACKAGE_NAME)) {
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
            return SystemUXRoute.HOME;
        }
        if (c == 1) {
            return SystemUXRoute.STORE;
        }
        if (c == 2) {
            return SystemUXRoute.SOCIAL;
        }
        if (c == 3) {
            return SystemUXRoute.HAND_TRACKING_NUX;
        }
        if (c == 4) {
            return SystemUXRoute.IPD_ADJUST;
        }
        if (c != 5) {
            return null;
        }
        return SystemUXRoute.CAMERA_ROLL;
    }

    public static boolean shouldAddReturnComponentToLaunch(String str) {
        return CAMERA_ROLL_PACKAGE_NAME.equals(str);
    }

    public static String getDrawableUri(Resources resources, int i) {
        return new Uri.Builder().scheme(UriUtil.QUALIFIED_RESOURCE_SCHEME).authority(resources.getResourcePackageName(i)).appendPath(resources.getResourceTypeName(i)).appendPath(resources.getResourceEntryName(i)).build().toString();
    }

    private static App buildFakeApp(LibraryStateHelper libraryStateHelper, String str, String str2, String str3) {
        Image image = new Image(Image.ImageName.LANDSCAPE_SMALL, str3, 171, 96);
        HashMap hashMap = new HashMap();
        hashMap.put(Image.ImageName.LANDSCAPE_SMALL, image);
        return new App.Builder().withCategory(Category.APPS).withComfortRating(ComfortRating.NOT_RATED).withDisplayName(str).withDownloadedSizeBytes(0).withDownloadSizeBytes(0).withDucNonCompliant(false).withEntitlementHash(FAKE_ENTITLEMENT_HASH).withGrantReason(GrantReason.UNKNOWN).withGrantTimeMs(-1).withHeadTracking(HeadTracking.ALLOW_6DOF).withIsUnseen(libraryStateHelper.loadFakeAppIsUnseenState(str2, false)).withLatestTargetSdkVersion(0).withMicrophoneUsage(MicrophoneUsage.NONE).withMinRecommendedVersionCode(0).withMinRequiredVersionCode(0).withPackageName(str2).withPlatform(SupportedPlatform.ANDROID_6DOF).withRecentActivityMs(libraryStateHelper.loadFakeAppRecentActivityState(str2, System.currentTimeMillis())).withStatus(AppStatus.INSTALLED).withTrustedBinaryStatus("TRUSTED").withImages(hashMap).withInternetConnection("NOT_REQUIRED").build();
    }

    public static List<App> getFakeApps(Context context, LibraryStateHelper libraryStateHelper, boolean z, boolean z2) {
        ArrayList arrayList = new ArrayList();
        if (z) {
            addSystemApps(context, arrayList, libraryStateHelper, z2);
        }
        arrayList.add(buildFakeApp(libraryStateHelper, context.getResources().getString(R.string.anytime_tablet_library_fake_app_display_name_hands), HANDS_TUTORIAL_ENTITLEMENT_PACKAGE_NAME, getDrawableUri(context.getResources(), R.drawable.hands)));
        if (DeviceProperties.supportsFitAndFocusEntitlement()) {
            arrayList.add(buildFakeApp(libraryStateHelper, context.getResources().getString(R.string.anytime_tablet_library_fake_app_display_name_fit_and_focus), FIT_AND_FOCUS_ENTITLEMENT_PACKAGE_NAME, getDrawableUri(context.getResources(), R.drawable.fit_and_focus)));
        }
        return arrayList;
    }

    private static void addSystemApps(Context context, List<App> list, LibraryStateHelper libraryStateHelper, boolean z) {
        list.add(buildFakeApp(libraryStateHelper, context.getResources().getString(R.string.anytime_tablet_library_home_button_v2), HOME_PACKAGE_NAME, getDrawableUri(context.getResources(), R.drawable.app_tile_home)));
        if (z) {
            list.add(getStoreSystemApp(context, libraryStateHelper));
        }
        list.add(buildFakeApp(libraryStateHelper, context.getResources().getString(R.string.anytime_tablet_library_social_button_v2), SOCIAL_PACKAGE_NAME, getDrawableUri(context.getResources(), R.drawable.app_tile_social)));
        list.add(buildFakeApp(libraryStateHelper, context.getResources().getString(R.string.anytime_tablet_library_camera_roll_button_v2), CAMERA_ROLL_PACKAGE_NAME, getDrawableUri(context.getResources(), R.drawable.app_tile_camera_roll)));
    }

    public static App getStoreSystemApp(Context context, LibraryStateHelper libraryStateHelper) {
        return buildFakeApp(libraryStateHelper, context.getResources().getString(R.string.anytime_tablet_library_store_button_v2), STORE_PACKAGE_NAME, getDrawableUri(context.getResources(), R.drawable.app_tile_store));
    }
}
