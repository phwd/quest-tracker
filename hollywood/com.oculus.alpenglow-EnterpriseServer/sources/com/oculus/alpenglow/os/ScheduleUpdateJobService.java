package com.oculus.alpenglow.os;

import X.AnonymousClass0Lh;
import X.AnonymousClass0NK;
import X.AnonymousClass0R7;
import android.app.job.JobParameters;
import android.app.job.JobService;
import android.content.Context;
import android.content.IntentFilter;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import com.facebook.infer.annotation.Nullsafe;
import com.oculus.alpenglow.config.ConfigurationStore;
import com.oculus.alpenglow.config.Device;
import com.oculus.executors.OculusThreadExecutor;

@Nullsafe(Nullsafe.Mode.LOCAL)
public class ScheduleUpdateJobService extends JobService {
    public static final String TAG = "EnterpriseServer.ScheduleUpdateJobService";
    public AnonymousClass0R7 _UL_mInjectionContext;

    public final boolean onStopJob(JobParameters jobParameters) {
        return true;
    }

    public final void onCreate() {
        super.onCreate();
        this._UL_mInjectionContext = new AnonymousClass0R7(2, AnonymousClass0Lh.get(this));
    }

    public final boolean onStartJob(JobParameters jobParameters) {
        Device.ManagementInfo.DeviceConfig.OsConfig osConfig;
        Device.ManagementInfo A3y;
        Device.ManagementInfo.DeviceConfig A3Q;
        jobParameters.getJobId();
        int jobId = jobParameters.getJobId();
        if (jobId == 1002) {
            ((ScheduleUpdate) AnonymousClass0Lh.A03(0, 33, this._UL_mInjectionContext)).A01(true);
            OculusThreadExecutor.A00().execute(new Runnable() {
                /* class com.oculus.alpenglow.os.$$Lambda$ScheduleUpdateJobService$uIrZnQpJlF7sIJTnWnd4h274wM82 */

                public final void run() {
                    NetworkInfo activeNetworkInfo;
                    ScheduleUpdateJobService scheduleUpdateJobService = ScheduleUpdateJobService.this;
                    ConnectivityManager connectivityManager = (ConnectivityManager) scheduleUpdateJobService.getApplicationContext().getSystemService("connectivity");
                    if (connectivityManager == null || (activeNetworkInfo = connectivityManager.getActiveNetworkInfo()) == null || !activeNetworkInfo.isConnected()) {
                        NetworkCheck networkCheck = new NetworkCheck();
                        Context applicationContext = scheduleUpdateJobService.getApplicationContext();
                        IntentFilter intentFilter = networkCheck.mIntentFilter;
                        if (intentFilter != null) {
                            applicationContext.registerReceiver(networkCheck, intentFilter);
                            return;
                        }
                        throw new UnsupportedOperationException("intent filter is not initialized");
                    } else if (NetworkCheck.A00()) {
                        ((ScheduleUpdate) AnonymousClass0Lh.A03(0, 33, scheduleUpdateJobService._UL_mInjectionContext)).A00();
                    } else {
                        OculusThreadExecutor.A00().execute(
                        /*  JADX ERROR: Method code generation error
                            jadx.core.utils.exceptions.CodegenException: Error generate insn: 0x004d: INVOKE  
                              (wrap: com.oculus.executors.OculusThreadExecutor : 0x0044: INVOKE  (r1v3 com.oculus.executors.OculusThreadExecutor) =  type: STATIC call: com.oculus.executors.OculusThreadExecutor.A00():com.oculus.executors.OculusThreadExecutor)
                              (wrap: com.oculus.alpenglow.os.-$$Lambda$NetworkCheck$KfKEtkDARhcKPPuXw1EX52kf6MU2 : 0x004a: CONSTRUCTOR  (r0v9 com.oculus.alpenglow.os.-$$Lambda$NetworkCheck$KfKEtkDARhcKPPuXw1EX52kf6MU2) = 
                              (wrap: com.oculus.alpenglow.os.NetworkCheck : 0x0033: CONSTRUCTOR  (r5v0 com.oculus.alpenglow.os.NetworkCheck) =  call: com.oculus.alpenglow.os.NetworkCheck.<init>():void type: CONSTRUCTOR)
                              (wrap: android.content.Context : 0x0036: INVOKE  (r4v0 android.content.Context) = (r3v0 'scheduleUpdateJobService' com.oculus.alpenglow.os.ScheduleUpdateJobService) type: VIRTUAL call: com.oculus.alpenglow.os.ScheduleUpdateJobService.getApplicationContext():android.content.Context)
                              (wrap: android.os.Handler : 0x0040: CONSTRUCTOR  (r3v1 android.os.Handler) = 
                              (wrap: android.os.Looper : 0x003a: INVOKE  (r0v8 android.os.Looper) =  type: STATIC call: android.os.Looper.getMainLooper():android.os.Looper)
                             call: android.os.Handler.<init>(android.os.Looper):void type: CONSTRUCTOR)
                              (5 int)
                             call: com.oculus.alpenglow.os.-$$Lambda$NetworkCheck$KfKEtkDARhcKPPuXw1EX52kf6MU2.<init>(com.oculus.alpenglow.os.NetworkCheck, android.content.Context, android.os.Handler, int):void type: CONSTRUCTOR)
                             type: VIRTUAL call: com.oculus.executors.OculusThreadExecutor.execute(java.lang.Runnable):void in method: com.oculus.alpenglow.os.-$$Lambda$ScheduleUpdateJobService$uIrZnQpJlF7sIJTnWnd4h274wM82.run():void, file: classes2.dex
                            	at jadx.core.codegen.InsnGen.makeInsn(InsnGen.java:255)
                            	at jadx.core.codegen.InsnGen.makeInsn(InsnGen.java:217)
                            	at jadx.core.codegen.RegionGen.makeSimpleBlock(RegionGen.java:110)
                            	at jadx.core.codegen.RegionGen.makeRegion(RegionGen.java:56)
                            	at jadx.core.codegen.RegionGen.makeSimpleRegion(RegionGen.java:93)
                            	at jadx.core.codegen.RegionGen.makeRegion(RegionGen.java:59)
                            	at jadx.core.codegen.RegionGen.makeRegionIndent(RegionGen.java:99)
                            	at jadx.core.codegen.RegionGen.makeIf(RegionGen.java:157)
                            	at jadx.core.codegen.RegionGen.connectElseIf(RegionGen.java:176)
                            	at jadx.core.codegen.RegionGen.makeIf(RegionGen.java:153)
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
                            Caused by: jadx.core.utils.exceptions.CodegenException: Error generate insn: 0x004a: CONSTRUCTOR  (r0v9 com.oculus.alpenglow.os.-$$Lambda$NetworkCheck$KfKEtkDARhcKPPuXw1EX52kf6MU2) = 
                              (wrap: com.oculus.alpenglow.os.NetworkCheck : 0x0033: CONSTRUCTOR  (r5v0 com.oculus.alpenglow.os.NetworkCheck) =  call: com.oculus.alpenglow.os.NetworkCheck.<init>():void type: CONSTRUCTOR)
                              (wrap: android.content.Context : 0x0036: INVOKE  (r4v0 android.content.Context) = (r3v0 'scheduleUpdateJobService' com.oculus.alpenglow.os.ScheduleUpdateJobService) type: VIRTUAL call: com.oculus.alpenglow.os.ScheduleUpdateJobService.getApplicationContext():android.content.Context)
                              (wrap: android.os.Handler : 0x0040: CONSTRUCTOR  (r3v1 android.os.Handler) = 
                              (wrap: android.os.Looper : 0x003a: INVOKE  (r0v8 android.os.Looper) =  type: STATIC call: android.os.Looper.getMainLooper():android.os.Looper)
                             call: android.os.Handler.<init>(android.os.Looper):void type: CONSTRUCTOR)
                              (5 int)
                             call: com.oculus.alpenglow.os.-$$Lambda$NetworkCheck$KfKEtkDARhcKPPuXw1EX52kf6MU2.<init>(com.oculus.alpenglow.os.NetworkCheck, android.content.Context, android.os.Handler, int):void type: CONSTRUCTOR in method: com.oculus.alpenglow.os.-$$Lambda$ScheduleUpdateJobService$uIrZnQpJlF7sIJTnWnd4h274wM82.run():void, file: classes2.dex
                            	at jadx.core.codegen.InsnGen.makeInsn(InsnGen.java:255)
                            	at jadx.core.codegen.InsnGen.addWrappedArg(InsnGen.java:119)
                            	at jadx.core.codegen.InsnGen.addArg(InsnGen.java:103)
                            	at jadx.core.codegen.InsnGen.generateMethodArguments(InsnGen.java:806)
                            	at jadx.core.codegen.InsnGen.makeInvoke(InsnGen.java:746)
                            	at jadx.core.codegen.InsnGen.makeInsnBody(InsnGen.java:367)
                            	at jadx.core.codegen.InsnGen.makeInsn(InsnGen.java:249)
                            	... 21 more
                            Caused by: jadx.core.utils.exceptions.JadxRuntimeException: Expected class to be processed at this point, class: com.oculus.alpenglow.os.-$$Lambda$NetworkCheck$KfKEtkDARhcKPPuXw1EX52kf6MU2, state: NOT_LOADED
                            	at jadx.core.dex.nodes.ClassNode.ensureProcessed(ClassNode.java:215)
                            	at jadx.core.codegen.InsnGen.makeConstructor(InsnGen.java:630)
                            	at jadx.core.codegen.InsnGen.makeInsnBody(InsnGen.java:363)
                            	at jadx.core.codegen.InsnGen.makeInsn(InsnGen.java:230)
                            	... 27 more
                            */
                        /*
                        // Method dump skipped, instructions count: 106
                        */
                        throw new UnsupportedOperationException("Method not decompiled: com.oculus.alpenglow.os.$$Lambda$ScheduleUpdateJobService$uIrZnQpJlF7sIJTnWnd4h274wM82.run():void");
                    }
                });
            } else if (jobId != 1003) {
                AnonymousClass0NK.A06(TAG, "ScheduleUpdateJobService called with unsupported job id = %s", Integer.valueOf(jobParameters.getJobId()));
            } else {
                ((ScheduleUpdate) AnonymousClass0Lh.A03(0, 33, this._UL_mInjectionContext)).A01(false);
                AnonymousClass0R7 r4 = this._UL_mInjectionContext;
                ScheduleUpdate scheduleUpdate = (ScheduleUpdate) AnonymousClass0Lh.A03(0, 33, r4);
                Device device = ((ConfigurationStore) AnonymousClass0Lh.A03(1, 97, r4)).mData.device;
                if (device == null || (A3y = device.A3y()) == null || (A3Q = A3y.A3Q()) == null) {
                    osConfig = null;
                } else {
                    osConfig = A3Q.A4D();
                }
                scheduleUpdate.A02(osConfig);
            }
            jobFinished(jobParameters, false);
            return true;
        }
    }
