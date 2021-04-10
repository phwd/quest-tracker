package X;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import java.lang.reflect.Modifier;
import java.util.ArrayList;

/* renamed from: X.9p  reason: invalid class name and case insensitive filesystem */
public abstract class AbstractC00359p {
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
    public ArrayList<C00349o> A0A;
    public ArrayList<String> A0B;
    public ArrayList<String> A0C;
    public boolean A0D;
    public boolean A0E;
    public final AnonymousClass9R A0F;
    public final ClassLoader A0G;

    public abstract void A00();

    public void A01(int i, Fragment fragment, @Nullable String str, int i2) {
        String A052;
        StringBuilder sb;
        Class<?> cls = fragment.getClass();
        int modifiers = cls.getModifiers();
        if (cls.isAnonymousClass() || !Modifier.isPublic(modifiers) || (cls.isMemberClass() && !Modifier.isStatic(modifiers))) {
            A052 = AnonymousClass06.A05("Fragment ", cls.getCanonicalName(), " must be a public static class to be  properly recreated from instance state.");
        } else {
            if (str != null) {
                String str2 = fragment.A0N;
                if (str2 == null || str.equals(str2)) {
                    fragment.A0N = str;
                } else {
                    sb = new StringBuilder("Can't change tag of fragment ");
                    sb.append(fragment);
                    sb.append(": was ");
                    sb.append(fragment.A0N);
                    sb.append(" now ");
                    sb.append(str);
                    A052 = sb.toString();
                }
            }
            if (i != 0) {
                if (i != -1) {
                    int i3 = fragment.A04;
                    if (i3 == 0 || i3 == i) {
                        fragment.A04 = i;
                        fragment.A03 = i;
                    } else {
                        sb = new StringBuilder("Can't change container ID of fragment ");
                        sb.append(fragment);
                        sb.append(": was ");
                        sb.append(fragment.A04);
                        sb.append(" now ");
                        sb.append(i);
                        A052 = sb.toString();
                    }
                } else {
                    StringBuilder sb2 = new StringBuilder("Can't add fragment ");
                    sb2.append(fragment);
                    sb2.append(" with tag ");
                    sb2.append(str);
                    sb2.append(" to container view with no id");
                    throw new IllegalArgumentException(sb2.toString());
                }
            }
            C00349o r1 = new C00349o(i2, fragment);
            this.A0A.add(r1);
            r1.A01 = this.A02;
            r1.A02 = this.A03;
            r1.A03 = this.A04;
            r1.A04 = this.A05;
            return;
        }
        throw new IllegalStateException(A052);
    }

    @Deprecated
    public AbstractC00359p() {
        this.A0A = new ArrayList<>();
        this.A0E = false;
        this.A0F = null;
        this.A0G = null;
    }

    public AbstractC00359p(@NonNull AnonymousClass9R r2, @Nullable ClassLoader classLoader) {
        this.A0A = new ArrayList<>();
        this.A0E = false;
        this.A0F = r2;
        this.A0G = classLoader;
    }
}
