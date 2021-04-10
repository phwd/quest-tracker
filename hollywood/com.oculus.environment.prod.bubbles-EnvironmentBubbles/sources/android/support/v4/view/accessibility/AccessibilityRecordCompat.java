package android.support.v4.view.accessibility;

import android.os.Build;
import android.os.Parcelable;
import android.view.View;
import java.util.Collections;
import java.util.List;

public class AccessibilityRecordCompat {
    private static final AccessibilityRecordImpl IMPL;
    private final Object mRecord;

    /* access modifiers changed from: package-private */
    public interface AccessibilityRecordImpl {
        int getAddedCount(Object obj);

        CharSequence getBeforeText(Object obj);

        CharSequence getClassName(Object obj);

        CharSequence getContentDescription(Object obj);

        int getCurrentItemIndex(Object obj);

        int getFromIndex(Object obj);

        int getItemCount(Object obj);

        int getMaxScrollX(Object obj);

        int getMaxScrollY(Object obj);

        Parcelable getParcelableData(Object obj);

        int getRemovedCount(Object obj);

        int getScrollX(Object obj);

        int getScrollY(Object obj);

        AccessibilityNodeInfoCompat getSource(Object obj);

        List<CharSequence> getText(Object obj);

        int getToIndex(Object obj);

        int getWindowId(Object obj);

        boolean isChecked(Object obj);

        boolean isEnabled(Object obj);

        boolean isFullScreen(Object obj);

        boolean isPassword(Object obj);

        boolean isScrollable(Object obj);

        Object obtain();

        Object obtain(Object obj);

        void recycle(Object obj);

        void setAddedCount(Object obj, int i);

        void setBeforeText(Object obj, CharSequence charSequence);

        void setChecked(Object obj, boolean z);

        void setClassName(Object obj, CharSequence charSequence);

        void setContentDescription(Object obj, CharSequence charSequence);

        void setCurrentItemIndex(Object obj, int i);

        void setEnabled(Object obj, boolean z);

        void setFromIndex(Object obj, int i);

        void setFullScreen(Object obj, boolean z);

        void setItemCount(Object obj, int i);

        void setMaxScrollX(Object obj, int i);

        void setMaxScrollY(Object obj, int i);

        void setParcelableData(Object obj, Parcelable parcelable);

        void setPassword(Object obj, boolean z);

        void setRemovedCount(Object obj, int i);

        void setScrollX(Object obj, int i);

        void setScrollY(Object obj, int i);

        void setScrollable(Object obj, boolean z);

        void setSource(Object obj, View view);

        void setSource(Object obj, View view, int i);

        void setToIndex(Object obj, int i);
    }

    static class AccessibilityRecordStubImpl implements AccessibilityRecordImpl {
        @Override // android.support.v4.view.accessibility.AccessibilityRecordCompat.AccessibilityRecordImpl
        public int getAddedCount(Object obj) {
            return 0;
        }

        @Override // android.support.v4.view.accessibility.AccessibilityRecordCompat.AccessibilityRecordImpl
        public CharSequence getBeforeText(Object obj) {
            return null;
        }

        @Override // android.support.v4.view.accessibility.AccessibilityRecordCompat.AccessibilityRecordImpl
        public CharSequence getClassName(Object obj) {
            return null;
        }

        @Override // android.support.v4.view.accessibility.AccessibilityRecordCompat.AccessibilityRecordImpl
        public CharSequence getContentDescription(Object obj) {
            return null;
        }

        @Override // android.support.v4.view.accessibility.AccessibilityRecordCompat.AccessibilityRecordImpl
        public int getCurrentItemIndex(Object obj) {
            return 0;
        }

        @Override // android.support.v4.view.accessibility.AccessibilityRecordCompat.AccessibilityRecordImpl
        public int getFromIndex(Object obj) {
            return 0;
        }

        @Override // android.support.v4.view.accessibility.AccessibilityRecordCompat.AccessibilityRecordImpl
        public int getItemCount(Object obj) {
            return 0;
        }

        @Override // android.support.v4.view.accessibility.AccessibilityRecordCompat.AccessibilityRecordImpl
        public int getMaxScrollX(Object obj) {
            return 0;
        }

