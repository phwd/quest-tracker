package com.oculus.widgets;

public abstract class ReactWidgetService extends BaseWidgetService {
    private static final String TAG = "ReactWidgetService";

    @Override // com.oculus.widgets.BaseWidgetService
    public abstract ReactWidget createWidgetInstance();

    /* access modifiers changed from: protected */
    public abstract String getLibraryName();

    /* access modifiers changed from: protected */
    public abstract void initPanelApp();

    @Override // com.oculus.widgets.BaseWidgetService
    public void onCreate() {
        super.onCreate();
        initPanelApp();
        System.loadLibrary(getLibraryName());
    }
}
