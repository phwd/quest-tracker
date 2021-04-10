package com.oculus.panelapp.anytimeui.v2.tablet.apps.utils;

import android.content.Context;
import android.text.TextUtils;
import android.util.Log;
import com.oculus.common.logutilities.LoggingUtil;
import com.oculus.library.database.contract.LibraryDBContract;
import com.oculus.library.model.App;
import com.oculus.library.model.AppStatus;
import com.oculus.library.model.Category;
import com.oculus.library.model.GrantReason;
import com.oculus.library.model.SupportedPlatform;
import com.oculus.panelapp.anytimeui.v2.AnytimeUIPanelAppBase;
import com.oculus.panelapp.anytimeui.v2.tablet.apps.models.LibraryFilter;
import com.oculus.panelapp.anytimeui.v2.tablet.apps.models.LibraryPlatform;
import com.oculus.panelapp.anytimeui.v2.tablet.apps.models.LibrarySorter;
import com.oculus.panelapp.anytimeui.v2.tablet.apps.models.UnknownSource;
import com.oculus.tablet.logging.ClickEventButtonId;
import com.oculus.vrshell.SystemUXRoute;
import com.oculus.vrshell.util.HorizonUtil;
import com.oculus.vrshell.util.PackageUtil;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.function.Predicate;
import java.util.stream.Collectors;

public class LibraryUtils {
    private static final List<String> ALL_PLATFORMS_SYSTEM_APP_PACKAGE_NAMES = new ArrayList(Arrays.asList("com.oculus.browser", "com.oculus.tv", LibraryFakeAppUtils.HOME_PACKAGE_NAME, LibraryFakeAppUtils.STORE_PACKAGE_NAME, LibraryFakeAppUtils.SOCIAL_PACKAGE_NAME, LibraryFakeAppUtils.CAMERA_ROLL_PACKAGE_NAME));
    private static final Map<String, Integer> APPS_OVERRIDE_STATE_ORDER = new HashMap();
    private static final List<String> HIDDEN_IN_ALL_PACKAGE_NAMES = new ArrayList(Arrays.asList(LibraryFakeAppUtils.HANDS_TUTORIAL_ENTITLEMENT_PACKAGE_NAME, LibraryFakeAppUtils.FIT_AND_FOCUS_ENTITLEMENT_PACKAGE_NAME));
    public static final String LIBRARY_SYNCING_TAG = LoggingUtil.tag("LibrarySyncing");
    public static final String LIBRARY_TTI_TAG = LoggingUtil.tag("LibraryTTI");
    private static final int MAX_QUEUED_APP_COUNT_IN_INSTALLED = 5;
    private static final List<String> OCULUS_APP_PACKAGE_NAMES = new ArrayList(Arrays.asList(PackageUtil.PACKAGE_NAME_QUILL_PLAYER, PackageUtil.PACKAGE_NAME_CINEMA, "com.oculus.fitnesstracker", "com.oculus.gamingactivity", "com.oculus.venues", "com.oculus.browser", "com.oculus.tv", PackageUtil.PACKAGE_NAME_MEDIA_GALLERY, LibraryFakeAppUtils.HOME_PACKAGE_NAME, LibraryFakeAppUtils.STORE_PACKAGE_NAME, LibraryFakeAppUtils.SOCIAL_PACKAGE_NAME, LibraryFakeAppUtils.CAMERA_ROLL_PACKAGE_NAME));
    private static final String PROTOTYPE_ORGANIZATION_ID = "2307425616184313";
    public static final List<String> SYSTEM_APP_PACKAGE_NAMES = new ArrayList(Arrays.asList("com.oculus.browser", "com.oculus.tv"));
    private static final String TAG = LoggingUtil.tag(LibraryUtils.class);
    private static final List<String> TUTORIAL_PACKAGE_NAMES = new ArrayList(Arrays.asList(LibraryFakeAppUtils.HANDS_TUTORIAL_ENTITLEMENT_PACKAGE_NAME, LibraryFakeAppUtils.FIT_AND_FOCUS_ENTITLEMENT_PACKAGE_NAME, PackageUtil.PACKAGE_NAME_FIRST_STEPS, PackageUtil.PACKAGE_NAME_FIRST_STEPS_MIRAMAR, PackageUtil.PACKAGE_NAME_FIRST_CONTACT));
    public static final List<String> UNINSTALL_RESTRICTED_PACKAGE_NAMES = new ArrayList(Arrays.asList("com.oculus.fitnesstracker", "com.oculus.gamingactivity", "com.oculus.browser", "com.oculus.tv"));

