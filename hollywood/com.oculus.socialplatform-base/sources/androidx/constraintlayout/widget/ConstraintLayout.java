package androidx.constraintlayout.widget;

import X.AnonymousClass2a3;
import X.AnonymousClass2a4;
import X.AnonymousClass2a5;
import X.AnonymousClass2a6;
import X.AnonymousClass2a8;
import X.AnonymousClass2aA;
import X.AnonymousClass2aI;
import X.AnonymousClass2aL;
import X.AnonymousClass2aY;
import X.AnonymousClass2aZ;
import X.AnonymousClass2ac;
import X.AnonymousClass2b2;
import X.C14932ab;
import X.C15002ak;
import X.C15022am;
import android.annotation.TargetApi;
import android.content.Context;
import android.content.res.Resources;
import android.content.res.TypedArray;
import android.content.res.XmlResourceParser;
import android.util.AttributeSet;
import android.util.SparseArray;
import android.util.Xml;
import android.view.View;
import android.view.ViewGroup;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import org.xmlpull.v1.XmlPullParserException;

public class ConstraintLayout extends ViewGroup {
    public static final boolean DEBUG = false;
    public static final boolean DEBUG_DRAW_CONSTRAINTS = false;
    public static final int DESIGN_INFO_ID = 0;
    public static final boolean MEASURE = false;
    public static final String TAG = "ConstraintLayout";
    public static final boolean USE_CONSTRAINTS_HELPER = true;
    public static final String VERSION = "ConstraintLayout-2.0.4";
    public SparseArray<View> mChildrenByIds = new SparseArray<>();
    public ArrayList<AnonymousClass2a3> mConstraintHelpers = new ArrayList<>(4);
    public AnonymousClass2a5 mConstraintLayoutSpec = null;
    public AnonymousClass2a4 mConstraintSet = null;
    public int mConstraintSetId = -1;
    public AnonymousClass2aZ mConstraintsChangedListener;
    public HashMap<String, Integer> mDesignIds = new HashMap<>();
    public boolean mDirtyHierarchy = true;
    public int mLastMeasureHeight = -1;
    public int mLastMeasureHeightMode = 0;
    public int mLastMeasureHeightSize = -1;
    public int mLastMeasureWidth = -1;
    public int mLastMeasureWidthMode = 0;
    public int mLastMeasureWidthSize = -1;
    public C14932ab mLayoutWidget = new C14932ab();
    public int mMaxHeight = Integer.MAX_VALUE;
    public int mMaxWidth = Integer.MAX_VALUE;
    public AnonymousClass2aA mMeasurer = new AnonymousClass2aA(this, this);
    public AnonymousClass2aY mMetrics;
    public int mMinHeight = 0;
    public int mMinWidth = 0;
    public int mOnMeasureHeightMeasureSpec = 0;
    public int mOnMeasureWidthMeasureSpec = 0;
    public int mOptimizationLevel = 257;
    public SparseArray<AnonymousClass2ac> mTempMapIdToWidget = new SparseArray<>();

    private void markHierarchyDirty() {
        this.mDirtyHierarchy = true;
        this.mLastMeasureWidth = -1;
        this.mLastMeasureHeight = -1;
        this.mLastMeasureWidthSize = -1;
        this.mLastMeasureHeightSize = -1;
        this.mLastMeasureWidthMode = 0;
        this.mLastMeasureHeightMode = 0;
    }

    public boolean isRtl() {
        return (getContext().getApplicationInfo().flags & 4194304) != 0 && 1 == getLayoutDirection();
    }

    public void loadLayoutDescription(int i) {
        if (i != 0) {
            try {
                this.mConstraintLayoutSpec = new AnonymousClass2a5(getContext(), this, i);
            } catch (Resources.NotFoundException unused) {
                this.mConstraintLayoutSpec = null;
            }
        } else {
            this.mConstraintLayoutSpec = null;
        }
    }