        @Override // android.support.v4.view.accessibility.AccessibilityRecordCompat.AccessibilityRecordImpl
        public int getMaxScrollY(Object obj) {
            return 0;
        }

        @Override // android.support.v4.view.accessibility.AccessibilityRecordCompat.AccessibilityRecordImpl
        public Parcelable getParcelableData(Object obj) {
            return null;
        }

        @Override // android.support.v4.view.accessibility.AccessibilityRecordCompat.AccessibilityRecordImpl
        public int getRemovedCount(Object obj) {
            return 0;
        }

        @Override // android.support.v4.view.accessibility.AccessibilityRecordCompat.AccessibilityRecordImpl
        public int getScrollX(Object obj) {
            return 0;
        }

        @Override // android.support.v4.view.accessibility.AccessibilityRecordCompat.AccessibilityRecordImpl
        public int getScrollY(Object obj) {
            return 0;
        }

        @Override // android.support.v4.view.accessibility.AccessibilityRecordCompat.AccessibilityRecordImpl
        public AccessibilityNodeInfoCompat getSource(Object obj) {
            return null;
        }

        @Override // android.support.v4.view.accessibility.AccessibilityRecordCompat.AccessibilityRecordImpl
        public int getToIndex(Object obj) {
            return 0;
        }

        @Override // android.support.v4.view.accessibility.AccessibilityRecordCompat.AccessibilityRecordImpl
        public int getWindowId(Object obj) {
            return 0;
        }

        @Override // android.support.v4.view.accessibility.AccessibilityRecordCompat.AccessibilityRecordImpl
        public boolean isChecked(Object obj) {
            return false;
        }

        @Override // android.support.v4.view.accessibility.AccessibilityRecordCompat.AccessibilityRecordImpl
        public boolean isEnabled(Object obj) {
            return false;
        }

        @Override // android.support.v4.view.accessibility.AccessibilityRecordCompat.AccessibilityRecordImpl
        public boolean isFullScreen(Object obj) {
            return false;
        }

        @Override // android.support.v4.view.accessibility.AccessibilityRecordCompat.AccessibilityRecordImpl
        public boolean isPassword(Object obj) {
            return false;
        }

        @Override // android.support.v4.view.accessibility.AccessibilityRecordCompat.AccessibilityRecordImpl
        public boolean isScrollable(Object obj) {
            return false;
        }

        @Override // android.support.v4.view.accessibility.AccessibilityRecordCompat.AccessibilityRecordImpl
        public Object obtain() {
            return null;
        }

        @Override // android.support.v4.view.accessibility.AccessibilityRecordCompat.AccessibilityRecordImpl
        public Object obtain(Object obj) {
            return null;
        }

        @Override // android.support.v4.view.accessibility.AccessibilityRecordCompat.AccessibilityRecordImpl
        public void recycle(Object obj) {
        }

        @Override // android.support.v4.view.accessibility.AccessibilityRecordCompat.AccessibilityRecordImpl
        public void setAddedCount(Object obj, int i) {
        }

        @Override // android.support.v4.view.accessibility.AccessibilityRecordCompat.AccessibilityRecordImpl
        public void setBeforeText(Object obj, CharSequence charSequence) {
        }

        @Override // android.support.v4.view.accessibility.AccessibilityRecordCompat.AccessibilityRecordImpl
        public void setChecked(Object obj, boolean z) {
        }

        @Override // android.support.v4.view.accessibility.AccessibilityRecordCompat.AccessibilityRecordImpl
        public void setClassName(Object obj, CharSequence charSequence) {
        }

        @Override // android.support.v4.view.accessibility.AccessibilityRecordCompat.AccessibilityRecordImpl
        public void setContentDescription(Object obj, CharSequence charSequence) {
        }

        @Override // android.support.v4.view.accessibility.AccessibilityRecordCompat.AccessibilityRecordImpl
        public void setCurrentItemIndex(Object obj, int i) {
        }

        @Override // android.support.v4.view.accessibility.AccessibilityRecordCompat.AccessibilityRecordImpl
        public void setEnabled(Object obj, boolean z) {
        }

        @Override // android.support.v4.view.accessibility.AccessibilityRecordCompat.AccessibilityRecordImpl
        public void setFromIndex(Object obj, int i) {
        }

