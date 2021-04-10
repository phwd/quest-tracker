package com.oculus.unifiedtelemetry.unifiedlogging;

import X.AbstractC0096Hu;
import X.AbstractC0247Xu;
import X.AbstractC0479qV;
import X.C0515sp;
import X.I3;
import X.QC;
import android.annotation.SuppressLint;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.os.UserHandle;
import com.facebook.inject.ApplicationScoped;
import com.facebook.ultralight.Dependencies;
import com.facebook.ultralight.Inject;
import com.oculus.android.os.internal.ContextInternal;
import com.oculus.android.os.internal.IntentInternal;
import com.oculus.android.os.internal.UserHandleInternal;
import com.oculus.auth.service.contract.ServiceContract;
import com.oculus.executors.OculusThreadExecutor;
import com.oculus.logging.analytics2.EventFactoryWithAnalytics2Support;
import com.oculus.logging.analytics2.LoggingUser;
import com.oculus.logging.funnel.FunnelLoggerManager;
import com.oculus.multiuser.UserClassifier;
import java.util.concurrent.ConcurrentHashMap;

@SuppressLint({"BadMethodUse-java.util.concurrent.ConcurrentHashMap._Constructor"})
@Dependencies({"_UL__ULSEP_android_content_Context_ULSEP_com_facebook_inject_ForAppContext_ULSEP_BINDING_ID", "_UL__ULSEP_com_oculus_unifiedtelemetry_unifiedlogging_SystemLoggingUser_ULSEP_BINDING_ID", "_UL__ULSEP_com_oculus_multiuser_UserClassifier_ULSEP_BINDING_ID", "_UL__ULSEP_com_oculus_unifiedtelemetry_unifiedlogging_NonSystemLoggingUserProvider_ULSEP_BINDING_ID", "_UL__ULSEP_com_oculus_logging_analytics2_EventFactoryWithAnalytics2SupportProvider_ULSEP_BINDING_ID", "_UL__ULSEP_com_oculus_logging_funnel_FunnelLoggerManagerProvider_ULSEP_BINDING_ID", "_UL__ULSEP_com_oculus_unifiedtelemetry_unifiedlogging_PresenceReporterProvider_ULSEP_BINDING_ID", "_UL__ULSEP_com_oculus_android_os_internal_UserHandleInternal_ULSEP_BINDING_ID"})
@ApplicationScoped
public class UserMonitor {
    public static final String NIL_USER_ID = "0";
    public static final String TAG = "UserMonitor";
    public static volatile UserMonitor _UL__ULSEP_com_oculus_unifiedtelemetry_unifiedlogging_UserMonitor_ULSEP_INSTANCE;
    public QC _UL_mInjectionContext;
    @Inject
    public final NonSystemLoggingUserProvider mNonSystemLoggingUserProvider;
    @Inject
    public final PresenceReporterProvider mPresenceReporterProvider;
    public final BroadcastReceiver mReceiver = new MyBroadcastReceiver();
    public final ConcurrentHashMap<UserHandle, User> mUserMap = new ConcurrentHashMap<>();

    @SuppressLint({"BadSuperClassBroadcastReceiver.SecureBroadcastReceiver", "EndpointWithoutSwitchOff"})
    public final class MyBroadcastReceiver extends BroadcastReceiver {
        public MyBroadcastReceiver() {
        }

