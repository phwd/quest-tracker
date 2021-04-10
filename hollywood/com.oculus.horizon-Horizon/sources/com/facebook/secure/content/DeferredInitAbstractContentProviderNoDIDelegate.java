package com.facebook.secure.content;

import X.AnonymousClass006;
import X.AnonymousClass1bn;
import X.C02870bf;
import android.content.ContentProviderOperation;
import android.content.ContentProviderResult;
import android.content.ContentValues;
import android.content.OperationApplicationException;
import android.content.res.AssetFileDescriptor;
import android.content.res.Configuration;
import android.net.Uri;
import android.os.ParcelFileDescriptor;
import com.facebook.secure.providerinit.DeferredInitContentProvider;
import com.facebook.systrace.Systrace;
import com.oculus.content.PermissionChecks;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.concurrent.atomic.AtomicBoolean;

public abstract class DeferredInitAbstractContentProviderNoDIDelegate extends AnonymousClass1bn {
    public final AtomicBoolean A00 = new AtomicBoolean();

    public static void A00() {
        Systrace.A00(512);
    }

    private final void A01() {
        AtomicBoolean atomicBoolean = this.A00;
        synchronized (atomicBoolean) {
            if (!atomicBoolean.get()) {
                atomicBoolean.set(true);
            }
        }
    }

    public static void A04(DeferredInitAbstractContentProviderNoDIDelegate deferredInitAbstractContentProviderNoDIDelegate, String str) {
        if (Systrace.A03(512)) {
            Systrace.A01(512, AnonymousClass006.A07(deferredInitAbstractContentProviderNoDIDelegate.getClass().getSimpleName(), ".", str));
        }
    }

    @Override // X.AnonymousClass1bn
    public final int A05(Uri uri, ContentValues[] contentValuesArr) {
        A04(this, "bulkInsert");
        try {
            A02(this);
            return super.A05(uri, contentValuesArr);
        } finally {
            A00();
        }
    }

    @Override // X.AnonymousClass1bn
    public final AssetFileDescriptor A06(Uri uri, String str) throws FileNotFoundException {
        A04(this, "openAssetFile");
        try {
            if (str.contains("w")) {
                A02(this);
            } else {
                A03(this);
            }
            return super.A06(uri, str);
        } finally {
            A00();
        }
    }

    @Override // X.AnonymousClass1bn
    public final ParcelFileDescriptor A07(Uri uri, String str) throws FileNotFoundException {
        A04(this, "openFile");
        try {
            if (str.contains("w")) {
                A02(this);
            } else {
                A03(this);
            }
            return super.A07(uri, str);
        } finally {
            A00();
        }
    }

    @Override // X.AnonymousClass1bn
    public final void A08() {
        A04(this, "onLowMemory");
        try {
            if (this.A00.get()) {
                super.A08();
            }
        } finally {
            A00();
        }
    }

    @Override // X.AnonymousClass1bn
    public final void A09(int i) {
        A04(this, "onTrimMemory");
        try {
            if (this.A00.get()) {
                super.A09(i);
            }
        } finally {
            A00();
        }
    }

    @Override // X.AnonymousClass1bn
    public final void A0A(Configuration configuration) {
        A04(this, "onConfigurationChanged");
        try {
            if (this.A00.get()) {
                super.A0A(configuration);
            }
        } finally {
            A00();
        }
    }

    @Override // X.AnonymousClass1bn
    public final boolean A0B() {
        A04(this, "isTemporary");
        try {
            A03(this);
            return super.A0B();
        } finally {
            A00();
        }
    }

    @Override // X.AnonymousClass1bn
    public final ContentProviderResult[] A0C(ArrayList<ContentProviderOperation> arrayList) throws OperationApplicationException {
        A04(this, "applyBatch");
        try {
            A02(this);
            return super.A0C(arrayList);
        } finally {
            A00();
        }
    }

    public DeferredInitAbstractContentProviderNoDIDelegate(DeferredInitContentProvider deferredInitContentProvider) {
        super(deferredInitContentProvider);
        A04(this, "onCreate");
        A00();
    }

    public static final void A02(DeferredInitAbstractContentProviderNoDIDelegate deferredInitAbstractContentProviderNoDIDelegate) {
        deferredInitAbstractContentProviderNoDIDelegate.A01();
        if (!(deferredInitAbstractContentProviderNoDIDelegate instanceof SecureContentDelegate)) {
            if (deferredInitAbstractContentProviderNoDIDelegate instanceof PublicContentDelegate) {
                return;
            }
        } else if (C02870bf.A02(((AnonymousClass1bn) deferredInitAbstractContentProviderNoDIDelegate).A00.getContext())) {
            return;
        }
        throw new SecurityException(PermissionChecks.ACCESS_NOT_ALLOWED_MESSAGE);
    }

    public static final void A03(DeferredInitAbstractContentProviderNoDIDelegate deferredInitAbstractContentProviderNoDIDelegate) {
        deferredInitAbstractContentProviderNoDIDelegate.A01();
        if (!(deferredInitAbstractContentProviderNoDIDelegate instanceof SecureContentDelegate)) {
            if (deferredInitAbstractContentProviderNoDIDelegate instanceof PublicContentDelegate) {
                return;
            }
        } else if (C02870bf.A02(((AnonymousClass1bn) deferredInitAbstractContentProviderNoDIDelegate).A00.getContext())) {
            return;
        }
        throw new SecurityException(PermissionChecks.ACCESS_NOT_ALLOWED_MESSAGE);
    }
}
