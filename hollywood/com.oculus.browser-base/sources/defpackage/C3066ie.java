package defpackage;

import android.content.Context;
import android.view.View;
import android.widget.AdapterView;
import android.widget.PopupWindow;
import com.oculus.browser.R;
import java.util.List;
import org.chromium.components.autofill.AutofillSuggestion;

/* renamed from: ie  reason: default package and case insensitive filesystem */
/* compiled from: chromium-OculusBrowser.apk-stable-281887347 */
public class C3066ie extends BJ implements AdapterView.OnItemClickListener, AdapterView.OnItemLongClickListener, PopupWindow.OnDismissListener, AbstractC0253Ed {
    public final Context G;
    public final AbstractC0070Bd H;
    public List I;

    /* renamed from: J  reason: collision with root package name */
    public final Runnable f10151J = new RunnableC2724ge(this);

    public C3066ie(Context context, View view, AbstractC0070Bd bd) {
        super(context, view);
        this.G = context;
        this.H = bd;
        ((EJ) this.F).O.setOnItemClickListener(this);
        ((EJ) this.F).L.P.b(this);
        O4 o4 = ((EJ) this.F).L;
        o4.O = false;
        o4.K.setOutsideTouchable(false);
        ((EJ) this.F).K = context.getString(R.string.f47540_resource_name_obfuscated_RES_2131952071);
    }

    public void onDismiss() {
        this.H.d();
    }

    @Override // android.widget.AdapterView.OnItemClickListener
    public void onItemClick(AdapterView adapterView, View view, int i, long j) {
        this.H.b(this.I.indexOf(((C0131Cd) adapterView.getAdapter()).getItem(i)));
    }

    @Override // android.widget.AdapterView.OnItemLongClickListener
    public boolean onItemLongClick(AdapterView adapterView, View view, int i, long j) {
        AutofillSuggestion autofillSuggestion = (AutofillSuggestion) ((C0131Cd) adapterView.getAdapter()).getItem(i);
        if (!autofillSuggestion.g) {
            return false;
        }
        this.H.a(this.I.indexOf(autofillSuggestion));
        return true;
    }
}