    public void onMeasure(int i, int i2) {
        int i3;
        C14932ab r0;
        if (!this.mDirtyHierarchy) {
            int childCount = getChildCount();
            int i4 = 0;
            while (true) {
                if (i4 >= childCount) {
                    break;
                } else if (getChildAt(i4).isLayoutRequested()) {
                    this.mDirtyHierarchy = true;
                    break;
                } else {
                    i4++;
                }
            }
        }
        if (!this.mDirtyHierarchy) {
            int i5 = this.mOnMeasureWidthMeasureSpec;
            if (!(i5 == i && this.mOnMeasureHeightMeasureSpec == i2)) {
                if (i5 == i && View.MeasureSpec.getMode(i) == 1073741824 && View.MeasureSpec.getMode(i2) == Integer.MIN_VALUE && View.MeasureSpec.getMode(this.mOnMeasureHeightMeasureSpec) == Integer.MIN_VALUE && View.MeasureSpec.getSize(i2) >= (r8 = (r0 = this.mLayoutWidget).A03())) {
                    this.mOnMeasureWidthMeasureSpec = i;
                    this.mOnMeasureHeightMeasureSpec = i2;
                    i3 = r0.A04();
                    resolveMeasuredDimension(i, i2, i3, r8, r0.A0F, r0.A0D);
                }
            }
            r0 = this.mLayoutWidget;
            i3 = r0.A04();
            int i6 = r0.A03();
            resolveMeasuredDimension(i, i2, i3, i6, r0.A0F, r0.A0D);
        }
        this.mOnMeasureWidthMeasureSpec = i;
        this.mOnMeasureHeightMeasureSpec = i2;
        this.mLayoutWidget.A0E = isRtl();
        if (this.mDirtyHierarchy) {
            this.mDirtyHierarchy = false;
            if (updateHierarchy()) {
                C14932ab r1 = this.mLayoutWidget;
                r1.A09.A02(r1);
            }
        }
        resolveSystem(this.mLayoutWidget, this.mOptimizationLevel, i, i2);
        r0 = this.mLayoutWidget;
        i3 = r0.A04();
        int i62 = r0.A03();
        resolveMeasuredDimension(i, i2, i3, i62, r0.A0F, r0.A0D);
    }

    public boolean shouldDelayChildPressedState() {
        return false;
    }

    private final AnonymousClass2ac getTargetWidget(int i) {
        if (i != 0) {
            View view = this.mChildrenByIds.get(i);
            if (view == null) {
                view = findViewById(i);
                if (view == null) {
                    return null;
                }
                if (view != this && view.getParent() == this) {
                    onViewAdded(view);
                }
            }
            if (view != this) {
                return ((AnonymousClass2a8) view.getLayoutParams()).A0w;
            }
        }
        return this.mLayoutWidget;
    }

    private void init(AttributeSet attributeSet, int i, int i2) {
        C14932ab r0 = this.mLayoutWidget;
        r0.A0i = this;
        AnonymousClass2aA r1 = this.mMeasurer;
        r0.A08 = r1;
        r0.A0A.A03 = r1;
        this.mChildrenByIds.put(getId(), this);
        this.mConstraintSet = null;
        if (attributeSet != null) {
            Context context = getContext();
            TypedArray obtainStyledAttributes = context.obtainStyledAttributes(attributeSet, AnonymousClass2aI.A01, i, i2);
            int indexCount = obtainStyledAttributes.getIndexCount();
            for (int i3 = 0; i3 < indexCount; i3++) {
                int index = obtainStyledAttributes.getIndex(i3);
                if (index == 9) {
                    this.mMinWidth = obtainStyledAttributes.getDimensionPixelOffset(index, this.mMinWidth);
                } else if (index == 10) {
                    this.mMinHeight = obtainStyledAttributes.getDimensionPixelOffset(index, this.mMinHeight);
                } else if (index == 7) {
                    this.mMaxWidth = obtainStyledAttributes.getDimensionPixelOffset(index, this.mMaxWidth);
                } else if (index == 8) {
                    this.mMaxHeight = obtainStyledAttributes.getDimensionPixelOffset(index, this.mMaxHeight);
                } else if (index == 90) {
                    this.mOptimizationLevel = obtainStyledAttributes.getInt(index, this.mOptimizationLevel);
                } else if (index == 39) {
                    int resourceId = obtainStyledAttributes.getResourceId(index, 0);
                    if (resourceId != 0) {
                        try {
                            parseLayoutDescription(resourceId);
                        } catch (Resources.NotFoundException unused) {
                            this.mConstraintLayoutSpec = null;
                        }
                    }
                } else if (index == 18) {
                    int resourceId2 = obtainStyledAttributes.getResourceId(index, 0);
                    try {
                        AnonymousClass2a4 r10 = new AnonymousClass2a4();
                        this.mConstraintSet = r10;
                        getContext();
                        XmlResourceParser xml = context.getResources().getXml(resourceId2);
                        try {
                            for (int eventType = xml.getEventType(); eventType != 1; eventType = xml.next()) {
                                if (eventType == 0) {
                                    xml.getName();
                                } else if (eventType == 2) {
                                    String name = xml.getName();
                                    AnonymousClass2a6 A01 = AnonymousClass2a4.A01(context, Xml.asAttributeSet(xml));
                                    if (name.equalsIgnoreCase("Guideline")) {
                                        A01.A02.A0y = true;
                                    }
                                    r10.A00.put(Integer.valueOf(A01.A00), A01);
                                }
                            }
                        } catch (IOException | XmlPullParserException e) {
                            e.printStackTrace();
                        }
                    } catch (Resources.NotFoundException unused2) {
                        this.mConstraintSet = null;
                    }
                    this.mConstraintSetId = resourceId2;
                }
            }
            obtainStyledAttributes.recycle();
        }
        C14932ab r02 = this.mLayoutWidget;
        int i4 = this.mOptimizationLevel;
        r02.A01 = i4;
        boolean z = false;
        if ((i4 & 512) == 512) {
            z = true;
        }
        C15022am.A0I = z;
    }

