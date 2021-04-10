package defpackage;

import android.view.View;
import android.widget.CheckBox;
import android.widget.ImageView;
import android.widget.TextView;
import com.oculus.browser.R;
import java.util.HashSet;

/* renamed from: w60  reason: default package and case insensitive filesystem */
/* compiled from: chromium-OculusBrowser.apk-stable-281887347 */
public class View$OnClickListenerC5369w60 extends XK0 implements View.OnClickListener {
    public TextView Z;
    public TextView a0;
    public CheckBox b0;
    public ImageView c0;
    public String d0;
    public HashSet e0;

    public View$OnClickListenerC5369w60(A60 a60, View view) {
        super(view);
        view.setOnClickListener(this);
        this.Z = (TextView) view.findViewById(R.id.ui_language_representation);
        this.a0 = (TextView) view.findViewById(R.id.native_language_representation);
        this.b0 = (CheckBox) view.findViewById(R.id.language_ask_checkbox);
        this.c0 = (ImageView) view.findViewById(R.id.device_language_icon);
        this.b0.setOnCheckedChangeListener(new C5199v60(this, a60));
    }

    public void onClick(View view) {
        CheckBox checkBox = this.b0;
        checkBox.setChecked(!checkBox.isChecked());
    }
}
