package defpackage;

import android.animation.Animator;
import android.animation.AnimatorSet;
import android.animation.ObjectAnimator;
import android.app.Activity;
import android.content.DialogInterface;
import android.graphics.drawable.ColorDrawable;
import android.os.Handler;
import android.text.InputFilter;
import android.text.TextWatcher;
import android.view.LayoutInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.Spinner;
import android.widget.TextView;
import com.oculus.browser.R;
import java.util.ArrayList;
import java.util.List;
import java.util.regex.Pattern;
import org.chromium.chrome.browser.autofill.prefeditor.EditorDialogToolbar;
import org.chromium.chrome.browser.profiles.Profile;
import org.chromium.components.browser_ui.widget.FadingEdgeScrollView;

/* renamed from: nK  reason: default package and case insensitive filesystem */
/* compiled from: chromium-OculusBrowser.apk-stable-281887347 */
public class View$OnClickListenerC3876nK extends K4 implements View.OnClickListener, DialogInterface.OnShowListener, DialogInterface.OnDismissListener {
    public static final /* synthetic */ int F = 0;
    public final Activity G;
    public final Handler H = new Handler();
    public final TextView.OnEditorActionListener I = new C2509fK(this);

    /* renamed from: J  reason: collision with root package name */
    public final int f10483J;
    public final List K;
    public final List L;
    public final List M;
    public final InputFilter N;
    public final TextWatcher O;
    public TextWatcher P;
    public View Q;
    public C5409wK R;
    public Button S;
    public boolean T;
    public ViewGroup U;
    public View V;
    public TextView W;
    public TextView X;
    public Animator Y;
    public Runnable Z;
    public boolean a0 = false;
    public Profile b0;

    public View$OnClickListenerC3876nK(Activity activity, Runnable runnable, Profile profile) {
        super(activity, R.style.f72740_resource_name_obfuscated_RES_2132017847);
        getWindow().setBackgroundDrawable(new ColorDrawable(0));
        this.G = activity;
        this.f10483J = activity.getResources().getDimensionPixelSize(R.dimen.f19000_resource_name_obfuscated_RES_2131165519);
        this.K = new ArrayList();
        this.L = new ArrayList();
        this.M = new ArrayList();
        this.N = new C2680gK(this, Pattern.compile("^[\\d- ]*$"));
        this.O = new C4362qB();
        this.Z = runnable;
        this.b0 = profile;
    }

    /* JADX DEBUG: Multi-variable search result rejected for r1v7, resolved type: android.widget.CheckBox */
    /* JADX WARN: Multi-variable type inference failed */
    /* JADX WARNING: Removed duplicated region for block: B:22:0x011e  */
    /* JADX WARNING: Removed duplicated region for block: B:23:0x0121  */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public final android.view.View a(android.view.ViewGroup r12, defpackage.C4729sK r13) {
        /*
        // Method dump skipped, instructions count: 298
        */
        throw new UnsupportedOperationException("Method not decompiled: defpackage.View$OnClickListenerC3876nK.a(android.view.ViewGroup, sK):android.view.View");
    }

    public final void b() {
        if (this.Y == null && isShowing()) {
            View view = this.Q;
            ObjectAnimator ofFloat = ObjectAnimator.ofFloat(view, View.TRANSLATION_Y, 0.0f, (float) view.getHeight());
            View view2 = this.Q;
            ObjectAnimator ofFloat2 = ObjectAnimator.ofFloat(view2, View.ALPHA, view2.getAlpha(), 0.0f);
            AnimatorSet animatorSet = new AnimatorSet();
            animatorSet.playTogether(ofFloat, ofFloat2);
            this.Y = animatorSet;
            animatorSet.setDuration(195L);
            this.Y.setInterpolator(G30.b);
            this.Y.addListener(new C3192jK(this));
            this.Y.start();
        }
    }

    public final List c(boolean z) {
        ArrayList arrayList = new ArrayList();
        for (int i = 0; i < this.K.size(); i++) {
            AbstractC4899tK tKVar = (AbstractC4899tK) this.K.get(i);
            if (!tKVar.a()) {
                arrayList.add(tKVar);
                if (!z) {
                    break;
                }
            }
        }
        return arrayList;
    }

