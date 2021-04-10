package com.oculus.appsafety;

import java.util.function.Function;
import oculus.internal.functional.Try;

/* renamed from: com.oculus.appsafety.-$$Lambda$SafetySignalCollectorJobService$sxQFDK2DkQSljTMsnnjrWUzK_NI  reason: invalid class name */
/* compiled from: lambda */
public final /* synthetic */ class $$Lambda$SafetySignalCollectorJobService$sxQFDK2DkQSljTMsnnjrWUzK_NI implements Function {
    public static final /* synthetic */ $$Lambda$SafetySignalCollectorJobService$sxQFDK2DkQSljTMsnnjrWUzK_NI INSTANCE = new $$Lambda$SafetySignalCollectorJobService$sxQFDK2DkQSljTMsnnjrWUzK_NI();

    private /* synthetic */ $$Lambda$SafetySignalCollectorJobService$sxQFDK2DkQSljTMsnnjrWUzK_NI() {
    }

    @Override // java.util.function.Function
    public final Object apply(Object obj) {
        return Try.Try(
        /*  JADX ERROR: Method code generation error
            jadx.core.utils.exceptions.CodegenException: Error generate insn: 0x0006: RETURN  
              (wrap: oculus.internal.functional.Try : 0x0002: ONE_ARG  (r1v2 oculus.internal.functional.Try) = 
              (wrap: oculus.internal.functional.Try : 0x0005: INVOKE  
              (wrap: com.oculus.appsafety.-$$Lambda$SafetySignalCollectorJobService$GN_PjGbEPeuwfLyZYWtGYSZMlEY : 0x0002: CONSTRUCTOR  
              (wrap: com.oculus.appsafety.signals.ISafetySignal : 0x0000: CHECK_CAST (r1v1 com.oculus.appsafety.signals.ISafetySignal) = (com.oculus.appsafety.signals.ISafetySignal) (r1v0 'obj' java.lang.Object))
             call: com.oculus.appsafety.-$$Lambda$SafetySignalCollectorJobService$GN_PjGbEPeuwfLyZYWtGYSZMlEY.<init>(com.oculus.appsafety.signals.ISafetySignal):void type: CONSTRUCTOR)
             type: STATIC call: oculus.internal.functional.Try.Try(oculus.internal.functional.Try$F0):oculus.internal.functional.Try)
            )
             in method: com.oculus.appsafety.-$$Lambda$SafetySignalCollectorJobService$sxQFDK2DkQSljTMsnnjrWUzK_NI.apply(java.lang.Object):java.lang.Object, file: classes.dex
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
            Caused by: jadx.core.utils.exceptions.CodegenException: Error generate insn: 0x0002: ONE_ARG  (r1v2 oculus.internal.functional.Try) = 
              (wrap: oculus.internal.functional.Try : 0x0005: INVOKE  
              (wrap: com.oculus.appsafety.-$$Lambda$SafetySignalCollectorJobService$GN_PjGbEPeuwfLyZYWtGYSZMlEY : 0x0002: CONSTRUCTOR  
              (wrap: com.oculus.appsafety.signals.ISafetySignal : 0x0000: CHECK_CAST (r1v1 com.oculus.appsafety.signals.ISafetySignal) = (com.oculus.appsafety.signals.ISafetySignal) (r1v0 'obj' java.lang.Object))
             call: com.oculus.appsafety.-$$Lambda$SafetySignalCollectorJobService$GN_PjGbEPeuwfLyZYWtGYSZMlEY.<init>(com.oculus.appsafety.signals.ISafetySignal):void type: CONSTRUCTOR)
             type: STATIC call: oculus.internal.functional.Try.Try(oculus.internal.functional.Try$F0):oculus.internal.functional.Try)
             in method: com.oculus.appsafety.-$$Lambda$SafetySignalCollectorJobService$sxQFDK2DkQSljTMsnnjrWUzK_NI.apply(java.lang.Object):java.lang.Object, file: classes.dex
            	at jadx.core.codegen.InsnGen.makeInsn(InsnGen.java:255)
            	at jadx.core.codegen.InsnGen.addWrappedArg(InsnGen.java:119)
            	at jadx.core.codegen.InsnGen.addArg(InsnGen.java:103)
            	at jadx.core.codegen.InsnGen.makeInsnBody(InsnGen.java:313)
            	at jadx.core.codegen.InsnGen.makeInsn(InsnGen.java:249)
            	... 14 more
            Caused by: jadx.core.utils.exceptions.CodegenException: Error generate insn: 0x0005: INVOKE  
              (wrap: com.oculus.appsafety.-$$Lambda$SafetySignalCollectorJobService$GN_PjGbEPeuwfLyZYWtGYSZMlEY : 0x0002: CONSTRUCTOR  
              (wrap: com.oculus.appsafety.signals.ISafetySignal : 0x0000: CHECK_CAST (r1v1 com.oculus.appsafety.signals.ISafetySignal) = (com.oculus.appsafety.signals.ISafetySignal) (r1v0 'obj' java.lang.Object))
             call: com.oculus.appsafety.-$$Lambda$SafetySignalCollectorJobService$GN_PjGbEPeuwfLyZYWtGYSZMlEY.<init>(com.oculus.appsafety.signals.ISafetySignal):void type: CONSTRUCTOR)
             type: STATIC call: oculus.internal.functional.Try.Try(oculus.internal.functional.Try$F0):oculus.internal.functional.Try in method: com.oculus.appsafety.-$$Lambda$SafetySignalCollectorJobService$sxQFDK2DkQSljTMsnnjrWUzK_NI.apply(java.lang.Object):java.lang.Object, file: classes.dex
            	at jadx.core.codegen.InsnGen.makeInsn(InsnGen.java:255)
            	at jadx.core.codegen.InsnGen.addWrappedArg(InsnGen.java:119)
            	at jadx.core.codegen.InsnGen.addArg(InsnGen.java:103)
            	at jadx.core.codegen.InsnGen.addArg(InsnGen.java:94)
            	at jadx.core.codegen.InsnGen.makeInsnBody(InsnGen.java:481)
            	at jadx.core.codegen.InsnGen.makeInsn(InsnGen.java:230)
            	... 18 more
            Caused by: jadx.core.utils.exceptions.CodegenException: Error generate insn: 0x0002: CONSTRUCTOR  
              (wrap: com.oculus.appsafety.signals.ISafetySignal : 0x0000: CHECK_CAST (r1v1 com.oculus.appsafety.signals.ISafetySignal) = (com.oculus.appsafety.signals.ISafetySignal) (r1v0 'obj' java.lang.Object))
             call: com.oculus.appsafety.-$$Lambda$SafetySignalCollectorJobService$GN_PjGbEPeuwfLyZYWtGYSZMlEY.<init>(com.oculus.appsafety.signals.ISafetySignal):void type: CONSTRUCTOR in method: com.oculus.appsafety.-$$Lambda$SafetySignalCollectorJobService$sxQFDK2DkQSljTMsnnjrWUzK_NI.apply(java.lang.Object):java.lang.Object, file: classes.dex
            	at jadx.core.codegen.InsnGen.makeInsn(InsnGen.java:255)
            	at jadx.core.codegen.InsnGen.addWrappedArg(InsnGen.java:119)
            	at jadx.core.codegen.InsnGen.addArg(InsnGen.java:103)
            	at jadx.core.codegen.InsnGen.generateMethodArguments(InsnGen.java:806)
            	at jadx.core.codegen.InsnGen.makeInvoke(InsnGen.java:746)
            	at jadx.core.codegen.InsnGen.makeInsnBody(InsnGen.java:367)
            	at jadx.core.codegen.InsnGen.makeInsn(InsnGen.java:230)
            	... 23 more
            Caused by: jadx.core.utils.exceptions.JadxRuntimeException: Expected class to be processed at this point, class: com.oculus.appsafety.-$$Lambda$SafetySignalCollectorJobService$GN_PjGbEPeuwfLyZYWtGYSZMlEY, state: NOT_LOADED
            	at jadx.core.dex.nodes.ClassNode.ensureProcessed(ClassNode.java:215)
            	at jadx.core.codegen.InsnGen.makeConstructor(InsnGen.java:630)
            	at jadx.core.codegen.InsnGen.makeInsnBody(InsnGen.java:363)
            	at jadx.core.codegen.InsnGen.makeInsn(InsnGen.java:230)
            	... 29 more
            */
        /*
            this = this;
            com.oculus.appsafety.signals.ISafetySignal r1 = (com.oculus.appsafety.signals.ISafetySignal) r1
            oculus.internal.functional.Try r1 = com.oculus.appsafety.SafetySignalCollectorJobService.lambda$collectSafetySignals$10(r1)
            return r1
        */
        throw new UnsupportedOperationException("Method not decompiled: com.oculus.appsafety.$$Lambda$SafetySignalCollectorJobService$sxQFDK2DkQSljTMsnnjrWUzK_NI.apply(java.lang.Object):java.lang.Object");
    }
}
