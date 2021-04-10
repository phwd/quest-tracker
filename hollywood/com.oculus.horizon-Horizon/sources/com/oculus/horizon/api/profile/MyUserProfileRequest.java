package com.oculus.horizon.api.profile;

import com.google.common.base.Joiner;
import com.google.common.collect.ImmutableMap;
import com.oculus.http.core.base.ApiRequest;
import java.util.Calendar;

public class MyUserProfileRequest extends ApiRequest<MyUserProfileResponse> {
    public static final int DEFAULT_PIC_SIZE = 360;
    public final String accessToken;
    public final int profilePictureDimension;

    private String getPicDimensionString() {
        Joiner joiner = new Joiner("x");
        Integer valueOf = Integer.valueOf(this.profilePictureDimension);
        return joiner.join(
        /*  JADX ERROR: Method code generation error
            jadx.core.utils.exceptions.CodegenException: Error generate insn: 0x0019: RETURN  
              (wrap: java.lang.String : 0x0015: INVOKE  (r0v4 java.lang.String) = 
              (r3v0 'joiner' com.google.common.base.Joiner)
              (wrap: com.google.common.base.Joiner$3 : 0x0012: CONSTRUCTOR  (r0v3 com.google.common.base.Joiner$3) = 
              (wrap: java.lang.Object[] : 0x000e: NEW_ARRAY  (r1v0 java.lang.Object[]) = (0 int) type: java.lang.Object[])
              (r2v0 'valueOf' java.lang.Integer)
              (r2v0 'valueOf' java.lang.Integer)
             call: com.google.common.base.Joiner.3.<init>(java.lang.Object[], java.lang.Object, java.lang.Object):void type: CONSTRUCTOR)
             type: VIRTUAL call: com.google.common.base.Joiner.join(java.lang.Iterable):java.lang.String)
             in method: com.oculus.horizon.api.profile.MyUserProfileRequest.getPicDimensionString():java.lang.String, file: classes2.dex
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
            Caused by: jadx.core.utils.exceptions.CodegenException: Error generate insn: 0x0015: INVOKE  (r0v4 java.lang.String) = 
              (r3v0 'joiner' com.google.common.base.Joiner)
              (wrap: com.google.common.base.Joiner$3 : 0x0012: CONSTRUCTOR  (r0v3 com.google.common.base.Joiner$3) = 
              (wrap: java.lang.Object[] : 0x000e: NEW_ARRAY  (r1v0 java.lang.Object[]) = (0 int) type: java.lang.Object[])
              (r2v0 'valueOf' java.lang.Integer)
              (r2v0 'valueOf' java.lang.Integer)
             call: com.google.common.base.Joiner.3.<init>(java.lang.Object[], java.lang.Object, java.lang.Object):void type: CONSTRUCTOR)
             type: VIRTUAL call: com.google.common.base.Joiner.join(java.lang.Iterable):java.lang.String in method: com.oculus.horizon.api.profile.MyUserProfileRequest.getPicDimensionString():java.lang.String, file: classes2.dex
            	at jadx.core.codegen.InsnGen.makeInsn(InsnGen.java:255)
            	at jadx.core.codegen.InsnGen.addWrappedArg(InsnGen.java:119)
            	at jadx.core.codegen.InsnGen.addArg(InsnGen.java:103)
            	at jadx.core.codegen.InsnGen.makeInsnBody(InsnGen.java:313)
            	at jadx.core.codegen.InsnGen.makeInsn(InsnGen.java:249)
            	... 14 more
            Caused by: jadx.core.utils.exceptions.CodegenException: Error generate insn: 0x0012: CONSTRUCTOR  (r0v3 com.google.common.base.Joiner$3) = 
              (wrap: java.lang.Object[] : 0x000e: NEW_ARRAY  (r1v0 java.lang.Object[]) = (0 int) type: java.lang.Object[])
              (r2v0 'valueOf' java.lang.Integer)
              (r2v0 'valueOf' java.lang.Integer)
             call: com.google.common.base.Joiner.3.<init>(java.lang.Object[], java.lang.Object, java.lang.Object):void type: CONSTRUCTOR in method: com.oculus.horizon.api.profile.MyUserProfileRequest.getPicDimensionString():java.lang.String, file: classes2.dex
            	at jadx.core.codegen.InsnGen.makeInsn(InsnGen.java:255)
            	at jadx.core.codegen.InsnGen.addWrappedArg(InsnGen.java:119)
            	at jadx.core.codegen.InsnGen.addArg(InsnGen.java:103)
            	at jadx.core.codegen.InsnGen.generateMethodArguments(InsnGen.java:806)
            	at jadx.core.codegen.InsnGen.makeInvoke(InsnGen.java:746)
            	at jadx.core.codegen.InsnGen.makeInsnBody(InsnGen.java:367)
            	at jadx.core.codegen.InsnGen.makeInsn(InsnGen.java:230)
            	... 18 more
            Caused by: jadx.core.utils.exceptions.JadxRuntimeException: Expected class to be processed at this point, class: com.google.common.base.Joiner, state: GENERATED_AND_UNLOADED
            	at jadx.core.dex.nodes.ClassNode.ensureProcessed(ClassNode.java:215)
            	at jadx.core.codegen.InsnGen.makeConstructor(InsnGen.java:630)
            	at jadx.core.codegen.InsnGen.makeInsnBody(InsnGen.java:363)
            	at jadx.core.codegen.InsnGen.makeInsn(InsnGen.java:230)
            	... 24 more
            */
        /*
            this = this;
            java.lang.String r0 = "x"
            com.google.common.base.Joiner r3 = new com.google.common.base.Joiner
            r3.<init>(r0)
            int r0 = r4.profilePictureDimension
            java.lang.Integer r2 = java.lang.Integer.valueOf(r0)
            r0 = 0
            java.lang.Object[] r1 = new java.lang.Object[r0]
            com.google.common.base.Joiner$3 r0 = new com.google.common.base.Joiner$3
            r0.<init>(r1, r2, r2)
            java.lang.String r0 = r3.join(r0)
            return r0
        */
        throw new UnsupportedOperationException("Method not decompiled: com.oculus.horizon.api.profile.MyUserProfileRequest.getPicDimensionString():java.lang.String");
    }

    public ImmutableMap<String, String> getParams() {
        Calendar instance = Calendar.getInstance();
        instance.add(12, -10);
        return ImmutableMap.A03("pic_dimension", getPicDimensionString(), "sent_after", String.valueOf(instance.getTimeInMillis() / 1000));
    }

    public MyUserProfileRequest() {
        this(null);
    }

    public MyUserProfileRequest(String str) {
        this.accessToken = str;
        this.profilePictureDimension = 360;
    }
}
