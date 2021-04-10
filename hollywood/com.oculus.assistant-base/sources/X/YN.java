package X;

import android.app.NotificationManager;
import android.content.Context;
import com.oculus.assistant.R;
import com.oculus.assistant.config.AssistantDeviceSettings;
import com.oculus.os.Version;
import com.oculus.os.VoiceAssistantManager;
import java.lang.reflect.InvocationTargetException;

public final class YN {
    public static boolean A02() {
        try {
            return "running".equals(Class.forName("android.os.SystemProperties").getMethod("get", String.class).invoke(null, "init.svc.voiceassistantserver"));
        } catch (ClassNotFoundException | IllegalAccessException | NoSuchMethodException | InvocationTargetException e) {
            C0139Dd.A0L("SystemProperties", e.getMessage(), e);
            throw new Vw(e);
        }
    }

    public static boolean A03() {
        boolean isWakeWordEnabled = AssistantDeviceSettings.INSTANCE.isWakeWordEnabled();
        boolean z = W0.A00().A00.getBoolean("enable_assistant_wakeword", false);
        if (Version.CURRENT_SDK_VERSION < 52 || !isWakeWordEnabled || !z) {
            return false;
        }
        return true;
    }

    public static void A00() {
        if (A02()) {
            new VoiceAssistantManager().getMicData();
        }
    }

    public static void A01(Context context) {
        if (A02()) {
            new VoiceAssistantManager().stopWakeWordRecognition();
            AnonymousClass1C r2 = new AnonymousClass1C(context);
            r2.A07 = AnonymousClass1C.A00(context.getString(R.string.notif_title_wakeword_disabled));
            r2.A06 = AnonymousClass1C.A00(context.getString(R.string.notif_text_wakeword_disabled));
            r2.A00 = 1;
            r2.A01.icon = R.drawable.ic_voice_assistant;
            ((NotificationManager) context.getSystemService("notification")).notify(1230, new C0638dd(r2).A00());
        }
    }

    public static boolean A04() {
        if (!A02() || !A03()) {
            return false;
        }
        return true;
    }
}
