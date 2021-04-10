package com.squareup.okhttp.internal.framed;

import X.AnonymousClass06;
import X.AnonymousClass8k;
import X.C00148h;
import X.Dp;
import X.WF;
import X.WM;
import com.facebook.tigon.iface.TigonRequest;
import com.oculus.auth.service.contract.ServiceContract;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

public final class Hpack {
    public static final Map<WM, Integer> NAME_TO_FIRST_INDEX = nameToFirstIndex();
    public static final int PREFIX_4_BITS = 15;
    public static final int PREFIX_5_BITS = 31;
    public static final int PREFIX_6_BITS = 63;
    public static final int PREFIX_7_BITS = 127;
    public static final Header[] STATIC_HEADER_TABLE;

    public static final class Reader {
        public Header[] dynamicTable = new Header[8];
        public int dynamicTableByteCount = 0;
        public int headerCount = 0;
        public final List<Header> headerList = new ArrayList();
        public int headerTableSizeSetting;
        public int maxDynamicTableByteCount;
        public int nextHeaderIndex = 7;
        public final Dp source;

        private int evictToRecoverBytes(int i) {
            int i2;
            int i3 = 0;
            if (i > 0) {
                Header[] headerArr = this.dynamicTable;
                int length = headerArr.length;
                while (true) {
                    length--;
                    i2 = this.nextHeaderIndex;
                    if (length < i2 || i <= 0) {
                        int i4 = i2 + 1;
                        System.arraycopy(headerArr, i4, headerArr, i4 + i3, this.headerCount);
                        this.nextHeaderIndex += i3;
                    } else {
                        int i5 = headerArr[length].hpackSize;
                        i -= i5;
                        this.dynamicTableByteCount -= i5;
                        this.headerCount--;
                        i3++;
                    }
                }
                int i42 = i2 + 1;
                System.arraycopy(headerArr, i42, headerArr, i42 + i3, this.headerCount);
                this.nextHeaderIndex += i3;
            }
            return i3;
        }

        private boolean isStaticHeader(int i) {
            return i >= 0 && i <= Hpack.STATIC_HEADER_TABLE.length - 1;
        }

        public int readInt(int i, int i2) throws IOException {
            int i3 = i & i2;
            if (i3 < i2) {
                return i3;
            }
            int i4 = 0;
            while (true) {
                int readByte = readByte();
                if ((readByte & 128) == 0) {
                    return i2 + (readByte << i4);
                }
                i2 += (readByte & Hpack.PREFIX_7_BITS) << i4;
                i4 += 7;
            }
        }

        private void adjustDynamicTableByteCount() {
            int i = this.maxDynamicTableByteCount;
            int i2 = this.dynamicTableByteCount;
            if (i >= i2) {
                return;
            }
            if (i == 0) {
                clearDynamicTable();
            } else {
                evictToRecoverBytes(i2 - i);
            }
        }

        private void clearDynamicTable() {
            this.headerList.clear();
            Arrays.fill(this.dynamicTable, (Object) null);
            this.nextHeaderIndex = this.dynamicTable.length - 1;
            this.headerCount = 0;
            this.dynamicTableByteCount = 0;
        }

        private int dynamicTableIndex(int i) {
            return this.nextHeaderIndex + 1 + i;
        }

        private void insertIntoDynamicTable(int i, Header header) {
            this.headerList.add(header);
            int i2 = header.hpackSize;
            if (i != -1) {
                i2 -= this.dynamicTable[(this.nextHeaderIndex + 1) + i].hpackSize;
            }
            int i3 = this.maxDynamicTableByteCount;
            if (i2 > i3) {
                clearDynamicTable();
                return;
            }
            int evictToRecoverBytes = evictToRecoverBytes((this.dynamicTableByteCount + i2) - i3);
            if (i == -1) {
                int i4 = this.headerCount + 1;
                Header[] headerArr = this.dynamicTable;
                int length = headerArr.length;
                if (i4 > length) {
                    Header[] headerArr2 = new Header[(length << 1)];
                    System.arraycopy(headerArr, 0, headerArr2, length, length);
                    this.nextHeaderIndex = this.dynamicTable.length - 1;
                    this.dynamicTable = headerArr2;
                    headerArr = headerArr2;
                }
                int i5 = this.nextHeaderIndex;
                this.nextHeaderIndex = i5 - 1;
                headerArr[i5] = header;
                this.headerCount++;
            } else {
                this.dynamicTable[i + this.nextHeaderIndex + 1 + i + evictToRecoverBytes] = header;
            }
            this.dynamicTableByteCount += i2;
        }