        public final void onReceive(Context context, Intent intent) {
            UserHandle userHandle;
            String action = intent.getAction();
            if (action != null) {
                int hashCode = action.hashCode();
                if (hashCode != 698177661) {
                    if (hashCode == 798292259 && action.equals("android.intent.action.BOOT_COMPLETED")) {
                        userHandle = UserHandle.of(intent.getIntExtra(IntentInternal.EXTRA_USER_HANDLE, 0));
                    } else {
                        return;
                    }
                } else if (!action.equals(ServiceContract.BROADCAST_LOGIN) || (userHandle = (UserHandle) intent.getParcelableExtra(ServiceContract.EXTRA_USER)) == null) {
                    return;
                }
                LoggingUser loggingUser = UserMonitor.this.A00(userHandle).mLoggingUser;
                if (!(loggingUser instanceof SystemLoggingUser)) {
                    OculusThreadExecutor.A00().execute(
                    /*  JADX ERROR: Method code generation error
                        jadx.core.utils.exceptions.CodegenException: Error generate insn: 0x003e: INVOKE  
                          (wrap: com.oculus.executors.OculusThreadExecutor : 0x0035: INVOKE  (r1v4 com.oculus.executors.OculusThreadExecutor) =  type: STATIC call: com.oculus.executors.OculusThreadExecutor.A00():com.oculus.executors.OculusThreadExecutor)
                          (wrap: com.oculus.unifiedtelemetry.unifiedlogging.NonSystemLoggingUser$1 : 0x003b: CONSTRUCTOR  (r0v7 com.oculus.unifiedtelemetry.unifiedlogging.NonSystemLoggingUser$1) = 
                          (wrap: com.oculus.unifiedtelemetry.unifiedlogging.NonSystemLoggingUser : 0x0033: CHECK_CAST (r2v2 com.oculus.unifiedtelemetry.unifiedlogging.NonSystemLoggingUser) = (com.oculus.unifiedtelemetry.unifiedlogging.NonSystemLoggingUser) (r2v1 'loggingUser' com.oculus.logging.analytics2.LoggingUser))
                         call: com.oculus.unifiedtelemetry.unifiedlogging.NonSystemLoggingUser.1.<init>(com.oculus.unifiedtelemetry.unifiedlogging.NonSystemLoggingUser):void type: CONSTRUCTOR)
                         type: VIRTUAL call: com.oculus.executors.OculusThreadExecutor.execute(java.lang.Runnable):void in method: com.oculus.unifiedtelemetry.unifiedlogging.UserMonitor.MyBroadcastReceiver.onReceive(android.content.Context, android.content.Intent):void, file: classes2.dex
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
                        Caused by: jadx.core.utils.exceptions.CodegenException: Error generate insn: 0x003b: CONSTRUCTOR  (r0v7 com.oculus.unifiedtelemetry.unifiedlogging.NonSystemLoggingUser$1) = 
                          (wrap: com.oculus.unifiedtelemetry.unifiedlogging.NonSystemLoggingUser : 0x0033: CHECK_CAST (r2v2 com.oculus.unifiedtelemetry.unifiedlogging.NonSystemLoggingUser) = (com.oculus.unifiedtelemetry.unifiedlogging.NonSystemLoggingUser) (r2v1 'loggingUser' com.oculus.logging.analytics2.LoggingUser))
                         call: com.oculus.unifiedtelemetry.unifiedlogging.NonSystemLoggingUser.1.<init>(com.oculus.unifiedtelemetry.unifiedlogging.NonSystemLoggingUser):void type: CONSTRUCTOR in method: com.oculus.unifiedtelemetry.unifiedlogging.UserMonitor.MyBroadcastReceiver.onReceive(android.content.Context, android.content.Intent):void, file: classes2.dex
                        	at jadx.core.codegen.InsnGen.makeInsn(InsnGen.java:255)
                        	at jadx.core.codegen.InsnGen.addWrappedArg(InsnGen.java:119)
                        	at jadx.core.codegen.InsnGen.addArg(InsnGen.java:103)
                        	at jadx.core.codegen.InsnGen.generateMethodArguments(InsnGen.java:806)
                        	at jadx.core.codegen.InsnGen.makeInvoke(InsnGen.java:746)
                        	at jadx.core.codegen.InsnGen.makeInsnBody(InsnGen.java:367)
                        	at jadx.core.codegen.InsnGen.makeInsn(InsnGen.java:249)
                        	... 28 more
                        Caused by: jadx.core.utils.exceptions.JadxRuntimeException: Expected class to be processed at this point, class: com.oculus.unifiedtelemetry.unifiedlogging.NonSystemLoggingUser, state: GENERATED_AND_UNLOADED
                        	at jadx.core.dex.nodes.ClassNode.ensureProcessed(ClassNode.java:215)
                        	at jadx.core.codegen.InsnGen.makeConstructor(InsnGen.java:630)
                        	at jadx.core.codegen.InsnGen.makeInsnBody(InsnGen.java:363)
                        	at jadx.core.codegen.InsnGen.makeInsn(InsnGen.java:230)
                        	... 34 more
                        */
                    /*
                        this = this;
                        java.lang.String r3 = r6.getAction()
                        if (r3 == 0) goto L_0x0041
                        int r2 = r3.hashCode()
                        r0 = 698177661(0x299d587d, float:6.987551E-14)
                        r1 = 0
                        if (r2 == r0) goto L_0x0042
                        r0 = 798292259(0x2f94f923, float:2.7098065E-10)
                        if (r2 != r0) goto L_0x0041
                        java.lang.String r0 = "android.intent.action.BOOT_COMPLETED"
                        boolean r0 = r3.equals(r0)
                        if (r0 == 0) goto L_0x0041
                        java.lang.String r0 = "android.intent.extra.user_handle"
                        int r0 = r6.getIntExtra(r0, r1)
                        android.os.UserHandle r1 = android.os.UserHandle.of(r0)
                    L_0x0027:
                        com.oculus.unifiedtelemetry.unifiedlogging.UserMonitor r0 = com.oculus.unifiedtelemetry.unifiedlogging.UserMonitor.this
                        com.oculus.unifiedtelemetry.unifiedlogging.UserMonitor$User r0 = r0.A00(r1)
                        com.oculus.logging.analytics2.LoggingUser r2 = r0.mLoggingUser
                        boolean r0 = r2 instanceof com.oculus.unifiedtelemetry.unifiedlogging.SystemLoggingUser
                        if (r0 != 0) goto L_0x0041
                        com.oculus.unifiedtelemetry.unifiedlogging.NonSystemLoggingUser r2 = (com.oculus.unifiedtelemetry.unifiedlogging.NonSystemLoggingUser) r2
                        com.oculus.executors.OculusThreadExecutor r1 = com.oculus.executors.OculusThreadExecutor.A00()
                        com.oculus.unifiedtelemetry.unifiedlogging.NonSystemLoggingUser$1 r0 = new com.oculus.unifiedtelemetry.unifiedlogging.NonSystemLoggingUser$1
                        r0.<init>()
                        r1.execute(r0)
                    L_0x0041:
                        return
                    L_0x0042:
                        java.lang.String r0 = "com.oculus.auth.BROADCAST_LOGIN"
                        boolean r0 = r3.equals(r0)
                        if (r0 == 0) goto L_0x0041
                        java.lang.String r0 = "user"
                        android.os.Parcelable r1 = r6.getParcelableExtra(r0)
                        android.os.UserHandle r1 = (android.os.UserHandle) r1
                        if (r1 == 0) goto L_0x0041
                        goto L_0x0027
                    */
                    throw new UnsupportedOperationException("Method not decompiled: com.oculus.unifiedtelemetry.unifiedlogging.UserMonitor.MyBroadcastReceiver.onReceive(android.content.Context, android.content.Intent):void");
                }
            }

            public class User {
                public final EventFactoryWithAnalytics2Support mEventFactory;
                public final AbstractC0479qV mFunnelLogger;
                public final LoggingUser mLoggingUser;
                public final PresenceReporter mPresenceReporter;

                public User(UserHandle userHandle, LoggingUser loggingUser) {
                    this.mLoggingUser = loggingUser;
                    EventFactoryWithAnalytics2Support eventFactoryWithAnalytics2Support = new EventFactoryWithAnalytics2Support((I3) AbstractC0096Hu.A03(3, 26, UserMonitor.this._UL_mInjectionContext), loggingUser);
                    this.mEventFactory = eventFactoryWithAnalytics2Support;
                    this.mFunnelLogger = new FunnelLoggerManager((I3) AbstractC0096Hu.A03(4, 49, UserMonitor.this._UL_mInjectionContext), eventFactoryWithAnalytics2Support).mFunnelLogger;
                    this.mPresenceReporter = new PresenceReporter(UserMonitor.this.mPresenceReporterProvider, userHandle);
                }
            }

            public final User A00(UserHandle userHandle) {
                User user;
                User user2 = this.mUserMap.get(userHandle);
                if (user2 != null) {
                    return user2;
                }
                synchronized (this.mUserMap) {
                    user = this.mUserMap.get(userHandle);
                    if (user == null) {
                        user = new User(userHandle, new NonSystemLoggingUser(this.mNonSystemLoggingUserProvider, userHandle));
                        LoggingUser loggingUser = user.mLoggingUser;
                        if (!(loggingUser instanceof SystemLoggingUser)) {
                            OculusThreadExecutor.A00().execute(
                            /*  JADX ERROR: Method code generation error
                                jadx.core.utils.exceptions.CodegenException: Error generate insn: 0x0034: INVOKE  
                                  (wrap: com.oculus.executors.OculusThreadExecutor : 0x002b: INVOKE  (r1v1 com.oculus.executors.OculusThreadExecutor) =  type: STATIC call: com.oculus.executors.OculusThreadExecutor.A00():com.oculus.executors.OculusThreadExecutor)
                                  (wrap: com.oculus.unifiedtelemetry.unifiedlogging.NonSystemLoggingUser$1 : 0x0031: CONSTRUCTOR  (r0v6 com.oculus.unifiedtelemetry.unifiedlogging.NonSystemLoggingUser$1) = 
                                  (wrap: com.oculus.unifiedtelemetry.unifiedlogging.NonSystemLoggingUser : 0x0029: CHECK_CAST (r2v1 com.oculus.unifiedtelemetry.unifiedlogging.NonSystemLoggingUser) = (com.oculus.unifiedtelemetry.unifiedlogging.NonSystemLoggingUser) (r2v0 'loggingUser' com.oculus.logging.analytics2.LoggingUser))
                                 call: com.oculus.unifiedtelemetry.unifiedlogging.NonSystemLoggingUser.1.<init>(com.oculus.unifiedtelemetry.unifiedlogging.NonSystemLoggingUser):void type: CONSTRUCTOR)
                                 type: VIRTUAL call: com.oculus.executors.OculusThreadExecutor.execute(java.lang.Runnable):void in method: com.oculus.unifiedtelemetry.unifiedlogging.UserMonitor.A00(android.os.UserHandle):com.oculus.unifiedtelemetry.unifiedlogging.UserMonitor$User, file: classes2.dex
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
                                	at jadx.core.codegen.RegionGen.makeRegionIndent(RegionGen.java:99)
                                	at jadx.core.codegen.RegionGen.makeSynchronizedRegion(RegionGen.java:249)
                                	at jadx.core.codegen.RegionGen.makeRegion(RegionGen.java:71)
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
                                Caused by: jadx.core.utils.exceptions.CodegenException: Error generate insn: 0x0031: CONSTRUCTOR  (r0v6 com.oculus.unifiedtelemetry.unifiedlogging.NonSystemLoggingUser$1) = 
                                  (wrap: com.oculus.unifiedtelemetry.unifiedlogging.NonSystemLoggingUser : 0x0029: CHECK_CAST (r2v1 com.oculus.unifiedtelemetry.unifiedlogging.NonSystemLoggingUser) = (com.oculus.unifiedtelemetry.unifiedlogging.NonSystemLoggingUser) (r2v0 'loggingUser' com.oculus.logging.analytics2.LoggingUser))
                                 call: com.oculus.unifiedtelemetry.unifiedlogging.NonSystemLoggingUser.1.<init>(com.oculus.unifiedtelemetry.unifiedlogging.NonSystemLoggingUser):void type: CONSTRUCTOR in method: com.oculus.unifiedtelemetry.unifiedlogging.UserMonitor.A00(android.os.UserHandle):com.oculus.unifiedtelemetry.unifiedlogging.UserMonitor$User, file: classes2.dex
                                	at jadx.core.codegen.InsnGen.makeInsn(InsnGen.java:255)
                                	at jadx.core.codegen.InsnGen.addWrappedArg(InsnGen.java:119)
                                	at jadx.core.codegen.InsnGen.addArg(InsnGen.java:103)
                                	at jadx.core.codegen.InsnGen.generateMethodArguments(InsnGen.java:806)
                                	at jadx.core.codegen.InsnGen.makeInvoke(InsnGen.java:746)
                                	at jadx.core.codegen.InsnGen.makeInsnBody(InsnGen.java:367)
                                	at jadx.core.codegen.InsnGen.makeInsn(InsnGen.java:249)
                                	... 35 more
                                Caused by: jadx.core.utils.exceptions.JadxRuntimeException: Expected class to be processed at this point, class: com.oculus.unifiedtelemetry.unifiedlogging.NonSystemLoggingUser, state: GENERATED_AND_UNLOADED
                                	at jadx.core.dex.nodes.ClassNode.ensureProcessed(ClassNode.java:215)
                                	at jadx.core.codegen.InsnGen.makeConstructor(InsnGen.java:630)
                                	at jadx.core.codegen.InsnGen.makeInsnBody(InsnGen.java:363)
                                	at jadx.core.codegen.InsnGen.makeInsn(InsnGen.java:230)
                                	... 41 more
                                */
                            /*
                                this = this;
                                java.util.concurrent.ConcurrentHashMap<android.os.UserHandle, com.oculus.unifiedtelemetry.unifiedlogging.UserMonitor$User> r0 = r5.mUserMap
                                java.lang.Object r4 = r0.get(r6)
                                com.oculus.unifiedtelemetry.unifiedlogging.UserMonitor$User r4 = (com.oculus.unifiedtelemetry.unifiedlogging.UserMonitor.User) r4
                                if (r4 != 0) goto L_0x0041
                                java.util.concurrent.ConcurrentHashMap<android.os.UserHandle, com.oculus.unifiedtelemetry.unifiedlogging.UserMonitor$User> r3 = r5.mUserMap
                                monitor-enter(r3)
                                java.util.concurrent.ConcurrentHashMap<android.os.UserHandle, com.oculus.unifiedtelemetry.unifiedlogging.UserMonitor$User> r0 = r5.mUserMap     // Catch:{ all -> 0x003e }
                                java.lang.Object r4 = r0.get(r6)     // Catch:{ all -> 0x003e }
                                com.oculus.unifiedtelemetry.unifiedlogging.UserMonitor$User r4 = (com.oculus.unifiedtelemetry.unifiedlogging.UserMonitor.User) r4     // Catch:{ all -> 0x003e }
                                if (r4 != 0) goto L_0x003c
                                com.oculus.unifiedtelemetry.unifiedlogging.NonSystemLoggingUserProvider r1 = r5.mNonSystemLoggingUserProvider     // Catch:{ all -> 0x003e }
                                com.oculus.unifiedtelemetry.unifiedlogging.NonSystemLoggingUser r0 = new com.oculus.unifiedtelemetry.unifiedlogging.NonSystemLoggingUser     // Catch:{ all -> 0x003e }
                                r0.<init>(r1, r6)     // Catch:{ all -> 0x003e }
                                com.oculus.unifiedtelemetry.unifiedlogging.UserMonitor$User r4 = new com.oculus.unifiedtelemetry.unifiedlogging.UserMonitor$User     // Catch:{ all -> 0x003e }
                                r4.<init>(r6, r0)     // Catch:{ all -> 0x003e }
                                com.oculus.logging.analytics2.LoggingUser r2 = r4.mLoggingUser     // Catch:{ all -> 0x003e }
                                boolean r0 = r2 instanceof com.oculus.unifiedtelemetry.unifiedlogging.SystemLoggingUser     // Catch:{ all -> 0x003e }
                                if (r0 != 0) goto L_0x0037
                                com.oculus.unifiedtelemetry.unifiedlogging.NonSystemLoggingUser r2 = (com.oculus.unifiedtelemetry.unifiedlogging.NonSystemLoggingUser) r2     // Catch:{ all -> 0x003e }
                                com.oculus.executors.OculusThreadExecutor r1 = com.oculus.executors.OculusThreadExecutor.A00()     // Catch:{ all -> 0x003e }
                                com.oculus.unifiedtelemetry.unifiedlogging.NonSystemLoggingUser$1 r0 = new com.oculus.unifiedtelemetry.unifiedlogging.NonSystemLoggingUser$1     // Catch:{ all -> 0x003e }
                                r0.<init>()     // Catch:{ all -> 0x003e }
                                r1.execute(r0)     // Catch:{ all -> 0x003e }
                            L_0x0037:
                                java.util.concurrent.ConcurrentHashMap<android.os.UserHandle, com.oculus.unifiedtelemetry.unifiedlogging.UserMonitor$User> r0 = r5.mUserMap     // Catch:{ all -> 0x003e }
                                r0.put(r6, r4)     // Catch:{ all -> 0x003e }
                            L_0x003c:
                                monitor-exit(r3)     // Catch:{ all -> 0x003e }
                                return r4
                            L_0x003e:
                                r0 = move-exception
                                monitor-exit(r3)     // Catch:{ all -> 0x003e }
                                throw r0
                            L_0x0041:
                                return r4
                            */
                            throw new UnsupportedOperationException("Method not decompiled: com.oculus.unifiedtelemetry.unifiedlogging.UserMonitor.A00(android.os.UserHandle):com.oculus.unifiedtelemetry.unifiedlogging.UserMonitor$User");
                        }

                        @Inject
                        @SuppressLint({"BadDependencyInjection"})
                        public UserMonitor(AbstractC0247Xu xu) {
                            this._UL_mInjectionContext = new QC(6, xu);
                            this.mNonSystemLoggingUserProvider = (NonSystemLoggingUserProvider) C0515sp.A00(132, xu);
                            this.mPresenceReporterProvider = (PresenceReporterProvider) C0515sp.A00(4, xu);
                            if (((UserClassifier) AbstractC0096Hu.A03(2, 22, this._UL_mInjectionContext)).A01()) {
                                this.mUserMap.put(UserHandleInternal.A02(), new User(UserHandleInternal.A02(), (LoggingUser) AbstractC0096Hu.A03(1, 19, this._UL_mInjectionContext)));
                                IntentFilter intentFilter = new IntentFilter();
                                intentFilter.addAction("android.intent.action.BOOT_COMPLETED");
                                ContextInternal.A00((Context) AbstractC0096Hu.A03(0, 3, this._UL_mInjectionContext), this.mReceiver, UserHandleInternal.A01(), intentFilter, null);
                                IntentFilter intentFilter2 = new IntentFilter();
                                intentFilter2.addAction(ServiceContract.BROADCAST_LOGIN);
                                ContextInternal.A00((Context) AbstractC0096Hu.A03(0, 3, this._UL_mInjectionContext), this.mReceiver, UserHandleInternal.A01(), intentFilter2, ServiceContract.PERMISSION_BROADCAST);
                                return;
                            }
                            throw new IllegalStateException("Not running as system user");
                        }
                    }
