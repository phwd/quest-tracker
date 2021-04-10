package X;

import android.content.Context;
import android.content.pm.PackageInfo;
import android.content.res.AssetManager;
import com.facebook.assistant.oacr.OacrConstants;
import com.facebook.mobileconfig.MobileConfigDependenciesInFBApps;
import com.facebook.mobileconfig.MobileConfigManagerParamsHolder;
import com.facebook.tigon.oktigon.OkTigonServiceHolder;
import java.io.File;
import java.util.Collections;
import java.util.HashMap;
import java.util.Locale;

public final class GD {
    public final AbstractC0463a6 A00;
    public final AbstractC0463a6 A01;
    public final AbstractC0463a6 A02;
    public final PackageInfo A03;
    public final C1227vh A04;
    public final AbstractC0463a6 A05;
    public final AbstractC0463a6 A06;
    public final AbstractC0463a6 A07;

    public final synchronized AbstractC0162Fh A00(String str, EnumC0164Fp fp) {
        MobileConfigManagerParamsHolder mobileConfigManagerParamsHolder;
        OkTigonServiceHolder okTigonServiceHolder;
        MobileConfigDependenciesInFBApps mobileConfigDependenciesInFBApps;
        File filesDir;
        String str2;
        String str3;
        AssetManager assets;
        HashMap hashMap;
        mobileConfigManagerParamsHolder = new MobileConfigManagerParamsHolder();
        mobileConfigManagerParamsHolder.setConsistencyLoggingEnabled(true);
        mobileConfigManagerParamsHolder.setConsistencyLoggingEveryNSec(2592000);
        mobileConfigManagerParamsHolder.setUniverseType(fp.getValue());
        mobileConfigManagerParamsHolder.setResponseCompressionEnabled(false);
        mobileConfigManagerParamsHolder.setQueryHashOptimization(false);
        AbstractC0463a6 a6Var = this.A06;
        if (a6Var == null) {
            okTigonServiceHolder = null;
        } else {
            okTigonServiceHolder = (OkTigonServiceHolder) a6Var.get();
        }
        mobileConfigDependenciesInFBApps = new MobileConfigDependenciesInFBApps(null, okTigonServiceHolder, false, ((AbstractC0241Mg) this.A07.get()).A38());
        AbstractC0463a6 a6Var2 = this.A05;
        filesDir = ((Context) a6Var2.get()).getApplicationContext().getFilesDir();
        str2 = this.A03.versionName;
        str3 = this.A04.A00;
        assets = ((Context) a6Var2.get()).getAssets();
        hashMap = new HashMap(1);
        hashMap.put("locale", ((Locale) this.A01.get()).toString());
        return mobileConfigDependenciesInFBApps.createManager(filesDir, str2, str3, str, OacrConstants.AUTO_SPEECH_DOMAIN, assets, false, mobileConfigManagerParamsHolder, Collections.unmodifiableMap(hashMap));
    }

    public final synchronized void A01(String str, EnumC0164Fp fp) {
        C0515bC bCVar = (C0515bC) this.A02.get();
        if (bCVar != null) {
            AbstractC0162Fh fh = bCVar.A05;
            if (fh instanceof C0892oE) {
                C0892oE oEVar = (C0892oE) fh;
                if (oEVar.A00() instanceof C0891oD) {
                    oEVar.A01(A00(str, fp), bCVar);
                }
            }
        }
    }

    public GD(AbstractC0463a6 a6Var, AbstractC0463a6 a6Var2, AbstractC0463a6 a6Var3, PackageInfo packageInfo, C1227vh vhVar, AbstractC0463a6 a6Var4, AbstractC0463a6 a6Var5, AbstractC0463a6 a6Var6) {
        this.A00 = a6Var2;
        this.A02 = a6Var;
        this.A05 = a6Var3;
        this.A03 = packageInfo;
        this.A04 = vhVar;
        this.A06 = a6Var4;
        this.A07 = a6Var5;
        this.A01 = a6Var6;
    }
}
