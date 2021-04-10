package com.facebook.config.application;

import com.facebook.common.build.SignatureType;
import com.facebook.infer.annotation.Nullsafe;
import javax.annotation.Nullable;
import javax.annotation.concurrent.Immutable;

@Nullsafe(Nullsafe.Mode.LOCAL)
@Immutable
public class FbAppType implements PlatformAppConfig {
    @Nullable
    private static volatile FbAppType sAppType;
    private final String apiKey;
    private final String appId;
    private final String appSecret;
    private final IntendedAudience audience;
    private final String defaultUpgradeUrl;
    private final String mapApiKey;
    private final Product product;
    private final String shortName;
    private final String signature;
    private final SignatureType signatureType;

    public static void setAppType(FbAppType fbAppType) {
        sAppType = fbAppType;
    }

    @Nullable
    @Deprecated
    public static FbAppType getAppType() {
        return sAppType;
    }

    public FbAppType(String str, String str2, String str3, String str4, String str5, String str6, String str7, IntendedAudience intendedAudience, Product product2, SignatureType signatureType2) {
        this.shortName = str;
        this.appId = str2;
        this.apiKey = str3;
        this.appSecret = str4;
        this.mapApiKey = str5;
        this.signature = str6;
        this.defaultUpgradeUrl = str7;
        this.audience = intendedAudience;
        this.product = product2;
        this.signatureType = signatureType2;
    }

    public String getShortName() {
        return this.shortName;
    }

    @Override // com.facebook.config.application.PlatformAppConfig
    public String getAppId() {
        return this.appId;
    }

    @Override // com.facebook.config.application.PlatformAppConfig
    public String getApiKey() {
        return this.apiKey;
    }

    @Override // com.facebook.config.application.PlatformAppConfig
    public String getAppSecret() {
        return this.appSecret;
    }

    public String getMapApiKey() {
        return this.mapApiKey;
    }

    public String getSignature() {
        return this.signature;
    }

    public String getDefaultUpgradeUrl() {
        return this.defaultUpgradeUrl;
    }

    public IntendedAudience getAudience() {
        return this.audience;
    }

    public Product getProduct() {
        return this.product;
    }

    public SignatureType getSignatureType() {
        return this.signatureType;
    }
}
