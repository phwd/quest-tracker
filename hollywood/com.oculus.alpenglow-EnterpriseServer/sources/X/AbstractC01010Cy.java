package X;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import java.lang.reflect.Modifier;
import java.util.ArrayList;

/* renamed from: X.0Cy  reason: invalid class name and case insensitive filesystem */
public abstract class AbstractC01010Cy {
    public int A00;
    public int A01;
    public int A02;
    public int A03;
    public int A04;
    public int A05;
    public int A06;
    public CharSequence A07;
    public CharSequence A08;
    @Nullable
    public String A09;
    public ArrayList<C01000Cx> A0A;
    public ArrayList<String> A0B;
    public ArrayList<String> A0C;
    public boolean A0D;
    public boolean A0E;
    public final C00940Ca A0F;
    public final ClassLoader A0G;

    public abstract void A00();

    public void A01(int i, AnonymousClass0MN r6, @Nullable String str, int i2) {
        String A072;
        StringBuilder sb;
        Class<?> cls = r6.getClass();
        int modifiers = cls.getModifiers();
        if (cls.isAnonymousClass() || !Modifier.isPublic(modifiers) || (cls.isMemberClass() && !Modifier.isStatic(modifiers))) {
            A072 = AnonymousClass006.A07("Fragment ", cls.getCanonicalName(), " must be a public static class to be  properly recreated from instance state.");
        } else {
            if (str != null) {
                String str2 = r6.A0N;
                if (str2 == null || str.equals(str2)) {
                    r6.A0N = str;
                } else {
                    sb = new StringBuilder("Can't change tag of fragment ");
                    sb.append(r6);
                    sb.append(": was ");
                    sb.append(r6.A0N);
                    sb.append(" now ");
                    sb.append(str);
                    A072 = sb.toString();
                }
            }
            if (i != 0) {
                if (i != -1) {
                    int i3 = r6.A04;
                    if (i3 == 0 || i3 == i) {
                        r6.A04 = i;
                        r6.A03 = i;
                    } else {
                        sb = new StringBuilder("Can't change container ID of fragment ");
                        sb.append(r6);
                        sb.append(": was ");
                        sb.append(r6.A04);
                        sb.append(" now ");
                        sb.append(i);
                        A072 = sb.toString();
                    }
                } else {
                    throw new IllegalArgumentException("Can't add fragment " + r6 + " with tag " + str + " to container view with no id");
                }
            }
            C01000Cx r1 = new C01000Cx(i2, r6);
            this.A0A.add(r1);
            r1.A01 = this.A02;
            r1.A02 = this.A03;
            r1.A03 = this.A04;
            r1.A04 = this.A05;
            return;
        }
        throw new IllegalStateException(A072);
    }

    @Deprecated
    public AbstractC01010Cy() {
        this.A0A = new ArrayList<>();
        this.A0E = false;
        this.A0F = null;
        this.A0G = null;
    }

    public AbstractC01010Cy(@NonNull C00940Ca r2, @Nullable ClassLoader classLoader) {
        this.A0A = new ArrayList<>();
        this.A0E = false;
        this.A0F = r2;
        this.A0G = classLoader;
    }
}
