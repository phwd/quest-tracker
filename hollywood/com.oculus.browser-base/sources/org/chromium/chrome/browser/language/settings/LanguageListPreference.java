package org.chromium.chrome.browser.language.settings;

import android.content.Context;
import android.graphics.drawable.Drawable;
import android.util.AttributeSet;
import android.widget.TextView;
import androidx.preference.Preference;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import com.oculus.browser.R;
import java.util.Objects;

/* compiled from: chromium-OculusBrowser.apk-stable-281887347 */
public class LanguageListPreference extends Preference {
    public TextView t0;
    public RecyclerView u0;
    public L60 v0;
    public AbstractC5019u3 w0;

    public LanguageListPreference(Context context, AttributeSet attributeSet) {
        super(context, attributeSet);
        this.v0 = new L60(context);
    }

    public final void a0() {
        LanguageSettings languageSettings = (LanguageSettings) this.w0;
        Objects.requireNonNull(languageSettings);
        T60.e(1);
        languageSettings.n1(0, 1);
    }

    @Override // androidx.preference.Preference
    public void x(C4886tF0 tf0) {
        super.x(tf0);
        TextView textView = (TextView) tf0.x(R.id.add_language);
        this.t0 = textView;
        textView.setCompoundDrawablesRelativeWithIntrinsicBounds(C0636Ki1.b(this.F, R.drawable.f34580_resource_name_obfuscated_RES_2131231498, R.color.f11100_resource_name_obfuscated_RES_2131099800), (Drawable) null, (Drawable) null, (Drawable) null);
        this.t0.setOnClickListener(new I60(this));
        this.u0 = (RecyclerView) tf0.x(R.id.language_list);
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(this.F);
        this.u0.t0(linearLayoutManager);
        this.u0.g(new C4547rG(this.F, linearLayoutManager.r));
        RecyclerView recyclerView = this.u0;
        AbstractC5750yK0 yk0 = recyclerView.T;
        L60 l60 = this.v0;
        if (yk0 != l60) {
            recyclerView.q0(l60);
            T60 a2 = T60.a();
            L60 l602 = this.v0;
            a2.c = l602;
            l602.u();
        }
    }
}
