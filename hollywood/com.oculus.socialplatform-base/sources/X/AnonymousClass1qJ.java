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
import com.oculus.socialplatform.R;

/* renamed from: X.1qJ  reason: invalid class name */
public final class AnonymousClass1qJ extends EditText {
    public final C10991qO A00;
    public final C11121qe A01 = new C11121qe(this);
    public final AnonymousClass1qE A02;

    @Nullable
    @RestrictTo({AnonymousClass02C.LIBRARY_GROUP_PREFIX})
    public ColorStateList getSupportBackgroundTintList() {
        C10991qO r0 = this.A00;
        if (r0 != null) {
            return r0.A01();
        }
        return null;
    }

    @Nullable
    @RestrictTo({AnonymousClass02C.LIBRARY_GROUP_PREFIX})
    public PorterDuff.Mode getSupportBackgroundTintMode() {
        C10991qO r0 = this.A00;
        if (r0 != null) {
            return r0.A02();
        }
        return null;
    }

    @NonNull
    @RequiresApi(api = 26)
    public TextClassifier getTextClassifier() {
        C11121qe r1;
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

    @RestrictTo({AnonymousClass02C.LIBRARY_GROUP_PREFIX})
    public void setSupportBackgroundTintList(@Nullable ColorStateList colorStateList) {
        C10991qO r0 = this.A00;
        if (r0 != null) {
            r0.A05(colorStateList);
        }
    }

    @RestrictTo({AnonymousClass02C.LIBRARY_GROUP_PREFIX})
    public void setSupportBackgroundTintMode(@Nullable PorterDuff.Mode mode) {
        C10991qO r0 = this.A00;
        if (r0 != null) {
            r0.A06(mode);
        }
    }

    @RequiresApi(api = 26)
    public void setTextClassifier(@Nullable TextClassifier textClassifier) {
        C11121qe r0;
        if (Build.VERSION.SDK_INT >= 28 || (r0 = this.A01) == null) {
            super.setTextClassifier(textClassifier);
        } else {
            r0.A00 = textClassifier;
        }
    }

    public AnonymousClass1qJ(@NonNull Context context, @Nullable AttributeSet attributeSet) {
        super(AnonymousClass1RS.A00(context), attributeSet, R.attr.editTextStyle);
        C10891q9.A03(this, getContext());
        C10991qO r0 = new C10991qO(this);
        this.A00 = r0;
        r0.A07(attributeSet, R.attr.editTextStyle);
        AnonymousClass1qE r02 = new AnonymousClass1qE(this);
        this.A02 = r02;
        r02.A0A(attributeSet, R.attr.editTextStyle);
        this.A02.A04();
    }

    public final void drawableStateChanged() {
        super.drawableStateChanged();
        C10991qO r0 = this.A00;
        if (r0 != null) {
            r0.A03();
        }
        AnonymousClass1qE r02 = this.A02;
        if (r02 != null) {
            r02.A04();
        }
    }

    public final InputConnection onCreateInputConnection(EditorInfo editorInfo) {
        InputConnection onCreateInputConnection = super.onCreateInputConnection(editorInfo);
        C11141qg.A00(onCreateInputConnection, editorInfo, this);
        return onCreateInputConnection;
    }

    public void setBackgroundDrawable(Drawable drawable) {
        super.setBackgroundDrawable(drawable);
        C10991qO r1 = this.A00;
        if (r1 != null) {
            C10991qO.A00(r1, null);
            r1.A03();
        }
    }

    public void setBackgroundResource(@DrawableRes int i) {
        super.setBackgroundResource(i);
        C10991qO r0 = this.A00;
        if (r0 != null) {
            r0.A04(i);
        }
    }

    public void setCustomSelectionActionModeCallback(ActionMode.Callback callback) {
        super.setCustomSelectionActionModeCallback(AnonymousClass08Y.A00(this, callback));
    }

    public final void setTextAppearance(Context context, int i) {
        super.setTextAppearance(context, i);
        AnonymousClass1qE r0 = this.A02;
        if (r0 != null) {
            r0.A07(context, i);
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
