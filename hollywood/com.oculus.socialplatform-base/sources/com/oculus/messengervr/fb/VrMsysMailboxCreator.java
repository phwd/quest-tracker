package com.oculus.messengervr.fb;

import X.AbstractC06371Zh;
import X.AbstractC10551og;
import X.AbstractC13251zE;
import X.AnonymousClass006;
import X.AnonymousClass1GN;
import X.AnonymousClass1O7;
import X.AnonymousClass1PX;
import X.AnonymousClass1Sj;
import X.AnonymousClass1YZ;
import X.AnonymousClass1Yr;
import X.AnonymousClass219;
import X.C06251Yg;
import X.C06281Yj;
import X.C06291Yl;
import X.C06321Yt;
import X.C06331Yu;
import X.C12701yB;
import X.C13401zX;
import android.annotation.TargetApi;
import android.app.Application;
import android.content.Context;
import android.os.Process;
import androidx.annotation.Nullable;
import com.facebook.infer.annotation.Nullsafe;
import com.facebook.messengervr.msys.MessengerVrDasmConfigCreator;
import com.facebook.messengervr.msys.MessengerVrTableToProcedureNameMapRegisterer;
import com.facebook.msys.mca.Mailbox;
import com.facebook.msys.mci.Analytics;
import com.facebook.msys.mci.AppInfo;
import com.facebook.msys.mci.AuthData;
import com.facebook.msys.mci.Database;
import com.facebook.msys.mci.Proxies;
import com.facebook.msys.mci.ProxyProvider;
import com.facebook.msys.mci.RedactedString;
import com.facebook.msys.mci.Settings;
import com.facebook.msys.mci.network.common.NetworkUtils;
import com.facebook.msys.util.Provider;
import java.io.File;
import java.util.Locale;
import java.util.function.Consumer;

@TargetApi(25)
@Nullsafe(Nullsafe.Mode.LOCAL)
public final class VrMsysMailboxCreator {
    public static final String NEW_DATABASE_NAME_PREFIX = "messengervr_msys_database_";
    public static final String OLD_DATABASE_NAME_PREFIX = "vr_msys_database_";

    public static /* synthetic */ Analytics lambda$createProxyProvider$7(Analytics analytics) {
        return analytics;
    }

    public static C06281Yj createMailboxConfig(AuthData authData, C06321Yt r9, final C12701yB<Boolean> r10, VrMsysMqttClientCallbacks vrMsysMqttClientCallbacks, @Nullable final Consumer<Boolean> consumer) {
        AnonymousClass1 r2 = new Database.OpenCallback() {
            /* class com.oculus.messengervr.fb.VrMsysMailboxCreator.AnonymousClass1 */

            @Override // com.facebook.msys.mci.Database.OpenCallback
            public void onOpen(boolean z, @Nullable Throwable th) {
                if (th != null) {
                    synchronized (AnonymousClass1GN.class) {
                    }
                    Process.killProcess(Process.myPid());
                }
                Consumer consumer = consumer;
                if (consumer != null) {
                    consumer.accept(Boolean.valueOf(z));
                }
                r10.onSuccess(Boolean.valueOf(z));
            }
        };
        MessengerVrDasmConfigCreator messengerVrDasmConfigCreator = MessengerVrDasmConfigCreator.sInstance;
        if (messengerVrDasmConfigCreator == null) {
            messengerVrDasmConfigCreator = new MessengerVrDasmConfigCreator();
            MessengerVrDasmConfigCreator.sInstance = messengerVrDasmConfigCreator;
        }
        C06291Yl r3 = new C06291Yl(authData, messengerVrDasmConfigCreator, new Object() {
            /* class com.oculus.messengervr.fb.VrMsysMailboxCreator.AnonymousClass2 */

            public Database.SchemaDeployer getCrossDatabaseSchemaDeployer() {
                return $$Lambda$VqnsstFgf_ntkvyq51ubqbvxcw2.INSTANCE;
            }

            public Database.SchemaDeployer getInMemorySchemaDeployer() {
                return $$Lambda$av0k0cWLAsV1YXgmih7oeXvqYR02.INSTANCE;
            }

            public Database.SchemaDeployer getPersistentSchemaDeployer() {
                return $$Lambda$n4FJ7IwJoHYz4RdBZXK2LqeXys02.INSTANCE;
            }
        }, Locale.getDefault().toString(), r9);
        r3.A03 = AnonymousClass006.A07(NEW_DATABASE_NAME_PREFIX, authData.getFacebookUserID());
        r3.A00 = new MessengerVrTableToProcedureNameMapRegisterer();
        r3.A01 = r2;
        r3.A02 = vrMsysMqttClientCallbacks;
        return new C06281Yj(r3);
    }

