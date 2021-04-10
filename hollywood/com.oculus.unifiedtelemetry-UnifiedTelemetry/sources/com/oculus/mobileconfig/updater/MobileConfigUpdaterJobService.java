package com.oculus.mobileconfig.updater;

import X.AbstractC0096Hu;
import X.AnonymousClass06;
import X.D3;
import X.DB;
import X.QC;
import android.app.job.JobParameters;
import android.app.job.JobService;
import android.content.Context;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.os.Handler;
import android.os.Looper;
import com.oculus.logging.utils.EventManager;
import com.oculus.mobileconfig.init.MobileConfigConfiguration;
import com.oculus.mobileconfig.init.MobileConfigInitOptions;
import com.oculus.util.network.NetworkUtils;
import java.util.concurrent.Callable;

public class MobileConfigUpdaterJobService extends JobService {
    public static final String EVENT_MOBILE_CONFIG_REFRESH = "oc_mobileconfig_refresh_started";
    public static String TAG = null;
    public static final int WIFI_CHECK_LATCH_TIMEOUT = 30;
    public QC _UL_mInjectionContext;

    public final boolean onStopJob(JobParameters jobParameters) {
        return false;
    }

    public final boolean onStartJob(final JobParameters jobParameters) {
        ((EventManager) AbstractC0096Hu.A03(1, 106, this._UL_mInjectionContext)).A1h(EVENT_MOBILE_CONFIG_REFRESH).A3Q();
        final Context applicationContext = getApplicationContext();
        DB.A00(new Callable<Void>() {
            /* class com.oculus.mobileconfig.updater.MobileConfigUpdaterJobService.AnonymousClass2 */

            /* Return type fixed from 'java.lang.Object' to match base method */
            @Override // java.util.concurrent.Callable
            public final Void call() throws Exception {
                NetworkInfo activeNetworkInfo;
                ConnectivityManager connectivityManager = (ConnectivityManager) MobileConfigUpdaterJobService.this.getApplicationContext().getSystemService("connectivity");
                boolean z = false;
                if (!(connectivityManager == null || (activeNetworkInfo = connectivityManager.getActiveNetworkInfo()) == null || !activeNetworkInfo.isConnected())) {
                    z = true;
                }
                if (z) {
                    if (!NetworkUtils.A00()) {
                        MobileConfigConnectivityChecker mobileConfigConnectivityChecker = new MobileConfigConnectivityChecker(((MobileConfigInitOptions) AbstractC0096Hu.A03(2, 131, MobileConfigUpdaterJobService.this._UL_mInjectionContext)).mTagPrefix);
                        Context context = applicationContext;
                        Handler handler = new Handler(Looper.getMainLooper());
                        handler.postDelayed(
                        /*  JADX ERROR: Method code generation error
                            jadx.core.utils.exceptions.CodegenException: Error generate insn: 0x004e: INVOKE  
                              (r3v2 'handler' android.os.Handler)
                              (wrap: com.oculus.mobileconfig.updater.MobileConfigConnectivityChecker$1 : 0x0049: CONSTRUCTOR  (r2v2 com.oculus.mobileconfig.updater.MobileConfigConnectivityChecker$1) = 
                              (r4v0 'mobileConfigConnectivityChecker' com.oculus.mobileconfig.updater.MobileConfigConnectivityChecker)
                              (r1v7 'context' android.content.Context)
                              (r3v2 'handler' android.os.Handler)
                              (5 int)
                             call: com.oculus.mobileconfig.updater.MobileConfigConnectivityChecker.1.<init>(com.oculus.mobileconfig.updater.MobileConfigConnectivityChecker, android.content.Context, android.os.Handler, int):void type: CONSTRUCTOR)
                              (wrap: long : 0x004c: SGET  (r0v19 long) =  com.oculus.mobileconfig.updater.MobileConfigConnectivityChecker.WIFI_CHECK_RETRY_INTERVAL long)
                             type: VIRTUAL call: android.os.Handler.postDelayed(java.lang.Runnable, long):boolean in method: com.oculus.mobileconfig.updater.MobileConfigUpdaterJobService.2.call():java.lang.Void, file: classes.dex
                            	at jadx.core.codegen.InsnGen.makeInsn(InsnGen.java:255)
                            	at jadx.core.codegen.InsnGen.makeInsn(InsnGen.java:217)
                            	at jadx.core.codegen.RegionGen.makeSimpleBlock(RegionGen.java:110)
                            	at jadx.core.codegen.RegionGen.makeRegion(RegionGen.java:56)
                            	at jadx.core.codegen.RegionGen.makeSimpleRegion(RegionGen.java:93)
                            	at jadx.core.codegen.RegionGen.makeRegion(RegionGen.java:59)
                            	at jadx.core.codegen.RegionGen.makeRegionIndent(RegionGen.java:99)
                            	at jadx.core.codegen.RegionGen.makeIf(RegionGen.java:143)
                            	at jadx.core.codegen.RegionGen.makeRegion(RegionGen.java:63)
                            	at jadx.core.codegen.RegionGen.makeSimpleRegion(RegionGen.java:93)
                            	at jadx.core.codegen.RegionGen.makeRegion(RegionGen.java:59)
                            	at jadx.core.codegen.RegionGen.makeRegionIndent(RegionGen.java:99)
                            	at jadx.core.codegen.RegionGen.makeIf(RegionGen.java:143)
                            	at jadx.core.codegen.RegionGen.makeRegion(RegionGen.java:63)
                            	at jadx.core.codegen.RegionGen.makeSimpleRegion(RegionGen.java:93)
                            	at jadx.core.codegen.RegionGen.makeRegion(RegionGen.java:59)
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
                            Caused by: jadx.core.utils.exceptions.CodegenException: Error generate insn: 0x0049: CONSTRUCTOR  (r2v2 com.oculus.mobileconfig.updater.MobileConfigConnectivityChecker$1) = 
                              (r4v0 'mobileConfigConnectivityChecker' com.oculus.mobileconfig.updater.MobileConfigConnectivityChecker)
                              (r1v7 'context' android.content.Context)
                              (r3v2 'handler' android.os.Handler)
                              (5 int)
                             call: com.oculus.mobileconfig.updater.MobileConfigConnectivityChecker.1.<init>(com.oculus.mobileconfig.updater.MobileConfigConnectivityChecker, android.content.Context, android.os.Handler, int):void type: CONSTRUCTOR in method: com.oculus.mobileconfig.updater.MobileConfigUpdaterJobService.2.call():java.lang.Void, file: classes.dex
                            	at jadx.core.codegen.InsnGen.makeInsn(InsnGen.java:255)
                            	at jadx.core.codegen.InsnGen.addWrappedArg(InsnGen.java:119)
                            	at jadx.core.codegen.InsnGen.addArg(InsnGen.java:103)
                            	at jadx.core.codegen.InsnGen.generateMethodArguments(InsnGen.java:806)
                            	at jadx.core.codegen.InsnGen.makeInvoke(InsnGen.java:746)
                            	at jadx.core.codegen.InsnGen.makeInsnBody(InsnGen.java:367)
                            	at jadx.core.codegen.InsnGen.makeInsn(InsnGen.java:249)
                            	... 26 more
                            Caused by: jadx.core.utils.exceptions.JadxRuntimeException: Expected class to be processed at this point, class: com.oculus.mobileconfig.updater.MobileConfigConnectivityChecker, state: GENERATED_AND_UNLOADED
                            	at jadx.core.dex.nodes.ClassNode.ensureProcessed(ClassNode.java:215)
                            	at jadx.core.codegen.InsnGen.makeConstructor(InsnGen.java:630)
                            	at jadx.core.codegen.InsnGen.makeInsnBody(InsnGen.java:363)
                            	at jadx.core.codegen.InsnGen.makeInsn(InsnGen.java:230)
                            	... 32 more
                            */
                        /*
                        // Method dump skipped, instructions count: 131
                        */
                        throw new UnsupportedOperationException("Method not decompiled: com.oculus.mobileconfig.updater.MobileConfigUpdaterJobService.AnonymousClass2.call():java.lang.Object");
                    }
                }, DB.A0C).A04(new D3<Void, Void>() {
                    /* class com.oculus.mobileconfig.updater.MobileConfigUpdaterJobService.AnonymousClass1 */

                    /* Return type fixed from 'java.lang.Object' to match base method */
                    /* JADX DEBUG: Method arguments types fixed to match base method, original types: [X.DB] */
                    @Override // X.D3
                    public final /* bridge */ /* synthetic */ Void A5U(DB<Void> db) throws Exception {
                        ((MobileConfigConfiguration) AbstractC0096Hu.A03(0, 1, MobileConfigUpdaterJobService.this._UL_mInjectionContext)).fetchAsync().A04(new D3<Void, Void>() {
                            /* class com.oculus.mobileconfig.updater.MobileConfigUpdaterJobService.AnonymousClass1.AnonymousClass1 */

                            /* Return type fixed from 'java.lang.Object' to match base method */
                            /* JADX DEBUG: Method arguments types fixed to match base method, original types: [X.DB] */
                            @Override // X.D3
                            public final Void A5U(DB<Void> db) throws Exception {
                                AnonymousClass1 r0 = AnonymousClass1.this;
                                MobileConfigUpdaterJobService.this.jobFinished(jobParameters, false);
                                return null;
                            }
                        });
                        return null;
                    }
                });
                return true;
            }

            public final void onCreate() {
                super.onCreate();
                QC qc = new QC(3, AbstractC0096Hu.get(this));
                this._UL_mInjectionContext = qc;
                TAG = AnonymousClass06.A04(((MobileConfigInitOptions) AbstractC0096Hu.A03(2, 131, qc)).mTagPrefix, "MobileConfigUpdaterJobService");
            }
        }
