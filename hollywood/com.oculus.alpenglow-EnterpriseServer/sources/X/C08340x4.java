package X;

import android.content.Context;
import android.content.SharedPreferences;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.Iterator;
import java.util.Locale;
import java.util.Map;
import java.util.TimeZone;
import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.ScheduledFuture;
import java.util.concurrent.TimeUnit;
import javax.annotation.Nullable;

/* renamed from: X.0x4  reason: invalid class name and case insensitive filesystem */
public final class C08340x4 implements AbstractC09080yd {
    public int A00;
    @Nullable
    public C09580zh A01 = null;
    public ArrayList<String> A02 = new ArrayList<>();
    @Nullable
    public ScheduledFuture<?> A03 = null;
    public final Context A04;
    public final SharedPreferences A05;
    public final ScheduledExecutorService A06;
    public final SimpleDateFormat A07;

    public static void A00(C08340x4 r8, boolean z) {
        ArrayList<String> arrayList;
        ScheduledFuture<?> scheduledFuture;
        synchronized (r8) {
            arrayList = r8.A02;
            r8.A02 = new ArrayList<>();
            if (z && (scheduledFuture = r8.A03) != null) {
                scheduledFuture.cancel(false);
            }
            r8.A03 = null;
        }
        if (!arrayList.isEmpty()) {
            Context context = r8.A04;
            File file = new File(context.getCacheDir(), AnonymousClass006.A01("fbnslite_log", r8.A00));
            int i = 1;
            try {
                FileWriter fileWriter = new FileWriter(file, true);
                try {
                    Iterator<String> it = arrayList.iterator();
                    while (it.hasNext()) {
                        fileWriter.write(it.next() + '\n');
                    }
                    fileWriter.close();
                } catch (Throwable unused) {
                }
            } catch (IOException unused2) {
            }
            if (file.length() >= 30000) {
                if (r8.A00 != 0) {
                    i = 0;
                }
                r8.A00 = i;
                r8.A05.edit().putInt("CurrentFile", r8.A00).commit();
                new File(context.getCacheDir(), AnonymousClass006.A01("fbnslite_log", r8.A00)).delete();
                return;
            }
            return;
        }
        return;
        throw th;
    }

    @Override // X.AbstractC09080yd
    public final void A5g(String str, String str2) {
        log(AnonymousClass006.A08("[", str, "] ", str2));
    }

    @Override // X.AbstractC09080yd
    public final void A5h(String str, Map<String, String> map) {
        StringBuilder sb = new StringBuilder();
        for (Map.Entry<String, String> entry : map.entrySet()) {
            sb.append(entry.getKey());
            sb.append("=");
            sb.append(entry.getValue());
            sb.append("; ");
        }
        log(AnonymousClass006.A08("[", str, "] ", sb.toString()));
    }

    public C08340x4(Context context) {
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("MM-dd HH:mm:ss.SSS", Locale.US);
        this.A07 = simpleDateFormat;
        this.A04 = context;
        simpleDateFormat.setTimeZone(TimeZone.getTimeZone("America/Los_Angeles"));
        this.A05 = this.A04.getSharedPreferences("Fbnslite_Flytrap", 0);
        this.A06 = Executors.newSingleThreadScheduledExecutor();
        this.A00 = this.A05.getInt("CurrentFile", 0);
    }

    @Override // X.AbstractC09080yd
    public final void log(String str) {
        String A072 = AnonymousClass006.A07(this.A07.format(new Date(System.currentTimeMillis())), " ", str);
        synchronized (this) {
            if (A072.length() > 500) {
                A072 = A072.substring(0, 500);
            }
            this.A02.add(A072);
            if (this.A03 == null) {
                this.A03 = this.A06.schedule(new RunnableC09570zg(this), 60000, TimeUnit.MILLISECONDS);
            }
        }
    }

    @Override // X.AbstractC09080yd
    public final void A8B(C09580zh r1) {
        this.A01 = r1;
    }
}
