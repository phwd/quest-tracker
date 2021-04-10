package com.facebook.analytics2.logger;

import X.AnonymousClass08;
import X.AnonymousClass79;
import X.AnonymousClass7D;
import X.AnonymousClass82;
import X.AnonymousClass83;
import X.Bw;
import X.C0;
import X.C0139Dd;
import X.JG;
import android.app.AlarmManager;
import android.app.PendingIntent;
import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;
import android.content.pm.ResolveInfo;
import android.os.Bundle;
import android.os.SystemClock;
import com.facebook.acra.constants.ErrorReportingConstants;
import com.facebook.common.gcmcompat.OneoffTask;
import com.facebook.common.gcmcompat.Task;
import com.facebook.common.stringformat.StringFormatUtil;
import com.google.android.gms.common.ConnectionResult;
import com.google.android.gms.common.GoogleApiAvailability;
import java.util.List;
import java.util.Set;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.atomic.AtomicInteger;

public class GooglePlayUploadService extends C0 {
    public static boolean A01;
    public static boolean A02;
    public static final long A03;
    public static final long A04;
    public static final AtomicInteger A05 = new AtomicInteger(0);
    public AnonymousClass83 A00;

    @Override // X.C0
    public final int onStartCommand(Intent intent, int i, int i2) {
        if (intent != null) {
            try {
                String action = intent.getAction();
                if (action.startsWith("com.facebook.analytics2.logger.gms.TRY_SCHEDULE")) {
                    AnonymousClass79 r0 = new AnonymousClass79(intent.getExtras());
                    A03(this, r0.A00, r0.A01);
                    return 2;
                } else if (action.startsWith("com.facebook")) {
                    return this.A00.A02(intent, new AnonymousClass82(this, i2));
                } else {
                    return super.onStartCommand(intent, i, i2);
                }
            } catch (AnonymousClass7D e) {
                C0139Dd.A0P("GooglePlayUploadService", "Unexpected service start parameters: %s", e.getMessage());
                stopSelf(i2);
                return 2;
            }
        } else {
            throw new AnonymousClass7D("Received a null intent, did you ever return START_STICKY?");
        }
    }

    static {
        TimeUnit timeUnit = TimeUnit.MINUTES;
        A03 = timeUnit.toMillis(2);
        A04 = timeUnit.toMillis(5);
    }

    public static synchronized void A01(Context context) {
        synchronized (GooglePlayUploadService.class) {
            if (!A02) {
                context.getPackageManager().setComponentEnabledSetting(new ComponentName(context, GooglePlayUploadService.class), 1, 1);
                A02 = true;
            }
        }
    }

