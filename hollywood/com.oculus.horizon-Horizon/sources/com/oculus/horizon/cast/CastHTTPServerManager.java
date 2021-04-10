package com.oculus.horizon.cast;

import X.AbstractC06640p5;
import X.AnonymousClass0NO;
import X.AnonymousClass0QC;
import android.annotation.SuppressLint;
import android.content.Context;
import android.os.Handler;
import com.facebook.infer.annotation.Nullsafe;
import com.facebook.inject.Assisted;
import com.facebook.ultralight.Dependencies;
import com.facebook.ultralight.Inject;
import javax.annotation.Nullable;

@Dependencies({"_UL__ULSEP_com_oculus_horizon_cast_CastAnalytics_ULSEP_BINDING_ID", "_UL__ULSEP_com_oculus_debug_DebugMode_ULSEP_BINDING_ID", "_UL__ULSEP_com_facebook_mobileconfig_factory_MobileConfig_ULSEP_BINDING_ID"})
@Nullsafe(Nullsafe.Mode.LOCAL)
public class CastHTTPServerManager {
    public static final int CAST_EVENT_MESSAGE_ID_ERROR = 3;
    public static final int CAST_EVENT_MESSAGE_ID_START = 1;
    public static final int CAST_EVENT_MESSAGE_ID_STOP = 2;
    public static final String TAG = "CastHTTPServerManager";
    public AnonymousClass0QC _UL_mInjectionContext;
    @Nullable
    public CastHTTPServerBase mCastHTTPServer;
    public final Context mContext;
    public Handler mEventHandler;

    public enum CastServerType {
        MOBILE_DEVICE,
        WEB
    }

    @SuppressLint({"EqualsWithoutHashCode"})
    public static class StartCastParams {
        public final boolean enableDataChannel;
        public final String endpoint = CastHTTPServerBase.LOCALHOST;
        public final String sessionId;
        public final VideoSpec spec;

        public final boolean equals(@Nullable Object obj) {
            if (obj == null) {
                return false;
            }
            StartCastParams startCastParams = (StartCastParams) obj;
            return this.endpoint.equals(startCastParams.endpoint) && this.sessionId.equals(startCastParams.sessionId) && this.spec.equals(startCastParams.spec) && this.enableDataChannel == startCastParams.enableDataChannel;
        }

        public StartCastParams(String str, VideoSpec videoSpec, boolean z) {
            this.sessionId = str;
            this.spec = videoSpec;
            this.enableDataChannel = z;
        }
    }

    @Nullable
    public final synchronized String A01() {
        String str;
        CastHTTPServerBase castHTTPServerBase = this.mCastHTTPServer;
        if (castHTTPServerBase == null) {
            str = null;
        } else {
            synchronized (castHTTPServerBase.mLock) {
                str = castHTTPServerBase.mSessionId;
            }
        }
        return str;
    }

