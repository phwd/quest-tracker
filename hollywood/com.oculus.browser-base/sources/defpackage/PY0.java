package defpackage;

import android.view.View;
import android.widget.AdapterView;
import android.widget.Spinner;
import org.chromium.components.browser_ui.settings.SpinnerPreference;

/* renamed from: PY0  reason: default package */
/* compiled from: chromium-OculusBrowser.apk-stable-281887347 */
public class PY0 implements AdapterView.OnItemSelectedListener {
    public final /* synthetic */ SpinnerPreference F;

    public PY0(SpinnerPreference spinnerPreference) {
        this.F = spinnerPreference;
    }

    @Override // android.widget.AdapterView.OnItemSelectedListener
    public void onItemSelected(AdapterView adapterView, View view, int i, long j) {
        Object obj;
        SpinnerPreference spinnerPreference = this.F;
        spinnerPreference.v0 = i;
        XE0 xe0 = spinnerPreference.f9480J;
        if (xe0 != null) {
            Spinner spinner = spinnerPreference.t0;
            if (spinner == null) {
                obj = spinnerPreference.u0.getItem(i);
            } else {
                obj = spinner.getSelectedItem();
            }
            xe0.a(spinnerPreference, obj);
        }
    }

    @Override // android.widget.AdapterView.OnItemSelectedListener
    public void onNothingSelected(AdapterView adapterView) {
    }
}