    public final void d() {
        f();
        ViewGroup viewGroup = (ViewGroup) this.Q.findViewById(R.id.contents);
        this.U = viewGroup;
        viewGroup.removeAllViews();
        this.K.clear();
        this.L.clear();
        this.M.clear();
        int i = 0;
        while (i < this.R.b.size()) {
            C4729sK sKVar = (C4729sK) this.R.b.get(i);
            C4729sK sKVar2 = null;
            boolean z = i == this.R.b.size() - 1;
            boolean z2 = sKVar.z;
            if (!z && !z2) {
                sKVar2 = (C4729sK) this.R.b.get(i + 1);
                if (sKVar2.z) {
                    z2 = true;
                }
            }
            if (!z && !z2) {
                if ((sKVar.f11266a == 9) != (sKVar2.f11266a == 9)) {
                    z2 = true;
                }
            }
            if (z2 || z) {
                a(this.U, sKVar);
            } else {
                LinearLayout linearLayout = new LinearLayout(this.G);
                this.U.addView(linearLayout);
                View a2 = a(linearLayout, sKVar);
                View a3 = a(linearLayout, sKVar2);
                LinearLayout.LayoutParams layoutParams = (LinearLayout.LayoutParams) a2.getLayoutParams();
                LinearLayout.LayoutParams layoutParams2 = (LinearLayout.LayoutParams) a3.getLayoutParams();
                layoutParams.width = 0;
                layoutParams.weight = 1.0f;
                layoutParams.setMarginEnd(this.f10483J);
                layoutParams2.width = 0;
                layoutParams2.weight = 1.0f;
                i++;
            }
            i++;
        }
        this.U.addView(this.V);
    }

    public final void e() {
        TextView textView = (TextView) this.Q.findViewById(R.id.required_fields_notice);
        int i = 0;
        int i2 = 0;
        while (true) {
            if (i2 >= this.K.size()) {
                i = 8;
                break;
            } else if (((AbstractC4899tK) this.K.get(i2)).b()) {
                break;
            } else {
                i2++;
            }
        }
        textView.setVisibility(i);
    }

    public final void f() {
        TextView textView = this.W;
        if (textView != null) {
            textView.removeTextChangedListener(this.O);
            this.W.setFilters(new InputFilter[0]);
            this.W = null;
        }
        TextView textView2 = this.X;
        if (textView2 != null) {
            textView2.removeTextChangedListener(this.P);
            this.X = null;
        }
    }

    public void g(C5409wK wKVar) {
        if (!this.G.isFinishing()) {
            setOnShowListener(this);
            setOnDismissListener(this);
            this.R = wKVar;
            View inflate = LayoutInflater.from(this.G).inflate(R.layout.f40510_resource_name_obfuscated_RES_2131624360, (ViewGroup) null);
            this.Q = inflate;
            setContentView(inflate);
            this.V = LayoutInflater.from(this.G).inflate(R.layout.f38340_resource_name_obfuscated_RES_2131624143, (ViewGroup) null, false);
            EditorDialogToolbar editorDialogToolbar = (EditorDialogToolbar) this.Q.findViewById(R.id.action_bar);
            editorDialogToolbar.setBackgroundColor(editorDialogToolbar.getResources().getColor(R.color.f10840_resource_name_obfuscated_RES_2131099774));
            editorDialogToolbar.J(editorDialogToolbar.getContext(), R.style.f71300_resource_name_obfuscated_RES_2132017703);
            editorDialogToolbar.I(this.R.f11539a);
            editorDialogToolbar.v0 = this.Z != null;
            MenuItem findItem = ((C4616ri0) editorDialogToolbar.p()).findItem(R.id.delete_menu_id);
            if (findItem != null) {
                findItem.setVisible(editorDialogToolbar.v0);
            }
            editorDialogToolbar.o0 = new C2851hK(this);
            editorDialogToolbar.B(R.string.f48470_resource_name_obfuscated_RES_2131952164);
            editorDialogToolbar.D(C0636Ki1.b(getContext(), R.drawable.f29550_resource_name_obfuscated_RES_2131230995, R.color.f11220_resource_name_obfuscated_RES_2131099812));
            View$OnClickListenerC3022iK iKVar = new View$OnClickListenerC3022iK(this);
            editorDialogToolbar.h();
            editorDialogToolbar.I.setOnClickListener(iKVar);
            FadingEdgeScrollView fadingEdgeScrollView = (FadingEdgeScrollView) this.Q.findViewById(R.id.scroll_view);
            fadingEdgeScrollView.b(0, 1);
            View findViewById = this.Q.findViewById(R.id.shadow);
            RelativeLayout.LayoutParams layoutParams = (RelativeLayout.LayoutParams) findViewById.getLayoutParams();
            layoutParams.topMargin = editorDialogToolbar.getLayoutParams().height;
            findViewById.setLayoutParams(layoutParams);
            fadingEdgeScrollView.getViewTreeObserver().addOnScrollChangedListener(new ViewTreeObserver$OnScrollChangedListenerC2699gT0(fadingEdgeScrollView, findViewById));
            d();
            e();
            Button button = (Button) this.Q.findViewById(R.id.button_primary);
            this.S = button;
            button.setId(R.id.editor_dialog_done_button);
            this.S.setOnClickListener(this);
            Button button2 = (Button) this.Q.findViewById(R.id.button_secondary);
            button2.setId(R.id.payments_edit_cancel_button);
            button2.setOnClickListener(this);
            show();
        }
    }

