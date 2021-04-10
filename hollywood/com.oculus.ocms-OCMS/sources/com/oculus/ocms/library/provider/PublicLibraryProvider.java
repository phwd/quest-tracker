package com.oculus.ocms.library.provider;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.net.Uri;
import android.text.TextUtils;
import com.facebook.debug.log.BLog;
import com.facebook.inject.FbInjector;
import com.facebook.inject.InjectorLike;
import com.facebook.ultralight.Eager;
import com.facebook.ultralight.Inject;
import com.facebook.ultralight.UL;
import com.google.common.collect.ImmutableList;
import com.oculus.content.OculusPublicReadContentProvider;
import com.oculus.library.database.LibraryStorage;
import com.oculus.library.model.App;
import com.oculus.library.utils.app.PublicAppsCursor;
import com.oculus.ocms.library.provider.ContentType;
import com.oculus.ocms.library.provider.contract.OCMSLibraryContract;
import javax.annotation.Nullable;

public class PublicLibraryProvider extends OculusPublicReadContentProvider {
    private static final String TAG = "PublicLibraryProvider";
    @Inject
    @Eager
    private LibraryStorage mLibraryStorage;

    /* access modifiers changed from: protected */
    @Override // com.facebook.secure.content.AbstractContentProviderNoDI
    @Nullable
    public String doGetType(Uri uri) {
        return null;
    }

    /* access modifiers changed from: protected */
    @Override // com.facebook.secure.content.FbPermissionsContentProvider
    @Nullable
    public String getFbPermission() {
        return null;
    }

    private static final void _UL_injectMe(Context context, PublicLibraryProvider publicLibraryProvider) {
        if (UL.USE_STATIC_DI) {
            _UL_staticInjectMe((InjectorLike) FbInjector.get(context), publicLibraryProvider);
        } else {
            FbInjector.injectMe(PublicLibraryProvider.class, publicLibraryProvider, context);
        }
    }

    public static final void _UL_staticInjectMe(InjectorLike injectorLike, PublicLibraryProvider publicLibraryProvider) {
        publicLibraryProvider.mLibraryStorage = LibraryStorage._UL__ULSEP_com_oculus_library_database_LibraryStorage_ULSEP_ACCESS_METHOD(injectorLike);
    }

    /* access modifiers changed from: protected */
    @Override // com.oculus.content.OculusFbPermissionsContentProvider
    public void doInitialization() {
        _UL_injectMe(getContext(), this);
    }

    /* access modifiers changed from: protected */
    @Override // com.facebook.secure.content.AbstractContentProviderNoDI
    public Uri doInsert(Uri uri, @Nullable ContentValues contentValues) {
        throw new UnsupportedOperationException("insert not supported");
    }

    /* access modifiers changed from: protected */
    @Override // com.facebook.secure.content.AbstractContentProviderNoDI
    public int doDelete(Uri uri, @Nullable String str, @Nullable String[] strArr) {
        throw new UnsupportedOperationException("delete not supported");
    }

    /* access modifiers changed from: protected */
    @Override // com.facebook.secure.content.AbstractContentProviderNoDI
    public int doUpdate(Uri uri, ContentValues contentValues, @Nullable String str, @Nullable String[] strArr) {
        throw new UnsupportedOperationException("update not supported");
    }

    /* access modifiers changed from: protected */
    @Override // com.facebook.secure.content.AbstractContentProviderNoDI
    @Nullable
    public Cursor doQuery(Uri uri, @Nullable String[] strArr, @Nullable String str, @Nullable String[] strArr2, @Nullable String str2) {
        ContentType publicContentTypeFor = Constants.getPublicContentTypeFor(uri);
        if (publicContentTypeFor != null) {
            int i = AnonymousClass1.$SwitchMap$com$oculus$ocms$library$provider$ContentType$QueryMethod[publicContentTypeFor.queryMethod.ordinal()];
            if (i == 1) {
                String packageFromAppsUri = OCMSLibraryContract.getPackageFromAppsUri(uri);
                if (!TextUtils.isEmpty(packageFromAppsUri)) {
                    return query(packageFromAppsUri);
                }
                throw new IllegalArgumentException("Invalid packageName in uri: " + uri);
            } else if (i == 2) {
                return queryAll();
            } else {
                throw new IllegalArgumentException("invalid uri");
            }
        } else {
            throw new IllegalArgumentException("Invalid uri: " + uri);
        }
    }

