package X;

import java.io.Serializable;

/* renamed from: X.0Of  reason: invalid class name and case insensitive filesystem */
public final class C00980Of extends AbstractC01840hB implements Serializable {
    public static final C00980Of A00 = new C00980Of(null);
    public static final long serialVersionUID = 1;

    @Override // X.AbstractC01840hB
    public final AbstractC04630qr A08(C04180pU r4) {
        if (this._factoryConfig == r4) {
            return this;
        }
        Class<?> cls = getClass();
        if (cls == C00980Of.class) {
            return new C00980Of(r4);
        }
        throw new IllegalStateException(AnonymousClass006.A09("Subtype of BeanSerializerFactory (", cls.getName(), ") has not properly overridden method 'withAdditionalSerializers': can not instantiate subtype with additional serializer definitions"));
    }

    @Override // X.AbstractC01840hB
    public final Iterable<AbstractC04640qs> A09() {
        return new C04780rF(this._factoryConfig._additionalSerializers);
    }

    public C00980Of(C04180pU r1) {
        super(r1);
    }

    /* JADX WARNING: Code restructure failed: missing block: B:120:0x028c, code lost:
        if (r15 == null) goto L_0x028e;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:144:0x0307, code lost:
        if (r15 == null) goto L_0x0309;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:170:0x0368, code lost:
        if (r15 != null) goto L_0x02c1;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:192:0x03cd, code lost:
        if (r2.startsWith("org.hibernate.proxy.") == false) goto L_0x0401;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:194:0x03d5, code lost:
        if (r1._class.isEnum() == false) goto L_0x03d7;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:195:0x03d7, code lost:
        r2 = r1._class;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:196:0x03df, code lost:
        if (java.util.Iterator.class.isAssignableFrom(r2) == false) goto L_0x0881;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:197:0x03e1, code lost:
        r4 = r1.A06(0);
     */
    /* JADX WARNING: Code restructure failed: missing block: B:198:0x03e6, code lost:
        if (r4 != null) goto L_0x03ef;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:199:0x03e8, code lost:
        r4 = new X.AnonymousClass0C7(java.lang.Object.class);
     */
    /* JADX WARNING: Code restructure failed: missing block: B:200:0x03ef, code lost:
        r2 = A04(r2, r4);
        r15 = new com.fasterxml.jackson.databind.ser.impl.IteratorSerializer(r4, X.AbstractC01840hB.A01(r2, r5, r2), r2, (X.AbstractC02220iI) null);
     */
    /* JADX WARNING: Code restructure failed: missing block: B:201:0x0401, code lost:
        r0 = r5.A00;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:202:0x0409, code lost:
        if (r0._class != java.lang.Object.class) goto L_0x0410;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:203:0x040b, code lost:
        r15 = r35._unknownTypeSerializer;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:204:0x040d, code lost:
        if (r15 != null) goto L_0x02c1;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:205:0x0410, code lost:
        r2 = r35._config;
        r0 = new X.C04580qi(r5);
        r0.A00 = r2;
        r11 = r5.A0M();
        r9 = r2.A01();
        r8 = new java.util.HashMap();
        r10 = r11.iterator();
     */
    /* JADX WARNING: Code restructure failed: missing block: B:207:0x042e, code lost:
        if (r10.hasNext() == false) goto L_0x0469;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:208:0x0430, code lost:
        r6 = r10.next().A09();
     */
    /* JADX WARNING: Code restructure failed: missing block: B:209:0x043a, code lost:
        if (r6 == null) goto L_0x0465;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:210:0x043c, code lost:
        r7 = r6.A0K();
        r6 = (java.lang.Boolean) r8.get(r7);
     */
    /* JADX WARNING: Code restructure failed: missing block: B:211:0x0446, code lost:
        if (r6 != null) goto L_0x045f;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:212:0x0448, code lost:
        r6 = r9.A0J(r2.A02(r2.A03(r7)).A07());
     */
    /* JADX WARNING: Code restructure failed: missing block: B:213:0x0458, code lost:
        if (r6 != null) goto L_0x045c;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:214:0x045a, code lost:
        r6 = java.lang.Boolean.FALSE;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:215:0x045c, code lost:
        r8.put(r7, r6);
     */
    /* JADX WARNING: Code restructure failed: missing block: B:217:0x0463, code lost:
        if (r6.booleanValue() == false) goto L_0x042a;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:218:0x0465, code lost:
        r10.remove();
     */
    /* JADX WARNING: Code restructure failed: missing block: B:220:0x046f, code lost:
        if (r2.A05(X.EnumC02160i9.REQUIRE_SETTERS_FOR_GETTERS) == false) goto L_0x0491;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:221:0x0471, code lost:
        r8 = r11.iterator();
     */
    /* JADX WARNING: Code restructure failed: missing block: B:223:0x0479, code lost:
        if (r8.hasNext() == false) goto L_0x0491;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:224:0x047b, code lost:
        r7 = r8.next();
     */
    /* JADX WARNING: Code restructure failed: missing block: B:225:0x0485, code lost:
        if (r7.A0A() != null) goto L_0x0475;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:227:0x048b, code lost:
        if (r7.A0L() != false) goto L_0x0475;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:228:0x048d, code lost:
        r8.remove();
     */
    /* JADX WARNING: Code restructure failed: missing block: B:230:0x0496, code lost:
        if (r11.isEmpty() == false) goto L_0x04bd;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:231:0x0498, code lost:
        r8 = new java.util.ArrayList();
     */
    /* JADX WARNING: Code restructure failed: missing block: B:232:0x049d, code lost:
        r7 = r34._factoryConfig._modifiers;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:233:0x04a2, code lost:
        if (r7.length <= 0) goto L_0x06c0;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:234:0x04a4, code lost:
        r7 = new X.C04780rF(r7).iterator();
     */
    /* JADX WARNING: Code restructure failed: missing block: B:236:0x04b1, code lost:
        if (r7.hasNext() == false) goto L_0x06c0;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:237:0x04b3, code lost:
        ((X.AbstractC04590qj) r7.next()).A00(r2, r5, r8);
     */
    /* JADX WARNING: Code restructure failed: missing block: B:238:0x04bd, code lost:
        r21 = X.AbstractC01840hB.A01(r2, r5, null);
        r9 = new X.AnonymousClass0qn(r2, r5);
        r8 = new java.util.ArrayList(r11.size());
        r20 = r5.A0E();
        r19 = r11.iterator();
     */
    /* JADX WARNING: Code restructure failed: missing block: B:240:0x04db, code lost:
        if (r19.hasNext() == false) goto L_0x049d;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:241:0x04dd, code lost:
        r6 = r19.next();
        r7 = r6.A09();
     */
    /* JADX WARNING: Code restructure failed: missing block: B:242:0x04ef, code lost:
        if (r6.A0G() == false) goto L_0x0509;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:243:0x04f1, code lost:
        if (r7 == null) goto L_0x04d7;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:245:0x04f9, code lost:
        if (r2.A05(X.EnumC02160i9.CAN_OVERRIDE_ACCESS_MODIFIERS) == false) goto L_0x0502;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:246:0x04fb, code lost:
        X.C04810rI.A06(r7.A0R());
     */
    /* JADX WARNING: Code restructure failed: missing block: B:247:0x0502, code lost:
        r6 = r0.A01;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:248:0x0504, code lost:
        if (r6 != null) goto L_0x09e9;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:249:0x0506, code lost:
        r0.A01 = r7;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:250:0x0509, code lost:
        r6 = r6.A06();
     */
    /* JADX WARNING: Code restructure failed: missing block: B:251:0x050d, code lost:
        if (r6 == null) goto L_0x0516;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:253:0x0513, code lost:
        if (r6.A00 != X.AnonymousClass007.A01) goto L_0x0516;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:254:0x0516, code lost:
        r17 = r6.A0E();
     */
    /* JADX WARNING: Code restructure failed: missing block: B:255:0x0524, code lost:
        if (r35.A06().A05(X.EnumC02160i9.CAN_OVERRIDE_ACCESS_MODIFIERS) == false) goto L_0x052d;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:256:0x0526, code lost:
        X.C04810rI.A06(r7.A0R());
     */
    /* JADX WARNING: Code restructure failed: missing block: B:257:0x052d, code lost:
        r6 = r7.A0I(r20);
        r25 = r6.A07();
        r10 = r9.A03;
        r11 = new X.AnonymousClass0Sn(r17, r6, r25, r10.A0F(), r7, r6.A0F());
        r12 = A07(r35, r7);
     */
    /* JADX WARNING: Code restructure failed: missing block: B:258:0x0556, code lost:
        if ((r12 instanceof X.AbstractC04620qo) == false) goto L_0x055e;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:259:0x0558, code lost:
        ((X.AbstractC04620qo) r12).A9O(r35);
     */
    /* JADX WARNING: Code restructure failed: missing block: B:261:0x0560, code lost:
        if ((r12 instanceof X.AbstractC04600qk) == false) goto L_0x0568;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:262:0x0562, code lost:
        r12 = ((X.AbstractC04600qk) r12).A2P(r35, r11);
     */
    /* JADX WARNING: Code restructure failed: missing block: B:263:0x0568, code lost:
        r13 = null;
        r11 = r6._class;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:264:0x056f, code lost:
        if (r11.isArray() != false) goto L_0x0581;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:266:0x0577, code lost:
        if (java.util.Collection.class.isAssignableFrom(r11) != false) goto L_0x0581;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:268:0x057f, code lost:
        if (java.util.Map.class.isAssignableFrom(r11) == false) goto L_0x0595;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:269:0x0581, code lost:
        r14 = r35._config;
        r13 = r6.A04();
        r15 = r14.A01();
        r11 = r15.A0E(r14, r7, r6);
     */
    /* JADX WARNING: Code restructure failed: missing block: B:270:0x058f, code lost:
        if (r11 != null) goto L_0x06b4;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:271:0x0591, code lost:
        r13 = A04(r14, r13);
     */
    /* JADX WARNING: Code restructure failed: missing block: B:272:0x0595, code lost:
        r14 = r35._config;
        r15 = r14.A01();
        r11 = r15.A0F(r14, r7, r6);
     */
    /* JADX WARNING: Code restructure failed: missing block: B:273:0x059f, code lost:
        if (r11 != null) goto L_0x06a8;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:274:0x05a1, code lost:
        r28 = A04(r14, r6);
     */
    /* JADX WARNING: Code restructure failed: missing block: B:275:0x05a5, code lost:
        r14 = r21;
        r10 = r6;
        r15 = r9.A02;
        r11 = r15.A0M(r7);
     */
    /* JADX WARNING: Code restructure failed: missing block: B:276:0x05ae, code lost:
        if (r11 == null) goto L_0x05bd;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:277:0x05b0, code lost:
        r10 = r6._class;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:278:0x05b6, code lost:
        if (r11.isAssignableFrom(r10) == false) goto L_0x0696;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:279:0x05b8, code lost:
        r10 = r6.A08(r11);
     */
    /* JADX WARNING: Code restructure failed: missing block: B:280:0x05bc, code lost:
        r14 = true;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:281:0x05bd, code lost:
        r11 = r9.A04;
        r11 = X.AbstractC01840hB.A00(r11, r7, r10);
     */
    /* JADX WARNING: Code restructure failed: missing block: B:282:0x05c5, code lost:
        if (r11 == r10) goto L_0x0682;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:283:0x05c7, code lost:
        r10 = r11;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:284:0x05c8, code lost:
        if (r13 == null) goto L_0x05d7;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:285:0x05ca, code lost:
        if (r10 != null) goto L_0x05cd;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:286:0x05cc, code lost:
        r10 = r6;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:288:0x05d1, code lost:
        if (r10.A04() == null) goto L_0x0a35;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:289:0x05d3, code lost:
        r10 = r10.A0C(r13);
     */
    /* JADX WARNING: Code restructure failed: missing block: B:290:0x05d7, code lost:
        r16 = null;
        r30 = false;
        r11 = r15.A03(r7, r9.A01);
     */
    /* JADX WARNING: Code restructure failed: missing block: B:291:0x05e1, code lost:
        if (r11 == null) goto L_0x05ea;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:293:0x05e7, code lost:
        switch(r11.ordinal()) {
            case 0: goto L_0x0616;
            case 1: goto L_0x0614;
            case 2: goto L_0x0629;
            case 3: goto L_0x067c;
            default: goto L_0x05ea;
        };
     */
    /* JADX WARNING: Code restructure failed: missing block: B:294:0x05ea, code lost:
        r11 = new X.AnonymousClass0Og(r6, r7, r10.A0F(), r6, r12, r28, r10, r30, r16);
        r7 = r15.A0G(r7);
     */
    /* JADX WARNING: Code restructure failed: missing block: B:295:0x0607, code lost:
        if (r7 == null) goto L_0x060f;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:296:0x0609, code lost:
        r11 = new X.AnonymousClass0CT(r11, r7);
     */
    /* JADX WARNING: Code restructure failed: missing block: B:297:0x060f, code lost:
        r8.add(r11);
     */
    /* JADX WARNING: Code restructure failed: missing block: B:298:0x0614, code lost:
        r30 = true;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:300:0x061a, code lost:
        if (r6.A0N() == false) goto L_0x05ea;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:302:0x0624, code lost:
        if (r11.A06(X.AnonymousClass0i4.WRITE_EMPTY_JSON_ARRAYS) != false) goto L_0x05ea;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:303:0x0626, code lost:
        r16 = X.AnonymousClass0Og.A0I;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:304:0x0629, code lost:
        r11 = r9.A00;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:305:0x062b, code lost:
        if (r11 != null) goto L_0x0657;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:306:0x062d, code lost:
        r11 = r10.A0J(r11.A05(X.EnumC02160i9.CAN_OVERRIDE_ACCESS_MODIFIERS));
        r9.A00 = r11;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:307:0x063d, code lost:
        if (r11 != null) goto L_0x0657;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:309:0x0656, code lost:
        throw new java.lang.IllegalArgumentException(X.AnonymousClass006.A09("Class ", r10.A07().A09.getName(), " has no default constructor; can not instantiate default bean value to support 'properties=JsonSerialize.Inclusion.NON_DEFAULT' annotation"));
     */
    /* JADX WARNING: Code restructure failed: missing block: B:311:?, code lost:
        r16 = r7.A0Q(r11);
     */
    /* JADX WARNING: Code restructure failed: missing block: B:312:0x065b, code lost:
        if (r16 == null) goto L_0x067e;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:313:0x065d, code lost:
        r14 = r16.getClass();
     */
    /* JADX WARNING: Code restructure failed: missing block: B:314:0x0665, code lost:
        if (r14.isArray() == false) goto L_0x05ea;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:315:0x0667, code lost:
        r16 = new X.C04770rE(r14, java.lang.reflect.Array.getLength(r16), r16);
     */
    /* JADX WARNING: Code restructure failed: missing block: B:316:0x067c, code lost:
        r16 = X.AnonymousClass0Og.A0I;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:317:0x067e, code lost:
        r30 = true;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:318:0x0682, code lost:
        if (r14 != false) goto L_0x05c8;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:319:0x0684, code lost:
        r16 = r15.A09(r7);
     */
    /* JADX WARNING: Code restructure failed: missing block: B:320:0x0688, code lost:
        if (r16 == null) goto L_0x0693;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:322:0x068f, code lost:
        if (r16 != X.EnumC04120pK.STATIC) goto L_0x0693;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:323:0x0693, code lost:
        r10 = null;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:325:0x069a, code lost:
        if (r10.isAssignableFrom(r11) == false) goto L_0x0a64;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:326:0x069c, code lost:
        r10 = r9.A04._base._typeFactory.A07(r6, r11);
     */
    /* JADX WARNING: Code restructure failed: missing block: B:327:0x06a8, code lost:
        r28 = r11.A1t(r14, r6, r14._subtypeResolver.A02(r7, r14, r15, r6));
     */
    /* JADX WARNING: Code restructure failed: missing block: B:328:0x06b4, code lost:
        r13 = r11.A1t(r14, r13, r14._subtypeResolver.A02(r7, r14, r15, r13));
     */
    /* JADX WARNING: Code restructure failed: missing block: B:329:0x06c0, code lost:
        r6 = r2.A01();
        r10 = r5.A07();
        r7 = r6.A0v(r10);
     */
    /* JADX WARNING: Code restructure failed: missing block: B:330:0x06cc, code lost:
        if (r7 == null) goto L_0x06f5;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:332:0x06cf, code lost:
        if (r7.length <= 0) goto L_0x06f5;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:333:0x06d1, code lost:
        r9 = X.C04790rG.A00(r7);
        r7 = r8.iterator();
     */
    /* JADX WARNING: Code restructure failed: missing block: B:335:0x06dd, code lost:
        if (r7.hasNext() == false) goto L_0x06f5;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:337:0x06ef, code lost:
        if (r9.contains(((X.AnonymousClass0Og) r7.next()).A06.getValue()) == false) goto L_0x06d9;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:338:0x06f1, code lost:
        r7.remove();
     */
    /* JADX WARNING: Code restructure failed: missing block: B:339:0x06f5, code lost:
        r7 = r34._factoryConfig._modifiers;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:340:0x06fa, code lost:
        if (r7.length <= 0) goto L_0x070f;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:341:0x06fc, code lost:
        r7 = new X.C04780rF(r7).iterator();
     */
    /* JADX WARNING: Code restructure failed: missing block: B:343:0x0709, code lost:
        if (r7.hasNext() == false) goto L_0x070f;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:344:0x070b, code lost:
        r7.next();
     */
    /* JADX WARNING: Code restructure failed: missing block: B:345:0x070f, code lost:
        r11 = r5.A0D();
        r7 = null;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:346:0x0714, code lost:
        if (r11 == null) goto L_0x0751;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:347:0x0716, code lost:
        r9 = r11.A00;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:348:0x071b, code lost:
        if (r9 != X.AnonymousClass0Sy.class) goto L_0x07e6;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:349:0x071d, code lost:
        r4 = r11.A02;
        r14 = r8.size();
        r9 = 0;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:350:0x0724, code lost:
        if (r9 == r14) goto L_0x09d1;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:351:0x0726, code lost:
        r13 = (X.AnonymousClass0Og) r8.get(r9);
     */
    /* JADX WARNING: Code restructure failed: missing block: B:352:0x0736, code lost:
        if (r4.equals(r13.A06.getValue()) == false) goto L_0x07e2;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:353:0x0738, code lost:
        if (r9 <= 0) goto L_0x0740;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:354:0x073a, code lost:
        r8.remove(r9);
        r8.add(0, r13);
     */
    /* JADX WARNING: Code restructure failed: missing block: B:355:0x0740, code lost:
        r7 = X.C04660qw.A00(r13.A59(), null, new X.C00520Ca(r11.A01, r13), r11.A03);
     */
    /* JADX WARNING: Code restructure failed: missing block: B:356:0x0751, code lost:
        r0.A03 = r7;
        r0.A05 = r8;
        r0.A04 = r2.A01().A0b(r10);
        r6 = r5.A09();
     */
    /* JADX WARNING: Code restructure failed: missing block: B:357:0x0763, code lost:
        if (r6 == null) goto L_0x07a8;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:359:0x076b, code lost:
        if (r2.A05(X.EnumC02160i9.CAN_OVERRIDE_ACCESS_MODIFIERS) == false) goto L_0x0774;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:360:0x076d, code lost:
        X.C04810rI.A06(r6.A0R());
     */
    /* JADX WARNING: Code restructure failed: missing block: B:361:0x0774, code lost:
        r8 = r6.A0I(r5.A0E());
        r9 = r2.A05(X.EnumC02160i9.USE_STATIC_TYPING);
        r4 = r8.A04();
        r0.A02 = new X.AnonymousClass0qh(new X.AnonymousClass0Sn(r6.A0L(), r4, null, r5.A0F(), r6, false), r6, com.fasterxml.jackson.databind.ser.std.MapSerializer.A00(null, r8, r9, A04(r2, r4), null, null));
     */
    /* JADX WARNING: Code restructure failed: missing block: B:362:0x07a8, code lost:
        r11 = r0.A05;
        r13 = r2.A05(X.EnumC02160i9.DEFAULT_VIEW_INCLUSION);
        r12 = r11.size();
        r10 = new X.AnonymousClass0Og[r12];
        r9 = 0;
        r8 = 0;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:363:0x07b8, code lost:
        if (r9 >= r12) goto L_0x0808;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:364:0x07ba, code lost:
        r7 = r11.get(r9);
        r6 = r7.A0C;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:365:0x07c2, code lost:
        if (r6 != null) goto L_0x07cb;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:366:0x07c4, code lost:
        if (r13 == false) goto L_0x07c8;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:367:0x07c6, code lost:
        r10[r9] = r7;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:368:0x07c8, code lost:
        r9 = r9 + 1;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:369:0x07cb, code lost:
        r8 = r8 + 1;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:370:0x07cf, code lost:
        if (r6.length != 1) goto L_0x07dc;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:371:0x07d1, code lost:
        r2 = new X.AnonymousClass0Cc(r7, r6[0]);
     */
    /* JADX WARNING: Code restructure failed: missing block: B:372:0x07d9, code lost:
        r10[r9] = r2;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:373:0x07dc, code lost:
        r2 = new X.AnonymousClass0Cd(r7, r6);
     */
    /* JADX WARNING: Code restructure failed: missing block: B:374:0x07e2, code lost:
        r9 = r9 + 1;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:375:0x07e6, code lost:
        r7 = X.C04660qw.A00(r35.A07().A0A(r35.A07().A09(r9, null), X.AbstractC03600nz.class)[0], r11.A02, r35.A03(r11), r11.A03);
     */
    /* JADX WARNING: Code restructure failed: missing block: B:376:0x0808, code lost:
        if (r13 == false) goto L_0x0826;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:377:0x080a, code lost:
        if (r8 != 0) goto L_0x0826;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:378:0x080c, code lost:
        r4 = r34._factoryConfig._modifiers;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:379:0x0811, code lost:
        if (r4.length <= 0) goto L_0x0829;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:380:0x0813, code lost:
        r4 = new X.C04780rF(r4).iterator();
     */
    /* JADX WARNING: Code restructure failed: missing block: B:382:0x0820, code lost:
        if (r4.hasNext() == false) goto L_0x0829;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:383:0x0822, code lost:
        r4.next();
     */
    /* JADX WARNING: Code restructure failed: missing block: B:384:0x0826, code lost:
        r0.A06 = r10;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:385:0x0829, code lost:
        r2 = r0.A05;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:386:0x082b, code lost:
        if (r2 == null) goto L_0x084e;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:388:0x0831, code lost:
        if (r2.isEmpty() != false) goto L_0x084e;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:389:0x0833, code lost:
        r4 = r0.A05;
        r6 = (X.AnonymousClass0Og[]) r4.toArray(new X.AnonymousClass0Og[r4.size()]);
     */
    /* JADX WARNING: Code restructure failed: missing block: B:390:0x0841, code lost:
        r15 = new com.fasterxml.jackson.databind.ser.BeanSerializer(r0.A07.A00, r0, r6, r0.A06);
     */
    /* JADX WARNING: Code restructure failed: missing block: B:392:0x0850, code lost:
        if (r0.A02 != null) goto L_0x0866;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:394:0x0857, code lost:
        if (r5.A0S() == false) goto L_0x03d7;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:395:0x0859, code lost:
        r15 = new com.fasterxml.jackson.databind.ser.BeanSerializer(r0.A07.A00, null, com.fasterxml.jackson.databind.ser.std.BeanSerializerBase.A07, null);
     */
    /* JADX WARNING: Code restructure failed: missing block: B:396:0x0866, code lost:
        r6 = X.C04580qi.A08;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:404:0x0887, code lost:
        if (java.lang.Iterable.class.isAssignableFrom(r2) == false) goto L_0x08a9;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:405:0x0889, code lost:
        r4 = r1.A06(0);
     */
    /* JADX WARNING: Code restructure failed: missing block: B:406:0x088e, code lost:
        if (r4 != null) goto L_0x0897;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:407:0x0890, code lost:
        r4 = new X.AnonymousClass0C7(java.lang.Object.class);
     */
    /* JADX WARNING: Code restructure failed: missing block: B:408:0x0897, code lost:
        r2 = A04(r2, r4);
        r15 = new com.fasterxml.jackson.databind.ser.std.IterableSerializer(r4, X.AbstractC01840hB.A01(r2, r5, r2), r2, (X.AbstractC02220iI) null);
     */
    /* JADX WARNING: Code restructure failed: missing block: B:410:0x08af, code lost:
        if (java.lang.CharSequence.class.isAssignableFrom(r2) != false) goto L_0x091b;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:411:0x08b1, code lost:
        return null;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:465:0x09e8, code lost:
        throw new java.lang.IllegalArgumentException(X.AnonymousClass006.A0C("Invalid Object Id definition for ", r0._class.getName(), ": can not find property with name '", r4, "'"));
     */
    /* JADX WARNING: Code restructure failed: missing block: B:466:0x09e9, code lost:
        r1 = new java.lang.StringBuilder("Multiple type ids specified with ");
        r1.append(r6);
        r1.append(" and ");
        r1.append(r7);
     */
    /* JADX WARNING: Code restructure failed: missing block: B:467:0x0a04, code lost:
        throw new java.lang.IllegalArgumentException(r1.toString());
     */
    /* JADX WARNING: Code restructure failed: missing block: B:468:0x0a05, code lost:
        r1 = e;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:470:0x0a0a, code lost:
        if (r1.getCause() != null) goto L_0x0a0c;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:471:0x0a0c, code lost:
        r1 = r1.getCause();
     */
    /* JADX WARNING: Code restructure failed: missing block: B:473:0x0a13, code lost:
        if ((r1 instanceof java.lang.Error) == false) goto L_0x0a15;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:475:0x0a17, code lost:
        if ((r1 instanceof java.lang.RuntimeException) != false) goto L_0x0a19;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:476:0x0a19, code lost:
        throw r1;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:478:0x0a33, code lost:
        throw new java.lang.IllegalArgumentException(X.AnonymousClass006.A0C("Failed to get property '", r17, "' of default ", r11.getClass().getName(), " instance"));
     */
    /* JADX WARNING: Code restructure failed: missing block: B:479:0x0a34, code lost:
        throw r1;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:480:0x0a35, code lost:
        r1 = new java.lang.StringBuilder("Problem trying to create BeanPropertyWriter for property '");
        r1.append(r17);
        r1.append("' (of type ");
        r1.append(r10.A00);
        r1.append("); serialization type ");
        r1.append(r10);
        r1.append(" has no content");
     */
    /* JADX WARNING: Code restructure failed: missing block: B:481:0x0a63, code lost:
        throw new java.lang.IllegalStateException(r1.toString());
     */
    /* JADX WARNING: Code restructure failed: missing block: B:483:0x0a7f, code lost:
        throw new java.lang.IllegalArgumentException(X.AnonymousClass006.A0D("Illegal concrete-type annotation for method '", r7.A0L(), "': class ", r11.getName(), " not a super-type of (declared) class ", r10.getName()));
     */
    /* JADX WARNING: Removed duplicated region for block: B:174:0x0374  */
    /* JADX WARNING: Removed duplicated region for block: B:175:0x0378  */
    @Override // X.AbstractC04630qr, X.AbstractC01840hB
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public final com.fasterxml.jackson.databind.JsonSerializer<java.lang.Object> A03(X.AbstractC02120i3 r35, X.AbstractC02190iF r36) throws X.C02180iD {
        /*
        // Method dump skipped, instructions count: 2724
        */
        throw new UnsupportedOperationException("Method not decompiled: X.C00980Of.A03(X.0i3, X.0iF):com.fasterxml.jackson.databind.JsonSerializer");
    }
}
