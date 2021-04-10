package X;

import android.app.PendingIntent;
import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageInfo;
import android.content.pm.PackageManager;
import android.os.Binder;
import android.os.Bundle;
import android.os.DeadObjectException;
import android.os.Handler;
import android.os.HandlerThread;
import android.os.Message;
import android.os.Process;
import android.os.Trace;
import com.facebook.analytics2.logger.PrivacyControlledUploader;
import com.facebook.analytics2.uploader.Uploader;
import java.io.File;
import java.lang.reflect.InvocationTargetException;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.Locale;
import javax.annotation.Nullable;

/* renamed from: X.0Ha  reason: invalid class name */
public final class AnonymousClass0Ha extends Handler {
    public static final AnonymousClass0qH A0C = new AnonymousClass0qH();
    public static final C01310Mu A0D = new C01310Mu();
    public PrivacyControlledUploader A00;
    @Nullable
    public C00920Hk A01;
    @Nullable
    public Iterator<AbstractC06950qN> A02;
    public boolean A03;
    public boolean A04;
    public final Context A05;
    public final C000602o<String, File> A06 = new C000602o<>(2);
    public final AnonymousClass0q8 A07 = new AnonymousClass0q8(this);
    public final AnonymousClass0HY A08;
    public final AnonymousClass0HZ A09;
    public final HandlerThread A0A;
    public final boolean A0B;

    private void A01(String str, Throwable th) {
        this.A09.A7D(false);
        A00();
        throw new RuntimeException(AnonymousClass006.A05("Failed to create instance of ", str), th);
    }

    /* JADX DEBUG: Multi-variable search result rejected for r2v5, resolved type: java.lang.Object[] */
    /* JADX DEBUG: Multi-variable search result rejected for r1v15, resolved type: java.lang.Object[] */
    /* JADX DEBUG: Multi-variable search result rejected for r0v42, resolved type: java.lang.String */
    /* JADX WARN: Multi-variable type inference failed */
    private void A00() {
        String str;
        Trace.beginSection("exitStateMachine");
        try {
            Context context = this.A05;
            AnonymousClass0HY r0 = this.A08;
            C00950Hp r2 = new C00950Hp(r0.A00, r0.A02, this.A04, this.A06);
            Intent intent = new Intent("com.facebook.analytics2.action.UPLOAD_JOB_RAN").setPackage(context.getPackageName());
            Bundle bundle = new Bundle();
            bundle.putInt("job_id", r2.A00);
            bundle.putString("hack_action", r2.A02);
            bundle.putBoolean("will_retry", r2.A03);
            C000602o<String, File> r10 = r2.A01;
            int size = r10.size();
            ArrayList<String> arrayList = new ArrayList<>(size);
            ArrayList arrayList2 = new ArrayList(size);
            for (int i = 0; i < size; i++) {
                Object[] objArr = r10.A02;
                File file = (File) objArr[(i << 1) + 1];
                arrayList.add(objArr[i << 1]);
                if (file != null) {
                    str = file.getAbsolutePath();
                } else {
                    str = null;
                }
                arrayList2.add(str);
            }
            bundle.putStringArrayList("successful_processes", arrayList);
            bundle.putStringArrayList("newest_files_uploaded", arrayList2);
            Intent putExtras = intent.putExtras(bundle);
            Intent intent2 = new Intent("com.facebook.analytics2.action.UPLOAD_JOB_RAN.token");
            intent2.setPackage(context.getPackageName());
            try {
                try {
                    context.sendBroadcast(putExtras.putExtra("uploader_service_broadcast_auth_token", PendingIntent.getBroadcast(context, 0, intent2, 1073741824)));
                } catch (RuntimeException e) {
                    if (e instanceof SecurityException) {
                        AnonymousClass0NO.A0D("UploadServiceBus", "Analytics2 not allowed in this application.", e);
                    } else {
                        Throwable cause = e.getCause();
                        if (cause != null) {
                            while (cause.getCause() != null) {
                                cause = cause.getCause();
                            }
                            if (cause instanceof DeadObjectException) {
                                AnonymousClass0NO.A0B("UploadServiceBus", "Failed to send broadcast. Handler may have died", e);
                            }
                        }
                        throw e;
                    }
                }
                this.A09.A65();
                if (this.A0B) {
                    this.A0A.quit();
                }
            } catch (SecurityException e2) {
                PackageManager packageManager = context.getPackageManager();
                String packageName = context.getPackageName();
                int myUid = Process.myUid();
                String nameForUid = packageManager.getNameForUid(myUid);
                int callingUid = Binder.getCallingUid();
                String nameForUid2 = packageManager.getNameForUid(callingUid);
                String str2 = null;
                Iterator<PackageInfo> it = packageManager.getInstalledPackages(0).iterator();
                while (true) {
                    if (!it.hasNext()) {
                        break;
                    }
                    PackageInfo next = it.next();
                    if (next.applicationInfo.uid == myUid) {
                        str2 = next.packageName;
                        break;
                    }
                }
                throw new SecurityException(String.format(Locale.US, "%s, ctx_pm=%s, uid=%d, uid_pn=%s, calling_uid=%d, calling_uid_pn=%s, pi_pn=%s", e2.getMessage(), packageName, Integer.valueOf(myUid), nameForUid, Integer.valueOf(callingUid), nameForUid2, str2), e2);
            }
        } finally {
            Trace.endSection();
        }
    }

