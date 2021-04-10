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

/* renamed from: X.0Ae  reason: invalid class name */
public final class AnonymousClass0Ae {
    public static final Class[] A03 = {Boolean.TYPE, boolean[].class, Double.TYPE, double[].class, Integer.TYPE, int[].class, Long.TYPE, long[].class, String.class, String[].class, Binder.class, Bundle.class, Byte.TYPE, byte[].class, Character.TYPE, char[].class, CharSequence.class, CharSequence[].class, ArrayList.class, Float.TYPE, float[].class, Parcelable.class, Parcelable[].class, Serializable.class, Short.TYPE, short[].class, SparseArray.class, Size.class, SizeF.class};
    public final Map<String, Object> A00;
    public final AbstractC00650Bz A01;
    public final Map<String, SavedStateHandle.SavingStateLiveData<?>> A02;

    public AnonymousClass0Ae() {
        this.A02 = new HashMap();
        this.A01 = new C07270rl(this);
        this.A00 = new HashMap();
    }

    public AnonymousClass0Ae(@NonNull Map<String, Object> map) {
        this.A02 = new HashMap();
        this.A01 = new C07270rl(this);
        this.A00 = new HashMap(map);
    }
}