        @Override // android.support.v4.view.accessibility.AccessibilityRecordCompat.AccessibilityRecordImpl
        public void setFullScreen(Object obj, boolean z) {
        }

        @Override // android.support.v4.view.accessibility.AccessibilityRecordCompat.AccessibilityRecordImpl
        public void setItemCount(Object obj, int i) {
        }

        @Override // android.support.v4.view.accessibility.AccessibilityRecordCompat.AccessibilityRecordImpl
        public void setMaxScrollX(Object obj, int i) {
        }

        @Override // android.support.v4.view.accessibility.AccessibilityRecordCompat.AccessibilityRecordImpl
        public void setMaxScrollY(Object obj, int i) {
        }

        @Override // android.support.v4.view.accessibility.AccessibilityRecordCompat.AccessibilityRecordImpl
        public void setParcelableData(Object obj, Parcelable parcelable) {
        }

        @Override // android.support.v4.view.accessibility.AccessibilityRecordCompat.AccessibilityRecordImpl
        public void setPassword(Object obj, boolean z) {
        }

        @Override // android.support.v4.view.accessibility.AccessibilityRecordCompat.AccessibilityRecordImpl
        public void setRemovedCount(Object obj, int i) {
        }

        @Override // android.support.v4.view.accessibility.AccessibilityRecordCompat.AccessibilityRecordImpl
        public void setScrollX(Object obj, int i) {
        }

        @Override // android.support.v4.view.accessibility.AccessibilityRecordCompat.AccessibilityRecordImpl
        public void setScrollY(Object obj, int i) {
        }

        @Override // android.support.v4.view.accessibility.AccessibilityRecordCompat.AccessibilityRecordImpl
        public void setScrollable(Object obj, boolean z) {
        }

        @Override // android.support.v4.view.accessibility.AccessibilityRecordCompat.AccessibilityRecordImpl
        public void setSource(Object obj, View view) {
        }

        @Override // android.support.v4.view.accessibility.AccessibilityRecordCompat.AccessibilityRecordImpl
        public void setSource(Object obj, View view, int i) {
        }

        @Override // android.support.v4.view.accessibility.AccessibilityRecordCompat.AccessibilityRecordImpl
        public void setToIndex(Object obj, int i) {
        }

        AccessibilityRecordStubImpl() {
        }

        @Override // android.support.v4.view.accessibility.AccessibilityRecordCompat.AccessibilityRecordImpl
        public List<CharSequence> getText(Object obj) {
            return Collections.emptyList();
        }
    }

    static class AccessibilityRecordIcsImpl extends AccessibilityRecordStubImpl {
        AccessibilityRecordIcsImpl() {
        }

        @Override // android.support.v4.view.accessibility.AccessibilityRecordCompat.AccessibilityRecordImpl, android.support.v4.view.accessibility.AccessibilityRecordCompat.AccessibilityRecordStubImpl
        public Object obtain() {
            return AccessibilityRecordCompatIcs.obtain();
        }

        @Override // android.support.v4.view.accessibility.AccessibilityRecordCompat.AccessibilityRecordImpl, android.support.v4.view.accessibility.AccessibilityRecordCompat.AccessibilityRecordStubImpl
        public Object obtain(Object obj) {
            return AccessibilityRecordCompatIcs.obtain(obj);
        }

        @Override // android.support.v4.view.accessibility.AccessibilityRecordCompat.AccessibilityRecordImpl, android.support.v4.view.accessibility.AccessibilityRecordCompat.AccessibilityRecordStubImpl
        public int getAddedCount(Object obj) {
            return AccessibilityRecordCompatIcs.getAddedCount(obj);
        }

        @Override // android.support.v4.view.accessibility.AccessibilityRecordCompat.AccessibilityRecordImpl, android.support.v4.view.accessibility.AccessibilityRecordCompat.AccessibilityRecordStubImpl
        public CharSequence getBeforeText(Object obj) {
            return AccessibilityRecordCompatIcs.getBeforeText(obj);
        }

        @Override // android.support.v4.view.accessibility.AccessibilityRecordCompat.AccessibilityRecordImpl, android.support.v4.view.accessibility.AccessibilityRecordCompat.AccessibilityRecordStubImpl
        public CharSequence getClassName(Object obj) {
            return AccessibilityRecordCompatIcs.getClassName(obj);
        }

