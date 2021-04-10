package X;

import android.annotation.SuppressLint;
import android.content.Context;
import android.content.res.ColorStateList;
import android.graphics.PorterDuff;
import android.graphics.Typeface;
import android.graphics.drawable.Drawable;
import android.os.Build;
import android.util.AttributeSet;
import android.view.ActionMode;
import android.view.inputmethod.EditorInfo;
import android.view.inputmethod.InputConnection;
import android.view.textclassifier.TextClassificationManager;
import android.view.textclassifier.TextClassifier;
import android.widget.TextView;
import androidx.annotation.DrawableRes;
import androidx.annotation.IntRange;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.annotation.Px;
import androidx.annotation.RequiresApi;
import androidx.annotation.RestrictTo;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.Future;

/* renamed from: X.0e2  reason: invalid class name and case insensitive filesystem */
public class C04090e2 extends TextView implements AnonymousClass0Bc {
    @Nullable
    public Future<AnonymousClass09p> A00;
    public final AnonymousClass04A A01;
    public final AnonymousClass04T A02 = new AnonymousClass04T(this);
    public final AnonymousClass04U A03;

    /* JADX WARNING: Code restructure failed: missing block: B:13:0x0051, code lost:
        if (r1 != 2) goto L_0x0077;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:22:0x0069, code lost:
        if (r6 != false) goto L_0x006b;
     */
    @androidx.annotation.NonNull
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public static X.AnonymousClass09m A02(@androidx.annotation.NonNull android.widget.TextView r7) {
        /*
        // Method dump skipped, instructions count: 138
        */
        throw new UnsupportedOperationException("Method not decompiled: X.C04090e2.A02(android.widget.TextView):X.09m");
    }

    @RestrictTo({AnonymousClass02D.LIBRARY_GROUP_PREFIX})
    public int getAutoSizeMaxTextSize() {
        if (AnonymousClass0Bc.A00) {
            return super.getAutoSizeMaxTextSize();
        }
        AnonymousClass04U r0 = this.A03;
        if (r0 != null) {
            return Math.round(r0.A0C.A00);
        }
        return -1;
    }

    @RestrictTo({AnonymousClass02D.LIBRARY_GROUP_PREFIX})
    public int getAutoSizeMinTextSize() {
        if (AnonymousClass0Bc.A00) {
            return super.getAutoSizeMinTextSize();
        }
        AnonymousClass04U r0 = this.A03;
        if (r0 != null) {
            return Math.round(r0.A0C.A01);
        }
        return -1;
    }

    @RestrictTo({AnonymousClass02D.LIBRARY_GROUP_PREFIX})
    public int getAutoSizeStepGranularity() {
        if (AnonymousClass0Bc.A00) {
            return super.getAutoSizeStepGranularity();
        }
        AnonymousClass04U r0 = this.A03;
        if (r0 != null) {
            return Math.round(r0.A0C.A02);
        }
        return -1;
    }

    @RestrictTo({AnonymousClass02D.LIBRARY_GROUP_PREFIX})
    public int[] getAutoSizeTextAvailableSizes() {
        if (AnonymousClass0Bc.A00) {
            return super.getAutoSizeTextAvailableSizes();
        }
        AnonymousClass04U r0 = this.A03;
        if (r0 != null) {
            return r0.A0C.A07;
        }
        return new int[0];
    }

    @SuppressLint({"WrongConstant"})
    @RestrictTo({AnonymousClass02D.LIBRARY_GROUP_PREFIX})
    public int getAutoSizeTextType() {
        if (!AnonymousClass0Bc.A00) {
            AnonymousClass04U r0 = this.A03;
            if (r0 != null) {
                return r0.A0C.A03;
            }
            return 0;
        } else if (super.getAutoSizeTextType() == 1) {
            return 1;
        } else {
            return 0;
        }
    }

    @Nullable
    @RestrictTo({AnonymousClass02D.LIBRARY_GROUP_PREFIX})
    public ColorStateList getSupportBackgroundTintList() {
        AnonymousClass04A r0 = this.A01;
        if (r0 != null) {
            return r0.A02();
        }
        return null;
    }

