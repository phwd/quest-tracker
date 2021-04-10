package X;

import android.content.Context;
import android.text.method.SingleLineTransformationMethod;
import android.view.View;
import java.util.Locale;

/* renamed from: X.0Cf  reason: invalid class name */
public class AnonymousClass0Cf extends SingleLineTransformationMethod {
    public Locale A00;

    public AnonymousClass0Cf(Context context) {
        this.A00 = context.getResources().getConfiguration().locale;
    }

    public final CharSequence getTransformation(CharSequence charSequence, View view) {
        CharSequence transformation = super.getTransformation(charSequence, view);
        if (transformation != null) {
            return transformation.toString().toUpperCase(this.A00);
        }
        return null;
    }
}
