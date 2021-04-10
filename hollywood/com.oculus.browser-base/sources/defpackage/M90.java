package defpackage;

import java.io.FileDescriptor;
import java.io.PrintWriter;

/* renamed from: M90  reason: default package */
/* compiled from: chromium-OculusBrowser.apk-stable-281887347 */
public class M90 extends J90 {

    /* renamed from: a  reason: collision with root package name */
    public final AbstractC4524r80 f8462a;
    public final L90 b;

    public M90(AbstractC4524r80 r80, C4653ru1 ru1) {
        this.f8462a = r80;
        String canonicalName = L90.class.getCanonicalName();
        if (canonicalName != null) {
            String f = AbstractC2531fV.f("androidx.lifecycle.ViewModelProvider.DefaultKey:", canonicalName);
            Object obj = (AbstractC4312pu1) ru1.f11233a.get(f);
            if (!L90.class.isInstance(obj)) {
                obj = new L90();
                AbstractC4312pu1 pu1 = (AbstractC4312pu1) ru1.f11233a.put(f, obj);
                if (pu1 != null) {
                    pu1.a();
                }
            }
            this.b = (L90) obj;
            return;
        }
        throw new IllegalArgumentException("Local and anonymous classes can not be ViewModels");
    }

    @Override // defpackage.J90
    @Deprecated
    public void a(String str, FileDescriptor fileDescriptor, PrintWriter printWriter, String[] strArr) {
        L90 l90 = this.b;
        if (l90.c.i() > 0) {
            printWriter.print(str);
            printWriter.println("Loaders:");
            if (l90.c.i() > 0) {
                C5859z.a(l90.c.j(0));
                printWriter.print(str);
                printWriter.print("  #");
                printWriter.print(l90.c.f(0));
                printWriter.print(": ");
                throw null;
            }
        }
    }

    public String toString() {
        StringBuilder sb = new StringBuilder(128);
        sb.append("LoaderManager{");
        sb.append(Integer.toHexString(System.identityHashCode(this)));
        sb.append(" in ");
        sb.append(this.f8462a.getClass().getSimpleName());
        sb.append("{");
        sb.append(Integer.toHexString(System.identityHashCode(this.f8462a)));
        sb.append("}}");
        return sb.toString();
    }
}
