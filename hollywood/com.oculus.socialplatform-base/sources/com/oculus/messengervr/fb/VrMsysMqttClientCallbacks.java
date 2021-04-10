package com.oculus.messengervr.fb;

import X.AbstractC12551xm;
import X.AbstractC13521zj;
import X.AbstractC13601zr;
import X.AbstractC141321x;
import X.AnonymousClass007;
import X.AnonymousClass1Kw;
import X.AnonymousClass1R6;
import X.AnonymousClass219;
import X.AnonymousClass220;
import X.AnonymousClass221;
import X.AnonymousClass222;
import X.AnonymousClass223;
import X.AnonymousClass224;
import X.AnonymousClass225;
import X.AnonymousClass226;
import X.AnonymousClass227;
import X.AnonymousClass22G;
import X.C13511zi;
import X.C13541zl;
import X.C137220e;
import X.C141421y;
import X.C141521z;
import X.C142422m;
import android.annotation.TargetApi;
import android.app.Application;
import android.text.TextUtils;
import androidx.annotation.Nullable;
import com.facebook.infer.annotation.Nullsafe;
import com.facebook.msys.mcd.MqttNetworkSessionPlugin;
import com.facebook.msys.mci.Execution;
import com.oculus.messengervr.common.utils.Triplet;
import com.oculus.messengervr.fb.utils.LogUtil;
import java.nio.charset.StandardCharsets;
import java.util.Collections;
import java.util.List;
import java.util.Map;
import java.util.Objects;
import java.util.concurrent.atomic.AtomicBoolean;
import java.util.concurrent.atomic.AtomicInteger;

@TargetApi(25)
@Nullsafe(Nullsafe.Mode.LOCAL)
public final class VrMsysMqttClientCallbacks {
    public static final String CLIENT_TYPE = "";
    public static final String MQTT_TOPIC_LS_RESP = "/ls_resp";
    public final AtomicBoolean mCanProcessPublish = new AtomicBoolean(false);
    public final AtomicInteger mCurrentPublishToken = new AtomicInteger();
    public final AbstractC141321x mMqttClient;
    public final AtomicInteger mPendingPublishCount = new AtomicInteger();
    public final C13511zi<Triplet<String, byte[], Integer>> mPublishSource = new C13511zi<>();
    @Nullable
    public AbstractC12551xm mPublishingSubscription;

    public /* synthetic */ void lambda$getMqttClientInitParams$2$VrMsysMqttClientCallbacks(C141421y r5) {
        switch (r5.A00.ordinal()) {
            case 0:
                this.mCanProcessPublish.set(false);
                Execution.executeAsyncWithPriority(new AnonymousClass221(MqttNetworkSessionPlugin.sInstance), 3, 0);
                return;
            case 1:
                this.mCanProcessPublish.set(true);
                int i = this.mPendingPublishCount.get();
                if (i > 0) {
                    ((AbstractC12551xm) Objects.requireNonNull(this.mPublishingSubscription, "mPublishingSubscription should not be null.")).request((long) i);
                }
                Execution.executeAsyncWithPriority(new AnonymousClass222(MqttNetworkSessionPlugin.sInstance), 3, 0);
                return;
            case 2:
                this.mCanProcessPublish.set(false);
                Execution.executeAsyncWithPriority(new AnonymousClass223(MqttNetworkSessionPlugin.sInstance), 3, 0);
                return;
            default:
                return;
        }
    }

    public void subscribeToTopic(String str) {
    }

    public void unsubscribeFromTopic(String str) {
    }

