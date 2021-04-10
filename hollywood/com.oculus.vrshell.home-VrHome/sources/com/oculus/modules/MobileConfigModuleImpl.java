package com.oculus.modules;

import android.content.Context;
import com.oculus.mobileconfigclient.MobileConfigClient;
import com.oculus.modules.codegen.MobileConfigModule;
import com.oculus.modules.codegen.Resolver;
import org.json.JSONObject;

public class MobileConfigModuleImpl extends MobileConfigModule {
    private static final String TAG = MobileConfigModuleImpl.class.getSimpleName();
    private static MobileConfigModuleImpl mInstance = null;
    private Context mContext = null;
    private final MobileConfigClient mMobileConfigClient;

    public MobileConfigModuleImpl(Context context) {
        mInstance = this;
        this.mContext = context;
        this.mMobileConfigClient = new MobileConfigClient(context);
    }

    @Override // com.oculus.modules.codegen.MobileConfigModule
    public void shutdownModule() {
        this.mMobileConfigClient.close();
        mInstance = null;
    }

    /* access modifiers changed from: protected */
    @Override // com.oculus.modules.codegen.MobileConfigModule
    public void initializeImpl(Resolver<JSONObject> resolver) {
        try {
            JSONObject metadata = getMetadataFromAssetsDirectory(this.mContext);
            String hash = metadata.getString("schemaHash");
            if (hash == null) {
                resolver.reject(new Exception("Failed to fetch hash from ConfigMetadata.json."));
                return;
            }
            this.mMobileConfigClient.updateMCsImpl(hash);
            resolver.resolve(metadata.getJSONObject("schema"));
        } catch (Exception e) {
            resolver.reject(e);
        }
    }

    /* JADX WARNING: Removed duplicated region for block: B:15:0x003b  */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    private static org.json.JSONObject getMetadataFromAssetsDirectory(android.content.Context r9) throws java.io.IOException, org.json.JSONException {
        /*
            android.content.res.AssetManager r0 = r9.getAssets()
            r2 = 0
            java.lang.String r7 = "ConfigMetadata.json"
            java.io.InputStream r1 = r0.open(r7)     // Catch:{ all -> 0x003f }
            java.io.InputStreamReader r3 = new java.io.InputStreamReader     // Catch:{ all -> 0x003f }
            java.lang.String r7 = "UTF-8"
            r3.<init>(r1, r7)     // Catch:{ all -> 0x003f }
            java.lang.StringBuilder r6 = new java.lang.StringBuilder     // Catch:{ all -> 0x0037 }
            r6.<init>()     // Catch:{ all -> 0x0037 }
            r7 = 1024(0x400, float:1.435E-42)
            char[] r4 = new char[r7]     // Catch:{ all -> 0x0037 }
        L_0x001b:
            r7 = 0
            int r8 = r4.length     // Catch:{ all -> 0x0037 }
            int r5 = r3.read(r4, r7, r8)     // Catch:{ all -> 0x0037 }
            if (r5 >= 0) goto L_0x0032
            org.json.JSONObject r7 = new org.json.JSONObject     // Catch:{ all -> 0x0037 }
            java.lang.String r8 = r6.toString()     // Catch:{ all -> 0x0037 }
            r7.<init>(r8)     // Catch:{ all -> 0x0037 }
            if (r3 == 0) goto L_0x0031
            r3.close()
        L_0x0031:
            return r7
        L_0x0032:
            r7 = 0
            r6.append(r4, r7, r5)
            goto L_0x001b
        L_0x0037:
            r7 = move-exception
            r2 = r3
        L_0x0039:
            if (r2 == 0) goto L_0x003e
            r2.close()
        L_0x003e:
            throw r7
        L_0x003f:
            r7 = move-exception
            goto L_0x0039
        */
        throw new UnsupportedOperationException("Method not decompiled: com.oculus.modules.MobileConfigModuleImpl.getMetadataFromAssetsDirectory(android.content.Context):org.json.JSONObject");
    }

    /* JADX DEBUG: Multi-variable search result rejected for r5v0, resolved type: com.oculus.modules.codegen.Resolver<java.lang.String> */
    /* JADX WARN: Multi-variable type inference failed */
    /* access modifiers changed from: protected */
    @Override // com.oculus.modules.codegen.MobileConfigModule
    public void getStringImpl(MobileConfigModule.MobileConfigParamType param, String defaultVal, Resolver<String> resolver) {
        resolver.resolve(this.mMobileConfigClient.getStringImpl(convertParamType(param), defaultVal).first);
    }