    @Nullable
    @RestrictTo({AnonymousClass02D.LIBRARY_GROUP_PREFIX})
    public PorterDuff.Mode getSupportBackgroundTintMode() {
        AnonymousClass04A r0 = this.A01;
        if (r0 != null) {
            return r0.A03();
        }
        return null;
    }

    @Nullable
    @RestrictTo({AnonymousClass02D.LIBRARY_GROUP_PREFIX})
    public ColorStateList getSupportCompoundDrawablesTintList() {
        AnonymousClass05X r0 = this.A03.A07;
        if (r0 != null) {
            return r0.A00;
        }
        return null;
    }

    @Nullable
    @RestrictTo({AnonymousClass02D.LIBRARY_GROUP_PREFIX})
    public PorterDuff.Mode getSupportCompoundDrawablesTintMode() {
        AnonymousClass05X r0 = this.A03.A07;
        if (r0 != null) {
            return r0.A01;
        }
        return null;
    }

    public CharSequence getText() {
        Future<AnonymousClass09p> future = this.A00;
        if (future != null) {
            try {
                this.A00 = null;
                future.get();
                if (Build.VERSION.SDK_INT < 29) {
                    A02(this);
                }
                throw null;
            } catch (InterruptedException | ExecutionException unused) {
            }
        }
        return super.getText();
    }

    @NonNull
    @RequiresApi(api = 26)
    public TextClassifier getTextClassifier() {
        AnonymousClass04T r1;
        if (Build.VERSION.SDK_INT >= 28 || (r1 = this.A02) == null) {
            return super.getTextClassifier();
        }
        TextClassifier textClassifier = r1.A00;
        if (textClassifier != null) {
            return textClassifier;
        }
        TextClassificationManager textClassificationManager = (TextClassificationManager) r1.A01.getContext().getSystemService(TextClassificationManager.class);
        if (textClassificationManager != null) {
            return textClassificationManager.getTextClassifier();
        }
        return TextClassifier.NO_OP;
    }

    public void onMeasure(int i, int i2) {
        Future<AnonymousClass09p> future = this.A00;
        if (future != null) {
            try {
                this.A00 = null;
                future.get();
                if (Build.VERSION.SDK_INT < 29) {
                    A02(this);
                }
                throw null;
            } catch (InterruptedException | ExecutionException unused) {
            }
        }
        super.onMeasure(i, i2);
    }

    @RestrictTo({AnonymousClass02D.LIBRARY_GROUP_PREFIX})
    public final void setAutoSizeTextTypeUniformWithConfiguration(int i, int i2, int i3, int i4) throws IllegalArgumentException {
        if (AnonymousClass0Bc.A00) {
            super.setAutoSizeTextTypeUniformWithConfiguration(i, i2, i3, i4);
            return;
        }
        AnonymousClass04U r0 = this.A03;
        if (r0 != null) {
            r0.A08(i, i2, i3, i4);
        }
    }

    @RestrictTo({AnonymousClass02D.LIBRARY_GROUP_PREFIX})
    public final void setAutoSizeTextTypeUniformWithPresetSizes(@NonNull int[] iArr, int i) throws IllegalArgumentException {
        if (AnonymousClass0Bc.A00) {
            super.setAutoSizeTextTypeUniformWithPresetSizes(iArr, i);
            return;
        }
        AnonymousClass04U r0 = this.A03;
        if (r0 != null) {
            r0.A0D(iArr, i);
        }
    }

    @RestrictTo({AnonymousClass02D.LIBRARY_GROUP_PREFIX})
    public void setAutoSizeTextTypeWithDefaults(int i) {
        if (AnonymousClass0Bc.A00) {
            super.setAutoSizeTextTypeWithDefaults(i);
            return;
        }
        AnonymousClass04U r0 = this.A03;
        if (r0 != null) {
            r0.A07(i);
        }
    }

    public void setFirstBaselineToTopHeight(@IntRange(from = 0) @Px int i) {
        if (Build.VERSION.SDK_INT >= 28) {
            super.setFirstBaselineToTopHeight(i);
        } else {
            AnonymousClass0Bq.A01(this, i);
        }
    }

