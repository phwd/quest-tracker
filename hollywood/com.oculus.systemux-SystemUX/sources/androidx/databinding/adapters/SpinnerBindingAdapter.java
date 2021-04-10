package androidx.databinding.adapters;

import android.widget.Spinner;
import androidx.annotation.RestrictTo;
import androidx.databinding.BindingMethod;
import androidx.databinding.BindingMethods;

@BindingMethods({@BindingMethod(attribute = "android:popupBackground", method = "setPopupBackgroundDrawable", type = Spinner.class)})
@RestrictTo({RestrictTo.Scope.LIBRARY})
public class SpinnerBindingAdapter {
}
