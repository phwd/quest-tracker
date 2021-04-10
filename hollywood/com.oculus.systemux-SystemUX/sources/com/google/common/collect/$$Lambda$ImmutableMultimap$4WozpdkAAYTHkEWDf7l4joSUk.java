package com.google.common.collect;

import java.util.Collection;
import java.util.Map;
import java.util.function.Function;

/* renamed from: com.google.common.collect.-$$Lambda$ImmutableMultimap$4Wo-zpdkA-AYTHkEWDf7l4joSUk  reason: invalid class name */
/* compiled from: lambda */
public final /* synthetic */ class $$Lambda$ImmutableMultimap$4WozpdkAAYTHkEWDf7l4joSUk implements Function {
    public static final /* synthetic */ $$Lambda$ImmutableMultimap$4WozpdkAAYTHkEWDf7l4joSUk INSTANCE = new $$Lambda$ImmutableMultimap$4WozpdkAAYTHkEWDf7l4joSUk();

    private /* synthetic */ $$Lambda$ImmutableMultimap$4WozpdkAAYTHkEWDf7l4joSUk() {
    }

    @Override // java.util.function.Function
    public final Object apply(Object obj) {
        Map.Entry entry;
        return CollectSpliterators.map(((Collection) entry.getValue()).spliterator(), 
        /*  JADX ERROR: Method code generation error
            jadx.core.utils.exceptions.CodegenException: Error generate insn: 0x0006: RETURN  
              (wrap: java.util.Spliterator : 0x0002: ONE_ARG  (r1v2 java.util.Spliterator) = 
              (wrap: java.util.Spliterator : 0x0013: INVOKE  
              (wrap: java.util.Spliterator : 0x000a: INVOKE  
              (wrap: java.util.Collection : 0x0008: CHECK_CAST (java.util.Collection) (wrap: java.lang.Object : 0x0004: INVOKE  (r1v1 'entry' java.util.Map$Entry) type: INTERFACE call: java.util.Map.Entry.getValue():java.lang.Object))
             type: INTERFACE call: java.util.Collection.spliterator():java.util.Spliterator)
              (wrap: com.google.common.collect.-$$Lambda$ImmutableMultimap$DlsdprBe_mrUXiodCaY-ewKzqvU : 0x0010: CONSTRUCTOR  
              (wrap: java.lang.Object : 0x0000: INVOKE  
              (wrap: java.util.Map$Entry : 0x0000: CHECK_CAST (r1v1 'entry' java.util.Map$Entry) = (java.util.Map$Entry) (r1v0 'obj' java.lang.Object))
             type: INTERFACE call: java.util.Map.Entry.getKey():java.lang.Object)
             call: com.google.common.collect.-$$Lambda$ImmutableMultimap$DlsdprBe_mrUXiodCaY-ewKzqvU.<init>(java.lang.Object):void type: CONSTRUCTOR)
             type: STATIC call: com.google.common.collect.CollectSpliterators.map(java.util.Spliterator, java.util.function.Function):java.util.Spliterator)
            )
             in method: com.google.common.collect.-$$Lambda$ImmutableMultimap$4Wo-zpdkA-AYTHkEWDf7l4joSUk.apply(java.lang.Object):java.lang.Object, file: classes.dex
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
            Caused by: jadx.core.utils.exceptions.CodegenException: Error generate insn: 0x0002: ONE_ARG  (r1v2 java.util.Spliterator) = 
              (wrap: java.util.Spliterator : 0x0013: INVOKE  
              (wrap: java.util.Spliterator : 0x000a: INVOKE  
              (wrap: java.util.Collection : 0x0008: CHECK_CAST (java.util.Collection) (wrap: java.lang.Object : 0x0004: INVOKE  (r1v1 'entry' java.util.Map$Entry) type: INTERFACE call: java.util.Map.Entry.getValue():java.lang.Object))
             type: INTERFACE call: java.util.Collection.spliterator():java.util.Spliterator)
              (wrap: com.google.common.collect.-$$Lambda$ImmutableMultimap$DlsdprBe_mrUXiodCaY-ewKzqvU : 0x0010: CONSTRUCTOR  
              (wrap: java.lang.Object : 0x0000: INVOKE  
              (wrap: java.util.Map$Entry : 0x0000: CHECK_CAST (r1v1 'entry' java.util.Map$Entry) = (java.util.Map$Entry) (r1v0 'obj' java.lang.Object))
             type: INTERFACE call: java.util.Map.Entry.getKey():java.lang.Object)
             call: com.google.common.collect.-$$Lambda$ImmutableMultimap$DlsdprBe_mrUXiodCaY-ewKzqvU.<init>(java.lang.Object):void type: CONSTRUCTOR)
             type: STATIC call: com.google.common.collect.CollectSpliterators.map(java.util.Spliterator, java.util.function.Function):java.util.Spliterator)
             in method: com.google.common.collect.-$$Lambda$ImmutableMultimap$4Wo-zpdkA-AYTHkEWDf7l4joSUk.apply(java.lang.Object):java.lang.Object, file: classes.dex
            	at jadx.core.codegen.InsnGen.makeInsn(InsnGen.java:255)
            	at jadx.core.codegen.InsnGen.addWrappedArg(InsnGen.java:119)
            	at jadx.core.codegen.InsnGen.addArg(InsnGen.java:103)
            	at jadx.core.codegen.InsnGen.makeInsnBody(InsnGen.java:313)
            	at jadx.core.codegen.InsnGen.makeInsn(InsnGen.java:249)
            	... 14 more
            Caused by: jadx.core.utils.exceptions.CodegenException: Error generate insn: 0x0013: INVOKE  
              (wrap: java.util.Spliterator : 0x000a: INVOKE  
              (wrap: java.util.Collection : 0x0008: CHECK_CAST (java.util.Collection) (wrap: java.lang.Object : 0x0004: INVOKE  (r1v1 'entry' java.util.Map$Entry) type: INTERFACE call: java.util.Map.Entry.getValue():java.lang.Object))
             type: INTERFACE call: java.util.Collection.spliterator():java.util.Spliterator)
              (wrap: com.google.common.collect.-$$Lambda$ImmutableMultimap$DlsdprBe_mrUXiodCaY-ewKzqvU : 0x0010: CONSTRUCTOR  
              (wrap: java.lang.Object : 0x0000: INVOKE  
              (wrap: java.util.Map$Entry : 0x0000: CHECK_CAST (r1v1 'entry' java.util.Map$Entry) = (java.util.Map$Entry) (r1v0 'obj' java.lang.Object))
             type: INTERFACE call: java.util.Map.Entry.getKey():java.lang.Object)
             call: com.google.common.collect.-$$Lambda$ImmutableMultimap$DlsdprBe_mrUXiodCaY-ewKzqvU.<init>(java.lang.Object):void type: CONSTRUCTOR)
             type: STATIC call: com.google.common.collect.CollectSpliterators.map(java.util.Spliterator, java.util.function.Function):java.util.Spliterator in method: com.google.common.collect.-$$Lambda$ImmutableMultimap$4Wo-zpdkA-AYTHkEWDf7l4joSUk.apply(java.lang.Object):java.lang.Object, file: classes.dex
            	at jadx.core.codegen.InsnGen.makeInsn(InsnGen.java:255)
            	at jadx.core.codegen.InsnGen.addWrappedArg(InsnGen.java:119)
            	at jadx.core.codegen.InsnGen.addArg(InsnGen.java:103)
            	at jadx.core.codegen.InsnGen.addArg(InsnGen.java:94)
            	at jadx.core.codegen.InsnGen.makeInsnBody(InsnGen.java:481)
            	at jadx.core.codegen.InsnGen.makeInsn(InsnGen.java:230)
            	... 18 more
            Caused by: jadx.core.utils.exceptions.CodegenException: Error generate insn: 0x0010: CONSTRUCTOR  
              (wrap: java.lang.Object : 0x0000: INVOKE  
              (wrap: java.util.Map$Entry : 0x0000: CHECK_CAST (r1v1 'entry' java.util.Map$Entry) = (java.util.Map$Entry) (r1v0 'obj' java.lang.Object))
             type: INTERFACE call: java.util.Map.Entry.getKey():java.lang.Object)
             call: com.google.common.collect.-$$Lambda$ImmutableMultimap$DlsdprBe_mrUXiodCaY-ewKzqvU.<init>(java.lang.Object):void type: CONSTRUCTOR in method: com.google.common.collect.-$$Lambda$ImmutableMultimap$4Wo-zpdkA-AYTHkEWDf7l4joSUk.apply(java.lang.Object):java.lang.Object, file: classes.dex
            	at jadx.core.codegen.InsnGen.makeInsn(InsnGen.java:255)
            	at jadx.core.codegen.InsnGen.addWrappedArg(InsnGen.java:119)
            	at jadx.core.codegen.InsnGen.addArg(InsnGen.java:103)
            	at jadx.core.codegen.InsnGen.generateMethodArguments(InsnGen.java:806)
            	at jadx.core.codegen.InsnGen.makeInvoke(InsnGen.java:746)
            	at jadx.core.codegen.InsnGen.makeInsnBody(InsnGen.java:367)
            	at jadx.core.codegen.InsnGen.makeInsn(InsnGen.java:230)
            	... 23 more
            Caused by: jadx.core.utils.exceptions.JadxRuntimeException: Expected class to be processed at this point, class: com.google.common.collect.-$$Lambda$ImmutableMultimap$DlsdprBe_mrUXiodCaY-ewKzqvU, state: NOT_LOADED
            	at jadx.core.dex.nodes.ClassNode.ensureProcessed(ClassNode.java:215)
            	at jadx.core.codegen.InsnGen.makeConstructor(InsnGen.java:630)
            	at jadx.core.codegen.InsnGen.makeInsnBody(InsnGen.java:363)
            	at jadx.core.codegen.InsnGen.makeInsn(InsnGen.java:230)
            	... 29 more
            */
        /*
            this = this;
            java.util.Map$Entry r1 = (java.util.Map.Entry) r1
            java.util.Spliterator r1 = com.google.common.collect.ImmutableMultimap.lambda$entrySpliterator$1(r1)
            return r1
        */
        throw new UnsupportedOperationException("Method not decompiled: com.google.common.collect.$$Lambda$ImmutableMultimap$4WozpdkAAYTHkEWDf7l4joSUk.apply(java.lang.Object):java.lang.Object");
    }
}
