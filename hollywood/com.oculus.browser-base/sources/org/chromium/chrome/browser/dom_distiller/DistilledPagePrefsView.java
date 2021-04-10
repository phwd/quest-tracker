package org.chromium.chrome.browser.dom_distiller;

import J.N;
import android.content.Context;
import android.util.AttributeSet;
import android.widget.LinearLayout;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.SeekBar;
import android.widget.Spinner;
import android.widget.SpinnerAdapter;
import android.widget.TextView;
import com.oculus.browser.R;
import java.text.NumberFormat;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Locale;
import java.util.Map;
import org.chromium.base.ThreadUtils;
import org.chromium.chrome.browser.profiles.Profile;
import org.chromium.components.dom_distiller.core.DistilledPagePrefs$DistilledPagePrefsObserverWrapper;
import org.chromium.components.dom_distiller.core.DomDistillerService;

/* compiled from: chromium-OculusBrowser.apk-stable-281887347 */
public class DistilledPagePrefsView extends LinearLayout implements AbstractC3693mG, SeekBar.OnSeekBarChangeListener {
    public static final /* synthetic */ int F = 0;
    public RadioGroup G;
    public final Map H;
    public final C3864nG I;

    /* renamed from: J  reason: collision with root package name */
    public TextView f10654J;
    public SeekBar K;
    public Spinner L;
    public final NumberFormat M;

    public DistilledPagePrefsView(Context context, AttributeSet attributeSet) {
        super(context, attributeSet);
        Profile b = Profile.b();
        HashMap hashMap = FG.f8005a;
        Object obj = ThreadUtils.f10596a;
        HashMap hashMap2 = FG.f8005a;
        DomDistillerService domDistillerService = (DomDistillerService) hashMap2.get(b);
        if (domDistillerService == null) {
            domDistillerService = (DomDistillerService) N.M2UAkcn4(b);
            hashMap2.put(b, domDistillerService);
        }
        this.I = domDistillerService.f10837a;
        this.H = new HashMap();
        this.M = NumberFormat.getPercentInstance(Locale.getDefault());
    }

    public final RadioButton a(int i, int i2) {
        AbstractC0934Pg1.a(i2);
        RadioButton radioButton = (RadioButton) findViewById(i);
        radioButton.setOnClickListener(new View$OnClickListenerC4377qG(this, i2));
        return radioButton;
    }

    public void b(float f) {
        double d = (double) f;
        this.f10654J.setText(this.M.format(d));
        this.K.setProgress((int) Math.round((d - 0.5d) * 20.0d));
    }

    public void onAttachedToWindow() {
        super.onAttachedToWindow();
        C3864nG nGVar = this.I;
        if (!nGVar.b.containsKey(this)) {
            DistilledPagePrefs$DistilledPagePrefsObserverWrapper distilledPagePrefs$DistilledPagePrefsObserverWrapper = new DistilledPagePrefs$DistilledPagePrefsObserverWrapper(this);
            N.MznRD745(nGVar.f10479a, nGVar, distilledPagePrefs$DistilledPagePrefsObserverWrapper.b);
            nGVar.b.put(this, distilledPagePrefs$DistilledPagePrefsObserverWrapper);
        }
    }

    public void onDetachedFromWindow() {
        super.onDetachedFromWindow();
        C3864nG nGVar = this.I;
        DistilledPagePrefs$DistilledPagePrefsObserverWrapper distilledPagePrefs$DistilledPagePrefsObserverWrapper = (DistilledPagePrefs$DistilledPagePrefsObserverWrapper) nGVar.b.remove(this);
        if (distilledPagePrefs$DistilledPagePrefsObserverWrapper != null) {
            N.M_HmEv0F(nGVar.f10479a, nGVar, distilledPagePrefs$DistilledPagePrefsObserverWrapper.b);
            N.MGXAfNxO(distilledPagePrefs$DistilledPagePrefsObserverWrapper.b);
        }
    }

    public void onFinishInflate() {
        super.onFinishInflate();
        this.G = (RadioGroup) findViewById(R.id.radio_button_group);
        this.H.put(0, a(R.id.light_mode, 0));
        this.H.put(1, a(R.id.dark_mode, 1));
        this.H.put(2, a(R.id.sepia_mode, 2));
        Map map = this.H;
        C3864nG nGVar = this.I;
        ((RadioButton) map.get(Integer.valueOf(N.Mi1cN$gk(nGVar.f10479a, nGVar)))).setChecked(true);
        this.K = (SeekBar) findViewById(R.id.font_size);
        this.f10654J = (TextView) findViewById(R.id.font_size_percentage);
        this.L = (Spinner) findViewById(R.id.font_family);
        C4035oG oGVar = new C4035oG(this, getContext(), 17367048, new String[]{getResources().getString(R.string.f60870_resource_name_obfuscated_RES_2131953404), getResources().getString(R.string.f61330_resource_name_obfuscated_RES_2131953450), getResources().getString(R.string.f55070_resource_name_obfuscated_RES_2131952824)});
        oGVar.setDropDownViewResource(R.layout.f37990_resource_name_obfuscated_RES_2131624108);
        this.L.setAdapter((SpinnerAdapter) oGVar);
        Spinner spinner = this.L;
        C3864nG nGVar2 = this.I;
        spinner.setSelection(N.MSGVGQGo(nGVar2.f10479a, nGVar2));
        this.L.setOnItemSelectedListener(new C4206pG(this));
        C3864nG nGVar3 = this.I;
        b(N.MGNXZIUg(nGVar3.f10479a, nGVar3));
        this.K.setOnSeekBarChangeListener(this);
    }

    public void onMeasure(int i, int i2) {
        this.G.setOrientation(0);
        for (RadioButton radioButton : this.H.values()) {
            radioButton.getLayoutParams().width = 0;
        }
        super.onMeasure(i, i2);
        Iterator it = this.H.values().iterator();
        while (true) {
            if (!it.hasNext()) {
                break;
            } else if (((RadioButton) it.next()).getLineCount() > 1) {
                this.G.setOrientation(1);
                for (RadioButton radioButton2 : this.H.values()) {
                    radioButton2.getLayoutParams().width = -1;
                }
            }
        }
        super.onMeasure(i, i2);
    }

    public void onProgressChanged(SeekBar seekBar, int i, boolean z) {
        float f = (((float) i) / 20.0f) + 0.5f;
        this.f10654J.setText(this.M.format((double) f));
        if (z) {
            C3864nG nGVar = this.I;
            N.MaB$bTgz(nGVar.f10479a, nGVar, f);
        }
    }

    public void onStartTrackingTouch(SeekBar seekBar) {
    }

    public void onStopTrackingTouch(SeekBar seekBar) {
    }
}