    /* renamed from: com.oculus.messengervr.fb.VrMsysMqttClientCallbacks$3  reason: invalid class name */
    public static /* synthetic */ class AnonymousClass3 {
        public static final /* synthetic */ int[] $SwitchMap$com$facebook$mqtt$client$MqttChannelState$ConnectionState;

        /* JADX WARNING: Can't wrap try/catch for region: R(8:0|1|2|3|4|5|6|8) */
        /* JADX WARNING: Failed to process nested try/catch */
        /* JADX WARNING: Missing exception handler attribute for start block: B:3:0x0012 */
        /* JADX WARNING: Missing exception handler attribute for start block: B:5:0x001b */
        static {
            /*
                X.22D[] r0 = X.AnonymousClass22D.values()
                int r0 = r0.length
                int[] r2 = new int[r0]
                com.oculus.messengervr.fb.VrMsysMqttClientCallbacks.AnonymousClass3.$SwitchMap$com$facebook$mqtt$client$MqttChannelState$ConnectionState = r2
                X.22D r0 = X.AnonymousClass22D.CONNECTED     // Catch:{ NoSuchFieldError -> 0x0012 }
                int r1 = r0.ordinal()     // Catch:{ NoSuchFieldError -> 0x0012 }
                r0 = 1
                r2[r1] = r0     // Catch:{ NoSuchFieldError -> 0x0012 }
            L_0x0012:
                X.22D r0 = X.AnonymousClass22D.CONNECTING     // Catch:{ NoSuchFieldError -> 0x001b }
                int r1 = r0.ordinal()     // Catch:{ NoSuchFieldError -> 0x001b }
                r0 = 2
                r2[r1] = r0     // Catch:{ NoSuchFieldError -> 0x001b }
            L_0x001b:
                X.22D r0 = X.AnonymousClass22D.DISCONNECTED     // Catch:{ NoSuchFieldError -> 0x0024 }
                int r1 = r0.ordinal()     // Catch:{ NoSuchFieldError -> 0x0024 }
                r0 = 3
                r2[r1] = r0     // Catch:{ NoSuchFieldError -> 0x0024 }
            L_0x0024:
                return
            */
            throw new UnsupportedOperationException("Method not decompiled: com.oculus.messengervr.fb.VrMsysMqttClientCallbacks.AnonymousClass3.<clinit>():void");
        }
    }

    public static AnonymousClass2 getConfig(final AnonymousClass1Kw r1) {
        return new Object() {
            /* class com.oculus.messengervr.fb.VrMsysMqttClientCallbacks.AnonymousClass2 */

            @Nullable
            public Map<String, String> getAppSpecificInfo() {
                return null;
            }

            @Nullable
            public String getCustomAnalyticsEventNameSuffix() {
                return null;
            }

            public long getEndpointCapabilities() {
                return 0;
            }

            public int getHealthStatsSamplingRate() {
                return 1;
            }

            public String getMqttConnectionConfig() {
                return "{}";
            }

            public String getMqttConnectionPreferredSandbox() {
                return "";
            }

            public String getMqttConnectionPreferredTier() {
                return "default";
            }

            @Nullable
            public String getRequestRoutingRegion() {
                return null;
            }

            public AnonymousClass1Kw getAnalyticsLogger() {
                return AnonymousClass1Kw.this;
            }

            public AnonymousClass1R6 getKeepaliveParams() {
                return new AnonymousClass1R6();
            }
        };
    }

    public static /* synthetic */ void lambda$getMqttClientInitParams$0(AnonymousClass227 r5) {
        String str = r5.A00;
        byte[] bArr = r5.A01;
        new String(bArr, StandardCharsets.UTF_8);
        if (str.equals(MQTT_TOPIC_LS_RESP)) {
            Execution.executeAsyncWithPriority(new AnonymousClass220(MqttNetworkSessionPlugin.sInstance, bArr), 3, 0);
        }
    }

    public void destroy() {
        this.mMqttClient.destroy();
    }

    public int onGetConnectionState() {
        switch (this.mMqttClient.A4V().A00.A00.ordinal()) {
            case 0:
                return 1;
            case 1:
                return 2;
            default:
                return 0;
        }
    }

    public int onPublish(String str, int i, byte[] bArr) {
        int incrementAndGet = this.mCurrentPublishToken.incrementAndGet();
        Integer valueOf = Integer.valueOf(incrementAndGet);
        new String(bArr, StandardCharsets.UTF_8);
        this.mPublishSource.onNext(new Triplet<>(str, bArr, valueOf));
        this.mPendingPublishCount.incrementAndGet();
        if (this.mCanProcessPublish.get()) {
            ((AbstractC12551xm) Objects.requireNonNull(this.mPublishingSubscription, "mPublishingSubscription should not be null.")).request(1);
        }
        return incrementAndGet;
    }

    public void start() {
        this.mMqttClient.start();
    }

    public void stop() {
        this.mMqttClient.stop();
    }

