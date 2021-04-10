package X;

import android.content.Context;
import android.os.Trace;
import com.facebook.messengervr.msys.MessengerVrTableToProcedureNameMapRegisterer;
import com.facebook.msys.dasm.DasmSupportHelper;
import com.facebook.msys.mca.Mailbox;
import com.facebook.msys.mci.AppState;
import com.facebook.msys.mci.AuthDataStorage;
import com.facebook.msys.mci.Database;
import com.facebook.msys.mci.DatabaseFileManager;
import com.facebook.msys.mci.Execution;
import com.facebook.msys.mci.FileManager;
import com.facebook.msys.mci.JsonSerialization;
import com.facebook.msys.mci.Log;
import com.facebook.msys.mci.NetworkSession;
import com.facebook.msys.mci.NotificationCenter;
import com.facebook.msys.mci.Proxies;
import com.facebook.msys.mci.ProxyProvider;
import com.oculus.messengervr.fb.$$Lambda$VqnsstFgf_ntkvyq51ubqbvxcw2;
import com.oculus.messengervr.fb.$$Lambda$av0k0cWLAsV1YXgmih7oeXvqYR02;
import com.oculus.messengervr.fb.$$Lambda$n4FJ7IwJoHYz4RdBZXK2LqeXys02;
import java.io.File;
import java.io.IOException;
import java.util.LinkedHashSet;
import java.util.Set;
import java.util.UUID;
import javax.annotation.Nullable;
import javax.annotation.concurrent.GuardedBy;
import javax.annotation.concurrent.ThreadSafe;

@ThreadSafe
/* renamed from: X.1Yg  reason: invalid class name and case insensitive filesystem */
public final class C06251Yg implements AnonymousClass1Z6 {
    @Nullable
    public C06261Yh A00;
    @Nullable
    public AnonymousClass1YU A01;
    @Nullable
    public Mailbox A02;
    public final C06281Yj A03;
    public final Set<AnonymousClass1YZ<Mailbox>> A04 = new LinkedHashSet();
    public volatile Boolean A05 = false;

