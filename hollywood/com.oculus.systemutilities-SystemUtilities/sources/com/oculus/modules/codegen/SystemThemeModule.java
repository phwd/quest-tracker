package com.oculus.modules.codegen;

import android.util.Pair;
import com.oculus.panellib.modules.RCTBaseJavaModule;
import java.util.ArrayList;
import java.util.List;

public abstract class SystemThemeModule extends RCTBaseJavaModule {
    protected static final String MODULE_NAME = SystemThemeModule.class.getSimpleName();

    /* access modifiers changed from: protected */
    public abstract void getSystemThemeImpl(Resolver<Double> resolver);

    /* access modifiers changed from: protected */
    public abstract void setSystemThemeImpl(double d, Resolver<Double> resolver);

    /* access modifiers changed from: protected */
    public final String getModuleName() {
        return MODULE_NAME;
    }

    protected static final List<Pair<String, String>> describe() {
        List<Pair<String, String>> list = new ArrayList<>();
        list.add(new Pair<>("getSystemTheme", "+ii"));
        list.add(new Pair<>("setSystemTheme", "+dii"));
        return list;
    }

    /* access modifiers changed from: protected */
    public final String marshallJSConstants() {
        return null;
    }

    /* access modifiers changed from: protected */
    public final void getSystemTheme(int resolveID, int rejectID) {
        getSystemThemeImpl(ResolverFactory.createBasicResolver(this, resolveID, rejectID));
    }

    /* access modifiers changed from: protected */
    public final void setSystemTheme(double theme, int resolveID, int rejectID) {
        setSystemThemeImpl(theme, ResolverFactory.createBasicResolver(this, resolveID, rejectID));
    }

    public void shutdownModule() {
    }
}
