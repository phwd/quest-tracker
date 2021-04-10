package X;

import android.content.Context;
import android.content.res.Resources;
import android.content.res.TypedArray;
import android.graphics.Canvas;
import android.util.AttributeSet;
import android.util.Log;
import android.view.View;
import android.view.ViewGroup;
import android.view.ViewParent;
import androidx.constraintlayout.widget.ConstraintLayout;
import com.oculus.vrshell.panels.AndroidPanelLayer;
import java.util.Arrays;
import java.util.HashMap;

/* renamed from: X.2a3  reason: invalid class name */
public abstract class AnonymousClass2a3 extends View {
    public int A00;
    public Context A01;
    public AnonymousClass2aL A02;
    public String A03;
    public HashMap<Integer, String> A04;
    public int[] A05;
    public String A06;

    public static int A00(AnonymousClass2a3 r7, ConstraintLayout constraintLayout, String str) {
        Resources resources;
        if (!(str == null || (resources = r7.A01.getResources()) == null)) {
            int childCount = constraintLayout.getChildCount();
            for (int i = 0; i < childCount; i++) {
                View childAt = constraintLayout.getChildAt(i);
                if (childAt.getId() != -1) {
                    String str2 = null;
                    try {
                        str2 = resources.getResourceEntryName(childAt.getId());
                    } catch (Resources.NotFoundException unused) {
                    }
                    if (str.equals(str2)) {
                        return childAt.getId();
                    }
                }
            }
        }
        return 0;
    }

    public final void onDraw(Canvas canvas) {
    }

    public final void onMeasure(int i, int i2) {
        setMeasuredDimension(0, 0);
    }

    public void setReferencedIds(int[] iArr) {
        this.A03 = null;
        this.A00 = 0;
        for (int i : iArr) {
            A01(i);
        }
    }

    /* JADX WARNING: Code restructure failed: missing block: B:17:0x0041, code lost:
        if (r2 != 0) goto L_0x007d;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:19:0x0047, code lost:
        if (r2 == 0) goto L_0x004f;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:20:0x004a, code lost:
        if (r4 != null) goto L_0x0043;
     */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    private void A02(java.lang.String r7) {
        /*
        // Method dump skipped, instructions count: 138
        */
        throw new UnsupportedOperationException("Method not decompiled: X.AnonymousClass2a3.A02(java.lang.String):void");
    }

    private void A03(String str) {
        if (!(str == null || str.length() == 0 || this.A01 == null)) {
            String trim = str.trim();
            ConstraintLayout constraintLayout = null;
            if (getParent() instanceof ConstraintLayout) {
                constraintLayout = (ConstraintLayout) getParent();
            }
            if (constraintLayout == null) {
                Log.w("ConstraintHelper", "Parent not a ConstraintLayout");
                return;
            }
            int childCount = constraintLayout.getChildCount();
            for (int i = 0; i < childCount; i++) {
                View childAt = constraintLayout.getChildAt(i);
                ViewGroup.LayoutParams layoutParams = childAt.getLayoutParams();
                if ((layoutParams instanceof AnonymousClass2a8) && trim.equals(((AnonymousClass2a8) layoutParams).A0x)) {
                    if (childAt.getId() == -1) {
                        Log.w("ConstraintHelper", AnonymousClass006.A09("to use ConstraintTag view ", childAt.getClass().getSimpleName(), " must have an ID"));
                    } else {
                        A01(childAt.getId());
                    }
                }
            }
        }
    }

    public final void A04() {
        if (this.A02 != null) {
            ViewGroup.LayoutParams layoutParams = getLayoutParams();
            if (layoutParams instanceof AnonymousClass2a8) {
                ((AnonymousClass2a8) layoutParams).A0w = (AnonymousClass2ac) this.A02;
            }
        }
    }

    public void A06(AttributeSet attributeSet) {
        if (attributeSet != null) {
            TypedArray obtainStyledAttributes = getContext().obtainStyledAttributes(attributeSet, AnonymousClass2aI.A01);
            int indexCount = obtainStyledAttributes.getIndexCount();
            for (int i = 0; i < indexCount; i++) {
                int index = obtainStyledAttributes.getIndex(i);
                if (index == 19) {
                    String string = obtainStyledAttributes.getString(index);
                    this.A03 = string;
                    setIds(string);
                } else if (index == 20) {
                    String string2 = obtainStyledAttributes.getString(index);
                    this.A06 = string2;
                    setReferenceTags(string2);
                }
            }
            obtainStyledAttributes.recycle();
        }
    }

    public int[] getReferencedIds() {
        return Arrays.copyOf(this.A05, this.A00);
    }

    public void setIds(String str) {
        this.A03 = str;
        if (str != null) {
            int i = 0;
            this.A00 = 0;
            while (true) {
                int indexOf = str.indexOf(44, i);
                if (indexOf == -1) {
                    A02(str.substring(i));
                    return;
                } else {
                    A02(str.substring(i, indexOf));
                    i = indexOf + 1;
                }
            }
        }
    }

    public void setReferenceTags(String str) {
        this.A06 = str;
        if (str != null) {
            int i = 0;
            this.A00 = 0;
            while (true) {
                int indexOf = str.indexOf(44, i);
                if (indexOf == -1) {
                    A03(str.substring(i));
                    return;
                } else {
                    A03(str.substring(i, indexOf));
                    i = indexOf + 1;
                }
            }
        }
    }

    private void A01(int i) {
        if (i != getId()) {
            int i2 = this.A00 + 1;
            int[] iArr = this.A05;
            int length = iArr.length;
            if (i2 > length) {
                iArr = Arrays.copyOf(iArr, length << 1);
                this.A05 = iArr;
            }
            int i3 = this.A00;
            iArr[i3] = i;
            this.A00 = i3 + 1;
        }
    }

    public final void A05() {
        ViewParent parent = getParent();
        if (parent != null && (parent instanceof ConstraintLayout)) {
            ConstraintLayout constraintLayout = (ConstraintLayout) parent;
            int visibility = getVisibility();
            float elevation = getElevation();
            for (int i = 0; i < this.A00; i++) {
                View viewById = constraintLayout.getViewById(this.A05[i]);
                if (viewById != null) {
                    viewById.setVisibility(visibility);
                    if (elevation > AndroidPanelLayer.Spec.DEFAULT_CYLINDER_POSITION_Z) {
                        viewById.setTranslationZ(viewById.getTranslationZ() + elevation);
                    }
                }
            }
        }
    }

    public void onAttachedToWindow() {
        super.onAttachedToWindow();
        String str = this.A03;
        if (str != null) {
            setIds(str);
        }
        String str2 = this.A06;
        if (str2 != null) {
            setReferenceTags(str2);
        }
    }

    public final void setTag(int i, Object obj) {
        super.setTag(i, obj);
        if (obj == null && this.A03 == null) {
            A01(i);
        }
    }

    public AnonymousClass2a3(Context context) {
        super(context);
        this.A05 = new int[32];
        this.A04 = new HashMap<>();
        this.A01 = context;
        A06(null);
    }

    public AnonymousClass2a3(Context context, AttributeSet attributeSet) {
        super(context, attributeSet);
        this.A05 = new int[32];
        this.A04 = new HashMap<>();
        this.A01 = context;
        A06(attributeSet);
    }

    public AnonymousClass2a3(Context context, AttributeSet attributeSet, int i) {
        super(context, attributeSet, i);
        this.A05 = new int[32];
        this.A04 = new HashMap<>();
        this.A01 = context;
        A06(attributeSet);
    }
}
