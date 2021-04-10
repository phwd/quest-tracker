package X;

import android.annotation.SuppressLint;
import android.annotation.TargetApi;
import java.util.Map;
import java.util.Objects;
import java.util.Set;

@SuppressLint({"CatchGeneralException", "DeprecatedMethod", "TodoWithoutTask"})
/* renamed from: X.hS  reason: case insensitive filesystem */
public final class C0418hS {
    public final Map<C0409hD, Set<String>> A00;
    public final Set<C0409hD> A01;

    public final boolean equals(Object obj) {
        boolean z;
        boolean z2;
        if (obj == this) {
            return true;
        }
        if (!(obj instanceof C0418hS)) {
            return false;
        }
        C0418hS hSVar = (C0418hS) obj;
        Set<C0409hD> set = hSVar.A01;
        if (set != null) {
            z = set.equals(this.A01);
        } else {
            z = false;
            if (this.A01 == null) {
                z = true;
            }
        }
        Map<C0409hD, Set<String>> map = hSVar.A00;
        if (map != null) {
            z2 = map.equals(this.A00);
        } else {
            z2 = false;
            if (this.A00 == null) {
                z2 = true;
            }
        }
        return z && z2;
    }

    public static boolean A00(C0409hD hDVar) {
        if (C0407hB.A14.contains(hDVar) || C0407hB.A1O.contains(hDVar) || C0407hB.A1E.contains(hDVar) || C0407hB.A1J.contains(hDVar) || C0407hB.A17.contains(hDVar)) {
            return true;
        }
        return false;
    }

    @SuppressLint({"ObjectsUse"})
    @TargetApi(19)
    public final int hashCode() {
        return Objects.hash(this.A01, this.A00);
    }
}
