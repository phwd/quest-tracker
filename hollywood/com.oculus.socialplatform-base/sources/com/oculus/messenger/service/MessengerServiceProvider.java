package com.oculus.messenger.service;

import X.AnonymousClass0k6;
import X.AnonymousClass0kQ;
import X.AnonymousClass0kR;
import X.C02920k8;
import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.MatrixCursor;
import android.net.Uri;
import com.oculus.content.OculusFbPermissionsContentProvider;
import com.oculus.messenger.service.MessengerServiceProviderContract;
import java.util.HashSet;
import java.util.Set;
import javax.annotation.Nullable;

public class MessengerServiceProvider extends OculusFbPermissionsContentProvider {
    public static final /* synthetic */ boolean $assertionsDisabled = false;
    @Nullable
    public AnonymousClass0kR mTrustedCaller;

    @Override // X.AnonymousClass0jF
    public int doDelete(Uri uri, String str, String[] strArr) {
        return 0;
    }

    @Override // X.AnonymousClass0jF
    @Nullable
    public String doGetType(Uri uri) {
        return null;
    }

    @Override // X.AnonymousClass0jF
    @Nullable
    public Uri doInsert(Uri uri, ContentValues contentValues) {
        return null;
    }

    @Override // X.AnonymousClass0jF
    public int doUpdate(Uri uri, ContentValues contentValues, String str, String[] strArr) {
        return 0;
    }

    @Override // com.facebook.secure.content.FbPermissionsContentProvider
    public String getFbPermission() {
        return "com.oculus.ocms.fbpermission.MESSENGER_SERVICE_PROVIDER";
    }

    @Override // com.oculus.content.OculusFbPermissionsContentProvider
    public void doInitialization() {
        Set<String> set;
        AnonymousClass0kQ r4 = new AnonymousClass0kQ();
        C02920k8 r3 = AnonymousClass0k6.A0P;
        if (!r4.A04.containsKey(r3) || (set = r4.A04.get(r3)) == null) {
            HashSet hashSet = new HashSet();
            hashSet.add("com.oculus.horizon");
            r4.A04.put(r3, hashSet);
        } else {
            set.add("com.oculus.horizon");
        }
        this.mTrustedCaller = r4.A00();
    }

    @Override // X.AnonymousClass0jF
    public Cursor doQuery(Uri uri, String[] strArr, String str, String[] strArr2, String str2) {
        int length;
        String path = uri.getPath();
        if (path == null) {
            throw new IllegalArgumentException();
        } else if (!path.equals(MessengerServiceProviderContract.Status.CONTENT_URI.getPath()) && !path.equals(MessengerServiceProviderContract.Status.CONTENT_URI_SOCIALPLATFORM.getPath())) {
            throw new IllegalArgumentException();
        } else if (strArr == null || (length = strArr.length) > 1) {
            throw new IllegalArgumentException();
        } else if (length == 0) {
            return new MatrixCursor(new String[0]);
        } else {
            if ("active".equals(strArr[0])) {
                MatrixCursor matrixCursor = new MatrixCursor(strArr);
                matrixCursor.newRow().add("active", Integer.valueOf(MessengerService.sActive ? 1 : 0));
                return matrixCursor;
            }
            throw new IllegalArgumentException();
        }
    }

    @Override // com.facebook.secure.content.FbPermissionsContentProvider, X.AnonymousClass0jF
    public final boolean onCheckPermissions() {
        Context context = getContext();
        if (super.onCheckPermissions() || this.mTrustedCaller.A01(context, null, null)) {
            return true;
        }
        return false;
    }

    @Override // com.facebook.secure.content.FbPermissionsContentProvider, X.AnonymousClass0jF
    public final boolean onCheckReadOnlyPermissions() {
        Context context = getContext();
        if (super.onCheckReadOnlyPermissions() || this.mTrustedCaller.A01(context, null, null)) {
            return true;
        }
        return false;
    }
}
