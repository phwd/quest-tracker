package com.oculus.defaultapps;

import X.AnonymousClass006;
import X.AnonymousClass0D4;
import X.AnonymousClass0DC;
import X.AnonymousClass0DD;
import X.AnonymousClass0J2;
import X.AnonymousClass0NO;
import X.AnonymousClass0QC;
import android.app.job.JobInfo;
import android.app.job.JobParameters;
import android.app.job.JobScheduler;
import android.app.job.JobService;
import android.content.ComponentName;
import android.content.Context;
import android.text.TextUtils;
import com.google.common.base.Optional;
import com.oculus.appmanager.info.model.RequestOrigin;
import com.oculus.coreapps.CoreApp;
import com.oculus.coreapps.CoreAppManager;
import com.oculus.defaultapps.net.DefaultAppsRequest;
import com.oculus.defaultapps.net.DefaultAppsResponse;
import com.oculus.device.DeviceType;
import com.oculus.errorreporting.interfaces.IErrorReporter;
import com.oculus.executors.OculusThreadExecutor;
import com.oculus.horizon.api.common.Item;
import com.oculus.horizon.api.common.MinimumAppVersion;
import com.oculus.nuxpreferencesapi.OVRNuxPreferences;
import com.oculus.oculustestsettings.OculusTestSettings;
import com.oculus.util.constants.JobSchedulerIds;
import com.oculus.util.thread.ThreadUtils;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.Iterator;
import java.util.List;
import java.util.Locale;
import java.util.Set;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.atomic.AtomicBoolean;

public class DefaultAppsSetupJobService extends JobService {
    public static final String ERROR_COMPLETION_SET = "standalone_setup_completion_already_set";
    public static final String ERROR_SETUP_EXCEPTION = "standalone_setup_exception";
    public static final int HIGH_PRI_APPS_MAX_RESCHEDULES = 5;
    public static final int HIGH_PRI_APPS_RESCHEDULE_MIN_LATENCY_SECONDS = 10;
    public static final int MIN_LATENCY_BETWEEN_SETUP_INSTALL_JOBS_SECONDS = 10;
    public static final int SETUP_INSTALLS_BACK_OFF_LATENCY_MINUTES = 5;
    public static final int SETUP_INSTALLS_MAX_RESCHEDULES = 3;
    public static final String TAG = "DefaultAppsSetupJobService";
    public AnonymousClass0QC _UL_mInjectionContext;
    public boolean mCompleted = false;
    public boolean mIsHighPriAppsDownloadInProgress;
    public final AtomicBoolean mIsJobRescheduled = new AtomicBoolean(false);

    public static void A04(DefaultAppsSetupJobService defaultAppsSetupJobService, Context context) {
        ((DefaultAppsPrefs) AnonymousClass0J2.A03(3, 0, defaultAppsSetupJobService._UL_mInjectionContext)).A00(0);
        A01(context, A00(context).setBackoffCriteria(TimeUnit.MINUTES.toMillis(5), 1).setMinimumLatency(TimeUnit.SECONDS.toMillis(10)).build());
    }

    private void A06(OVRNuxPreferences.Status status) {
        String str;
        String str2;
        if (((DefaultAppsPrefs) AnonymousClass0J2.A03(3, 0, this._UL_mInjectionContext)).mPrefs.getBoolean(DefaultAppsPrefs.HIGH_PRIORITY_APPS_SETUP_COMPLETE, false)) {
            str = TAG;
            str2 = "High Priority Apps Download is already complete";
        } else if (status == OVRNuxPreferences.Status.UNSET) {
            str = TAG;
            str2 = "Trying to set the High Priority Apps Download Status to UNSET";
        } else if (status != OVRNuxPreferences.Status.FAILED || OVRNuxPreferences.Status.values()[((DefaultAppsPrefs) AnonymousClass0J2.A03(2, 0, ((DefaultAppsSetupManager) AnonymousClass0J2.A03(4, 436, this._UL_mInjectionContext))._UL_mInjectionContext)).mPrefs.getInt(DefaultAppsPrefs.HIGH_PRIORITY_APPS_DOWNLOAD_STATUS, 0)] != OVRNuxPreferences.Status.COMPLETED) {
            ((DefaultAppsSetupManager) AnonymousClass0J2.A03(4, 436, this._UL_mInjectionContext)).A03(status);
            return;
        } else {
            return;
        }
        AnonymousClass0NO.A09(str, str2);
    }

