package defpackage;

import android.text.TextUtils;
import java.util.HashSet;
import java.util.List;
import org.chromium.chrome.browser.video_tutorials.Tutorial;

/* renamed from: Ot1  reason: default package */
/* compiled from: chromium-OculusBrowser.apk-stable-281887347 */
public final /* synthetic */ class Ot1 extends AbstractC0823Nl {
    @Override // org.chromium.base.Callback
    public void onResult(Object obj) {
        HashSet hashSet = new HashSet();
        for (Tutorial tutorial : (List) obj) {
            if (!TextUtils.isEmpty(tutorial.e)) {
                hashSet.add(tutorial.e);
            }
        }
        NU0.f8549a.q("Chrome.VideoTutorials.ShareUrls", hashSet);
    }
}
