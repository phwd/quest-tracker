package defpackage;

import java.io.IOException;
import java.nio.charset.Charset;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.Objects;

/* renamed from: h  reason: default package and case insensitive filesystem */
/* compiled from: chromium-OculusBrowser.apk-stable-281887347 */
public abstract class AbstractC2790h implements AbstractC1125Sj0 {

    /* renamed from: a  reason: collision with root package name */
    public int f10040a = 0;

    public static void a(Iterable iterable, List list) {
        Charset charset = F30.f7992a;
        Objects.requireNonNull(iterable);
        if (iterable instanceof R70) {
            List j = ((R70) iterable).j();
            R70 r70 = (R70) list;
            int size = list.size();
            for (Object obj : j) {
                if (obj == null) {
                    StringBuilder sb = new StringBuilder(37);
                    sb.append("Element at index ");
                    sb.append(r70.size() - size);
                    sb.append(" is null.");
                    String sb2 = sb.toString();
                    int size2 = r70.size();
                    while (true) {
                        size2--;
                        if (size2 >= size) {
                            r70.remove(size2);
                        } else {
                            throw new NullPointerException(sb2);
                        }
                    }
                } else if (obj instanceof AbstractC1248Uk) {
                    r70.k((AbstractC1248Uk) obj);
                } else {
                    r70.add((String) obj);
                }
            }
        } else if (iterable instanceof LF0) {
            list.addAll((Collection) iterable);
        } else {
            if ((list instanceof ArrayList) && (iterable instanceof Collection)) {
                ((ArrayList) list).ensureCapacity(((Collection) iterable).size() + list.size());
            }
            int size3 = list.size();
            for (Object obj2 : iterable) {
                if (obj2 == null) {
                    StringBuilder sb3 = new StringBuilder(37);
                    sb3.append("Element at index ");
                    sb3.append(list.size() - size3);
                    sb3.append(" is null.");
                    String sb4 = sb3.toString();
                    int size4 = list.size();
                    while (true) {
                        size4--;
                        if (size4 >= size3) {
                            list.remove(size4);
                        } else {
                            throw new NullPointerException(sb4);
                        }
                    }
                } else {
                    list.add(obj2);
                }
            }
        }
    }

    public int b(UO0 uo0) {
        AbstractC2360eV eVVar = (AbstractC2360eV) this;
        int i = eVVar.d;
        if (i != -1) {
            return i;
        }
        int g = uo0.g(this);
        eVVar.d = g;
        return g;
    }

    public byte[] c() {
        AbstractC2360eV eVVar = (AbstractC2360eV) this;
        try {
            int g = eVVar.g();
            byte[] bArr = new byte[g];
            boolean z = C6014zv.f11779a;
            C6014zv zvVar = new C6014zv(bArr, 0, g);
            eVVar.l(zvVar);
            if (zvVar.C() == 0) {
                return bArr;
            }
            throw new IllegalStateException("Did not write as much data as expected.");
        } catch (IOException e) {
            String name = getClass().getName();
            StringBuilder sb = new StringBuilder(10 + name.length() + 62);
            sb.append("Serializing ");
            sb.append(name);
            sb.append(" to a ");
            sb.append("byte array");
            sb.append(" threw an IOException (should never happen).");
            throw new RuntimeException(sb.toString(), e);
        }
    }
}