    private void setChildrenConstraints() {
        boolean isInEditMode = isInEditMode();
        int childCount = getChildCount();
        for (int i = 0; i < childCount; i++) {
            AnonymousClass2ac viewWidget = getViewWidget(getChildAt(i));
            if (viewWidget != null) {
                viewWidget.A0A();
            }
        }
        if (isInEditMode) {
            for (int i2 = 0; i2 < childCount; i2++) {
                View childAt = getChildAt(i2);
                try {
                    String resourceName = getResources().getResourceName(childAt.getId());
                    setDesignInformation(0, resourceName, Integer.valueOf(childAt.getId()));
                    int indexOf = resourceName.indexOf(47);
                    if (indexOf != -1) {
                        resourceName = resourceName.substring(indexOf + 1);
                    }
                    getTargetWidget(childAt.getId()).A0j = resourceName;
                } catch (Resources.NotFoundException unused) {
                }
            }
        }
        if (this.mConstraintSetId != -1) {
            for (int i3 = 0; i3 < childCount; i3++) {
                getChildAt(i3).getId();
            }
        }
        AnonymousClass2a4 r0 = this.mConstraintSet;
        if (r0 != null) {
            r0.A08(this);
        }
        ((AnonymousClass2b2) this.mLayoutWidget).A00.clear();
        int size = this.mConstraintHelpers.size();
        if (size > 0) {
            int i4 = 0;
            do {
                AnonymousClass2a3 r9 = this.mConstraintHelpers.get(i4);
                if (r9.isInEditMode()) {
                    r9.setIds(r9.A03);
                }
                AnonymousClass2aL r02 = r9.A02;
                if (r02 != null) {
                    r02.A97();
                    for (int i5 = 0; i5 < r9.A00; i5++) {
                        int i6 = r9.A05[i5];
                        View viewById = getViewById(i6);
                        if (viewById == null) {
                            String str = r9.A04.get(Integer.valueOf(i6));
                            int A00 = AnonymousClass2a3.A00(r9, this, str);
                            if (A00 != 0) {
                                r9.A05[i5] = A00;
                                r9.A04.put(Integer.valueOf(A00), str);
                                viewById = getViewById(A00);
                                if (viewById == null) {
                                }
                            }
                        }
                        r9.A02.A1C(getViewWidget(viewById));
                    }
                    r9.A02.AAu(this.mLayoutWidget);
                }
                i4++;
            } while (i4 < size);
        }
        for (int i7 = 0; i7 < childCount; i7++) {
            getChildAt(i7);
        }
        this.mTempMapIdToWidget.clear();
        SparseArray<AnonymousClass2ac> sparseArray = this.mTempMapIdToWidget;
        sparseArray.put(0, this.mLayoutWidget);
        sparseArray.put(getId(), this.mLayoutWidget);
        for (int i8 = 0; i8 < childCount; i8++) {
            View childAt2 = getChildAt(i8);
            this.mTempMapIdToWidget.put(childAt2.getId(), getViewWidget(childAt2));
        }
        for (int i9 = 0; i9 < childCount; i9++) {
            View childAt3 = getChildAt(i9);
            AnonymousClass2ac viewWidget2 = getViewWidget(childAt3);
            if (viewWidget2 != null) {
                AnonymousClass2a8 r14 = (AnonymousClass2a8) childAt3.getLayoutParams();
                C14932ab r1 = this.mLayoutWidget;
                ((AnonymousClass2b2) r1).A00.add(viewWidget2);
                AnonymousClass2ac r03 = viewWidget2.A0d;
                if (r03 != null) {
                    ((AnonymousClass2b2) r03).A00.remove(viewWidget2);
                    viewWidget2.A0A();
                }
                viewWidget2.A0d = r1;
                applyConstraintsFromLayoutParams(isInEditMode, childAt3, viewWidget2, r14, this.mTempMapIdToWidget);
            }
        }
    }

