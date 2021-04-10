package com.oculus.assistant.service.typeahead;

import X.A2;
import X.AbstractC0105Aj;
import X.BX;
import X.C00638p;
import X.C00698x;
import X.C00799i;
import X.C0112Aq;
import X.C0139Dd;
import X.C0616d1;
import X.C1300wu;
import X.C1402yg;
import X.HandlerC0422Wz;
import X.W0;
import android.content.Context;
import android.database.SQLException;
import android.preference.PreferenceManager;
import com.facebook.assistant.clientplatform.keyboard.learning.LearningListener;
import com.facebook.assistant.clientplatform.keyboard.learning.util.LearningStore;
import com.facebook.assistant.clientplatform.keyboard.modeltypeahead.ByteLMWordTypeaheadProvider;
import com.facebook.common.jniexecutors.AndroidAsyncExecutorFactory;
import com.facebook.models.ModelLoader;
import com.facebook.models.StaticAssetMeta;
import com.facebook.models.StaticManifestLoader;
import com.facebook.tigon.oktigon.OkTigonService;
import com.facebook.tigon.oktigon.OkTigonServiceHolder;
import com.oculus.assistant.R;
import com.oculus.assistant.config.AssistantDeviceSettings;
import com.oculus.assistant.learning.DataDeletionJobService;
import com.oculus.os.SettingsManager;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.atomic.AtomicBoolean;

public final class OculusTypeaheadProvider {
    public Context A00;
    public LearningListener A01;
    public ByteLMWordTypeaheadProvider A02;
    public AbstractC0105Aj A03;
    public AbstractC0105Aj A04;
    public AbstractC0105Aj A05;
    public C0112Aq A06 = C0112Aq.A00();
    public AndroidAsyncExecutorFactory A07;
    public ModelLoader A08;
    public StaticManifestLoader A09;
    public String A0A;
    public String A0B;
    public List A0C;
    public C00698x A0D;
    public SettingsManager A0E;
    public ScheduledExecutorService A0F;
    public final AtomicBoolean A0G = new AtomicBoolean(false);
    public final String A0H = "kd_model";
    public final C0616d1 backupModel;

    private SettingsManager A00() {
        SettingsManager settingsManager = this.A0E;
        if (settingsManager != null) {
            return settingsManager;
        }
        SettingsManager settingsManager2 = new SettingsManager(BX.A00());
        this.A0E = settingsManager2;
        return settingsManager2;
    }

    private void A01() {
        boolean deleteDatabase;
        try {
            LearningStore A002 = LearningStore.A00("oculus", this.A00.getApplicationContext());
            Context applicationContext = this.A00.getApplicationContext();
            synchronized (A002) {
                A002.A00.close();
                deleteDatabase = applicationContext.deleteDatabase("federated_learning_store.db");
            }
            if (deleteDatabase) {
                C0139Dd.A09("com.oculus.assistant.service.typeahead.OculusTypeaheadProvider", "Succesfully deleted ExampleStore after FL opt out");
            } else {
                C0139Dd.A09("com.oculus.assistant.service.typeahead.OculusTypeaheadProvider", "Failed to delete ExampleStore after FL opt out");
            }
        } catch (SQLException e) {
            C0139Dd.A0S("com.oculus.assistant.service.typeahead.OculusTypeaheadProvider", e, "Error when deleting ExampleStore after FL opt out");
        }
        Context context = this.A00;
        C00638p.A00(context, "assistant_keyboard_fl_device_name");
        C00638p.A00(context, "assistant_keyboard_fl_papaya_last_submit");
        C00638p.A00(context, "assistant_keyboard_fl_data_num_rows");
    }