        @Override // android.support.v4.view.accessibility.AccessibilityRecordCompat.AccessibilityRecordImpl, android.support.v4.view.accessibility.AccessibilityRecordCompat.AccessibilityRecordStubImpl
        public CharSequence getContentDescription(Object obj) {
            return AccessibilityRecordCompatIcs.getContentDescription(obj);
        }

        @Override // android.support.v4.view.accessibility.AccessibilityRecordCompat.AccessibilityRecordImpl, android.support.v4.view.accessibility.AccessibilityRecordCompat.AccessibilityRecordStubImpl
        public int getCurrentItemIndex(Object obj) {
            return AccessibilityRecordCompatIcs.getCurrentItemIndex(obj);
        }

        @Override // android.support.v4.view.accessibility.AccessibilityRecordCompat.AccessibilityRecordImpl, android.support.v4.view.accessibility.AccessibilityRecordCompat.AccessibilityRecordStubImpl
        public int getFromIndex(Object obj) {
            return AccessibilityRecordCompatIcs.getFromIndex(obj);
        }

        @Override // android.support.v4.view.accessibility.AccessibilityRecordCompat.AccessibilityRecordImpl, android.support.v4.view.accessibility.AccessibilityRecordCompat.AccessibilityRecordStubImpl
        public int getItemCount(Object obj) {
            return AccessibilityRecordCompatIcs.getItemCount(obj);
        }

        @Override // android.support.v4.view.accessibility.AccessibilityRecordCompat.AccessibilityRecordImpl, android.support.v4.view.accessibility.AccessibilityRecordCompat.AccessibilityRecordStubImpl
        public Parcelable getParcelableData(Object obj) {
            return AccessibilityRecordCompatIcs.getParcelableData(obj);
        }

        @Override // android.support.v4.view.accessibility.AccessibilityRecordCompat.AccessibilityRecordImpl, android.support.v4.view.accessibility.AccessibilityRecordCompat.AccessibilityRecordStubImpl
        public int getRemovedCount(Object obj) {
            return AccessibilityRecordCompatIcs.getRemovedCount(obj);
        }

        @Override // android.support.v4.view.accessibility.AccessibilityRecordCompat.AccessibilityRecordImpl, android.support.v4.view.accessibility.AccessibilityRecordCompat.AccessibilityRecordStubImpl
        public int getScrollX(Object obj) {
            return AccessibilityRecordCompatIcs.getScrollX(obj);
        }

        @Override // android.support.v4.view.accessibility.AccessibilityRecordCompat.AccessibilityRecordImpl, android.support.v4.view.accessibility.AccessibilityRecordCompat.AccessibilityRecordStubImpl
        public int getScrollY(Object obj) {
            return AccessibilityRecordCompatIcs.getScrollY(obj);
        }

        @Override // android.support.v4.view.accessibility.AccessibilityRecordCompat.AccessibilityRecordImpl, android.support.v4.view.accessibility.AccessibilityRecordCompat.AccessibilityRecordStubImpl
        public AccessibilityNodeInfoCompat getSource(Object obj) {
            return AccessibilityNodeInfoCompat.wrapNonNullInstance(AccessibilityRecordCompatIcs.getSource(obj));
        }

        @Override // android.support.v4.view.accessibility.AccessibilityRecordCompat.AccessibilityRecordImpl, android.support.v4.view.accessibility.AccessibilityRecordCompat.AccessibilityRecordStubImpl
        public List<CharSequence> getText(Object obj) {
            return AccessibilityRecordCompatIcs.getText(obj);
        }

        @Override // android.support.v4.view.accessibility.AccessibilityRecordCompat.AccessibilityRecordImpl, android.support.v4.view.accessibility.AccessibilityRecordCompat.AccessibilityRecordStubImpl
        public int getToIndex(Object obj) {
            return AccessibilityRecordCompatIcs.getToIndex(obj);
        }

        @Override // android.support.v4.view.accessibility.AccessibilityRecordCompat.AccessibilityRecordImpl, android.support.v4.view.accessibility.AccessibilityRecordCompat.AccessibilityRecordStubImpl
        public int getWindowId(Object obj) {
            return AccessibilityRecordCompatIcs.getWindowId(obj);
        }

