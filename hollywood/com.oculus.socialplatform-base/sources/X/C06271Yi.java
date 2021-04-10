package X;

import android.content.Context;
import android.content.pm.PackageInfo;
import android.content.pm.PackageManager;
import android.util.DisplayMetrics;
import com.facebook.mediamanager.MediaManager;
import com.facebook.msys.mca.Mailbox;
import com.facebook.msys.mca.MailboxExperimentCache;
import com.facebook.msys.mcd.DatabaseOpenCallback;
import com.facebook.msys.mcd.MediaSendManager;
import com.facebook.msys.mcd.MqttNetworkSessionPlugin;
import com.facebook.msys.mci.AuthData;
import com.facebook.msys.mci.AuthDataContext;
import com.facebook.msys.mci.Database;
import com.facebook.msys.mci.DatabaseConnectionSettings;
import com.facebook.msys.mci.Execution;
import com.facebook.msys.mci.NetworkSession;
import com.facebook.msys.mci.NotificationCenter;
import com.facebook.msys.mci.SqliteHolder;
import com.facebook.msys.mcs.DasmConfigCreator;
import com.oculus.messengervr.fb.VrMsysMqttClientCallbacks;
import javax.annotation.Nullable;

/* renamed from: X.1Yi  reason: invalid class name and case insensitive filesystem */
public class C06271Yi extends DatabaseOpenCallback {
    public final /* synthetic */ C06281Yj A00;
    public final /* synthetic */ C06261Yh A01;
    public final /* synthetic */ AnonymousClass1YZ A02;
    public final /* synthetic */ Database.OpenCallback A03;

    public C06271Yi(C06261Yh r1, Database.OpenCallback openCallback, C06281Yj r3, AnonymousClass1YZ r4) {
        this.A01 = r1;
        this.A03 = openCallback;
        this.A00 = r3;
        this.A02 = r4;
    }

