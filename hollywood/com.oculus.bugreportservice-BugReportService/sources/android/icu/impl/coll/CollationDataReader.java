package android.icu.impl.coll;

import android.icu.impl.ICUBinary;
import android.icu.impl.Trie2_32;
import android.icu.impl.USerializedSet;
import android.icu.text.UnicodeSet;
import android.icu.util.ICUException;
import java.nio.ByteBuffer;
import java.nio.CharBuffer;
import java.util.Arrays;

final class CollationDataReader {
    private static final IsAcceptable IS_ACCEPTABLE = new IsAcceptable();

    static void read(CollationTailoring collationTailoring, ByteBuffer byteBuffer, CollationTailoring collationTailoring2) {
        int i;
        CollationData collationData;
        int i2;
        int[] iArr;
        byte[] bArr;
        CollationData collationData2;
        int[] iArr2;
        int i3;
        int[] iArr3;
        collationTailoring2.version = ICUBinary.readHeader(byteBuffer, 1430482796, IS_ACCEPTABLE);
        if (collationTailoring == null || collationTailoring.getUCAVersion() == collationTailoring2.getUCAVersion()) {
            int remaining = byteBuffer.remaining();
            if (remaining >= 8) {
                int i4 = byteBuffer.getInt();
                if (i4 < 2 || remaining < i4 * 4) {
                    throw new ICUException("not enough indexes");
                }
                int[] iArr4 = new int[20];
                iArr4[0] = i4;
                int i5 = 1;
                while (i5 < i4 && i5 < iArr4.length) {
                    iArr4[i5] = byteBuffer.getInt();
                    i5++;
                }
                for (int i6 = i4; i6 < iArr4.length; i6++) {
                    iArr4[i6] = -1;
                }
                if (i4 > iArr4.length) {
                    ICUBinary.skipBytes(byteBuffer, (i4 - iArr4.length) * 4);
                }
                if (i4 > 19) {
                    i = iArr4[19];
                } else {
                    i = i4 > 5 ? iArr4[i4 - 1] : 0;
                }
                if (remaining >= i) {
                    if (collationTailoring == null) {
                        collationData = null;
                    } else {
                        collationData = collationTailoring.data;
                    }
                    int i7 = iArr4[6] - iArr4[5];
                    if (i7 < 4) {
                        ICUBinary.skipBytes(byteBuffer, i7);
                        iArr = new int[0];
                        i2 = 0;
                    } else if (collationData != null) {
                        int i8 = i7 / 4;
                        iArr = ICUBinary.getInts(byteBuffer, i8, i7 & 3);
                        int i9 = 0;
                        while (i9 < i8 && (iArr[(i8 - i9) - 1] & -65536) != 0) {
                            i9++;
                        }
                        i2 = i8 - i9;
                    } else {
                        throw new ICUException("Collation base data must not reorder scripts");
                    }
                    int i10 = iArr4[7] - iArr4[6];
                    if (i10 < 256) {
                        bArr = null;
                    } else if (i2 != 0) {
                        bArr = new byte[256];
                        byteBuffer.get(bArr);
                        i10 -= 256;
                    } else {
                        throw new ICUException("Reordering table without reordering codes");
                    }
                    ICUBinary.skipBytes(byteBuffer, i10);
                    if (collationData == null || collationData.numericPrimary == (((long) iArr4[1]) & 4278190080L)) {
                        int i11 = iArr4[8] - iArr4[7];
                        if (i11 >= 8) {
                            collationTailoring2.ensureOwnedData();
                            collationData2 = collationTailoring2.ownedData;
                            collationData2.base = collationData;
                            collationData2.numericPrimary = ((long) iArr4[1]) & 4278190080L;
                            Trie2_32 createFromSerialized = Trie2_32.createFromSerialized(byteBuffer);
                            collationTailoring2.trie = createFromSerialized;
                            collationData2.trie = createFromSerialized;
                            int serializedLength = collationData2.trie.getSerializedLength();
                            if (serializedLength <= i11) {
                                i11 -= serializedLength;
                            } else {
                                throw new ICUException("Not enough bytes for the mappings trie");
                            }
                        } else if (collationData != null) {
                            collationTailoring2.data = collationData;
                            collationData2 = null;
                        } else {
                            throw new ICUException("Missing collation data mappings");
                        }
                        ICUBinary.skipBytes(byteBuffer, i11);
                        ICUBinary.skipBytes(byteBuffer, iArr4[9] - iArr4[8]);
                        int i12 = iArr4[10] - iArr4[9];
                        if (i12 < 8) {
                            ICUBinary.skipBytes(byteBuffer, i12);
                        } else if (collationData2 != null) {
                            collationData2.ces = ICUBinary.getLongs(byteBuffer, i12 / 8, i12 & 7);
                        } else {
                            throw new ICUException("Tailored ces without tailored trie");
                        }
                        ICUBinary.skipBytes(byteBuffer, iArr4[11] - iArr4[10]);
                        int i13 = iArr4[12] - iArr4[11];
                        if (i13 < 4) {
                            ICUBinary.skipBytes(byteBuffer, i13);
                        } else if (collationData2 != null) {
                            collationData2.ce32s = ICUBinary.getInts(byteBuffer, i13 / 4, i13 & 3);
                        } else {
                            throw new ICUException("Tailored ce32s without tailored trie");
                        }
                        int i14 = iArr4[4];
                        if (i14 >= 0) {
                            if (collationData2 == null || (iArr3 = collationData2.ce32s) == null) {
                                throw new ICUException("JamoCE32sStart index into non-existent ce32s[]");
                            }
                            collationData2.jamoCE32s = new int[67];
                            System.arraycopy(iArr3, i14, collationData2.jamoCE32s, 0, 67);
                        } else if (collationData2 != null) {
                            if (collationData != null) {
                                collationData2.jamoCE32s = collationData.jamoCE32s;
                            } else {
                                throw new ICUException("Missing Jamo CE32s for Hangul processing");
                            }
                        }
                        int i15 = iArr4[13] - iArr4[12];
                        if (i15 >= 4) {
                            int i16 = i15 / 4;
                            if (collationData2 == null) {
                                throw new ICUException("Root elements but no mappings");
                            } else if (i16 > 4) {
                                collationData2.rootElements = new long[i16];
                                int i17 = 0;
                                while (i17 < i16) {
                                    collationData2.rootElements[i17] = ((long) byteBuffer.getInt()) & 4294967295L;
                                    i17++;
                                    iArr4 = iArr4;
                                }
                                iArr2 = iArr4;
                                long[] jArr = collationData2.rootElements;
                                if (jArr[3] != 83887360) {
                                    throw new ICUException("Common sec/ter weights in base data differ from the hardcoded value");
                                } else if ((jArr[4] >>> 24) >= 69) {
                                    i15 &= 3;
                                } else {
                                    throw new ICUException("[fixed last secondary common byte] is too low");
                                }
                            } else {
                                throw new ICUException("Root elements array too short");
                            }
                        } else {
                            iArr2 = iArr4;
                        }
                        ICUBinary.skipBytes(byteBuffer, i15);
                        int i18 = iArr2[14] - iArr2[13];
                        if (i18 < 2) {
                            ICUBinary.skipBytes(byteBuffer, i18);
                        } else if (collationData2 != null) {
                            collationData2.contexts = ICUBinary.getString(byteBuffer, i18 / 2, i18 & 1);
                        } else {
                            throw new ICUException("Tailored contexts without tailored trie");
                        }
                        int i19 = iArr2[15] - iArr2[14];
                        if (i19 >= 2) {
                            if (collationData2 != null) {
                                if (collationData == null) {
                                    collationTailoring2.unsafeBackwardSet = new UnicodeSet(56320, 57343);
                                    collationData2.nfcImpl.addLcccChars(collationTailoring2.unsafeBackwardSet);
                                } else {
                                    collationTailoring2.unsafeBackwardSet = collationData.unsafeBackwardSet.cloneAsThawed();
                                }
                                USerializedSet uSerializedSet = new USerializedSet();
                                char c = 0;
                                uSerializedSet.getSet(ICUBinary.getChars(byteBuffer, i19 / 2, i19 & 1), 0);
                                int countRanges = uSerializedSet.countRanges();
                                int[] iArr5 = new int[2];
                                int i20 = 0;
                                while (i20 < countRanges) {
                                    uSerializedSet.getRange(i20, iArr5);
                                    collationTailoring2.unsafeBackwardSet.add(iArr5[c], iArr5[1]);
                                    i20++;
                                    c = 0;
                                }
                                int i21 = 65536;
                                int i22 = 55296;
                                while (i22 < 56320) {
                                    if (!collationTailoring2.unsafeBackwardSet.containsNone(i21, i21 + 1023)) {
                                        collationTailoring2.unsafeBackwardSet.add(i22);
                                    }
                                    i22++;
                                    i21 += 1024;
                                }
                                collationTailoring2.unsafeBackwardSet.freeze();
                                collationData2.unsafeBackwardSet = collationTailoring2.unsafeBackwardSet;
                                i19 = 0;
                            } else {
                                throw new ICUException("Unsafe-backward-set but no mappings");
                            }
                        } else if (collationData2 != null) {
                            if (collationData != null) {
                                collationData2.unsafeBackwardSet = collationData.unsafeBackwardSet;
                            } else {
                                throw new ICUException("Missing unsafe-backward-set");
                            }
                        }
                        ICUBinary.skipBytes(byteBuffer, i19);
                        int i23 = iArr2[16] - iArr2[15];
                        if (collationData2 != null) {
                            collationData2.fastLatinTable = null;
                            collationData2.fastLatinTableHeader = null;
                            if (((iArr2[1] >> 16) & 255) == 2) {
                                if (i23 >= 2) {
                                    char c2 = byteBuffer.getChar();
                                    int i24 = c2 & 255;
                                    collationData2.fastLatinTableHeader = new char[i24];
                                    collationData2.fastLatinTableHeader[0] = c2;
                                    for (int i25 = 1; i25 < i24; i25++) {
                                        collationData2.fastLatinTableHeader[i25] = byteBuffer.getChar();
                                    }
                                    collationData2.fastLatinTable = ICUBinary.getChars(byteBuffer, (i23 / 2) - i24, i23 & 1);
                                    if ((c2 >> '\b') == 2) {
                                        i23 = 0;
                                    } else {
                                        throw new ICUException("Fast-Latin table version differs from version in data header");
                                    }
                                } else if (collationData != null) {
                                    collationData2.fastLatinTable = collationData.fastLatinTable;
                                    collationData2.fastLatinTableHeader = collationData.fastLatinTableHeader;
                                }
                            }
                        }
                        ICUBinary.skipBytes(byteBuffer, i23);
                        int i26 = iArr2[17] - iArr2[16];
                        if (i26 < 2) {
                            i3 = false;
                            if (!(collationData2 == null || collationData == null)) {
                                collationData2.numScripts = collationData.numScripts;
                                collationData2.scriptsIndex = collationData.scriptsIndex;
                                collationData2.scriptStarts = collationData.scriptStarts;
                            }
                        } else if (collationData2 != null) {
                            CharBuffer asCharBuffer = byteBuffer.asCharBuffer();
                            collationData2.numScripts = asCharBuffer.get();
                            int i27 = collationData2.numScripts;
                            int i28 = (i26 / 2) - ((i27 + 1) + 16);
                            if (i28 > 2) {
                                char[] cArr = new char[(i27 + 16)];
                                collationData2.scriptsIndex = cArr;
                                asCharBuffer.get(cArr);
                                char[] cArr2 = new char[i28];
                                collationData2.scriptStarts = cArr2;
                                asCharBuffer.get(cArr2);
                                char[] cArr3 = collationData2.scriptStarts;
                                i3 = false;
                                if (!(cArr3[0] == 0 && cArr3[1] == 768 && cArr3[i28 - 1] == 65280)) {
                                    throw new ICUException("Script order data not valid");
                                }
                            } else {
                                throw new ICUException("Script order data too short");
                            }
                        } else {
                            throw new ICUException("Script order data but no mappings");
                        }
                        ICUBinary.skipBytes(byteBuffer, i26);
                        int i29 = iArr2[18] - iArr2[17];
                        if (i29 >= 256) {
                            if (collationData2 != null) {
                                collationData2.compressibleBytes = new boolean[256];
                                for (int i30 = i3; i30 < 256; i30++) {
                                    collationData2.compressibleBytes[i30] = byteBuffer.get() != 0 ? true : i3;
                                }
                                i29 -= 256;
                            } else {
                                throw new ICUException("Data for compressible primary lead bytes but no mappings");
                            }
                        } else if (collationData2 != null) {
                            if (collationData != null) {
                                collationData2.compressibleBytes = collationData.compressibleBytes;
                            } else {
                                throw new ICUException("Missing data for compressible primary lead bytes");
                            }
                        }
                        ICUBinary.skipBytes(byteBuffer, i29);
                        ICUBinary.skipBytes(byteBuffer, iArr2[19] - iArr2[18]);
                        CollationSettings collationSettings = (CollationSettings) collationTailoring2.settings.readOnly();
                        int i31 = iArr2[1] & 65535;
                        char[] cArr4 = new char[384];
                        int options = CollationFastLatin.getOptions(collationTailoring2.data, collationSettings, cArr4);
                        if (i31 != collationSettings.options || collationSettings.variableTop == 0 || !Arrays.equals(iArr, collationSettings.reorderCodes) || options != collationSettings.fastLatinOptions || (options >= 0 && !Arrays.equals(cArr4, collationSettings.fastLatinPrimaries))) {
                            CollationSettings collationSettings2 = (CollationSettings) collationTailoring2.settings.copyOnWrite();
                            collationSettings2.options = i31;
                            collationSettings2.variableTop = collationTailoring2.data.getLastPrimaryForGroup(collationSettings2.getMaxVariable() + 4096);
                            if (collationSettings2.variableTop != 0) {
                                if (i2 != 0) {
                                    collationSettings2.aliasReordering(collationData, iArr, i2, bArr);
                                }
                                collationSettings2.fastLatinOptions = CollationFastLatin.getOptions(collationTailoring2.data, collationSettings2, collationSettings2.fastLatinPrimaries);
                                return;
                            }
                            throw new ICUException("The maxVariable could not be mapped to a variableTop");
                        }
                        return;
                    }
                    throw new ICUException("Tailoring numeric primary weight differs from base data");
                }
                throw new ICUException("not enough bytes");
            }
            throw new ICUException("not enough bytes");
        }
        throw new ICUException("Tailoring UCA version differs from base data UCA version");
    }

    private static final class IsAcceptable implements ICUBinary.Authenticate {
        private IsAcceptable() {
        }

        @Override // android.icu.impl.ICUBinary.Authenticate
        public boolean isDataVersionAcceptable(byte[] bArr) {
            return bArr[0] == 5;
        }
    }
}
