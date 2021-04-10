package com.oculus.modules;

import android.app.UiModeManager;
import android.content.Context;
import android.util.Log;
import com.oculus.modules.codegen.Resolver;
import com.oculus.modules.codegen.SystemThemeModule;
import com.oculus.panellib.SystraceBlock;

public class SystemThemeModuleImpl extends SystemThemeModule {
    private static final String TAG = SystemThemeModuleImpl.class.getSimpleName();
    private final UiModeManager mUiModeManager;

    public SystemThemeModuleImpl(Context context) {
        this.mUiModeManager = (UiModeManager) context.getSystemService(UiModeManager.class);
    }

    private static int coerceUiMode(int uiMode) {
        switch (uiMode) {
            case 1:
            case 2:
                return uiMode;
            default:
                Log.d(TAG, "Unsupported system ui mode: " + uiMode);
                return 2;
        }
    }

    /* access modifiers changed from: protected */
    @Override // com.oculus.modules.codegen.SystemThemeModule
    public void getSystemThemeImpl(Resolver<Double> resolver) {
        SystraceBlock block = new SystraceBlock(TAG, "getSystemThemeImpl");
        Throwable th = null;
        try {
            int uiMode = this.mUiModeManager.getNightMode();
            Log.d(TAG, "Got system ui mode: " + uiMode);
            resolver.resolve(new Double((double) coerceUiMode(uiMode)));
        } catch (Exception e) {
            Log.e(TAG, "Failed to get system ui mode settings from the OS: " + e.getMessage());
            resolver.reject(e);
        }
        if (block == null) {
            return;
        }
        if (0 != 0) {
            try {
                block.close();
                return;
            } catch (Throwable th2) {
                th.addSuppressed(th2);
                return;
            }
        } else {
            block.close();
            return;
        }
        if (block != null) {
            if (th != null) {
                try {
                    block.close();
                } catch (Throwable th3) {
                    th.addSuppressed(th3);
                }
            } else {
                block.close();
            }
        }
        throw th;
        throw th;
        try {
        } catch (Throwable th4) {
            th = r3;
            th = th4;
        }
    }

    /* access modifiers changed from: protected */
    @Override // com.oculus.modules.codegen.SystemThemeModule
    public void setSystemThemeImpl(double uiMode, Resolver<Double> resolver) {
        Log.d(TAG, "Setting system ui mode: " + uiMode);
        this.mUiModeManager.setNightMode(coerceUiMode((int) uiMode));
        getSystemThemeImpl(resolver);
    }
}
