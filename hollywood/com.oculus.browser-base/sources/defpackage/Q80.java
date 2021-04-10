package defpackage;

import android.content.Context;
import android.os.Bundle;
import android.os.Handler;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.AnimationUtils;
import android.widget.AdapterView;
import android.widget.FrameLayout;
import android.widget.LinearLayout;
import android.widget.ListAdapter;
import android.widget.ListView;
import android.widget.ProgressBar;
import android.widget.TextView;

/* renamed from: Q80  reason: default package */
/* compiled from: chromium-OculusBrowser.apk-stable-281887347 */
public class Q80 extends AbstractComponentCallbacksC3550lS {
    public final AdapterView.OnItemClickListener A0 = new P80(this);
    public ListAdapter B0;
    public ListView C0;
    public View D0;
    public TextView E0;
    public View F0;
    public View G0;
    public boolean H0;
    public final Handler y0 = new Handler();
    public final Runnable z0 = new O80(this);

    @Override // defpackage.AbstractComponentCallbacksC3550lS
    public void F0(View view, Bundle bundle) {
        e1();
    }

    public final void e1() {
        if (this.C0 == null) {
            View view = this.k0;
            if (view != null) {
                if (view instanceof ListView) {
                    this.C0 = (ListView) view;
                } else {
                    TextView textView = (TextView) view.findViewById(16711681);
                    this.E0 = textView;
                    if (textView == null) {
                        this.D0 = view.findViewById(16908292);
                    } else {
                        textView.setVisibility(8);
                    }
                    this.F0 = view.findViewById(16711682);
                    this.G0 = view.findViewById(16711683);
                    View findViewById = view.findViewById(16908298);
                    if (findViewById instanceof ListView) {
                        ListView listView = (ListView) findViewById;
                        this.C0 = listView;
                        View view2 = this.D0;
                        if (view2 != null) {
                            listView.setEmptyView(view2);
                        }
                    } else if (findViewById == null) {
                        throw new RuntimeException("Your content must have a ListView whose id attribute is 'android.R.id.list'");
                    } else {
                        throw new RuntimeException("Content has view with id attribute 'android.R.id.list' that is not a ListView class");
                    }
                }
                this.H0 = true;
                this.C0.setOnItemClickListener(this.A0);
                ListAdapter listAdapter = this.B0;
                if (listAdapter != null) {
                    this.B0 = null;
                    g1(listAdapter);
                } else if (this.F0 != null) {
                    h1(false, false);
                }
                this.y0.post(this.z0);
                return;
            }
            throw new IllegalStateException("Content view not yet created");
        }
    }

    public void f1() {
    }

    public void g1(ListAdapter listAdapter) {
        boolean z = false;
        boolean z2 = this.B0 != null;
        this.B0 = listAdapter;
        ListView listView = this.C0;
        if (listView != null) {
            listView.setAdapter(listAdapter);
            if (!this.H0 && !z2) {
                if (Q0().getWindowToken() != null) {
                    z = true;
                }
                h1(true, z);
            }
        }
    }

    public final void h1(boolean z, boolean z2) {
        e1();
        View view = this.F0;
        if (view == null) {
            throw new IllegalStateException("Can't be used with a custom content view");
        } else if (this.H0 != z) {
            this.H0 = z;
            if (z) {
                if (z2) {
                    view.startAnimation(AnimationUtils.loadAnimation(x(), 17432577));
                    this.G0.startAnimation(AnimationUtils.loadAnimation(x(), 17432576));
                } else {
                    view.clearAnimation();
                    this.G0.clearAnimation();
                }
                this.F0.setVisibility(8);
                this.G0.setVisibility(0);
                return;
            }
            if (z2) {
                view.startAnimation(AnimationUtils.loadAnimation(x(), 17432576));
                this.G0.startAnimation(AnimationUtils.loadAnimation(x(), 17432577));
            } else {
                view.clearAnimation();
                this.G0.clearAnimation();
            }
            this.F0.setVisibility(0);
            this.G0.setVisibility(8);
        }
    }

    @Override // defpackage.AbstractComponentCallbacksC3550lS
    public View l0(LayoutInflater layoutInflater, ViewGroup viewGroup, Bundle bundle) {
        Context P0 = P0();
        FrameLayout frameLayout = new FrameLayout(P0);
        LinearLayout linearLayout = new LinearLayout(P0);
        linearLayout.setId(16711682);
        linearLayout.setOrientation(1);
        linearLayout.setVisibility(8);
        linearLayout.setGravity(17);
        linearLayout.addView(new ProgressBar(P0, null, 16842874), new FrameLayout.LayoutParams(-2, -2));
        frameLayout.addView(linearLayout, new FrameLayout.LayoutParams(-1, -1));
        FrameLayout frameLayout2 = new FrameLayout(P0);
        frameLayout2.setId(16711683);
        TextView textView = new TextView(P0);
        textView.setId(16711681);
        textView.setGravity(17);
        frameLayout2.addView(textView, new FrameLayout.LayoutParams(-1, -1));
        ListView listView = new ListView(P0);
        listView.setId(16908298);
        listView.setDrawSelectorOnTop(false);
        frameLayout2.addView(listView, new FrameLayout.LayoutParams(-1, -1));
        frameLayout.addView(frameLayout2, new FrameLayout.LayoutParams(-1, -1));
        frameLayout.setLayoutParams(new FrameLayout.LayoutParams(-1, -1));
        return frameLayout;
    }

    @Override // defpackage.AbstractComponentCallbacksC3550lS
    public void n0() {
        this.y0.removeCallbacks(this.z0);
        this.C0 = null;
        this.H0 = false;
        this.G0 = null;
        this.F0 = null;
        this.D0 = null;
        this.E0 = null;
        this.i0 = true;
    }
}
