package X;

import com.facebook.infer.annotation.Nullsafe;
import javax.annotation.Nullable;

@Nullsafe(Nullsafe.Mode.LOCAL)
/* renamed from: X.qo  reason: case insensitive filesystem */
public abstract class AbstractC0481qo {
    public static final int MAX_TTL_SECONDS = 86400;
    public final short id;
    public boolean mIsPseudoEndEnabled = false;
    public int mSecondsToEndSinceLastUpdate = 600;
    public boolean mShouldCreateNoopInstances = false;
    public boolean mShouldEndOnUserLeavingTheApp = true;
    public final String name;

    public final boolean equals(@Nullable Object obj) {
        if (obj == null || !(obj instanceof AbstractC0481qo)) {
            return false;
        }
        return ((AbstractC0481qo) obj).name.equals(this.name);
    }

    public final int hashCode() {
        return this.name.hashCode();
    }

    public AbstractC0481qo(String str) {
        this.name = str;
        this.id = (short) str.hashCode();
    }
}
