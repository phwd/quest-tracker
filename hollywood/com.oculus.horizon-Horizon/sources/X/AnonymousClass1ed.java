package X;

import com.oculus.horizon.cast.CastWebSocket;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.nio.charset.CharacterCodingException;

/* renamed from: X.1ed  reason: invalid class name */
public class AnonymousClass1ed extends AnonymousClass1eX {
    public final /* synthetic */ AbstractC09411eb A00;

    /* JADX INFO: super call moved to the top of the method (can break code semantics) */
    public AnonymousClass1ed(AbstractC09411eb r7, AnonymousClass1iB r8) {
        super(r8, null, null, 0);
        this.A00 = r7;
    }

    @Override // X.AnonymousClass1eX
    public final void A03(OutputStream outputStream) {
        AnonymousClass1fR r1;
        String str;
        AnonymousClass1ec r12;
        String str2;
        AbstractC09411eb r6 = this.A00;
        r6.out = outputStream;
        r6.state = AnonymousClass1fb.CONNECTING;
        super.A03(outputStream);
        AnonymousClass1fb r8 = AnonymousClass1fb.OPEN;
        r6.state = r8;
        while (r6.state == r8) {
            try {
                InputStream inputStream = r6.in;
                int read = inputStream.read();
                AnonymousClass1ec.A00(read);
                byte b = (byte) read;
                boolean z = false;
                if ((b & 128) != 0) {
                    z = true;
                }
                int i = b & 15;
                AnonymousClass1fE find = AnonymousClass1fE.find((byte) i);
                int i2 = b & 112;
                if (i2 != 0) {
                    throw new C09451fe(AnonymousClass1fR.ProtocolError, AnonymousClass006.A07("The reserved bits (", Integer.toBinaryString(i2), ") must be 0."));
                } else if (find == null) {
                    throw new C09451fe(AnonymousClass1fR.ProtocolError, AnonymousClass006.A02("Received frame with reserved/unknown opcode ", i, "."));
                } else if (!find.isControlFrame() || z) {
                    AnonymousClass1ec r5 = new AnonymousClass1ec(find, z);
                    int read2 = inputStream.read();
                    AnonymousClass1ec.A00(read2);
                    byte b2 = (byte) read2;
                    int i3 = 0;
                    boolean z2 = false;
                    if ((b2 & 128) != 0) {
                        z2 = true;
                    }
                    int i4 = (byte) (b2 & Byte.MAX_VALUE);
                    r5.A04 = i4;
                    if (i4 == 126) {
                        int read3 = inputStream.read();
                        AnonymousClass1ec.A00(read3);
                        int i5 = read3 << 8;
                        int read4 = inputStream.read();
                        AnonymousClass1ec.A00(read4);
                        i4 = (i5 | read4) & 65535;
                        r5.A04 = i4;
                        if (i4 < 126) {
                            throw new C09451fe(AnonymousClass1fR.ProtocolError, "Invalid data frame 2byte length. (not using minimal length encoding)");
                        }
                    } else if (i4 == 127) {
                        int read5 = inputStream.read();
                        AnonymousClass1ec.A00(read5);
                        int read6 = inputStream.read();
                        AnonymousClass1ec.A00(read6);
                        long j = (((long) read5) << 56) | (((long) read6) << 48);
                        int read7 = inputStream.read();
                        AnonymousClass1ec.A00(read7);
                        long j2 = j | (((long) read7) << 40);
                        int read8 = inputStream.read();
                        AnonymousClass1ec.A00(read8);
                        long j3 = j2 | (((long) read8) << 32);
                        int read9 = inputStream.read();
                        AnonymousClass1ec.A00(read9);
                        long j4 = j3 | ((long) (read9 << 24));
                        int read10 = inputStream.read();
                        AnonymousClass1ec.A00(read10);
                        long j5 = j4 | ((long) (read10 << 16));
                        int read11 = inputStream.read();
                        AnonymousClass1ec.A00(read11);
                        long j6 = j5 | ((long) (read11 << 8));
                        int read12 = inputStream.read();
                        AnonymousClass1ec.A00(read12);
                        long j7 = j6 | ((long) read12);
                        if (j7 < 65536) {
                            throw new C09451fe(AnonymousClass1fR.ProtocolError, "Invalid data frame 4byte length. (not using minimal length encoding)");
                        } else if (j7 < 0 || j7 > 2147483647L) {
                            throw new C09451fe(AnonymousClass1fR.MessageTooBig, "Max frame length has been exceeded.");
                        } else {
                            i4 = (int) j7;
                            r5.A04 = i4;
                        }
                    }
                    AnonymousClass1fE r13 = r5.A00;
                    if (r13.isControlFrame()) {
                        if (i4 > 125) {
                            throw new C09451fe(AnonymousClass1fR.ProtocolError, "Control frame with payload length > 125 bytes.");
                        } else if (r13 == AnonymousClass1fE.Close && i4 == 1) {
                            throw new C09451fe(AnonymousClass1fR.ProtocolError, "Received close frame with payload len 1.");
                        }
                    }
                    if (z2) {
                        r5.A02 = new byte[4];
                        while (true) {
                            byte[] bArr = r5.A02;
                            int length = bArr.length;
                            if (i3 >= length) {
                                break;
                            }
                            int read13 = inputStream.read(bArr, i3, length - i3);
                            AnonymousClass1ec.A00(read13);
                            i3 += read13;
                        }
                    }
                    r5.A03 = new byte[r5.A04];
                    int i6 = 0;
                    int i7 = 0;
                    while (true) {
                        int i8 = r5.A04;
                        if (i7 >= i8) {
                            break;
                        }
                        int read14 = inputStream.read(r5.A03, i7, i8 - i7);
                        AnonymousClass1ec.A00(read14);
                        i7 += read14;
                    }
                    if (AnonymousClass1ec.A01(r5)) {
                        while (true) {
                            byte[] bArr2 = r5.A03;
                            if (i6 >= bArr2.length) {
                                break;
                            }
                            bArr2[i6] = (byte) (bArr2[i6] ^ r5.A02[i6 % 4]);
                            i6++;
                        }
                    }
                    if (r5.A00 == AnonymousClass1fE.Text) {
                        r5.A05 = new String(r5.A03, AnonymousClass1ec.A06);
                    }
                    AnonymousClass1fE r14 = r5.A00;
                    AnonymousClass1fE r0 = AnonymousClass1fE.Close;
                    if (r14 == r0) {
                        r12 = new C09431el(r5);
                    } else {
                        r12 = r5;
                    }
                    AnonymousClass1fE r2 = r12.A00;
                    if (r2 == r0) {
                        AnonymousClass1fR r4 = AnonymousClass1fR.NormalClosure;
                        if (r12 instanceof C09431el) {
                            C09431el r15 = (C09431el) r12;
                            r4 = r15.A00;
                            str2 = r15.A01;
                        } else {
                            str2 = "";
                        }
                        AnonymousClass1fb r22 = r6.state;
                        AnonymousClass1fb r16 = AnonymousClass1fb.CLOSING;
                        if (r22 == r16) {
                            AbstractC09411eb.A00(r6, r4, str2, false);
                        } else {
                            r6.state = r16;
                            if (r22 == r8) {
                                AbstractC09411eb.A01(r6, new C09431el(r4, str2));
                            } else {
                                AbstractC09411eb.A00(r6, r4, str2, true);
                            }
                        }
                    } else if (r2 == AnonymousClass1fE.Ping) {
                        AnonymousClass1fE r42 = AnonymousClass1fE.Pong;
                        byte[] bArr3 = r12.A03;
                        AnonymousClass1ec r17 = new AnonymousClass1ec(r42, true);
                        r17.A02 = null;
                        r17.A03 = bArr3;
                        r17.A04 = bArr3.length;
                        r17.A05 = null;
                        AbstractC09411eb.A01(r6, r17);
                    } else if (r2 == AnonymousClass1fE.Pong) {
                        continue;
                    } else {
                        boolean z3 = r12.A01;
                        if (!z3 || r2 == AnonymousClass1fE.Continuation) {
                            if (r2 != AnonymousClass1fE.Continuation) {
                                if (r6.continuousOpCode == null) {
                                    r6.continuousOpCode = r2;
                                    r6.continuousFrames.clear();
                                    r6.continuousFrames.add(r12);
                                } else {
                                    throw new C09451fe(AnonymousClass1fR.ProtocolError, "Previous continuous frame sequence not completed.");
                                }
                            } else if (z3) {
                                if (r6.continuousOpCode != null) {
                                    r6.continuousFrames.add(r12);
                                    r6.A02(new AnonymousClass1ec(r6.continuousOpCode, r6.continuousFrames));
                                    r6.continuousOpCode = null;
                                    r6.continuousFrames.clear();
                                } else {
                                    throw new C09451fe(AnonymousClass1fR.ProtocolError, "Continuous frame sequence was not started.");
                                }
                            } else if (r6.continuousOpCode != null) {
                                r6.continuousFrames.add(r12);
                            } else {
                                throw new C09451fe(AnonymousClass1fR.ProtocolError, "Continuous frame sequence was not started.");
                            }
                        } else if (r6.continuousOpCode != null) {
                            throw new C09451fe(AnonymousClass1fR.ProtocolError, "Continuous frame sequence not completed.");
                        } else if (r2 == AnonymousClass1fE.Text || r2 == AnonymousClass1fE.Binary) {
                            r6.A02(r12);
                        } else {
                            throw new C09451fe(AnonymousClass1fR.ProtocolError, "Non control or continuous frame expected.");
                        }
                    }
                } else {
                    throw new C09451fe(AnonymousClass1fR.ProtocolError, "Fragmented control frame.");
                }
            } catch (CharacterCodingException e) {
                ((CastWebSocket) r6).mListener.A64(e.getMessage());
                r1 = AnonymousClass1fR.InvalidFramePayloadData;
                str = e.toString();
            } catch (IOException e2) {
                ((CastWebSocket) r6).mListener.A64(e2.getMessage());
                if (e2 instanceof C09451fe) {
                    C09451fe r23 = (C09451fe) e2;
                    r1 = r23.code;
                    str = r23.reason;
                }
            } catch (Throwable th) {
                AbstractC09411eb.A00(r6, AnonymousClass1fR.InternalServerError, "Handler terminated without closing the connection.", false);
                throw th;
            }
        }
        AbstractC09411eb.A00(r6, AnonymousClass1fR.InternalServerError, "Handler terminated without closing the connection.", false);
        AbstractC09411eb.A00(r6, r1, str, false);
        AbstractC09411eb.A00(r6, AnonymousClass1fR.InternalServerError, "Handler terminated without closing the connection.", false);
    }
}