    /* JADX WARNING: Code restructure failed: missing block: B:177:0x034a, code lost:
        if (r5 == 6) goto L_0x034c;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:7:0x002d, code lost:
        if (r5 == 6) goto L_0x002f;
     */
    /* JADX WARNING: Removed duplicated region for block: B:105:0x01e5  */
    /* JADX WARNING: Removed duplicated region for block: B:111:0x0202  */
    /* JADX WARNING: Removed duplicated region for block: B:119:0x0250  */
    /* JADX WARNING: Removed duplicated region for block: B:11:0x0034  */
    /* JADX WARNING: Removed duplicated region for block: B:122:0x0258  */
    /* JADX WARNING: Removed duplicated region for block: B:123:0x025c  */
    /* JADX WARNING: Removed duplicated region for block: B:128:0x0273  */
    /* JADX WARNING: Removed duplicated region for block: B:133:0x028b A[ADDED_TO_REGION] */
    /* JADX WARNING: Removed duplicated region for block: B:93:0x01ad  */
    /* JADX WARNING: Removed duplicated region for block: B:99:0x01c8  */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public void applyConstraintsFromLayoutParams(boolean r21, android.view.View r22, X.AnonymousClass2ac r23, X.AnonymousClass2a8 r24, android.util.SparseArray<X.AnonymousClass2ac> r25) {
        /*
        // Method dump skipped, instructions count: 847
        */
        throw new UnsupportedOperationException("Method not decompiled: androidx.constraintlayout.widget.ConstraintLayout.applyConstraintsFromLayoutParams(boolean, android.view.View, X.2ac, X.2a8, android.util.SparseArray):void");
    }

    /* JADX WARNING: Removed duplicated region for block: B:28:? A[ORIG_RETURN, RETURN, SYNTHETIC] */
    /* JADX WARNING: Removed duplicated region for block: B:9:0x0022  */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public void dispatchDraw(android.graphics.Canvas r24) {
        /*
        // Method dump skipped, instructions count: 211
        */
        throw new UnsupportedOperationException("Method not decompiled: androidx.constraintlayout.widget.ConstraintLayout.dispatchDraw(android.graphics.Canvas):void");
    }

    public void fillMetrics(AnonymousClass2aY r2) {
        this.mMetrics = r2;
        this.mLayoutWidget.A06 = r2;
        C15022am.A0H = r2;
    }

    public Object getDesignInformation(int i, Object obj) {
        HashMap<String, Integer> hashMap;
        if (i != 0 || !(obj instanceof String) || (hashMap = this.mDesignIds) == null || !hashMap.containsKey(obj)) {
            return null;
        }
        return this.mDesignIds.get(obj);
    }

    public int getMaxHeight() {
        return this.mMaxHeight;
    }

    public int getMaxWidth() {
        return this.mMaxWidth;
    }

    public int getMinHeight() {
        return this.mMinHeight;
    }

    public int getMinWidth() {
        return this.mMinWidth;
    }

    public int getOptimizationLevel() {
        return this.mLayoutWidget.A01;
    }

    public View getViewById(int i) {
        return this.mChildrenByIds.get(i);
    }

    public final AnonymousClass2ac getViewWidget(View view) {
        if (view == this) {
            return this.mLayoutWidget;
        }
        if (view == null) {
            return null;
        }
        return ((AnonymousClass2a8) view.getLayoutParams()).A0w;
    }

    public void resolveMeasuredDimension(int i, int i2, int i3, int i4, boolean z, boolean z2) {
        AnonymousClass2aA r0 = this.mMeasurer;
        int i5 = r0.A04;
        int resolveSizeAndState = resolveSizeAndState(i3 + r0.A06, i, 0);
        int min = Math.min(this.mMaxWidth, resolveSizeAndState & 16777215);
        int min2 = Math.min(this.mMaxHeight, resolveSizeAndState(i4 + i5, i2, 0) & 16777215);
        if (z) {
            min |= 16777216;
        }
        if (z2) {
            min2 |= 16777216;
        }
        setMeasuredDimension(min, min2);
        this.mLastMeasureWidth = min;
        this.mLastMeasureHeight = min2;
    }

