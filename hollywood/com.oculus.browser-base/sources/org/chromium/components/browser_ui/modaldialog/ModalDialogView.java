package org.chromium.components.browser_ui.modaldialog;

import android.content.Context;
import android.text.TextUtils;
import android.util.AttributeSet;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import com.oculus.browser.R;
import org.chromium.base.Callback;
import org.chromium.components.browser_ui.widget.BoundedLinearLayout;
import org.chromium.components.browser_ui.widget.FadingEdgeScrollView;

/* compiled from: chromium-OculusBrowser.apk-stable-281887347 */
public class ModalDialogView extends BoundedLinearLayout implements View.OnClickListener {
    public static final /* synthetic */ int I = 0;

    /* renamed from: J  reason: collision with root package name */
    public FadingEdgeScrollView f10815J;
    public ViewGroup K;
    public TextView L;
    public ImageView M;
    public TextView N;
    public ViewGroup O;
    public View P;
    public Button Q;
    public Button R;
    public Callback S;
    public boolean T;
    public boolean U;
    public boolean V;
    public Runnable W;

    public ModalDialogView(Context context, AttributeSet attributeSet) {
        super(context, attributeSet);
    }

    public final Button a(int i) {
        if (i == 0) {
            return this.Q;
        }
        if (i != 1) {
            return null;
        }
        return this.R;
    }

    public final boolean b(MotionEvent motionEvent) {
        boolean z;
        Object e;
        try {
            int i = 1;
            z = (MotionEvent.class.getField("FLAG_WINDOW_IS_PARTIALLY_OBSCURED").getInt(null) & motionEvent.getFlags()) != 0;
            try {
                if (motionEvent.getAction() == 0 && !this.V) {
                    this.V = true;
                    if (!z) {
                        i = 0;
                    }
                    AbstractC3364kK0.g("Android.ModalDialog.SecurityFilteredTouchResult", i, 2);
                }
                if (z && this.W != null && motionEvent.getAction() == 0) {
                    this.W.run();
                }
            } catch (IllegalAccessException | NoSuchFieldException e2) {
                e = e2;
                AbstractC1220Ua0.a("ModalDialogView", "Reflection failure: " + e, new Object[0]);
                return z;
            }
        } catch (IllegalAccessException | NoSuchFieldException e3) {
            e = e3;
            z = false;
            AbstractC1220Ua0.a("ModalDialogView", "Reflection failure: " + e, new Object[0]);
            return z;
        }
        return z;
    }

    public final void c() {
        boolean z = true;
        boolean z2 = !TextUtils.isEmpty(this.Q.getText());
        boolean z3 = !TextUtils.isEmpty(this.R.getText());
        int i = 0;
        if (!z2 && !z3) {
            z = false;
        }
        this.Q.setVisibility(z2 ? 0 : 8);
        this.R.setVisibility(z3 ? 0 : 8);
        View view = this.P;
        if (!z) {
            i = 8;
        }
        view.setVisibility(i);
    }

    public final void d() {
        boolean z = true;
        boolean z2 = !TextUtils.isEmpty(this.L.getText());
        int i = 0;
        boolean z3 = this.M.getDrawable() != null;
        boolean z4 = z2 || z3;
        boolean z5 = !TextUtils.isEmpty(this.N.getText());
        if ((!this.T || !z4) && !z5) {
            z = false;
        }
        this.L.setVisibility(z2 ? 0 : 8);
        this.M.setVisibility(z3 ? 0 : 8);
        this.K.setVisibility(z4 ? 0 : 8);
        this.N.setVisibility(z5 ? 0 : 8);
        FadingEdgeScrollView fadingEdgeScrollView = this.f10815J;
        if (!z) {
            i = 8;
        }
        fadingEdgeScrollView.setVisibility(i);
    }

    public void onClick(View view) {
        if (view == this.Q) {
            this.S.onResult(0);
        } else if (view == this.R) {
            this.S.onResult(1);
        }
    }

    public void onFinishInflate() {
        super.onFinishInflate();
        this.f10815J = (FadingEdgeScrollView) findViewById(R.id.modal_dialog_scroll_view);
        ViewGroup viewGroup = (ViewGroup) findViewById(R.id.title_container);
        this.K = viewGroup;
        this.L = (TextView) viewGroup.findViewById(R.id.title);
        this.M = (ImageView) this.K.findViewById(R.id.title_icon);
        this.N = (TextView) findViewById(R.id.message);
        this.O = (ViewGroup) findViewById(R.id.custom);
        this.P = findViewById(R.id.button_bar);
        this.Q = (Button) findViewById(R.id.positive_button);
        this.R = (Button) findViewById(R.id.negative_button);
        this.Q.setOnClickListener(this);
        this.R.setOnClickListener(this);
        d();
        c();
        this.f10815J.addOnLayoutChangeListener(new View$OnLayoutChangeListenerC3429kl0());
    }
}