        @Override // android.support.v4.view.accessibility.AccessibilityRecordCompat.AccessibilityRecordImpl, android.support.v4.view.accessibility.AccessibilityRecordCompat.AccessibilityRecordStubImpl
        public boolean isChecked(Object obj) {
            return AccessibilityRecordCompatIcs.isChecked(obj);
        }

        @Override // android.support.v4.view.accessibility.AccessibilityRecordCompat.AccessibilityRecordImpl, android.support.v4.view.accessibility.AccessibilityRecordCompat.AccessibilityRecordStubImpl
        public boolean isEnabled(Object obj) {
            return AccessibilityRecordCompatIcs.isEnabled(obj);
        }

        @Override // android.support.v4.view.accessibility.AccessibilityRecordCompat.AccessibilityRecordImpl, android.support.v4.view.accessibility.AccessibilityRecordCompat.AccessibilityRecordStubImpl
        public boolean isFullScreen(Object obj) {
            return AccessibilityRecordCompatIcs.isFullScreen(obj);
        }

        @Override // android.support.v4.view.accessibility.AccessibilityRecordCompat.AccessibilityRecordImpl, android.support.v4.view.accessibility.AccessibilityRecordCompat.AccessibilityRecordStubImpl
        public boolean isPassword(Object obj) {
            return AccessibilityRecordCompatIcs.isPassword(obj);
        }

        @Override // android.support.v4.view.accessibility.AccessibilityRecordCompat.AccessibilityRecordImpl, android.support.v4.view.accessibility.AccessibilityRecordCompat.AccessibilityRecordStubImpl
        public boolean isScrollable(Object obj) {
            return AccessibilityRecordCompatIcs.isScrollable(obj);
        }

        @Override // android.support.v4.view.accessibility.AccessibilityRecordCompat.AccessibilityRecordImpl, android.support.v4.view.accessibility.AccessibilityRecordCompat.AccessibilityRecordStubImpl
        public void recycle(Object obj) {
            AccessibilityRecordCompatIcs.recycle(obj);
        }

        @Override // android.support.v4.view.accessibility.AccessibilityRecordCompat.AccessibilityRecordImpl, android.support.v4.view.accessibility.AccessibilityRecordCompat.AccessibilityRecordStubImpl
        public void setAddedCount(Object obj, int i) {
            AccessibilityRecordCompatIcs.setAddedCount(obj, i);
        }

        @Override // android.support.v4.view.accessibility.AccessibilityRecordCompat.AccessibilityRecordImpl, android.support.v4.view.accessibility.AccessibilityRecordCompat.AccessibilityRecordStubImpl
        public void setBeforeText(Object obj, CharSequence charSequence) {
            AccessibilityRecordCompatIcs.setBeforeText(obj, charSequence);
        }

        @Override // android.support.v4.view.accessibility.AccessibilityRecordCompat.AccessibilityRecordImpl, android.support.v4.view.accessibility.AccessibilityRecordCompat.AccessibilityRecordStubImpl
        public void setChecked(Object obj, boolean z) {
            AccessibilityRecordCompatIcs.setChecked(obj, z);
        }

        @Override // android.support.v4.view.accessibility.AccessibilityRecordCompat.AccessibilityRecordImpl, android.support.v4.view.accessibility.AccessibilityRecordCompat.AccessibilityRecordStubImpl
        public void setClassName(Object obj, CharSequence charSequence) {
            AccessibilityRecordCompatIcs.setClassName(obj, charSequence);
        }

        @Override // android.support.v4.view.accessibility.AccessibilityRecordCompat.AccessibilityRecordImpl, android.support.v4.view.accessibility.AccessibilityRecordCompat.AccessibilityRecordStubImpl
        public void setContentDescription(Object obj, CharSequence charSequence) {
            AccessibilityRecordCompatIcs.setContentDescription(obj, charSequence);
        }

        @Override // android.support.v4.view.accessibility.AccessibilityRecordCompat.AccessibilityRecordImpl, android.support.v4.view.accessibility.AccessibilityRecordCompat.AccessibilityRecordStubImpl
        public void setCurrentItemIndex(Object obj, int i) {
            AccessibilityRecordCompatIcs.setCurrentItemIndex(obj, i);
        }