    static {
        APPS_OVERRIDE_STATE_ORDER.put(PackageUtil.PACKAGE_NAME_FIRST_STEPS, 0);
        APPS_OVERRIDE_STATE_ORDER.put(PackageUtil.PACKAGE_NAME_FIRST_STEPS_MIRAMAR, 0);
        APPS_OVERRIDE_STATE_ORDER.put(LibraryFakeAppUtils.FIT_AND_FOCUS_ENTITLEMENT_PACKAGE_NAME, 1);
        APPS_OVERRIDE_STATE_ORDER.put(LibraryFakeAppUtils.HOME_PACKAGE_NAME, 2);
        APPS_OVERRIDE_STATE_ORDER.put(LibraryFakeAppUtils.STORE_PACKAGE_NAME, 3);
        APPS_OVERRIDE_STATE_ORDER.put("com.oculus.browser", 4);
        APPS_OVERRIDE_STATE_ORDER.put(LibraryFakeAppUtils.SOCIAL_PACKAGE_NAME, 5);
        APPS_OVERRIDE_STATE_ORDER.put("com.oculus.tv", 6);
        APPS_OVERRIDE_STATE_ORDER.put(LibraryFakeAppUtils.CAMERA_ROLL_PACKAGE_NAME, 7);
        APPS_OVERRIDE_STATE_ORDER.put("com.oculus.gamingactivity", 8);
        APPS_OVERRIDE_STATE_ORDER.put(PackageUtil.PACKAGE_NAME_QUILL_PLAYER, 9);
        APPS_OVERRIDE_STATE_ORDER.put("com.oculus.fitnesstracker", 10);
        APPS_OVERRIDE_STATE_ORDER.put(PackageUtil.PACKAGE_NAME_CINEMA, 11);
        APPS_OVERRIDE_STATE_ORDER.put("com.oculus.venues", 12);
        APPS_OVERRIDE_STATE_ORDER.put(LibraryFakeAppUtils.HANDS_TUTORIAL_ENTITLEMENT_PACKAGE_NAME, 13);
    }

    public static List<App> excludeHiddenApps(List<App> list) {
        return (List) list.stream().filter($$Lambda$LibraryUtils$zqjozE1mb24ElxZBYrK_d_xhMvU.INSTANCE).collect(Collectors.toList());
    }

    static /* synthetic */ boolean lambda$excludeHiddenApps$151(App app) {
        return (app.category == Category.ENVIRONMENTS || app.category == Category.INTERNAL) ? false : true;
    }

    public static List<App> excludeSystemApps(List<App> list) {
        return (List) list.stream().filter($$Lambda$LibraryUtils$14yc7V0a54DIO0ZfyXUhzWKv9go.INSTANCE).collect(Collectors.toList());
    }

    static /* synthetic */ boolean lambda$excludeSystemApps$152(App app) {
        return !SYSTEM_APP_PACKAGE_NAMES.contains(app.packageName);
    }