    /* JADX DEBUG: Failed to insert an additional move for type inference into block B:37:0x0093 */
    /* JADX DEBUG: Multi-variable search result rejected for r6v3, resolved type: int */
    /* JADX WARN: Multi-variable type inference failed */
    /* JADX WARN: Type inference failed for: r6v0 */
    /* JADX WARN: Type inference failed for: r6v5, types: [java.lang.Object] */
    public final void handleMessage(Message message) {
        PrivacyControlledUploader privacyControlledUploader;
        AnonymousClass0qH r5;
        if (!this.A03) {
            int i = message.what;
            int i2 = 1;
            if (i == 1) {
                Trace.beginSection("doInit");
                AnonymousClass0HX r3 = this.A08.A01;
                String str = r3.A09;
                try {
                    Context context = this.A05;
                    AnonymousClass0GM A002 = AnonymousClass0GM.A00(context);
                    ArrayList<Uploader> arrayList = A002.A05;
                    synchronized (A002) {
                        try {
                            i2 = AnonymousClass0GM.A01(A002, arrayList, Class.forName(str));
                            privacyControlledUploader = i2;
                        } catch (ClassNotFoundException e) {
                            Object[] objArr = new Object[i2];
                            objArr[0] = str;
                            AnonymousClass0NO.A0K("ContextConstructorHelper", e, "Cannot find class: %s", objArr);
                            privacyControlledUploader = null;
                        }
                    }
                    PrivacyControlledUploader privacyControlledUploader2 = privacyControlledUploader;
                    if (privacyControlledUploader2 != null) {
                        String str2 = r3.A05;
                        if (str2 != null) {
                            AnonymousClass0GM A003 = AnonymousClass0GM.A00(context);
                            r5 = (AnonymousClass0qH) AnonymousClass0GM.A02(A003, A003.A06, str2);
                        } else {
                            r5 = A0C;
                        }
                        String str3 = r3.A06;
                        if (str3 != null) {
                            AnonymousClass0GM A004 = AnonymousClass0GM.A00(context);
                            AnonymousClass0GM.A02(A004, A004.A03, str3);
                        }
                        AnonymousClass0GB r4 = new AnonymousClass0GB(context, A0D);
                        String str4 = r3.A03;
                        if (str4 != null) {
                            AnonymousClass0GM A005 = AnonymousClass0GM.A00(context);
                            AnonymousClass0GM.A02(A005, A005.A01, str4);
                        }
                        AnonymousClass0Gr r32 = new AnonymousClass0Gr(r3.A01, r4, this.A07, r3.A00);
                        this.A02 = r32;
                        PrivacyControlledUploader privacyControlledUploader3 = this.A00;
                        if (privacyControlledUploader3 == null) {
                            privacyControlledUploader3 = new PrivacyControlledUploader(privacyControlledUploader2, r5);
                            this.A00 = privacyControlledUploader3;
                        } else {
                            privacyControlledUploader3.A00 = r5;
                            privacyControlledUploader3.A01 = privacyControlledUploader2;
                        }
                        this.A01 = new C00920Hk(privacyControlledUploader3, r32, new AnonymousClass0q6(this));
                        r32.hasNext();
                        sendMessage(obtainMessage(2));
                    }
                } catch (IllegalAccessException e2) {
                    A01(str, e2);
                    throw new RuntimeException("Redex: Unreachable code after no-return invoke");
                } catch (InstantiationException e3) {
                    A01(str, e3);
                    throw new RuntimeException("Redex: Unreachable code after no-return invoke");
                } catch (NoSuchMethodException e4) {
                    A01(str, e4);
                    throw new RuntimeException("Redex: Unreachable code after no-return invoke");
                } catch (InvocationTargetException e5) {
                    A01(str, e5);
                    throw new RuntimeException("Redex: Unreachable code after no-return invoke");
                }
            } else if (i != 2) {
                if (i != 3) {
                    if (i == 4) {
                        Trace.beginSection("doNoMoreInput");
                        this.A09.A7D(false);
                    } else if (i == 5) {
                        Object obj = message.obj;
                        Trace.beginSection("doUploadFailure");
                        try {
                            if (AnonymousClass0NO.A01.A54(3)) {
                                obj.toString();
                            }
                            this.A04 = true;
                            this.A09.A7D(true);
                        } catch (Throwable th) {
                            Trace.endSection();
                            throw th;
                        }
                    } else {
                        throw new IllegalArgumentException(AnonymousClass006.A01("Unknown what=", i));
                    }
                    Trace.endSection();
                } else {
                    this.A03 = true;
                }
                A00();
                return;
            } else {
                Trace.beginSection("doMaybeUploadNext");
                C00920Hk r1 = this.A01;
                if (r1.A00.hasNext()) {
                    Iterator<AbstractC06950qN> it = r1.A00;
                    if (it.hasNext()) {
                        it.next();
                    } else {
                        throw new IllegalStateException("No more batches to upload");
                    }
                } else {
                    sendMessage(obtainMessage(4));
                }
            }
            Trace.endSection();
            return;
        }
        return;
    }

    public AnonymousClass0Ha(Context context, HandlerThread handlerThread, boolean z, AnonymousClass0HY r6, AnonymousClass0HZ r7) {
        super(handlerThread.getLooper());
        this.A05 = context;
        this.A0A = handlerThread;
        this.A08 = r6;
        this.A09 = r7;
        this.A0B = z;
    }
}
