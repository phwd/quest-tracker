package X;

import android.content.Context;
import android.content.SharedPreferences;
import android.os.Handler;
import android.text.TextUtils;
import com.oculus.util.constants.OculusConstants;
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
/* renamed from: X.0wL  reason: invalid class name and case insensitive filesystem */
public final class C07980wL {
    public static final AnonymousClass0wM A0F = AnonymousClass0wM.A00();
    public C07960wJ A00;
    public final SharedPreferences A01;
    public final Handler A02;
    public final C08060wV A03;
    public final AbstractC09610zk<String> A04;
    public final Runnable A05;
    public final String A06;
    public final String A07;
    public final String A08;
    public final Queue<Runnable> A09 = new ConcurrentLinkedQueue();
    public final AtomicBoolean A0A = new AtomicBoolean(false);
    public final Context A0B;
    public final C08050wU A0C;
    public final AnonymousClass0vA A0D;
    public final String A0E;

    public static void A00(C07980wL r8) {
        String str;
        IOException e;
        if (!r8.A00.A07.isEmpty()) {
            C08050wU r0 = r8.A0C;
            C07960wJ r7 = r8.A00;
            str = "failed to close writer";
            File file = r0.A00;
            if (!file.exists() && !file.mkdir()) {
                AnonymousClass0NK.A01("AnalyticsStorage", "Unable to open analytics storage.");
            }
            UUID uuid = r7.A08;
            if (uuid == null) {
                uuid = UUID.randomUUID();
                r7.A08 = uuid;
            }
            File file2 = new File(file, String.format(null, "%s_%d.batch", uuid.toString(), Integer.valueOf(r7.A00)));
            if (file2.exists() && !file2.delete()) {
                AnonymousClass0NK.A07("AnalyticsStorage", "File %s was not deleted", file2);
            }
            r7.A01 = System.currentTimeMillis();
            try {
                FileOutputStream fileOutputStream = new FileOutputStream(file2);
                try {
                    OutputStreamWriter outputStreamWriter = new OutputStreamWriter(fileOutputStream, "UTF8");
                    try {
                        outputStreamWriter.write(r7.toString());
                    } catch (IOException e2) {
                        AnonymousClass0NK.A0A("AnalyticsStorage", e2, "failed to write session to file");
                    } catch (Throwable th) {
                        try {
                            outputStreamWriter.close();
                            throw th;
                        } catch (IOException e3) {
                            AnonymousClass0NK.A0A("AnalyticsStorage", e3, str);
                            throw th;
                        }
                    }
                    try {
                        outputStreamWriter.close();
                    } catch (IOException e4) {
                        e = e4;
                    }
                } catch (UnsupportedEncodingException e5) {
                    AnonymousClass0NK.A0A("AnalyticsStorage", e5, "UTF8 encoding is not supported");
                    try {
                        fileOutputStream.close();
                    } catch (IOException e6) {
                        e = e6;
                        str = "failed to close output stream";
                    }
                }
            } catch (FileNotFoundException e7) {
                AnonymousClass0NK.A0D("AnalyticsStorage", e7, "Batch file creation failed %s", file2);
            }
            C07960wJ r1 = r8.A00;
            r1.A07.clear();
            r1.A00++;
        }
        return;
        AnonymousClass0NK.A0A("AnalyticsStorage", e, str);
        C07960wJ r12 = r8.A00;
        r12.A07.clear();
        r12.A00++;
    }

    public static void A01(C07980wL r3, Runnable runnable) {
        r3.A09.add(runnable);
        if (r3.A0A.compareAndSet(false, true)) {
            A0F.execute(r3.A05);
        }
    }

    public final void A02(C07840w6 r4) {
        AnonymousClass0vA r1 = this.A0D;
        if (r1.A02 || r1.A01.getBoolean(EnumC07870w9.ANALYTIC_IS_EMPLOYEE.getPrefKey(), false)) {
            A01(this, new RunnableC07970wK(this, r4));
        }
    }

    /* JADX WARN: Incorrect args count in method signature: (Landroid/content/Context;Ljava/lang/String;LX/0zk<Ljava/lang/String;>;Lcom/facebook/rti/common/analytics/defaultlogger/AnalyticsSamplePolicy;Landroid/content/SharedPreferences;LX/0zk<Ljava/lang/String;>;Ljava/lang/String;Ljava/lang/String;Ljava/lang/String;Ljava/lang/String;Ljava/lang/String;Ljava/lang/String;)V */
    public C07980wL(Context context, String str, AbstractC09610zk r7, AnonymousClass0vA r8, SharedPreferences sharedPreferences, AbstractC09610zk r10, String str2, String str3, String str4) {
        this.A0B = context;
        this.A0E = str;
        this.A01 = sharedPreferences;
        this.A04 = r10;
        this.A08 = str4;
        this.A07 = str3;
        this.A06 = "567310203415052";
        this.A02 = new HandlerC07990wN(this, context.getMainLooper());
        this.A05 = new RunnableC08020wR(this);
        this.A0C = new C08050wU(context.getApplicationContext(), this.A0E);
        this.A03 = new C08060wV(context.getApplicationContext(), this.A0E, r7, str2);
        this.A0D = r8;
        if (this.A00 != null) {
            A00(this);
        }
        C07960wJ r3 = new C07960wJ();
        r3.A04 = this.A07;
        r3.A05 = this.A08;
        String string = this.A01.getString("fb_uid", "");
        r3.A06 = TextUtils.isEmpty(string) ? OculusConstants.DEFAULT_ENTERPRISE_USER_ID : string;
        r3.A03 = this.A06;
        r3.A02 = this.A04;
        this.A00 = r3;
    }
}
