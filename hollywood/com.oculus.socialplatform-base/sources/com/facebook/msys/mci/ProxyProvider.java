package com.facebook.msys.mci;

import X.AnonymousClass1Yr;
import com.facebook.msys.util.Provider;
import com.facebook.proguard.annotations.DoNotStrip;
import javax.annotation.Nullable;

@DoNotStrip
public final class ProxyProvider {
    @Nullable
    public final Provider<Analytics> mAnalyticsProvider;
    public final Provider<Crypto> mCryptoProvider;
    @Nullable
    public final Provider<MediaTranscoder> mMediaTranscoderProvider;
    public final Provider<Settings> mSettingsProvider;
    public final Provider<UUID> mUUIDProvider;

    @DoNotStrip
    @Nullable
    private Analytics getAnalytics() {
        Provider<Analytics> provider = this.mAnalyticsProvider;
        if (provider != null) {
            return (Analytics) retrieveProxyOrThrow(provider);
        }
        return null;
    }

    @DoNotStrip
    private Crypto getCrypto() {
        return (Crypto) retrieveProxyOrThrow(this.mCryptoProvider);
    }

    @DoNotStrip
    @Nullable
    private MediaTranscoder getMediaTranscoder() {
        Provider<MediaTranscoder> provider = this.mMediaTranscoderProvider;
        if (provider != null) {
            return (MediaTranscoder) retrieveProxyOrThrow(provider);
        }
        return null;
    }

    @DoNotStrip
    private Settings getSettings() {
        return (Settings) retrieveProxyOrThrow(this.mSettingsProvider);
    }

    @DoNotStrip
    private UUID getUUID() {
        return (UUID) retrieveProxyOrThrow(this.mUUIDProvider);
    }

    public ProxyProvider(AnonymousClass1Yr r2) {
        this.mAnalyticsProvider = r2.A00;
        this.mCryptoProvider = r2.A02;
        this.mMediaTranscoderProvider = r2.A01;
        this.mSettingsProvider = r2.A03;
        this.mUUIDProvider = r2.A04;
    }

    public static <T> T retrieveProxyOrThrow(Provider<T> provider) {
        T t = provider.get();
        if (t != null) {
            return t;
        }
        throw new IllegalArgumentException("Msys proxy was null when provider.get called.  Please ensure that all providers given to ProxyProvider do not return null.");
    }
}