    public static void createNullStateOrdering(Context context, LibraryStateHelper libraryStateHelper) {
        if (libraryStateHelper.loadIsFirstTime()) {
            Log.d(TAG, "Creating null state ordering for system and fake apps");
            long currentTimeMillis = System.currentTimeMillis();
            for (String str : APPS_OVERRIDE_STATE_ORDER.keySet()) {
                long intValue = currentTimeMillis - ((long) (APPS_OVERRIDE_STATE_ORDER.get(str).intValue() * 10));
                if (LibraryFakeAppUtils.isFakeApp(str)) {
                    libraryStateHelper.saveFakeAppState(str, libraryStateHelper.loadFakeAppIsUnseenState(str, false), intValue);
                } else {
                    HorizonUtil.overrideRecentActivty(context, str, intValue);
                }
            }
        }
    }

    public static List<App> excludeIncompatibleApps(List<App> list) {
        return (List) list.stream().filter($$Lambda$LibraryUtils$UEcQBqrBcWvFuJFUlpWD6gcvqZ4.INSTANCE).collect(Collectors.toList());
    }

    static /* synthetic */ boolean lambda$excludeIncompatibleApps$153(App app) {
        return app.status != AppStatus.INCOMPATIBLE;
    }

    public static List<App> excludePrototypes(List<App> list, String str) {
        return (List) list.stream().filter(new Predicate(str) {
            /* class com.oculus.panelapp.anytimeui.v2.tablet.apps.utils.$$Lambda$LibraryUtils$NamX2_QoiNDQbGOkFiqRs5lgVU */
            private final /* synthetic */ String f$0;

            {
                this.f$0 = r1;
            }

            @Override // java.util.function.Predicate
            public final boolean test(Object obj) {
                return LibraryUtils.lambda$excludePrototypes$154(this.f$0, (App) obj);
            }
        }).collect(Collectors.toList());
    }

    static /* synthetic */ boolean lambda$excludePrototypes$154(String str, App app) {
        return !LibraryAppUtils.isAppPrototype(app, str);
    }

    public static List<App> excludeDemos(List<App> list) {
        return (List) list.stream().filter($$Lambda$LibraryUtils$dkTR5_hwmt2tC4eKgX3VEhoQVKw.INSTANCE).collect(Collectors.toList());
    }

    public static List<App> excludeTutorials(List<App> list) {
        return (List) list.stream().filter($$Lambda$LibraryUtils$Gwh1T0CioFldfiEiTQUgtoZ_SmA.INSTANCE).collect(Collectors.toList());
    }

    static /* synthetic */ boolean lambda$excludeTutorials$156(App app) {
        return !TUTORIAL_PACKAGE_NAMES.contains(app.packageName);
    }

    public static List<App> excludeInstallingApps(List<App> list) {
        return (List) list.stream().filter($$Lambda$LibraryUtils$xK4MQGZ_XSjlzXRrwsGa5Vb9SvM.INSTANCE).collect(Collectors.toList());
    }

    static /* synthetic */ boolean lambda$excludeInstallingApps$157(App app) {
        return (app.status == AppStatus.INSTALLING || app.status == AppStatus.DOWNLOADING || app.status == AppStatus.DOWNLOAD_QUEUED) ? false : true;
    }

    public static List<App> filterMigratableApps(List<App> list, String str) {
        return filterAppsByPlatform(excludeInstallingApps(filterAppsByFilter(excludeTutorials(excludeDemos(excludePrototypes(excludePrototypes(excludeIncompatibleApps(excludeSystemApps(excludeHiddenApps(list))), str), PROTOTYPE_ORGANIZATION_ID))), LibraryFilter.NOT_INSTALLED, null)), LibraryPlatform.ANDROID_6DOF, false);
    }

    public static List<App> filterAppsByPlatform(List<App> list, LibraryPlatform libraryPlatform, boolean z) {
        return (List) list.stream().filter(new Predicate(z) {
            /* class com.oculus.panelapp.anytimeui.v2.tablet.apps.utils.$$Lambda$LibraryUtils$TfVI6Q9XdQ9i0fmlQnLHksBUuA */
            private final /* synthetic */ boolean f$1;

            {
                this.f$1 = r2;
            }

            @Override // java.util.function.Predicate
            public final boolean test(Object obj) {
                return LibraryUtils.lambda$filterAppsByPlatform$158(SupportedPlatform.this, this.f$1, (App) obj);
            }
        }).collect(Collectors.toList());
    }

