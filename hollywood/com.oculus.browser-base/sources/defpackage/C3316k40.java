package defpackage;

import android.app.Activity;
import android.app.Dialog;
import android.graphics.drawable.ColorDrawable;
import android.text.method.LinkMovementMethod;
import android.view.LayoutInflater;
import android.view.ViewGroup;
import android.view.Window;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.ListAdapter;
import android.widget.ListView;
import android.widget.ProgressBar;
import android.widget.TextView;
import com.oculus.browser.R;
import org.chromium.ui.base.DeviceFormFactor;
import org.chromium.ui.widget.TextViewWithClickableSpans;

/* renamed from: k40  reason: default package and case insensitive filesystem */
/* compiled from: chromium-OculusBrowser.apk-stable-281887347 */
public class C3316k40 extends AbstractC5732yE {

    /* renamed from: a  reason: collision with root package name */
    public Activity f10258a;
    public Dialog b;
    public AbstractC3145j40 c;
    public TextViewWithClickableSpans d;
    public TextViewWithClickableSpans e;
    public ProgressBar f;
    public ListView g;
    public TextView h;
    public Button i;
    public C2975i40 j;
    public AE k;
    public boolean l = false;

    public C3316k40(Activity activity, AbstractC3145j40 j40, C2975i40 i40) {
        this.f10258a = activity;
        this.c = j40;
        this.j = i40;
        LinearLayout linearLayout = (LinearLayout) LayoutInflater.from(activity).inflate(R.layout.f38870_resource_name_obfuscated_RES_2131624196, (ViewGroup) null);
        this.g = (ListView) linearLayout.findViewById(R.id.items);
        this.f = (ProgressBar) linearLayout.findViewById(R.id.progress);
        this.h = (TextView) linearLayout.findViewById(R.id.status);
        this.d = (TextViewWithClickableSpans) linearLayout.findViewById(R.id.dialog_title);
        this.e = (TextViewWithClickableSpans) linearLayout.findViewById(R.id.not_found_message);
        this.d.setText(i40.f10119a);
        this.d.setMovementMethod(LinkMovementMethod.getInstance());
        this.e.setMovementMethod(LinkMovementMethod.getInstance());
        this.h.setMovementMethod(LinkMovementMethod.getInstance());
        Button button = (Button) linearLayout.findViewById(R.id.positive);
        this.i = button;
        button.setText(i40.g);
        this.i.setEnabled(false);
        View$OnClickListenerC2633g40 g40 = new View$OnClickListenerC2633g40(this);
        AE ae = new AE(this.f10258a, true, R.layout.f38880_resource_name_obfuscated_RES_2131624197);
        this.k = ae;
        ae.setNotifyOnChange(true);
        this.k.N = this;
        this.i.setOnClickListener(g40);
        this.g.setOnItemClickListener(this.k);
        this.g.setAdapter((ListAdapter) this.k);
        this.g.setChoiceMode(1);
        this.g.setEmptyView(this.e);
        this.g.setDivider(null);
        c(1);
        DialogC2804h40 h40 = new DialogC2804h40(this, this.f10258a);
        this.b = h40;
        h40.requestWindowFeature(1);
        this.b.setCanceledOnTouchOutside(true);
        this.b.addContentView(linearLayout, new LinearLayout.LayoutParams(-1, -1));
        this.b.setOnCancelListener(new DialogInterface$OnCancelListenerC2462f40(this));
        Window window = this.b.getWindow();
        if (!DeviceFormFactor.a(this.f10258a)) {
            window.setBackgroundDrawable(new ColorDrawable(-1));
            window.setGravity(48);
            window.setLayout(-1, -2);
        }
        this.b.show();
        linearLayout.addOnLayoutChangeListener(new View$OnLayoutChangeListenerC2291e40(this, linearLayout));
    }

    public void a() {
        this.k.clear();
        c(1);
    }

    public void b(CharSequence charSequence, CharSequence charSequence2) {
        this.g.setVisibility(8);
        this.f.setVisibility(8);
        this.e.setText(charSequence);
        this.e.setVisibility(0);
        this.h.setText(charSequence2);
    }

    public final void c(int i2) {
        int i3 = 0;
        if (i2 != 0) {
            if (i2 == 1) {
                this.h.setText(this.j.b);
            } else if (i2 == 2) {
                this.h.setText(this.j.d);
                this.f.setVisibility(8);
                this.g.setVisibility(0);
                return;
            } else if (i2 == 3) {
                boolean isEmpty = this.k.isEmpty();
                this.h.setText(isEmpty ? this.j.e : this.j.f);
                this.e.setText(this.j.c);
                TextViewWithClickableSpans textViewWithClickableSpans = this.e;
                if (!isEmpty) {
                    i3 = 8;
                }
                textViewWithClickableSpans.setVisibility(i3);
                return;
            } else {
                return;
            }
        }
        this.g.setVisibility(8);
        this.f.setVisibility(0);
        this.e.setVisibility(8);
    }
}
