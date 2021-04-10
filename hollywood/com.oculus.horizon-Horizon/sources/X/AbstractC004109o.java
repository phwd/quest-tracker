package X;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import java.lang.reflect.Modifier;
import java.util.ArrayList;

/* renamed from: X.09o  reason: invalid class name and case insensitive filesystem */
public abstract class AbstractC004109o {
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
    public ArrayList<C004009n> A0A;
    public ArrayList<String> A0B;
    public ArrayList<String> A0C;
    public boolean A0D;
    public boolean A0E;
    public final AnonymousClass09Q A0F;
    public final ClassLoader A0G;

    public abstract int A03();

    public abstract int A04();

    @NonNull
    public AbstractC004109o A05(@NonNull Fragment fragment) {
        A02(new C004009n(3, fragment));
        return this;
    }

    public abstract void A06();

    public abstract void A07();

    public final void A02(C004009n r2) {
        this.A0A.add(r2);
        r2.A01 = this.A02;
        r2.A02 = this.A03;
        r2.A03 = this.A04;
        r2.A04 = this.A05;
    }

    public void A08(int i, Fragment fragment, @Nullable String str, int i2) {
        String A072;
        StringBuilder sb;
        Class<?> cls = fragment.getClass();
        int modifiers = cls.getModifiers();
        if (cls.isAnonymousClass() || !Modifier.isPublic(modifiers) || (cls.isMemberClass() && !Modifier.isStatic(modifiers))) {
            A072 = AnonymousClass006.A07("Fragment ", cls.getCanonicalName(), " must be a public static class to be  properly recreated from instance state.");
        } else {
            if (str != null) {
                String str2 = fragment.mTag;
                if (str2 == null || str.equals(str2)) {
                    fragment.mTag = str;
                } else {
                    sb = new StringBuilder("Can't change tag of fragment ");
                    sb.append(fragment);
                    sb.append(": was ");
                    sb.append(fragment.mTag);
                    sb.append(" now ");
                    sb.append(str);
                    A072 = sb.toString();
                }
            }
            if (i != 0) {
                if (i != -1) {
                    int i3 = fragment.mFragmentId;
                    if (i3 == 0 || i3 == i) {
                        fragment.mFragmentId = i;
                        fragment.mContainerId = i;
                    } else {
                        sb = new StringBuilder("Can't change container ID of fragment ");
                        sb.append(fragment);
                        sb.append(": was ");
                        sb.append(fragment.mFragmentId);
                        sb.append(" now ");
                        sb.append(i);
                        A072 = sb.toString();
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
            A02(new C004009n(i2, fragment));
            return;
        }
        throw new IllegalStateException(A072);
    }

    @Deprecated
    public AbstractC004109o() {
        this.A0A = new ArrayList<>();
        this.A0E = false;
        this.A0F = null;
        this.A0G = null;
    }

    public AbstractC004109o(@NonNull AnonymousClass09Q r2, @Nullable ClassLoader classLoader) {
        this.A0A = new ArrayList<>();
        this.A0E = false;
        this.A0F = r2;
        this.A0G = classLoader;
    }
}
