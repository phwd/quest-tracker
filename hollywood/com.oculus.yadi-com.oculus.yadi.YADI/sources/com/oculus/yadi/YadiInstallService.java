package com.oculus.yadi;

import android.app.Notification;
import android.app.Service;
import android.content.Context;
import android.content.Intent;
import android.content.IntentSender;
import android.os.Handler;
import android.os.Parcelable;
import android.util.Log;
import com.oculus.os.yadi.IInstallService;
import com.oculus.os.yadi.RemoteApp;
import com.oculus.os.yadi.RemoteResource;
import com.oculus.os.yadi.YadiStatus;
import com.oculus.yadi.YadiInstallService;
import java.io.File;
import java.io.IOException;
import java.util.function.Consumer;
import oculus.internal.NotificationChannelCompat;
import oculus.internal.StorageListenerInterface;
import oculus.internal.yadi.DownloadService;
import oculus.internal.yadi.InstallService;

public class YadiInstallService extends Service {
    private volatile int _lastStartId = 0;
    private int _queuedOps;
    private StorageListenerInterface _storageListener;
    private final InstallServiceClient _yadiBinder = new InstallServiceClient();
    private DownloadService _yadiDownload;
    private InstallService _yadiInstall;

    public void onCreate() {
        new NotificationChannelCompat().setupNotificationChannel(this, "com.oculus.os.yadi.notif_channel", "YadiInstallService Notifications", "Notification Channel");
        this._yadiDownload = new DownloadService(this, 4);
        this._yadiDownload.init();
        this._yadiDownload.start();
        try {
            Context createDeviceProtectedStorageContext = createDeviceProtectedStorageContext();
            this._yadiInstall = new InstallService(createDeviceProtectedStorageContext, new File(createDeviceProtectedStorageContext.getCacheDir(), "yadi"));
            this._storageListener = StorageListenerInterface.register($$Lambda$YadiInstallService$iu_fbaOMFLTTbx6V_4TDYh0Hp9Y.INSTANCE);
            this._yadiInstall.init(this._yadiDownload);
            this._yadiInstall.start();
        } catch (IOException e) {
            Log.wtf("YadiInstallService", "Can not access cache directory", e);
            throw new RuntimeException("Fatal error starting", e);
        }
    }

    public void onDestroy() {
        this._yadiDownload.stop();
        this._yadiInstall.stop();
        this._yadiDownload = null;
        this._yadiInstall = null;
        this._storageListener.unregister();
        this._storageListener = null;
        this._queuedOps = 0;
        new NotificationChannelCompat().deleteNotificationChannel(this, "com.oculus.os.yadi.notif_channel");
    }

    public int onStartCommand(Intent intent, int i, int i2) {
        this._lastStartId = i2;
        new Handler().postDelayed(new Runnable(i2) {
            /* class com.oculus.yadi.$$Lambda$YadiInstallService$7rYn8vOZL0D_iw_92c45xhQAlds */
            private final /* synthetic */ int f$1;

            {
                this.f$1 = r2;
            }

            public final void run() {
                YadiInstallService.this.lambda$onStartCommand$1$YadiInstallService(this.f$1);
            }
        }, 15000);
        return 2;
    }

    /* JADX WARN: Type inference failed for: r0v1, types: [com.oculus.yadi.YadiInstallService$InstallServiceClient, android.os.IBinder] */
    /* JADX WARNING: Unknown variable types count: 1 */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public android.os.IBinder onBind(android.content.Intent r1) {
        /*
            r0 = this;
            com.oculus.yadi.YadiInstallService$InstallServiceClient r0 = r0._yadiBinder
            return r0
        */
        throw new UnsupportedOperationException("Method not decompiled: com.oculus.yadi.YadiInstallService.onBind(android.content.Intent):android.os.IBinder");
    }

    /* access modifiers changed from: private */
    /* renamed from: stopIfQuiescent */
    public synchronized void lambda$onStartCommand$1$YadiInstallService(int i) {
        if (this._queuedOps == 0) {
            stopSelf(i);
        }
    }

