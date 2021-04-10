package X;

import android.os.Build;
import android.os.Bundle;
import android.text.Spanned;
import android.text.style.ClickableSpan;
import android.util.SparseArray;
import android.view.View;
import android.view.accessibility.AccessibilityNodeInfo;
import androidx.annotation.Nullable;
import androidx.annotation.RestrictTo;
import com.oculus.alpenglow.R;
import java.lang.ref.WeakReference;
import java.util.ArrayList;
import java.util.List;

/* renamed from: X.0BK  reason: invalid class name */
public final class AnonymousClass0BK {
    public static int A01;
    public final AccessibilityNodeInfo A00;

    public final boolean equals(Object obj) {
        if (this != obj) {
            if (obj != null && (obj instanceof AnonymousClass0BK)) {
                AnonymousClass0BK r5 = (AnonymousClass0BK) obj;
                AccessibilityNodeInfo accessibilityNodeInfo = this.A00;
                if (accessibilityNodeInfo == null) {
                    if (r5.A00 != null) {
                        return false;
                    }
                } else if (!accessibilityNodeInfo.equals(r5.A00)) {
                    return false;
                }
            }
            return false;
        }
        return true;
    }

    private List<Integer> A00(String str) {
        AccessibilityNodeInfo accessibilityNodeInfo = this.A00;
        ArrayList<Integer> integerArrayList = accessibilityNodeInfo.getExtras().getIntegerArrayList(str);
        if (integerArrayList != null) {
            return integerArrayList;
        }
        ArrayList<Integer> arrayList = new ArrayList<>();
        accessibilityNodeInfo.getExtras().putIntegerArrayList(str, arrayList);
        return arrayList;
    }

    public final void A01(AnonymousClass0BF r3) {
        this.A00.addAction((AccessibilityNodeInfo.AccessibilityAction) r3.A00);
    }

    public final void A02(@Nullable CharSequence charSequence) {
        if (Build.VERSION.SDK_INT >= 28) {
            this.A00.setPaneTitle(charSequence);
        } else {
            this.A00.getExtras().putCharSequence("androidx.view.accessibility.AccessibilityNodeInfoCompat.PANE_TITLE_KEY", charSequence);
        }
    }

    @RestrictTo({AnonymousClass02D.LIBRARY_GROUP_PREFIX})
    public final void A03(CharSequence charSequence, View view) {
        int length;
        int i;
        if (Build.VERSION.SDK_INT < 26) {
            AccessibilityNodeInfo accessibilityNodeInfo = this.A00;
            accessibilityNodeInfo.getExtras().remove("androidx.view.accessibility.AccessibilityNodeInfoCompat.SPANS_START_KEY");
            accessibilityNodeInfo.getExtras().remove("androidx.view.accessibility.AccessibilityNodeInfoCompat.SPANS_END_KEY");
            accessibilityNodeInfo.getExtras().remove("androidx.view.accessibility.AccessibilityNodeInfoCompat.SPANS_FLAGS_KEY");
            accessibilityNodeInfo.getExtras().remove("androidx.view.accessibility.AccessibilityNodeInfoCompat.SPANS_ID_KEY");
            SparseArray sparseArray = (SparseArray) view.getTag(R.id.tag_accessibility_clickable_spans);
            if (sparseArray != null) {
                ArrayList arrayList = new ArrayList();
                for (int i2 = 0; i2 < sparseArray.size(); i2++) {
                    if (((WeakReference) sparseArray.valueAt(i2)).get() == null) {
                        arrayList.add(Integer.valueOf(i2));
                    }
                }
                for (int i3 = 0; i3 < arrayList.size(); i3++) {
                    sparseArray.remove(((Integer) arrayList.get(i3)).intValue());
                }
            }
            if (charSequence instanceof Spanned) {
                Spanned spanned = (Spanned) charSequence;
                ClickableSpan[] clickableSpanArr = (ClickableSpan[]) spanned.getSpans(0, charSequence.length(), ClickableSpan.class);
                if (clickableSpanArr != null && (length = clickableSpanArr.length) > 0) {
                    accessibilityNodeInfo.getExtras().putInt("androidx.view.accessibility.AccessibilityNodeInfoCompat.SPANS_ACTION_ID_KEY", R.id.accessibility_action_clickable_span);
                    SparseArray sparseArray2 = (SparseArray) view.getTag(R.id.tag_accessibility_clickable_spans);
                    if (sparseArray2 == null) {
                        sparseArray2 = new SparseArray();
                        view.setTag(R.id.tag_accessibility_clickable_spans, sparseArray2);
                    }
                    for (int i4 = 0; i4 < length; i4++) {
                        ClickableSpan clickableSpan = clickableSpanArr[i4];
                        int i5 = 0;
                        while (true) {
                            if (i5 >= sparseArray2.size()) {
                                i = A01;
                                A01 = i + 1;
                                break;
                            } else if (clickableSpan.equals(((WeakReference) sparseArray2.valueAt(i5)).get())) {
                                i = sparseArray2.keyAt(i5);
                                break;
                            } else {
                                i5++;
                            }
                        }
                        sparseArray2.put(i, new WeakReference(clickableSpanArr[i4]));
                        ClickableSpan clickableSpan2 = clickableSpanArr[i4];
                        A00("androidx.view.accessibility.AccessibilityNodeInfoCompat.SPANS_START_KEY").add(Integer.valueOf(spanned.getSpanStart(clickableSpan2)));
                        A00("androidx.view.accessibility.AccessibilityNodeInfoCompat.SPANS_END_KEY").add(Integer.valueOf(spanned.getSpanEnd(clickableSpan2)));
                        A00("androidx.view.accessibility.AccessibilityNodeInfoCompat.SPANS_FLAGS_KEY").add(Integer.valueOf(spanned.getSpanFlags(clickableSpan2)));
                        A00("androidx.view.accessibility.AccessibilityNodeInfoCompat.SPANS_ID_KEY").add(Integer.valueOf(i));
                    }
                }
            }
        }
    }

