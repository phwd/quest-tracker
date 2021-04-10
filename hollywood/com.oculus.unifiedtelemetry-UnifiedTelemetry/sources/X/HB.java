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
import javax.annotation.Nullable;

public final class HB extends Handler {
    public static final Z3 A0C = new Z3();
    public static final MG A0D = MG.A00();
    public PrivacyControlledUploader A00;
    @Nullable
    public HJ A01;
    @Nullable
    public Iterator<Z9> A02;
    public boolean A03;
    public boolean A04;
    public final Context A05;
    public final C00062o<String, File> A06 = new C00062o<>(2);
    public final Ya A07 = new Ya(this);
    public final H9 A08;
    public final HA A09;
    public final HandlerThread A0A;
    public final boolean A0B;

    private void A01(String str, Throwable th) {
        this.A09.A46(false);
        A00();
        throw new RuntimeException(AnonymousClass06.A04("Failed to create instance of ", str), th);
    }

    /* JADX DEBUG: Multi-variable search result rejected for r2v5, resolved type: java.lang.Object[] */
    /* JADX DEBUG: Multi-variable search result rejected for r1v15, resolved type: java.lang.Object[] */
    /* JADX DEBUG: Multi-variable search result rejected for r0v34, resolved type: java.lang.String */
    /* JADX WARN: Multi-variable type inference failed */
    private void A00() {
        int i;
        String str;
        Trace.beginSection("exitStateMachine");
        try {
            Context context = this.A05;
            H9 h9 = this.A08;
            HO ho = new HO(h9.A00, h9.A02, this.A04, this.A06);
            Intent intent = new Intent("com.facebook.analytics2.action.UPLOAD_JOB_RAN").setPackage(context.getPackageName());
            Bundle bundle = new Bundle();
            bundle.putInt("job_id", ho.A00);
            bundle.putString("hack_action", ho.A02);
            bundle.putBoolean("will_retry", ho.A03);
            C00062o<String, File> r10 = ho.A01;
            int size = r10.size();
            ArrayList<String> arrayList = new ArrayList<>(size);
            ArrayList arrayList2 = new ArrayList(size);
            for (int i2 = 0; i2 < size; i2++) {
                Object[] objArr = r10.A02;
                File file = (File) objArr[(i2 << 1) + 1];
                arrayList.add(objArr[i2 << 1]);
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
                        Mu.A04("UploadServiceBus", "Analytics2 not allowed in this application.", e);
                    } else {
                        Throwable cause = e.getCause();
                        if (cause != null) {
                            while (cause.getCause() != null) {
                                cause = cause.getCause();
                            }
                            if (cause instanceof DeadObjectException) {
                                Mu.A02("UploadServiceBus", "Failed to send broadcast. Handler may have died", e);
                            }
                        }
                        throw e;
                    }
                }
                this.A09.A3h();
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
                Iterator<PackageInfo> it = packageManager.getInstalledPackages(i).iterator();
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
        Z3 z3;
        if (!this.A03) {
            int i = message.what;
            if (i == 1) {
                Trace.beginSection("doInit");
                H8 h8 = this.A08.A01;
                String str = h8.A09;
                try {
                    Context context = this.A05;
                    AbstractC0090Hb A042 = G7.A00(context).A04(str);
                    if (A042 != null) {
                        String str2 = h8.A05;
                        if (str2 != null) {
                            G7 A002 = G7.A00(context);
                            z3 = (Z3) G7.A01(A002, A002.A03, str2);
                        } else {
                            z3 = A0C;
                        }
                        String str3 = h8.A06;
                        if (str3 != null) {
                            G7 A003 = G7.A00(context);
                            G7.A01(A003, A003.A01, str3);
                        }
                        Fw fw = new Fw(context, A0D, h8.A04);
                        String str4 = h8.A03;
                        if (str4 != null) {
                            G7 A004 = G7.A00(context);
                            G7.A01(A004, A004.A00, str4);
                        }
                        GY gy = new GY(h8.A01, fw, this.A07, h8.A00);
                        this.A02 = gy;
                        PrivacyControlledUploader privacyControlledUploader = this.A00;
                        if (privacyControlledUploader == null) {
                            privacyControlledUploader = new PrivacyControlledUploader(A042, z3);
                            this.A00 = privacyControlledUploader;
                        } else {
                            privacyControlledUploader.A00 = z3;
                            privacyControlledUploader.A01 = A042;
                        }
                        this.A01 = new HJ(privacyControlledUploader, h8.A02, gy, new YZ(this));
                        gy.hasNext();
                        sendMessage(obtainMessage(2));
                    }
                } catch (IllegalAccessException e) {
                    A01(str, e);
                    throw new RuntimeException("Redex: Unreachable code after no-return invoke");
                } catch (InstantiationException e2) {
                    A01(str, e2);
                    throw new RuntimeException("Redex: Unreachable code after no-return invoke");
                } catch (NoSuchMethodException e3) {
                    A01(str, e3);
                    throw new RuntimeException("Redex: Unreachable code after no-return invoke");
                } catch (InvocationTargetException e4) {
                    A01(str, e4);
                    throw new RuntimeException("Redex: Unreachable code after no-return invoke");
                }
            } else if (i != 2) {
                if (i != 3) {
                    if (i == 4) {
                        Trace.beginSection("doNoMoreInput");
                        this.A09.A46(false);
                    } else if (i == 5) {
                        Object obj = message.obj;
                        Trace.beginSection("doUploadFailure");
                        try {
                            if (Mu.A01.A3F(3)) {
                                obj.toString();
                            }
                            this.A04 = true;
                            this.A09.A46(true);
                        } catch (Throwable th) {
                            Trace.endSection();
                            throw th;
                        }
                    } else {
                        throw new IllegalArgumentException(AnonymousClass06.A01("Unknown what=", i));
                    }
                    Trace.endSection();
                } else {
                    this.A03 = true;
                }
                A00();
                return;
            } else {
                Trace.beginSection("doMaybeUploadNext");
                HJ hj = this.A01;
                if (hj.A00.hasNext()) {
                    hj.A00();
                } else {
                    sendMessage(obtainMessage(4));
                }
            }
            Trace.endSection();
        }
    }

    public HB(Context context, HandlerThread handlerThread, boolean z, H9 h9, HA ha) {
        super(handlerThread.getLooper());
        this.A05 = context;
        this.A0A = handlerThread;
        this.A08 = h9;
        this.A09 = ha;
        this.A0B = z;
    }
}