    public static void A02(OculusTypeaheadProvider oculusTypeaheadProvider) {
        if (!oculusTypeaheadProvider.A0G.getAndSet(true)) {
            ArrayList arrayList = new ArrayList();
            String[] split = W0.A00().A00.getString("smart_keyboard_model_assets", "model https://scontent.xx.fbcdn.net/v/t39.12388-6/109951218_898149157374721_5906208141876638659_n.bin?_nc_cat=107&_nc_sid=37fa7d&_nc_ohc=mFovZDNMu0sAX8qG8M-&_nc_ht=scontent.xx&_nc_rmd=260&_nc_log=1&oh=8c06e864167a4d2c44359a7b767de4a5&oe=5F345D8E 898149157374721,").split(",");
            if (split.length == 0) {
                split = "model https://scontent.xx.fbcdn.net/v/t39.12388-6/109951218_898149157374721_5906208141876638659_n.bin?_nc_cat=107&_nc_sid=37fa7d&_nc_ohc=mFovZDNMu0sAX8qG8M-&_nc_ht=scontent.xx&_nc_rmd=260&_nc_log=1&oh=8c06e864167a4d2c44359a7b767de4a5&oe=5F345D8E 898149157374721,".split(",");
            }
            for (String str : split) {
                String[] split2 = str.split(" ");
                if (split2.length != 3) {
                    C00799i.A00.logError("Fail to fetch model assets from mobile config. Use backup assets in AssistantConfig.");
                } else {
                    arrayList.add(new StaticAssetMeta("lite_lmlstm_oculus_torch_v1", 4, split2[0], split2[1], split2[2]));
                }
            }
            oculusTypeaheadProvider.A09 = new StaticManifestLoader(arrayList);
            oculusTypeaheadProvider.A08 = new ModelLoader(new C1402yg(), new OkTigonServiceHolder(new OkTigonService(A2.A00(), null, oculusTypeaheadProvider.A0B)), oculusTypeaheadProvider.A07, oculusTypeaheadProvider.A09, oculusTypeaheadProvider.A0A);
        }
        C00698x r7 = new C00698x(oculusTypeaheadProvider.A0H);
        oculusTypeaheadProvider.A0D = r7;
        ByteLMWordTypeaheadProvider byteLMWordTypeaheadProvider = new ByteLMWordTypeaheadProvider(oculusTypeaheadProvider.A00, oculusTypeaheadProvider.A08, oculusTypeaheadProvider.A0F, r7, oculusTypeaheadProvider.backupModel);
        oculusTypeaheadProvider.A02 = byteLMWordTypeaheadProvider;
        byteLMWordTypeaheadProvider.A3e();
        boolean z = W0.A00().A00.getBoolean("enable_federated_learning", false);
        Context context = oculusTypeaheadProvider.A00;
        if (z) {
            C00638p.A02(context, "assistant_keyboard_fl_user_in_gk", true);
            if (!AssistantDeviceSettings.INSTANCE.isKeyboardFederatedLearningEnabled()) {
                if (PreferenceManager.getDefaultSharedPreferences(oculusTypeaheadProvider.A00.getApplicationContext()).getBoolean("assistant_keyboard_fl_user_opted_in_setting", false)) {
                    oculusTypeaheadProvider.A01();
                    C00638p.A02(oculusTypeaheadProvider.A00, "assistant_keyboard_fl_user_opted_in_setting", false);
                }
                if (!PreferenceManager.getDefaultSharedPreferences(oculusTypeaheadProvider.A00.getApplicationContext()).getBoolean("assistant_keyboard_fl_user_shown_opt_in_dialog", false)) {
                    C00799i.A00.logNuxEvent("KeyboardFederatedLearningDialog: Showing Prompt");
                    C1300wu wuVar = new C1300wu();
                    wuVar.A0G("dlg-enable-keyboard-fl");
                    wuVar.A0C(R.string.enable_keyboard_fl_title);
                    wuVar.A09(R.string.enable_keyboard_fl_description);
                    wuVar.A0A(R.string.enable_keyboard_fl_enable_improvements);
                    wuVar.A0B(R.string.no_button_text);
                    wuVar.A0E("system-dialog");
                    HandlerC0422Wz.A06.A09(wuVar, true);
                    return;
                }
                return;
            }
            DataDeletionJobService.A00(oculusTypeaheadProvider.A00);
            C00638p.A02(oculusTypeaheadProvider.A00, "assistant_keyboard_fl_user_opted_in_setting", true);
            PreferenceManager.getDefaultSharedPreferences(oculusTypeaheadProvider.A00.getApplicationContext()).edit().putString("assistant_keyboard_fl_device_name", "oculus").apply();
            LearningListener learningListener = new LearningListener(oculusTypeaheadProvider.A00, W0.A00().A00.getLong("fl_data_logging_freq", 1));
            oculusTypeaheadProvider.A01 = learningListener;
            learningListener.A3e();
            return;
        }
        if (PreferenceManager.getDefaultSharedPreferences(context.getApplicationContext()).getBoolean("assistant_keyboard_fl_user_in_gk", false)) {
            oculusTypeaheadProvider.A01();
            C00638p.A02(oculusTypeaheadProvider.A00, "assistant_keyboard_fl_user_in_gk", false);
        } else if (!AssistantDeviceSettings.INSTANCE.isKeyboardFederatedLearningEnabled()) {
            return;
        }
        oculusTypeaheadProvider.A00().setBoolean("keyboard_federated_learning_enabled", false);
        C00638p.A02(oculusTypeaheadProvider.A00, "assistant_keyboard_fl_user_opted_in_setting", false);
    }

    public OculusTypeaheadProvider(Context context, String str, String str2, SettingsManager settingsManager) {
        this.A0B = str;
        this.A0A = str2;
        this.A00 = context;
        ScheduledExecutorService newSingleThreadScheduledExecutor = Executors.newSingleThreadScheduledExecutor();
        this.A0F = newSingleThreadScheduledExecutor;
        this.A07 = new AndroidAsyncExecutorFactory(newSingleThreadScheduledExecutor);
        this.A02 = null;
        this.A01 = null;
        this.A0C = new ArrayList();
        this.A0E = settingsManager;
    }
}
