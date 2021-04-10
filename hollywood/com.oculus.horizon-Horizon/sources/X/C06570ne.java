package X;

import android.content.Context;
import android.content.SharedPreferences;
import android.os.Handler;
import android.text.TextUtils;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStreamWriter;
import java.io.UnsupportedEncodingException;
import java.util.Queue;
import java.util.UUID;
import java.util.concurrent.ConcurrentLinkedQueue;
import java.util.concurrent.atomic.AtomicBoolean;
import javax.annotation.concurrent.ThreadSafe;

@ThreadSafe
/* renamed from: X.0ne  reason: invalid class name and case insensitive filesystem */
public final class C06570ne {
    public static final AnonymousClass0Vq A0F = AnonymousClass0Vq.A00();
    public C01500Vc A00;
    public final SharedPreferences A01;
    public final Handler A02;
    public final C01510Ve A03;
    public final AnonymousClass0WY<String> A04;
    public final Runnable A05;
    public final String A06;
    public final String A07;
    public final String A08;
    public final Queue<Runnable> A09 = new ConcurrentLinkedQueue();
    public final AtomicBoolean A0A = new AtomicBoolean(false);
    public final Context A0B;
    public final AnonymousClass0Vd A0C;
    public final C04790jY A0D;
    public final String A0E;

    public static void A00(C06570ne r10) {
        String str;
        IOException e;
        if (!r10.A00.A07.isEmpty()) {
            AnonymousClass0Vd r0 = r10.A0C;
            C01500Vc r7 = r10.A00;
            str = "failed to close writer";
            File file = r0.A00;
            if (!file.exists() && !file.mkdir()) {
                AnonymousClass0NO.A08("AnalyticsStorage", "Unable to open analytics storage.");
            }
            Object[] objArr = new Object[2];
            UUID uuid = r7.A08;
            if (uuid == null) {
                uuid = UUID.randomUUID();
                r7.A08 = uuid;
            }
            objArr[0] = uuid.toString();
            objArr[1] = Integer.valueOf(r7.A00);
            File file2 = new File(file, String.format(null, "%s_%d.batch", objArr));
            if (file2.exists() && !file2.delete()) {
                AnonymousClass0NO.A0F("AnalyticsStorage", "File %s was not deleted", file2);
            }
            r7.A01 = System.currentTimeMillis();
            try {
                FileOutputStream fileOutputStream = new FileOutputStream(file2);
                try {
                    OutputStreamWriter outputStreamWriter = new OutputStreamWriter(fileOutputStream, "UTF8");
                    try {
                        outputStreamWriter.write(r7.toString());
                    } catch (IOException e2) {
                        AnonymousClass0NO.A0I("AnalyticsStorage", e2, "failed to write session to file");
                    } catch (Throwable th) {
                        try {
                            outputStreamWriter.close();
                            throw th;
                        } catch (IOException e3) {
                            AnonymousClass0NO.A0I("AnalyticsStorage", e3, str);
                            throw th;
                        }
                    }
                    try {
                        outputStreamWriter.close();
                    } catch (IOException e4) {
                        e = e4;
                    }
                } catch (UnsupportedEncodingException e5) {
                    AnonymousClass0NO.A0I("AnalyticsStorage", e5, "UTF8 encoding is not supported");
                    try {
                        fileOutputStream.close();
                    } catch (IOException e6) {
                        e = e6;
                        str = "failed to close output stream";
                    }
                }
            } catch (FileNotFoundException e7) {
                AnonymousClass0NO.A0L("AnalyticsStorage", e7, "Batch file creation failed %s", file2);
            }
            C01500Vc r1 = r10.A00;
            r1.A07.clear();
            r1.A00++;
        }
        return;
        AnonymousClass0NO.A0I("AnalyticsStorage", e, str);
        C01500Vc r12 = r10.A00;
        r12.A07.clear();
        r12.A00++;
    }

    public static void A01(C06570ne r3, Runnable runnable) {
        r3.A09.add(runnable);
        if (r3.A0A.compareAndSet(false, true)) {
            A0F.execute(r3.A05);
        }
    }

    public final void A02(AnonymousClass0VX r4) {
        C04790jY r1 = this.A0D;
        if (r1.A02 || r1.A01.getBoolean(EnumC02400aQ.ANALYTIC_IS_EMPLOYEE.getPrefKey(), false)) {
            A01(this, new RunnableC01540Vh(this, r4));
        }
    }

    /* JADX WARN: Incorrect args count in method signature: (Landroid/content/Context;Ljava/lang/String;LX/0WY<Ljava/lang/String;>;Lcom/facebook/rti/common/analytics/defaultlogger/AnalyticsSamplePolicy;Landroid/content/SharedPreferences;LX/0WY<Ljava/lang/String;>;Ljava/lang/String;Ljava/lang/String;Ljava/lang/String;Ljava/lang/String;Ljava/lang/String;Ljava/lang/String;)V */
    public C06570ne(Context context, String str, AnonymousClass0WY r7, C04790jY r8, SharedPreferences sharedPreferences, AnonymousClass0WY r10, String str2, String str3, String str4) {
        this.A0B = context;
        this.A0E = str;
        this.A01 = sharedPreferences;
        this.A04 = r10;
        this.A08 = str4;
        this.A07 = str3;
        this.A06 = "567310203415052";
        this.A02 = new HandlerC01520Vf(this, context.getMainLooper());
        this.A05 = new RunnableC01530Vg(this);
        this.A0C = new AnonymousClass0Vd(context.getApplicationContext(), this.A0E);
        this.A03 = new C01510Ve(context.getApplicationContext(), this.A0E, r7, str2);
        this.A0D = r8;
        if (this.A00 != null) {
            A00(this);
        }
        C01500Vc r3 = new C01500Vc();
        r3.A04 = this.A07;
        r3.A05 = this.A08;
        String string = this.A01.getString("fb_uid", "");
        r3.A06 = TextUtils.isEmpty(string) ? "0" : string;
        r3.A03 = this.A06;
        r3.A02 = this.A04;
        this.A00 = r3;
    }
}