    public void setLastBaselineToBottomHeight(@IntRange(from = 0) @Px int i) {
        if (Build.VERSION.SDK_INT >= 28) {
            super.setLastBaselineToBottomHeight(i);
        } else {
            AnonymousClass0Bq.A02(this, i);
        }
    }

    public void setPrecomputedText(@NonNull AnonymousClass09p r3) {
        if (Build.VERSION.SDK_INT < 29) {
            A02(this);
        }
        throw null;
    }

    @RestrictTo({AnonymousClass02D.LIBRARY_GROUP_PREFIX})
    public void setSupportBackgroundTintList(@Nullable ColorStateList colorStateList) {
        AnonymousClass04A r0 = this.A01;
        if (r0 != null) {
            r0.A06(colorStateList);
        }
    }

    @RestrictTo({AnonymousClass02D.LIBRARY_GROUP_PREFIX})
    public void setSupportBackgroundTintMode(@Nullable PorterDuff.Mode mode) {
        AnonymousClass04A r0 = this.A01;
        if (r0 != null) {
            r0.A07(mode);
        }
    }

    @RestrictTo({AnonymousClass02D.LIBRARY_GROUP_PREFIX})
    public void setSupportCompoundDrawablesTintList(@Nullable ColorStateList colorStateList) {
        AnonymousClass04U r0 = this.A03;
        r0.A0A(colorStateList);
        r0.A06();
    }

    @RestrictTo({AnonymousClass02D.LIBRARY_GROUP_PREFIX})
    public void setSupportCompoundDrawablesTintMode(@Nullable PorterDuff.Mode mode) {
        AnonymousClass04U r0 = this.A03;
        r0.A0B(mode);
        r0.A06();
    }

    @RequiresApi(api = 26)
    public void setTextClassifier(@Nullable TextClassifier textClassifier) {
        AnonymousClass04T r0;
        if (Build.VERSION.SDK_INT >= 28 || (r0 = this.A02) == null) {
            super.setTextClassifier(textClassifier);
        } else {
            r0.A00 = textClassifier;
        }
    }

    public void setTextFuture(@Nullable Future<AnonymousClass09p> future) {
        this.A00 = future;
        if (future != null) {
            requestLayout();
        }
    }

    /* JADX WARNING: Code restructure failed: missing block: B:21:0x003d, code lost:
        if (r3 != r2) goto L_0x003f;
     */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public void setTextMetricsParamsCompat(@androidx.annotation.NonNull X.AnonymousClass09m r5) {
        /*
            r4 = this;
            android.text.TextDirectionHeuristic r3 = r5.A03
            android.text.TextDirectionHeuristic r2 = android.text.TextDirectionHeuristics.FIRSTSTRONG_RTL
            if (r3 == r2) goto L_0x003f
            android.text.TextDirectionHeuristic r1 = android.text.TextDirectionHeuristics.FIRSTSTRONG_LTR
            if (r3 == r1) goto L_0x003f
            android.text.TextDirectionHeuristic r0 = android.text.TextDirectionHeuristics.ANYRTL_LTR
            if (r3 != r0) goto L_0x0026
            r0 = 2
        L_0x000f:
            r4.setTextDirection(r0)
            android.text.TextPaint r1 = r4.getPaint()
            android.text.TextPaint r0 = r5.A04
            r1.set(r0)
            int r0 = r5.A01
            r4.setBreakStrategy(r0)
            int r0 = r5.A02
            r4.setHyphenationFrequency(r0)
            return
        L_0x0026:
            android.text.TextDirectionHeuristic r0 = android.text.TextDirectionHeuristics.LTR
            if (r3 != r0) goto L_0x002c
            r0 = 3
            goto L_0x000f
        L_0x002c:
            android.text.TextDirectionHeuristic r0 = android.text.TextDirectionHeuristics.RTL
            if (r3 != r0) goto L_0x0032
            r0 = 4
            goto L_0x000f
        L_0x0032:
            android.text.TextDirectionHeuristic r0 = android.text.TextDirectionHeuristics.LOCALE
            if (r3 != r0) goto L_0x0038
            r0 = 5
            goto L_0x000f
        L_0x0038:
            if (r3 != r1) goto L_0x003c
            r0 = 6
            goto L_0x000f
        L_0x003c:
            r0 = 7
            if (r3 == r2) goto L_0x000f
        L_0x003f:
            r0 = 1
            goto L_0x000f
        */
        throw new UnsupportedOperationException("Method not decompiled: X.C04090e2.setTextMetricsParamsCompat(X.09m):void");
    }