    public VrMsysMqttClientCallbacks(Application application, String str, String str2, String str3, String str4, String str5, final AnonymousClass1Kw r11) {
        AnonymousClass22G r1 = new AnonymousClass22G(new Object() {
            /* class com.oculus.messengervr.fb.VrMsysMqttClientCallbacks.AnonymousClass2 */

            @Nullable
            public Map<String, String> getAppSpecificInfo() {
                return null;
            }

            @Nullable
            public String getCustomAnalyticsEventNameSuffix() {
                return null;
            }

            public long getEndpointCapabilities() {
                return 0;
            }

            public int getHealthStatsSamplingRate() {
                return 1;
            }

            public String getMqttConnectionConfig() {
                return "{}";
            }

            public String getMqttConnectionPreferredSandbox() {
                return "";
            }

            public String getMqttConnectionPreferredTier() {
                return "default";
            }

            @Nullable
            public String getRequestRoutingRegion() {
                return null;
            }

            public AnonymousClass1Kw getAnalyticsLogger() {
                return AnonymousClass1Kw.this;
            }

            public AnonymousClass1R6 getKeepaliveParams() {
                return new AnonymousClass1R6();
            }
        });
        this.mMqttClient = r1;
        r1.A5c(getMqttClientInitParams(application, str, str2, str3, str4, str5));
        C13511zi<Triplet<String, byte[], Integer>> r3 = this.mPublishSource;
        int i = AbstractC13521zj.A00;
        AnonymousClass219.A00(i, "bufferSize");
        new C13541zl(r3, i, C137220e.A03).A00(new AbstractC13601zr<Triplet<String, byte[], Integer>>() {
            /* class com.oculus.messengervr.fb.VrMsysMqttClientCallbacks.AnonymousClass1 */

            @Override // X.AbstractC13581zp
            public void onComplete() {
            }

            @Override // X.AbstractC13581zp
            public void onError(Throwable th) {
                LogUtil.e(LogUtil.MQTT, th, "mPublishSource error", new Object[0]);
            }

            @Override // X.AbstractC13581zp
            public void onSubscribe(AbstractC12551xm r2) {
                VrMsysMqttClientCallbacks.this.mPublishingSubscription = r2;
            }

            public void onNext(final Triplet<String, byte[], Integer> triplet) {
                VrMsysMqttClientCallbacks.this.mPendingPublishCount.decrementAndGet();
                VrMsysMqttClientCallbacks.this.mMqttClient.A8i(triplet.first, (byte[]) triplet.second, AnonymousClass007.A01, null, new Object() {
                    /* class com.oculus.messengervr.fb.VrMsysMqttClientCallbacks.AnonymousClass1.AnonymousClass1 */

                    public void onFailure(int i) {
                        Execution.executeAsyncWithPriority(new AnonymousClass226(MqttNetworkSessionPlugin.sInstance, triplet.third.intValue()), 3, 0);
                    }

                    public void onPubAckTimeout(int i) {
                        Execution.executeAsyncWithPriority(new AnonymousClass225(MqttNetworkSessionPlugin.sInstance, triplet.third.intValue()), 3, 0);
                    }

                    public void onSuccess(int i) {
                        Execution.executeAsyncWithPriority(new AnonymousClass224(MqttNetworkSessionPlugin.sInstance, triplet.third.intValue()), 3, 0);
                    }
                });
            }
        });
    }

    private C142422m getMqttClientInitParams(Application application, String str, String str2, String str3, String str4, String str5) {
        C141521z r2;
        if (TextUtils.isEmpty(str3) || TextUtils.isEmpty(str4)) {
            r2 = C141521z.A00;
        } else {
            r2 = new C141521z(str3, str4);
        }
        List singletonList = Collections.singletonList(MQTT_TOPIC_LS_RESP);
        return new C142422m(application, r2, str, str5, str2, $$Lambda$VrMsysMqttClientCallbacks$O_Zp9F_wgej5LDIVLNVImUs2JG02.INSTANCE, new Object() {
            /* class com.oculus.messengervr.fb.$$Lambda$VrMsysMqttClientCallbacks$YQAzKNR0OPr5z4Ya6bxvedcq9Ws2 */

            public final void onChannelStateChanged(C141421y r2) {
                VrMsysMqttClientCallbacks.this.lambda$getMqttClientInitParams$2$VrMsysMqttClientCallbacks(r2);
            }
        }, $$Lambda$VrMsysMqttClientCallbacks$fDCk4RJ2sVQX3AFy96f42X6u_uY2.INSTANCE, singletonList);
    }

    public void onCancelPublish(int i) {
    }

    public static /* synthetic */ void lambda$getMqttClientInitParams$1(String str, long j, boolean z) {
    }
}
