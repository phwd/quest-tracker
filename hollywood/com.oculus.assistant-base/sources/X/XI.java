package X;

import android.app.Application;
import android.content.pm.PackageManager;
import android.preference.PreferenceManager;
import android.text.TextUtils;
import com.facebook.auth.viewercontext.ViewerContext;
import com.facebook.debug.tracer.Tracer;
import com.facebook.mobileconfig.MobileConfigDependenciesInFBApps;
import com.facebook.mobileconfig.MobileConfigManagerHolderImpl;
import com.facebook.tigon.iface.TigonServiceHolder;
import com.oculus.assistant.service.AssistantService;
import java.io.IOException;
import java.util.Locale;
import java.util.concurrent.ExecutorService;

public final /* synthetic */ class XI implements Runnable {
    public static final String __redex_internal_original_name = "com.oculus.assistant.service.-$$Lambda$AssistantService$-ySbRpOhd893-4c42Mky-cYb7pA";
    public final /* synthetic */ AssistantService A00;

    public /* synthetic */ XI(AssistantService assistantService) {
        this.A00 = assistantService;
    }

    public final void run() {
        C0894oG oGVar;
        AssistantService assistantService = this.A00;
        Tracer.A01("InitializationReady");
        try {
            Application A002 = BX.A00();
            ViewerContext A003 = assistantService.A0Q.A00();
            Tracer.A01("MCFactory.init");
            try {
                W4.A00 = A003;
                String A004 = new C1398yc().A00();
                synchronized (W4.class) {
                    oGVar = W4.A04;
                    if (oGVar == null) {
                        try {
                            oGVar = new C0894oG(new C1239vt(), new C1240vu(), new BU(A003), new C1225vf(A002), A002.getPackageManager().getPackageInfo(A002.getPackageName(), 0), new C1226vg(), new C1227vh(A004), new C1228vi(), new C1229vj(A002, A003), new C1231vl(), EnumC0164Fp.Oculus);
                            W4.A04 = oGVar;
                        } catch (PackageManager.NameNotFoundException e) {
                            throw new RuntimeException(e);
                        }
                    }
                }
                Class<C0894oG> cls = C0894oG.class;
                try {
                    C0139Dd.A01(cls, "Trying to initialize MobileConfig");
                    GD gd = oGVar.A00;
                    String A37 = ((GB) oGVar.A02.get()).A37();
                    EnumC0164Fp fp = oGVar.A01;
                    synchronized (gd) {
                        C0515bC bCVar = (C0515bC) gd.A02.get();
                        bCVar.A07();
                        if (bCVar.A0A.get()) {
                            ((ExecutorService) gd.A00.get()).execute(new GC(gd, A37, fp));
                        } else {
                            ((C0892oE) bCVar.A05).A01(gd.A00(A37, fp), bCVar);
                            C0515bC.A02(bCVar, true);
                        }
                    }
                    C0139Dd.A05(cls, "MobileConfig enabled : %s", Boolean.valueOf(((C0515bC) oGVar.A04.get()).A05.isValid()));
                } catch (Exception e2) {
                    cls = C0894oG.class;
                    C0139Dd.A07(cls, e2, "Exception while initing mobileconfig", new Object[0]);
                    if (!(e2 instanceof IOException)) {
                        oGVar.A03.get();
                    }
                }
                W4.A05 = new GE(new C1232vm(), new C1233vn(), W4.A04, new C1234vo(), new C1236vq(A003), new C1237vr(A002, A003));
                long j = A002.getSharedPreferences("AssistantServiceConfig", 0).getLong("last_mobileconfig_sync", 0);
                long currentTimeMillis = System.currentTimeMillis();
                if (j == 0) {
                    GE ge = W4.A05;
                    C0892oE oEVar = (C0892oE) ge.A03.get();
                    C0139Dd.A01(GE.class, "Setting Tigon service");
                    C0894oG oGVar2 = ge.A00;
                    String A372 = ((GB) ge.A01.get()).A37();
                    synchronized (oGVar2) {
                        if (!(!TextUtils.isEmpty(A372))) {
                            C0139Dd.A01(cls, "Cannot enable mobile config.");
                        } else {
                            C0515bC bCVar2 = (C0515bC) oGVar2.A04.get();
                            bCVar2.A07();
                            AbstractC0162Fh fh = bCVar2.A05;
                            if (fh instanceof C0892oE) {
                                C0892oE oEVar2 = (C0892oE) fh;
                                MobileConfigManagerHolderImpl mobileConfigManagerHolderImpl = (MobileConfigManagerHolderImpl) oGVar2.A00.A00(A372, oGVar2.A01);
                                oEVar2.A01(mobileConfigManagerHolderImpl, bCVar2);
                                MobileConfigDependenciesInFBApps.setNetworkService(mobileConfigManagerHolderImpl, (TigonServiceHolder) oGVar2.A05.get(), true);
                                C0139Dd.A05(cls, "Initialized MobileConfigManager after login - valid: %s", Boolean.valueOf(oEVar2.isValid()));
                            }
                        }
                    }
                    C0515bC.A02((C0515bC) ge.A02.get(), true);
                    C0139Dd.A01(GE.class, "Updating MobileConfig on login");
                    boolean tryUpdateConfigsSynchronously = oEVar.tryUpdateConfigsSynchronously(5000);
                    C0139Dd.A05(GE.class, "Update completed or timed out - sync fetched: %s", Boolean.valueOf(tryUpdateConfigsSynchronously));
                    if (tryUpdateConfigsSynchronously || !oEVar.isFetchNeeded()) {
                        A002.getSharedPreferences("AssistantServiceConfig", 0).edit().putLong("last_mobileconfig_sync", currentTimeMillis).apply();
                    } else {
                        String format = String.format(Locale.US, "Unable to finish downloading config values after: %d ms", 5000);
                        C0139Dd.A02(GE.class, format);
                        throw new RuntimeException(format);
                    }
                } else if (j + W4.A09 < currentTimeMillis) {
                    GE ge2 = W4.A05;
                    C0139Dd.A01(GE.class, "Force updating session MobileConfig");
                    C0892oE oEVar3 = (C0892oE) ge2.A03.get();
                    C0894oG oGVar3 = ge2.A00;
                    oGVar3.A00.A01(((GB) oGVar3.A02.get()).A37(), oGVar3.A01);
                    if (!(oEVar3.A00() instanceof MobileConfigManagerHolderImpl)) {
                        C0139Dd.A04(GE.class, "Skip refreshSessionConfigsAsync due to missing native MobileConfig manager.");
                    } else {
                        MobileConfigManagerHolderImpl mobileConfigManagerHolderImpl2 = (MobileConfigManagerHolderImpl) oEVar3.A00();
                        MobileConfigDependenciesInFBApps.setNetworkService(mobileConfigManagerHolderImpl2, (TigonServiceHolder) ge2.A04.get(), true);
                        C0139Dd.A05(GE.class, "Updated session config result: %s", Boolean.valueOf(mobileConfigManagerHolderImpl2.tryUpdateConfigs()));
                    }
                    A002.getSharedPreferences("AssistantServiceConfig", 0).edit().putLong("last_mobileconfig_sync", currentTimeMillis).apply();
                }
                Tracer.A01("populateOverrides");
                try {
                    if (PreferenceManager.getDefaultSharedPreferences(BX.A00()).getBoolean("enable_mc_override", false)) {
                        W4.A03 = new GA(W4.A02, W4.A01);
                        W4.A02("mc_enable_oacr", 2342153418648453331L);
                        W4.A02("mc_disable_noise_supression", 36310409434693842L);
                        W4.A02("mc_dont_reenable_noise_suppression", 36310409434955990L);
                        W4.A02("mc_audio_ducking_enabled", 36310409435087064L);
                        W4.A02("mc_server_side_what_can_i_do", 36310409435152601L);
                        W4.A02("mc_simplified_help_screen_enabled", 36310409435480285L);
                        W4.A02("mc_ingame_voice_commands_disabled", 2342153418648584405L);
                        W4.A02("mc_use_native_voice_service", 36310409435611358L);
                        W4.A02("mc_enabled", 2342153418648322257L);
                        W4.A02("mc_voice_commands_disabled", 36310409434824916L);
                        W4.A02("mc_enable_transcription", 2342153418649501920L);
                        W4.A02("mc_enable_voice_selection", 36310409436528872L);
                    }
                    Tracer.A00();
                    Tracer.A00();
                    W0 A005 = W0.A00();
                    Tracer.A01("updateConfigs");
                    C0515bC A006 = W4.A00();
                    A005.A00.edit().putBoolean("enabled", A006.A08(2342153418648322257L)).putBoolean("disable_noise_supression", A006.A08(36310409434693842L)).putBoolean("voice_commands_disabled", A006.A08(36310409434824916L)).putBoolean("is_allowed_usersettings", A006.A08(36310409435021527L)).putBoolean("is_allowed_debugusersettings", A006.A08(36310409436463335L)).putBoolean("audio_ducking_enabled", A006.A08(36310409435087064L)).putBoolean("os_version_enabled", A006.A08(2342153418648912090L)).putBoolean("app_version_enabled", A006.A08(2342153418648977627L)).putBoolean("reset_if_busy", A006.A08(2342153418649043164L)).putLong("num_is_busy_before_reset", A006.A04(36591884412125230L)).putBoolean("simplified_help_screen_enabled", A006.A08(36310409435480285L)).putString("smart_keyboard_model_assets", A006.A06(36873359388966918L)).putBoolean("enable_nux_double_tap", A006.A08(36310409435676895L)).putLong("num_max_double_tap_nux_launch", A006.A04(36591884412452911L)).putBoolean("use_voice_assistant_service", A006.A08(36310409435611358L)).putBoolean("enable_transcription", A006.A08(2342153418649501920L)).putBoolean("enable_dynamic_attn_system_time", A006.A08(36310409435873505L)).putBoolean("enable_assistant_wakeword", A006.A08(36310409435939042L)).putBoolean("enable_voice_selection", A006.A08(36310409436528872L)).putBoolean("disableo_attn_message_truncation_war", A006.A08(36310409436004579L)).putInt("evaluate_smart_keyboard_models", (int) A006.A04(36591884412780592L)).putBoolean("enable_federated_learning", A006.A08(36310409436135652L)).putLong("fl_data_logging_freq", A006.A04(36591884412911665L)).putBoolean("oc_assistant_tips_on_day", A006.A08(36310409436266725L)).putBoolean("fl_execute_on_idle", A006.A08(36310409436332262L)).putLong("fl_idle_retry_delay_sec", A006.A04(36591884413108274L)).apply();
                    Tracer.A00();
                    assistantService.A0n = C0408Wk.A01;
                    assistantService.A1C.setIsReady(true);
                    AssistantService.A08(assistantService);
                } finally {
                    Tracer.A00();
                }
            } finally {
                Tracer.A00();
            }
        } finally {
            Tracer.A00();
        }
    }
}
