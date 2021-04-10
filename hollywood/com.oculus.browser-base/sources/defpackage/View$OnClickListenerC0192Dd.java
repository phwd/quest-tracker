package defpackage;

import android.content.Context;
import android.view.View;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.TextView;
import com.oculus.browser.R;
import org.chromium.components.autofill.AutofillSuggestion;

/* renamed from: Dd  reason: default package and case insensitive filesystem */
/* compiled from: chromium-OculusBrowser.apk-stable-281887347 */
public class View$OnClickListenerC0192Dd extends FrameLayout implements View.OnClickListener {
    public AbstractC2335eJ F;
    public AbstractC0253Ed G;

    public View$OnClickListenerC0192Dd(C0314Fd fd, Context context, AbstractC2335eJ eJVar, AbstractC0253Ed ed) {
        super(context);
        this.F = eJVar;
        this.G = ed;
        FrameLayout.inflate(context, R.layout.f36910_resource_name_obfuscated_RES_2131624000, this);
        ((TextView) findViewById(R.id.dropdown_label)).setText(((AutofillSuggestion) eJVar).f10810a);
        ImageView imageView = (ImageView) findViewById(R.id.dropdown_icon);
        int i = ((AutofillSuggestion) eJVar).d;
        if (i == 0) {
            imageView.setVisibility(8);
        } else {
            imageView.setImageDrawable(AbstractC5544x8.a(context, i));
        }
        setOnClickListener(this);
    }

    public void onClick(View view) {
        AbstractC0253Ed ed = this.G;
        AbstractC2335eJ eJVar = this.F;
        C3066ie ieVar = (C3066ie) ed;
        int i = 0;
        while (true) {
            if (i >= ieVar.I.size()) {
                i = -1;
                break;
            } else if (((AutofillSuggestion) ieVar.I.get(i)).f == ((AutofillSuggestion) eJVar).f) {
                break;
            } else {
                i++;
            }
        }
        ieVar.H.b(i);
    }
}
