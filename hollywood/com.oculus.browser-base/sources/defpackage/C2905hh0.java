package defpackage;

import java.util.function.Function;

/* renamed from: hh0  reason: default package and case insensitive filesystem */
/* compiled from: chromium-OculusBrowser.apk-stable-281887347 */
public final /* synthetic */ class C2905hh0 implements Function {
    @Override // java.util.function.Function
    public Object apply(Object obj) {
        String str = (String) obj;
        str.hashCode();
        char c = 65535;
        switch (str.hashCode()) {
            case -2065577523:
                if (str.equals("android.media.intent.category.REMOTE_PLAYBACK")) {
                    c = 0;
                    break;
                }
                break;
            case 956939050:
                if (str.equals("android.media.intent.category.LIVE_AUDIO")) {
                    c = 1;
                    break;
                }
                break;
            case 975975375:
                if (str.equals("android.media.intent.category.LIVE_VIDEO")) {
                    c = 2;
                    break;
                }
                break;
        }
        switch (c) {
            case 0:
                return "android.media.route.feature.REMOTE_PLAYBACK";
            case 1:
                return "android.media.route.feature.LIVE_AUDIO";
            case 2:
                return "android.media.route.feature.LIVE_VIDEO";
            default:
                return str;
        }
    }
}
