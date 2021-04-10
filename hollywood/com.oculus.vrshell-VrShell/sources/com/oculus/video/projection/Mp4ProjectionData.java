package com.oculus.video.projection;

import com.oculus.android.exoplayer2.util.ParsableBitArray;
import com.oculus.android.exoplayer2.util.ParsableByteArray;
import com.oculus.android.exoplayer2.util.Util;
import com.oculus.video.extractor.mp4.Atom;
import com.oculus.video.projection.ProjectionData;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.util.zip.CRC32;
import java.util.zip.DataFormatException;
import java.util.zip.Inflater;

/* access modifiers changed from: package-private */
public class Mp4ProjectionData extends ProjectionData {
    private static final int ENC_dfl8 = Util.getIntegerCodeForString("dfl8");
    private static final int ENC_raw = Util.getIntegerCodeForString("raw ");
    private static final int TYPE_cbmp = Util.getIntegerCodeForString("cbmp");
    private static final int TYPE_equi = Util.getIntegerCodeForString("equi");
    private static final int TYPE_mesh = Util.getIntegerCodeForString("mesh");
    private static final int TYPE_mshp = Util.getIntegerCodeForString("mshp");
    private static final int TYPE_prhd = Util.getIntegerCodeForString("prhd");

    private float convertFixed_0_32(long j) {
        return (float) (((double) j) / 4.294967296E9d);
    }

    private float convertFixed_16_16(int i) {
        float f = ((float) (Integer.MAX_VALUE & i)) / 65536.0f;
        return i < 0 ? -f : f;
    }

    Mp4ProjectionData() {
    }

    @Override // com.oculus.video.projection.ProjectionData
    public void parse(byte[] bArr) throws IllegalArgumentException {
        ParsableByteArray parsableByteArray = new ParsableByteArray(bArr);
        parsableByteArray.reset(bArr, parsableByteArray.readInt());
        parsableByteArray.skipBytes(4);
        if (parsableByteArray.readInt() == Atom.TYPE_proj) {
            while (parsableByteArray.bytesLeft() > 0) {
                int position = parsableByteArray.getPosition();
                int readInt = parsableByteArray.readInt();
                int readInt2 = parsableByteArray.readInt();
                if (readInt2 == TYPE_prhd) {
                    parsePrhd(parsableByteArray);
                } else if (readInt2 == TYPE_equi) {
                    parseEqui(parsableByteArray);
                } else if (readInt2 == TYPE_cbmp) {
                    parseCbmp(parsableByteArray);
                } else if (readInt2 == TYPE_mshp) {
                    try {
                        parseMshp(parsableByteArray, readInt - 8);
                    } catch (IOException | DataFormatException unused) {
                        throw new IllegalArgumentException("Unable to parse mesh projection data");
                    }
                }
                parsableByteArray.setPosition(Math.min(position + readInt, parsableByteArray.limit()));
            }
            return;
        }
        throw new IllegalArgumentException("Invalid projection data");
    }

    private void parsePrhd(ParsableByteArray parsableByteArray) {
        parsableByteArray.skipBytes(4);
        this.pose = new ProjectionData.Pose(convertFixed_16_16(parsableByteArray.readInt()), convertFixed_16_16(parsableByteArray.readInt()), convertFixed_16_16(parsableByteArray.readInt()));
    }

    private void parseEqui(ParsableByteArray parsableByteArray) {
        parsableByteArray.skipBytes(4);
        this.projectionType = ProjectionType.EQUIRECTANGULAR;
        float convertFixed_0_32 = convertFixed_0_32(parsableByteArray.readUnsignedInt());
        float convertFixed_0_322 = convertFixed_0_32(parsableByteArray.readUnsignedInt());
        float convertFixed_0_323 = convertFixed_0_32(parsableByteArray.readUnsignedInt());
        float convertFixed_0_324 = convertFixed_0_32(parsableByteArray.readUnsignedInt());
        this.leftFovProperties = new ProjectionData.FoVProperties((1.0f - (convertFixed_0_323 + convertFixed_0_324)) * 360.0f, (1.0f - (convertFixed_0_32 + convertFixed_0_322)) * 180.0f, (((convertFixed_0_323 + (1.0f - convertFixed_0_324)) / 2.0f) * 360.0f) - 180.0f, (((convertFixed_0_322 + (1.0f - convertFixed_0_32)) / 2.0f) * 180.0f) - 90.0f);
    }

    private void parseCbmp(ParsableByteArray parsableByteArray) {
        parsableByteArray.skipBytes(4);
        if (parsableByteArray.readUnsignedInt() == 0) {
            this.projectionType = ProjectionType.CUBEMAP;
        } else {
            this.projectionType = ProjectionType.CUBEMAP;
        }
        this.cubemapPadding = parsableByteArray.readUnsignedInt();
    }

