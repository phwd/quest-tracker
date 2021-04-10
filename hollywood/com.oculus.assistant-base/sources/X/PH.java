package X;

import java.io.Serializable;

public final class PH extends AbstractC1059rZ implements Serializable {
    public static final PH A00 = new PH();
    public static final long serialVersionUID = 1;

    /* JADX WARNING: Code restructure failed: missing block: B:188:0x03f6, code lost:
        if (r9 == null) goto L_0x03f8;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:214:0x0459, code lost:
        if (r9 != null) goto L_0x02b0;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:236:0x04be, code lost:
        if (r3.startsWith("org.hibernate.proxy.") == false) goto L_0x04f2;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:238:0x04c6, code lost:
        if (r0._class.isEnum() == false) goto L_0x04c8;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:239:0x04c8, code lost:
        r3 = r0._class;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:240:0x04d0, code lost:
        if (java.util.Iterator.class.isAssignableFrom(r3) == false) goto L_0x0a55;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:241:0x04d2, code lost:
        r5 = r0.A06(0);
     */
    /* JADX WARNING: Code restructure failed: missing block: B:242:0x04d7, code lost:
        if (r5 != null) goto L_0x04e0;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:243:0x04d9, code lost:
        r5 = new X.fF(java.lang.Object.class);
     */
    /* JADX WARNING: Code restructure failed: missing block: B:244:0x04e0, code lost:
        r3 = A03(r3, r5);
        r9 = new com.fasterxml.jackson.databind.ser.impl.IteratorSerializer(r5, X.AbstractC1059rZ.A01(r3, r4, r3), r3, (X.O5) null);
     */
    /* JADX WARNING: Code restructure failed: missing block: B:245:0x04f2, code lost:
        r1 = r4.A00;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:246:0x04fa, code lost:
        if (r1._class != java.lang.Object.class) goto L_0x0501;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:247:0x04fc, code lost:
        r9 = r38._unknownTypeSerializer;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:248:0x04fe, code lost:
        if (r9 != null) goto L_0x02b0;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:249:0x0501, code lost:
        r3 = r38._config;
        r1 = new X.PY(r4);
        r1.A00 = r3;
        r10 = (X.C1046rL) r4;
        r11 = r10.A0A;
        r9 = r3.A01();
        r8 = new java.util.HashMap();
        r13 = r11.iterator();
     */
    /* JADX WARNING: Code restructure failed: missing block: B:251:0x0520, code lost:
        if (r13.hasNext() == false) goto L_0x056d;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:252:0x0522, code lost:
        r6 = ((X.PE) r13.next()).A08();
     */
    /* JADX WARNING: Code restructure failed: missing block: B:253:0x052c, code lost:
        if (r6 == null) goto L_0x0569;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:254:0x052e, code lost:
        r12 = r6.A0J();
        r6 = (java.lang.Boolean) r8.get(r12);
     */
    /* JADX WARNING: Code restructure failed: missing block: B:255:0x0538, code lost:
        if (r6 != null) goto L_0x0563;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:256:0x053a, code lost:
        r7 = ((X.C1046rL) r3.A02(r3.A03(r12))).A09;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:257:0x0548, code lost:
        if ((r9 instanceof X.Rw) == false) goto L_0x055e;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:258:0x054a, code lost:
        r6 = (com.fasterxml.jackson.annotation.JsonIgnoreType) r7.A0L(com.fasterxml.jackson.annotation.JsonIgnoreType.class);
     */
    /* JADX WARNING: Code restructure failed: missing block: B:259:0x0552, code lost:
        if (r6 == null) goto L_0x055e;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:260:0x0554, code lost:
        r6 = java.lang.Boolean.valueOf(r6.value());
     */
    /* JADX WARNING: Code restructure failed: missing block: B:261:0x055c, code lost:
        if (r6 != null) goto L_0x0560;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:262:0x055e, code lost:
        r6 = java.lang.Boolean.FALSE;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:263:0x0560, code lost:
        r8.put(r12, r6);
     */
    /* JADX WARNING: Code restructure failed: missing block: B:265:0x0567, code lost:
        if (r6.booleanValue() == false) goto L_0x051c;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:266:0x0569, code lost:
        r13.remove();
     */
    /* JADX WARNING: Code restructure failed: missing block: B:268:0x0573, code lost:
        if (r3.A05(X.EnumC1027qy.REQUIRE_SETTERS_FOR_GETTERS) == false) goto L_0x0595;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:269:0x0575, code lost:
        r8 = r11.iterator();
     */
    /* JADX WARNING: Code restructure failed: missing block: B:271:0x057d, code lost:
        if (r8.hasNext() == false) goto L_0x0595;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:272:0x057f, code lost:
        r7 = (X.PE) r8.next();
     */
    /* JADX WARNING: Code restructure failed: missing block: B:273:0x0589, code lost:
        if (r7.A09() != null) goto L_0x0579;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:275:0x058f, code lost:
        if (r7.A0I() != false) goto L_0x0579;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:276:0x0591, code lost:
        r8.remove();
     */
    /* JADX WARNING: Code restructure failed: missing block: B:278:0x059a, code lost:
        if (r11.isEmpty() == false) goto L_0x05bb;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:279:0x059c, code lost:
        r8 = new java.util.ArrayList();
     */
    /* JADX WARNING: Code restructure failed: missing block: B:280:0x05a1, code lost:
        r7 = r37._factoryConfig._modifiers;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:281:0x05a6, code lost:
        if (r7.length <= 0) goto L_0x0855;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:282:0x05a8, code lost:
        r7 = new X.Q2(r7).iterator();
     */
    /* JADX WARNING: Code restructure failed: missing block: B:284:0x05b5, code lost:
        if (r7.hasNext() == false) goto L_0x0855;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:285:0x05b7, code lost:
        r7.next();
     */
    /* JADX WARNING: Code restructure failed: missing block: B:286:0x05bb, code lost:
        r23 = X.AbstractC1059rZ.A01(r3, r4, null);
        r9 = new X.C0281Pd(r3, r4);
        r8 = new java.util.ArrayList(r11.size());
        r22 = r4.A05();
        r21 = r11.iterator();
     */
    /* JADX WARNING: Code restructure failed: missing block: B:288:0x05d9, code lost:
        if (r21.hasNext() == false) goto L_0x05a1;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:289:0x05db, code lost:
        r6 = (X.PE) r21.next();
        r7 = r6.A08();
        r12 = r6 instanceof X.C1052rR;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:290:0x05eb, code lost:
        if (r12 == false) goto L_0x061c;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:291:0x05ed, code lost:
        r11 = (X.C1052rR) r6;
        r6 = (java.lang.Boolean) X.C1052rR.A04(r11, new X.C1050rP(r11));
     */
    /* JADX WARNING: Code restructure failed: missing block: B:292:0x05fc, code lost:
        if (r6 == null) goto L_0x061c;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:294:0x0602, code lost:
        if (r6.booleanValue() == false) goto L_0x061c;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:295:0x0604, code lost:
        if (r7 == null) goto L_0x05d5;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:297:0x060c, code lost:
        if (r3.A05(X.EnumC1027qy.CAN_OVERRIDE_ACCESS_MODIFIERS) == false) goto L_0x0615;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:298:0x060e, code lost:
        X.Q5.A06(r7.A0R());
     */
    /* JADX WARNING: Code restructure failed: missing block: B:299:0x0615, code lost:
        r6 = r1.A01;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:300:0x0617, code lost:
        if (r6 != null) goto L_0x0b22;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:301:0x0619, code lost:
        r1.A01 = r7;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:302:0x061c, code lost:
        if (r12 == false) goto L_0x0636;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:303:0x061e, code lost:
        r11 = (X.C1052rR) r6;
        r6 = (X.O3) X.C1052rR.A04(r11, new X.C1049rO(r11));
     */
    /* JADX WARNING: Code restructure failed: missing block: B:304:0x062d, code lost:
        if (r6 == null) goto L_0x0636;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:306:0x0633, code lost:
        if (r6.A00 != X.AnonymousClass09.A01) goto L_0x0636;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:307:0x0636, code lost:
        r19 = r6.A0D();
     */
    /* JADX WARNING: Code restructure failed: missing block: B:308:0x0644, code lost:
        if (r38.A04().A05(X.EnumC1027qy.CAN_OVERRIDE_ACCESS_MODIFIERS) == false) goto L_0x064d;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:309:0x0646, code lost:
        X.Q5.A06(r7.A0R());
     */
    /* JADX WARNING: Code restructure failed: missing block: B:310:0x064d, code lost:
        r6 = r7.A0I(r22);
        r14 = r6.A06();
        r11 = r9.A03;
        r11 = new X.C1021qq(r19, r6, r14, r11.A06(), r7, r6.A0J());
        r13 = A04(r38, r7);
     */
    /* JADX WARNING: Code restructure failed: missing block: B:311:0x0675, code lost:
        if ((r13 instanceof X.AbstractC0282Pe) == false) goto L_0x067d;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:312:0x0677, code lost:
        ((X.AbstractC0282Pe) r13).A4t(r38);
     */
    /* JADX WARNING: Code restructure failed: missing block: B:314:0x067f, code lost:
        if ((r13 instanceof X.AbstractC0278Pa) == false) goto L_0x0687;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:315:0x0681, code lost:
        r13 = ((X.AbstractC0278Pa) r13).A1Y(r38, r11);
     */
    /* JADX WARNING: Code restructure failed: missing block: B:316:0x0687, code lost:
        r15 = null;
        r12 = r6._class;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:317:0x068e, code lost:
        if (r12.isArray() != false) goto L_0x06a0;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:319:0x0696, code lost:
        if (java.util.Collection.class.isAssignableFrom(r12) != false) goto L_0x06a0;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:321:0x069e, code lost:
        if (java.util.Map.class.isAssignableFrom(r12) == false) goto L_0x06b6;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:322:0x06a0, code lost:
        r14 = r38._config;
        r12 = r6.A04();
        r16 = r14.A01();
        r15 = r16.A09(r14, r7, r6);
     */
    /* JADX WARNING: Code restructure failed: missing block: B:323:0x06b0, code lost:
        if (r15 != null) goto L_0x083f;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:324:0x06b2, code lost:
        r15 = A03(r14, r12);
     */
    /* JADX WARNING: Code restructure failed: missing block: B:325:0x06b6, code lost:
        r12 = r38._config;
        r16 = r12.A01();
     */
    /* JADX WARNING: Code restructure failed: missing block: B:326:0x06c0, code lost:
        if ((r16 instanceof X.Rw) == false) goto L_0x0839;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:327:0x06c2, code lost:
        r14 = (X.Rw) r16;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:328:0x06ca, code lost:
        if (r6.A0H() != false) goto L_0x0839;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:329:0x06cc, code lost:
        r14 = X.Rw.A00(r14, r12, r7);
     */
    /* JADX WARNING: Code restructure failed: missing block: B:330:0x06d0, code lost:
        if (r14 == null) goto L_0x0839;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:331:0x06d2, code lost:
        r30 = r14.A1O(r12, r6, r12._subtypeResolver.A02(r7, r12, r16, r6));
     */
    /* JADX WARNING: Code restructure failed: missing block: B:332:0x06e6, code lost:
        r17 = r23;
        r12 = r6;
        r11 = r9.A02;
        r11 = r11 instanceof X.Rw;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:333:0x06f1, code lost:
        if (r11 == false) goto L_0x0715;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:334:0x06f3, code lost:
        r11 = (com.fasterxml.jackson.databind.annotation.JsonSerialize) r7.A0L(com.fasterxml.jackson.databind.annotation.JsonSerialize.class);
     */
    /* JADX WARNING: Code restructure failed: missing block: B:335:0x06fb, code lost:
        if (r11 == null) goto L_0x0715;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:336:0x06fd, code lost:
        r11 = r11.as();
     */
    /* JADX WARNING: Code restructure failed: missing block: B:337:0x0703, code lost:
        if (r11 == X.OR.class) goto L_0x0715;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:338:0x0705, code lost:
        if (r11 == null) goto L_0x0715;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:339:0x0707, code lost:
        r12 = r6._class;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:340:0x070d, code lost:
        if (r11.isAssignableFrom(r12) == false) goto L_0x0827;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:341:0x070f, code lost:
        r12 = r6.A0A(r11);
     */
    /* JADX WARNING: Code restructure failed: missing block: B:342:0x0713, code lost:
        r17 = true;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:343:0x0715, code lost:
        r11 = r9.A04;
        r11 = X.AbstractC1059rZ.A00(r11, r7, r12);
     */
    /* JADX WARNING: Code restructure failed: missing block: B:344:0x0723, code lost:
        if (r11 == r12) goto L_0x080a;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:345:0x0725, code lost:
        r12 = r11;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:346:0x0726, code lost:
        if (r15 == null) goto L_0x0735;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:347:0x0728, code lost:
        if (r12 != null) goto L_0x072b;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:348:0x072a, code lost:
        r12 = r6;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:350:0x072f, code lost:
        if (r12.A04() == null) goto L_0x0ba6;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:351:0x0731, code lost:
        r12 = r12.A0B(r15);
     */
    /* JADX WARNING: Code restructure failed: missing block: B:352:0x0735, code lost:
        r16 = null;
        r15 = false;
        r11 = r11.A03(r7, r9.A01);
     */
    /* JADX WARNING: Code restructure failed: missing block: B:353:0x0742, code lost:
        if (r11 == null) goto L_0x074b;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:355:0x0748, code lost:
        switch(r11.ordinal()) {
            case 0: goto L_0x077b;
            case 1: goto L_0x077a;
            case 2: goto L_0x0790;
            case 3: goto L_0x0805;
            default: goto L_0x074b;
        };
     */
    /* JADX WARNING: Code restructure failed: missing block: B:356:0x074b, code lost:
        r15 = false;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:357:0x074c, code lost:
        r11 = new X.C1060ra(r6, r7, r11.A06(), r6, r13, r30, r12, r15, r16);
        r7 = r11.A0A(r7);
     */
    /* JADX WARNING: Code restructure failed: missing block: B:358:0x076d, code lost:
        if (r7 == null) goto L_0x0775;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:359:0x076f, code lost:
        r11 = new X.MN(r11, r7);
     */
    /* JADX WARNING: Code restructure failed: missing block: B:360:0x0775, code lost:
        r8.add(r11);
     */
    /* JADX WARNING: Code restructure failed: missing block: B:361:0x077a, code lost:
        r15 = true;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:363:0x077f, code lost:
        if (r6.A0H() == false) goto L_0x074c;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:365:0x078b, code lost:
        if (r11.A06(X.EnumC1030r1.WRITE_EMPTY_JSON_ARRAYS) != false) goto L_0x074c;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:366:0x078d, code lost:
        r16 = X.C1060ra.A0I;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:367:0x0790, code lost:
        r14 = r9.A00;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:368:0x0792, code lost:
        if (r14 != null) goto L_0x07e0;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:369:0x0794, code lost:
        r16 = r11.A05(X.EnumC1027qy.CAN_OVERRIDE_ACCESS_MODIFIERS);
        r11 = ((X.C1046rL) r11).A09;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:370:0x07a6, code lost:
        if (r11.A06 != false) goto L_0x07ab;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:371:0x07a8, code lost:
        X.C1043rI.A06(r11);
     */
    /* JADX WARNING: Code restructure failed: missing block: B:372:0x07ab, code lost:
        r14 = r11.A00;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:373:0x07ad, code lost:
        if (r14 != null) goto L_0x07c8;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:374:0x07af, code lost:
        r14 = null;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:375:0x07b0, code lost:
        r9.A00 = r14;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:376:0x07b2, code lost:
        if (r14 != null) goto L_0x07e0;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:378:0x07c7, code lost:
        throw new java.lang.IllegalArgumentException(X.AnonymousClass08.A05("Class ", r11.A09.getName(), " has no default constructor; can not instantiate default bean value to support 'properties=JsonSerialize.Inclusion.NON_DEFAULT' annotation"));
     */
    /* JADX WARNING: Code restructure failed: missing block: B:379:0x07c8, code lost:
        if (r16 == false) goto L_0x07d1;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:380:0x07ca, code lost:
        X.Q5.A06(r14.A0R());
     */
    /* JADX WARNING: Code restructure failed: missing block: B:382:?, code lost:
        r14 = r14._constructor.newInstance(new java.lang.Object[0]);
     */
    /* JADX WARNING: Code restructure failed: missing block: B:384:?, code lost:
        r16 = r7.A0Q(r14);
     */
    /* JADX WARNING: Code restructure failed: missing block: B:385:0x07e4, code lost:
        if (r16 == null) goto L_0x0807;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:386:0x07e6, code lost:
        r15 = r16.getClass();
     */
    /* JADX WARNING: Code restructure failed: missing block: B:387:0x07ee, code lost:
        if (r15.isArray() == false) goto L_0x074b;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:388:0x07f0, code lost:
        r16 = new X.Q1(r15, java.lang.reflect.Array.getLength(r16), r16);
     */
    /* JADX WARNING: Code restructure failed: missing block: B:389:0x0805, code lost:
        r16 = X.C1060ra.A0I;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:390:0x0807, code lost:
        r15 = true;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:391:0x080a, code lost:
        if (r17 != false) goto L_0x0726;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:392:0x080c, code lost:
        if (r11 == false) goto L_0x0824;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:393:0x080e, code lost:
        r11 = (com.fasterxml.jackson.databind.annotation.JsonSerialize) r7.A0L(com.fasterxml.jackson.databind.annotation.JsonSerialize.class);
     */
    /* JADX WARNING: Code restructure failed: missing block: B:394:0x0816, code lost:
        if (r11 == null) goto L_0x0824;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:395:0x0818, code lost:
        r11 = r11.typing();
     */
    /* JADX WARNING: Code restructure failed: missing block: B:396:0x081c, code lost:
        if (r11 == null) goto L_0x0824;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:398:0x0820, code lost:
        if (r11 != X.OM.STATIC) goto L_0x0824;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:399:0x0824, code lost:
        r12 = null;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:401:0x082b, code lost:
        if (r12.isAssignableFrom(r11) == false) goto L_0x0bd5;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:402:0x082d, code lost:
        r12 = r9.A04._base._typeFactory.A07(r6, r11);
     */
    /* JADX WARNING: Code restructure failed: missing block: B:403:0x0839, code lost:
        r30 = A03(r12, r6);
     */
    /* JADX WARNING: Code restructure failed: missing block: B:404:0x083f, code lost:
        r15 = r15.A1O(r14, r12, r14._subtypeResolver.A02(r7, r14, r16, r12));
     */
    /* JADX WARNING: Code restructure failed: missing block: B:405:0x0855, code lost:
        r7 = r3.A01();
        r6 = r10.A09;
        r9 = r7.A0Q(r6);
     */
    /* JADX WARNING: Code restructure failed: missing block: B:406:0x085f, code lost:
        if (r9 == null) goto L_0x0888;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:408:0x0862, code lost:
        if (r9.length <= 0) goto L_0x0888;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:409:0x0864, code lost:
        r11 = X.Q3.A00(r9);
        r9 = r8.iterator();
     */
    /* JADX WARNING: Code restructure failed: missing block: B:411:0x0870, code lost:
        if (r9.hasNext() == false) goto L_0x0888;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:413:0x0882, code lost:
        if (r11.contains(((X.C1060ra) r9.next()).A06.getValue()) == false) goto L_0x086c;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:414:0x0884, code lost:
        r9.remove();
     */
    /* JADX WARNING: Code restructure failed: missing block: B:415:0x0888, code lost:
        r9 = r37._factoryConfig._modifiers;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:416:0x088d, code lost:
        if (r9.length <= 0) goto L_0x08a2;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:417:0x088f, code lost:
        r9 = new X.Q2(r9).iterator();
     */
    /* JADX WARNING: Code restructure failed: missing block: B:419:0x089c, code lost:
        if (r9.hasNext() == false) goto L_0x08a2;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:420:0x089e, code lost:
        r9.next();
     */
    /* JADX WARNING: Code restructure failed: missing block: B:421:0x08a2, code lost:
        r12 = r10.A03;
        r9 = null;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:422:0x08a5, code lost:
        if (r12 == null) goto L_0x08e2;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:423:0x08a7, code lost:
        r11 = r12.A00;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:424:0x08ac, code lost:
        if (r11 != X.VP.class) goto L_0x092d;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:425:0x08ae, code lost:
        r5 = r12.A02;
        r15 = r8.size();
        r11 = 0;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:426:0x08b5, code lost:
        if (r11 == r15) goto L_0x0b0a;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:427:0x08b7, code lost:
        r14 = (X.C1060ra) r8.get(r11);
     */
    /* JADX WARNING: Code restructure failed: missing block: B:428:0x08c7, code lost:
        if (r5.equals(r14.A06.getValue()) == false) goto L_0x092a;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:429:0x08c9, code lost:
        if (r11 <= 0) goto L_0x08d1;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:430:0x08cb, code lost:
        r8.remove(r11);
        r8.add(0, r14);
     */
    /* JADX WARNING: Code restructure failed: missing block: B:431:0x08d1, code lost:
        r9 = X.C0289Pl.A00(r14.A34(), null, new X.AnonymousClass2x(r12.A01, r14), r12.A03);
     */
    /* JADX WARNING: Code restructure failed: missing block: B:432:0x08e2, code lost:
        r1.A03 = r9;
        r1.A05 = r8;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:433:0x08ec, code lost:
        if ((r3.A01() instanceof X.Rw) == false) goto L_0x0928;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:434:0x08ee, code lost:
        r5 = (com.fasterxml.jackson.annotation.JsonFilter) r6.A0L(com.fasterxml.jackson.annotation.JsonFilter.class);
     */
    /* JADX WARNING: Code restructure failed: missing block: B:435:0x08f6, code lost:
        if (r5 == null) goto L_0x0928;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:436:0x08f8, code lost:
        r7 = r5.value();
     */
    /* JADX WARNING: Code restructure failed: missing block: B:437:0x0900, code lost:
        if (r7.length() <= 0) goto L_0x0928;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:438:0x0902, code lost:
        r1.A04 = r7;
        r5 = r10.A00;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:439:0x0906, code lost:
        if (r5 == null) goto L_0x094e;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:441:0x0912, code lost:
        if (java.util.Map.class.isAssignableFrom(r5.A0J()) != false) goto L_0x094e;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:443:0x0927, code lost:
        throw new java.lang.IllegalArgumentException(X.AnonymousClass08.A05("Invalid 'any-getter' annotation on method ", r10.A00.A0K(), "(): return type is not instance of java.util.Map"));
     */
    /* JADX WARNING: Code restructure failed: missing block: B:444:0x0928, code lost:
        r7 = null;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:445:0x092a, code lost:
        r11 = r11 + 1;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:446:0x092d, code lost:
        r9 = X.C0289Pl.A00(r38.A05().A0A(r38.A05().A09(r11, null), X.NN.class)[0], r12.A02, r38.A02(r12), r12.A03);
     */
    /* JADX WARNING: Code restructure failed: missing block: B:447:0x094e, code lost:
        r5 = r10.A00;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:448:0x0950, code lost:
        if (r5 == null) goto L_0x0995;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:450:0x0958, code lost:
        if (r3.A05(X.EnumC1027qy.CAN_OVERRIDE_ACCESS_MODIFIERS) == false) goto L_0x0961;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:451:0x095a, code lost:
        X.Q5.A06(r5.A0R());
     */
    /* JADX WARNING: Code restructure failed: missing block: B:452:0x0961, code lost:
        r9 = r5.A0I(r4.A05());
        r10 = r3.A05(X.EnumC1027qy.USE_STATIC_TYPING);
        r7 = r9.A04();
        r1.A02 = new X.PX(new X.C1021qq(r5.A0K(), r7, null, r4.A06(), r5, false), r5, com.fasterxml.jackson.databind.ser.std.MapSerializer.A01(null, r9, r10, A03(r3, r7), null, null));
     */
    /* JADX WARNING: Code restructure failed: missing block: B:453:0x0995, code lost:
        r11 = r1.A05;
        r14 = r3.A05(X.EnumC1027qy.DEFAULT_VIEW_INCLUSION);
        r12 = r11.size();
        r10 = new X.C1060ra[r12];
        r9 = 0;
        r13 = 0;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:454:0x09a5, code lost:
        if (r9 >= r12) goto L_0x09cf;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:455:0x09a7, code lost:
        r8 = (X.C1060ra) r11.get(r9);
        r7 = r8.A0C;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:456:0x09af, code lost:
        if (r7 != null) goto L_0x09b8;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:457:0x09b1, code lost:
        if (r14 == false) goto L_0x09b5;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:458:0x09b3, code lost:
        r10[r9] = r8;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:459:0x09b5, code lost:
        r9 = r9 + 1;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:460:0x09b8, code lost:
        r13 = r13 + 1;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:461:0x09bc, code lost:
        if (r7.length != 1) goto L_0x09c9;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:462:0x09be, code lost:
        r3 = new X.MP(r8, r7[0]);
     */
    /* JADX WARNING: Code restructure failed: missing block: B:463:0x09c6, code lost:
        r10[r9] = r3;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:464:0x09c9, code lost:
        r3 = new X.MZ(r8, r7);
     */
    /* JADX WARNING: Code restructure failed: missing block: B:465:0x09cf, code lost:
        if (r14 == false) goto L_0x09ed;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:466:0x09d1, code lost:
        if (r13 != 0) goto L_0x09ed;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:467:0x09d3, code lost:
        r5 = r37._factoryConfig._modifiers;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:468:0x09d8, code lost:
        if (r5.length <= 0) goto L_0x09f0;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:469:0x09da, code lost:
        r5 = new X.Q2(r5).iterator();
     */
    /* JADX WARNING: Code restructure failed: missing block: B:471:0x09e7, code lost:
        if (r5.hasNext() == false) goto L_0x09f0;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:472:0x09e9, code lost:
        r5.next();
     */
    /* JADX WARNING: Code restructure failed: missing block: B:473:0x09ed, code lost:
        r1.A06 = r10;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:474:0x09f0, code lost:
        r3 = r1.A05;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:475:0x09f2, code lost:
        if (r3 == null) goto L_0x0a15;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:477:0x09f8, code lost:
        if (r3.isEmpty() != false) goto L_0x0a15;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:478:0x09fa, code lost:
        r5 = r1.A05;
        r6 = (X.C1060ra[]) r5.toArray(new X.C1060ra[r5.size()]);
     */
    /* JADX WARNING: Code restructure failed: missing block: B:479:0x0a08, code lost:
        r9 = new com.fasterxml.jackson.databind.ser.BeanSerializer(r1.A07.A00, r1, r6, r1.A06);
     */
    /* JADX WARNING: Code restructure failed: missing block: B:481:0x0a17, code lost:
        if (r1.A02 != null) goto L_0x0a3a;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:483:0x0a1c, code lost:
        if (r6.A02 != null) goto L_0x0a21;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:484:0x0a1e, code lost:
        X.C1043rI.A05(r6);
     */
    /* JADX WARNING: Code restructure failed: missing block: B:485:0x0a21, code lost:
        r3 = r6.A02.A00;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:486:0x0a25, code lost:
        if (r3 == null) goto L_0x04c8;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:488:0x0a2b, code lost:
        if (r3.size() <= 0) goto L_0x04c8;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:489:0x0a2d, code lost:
        r9 = new com.fasterxml.jackson.databind.ser.BeanSerializer(r1.A07.A00, null, com.fasterxml.jackson.databind.ser.std.BeanSerializerBase.A07, null);
     */
    /* JADX WARNING: Code restructure failed: missing block: B:490:0x0a3a, code lost:
        r6 = X.PY.A08;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:498:0x0a5b, code lost:
        if (java.lang.Iterable.class.isAssignableFrom(r3) == false) goto L_0x0a7d;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:499:0x0a5d, code lost:
        r5 = r0.A06(0);
     */
    /* JADX WARNING: Code restructure failed: missing block: B:500:0x0a62, code lost:
        if (r5 != null) goto L_0x0a6b;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:501:0x0a64, code lost:
        r5 = new X.fF(java.lang.Object.class);
     */
    /* JADX WARNING: Code restructure failed: missing block: B:502:0x0a6b, code lost:
        r3 = A03(r3, r5);
        r9 = new com.fasterxml.jackson.databind.ser.std.IterableSerializer(r5, X.AbstractC1059rZ.A01(r3, r4, r3), r3, (X.O5) null);
     */
    /* JADX WARNING: Code restructure failed: missing block: B:504:0x0a83, code lost:
        if (java.lang.CharSequence.class.isAssignableFrom(r3) != false) goto L_0x0aef;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:505:0x0a85, code lost:
        return null;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:529:0x0b21, code lost:
        throw new java.lang.IllegalArgumentException(X.AnonymousClass08.A07("Invalid Object Id definition for ", r1._class.getName(), ": can not find property with name '", r5, "'"));
     */
    /* JADX WARNING: Code restructure failed: missing block: B:530:0x0b22, code lost:
        r1 = new java.lang.StringBuilder("Multiple type ids specified with ");
        r1.append(r6);
        r1.append(" and ");
        r1.append(r7);
     */
    /* JADX WARNING: Code restructure failed: missing block: B:531:0x0b3d, code lost:
        throw new java.lang.IllegalArgumentException(r1.toString());
     */
    /* JADX WARNING: Code restructure failed: missing block: B:532:0x0b3e, code lost:
        r2 = e;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:534:0x0b43, code lost:
        if (r2.getCause() != null) goto L_0x0b45;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:535:0x0b45, code lost:
        r2 = r2.getCause();
     */
    /* JADX WARNING: Code restructure failed: missing block: B:537:0x0b4c, code lost:
        if ((r2 instanceof java.lang.Error) == false) goto L_0x0b4e;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:539:0x0b50, code lost:
        if ((r2 instanceof java.lang.RuntimeException) != false) goto L_0x0b52;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:540:0x0b52, code lost:
        throw r2;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:542:0x0b74, code lost:
        throw new java.lang.IllegalArgumentException(X.AnonymousClass08.A08("Failed to instantiate bean of type ", r11.A09.getName(), ": (", r2.getClass().getName(), ") ", r2.getMessage()), r2);
     */
    /* JADX WARNING: Code restructure failed: missing block: B:543:0x0b75, code lost:
        throw r2;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:544:0x0b76, code lost:
        r1 = e;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:546:0x0b7b, code lost:
        if (r1.getCause() != null) goto L_0x0b7d;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:547:0x0b7d, code lost:
        r1 = r1.getCause();
     */
    /* JADX WARNING: Code restructure failed: missing block: B:549:0x0b84, code lost:
        if ((r1 instanceof java.lang.Error) == false) goto L_0x0b86;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:551:0x0b88, code lost:
        if ((r1 instanceof java.lang.RuntimeException) != false) goto L_0x0b8a;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:552:0x0b8a, code lost:
        throw r1;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:554:0x0ba4, code lost:
        throw new java.lang.IllegalArgumentException(X.AnonymousClass08.A07("Failed to get property '", r19, "' of default ", r14.getClass().getName(), " instance"));
     */
    /* JADX WARNING: Code restructure failed: missing block: B:555:0x0ba5, code lost:
        throw r1;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:556:0x0ba6, code lost:
        r1 = new java.lang.StringBuilder("Problem trying to create BeanPropertyWriter for property '");
        r1.append(r19);
        r1.append("' (of type ");
        r1.append(r11.A00);
        r1.append("); serialization type ");
        r1.append(r12);
        r1.append(" has no content");
     */
    /* JADX WARNING: Code restructure failed: missing block: B:557:0x0bd4, code lost:
        throw new java.lang.IllegalStateException(r1.toString());
     */
    /* JADX WARNING: Code restructure failed: missing block: B:559:0x0bf0, code lost:
        throw new java.lang.IllegalArgumentException(X.AnonymousClass08.A08("Illegal concrete-type annotation for method '", r7.A0K(), "': class ", r11.getName(), " not a super-type of (declared) class ", r12.getName()));
     */
    /* JADX WARNING: Code restructure failed: missing block: B:79:0x017e, code lost:
        if (r9 == null) goto L_0x0180;
     */
    /* JADX WARNING: Removed duplicated region for block: B:218:0x0465  */
    /* JADX WARNING: Removed duplicated region for block: B:219:0x0469  */
    @Override // X.AbstractC0285Ph
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public final com.fasterxml.jackson.databind.JsonSerializer A02(X.AbstractC1031r2 r38, X.AbstractC1024qt r39) {
        /*
        // Method dump skipped, instructions count: 3086
        */
        throw new UnsupportedOperationException("Method not decompiled: X.PH.A02(X.r2, X.qt):com.fasterxml.jackson.databind.JsonSerializer");
    }
}