    public final void setTextSize(int i, float f) {
        boolean z = AnonymousClass0Bc.A00;
        if (z) {
            super.setTextSize(i, f);
            return;
        }
        AnonymousClass04U r1 = this.A03;
        if (r1 != null && !z && !r1.A0E()) {
            r1.A0C.A07(i, f);
        }
    }

    public final void setTypeface(@Nullable Typeface typeface, int i) {
        if (typeface != null && i > 0) {
            if (getContext() != null) {
                Typeface create = Typeface.create(typeface, i);
                if (create != null) {
                    typeface = create;
                }
            } else {
                throw new IllegalArgumentException("Context cannot be null");
            }
        }
        super.setTypeface(typeface, i);
    }

    public C04090e2(@NonNull Context context, @Nullable AttributeSet attributeSet, int i) {
        super(AnonymousClass17C.A00(context), attributeSet, i);
        AnonymousClass05V.A03(this, getContext());
        AnonymousClass04A r0 = new AnonymousClass04A(this);
        this.A01 = r0;
        r0.A08(attributeSet, i);
        AnonymousClass04U r02 = new AnonymousClass04U(this);
        this.A03 = r02;
        r02.A0C(attributeSet, i);
        this.A03.A06();
    }

    public final void drawableStateChanged() {
        super.drawableStateChanged();
        AnonymousClass04A r0 = this.A01;
        if (r0 != null) {
            r0.A04();
        }
        AnonymousClass04U r02 = this.A03;
        if (r02 != null) {
            r02.A06();
        }
    }

    public int getFirstBaselineToTopHeight() {
        return getPaddingTop() - getPaint().getFontMetricsInt().top;
    }

    public int getLastBaselineToBottomHeight() {
        return getPaddingBottom() + getPaint().getFontMetricsInt().bottom;
    }

    @NonNull
    public AnonymousClass09m getTextMetricsParamsCompat() {
        return A02(this);
    }

    public final InputConnection onCreateInputConnection(EditorInfo editorInfo) {
        InputConnection onCreateInputConnection = super.onCreateInputConnection(editorInfo);
        AnonymousClass04F.A00(onCreateInputConnection, editorInfo, this);
        return onCreateInputConnection;
    }

    public final void onLayout(boolean z, int i, int i2, int i3, int i4) {
        super.onLayout(z, i, i2, i3, i4);
        AnonymousClass04U r1 = this.A03;
        if (r1 != null && !AnonymousClass0Bc.A00) {
            r1.A0C.A06();
        }
    }

    public final void onTextChanged(CharSequence charSequence, int i, int i2, int i3) {
        super.onTextChanged(charSequence, i, i2, i3);
        AnonymousClass04U r1 = this.A03;
        if (r1 != null && !AnonymousClass0Bc.A00 && r1.A0E()) {
            r1.A0C.A06();
        }
    }

    public void setBackgroundDrawable(Drawable drawable) {
        super.setBackgroundDrawable(drawable);
        AnonymousClass04A r1 = this.A01;
        if (r1 != null) {
            AnonymousClass04A.A00(r1, null);
            r1.A04();
        }
    }

    public void setBackgroundResource(@DrawableRes int i) {
        super.setBackgroundResource(i);
        AnonymousClass04A r0 = this.A01;
        if (r0 != null) {
            r0.A05(i);
        }
    }

    public void setCompoundDrawables(@Nullable Drawable drawable, @Nullable Drawable drawable2, @Nullable Drawable drawable3, @Nullable Drawable drawable4) {
        super.setCompoundDrawables(drawable, drawable2, drawable3, drawable4);
        AnonymousClass04U r0 = this.A03;
        if (r0 != null) {
            r0.A06();
        }
    }