        @Override // android.support.v4.view.accessibility.AccessibilityRecordCompat.AccessibilityRecordImpl, android.support.v4.view.accessibility.AccessibilityRecordCompat.AccessibilityRecordStubImpl
        public void setEnabled(Object obj, boolean z) {
            AccessibilityRecordCompatIcs.setEnabled(obj, z);
        }

        @Override // android.support.v4.view.accessibility.AccessibilityRecordCompat.AccessibilityRecordImpl, android.support.v4.view.accessibility.AccessibilityRecordCompat.AccessibilityRecordStubImpl
        public void setFromIndex(Object obj, int i) {
            AccessibilityRecordCompatIcs.setFromIndex(obj, i);
        }

        @Override // android.support.v4.view.accessibility.AccessibilityRecordCompat.AccessibilityRecordImpl, android.support.v4.view.accessibility.AccessibilityRecordCompat.AccessibilityRecordStubImpl
        public void setFullScreen(Object obj, boolean z) {
            AccessibilityRecordCompatIcs.setFullScreen(obj, z);
        }

        @Override // android.support.v4.view.accessibility.AccessibilityRecordCompat.AccessibilityRecordImpl, android.support.v4.view.accessibility.AccessibilityRecordCompat.AccessibilityRecordStubImpl
        public void setItemCount(Object obj, int i) {
            AccessibilityRecordCompatIcs.setItemCount(obj, i);
        }

        @Override // android.support.v4.view.accessibility.AccessibilityRecordCompat.AccessibilityRecordImpl, android.support.v4.view.accessibility.AccessibilityRecordCompat.AccessibilityRecordStubImpl
        public void setParcelableData(Object obj, Parcelable parcelable) {
            AccessibilityRecordCompatIcs.setParcelableData(obj, parcelable);
        }

        @Override // android.support.v4.view.accessibility.AccessibilityRecordCompat.AccessibilityRecordImpl, android.support.v4.view.accessibility.AccessibilityRecordCompat.AccessibilityRecordStubImpl
        public void setPassword(Object obj, boolean z) {
            AccessibilityRecordCompatIcs.setPassword(obj, z);
        }

        @Override // android.support.v4.view.accessibility.AccessibilityRecordCompat.AccessibilityRecordImpl, android.support.v4.view.accessibility.AccessibilityRecordCompat.AccessibilityRecordStubImpl
        public void setRemovedCount(Object obj, int i) {
            AccessibilityRecordCompatIcs.setRemovedCount(obj, i);
        }

        @Override // android.support.v4.view.accessibility.AccessibilityRecordCompat.AccessibilityRecordImpl, android.support.v4.view.accessibility.AccessibilityRecordCompat.AccessibilityRecordStubImpl
        public void setScrollX(Object obj, int i) {
            AccessibilityRecordCompatIcs.setScrollX(obj, i);
        }

        @Override // android.support.v4.view.accessibility.AccessibilityRecordCompat.AccessibilityRecordImpl, android.support.v4.view.accessibility.AccessibilityRecordCompat.AccessibilityRecordStubImpl
        public void setScrollY(Object obj, int i) {
            AccessibilityRecordCompatIcs.setScrollY(obj, i);
        }

        @Override // android.support.v4.view.accessibility.AccessibilityRecordCompat.AccessibilityRecordImpl, android.support.v4.view.accessibility.AccessibilityRecordCompat.AccessibilityRecordStubImpl
        public void setScrollable(Object obj, boolean z) {
            AccessibilityRecordCompatIcs.setScrollable(obj, z);
        }

        @Override // android.support.v4.view.accessibility.AccessibilityRecordCompat.AccessibilityRecordImpl, android.support.v4.view.accessibility.AccessibilityRecordCompat.AccessibilityRecordStubImpl
        public void setSource(Object obj, View view) {
            AccessibilityRecordCompatIcs.setSource(obj, view);
        }

