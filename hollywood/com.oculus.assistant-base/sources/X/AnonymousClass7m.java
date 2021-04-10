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
import java.io.File;
import java.lang.reflect.InvocationTargetException;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.Locale;

/* renamed from: X.7m  reason: invalid class name */
public final class AnonymousClass7m extends Handler {
    public static final C0674ew A0C = new C0674ew();
    public static final DR A0D = new DR();
    public PrivacyControlledUploader A00;
    public AnonymousClass7t A01;
    public Iterator A02;
    public boolean A03;
    public boolean A04;
    public final Context A05;
    public final AnonymousClass0m A06 = new AnonymousClass0m(2);
    public final C0692fT A07 = new C0692fT(this);
    public final AnonymousClass7k A08;
    public final HandlerThread A09;
    public final AnonymousClass7l A0A;
    public final boolean A0B;

    private void A01(String str, Throwable th) {
        A02(false);
        A00();
        throw new RuntimeException(AnonymousClass08.A04("Failed to create instance of ", str), th);
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
            AnonymousClass7k r0 = this.A08;
            AnonymousClass7x r2 = new AnonymousClass7x(r0.A00, r0.A02, this.A04, this.A06);
            Intent intent = new Intent("com.facebook.analytics2.action.UPLOAD_JOB_RAN").setPackage(context.getPackageName());
            Bundle bundle = new Bundle();
            bundle.putInt("job_id", r2.A00);
            bundle.putString("hack_action", r2.A02);
            bundle.putBoolean("will_retry", r2.A03);
            AnonymousClass0m r10 = r2.A01;
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
                        C0139Dd.A0N("UploadServiceBus", "Analytics2 not allowed in this application.", e);
                    } else {
                        Throwable cause = e.getCause();
                        if (cause != null) {
                            while (cause.getCause() != null) {
                                cause = cause.getCause();
                            }
                            if (cause instanceof DeadObjectException) {
                                C0139Dd.A0L("UploadServiceBus", "Failed to send broadcast. Handler may have died", e);
                            }
                        }
                        throw e;
                    }
                }
                this.A0A.A48();
                if (this.A0B) {
                    this.A09.quit();
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

    public final void handleMessage(Message message) {
        Object obj;
        C0674ew ewVar;
        if (this.A03) {
            C0139Dd.A09("UploadJobHandler", "Ignoring msg type %d after MSG_HALT received");
            return;
        }
        int i = message.what;
        if (i == 1) {
            Trace.beginSection("doInit");
            try {
                AnonymousClass7k r0 = this.A08;
                AnonymousClass7j r3 = r0.A01;
                Integer valueOf = Integer.valueOf(r0.A00);
                File file = r3.A01;
                C0139Dd.A0H("UploadJobHandler", "Starting upload for jobId %d of %s", valueOf, file);
                String str = r3.A09;
                try {
                    Context context = this.A05;
                    AnonymousClass6k A002 = AnonymousClass6k.A00(context);
                    ArrayList arrayList = A002.A05;
                    synchronized (A002) {
                        try {
                            obj = AnonymousClass6k.A01(A002, arrayList, Class.forName(str));
                        } catch (ClassNotFoundException e) {
                            C0139Dd.A0V("ContextConstructorHelper", e, "Cannot find class: %s", str);
                            obj = null;
                        }
                    }
                    AnonymousClass87 r7 = (AnonymousClass87) obj;
                    if (r7 != null) {
                        String str2 = r3.A05;
                        if (str2 != null) {
                            AnonymousClass6k A003 = AnonymousClass6k.A00(context);
                            ewVar = (C0674ew) AnonymousClass6k.A02(A003, A003.A06, str2);
                        } else {
                            ewVar = A0C;
                        }
                        String str3 = r3.A06;
                        if (str3 != null) {
                            AnonymousClass6k A004 = AnonymousClass6k.A00(context);
                            AnonymousClass6k.A02(A004, A004.A03, str3);
                        }
                        AnonymousClass6Z r4 = new AnonymousClass6Z(context, A0D);
                        String str4 = r3.A03;
                        if (str4 != null) {
                            AnonymousClass6k A005 = AnonymousClass6k.A00(context);
                            AnonymousClass6k.A02(A005, A005.A01, str4);
                        }
                        AnonymousClass77 r32 = new AnonymousClass77(file, r4, this.A07, r3.A00);
                        this.A02 = r32;
                        PrivacyControlledUploader privacyControlledUploader = this.A00;
                        if (privacyControlledUploader == null) {
                            privacyControlledUploader = new PrivacyControlledUploader(r7, ewVar);
                            this.A00 = privacyControlledUploader;
                        } else {
                            privacyControlledUploader.A00 = ewVar;
                            privacyControlledUploader.A01 = r7;
                        }
                        this.A01 = new AnonymousClass7t(privacyControlledUploader, r32, new C0693fU(this));
                        if (!r32.hasNext()) {
                            C0139Dd.A0B("UploadJobHandler", "Nothing to upload, why did you run me?");
                        }
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
            } catch (Throwable th) {
                Trace.endSection();
                throw th;
            }
        } else if (i != 2) {
            if (i != 3) {
                if (i == 4) {
                    Trace.beginSection("doNoMoreInput");
                    try {
                        C0139Dd.A0F("UploadJobHandler", "Upload for jobId %d is successful", Integer.valueOf(this.A08.A00));
                        A02(false);
                    } catch (Throwable th2) {
                        Trace.endSection();
                        throw th2;
                    }
                } else if (i == 5) {
                    Object obj2 = message.obj;
                    Trace.beginSection("doUploadFailure");
                    try {
                        if (C0139Dd.A01.A3Y(3)) {
                            C0139Dd.A0H("UploadJobHandler", "Upload failed for %d : %s", Integer.valueOf(this.A08.A00), obj2.toString());
                        }
                        A02(true);
                    } catch (Throwable th3) {
                        Trace.endSection();
                        throw th3;
                    }
                } else {
                    throw new IllegalArgumentException(AnonymousClass08.A00("Unknown what=", i));
                }
                Trace.endSection();
            } else {
                C0139Dd.A0F("UploadJobHandler", "Acknowledged upload halt for %d", Integer.valueOf(this.A08.A00));
                this.A03 = true;
            }
            A00();
            return;
        } else {
            Trace.beginSection("doMaybeUploadNext");
            try {
                AnonymousClass7t r1 = this.A01;
                if (r1.A02.hasNext()) {
                    Iterator it = r1.A02;
                    if (it.hasNext()) {
                        it.next();
                    } else {
                        throw new IllegalStateException("No more batches to upload");
                    }
                } else {
                    sendMessage(obtainMessage(4));
                }
            } catch (Throwable th4) {
                Trace.endSection();
                throw th4;
            }
        }
        Trace.endSection();
        return;
    }

    public AnonymousClass7m(Context context, HandlerThread handlerThread, boolean z, AnonymousClass7k r6, AnonymousClass7l r7) {
        super(handlerThread.getLooper());
        this.A05 = context;
        this.A09 = handlerThread;
        this.A08 = r6;
        this.A0A = r7;
        this.A0B = z;
    }

    private void A02(boolean z) {
        C0139Dd.A0F("UploadJobHandler", "UploadJobHandler#signalVoluntaryTermination needsReschedule: %s", Boolean.valueOf(z));
        if (z) {
            this.A04 = true;
        }
        this.A0A.A4R(z);
    }
}
