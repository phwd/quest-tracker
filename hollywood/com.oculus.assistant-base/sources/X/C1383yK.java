package X;

import android.content.Intent;
import android.content.IntentFilter;
import android.content.SharedPreferences;
import android.media.AudioManager;
import android.os.Environment;
import android.os.StatFs;
import android.util.Base64;
import com.facebook.common.stringformat.StringFormatUtil;
import com.facebook.debug.tracer.Tracer;
import com.facebook.messenger.assistant.thrift.DeviceContext;
import com.oculus.assistant.config.AssistantDeviceSettings;
import com.oculus.os.SettingsManager;
import java.util.List;
import java.util.Locale;
import java.util.TimeZone;
import org.json.JSONException;
import org.json.JSONObject;

/* renamed from: X.yK  reason: case insensitive filesystem */
public final class C1383yK implements AnonymousClass8c {
    public static final byte[] A06 = StringFormatUtil.formatStrLocaleSafe("{\"%s\": true}", "passthrough_on_demand_enabled").getBytes();
    public AnonymousClass8J A00;
    public List A01;
    public StatFs A02;
    public final SharedPreferences A03;
    public final SettingsManager A04;
    public final String A05 = Environment.getExternalStorageDirectory().getPath();

    /* JADX INFO: finally extract failed */
    public final synchronized C0873lz A01() {
        C0873lz lzVar;
        int i;
        int i2;
        Tracer.A01("getDeviceContextBuilder");
        try {
            AnonymousClass8J r0 = this.A00;
            if (r0 != null) {
                AnonymousClass8I r3 = new AnonymousClass8I(r0);
                try {
                    Tracer.A01("updateContext");
                    Tracer.A01("updateBatteryLevel");
                    Intent registerReceiver = BX.A00().registerReceiver(null, new IntentFilter("android.intent.action.BATTERY_CHANGED"));
                    registerReceiver.getIntExtra("status", -1);
                    this.A00.A02("70681111-7232-4722-8b96-cd0fbb3a97e1", Base64.encode(StringFormatUtil.formatStrLocaleSafe("{\"headset_battery\": %d, \"left_controller_battery\": %d, \"right_controller_battery\": %d }", Integer.valueOf((registerReceiver.getIntExtra("level", -1) * 100) / registerReceiver.getIntExtra("scale", -1)), -1, -1).getBytes(), 2));
                    Tracer.A00();
                    Tracer.A01("updateStorageInfo");
                    StatFs A002 = A00();
                    int i3 = 0;
                    if (A002 != null) {
                        A002.restat(this.A05);
                        i = (int) (A002.getTotalBytes() / 1073741824);
                        i2 = (int) (A002.getAvailableBytes() / 1073741824);
                    } else {
                        i2 = 0;
                        i = 0;
                    }
                    int i4 = 5;
                    while (true) {
                        double pow = Math.pow(2.0d, (double) i4);
                        if (((double) i) >= pow) {
                            i4++;
                            if (i4 >= 10) {
                                break;
                            }
                        } else {
                            i3 = (int) pow;
                            break;
                        }
                    }
                    this.A00.A02("f92b841a-e8b3-4f2d-91d4-3c29ef88c2cb", Base64.encode(StringFormatUtil.formatStrLocaleSafe("{\"used_storage\": %d, \"total_storage\": %d }", Integer.valueOf(i3 - i2), Integer.valueOf(i3)).getBytes(), 2));
                    Tracer.A00();
                    AnonymousClass8J r5 = this.A00;
                    JSONObject jSONObject = new JSONObject();
                    C0414Wr A003 = HandlerC0422Wz.A00();
                    try {
                        C0514bB.A00(A003);
                        jSONObject.put("mode", A003.A00);
                        jSONObject.put("is_available", A003.A01);
                    } catch (JSONException e) {
                        C0139Dd.A0L("AssistantPanelManager", e.getMessage(), e);
                    }
                    String obj = jSONObject.toString();
                    C0514bB.A01(obj, "panelStateJSON.toString()");
                    r5.A02("42ab2c51-0538-492e-adac-b15a987a7e69", Base64.encode(obj.getBytes(), 2));
                    AnonymousClass8J r6 = this.A00;
                    JSONObject jSONObject2 = new JSONObject();
                    try {
                        jSONObject2.put("show_opt:", this.A03.getBoolean("should_show_in_game_opt_out_flag", true));
                        C0414Wr A004 = HandlerC0422Wz.A00();
                        C0514bB.A00(A004);
                        int i5 = A004.A00;
                        boolean z = false;
                        if (3 == i5) {
                            z = true;
                        }
                        jSONObject2.put("in_game:", z);
                    } catch (JSONException e2) {
                        C0139Dd.A0L("OculusDeviceContextBuilderProvider", e2.getMessage(), e2);
                    }
                    r6.A02("840f6c59-0691-482a-965c-fa733fde7877", Base64.encode(jSONObject2.toString().getBytes(), 2));
                    AnonymousClass8J r62 = this.A00;
                    JSONObject jSONObject3 = new JSONObject();
                    try {
                        jSONObject3.put("is_user_in_nux", Z4.A02());
                    } catch (JSONException e3) {
                        C0139Dd.A0L("OculusDeviceContextBuilderProvider", e3.getMessage(), e3);
                    }
                    r62.A02("0d1089bc-c08c-4c27-916f-6618a93922c3", Base64.encode(jSONObject3.toString().getBytes(), 2));
                    if (this.A04.getBoolean("passthrough_on_demand_enabled", false)) {
                        this.A00.A02("88db5726-1642-4c33-b024-3b3686cc35c9", Base64.encode(A06, 2));
                    }
                    Tracer.A00();
                    r3.close();
                } catch (Throwable unused) {
                }
            }
            lzVar = new C0873lz();
            lzVar.A02(21, TimeZone.getDefault().getID());
            lzVar.A02(32, Locale.getDefault().toString());
            lzVar.A02(34, this.A01);
            AudioManager audioManager = (AudioManager) BX.A00().getSystemService("audio");
            lzVar.A02(0, Integer.valueOf(Math.round((((float) audioManager.getStreamVolume(3)) / ((float) audioManager.getStreamMaxVolume(3))) * 10.0f)));
            lzVar.A02(39, Boolean.valueOf(!AssistantDeviceSettings.INSTANCE.isVoiceStorageEnabled()));
            lzVar.A02(2, C0398Vv.A01());
            Tracer.A00();
        } catch (Throwable th) {
            Tracer.A00();
            throw th;
        }
        return lzVar;
        throw th;
    }

