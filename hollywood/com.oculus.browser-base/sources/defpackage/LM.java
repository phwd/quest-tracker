package defpackage;

import com.oculus.browser.R;
import java.io.File;
import org.chromium.base.ContentUriUtils;
import org.chromium.base.IntStringCallback;

/* renamed from: LM  reason: default package */
/* compiled from: chromium-OculusBrowser.apk-stable-281887347 */
public final /* synthetic */ class LM implements IntStringCallback {

    /* renamed from: a  reason: collision with root package name */
    public final SM f8416a;

    public LM(SM sm) {
        this.f8416a = sm;
    }

    @Override // org.chromium.base.IntStringCallback
    public void onResult(int i, String str) {
        SM sm = this.f8416a;
        sm.c = Integer.valueOf(i);
        if (sm.f8891a != 0) {
            File file = new File(str);
            file.deleteOnExit();
            try {
                sm.b = ContentUriUtils.b(file);
                sm.d();
            } catch (IllegalArgumentException e) {
                sm.b(R.string.f58040_resource_name_obfuscated_RES_2131953121, e.getMessage(), R.string.f63810_resource_name_obfuscated_RES_2131953698, 2);
            }
        }
    }
}
