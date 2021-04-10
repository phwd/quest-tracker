package com.oculus.horizon.platformpush;

import X.AbstractC06640p5;
import X.AnonymousClass0DC;
import X.AnonymousClass0J2;
import X.AnonymousClass0QC;
import X.C07190ra;
import com.facebook.ultralight.Dependencies;
import com.facebook.ultralight.Inject;
import com.oculus.horizon.push.FbnsPushHandler;
import com.oculus.horizon.remotewipe.RemoteWipeManager;
import com.oculus.remotewipe.Source;
import com.oculus.remotewipe.WipeRequester;
import java.util.Collections;
import java.util.HashSet;
import java.util.Set;
import org.json.JSONObject;

@Dependencies({"_UL__ULSEP_com_oculus_antipiracy_AntiPiracyPushHelper_ULSEP_BINDING_ID", "_UL__ULSEP_com_oculus_errorreporting_interfaces_IErrorReporter_ULSEP_BINDING_ID", "_UL__ULSEP_com_oculus_horizon_remotewipe_RemoteWipeManager_ULSEP_BINDING_ID", "_UL__ULSEP_android_content_Context_ULSEP_com_facebook_inject_ForAppContext_ULSEP_BINDING_ID"})
public class PlatformPushHandler implements FbnsPushHandler {
    public static final String CORE_APP_MESSAGE_DISABLE_DEVELOPER_MODE = "oculus_standalone_disable_developer_mode";
    public static final String CORE_APP_MESSAGE_REFRESH_USERS = "oculus_standalone_refresh_users";
    public static final String CORE_APP_MESSAGE_REMOTE_WIPE = "oculus_standalone_remote_wipe";
    public static final String ERROR_CATEGORY_PLATFORM_PUSH_JSON_EXCEPTION = "PlatformPushJsonException";
    public static final Set<String> HANDLED_NOTIFICATION_TYPES;
    public static final String JSON_EXTRA_DATA = "extra_data";
    public static final String JSON_MESSAGE_TYPE = "message_type";
    public static final String JSON_PARAMS = "params";
    public static final String JSON_PAYLOAD = "payload";
    public static final String PUSH_TYPE_APP_DATA = "oc_app_data";
    public static final String PUSH_TYPE_CORE_APP_DATA = "oc_core_app_data";
    public static final String TAG = "PlatformPushHandler";
    public AnonymousClass0QC _UL_mInjectionContext;

    static {
        String[] strArr = {PUSH_TYPE_APP_DATA, PUSH_TYPE_CORE_APP_DATA};
        HashSet A01 = C07190ra.A01(2);
        Collections.addAll(A01, strArr);
        HANDLED_NOTIFICATION_TYPES = A01;
    }