        private int readByte() throws IOException {
            return this.source.readByte() & 255;
        }

        public List<Header> getAndResetHeaderList() {
            ArrayList arrayList = new ArrayList(this.headerList);
            this.headerList.clear();
            return arrayList;
        }

        public void headerTableSizeSetting(int i) {
            this.headerTableSizeSetting = i;
            this.maxDynamicTableByteCount = i;
            adjustDynamicTableByteCount();
        }

        public void readHeaders() throws IOException {
            String str;
            while (!this.source.A1V()) {
                int readByte = this.source.readByte() & 255;
                if (readByte == 128) {
                    str = "index == 0";
                } else if ((readByte & 128) == 128) {
                    readIndexedHeader(readInt(readByte, Hpack.PREFIX_7_BITS) - 1);
                } else if (readByte == 64) {
                    readLiteralHeaderWithIncrementalIndexingNewName();
                } else if ((readByte & 64) == 64) {
                    readLiteralHeaderWithIncrementalIndexingIndexedName(readInt(readByte, 63) - 1);
                } else if ((readByte & 32) == 32) {
                    int readInt = readInt(readByte, 31);
                    this.maxDynamicTableByteCount = readInt;
                    if (readInt < 0 || readInt > this.headerTableSizeSetting) {
                        str = AnonymousClass06.A01("Invalid dynamic table size update ", readInt);
                    } else {
                        adjustDynamicTableByteCount();
                    }
                } else if (readByte == 16 || readByte == 0) {
                    readLiteralHeaderWithoutIndexingNewName();
                } else {
                    readLiteralHeaderWithoutIndexingIndexedName(readInt(readByte, 15) - 1);
                }
                throw new IOException(str);
            }
        }

        public Reader(int i, WF wf) {
            this.headerTableSizeSetting = i;
            this.maxDynamicTableByteCount = i;
            this.source = new C00148h(wf);
        }

        private WM getName(int i) {
            Header header;
            if (isStaticHeader(i)) {
                header = Hpack.STATIC_HEADER_TABLE[i];
            } else {
                header = this.dynamicTable[this.nextHeaderIndex + 1 + (i - Hpack.STATIC_HEADER_TABLE.length)];
            }
            return header.name;
        }

        private void readIndexedHeader(int i) throws IOException {
            if (isStaticHeader(i)) {
                this.headerList.add(Hpack.STATIC_HEADER_TABLE[i]);
                return;
            }
            int length = this.nextHeaderIndex + 1 + (i - Hpack.STATIC_HEADER_TABLE.length);
            if (length >= 0) {
                Header[] headerArr = this.dynamicTable;
                if (length <= headerArr.length - 1) {
                    this.headerList.add(headerArr[length]);
                    return;
                }
            }
            throw new IOException(AnonymousClass06.A01("Header index too large ", i + 1));
        }

        private void readLiteralHeaderWithIncrementalIndexingIndexedName(int i) throws IOException {
            insertIntoDynamicTable(-1, new Header(getName(i), readByteString()));
        }

        private void readLiteralHeaderWithIncrementalIndexingNewName() throws IOException {
            WM readByteString = readByteString();
            Hpack.checkLowercase(readByteString);
            insertIntoDynamicTable(-1, new Header(readByteString, readByteString()));
        }

        private void readLiteralHeaderWithoutIndexingIndexedName(int i) throws IOException {
            this.headerList.add(new Header(getName(i), readByteString()));
        }

        private void readLiteralHeaderWithoutIndexingNewName() throws IOException {
            WM readByteString = readByteString();
            Hpack.checkLowercase(readByteString);
            this.headerList.add(new Header(readByteString, readByteString()));
        }

        public int maxDynamicTableByteCount() {
            return this.maxDynamicTableByteCount;
        }

        public WM readByteString() throws IOException {
            int readByte = readByte();
            boolean z = false;
            if ((readByte & 128) == 128) {
                z = true;
            }
            int readInt = readInt(readByte, Hpack.PREFIX_7_BITS);
            if (z) {
                return WM.A05(Huffman.INSTANCE.decode(this.source.A32((long) readInt)));
            }
            return this.source.A33((long) readInt);
        }
    }

    public static final class Writer {
        public final AnonymousClass8k out;

        public void writeInt(int i, int i2, int i3) throws IOException {
            int i4;
            AnonymousClass8k r0;
            if (i < i2) {
                r0 = this.out;
                i4 = i | i3;
            } else {
                this.out.A09(i3 | i2);
                i4 = i - i2;
                while (i4 >= 128) {
                    this.out.A09(128 | (i4 & Hpack.PREFIX_7_BITS));
                    i4 >>>= 7;
                }
                r0 = this.out;
            }
            r0.A09(i4);
        }