    /* JADX INFO: finally extract failed */
    private byte[] inflate(byte[] bArr, int i, int i2) throws DataFormatException, IOException {
        Inflater inflater = new Inflater(true);
        inflater.setInput(bArr, i, i2);
        ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream();
        try {
            byte[] bArr2 = new byte[262144];
            while (true) {
                if (inflater.finished()) {
                    break;
                }
                int inflate = inflater.inflate(bArr2);
                if (inflate == 0) {
                    break;
                }
                byteArrayOutputStream.write(bArr2, 0, inflate);
            }
            byteArrayOutputStream.close();
            return byteArrayOutputStream.toByteArray();
        } catch (Throwable th) {
            byteArrayOutputStream.close();
            throw th;
        }
    }

    private void parseMshp(ParsableByteArray parsableByteArray, int i) throws IllegalArgumentException, DataFormatException, IOException {
        ParsableByteArray parsableByteArray2;
        int position = parsableByteArray.getPosition();
        parsableByteArray.skipBytes(4);
        this.projectionType = ProjectionType.VR180;
        long readUnsignedInt = parsableByteArray.readUnsignedInt();
        CRC32 crc32 = new CRC32();
        crc32.update(parsableByteArray.data, parsableByteArray.getPosition(), i - (parsableByteArray.getPosition() - position));
        if (crc32.getValue() == readUnsignedInt) {
            int readInt = parsableByteArray.readInt();
            int position2 = i - (parsableByteArray.getPosition() - position);
            if (readInt == ENC_dfl8) {
                parsableByteArray2 = new ParsableByteArray(inflate(parsableByteArray.data, parsableByteArray.getPosition(), position2));
            } else if (readInt == ENC_raw) {
                ParsableByteArray parsableByteArray3 = new ParsableByteArray(parsableByteArray.data, position + i);
                parsableByteArray3.setPosition(parsableByteArray.getPosition());
                parsableByteArray2 = parsableByteArray3;
            } else {
                throw new IllegalArgumentException("Unsupported projection data encoding");
            }
            parsableByteArray.skipBytes(position2);
            while (parsableByteArray2.bytesLeft() > 0) {
                int position3 = parsableByteArray2.getPosition();
                int readInt2 = parsableByteArray2.readInt();
                if (parsableByteArray2.readInt() == TYPE_mesh) {
                    parseMesh(parsableByteArray2, readInt2 - 8);
                }
                parsableByteArray2.setPosition(Math.min(position3 + readInt2, parsableByteArray2.limit()));
            }
            return;
        }
        throw new IllegalArgumentException("Corrupted projection data");
    }

    private void parseMesh(ParsableByteArray parsableByteArray, int i) {
        int position = parsableByteArray.getPosition();
        int readInt = parsableByteArray.readInt();
        float[] fArr = new float[readInt];
        int i2 = 0;
        for (int i3 = 0; i3 < readInt; i3++) {
            fArr[i3] = parsableByteArray.readFloat();
        }
        int readInt2 = parsableByteArray.readInt();
        ProjectionData.Vertex[] vertexArr = new ProjectionData.Vertex[readInt2];
        ParsableBitArray parsableBitArray = new ParsableBitArray(parsableByteArray.data);
        parsableBitArray.setPosition(parsableByteArray.getPosition() * 8);
        int csb = getCSB(readInt);
        int i4 = 0;
        int i5 = 0;
        int i6 = 0;
        int i7 = 0;
        int i8 = 0;
        while (i8 < readInt2) {
            int deZigZag = i2 + deZigZag(parsableBitArray.readBits(csb));
            int deZigZag2 = i4 + deZigZag(parsableBitArray.readBits(csb));
            int deZigZag3 = i5 + deZigZag(parsableBitArray.readBits(csb));
            int deZigZag4 = i6 + deZigZag(parsableBitArray.readBits(csb));
            int deZigZag5 = i7 + deZigZag(parsableBitArray.readBits(csb));
            vertexArr[i8] = new ProjectionData.Vertex(fArr[deZigZag], fArr[deZigZag2], fArr[deZigZag3], fArr[deZigZag4], fArr[deZigZag5]);
            i8++;
            i2 = deZigZag;
            i4 = deZigZag2;
            i5 = deZigZag3;
            i6 = deZigZag4;
            i7 = deZigZag5;
        }
        parsableByteArray.skipBytes((((readInt2 * 5) * csb) + 7) / 8);
        estimateProjectionMetadataFromMesh(vertexArr);
        parsableByteArray.setPosition(position + i);
    }

    private int getCSB(int i) {
        return (int) (Math.ceil(Math.log((double) (i * 2)) / Math.log(2.0d)) + 0.5d);
    }

    private int deZigZag(int i) {
        boolean z = i % 2 == 1;
        int i2 = (i + 1) / 2;
        return z ? -i2 : i2;
    }
}
