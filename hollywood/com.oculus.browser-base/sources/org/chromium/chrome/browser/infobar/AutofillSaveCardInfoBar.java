package org.chromium.chrome.browser.infobar;

import android.graphics.Bitmap;
import com.oculus.browser.R;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import org.chromium.components.infobars.ConfirmInfoBar;
import org.chromium.components.signin.base.AccountInfo;

/* compiled from: chromium-OculusBrowser.apk-stable-281887347 */
public class AutofillSaveCardInfoBar extends ConfirmInfoBar {
    public final List I;

    /* renamed from: J  reason: collision with root package name */
    public final LinkedList f10682J;

    /* JADX INFO: super call moved to the top of the method (can break code semantics) */
    public AutofillSaveCardInfoBar(long j, int i, Bitmap bitmap, String str, String str2, String str3, String str4, boolean z, AccountInfo accountInfo) {
        super(z ? 0 : i, !z ? R.color.f12850_resource_name_obfuscated_RES_2131099975 : 0, bitmap, str, str2, str3, str4);
        this.I = new ArrayList();
        this.f10682J = new LinkedList();
    }

    public static AutofillSaveCardInfoBar create(long j, int i, Bitmap bitmap, String str, String str2, String str3, String str4, boolean z, AccountInfo accountInfo) {
        return new AutofillSaveCardInfoBar(j, i, bitmap, str, str2, str3, str4, z, accountInfo);
    }

    public final void addDetail(int i, String str, String str2) {
        this.I.add(new C3944nm(i, str, str2));
    }

    public final void addLegalMessageLine(String str) {
        this.f10682J.add(new C4943te(str));
    }

    public final void addLinkToLastLegalMessageLine(int i, int i2, String str) {
        ((C4943te) this.f10682J.getLast()).f11355a.add(new C4773se(i, i2, str));
    }

    public final void setDescriptionText(String str) {
    }
}
