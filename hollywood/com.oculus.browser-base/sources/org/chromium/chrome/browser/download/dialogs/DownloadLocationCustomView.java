package org.chromium.chrome.browser.download.dialogs;

import J.N;
import android.content.Context;
import android.util.AttributeSet;
import android.view.ViewGroup;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.ScrollView;
import android.widget.Spinner;
import android.widget.SpinnerAdapter;
import android.widget.TextView;
import com.oculus.browser.R;
import java.util.Objects;
import org.chromium.chrome.browser.download.DownloadDialogBridge;
import org.chromium.components.browser_ui.widget.text.AlertDialogEditText;

/* compiled from: chromium-OculusBrowser.apk-stable-281887347 */
public class DownloadLocationCustomView extends ScrollView implements CompoundButton.OnCheckedChangeListener, AbstractC3525lH {
    public C3696mH F;
    public TextView G;
    public TextView H;
    public AlertDialogEditText I;

    /* renamed from: J  reason: collision with root package name */
    public TextView f10665J;
    public Spinner K;
    public TextView L;
    public CheckBox M;
    public int N;
    public long O;

    public DownloadLocationCustomView(Context context, AttributeSet attributeSet) {
        super(context, attributeSet);
        this.F = new C3696mH(context, this);
    }

    @Override // defpackage.AbstractC3525lH
    public void a() {
    }

    @Override // defpackage.AbstractC3525lH
    public void b() {
        int i;
        C3696mH mHVar = this.F;
        int i2 = mHVar.G;
        int i3 = C3696mH.F;
        if (i2 == -1 || (i = this.N) == 2 || i == 3) {
            i2 = mHVar.c();
        }
        if (this.N == 6) {
            C3696mH mHVar2 = this.F;
            long j = this.O;
            Objects.requireNonNull(mHVar2);
            String M4fixBWD = N.M4fixBWD();
            double d = 0.0d;
            int i4 = -1;
            for (int i5 = 0; i5 < mHVar2.getCount(); i5++) {
                LF lf = (LF) mHVar2.getItem(i5);
                if (lf != null && !M4fixBWD.equals(lf.b)) {
                    double d2 = ((double) (lf.c - j)) / ((double) lf.d);
                    if (d2 > d) {
                        i4 = i5;
                        d = d2;
                    }
                }
            }
            if (i4 != -1) {
                LF lf2 = (LF) mHVar2.getItem(i4);
                mHVar2.G = i4;
                i2 = i4;
            } else {
                mHVar2.a();
                i2 = 0;
            }
        }
        this.K.setAdapter((SpinnerAdapter) this.F);
        this.K.setSelection(i2);
        if (N.M09VlOh_("SmartSuggestionForLargeDownloads")) {
            this.K.setOnItemSelectedListener(new SH(this));
        }
    }

    public final void c() {
        this.H.setVisibility(8);
        ViewGroup.MarginLayoutParams marginLayoutParams = (ViewGroup.MarginLayoutParams) this.G.getLayoutParams();
        marginLayoutParams.bottomMargin = getResources().getDimensionPixelSize(R.dimen.f18760_resource_name_obfuscated_RES_2131165495);
        this.G.setLayoutParams(marginLayoutParams);
    }

    public void onCheckedChanged(CompoundButton compoundButton, boolean z) {
        DownloadDialogBridge.f(z ? 2 : 1);
    }

    public void onFinishInflate() {
        super.onFinishInflate();
        this.G = (TextView) findViewById(R.id.title);
        this.H = (TextView) findViewById(R.id.subtitle);
        this.I = (AlertDialogEditText) findViewById(R.id.file_name);
        this.f10665J = (TextView) findViewById(R.id.file_size);
        this.K = (Spinner) findViewById(R.id.file_location);
        this.L = (TextView) findViewById(R.id.location_available_space);
        this.M = (CheckBox) findViewById(R.id.show_again_checkbox);
    }
}