    public static AbstractC13251zE<Mailbox> createMailboxWaitOnDbOpenSingle(Application application, String str, String str2, String str3, String str4, String str5, String str6, VrMsysMqttClientCallbacks vrMsysMqttClientCallbacks, @Nullable String str7, @Nullable Consumer<Boolean> consumer, Analytics analytics) {
        C12701yB r9 = new C12701yB();
        AbstractC13251zE A01 = AbstractC13251zE.A01(AbstractC13251zE.A00(new AbstractC06371Zh(application, str, str2, str3, str4, str5, vrMsysMqttClientCallbacks, str7, r9, consumer, str6, analytics) {
            /* class com.oculus.messengervr.fb.$$Lambda$VrMsysMailboxCreator$L5h1gMnps5otiZKf7bifNBXpJtg2 */
            public final /* synthetic */ Application f$0;
            public final /* synthetic */ String f$1;
            public final /* synthetic */ String f$10;
            public final /* synthetic */ Analytics f$11;
            public final /* synthetic */ String f$2;
            public final /* synthetic */ String f$3;
            public final /* synthetic */ String f$4;
            public final /* synthetic */ String f$5;
            public final /* synthetic */ VrMsysMqttClientCallbacks f$6;
            public final /* synthetic */ String f$7;
            public final /* synthetic */ C12701yB f$8;
            public final /* synthetic */ Consumer f$9;

            {
                this.f$0 = r1;
                this.f$1 = r2;
                this.f$2 = r3;
                this.f$3 = r4;
                this.f$4 = r5;
                this.f$5 = r6;
                this.f$6 = r7;
                this.f$7 = r8;
                this.f$8 = r9;
                this.f$9 = r10;
                this.f$10 = r11;
                this.f$11 = r12;
            }

            @Override // X.AbstractC06371Zh
            public final void subscribe(AbstractC10551og r14) {
                VrMsysMailboxCreator.lambda$createMailboxWaitOnDbOpenSingle$1(this.f$0, this.f$1, this.f$2, this.f$3, this.f$4, this.f$5, this.f$6, this.f$7, this.f$8, this.f$9, this.f$10, this.f$11, r14);
            }
        }), r9, $$Lambda$GueltK5Imt9ysuZSSkh41eIe0A2.INSTANCE);
        $$Lambda$VrMsysMailboxCreator$vV7mP94W3JaK9Dmn6TMTt6OiT3I2 r1 = $$Lambda$VrMsysMailboxCreator$vV7mP94W3JaK9Dmn6TMTt6OiT3I2.INSTANCE;
        AnonymousClass219.A01(r1, "mapper is null");
        return new C13401zX(A01, r1);
    }

    public static ProxyProvider createProxyProvider(Context context, Analytics analytics) {
        AnonymousClass1Yr r1 = new AnonymousClass1Yr($$Lambda$VrMsysMailboxCreator$0KMrdMi68cDBb1ZBmfM2GDUaCY2.INSTANCE, new Provider(context) {
            /* class com.oculus.messengervr.fb.$$Lambda$VrMsysMailboxCreator$3CfGt3jFUndYK7uym1PtxIXkk2 */
            public final /* synthetic */ Context f$0;

            {
                this.f$0 = r1;
            }

            @Override // com.facebook.msys.util.Provider
            public final Object get() {
                return new AnonymousClass1PX(this.f$0);
            }
        }, $$Lambda$VrMsysMailboxCreator$heR9IjRSDTS09VrblXPbSZ1ck7c2.INSTANCE);
        r1.A00 = new Provider() {
            /* class com.oculus.messengervr.fb.$$Lambda$VrMsysMailboxCreator$k8GeDY8UNqI6mhcrQQXkbjTzRYA2 */

            @Override // com.facebook.msys.util.Provider
            public final Object get() {
                return Analytics.this;
            }
        };
        $$Lambda$VrMsysMailboxCreator$r612BenJ4wc0Dof9Etq2m4aEQes2 r0 = $$Lambda$VrMsysMailboxCreator$r612BenJ4wc0Dof9Etq2m4aEQes2.INSTANCE;
        if (r0 != null) {
            r1.A01 = r0;
            return new ProxyProvider(r1);
        }
        throw new IllegalArgumentException("mediaTranscoderProvider must not be null");
    }

