package com.facebook.appevents;

import X.AnonymousClass006;
import X.AnonymousClass1dz;
import X.AnonymousClass1eL;
import X.AnonymousClass1fx;
import X.AnonymousClass1gU;
import X.AnonymousClass1gV;
import X.C09401dq;
import android.content.Context;
import android.os.Bundle;
import com.facebook.AccessToken;
import com.facebook.AccessTokenManager;
import com.facebook.FacebookSdk;
import com.facebook.LoggingBehavior;
import com.facebook.internal.AttributionIdentifiers;
import com.facebook.internal.Logger;
import com.facebook.internal.Utility;
import com.facebook.internal.Validate;
import java.util.Map;
import java.util.UUID;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.ScheduledThreadPoolExecutor;
import java.util.concurrent.TimeUnit;

public final class AppEventsLogger {
    public static Context A02;
    public static Object A03 = new Object();
    public static Map<AnonymousClass1gV, AnonymousClass1fx> A04 = new ConcurrentHashMap();
    public static ScheduledThreadPoolExecutor A05;
    public static boolean A06;
    public static String A07;
    public static boolean A08;
    public final AnonymousClass1gV A00;
    public final String A01;

    public static AnonymousClass1fx A00(Context context, AnonymousClass1gV r5) {
        AttributionIdentifiers attributionIdentifiers;
        AnonymousClass1fx r1;
        if (A04.get(r5) == null) {
            attributionIdentifiers = AttributionIdentifiers.getAttributionIdentifiers(context);
        } else {
            attributionIdentifiers = null;
        }
        synchronized (A03) {
            r1 = A04.get(r5);
            if (r1 == null) {
                context.getPackageName();
                r1 = new AnonymousClass1fx(attributionIdentifiers, A01(context));
                A04.put(r5, r1);
            }
        }
        return r1;
    }

    public static String A01(Context context) {
        if (A07 == null) {
            synchronized (A03) {
                if (A07 == null) {
                    String string = context.getSharedPreferences("com.facebook.sdk.appEventPreferences", 0).getString("anonymousAppDeviceGUID", null);
                    A07 = string;
                    if (string == null) {
                        A07 = AnonymousClass006.A05("XZ", UUID.randomUUID().toString());
                        context.getSharedPreferences("com.facebook.sdk.appEventPreferences", 0).edit().putString("anonymousAppDeviceGUID", A07).apply();
                    }
                }
            }
        }
        return A07;
    }

    public static void A02(AppEventsLogger appEventsLogger, String str, Bundle bundle, boolean z) {
        C09401dq r4 = new C09401dq(appEventsLogger.A01, str, bundle, z);
        FacebookSdk.getExecutor().execute(new AnonymousClass1dz(A02, appEventsLogger.A00, r4));
        if (!r4.isImplicit && !A06) {
            if (r4.name == "fb_mobile_activate_app") {
                A06 = true;
            } else {
                Logger.log(LoggingBehavior.APP_EVENTS, "AppEvents", "Warning: Please call AppEventsLogger.activateApp(...)from the long-lived activity's onResume() methodbefore logging other app events.");
            }
        }
    }