    private StatFs A00() {
        if (this.A02 == null) {
            try {
                this.A02 = new StatFs(this.A05);
            } catch (IllegalArgumentException e) {
                C0139Dd.A0L("OculusDeviceContextBuilderProvider", "Can't get statfs", e);
            }
        }
        return this.A02;
    }

    public C1383yK(SettingsManager settingsManager) {
        this.A04 = settingsManager;
        this.A03 = BX.A00().getSharedPreferences("AssistantServiceConfig", 0);
        A00();
    }

    public final String toString() {
        DeviceContext A042 = A01().A04();
        StringBuilder sb = new StringBuilder("{");
        sb.append("\"timezone\":\"");
        sb.append((String) A042.A00(21));
        sb.append("\",");
        sb.append("\"deviceLocale\":\"");
        sb.append((String) A042.A00(32));
        sb.append("\",");
        sb.append("\"volumeLevel\":\"");
        sb.append(A042.A00(0));
        sb.append("\",");
        sb.append("\"foregroundApp\":\"");
        sb.append((String) A042.A00(2));
        sb.append("\",");
        sb.append("\"oculusSettings\":");
        sb.append(AssistantDeviceSettings.INSTANCE.toJson());
        sb.append("}");
        return sb.toString();
    }

    @Override // X.AnonymousClass8c
    public final void A3q(List list) {
        this.A01 = list;
    }
}