    @Override // X.AnonymousClass1Z6
    @GuardedBy("this")
    public final synchronized void A9T(AnonymousClass1YZ<Mailbox> r26) {
        C06311Ys r4;
        NotificationCenter notificationCenter;
        NetworkSession networkSession;
        Database database;
        Mailbox mailbox = this.A02;
        if (mailbox != null) {
            if (mailbox.getState() == 0) {
                Execution.executePossiblySync(new AnonymousClass1ZM(this.A02), 1);
            }
            Execution.executePossiblySync(new C06241Yf(this, r26), 1);
        } else if (this.A05.booleanValue()) {
            this.A04.add(r26);
        } else {
            this.A05 = true;
            this.A04.add(r26);
            C06261Yh r5 = this.A00;
            if (r5 == null) {
                r5 = new C06261Yh();
                this.A00 = r5;
            }
            C06281Yj r7 = this.A03;
            AnonymousClass1YS r6 = new AnonymousClass1YS(this);
            if (r7 != null) {
                r5.A00 = r7;
                AbstractC06301Yq.A00();
                C03850of A002 = AnonymousClass1RO.A00(r7.A01.A00);
                C03850of.A00(A002);
                synchronized (A002.A03) {
                    try {
                        A002.A05.get("mailbox_has_init");
                    } catch (ClassCastException e) {
                        throw new RuntimeException(AnonymousClass006.A0B("LightSharedPreferences threw an exception for Key: ", "mailbox_has_init", "; Raw file: ", A002.A02.A00()), e);
                    }
                }
                Context context = r5.A00.A01.A00;
                synchronized (DasmSupportHelper.class) {
                    Trace.beginSection("DasmSupportHelper.initialize");
                    try {
                        if (DasmSupportHelper.sContext == null) {
                            DasmSupportHelper.sContext = context.getApplicationContext();
                        }
                    } finally {
                        Trace.endSection();
                    }
                }
                C06321Yt r8 = r5.A00.A01;
                synchronized (AnonymousClass1ZG.class) {
                    String obj = UUID.randomUUID().toString();
                    synchronized (C06311Ys.class) {
                        r4 = C06311Ys.A04;
                        r4.A00.add(obj);
                    }
                    ProxyProvider proxyProvider = r8.A01;
                    Context context2 = r8.A00;
                    synchronized (AnonymousClass1ZH.class) {
                        synchronized (Log.class) {
                            Trace.beginSection("registerLogger");
                            try {
                                if (!Log.sRegistered) {
                                    Log.registerLoggerNative(823, 5);
                                    Log.setLogLevel(AnonymousClass0MD.A01.A4S());
                                    AnonymousClass1Z0 r9 = new AnonymousClass1Z0();
                                    synchronized (AnonymousClass0MD.class) {
                                        AnonymousClass0MD.A00.add(r9);
                                    }
                                    Log.sRegistered = true;
                                }
                            } finally {
                                Trace.endSection();
                            }
                        }
                        try {
                            Proxies.configure(proxyProvider);
                        } catch (IllegalStateException e2) {
                            Log.log(5, AnonymousClass006.A07("Proxies already configured: ", e2.getMessage()));
                        }
                        synchronized (AuthDataStorage.class) {
                            Trace.beginSection("AuthDataStorage.initialize");
                            try {
                                if (!AuthDataStorage.sInitialized) {
                                    AuthDataStorage.sSharedPreferences = context2.getSharedPreferences("msys-auth-data", 0);
                                    AuthDataStorage.sObjectSerializer = new AnonymousClass1PG();
                                    AuthDataStorage.nativeInitialize();
                                    AuthDataStorage.sInitialized = true;
                                }
                            } finally {
                                Trace.endSection();
                            }
                        }
                        Execution.initialize();
                        synchronized (JsonSerialization.class) {
                            Trace.beginSection("JsonSerialization.initialize");
                            try {
                                if (!JsonSerialization.sInitialized) {
                                    JsonSerialization.nativeInitialize();
                                    JsonSerialization.sInitialized = true;
                                }
                            } finally {
                                Trace.endSection();
                            }
                        }
                        File cacheDir = context2.getCacheDir();
                        synchronized (FileManager.class) {
                            Trace.beginSection("FileManager.initialize");
                            try {
                                if (!FileManager.sInitialized) {
                                    FileManager.mCacheDir = cacheDir;
                                    FileManager.nativeInitialize();
                                    FileManager.sInitialized = true;
                                }
                            } finally {
                                Trace.endSection();
                            }
                        }
                    }
                    AbstractC06301Yq.A00();
                    NotificationCenter notificationCenter2 = new NotificationCenter();
                    synchronized (C06311Ys.class) {
                        r4.A03 = notificationCenter2;
                    }
                    AbstractC06301Yq.A00();
                    synchronized (C06311Ys.class) {
                        notificationCenter = r4.A03;
                    }
                    synchronized (C06311Ys.class) {
                        networkSession = r4.A02;
                    }
                    if (networkSession == null) {
                        NetworkSession networkSession2 = new NetworkSession(r8.A03, notificationCenter, r8.A02);
                        synchronized (C06311Ys.class) {
                            r4.A02 = networkSession2;
                        }
                        AbstractC06301Yq.A00();
                    }
                    AppState appState = new AppState();
                    synchronized (C06311Ys.class) {
                        r4.A01 = appState;
                    }
                }
                C06271Yi r42 = new C06271Yi(r5, r7.A03, r7, r6);
                AnonymousClass1RW r3 = new AnonymousClass1RW(r5);
                MessengerVrTableToProcedureNameMapRegisterer messengerVrTableToProcedureNameMapRegisterer = r5.A00.A00;
                if (messengerVrTableToProcedureNameMapRegisterer != null) {
                    messengerVrTableToProcedureNameMapRegisterer.registerMappings();
                }
                C06281Yj r72 = r5.A00;
                try {
                    Context context3 = r72.A01.A00;
                    String str = r72.A0B;
                    String canonicalPath = context3.getDir(str, 0).getCanonicalPath();
                    boolean booleanValue = r72.A09.booleanValue();
                    boolean booleanValue2 = r72.A08.booleanValue();
                    boolean booleanValue3 = r72.A07.booleanValue();
                    boolean booleanValue4 = r72.A0A.booleanValue();
                    synchronized (Database.class) {
                        Trace.beginSection("Database.config");
                        try {
                            if (!Database.sConfigured) {
                                Database.configNative(canonicalPath);
                                if (booleanValue) {
                                    Database.enableSqliteErrorLogs();
                                }
                                Database.enableReadOnlyConnection(booleanValue2);
                                Database.enableInteractiveReadOnlyConnection(booleanValue3);
                                if (booleanValue4) {
                                    Database.enableSqliteOndemandLoading();
                                }
                                Database.sConfigured = true;
                            }
                        } finally {
                            Trace.endSection();
                        }
                    }
                    File databasePath = context3.getDatabasePath(str);
                    File parentFile = databasePath.getParentFile();
                    if (parentFile != null) {
                        parentFile.mkdirs();
                        long parseLong = Long.parseLong(r72.A02.getFacebookUserID());
                        String canonicalPath2 = databasePath.getCanonicalPath();
                        AnonymousClass1Yn r2 = AnonymousClass1Yn.A03;
                        Database database2 = new Database(parseLong, canonicalPath2, r2.A01(), $$Lambda$n4FJ7IwJoHYz4RdBZXK2LqeXys02.INSTANCE, $$Lambda$av0k0cWLAsV1YXgmih7oeXvqYR02.INSTANCE, $$Lambda$VqnsstFgf_ntkvyq51ubqbvxcw2.INSTANCE, null);
                        AbstractC06301Yq.A00();
                        synchronized (r2) {
                            r2.A02 = database2;
                        }
                        synchronized (DatabaseFileManager.class) {
                            DatabaseFileManager.maybeDecryptDatabaseNative(database2);
                        }
                        synchronized (r2) {
                            database = r2.A02;
                        }
                        database.open(r42, r3);
                    } else {
                        throw null;
                    }
                } catch (IOException e3) {
                    throw new RuntimeException("MsysDatabase initialization failed.", e3);
                }
            } else {
                throw null;
            }
        }
    }

    public C06251Yg(C06281Yj r2) {
        this.A03 = r2;
    }
}
