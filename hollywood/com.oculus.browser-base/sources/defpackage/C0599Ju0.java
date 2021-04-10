package defpackage;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.AccelerateInterpolator;
import android.widget.FrameLayout;
import android.widget.TextView;
import com.oculus.browser.R;
import org.chromium.components.page_info.PageInfoView$ElidedUrlTextView;

/* renamed from: Ju0  reason: default package and case insensitive filesystem */
/* compiled from: chromium-OculusBrowser.apk-stable-281887347 */
public class C0599Ju0 extends FrameLayout {
    public PageInfoView$ElidedUrlTextView F;
    public TextView G;
    public TextView H;
    public final ViewGroup I = ((ViewGroup) findViewById(R.id.page_info_wrapper));

    /* renamed from: J  reason: collision with root package name */
    public final ViewGroup f8324J = ((ViewGroup) findViewById(R.id.page_info_content));
    public View K;
    public final View L = findViewById(R.id.page_info_subpage_header);
    public final TextView M = ((TextView) findViewById(R.id.page_info_subpage_title));

    public C0599Ju0(Context context) {
        super(context);
        LayoutInflater.from(context).inflate(R.layout.f40180_resource_name_obfuscated_RES_2131624327, (ViewGroup) this, true);
    }

    public final void a(View view, C0538Iu0 iu0) {
        if (iu0.g != null) {
            view.setOnClickListener(new View$OnClickListenerC0355Fu0(iu0));
        }
        if (iu0.h != null) {
            view.setOnLongClickListener(new View$OnLongClickListenerC0416Gu0(iu0));
        }
    }

    public final void b(View view, CharSequence charSequence) {
        this.f8324J.removeAllViews();
        this.K = view;
        this.L.setVisibility(charSequence != null ? 0 : 8);
        this.M.setText(charSequence);
        this.f8324J.addView(view);
        if (charSequence == null) {
            charSequence = getResources().getString(R.string.f46290_resource_name_obfuscated_RES_2131951946);
        }
        announceForAccessibility(charSequence);
    }

    public void c(View view, CharSequence charSequence, Runnable runnable) {
        if (this.K == null) {
            b(view, charSequence);
        } else {
            this.I.animate().setDuration(90).alpha(0.0f).setInterpolator(new AccelerateInterpolator()).withEndAction(new RunnableC0477Hu0(this, view, charSequence, runnable));
        }
    }
}