    public final void A04(boolean z) {
        if (Build.VERSION.SDK_INT >= 28) {
            this.A00.setHeading(z);
            return;
        }
        int i = 2;
        Bundle extras = this.A00.getExtras();
        if (extras != null) {
            int i2 = extras.getInt("androidx.view.accessibility.AccessibilityNodeInfoCompat.BOOLEAN_PROPERTY_KEY", 0) & -3;
            if (!z) {
                i = 0;
            }
            extras.putInt("androidx.view.accessibility.AccessibilityNodeInfoCompat.BOOLEAN_PROPERTY_KEY", i | i2);
        }
    }

    public final void A05(boolean z) {
        if (Build.VERSION.SDK_INT >= 28) {
            this.A00.setScreenReaderFocusable(z);
            return;
        }
        int i = 1;
        Bundle extras = this.A00.getExtras();
        if (extras != null) {
            int i2 = extras.getInt("androidx.view.accessibility.AccessibilityNodeInfoCompat.BOOLEAN_PROPERTY_KEY", 0) & -2;
            if (!z) {
                i = 0;
            }
            extras.putInt("androidx.view.accessibility.AccessibilityNodeInfoCompat.BOOLEAN_PROPERTY_KEY", i | i2);
        }
    }

    public final int hashCode() {
        AccessibilityNodeInfo accessibilityNodeInfo = this.A00;
        if (accessibilityNodeInfo == null) {
            return 0;
        }
        return accessibilityNodeInfo.hashCode();
    }

    /* JADX DEBUG: Failed to insert an additional move for type inference into block B:71:0x00d9 */
    /* JADX WARN: Multi-variable type inference failed */
    /* JADX WARN: Type inference failed for: r5v0, types: [java.lang.StringBuilder] */
    /* JADX WARN: Type inference failed for: r6v0, types: [java.lang.CharSequence] */
    /* JADX WARN: Type inference failed for: r6v2, types: [java.lang.CharSequence] */
    /* JADX WARN: Type inference failed for: r6v3, types: [android.text.SpannableString, android.text.Spannable] */
    /* JADX WARNING: Unknown variable types count: 2 */
    @androidx.annotation.NonNull
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public final java.lang.String toString() {
        /*
        // Method dump skipped, instructions count: 748
        */
        throw new UnsupportedOperationException("Method not decompiled: X.AnonymousClass0BK.toString():java.lang.String");
    }

    public AnonymousClass0BK(AccessibilityNodeInfo accessibilityNodeInfo) {
        this.A00 = accessibilityNodeInfo;
    }
}
