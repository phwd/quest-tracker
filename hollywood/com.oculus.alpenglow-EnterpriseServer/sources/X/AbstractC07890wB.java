package X;

import android.content.SharedPreferences;
import android.os.Bundle;
import javax.annotation.Nullable;

/* renamed from: X.0wB  reason: invalid class name and case insensitive filesystem */
public abstract class AbstractC07890wB<ValueType> {
    public static final AbstractC07890wB<Boolean> A00 = new C07910wD();
    public static final AbstractC07890wB<Integer> A01 = new C07930wF();
    public static final AbstractC07890wB<Long> A02 = new C07900wC();
    public static final AbstractC07890wB<String> A03 = new C07940wG();

    public final Class<?> A00() {
        if (this instanceof C07900wC) {
            return Long.class;
        }
        if (this instanceof C07910wD) {
            return Boolean.class;
        }
        if (!(this instanceof C07930wF)) {
            return String.class;
        }
        return Integer.class;
    }

    public final ValueType A01(SharedPreferences sharedPreferences, String str, @Nullable ValueType valuetype) {
        long longValue;
        boolean booleanValue;
        int intValue;
        if (this instanceof C07900wC) {
            ValueType valuetype2 = valuetype;
            if (valuetype2 == null) {
                longValue = Long.MAX_VALUE;
            } else {
                longValue = valuetype2.longValue();
            }
            return (ValueType) Long.valueOf(sharedPreferences.getLong(str, longValue));
        } else if (this instanceof C07910wD) {
            ValueType valuetype3 = valuetype;
            if (valuetype3 == null) {
                booleanValue = false;
            } else {
                booleanValue = valuetype3.booleanValue();
            }
            return (ValueType) Boolean.valueOf(sharedPreferences.getBoolean(str, booleanValue));
        } else if (!(this instanceof C07930wF)) {
            return (ValueType) sharedPreferences.getString(str, valuetype);
        } else {
            ValueType valuetype4 = valuetype;
            if (valuetype4 == null) {
                intValue = 0;
            } else {
                intValue = valuetype4.intValue();
            }
            return (ValueType) Integer.valueOf(sharedPreferences.getInt(str, intValue));
        }
    }

    public final ValueType A02(Bundle bundle, String str, @Nullable ValueType valuetype) {
        long longValue;
        boolean booleanValue;
        int intValue;
        if (this instanceof C07900wC) {
            ValueType valuetype2 = valuetype;
            if (valuetype2 == null) {
                longValue = Long.MAX_VALUE;
            } else {
                longValue = valuetype2.longValue();
            }
            return (ValueType) Long.valueOf(bundle.getLong(str, longValue));
        } else if (this instanceof C07910wD) {
            ValueType valuetype3 = valuetype;
            if (valuetype3 == null) {
                booleanValue = false;
            } else {
                booleanValue = valuetype3.booleanValue();
            }
            return (ValueType) Boolean.valueOf(bundle.getBoolean(str, booleanValue));
        } else if (!(this instanceof C07930wF)) {
            return (ValueType) bundle.getString(str, valuetype);
        } else {
            ValueType valuetype4 = valuetype;
            if (valuetype4 == null) {
                intValue = 0;
            } else {
                intValue = valuetype4.intValue();
            }
            return (ValueType) Integer.valueOf(bundle.getInt(str, intValue));
        }
    }

    public final void A03(SharedPreferences.Editor editor, String str, ValueType valuetype) {
        if (this instanceof C07900wC) {
            editor.putLong(str, valuetype.longValue());
        } else if (this instanceof C07910wD) {
            editor.putBoolean(str, valuetype.booleanValue());
        } else if (!(this instanceof C07930wF)) {
            editor.putString(str, valuetype);
        } else {
            editor.putInt(str, valuetype.intValue());
        }
    }

    public final void A04(Bundle bundle, String str, ValueType valuetype) {
        if (this instanceof C07900wC) {
            bundle.putLong(str, valuetype.longValue());
        } else if (this instanceof C07910wD) {
            bundle.putBoolean(str, valuetype.booleanValue());
        } else if (!(this instanceof C07930wF)) {
            bundle.putString(str, valuetype);
        } else {
            bundle.putInt(str, valuetype.intValue());
        }
    }
}
