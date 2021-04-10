package java.io;

import java.io.ObjectStreamClass;

/* renamed from: java.io.-$$Lambda$ObjectStreamClass$GVMp_c-BEBrBo_ZKh_HiLSO-fGo  reason: invalid class name */
/* compiled from: lambda */
public final /* synthetic */ class $$Lambda$ObjectStreamClass$GVMp_cBEBrBo_ZKh_HiLSOfGo implements ObjectStreamClass.DefaultSUIDCompatibilityListener {
    public static final /* synthetic */ $$Lambda$ObjectStreamClass$GVMp_cBEBrBo_ZKh_HiLSOfGo INSTANCE = new $$Lambda$ObjectStreamClass$GVMp_cBEBrBo_ZKh_HiLSOfGo();

    private /* synthetic */ $$Lambda$ObjectStreamClass$GVMp_cBEBrBo_ZKh_HiLSOfGo() {
    }

    @Override // java.io.ObjectStreamClass.DefaultSUIDCompatibilityListener
    public final void warnDefaultSUIDTargetVersionDependent(Class cls, long j) {
        System.logW("Class " + cls.getCanonicalName() + " relies on its default SUID which is dependent on the app's targetSdkVersion. To avoid problems during upgrade add the following to class " + cls.getCanonicalName() + "\n    private static final long serialVersionUID = " + j + "L;");
    }
}