        @Override // android.support.v4.view.accessibility.AccessibilityRecordCompat.AccessibilityRecordImpl, android.support.v4.view.accessibility.AccessibilityRecordCompat.AccessibilityRecordStubImpl
        public void setToIndex(Object obj, int i) {
            AccessibilityRecordCompatIcs.setToIndex(obj, i);
        }
    }

    static class AccessibilityRecordIcsMr1Impl extends AccessibilityRecordIcsImpl {
        AccessibilityRecordIcsMr1Impl() {
        }

        @Override // android.support.v4.view.accessibility.AccessibilityRecordCompat.AccessibilityRecordImpl, android.support.v4.view.accessibility.AccessibilityRecordCompat.AccessibilityRecordStubImpl
        public int getMaxScrollX(Object obj) {
            return AccessibilityRecordCompatIcsMr1.getMaxScrollX(obj);
        }

        @Override // android.support.v4.view.accessibility.AccessibilityRecordCompat.AccessibilityRecordImpl, android.support.v4.view.accessibility.AccessibilityRecordCompat.AccessibilityRecordStubImpl
        public int getMaxScrollY(Object obj) {
            return AccessibilityRecordCompatIcsMr1.getMaxScrollY(obj);
        }

        @Override // android.support.v4.view.accessibility.AccessibilityRecordCompat.AccessibilityRecordImpl, android.support.v4.view.accessibility.AccessibilityRecordCompat.AccessibilityRecordStubImpl
        public void setMaxScrollX(Object obj, int i) {
            AccessibilityRecordCompatIcsMr1.setMaxScrollX(obj, i);
        }

        @Override // android.support.v4.view.accessibility.AccessibilityRecordCompat.AccessibilityRecordImpl, android.support.v4.view.accessibility.AccessibilityRecordCompat.AccessibilityRecordStubImpl
        public void setMaxScrollY(Object obj, int i) {
            AccessibilityRecordCompatIcsMr1.setMaxScrollY(obj, i);
        }
    }

    static class AccessibilityRecordJellyBeanImpl extends AccessibilityRecordIcsMr1Impl {
        AccessibilityRecordJellyBeanImpl() {
        }

        @Override // android.support.v4.view.accessibility.AccessibilityRecordCompat.AccessibilityRecordImpl, android.support.v4.view.accessibility.AccessibilityRecordCompat.AccessibilityRecordStubImpl
        public void setSource(Object obj, View view, int i) {
            AccessibilityRecordCompatJellyBean.setSource(obj, view, i);
        }
    }

    static {
        if (Build.VERSION.SDK_INT >= 16) {
            IMPL = new AccessibilityRecordJellyBeanImpl();
        } else if (Build.VERSION.SDK_INT >= 15) {
            IMPL = new AccessibilityRecordIcsMr1Impl();
        } else if (Build.VERSION.SDK_INT >= 14) {
            IMPL = new AccessibilityRecordIcsImpl();
        } else {
            IMPL = new AccessibilityRecordStubImpl();
        }
    }

    @Deprecated
    public AccessibilityRecordCompat(Object obj) {
        this.mRecord = obj;
    }

    @Deprecated
    public Object getImpl() {
        return this.mRecord;
    }

    public static AccessibilityRecordCompat obtain(AccessibilityRecordCompat accessibilityRecordCompat) {
        return new AccessibilityRecordCompat(IMPL.obtain(accessibilityRecordCompat.mRecord));
    }

    public static AccessibilityRecordCompat obtain() {
        return new AccessibilityRecordCompat(IMPL.obtain());
    }

    public void setSource(View view) {
        IMPL.setSource(this.mRecord, view);
    }

    public void setSource(View view, int i) {
        IMPL.setSource(this.mRecord, view, i);
    }

    public AccessibilityNodeInfoCompat getSource() {
        return IMPL.getSource(this.mRecord);
    }

    public int getWindowId() {
        return IMPL.getWindowId(this.mRecord);
    }

    public boolean isChecked() {
        return IMPL.isChecked(this.mRecord);
    }

    public void setChecked(boolean z) {
        IMPL.setChecked(this.mRecord, z);
    }

    public boolean isEnabled() {
        return IMPL.isEnabled(this.mRecord);
    }

    public void setEnabled(boolean z) {
        IMPL.setEnabled(this.mRecord, z);
    }

    public boolean isPassword() {
        return IMPL.isPassword(this.mRecord);
    }