    /* renamed from: com.oculus.ocms.library.provider.PublicLibraryProvider$1  reason: invalid class name */
    static /* synthetic */ class AnonymousClass1 {
        static final /* synthetic */ int[] $SwitchMap$com$oculus$ocms$library$provider$ContentType$QueryMethod = new int[ContentType.QueryMethod.values().length];

        /* JADX WARNING: Can't wrap try/catch for region: R(6:0|1|2|3|4|6) */
        /* JADX WARNING: Code restructure failed: missing block: B:7:?, code lost:
            return;
         */
        /* JADX WARNING: Failed to process nested try/catch */
        /* JADX WARNING: Missing exception handler attribute for start block: B:3:0x0014 */
        static {
            /*
                com.oculus.ocms.library.provider.ContentType$QueryMethod[] r0 = com.oculus.ocms.library.provider.ContentType.QueryMethod.values()
                int r0 = r0.length
                int[] r0 = new int[r0]
                com.oculus.ocms.library.provider.PublicLibraryProvider.AnonymousClass1.$SwitchMap$com$oculus$ocms$library$provider$ContentType$QueryMethod = r0
                int[] r0 = com.oculus.ocms.library.provider.PublicLibraryProvider.AnonymousClass1.$SwitchMap$com$oculus$ocms$library$provider$ContentType$QueryMethod     // Catch:{ NoSuchFieldError -> 0x0014 }
                com.oculus.ocms.library.provider.ContentType$QueryMethod r1 = com.oculus.ocms.library.provider.ContentType.QueryMethod.APPS_SINGLE     // Catch:{ NoSuchFieldError -> 0x0014 }
                int r1 = r1.ordinal()     // Catch:{ NoSuchFieldError -> 0x0014 }
                r2 = 1
                r0[r1] = r2     // Catch:{ NoSuchFieldError -> 0x0014 }
            L_0x0014:
                int[] r0 = com.oculus.ocms.library.provider.PublicLibraryProvider.AnonymousClass1.$SwitchMap$com$oculus$ocms$library$provider$ContentType$QueryMethod     // Catch:{ NoSuchFieldError -> 0x001f }
                com.oculus.ocms.library.provider.ContentType$QueryMethod r1 = com.oculus.ocms.library.provider.ContentType.QueryMethod.APPS_MULTIPLE     // Catch:{ NoSuchFieldError -> 0x001f }
                int r1 = r1.ordinal()     // Catch:{ NoSuchFieldError -> 0x001f }
                r2 = 2
                r0[r1] = r2     // Catch:{ NoSuchFieldError -> 0x001f }
            L_0x001f:
                return
            */
            throw new UnsupportedOperationException("Method not decompiled: com.oculus.ocms.library.provider.PublicLibraryProvider.AnonymousClass1.<clinit>():void");
        }
    }

    @Nullable
    private Cursor query(String str) {
        App app = this.mLibraryStorage.get(str);
        if (app == null) {
            BLog.w(TAG, "Cannot find entitlement for %s", str);
            return null;
        }
        PublicAppsCursor publicAppsCursor = new PublicAppsCursor(1);
        publicAppsCursor.fill(ImmutableList.of(app));
        return publicAppsCursor;
    }

    private Cursor queryAll() {
        ImmutableList<App> all = this.mLibraryStorage.getAll();
        PublicAppsCursor publicAppsCursor = new PublicAppsCursor(all.size());
        publicAppsCursor.fill(all);
        return publicAppsCursor;
    }
}