    /* renamed from: com.oculus.defaultapps.DefaultAppsSetupJobService$2  reason: invalid class name */
    public static /* synthetic */ class AnonymousClass2 {
        public static final /* synthetic */ int[] $SwitchMap$com$oculus$coreapps$CoreApp$Status;

        /* JADX WARNING: Can't wrap try/catch for region: R(10:0|1|2|3|4|5|6|7|8|10) */
        /* JADX WARNING: Code restructure failed: missing block: B:11:?, code lost:
            return;
         */
        /* JADX WARNING: Failed to process nested try/catch */
        /* JADX WARNING: Missing exception handler attribute for start block: B:3:0x0012 */
        /* JADX WARNING: Missing exception handler attribute for start block: B:5:0x001b */
        /* JADX WARNING: Missing exception handler attribute for start block: B:7:0x0024 */
        static {
            /*
                com.oculus.coreapps.CoreApp$Status[] r0 = com.oculus.coreapps.CoreApp.Status.values()
                int r0 = r0.length
                int[] r2 = new int[r0]
                com.oculus.defaultapps.DefaultAppsSetupJobService.AnonymousClass2.$SwitchMap$com$oculus$coreapps$CoreApp$Status = r2
                com.oculus.coreapps.CoreApp$Status r0 = com.oculus.coreapps.CoreApp.Status.INSTALL_SUCCESS     // Catch:{ NoSuchFieldError -> 0x0012 }
                int r1 = r0.ordinal()     // Catch:{ NoSuchFieldError -> 0x0012 }
                r0 = 1
                r2[r1] = r0     // Catch:{ NoSuchFieldError -> 0x0012 }
            L_0x0012:
                com.oculus.coreapps.CoreApp$Status r0 = com.oculus.coreapps.CoreApp.Status.UP_TO_DATE     // Catch:{ NoSuchFieldError -> 0x001b }
                int r1 = r0.ordinal()     // Catch:{ NoSuchFieldError -> 0x001b }
                r0 = 2
                r2[r1] = r0     // Catch:{ NoSuchFieldError -> 0x001b }
            L_0x001b:
                com.oculus.coreapps.CoreApp$Status r0 = com.oculus.coreapps.CoreApp.Status.DOWNLOAD_FAILED     // Catch:{ NoSuchFieldError -> 0x0024 }
                int r1 = r0.ordinal()     // Catch:{ NoSuchFieldError -> 0x0024 }
                r0 = 3
                r2[r1] = r0     // Catch:{ NoSuchFieldError -> 0x0024 }
            L_0x0024:
                com.oculus.coreapps.CoreApp$Status r0 = com.oculus.coreapps.CoreApp.Status.INSTALL_FAILED     // Catch:{ NoSuchFieldError -> 0x002d }
                int r1 = r0.ordinal()     // Catch:{ NoSuchFieldError -> 0x002d }
                r0 = 4
                r2[r1] = r0     // Catch:{ NoSuchFieldError -> 0x002d }
            L_0x002d:
                return
            */
            throw new UnsupportedOperationException("Method not decompiled: com.oculus.defaultapps.DefaultAppsSetupJobService.AnonymousClass2.<clinit>():void");
        }
    }

    public static JobInfo.Builder A00(Context context) {
        return new JobInfo.Builder(JobSchedulerIds.STANDALONE_SETUP, new ComponentName(context, DefaultAppsSetupJobService.class.getName())).setRequiredNetworkType(1).setPersisted(false);
    }

    public static void A01(Context context, JobInfo jobInfo) {
        int schedule = ((JobScheduler) context.getSystemService("jobscheduler")).schedule(jobInfo);
        if (schedule != 1) {
            AnonymousClass0NO.A0E(TAG, "Failed to schedule standalone setup job. Return: %d", Integer.valueOf(schedule));
        }
    }

