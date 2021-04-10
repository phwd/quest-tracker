package defpackage;

import android.content.Context;
import android.content.Intent;
import android.view.inputmethod.InputMethodInfo;
import android.view.inputmethod.InputMethodManager;
import android.view.inputmethod.InputMethodSubtype;
import java.util.ArrayList;
import java.util.List;
import java.util.Locale;
import java.util.Objects;
import java.util.Set;
import org.chromium.base.ApplicationStatus;
import org.chromium.base.ContextUtils;
import org.chromium.base.SysUtils;
import org.chromium.chrome.browser.media.MediaCaptureNotificationService;
import org.chromium.content.browser.ChildProcessLauncherHelperImpl;
import org.chromium.content.browser.LauncherThread;

/* renamed from: FG0  reason: default package */
/* compiled from: chromium-OculusBrowser.apk-stable-281887347 */
public class FG0 implements Runnable {
    public final /* synthetic */ OG0 F;

    public FG0(OG0 og0) {
        this.F = og0;
    }

    public void run() {
        Set k = NU0.f8549a.k("WebRTCNotificationIds", null);
        if (k != null && !k.isEmpty()) {
            Context applicationContext = ContextUtils.getApplicationContext();
            applicationContext.startService(new Intent(applicationContext, MediaCaptureNotificationService.class));
        }
        Objects.requireNonNull(this.F);
        if (!SysUtils.isLowEndDevice()) {
            Context applicationContext2 = ContextUtils.getApplicationContext();
            boolean z = ChildProcessLauncherHelperImpl.f10910a;
            LauncherThread.c.post(new RunnableC0890Oo(applicationContext2));
            ChildProcessLauncherHelperImpl.j = ApplicationStatus.getStateForApplication() == 1 || ApplicationStatus.getStateForApplication() == 2;
            ApplicationStatus.h.b(new C0342Fo());
        }
        Objects.requireNonNull(this.F);
        InputMethodManager inputMethodManager = (InputMethodManager) ContextUtils.getApplicationContext().getSystemService("input_method");
        List<InputMethodInfo> enabledInputMethodList = inputMethodManager.getEnabledInputMethodList();
        ArrayList arrayList = new ArrayList();
        for (InputMethodInfo inputMethodInfo : enabledInputMethodList) {
            for (InputMethodSubtype inputMethodSubtype : inputMethodManager.getEnabledInputMethodSubtypeList(inputMethodInfo, true)) {
                if (inputMethodSubtype.getMode().equals("keyboard")) {
                    String str = inputMethodSubtype.getLocale().split("_")[0];
                    if (!arrayList.contains(str)) {
                        arrayList.add(str);
                    }
                }
            }
        }
        AbstractC3364kK0.d("InputMethod.ActiveCount", arrayList.size());
        InputMethodSubtype currentInputMethodSubtype = inputMethodManager.getCurrentInputMethodSubtype();
        Locale locale = Locale.getDefault();
        if (!(currentInputMethodSubtype == null || currentInputMethodSubtype.getLocale() == null || locale == null)) {
            AbstractC3100ip1.f10165a.a("InputMethod.MatchesSystemLanguage", locale.getLanguage().equalsIgnoreCase(currentInputMethodSubtype.getLocale().split("_")[0]));
        }
    }
}
