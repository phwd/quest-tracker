package X;

import android.content.Context;
import android.content.res.ColorStateList;
import android.graphics.PorterDuff;
import android.graphics.drawable.Drawable;
import android.os.Build;
import android.text.Editable;
import android.util.AttributeSet;
import android.view.ActionMode;
import android.view.inputmethod.EditorInfo;
import android.view.inputmethod.InputConnection;
import android.view.textclassifier.TextClassificationManager;
import android.view.textclassifier.TextClassifier;
import android.widget.EditText;
import androidx.annotation.DrawableRes;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.annotation.RequiresApi;
import androidx.annotation.RestrictTo;
import com.oculus.alpenglow.R;

/* renamed from: X.0eC  reason: invalid class name and case insensitive filesystem */
public final class C04170eC extends EditText {
    public final AnonymousClass04A A00;
    public final AnonymousClass04T A01 = new AnonymousClass04T(this);
    public final AnonymousClass04U A02;

    @Nullable
    @RestrictTo({AnonymousClass02D.LIBRARY_GROUP_PREFIX})
    public ColorStateList getSupportBackgroundTintList() {
        AnonymousClass04A r0 = this.A00;
        if (r0 != null) {
            return r0.A02();
        }
        return null;
    }

    @Nullable
    @RestrictTo({AnonymousClass02D.LIBRARY_GROUP_PREFIX})
    public PorterDuff.Mode getSupportBackgroundTintMode() {
        AnonymousClass04A r0 = this.A00;
        if (r0 != null) {
            return r0.A03();
        }
        return null;
    }

    @NonNull
    @RequiresApi(api = 26)
    public TextClassifier getTextClassifier() {
        AnonymousClass04T r1;
        if (Build.VERSION.SDK_INT >= 28 || (r1 = this.A01) == null) {
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

    @RestrictTo({AnonymousClass02D.LIBRARY_GROUP_PREFIX})
    public void setSupportBackgroundTintList(@Nullable ColorStateList colorStateList) {
        AnonymousClass04A r0 = this.A00;
        if (r0 != null) {
            r0.A06(colorStateList);
        }
    }

    @RestrictTo({AnonymousClass02D.LIBRARY_GROUP_PREFIX})
    public void setSupportBackgroundTintMode(@Nullable PorterDuff.Mode mode) {
        AnonymousClass04A r0 = this.A00;
        if (r0 != null) {
            r0.A07(mode);
        }
    }

    @RequiresApi(api = 26)
    public void setTextClassifier(@Nullable TextClassifier textClassifier) {
        AnonymousClass04T r0;
        if (Build.VERSION.SDK_INT >= 28 || (r0 = this.A01) == null) {
            super.setTextClassifier(textClassifier);
        } else {
            r0.A00 = textClassifier;
        }
    }

    public C04170eC(@NonNull Context context, @Nullable AttributeSet attributeSet) {
        super(AnonymousClass17C.A00(context), attributeSet, R.attr.editTextStyle);
        AnonymousClass05V.A03(this, getContext());
        AnonymousClass04A r0 = new AnonymousClass04A(this);
        this.A00 = r0;
        r0.A08(attributeSet, R.attr.editTextStyle);
        AnonymousClass04U r02 = new AnonymousClass04U(this);
        this.A02 = r02;
        r02.A0C(attributeSet, R.attr.editTextStyle);
        this.A02.A06();
    }

    public final void drawableStateChanged() {
        super.drawableStateChanged();
        AnonymousClass04A r0 = this.A00;
        if (r0 != null) {
            r0.A04();
        }
        AnonymousClass04U r02 = this.A02;
        if (r02 != null) {
            r02.A06();
        }
    }

    public final InputConnection onCreateInputConnection(EditorInfo editorInfo) {
        InputConnection onCreateInputConnection = super.onCreateInputConnection(editorInfo);
        AnonymousClass04F.A00(onCreateInputConnection, editorInfo, this);
        return onCreateInputConnection;
    }

    public void setBackgroundDrawable(Drawable drawable) {
        super.setBackgroundDrawable(drawable);
        AnonymousClass04A r1 = this.A00;
        if (r1 != null) {
            AnonymousClass04A.A00(r1, null);
            r1.A04();
        }
    }

    public void setBackgroundResource(@DrawableRes int i) {
        super.setBackgroundResource(i);
        AnonymousClass04A r0 = this.A00;
        if (r0 != null) {
            r0.A05(i);
        }
    }

    public void setCustomSelectionActionModeCallback(ActionMode.Callback callback) {
        super.setCustomSelectionActionModeCallback(AnonymousClass0Bq.A00(this, callback));
    }

    public final void setTextAppearance(Context context, int i) {
        super.setTextAppearance(context, i);
        AnonymousClass04U r0 = this.A02;
        if (r0 != null) {
            r0.A09(context, i);
        }
    }

    @Override // android.widget.EditText, android.widget.EditText
    @Nullable
    public Editable getText() {
        if (Build.VERSION.SDK_INT >= 28) {
            return super.getText();
        }
        return super.getEditableText();
    }
}
