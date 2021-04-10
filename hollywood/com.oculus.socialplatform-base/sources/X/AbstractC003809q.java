package X;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import java.lang.reflect.Modifier;
import java.util.ArrayList;

/* renamed from: X.09q  reason: invalid class name and case insensitive filesystem */
public abstract class AbstractC003809q {
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
    public ArrayList<C003709p> A0A;
    public ArrayList<String> A0B;
    public ArrayList<String> A0C;
    public boolean A0D;
    public boolean A0E;
    public final AnonymousClass09S A0F;
    public final ClassLoader A0G;

    public abstract int A00();

    public abstract void A01();

    public void A02(int i, Fragment fragment, @Nullable String str, int i2) {
        Class<?> cls = fragment.getClass();
        int modifiers = cls.getModifiers();
        if (cls.isAnonymousClass() || !Modifier.isPublic(modifiers) || (cls.isMemberClass() && !Modifier.isStatic(modifiers))) {
            throw new IllegalStateException(AnonymousClass006.A09("Fragment ", cls.getCanonicalName(), " must be a public static class to be  properly recreated from instance state."));
        }
        if (str != null) {
            String str2 = fragment.A0N;
            if (str2 == null || str.equals(str2)) {
                fragment.A0N = str;
            } else {
                StringBuilder sb = new StringBuilder("Can't change tag of fragment ");
                sb.append(fragment);
                sb.append(": was ");
                sb.append(fragment.A0N);
                sb.append(" now ");
                sb.append(str);
                throw new IllegalStateException(sb.toString());
            }
        }
        if (i != 0) {
            if (i != -1) {
                int i3 = fragment.A04;
                if (i3 == 0 || i3 == i) {
                    fragment.A04 = i;
                    fragment.A03 = i;
                } else {
                    StringBuilder sb2 = new StringBuilder("Can't change container ID of fragment ");
                    sb2.append(fragment);
                    sb2.append(": was ");
                    sb2.append(fragment.A04);
                    sb2.append(" now ");
                    sb2.append(i);
                    throw new IllegalStateException(sb2.toString());
                }
            } else {
                StringBuilder sb3 = new StringBuilder("Can't add fragment ");
                sb3.append(fragment);
                sb3.append(" with tag ");
                sb3.append(str);
                sb3.append(" to container view with no id");
                throw new IllegalArgumentException(sb3.toString());
            }
        }
        C003709p r1 = new C003709p(i2, fragment);
        this.A0A.add(r1);
        r1.A01 = this.A02;
        r1.A02 = this.A03;
        r1.A03 = this.A04;
        r1.A04 = this.A05;
    }

    @Deprecated
    public AbstractC003809q() {
        this.A0A = new ArrayList<>();
        this.A0E = false;
        this.A0F = null;
        this.A0G = null;
    }

    public AbstractC003809q(@NonNull AnonymousClass09S r2, @Nullable ClassLoader classLoader) {
        this.A0A = new ArrayList<>();
        this.A0E = false;
        this.A0F = r2;
        this.A0G = classLoader;
    }
}
