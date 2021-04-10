package X;

import android.util.Log;
import android.view.View;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.annotation.VisibleForTesting;
import androidx.recyclerview.widget.RecyclerView;
import java.lang.ref.WeakReference;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

/* renamed from: X.1Ah  reason: invalid class name */
public abstract class AnonymousClass1Ah {
    public static final int FLAG_ADAPTER_FULLUPDATE = 1024;
    public static final int FLAG_ADAPTER_POSITION_UNKNOWN = 512;
    public static final int FLAG_APPEARED_IN_PRE_LAYOUT = 4096;
    public static final int FLAG_BOUNCED_FROM_HIDDEN_LIST = 8192;
    public static final int FLAG_BOUND = 1;
    public static final int FLAG_IGNORE = 128;
    public static final int FLAG_INVALID = 4;
    public static final int FLAG_MOVED = 2048;
    public static final int FLAG_NOT_RECYCLABLE = 16;
    public static final int FLAG_REMOVED = 8;
    public static final int FLAG_RETURNED_FROM_SCRAP = 32;
    public static final int FLAG_TMP_DETACHED = 256;
    public static final int FLAG_UPDATE = 2;
    public static final List<Object> FULLUPDATE_PAYLOADS = Collections.emptyList();
    public static final int PENDING_ACCESSIBILITY_STATE_NOT_SET = -1;
    @NonNull
    public final View itemView;
    public AnonymousClass1Aj<? extends AnonymousClass1Ah> mBindingAdapter;
    public int mFlags;
    public boolean mInChangeScrap = false;
    public int mIsRecyclableCount = 0;
    public long mItemId = -1;
    public int mItemViewType = -1;
    public WeakReference<RecyclerView> mNestedRecyclerView;
    public int mOldPosition = -1;
    public RecyclerView mOwnerRecyclerView;
    public List<Object> mPayloads = null;
    @VisibleForTesting
    public int mPendingAccessibilityState = -1;
    public int mPosition = -1;
    public int mPreLayoutPosition = -1;
    public AnonymousClass1Af mScrapContainer = null;
    public AnonymousClass1Ah mShadowedHolder = null;
    public AnonymousClass1Ah mShadowingHolder = null;
    public List<Object> mUnmodifiedPayloads = null;
    public int mWasImportantForAccessibilityBeforeHidden = 0;

    public void clearOldPosition() {
        this.mOldPosition = -1;
        this.mPreLayoutPosition = -1;
    }

    public void resetInternal() {
        this.mFlags = 0;
        this.mPosition = -1;
        this.mOldPosition = -1;
        this.mItemId = -1;
        this.mPreLayoutPosition = -1;
        this.mIsRecyclableCount = 0;
        this.mShadowedHolder = null;
        this.mShadowingHolder = null;
        clearPayload();
        this.mWasImportantForAccessibilityBeforeHidden = 0;
        this.mPendingAccessibilityState = -1;
        RecyclerView.clearNestedRecyclerViewIfNotNested(this);
    }

    public final void setIsRecyclable(boolean z) {
        int i;
        int i2;
        int i3 = this.mIsRecyclableCount;
        if (z) {
            i = i3 - 1;
        } else {
            i = i3 + 1;
        }
        this.mIsRecyclableCount = i;
        if (i < 0) {
            this.mIsRecyclableCount = 0;
            StringBuilder sb = new StringBuilder("isRecyclable decremented below 0: unmatched pair of setIsRecyable() calls for ");
            sb.append(this);
            Log.e("View", sb.toString());
            return;
        }
        if (!z) {
            if (i == 1) {
                i2 = this.mFlags | 16;
            } else {
                return;
            }
        } else if (i == 0) {
            i2 = this.mFlags & -17;
        } else {
            return;
        }
        this.mFlags = i2;
    }

    private void createPayloadsIfNeeded() {
        if (this.mPayloads == null) {
            ArrayList arrayList = new ArrayList();
            this.mPayloads = arrayList;
            this.mUnmodifiedPayloads = Collections.unmodifiableList(arrayList);
        }
    }

    public void addChangePayload(Object obj) {
        if (obj == null) {
            addFlags(1024);
        } else if ((1024 & this.mFlags) == 0) {
            createPayloadsIfNeeded();
            this.mPayloads.add(obj);
        }
    }