    public boolean h() {
        AbstractC4899tK tKVar;
        List c = c(true);
        for (int i = 0; i < this.K.size(); i++) {
            AbstractC4899tK tKVar2 = (AbstractC4899tK) this.K.get(i);
            tKVar2.c(((ArrayList) c).contains(tKVar2));
        }
        ArrayList arrayList = (ArrayList) c;
        if (!arrayList.isEmpty()) {
            View currentFocus = getCurrentFocus();
            if (!(currentFocus instanceof TextView) || currentFocus.getParent() == null || !(currentFocus.getParent() instanceof AbstractC4899tK)) {
                tKVar = (!(currentFocus instanceof Spinner) || currentFocus.getTag() == null) ? null : (AbstractC4899tK) currentFocus.getTag();
            } else {
                tKVar = (AbstractC4899tK) currentFocus.getParent();
            }
            if (arrayList.contains(tKVar)) {
                tKVar.d();
            } else {
                ((AbstractC4899tK) arrayList.get(0)).d();
            }
        }
        arrayList.isEmpty();
        return arrayList.isEmpty();
    }

    public void onClick(View view) {
        if (this.Y == null) {
            if (view.getId() == R.id.editor_dialog_done_button) {
                if (h()) {
                    this.T = true;
                    b();
                }
            } else if (view.getId() == R.id.payments_edit_cancel_button) {
                b();
            }
        }
    }

    public void onDismiss(DialogInterface dialogInterface) {
        this.a0 = true;
        C5409wK wKVar = this.R;
        if (wKVar != null) {
            if (this.T) {
                Runnable runnable = wKVar.c;
                if (runnable != null) {
                    runnable.run();
                }
                wKVar.c = null;
                wKVar.d = null;
                this.T = false;
            } else {
                Runnable runnable2 = wKVar.d;
                if (runnable2 != null) {
                    runnable2.run();
                }
                wKVar.c = null;
                wKVar.d = null;
            }
            this.R = null;
        }
        f();
    }

    public void onShow(DialogInterface dialogInterface) {
        if (this.Y == null || !this.a0) {
            if (getCurrentFocus() != null) {
                C3493l60.F.d(getCurrentFocus());
            }
            for (int i = 0; i < this.L.size(); i++) {
                ((EditText) this.L.get(i)).setEnabled(false);
            }
            this.Q.setLayerType(2, null);
            this.Q.buildLayer();
            View view = this.Q;
            ObjectAnimator ofFloat = ObjectAnimator.ofFloat(view, View.TRANSLATION_Y, (float) view.getHeight(), 0.0f);
            ObjectAnimator ofFloat2 = ObjectAnimator.ofFloat(this.Q, View.ALPHA, 0.0f, 1.0f);
            AnimatorSet animatorSet = new AnimatorSet();
            animatorSet.playTogether(ofFloat, ofFloat2);
            this.Y = animatorSet;
            animatorSet.setDuration(300L);
            this.Y.setInterpolator(G30.e);
            this.Y.addListener(new C3705mK(this));
            this.Y.start();
        }
    }
}