    public void setPassword(boolean z) {
        IMPL.setPassword(this.mRecord, z);
    }

    public boolean isFullScreen() {
        return IMPL.isFullScreen(this.mRecord);
    }

    public void setFullScreen(boolean z) {
        IMPL.setFullScreen(this.mRecord, z);
    }

    public boolean isScrollable() {
        return IMPL.isScrollable(this.mRecord);
    }

    public void setScrollable(boolean z) {
        IMPL.setScrollable(this.mRecord, z);
    }

    public int getItemCount() {
        return IMPL.getItemCount(this.mRecord);
    }

    public void setItemCount(int i) {
        IMPL.setItemCount(this.mRecord, i);
    }

    public int getCurrentItemIndex() {
        return IMPL.getCurrentItemIndex(this.mRecord);
    }

    public void setCurrentItemIndex(int i) {
        IMPL.setCurrentItemIndex(this.mRecord, i);
    }

    public int getFromIndex() {
        return IMPL.getFromIndex(this.mRecord);
    }

    public void setFromIndex(int i) {
        IMPL.setFromIndex(this.mRecord, i);
    }

    public int getToIndex() {
        return IMPL.getToIndex(this.mRecord);
    }

    public void setToIndex(int i) {
        IMPL.setToIndex(this.mRecord, i);
    }

    public int getScrollX() {
        return IMPL.getScrollX(this.mRecord);
    }

    public void setScrollX(int i) {
        IMPL.setScrollX(this.mRecord, i);
    }

    public int getScrollY() {
        return IMPL.getScrollY(this.mRecord);
    }

    public void setScrollY(int i) {
        IMPL.setScrollY(this.mRecord, i);
    }

    public int getMaxScrollX() {
        return IMPL.getMaxScrollX(this.mRecord);
    }

    public void setMaxScrollX(int i) {
        IMPL.setMaxScrollX(this.mRecord, i);
    }

    public int getMaxScrollY() {
        return IMPL.getMaxScrollY(this.mRecord);
    }

    public void setMaxScrollY(int i) {
        IMPL.setMaxScrollY(this.mRecord, i);
    }

    public int getAddedCount() {
        return IMPL.getAddedCount(this.mRecord);
    }

    public void setAddedCount(int i) {
        IMPL.setAddedCount(this.mRecord, i);
    }

    public int getRemovedCount() {
        return IMPL.getRemovedCount(this.mRecord);
    }

    public void setRemovedCount(int i) {
        IMPL.setRemovedCount(this.mRecord, i);
    }

    public CharSequence getClassName() {
        return IMPL.getClassName(this.mRecord);
    }

    public void setClassName(CharSequence charSequence) {
        IMPL.setClassName(this.mRecord, charSequence);
    }

    public List<CharSequence> getText() {
        return IMPL.getText(this.mRecord);
    }

    public CharSequence getBeforeText() {
        return IMPL.getBeforeText(this.mRecord);
    }

    public void setBeforeText(CharSequence charSequence) {
        IMPL.setBeforeText(this.mRecord, charSequence);
    }

    public CharSequence getContentDescription() {
        return IMPL.getContentDescription(this.mRecord);
    }

    public void setContentDescription(CharSequence charSequence) {
        IMPL.setContentDescription(this.mRecord, charSequence);
    }

    public Parcelable getParcelableData() {
        return IMPL.getParcelableData(this.mRecord);
    }

    public void setParcelableData(Parcelable parcelable) {
        IMPL.setParcelableData(this.mRecord, parcelable);
    }

    public void recycle() {
        IMPL.recycle(this.mRecord);
    }

    public int hashCode() {
        Object obj = this.mRecord;
        if (obj == null) {
            return 0;
        }
        return obj.hashCode();
    }

    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null || getClass() != obj.getClass()) {
            return false;
        }
        AccessibilityRecordCompat accessibilityRecordCompat = (AccessibilityRecordCompat) obj;
        Object obj2 = this.mRecord;
        if (obj2 == null) {
            if (accessibilityRecordCompat.mRecord != null) {
                return false;
            }
        } else if (!obj2.equals(accessibilityRecordCompat.mRecord)) {
            return false;
        }
        return true;
    }
}