        public Writer(AnonymousClass8k r1) {
            this.out = r1;
        }

        public void writeByteString(WM wm) throws IOException {
            writeInt(wm.A07(), Hpack.PREFIX_7_BITS, 0);
            wm.A0F(this.out);
        }

        public void writeHeaders(List<Header> list) throws IOException {
            int size = list.size();
            for (int i = 0; i < size; i++) {
                WM A0D = list.get(i).name.A0D();
                Integer num = Hpack.NAME_TO_FIRST_INDEX.get(A0D);
                if (num != null) {
                    writeInt(num.intValue() + 1, 15, 0);
                } else {
                    this.out.A09(0);
                    writeByteString(A0D);
                }
                writeByteString(list.get(i).value);
            }
        }
    }

    static {
        Header[] headerArr = new Header[61];
        Header header = new Header(Header.TARGET_AUTHORITY, "");
        WM wm = Header.TARGET_METHOD;
        Header header2 = new Header(wm, TigonRequest.GET);
        Header header3 = new Header(wm, TigonRequest.POST);
        WM wm2 = Header.TARGET_PATH;
        Header header4 = new Header(wm2, "/");
        Header header5 = new Header(wm2, "/index.html");
        WM wm3 = Header.TARGET_SCHEME;
        Header header6 = new Header(wm3, "http");
        Header header7 = new Header(wm3, "https");
        WM wm4 = Header.RESPONSE_STATUS;
        System.arraycopy(new Header[]{header, header2, header3, header4, header5, header6, header7, new Header(wm4, "200"), new Header(wm4, "204"), new Header(wm4, "206"), new Header(wm4, "304"), new Header(wm4, "400"), new Header(wm4, "404"), new Header(wm4, "500"), new Header("accept-charset", ""), new Header("accept-encoding", "gzip, deflate"), new Header("accept-language", ""), new Header("accept-ranges", ""), new Header("accept", ""), new Header("access-control-allow-origin", ""), new Header("age", ""), new Header("allow", ""), new Header("authorization", ""), new Header("cache-control", ""), new Header("content-disposition", ""), new Header("content-encoding", ""), new Header("content-language", "")}, 0, headerArr, 0, 27);
        System.arraycopy(new Header[]{new Header("content-length", ""), new Header("content-location", ""), new Header("content-range", ""), new Header("content-type", ""), new Header("cookie", ""), new Header("date", ""), new Header("etag", ""), new Header("expect", ""), new Header("expires", ""), new Header("from", ""), new Header("host", ""), new Header("if-match", ""), new Header("if-modified-since", ""), new Header("if-none-match", ""), new Header("if-range", ""), new Header("if-unmodified-since", ""), new Header("last-modified", ""), new Header(ServiceContract.FOLLOW_UP_LINK, ""), new Header("location", ""), new Header("max-forwards", ""), new Header("proxy-authenticate", ""), new Header("proxy-authorization", ""), new Header("range", ""), new Header("referer", ""), new Header("refresh", ""), new Header("retry-after", ""), new Header("server", "")}, 0, headerArr, 27, 27);
        System.arraycopy(new Header[]{new Header("set-cookie", ""), new Header("strict-transport-security", ""), new Header("transfer-encoding", ""), new Header("user-agent", ""), new Header("vary", ""), new Header("via", ""), new Header("www-authenticate", "")}, 0, headerArr, 54, 7);
        STATIC_HEADER_TABLE = headerArr;
    }

    public static Map<WM, Integer> nameToFirstIndex() {
        LinkedHashMap linkedHashMap = new LinkedHashMap(STATIC_HEADER_TABLE.length);
        int i = 0;
        while (true) {
            Header[] headerArr = STATIC_HEADER_TABLE;
            if (i >= headerArr.length) {
                return Collections.unmodifiableMap(linkedHashMap);
            }
            if (!linkedHashMap.containsKey(headerArr[i].name)) {
                linkedHashMap.put(headerArr[i].name, Integer.valueOf(i));
            }
            i++;
        }
    }

    public static WM checkLowercase(WM wm) throws IOException {
        int A07 = wm.A07();
        for (int i = 0; i < A07; i++) {
            byte A06 = wm.A06(i);
            if (A06 >= 65 && A06 <= 90) {
                throw new IOException(AnonymousClass06.A03("PROTOCOL_ERROR response malformed: mixed case name: ", wm.A0A()));
            }
        }
        return wm;
    }
}
