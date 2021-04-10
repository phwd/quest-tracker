package com.oculus.horizon.linkedaccounts.provider;

import X.AbstractC06640p5;
import X.AnonymousClass0DC;
import X.AnonymousClass0J2;
import X.AnonymousClass117;
import X.C07820vP;
import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.net.Uri;
import com.facebook.ultralight.Eager;
import com.facebook.ultralight.Inject;
import com.oculus.content.OculusPublicContentProvider;
import com.oculus.horizon.fbconnect.FBConnectLogger;
import com.oculus.horizon.linkedaccounts.LinkedAccountsManager;
import com.oculus.horizon.linkedaccounts.model.LinkedAccount;
import com.oculus.horizon.linkedaccounts.model.ServiceProvider;
import com.oculus.horizon.linkedaccounts.provider.verifier.LinkedAccountsCallerVerifier;
import com.oculus.http.core.base.ApiErrorCodes;
import java.lang.reflect.Array;
import java.util.Arrays;
import java.util.Collection;
import java.util.Iterator;
import java.util.List;
import javax.annotation.Nullable;

public class LinkedAccountsContentProvider extends OculusPublicContentProvider {
    public static final String TAG = "LinkedAccountsContentProvider";
    @Inject
    @Eager
    public LinkedAccountsCallerVerifier mLinkedAccountsCallerVerifier;
    @Inject
    @Eager
    public LinkedAccountsManager mLinkedAccountsManager;
    @Inject
    @Eager
    public LinkedAccountsContentProviderLogger mLogger;

    public static final void _UL_staticInjectMe(AbstractC06640p5 r1, LinkedAccountsContentProvider linkedAccountsContentProvider) {
        linkedAccountsContentProvider.mLinkedAccountsManager = (LinkedAccountsManager) AnonymousClass117.A00(19, r1);
        linkedAccountsContentProvider.mLinkedAccountsCallerVerifier = (LinkedAccountsCallerVerifier) AnonymousClass117.A00(ApiErrorCodes.ERROR_CODE_FAILED_AUTHENTICATION_WRONG_PASSWORD, r1);
        linkedAccountsContentProvider.mLogger = (LinkedAccountsContentProviderLogger) AnonymousClass117.A00(451, r1);
    }

    private void assertCallerCanRead() {
        if (!this.mLinkedAccountsCallerVerifier.A00()) {
            throw new SecurityException("Access denied: no read permission");
        }
    }

    private void assertCallerCanWrite() {
        if (!this.mLinkedAccountsCallerVerifier.A01(getCallingPackage())) {
            throw new SecurityException("Access denied: no write permission");
        }
    }

    public static void assertIsContentURI(Uri uri) {
        if (!LinkedAccountsContentProviderContract.CONTENT_URI.equals(uri)) {
            throw new UnsupportedOperationException(FBConnectLogger.MESSAGE_UNSUPPORTED_URI);
        }
    }

    @Override // X.AbstractC09361bk
    public String doGetType(Uri uri) {
        throw new UnsupportedOperationException();
    }

    @Override // X.AbstractC09361bk
    public int doUpdate(Uri uri, ContentValues contentValues, @Nullable String str, @Nullable String[] strArr) {
        throw new UnsupportedOperationException();
    }