    public static void A02(DefaultAppsSetupJobService defaultAppsSetupJobService, JobParameters jobParameters) {
        defaultAppsSetupJobService.mIsJobRescheduled.set(true);
        int i = ((DefaultAppsPrefs) AnonymousClass0J2.A03(3, 0, defaultAppsSetupJobService._UL_mInjectionContext)).mPrefs.getInt(DefaultAppsPrefs.RESCHEDULE_ATTEMPT, 0);
        int i2 = 3;
        if (defaultAppsSetupJobService.mIsHighPriAppsDownloadInProgress) {
            i2 = 5;
        }
        boolean z = false;
        if (i >= i2) {
            z = true;
        }
        if (z) {
            AnonymousClass0NO.A09(TAG, "Maximum reschedule attempts reached. Bailing.");
            if (defaultAppsSetupJobService.mIsHighPriAppsDownloadInProgress) {
                defaultAppsSetupJobService.A06(OVRNuxPreferences.Status.FAILED);
                ((DefaultAppsPrefs) AnonymousClass0J2.A03(3, 0, defaultAppsSetupJobService._UL_mInjectionContext)).A02(true);
                A04(defaultAppsSetupJobService, defaultAppsSetupJobService);
            }
        } else {
            ((DefaultAppsPrefs) AnonymousClass0J2.A03(3, 0, defaultAppsSetupJobService._UL_mInjectionContext)).A00(i + 1);
            if (defaultAppsSetupJobService.mIsHighPriAppsDownloadInProgress) {
                A01(defaultAppsSetupJobService, A00(defaultAppsSetupJobService).setMinimumLatency(TimeUnit.SECONDS.toMillis(10)).build());
            } else {
                A03(defaultAppsSetupJobService, jobParameters, true);
                return;
            }
        }
        A03(defaultAppsSetupJobService, jobParameters, false);
    }

    /* JADX WARNING: Code restructure failed: missing block: B:37:0x00fe, code lost:
        if (r7 == false) goto L_0x0100;
     */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public static void A05(com.oculus.defaultapps.DefaultAppsSetupJobService r15, com.oculus.coreapps.CoreApp[] r16, android.app.job.JobParameters r17) {
        /*
        // Method dump skipped, instructions count: 498
        */
        throw new UnsupportedOperationException("Method not decompiled: com.oculus.defaultapps.DefaultAppsSetupJobService.A05(com.oculus.defaultapps.DefaultAppsSetupJobService, com.oculus.coreapps.CoreApp[], android.app.job.JobParameters):void");
    }

    public static void A07(StringBuilder sb, Object... objArr) {
        Object[] objArr2;
        boolean z = true;
        for (Object obj : objArr) {
            if (z) {
                z = false;
            } else {
                sb.append(",");
            }
            if (obj instanceof Collection) {
                objArr2 = ((Collection) obj).toArray();
            } else if (obj instanceof Object[]) {
                objArr2 = (Object[]) obj;
            } else {
                sb.append(obj.toString());
            }
            A07(sb, objArr2);
        }
    }