    static /* synthetic */ boolean lambda$filterAppsByPlatform$158(SupportedPlatform supportedPlatform, boolean z, App app) {
        return app.platform == supportedPlatform || (z && ALL_PLATFORMS_SYSTEM_APP_PACKAGE_NAMES.contains(app.packageName));
    }

    public static List<App> filterAppsByFilter(List<App> list, LibraryFilter libraryFilter, String str) {
        switch (libraryFilter) {
            case OCULUS_APPS:
                return (List) list.stream().filter($$Lambda$LibraryUtils$CzqYOlBvi9qKY3QC2f6RQ_AhnE.INSTANCE).collect(Collectors.toList());
            case INSTALLED:
                List<App> list2 = (List) list.stream().filter($$Lambda$LibraryUtils$YQfJxekDwNVmMLW5InyZZaPgjE.INSTANCE).collect(Collectors.toList());
                int i = 0;
                for (int i2 = 0; i2 < list.size() && i < 5; i2++) {
                    App app = list.get(i2);
                    if (app.status == AppStatus.DOWNLOAD_QUEUED) {
                        list2.add(app);
                        i++;
                    }
                }
                return list2;
            case UPDATES:
                return (List) list.stream().filter($$Lambda$IeWPUv6OibevvY26RpuIt7XZt1Q.INSTANCE).collect(Collectors.toList());
            case NOT_INSTALLED:
                return (List) list.stream().filter($$Lambda$LibraryUtils$BhJfsNsK9BPq2UXJ_nxslHHVt8.INSTANCE).collect(Collectors.toList());
            case DEMOS:
                return (List) list.stream().filter($$Lambda$LibraryUtils$a09BOhp7KgnxVzP6PrVcJxuehWM.INSTANCE).collect(Collectors.toList());
            case TUTORIALS:
                return (List) list.stream().filter($$Lambda$LibraryUtils$JP9fwH88Hp_vzZzWZC1pD_7pjI.INSTANCE).collect(Collectors.toList());
            case UNKNOWN_SOURCES:
                return new ArrayList();
            case PROTOTYPES:
                return (List) list.stream().filter(new Predicate(str) {
                    /* class com.oculus.panelapp.anytimeui.v2.tablet.apps.utils.$$Lambda$LibraryUtils$QrKmQuv8geaigvUGX7Cb63DT8M */
                    private final /* synthetic */ String f$0;

                    {
                        this.f$0 = r1;
                    }

                    @Override // java.util.function.Predicate
                    public final boolean test(Object obj) {
                        return LibraryAppUtils.isAppPrototype((App) obj, this.f$0);
                    }
                }).collect(Collectors.toList());
            case SHARED:
                return (List) list.stream().filter($$Lambda$LibraryUtils$GNXjhI5Kin_MSICu3ECYM0KHM_s.INSTANCE).collect(Collectors.toList());
            default:
                return (List) list.stream().filter($$Lambda$LibraryUtils$D_23biU3x0ZclLlKI_0EdCSAFkE.INSTANCE).collect(Collectors.toList());
        }
    }

    static /* synthetic */ boolean lambda$filterAppsByFilter$160(App app) {
        return app.status == AppStatus.INSTALLED || app.status == AppStatus.INSTALL_AVAILABLE || app.status == AppStatus.DOWNLOADING || app.status == AppStatus.INSTALLING || app.status == AppStatus.UNINSTALLING;
    }