    /* access modifiers changed from: private */
    /* access modifiers changed from: public */
    private synchronized void handleForegroundTransition(boolean z) {
        if (z) {
            this._queuedOps++;
        } else {
            this._queuedOps--;
        }
        if (z && this._queuedOps == 1) {
            Notification.Builder smallIcon = new Notification.Builder(this).setContentTitle(getString(R.string.installing)).setSmallIcon(17301633);
            new NotificationChannelCompat().setChannelIdForNotification("com.oculus.os.yadi.notif_channel", smallIcon);
            startService(new Intent(this, getClass()));
            startForeground(1337, smallIcon.build());
        } else if (!z && this._queuedOps == 0) {
            int i = this._lastStartId;
            stopForeground(true);
            new Handler(getMainLooper()).postDelayed(new Runnable(i) {
                /* class com.oculus.yadi.$$Lambda$YadiInstallService$YVSbwdUWYg0FbqhsHeKjpoNASo */
                private final /* synthetic */ int f$1;

                {
                    this.f$1 = r2;
                }

                public final void run() {
                    YadiInstallService.this.lambda$handleForegroundTransition$2$YadiInstallService(this.f$1);
                }
            }, 5000);
        }
    }

    /* access modifiers changed from: private */
    /* access modifiers changed from: public */
    private void sendQuietly(IntentSender intentSender, Intent intent) {
        try {
            intentSender.sendIntent(this, -1, intent, null, null);
        } catch (IntentSender.SendIntentException unused) {
            Log.d("YadiInstallService", "failed to send intent to " + intentSender.getCreatorPackage());
        }
    }

    /* access modifiers changed from: private */
    /* access modifiers changed from: public */
    private void onUpdate(String str, IntentSender intentSender, Intent intent) {
        YadiStatus parcelableExtra = intent.getParcelableExtra("YadiStatus");
        if (parcelableExtra == YadiStatus.Complete || parcelableExtra == YadiStatus.Terminated) {
            handleForegroundTransition(false);
        }
        Intent intent2 = new Intent();
        intent2.putExtras(intent.getExtras());
        intent2.putExtra("YadiTag", str);
        sendQuietly(intentSender, intent2);
    }

    /* access modifiers changed from: private */
    public class InstallServiceClient extends IInstallService.Stub {
        private InstallServiceClient() {
        }

        private void checkYadiPermission() {
            if (YadiInstallService.this.checkCallingPermission("com.oculus.permission.YADI_QUEUE_INSTALL") != 0) {
                throw new SecurityException("Missing permission: com.oculus.permission.YADI_QUEUE_INSTALL");
            }
        }

        public String enqueueInstall(RemoteApp remoteApp, RemoteResource[] remoteResourceArr, IntentSender intentSender) {
            enqueueTaggedInstall(remoteApp.appId, remoteApp, remoteResourceArr, intentSender);
            return remoteApp.appId;
        }

        public void enqueueTaggedInstall(String str, RemoteApp remoteApp, RemoteResource[] remoteResourceArr, IntentSender intentSender) {
            checkYadiPermission();
            try {
                Object obj = new Object();
                synchronized (obj) {
                    YadiInstallService.this._yadiInstall.enqueueInstall(remoteApp, remoteResourceArr, new Consumer(obj, str, intentSender) {
                        /* class com.oculus.yadi.$$Lambda$YadiInstallService$InstallServiceClient$Q6OlwIpvcBRMiI3E6fEswQRwldE */
                        private final /* synthetic */ Object f$1;
                        private final /* synthetic */ String f$2;
                        private final /* synthetic */ IntentSender f$3;

                        {
                            this.f$1 = r2;
                            this.f$2 = r3;
                            this.f$3 = r4;
                        }

                        @Override // java.util.function.Consumer
                        public final void accept(Object obj) {
                            YadiInstallService.InstallServiceClient.this.lambda$enqueueTaggedInstall$0$YadiInstallService$InstallServiceClient(this.f$1, this.f$2, this.f$3, (Intent) obj);
                        }
                    });
                    YadiInstallService.this.handleForegroundTransition(true);
                }
            } catch (IllegalArgumentException unused) {
                Intent intent = new Intent();
                intent.putExtra("YadiStatus", (Parcelable) YadiStatus.Queued);
                intent.putExtra("YadiTag", str);
                YadiInstallService.this.sendQuietly(intentSender, intent);
            }
        }

        public /* synthetic */ void lambda$enqueueTaggedInstall$0$YadiInstallService$InstallServiceClient(Object obj, String str, IntentSender intentSender, Intent intent) {
            synchronized (obj) {
                YadiInstallService.this.onUpdate(str, intentSender, intent);
            }
        }
    }
}