    /* JADX WARNING: Code restructure failed: missing block: B:3:0x0013, code lost:
        if (r3.mLinkedAccountsCallerVerifier.A01(getCallingPackage()) != false) goto L_0x0015;
     */
    @Override // com.facebook.secure.content.PublicContentProvider, X.AbstractC09361bk
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public final boolean onCheckPermissions() {
        /*
            r3 = this;
            com.oculus.horizon.linkedaccounts.provider.verifier.LinkedAccountsCallerVerifier r0 = r3.mLinkedAccountsCallerVerifier
            boolean r0 = r0.A00()
            if (r0 != 0) goto L_0x0015
            com.oculus.horizon.linkedaccounts.provider.verifier.LinkedAccountsCallerVerifier r1 = r3.mLinkedAccountsCallerVerifier
            java.lang.String r0 = r3.getCallingPackage()
            boolean r0 = r1.A01(r0)
            r2 = 0
            if (r0 == 0) goto L_0x0016
        L_0x0015:
            r2 = 1
        L_0x0016:
            com.oculus.horizon.linkedaccounts.provider.LinkedAccountsContentProviderLogger r1 = r3.mLogger
            java.lang.String r0 = "oculus_mobile_linked_accounts_provider_perm_check"
            com.oculus.logging.utils.Event r1 = com.oculus.horizon.linkedaccounts.provider.LinkedAccountsContentProviderLogger.A00(r1, r0, r3)
            java.lang.String r0 = "result"
            r1.A16(r0, r2)
            r1.A5L()
            return r2
        */
        throw new UnsupportedOperationException("Method not decompiled: com.oculus.horizon.linkedaccounts.provider.LinkedAccountsContentProvider.onCheckPermissions():boolean");
    }

    public static <T extends Enum<T>> T[] A00(Class<T> cls, String[] strArr, T t) {
        int A00;
        T t2;
        List<String> asList = Arrays.asList(strArr);
        if (asList instanceof Collection) {
            A00 = asList.size();
        } else {
            Iterator it = asList.iterator();
            long j = 0;
            while (it.hasNext()) {
                it.next();
                j++;
            }
            A00 = C07820vP.A00(j);
        }
        T[] tArr = (T[]) ((Enum[]) Array.newInstance((Class<?>) cls, A00));
        int i = 0;
        for (String str : asList) {
            int i2 = i + 1;
            T[] enumConstants = cls.getEnumConstants();
            int length = enumConstants.length;
            int i3 = 0;
            while (true) {
                if (i3 >= length) {
                    t2 = t;
                    break;
                }
                t2 = enumConstants[i3];
                if (t2.name().equalsIgnoreCase(str)) {
                    break;
                }
                i3++;
            }
            tArr[i] = t2;
            i = i2;
        }
        return tArr;
    }

    public static final void _UL_injectMe(Context context, LinkedAccountsContentProvider linkedAccountsContentProvider) {
        _UL_staticInjectMe((AbstractC06640p5) AnonymousClass0J2.get(context), linkedAccountsContentProvider);
    }

