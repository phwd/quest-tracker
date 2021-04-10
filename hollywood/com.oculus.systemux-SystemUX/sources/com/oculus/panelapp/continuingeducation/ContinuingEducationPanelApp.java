package com.oculus.panelapp.continuingeducation;

import android.app.Application;
import android.content.Context;
import android.os.Bundle;
import android.text.TextUtils;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.Surface;
import android.view.View;
import android.widget.TextView;
import androidx.annotation.VisibleForTesting;
import com.oculus.common.logutilities.LoggingUtil;
import com.oculus.os.SettingsManager;
import com.oculus.panelapp.continuingeducation.databinding.ContinuingEducationBinding;
import com.oculus.vrshell.panels.AndroidPanelApp;
import com.oculus.vrshell.panels.AndroidPanelLayer;
import java.util.Map;
import java.util.function.Function;

public final class ContinuingEducationPanelApp extends AndroidPanelApp {
    private static final String CONTINUING_EDUCATION_TYPE_KEY = "type";
    private static final int MAIN_LAYER_HEIGHT = 100;
    private static final int MAIN_LAYER_WIDTH = 420;
    private static final String TAG = LoggingUtil.tag(ContinuingEducationPanelApp.class);
    private static final AndroidPanelLayer.Spec mainLayerSpec = new AndroidPanelLayer.Spec(AndroidPanelApp.MAIN_LAYER, MAIN_LAYER_WIDTH, 100, AndroidPanelLayer.ResizeBehavior.NONE, AndroidPanelLayer.HitTestingBehavior.NOT_HIT_TESTABLE, AndroidPanelLayer.Shape.Flat, R.style.PanelAppTheme);

    /* access modifiers changed from: private */
    public enum ContinuingEducationType {
        RECENTER_VIEW,
        SHOW_TASKBAR
    }

    private native long nativeCreateInstance(long j, long j2);

    public ContinuingEducationPanelApp(Application application, Context context, Surface surface, Map<String, Surface> map, Bundle bundle) {
        super(application, context, surface, map, bundle);
        Log.i(TAG, "Create continuing education panel app");
        createMainLayer();
    }

    private void createMainLayer() {
        ensurePanelLayer(AndroidPanelApp.MAIN_LAYER, mainLayerSpec, new AndroidPanelApp.ViewCreator() {
            /* class com.oculus.panelapp.continuingeducation.ContinuingEducationPanelApp.AnonymousClass1 */

            @Override // com.oculus.vrshell.panels.AndroidPanelApp.ViewCreator
            public String name() {
                return "continuing_education#main";
            }

            @Override // com.oculus.vrshell.panels.AndroidPanelApp.ViewCreator
            public View createView(Context context) {
                ContinuingEducationBinding inflate = ContinuingEducationBinding.inflate(LayoutInflater.from(context));
                ContinuingEducationPanelApp.configureContinuingEducationView(new Function() {
                    /* class com.oculus.panelapp.continuingeducation.$$Lambda$ebd_CUpOKPyNABVPPpxflqRqrE */

                    @Override // java.util.function.Function
                    public final Object apply(Object obj) {
                        return ContinuingEducationPanelApp.this.getEnvironmentArg((String) obj);
                    }
                }, inflate.text, new SettingsManager());
                return inflate.getRoot();
            }
        });
    }

    @VisibleForTesting
    static void configureContinuingEducationView(Function<String, String> function, TextView textView, SettingsManager settingsManager) {
        int i;
        int i2;
        String apply = function.apply("type");
        if (!TextUtils.isEmpty(apply)) {
            try {
                ContinuingEducationType valueOf = ContinuingEducationType.valueOf(apply);
                boolean z = settingsManager.getBoolean(SettingsManager.HAND_TRACKING_ENABLED, false);
                int i3 = AnonymousClass2.$SwitchMap$com$oculus$panelapp$continuingeducation$ContinuingEducationPanelApp$ContinuingEducationType[valueOf.ordinal()];
                if (i3 == 1) {
                    if (z) {
                        i = R.string.view_recenter_continuing_education_description_hands;
                    } else {
                        i = R.string.view_recenter_continuing_education_description;
                    }
                    textView.setText(i);
                } else if (i3 == 2) {
                    if (z) {
                        i2 = R.string.show_taskbar_continuing_education_description_hands;
                    } else {
                        i2 = R.string.show_taskbar_continuing_education_description;
                    }
                    textView.setText(i2);
                }
            } catch (IllegalArgumentException e) {
                throw new IllegalArgumentException("invalid continuing education type: " + apply, e);
            }
        } else {
            throw new IllegalArgumentException("continuing education type is missing");
        }
    }

