package com.oculus.security.basecomponent;

import X.AbstractC02800aq;
import X.AbstractC02820as;
import X.AbstractC04970iB;
import X.AbstractC04990iH;
import X.AbstractC05000iI;
import X.AnonymousClass0Lh;
import X.AnonymousClass0NK;
import X.AnonymousClass0R7;
import X.AnonymousClass13m;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.net.Uri;
import android.os.Handler;
import android.os.Looper;
import android.text.TextUtils;
import com.facebook.ultralight.Eager;
import com.facebook.ultralight.Inject;
import com.oculus.alpenglow.config.ConfigurationStore;
import com.oculus.alpenglow.database.ApplicationDatabase;
import com.oculus.alpenglow.install.PackageChangeListener;
import com.oculus.alpenglow.logging.ConfigLogger;
import com.oculus.alpenglow.logging.LoggerConstants;
import com.oculus.alpenglow.os.NetworkCheck;
import com.oculus.executors.OculusThreadExecutor;
import com.oculus.managed.ManagedMode;
import javax.annotation.Nullable;

public abstract class OculusSecureBroadcastReceiverBase extends AbstractC02800aq {
    public AnonymousClass0R7 _UL_mInjectionContext;
    public AbstractC04990iH mActionReceiver = new AbstractC04990iH() {
        /* class com.oculus.security.basecomponent.OculusSecureBroadcastReceiverBase.AnonymousClass1 */

        @Override // X.AbstractC04990iH
        public final void onReceive(Context context, Intent intent, AbstractC05000iI r9) {
            NetworkInfo activeNetworkInfo;
            String str;
            String str2;
            OculusSecureBroadcastReceiverBase oculusSecureBroadcastReceiverBase = OculusSecureBroadcastReceiverBase.this;
            if (!(oculusSecureBroadcastReceiverBase instanceof NetworkCheck)) {
                PackageChangeListener packageChangeListener = (PackageChangeListener) oculusSecureBroadcastReceiverBase;
                packageChangeListener._UL_mInjectionContext = new AnonymousClass0R7(4, AnonymousClass0Lh.get(context));
                String action = intent.getAction();
                if (!((ManagedMode) AnonymousClass0Lh.A03(3, 86, packageChangeListener._UL_mInjectionContext)).isEnterpriseModeEnabled) {
                    str = PackageChangeListener.TAG;
                    str2 = "Device is not in Enterprise Mode, should not be receiving this broadcast";
                } else if (TextUtils.isEmpty(action)) {
                    AnonymousClass0NK.A02(PackageChangeListener.TAG, "No action found");
                    return;
                } else if (!"android.intent.action.PACKAGE_FULLY_REMOVED".equals(action)) {
                    AnonymousClass0NK.A07(PackageChangeListener.TAG, "Unsupported action for the receiver, action = %s", action);
                    return;
                } else {
                    Uri data = intent.getData();
                    if (data == null) {
                        str = PackageChangeListener.TAG;
                        str2 = "Package name not found";
                    } else {
                        OculusThreadExecutor.A00().execute(new Runnable(data.getEncodedSchemeSpecificPart()) {
                            /* class com.oculus.alpenglow.install.$$Lambda$PackageChangeListener$yM3vIGNU8TJEz2RXGuB3FbGVyZg2 */
                            public final /* synthetic */ String f$1;

                            {
                                this.f$1 = r2;
                            }

                            public final void run() {
                                PackageChangeListener packageChangeListener = PackageChangeListener.this;
                                for (String str : ((ApplicationDatabase) AnonymousClass0Lh.A03(0, 3, packageChangeListener._UL_mInjectionContext)).A04(this.f$1)) {
                                    ((ApplicationDatabase) AnonymousClass0Lh.A03(0, 3, packageChangeListener._UL_mInjectionContext)).A05(str);
                                }
                                ((ConfigurationStore) AnonymousClass0Lh.A03(1, 97, packageChangeListener._UL_mInjectionContext)).A03(((ConfigLogger) AnonymousClass0Lh.A03(2, 45, packageChangeListener._UL_mInjectionContext)).A01(LoggerConstants.CONFIGURATION_FETCH_REASON_INDUCTION, LoggerConstants.CONFIGURATION_FETCH_TYPE_SILENT));
                            }
                        });
                        return;
                    }
                }
            } else {
                NetworkCheck networkCheck = (NetworkCheck) oculusSecureBroadcastReceiverBase;
                if (intent.getAction() == null) {
                    str = NetworkCheck.TAG;
                    str2 = "NetworkCheck called with null action";
                } else {
                    networkCheck._UL_mInjectionContext = new AnonymousClass0R7(1, AnonymousClass0Lh.get(context));
                    ConnectivityManager connectivityManager = (ConnectivityManager) context.getApplicationContext().getSystemService("connectivity");
                    if (connectivityManager != null && (activeNetworkInfo = connectivityManager.getActiveNetworkInfo()) != null && activeNetworkInfo.isConnected()) {
                        OculusThreadExecutor.A00().execute(new Runnable(context.getApplicationContext(), new Handler(Looper.getMainLooper()), 5) {
                            /* class com.oculus.alpenglow.os.$$Lambda$NetworkCheck$KfKEtkDARhcKPPuXw1EX52kf6MU2 */
                            public final /* synthetic */ Context f$1;
                            public final /* synthetic */ Handler f$2;
                            public final /* synthetic */ int f$3;

                            {
                                this.f$1 = r2;
                                this.f$2 = r3;
                                this.f$3 = r4;
                            }

                            public final void run() {
                                NetworkCheck networkCheck = NetworkCheck.this;
                                Context context = this.f$1;
                                Handler handler = this.f$2;
                                int i = this.f$3;
                                if (NetworkCheck.A00()) {
                                    context.unregisterReceiver(networkCheck);
                                    ((ScheduleUpdate) AnonymousClass0Lh.A03(0, 33, networkCheck._UL_mInjectionContext)).A00();
                                    return;
                                }
                                handler.postDelayed(
                                /*  JADX ERROR: Method code generation error
                                    jadx.core.utils.exceptions.CodegenException: Error generate insn: 0x0027: INVOKE  
                                      (r3v0 'handler' android.os.Handler)
                                      (wrap: com.oculus.alpenglow.os.-$$Lambda$NetworkCheck$zsHERH6rdnw687YTxpZCLL-Ii-w2 : 0x0022: CONSTRUCTOR  (r2v0 com.oculus.alpenglow.os.-$$Lambda$NetworkCheck$zsHERH6rdnw687YTxpZCLL-Ii-w2) = 
                                      (r5v0 'networkCheck' com.oculus.alpenglow.os.NetworkCheck)
                                      (r4v0 'context' android.content.Context)
                                      (r3v0 'handler' android.os.Handler)
                                      (r1v0 'i' int)
                                     call: com.oculus.alpenglow.os.-$$Lambda$NetworkCheck$zsHERH6rdnw687YTxpZCLL-Ii-w2.<init>(com.oculus.alpenglow.os.NetworkCheck, android.content.Context, android.os.Handler, int):void type: CONSTRUCTOR)
                                      (wrap: long : 0x0025: SGET  (r0v1 long) =  com.oculus.alpenglow.os.NetworkCheck.WIFI_CHECK_RETRY_INTERVAL long)
                                     type: VIRTUAL call: android.os.Handler.postDelayed(java.lang.Runnable, long):boolean in method: com.oculus.alpenglow.os.-$$Lambda$NetworkCheck$KfKEtkDARhcKPPuXw1EX52kf6MU2.run():void, file: classes2.dex
                                    	at jadx.core.codegen.InsnGen.makeInsn(InsnGen.java:255)
                                    	at jadx.core.codegen.InsnGen.makeInsn(InsnGen.java:217)
                                    	at jadx.core.codegen.RegionGen.makeSimpleBlock(RegionGen.java:110)
                                    	at jadx.core.codegen.RegionGen.makeRegion(RegionGen.java:56)
                                    	at jadx.core.codegen.RegionGen.makeSimpleRegion(RegionGen.java:93)
                                    	at jadx.core.codegen.RegionGen.makeRegion(RegionGen.java:59)
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
                                    Caused by: jadx.core.utils.exceptions.CodegenException: Error generate insn: 0x0022: CONSTRUCTOR  (r2v0 com.oculus.alpenglow.os.-$$Lambda$NetworkCheck$zsHERH6rdnw687YTxpZCLL-Ii-w2) = 
                                      (r5v0 'networkCheck' com.oculus.alpenglow.os.NetworkCheck)
                                      (r4v0 'context' android.content.Context)
                                      (r3v0 'handler' android.os.Handler)
                                      (r1v0 'i' int)
                                     call: com.oculus.alpenglow.os.-$$Lambda$NetworkCheck$zsHERH6rdnw687YTxpZCLL-Ii-w2.<init>(com.oculus.alpenglow.os.NetworkCheck, android.content.Context, android.os.Handler, int):void type: CONSTRUCTOR in method: com.oculus.alpenglow.os.-$$Lambda$NetworkCheck$KfKEtkDARhcKPPuXw1EX52kf6MU2.run():void, file: classes2.dex
                                    	at jadx.core.codegen.InsnGen.makeInsn(InsnGen.java:255)
                                    	at jadx.core.codegen.InsnGen.addWrappedArg(InsnGen.java:119)
                                    	at jadx.core.codegen.InsnGen.addArg(InsnGen.java:103)
                                    	at jadx.core.codegen.InsnGen.generateMethodArguments(InsnGen.java:806)
                                    	at jadx.core.codegen.InsnGen.makeInvoke(InsnGen.java:746)
                                    	at jadx.core.codegen.InsnGen.makeInsnBody(InsnGen.java:367)
                                    	at jadx.core.codegen.InsnGen.makeInsn(InsnGen.java:249)
                                    	... 18 more
                                    Caused by: jadx.core.utils.exceptions.JadxRuntimeException: Expected class to be processed at this point, class: com.oculus.alpenglow.os.-$$Lambda$NetworkCheck$zsHERH6rdnw687YTxpZCLL-Ii-w2, state: NOT_LOADED
                                    	at jadx.core.dex.nodes.ClassNode.ensureProcessed(ClassNode.java:215)
                                    	at jadx.core.codegen.InsnGen.makeConstructor(InsnGen.java:630)
                                    	at jadx.core.codegen.InsnGen.makeInsnBody(InsnGen.java:363)
                                    	at jadx.core.codegen.InsnGen.makeInsn(InsnGen.java:230)
                                    	... 24 more
                                    */
                                /*
                                    this = this;
                                    com.oculus.alpenglow.os.NetworkCheck r5 = com.oculus.alpenglow.os.NetworkCheck.this
                                    android.content.Context r4 = r6.f$1
                                    android.os.Handler r3 = r6.f$2
                                    int r1 = r6.f$3
                                    boolean r0 = com.oculus.alpenglow.os.NetworkCheck.A00()
                                    if (r0 == 0) goto L_0x0020
                                    r4.unregisterReceiver(r5)
                                    r2 = 0
                                    r1 = 33
                                    X.0R7 r0 = r5._UL_mInjectionContext
                                    java.lang.Object r0 = X.AnonymousClass0Lh.A03(r2, r1, r0)
                                    com.oculus.alpenglow.os.ScheduleUpdate r0 = (com.oculus.alpenglow.os.ScheduleUpdate) r0
                                    r0.A00()
                                    return
                                L_0x0020:
                                    com.oculus.alpenglow.os.-$$Lambda$NetworkCheck$zsHERH6rdnw687YTxpZCLL-Ii-w2 r2 = new com.oculus.alpenglow.os.-$$Lambda$NetworkCheck$zsHERH6rdnw687YTxpZCLL-Ii-w2
                                    r2.<init>(r5, r4, r3, r1)
                                    long r0 = com.oculus.alpenglow.os.NetworkCheck.WIFI_CHECK_RETRY_INTERVAL
                                    r3.postDelayed(r2, r0)
                                    return
                                */
                                throw new UnsupportedOperationException("Method not decompiled: com.oculus.alpenglow.os.$$Lambda$NetworkCheck$KfKEtkDARhcKPPuXw1EX52kf6MU2.run():void");
                            }
                        });
                        return;
                    }
                    return;
                }
            }
            AnonymousClass0NK.A01(str, str2);
        }
    };
    @Nullable
    public IntentFilter mIntentFilter = null;
    @Inject
    @Eager
    public OculusIntentLogger mOculusIntentLogger;
    public final AbstractC04970iB mReporter = new BLogDebugReporter(getClass().getName());

    @Override // X.AbstractC02800aq
    public final Object A04(AbstractC04990iH r1) {
        return this;
    }

    @Override // X.AbstractC02800aq
    public final boolean A07(String str) {
        return false;
    }

    @Override // X.AbstractC02800aq
    public final boolean A06(Context context, Intent intent) {
        IntentFilter intentFilter = this.mIntentFilter;
        if ((intentFilter == null || intentFilter.hasAction(intent.getAction())) && super.A06(context, intent)) {
            return true;
        }
        return false;
    }

    public OculusSecureBroadcastReceiverBase(String... strArr) {
        int length = strArr.length;
        if (length > 0) {
            this.mIntentFilter = new IntentFilter();
            for (String str : strArr) {
                this.mIntentFilter.addAction(str);
            }
        }
    }

    @Override // X.AbstractC02800aq
    public final void A05(Context context) {
        super.A05(context);
        if (this.mOculusIntentLogger == null || AnonymousClass0Lh.A03(0, 131, this._UL_mInjectionContext) == null) {
            AnonymousClass0Lh r2 = AnonymousClass0Lh.get(context);
            this._UL_mInjectionContext = new AnonymousClass0R7(1, r2);
            this.mOculusIntentLogger = (OculusIntentLogger) AnonymousClass13m.A00(23, r2);
        }
    }

    @Override // X.AbstractC02800aq
    public final AbstractC02820as A01() {
        return this.mOculusIntentLogger;
    }

    @Override // X.AbstractC02800aq
    public final AbstractC04970iB A02() {
        return this.mReporter;
    }

    @Override // X.AbstractC02800aq
    @Nullable
    public final AbstractC04990iH A03(Context context, String str) {
        return this.mActionReceiver;
    }
}
