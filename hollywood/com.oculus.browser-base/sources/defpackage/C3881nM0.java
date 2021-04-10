package defpackage;

import java.io.File;
import org.chromium.base.PathUtils;
import org.chromium.ui.base.ResourceBundle;

/* renamed from: nM0  reason: default package and case insensitive filesystem */
/* compiled from: chromium-OculusBrowser.apk-stable-281887347 */
public class C3881nM0 {

    /* renamed from: a  reason: collision with root package name */
    public static C3881nM0 f10486a;
    public RunnableC3710mM0 b;
    public C3070if1 c;

    public static C3881nM0 b() {
        if (f10486a == null) {
            f10486a = new C3881nM0();
        }
        return f10486a;
    }

    public static boolean e() {
        return ResourceBundle.f11019a.length == 0;
    }

    public final void a(String[] strArr) {
        File file = new File(c(), "icudtl.dat");
        WT wt = AbstractC3375kQ.f10275a;
        AbstractC3375kQ.e(file, wt);
        AbstractC3375kQ.e(new File(c(), "snapshot_blob.bin"), wt);
        if (strArr != null) {
            for (String str : strArr) {
                AbstractC3375kQ.e(new File(d(), str), AbstractC3375kQ.f10275a);
            }
        }
    }

    public final File c() {
        return new File(PathUtils.getDataDirectory());
    }

    public final File d() {
        return new File(c(), "paks");
    }
}
