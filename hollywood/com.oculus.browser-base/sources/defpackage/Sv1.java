package defpackage;

import J.N;
import android.app.Activity;
import android.content.Intent;
import android.os.SystemClock;
import com.oculus.browser.R;
import org.chromium.base.PackageManagerUtils;
import org.chromium.base.ThreadUtils;
import org.chromium.chrome.browser.profiles.Profile;
import org.chromium.chrome.browser.profiles.ProfileManager;
import org.chromium.components.prefs.PrefService;
import org.chromium.ui.base.WindowAndroid;

/* renamed from: Sv1  reason: default package */
/* compiled from: chromium-OculusBrowser.apk-stable-281887347 */
public class Sv1 {

    /* renamed from: a  reason: collision with root package name */
    public final Nv1 f8925a;
    public Long b;
    public AbstractC6022zx1 c;
    public Q31 d;
    public Ov1 e = new Ov1();

    public Sv1(Nv1 nv1, Q31 q31) {
        this.f8925a = nv1;
        this.d = q31;
    }

    public boolean a(boolean z) {
        Object obj = ThreadUtils.f10596a;
        if (Tv1.f8995a == null || !z) {
            Tv1.f8995a = Boolean.valueOf(!PackageManagerUtils.c(new Intent("android.speech.action.RECOGNIZE_SPEECH"), 0).isEmpty());
        }
        return Tv1.f8995a.booleanValue();
    }

    public boolean b() {
        PrefService prefService;
        AbstractC4422qa0 qa0 = ((View$OnKeyListenerC0001Aa0) this.f8925a).H;
        if (qa0 == null) {
            return false;
        }
        boolean a2 = qa0.a();
        WindowAndroid windowAndroid = ((View$OnKeyListenerC0001Aa0) this.f8925a).F.P;
        if (windowAndroid == null || a2) {
            return false;
        }
        if (!windowAndroid.hasPermission("android.permission.RECORD_AUDIO") && !windowAndroid.canRequestPermission("android.permission.RECORD_AUDIO")) {
            return false;
        }
        if (AbstractC4226pO.a() && N.M09VlOh_("VoiceSearchAudioCapturePolicy")) {
            if (!ProfileManager.b) {
                prefService = null;
            } else {
                prefService = Wr1.a(Profile.b());
            }
            if (prefService != null && !N.MzIXnlkD(prefService.f10883a, "hardware.audio_capture_enabled")) {
                return false;
            }
        }
        if (((Activity) windowAndroid.s0().get()) == null || !a(true)) {
            return false;
        }
        return true;
    }

    public void c(int i) {
        AbstractC3364kK0.g("VoiceInteraction.FailureEventSource", i, 5);
    }

    public void d(boolean z) {
        AbstractC3100ip1.f10165a.a("VoiceInteraction.VoiceSearchResult", z);
    }

    public final boolean e(WindowAndroid windowAndroid, Intent intent, int i) {
        AbstractC3364kK0.g("VoiceInteraction.StartEventSource", i, 5);
        return windowAndroid.F0(intent, new Pv1(this, i), Integer.valueOf(R.string.f64550_resource_name_obfuscated_RES_2131953772)) >= 0;
    }

    public final boolean f(Activity activity, WindowAndroid windowAndroid, int i) {
        C0122Ca ca = (C0122Ca) this.d.get();
        if (ca == null) {
            return false;
        }
        ca.a();
        ca.a();
        AbstractC3100ip1.f10165a.a("Assistant.VoiceSearch.UserEligibility", false);
        if (ca.N) {
            ca.a();
        }
        return false;
    }

    public final boolean g(Activity activity, WindowAndroid windowAndroid, int i) {
        boolean z;
        if (windowAndroid.hasPermission("android.permission.RECORD_AUDIO")) {
            z = true;
        } else {
            if (!windowAndroid.canRequestPermission("android.permission.RECORD_AUDIO")) {
                ((View$OnKeyListenerC0001Aa0) this.f8925a).F.u();
            } else {
                windowAndroid.i(new String[]{"android.permission.RECORD_AUDIO"}, new Mv1(this, i));
            }
            z = false;
        }
        if (!z) {
            return false;
        }
        Intent intent = new Intent("android.speech.action.RECOGNIZE_SPEECH");
        intent.putExtra("android.speech.extra.LANGUAGE_MODEL", "web_search");
        intent.putExtra("calling_package", activity.getComponentName().flattenToString());
        intent.putExtra("android.speech.extra.WEB_SEARCH_ONLY", true);
        if (e(windowAndroid, intent, i)) {
            return true;
        }
        a(false);
        ((View$OnKeyListenerC0001Aa0) this.f8925a).F.u();
        c(i);
        return false;
    }

    public void h(int i) {
        Activity activity;
        PrefService prefService;
        Object obj = ThreadUtils.f10596a;
        this.b = Long.valueOf(SystemClock.elapsedRealtime());
        WindowAndroid windowAndroid = ((View$OnKeyListenerC0001Aa0) this.f8925a).F.P;
        if (windowAndroid != null && (activity = (Activity) windowAndroid.s0().get()) != null) {
            if (AbstractC4226pO.a() && N.M09VlOh_("VoiceSearchAudioCapturePolicy")) {
                if (!ProfileManager.b) {
                    prefService = null;
                } else {
                    prefService = Wr1.a(Profile.b());
                }
                if (prefService == null || !N.MzIXnlkD(prefService.f10883a, "hardware.audio_capture_enabled")) {
                    return;
                }
            }
            f(activity, windowAndroid, i);
            if (!g(activity, windowAndroid, i)) {
                AbstractC1220Ua0.f("VoiceRecognition", "Couldn't find suitable provider for voice searching", new Object[0]);
            }
        }
    }
}