    /* JADX DEBUG: Multi-variable search result rejected for r5v0, resolved type: com.oculus.modules.codegen.Resolver<java.lang.Boolean> */
    /* JADX WARN: Multi-variable type inference failed */
    /* access modifiers changed from: protected */
    @Override // com.oculus.modules.codegen.MobileConfigModule
    public void getBoolImpl(MobileConfigModule.MobileConfigParamType param, boolean defaultVal, Resolver<Boolean> resolver) {
        resolver.resolve(this.mMobileConfigClient.getBoolImpl(convertParamType(param), defaultVal).first);
    }

    /* access modifiers changed from: protected */
    @Override // com.oculus.modules.codegen.MobileConfigModule
    public void getIntImpl(MobileConfigModule.MobileConfigParamType param, double defaultVal, Resolver<Double> resolver) {
        resolver.resolve(Double.valueOf((double) ((Integer) this.mMobileConfigClient.getIntImpl(convertParamType(param), defaultVal).first).intValue()));
    }

    /* JADX DEBUG: Multi-variable search result rejected for r6v0, resolved type: com.oculus.modules.codegen.Resolver<java.lang.Double> */
    /* JADX WARN: Multi-variable type inference failed */
    /* access modifiers changed from: protected */
    @Override // com.oculus.modules.codegen.MobileConfigModule
    public void getDoubleImpl(MobileConfigModule.MobileConfigParamType param, double defaultVal, Resolver<Double> resolver) {
        resolver.resolve(this.mMobileConfigClient.getDoubleImpl(convertParamType(param), defaultVal).first);
    }

    /* JADX DEBUG: Multi-variable search result rejected for r5v0, resolved type: com.oculus.modules.codegen.Resolver<java.lang.String> */
    /* JADX WARN: Multi-variable type inference failed */
    /* access modifiers changed from: protected */
    @Override // com.oculus.modules.codegen.MobileConfigModule
    public void getStringWithoutLoggingExposureImpl(MobileConfigModule.MobileConfigParamType param, String defaultVal, Resolver<String> resolver) {
        resolver.resolve(this.mMobileConfigClient.getStringWithoutLoggingExposureImpl(convertParamType(param), defaultVal).first);
    }

    /* JADX DEBUG: Multi-variable search result rejected for r5v0, resolved type: com.oculus.modules.codegen.Resolver<java.lang.Boolean> */
    /* JADX WARN: Multi-variable type inference failed */
    /* access modifiers changed from: protected */
    @Override // com.oculus.modules.codegen.MobileConfigModule
    public void getBoolWithoutLoggingExposureImpl(MobileConfigModule.MobileConfigParamType param, boolean defaultVal, Resolver<Boolean> resolver) {
        resolver.resolve(this.mMobileConfigClient.getBoolWithoutLoggingExposureImpl(convertParamType(param), defaultVal).first);
    }

    /* access modifiers changed from: protected */
    @Override // com.oculus.modules.codegen.MobileConfigModule
    public void getIntWithoutLoggingExposureImpl(MobileConfigModule.MobileConfigParamType param, double defaultVal, Resolver<Double> resolver) {
        resolver.resolve(Double.valueOf((double) ((Integer) this.mMobileConfigClient.getIntWithoutLoggingExposureImpl(convertParamType(param), defaultVal).first).intValue()));
    }

    /* JADX DEBUG: Multi-variable search result rejected for r6v0, resolved type: com.oculus.modules.codegen.Resolver<java.lang.Double> */
    /* JADX WARN: Multi-variable type inference failed */
    /* access modifiers changed from: protected */
    @Override // com.oculus.modules.codegen.MobileConfigModule
    public void getDoubleWithoutLoggingExposureImpl(MobileConfigModule.MobileConfigParamType param, double defaultVal, Resolver<Double> resolver) {
        resolver.resolve(this.mMobileConfigClient.getDoubleWithoutLoggingExposureImpl(convertParamType(param), defaultVal).first);
    }

    /* access modifiers changed from: protected */
    @Override // com.oculus.modules.codegen.MobileConfigModule
    public void logExposureImpl(MobileConfigModule.MobileConfigParamType param, Resolver<Void> resolver) {
        this.mMobileConfigClient.logExposureImpl(convertParamType(param));
        resolver.resolve(null);
    }

    private MobileConfigClient.MobileConfigParamType convertParamType(MobileConfigModule.MobileConfigParamType param) {
        return new MobileConfigClient.MobileConfigParamType(param.config, param.parameter, param.defaultValue, param.type, (int) param.unit_type, (int) param.configId, (int) param.paramId, (int) param.slotId);
    }
}