    public final synchronized void A02(String str, String str2, boolean z, boolean z2) {
        CastHTTPServerBase castHTTPServerBase = this.mCastHTTPServer;
        if (castHTTPServerBase != null) {
            if (!(castHTTPServerBase instanceof CastHTTPServerForMobileDevice)) {
                AnonymousClass0NO.A08(TAG, "sendAppInfo error: not a CastHTTPServer for mobile");
            } else {
                CastHTTPServerForMobileDevice castHTTPServerForMobileDevice = (CastHTTPServerForMobileDevice) castHTTPServerBase;
                synchronized (castHTTPServerForMobileDevice.mLock) {
                    if (castHTTPServerForMobileDevice.mSocket != null && castHTTPServerForMobileDevice.mSessionId != null && castHTTPServerForMobileDevice.mEnableSendAppInfo.get() && !castHTTPServerForMobileDevice.mIsDataChannelEnabled.get()) {
                        castHTTPServerForMobileDevice.mEventHandler.post(
                        /*  JADX ERROR: Method code generation error
                            jadx.core.utils.exceptions.CodegenException: Error generate insn: 0x004a: INVOKE  
                              (wrap: android.os.Handler : 0x0048: IGET  (r0v11 android.os.Handler) = (r3v1 'castHTTPServerForMobileDevice' com.oculus.horizon.cast.CastHTTPServerForMobileDevice) com.oculus.horizon.cast.CastHTTPServerBase.mEventHandler android.os.Handler)
                              (wrap: com.oculus.horizon.cast.CastHTTPServerForMobileDevice$1 : 0x0045: CONSTRUCTOR  (r1v0 com.oculus.horizon.cast.CastHTTPServerForMobileDevice$1) = 
                              (r3v1 'castHTTPServerForMobileDevice' com.oculus.horizon.cast.CastHTTPServerForMobileDevice)
                              (wrap: java.lang.String : 0x003f: INVOKE  (r0v10 java.lang.String) = 
                              (wrap: org.json.JSONObject : 0x003b: INVOKE  (r0v9 org.json.JSONObject) = 
                              (wrap: com.oculus.horizon.cast.Message : 0x0038: CONSTRUCTOR  (r4v0 com.oculus.horizon.cast.Message) = 
                              (wrap: java.lang.String : 0x002e: IGET  (r5v0 java.lang.String) = (r3v1 'castHTTPServerForMobileDevice' com.oculus.horizon.cast.CastHTTPServerForMobileDevice) com.oculus.horizon.cast.CastHTTPServerBase.mSessionId java.lang.String)
                              (wrap: com.oculus.horizon.cast.Message$Type : 0x0030: SGET  (r6v0 com.oculus.horizon.cast.Message$Type) =  com.oculus.horizon.cast.Message.Type.APPINFO com.oculus.horizon.cast.Message$Type)
                              (r12v0 'str' java.lang.String)
                              (r13v0 'str2' java.lang.String)
                              (r14v0 'z' boolean)
                              (r15v0 'z2' boolean)
                             call: com.oculus.horizon.cast.Message.<init>(java.lang.String, com.oculus.horizon.cast.Message$Type, java.lang.String, java.lang.String, boolean, boolean):void type: CONSTRUCTOR)
                             type: VIRTUAL call: com.oculus.horizon.cast.Message.A01():org.json.JSONObject)
                             type: VIRTUAL call: java.lang.Object.toString():java.lang.String)
                             call: com.oculus.horizon.cast.CastHTTPServerForMobileDevice.1.<init>(com.oculus.horizon.cast.CastHTTPServerForMobileDevice, java.lang.String):void type: CONSTRUCTOR)
                             type: VIRTUAL call: android.os.Handler.post(java.lang.Runnable):boolean in method: com.oculus.horizon.cast.CastHTTPServerManager.A02(java.lang.String, java.lang.String, boolean, boolean):void, file: classes2.dex
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
                            	at jadx.core.codegen.RegionGen.makeSimpleRegion(RegionGen.java:93)
                            	at jadx.core.codegen.RegionGen.makeRegion(RegionGen.java:59)
                            	at jadx.core.codegen.RegionGen.makeRegionIndent(RegionGen.java:99)
                            	at jadx.core.codegen.RegionGen.makeSynchronizedRegion(RegionGen.java:249)
                            	at jadx.core.codegen.RegionGen.makeRegion(RegionGen.java:71)
                            	at jadx.core.codegen.RegionGen.makeSimpleRegion(RegionGen.java:93)
                            	at jadx.core.codegen.RegionGen.makeRegion(RegionGen.java:59)
                            	at jadx.core.codegen.RegionGen.makeRegionIndent(RegionGen.java:99)
                            	at jadx.core.codegen.RegionGen.makeIf(RegionGen.java:157)
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
                            Caused by: jadx.core.utils.exceptions.CodegenException: Error generate insn: 0x0045: CONSTRUCTOR  (r1v0 com.oculus.horizon.cast.CastHTTPServerForMobileDevice$1) = 
                              (r3v1 'castHTTPServerForMobileDevice' com.oculus.horizon.cast.CastHTTPServerForMobileDevice)
                              (wrap: java.lang.String : 0x003f: INVOKE  (r0v10 java.lang.String) = 
                              (wrap: org.json.JSONObject : 0x003b: INVOKE  (r0v9 org.json.JSONObject) = 
                              (wrap: com.oculus.horizon.cast.Message : 0x0038: CONSTRUCTOR  (r4v0 com.oculus.horizon.cast.Message) = 
                              (wrap: java.lang.String : 0x002e: IGET  (r5v0 java.lang.String) = (r3v1 'castHTTPServerForMobileDevice' com.oculus.horizon.cast.CastHTTPServerForMobileDevice) com.oculus.horizon.cast.CastHTTPServerBase.mSessionId java.lang.String)
                              (wrap: com.oculus.horizon.cast.Message$Type : 0x0030: SGET  (r6v0 com.oculus.horizon.cast.Message$Type) =  com.oculus.horizon.cast.Message.Type.APPINFO com.oculus.horizon.cast.Message$Type)
                              (r12v0 'str' java.lang.String)
                              (r13v0 'str2' java.lang.String)
                              (r14v0 'z' boolean)
                              (r15v0 'z2' boolean)
                             call: com.oculus.horizon.cast.Message.<init>(java.lang.String, com.oculus.horizon.cast.Message$Type, java.lang.String, java.lang.String, boolean, boolean):void type: CONSTRUCTOR)
                             type: VIRTUAL call: com.oculus.horizon.cast.Message.A01():org.json.JSONObject)
                             type: VIRTUAL call: java.lang.Object.toString():java.lang.String)
                             call: com.oculus.horizon.cast.CastHTTPServerForMobileDevice.1.<init>(com.oculus.horizon.cast.CastHTTPServerForMobileDevice, java.lang.String):void type: CONSTRUCTOR in method: com.oculus.horizon.cast.CastHTTPServerManager.A02(java.lang.String, java.lang.String, boolean, boolean):void, file: classes2.dex
                            	at jadx.core.codegen.InsnGen.makeInsn(InsnGen.java:255)
                            	at jadx.core.codegen.InsnGen.addWrappedArg(InsnGen.java:119)
                            	at jadx.core.codegen.InsnGen.addArg(InsnGen.java:103)
                            	at jadx.core.codegen.InsnGen.generateMethodArguments(InsnGen.java:806)
                            	at jadx.core.codegen.InsnGen.makeInvoke(InsnGen.java:746)
                            	at jadx.core.codegen.InsnGen.makeInsnBody(InsnGen.java:367)
                            	at jadx.core.codegen.InsnGen.makeInsn(InsnGen.java:249)
                            	... 40 more
                            Caused by: jadx.core.utils.exceptions.JadxRuntimeException: Expected class to be processed at this point, class: com.oculus.horizon.cast.CastHTTPServerForMobileDevice, state: GENERATED_AND_UNLOADED
                            	at jadx.core.dex.nodes.ClassNode.ensureProcessed(ClassNode.java:215)
                            	at jadx.core.codegen.InsnGen.makeConstructor(InsnGen.java:630)
                            	at jadx.core.codegen.InsnGen.makeInsnBody(InsnGen.java:363)
                            	at jadx.core.codegen.InsnGen.makeInsn(InsnGen.java:230)
                            	... 46 more
                            */
                        /*
                            this = this;
                            monitor-enter(r11)
                            com.oculus.horizon.cast.CastHTTPServerBase r3 = r11.mCastHTTPServer     // Catch:{ all -> 0x0054 }
                            if (r3 == 0) goto L_0x0052
                            boolean r0 = r3 instanceof com.oculus.horizon.cast.CastHTTPServerForMobileDevice     // Catch:{ all -> 0x0054 }
                            if (r0 != 0) goto L_0x0011
                            java.lang.String r1 = "CastHTTPServerManager"
                            java.lang.String r0 = "sendAppInfo error: not a CastHTTPServer for mobile"
                            X.AnonymousClass0NO.A08(r1, r0)     // Catch:{ all -> 0x0054 }
                            goto L_0x0052
                        L_0x0011:
                            com.oculus.horizon.cast.CastHTTPServerForMobileDevice r3 = (com.oculus.horizon.cast.CastHTTPServerForMobileDevice) r3     // Catch:{ all -> 0x0054 }
                            java.lang.Object r2 = r3.mLock     // Catch:{ all -> 0x0054 }
                            monitor-enter(r2)     // Catch:{ all -> 0x0054 }
                            X.1eb r0 = r3.mSocket     // Catch:{ all -> 0x004f }
                            if (r0 == 0) goto L_0x004d
                            java.lang.String r0 = r3.mSessionId     // Catch:{ all -> 0x004f }
                            if (r0 == 0) goto L_0x004d
                            java.util.concurrent.atomic.AtomicBoolean r0 = r3.mEnableSendAppInfo     // Catch:{ all -> 0x004f }
                            boolean r0 = r0.get()     // Catch:{ all -> 0x004f }
                            if (r0 == 0) goto L_0x004d
                            java.util.concurrent.atomic.AtomicBoolean r0 = r3.mIsDataChannelEnabled     // Catch:{ all -> 0x004f }
                            boolean r0 = r0.get()     // Catch:{ all -> 0x004f }
                            if (r0 != 0) goto L_0x004d
                            java.lang.String r5 = r3.mSessionId     // Catch:{ all -> 0x004f }
                            com.oculus.horizon.cast.Message$Type r6 = com.oculus.horizon.cast.Message.Type.APPINFO     // Catch:{ all -> 0x004f }
                            r8 = r13
                            r7 = r12
                            r10 = r15
                            r9 = r14
                            com.oculus.horizon.cast.Message r4 = new com.oculus.horizon.cast.Message     // Catch:{ all -> 0x004f }
                            r4.<init>(r5, r6, r7, r8, r9, r10)     // Catch:{ all -> 0x004f }
                            org.json.JSONObject r0 = r4.A01()     // Catch:{ all -> 0x004f }
                            java.lang.String r0 = r0.toString()     // Catch:{ all -> 0x004f }
                            com.oculus.horizon.cast.CastHTTPServerForMobileDevice$1 r1 = new com.oculus.horizon.cast.CastHTTPServerForMobileDevice$1     // Catch:{ all -> 0x004f }
                            r1.<init>(r0)     // Catch:{ all -> 0x004f }
                            android.os.Handler r0 = r3.mEventHandler     // Catch:{ all -> 0x004f }
                            r0.post(r1)     // Catch:{ all -> 0x004f }
                        L_0x004d:
                            monitor-exit(r2)     // Catch:{ all -> 0x004f }
                            goto L_0x0052
                        L_0x004f:
                            r0 = move-exception
                            monitor-exit(r2)     // Catch:{ all -> 0x004f }
                            throw r0
                        L_0x0052:
                            monitor-exit(r11)
                            return
                        L_0x0054:
                            r0 = move-exception
                            monitor-exit(r11)
                            throw r0
                        */
                        throw new UnsupportedOperationException("Method not decompiled: com.oculus.horizon.cast.CastHTTPServerManager.A02(java.lang.String, java.lang.String, boolean, boolean):void");
                    }

                    /* renamed from: com.oculus.horizon.cast.CastHTTPServerManager$1  reason: invalid class name */
                    public static /* synthetic */ class AnonymousClass1 {
                        public static final /* synthetic */ int[] $SwitchMap$com$oculus$horizon$cast$CastHTTPServerManager$CastServerType;

                        /* JADX WARNING: Can't wrap try/catch for region: R(6:0|1|2|3|4|6) */
                        /* JADX WARNING: Code restructure failed: missing block: B:7:?, code lost:
                            return;
                         */
                        /* JADX WARNING: Failed to process nested try/catch */
                        /* JADX WARNING: Missing exception handler attribute for start block: B:3:0x0012 */
                        static {
                            /*
                                com.oculus.horizon.cast.CastHTTPServerManager$CastServerType[] r0 = com.oculus.horizon.cast.CastHTTPServerManager.CastServerType.values()
                                int r0 = r0.length
                                int[] r2 = new int[r0]
                                com.oculus.horizon.cast.CastHTTPServerManager.AnonymousClass1.$SwitchMap$com$oculus$horizon$cast$CastHTTPServerManager$CastServerType = r2
                                com.oculus.horizon.cast.CastHTTPServerManager$CastServerType r0 = com.oculus.horizon.cast.CastHTTPServerManager.CastServerType.MOBILE_DEVICE     // Catch:{ NoSuchFieldError -> 0x0012 }
                                int r1 = r0.ordinal()     // Catch:{ NoSuchFieldError -> 0x0012 }
                                r0 = 1
                                r2[r1] = r0     // Catch:{ NoSuchFieldError -> 0x0012 }
                            L_0x0012:
                                com.oculus.horizon.cast.CastHTTPServerManager$CastServerType r0 = com.oculus.horizon.cast.CastHTTPServerManager.CastServerType.WEB     // Catch:{ NoSuchFieldError -> 0x001b }
                                int r1 = r0.ordinal()     // Catch:{ NoSuchFieldError -> 0x001b }
                                r0 = 2
                                r2[r1] = r0     // Catch:{ NoSuchFieldError -> 0x001b }
                            L_0x001b:
                                return
                            */
                            throw new UnsupportedOperationException("Method not decompiled: com.oculus.horizon.cast.CastHTTPServerManager.AnonymousClass1.<clinit>():void");
                        }
                    }

                    /* JADX WARNING: Code restructure failed: missing block: B:94:0x0169, code lost:
                        if (r5 == null) goto L_0x017f;
                     */
                    /* Code decompiled incorrectly, please refer to instructions dump. */
                    public final synchronized android.util.Pair<java.lang.String, java.lang.String> A00(com.oculus.horizon.cast.CastHTTPServerManager.CastServerType r21, @javax.annotation.Nullable android.content.Intent r22, com.oculus.horizon.cast.CastHTTPServerBase.StartSource r23) {
                        /*
                        // Method dump skipped, instructions count: 470
                        */
                        throw new UnsupportedOperationException("Method not decompiled: com.oculus.horizon.cast.CastHTTPServerManager.A00(com.oculus.horizon.cast.CastHTTPServerManager$CastServerType, android.content.Intent, com.oculus.horizon.cast.CastHTTPServerBase$StartSource):android.util.Pair");
                    }

                    @Inject
                    public CastHTTPServerManager(AbstractC06640p5 r3, @Assisted Context context, @Assisted Handler handler) {
                        this._UL_mInjectionContext = new AnonymousClass0QC(3, r3);
                        this.mContext = context;
                        this.mEventHandler = handler;
                    }
                }
