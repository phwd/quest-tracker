package X;

import android.annotation.SuppressLint;
import android.os.Build;
import android.os.Process;
import com.facebook.infer.annotation.Nullsafe;
import com.facebook.systrace.Systrace;
import com.facebook.systrace.TraceListener;
import com.squareup.okhttp.internal.framed.Hpack;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import javax.annotation.concurrent.GuardedBy;

@Nullsafe(Nullsafe.Mode.LOCAL)
public final class PF {
    public static final File A03 = new File("/sys/kernel/debug/tracing/trace");
    public boolean A00;
    public final Object A01 = new Object[0];
    @GuardedBy("mLock")
    @SuppressLint({"BadMethodUse-java.util.ArrayList._Constructor"})
    public final List<TraceListener> A02 = new ArrayList();

    public final void A00() {
        long j;
        Number number;
        synchronized (this.A01) {
            Systrace.A01(1, "Run Trace Listeners");
            try {
                this.A00 = true;
                int i = 0;
                while (true) {
                    List<TraceListener> list = this.A02;
                    if (i < list.size()) {
                        list.get(i);
                        if ((64 & PM.A01) != 0) {
                            Systrace.A02("thread_name", Thread.currentThread().getName(), Process.myTid());
                        }
                        if ((1 & PM.A01) != 0) {
                            StringBuilder sb = new StringBuilder((int) Hpack.PREFIX_7_BITS);
                            sb.append("Android trace tags: ");
                            if (!Hw.A05 || (number = (Number) Hw.A00(Hw.A01, "debug.atrace.tags.enableflags", 0L)) == null) {
                                j = 0;
                            } else {
                                j = number.longValue();
                            }
                            sb.append(j);
                            sb.append(", Facebook trace tags: ");
                            sb.append(PM.A01);
                            Systrace.A02("process_labels", sb.toString(), 0);
                        }
                        if ((64 & PM.A01) != 0) {
                            try {
                                FileReader fileReader = new FileReader("/proc/self/cmdline");
                                try {
                                    BufferedReader bufferedReader = new BufferedReader(fileReader);
                                    try {
                                        String readLine = bufferedReader.readLine();
                                        int indexOf = readLine.indexOf(0);
                                        if (indexOf >= 0) {
                                            readLine = readLine.substring(0, indexOf);
                                        }
                                        bufferedReader.close();
                                        fileReader.close();
                                        Systrace.A02("process_name", readLine, 0);
                                        Systrace.A02("process_labels", String.format("device=%s,heapgrowthlimit=%s,heapstartsize=%s,heapminfree=%s,heapmaxfree=%s,heaptargetutilization=%s", Build.MODEL, Hw.A01("dalvik.vm.heapgrowthlimit"), Hw.A01("dalvik.vm.heapstartsize"), Hw.A01("dalvik.vm.heapmaxfree"), Hw.A01("dalvik.vm.heapminfree"), Hw.A01("dalvik.vm.heaptargetutilization")), 0);
                                    } catch (Throwable unused) {
                                    }
                                } catch (Throwable unused2) {
                                }
                            } catch (IOException e) {
                                throw new RuntimeException(e);
                            }
                        }
                        i++;
                    }
                }
            } finally {
                Systrace.A00(1);
            }
        }
        return;
        throw th;
        throw th;
    }
}