    public final boolean onStartJob(final JobParameters jobParameters) {
        this.mIsJobRescheduled.set(false);
        this.mIsHighPriAppsDownloadInProgress = !((DefaultAppsPrefs) AnonymousClass0J2.A03(3, 0, this._UL_mInjectionContext)).mPrefs.getBoolean(DefaultAppsPrefs.HIGH_PRIORITY_APPS_SETUP_COMPLETE, false);
        OculusThreadExecutor.A00().execute(new Runnable() {
            /* class com.oculus.defaultapps.DefaultAppsSetupJobService.AnonymousClass1 */

            public final void run() {
                AnonymousClass0DC<TResult> r2;
                Set<String> stringSet;
                DefaultAppsSetupJobService defaultAppsSetupJobService = DefaultAppsSetupJobService.this;
                DefaultAppsSetupManager defaultAppsSetupManager = (DefaultAppsSetupManager) AnonymousClass0J2.A03(0, 436, defaultAppsSetupJobService._UL_mInjectionContext);
                boolean z = defaultAppsSetupJobService.mIsHighPriAppsDownloadInProgress;
                ((ThreadUtils) AnonymousClass0J2.A03(4, 583, defaultAppsSetupManager._UL_mInjectionContext)).A05();
                CoreAppManager coreAppManager = (CoreAppManager) AnonymousClass0J2.A03(0, 472, defaultAppsSetupManager._UL_mInjectionContext);
                synchronized (coreAppManager) {
                    AnonymousClass0DD<CoreApp[]> r0 = coreAppManager.mRunningTaskCompletionSource;
                    if (r0 == null) {
                        r2 = null;
                    } else {
                        r2 = r0.A00;
                    }
                }
                if (!((CoreAppManager) AnonymousClass0J2.A03(0, 472, defaultAppsSetupManager._UL_mInjectionContext)).A06() || r2 == null) {
                    DefaultAppsResponse A00 = ((DefaultAppsMethods) AnonymousClass0J2.A03(3, 124, defaultAppsSetupManager._UL_mInjectionContext)).A00(new DefaultAppsRequest(DeviceType.current().hmdType, z));
                    if (z && DeviceType.is6DOF() && (stringSet = ((DefaultAppsPrefs) AnonymousClass0J2.A03(2, 0, defaultAppsSetupManager._UL_mInjectionContext)).mPrefs.getStringSet(DefaultAppsPrefs.MODIFIABLE_HIGH_PRI_APP_LIST, null)) != null) {
                        Iterator<Item> it = A00.node.default_applications.iterator();
                        while (it.hasNext()) {
                            Item next = it.next();
                            if (!stringSet.contains(next.getPackageName())) {
                                next.getPackageName();
                                it.remove();
                            }
                        }
                    }
                    r2 = AnonymousClass0DC.A04(A00).A0A(new AnonymousClass0D4<DefaultAppsResponse, AnonymousClass0DC<CoreApp[]>>(z) {
                        /* class com.oculus.defaultapps.DefaultAppsSetupManager.AnonymousClass1 */
                        public final /* synthetic */ boolean val$isHighPriorityAppsList;

                        {
                            this.val$isHighPriorityAppsList = r2;
                        }

                        /* Return type fixed from 'java.lang.Object' to match base method */
                        /* JADX DEBUG: Method arguments types fixed to match base method, original types: [X.0DC] */
                        @Override // X.AnonymousClass0D4
                        public final /* bridge */ /* synthetic */ AnonymousClass0DC<CoreApp[]> then(AnonymousClass0DC<DefaultAppsResponse> r14) throws Exception {
                            final String str;
                            String str2;
                            final AnonymousClass0DD r3 = new AnonymousClass0DD();
                            if (r14.A0K()) {
                                Exception A0F = r14.A0F();
                                if (this.val$isHighPriorityAppsList) {
                                    str2 = "high Priority App List";
                                } else {
                                    str2 = "default standalone App list";
                                }
                                ((IErrorReporter) AnonymousClass0J2.A03(1, 428, DefaultAppsSetupManager.this._UL_mInjectionContext)).A97(DefaultAppsSetupManager.TAG, String.format(Locale.US, "Error while fetching %s", str2), A0F);
                                r3.A01(A0F);
                            } else {
                                CoreAppManager coreAppManager = (CoreAppManager) AnonymousClass0J2.A03(0, 472, DefaultAppsSetupManager.this._UL_mInjectionContext);
                                AnonymousClass1 r2 = new CoreAppManager.CoreAppsListener() {
                                    /* class com.oculus.defaultapps.DefaultAppsSetupManager.AnonymousClass1.AnonymousClass1 */

                                    @Override // com.oculus.coreapps.CoreAppManager.CoreAppsListener
                                    public final void A5t(CoreApp[] coreAppArr) {
                                        CoreAppManager coreAppManager = (CoreAppManager) AnonymousClass0J2.A03(0, 472, DefaultAppsSetupManager.this._UL_mInjectionContext);
                                        synchronized (coreAppManager) {
                                            synchronized (coreAppManager.mCoreAppsListeners) {
                                                coreAppManager.mCoreAppsListeners.remove(this);
                                            }
                                        }
                                        ((CoreAppManager) AnonymousClass0J2.A03(0, 472, DefaultAppsSetupManager.this._UL_mInjectionContext)).A05(Collections.emptyList(), true);
                                        r3.A02(coreAppArr);
                                    }
                                };
                                synchronized (coreAppManager) {
                                    synchronized (coreAppManager.mCoreAppsListeners) {
                                        coreAppManager.mCoreAppsListeners.add(r2);
                                    }
                                }
                                List<Item> list = r14.A0G().node.default_applications;
                                if (((OculusTestSettings) AnonymousClass0J2.A03(6, 330, DefaultAppsSetupManager.this._UL_mInjectionContext)).A39(OculusTestSettings.SettingsKey.FAIL_MNUX_UPDATE)) {
                                    for (Item item : list) {
                                        Item.AndroidBinary androidBinary = item.latest_supported_binary;
                                        if (androidBinary != null) {
                                            androidBinary.uri = AnonymousClass006.A05(androidBinary.uri, "?");
                                        }
                                    }
                                }
                                DefaultAppsSetupManager defaultAppsSetupManager = DefaultAppsSetupManager.this;
                                Iterator<Item> it = list.iterator();
                                while (it.hasNext()) {
                                    Item next = it.next();
                                    if (next.latest_supported_binary == null) {
                                        ((IErrorReporter) AnonymousClass0J2.A03(1, 428, defaultAppsSetupManager._UL_mInjectionContext)).A96(DefaultAppsSetupManager.TAG, String.format(Locale.US, "Application ID %s is unsupported on this device!", next.id));
                                        it.remove();
                                    }
                                }
                                DefaultAppsSetupManager defaultAppsSetupManager2 = DefaultAppsSetupManager.this;
                                CoreAppManager coreAppManager2 = (CoreAppManager) AnonymousClass0J2.A03(0, 472, defaultAppsSetupManager2._UL_mInjectionContext);
                                ArrayList<MinimumAppVersion.MinimumAppVersionEntry> arrayList = new ArrayList();
                                for (Item item2 : list) {
                                    if (item2.latest_supported_binary != null) {
                                        MinimumAppVersion.MinimumAppVersionEntry minimumAppVersionEntry = new MinimumAppVersion.MinimumAppVersionEntry();
                                        minimumAppVersionEntry.package_name = item2.getPackageName();
                                        Item.AndroidBinary androidBinary2 = item2.latest_supported_binary;
                                        minimumAppVersionEntry.download_uri = androidBinary2.uri;
                                        int i = androidBinary2.version_code;
                                        minimumAppVersionEntry.download_version = i;
                                        minimumAppVersionEntry.display_name = item2.display_name;
                                        minimumAppVersionEntry.minimum_version = i;
                                        if (DeviceType.is6DOF()) {
                                            String externalSignaturesAsJson = item2.getExternalSignaturesAsJson();
                                            if (TextUtils.isEmpty(externalSignaturesAsJson)) {
                                                ((IErrorReporter) AnonymousClass0J2.A03(1, 428, defaultAppsSetupManager2._UL_mInjectionContext)).A96(DefaultAppsSetupManager.TAG, String.format("No external signature found for %s", minimumAppVersionEntry.package_name));
                                            } else {
                                                minimumAppVersionEntry.external_signature = Optional.of(externalSignaturesAsJson);
                                            }
                                        }
                                        Item.ObbBinary obbBinary = item2.latest_supported_binary.obb_binary;
                                        if (obbBinary != null && !TextUtils.isEmpty(obbBinary.uri)) {
                                            Item.ObbBinary obbBinary2 = item2.latest_supported_binary.obb_binary;
                                            minimumAppVersionEntry.obbUri = Optional.of(obbBinary2.uri);
                                            minimumAppVersionEntry.obbSize = Optional.of(Long.valueOf(obbBinary2.size));
                                        }
                                        long j = item2.latest_supported_binary.size;
                                        if (j > 0) {
                                            minimumAppVersionEntry.size = Optional.of(Long.valueOf(j));
                                        }
                                        arrayList.add(minimumAppVersionEntry);
                                    } else {
                                        throw null;
                                    }
                                }
                                Set<String> stringSet = ((DefaultAppsPrefs) AnonymousClass0J2.A03(2, 0, defaultAppsSetupManager2._UL_mInjectionContext)).mPrefs.getStringSet(DefaultAppsPrefs.SETUP_COMPLETED_PACKAGES, Collections.EMPTY_SET);
                                ArrayList arrayList2 = new ArrayList();
                                for (MinimumAppVersion.MinimumAppVersionEntry minimumAppVersionEntry2 : arrayList) {
                                    if (!stringSet.contains(minimumAppVersionEntry2.package_name)) {
                                        arrayList2.add(minimumAppVersionEntry2);
                                    }
                                }
                                coreAppManager2.A05(arrayList2, true);
                                CoreAppManager coreAppManager3 = (CoreAppManager) AnonymousClass0J2.A03(0, 472, DefaultAppsSetupManager.this._UL_mInjectionContext);
                                CoreAppManager.InstallOrder installOrder = CoreAppManager.InstallOrder.SEQUENTIAL;
                                synchronized (coreAppManager3) {
                                    coreAppManager3.mInstallOrder = installOrder;
                                }
                                CoreAppManager coreAppManager4 = (CoreAppManager) AnonymousClass0J2.A03(0, 472, DefaultAppsSetupManager.this._UL_mInjectionContext);
                                RequestOrigin requestOrigin = RequestOrigin.PACIFIC_SETUP;
                                synchronized (coreAppManager4) {
                                    coreAppManager4.mRequestOrigin = requestOrigin;
                                }
                                if (this.val$isHighPriorityAppsList) {
                                    str = "high_pri_apps_setup";
                                } else {
                                    str = "default_apps_setup";
                                }
                                OculusThreadExecutor A00 = OculusThreadExecutor.A00();
                                A00.mUiThreadExecutor.execute(new Runnable() {
                                    /* class com.oculus.defaultapps.DefaultAppsSetupManager.AnonymousClass1.AnonymousClass2 */

                                    /* JADX WARNING: Code restructure failed: missing block: B:43:0x0104, code lost:
                                        if (r1 < r3) goto L_0x0106;
                                     */
                                    /* Code decompiled incorrectly, please refer to instructions dump. */
                                    public final void run() {
                                        /*
                                        // Method dump skipped, instructions count: 315
                                        */
                                        throw new UnsupportedOperationException("Method not decompiled: com.oculus.defaultapps.DefaultAppsSetupManager.AnonymousClass1.AnonymousClass2.run():void");
                                    }
                                });
                            }
                            return r3.A00;
                        }
                    });
                }
                r2.A0A(new AnonymousClass0D4<CoreApp[], AnonymousClass0DC<Void>>() {
                    /* class com.oculus.defaultapps.DefaultAppsSetupJobService.AnonymousClass1.AnonymousClass1 */

                    /* Return type fixed from 'java.lang.Object' to match base method */
                    /* JADX DEBUG: Method arguments types fixed to match base method, original types: [X.0DC] */
                    /* JADX WARNING: Code restructure failed: missing block: B:11:0x001f, code lost:
                        if (r7.A0F() == null) goto L_0x0057;
                     */
                    /* JADX WARNING: Code restructure failed: missing block: B:12:0x0021, code lost:
                        X.AnonymousClass0NO.A0H(com.oculus.defaultapps.DefaultAppsSetupJobService.TAG, r7.A0F(), "Failed to run setup job");
                        r4 = r6.this$1.this$0;
                     */
                    /* JADX WARNING: Code restructure failed: missing block: B:13:0x0032, code lost:
                        if (r4.mIsHighPriAppsDownloadInProgress == false) goto L_0x0054;
                     */
                    /* JADX WARNING: Code restructure failed: missing block: B:14:0x0034, code lost:
                        r3 = "Error while running High priority Apps Install";
                     */
                    /* JADX WARNING: Code restructure failed: missing block: B:15:0x0036, code lost:
                        ((com.oculus.errorreporting.interfaces.IErrorReporter) X.AnonymousClass0J2.A03(2, 428, r4._UL_mInjectionContext)).A97(com.oculus.defaultapps.DefaultAppsSetupJobService.ERROR_SETUP_EXCEPTION, r3, r7.A0F());
                        r0 = r6.this$1;
                        com.oculus.defaultapps.DefaultAppsSetupJobService.A02(r0.this$0, r4);
                     */
                    /* JADX WARNING: Code restructure failed: missing block: B:16:0x0053, code lost:
                        return null;
                     */
                    /* JADX WARNING: Code restructure failed: missing block: B:17:0x0054, code lost:
                        r3 = "Error while running setup-installs";
                     */
                    /* JADX WARNING: Code restructure failed: missing block: B:18:0x0057, code lost:
                        com.oculus.defaultapps.DefaultAppsSetupJobService.A05(r6.this$1.this$0, r7.A0G(), r4);
                        r3 = r6.this$1.this$0;
                     */
                    /* JADX WARNING: Code restructure failed: missing block: B:19:0x006e, code lost:
                        if (r3.mIsHighPriAppsDownloadInProgress == false) goto L_0x0094;
                     */
                    /* JADX WARNING: Code restructure failed: missing block: B:21:0x0082, code lost:
                        if (((com.oculus.defaultapps.DefaultAppsPrefs) X.AnonymousClass0J2.A03(3, 0, r3._UL_mInjectionContext)).mPrefs.getBoolean(com.oculus.defaultapps.DefaultAppsPrefs.HIGH_PRIORITY_APPS_SETUP_COMPLETE, false) == false) goto L_0x0094;
                     */
                    /* JADX WARNING: Code restructure failed: missing block: B:22:0x0084, code lost:
                        r6.this$1.this$0.mIsJobRescheduled.set(true);
                        r0 = r6.this$1.this$0;
                        com.oculus.defaultapps.DefaultAppsSetupJobService.A04(r0, r0);
                     */
                    /* JADX WARNING: Code restructure failed: missing block: B:23:0x0094, code lost:
                        r0 = r6.this$1;
                        com.oculus.defaultapps.DefaultAppsSetupJobService.A03(r0.this$0, r4, false);
                     */
                    /* JADX WARNING: Code restructure failed: missing block: B:24:0x009e, code lost:
                        return null;
                     */
                    @Override // X.AnonymousClass0D4
                    /* Code decompiled incorrectly, please refer to instructions dump. */
                    public final X.AnonymousClass0DC<java.lang.Void> then(X.AnonymousClass0DC<com.oculus.coreapps.CoreApp[]> r7) throws java.lang.Exception {
                        /*
                        // Method dump skipped, instructions count: 162
                        */
                        throw new UnsupportedOperationException("Method not decompiled: com.oculus.defaultapps.DefaultAppsSetupJobService.AnonymousClass1.AnonymousClass1.then(X.0DC):java.lang.Object");
                    }
                });
            }
        });
        return true;
    }