    public void addFlags(int i) {
        this.mFlags = i | this.mFlags;
    }

    public void clearPayload() {
        List<Object> list = this.mPayloads;
        if (list != null) {
            list.clear();
        }
        this.mFlags &= -1025;
    }

    public void clearReturnedFromScrapFlag() {
        this.mFlags &= -33;
    }

    public void clearTmpDetachFlag() {
        this.mFlags &= -257;
    }

    public boolean doesTransientStatePreventRecycling() {
        if ((this.mFlags & 16) != 0 || !this.itemView.hasTransientState()) {
            return false;
        }
        return true;
    }

    public void flagRemovedAndOffsetPosition(int i, int i2, boolean z) {
        addFlags(8);
        offsetPosition(i2, z);
        this.mPosition = i;
    }

    public final int getAbsoluteAdapterPosition() {
        RecyclerView recyclerView = this.mOwnerRecyclerView;
        if (recyclerView == null) {
            return -1;
        }
        return recyclerView.getAdapterPositionInRecyclerView(this);
    }

    @Nullable
    public final AnonymousClass1Aj<? extends AnonymousClass1Ah> getBindingAdapter() {
        return this.mBindingAdapter;
    }

    public final int getBindingAdapterPosition() {
        RecyclerView recyclerView;
        AnonymousClass1Aj<? extends AnonymousClass1Ah> r2;
        int adapterPositionInRecyclerView;
        AnonymousClass1Aj<? extends AnonymousClass1Ah> r4 = this.mBindingAdapter;
        if (r4 == null || (recyclerView = this.mOwnerRecyclerView) == null || (r2 = recyclerView.mAdapter) == null || (adapterPositionInRecyclerView = recyclerView.getAdapterPositionInRecyclerView(this)) == -1 || r4 != r2) {
            return -1;
        }
        return adapterPositionInRecyclerView;
    }

    public final long getItemId() {
        return this.mItemId;
    }

    public final int getItemViewType() {
        return this.mItemViewType;
    }

    public final int getLayoutPosition() {
        int i = this.mPreLayoutPosition;
        if (i == -1) {
            return this.mPosition;
        }
        return i;
    }

    public final int getOldPosition() {
        return this.mOldPosition;
    }

    @Deprecated
    public final int getPosition() {
        int i = this.mPreLayoutPosition;
        if (i == -1) {
            return this.mPosition;
        }
        return i;
    }

    public List<Object> getUnmodifiedPayloads() {
        List<Object> list;
        if ((this.mFlags & 1024) != 0 || (list = this.mPayloads) == null || list.size() == 0) {
            return FULLUPDATE_PAYLOADS;
        }
        return this.mUnmodifiedPayloads;
    }

    public boolean hasAnyOfTheFlags(int i) {
        if ((i & this.mFlags) != 0) {
            return true;
        }
        return false;
    }

    public boolean isAdapterPositionUnknown() {
        if ((this.mFlags & 512) != 0 || isInvalid()) {
            return true;
        }
        return false;
    }

    public boolean isAttachedToTransitionOverlay() {
        if (this.itemView.getParent() == null || this.itemView.getParent() == this.mOwnerRecyclerView) {
            return false;
        }
        return true;
    }

    public boolean isBound() {
        if ((this.mFlags & 1) == 0) {
            return false;
        }
        return true;
    }

    public boolean isInvalid() {
        if ((this.mFlags & 4) != 0) {
            return true;
        }
        return false;
    }

    public final boolean isRecyclable() {
        if ((this.mFlags & 16) != 0 || this.itemView.hasTransientState()) {
            return false;
        }
        return true;
    }

    public boolean isRemoved() {
        if ((this.mFlags & 8) != 0) {
            return true;
        }
        return false;
    }

    public boolean isScrap() {
        if (this.mScrapContainer != null) {
            return true;
        }
        return false;
    }

    public boolean isTmpDetached() {
        if ((this.mFlags & 256) != 0) {
            return true;
        }
        return false;
    }

    public boolean isUpdated() {
        if ((this.mFlags & 2) != 0) {
            return true;
        }
        return false;
    }

    public boolean needsUpdate() {
        if ((this.mFlags & 2) != 0) {
            return true;
        }
        return false;
    }

