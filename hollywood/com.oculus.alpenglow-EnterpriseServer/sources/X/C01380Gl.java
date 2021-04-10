package X;

import com.facebook.graphservice.tree.TreeJNI;
import com.facebook.infer.annotation.Nullsafe;
import javax.annotation.Nullable;

@Nullsafe(Nullsafe.Mode.LOCAL)
/* renamed from: X.0Gl  reason: invalid class name and case insensitive filesystem */
public class C01380Gl extends TreeJNI {
    public static final Object NULL = new Object();
    public static final int OBJECT_TYPE_HASH_CODE = -2073950043;
    public static final boolean sCachingEnabled = true;
    @Nullable
    public final Object[] mFieldCache;

    @Override // com.facebook.graphservice.tree.TreeJNI
    public final int getFieldCacheIndex(int i) {
        Object[] objArr = this.mFieldCache;
        if (objArr == null || i != -2073950043) {
            return super.getFieldCacheIndex(i);
        }
        return objArr.length - 1;
    }

    @Override // com.facebook.graphservice.tree.TreeJNI
    @Nullable
    public final String getTypeName() {
        int fieldCacheIndex;
        if (this.mFieldCache == null || (fieldCacheIndex = getFieldCacheIndex(OBJECT_TYPE_HASH_CODE)) < 0) {
            return super.getTypeName();
        }
        Object obj = this.mFieldCache[fieldCacheIndex];
        if (obj == null) {
            obj = super.getTypeName();
            this.mFieldCache[fieldCacheIndex] = obj;
        }
        if (obj != NULL) {
            return (String) obj;
        }
        return null;
    }

    public C01380Gl(int i, @Nullable int[] iArr) {
        super(i, iArr);
        Object[] objArr;
        int[] iArr2 = this.mSetFields;
        if (iArr2 != null) {
            objArr = new Object[(iArr2.length + 1)];
        } else {
            objArr = null;
        }
        this.mFieldCache = objArr;
    }
}
