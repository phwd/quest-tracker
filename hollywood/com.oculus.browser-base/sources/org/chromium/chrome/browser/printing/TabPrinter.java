package org.chromium.chrome.browser.printing;

import J.N;
import android.text.TextUtils;
import com.oculus.browser.R;
import java.lang.ref.WeakReference;
import org.chromium.base.ContextUtils;
import org.chromium.chrome.browser.tab.Tab;
import org.chromium.printing.Printable;

/* compiled from: chromium-OculusBrowser.apk-stable-281887347 */
public class TabPrinter implements Printable {

    /* renamed from: a  reason: collision with root package name */
    public final WeakReference f10750a;
    public final String b = ContextUtils.getApplicationContext().getString(R.string.f54770_resource_name_obfuscated_RES_2131952794);
    public final String c = ContextUtils.getApplicationContext().getString(R.string.f52010_resource_name_obfuscated_RES_2131952518);

    public TabPrinter(Tab tab) {
        this.f10750a = new WeakReference(tab);
    }

    public static TabPrinter getPrintable(Tab tab) {
        return new TabPrinter(tab);
    }

    @Override // org.chromium.printing.Printable
    public String a() {
        return this.c;
    }

    @Override // org.chromium.printing.Printable
    public boolean b(int i, int i2) {
        if (!c()) {
            return false;
        }
        return N.MmYX7nWa(((Tab) this.f10750a.get()).l(), i, i2);
    }

    @Override // org.chromium.printing.Printable
    public boolean c() {
        Tab tab = (Tab) this.f10750a.get();
        return tab != null && tab.isInitialized();
    }

    @Override // org.chromium.printing.Printable
    public String getTitle() {
        Tab tab = (Tab) this.f10750a.get();
        if (tab == null || !tab.isInitialized()) {
            return this.b;
        }
        String title = tab.getTitle();
        if (!TextUtils.isEmpty(title)) {
            return title;
        }
        String s = tab.s();
        if (!TextUtils.isEmpty(s)) {
            return s;
        }
        return this.b;
    }
}