    static /* synthetic */ boolean lambda$filterAppsByFilter$161(App app) {
        return app.versionCode == LibraryDBContract.VERSION_NOT_INSTALLED && (app.status == AppStatus.ENTITLED || app.status == AppStatus.INCOMPATIBLE || app.status == AppStatus.DOWNLOADING || app.status == AppStatus.DOWNLOAD_QUEUED || app.status == AppStatus.INSTALLING);
    }

    static /* synthetic */ boolean lambda$filterAppsByFilter$162(App app) {
        return !TextUtils.isEmpty(app.isDemoOf);
    }

    static /* synthetic */ boolean lambda$filterAppsByFilter$165(App app) {
        return app.grantReason == GrantReason.SHARED_ON_DEVICE;
    }

    static /* synthetic */ boolean lambda$filterAppsByFilter$166(App app) {
        return !HIDDEN_IN_ALL_PACKAGE_NAMES.contains(app.packageName);
    }

    public static boolean appHasPendingUpdate(App app) {
        return app != null && app.versionCode != LibraryDBContract.VERSION_NOT_INSTALLED && app.versionCode < app.latestVersionCode && (app.status == AppStatus.INSTALLED || app.status == AppStatus.DOWNLOADING || app.status == AppStatus.DOWNLOAD_QUEUED || app.status == AppStatus.INSTALLING);
    }

    /* access modifiers changed from: package-private */
    /* renamed from: com.oculus.panelapp.anytimeui.v2.tablet.apps.utils.LibraryUtils$1  reason: invalid class name */
    public static /* synthetic */ class AnonymousClass1 {
        static final /* synthetic */ int[] $SwitchMap$com$oculus$panelapp$anytimeui$v2$tablet$apps$models$LibrarySorter = new int[LibrarySorter.values().length];

        /* JADX WARNING: Can't wrap try/catch for region: R(26:0|(2:1|2)|3|(2:5|6)|7|(2:9|10)|11|(2:13|14)|15|17|18|19|20|21|22|23|24|25|26|27|28|29|30|31|32|(3:33|34|36)) */
        /* JADX WARNING: Can't wrap try/catch for region: R(28:0|1|2|3|(2:5|6)|7|(2:9|10)|11|13|14|15|17|18|19|20|21|22|23|24|25|26|27|28|29|30|31|32|(3:33|34|36)) */
        /* JADX WARNING: Can't wrap try/catch for region: R(31:0|1|2|3|5|6|7|(2:9|10)|11|13|14|15|17|18|19|20|21|22|23|24|25|26|27|28|29|30|31|32|33|34|36) */
        /* JADX WARNING: Failed to process nested try/catch */
        /* JADX WARNING: Missing exception handler attribute for start block: B:19:0x0048 */
        /* JADX WARNING: Missing exception handler attribute for start block: B:21:0x0052 */
        /* JADX WARNING: Missing exception handler attribute for start block: B:23:0x005c */
        /* JADX WARNING: Missing exception handler attribute for start block: B:25:0x0066 */
        /* JADX WARNING: Missing exception handler attribute for start block: B:27:0x0071 */
        /* JADX WARNING: Missing exception handler attribute for start block: B:29:0x007c */
        /* JADX WARNING: Missing exception handler attribute for start block: B:31:0x0087 */
        /* JADX WARNING: Missing exception handler attribute for start block: B:33:0x0093 */
        static {
            /*
            // Method dump skipped, instructions count: 160
            */
            throw new UnsupportedOperationException("Method not decompiled: com.oculus.panelapp.anytimeui.v2.tablet.apps.utils.LibraryUtils.AnonymousClass1.<clinit>():void");
        }
    }