    /* JADX WARNING: Code restructure failed: missing block: B:11:0x002c, code lost:
        if (r3.hasNext() == false) goto L_0x0050;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:12:0x002e, code lost:
        r1 = r3.next();
        r2 = A00(com.facebook.appevents.AppEventsLogger.A02, r1);
        r1 = r5.A01.get(r1);
     */
    /* JADX WARNING: Code restructure failed: missing block: B:13:0x0042, code lost:
        monitor-enter(r2);
     */
    /* JADX WARNING: Code restructure failed: missing block: B:15:?, code lost:
        r2.A03.addAll(r1);
     */
    /* JADX WARNING: Code restructure failed: missing block: B:16:0x0048, code lost:
        monitor-exit(r2);
     */
    /* JADX WARNING: Code restructure failed: missing block: B:17:0x0049, code lost:
        r1.size();
     */
    /* JADX WARNING: Code restructure failed: missing block: B:21:0x0050, code lost:
        r3 = null;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:23:?, code lost:
        r6 = new X.AnonymousClass1h6();
        r9 = com.facebook.FacebookSdk.getLimitEventAndDataUsage(com.facebook.appevents.AppEventsLogger.A02);
        r7 = new java.util.ArrayList();
        r13 = r4.iterator();
     */
    /* JADX WARNING: Code restructure failed: missing block: B:25:0x0069, code lost:
        if (r13.hasNext() == false) goto L_0x0145;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:26:0x006b, code lost:
        r8 = (X.AnonymousClass1gV) r13.next();
        r1 = com.facebook.appevents.AppEventsLogger.A03;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:27:0x0073, code lost:
        monitor-enter(r1);
     */
    /* JADX WARNING: Code restructure failed: missing block: B:29:?, code lost:
        r5 = com.facebook.appevents.AppEventsLogger.A04.get(r8);
     */
    /* JADX WARNING: Code restructure failed: missing block: B:30:0x007c, code lost:
        monitor-exit(r1);
     */
    /* JADX WARNING: Code restructure failed: missing block: B:31:0x007d, code lost:
        if (r5 == null) goto L_0x0065;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:32:0x007f, code lost:
        r4 = r8.applicationId;
        r10 = com.facebook.internal.Utility.queryAppSettings(r4, false);
        r4 = com.facebook.GraphRequest.newPostRequest(null, java.lang.String.format(com.facebook.FacebookSdk.PUBLISH_ACTIVITY_PATH, r4), null, null);
        r2 = r4.parameters;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:33:0x0097, code lost:
        if (r2 != null) goto L_0x009e;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:34:0x0099, code lost:
        r2 = new android.os.Bundle();
     */
    /* JADX WARNING: Code restructure failed: missing block: B:35:0x009e, code lost:
        r2.putString("access_token", r8.accessTokenString);
        r4.parameters = r2;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:36:0x00a7, code lost:
        if (r10 == null) goto L_0x0065;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:37:0x00a9, code lost:
        r10 = r10.supportsImplicitLogging;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:38:0x00ab, code lost:
        monitor-enter(r5);
     */
    /* JADX WARNING: Code restructure failed: missing block: B:40:?, code lost:
        r12 = r5.A00;
        r5.A04.addAll(r5.A03);
        r5.A03.clear();
        r11 = new org.json.JSONArray();
        r2 = r5.A04.iterator();
     */
    /* JADX WARNING: Code restructure failed: missing block: B:42:0x00c9, code lost:
        if (r2.hasNext() == false) goto L_0x00dd;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:43:0x00cb, code lost:
        r1 = r2.next();
     */
    /* JADX WARNING: Code restructure failed: missing block: B:44:0x00d1, code lost:
        if (r10 != false) goto L_0x00d7;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:46:0x00d5, code lost:
        if (r1.isImplicit != false) goto L_0x00c5;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:47:0x00d7, code lost:
        r11.put(r1.jsonObject);
     */
    /* JADX WARNING: Code restructure failed: missing block: B:48:0x00dd, code lost:
        r0 = r11.length();
     */
    /* JADX WARNING: Code restructure failed: missing block: B:49:0x00e1, code lost:
        monitor-exit(r5);
     */
    /* JADX WARNING: Code restructure failed: missing block: B:50:0x00e2, code lost:
        if (r0 == 0) goto L_0x0065;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:52:?, code lost:
        r1 = com.facebook.internal.AppEventsLoggerUtility.getJSONObjectForGraphAPICall(com.facebook.internal.AppEventsLoggerUtility.GraphAPIActivityType.CUSTOM_APP_EVENTS, r5.A01, r5.A02, r9, com.facebook.appevents.AppEventsLogger.A02);
     */
    /* JADX WARNING: Code restructure failed: missing block: B:53:0x00f2, code lost:
        if (r5.A00 <= 0) goto L_0x00ff;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:54:0x00f4, code lost:
        r1.put("num_skipped_events", r12);
     */
    /* JADX WARNING: Code restructure failed: missing block: B:55:0x00fa, code lost:
        r1 = new org.json.JSONObject();
     */
    /* JADX WARNING: Code restructure failed: missing block: B:71:0x013f, code lost:
        r0 = th;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:73:0x0142, code lost:
        r0 = th;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:75:0x0144, code lost:
        throw r0;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:77:0x0149, code lost:
        if (r7.size() <= 0) goto L_0x0190;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:78:0x014b, code lost:
        r5 = com.facebook.LoggingBehavior.APP_EVENTS;
        r2 = new java.lang.Object[2];
        r2[0] = java.lang.Integer.valueOf(r6.A00);
     */
    /* JADX WARNING: Code restructure failed: missing block: B:79:0x0160, code lost:
        switch(r14.intValue()) {
            case 1: goto L_0x018c;
            case 2: goto L_0x0189;
            case 3: goto L_0x0186;
            case 4: goto L_0x0183;
            case 5: goto L_0x0180;
            default: goto L_0x0163;
        };
     */
    /* JADX WARNING: Code restructure failed: missing block: B:80:0x0163, code lost:
        r0 = "EXPLICIT";
     */
    /* JADX WARNING: Code restructure failed: missing block: B:81:0x0165, code lost:
        r2[1] = r0;
        com.facebook.internal.Logger.log(r5, "com.facebook.appevents.AppEventsLogger", "Flushing %d events due to %s.", r2);
        r1 = r7.iterator();
     */
    /* JADX WARNING: Code restructure failed: missing block: B:83:0x0174, code lost:
        if (r1.hasNext() == false) goto L_0x018f;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:84:0x0176, code lost:
        com.facebook.GraphRequest.executeAndWait((com.facebook.GraphRequest) r1.next());
     */
    /* JADX WARNING: Code restructure failed: missing block: B:85:0x0180, code lost:
        r0 = "EAGER_FLUSHING_EVENT";
     */
    /* JADX WARNING: Code restructure failed: missing block: B:86:0x0183, code lost:
        r0 = "EVENT_THRESHOLD";
     */
    /* JADX WARNING: Code restructure failed: missing block: B:87:0x0186, code lost:
        r0 = "PERSISTED_EVENTS";
     */
    /* JADX WARNING: Code restructure failed: missing block: B:88:0x0189, code lost:
        r0 = "SESSION_CHANGE";
     */
    /* JADX WARNING: Code restructure failed: missing block: B:89:0x018c, code lost:
        r0 = "TIMER";
     */
    /* JADX WARNING: Code restructure failed: missing block: B:90:0x018f, code lost:
        r3 = r6;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:9:0x0018, code lost:
        r5 = X.AnonymousClass1e2.A00(com.facebook.appevents.AppEventsLogger.A02);
        r3 = r5.A01.keySet().iterator();
     */
    /* JADX WARNING: Removed duplicated region for block: B:112:0x0065 A[SYNTHETIC] */
    /* JADX WARNING: Removed duplicated region for block: B:70:0x012d  */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public static void A03(java.lang.Integer r14) {
        /*
        // Method dump skipped, instructions count: 460
        */
        throw new UnsupportedOperationException("Method not decompiled: com.facebook.appevents.AppEventsLogger.A03(java.lang.Integer):void");
    }