    public void setDesignInformation(int i, Object obj, Object obj2) {
        if (i == 0 && (obj instanceof String) && (obj2 instanceof Integer)) {
            if (this.mDesignIds == null) {
                this.mDesignIds = new HashMap<>();
            }
            String str = (String) obj;
            int indexOf = str.indexOf("/");
            if (indexOf != -1) {
                str = str.substring(indexOf + 1);
            }
            this.mDesignIds.put(str, Integer.valueOf(((Number) obj2).intValue()));
        }
    }

    public void setId(int i) {
        this.mChildrenByIds.remove(getId());
        super.setId(i);
        this.mChildrenByIds.put(getId(), this);
    }

    public void setMaxHeight(int i) {
        if (i != this.mMaxHeight) {
            this.mMaxHeight = i;
            requestLayout();
        }
    }

    public void setMaxWidth(int i) {
        if (i != this.mMaxWidth) {
            this.mMaxWidth = i;
            requestLayout();
        }
    }

    public void setMinHeight(int i) {
        if (i != this.mMinHeight) {
            this.mMinHeight = i;
            requestLayout();
        }
    }

    public void setMinWidth(int i) {
        if (i != this.mMinWidth) {
            this.mMinWidth = i;
            requestLayout();
        }
    }

    public void setOnConstraintsChanged(AnonymousClass2aZ r2) {
        this.mConstraintsChangedListener = r2;
        AnonymousClass2a5 r0 = this.mConstraintLayoutSpec;
        if (r0 != null) {
            r0.A04 = r2;
        }
    }

    public void setOptimizationLevel(int i) {
        this.mOptimizationLevel = i;
        this.mLayoutWidget.A01 = i;
        boolean z = false;
        if ((i & 512) == 512) {
            z = true;
        }
        C15022am.A0I = z;
    }

    /* JADX WARNING: Code restructure failed: missing block: B:23:0x0071, code lost:
        if (r6 == 0) goto L_0x0078;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:25:0x0076, code lost:
        if (r6 == 0) goto L_0x0078;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:26:0x0078, code lost:
        r14 = java.lang.Math.max(0, r9.mMinHeight);
     */
    /* JADX WARNING: Code restructure failed: missing block: B:29:0x0083, code lost:
        if (r6 == 0) goto L_0x008a;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:31:0x0088, code lost:
        if (r6 == 0) goto L_0x008a;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:32:0x008a, code lost:
        r12 = java.lang.Math.max(0, r9.mMinWidth);
     */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public void setSelfDimensionBehaviour(X.C14932ab r10, int r11, int r12, int r13, int r14) {
        /*
        // Method dump skipped, instructions count: 147
        */
        throw new UnsupportedOperationException("Method not decompiled: androidx.constraintlayout.widget.ConstraintLayout.setSelfDimensionBehaviour(X.2ab, int, int, int, int):void");
    }

    /* JADX WARNING: Code restructure failed: missing block: B:27:0x006b, code lost:
        if (r2 == -1) goto L_0x006d;
     */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public void setState(int r10, int r11, int r12) {
        /*
        // Method dump skipped, instructions count: 144
        */
        throw new UnsupportedOperationException("Method not decompiled: androidx.constraintlayout.widget.ConstraintLayout.setState(int, int, int):void");
    }

    private int getPaddingWidth() {
        int max = Math.max(0, getPaddingLeft()) + Math.max(0, getPaddingRight());
        int max2 = Math.max(0, getPaddingEnd()) + Math.max(0, getPaddingStart());
        if (max2 > 0) {
            return max2;
        }
        return max;
    }

    private boolean updateHierarchy() {
        int childCount = getChildCount();
        for (int i = 0; i < childCount; i++) {
            if (getChildAt(i).isLayoutRequested()) {
                setChildrenConstraints();
                return true;
            }
        }
        return false;
    }

    public void forceLayout() {
        markHierarchyDirty();
        super.forceLayout();
    }

