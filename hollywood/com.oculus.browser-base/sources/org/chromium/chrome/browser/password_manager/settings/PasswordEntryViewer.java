package org.chromium.chrome.browser.password_manager.settings;

import J.N;
import android.content.ClipData;
import android.content.ClipboardManager;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageButton;
import android.widget.TextView;
import com.oculus.browser.R;
import java.util.Objects;
import org.chromium.base.ThreadUtils;

/* compiled from: chromium-OculusBrowser.apk-stable-281887347 */
public class PasswordEntryViewer extends AbstractComponentCallbacksC3550lS implements AbstractC4323py0 {
    public ClipboardManager A0;
    public Bundle B0;
    public View C0;
    public boolean D0;
    public boolean E0;
    public boolean F0;
    public int y0;
    public boolean z0;

    @Override // defpackage.AbstractComponentCallbacksC3550lS
    public void B0() {
        this.i0 = true;
        if (AbstractC2852hK0.a(0)) {
            if (this.D0) {
                g1();
            }
            if (this.E0) {
                f1();
            }
        }
        C4834sy0 sy0 = AbstractC4664ry0.f11238a;
        sy0.a(this);
        Objects.requireNonNull(sy0);
        Object obj = ThreadUtils.f10596a;
        PasswordUIView passwordUIView = sy0.F;
        N.MG_PqeQw(passwordUIView.f10742a, passwordUIView);
    }

    public final void e1(int i, int i2, int i3) {
        TextView textView = (TextView) this.C0.findViewById(R.id.password_entry_viewer_password);
        ImageButton imageButton = (ImageButton) this.C0.findViewById(R.id.password_entry_viewer_view_password);
        textView.setText(this.B0.getString("password"));
        textView.setInputType(i2);
        imageButton.setImageResource(i);
        imageButton.setContentDescription(u().getString(i3));
    }

    public final void f1() {
        this.A0.setPrimaryClip(ClipData.newPlainText("password", this.K.getString("password")));
        C1184Ti1.a(u().getApplicationContext(), R.string.f57810_resource_name_obfuscated_RES_2131953098, 0).b.show();
        AbstractC3364kK0.g("PasswordManager.Android.PasswordCredentialEntry.Password", 0, 3);
        AbstractC3364kK0.g("PasswordManager.AccessPasswordInSettings", 1, 2);
    }

    public final void g1() {
        u().getWindow().setFlags(8192, 8192);
        e1(R.drawable.f32930_resource_name_obfuscated_RES_2131231333, 131217, R.string.f57790_resource_name_obfuscated_RES_2131953096);
        AbstractC3364kK0.g("PasswordManager.Android.PasswordCredentialEntry.Password", 1, 3);
        AbstractC3364kK0.g("PasswordManager.AccessPasswordInSettings", 0, 2);
    }

    @Override // defpackage.AbstractComponentCallbacksC3550lS
    public void h0(Bundle bundle) {
        super.h0(bundle);
        V0(true);
    }

    public final void h1() {
        u().getWindow().clearFlags(8192);
        e1(R.drawable.f32920_resource_name_obfuscated_RES_2131231332, 131201, R.string.f57880_resource_name_obfuscated_RES_2131953105);
        AbstractC3364kK0.g("PasswordManager.Android.PasswordCredentialEntry.Password", 2, 3);
    }

    public final void i1() {
        this.A0.setPrimaryClip(ClipData.newPlainText("site", this.K.getString("url")));
        C1184Ti1.a(u().getApplicationContext(), R.string.f57830_resource_name_obfuscated_RES_2131953100, 0).b.show();
        if (this.z0) {
            AbstractC3364kK0.g("PasswordManager.Android.PasswordExceptionEntry.Website", 0, 2);
        } else {
            AbstractC3364kK0.g("PasswordManager.Android.PasswordCredentialEntry.Website", 0, 2);
        }
    }

    @Override // defpackage.AbstractC4323py0
    public void j(int i) {
        if (!this.z0) {
            C4834sy0 sy0 = AbstractC4664ry0.f11238a;
            Objects.requireNonNull(sy0);
            Object obj = ThreadUtils.f10596a;
            PasswordUIView passwordUIView = sy0.F;
            SavedPasswordEntry savedPasswordEntry = (SavedPasswordEntry) N.MkSJC9m5(passwordUIView.f10742a, passwordUIView, this.y0);
            m1(R.id.url_row, savedPasswordEntry.f10743a);
            m1(R.id.username_row, savedPasswordEntry.b);
            ((TextView) this.C0.findViewById(R.id.password_entry_viewer_password)).setText(savedPasswordEntry.c);
        }
    }

    public final void j1() {
        this.A0.setPrimaryClip(ClipData.newPlainText("username", this.K.getString("name")));
        C1184Ti1.a(u().getApplicationContext(), R.string.f57860_resource_name_obfuscated_RES_2131953103, 0).b.show();
        AbstractC3364kK0.g("PasswordManager.Android.PasswordCredentialEntry.Username", 0, 2);
    }

    @Override // defpackage.AbstractComponentCallbacksC3550lS
    public void k0(Menu menu, MenuInflater menuInflater) {
        menuInflater.inflate(R.menu.f42470_resource_name_obfuscated_RES_2131689481, menu);
    }

    public final void k1() {
        if (!AbstractC2852hK0.c(u().getApplicationContext())) {
            C1184Ti1.a(u().getApplicationContext(), R.string.f57820_resource_name_obfuscated_RES_2131953099, 1).b.show();
        } else if (AbstractC2852hK0.a(0)) {
            f1();
        } else {
            this.E0 = true;
            AbstractC2852hK0.b(R.string.f54200_resource_name_obfuscated_RES_2131952737, R.id.password_entry_viewer_interactive, this.W, 0);
        }
    }

