package defpackage;

import J.N;
import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.graphics.Rect;
import android.text.SpannableString;
import android.util.DisplayMetrics;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.LinearLayout;
import android.widget.ListAdapter;
import android.widget.ListView;
import android.widget.PopupWindow;
import android.widget.TextView;
import com.oculus.browser.R;
import org.chromium.content.browser.input.TextSuggestionHost;
import org.chromium.ui.base.WindowAndroid;

/* renamed from: N31  reason: default package */
/* compiled from: chromium-OculusBrowser.apk-stable-281887347 */
public abstract class N31 implements AdapterView.OnItemClickListener, PopupWindow.OnDismissListener, View.OnClickListener {
    public final Context F;
    public final TextSuggestionHost G;
    public final View H;
    public WindowAndroid I;

    /* renamed from: J  reason: collision with root package name */
    public Activity f8525J;
    public DisplayMetrics K;
    public PopupWindow L;
    public LinearLayout M;
    public String N;
    public int O;
    public TextView P;
    public TextView Q;
    public ListView R;
    public LinearLayout S;
    public View T = this.M.findViewById(R.id.divider);
    public int U;
    public boolean V;

    public N31(Context context, TextSuggestionHost textSuggestionHost, WindowAndroid windowAndroid, View view) {
        this.F = context;
        this.G = textSuggestionHost;
        this.I = windowAndroid;
        this.H = view;
        PopupWindow popupWindow = new PopupWindow();
        this.L = popupWindow;
        popupWindow.setWidth(-2);
        this.L.setHeight(-2);
        this.L.setBackgroundDrawable(AbstractC3153j7.c(context.getResources(), R.drawable.f29300_resource_name_obfuscated_RES_2131230970));
        this.L.setElevation((float) context.getResources().getDimensionPixelSize(R.dimen.f26000_resource_name_obfuscated_RES_2131166219));
        this.L.setInputMethodMode(2);
        this.L.setFocusable(true);
        this.L.setClippingEnabled(false);
        this.L.setOnDismissListener(this);
        LayoutInflater layoutInflater = (LayoutInflater) context.getSystemService("layout_inflater");
        this.M = (LinearLayout) layoutInflater.inflate(R.layout.f41900_resource_name_obfuscated_RES_2131624499, (ViewGroup) null);
        this.U = context.getResources().getDimensionPixelSize(R.dimen.f26010_resource_name_obfuscated_RES_2131166220);
        ListView listView = (ListView) this.M.findViewById(R.id.suggestionContainer);
        this.R = listView;
        listView.setDivider(null);
        LinearLayout linearLayout = (LinearLayout) layoutInflater.inflate(R.layout.f41920_resource_name_obfuscated_RES_2131624501, (ViewGroup) null);
        this.S = linearLayout;
        this.R.addFooterView(linearLayout, null, false);
        this.R.setAdapter((ListAdapter) new M31(this, null));
        this.R.setOnItemClickListener(this);
        TextView textView = (TextView) this.M.findViewById(R.id.addToDictionaryButton);
        this.P = textView;
        textView.setOnClickListener(this);
        TextView textView2 = (TextView) this.M.findViewById(R.id.deleteButton);
        this.Q = textView2;
        textView2.setOnClickListener(this);
        this.L.setContentView(this.M);
    }

    public abstract void a(int i);

    public abstract Object b(int i);

    public abstract SpannableString c(int i);

    public abstract int d();

    public void e(double d, double d2, String str) {
        int i;
        this.O = d();
        this.N = str;
        Activity activity = (Activity) this.I.s0().get();
        this.f8525J = activity;
        if (activity != null) {
            this.K = activity.getResources().getDisplayMetrics();
        } else {
            this.K = this.F.getResources().getDisplayMetrics();
        }
        Activity activity2 = this.f8525J;
        if (activity2 == null || activity2.isInMultiWindowMode()) {
            i = 0;
        } else {
            Rect rect = new Rect();
            this.f8525J.getWindow().getDecorView().getWindowVisibleDisplayFrame(rect);
            i = rect.top;
        }
        this.S.measure(View.MeasureSpec.makeMeasureSpec(0, 0), View.MeasureSpec.makeMeasureSpec(0, 0));
        int measuredHeight = ((((this.K.heightPixels - i) - this.S.getMeasuredHeight()) - (this.U * 2)) - this.M.getPaddingTop()) - this.M.getPaddingBottom();
        int min = Math.min(this.O, measuredHeight > 0 ? measuredHeight / this.F.getResources().getDimensionPixelSize(R.dimen.f25930_resource_name_obfuscated_RES_2131166212) : 0);
        this.O = min;
        if (min == 0) {
            this.T.setVisibility(8);
        } else {
            this.T.setVisibility(0);
        }
        int paddingRight = this.M.getPaddingRight() + this.M.getPaddingLeft() + AbstractC2417ep1.a(this.R.getAdapter());
        this.M.measure(View.MeasureSpec.makeMeasureSpec(paddingRight, 1073741824), View.MeasureSpec.makeMeasureSpec(this.K.heightPixels, Integer.MIN_VALUE));
        this.L.setWidth(paddingRight);
        int measuredWidth = this.M.getMeasuredWidth();
        int measuredHeight2 = this.M.getMeasuredHeight();
        int[] iArr = new int[2];
        this.H.getLocationInWindow(iArr);
        this.L.showAtLocation(this.H, 0, Math.max(-this.M.getPaddingLeft(), Math.min(this.M.getPaddingRight() + (this.K.widthPixels - measuredWidth), ((int) Math.round(d - ((double) (((float) measuredWidth) / 2.0f)))) + iArr[0])), Math.min((((int) Math.round(d2)) + iArr[1]) - this.M.getPaddingTop(), ((this.K.heightPixels - measuredHeight2) - this.M.getPaddingTop()) - this.U));
    }

    public void onClick(View view) {
        if (view == this.P) {
            Intent intent = new Intent("com.android.settings.USER_DICTIONARY_INSERT");
            intent.putExtra("word", this.N);
            intent.setFlags(intent.getFlags() | 268435456);
            this.F.startActivity(intent);
            TextSuggestionHost textSuggestionHost = this.G;
            N.MpJ8AQhr(textSuggestionHost.F, textSuggestionHost, this.N);
            this.V = true;
            this.L.dismiss();
        } else if (view == this.Q) {
            TextSuggestionHost textSuggestionHost2 = this.G;
            N.MCBTtv2g(textSuggestionHost2.F, textSuggestionHost2);
            this.V = true;
            this.L.dismiss();
        }
    }

    public void onDismiss() {
        this.G.r0(this.V);
        this.V = false;
    }

    @Override // android.widget.AdapterView.OnItemClickListener
    public void onItemClick(AdapterView adapterView, View view, int i, long j) {
        if (i < this.O) {
            a(i);
            this.V = true;
            this.L.dismiss();
        }
    }
}
