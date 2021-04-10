package com.oculus.unifiedtelemetry.boot;

import X.AbstractC0096Hu;
import X.Mu;
import X.QB;
import X.QC;
import android.annotation.SuppressLint;
import android.app.job.JobInfo;
import android.app.job.JobScheduler;
import android.content.BroadcastReceiver;
import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.content.SharedPreferences;
import android.os.SystemClock;
import android.os.UserHandle;
import com.oculus.common.init.AppInitLock;
import com.oculus.common.init.INeedInit;
import com.oculus.multiuser.UserClassifier;
import com.oculus.unifiedtelemetry.appinit.UnifiedTelemetryAppInitializer;
import com.oculus.unifiedtelemetry.unifiedlogging.EventCacheShutdownBroadcastReceiver;
import com.oculus.unifiedtelemetry.unifiedlogging.LoggingHandlerBroadcastReceiver;
import com.oculus.unifiedtelemetry.unifiedlogging.SendDeviceInfoJobService;
import com.oculus.unifiedtelemetry.unifiedlogging.SessionController;
import com.oculus.unifiedtelemetry.unifiedtelemetryservice.UnifiedTelemetryService;
import com.oculus.util.constants.JobSchedulerIds;
import java.lang.reflect.Method;
import java.util.Set;

@SuppressLint({"BadMethodUse-android.content.Context.getSharedPreferences", "BadMethodUse-java.lang.Class.getSimpleName", "BadSuperClassBroadcastReceiver.SecureBroadcastReceiver", "EndpointWithoutSwitchOff", "ObsoleteSdkInt", "SharedPreferencesUse"})
public final class UnifiedTelemetryServiceBootReceiver extends BroadcastReceiver implements QB {
    public static final String BOOT_REASON_KEY = "bootup_reason";
    public static final String BOOT_TIME_KEY = "bootup_time";
    public static final String CONTEXT_CLASS = "android.content.Context";
    public static final long DEFAULT_BOOT_TIME = -1;
    public static final String EVENT_NAME_BOOT_TIME = "oculus_mobile_boot_time";
    public static final String FIELD_SYSTEM = "SYSTEM";
    public static final String METHOD_START_SERVICE_AS_USER = "startServiceAsUser";
    public static final String SHARED_PREFS_BOOT_DEVICE_PROTECTED = "oculus.boot.prefs.deviceprotected";
    public static final String TAG = "UnifiedTelemetryServiceBootReceiver";
    public static final String USER_HANDLE_CLASS = "android.os.UserHandle";
    public QC _UL_mInjectionContext;