    @Override // com.oculus.horizon.push.FbnsPushHandler
    public final void A6M(String str, JSONObject jSONObject) {
        try {
            int hashCode = str.hashCode();
            if (hashCode != -337372035) {
                if (hashCode == 1384762643 && str.equals(PUSH_TYPE_APP_DATA)) {
                    jSONObject.toString();
                    return;
                }
            } else if (str.equals(PUSH_TYPE_CORE_APP_DATA)) {
                JSONObject jSONObject2 = new JSONObject(jSONObject.getJSONObject("params").getString("extra_data"));
                String string = jSONObject2.getString("message_type");
                jSONObject2.getString("payload");
                if (CORE_APP_MESSAGE_REMOTE_WIPE.equals(string)) {
                    RemoteWipeManager.A00((RemoteWipeManager) AnonymousClass0J2.A03(2, 285, this._UL_mInjectionContext), new WipeRequester(Source.PUSH_NOTIFICATION));
                    return;
                } else if (CORE_APP_MESSAGE_DISABLE_DEVELOPER_MODE.equals(string)) {
                    AnonymousClass0DC.A06(
                    /*  JADX ERROR: Method code generation error
                        jadx.core.utils.exceptions.CodegenException: Error generate insn: 0x0075: INVOKE  
                          (wrap: com.oculus.antipiracy.AntiPiracyPushHelper$1 : 0x0072: CONSTRUCTOR  (r0v28 com.oculus.antipiracy.AntiPiracyPushHelper$1) = 
                          (wrap: com.oculus.antipiracy.AntiPiracyPushHelper : 0x006e: CHECK_CAST (r1v13 com.oculus.antipiracy.AntiPiracyPushHelper) = (com.oculus.antipiracy.AntiPiracyPushHelper) (wrap: java.lang.Object : 0x006a: INVOKE  (r1v12 java.lang.Object) = 
                          (0 int)
                          (496 int)
                          (wrap: X.0QC : 0x0068: IGET  (r0v27 X.0QC) = (r6v0 'this' com.oculus.horizon.platformpush.PlatformPushHandler A[IMMUTABLE_TYPE, THIS]) com.oculus.horizon.platformpush.PlatformPushHandler._UL_mInjectionContext X.0QC)
                         type: STATIC call: X.0J2.A03(int, int, X.0QC):java.lang.Object))
                         call: com.oculus.antipiracy.AntiPiracyPushHelper.1.<init>(com.oculus.antipiracy.AntiPiracyPushHelper):void type: CONSTRUCTOR)
                         type: STATIC call: X.0DC.A06(java.util.concurrent.Callable):X.0DC in method: com.oculus.horizon.platformpush.PlatformPushHandler.A6M(java.lang.String, org.json.JSONObject):void, file: classes2.dex
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
                        	at jadx.core.codegen.RegionGen.connectElseIf(RegionGen.java:176)
                        	at jadx.core.codegen.RegionGen.makeIf(RegionGen.java:153)
                        	at jadx.core.codegen.RegionGen.makeRegion(RegionGen.java:63)
                        	at jadx.core.codegen.RegionGen.makeSimpleRegion(RegionGen.java:93)
                        	at jadx.core.codegen.RegionGen.makeRegion(RegionGen.java:59)
                        	at jadx.core.codegen.RegionGen.makeRegionIndent(RegionGen.java:99)
                        	at jadx.core.codegen.RegionGen.makeTryCatch(RegionGen.java:306)
                        	at jadx.core.codegen.RegionGen.makeRegion(RegionGen.java:69)
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
                        Caused by: jadx.core.utils.exceptions.CodegenException: Error generate insn: 0x0072: CONSTRUCTOR  (r0v28 com.oculus.antipiracy.AntiPiracyPushHelper$1) = 
                          (wrap: com.oculus.antipiracy.AntiPiracyPushHelper : 0x006e: CHECK_CAST (r1v13 com.oculus.antipiracy.AntiPiracyPushHelper) = (com.oculus.antipiracy.AntiPiracyPushHelper) (wrap: java.lang.Object : 0x006a: INVOKE  (r1v12 java.lang.Object) = 
                          (0 int)
                          (496 int)
                          (wrap: X.0QC : 0x0068: IGET  (r0v27 X.0QC) = (r6v0 'this' com.oculus.horizon.platformpush.PlatformPushHandler A[IMMUTABLE_TYPE, THIS]) com.oculus.horizon.platformpush.PlatformPushHandler._UL_mInjectionContext X.0QC)
                         type: STATIC call: X.0J2.A03(int, int, X.0QC):java.lang.Object))
                         call: com.oculus.antipiracy.AntiPiracyPushHelper.1.<init>(com.oculus.antipiracy.AntiPiracyPushHelper):void type: CONSTRUCTOR in method: com.oculus.horizon.platformpush.PlatformPushHandler.A6M(java.lang.String, org.json.JSONObject):void, file: classes2.dex
                        	at jadx.core.codegen.InsnGen.makeInsn(InsnGen.java:255)
                        	at jadx.core.codegen.InsnGen.addWrappedArg(InsnGen.java:119)
                        	at jadx.core.codegen.InsnGen.addArg(InsnGen.java:103)
                        	at jadx.core.codegen.InsnGen.generateMethodArguments(InsnGen.java:806)
                        	at jadx.core.codegen.InsnGen.makeInvoke(InsnGen.java:746)
                        	at jadx.core.codegen.InsnGen.makeInsnBody(InsnGen.java:367)
                        	at jadx.core.codegen.InsnGen.makeInsn(InsnGen.java:249)
                        	... 33 more
                        Caused by: jadx.core.utils.exceptions.JadxRuntimeException: Expected class to be processed at this point, class: com.oculus.antipiracy.AntiPiracyPushHelper, state: GENERATED_AND_UNLOADED
                        	at jadx.core.dex.nodes.ClassNode.ensureProcessed(ClassNode.java:215)
                        	at jadx.core.codegen.InsnGen.makeConstructor(InsnGen.java:630)
                        	at jadx.core.codegen.InsnGen.makeInsnBody(InsnGen.java:363)
                        	at jadx.core.codegen.InsnGen.makeInsn(InsnGen.java:230)
                        	... 39 more
                        */
                    /*
                    // Method dump skipped, instructions count: 231
                    */
                    throw new UnsupportedOperationException("Method not decompiled: com.oculus.horizon.platformpush.PlatformPushHandler.A6M(java.lang.String, org.json.JSONObject):void");
                }

                @Inject
                public PlatformPushHandler(AbstractC06640p5 r3) {
                    this._UL_mInjectionContext = new AnonymousClass0QC(4, r3);
                }

                @Override // com.oculus.horizon.push.FbnsPushHandler
                public final Set<String> A3T() {
                    return HANDLED_NOTIFICATION_TYPES;
                }
            }