    @Override // X.AbstractC09361bk
    public int doDelete(Uri uri, @Nullable String str, @Nullable String[] strArr) {
        ServiceProvider[] serviceProviderArr;
        assertCallerCanWrite();
        assertIsContentURI(uri);
        int i = 0;
        if (strArr != null) {
            serviceProviderArr = (ServiceProvider[]) A00(ServiceProvider.class, strArr, ServiceProvider.UNKNOWN);
        } else {
            serviceProviderArr = new ServiceProvider[0];
        }
        AnonymousClass0DC A06 = AnonymousClass0DC.A06(
        /*  JADX ERROR: Method code generation error
            jadx.core.utils.exceptions.CodegenException: Error generate insn: 0x001a: INVOKE  (r1v1 'A06' X.0DC) = 
              (wrap: com.oculus.horizon.linkedaccounts.LinkedAccountsManager$3 : 0x0017: CONSTRUCTOR  (r0v0 com.oculus.horizon.linkedaccounts.LinkedAccountsManager$3) = 
              (wrap: com.oculus.horizon.linkedaccounts.LinkedAccountsManager : 0x0013: IGET  (r1v0 com.oculus.horizon.linkedaccounts.LinkedAccountsManager) = (r5v0 'this' com.oculus.horizon.linkedaccounts.provider.LinkedAccountsContentProvider A[IMMUTABLE_TYPE, THIS]) com.oculus.horizon.linkedaccounts.provider.LinkedAccountsContentProvider.mLinkedAccountsManager com.oculus.horizon.linkedaccounts.LinkedAccountsManager)
              (r4v1 'serviceProviderArr' com.oculus.horizon.linkedaccounts.model.ServiceProvider[])
             call: com.oculus.horizon.linkedaccounts.LinkedAccountsManager.3.<init>(com.oculus.horizon.linkedaccounts.LinkedAccountsManager, com.oculus.horizon.linkedaccounts.model.ServiceProvider[]):void type: CONSTRUCTOR)
             type: STATIC call: X.0DC.A06(java.util.concurrent.Callable):X.0DC in method: com.oculus.horizon.linkedaccounts.provider.LinkedAccountsContentProvider.doDelete(android.net.Uri, java.lang.String, java.lang.String[]):int, file: classes2.dex
            	at jadx.core.codegen.InsnGen.makeInsn(InsnGen.java:255)
            	at jadx.core.codegen.InsnGen.makeInsn(InsnGen.java:217)
            	at jadx.core.codegen.RegionGen.makeSimpleBlock(RegionGen.java:110)
            	at jadx.core.codegen.RegionGen.makeRegion(RegionGen.java:56)
            	at jadx.core.codegen.RegionGen.makeSimpleRegion(RegionGen.java:93)
            	at jadx.core.codegen.RegionGen.makeRegion(RegionGen.java:59)
            	at jadx.core.codegen.MethodGen.addRegionInsns(MethodGen.java:244)
            	at jadx.core.codegen.MethodGen.addInstructions(MethodGen.java:237)
            	at jadx.core.codegen.ClassGen.addMethodCode(ClassGen.java:342)
            	at jadx.core.codegen.ClassGen.addMethod(ClassGen.java:295)
            	at jadx.core.codegen.ClassGen.lambda$addInnerClsAndMethods$2(ClassGen.java:264)
            	at java.util.stream.ForEachOps$ForEachOp$OfRef.accept(ForEachOps.java:183)
            	at java.util.ArrayList.forEach(ArrayList.java:1259)
            	at java.util.stream.SortedOps$RefSortingSink.end(SortedOps.java:395)
            	at java.util.stream.Sink$ChainedReference.end(Sink.java:258)
            Caused by: jadx.core.utils.exceptions.CodegenException: Error generate insn: 0x0017: CONSTRUCTOR  (r0v0 com.oculus.horizon.linkedaccounts.LinkedAccountsManager$3) = 
              (wrap: com.oculus.horizon.linkedaccounts.LinkedAccountsManager : 0x0013: IGET  (r1v0 com.oculus.horizon.linkedaccounts.LinkedAccountsManager) = (r5v0 'this' com.oculus.horizon.linkedaccounts.provider.LinkedAccountsContentProvider A[IMMUTABLE_TYPE, THIS]) com.oculus.horizon.linkedaccounts.provider.LinkedAccountsContentProvider.mLinkedAccountsManager com.oculus.horizon.linkedaccounts.LinkedAccountsManager)
              (r4v1 'serviceProviderArr' com.oculus.horizon.linkedaccounts.model.ServiceProvider[])
             call: com.oculus.horizon.linkedaccounts.LinkedAccountsManager.3.<init>(com.oculus.horizon.linkedaccounts.LinkedAccountsManager, com.oculus.horizon.linkedaccounts.model.ServiceProvider[]):void type: CONSTRUCTOR in method: com.oculus.horizon.linkedaccounts.provider.LinkedAccountsContentProvider.doDelete(android.net.Uri, java.lang.String, java.lang.String[]):int, file: classes2.dex
            	at jadx.core.codegen.InsnGen.makeInsn(InsnGen.java:255)
            	at jadx.core.codegen.InsnGen.addWrappedArg(InsnGen.java:119)
            	at jadx.core.codegen.InsnGen.addArg(InsnGen.java:103)
            	at jadx.core.codegen.InsnGen.generateMethodArguments(InsnGen.java:806)
            	at jadx.core.codegen.InsnGen.makeInvoke(InsnGen.java:746)
            	at jadx.core.codegen.InsnGen.makeInsnBody(InsnGen.java:367)
            	at jadx.core.codegen.InsnGen.makeInsn(InsnGen.java:249)
            	... 14 more
            Caused by: jadx.core.utils.exceptions.JadxRuntimeException: Expected class to be processed at this point, class: com.oculus.horizon.linkedaccounts.LinkedAccountsManager, state: GENERATED_AND_UNLOADED
            	at jadx.core.dex.nodes.ClassNode.ensureProcessed(ClassNode.java:215)
            	at jadx.core.codegen.InsnGen.makeConstructor(InsnGen.java:630)
            	at jadx.core.codegen.InsnGen.makeInsnBody(InsnGen.java:363)
            	at jadx.core.codegen.InsnGen.makeInsn(InsnGen.java:230)
            	... 20 more
            */
        /*
            this = this;
            r5.assertCallerCanWrite()
            assertIsContentURI(r6)
            r3 = 0
            if (r8 == 0) goto L_0x001f
            java.lang.Class<com.oculus.horizon.linkedaccounts.model.ServiceProvider> r1 = com.oculus.horizon.linkedaccounts.model.ServiceProvider.class
            com.oculus.horizon.linkedaccounts.model.ServiceProvider r0 = com.oculus.horizon.linkedaccounts.model.ServiceProvider.UNKNOWN
            java.lang.Enum[] r4 = A00(r1, r8, r0)
            com.oculus.horizon.linkedaccounts.model.ServiceProvider[] r4 = (com.oculus.horizon.linkedaccounts.model.ServiceProvider[]) r4
        L_0x0013:
            com.oculus.horizon.linkedaccounts.LinkedAccountsManager r1 = r5.mLinkedAccountsManager
            com.oculus.horizon.linkedaccounts.LinkedAccountsManager$3 r0 = new com.oculus.horizon.linkedaccounts.LinkedAccountsManager$3
            r0.<init>(r4)
            X.0DC r1 = X.AnonymousClass0DC.A06(r0)
            goto L_0x0022
        L_0x001f:
            com.oculus.horizon.linkedaccounts.model.ServiceProvider[] r4 = new com.oculus.horizon.linkedaccounts.model.ServiceProvider[r3]
            goto L_0x0013
        L_0x0022:
            r1.A0H()     // Catch:{ InterruptedException -> 0x0025 }
        L_0x0025:
            boolean r0 = r1.A0K()
            if (r0 != 0) goto L_0x0035
            java.lang.Object r0 = r1.A0G()
            java.lang.Number r0 = (java.lang.Number) r0
            int r3 = r0.intValue()
        L_0x0035:
            com.oculus.horizon.linkedaccounts.provider.LinkedAccountsContentProviderLogger r1 = r5.mLogger
            java.lang.String r0 = "oculus_mobile_linked_accounts_provider_delete"
            com.oculus.logging.utils.Event r2 = com.oculus.horizon.linkedaccounts.provider.LinkedAccountsContentProviderLogger.A00(r1, r0, r5)
            r0 = 44
            java.lang.String r0 = java.lang.String.valueOf(r0)
            com.google.common.base.Joiner r1 = new com.google.common.base.Joiner
            r1.<init>(r0)
            java.util.List r0 = java.util.Arrays.asList(r4)
            java.lang.String r1 = r1.join(r0)
            java.lang.String r0 = "service_providers"
            r2.A15(r0, r1)
            java.lang.String r0 = "result"
            r2.A13(r0, r3)
            r2.A5L()
            return r3
        */
        throw new UnsupportedOperationException("Method not decompiled: com.oculus.horizon.linkedaccounts.provider.LinkedAccountsContentProvider.doDelete(android.net.Uri, java.lang.String, java.lang.String[]):int");
    }

    @Override // com.oculus.content.OculusPublicContentProvider
    public void doInitialization() {
        _UL_injectMe(getContext(), this);
    }

    @Override // X.AbstractC09361bk
    public Uri doInsert(Uri uri, ContentValues contentValues) {
        long longValue;
        assertCallerCanWrite();
        assertIsContentURI(uri);
        String asString = contentValues.getAsString("user_id");
        String asString2 = contentValues.getAsString("token");
        ServiceProvider fromString = ServiceProvider.fromString(contentValues.getAsString("service_provider"));
        AnonymousClass0DC A06 = AnonymousClass0DC.A06(
        /*  JADX ERROR: Method code generation error
            jadx.core.utils.exceptions.CodegenException: Error generate insn: 0x0023: INVOKE  (r1v1 'A06' X.0DC) = 
              (wrap: com.oculus.horizon.linkedaccounts.LinkedAccountsManager$1 : 0x0020: CONSTRUCTOR  (r0v3 com.oculus.horizon.linkedaccounts.LinkedAccountsManager$1) = 
              (wrap: com.oculus.horizon.linkedaccounts.LinkedAccountsManager : 0x001c: IGET  (r1v0 com.oculus.horizon.linkedaccounts.LinkedAccountsManager) = (r6v0 'this' com.oculus.horizon.linkedaccounts.provider.LinkedAccountsContentProvider A[IMMUTABLE_TYPE, THIS]) com.oculus.horizon.linkedaccounts.provider.LinkedAccountsContentProvider.mLinkedAccountsManager com.oculus.horizon.linkedaccounts.LinkedAccountsManager)
              (r3v0 'asString' java.lang.String)
              (r2v0 'asString2' java.lang.String)
              (r4v0 'fromString' com.oculus.horizon.linkedaccounts.model.ServiceProvider)
             call: com.oculus.horizon.linkedaccounts.LinkedAccountsManager.1.<init>(com.oculus.horizon.linkedaccounts.LinkedAccountsManager, java.lang.String, java.lang.String, com.oculus.horizon.linkedaccounts.model.ServiceProvider):void type: CONSTRUCTOR)
             type: STATIC call: X.0DC.A06(java.util.concurrent.Callable):X.0DC in method: com.oculus.horizon.linkedaccounts.provider.LinkedAccountsContentProvider.doInsert(android.net.Uri, android.content.ContentValues):android.net.Uri, file: classes2.dex
            	at jadx.core.codegen.InsnGen.makeInsn(InsnGen.java:255)
            	at jadx.core.codegen.InsnGen.makeInsn(InsnGen.java:217)
            	at jadx.core.codegen.RegionGen.makeSimpleBlock(RegionGen.java:110)
            	at jadx.core.codegen.RegionGen.makeRegion(RegionGen.java:56)
            	at jadx.core.codegen.RegionGen.makeSimpleRegion(RegionGen.java:93)
            	at jadx.core.codegen.RegionGen.makeRegion(RegionGen.java:59)
            	at jadx.core.codegen.MethodGen.addRegionInsns(MethodGen.java:244)
            	at jadx.core.codegen.MethodGen.addInstructions(MethodGen.java:237)
            	at jadx.core.codegen.ClassGen.addMethodCode(ClassGen.java:342)
            	at jadx.core.codegen.ClassGen.addMethod(ClassGen.java:295)
            	at jadx.core.codegen.ClassGen.lambda$addInnerClsAndMethods$2(ClassGen.java:264)
            	at java.util.stream.ForEachOps$ForEachOp$OfRef.accept(ForEachOps.java:183)
            	at java.util.ArrayList.forEach(ArrayList.java:1259)
            	at java.util.stream.SortedOps$RefSortingSink.end(SortedOps.java:395)
            	at java.util.stream.Sink$ChainedReference.end(Sink.java:258)
            Caused by: jadx.core.utils.exceptions.CodegenException: Error generate insn: 0x0020: CONSTRUCTOR  (r0v3 com.oculus.horizon.linkedaccounts.LinkedAccountsManager$1) = 
              (wrap: com.oculus.horizon.linkedaccounts.LinkedAccountsManager : 0x001c: IGET  (r1v0 com.oculus.horizon.linkedaccounts.LinkedAccountsManager) = (r6v0 'this' com.oculus.horizon.linkedaccounts.provider.LinkedAccountsContentProvider A[IMMUTABLE_TYPE, THIS]) com.oculus.horizon.linkedaccounts.provider.LinkedAccountsContentProvider.mLinkedAccountsManager com.oculus.horizon.linkedaccounts.LinkedAccountsManager)
              (r3v0 'asString' java.lang.String)
              (r2v0 'asString2' java.lang.String)
              (r4v0 'fromString' com.oculus.horizon.linkedaccounts.model.ServiceProvider)
             call: com.oculus.horizon.linkedaccounts.LinkedAccountsManager.1.<init>(com.oculus.horizon.linkedaccounts.LinkedAccountsManager, java.lang.String, java.lang.String, com.oculus.horizon.linkedaccounts.model.ServiceProvider):void type: CONSTRUCTOR in method: com.oculus.horizon.linkedaccounts.provider.LinkedAccountsContentProvider.doInsert(android.net.Uri, android.content.ContentValues):android.net.Uri, file: classes2.dex
            	at jadx.core.codegen.InsnGen.makeInsn(InsnGen.java:255)
            	at jadx.core.codegen.InsnGen.addWrappedArg(InsnGen.java:119)
            	at jadx.core.codegen.InsnGen.addArg(InsnGen.java:103)
            	at jadx.core.codegen.InsnGen.generateMethodArguments(InsnGen.java:806)
            	at jadx.core.codegen.InsnGen.makeInvoke(InsnGen.java:746)
            	at jadx.core.codegen.InsnGen.makeInsnBody(InsnGen.java:367)
            	at jadx.core.codegen.InsnGen.makeInsn(InsnGen.java:249)
            	... 14 more
            Caused by: jadx.core.utils.exceptions.JadxRuntimeException: Expected class to be processed at this point, class: com.oculus.horizon.linkedaccounts.LinkedAccountsManager, state: GENERATED_AND_UNLOADED
            	at jadx.core.dex.nodes.ClassNode.ensureProcessed(ClassNode.java:215)
            	at jadx.core.codegen.InsnGen.makeConstructor(InsnGen.java:630)
            	at jadx.core.codegen.InsnGen.makeInsnBody(InsnGen.java:363)
            	at jadx.core.codegen.InsnGen.makeInsn(InsnGen.java:230)
            	... 20 more
            */
        /*
            this = this;
            r6.assertCallerCanWrite()
            assertIsContentURI(r7)
            java.lang.String r0 = "user_id"
            java.lang.String r3 = r8.getAsString(r0)
            java.lang.String r0 = "token"
            java.lang.String r2 = r8.getAsString(r0)
            java.lang.String r5 = "service_provider"
            java.lang.String r0 = r8.getAsString(r5)
            com.oculus.horizon.linkedaccounts.model.ServiceProvider r4 = com.oculus.horizon.linkedaccounts.model.ServiceProvider.fromString(r0)
            com.oculus.horizon.linkedaccounts.LinkedAccountsManager r1 = r6.mLinkedAccountsManager
            com.oculus.horizon.linkedaccounts.LinkedAccountsManager$1 r0 = new com.oculus.horizon.linkedaccounts.LinkedAccountsManager$1
            r0.<init>(r3, r2, r4)
            X.0DC r1 = X.AnonymousClass0DC.A06(r0)
            r1.A0H()     // Catch:{ InterruptedException -> 0x002a }
        L_0x002a:
            boolean r0 = r1.A0K()
            if (r0 == 0) goto L_0x004e
            r1 = -1
        L_0x0032:
            com.oculus.horizon.linkedaccounts.provider.LinkedAccountsContentProviderLogger r3 = r6.mLogger
            java.lang.String r0 = "oculus_mobile_linked_accounts_provider_insert"
            com.oculus.logging.utils.Event r3 = com.oculus.horizon.linkedaccounts.provider.LinkedAccountsContentProviderLogger.A00(r3, r0, r6)
            java.lang.String r0 = r4.toString()
            r3.A15(r5, r0)
            java.lang.String r0 = "result"
            r3.A14(r0, r1)
            r3.A5L()
            android.net.Uri r0 = android.content.ContentUris.withAppendedId(r7, r1)
            return r0
        L_0x004e:
            java.lang.Object r0 = r1.A0G()
            java.lang.Number r0 = (java.lang.Number) r0
            long r1 = r0.longValue()
            goto L_0x0032
        */
        throw new UnsupportedOperationException("Method not decompiled: com.oculus.horizon.linkedaccounts.provider.LinkedAccountsContentProvider.doInsert(android.net.Uri, android.content.ContentValues):android.net.Uri");
    }

    @Override // X.AbstractC09361bk
    public Cursor doQuery(Uri uri, @Nullable String[] strArr, @Nullable String str, @Nullable String[] strArr2, @Nullable String str2) {
        ServiceProvider[] serviceProviderArr;
        List<LinkedAccount> list;
        assertCallerCanRead();
        assertIsContentURI(uri);
        String[] strArr3 = {"user_id", "access_token", "service_provider"};
        if (strArr2 != null) {
            serviceProviderArr = (ServiceProvider[]) A00(ServiceProvider.class, strArr2, ServiceProvider.UNKNOWN);
        } else {
            serviceProviderArr = new ServiceProvider[0];
        }
        AnonymousClass0DC A06 = AnonymousClass0DC.A06(
        /*  JADX ERROR: Method code generation error
            jadx.core.utils.exceptions.CodegenException: Error generate insn: 0x002b: INVOKE  (r1v1 'A06' X.0DC) = 
              (wrap: com.oculus.horizon.linkedaccounts.LinkedAccountsManager$2 : 0x0028: CONSTRUCTOR  (r0v3 com.oculus.horizon.linkedaccounts.LinkedAccountsManager$2) = 
              (wrap: com.oculus.horizon.linkedaccounts.LinkedAccountsManager : 0x0024: IGET  (r1v0 com.oculus.horizon.linkedaccounts.LinkedAccountsManager) = (r10v0 'this' com.oculus.horizon.linkedaccounts.provider.LinkedAccountsContentProvider A[IMMUTABLE_TYPE, THIS]) com.oculus.horizon.linkedaccounts.provider.LinkedAccountsContentProvider.mLinkedAccountsManager com.oculus.horizon.linkedaccounts.LinkedAccountsManager)
              (r2v1 'serviceProviderArr' com.oculus.horizon.linkedaccounts.model.ServiceProvider[])
             call: com.oculus.horizon.linkedaccounts.LinkedAccountsManager.2.<init>(com.oculus.horizon.linkedaccounts.LinkedAccountsManager, com.oculus.horizon.linkedaccounts.model.ServiceProvider[]):void type: CONSTRUCTOR)
             type: STATIC call: X.0DC.A06(java.util.concurrent.Callable):X.0DC in method: com.oculus.horizon.linkedaccounts.provider.LinkedAccountsContentProvider.doQuery(android.net.Uri, java.lang.String[], java.lang.String, java.lang.String[], java.lang.String):android.database.Cursor, file: classes2.dex
            	at jadx.core.codegen.InsnGen.makeInsn(InsnGen.java:255)
            	at jadx.core.codegen.InsnGen.makeInsn(InsnGen.java:217)
            	at jadx.core.codegen.RegionGen.makeSimpleBlock(RegionGen.java:110)
            	at jadx.core.codegen.RegionGen.makeRegion(RegionGen.java:56)
            	at jadx.core.codegen.RegionGen.makeSimpleRegion(RegionGen.java:93)
            	at jadx.core.codegen.RegionGen.makeRegion(RegionGen.java:59)
            	at jadx.core.codegen.MethodGen.addRegionInsns(MethodGen.java:244)
            	at jadx.core.codegen.MethodGen.addInstructions(MethodGen.java:237)
            	at jadx.core.codegen.ClassGen.addMethodCode(ClassGen.java:342)
            	at jadx.core.codegen.ClassGen.addMethod(ClassGen.java:295)
            	at jadx.core.codegen.ClassGen.lambda$addInnerClsAndMethods$2(ClassGen.java:264)
            	at java.util.stream.ForEachOps$ForEachOp$OfRef.accept(ForEachOps.java:183)
            	at java.util.ArrayList.forEach(ArrayList.java:1259)
            	at java.util.stream.SortedOps$RefSortingSink.end(SortedOps.java:395)
            	at java.util.stream.Sink$ChainedReference.end(Sink.java:258)
            Caused by: jadx.core.utils.exceptions.CodegenException: Error generate insn: 0x0028: CONSTRUCTOR  (r0v3 com.oculus.horizon.linkedaccounts.LinkedAccountsManager$2) = 
              (wrap: com.oculus.horizon.linkedaccounts.LinkedAccountsManager : 0x0024: IGET  (r1v0 com.oculus.horizon.linkedaccounts.LinkedAccountsManager) = (r10v0 'this' com.oculus.horizon.linkedaccounts.provider.LinkedAccountsContentProvider A[IMMUTABLE_TYPE, THIS]) com.oculus.horizon.linkedaccounts.provider.LinkedAccountsContentProvider.mLinkedAccountsManager com.oculus.horizon.linkedaccounts.LinkedAccountsManager)
              (r2v1 'serviceProviderArr' com.oculus.horizon.linkedaccounts.model.ServiceProvider[])
             call: com.oculus.horizon.linkedaccounts.LinkedAccountsManager.2.<init>(com.oculus.horizon.linkedaccounts.LinkedAccountsManager, com.oculus.horizon.linkedaccounts.model.ServiceProvider[]):void type: CONSTRUCTOR in method: com.oculus.horizon.linkedaccounts.provider.LinkedAccountsContentProvider.doQuery(android.net.Uri, java.lang.String[], java.lang.String, java.lang.String[], java.lang.String):android.database.Cursor, file: classes2.dex
            	at jadx.core.codegen.InsnGen.makeInsn(InsnGen.java:255)
            	at jadx.core.codegen.InsnGen.addWrappedArg(InsnGen.java:119)
            	at jadx.core.codegen.InsnGen.addArg(InsnGen.java:103)
            	at jadx.core.codegen.InsnGen.generateMethodArguments(InsnGen.java:806)
            	at jadx.core.codegen.InsnGen.makeInvoke(InsnGen.java:746)
            	at jadx.core.codegen.InsnGen.makeInsnBody(InsnGen.java:367)
            	at jadx.core.codegen.InsnGen.makeInsn(InsnGen.java:249)
            	... 14 more
            Caused by: jadx.core.utils.exceptions.JadxRuntimeException: Expected class to be processed at this point, class: com.oculus.horizon.linkedaccounts.LinkedAccountsManager, state: GENERATED_AND_UNLOADED
            	at jadx.core.dex.nodes.ClassNode.ensureProcessed(ClassNode.java:215)
            	at jadx.core.codegen.InsnGen.makeConstructor(InsnGen.java:630)
            	at jadx.core.codegen.InsnGen.makeInsnBody(InsnGen.java:363)
            	at jadx.core.codegen.InsnGen.makeInsn(InsnGen.java:230)
            	... 20 more
            */
        /*
        // Method dump skipped, instructions count: 186
        */
        throw new UnsupportedOperationException("Method not decompiled: com.oculus.horizon.linkedaccounts.provider.LinkedAccountsContentProvider.doQuery(android.net.Uri, java.lang.String[], java.lang.String, java.lang.String[], java.lang.String):android.database.Cursor");
    }
}
