package com.oculus.os;

import android.os.Bundle;
import android.util.SparseArray;
import java.util.Optional;

public final class Quirk<T> {
    public static final Quirk<Boolean> AlwaysDrawViewRoot;
    private static final Loader<Boolean> BooleanLoader = new Loader<Boolean>() {
        /* class com.oculus.os.Quirk.AnonymousClass1 */

        /* access modifiers changed from: package-private */
        @Override // com.oculus.os.Quirk.Loader
        public Boolean get(Bundle b, String key) {
            return Boolean.valueOf(b.getBoolean(key));
        }

        /* access modifiers changed from: package-private */
        public void set(Bundle b, String key, Boolean val) {
            b.putBoolean(key, val.booleanValue());
        }
    };
    public static final Quirk<Boolean> FirefoxDisableHWUIAcceleration;
    public static final Quirk<Boolean> GrantClearText = new Quirk<>("GrantClearText", 1000, BooleanLoader);
    public static final Quirk<Boolean> GrantFullConnectionInfoAccess;
    private static final Loader<Long> LongLoader = new Loader<Long>() {
        /* class com.oculus.os.Quirk.AnonymousClass2 */

        /* access modifiers changed from: package-private */
        @Override // com.oculus.os.Quirk.Loader
        public Long get(Bundle b, String key) {
            return Long.valueOf(b.getLong(key));
        }

        /* access modifiers changed from: package-private */
        public void set(Bundle b, String key, Long val) {
            b.putLong(key, val.longValue());
        }
    };
    public static final Quirk<String> OverrideBuildModel;
    private static final SparseArray<Quirk<?>> QUIRK_ARRAY = new SparseArray<>();
    public static final Quirk<Long> ReducedDalvikVmSize;
    private static final Loader<String> StringLoader = new Loader<String>() {
        /* class com.oculus.os.Quirk.AnonymousClass3 */

        /* access modifiers changed from: package-private */
        @Override // com.oculus.os.Quirk.Loader
        public String get(Bundle b, String key) {
            return b.getString(key);
        }

        /* access modifiers changed from: package-private */
        public void set(Bundle b, String key, String val) {
            b.putString(key, val);
        }
    };
    private final int mId;
    private final Loader<T> mLoader;
    private final String mName;

    public String getName() {
        return this.mName;
    }

    /* access modifiers changed from: package-private */
    public int getId() {
        return this.mId;
    }

    public Bundle addToBundle(Bundle b, Object val) {
        this.mLoader.set(b, this.mName, val);
        return b;
    }

    public Optional<T> getFromBundle(Bundle b) {
        return b.containsKey(this.mName) ? Optional.of(this.mLoader.get(b, this.mName)) : Optional.empty();
    }

    public static Quirk<?> lookup(int id) {
        return QUIRK_ARRAY.get(id);
    }

    private Quirk(String name, int id, Loader<T> loader) {
        this.mName = name;
        this.mId = id;
        this.mLoader = loader;
    }

    static {
        int id = 1000 + 1;
        int id2 = id + 1;
        ReducedDalvikVmSize = new Quirk<>("ReducedDalvikVmSize", id, LongLoader);
        int id3 = id2 + 1;
        OverrideBuildModel = new Quirk<>("OverrideBuildModel", id2, StringLoader);
        int id4 = id3 + 1;
        GrantFullConnectionInfoAccess = new Quirk<>("GrantFullConnectionInfoAccess", id3, BooleanLoader);
        int id5 = id4 + 1;
        FirefoxDisableHWUIAcceleration = new Quirk<>("FirefoxDisableHWUIAcceleration", id4, BooleanLoader);
        int i = id5 + 1;
        AlwaysDrawViewRoot = new Quirk<>("AlwaysDrawViewRoot", id5, BooleanLoader);
        QUIRK_ARRAY.append(GrantClearText.getId(), GrantClearText);
        QUIRK_ARRAY.append(ReducedDalvikVmSize.getId(), ReducedDalvikVmSize);
        QUIRK_ARRAY.append(OverrideBuildModel.getId(), OverrideBuildModel);
        QUIRK_ARRAY.append(GrantFullConnectionInfoAccess.getId(), GrantFullConnectionInfoAccess);
        QUIRK_ARRAY.append(FirefoxDisableHWUIAcceleration.getId(), FirefoxDisableHWUIAcceleration);
        QUIRK_ARRAY.append(AlwaysDrawViewRoot.getId(), AlwaysDrawViewRoot);
    }

    private static abstract class Loader<T> {
        /* access modifiers changed from: package-private */
        public abstract T get(Bundle bundle, String str);

        /* access modifiers changed from: package-private */
        public abstract void set(Bundle bundle, String str, T t);

        private Loader() {
        }
    }
}