    /* access modifiers changed from: package-private */
    /* renamed from: com.oculus.panelapp.continuingeducation.ContinuingEducationPanelApp$2  reason: invalid class name */
    public static /* synthetic */ class AnonymousClass2 {
        static final /* synthetic */ int[] $SwitchMap$com$oculus$panelapp$continuingeducation$ContinuingEducationPanelApp$ContinuingEducationType = new int[ContinuingEducationType.values().length];

        /* JADX WARNING: Can't wrap try/catch for region: R(6:0|1|2|3|4|6) */
        /* JADX WARNING: Code restructure failed: missing block: B:7:?, code lost:
            return;
         */
        /* JADX WARNING: Failed to process nested try/catch */
        /* JADX WARNING: Missing exception handler attribute for start block: B:3:0x0014 */
        static {
            /*
                com.oculus.panelapp.continuingeducation.ContinuingEducationPanelApp$ContinuingEducationType[] r0 = com.oculus.panelapp.continuingeducation.ContinuingEducationPanelApp.ContinuingEducationType.values()
                int r0 = r0.length
                int[] r0 = new int[r0]
                com.oculus.panelapp.continuingeducation.ContinuingEducationPanelApp.AnonymousClass2.$SwitchMap$com$oculus$panelapp$continuingeducation$ContinuingEducationPanelApp$ContinuingEducationType = r0
                int[] r0 = com.oculus.panelapp.continuingeducation.ContinuingEducationPanelApp.AnonymousClass2.$SwitchMap$com$oculus$panelapp$continuingeducation$ContinuingEducationPanelApp$ContinuingEducationType     // Catch:{ NoSuchFieldError -> 0x0014 }
                com.oculus.panelapp.continuingeducation.ContinuingEducationPanelApp$ContinuingEducationType r1 = com.oculus.panelapp.continuingeducation.ContinuingEducationPanelApp.ContinuingEducationType.RECENTER_VIEW     // Catch:{ NoSuchFieldError -> 0x0014 }
                int r1 = r1.ordinal()     // Catch:{ NoSuchFieldError -> 0x0014 }
                r2 = 1
                r0[r1] = r2     // Catch:{ NoSuchFieldError -> 0x0014 }
            L_0x0014:
                int[] r0 = com.oculus.panelapp.continuingeducation.ContinuingEducationPanelApp.AnonymousClass2.$SwitchMap$com$oculus$panelapp$continuingeducation$ContinuingEducationPanelApp$ContinuingEducationType     // Catch:{ NoSuchFieldError -> 0x001f }
                com.oculus.panelapp.continuingeducation.ContinuingEducationPanelApp$ContinuingEducationType r1 = com.oculus.panelapp.continuingeducation.ContinuingEducationPanelApp.ContinuingEducationType.SHOW_TASKBAR     // Catch:{ NoSuchFieldError -> 0x001f }
                int r1 = r1.ordinal()     // Catch:{ NoSuchFieldError -> 0x001f }
                r2 = 2
                r0[r1] = r2     // Catch:{ NoSuchFieldError -> 0x001f }
            L_0x001f:
                return
            */
            throw new UnsupportedOperationException("Method not decompiled: com.oculus.panelapp.continuingeducation.ContinuingEducationPanelApp.AnonymousClass2.<clinit>():void");
        }
    }

    /* access modifiers changed from: protected */
    @Override // com.oculus.vrshell.panels.AndroidPanelApp
    public View createViewForLayer(String str, int i, Context context) {
        throw new UnsupportedOperationException("layers are created in the contstructor");
    }

    /* access modifiers changed from: protected */
    @Override // com.oculus.vrshell.panels.AndroidPanelApp
    public AndroidPanelLayer.Spec getLayerSpec(String str, int i) {
        throw new UnsupportedOperationException("layers are created in the contstructor");
    }

    /* access modifiers changed from: protected */
    @Override // com.oculus.vrshell.panels.AndroidPanelApp
    public long createNativeInstance() {
        return nativeCreateInstance(420, 100);
    }
}