    public void offsetPosition(int i, boolean z) {
        if (this.mOldPosition == -1) {
            this.mOldPosition = this.mPosition;
        }
        int i2 = this.mPreLayoutPosition;
        if (i2 == -1) {
            i2 = this.mPosition;
            this.mPreLayoutPosition = i2;
        }
        if (z) {
            this.mPreLayoutPosition = i2 + i;
        }
        this.mPosition += i;
        if (this.itemView.getLayoutParams() != null) {
            ((C05831Bi) this.itemView.getLayoutParams()).A02 = true;
        }
    }

    public void onEnteredHiddenState(RecyclerView recyclerView) {
        int i = this.mPendingAccessibilityState;
        if (i != -1) {
            this.mWasImportantForAccessibilityBeforeHidden = i;
        } else {
            this.mWasImportantForAccessibilityBeforeHidden = this.itemView.getImportantForAccessibility();
        }
        recyclerView.setChildImportantForAccessibilityInternal(this, 4);
    }

    public void onLeftHiddenState(RecyclerView recyclerView) {
        recyclerView.setChildImportantForAccessibilityInternal(this, this.mWasImportantForAccessibilityBeforeHidden);
        this.mWasImportantForAccessibilityBeforeHidden = 0;
    }

    public void saveOldPosition() {
        if (this.mOldPosition == -1) {
            this.mOldPosition = this.mPosition;
        }
    }

    public void setFlags(int i, int i2) {
        this.mFlags = (i & i2) | (this.mFlags & (i2 ^ -1));
    }

    public void setScrapContainer(AnonymousClass1Af r1, boolean z) {
        this.mScrapContainer = r1;
        this.mInChangeScrap = z;
    }

    public boolean shouldBeKeptAsChild() {
        if ((this.mFlags & 16) != 0) {
            return true;
        }
        return false;
    }

    public boolean shouldIgnore() {
        if ((this.mFlags & 128) != 0) {
            return true;
        }
        return false;
    }

    public void stopIgnoring() {
        this.mFlags &= -129;
    }

    public void unScrap() {
        this.mScrapContainer.A08(this);
    }

    public boolean wasReturnedFromScrap() {
        if ((this.mFlags & 32) != 0) {
            return true;
        }
        return false;
    }

    public AnonymousClass1Ah(@NonNull View view) {
        if (view != null) {
            this.itemView = view;
            return;
        }
        throw new IllegalArgumentException("itemView may not be null");
    }

    @Deprecated
    public final int getAdapterPosition() {
        return getBindingAdapterPosition();
    }

    public String toString() {
        String simpleName;
        String str;
        Class<?> cls = getClass();
        if (cls.isAnonymousClass()) {
            simpleName = "ViewHolder";
        } else {
            simpleName = cls.getSimpleName();
        }
        StringBuilder sb = new StringBuilder();
        sb.append(simpleName);
        sb.append("{");
        sb.append(Integer.toHexString(hashCode()));
        sb.append(" position=");
        sb.append(this.mPosition);
        sb.append(" id=");
        sb.append(this.mItemId);
        sb.append(", oldPos=");
        sb.append(this.mOldPosition);
        sb.append(", pLpos:");
        sb.append(this.mPreLayoutPosition);
        StringBuilder sb2 = new StringBuilder(sb.toString());
        if (isScrap()) {
            sb2.append(" scrap ");
            if (this.mInChangeScrap) {
                str = "[changeScrap]";
            } else {
                str = "[attachedScrap]";
            }
            sb2.append(str);
        }
        if (isInvalid()) {
            sb2.append(" invalid");
        }
        if (!isBound()) {
            sb2.append(" unbound");
        }
        if (needsUpdate()) {
            sb2.append(" update");
        }
        if (isRemoved()) {
            sb2.append(" removed");
        }
        if (shouldIgnore()) {
            sb2.append(" ignored");
        }
        if (isTmpDetached()) {
            sb2.append(" tmpDetached");
        }
        if (!isRecyclable()) {
            sb2.append(AnonymousClass006.A04(" not recyclable(", this.mIsRecyclableCount, ")"));
        }
        if (isAdapterPositionUnknown()) {
            sb2.append(" undefined adapter position");
        }
        if (this.itemView.getParent() == null) {
            sb2.append(" no parent");
        }
        sb2.append("}");
        return sb2.toString();
    }
}