    public final boolean onStopJob(JobParameters jobParameters) {
        if (!this.mIsJobRescheduled.get() && !this.mCompleted) {
            int i = ((DefaultAppsPrefs) AnonymousClass0J2.A03(3, 0, this._UL_mInjectionContext)).mPrefs.getInt(DefaultAppsPrefs.RESCHEDULE_ATTEMPT, 0);
            boolean z = this.mIsHighPriAppsDownloadInProgress;
            int i2 = 3;
            if (z) {
                i2 = 5;
            }
            boolean z2 = false;
            if (i >= i2) {
                z2 = true;
            }
            if (!z2) {
                ((DefaultAppsPrefs) AnonymousClass0J2.A03(3, 0, this._UL_mInjectionContext)).A00(i);
                if (!this.mIsHighPriAppsDownloadInProgress) {
                    return true;
                }
                A01(this, A00(this).setMinimumLatency(TimeUnit.SECONDS.toMillis(10)).build());
                return false;
            } else if (z) {
                A06(OVRNuxPreferences.Status.FAILED);
                ((DefaultAppsPrefs) AnonymousClass0J2.A03(3, 0, this._UL_mInjectionContext)).A02(true);
                A04(this, this);
            }
        }
        return false;
    }

    public static void A03(DefaultAppsSetupJobService defaultAppsSetupJobService, JobParameters jobParameters, boolean z) {
        try {
            defaultAppsSetupJobService.jobFinished(jobParameters, z);
        } catch (IllegalStateException e) {
            ((IErrorReporter) AnonymousClass0J2.A03(2, 428, defaultAppsSetupJobService._UL_mInjectionContext)).A97(ERROR_COMPLETION_SET, "Exception while setting job completion", e);
        }
    }

    public final void onCreate() {
        super.onCreate();
        this._UL_mInjectionContext = new AnonymousClass0QC(5, AnonymousClass0J2.get(this));
    }
}