    public void onLayout(boolean z, int i, int i2, int i3, int i4) {
        int childCount = getChildCount();
        boolean isInEditMode = isInEditMode();
        int i5 = 0;
        for (int i6 = 0; i6 < childCount; i6++) {
            View childAt = getChildAt(i6);
            AnonymousClass2a8 r2 = (AnonymousClass2a8) childAt.getLayoutParams();
            AnonymousClass2ac r5 = r2.A0w;
            if (childAt.getVisibility() != 8 || r2.A0C || r2.A0D || isInEditMode) {
                int A05 = r5.A05();
                int A06 = r5.A06();
                childAt.layout(A05, A06, r5.A04() + A05, r5.A03() + A06);
            }
        }
        int size = this.mConstraintHelpers.size();
        if (size > 0) {
            do {
                AnonymousClass2a3 r1 = this.mConstraintHelpers.get(i5);
                if (r1 instanceof Group) {
                    AnonymousClass2ac r12 = ((AnonymousClass2a8) ((Group) r1).getLayoutParams()).A0w;
                    r12.A0E(0);
                    r12.A0D(0);
                }
                i5++;
            } while (i5 < size);
        }
    }

    public void onViewAdded(View view) {
        super.onViewAdded(view);
        AnonymousClass2ac viewWidget = getViewWidget(view);
        if ((view instanceof Guideline) && !(viewWidget instanceof C15002ak)) {
            AnonymousClass2a8 r0 = (AnonymousClass2a8) view.getLayoutParams();
            C15002ak r1 = new C15002ak();
            r0.A0w = r1;
            r0.A0C = true;
            r1.A0V(r0.A0o);
        }
        if (view instanceof AnonymousClass2a3) {
            AnonymousClass2a3 r12 = (AnonymousClass2a3) view;
            r12.A04();
            ((AnonymousClass2a8) view.getLayoutParams()).A0D = true;
            if (!this.mConstraintHelpers.contains(r12)) {
                this.mConstraintHelpers.add(r12);
            }
        }
        this.mChildrenByIds.put(view.getId(), view);
        this.mDirtyHierarchy = true;
    }

    public void onViewRemoved(View view) {
        super.onViewRemoved(view);
        this.mChildrenByIds.remove(view.getId());
        AnonymousClass2ac viewWidget = getViewWidget(view);
        ((AnonymousClass2b2) this.mLayoutWidget).A00.remove(viewWidget);
        viewWidget.A0A();
        this.mConstraintHelpers.remove(view);
        this.mDirtyHierarchy = true;
    }

    public void parseLayoutDescription(int i) {
        this.mConstraintLayoutSpec = new AnonymousClass2a5(getContext(), this, i);
    }

    public void requestLayout() {
        markHierarchyDirty();
        super.requestLayout();
    }

    public boolean checkLayoutParams(ViewGroup.LayoutParams layoutParams) {
        return layoutParams instanceof AnonymousClass2a8;
    }

    public void setConstraintSet(AnonymousClass2a4 r1) {
        this.mConstraintSet = r1;
    }

    public ConstraintLayout(@NonNull Context context) {
        super(context);
        init(null, 0, 0);
    }

    public ConstraintLayout(@NonNull Context context, @Nullable AttributeSet attributeSet) {
        super(context, attributeSet);
        init(attributeSet, 0, 0);
    }

    public ConstraintLayout(@NonNull Context context, @Nullable AttributeSet attributeSet, int i) {
        super(context, attributeSet, i);
        init(attributeSet, i, 0);
    }

    @TargetApi(21)
    public ConstraintLayout(@NonNull Context context, @Nullable AttributeSet attributeSet, int i, int i2) {
        super(context, attributeSet, i, i2);
        init(attributeSet, i, i2);
    }

    public AnonymousClass2a8 generateDefaultLayoutParams() {
        return new AnonymousClass2a8(-2, -2);
    }

    @Override // android.view.ViewGroup
    public ViewGroup.LayoutParams generateLayoutParams(ViewGroup.LayoutParams layoutParams) {
        return new AnonymousClass2a8(layoutParams);
    }

    @Override // android.view.ViewGroup
    public AnonymousClass2a8 generateLayoutParams(AttributeSet attributeSet) {
        return new AnonymousClass2a8(getContext(), attributeSet);
    }

    /* JADX WARNING: Code restructure failed: missing block: B:197:0x03e5, code lost:
        if (r12 != r1) goto L_0x03ef;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:22:0x00ba, code lost:
        if (r2.A01 <= com.oculus.vrshell.panels.AndroidPanelLayer.Spec.DEFAULT_CYLINDER_POSITION_Z) goto L_0x00bc;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:32:0x00ce, code lost:
        if (r15 != false) goto L_0x00d0;
     */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public void resolveSystem(X.C14932ab r26, int r27, int r28, int r29) {
        /*
        // Method dump skipped, instructions count: 1408
        */
        throw new UnsupportedOperationException("Method not decompiled: androidx.constraintlayout.widget.ConstraintLayout.resolveSystem(X.2ab, int, int, int):void");
    }
}
