package org.chromium.chrome.browser.accessibility.settings;

import J.N;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import androidx.preference.Preference;
import com.oculus.browser.R;
import java.util.Objects;
import org.chromium.chrome.browser.accessibility.FontSizePrefs;
import org.chromium.chrome.browser.profiles.Profile;
import org.chromium.components.browser_ui.settings.ChromeBaseCheckBoxPreference;
import org.chromium.components.prefs.PrefService;

/* compiled from: chromium-OculusBrowser.apk-stable-281887347 */
public class AccessibilitySettings extends AbstractC2324eF0 implements XE0 {
    public TextScalePreference G0;
    public ChromeBaseCheckBoxPreference H0;
    public boolean I0;
    public FontSizePrefs J0 = FontSizePrefs.b();
    public J K0 = new J(this);

    @Override // defpackage.AbstractComponentCallbacksC3550lS, defpackage.AbstractC2324eF0
    public void D0() {
        super.D0();
        FontSizePrefs fontSizePrefs = this.J0;
        fontSizePrefs.c.b(this.K0);
    }

    @Override // defpackage.AbstractComponentCallbacksC3550lS, defpackage.AbstractC2324eF0
    public void E0() {
        FontSizePrefs fontSizePrefs = this.J0;
        fontSizePrefs.c.c(this.K0);
        if (this.I0) {
            Objects.requireNonNull(this.J0);
            AbstractC3100ip1.f10165a.d("Accessibility.Android.UserFontSizePref.Change", (int) (FontSizePrefs.b().d() * 100.0f));
            this.I0 = false;
        }
        super.E0();
    }

    @Override // defpackage.XE0
    public boolean a(Preference preference, Object obj) {
        if ("text_scale".equals(preference.Q)) {
            this.I0 = true;
            FontSizePrefs fontSizePrefs = this.J0;
            float floatValue = ((Float) obj).floatValue();
            Objects.requireNonNull(fontSizePrefs);
            NU0.f8549a.f8694a.a("user_font_scale_factor");
            SharedPreferences.Editor edit = AbstractC3983nz.f10523a.edit();
            edit.putFloat("user_font_scale_factor", floatValue);
            edit.apply();
            fontSizePrefs.e(fontSizePrefs.c() * floatValue);
        } else if ("force_enable_zoom".equals(preference.Q)) {
            this.J0.f(((Boolean) obj).booleanValue(), true);
        } else if ("reader_for_accessibility".equals(preference.Q)) {
            PrefService a2 = Wr1.a(Profile.b());
            N.Mf2ABpoH(a2.f10883a, "dom_distiller.reader_for_accessibility", ((Boolean) obj).booleanValue());
        }
        return true;
    }

    @Override // defpackage.AbstractComponentCallbacksC3550lS
    public void b0(Bundle bundle) {
        this.i0 = true;
        u().setTitle(R.string.f59050_resource_name_obfuscated_RES_2131953222);
        i1(null);
    }

    @Override // defpackage.AbstractC2324eF0
    public void g1(Bundle bundle, String str) {
        AbstractC2870hT0.a(this, R.xml.f76050_resource_name_obfuscated_RES_2132213761);
        TextScalePreference textScalePreference = (TextScalePreference) f1("text_scale");
        this.G0 = textScalePreference;
        textScalePreference.f9480J = this;
        float a2 = this.J0.a();
        float d = this.J0.d();
        textScalePreference.u0 = a2;
        textScalePreference.t0 = d;
        ChromeBaseCheckBoxPreference chromeBaseCheckBoxPreference = (ChromeBaseCheckBoxPreference) f1("force_enable_zoom");
        this.H0 = chromeBaseCheckBoxPreference;
        chromeBaseCheckBoxPreference.f9480J = this;
        FontSizePrefs fontSizePrefs = this.J0;
        chromeBaseCheckBoxPreference.a0(N.MOnmBKet(fontSizePrefs.b, fontSizePrefs));
        ChromeBaseCheckBoxPreference chromeBaseCheckBoxPreference2 = (ChromeBaseCheckBoxPreference) f1("reader_for_accessibility");
        chromeBaseCheckBoxPreference2.a0(N.MzIXnlkD(Wr1.a(Profile.b()).f10883a, "dom_distiller.reader_for_accessibility"));
        chromeBaseCheckBoxPreference2.f9480J = this;
        ChromeBaseCheckBoxPreference chromeBaseCheckBoxPreference3 = (ChromeBaseCheckBoxPreference) f1("accessibility_tab_switcher");
        if (C0283Ep.h().d()) {
            chromeBaseCheckBoxPreference3.a0(NU0.f8549a.d("accessibility_tab_switcher", true));
        } else {
            this.z0.g.f0(chromeBaseCheckBoxPreference3);
        }
        f1("captions").K = new I(this);
        f1("image_descriptions").W(CZ.b().c());
    }

    public final /* synthetic */ boolean k1() {
        Intent intent = new Intent("android.settings.CAPTIONING_SETTINGS");
        intent.addFlags(268435456);
        c1(intent);
        return true;
    }
}
