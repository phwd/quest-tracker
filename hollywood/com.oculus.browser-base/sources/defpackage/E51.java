package defpackage;

import J.N;
import android.os.StatFs;
import android.util.Pair;
import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.Executor;
import org.chromium.base.LocaleUtils;

/* renamed from: E51  reason: default package */
/* compiled from: chromium-OculusBrowser.apk-stable-281887347 */
public class E51 extends AbstractC5079uP implements AbstractC0183Da {

    /* renamed from: a  reason: collision with root package name */
    public C0244Ea f7934a;

    @Override // defpackage.AbstractC0183Da
    public final boolean a() {
        C0244Ea ea = this.f7934a;
        return ea != null && ea.f == 2;
    }

    @Override // defpackage.AbstractC0183Da
    public final void b(Runnable runnable) {
        if (this.f7934a == null) {
            C0244Ea ea = new C0244Ea(this, runnable);
            this.f7934a = ea;
            Executor executor = AbstractC2032cb.f9616a;
            ea.f();
            ((ExecutorC1463Ya) executor).execute(ea.e);
        }
    }

    @Override // defpackage.AbstractC5249vP, defpackage.AbstractC5079uP
    public Map c() {
        HashMap c = AbstractC0417Gv.c(Pair.create("CPU Architecture", N.MpdXuPgt()), Pair.create("Available Memory (MB)", Integer.toString(N.Mnh$RUKx())), Pair.create("Total Memory (MB)", Integer.toString(N.MEPW6xxL())), Pair.create("GPU Vendor", N.M3VLomue()), Pair.create("GPU Model", N.MZ6RjAhF()), Pair.create("UI Locale", LocaleUtils.getDefaultLocaleString()));
        Object obj = null;
        try {
            C0244Ea ea = this.f7934a;
            if (ea != null && ea.f == 2) {
                obj = this.f7934a.g();
            }
        } catch (InterruptedException | ExecutionException unused) {
        }
        StatFs statFs = (StatFs) obj;
        if (statFs != null) {
            long blockSizeLong = statFs.getBlockSizeLong();
            c.put("Available Storage (MB)", Long.toString((statFs.getAvailableBlocksLong() * blockSizeLong) / 1048576));
            c.put("Total Storage (MB)", Long.toString((statFs.getBlockCountLong() * blockSizeLong) / 1048576));
        }
        return c;
    }
}
