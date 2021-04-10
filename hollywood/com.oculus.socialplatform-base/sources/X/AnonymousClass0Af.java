package X;

import android.os.Binder;
import android.os.Bundle;
import android.os.Parcelable;
import android.util.Size;
import android.util.SizeF;
import android.util.SparseArray;
import androidx.annotation.NonNull;
import androidx.lifecycle.SavedStateHandle;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

/* renamed from: X.0Af  reason: invalid class name */
public final class AnonymousClass0Af {
    public static final Class[] A03;
    public final Map<String, Object> A00;
    public final AnonymousClass0C3 A01;
    public final Map<String, SavedStateHandle.SavingStateLiveData<?>> A02;

    static {
        Class[] clsArr = new Class[29];
        System.arraycopy(new Class[]{Boolean.TYPE, boolean[].class, Double.TYPE, double[].class, Integer.TYPE, int[].class, Long.TYPE, long[].class, String.class, String[].class, Binder.class, Bundle.class, Byte.TYPE, byte[].class, Character.TYPE, char[].class, CharSequence.class, CharSequence[].class, ArrayList.class, Float.TYPE, float[].class, Parcelable.class, Parcelable[].class, Serializable.class, Short.TYPE, short[].class, SparseArray.class}, 0, clsArr, 0, 27);
        System.arraycopy(new Class[]{Size.class, SizeF.class}, 0, clsArr, 27, 2);
        A03 = clsArr;
    }

    public AnonymousClass0Af() {
        this.A02 = new HashMap();
        this.A01 = new AnonymousClass0ut(this);
        this.A00 = new HashMap();
    }

    public AnonymousClass0Af(@NonNull Map<String, Object> map) {
        this.A02 = new HashMap();
        this.A01 = new AnonymousClass0ut(this);
        this.A00 = new HashMap(map);
    }
}