    public static void deleteOldMsysDatabaseIfExists(Context context, @Nullable String str) {
        File databasePath = context.getDatabasePath(AnonymousClass006.A07(OLD_DATABASE_NAME_PREFIX, str));
        if (databasePath.exists()) {
            databasePath.delete();
        }
    }

    public static AnonymousClass2 getDBSchemaDeployerConfig() {
        return new Object() {
            /* class com.oculus.messengervr.fb.VrMsysMailboxCreator.AnonymousClass2 */

            public Database.SchemaDeployer getCrossDatabaseSchemaDeployer() {
                return $$Lambda$VqnsstFgf_ntkvyq51ubqbvxcw2.INSTANCE;
            }

            public Database.SchemaDeployer getInMemorySchemaDeployer() {
                return $$Lambda$av0k0cWLAsV1YXgmih7oeXvqYR02.INSTANCE;
            }

            public Database.SchemaDeployer getPersistentSchemaDeployer() {
                return $$Lambda$n4FJ7IwJoHYz4RdBZXK2LqeXys02.INSTANCE;
            }
        };
    }

    public static /* synthetic */ void lambda$createMailboxWaitOnDbOpenSingle$1(Application application, String str, String str2, String str3, String str4, String str5, VrMsysMqttClientCallbacks vrMsysMqttClientCallbacks, String str6, C12701yB r21, Consumer consumer, String str7, Analytics analytics, AbstractC10551og r25) throws Exception {
        C06251Yg createLazyMailbox = createLazyMailbox(application, str, str2, str2, str3, str4, str5, vrMsysMqttClientCallbacks, str6, r21, consumer, str7, analytics);
        $$Lambda$VrMsysMailboxCreator$5jZNePFxrZK8Q9wFVBo_4aNtzYY2 r0 = new AnonymousClass1YZ() {
            /* class com.oculus.messengervr.fb.$$Lambda$VrMsysMailboxCreator$5jZNePFxrZK8Q9wFVBo_4aNtzYY2 */

            @Override // X.AnonymousClass1YZ
            public final void onCompletion(Object obj) {
                AbstractC10551og.this.onSuccess(obj);
            }
        };
        synchronized (createLazyMailbox) {
            createLazyMailbox.A9T(r0);
        }
    }

    public static /* synthetic */ Settings lambda$createProxyProvider$5(Context context) {
        return new AnonymousClass1PX(context);
    }

    public static C06321Yt createInfraConfig(Application application, String str, ProxyProvider proxyProvider) {
        return new C06321Yt(new C06331Yu(application, str, new AnonymousClass1O7(str, application.getCacheDir()), proxyProvider));
    }

    public static String getDatabaseFileName(String str, @Nullable String str2) {
        return AnonymousClass006.A07(str, str2);
    }

    public static C06251Yg createLazyMailbox(Application application, String str, String str2, String str3, String str4, String str5, String str6, VrMsysMqttClientCallbacks vrMsysMqttClientCallbacks, @Nullable String str7, C12701yB<Boolean> r26, @Nullable Consumer<Boolean> consumer, String str8, Analytics analytics) {
        AppInfo appInfo = new AppInfo(str, str2, str3, str4, null, null);
        AuthData authData = new AuthData(new RedactedString(str6), null, str8, "", str5, null, null, null, null, null, null, 0);
        String str9 = new AnonymousClass1Sj(application, appInfo).A00;
        ProxyProvider createProxyProvider = createProxyProvider(application, analytics);
        Proxies.configure(createProxyProvider);
        NetworkUtils.setSandboxDomain(str7);
        deleteOldMsysDatabaseIfExists(application, str5);
        return new C06251Yg(createMailboxConfig(authData, createInfraConfig(application, str9, createProxyProvider), r26, vrMsysMqttClientCallbacks, consumer));
    }
}