    @Override // com.facebook.msys.mci.Database.OpenCallback
    public final void onOpen(boolean z, @Nullable Throwable th) {
        Database database;
        AuthDataContext authDataContext;
        AuthData authData;
        C06261Yh r6 = this.A01;
        AbstractC06301Yq.A00();
        AnonymousClass1Yn r1 = AnonymousClass1Yn.A03;
        AuthData authData2 = r6.A00.A02;
        synchronized (r1) {
            database = r1.A02;
        }
        AuthDataContext authDataContext2 = new AuthDataContext(authData2, database);
        synchronized (r1) {
            r1.A01 = authDataContext2;
        }
        AbstractC06301Yq.A00();
        MediaManager mediaManager = new MediaManager(r1.A00(), r1.A01(), r6.A00.A01.A00.getCacheDir());
        synchronized (r1) {
            r1.A00 = mediaManager;
        }
        AnonymousClass1YZ r3 = this.A02;
        C06281Yj r10 = r6.A00;
        MailboxExperimentCache mailboxExperimentCache = r6.A01;
        Context context = r10.A01.A00;
        AbstractC06301Yq.A00();
        NetworkSession A002 = r1.A00();
        NotificationCenter A012 = r1.A01();
        DasmConfigCreator dasmConfigCreator = r10.A04;
        MediaSendManager mediaSendManager = new MediaSendManager(A002, A012, context.getCacheDir());
        try {
            String str = r10.A0C;
            synchronized (r1) {
                authDataContext = r1.A01;
            }
            if (!AnonymousClass1R2.A01) {
                synchronized (AnonymousClass1R2.class) {
                    try {
                        PackageInfo packageInfo = context.getPackageManager().getPackageInfo(context.getPackageName(), 0);
                        if (packageInfo != null) {
                            AnonymousClass1R2.A00 = packageInfo.versionName;
                            AnonymousClass1R2.A01 = true;
                        } else {
                            AnonymousClass0MD.A0B("ApplicationManifestHelper", "Package info for %s is null", context.getPackageName());
                        }
                    } catch (PackageManager.NameNotFoundException e) {
                        AnonymousClass0MD.A0E("ApplicationManifestHelper", e, "Failed to get package info for %s", context.getPackageName());
                    }
                }
            }
            String str2 = AnonymousClass1R2.A00;
            String str3 = r10.A0D;
            Mailbox mailbox = new Mailbox(0, str, authDataContext, mediaSendManager, A002, A012, dasmConfigCreator, str2, str3, mailboxExperimentCache, new AnonymousClass1RV());
            AbstractC06301Yq.A00();
            synchronized (r1) {
                AuthDataContext authDataContext3 = r1.A01;
                if (authDataContext3 != null) {
                    authData = authDataContext3.mAuthData;
                } else {
                    authData = null;
                }
            }
            if (authData != null) {
                NetworkSession A003 = r1.A00();
                if (A003 != null) {
                    NotificationCenter A013 = r1.A01();
                    if (A013 != null) {
                        MqttNetworkSessionPlugin mqttNetworkSessionPlugin = MqttNetworkSessionPlugin.sInstance;
                        VrMsysMqttClientCallbacks vrMsysMqttClientCallbacks = r10.A06;
                        if (vrMsysMqttClientCallbacks != null) {
                            if (str3 != null) {
                                mqttNetworkSessionPlugin.mMqttClientCallbacks = vrMsysMqttClientCallbacks;
                                MqttNetworkSessionPlugin.registerNative(A003, authData, A013, mailbox, str3);
                                AbstractC06301Yq.A00();
                            } else {
                                throw null;
                            }
                        }
                        Context context2 = r10.A01.A00;
                        DisplayMetrics displayMetrics = context2.getResources().getDisplayMetrics();
                        int i = displayMetrics.widthPixels;
                        int i2 = displayMetrics.heightPixels;
                        float f = displayMetrics.density;
                        C06461Zy r8 = new C06461Zy(mailbox);
                        float f2 = (float) i;
                        Integer valueOf = Integer.valueOf((int) (f2 * 0.5f));
                        float f3 = (float) i2;
                        Integer valueOf2 = Integer.valueOf((int) (0.5f * f3));
                        Integer valueOf3 = Integer.valueOf(i);
                        Integer valueOf4 = Integer.valueOf(i2);
                        Float valueOf5 = Float.valueOf(f);
                        Integer valueOf6 = Integer.valueOf((int) (f2 * 0.3f));
                        Integer valueOf7 = Integer.valueOf((int) (f3 * 0.3f));
                        AnonymousClass1Z6 r7 = r8.A00;
                        r7.A9T(new C06391Zq(r8, new AnonymousClass1Zb(r7), valueOf, valueOf2, valueOf3, valueOf4, valueOf5, valueOf6, valueOf7, str));
                        r7.A9T(new C06411Zs(r8, new AnonymousClass1Zb(r7), Integer.valueOf((int) (context2.getResources().getDisplayMetrics().density * 60.0f)), Integer.valueOf((int) (context2.getResources().getDisplayMetrics().density * 100.0f))));
                        AnonymousClass1ZT r72 = new AnonymousClass1ZT(mailbox);
                        AnonymousClass1Z6 r5 = r72.A00;
                        r5.A9T(new AnonymousClass1ZS(r72, new AnonymousClass1Zb(r5), str));
                        AbstractC06301Yq.A00();
                        mailbox.getSyncHandler();
                        Execution.executePossiblySync(new AnonymousClass1ZM(mailbox), 1);
                        AbstractC06301Yq.A00();
                        r3.onCompletion(mailbox);
                        Database.OpenCallback openCallback = this.A03;
                        if (openCallback != null) {
                            openCallback.onOpen(z, th);
                            return;
                        }
                        return;
                    }
                    throw null;
                }
                throw null;
            }
            throw null;
        } catch (Exception e2) {
            throw new RuntimeException(e2.getMessage());
        }
    }

    @Override // com.facebook.msys.mci.Database.OpenCallback
    public final void onConfig(SqliteHolder sqliteHolder, int i, DatabaseConnectionSettings databaseConnectionSettings) {
    }
}
