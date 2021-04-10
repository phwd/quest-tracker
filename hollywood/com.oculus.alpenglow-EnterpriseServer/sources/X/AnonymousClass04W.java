package X;

import android.content.Context;
import android.content.res.Resources;
import android.content.res.TypedArray;
import android.graphics.RectF;
import android.os.Build;
import android.text.Layout;
import android.text.StaticLayout;
import android.text.TextPaint;
import android.text.method.TransformationMethod;
import android.util.AttributeSet;
import android.util.DisplayMetrics;
import android.util.Log;
import android.util.TypedValue;
import android.widget.TextView;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.annotation.RestrictTo;
import com.oculus.appmanager.info.ApkUpdateInfoContract;
import java.lang.reflect.Field;
import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.concurrent.ConcurrentHashMap;

/* renamed from: X.04W  reason: invalid class name */
public final class AnonymousClass04W {
    public static ConcurrentHashMap<String, Field> A0B = new ConcurrentHashMap<>();
    public static ConcurrentHashMap<String, Method> A0C = new ConcurrentHashMap<>();
    public static final RectF A0D = new RectF();
    public float A00 = -1.0f;
    public float A01 = -1.0f;
    public float A02 = -1.0f;
    public int A03 = 0;
    public TextPaint A04;
    public boolean A05 = false;
    public boolean A06 = false;
    public int[] A07 = new int[0];
    public final Context A08;
    @NonNull
    public final TextView A09;
    public final AnonymousClass04V A0A;

    public static <T> T A00(@NonNull Object obj, @NonNull String str, @NonNull T t) {
        try {
            return (T) A01(str).invoke(obj, new Object[0]);
        } catch (Exception e) {
            Log.w("ACTVAutoSizeHelper", AnonymousClass006.A07("Failed to invoke TextView#", str, "() method"), e);
            return t;
        }
    }

    public static int[] A05(int[] iArr) {
        int length = iArr.length;
        if (length != 0) {
            Arrays.sort(iArr);
            ArrayList arrayList = new ArrayList();
            int i = 0;
            do {
                int i2 = iArr[i];
                if (i2 > 0 && Collections.binarySearch(arrayList, Integer.valueOf(i2)) < 0) {
                    arrayList.add(Integer.valueOf(i2));
                }
                i++;
            } while (i < length);
            if (length != arrayList.size()) {
                int size = arrayList.size();
                int[] iArr2 = new int[size];
                for (int i3 = 0; i3 < size; i3++) {
                    iArr2[i3] = ((Integer) arrayList.get(i3)).intValue();
                }
                return iArr2;
            }
        }
        return iArr;
    }

    @Nullable
    public static Method A01(@NonNull String str) {
        try {
            Method method = A0C.get(str);
            if (method == null && (method = TextView.class.getDeclaredMethod(str, new Class[0])) != null) {
                method.setAccessible(true);
                A0C.put(str, method);
            }
            return method;
        } catch (Exception e) {
            Log.w("ACTVAutoSizeHelper", AnonymousClass006.A07("Failed to retrieve TextView#", str, "() method"), e);
            return null;
        }
    }

    public static void A02(AnonymousClass04W r3, float f, float f2, float f3) throws IllegalArgumentException {
        String str;
        StringBuilder sb;
        if (f <= 0.0f) {
            sb = new StringBuilder("Minimum auto-size text size (");
            sb.append(f);
        } else if (f2 <= f) {
            str = "Maximum auto-size text size (" + f2 + "px) is less or equal to minimum auto-size text size (" + f + "px)";
            throw new IllegalArgumentException(str);
        } else if (f3 > 0.0f) {
            r3.A03 = 1;
            r3.A01 = f;
            r3.A00 = f2;
            r3.A02 = f3;
            r3.A05 = false;
            return;
        } else {
            sb = new StringBuilder("The auto-size step granularity (");
            sb.append(f3);
        }
        sb.append("px) is less or equal to (0px)");
        str = sb.toString();
        throw new IllegalArgumentException(str);
    }

