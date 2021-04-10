package org.chromium.chrome.browser.download.dialogs;

import android.content.Context;
import android.util.AttributeSet;
import android.widget.CheckBox;
import android.widget.RadioGroup;
import android.widget.ScrollView;
import android.widget.TextView;
import com.oculus.browser.R;
import java.util.Objects;
import org.chromium.chrome.browser.download.DownloadDialogBridge;
import org.chromium.components.browser_ui.widget.RadioButtonWithDescription;

/* compiled from: chromium-OculusBrowser.apk-stable-281887347 */
public class DownloadLaterDialogView extends ScrollView implements RadioGroup.OnCheckedChangeListener {
    public QH F;
    public RadioButtonWithDescription G;
    public RadioButtonWithDescription H;
    public RadioButtonWithDescription I;

    /* renamed from: J  reason: collision with root package name */
    public RadioGroup f10664J;
    public CheckBox K;
    public TextView L;

    public DownloadLaterDialogView(Context context, AttributeSet attributeSet) {
        super(context, attributeSet);
    }

    public Integer a() {
        if (this.K.getVisibility() == 8 || !this.K.isEnabled()) {
            return null;
        }
        return Integer.valueOf(this.K.isChecked() ? 2 : 1);
    }

    public final void b() {
        DownloadDialogBridge downloadDialogBridge = (DownloadDialogBridge) ((NH) this.F).M;
        Objects.requireNonNull(downloadDialogBridge);
        RH.a(12);
        NH nh = downloadDialogBridge.c;
        nh.I.b(nh.K, 3);
        downloadDialogBridge.h(true);
    }

    public void onCheckedChanged(RadioGroup radioGroup, int i) {
        int i2 = 0;
        if (!this.G.e()) {
            if (this.H.e()) {
                i2 = 1;
            } else if (this.I.e()) {
                i2 = 2;
            }
        }
        ((NH) this.F).c(i2);
    }

    public void onFinishInflate() {
        super.onFinishInflate();
        this.G = (RadioButtonWithDescription) findViewById(R.id.download_now);
        this.H = (RadioButtonWithDescription) findViewById(R.id.on_wifi);
        this.I = (RadioButtonWithDescription) findViewById(R.id.choose_date_time);
        RadioGroup radioGroup = (RadioGroup) findViewById(R.id.radio_button_layout);
        this.f10664J = radioGroup;
        radioGroup.setOnCheckedChangeListener(this);
        this.K = (CheckBox) findViewById(R.id.show_again_checkbox);
        this.L = (TextView) findViewById(R.id.edit_location);
    }
}