    @RequiresApi(17)
    public void setCompoundDrawablesRelative(@Nullable Drawable drawable, @Nullable Drawable drawable2, @Nullable Drawable drawable3, @Nullable Drawable drawable4) {
        super.setCompoundDrawablesRelative(drawable, drawable2, drawable3, drawable4);
        AnonymousClass04U r0 = this.A03;
        if (r0 != null) {
            r0.A06();
        }
    }

    public void setCustomSelectionActionModeCallback(ActionMode.Callback callback) {
        super.setCustomSelectionActionModeCallback(AnonymousClass0Bq.A00(this, callback));
    }

    public final void setTextAppearance(Context context, int i) {
        super.setTextAppearance(context, i);
        AnonymousClass04U r0 = this.A03;
        if (r0 != null) {
            r0.A09(context, i);
        }
    }

    public void setLineHeight(@IntRange(from = 0) @Px int i) {
        AnonymousClass0Bq.A03(this, i);
    }

    @Override // android.widget.TextView
    @RequiresApi(17)
    public final void setCompoundDrawablesRelativeWithIntrinsicBounds(int i, int i2, int i3, int i4) {
        Drawable drawable;
        Drawable drawable2;
        Drawable drawable3;
        Context context = getContext();
        Drawable drawable4 = null;
        if (i != 0) {
            drawable = AnonymousClass17E.A00(context, i);
        } else {
            drawable = null;
        }
        if (i2 != 0) {
            drawable2 = AnonymousClass17E.A00(context, i2);
        } else {
            drawable2 = null;
        }
        if (i3 != 0) {
            drawable3 = AnonymousClass17E.A00(context, i3);
        } else {
            drawable3 = null;
        }
        if (i4 != 0) {
            drawable4 = AnonymousClass17E.A00(context, i4);
        }
        setCompoundDrawablesRelativeWithIntrinsicBounds(drawable, drawable2, drawable3, drawable4);
        AnonymousClass04U r0 = this.A03;
        if (r0 != null) {
            r0.A06();
        }
    }

    @Override // android.widget.TextView
    @RequiresApi(17)
    public void setCompoundDrawablesRelativeWithIntrinsicBounds(@Nullable Drawable drawable, @Nullable Drawable drawable2, @Nullable Drawable drawable3, @Nullable Drawable drawable4) {
        super.setCompoundDrawablesRelativeWithIntrinsicBounds(drawable, drawable2, drawable3, drawable4);
        AnonymousClass04U r0 = this.A03;
        if (r0 != null) {
            r0.A06();
        }
    }

    @Override // android.widget.TextView
    public final void setCompoundDrawablesWithIntrinsicBounds(int i, int i2, int i3, int i4) {
        Drawable drawable;
        Drawable drawable2;
        Drawable drawable3;
        Context context = getContext();
        Drawable drawable4 = null;
        if (i != 0) {
            drawable = AnonymousClass17E.A00(context, i);
        } else {
            drawable = null;
        }
        if (i2 != 0) {
            drawable2 = AnonymousClass17E.A00(context, i2);
        } else {
            drawable2 = null;
        }
        if (i3 != 0) {
            drawable3 = AnonymousClass17E.A00(context, i3);
        } else {
            drawable3 = null;
        }
        if (i4 != 0) {
            drawable4 = AnonymousClass17E.A00(context, i4);
        }
        setCompoundDrawablesWithIntrinsicBounds(drawable, drawable2, drawable3, drawable4);
        AnonymousClass04U r0 = this.A03;
        if (r0 != null) {
            r0.A06();
        }
    }

    @Override // android.widget.TextView
    public void setCompoundDrawablesWithIntrinsicBounds(@Nullable Drawable drawable, @Nullable Drawable drawable2, @Nullable Drawable drawable3, @Nullable Drawable drawable4) {
        super.setCompoundDrawablesWithIntrinsicBounds(drawable, drawable2, drawable3, drawable4);
        AnonymousClass04U r0 = this.A03;
        if (r0 != null) {
            r0.A06();
        }
    }
}