    public static boolean A03(AnonymousClass04W r7) {
        boolean z = false;
        if (!(r7.A09 instanceof C04170eC)) {
            z = true;
        }
        if (!z || r7.A03 != 1) {
            r7.A06 = false;
            return false;
        }
        if (!r7.A05 || r7.A07.length == 0) {
            float f = r7.A00;
            float f2 = r7.A01;
            float f3 = r7.A02;
            int floor = ((int) Math.floor((double) ((f - f2) / f3))) + 1;
            int[] iArr = new int[floor];
            for (int i = 0; i < floor; i++) {
                iArr[i] = Math.round((((float) i) * f3) + f2);
            }
            r7.A07 = A05(iArr);
        }
        r7.A06 = true;
        return true;
    }

    public static boolean A04(AnonymousClass04W r5) {
        int[] iArr = r5.A07;
        int length = iArr.length;
        boolean z = false;
        if (length > 0) {
            z = true;
        }
        r5.A05 = z;
        if (z) {
            r5.A03 = 1;
            r5.A01 = (float) iArr[0];
            r5.A00 = (float) iArr[length - 1];
            r5.A02 = -1.0f;
        }
        return z;
    }

    @RestrictTo({AnonymousClass02D.LIBRARY_GROUP_PREFIX})
    public final void A06() {
        int measuredWidth;
        CharSequence transformation;
        TextView textView = this.A09;
        if (!(textView instanceof C04170eC) && this.A03 != 0) {
            if (this.A06) {
                if (textView.getMeasuredHeight() > 0 && textView.getMeasuredWidth() > 0) {
                    AnonymousClass04V r6 = this.A0A;
                    if (r6.A01(textView)) {
                        measuredWidth = ApkUpdateInfoContract.UPDATE_TYPE_FULL;
                    } else {
                        measuredWidth = (textView.getMeasuredWidth() - textView.getTotalPaddingLeft()) - textView.getTotalPaddingRight();
                    }
                    int height = (textView.getHeight() - textView.getCompoundPaddingBottom()) - textView.getCompoundPaddingTop();
                    if (measuredWidth > 0 && height > 0) {
                        RectF rectF = A0D;
                        synchronized (rectF) {
                            rectF.setEmpty();
                            rectF.right = (float) measuredWidth;
                            rectF.bottom = (float) height;
                            int length = this.A07.length;
                            if (length != 0) {
                                int i = length - 1;
                                int i2 = 1;
                                int i3 = 0;
                                while (i2 <= i) {
                                    int i4 = (i2 + i) >> 1;
                                    int i5 = this.A07[i4];
                                    CharSequence text = textView.getText();
                                    TransformationMethod transformationMethod = textView.getTransformationMethod();
                                    if (!(transformationMethod == null || (transformation = transformationMethod.getTransformation(text, textView)) == null)) {
                                        text = transformation;
                                    }
                                    int maxLines = textView.getMaxLines();
                                    TextPaint textPaint = this.A04;
                                    if (textPaint == null) {
                                        this.A04 = new TextPaint();
                                    } else {
                                        textPaint.reset();
                                    }
                                    this.A04.set(textView.getPaint());
                                    this.A04.setTextSize((float) i5);
                                    int i6 = maxLines;
                                    StaticLayout.Builder obtain = StaticLayout.Builder.obtain(text, 0, text.length(), this.A04, Math.round(rectF.right));
                                    StaticLayout.Builder hyphenationFrequency = obtain.setAlignment((Layout.Alignment) A00(textView, "getLayoutAlignment", Layout.Alignment.ALIGN_NORMAL)).setLineSpacing(textView.getLineSpacingExtra(), textView.getLineSpacingMultiplier()).setIncludePad(textView.getIncludeFontPadding()).setBreakStrategy(textView.getBreakStrategy()).setHyphenationFrequency(textView.getHyphenationFrequency());
                                    if (maxLines == -1) {
                                        i6 = Integer.MAX_VALUE;
                                    }
                                    hyphenationFrequency.setMaxLines(i6);
                                    try {
                                        r6.A00(obtain, textView);
                                    } catch (ClassCastException unused) {
                                        Log.w("ACTVAutoSizeHelper", "Failed to obtain TextDirectionHeuristic, auto size may be incorrect");
                                    }
                                    StaticLayout build = obtain.build();
                                    if ((maxLines == -1 || (build.getLineCount() <= maxLines && build.getLineEnd(build.getLineCount() - 1) == text.length())) && ((float) build.getHeight()) <= rectF.bottom) {
                                        i3 = i2;
                                        i2 = i4 + 1;
                                    } else {
                                        i3 = i4 - 1;
                                        i = i3;
                                    }
                                }
                                float f = (float) this.A07[i3];
                                if (f != textView.getTextSize()) {
                                    A07(0, f);
                                }
                            } else {
                                throw new IllegalStateException("No available text sizes to choose from.");
                            }
                        }
                    } else {
                        return;
                    }
                } else {
                    return;
                }
            }
            this.A06 = true;
        }
    }

