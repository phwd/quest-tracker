package org.chromium.components.messages;

import android.content.Context;
import android.util.AttributeSet;
import android.view.MotionEvent;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;
import com.oculus.browser.R;
import java.util.HashMap;
import java.util.Map;
import org.chromium.components.browser_ui.widget.BoundedLinearLayout;
import org.chromium.components.browser_ui.widget.listmenu.ListMenuButton;

/* compiled from: chromium-OculusBrowser.apk-stable-281887347 */
public class MessageBannerView extends BoundedLinearLayout {
    public ImageView I;

    /* renamed from: J  reason: collision with root package name */
    public TextView f10853J;
    public TextView K;
    public TextView L;
    public ListMenuButton M;
    public View N;
    public String O;
    public Runnable P;
    public AbstractC5364w41 Q;

    public MessageBannerView(Context context, AttributeSet attributeSet) {
        super(context, attributeSet);
    }

    public final void a() {
        Map c = UH0.c(Y80.h);
        TH0 th0 = Y80.b;
        String str = this.O;
        LH0 lh0 = new LH0(null);
        lh0.f8415a = str;
        HashMap hashMap = (HashMap) c;
        hashMap.put(th0, lh0);
        QH0 qh0 = Y80.g;
        GH0 gh0 = new GH0(null);
        gh0.f8081a = true;
        hashMap.put(qh0, gh0);
        UH0 uh0 = new UH0(c, null);
        C4935tb0 tb0 = new C4935tb0();
        tb0.q(new C4765sb0(1, uh0));
        C5129uj0 uj0 = new C5129uj0(this, new C1237Ug(getContext(), tb0, new C4959tj0(this, uh0)));
        ListMenuButton listMenuButton = this.M;
        listMenuButton.d();
        listMenuButton.M = uj0;
        this.M.e();
    }

    public void onFinishInflate() {
        super.onFinishInflate();
        this.f10853J = (TextView) findViewById(R.id.message_title);
        this.K = (TextView) findViewById(R.id.message_description);
        this.L = (TextView) findViewById(R.id.message_primary_button);
        this.I = (ImageView) findViewById(R.id.message_icon);
        this.M = (ListMenuButton) findViewById(R.id.message_secondary_button);
        this.N = findViewById(R.id.message_divider);
        this.M.setOnClickListener(new View$OnClickListenerC4789sj0(this));
    }

    public boolean onTouchEvent(MotionEvent motionEvent) {
        AbstractC5364w41 w41 = this.Q;
        if (w41 != null) {
            return w41.a(motionEvent) || super.onTouchEvent(motionEvent);
        }
        return super.onTouchEvent(motionEvent);
    }
}
