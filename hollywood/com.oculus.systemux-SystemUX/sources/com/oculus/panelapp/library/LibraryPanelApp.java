package com.oculus.panelapp.library;

import android.app.Application;
import android.content.Context;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.Surface;
import android.view.View;
import androidx.databinding.ViewDataBinding;
import com.oculus.common.gatekeepers.Gatekeeper;
import com.oculus.common.logutilities.LoggingUtil;
import com.oculus.horizoncontent.horizon.HorizonContent;
import com.oculus.panelapp.anytimeui.databinding.AnytimeTabletLibraryViewV2Binding;
import com.oculus.panelapp.anytimeui.v2.AnytimeUIPanelAppBase;
import com.oculus.panelapp.anytimeui.v2.tablet.apps.LibraryView;
import com.oculus.vrshell.panels.AndroidPanelApp;
import com.oculus.vrshell.panels.AndroidPanelLayer;
import java.util.Map;

public final class LibraryPanelApp extends AnytimeUIPanelAppBase {
    private static String TAG = LoggingUtil.tag(LibraryPanelApp.class);
    private LibraryView mLibraryView;

    private native long nativeCreateInstance(long j, long j2);

    public LibraryPanelApp(Application application, Context context, Surface surface, Map<String, Surface> map, Bundle bundle) {
        super(application, context, surface, map, bundle);
        Log.d(TAG, "Creating LibraryPanelApp");
        if (isGKEnabled(Gatekeeper.AUI_STANDALONE_APPS)) {
            HorizonContent.build("com.oculus.horizon");
            configureLayerSurfaceGeometryBorderRadiuses();
            showMainLayer();
            return;
        }
        throw new RuntimeException("Refusing to instantiate standalone Library because GK is off.");
    }

    @Override // com.oculus.panelapp.anytimeui.v2.AnytimeUIPanelAppBase, com.oculus.vrshell.panels.AndroidPanelApp
    public void destroy() {
        this.mLibraryView.destroy();
        super.destroy();
    }

    private void configureLayerSurfaceGeometryBorderRadiuses() {
        configureLayerSurfaceGeometryBorderRadius(AndroidPanelApp.MAIN_LAYER, AndroidPanelLayer.BorderRadiusType.All, getContext().getResources().getDimensionPixelSize(R.dimen.octablet_border_radius));
    }

    private int getPanelThemeId() {
        if (!isGKEnabled(Gatekeeper.SYSTEM_THEME_SETTING)) {
            return R.style.PanelAppTheme;
        }
        if ((getContext().getResources().getConfiguration().uiMode & 48) != 16) {
            return R.style.PanelAppTheme;
        }
        return R.style.PanelAppTheme_Light;
    }

    private void showMainLayer() {
        ensurePanelLayer(AndroidPanelApp.MAIN_LAYER, new AndroidPanelLayer.Spec(AndroidPanelApp.MAIN_LAYER, 768, 340, AndroidPanelLayer.ResizeBehavior.NONE, AndroidPanelLayer.HitTestingBehavior.HIT_TESTABLE, AndroidPanelLayer.Shape.Flat, getPanelThemeId()), new AndroidPanelApp.ViewCreator() {
            /* class com.oculus.panelapp.library.LibraryPanelApp.AnonymousClass1 */

            @Override // com.oculus.vrshell.panels.AndroidPanelApp.ViewCreator
            public String name() {
                return AndroidPanelApp.MAIN_LAYER;
            }

            @Override // com.oculus.vrshell.panels.AndroidPanelApp.ViewCreator
            public View createView(Context context) {
                return LibraryPanelApp.this.createMainLayerView(context);
            }
        });
    }

    /* access modifiers changed from: private */
    /* access modifiers changed from: public */
    private View createMainLayerView(Context context) {
        if (this.mLibraryView == null) {
            AnytimeTabletLibraryViewV2Binding inflate = AnytimeTabletLibraryViewV2Binding.inflate(LayoutInflater.from(context));
            this.mLibraryView = (LibraryView) inflate.getRoot();
            this.mLibraryView.initialize((AnytimeUIPanelAppBase) this, (ViewDataBinding) inflate);
            this.mLibraryView.onShow("");
            return this.mLibraryView;
        }
        throw new UnsupportedOperationException("Trying to recreate main layer!");
    }

    /* access modifiers changed from: protected */
    @Override // com.oculus.vrshell.panels.AndroidPanelApp
    public long createNativeInstance() {
        return nativeCreateInstance(0, 0);
    }

    /* access modifiers changed from: protected */
    @Override // com.oculus.vrshell.panels.AndroidPanelApp
    public AndroidPanelLayer.Spec getLayerSpec(String str, int i) {
        throw new IllegalStateException("Unexpected method call");
    }

    /* access modifiers changed from: protected */
    @Override // com.oculus.vrshell.panels.AndroidPanelApp
    public View createViewForLayer(String str, int i, Context context) {
        throw new IllegalStateException("Unexpected method call");
    }

    /* access modifiers changed from: protected */
    @Override // com.oculus.vrshell.panels.AndroidPanelApp
    public void onDeepLink(String str, String str2) {
        Log.d(TAG, String.format("Received deeplink; uri: %s.", str2));
        LibraryView libraryView = this.mLibraryView;
        if (libraryView == null) {
            Log.e(TAG, "Unable to handle deeplink; library view has not been initialized.");
        } else {
            libraryView.onShow(str2);
        }
    }
}
