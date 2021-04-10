package org.chromium.chrome.browser.autofill.settings;

import android.content.Context;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.view.inputmethod.InputMethodManager;
import android.widget.AdapterView;
import android.widget.LinearLayout;
import android.widget.Spinner;
import com.oculus.browser.R;
import org.chromium.chrome.browser.profiles.Profile;
import org.chromium.components.browser_ui.widget.FadingEdgeScrollView;

/* compiled from: chromium-OculusBrowser.apk-stable-281887347 */
public abstract class AutofillEditorBase extends AbstractComponentCallbacksC3550lS implements AdapterView.OnItemSelectedListener, View.OnTouchListener, TextWatcher {
    public Context A0;
    public String y0;
    public boolean z0;

    public void afterTextChanged(Editable editable) {
    }

    public void beforeTextChanged(CharSequence charSequence, int i, int i2, int i3) {
    }

    public void e1() {
    }

    public abstract int f1();

    public abstract int g1(boolean z);

    public abstract boolean h1();

    @Override // defpackage.AbstractComponentCallbacksC3550lS
    public void k0(Menu menu, MenuInflater menuInflater) {
        menu.clear();
        menuInflater.inflate(R.menu.f42480_resource_name_obfuscated_RES_2131689482, menu);
        MenuItem findItem = menu.findItem(R.id.delete_menu_id);
        if (findItem != null) {
            boolean z = true;
            if (this.z0 || !(!(this instanceof AutofillServerCardEditor))) {
                z = false;
            }
            findItem.setVisible(z);
        }
    }

    @Override // defpackage.AbstractComponentCallbacksC3550lS
    public View l0(LayoutInflater layoutInflater, ViewGroup viewGroup, Bundle bundle) {
        V0(true);
        this.A0 = viewGroup.getContext();
        Bundle bundle2 = this.K;
        if (bundle2 != null) {
            this.y0 = bundle2.getString("guid");
        }
        if (this.y0 == null) {
            this.y0 = "";
            this.z0 = true;
        } else {
            this.z0 = false;
        }
        u().setTitle(g1(this.z0));
        View inflate = layoutInflater.inflate(R.layout.f36940_resource_name_obfuscated_RES_2131624003, viewGroup, false);
        FadingEdgeScrollView fadingEdgeScrollView = (FadingEdgeScrollView) inflate.findViewById(R.id.scroll_view);
        fadingEdgeScrollView.b(0, 1);
        fadingEdgeScrollView.getViewTreeObserver().addOnScrollChangedListener(new ViewTreeObserver$OnScrollChangedListenerC2699gT0(fadingEdgeScrollView, inflate.findViewById(R.id.shadow)));
        LinearLayout linearLayout = (LinearLayout) fadingEdgeScrollView.findViewById(R.id.content);
        layoutInflater.inflate(f1(), (ViewGroup) linearLayout, true);
        layoutInflater.inflate(R.layout.f36950_resource_name_obfuscated_RES_2131624004, (ViewGroup) linearLayout, true);
        return inflate;
    }

    @Override // android.widget.AdapterView.OnItemSelectedListener
    public void onNothingSelected(AdapterView adapterView) {
    }

    public void onTextChanged(CharSequence charSequence, int i, int i2, int i3) {
    }

    public boolean onTouch(View view, MotionEvent motionEvent) {
        if (view instanceof Spinner) {
            ((InputMethodManager) view.getContext().getSystemService("input_method")).hideSoftInputFromWindow(view.getWindowToken(), 0);
        }
        return false;
    }

    @Override // defpackage.AbstractComponentCallbacksC3550lS
    public boolean u0(MenuItem menuItem) {
        if (menuItem.getItemId() == R.id.delete_menu_id) {
            e1();
            u().finish();
            return true;
        } else if (menuItem.getItemId() != R.id.help_menu_id) {
            return false;
        } else {
            AbstractC0073Be.c(u(), Profile.b());
            return true;
        }
    }
}
