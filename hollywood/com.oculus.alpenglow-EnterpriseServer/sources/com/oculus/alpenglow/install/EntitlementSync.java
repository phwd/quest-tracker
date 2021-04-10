package com.oculus.alpenglow.install;

import X.AbstractC02990bJ;
import X.AnonymousClass0Lh;
import X.AnonymousClass0NK;
import X.AnonymousClass0R7;
import android.content.Context;
import android.net.Uri;
import android.os.Handler;
import com.facebook.inject.ApplicationScoped;
import com.facebook.ultralight.Dependencies;
import com.facebook.ultralight.Inject;
import com.oculus.alpenglow.config.ConfigChangeListener;
import com.oculus.alpenglow.config.Device;
import com.oculus.alpenglow.database.ApplicationDatabase;
import com.oculus.alpenglow.database.MigrationCompleteListener;
import com.oculus.alpenglow.lifecycle.StartupListener;
import com.oculus.executors.OculusThreadExecutor;
import com.oculus.libraryapi.OVRLibrary;
import com.oculus.managed.ManagedMode;
import com.oculus.ocms.library.provider.contract.OCMSLibraryContract;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.concurrent.atomic.AtomicBoolean;

@Dependencies({"_UL__ULSEP_com_oculus_libraryapi_OVRLibrary_ULSEP_BINDING_ID", "_UL__ULSEP_com_oculus_alpenglow_install_OCMSInstaller_ULSEP_BINDING_ID", "_UL__ULSEP_com_oculus_alpenglow_database_ApplicationDatabase_ULSEP_BINDING_ID", "_UL__ULSEP_com_oculus_managed_ManagedMode_ULSEP_BINDING_ID"})
@ApplicationScoped
public class EntitlementSync implements ConfigChangeListener, MigrationCompleteListener, StartupListener {
    public static final String TAG = "EnterpriseServer.EntitlementSync";
    public static volatile EntitlementSync _UL__ULSEP_com_oculus_alpenglow_install_EntitlementSync_ULSEP_INSTANCE;
    public AnonymousClass0R7 _UL_mInjectionContext;
    public final AtomicBoolean mIsRegistered = new AtomicBoolean(false);

    @Override // com.oculus.alpenglow.config.ConfigChangeListener
    public final void A5z(Device device, Device device2) {
        if (!((ApplicationDatabase) AnonymousClass0Lh.A03(2, 3, this._UL_mInjectionContext)).mMigrationToDeviceProtectedComplete) {
            AnonymousClass0NK.A01(TAG, "Migration incomplete, stop processing config");
        }
        A00();
        ((OVRLibrary) AnonymousClass0Lh.A03(0, 119, this._UL_mInjectionContext)).A01();
    }

