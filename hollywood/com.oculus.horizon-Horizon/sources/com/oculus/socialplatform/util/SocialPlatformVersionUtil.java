package com.oculus.socialplatform.util;

import android.content.Context;
import com.facebook.inject.ForAppContext;
import com.facebook.proguard.annotations.DoNotStrip;
import com.oculus.deviceconfigclient.DeviceConfigCallback;
import com.oculus.deviceconfigclient.DeviceConfigClient;
import java.util.concurrent.ConcurrentLinkedQueue;
import java.util.concurrent.atomic.AtomicBoolean;

public class SocialPlatformVersionUtil {
    public static final String PARTY_INFRA_GK_MC = "oculus_shared_social:is_socialplatform_parties_infra_enabled";
    public static final String TAG = "SocialPlatformVersionUtil";
    public static DeviceConfigClient sDeviceConfigClient;
    @DoNotStrip
    public static AtomicBoolean sHasSubscribeFinished = new AtomicBoolean(false);
    @DoNotStrip
    public static ConcurrentLinkedQueue<SubscribeCallback> sOnSubscribedCallback = new ConcurrentLinkedQueue<>();

    @FunctionalInterface
    public interface SubscribeCallback {
        void onSubscribed(boolean z);
    }

    public static synchronized void A00() {
        synchronized (SocialPlatformVersionUtil.class) {
            sHasSubscribeFinished.set(true);
            boolean A07 = sDeviceConfigClient.A07();
            while (sOnSubscribedCallback.peek() != null) {
                sOnSubscribedCallback.peek().onSubscribed(A07);
                sOnSubscribedCallback.remove();
            }
        }
    }

    public static void A01(@ForAppContext SubscribeCallback subscribeCallback) {
        if (sHasSubscribeFinished.get()) {
            subscribeCallback.onSubscribed(sDeviceConfigClient.A07());
        } else {
            sOnSubscribedCallback.add(subscribeCallback);
        }
    }

    public static boolean A02(@ForAppContext Context context) {
        DeviceConfigClient deviceConfigClient;
        synchronized (SocialPlatformVersionUtil.class) {
            if (sDeviceConfigClient == null) {
                DeviceConfigClient deviceConfigClient2 = new DeviceConfigClient(context);
                sDeviceConfigClient = deviceConfigClient2;
                AnonymousClass1 r2 = new DeviceConfigCallback() {
                    /* class com.oculus.socialplatform.util.SocialPlatformVersionUtil.AnonymousClass1 */
                };
                if (!deviceConfigClient2.mSubscribeSucceeded.get()) {
                    try {
                        deviceConfigClient2.mCallback = r2;
                        DeviceConfigClient.AnonymousClass1 r5 = 
                        /*  JADX ERROR: Method code generation error
                            jadx.core.utils.exceptions.CodegenException: Error generate insn: 0x0023: CONSTRUCTOR  (r5v0 'r5' com.oculus.deviceconfigclient.DeviceConfigClient$1) = 
                              (r3v0 'deviceConfigClient2' com.oculus.deviceconfigclient.DeviceConfigClient)
                              (wrap: long : 0x001d: INVOKE  (r0v12 long) =  type: STATIC call: java.lang.System.currentTimeMillis():long)
                              (r2v0 'r2' com.oculus.socialplatform.util.SocialPlatformVersionUtil$1)
                             call: com.oculus.deviceconfigclient.DeviceConfigClient.1.<init>(com.oculus.deviceconfigclient.DeviceConfigClient, long, com.oculus.deviceconfigclient.DeviceConfigCallback):void type: CONSTRUCTOR in method: com.oculus.socialplatform.util.SocialPlatformVersionUtil.A02(android.content.Context):boolean, file: classes2.dex
                            	at jadx.core.codegen.InsnGen.makeInsn(InsnGen.java:255)
                            	at jadx.core.codegen.InsnGen.makeInsn(InsnGen.java:217)
                            	at jadx.core.codegen.RegionGen.makeSimpleBlock(RegionGen.java:110)
                            	at jadx.core.codegen.RegionGen.makeRegion(RegionGen.java:56)
                            	at jadx.core.codegen.RegionGen.makeSimpleRegion(RegionGen.java:93)
                            	at jadx.core.codegen.RegionGen.makeRegion(RegionGen.java:59)
                            	at jadx.core.codegen.RegionGen.makeRegionIndent(RegionGen.java:99)
                            	at jadx.core.codegen.RegionGen.makeTryCatch(RegionGen.java:306)
                            	at jadx.core.codegen.RegionGen.makeRegion(RegionGen.java:69)
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
                            	at jadx.core.codegen.MethodGen.addRegionInsns(MethodGen.java:244)
                            	at jadx.core.codegen.MethodGen.addInstructions(MethodGen.java:237)
                            	at jadx.core.codegen.ClassGen.addMethodCode(ClassGen.java:342)
                            	at jadx.core.codegen.ClassGen.addMethod(ClassGen.java:295)
                            	at jadx.core.codegen.ClassGen.lambda$addInnerClsAndMethods$2(ClassGen.java:264)
                            	at java.util.stream.ForEachOps$ForEachOp$OfRef.accept(ForEachOps.java:183)
                            	at java.util.ArrayList.forEach(ArrayList.java:1259)
                            	at java.util.stream.SortedOps$RefSortingSink.end(SortedOps.java:395)
                            	at java.util.stream.Sink$ChainedReference.end(Sink.java:258)
                            Caused by: jadx.core.utils.exceptions.JadxRuntimeException: Expected class to be processed at this point, class: com.oculus.deviceconfigclient.DeviceConfigClient, state: GENERATED_AND_UNLOADED
                            	at jadx.core.dex.nodes.ClassNode.ensureProcessed(ClassNode.java:215)
                            	at jadx.core.codegen.InsnGen.makeConstructor(InsnGen.java:630)
                            	at jadx.core.codegen.InsnGen.makeInsnBody(InsnGen.java:363)
                            	at jadx.core.codegen.InsnGen.makeInsn(InsnGen.java:249)
                            	... 36 more
                            */
                        /*
                        // Method dump skipped, instructions count: 195
                        */
                        throw new UnsupportedOperationException("Method not decompiled: com.oculus.socialplatform.util.SocialPlatformVersionUtil.A02(android.content.Context):boolean");
                    }
                }