    @RestrictTo({AnonymousClass02D.LIBRARY_GROUP_PREFIX})
    public final void A07(int i, float f) {
        Resources resources;
        Context context = this.A08;
        if (context == null) {
            resources = Resources.getSystem();
        } else {
            resources = context.getResources();
        }
        float applyDimension = TypedValue.applyDimension(i, f, resources.getDisplayMetrics());
        TextView textView = this.A09;
        if (applyDimension != textView.getPaint().getTextSize()) {
            textView.getPaint().setTextSize(applyDimension);
            boolean isInLayout = textView.isInLayout();
            if (textView.getLayout() != null) {
                this.A06 = false;
                try {
                    Method A012 = A01("nullLayouts");
                    if (A012 != null) {
                        A012.invoke(textView, new Object[0]);
                    }
                } catch (Exception e) {
                    Log.w("ACTVAutoSizeHelper", "Failed to invoke TextView#nullLayouts() method", e);
                }
                if (!isInLayout) {
                    textView.requestLayout();
                } else {
                    textView.forceLayout();
                }
                textView.invalidate();
            }
        }
    }

    public final void A08(@Nullable AttributeSet attributeSet, int i) {
        int resourceId;
        Context context = this.A08;
        int[] iArr = AnonymousClass18N.A08;
        TypedArray obtainStyledAttributes = context.obtainStyledAttributes(attributeSet, iArr, i, 0);
        TextView textView = this.A09;
        AnonymousClass0Aw.A01(textView, textView.getContext(), iArr, attributeSet, obtainStyledAttributes, i);
        if (obtainStyledAttributes.hasValue(5)) {
            this.A03 = obtainStyledAttributes.getInt(5, 0);
        }
        float f = -1.0f;
        if (obtainStyledAttributes.hasValue(4)) {
            f = obtainStyledAttributes.getDimension(4, -1.0f);
        }
        float f2 = -1.0f;
        if (obtainStyledAttributes.hasValue(2)) {
            f2 = obtainStyledAttributes.getDimension(2, -1.0f);
        }
        float f3 = -1.0f;
        if (obtainStyledAttributes.hasValue(1)) {
            f3 = obtainStyledAttributes.getDimension(1, -1.0f);
        }
        if (obtainStyledAttributes.hasValue(3) && (resourceId = obtainStyledAttributes.getResourceId(3, 0)) > 0) {
            TypedArray obtainTypedArray = obtainStyledAttributes.getResources().obtainTypedArray(resourceId);
            int length = obtainTypedArray.length();
            int[] iArr2 = new int[length];
            if (length > 0) {
                int i2 = 0;
                do {
                    iArr2[i2] = obtainTypedArray.getDimensionPixelSize(i2, -1);
                    i2++;
                } while (i2 < length);
                this.A07 = A05(iArr2);
                A04(this);
            }
            obtainTypedArray.recycle();
        }
        obtainStyledAttributes.recycle();
        if (textView instanceof C04170eC) {
            this.A03 = 0;
        } else if (this.A03 == 1) {
            if (!this.A05) {
                DisplayMetrics displayMetrics = context.getResources().getDisplayMetrics();
                if (f2 == -1.0f) {
                    f2 = TypedValue.applyDimension(2, 12.0f, displayMetrics);
                }
                if (f3 == -1.0f) {
                    f3 = TypedValue.applyDimension(2, 112.0f, displayMetrics);
                }
                if (f == -1.0f) {
                    f = 1.0f;
                }
                A02(this, f2, f3, f);
            }
            A03(this);
        }
    }

    public AnonymousClass04W(@NonNull TextView textView) {
        AnonymousClass04V r0;
        this.A09 = textView;
        this.A08 = textView.getContext();
        if (Build.VERSION.SDK_INT >= 29) {
            r0 = new AnonymousClass0Mj();
        } else {
            r0 = new AnonymousClass0e1();
        }
        this.A0A = r0;
    }
}
