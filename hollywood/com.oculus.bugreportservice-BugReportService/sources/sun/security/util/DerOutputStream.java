package sun.security.util;

import java.io.ByteArrayOutputStream;
import java.io.OutputStream;
import java.math.BigInteger;
import java.text.SimpleDateFormat;
import java.util.Arrays;
import java.util.Comparator;
import java.util.Date;
import java.util.Locale;
import java.util.TimeZone;

public class DerOutputStream extends ByteArrayOutputStream implements DerEncoder {
    private static ByteArrayLexOrder lexOrder = new ByteArrayLexOrder();
    private static ByteArrayTagOrder tagOrder = new ByteArrayTagOrder();

    public void write(byte b, byte[] bArr) {
        write(b);
        putLength(bArr.length);
        write(bArr, 0, bArr.length);
    }

    public void write(byte b, DerOutputStream derOutputStream) {
        write(b);
        putLength(derOutputStream.count);
        write(derOutputStream.buf, 0, derOutputStream.count);
    }

    public void writeImplicit(byte b, DerOutputStream derOutputStream) {
        write(b);
        write(derOutputStream.buf, 1, derOutputStream.count - 1);
    }

    public void putDerValue(DerValue derValue) {
        derValue.encode(this);
    }

    public void putBoolean(boolean z) {
        write(1);
        putLength(1);
        if (z) {
            write(255);
        } else {
            write(0);
        }
    }

    public void putEnumerated(int i) {
        write(10);
        putIntegerContents(i);
    }

    public void putInteger(BigInteger bigInteger) {
        write(2);
        byte[] byteArray = bigInteger.toByteArray();
        putLength(byteArray.length);
        write(byteArray, 0, byteArray.length);
    }

    public void putInteger(int i) {
        write(2);
        putIntegerContents(i);
    }

    private void putIntegerContents(int i) {
        int i2;
        byte[] bArr = new byte[4];
        bArr[3] = (byte) (i & 255);
        bArr[2] = (byte) ((65280 & i) >>> 8);
        bArr[1] = (byte) ((16711680 & i) >>> 16);
        int i3 = 0;
        bArr[0] = (byte) ((i & -16777216) >>> 24);
        if (bArr[0] == -1) {
            i2 = 0;
            while (i3 < 3 && bArr[i3] == -1) {
                i3++;
                if ((bArr[i3] & 128) != 128) {
                    break;
                }
                i2++;
            }
        } else if (bArr[0] == 0) {
            i2 = 0;
            while (i3 < 3 && bArr[i3] == 0) {
                i3++;
                if ((bArr[i3] & 128) != 0) {
                    break;
                }
                i2++;
            }
        } else {
            i2 = 0;
        }
        putLength(4 - i2);
        while (i2 < 4) {
            write(bArr[i2]);
            i2++;
        }
    }

    public void putUnalignedBitString(BitArray bitArray) {
        byte[] byteArray = bitArray.toByteArray();
        write(3);
        putLength(byteArray.length + 1);
        write((byteArray.length * 8) - bitArray.length());
        write(byteArray);
    }

    public void putTruncatedUnalignedBitString(BitArray bitArray) {
        putUnalignedBitString(bitArray.truncate());
    }

    public void putOctetString(byte[] bArr) {
        write((byte) 4, bArr);
    }

    public void putNull() {
        write(5);
        putLength(0);
    }

    public void putOID(ObjectIdentifier objectIdentifier) {
        objectIdentifier.encode(this);
    }

    public void putOrderedSetOf(byte b, DerEncoder[] derEncoderArr) {
        putOrderedSet(b, derEncoderArr, lexOrder);
    }

    private void putOrderedSet(byte b, DerEncoder[] derEncoderArr, Comparator comparator) {
        DerOutputStream[] derOutputStreamArr = new DerOutputStream[derEncoderArr.length];
        for (int i = 0; i < derEncoderArr.length; i++) {
            derOutputStreamArr[i] = new DerOutputStream();
            derEncoderArr[i].derEncode(derOutputStreamArr[i]);
        }
        byte[][] bArr = new byte[derOutputStreamArr.length][];
        for (int i2 = 0; i2 < derOutputStreamArr.length; i2++) {
            bArr[i2] = derOutputStreamArr[i2].toByteArray();
        }
        Arrays.sort(bArr, comparator);
        DerOutputStream derOutputStream = new DerOutputStream();
        for (int i3 = 0; i3 < derOutputStreamArr.length; i3++) {
            derOutputStream.write(bArr[i3]);
        }
        write(b, derOutputStream);
    }

    public void putPrintableString(String str) {
        writeString(str, (byte) 19, "ASCII");
    }

    public void putIA5String(String str) {
        writeString(str, (byte) 22, "ASCII");
    }

    private void writeString(String str, byte b, String str2) {
        byte[] bytes = str.getBytes(str2);
        write(b);
        putLength(bytes.length);
        write(bytes);
    }

    public void putUTCTime(Date date) {
        putTime(date, (byte) 23);
    }

    public void putGeneralizedTime(Date date) {
        putTime(date, (byte) 24);
    }

    private void putTime(Date date, byte b) {
        String str;
        TimeZone timeZone = TimeZone.getTimeZone("GMT");
        if (b == 23) {
            str = "yyMMddHHmmss'Z'";
        } else {
            b = 24;
            str = "yyyyMMddHHmmss'Z'";
        }
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat(str, Locale.US);
        simpleDateFormat.setTimeZone(timeZone);
        byte[] bytes = simpleDateFormat.format(date).getBytes("ISO-8859-1");
        write(b);
        putLength(bytes.length);
        write(bytes);
    }

    public void putLength(int i) {
        if (i < 128) {
            write((byte) i);
        } else if (i < 256) {
            write(-127);
            write((byte) i);
        } else if (i < 65536) {
            write(-126);
            write((byte) (i >> 8));
            write((byte) i);
        } else if (i < 16777216) {
            write(-125);
            write((byte) (i >> 16));
            write((byte) (i >> 8));
            write((byte) i);
        } else {
            write(-124);
            write((byte) (i >> 24));
            write((byte) (i >> 16));
            write((byte) (i >> 8));
            write((byte) i);
        }
    }

    @Override // sun.security.util.DerEncoder
    public void derEncode(OutputStream outputStream) {
        outputStream.write(toByteArray());
    }
}
