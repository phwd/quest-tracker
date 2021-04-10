package oculus.internal.yadi;

import java.util.Optional;
import java.util.function.BinaryOperator;

/* renamed from: oculus.internal.yadi.-$$Lambda$InstallServiceHelpers$hJ1u0Ck38qA4Qktcf_LfZ1Qtw94  reason: invalid class name */
/* compiled from: lambda */
public final /* synthetic */ class $$Lambda$InstallServiceHelpers$hJ1u0Ck38qA4Qktcf_LfZ1Qtw94 implements BinaryOperator {
    public static final /* synthetic */ $$Lambda$InstallServiceHelpers$hJ1u0Ck38qA4Qktcf_LfZ1Qtw94 INSTANCE = new $$Lambda$InstallServiceHelpers$hJ1u0Ck38qA4Qktcf_LfZ1Qtw94();

    private /* synthetic */ $$Lambda$InstallServiceHelpers$hJ1u0Ck38qA4Qktcf_LfZ1Qtw94() {
    }

    @Override // java.util.function.BiFunction
    public final Object apply(Object obj, Object obj2) {
        return ((Optional) obj).flatMap(
        /*  JADX ERROR: Method code generation error
            jadx.core.utils.exceptions.CodegenException: Error generate insn: 0x0008: RETURN  
              (wrap: java.util.Optional : 0x0004: ONE_ARG  (r0v1 java.util.Optional) = 
              (wrap: java.util.Optional : 0x0005: INVOKE  
              (wrap: java.util.Optional : 0x0000: CHECK_CAST (r1v1 java.util.Optional) = (java.util.Optional) (r1v0 'obj' java.lang.Object))
              (wrap: oculus.internal.yadi.-$$Lambda$InstallServiceHelpers$6DRKxLXpclOBu8sMWgj49RiwGbQ : 0x0002: CONSTRUCTOR  
              (wrap: java.util.Optional : 0x0002: CHECK_CAST (r2v1 java.util.Optional) = (java.util.Optional) (r2v0 'obj2' java.lang.Object))
             call: oculus.internal.yadi.-$$Lambda$InstallServiceHelpers$6DRKxLXpclOBu8sMWgj49RiwGbQ.<init>(java.util.Optional):void type: CONSTRUCTOR)
             type: VIRTUAL call: java.util.Optional.flatMap(java.util.function.Function):java.util.Optional)
            )
             in method: oculus.internal.yadi.-$$Lambda$InstallServiceHelpers$hJ1u0Ck38qA4Qktcf_LfZ1Qtw94.apply(java.lang.Object, java.lang.Object):java.lang.Object, file: classes.dex
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
            Caused by: jadx.core.utils.exceptions.CodegenException: Error generate insn: 0x0004: ONE_ARG  (r0v1 java.util.Optional) = 
              (wrap: java.util.Optional : 0x0005: INVOKE  
              (wrap: java.util.Optional : 0x0000: CHECK_CAST (r1v1 java.util.Optional) = (java.util.Optional) (r1v0 'obj' java.lang.Object))
              (wrap: oculus.internal.yadi.-$$Lambda$InstallServiceHelpers$6DRKxLXpclOBu8sMWgj49RiwGbQ : 0x0002: CONSTRUCTOR  
              (wrap: java.util.Optional : 0x0002: CHECK_CAST (r2v1 java.util.Optional) = (java.util.Optional) (r2v0 'obj2' java.lang.Object))
             call: oculus.internal.yadi.-$$Lambda$InstallServiceHelpers$6DRKxLXpclOBu8sMWgj49RiwGbQ.<init>(java.util.Optional):void type: CONSTRUCTOR)
             type: VIRTUAL call: java.util.Optional.flatMap(java.util.function.Function):java.util.Optional)
             in method: oculus.internal.yadi.-$$Lambda$InstallServiceHelpers$hJ1u0Ck38qA4Qktcf_LfZ1Qtw94.apply(java.lang.Object, java.lang.Object):java.lang.Object, file: classes.dex
            	at jadx.core.codegen.InsnGen.makeInsn(InsnGen.java:255)
            	at jadx.core.codegen.InsnGen.addWrappedArg(InsnGen.java:119)
            	at jadx.core.codegen.InsnGen.addArg(InsnGen.java:103)
            	at jadx.core.codegen.InsnGen.makeInsnBody(InsnGen.java:313)
            	at jadx.core.codegen.InsnGen.makeInsn(InsnGen.java:249)
            	... 14 more
            Caused by: jadx.core.utils.exceptions.CodegenException: Error generate insn: 0x0005: INVOKE  
              (wrap: java.util.Optional : 0x0000: CHECK_CAST (r1v1 java.util.Optional) = (java.util.Optional) (r1v0 'obj' java.lang.Object))
              (wrap: oculus.internal.yadi.-$$Lambda$InstallServiceHelpers$6DRKxLXpclOBu8sMWgj49RiwGbQ : 0x0002: CONSTRUCTOR  
              (wrap: java.util.Optional : 0x0002: CHECK_CAST (r2v1 java.util.Optional) = (java.util.Optional) (r2v0 'obj2' java.lang.Object))
             call: oculus.internal.yadi.-$$Lambda$InstallServiceHelpers$6DRKxLXpclOBu8sMWgj49RiwGbQ.<init>(java.util.Optional):void type: CONSTRUCTOR)
             type: VIRTUAL call: java.util.Optional.flatMap(java.util.function.Function):java.util.Optional in method: oculus.internal.yadi.-$$Lambda$InstallServiceHelpers$hJ1u0Ck38qA4Qktcf_LfZ1Qtw94.apply(java.lang.Object, java.lang.Object):java.lang.Object, file: classes.dex
            	at jadx.core.codegen.InsnGen.makeInsn(InsnGen.java:255)
            	at jadx.core.codegen.InsnGen.addWrappedArg(InsnGen.java:119)
            	at jadx.core.codegen.InsnGen.addArg(InsnGen.java:103)
            	at jadx.core.codegen.InsnGen.addArg(InsnGen.java:94)
            	at jadx.core.codegen.InsnGen.makeInsnBody(InsnGen.java:481)
            	at jadx.core.codegen.InsnGen.makeInsn(InsnGen.java:230)
            	... 18 more
            Caused by: jadx.core.utils.exceptions.CodegenException: Error generate insn: 0x0002: CONSTRUCTOR  
              (wrap: java.util.Optional : 0x0002: CHECK_CAST (r2v1 java.util.Optional) = (java.util.Optional) (r2v0 'obj2' java.lang.Object))
             call: oculus.internal.yadi.-$$Lambda$InstallServiceHelpers$6DRKxLXpclOBu8sMWgj49RiwGbQ.<init>(java.util.Optional):void type: CONSTRUCTOR in method: oculus.internal.yadi.-$$Lambda$InstallServiceHelpers$hJ1u0Ck38qA4Qktcf_LfZ1Qtw94.apply(java.lang.Object, java.lang.Object):java.lang.Object, file: classes.dex
            	at jadx.core.codegen.InsnGen.makeInsn(InsnGen.java:255)
            	at jadx.core.codegen.InsnGen.addWrappedArg(InsnGen.java:119)
            	at jadx.core.codegen.InsnGen.addArg(InsnGen.java:103)
            	at jadx.core.codegen.InsnGen.generateMethodArguments(InsnGen.java:806)
            	at jadx.core.codegen.InsnGen.makeInvoke(InsnGen.java:746)
            	at jadx.core.codegen.InsnGen.makeInsnBody(InsnGen.java:367)
            	at jadx.core.codegen.InsnGen.makeInsn(InsnGen.java:230)
            	... 23 more
            Caused by: jadx.core.utils.exceptions.JadxRuntimeException: Expected class to be processed at this point, class: oculus.internal.yadi.-$$Lambda$InstallServiceHelpers$6DRKxLXpclOBu8sMWgj49RiwGbQ, state: NOT_LOADED
            	at jadx.core.dex.nodes.ClassNode.ensureProcessed(ClassNode.java:215)
            	at jadx.core.codegen.InsnGen.makeConstructor(InsnGen.java:630)
            	at jadx.core.codegen.InsnGen.makeInsnBody(InsnGen.java:363)
            	at jadx.core.codegen.InsnGen.makeInsn(InsnGen.java:230)
            	... 29 more
            */
        /*
            this = this;
            java.util.Optional r1 = (java.util.Optional) r1
            java.util.Optional r2 = (java.util.Optional) r2
            java.util.Optional r0 = oculus.internal.yadi.InstallServiceHelpers.lambda$foldDownloadSize$2(r1, r2)
            return r0
        */
        throw new UnsupportedOperationException("Method not decompiled: oculus.internal.yadi.$$Lambda$InstallServiceHelpers$hJ1u0Ck38qA4Qktcf_LfZ1Qtw94.apply(java.lang.Object, java.lang.Object):java.lang.Object");
    }
}