    private void A00() {
        if (this.mIsRegistered.compareAndSet(false, true)) {
            OVRLibrary oVRLibrary = (OVRLibrary) AnonymousClass0Lh.A03(0, 119, this._UL_mInjectionContext);
            $$Lambda$EntitlementSync$GsCZuaZifjE9DawqOj21KnUe02 r6 = new OVRLibrary.OnChangeListener() {
                /* class com.oculus.alpenglow.install.$$Lambda$EntitlementSync$GsCZuaZifjE9DawqOj21KnUe02 */

                @Override // com.oculus.libraryapi.OVRLibrary.OnChangeListener
                public final void A5w(String str) {
                    OculusThreadExecutor.A00().execute(
                    /*  JADX ERROR: Method code generation error
                        jadx.core.utils.exceptions.CodegenException: Error generate insn: 0x000b: INVOKE  
                          (wrap: com.oculus.executors.OculusThreadExecutor : 0x0002: INVOKE  (r1v0 com.oculus.executors.OculusThreadExecutor) =  type: STATIC call: com.oculus.executors.OculusThreadExecutor.A00():com.oculus.executors.OculusThreadExecutor)
                          (wrap: com.oculus.alpenglow.install.-$$Lambda$EntitlementSync$wT4uD0TxvXp6iD8omrtxojziRqs2 : 0x0008: CONSTRUCTOR  (r0v0 com.oculus.alpenglow.install.-$$Lambda$EntitlementSync$wT4uD0TxvXp6iD8omrtxojziRqs2) = 
                          (wrap: com.oculus.alpenglow.install.EntitlementSync : 0x0000: IGET  (r2v0 com.oculus.alpenglow.install.EntitlementSync) = 
                          (r3v0 'this' com.oculus.alpenglow.install.-$$Lambda$EntitlementSync$GsCZuaZ-ifjE9Da-wqOj21KnUe02 A[IMMUTABLE_TYPE, THIS])
                         com.oculus.alpenglow.install.-$$Lambda$EntitlementSync$GsCZuaZ-ifjE9Da-wqOj21KnUe02.f$0 com.oculus.alpenglow.install.EntitlementSync)
                          (r4v0 'str' java.lang.String)
                         call: com.oculus.alpenglow.install.-$$Lambda$EntitlementSync$wT4uD0TxvXp6iD8omrtxojziRqs2.<init>(com.oculus.alpenglow.install.EntitlementSync, java.lang.String):void type: CONSTRUCTOR)
                         type: VIRTUAL call: com.oculus.executors.OculusThreadExecutor.execute(java.lang.Runnable):void in method: com.oculus.alpenglow.install.-$$Lambda$EntitlementSync$GsCZuaZ-ifjE9Da-wqOj21KnUe02.A5w(java.lang.String):void, file: classes2.dex
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
                        Caused by: jadx.core.utils.exceptions.CodegenException: Error generate insn: 0x0008: CONSTRUCTOR  (r0v0 com.oculus.alpenglow.install.-$$Lambda$EntitlementSync$wT4uD0TxvXp6iD8omrtxojziRqs2) = 
                          (wrap: com.oculus.alpenglow.install.EntitlementSync : 0x0000: IGET  (r2v0 com.oculus.alpenglow.install.EntitlementSync) = 
                          (r3v0 'this' com.oculus.alpenglow.install.-$$Lambda$EntitlementSync$GsCZuaZ-ifjE9Da-wqOj21KnUe02 A[IMMUTABLE_TYPE, THIS])
                         com.oculus.alpenglow.install.-$$Lambda$EntitlementSync$GsCZuaZ-ifjE9Da-wqOj21KnUe02.f$0 com.oculus.alpenglow.install.EntitlementSync)
                          (r4v0 'str' java.lang.String)
                         call: com.oculus.alpenglow.install.-$$Lambda$EntitlementSync$wT4uD0TxvXp6iD8omrtxojziRqs2.<init>(com.oculus.alpenglow.install.EntitlementSync, java.lang.String):void type: CONSTRUCTOR in method: com.oculus.alpenglow.install.-$$Lambda$EntitlementSync$GsCZuaZ-ifjE9Da-wqOj21KnUe02.A5w(java.lang.String):void, file: classes2.dex
                        	at jadx.core.codegen.InsnGen.makeInsn(InsnGen.java:255)
                        	at jadx.core.codegen.InsnGen.addWrappedArg(InsnGen.java:119)
                        	at jadx.core.codegen.InsnGen.addArg(InsnGen.java:103)
                        	at jadx.core.codegen.InsnGen.generateMethodArguments(InsnGen.java:806)
                        	at jadx.core.codegen.InsnGen.makeInvoke(InsnGen.java:746)
                        	at jadx.core.codegen.InsnGen.makeInsnBody(InsnGen.java:367)
                        	at jadx.core.codegen.InsnGen.makeInsn(InsnGen.java:249)
                        	... 14 more
                        Caused by: jadx.core.utils.exceptions.JadxRuntimeException: Expected class to be processed at this point, class: com.oculus.alpenglow.install.-$$Lambda$EntitlementSync$wT4uD0TxvXp6iD8omrtxojziRqs2, state: NOT_LOADED
                        	at jadx.core.dex.nodes.ClassNode.ensureProcessed(ClassNode.java:215)
                        	at jadx.core.codegen.InsnGen.makeConstructor(InsnGen.java:630)
                        	at jadx.core.codegen.InsnGen.makeInsnBody(InsnGen.java:363)
                        	at jadx.core.codegen.InsnGen.makeInsn(InsnGen.java:230)
                        	... 20 more
                        */
                    /*
                        this = this;
                        com.oculus.alpenglow.install.EntitlementSync r2 = com.oculus.alpenglow.install.EntitlementSync.this
                        com.oculus.executors.OculusThreadExecutor r1 = com.oculus.executors.OculusThreadExecutor.A00()
                        com.oculus.alpenglow.install.-$$Lambda$EntitlementSync$wT4uD0TxvXp6iD8omrtxojziRqs2 r0 = new com.oculus.alpenglow.install.-$$Lambda$EntitlementSync$wT4uD0TxvXp6iD8omrtxojziRqs2
                        r0.<init>(r2, r4)
                        r1.execute(r0)
                        return
                    */
                    throw new UnsupportedOperationException("Method not decompiled: com.oculus.alpenglow.install.$$Lambda$EntitlementSync$GsCZuaZifjE9DawqOj21KnUe02.A5w(java.lang.String):void");
                }
            };
            Uri build = new Uri.Builder().scheme("content").authority(OCMSLibraryContract.AUTHORITY).path("apps").build();
            synchronized (oVRLibrary) {
                ArrayList<OVRLibrary.LibraryContentObserver> arrayList = oVRLibrary.mEntitlementListeners.get(r6);
                if (arrayList == null) {
                    arrayList = new ArrayList<>();
                    oVRLibrary.mEntitlementListeners.put(r6, arrayList);
                }
                Iterator<OVRLibrary.LibraryContentObserver> it = arrayList.iterator();
                while (true) {
                    if (it.hasNext()) {
                        if (it.next().uri.equals(build)) {
                            break;
                        }
                    } else {
                        OVRLibrary.LibraryContentObserver libraryContentObserver = new OVRLibrary.LibraryContentObserver(build, r6, new Handler());
                        oVRLibrary.mContext.getContentResolver().registerContentObserver(build, true, libraryContentObserver);
                        arrayList.add(libraryContentObserver);
                        break;
                    }
                }
            }
        }
    }

    @Override // com.oculus.alpenglow.database.MigrationCompleteListener
    public final void A6G() {
        if (((ManagedMode) AnonymousClass0Lh.A03(3, 86, this._UL_mInjectionContext)).isEnterpriseModeEnabled) {
            A00();
            ((OVRLibrary) AnonymousClass0Lh.A03(0, 119, this._UL_mInjectionContext)).A01();
        }
    }

    @Override // com.oculus.alpenglow.lifecycle.StartupListener
    public final void A6b(Context context) {
        AnonymousClass0R7 r2 = this._UL_mInjectionContext;
        if (((ManagedMode) AnonymousClass0Lh.A03(3, 86, r2)).isEnterpriseModeEnabled) {
            if (!((ApplicationDatabase) AnonymousClass0Lh.A03(2, 3, r2)).mMigrationToDeviceProtectedComplete) {
                AnonymousClass0NK.A01(TAG, "Migration incomplete, stop processing config");
            }
            A00();
        }
    }

    @Inject
    public EntitlementSync(AbstractC02990bJ r3) {
        this._UL_mInjectionContext = new AnonymousClass0R7(4, r3);
    }
}