    public AppEventsLogger(Context context, String str) {
        AnonymousClass1gV r0;
        Validate.notNull(context, "context");
        this.A01 = Utility.getActivityName(context);
        AccessToken accessToken = AccessTokenManager.getInstance().currentAccessToken;
        if (accessToken != null) {
            if (str == null || str.equals(accessToken.applicationId)) {
                String str2 = accessToken.token;
                Validate.sdkInitialized();
                r0 = new AnonymousClass1gV(str2, FacebookSdk.applicationId);
            }
            r0 = new AnonymousClass1gV(null, str);
        } else {
            if (str == null) {
                str = Utility.getMetadataApplicationId(context);
            }
            r0 = new AnonymousClass1gV(null, str);
        }
        this.A00 = r0;
        synchronized (A03) {
            if (A02 == null) {
                A02 = context.getApplicationContext();
            }
        }
        synchronized (A03) {
            if (A05 == null) {
                A05 = new ScheduledThreadPoolExecutor(1);
                AnonymousClass1gU r1 = new AnonymousClass1gU();
                ScheduledThreadPoolExecutor scheduledThreadPoolExecutor = A05;
                TimeUnit timeUnit = TimeUnit.SECONDS;
                scheduledThreadPoolExecutor.scheduleAtFixedRate(r1, 0, 15, timeUnit);
                A05.scheduleAtFixedRate(new AnonymousClass1eL(), 0, 86400, timeUnit);
            }
        }
    }
}