    public static void sortAppsBySorter(List<App> list, LibrarySorter librarySorter) {
        int i = AnonymousClass1.$SwitchMap$com$oculus$panelapp$anytimeui$v2$tablet$apps$models$LibrarySorter[librarySorter.ordinal()];
        if (i == 1 || i == 2) {
            Collections.sort(list, $$Lambda$LibraryUtils$vqVjSDmePdyOOwXvXmPNTsnmO4Q.INSTANCE);
            if (librarySorter == LibrarySorter.LEAST_RECENT) {
                Collections.reverse(list);
            }
        } else if (i == 3 || i == 4) {
            Collections.sort(list, $$Lambda$LibraryUtils$PwOE78WBBuf15pIHo8Li9gkivb8.INSTANCE);
            if (librarySorter == LibrarySorter.Z_TO_A) {
                Collections.reverse(list);
            }
        }
    }

    static /* synthetic */ int lambda$sortAppsBySorter$167(App app, App app2) {
        long j = app.recentActivityMs;
        long j2 = app2.recentActivityMs;
        if (String.valueOf(j).length() == 10) {
            j *= 1000;
        }
        if (String.valueOf(j2).length() == 10) {
            j2 *= 1000;
        }
        return Long.compare(j2, j);
    }

    public static void sortUnknownSourcesBySorter(List<UnknownSource> list, LibrarySorter librarySorter) {
        int i = AnonymousClass1.$SwitchMap$com$oculus$panelapp$anytimeui$v2$tablet$apps$models$LibrarySorter[librarySorter.ordinal()];
        if (i == 1 || i == 2) {
            Collections.sort(list, $$Lambda$LibraryUtils$dIFBgpJaNW7JPB0R3sI_AFq8fw.INSTANCE);
            if (librarySorter == LibrarySorter.LEAST_RECENT) {
                Collections.reverse(list);
            }
        } else if (i == 3 || i == 4) {
            Collections.sort(list, $$Lambda$LibraryUtils$GvheKIp0vySKhcJabvVu8tNJ4hg.INSTANCE);
            if (librarySorter == LibrarySorter.Z_TO_A) {
                Collections.reverse(list);
            }
        }
    }

    public static void openUriWithBrowser(String str, AnytimeUIPanelAppBase anytimeUIPanelAppBase) {
        Log.d(TAG, String.format("Launching %s", str));
        anytimeUIPanelAppBase.actionNavigate(SystemUXRoute.DEFAULT_BROWSER.path(), str);
        anytimeUIPanelAppBase.logButtonClick(ClickEventButtonId.AUIV2_LIBRARY_BROWSER);
    }

    public static void promoteAvailableApps(List<App> list, boolean z, boolean z2) {
        ArrayList arrayList = new ArrayList();
        ArrayList arrayList2 = new ArrayList();
        for (App app : list) {
            if (LibraryAppUtils.isAppAvailable(z, z2, app)) {
                arrayList.add(app);
            } else {
                arrayList2.add(app);
            }
        }
        list.clear();
        list.addAll(arrayList);
        list.addAll(arrayList2);
    }

    public static boolean appsContainPrototypes(Collection<App> collection, LibraryPlatform libraryPlatform, String str) {
        return ((List) collection.stream().filter(new Predicate(str, libraryPlatform == LibraryPlatform.ANDROID_6DOF ? SupportedPlatform.ANDROID_6DOF : SupportedPlatform.ANDROID) {
            /* class com.oculus.panelapp.anytimeui.v2.tablet.apps.utils.$$Lambda$LibraryUtils$QNfAT8txz_X0O7GZZq57OAcxJM */
            private final /* synthetic */ String f$0;
            private final /* synthetic */ SupportedPlatform f$1;

            {
                this.f$0 = r1;
                this.f$1 = r2;
            }

            @Override // java.util.function.Predicate
            public final boolean test(Object obj) {
                return LibraryUtils.lambda$appsContainPrototypes$171(this.f$0, this.f$1, (App) obj);
            }
        }).collect(Collectors.toList())).size() > 0;
    }

    static /* synthetic */ boolean lambda$appsContainPrototypes$171(String str, SupportedPlatform supportedPlatform, App app) {
        return LibraryAppUtils.isAppPrototype(app, str) && app.platform == supportedPlatform;
    }
}
