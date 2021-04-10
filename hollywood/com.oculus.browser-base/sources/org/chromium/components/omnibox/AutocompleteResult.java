package org.chromium.components.omnibox;

import android.util.SparseArray;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Objects;

/* compiled from: chromium-OculusBrowser.apk-stable-281887347 */
public class AutocompleteResult {

    /* renamed from: a  reason: collision with root package name */
    public final List f10862a;
    public final SparseArray b;

    public AutocompleteResult(List list, SparseArray sparseArray) {
        this.f10862a = list == null ? new ArrayList() : list;
        this.b = sparseArray == null ? new SparseArray() : sparseArray;
    }

    public static AutocompleteResult build(AutocompleteMatch[] autocompleteMatchArr, int[] iArr, String[] strArr, boolean[] zArr) {
        SparseArray sparseArray = new SparseArray(iArr.length);
        for (int i = 0; i < iArr.length; i++) {
            sparseArray.put(iArr[i], new C2550fd(strArr[i], zArr[i]));
        }
        return new AutocompleteResult(Arrays.asList(autocompleteMatchArr), sparseArray);
    }

    public boolean equals(Object obj) {
        if (obj == this) {
            return true;
        }
        if (!(obj instanceof AutocompleteResult)) {
            return false;
        }
        AutocompleteResult autocompleteResult = (AutocompleteResult) obj;
        if (!this.f10862a.equals(autocompleteResult.f10862a)) {
            return false;
        }
        SparseArray sparseArray = autocompleteResult.b;
        if (this.b.size() != sparseArray.size()) {
            return false;
        }
        for (int i = 0; i < this.b.size(); i++) {
            if (!(this.b.keyAt(i) == sparseArray.keyAt(i) && Objects.equals(this.b.valueAt(i), sparseArray.valueAt(i)))) {
                return false;
            }
        }
        return true;
    }

    public int hashCode() {
        int i = 0;
        for (int i2 = 0; i2 < this.b.size(); i2++) {
            i = Integer.rotateLeft(((C2550fd) this.b.valueAt(i2)).hashCode() ^ (this.b.keyAt(i2) + i), 10);
        }
        return this.f10862a.hashCode() ^ i;
    }
}