    public final void onReceive(Context context, Intent intent) {
        String action;
        int schedule;
        String str;
        String str2;
        QC qc = new QC(8, AbstractC0096Hu.get(context));
        this._UL_mInjectionContext = qc;
        if (((UserClassifier) AbstractC0096Hu.A03(0, 22, qc)).A01() && (action = intent.getAction()) != null) {
            SharedPreferences sharedPreferences = context.createDeviceProtectedStorageContext().getSharedPreferences(SHARED_PREFS_BOOT_DEVICE_PROTECTED, 0);
            char c = 65535;
            int hashCode = action.hashCode();
            if (hashCode != -905063602) {
                if (hashCode == 798292259 && action.equals("android.intent.action.BOOT_COMPLETED")) {
                    c = 1;
                }
            } else if (action.equals("android.intent.action.LOCKED_BOOT_COMPLETED")) {
                c = 0;
            }
            if (c == 0) {
                sharedPreferences.edit().putLong(BOOT_TIME_KEY, SystemClock.elapsedRealtime()).apply();
                SessionController sessionController = (SessionController) AbstractC0096Hu.A03(4, 14, this._UL_mInjectionContext);
                synchronized (sessionController.mSessionCache) {
                    sessionController.mSessionCache.clear();
                    SessionController.A01(sessionController);
                }
                EventCacheShutdownBroadcastReceiver eventCacheShutdownBroadcastReceiver = (EventCacheShutdownBroadcastReceiver) AbstractC0096Hu.A03(6, 35, this._UL_mInjectionContext);
                IntentFilter intentFilter = new IntentFilter("android.intent.action.ACTION_SHUTDOWN");
                eventCacheShutdownBroadcastReceiver.mIntentFilter = intentFilter;
                ((Context) AbstractC0096Hu.A03(0, 3, eventCacheShutdownBroadcastReceiver._UL_mInjectionContext)).registerReceiver(eventCacheShutdownBroadcastReceiver, intentFilter);
                LoggingHandlerBroadcastReceiver loggingHandlerBroadcastReceiver = (LoggingHandlerBroadcastReceiver) AbstractC0096Hu.A03(7, 33, this._UL_mInjectionContext);
                IntentFilter intentFilter2 = new IntentFilter("android.intent.action.ACTION_SHUTDOWN");
                loggingHandlerBroadcastReceiver.mIntentFilter = intentFilter2;
                ((Context) AbstractC0096Hu.A03(0, 3, loggingHandlerBroadcastReceiver._UL_mInjectionContext)).registerReceiver(loggingHandlerBroadcastReceiver, intentFilter2);
                Intent intent2 = new Intent(context, UnifiedTelemetryService.class);
                try {
                    Method method = Class.forName(CONTEXT_CLASS).getMethod(METHOD_START_SERVICE_AS_USER, Intent.class, UserHandle.class);
                    Class<?> cls = Class.forName(USER_HANDLE_CLASS);
                    method.invoke(context, intent2, cls.getDeclaredField(FIELD_SYSTEM).get(cls));
                } catch (ReflectiveOperationException e) {
                    Mu.A02(TAG, "Context startServiceAsUser failed", e);
                }
                Context applicationContext = context.getApplicationContext();
                JobInfo build = new JobInfo.Builder(JobSchedulerIds.SEND_DEVICE_INFO, new ComponentName(applicationContext, SendDeviceInfoJobService.class)).setRequiredNetworkType(1).setPeriodic(SendDeviceInfoJobService.DAILY_INTERVAL_MS).setPersisted(false).build();
                JobScheduler jobScheduler = (JobScheduler) applicationContext.getSystemService("jobscheduler");
                if (!(jobScheduler == null || build == null || (schedule = jobScheduler.schedule(build)) == 1)) {
                    Mu.A05(SendDeviceInfoJobService.TAG, "Failed to schedule job. Return: %d", Integer.valueOf(schedule));
                }
            } else if (c == 1) {
                UnifiedTelemetryAppInitializer unifiedTelemetryAppInitializer = (UnifiedTelemetryAppInitializer) AbstractC0096Hu.A03(1, 126, this._UL_mInjectionContext);
                try {
                    Set<INeedInit> set = unifiedTelemetryAppInitializer.mHighPriorityINeedInitsLazy.get();
                    set.size();
                    for (INeedInit iNeedInit : set) {
                        try {
                            iNeedInit.A36();
                        } catch (ExceptionInInitializerError unused) {
                        }
                    }
                    Set<INeedInit> set2 = unifiedTelemetryAppInitializer.mINeedInitsLazy.get();
                    set2.size();
                    for (INeedInit iNeedInit2 : set2) {
                        try {
                            iNeedInit2.A36();
                        } catch (ExceptionInInitializerError unused2) {
                        }
                    }
                } catch (RuntimeException e2) {
                    Mu.A02(UnifiedTelemetryAppInitializer.TAG, "Failure in INeedInit", e2);
                }
                AppInitLock appInitLock = (AppInitLock) AbstractC0096Hu.A03(0, 133, unifiedTelemetryAppInitializer._UL_mInjectionContext);
                synchronized (appInitLock) {
                    appInitLock.initialized = true;
                    appInitLock.notifyAll();
                }
                AppInitLock.AnonymousClass1 r2 = 
                /*  JADX ERROR: Method code generation error
                    jadx.core.utils.exceptions.CodegenException: Error generate insn: 0x00ba: CONSTRUCTOR  (r2v20 'r2' com.oculus.common.init.AppInitLock$1) = (r1v18 'appInitLock' com.oculus.common.init.AppInitLock) call: com.oculus.common.init.AppInitLock.1.<init>(com.oculus.common.init.AppInitLock):void type: CONSTRUCTOR in method: com.oculus.unifiedtelemetry.boot.UnifiedTelemetryServiceBootReceiver.onReceive(android.content.Context, android.content.Intent):void, file: classes2.dex
                    	at jadx.core.codegen.InsnGen.makeInsn(InsnGen.java:255)
                    	at jadx.core.codegen.InsnGen.makeInsn(InsnGen.java:217)
                    	at jadx.core.codegen.RegionGen.makeSimpleBlock(RegionGen.java:110)
                    	at jadx.core.codegen.RegionGen.makeRegion(RegionGen.java:56)
                    	at jadx.core.codegen.RegionGen.makeSimpleRegion(RegionGen.java:93)
                    	at jadx.core.codegen.RegionGen.makeRegion(RegionGen.java:59)
                    	at jadx.core.codegen.RegionGen.makeRegionIndent(RegionGen.java:99)
                    	at jadx.core.codegen.RegionGen.makeIf(RegionGen.java:143)
                    	at jadx.core.codegen.RegionGen.connectElseIf(RegionGen.java:176)
                    	at jadx.core.codegen.RegionGen.makeIf(RegionGen.java:153)
                    	at jadx.core.codegen.RegionGen.makeRegion(RegionGen.java:63)
                    	at jadx.core.codegen.RegionGen.makeSimpleRegion(RegionGen.java:93)
                    	at jadx.core.codegen.RegionGen.makeRegion(RegionGen.java:59)
                    	at jadx.core.codegen.RegionGen.makeRegionIndent(RegionGen.java:99)
                    	at jadx.core.codegen.RegionGen.makeIf(RegionGen.java:143)
                    	at jadx.core.codegen.RegionGen.makeRegion(RegionGen.java:63)
                    	at jadx.core.codegen.RegionGen.makeSimpleRegion(RegionGen.java:93)
                    	at jadx.core.codegen.RegionGen.makeRegion(RegionGen.java:59)
                    	at jadx.core.codegen.MethodGen.addRegionInsns(MethodGen.java:244)
                    	at jadx.core.codegen.MethodGen.addInstructions(MethodGen.java:237)
                    	at jadx.core.codegen.ClassGen.addMethodCode(ClassGen.java:342)
                    	at jadx.core.codegen.ClassGen.addMethod(ClassGen.java:295)
                    	at jadx.core.codegen.ClassGen.lambda$addInnerClsAndMethods$2(ClassGen.java:264)
                    	at java.util.stream.ForEachOps$ForEachOp$OfRef.accept(ForEachOps.java:183)
                    	at java.util.ArrayList.forEach(ArrayList.java:1259)
                    	at java.util.stream.SortedOps$RefSortingSink.end(SortedOps.java:395)
                    	at java.util.stream.Sink$ChainedReference.end(Sink.java:258)
                    Caused by: jadx.core.utils.exceptions.JadxRuntimeException: Expected class to be processed at this point, class: com.oculus.common.init.AppInitLock, state: GENERATED_AND_UNLOADED
                    	at jadx.core.dex.nodes.ClassNode.ensureProcessed(ClassNode.java:215)
                    	at jadx.core.codegen.InsnGen.makeConstructor(InsnGen.java:630)
                    	at jadx.core.codegen.InsnGen.makeInsnBody(InsnGen.java:363)
                    	at jadx.core.codegen.InsnGen.makeInsn(InsnGen.java:249)
                    	... 26 more
                    */
                /*
                // Method dump skipped, instructions count: 784
                */
                throw new UnsupportedOperationException("Method not decompiled: com.oculus.unifiedtelemetry.boot.UnifiedTelemetryServiceBootReceiver.onReceive(android.content.Context, android.content.Intent):void");
            }
        }