    public static void A03(Context context, int i, OneoffTask oneoffTask) {
        Bw bw;
        int isGooglePlayServicesAvailable = GoogleApiAvailability.A00.isGooglePlayServicesAvailable(context);
        if (isGooglePlayServicesAvailable == 0) {
            try {
                synchronized (Bw.class) {
                    bw = Bw.A02;
                    if (bw == null) {
                        bw = new Bw(context.getApplicationContext());
                        Bw.A02 = bw;
                    }
                }
                String str = ((Task) oneoffTask).A00;
                if (str != null) {
                    Intent intent = new Intent("com.google.android.gms.gcm.ACTION_TASK_READY");
                    Context context2 = bw.A01;
                    intent.setPackage(context2.getPackageName());
                    List<ResolveInfo> queryIntentServices = context2.getPackageManager().queryIntentServices(intent, 512);
                    if (queryIntentServices == null || queryIntentServices.isEmpty()) {
                        throw new IllegalArgumentException("There is no GcmTaskService component registered within this package. Have you extended GcmTaskService correctly?");
                    }
                    for (ResolveInfo resolveInfo : queryIntentServices) {
                        if (resolveInfo.serviceInfo.name.equals(str)) {
                            Intent putExtra = new Intent("com.google.android.gms.gcm.ACTION_SCHEDULE").setPackage("com.google.android.gms").putExtra("scheduler_action", "SCHEDULE_TASK").putExtra(ErrorReportingConstants.APP_NAME_KEY, bw.A00).putExtra("source", Bw.A03).putExtra("source_version", 12451000);
                            if (putExtra != null) {
                                Bundle bundle = new Bundle();
                                oneoffTask.A01(bundle);
                                putExtra.putExtras(bundle);
                                context2.sendBroadcast(putExtra);
                            }
                            A05.set(0);
                            return;
                        }
                    }
                    throw new IllegalArgumentException("The GcmTaskService class you provided  does not seem to support receiving com.google.android.gms.gcm.ACTION_TASK_READY.");
                }
                throw new NullPointerException("GcmTaskService must not be null.");
            } catch (IllegalArgumentException e) {
                ComponentName componentName = new ComponentName(context, ((Task) oneoffTask).A00);
                int componentEnabledSetting = context.getPackageManager().getComponentEnabledSetting(componentName);
                if (componentEnabledSetting == 1 || componentEnabledSetting == 0) {
                    throw e;
                }
                Object[] objArr = {componentName};
                if (C0139Dd.A01.A3Y(4)) {
                    String formatStrLocaleSafe = StringFormatUtil.formatStrLocaleSafe("Service has been disabled; cannot schedule job for %s", objArr);
                    if (C0139Dd.A01.A3Y(4)) {
                        C0139Dd.A01.A3D("DisabledServiceWorkaround", formatStrLocaleSafe, e);
                    }
                }
            }
        } else if (A05.incrementAndGet() == 3) {
            C0139Dd.A0Q("GooglePlayUploadService", "Google Play Services became consistently unavailable after initial check: %s", ConnectionResult.A00(isGooglePlayServicesAvailable));
        } else {
            C0139Dd.A0G("GooglePlayUploadService", "Scheduling Google Play Services retry due to: %s", ConnectionResult.A00(isGooglePlayServicesAvailable));
            AlarmManager alarmManager = (AlarmManager) context.getSystemService("alarm");
            JG jg = new JG();
            Intent action = new Intent(context, GooglePlayUploadService.class).setAction(AnonymousClass08.A00("com.facebook.analytics2.logger.gms.TRY_SCHEDULE-", i));
            if (oneoffTask != null) {
                AnonymousClass79 r7 = new AnonymousClass79(i, oneoffTask);
                Bundle bundle2 = new Bundle();
                bundle2.putInt("job_id", r7.A00);
                bundle2.putParcelable("task", r7.A01);
                action.putExtras(bundle2);
            }
            ClassLoader classLoader = context.getClassLoader();
            jg.A02 = action.getComponent();
            jg.A07 = action.getAction();
            jg.A05 = action.getData();
            jg.A08 = action.getType();
            jg.A04 = action.getSourceBounds();
            jg.A03 = action.getSelector();
            jg.A01 = action.getClipData();
            Set<String> categories = action.getCategories();
            if (categories != null) {
                jg.A09.addAll(categories);
            }
            jg.A00 = action.getFlags();
            if (action.getExtras() != null) {
                if (classLoader != null) {
                    action.setExtrasClassLoader(classLoader);
                }
                Bundle extras = action.getExtras();
                Bundle bundle3 = jg.A06;
                if (bundle3 == null) {
                    bundle3 = new Bundle();
                    jg.A06 = bundle3;
                }
                if (classLoader != null) {
                    bundle3.setClassLoader(classLoader);
                }
                jg.A06.putAll(extras);
            }
            alarmManager.set(2, SystemClock.elapsedRealtime() + A04, PendingIntent.getService(context, 0, JG.A00(jg, context), 201326592));
        }
    }

    public final void onCreate() {
        super.onCreate();
        this.A00 = AnonymousClass83.A00(this);
    }
}
