package X;

import android.os.Build;
import android.os.Process;
import com.facebook.systrace.Systrace;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public final class L6 {
    public static final File A03 = new File("/sys/kernel/debug/tracing/trace");
    public boolean A00;
    public final Object A01 = new Object[0];
    public final List A02 = new ArrayList();

    public final void A00() {
        synchronized (this.A01) {
            Systrace.A01(1, "Run Trace Listeners");
            try {
                this.A00 = true;
                int i = 0;
                while (true) {
                    List list = this.A02;
                    if (i < list.size()) {
                        list.get(i);
                        if ((64 & C0225Kz.A01) != 0) {
                            Systrace.A02("thread_name", Thread.currentThread().getName(), Process.myTid());
                        }
                        if ((1 & C0225Kz.A01) != 0) {
                            StringBuilder sb = new StringBuilder(127);
                            sb.append("Android trace tags: ");
                            sb.append(AnonymousClass89.A00("debug.atrace.tags.enableflags"));
                            sb.append(", Facebook trace tags: ");
                            sb.append(C0225Kz.A01);
                            Systrace.A02("process_labels", sb.toString(), 0);
                        }
                        if ((64 & C0225Kz.A01) != 0) {
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
                                        String A022 = AnonymousClass89.A02("dalvik.vm.heapgrowthlimit");
                                        String A023 = AnonymousClass89.A02("dalvik.vm.heapmaxfree");
                                        String A024 = AnonymousClass89.A02("dalvik.vm.heapminfree");
                                        Systrace.A02("process_labels", String.format("device=%s,heapgrowthlimit=%s,heapstartsize=%s,heapminfree=%s,heapmaxfree=%s,heaptargetutilization=%s", Build.MODEL, A022, AnonymousClass89.A02("dalvik.vm.heapstartsize"), A023, A024, AnonymousClass89.A02("dalvik.vm.heaptargetutilization")), 0);
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
