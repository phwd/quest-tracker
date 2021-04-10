package defpackage;

import android.view.LayoutInflater;
import android.view.ViewGroup;
import com.oculus.browser.R;
import org.chromium.chrome.browser.app.ChromeActivity;
import org.chromium.chrome.browser.password_manager.PasswordGenerationDialogCustomView;

/* renamed from: ay0  reason: default package and case insensitive filesystem */
/* compiled from: chromium-OculusBrowser.apk-stable-281887347 */
public class C1760ay0 {

    /* renamed from: a  reason: collision with root package name */
    public final C2746gl0 f9503a;
    public final C2102cy0 b = new C2102cy0();
    public final PasswordGenerationDialogCustomView c;
    public UH0 d;

    public C1760ay0(ChromeActivity chromeActivity) {
        this.c = (PasswordGenerationDialogCustomView) LayoutInflater.from(chromeActivity).inflate(R.layout.f40370_resource_name_obfuscated_RES_2131624346, (ViewGroup) null);
        this.f9503a = chromeActivity.l0();
    }
}
