package defpackage;

import android.content.Context;
import android.content.res.Resources;
import android.graphics.RectF;
import android.os.Build;
import android.text.Layout;
import android.text.StaticLayout;
import android.text.TextPaint;
import android.text.method.TransformationMethod;
import android.util.Log;
import android.util.TypedValue;
import android.widget.TextView;
import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.concurrent.ConcurrentHashMap;

/* renamed from: R8  reason: default package */
/* compiled from: chromium-OculusBrowser.apk-stable-281887347 */
public class R8 {

    /* renamed from: a  reason: collision with root package name */
    public static final RectF f8812a = new RectF();
    public static ConcurrentHashMap b = new ConcurrentHashMap();
    public static ConcurrentHashMap c = new ConcurrentHashMap();
    public int d = 0;
    public boolean e = false;
    public float f = -1.0f;
    public float g = -1.0f;
    public float h = -1.0f;
    public int[] i = new int[0];
    public boolean j = false;
    public TextPaint k;
    public final TextView l;
    public final Context m;
    public final Q8 n;

    public R8(TextView textView) {
        this.l = textView;
        this.m = textView.getContext();
        if (Build.VERSION.SDK_INT >= 29) {
            this.n = new P8();
        } else {
            this.n = new O8();
        }
    }

    public static Method d(String str) {
        try {
            Method method = (Method) b.get(str);
            if (method == null && (method = TextView.class.getDeclaredMethod(str, new Class[0])) != null) {
                method.setAccessible(true);
                b.put(str, method);
            }
            return method;
        } catch (Exception e2) {
            Log.w("ACTVAutoSizeHelper", "Failed to retrieve TextView#" + str + "() method", e2);
            return null;
        }
    }

    public static Object e(Object obj, String str, Object obj2) {
        try {
            return d(str).invoke(obj, new Object[0]);
        } catch (Exception e2) {
            Log.w("ACTVAutoSizeHelper", "Failed to invoke TextView#" + str + "() method", e2);
            return obj2;
        }
    }

    public void a() {
        int i2;
        if (i() && this.d != 0) {
            if (this.e) {
                if (this.l.getMeasuredHeight() > 0 && this.l.getMeasuredWidth() > 0) {
                    if (this.n.b(this.l)) {
                        i2 = 1048576;
                    } else {
                        i2 = (this.l.getMeasuredWidth() - this.l.getTotalPaddingLeft()) - this.l.getTotalPaddingRight();
                    }
                    int height = (this.l.getHeight() - this.l.getCompoundPaddingBottom()) - this.l.getCompoundPaddingTop();
                    if (i2 > 0 && height > 0) {
                        RectF rectF = f8812a;
                        synchronized (rectF) {
                            rectF.setEmpty();
                            rectF.right = (float) i2;
                            rectF.bottom = (float) height;
                            float c2 = (float) c(rectF);
                            if (c2 != this.l.getTextSize()) {
                                f(0, c2);
                            }
                        }
                    } else {
                        return;
                    }
                } else {
                    return;
                }
            }
            this.e = true;
        }
    }

    public final int[] b(int[] iArr) {
        int length = iArr.length;
        if (length == 0) {
            return iArr;
        }
        Arrays.sort(iArr);
        ArrayList arrayList = new ArrayList();
        for (int i2 : iArr) {
            if (i2 > 0 && Collections.binarySearch(arrayList, Integer.valueOf(i2)) < 0) {
                arrayList.add(Integer.valueOf(i2));
            }
        }
        if (length == arrayList.size()) {
            return iArr;
        }
        int size = arrayList.size();
        int[] iArr2 = new int[size];
        for (int i3 = 0; i3 < size; i3++) {
            iArr2[i3] = ((Integer) arrayList.get(i3)).intValue();
        }
        return iArr2;
    }