    @Override // defpackage.AbstractC4323py0
    public void l(int i) {
    }

    @Override // defpackage.AbstractComponentCallbacksC3550lS
    public View l0(LayoutInflater layoutInflater, ViewGroup viewGroup, Bundle bundle) {
        super.h0(bundle);
        Bundle bundle2 = this.K;
        this.B0 = bundle2;
        this.y0 = bundle2.getInt("id");
        this.F0 = this.B0.getBoolean("found_via_search_args", false);
        String string = this.B0.containsKey("name") ? this.B0.getString("name") : null;
        this.z0 = string == null;
        String string2 = this.B0.getString("url");
        this.A0 = (ClipboardManager) u().getApplicationContext().getSystemService("clipboard");
        View inflate = layoutInflater.inflate(this.z0 ? R.layout.f40340_resource_name_obfuscated_RES_2131624343 : R.layout.f40360_resource_name_obfuscated_RES_2131624345, viewGroup, false);
        this.C0 = inflate.findViewById(R.id.scroll_view);
        u().setTitle(R.string.f57850_resource_name_obfuscated_RES_2131953102);
        this.A0 = (ClipboardManager) u().getApplicationContext().getSystemService("clipboard");
        m1(R.id.url_row, string2);
        this.C0.getViewTreeObserver().addOnScrollChangedListener(new ViewTreeObserver$OnScrollChangedListenerC2699gT0(this.C0, inflate.findViewById(R.id.shadow)));
        ImageButton imageButton = (ImageButton) this.C0.findViewById(R.id.url_row).findViewById(R.id.password_entry_viewer_copy);
        imageButton.setContentDescription(u().getString(R.string.f57740_resource_name_obfuscated_RES_2131953091));
        imageButton.setImageDrawable(AbstractC5544x8.a(u(), R.drawable.f29810_resource_name_obfuscated_RES_2131231021));
        imageButton.setOnClickListener(new View$OnClickListenerC1336Vx0(this));
        if (!this.z0) {
            u().setTitle(R.string.f57850_resource_name_obfuscated_RES_2131953102);
            m1(R.id.username_row, string);
            ImageButton imageButton2 = (ImageButton) this.C0.findViewById(R.id.username_row).findViewById(R.id.password_entry_viewer_copy);
            imageButton2.setImageDrawable(AbstractC5544x8.a(u(), R.drawable.f29810_resource_name_obfuscated_RES_2131231021));
            imageButton2.setContentDescription(u().getString(R.string.f57750_resource_name_obfuscated_RES_2131953092));
            imageButton2.setOnClickListener(new View$OnClickListenerC1275Ux0(this));
            h1();
            ImageButton imageButton3 = (ImageButton) this.C0.findViewById(R.id.password_entry_viewer_copy_password);
            imageButton3.setImageDrawable(AbstractC5544x8.a(u(), R.drawable.f29810_resource_name_obfuscated_RES_2131231021));
            imageButton3.setOnClickListener(new View$OnClickListenerC1397Wx0(this));
            ((ImageButton) this.C0.findViewById(R.id.password_entry_viewer_view_password)).setOnClickListener(new View$OnClickListenerC1458Xx0(this));
            AbstractC3364kK0.g("PasswordManager.Android.PasswordCredentialEntry", 0, 4);
            if (this.F0) {
                AbstractC3364kK0.g("PasswordManager.Android.PasswordCredentialEntry", 3, 4);
            }
        } else {
            u().setTitle(R.string.f61150_resource_name_obfuscated_RES_2131953432);
            AbstractC3364kK0.g("PasswordManager.Android.PasswordExceptionEntry", 0, 4);
        }
        return inflate;
    }

    public final void l1() {
        TextView textView = (TextView) this.C0.findViewById(R.id.password_entry_viewer_password);
        if (!AbstractC2852hK0.c(u().getApplicationContext())) {
            C1184Ti1.a(u().getApplicationContext(), R.string.f57820_resource_name_obfuscated_RES_2131953099, 1).b.show();
        } else if ((textView.getInputType() & 144) == 144) {
            h1();
        } else if (AbstractC2852hK0.a(0)) {
            g1();
        } else {
            this.D0 = true;
            AbstractC2852hK0.b(R.string.f54220_resource_name_obfuscated_RES_2131952739, R.id.password_entry_viewer_interactive, this.W, 0);
        }
    }

    public final void m1(int i, String str) {
        ((TextView) this.C0.findViewById(i).findViewById(R.id.password_entry_viewer_row_data)).setText(str);
    }

    @Override // defpackage.AbstractComponentCallbacksC3550lS
    public boolean u0(MenuItem menuItem) {
        if (menuItem.getItemId() != R.id.action_delete_saved_password) {
            return false;
        }
        C1519Yx0 yx0 = new C1519Yx0(this);
        C4834sy0 sy0 = AbstractC4664ry0.f11238a;
        sy0.a(yx0);
        Object obj = ThreadUtils.f10596a;
        PasswordUIView passwordUIView = sy0.F;
        N.MG_PqeQw(passwordUIView.f10742a, passwordUIView);
        return true;
    }

    @Override // defpackage.AbstractComponentCallbacksC3550lS
    public void w0() {
        this.i0 = true;
        AbstractC4664ry0.f11238a.b(this);
    }
}