    public final int c(RectF rectF) {
        CharSequence transformation;
        int length = this.i.length;
        if (length != 0) {
            int i2 = length - 1;
            int i3 = 1;
            int i4 = 0;
            while (i3 <= i2) {
                int i5 = (i3 + i2) / 2;
                int i6 = this.i[i5];
                CharSequence text = this.l.getText();
                TransformationMethod transformationMethod = this.l.getTransformationMethod();
                if (!(transformationMethod == null || (transformation = transformationMethod.getTransformation(text, this.l)) == null)) {
                    text = transformation;
                }
                int maxLines = this.l.getMaxLines();
                TextPaint textPaint = this.k;
                if (textPaint == null) {
                    this.k = new TextPaint();
                } else {
                    textPaint.reset();
                }
                this.k.set(this.l.getPaint());
                this.k.setTextSize((float) i6);
                StaticLayout.Builder obtain = StaticLayout.Builder.obtain(text, 0, text.length(), this.k, Math.round(rectF.right));
                obtain.setAlignment((Layout.Alignment) e(this.l, "getLayoutAlignment", Layout.Alignment.ALIGN_NORMAL)).setLineSpacing(this.l.getLineSpacingExtra(), this.l.getLineSpacingMultiplier()).setIncludePad(this.l.getIncludeFontPadding()).setBreakStrategy(this.l.getBreakStrategy()).setHyphenationFrequency(this.l.getHyphenationFrequency()).setMaxLines(maxLines == -1 ? Integer.MAX_VALUE : maxLines);
                try {
                    this.n.a(obtain, this.l);
                } catch (ClassCastException unused) {
                    Log.w("ACTVAutoSizeHelper", "Failed to obtain TextDirectionHeuristic, auto size may be incorrect");
                }
                StaticLayout build = obtain.build();
                if ((maxLines == -1 || (build.getLineCount() <= maxLines && build.getLineEnd(build.getLineCount() - 1) == text.length())) && ((float) build.getHeight()) <= rectF.bottom) {
                    int i7 = i5 + 1;
                    i4 = i3;
                    i3 = i7;
                } else {
                    i4 = i5 - 1;
                    i2 = i4;
                }
            }
            return this.i[i4];
        }
        throw new IllegalStateException("No available text sizes to choose from.");
    }

    public void f(int i2, float f2) {
        Resources resources;
        Context context = this.m;
        if (context == null) {
            resources = Resources.getSystem();
        } else {
            resources = context.getResources();
        }
        float applyDimension = TypedValue.applyDimension(i2, f2, resources.getDisplayMetrics());
        if (applyDimension != this.l.getPaint().getTextSize()) {
            this.l.getPaint().setTextSize(applyDimension);
            boolean isInLayout = this.l.isInLayout();
            if (this.l.getLayout() != null) {
                this.e = false;
                try {
                    Method d2 = d("nullLayouts");
                    if (d2 != null) {
                        d2.invoke(this.l, new Object[0]);
                    }
                } catch (Exception e2) {
                    Log.w("ACTVAutoSizeHelper", "Failed to invoke TextView#nullLayouts() method", e2);
                }
                if (!isInLayout) {
                    this.l.requestLayout();
                } else {
                    this.l.forceLayout();
                }
                this.l.invalidate();
            }
        }
    }

    public final boolean g() {
        if (!i() || this.d != 1) {
            this.e = false;
        } else {
            if (!this.j || this.i.length == 0) {
                int floor = ((int) Math.floor((double) ((this.h - this.g) / this.f))) + 1;
                int[] iArr = new int[floor];
                for (int i2 = 0; i2 < floor; i2++) {
                    iArr[i2] = Math.round((((float) i2) * this.f) + this.g);
                }
                this.i = b(iArr);
            }
            this.e = true;
        }
        return this.e;
    }

    public final boolean h() {
        int[] iArr = this.i;
        int length = iArr.length;
        boolean z = length > 0;
        this.j = z;
        if (z) {
            this.d = 1;
            this.g = (float) iArr[0];
            this.h = (float) iArr[length - 1];
            this.f = -1.0f;
        }
        return z;
    }

    public final boolean i() {
        return !(this.l instanceof C4011o8);
    }

    public final void j(float f2, float f3, float f4) {
        if (f2 <= 0.0f) {
            throw new IllegalArgumentException("Minimum auto-size text size (" + f2 + "px) is less or equal to (0px)");
        } else if (f3 <= f2) {
            throw new IllegalArgumentException("Maximum auto-size text size (" + f3 + "px) is less or equal to minimum auto-size text size (" + f2 + "px)");
        } else if (f4 > 0.0f) {
            this.d = 1;
            this.g = f2;
            this.h = f3;
            this.f = f4;
            this.j = false;
        } else {
            throw new IllegalArgumentException("The auto-size step granularity (" + f4 + "px) is less or equal to (0px)");
        }
    }
}
