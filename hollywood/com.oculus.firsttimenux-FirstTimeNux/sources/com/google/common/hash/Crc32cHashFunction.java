package com.google.common.hash;

import com.facebook.common.procread.ProcReader;
import com.google.errorprone.annotations.Immutable;

@Immutable
final class Crc32cHashFunction extends AbstractHashFunction {
    static final HashFunction CRC_32_C = new Crc32cHashFunction();

    Crc32cHashFunction() {
    }

    @Override // com.google.common.hash.HashFunction
    public int bits() {
        return 32;
    }

    @Override // com.google.common.hash.HashFunction
    public Hasher newHasher() {
        return new Crc32cHasher();
    }

    public String toString() {
        return "Hashing.crc32c()";
    }

    static final class Crc32cHasher extends AbstractByteHasher {
        static final int[] CRC_TABLE;
        private int crc = 0;

        Crc32cHasher() {
        }

        static {
            int[] iArr = new int[ProcReader.PROC_COMBINE];
            // fill-array-data instruction
            iArr[0] = 0;
            iArr[1] = -227835133;
            iArr[2] = -516198153;
            iArr[3] = 324072436;
            iArr[4] = -946170081;
            iArr[5] = 904991772;
            iArr[6] = 648144872;
            iArr[7] = -724933397;
            iArr[8] = -1965467441;
            iArr[9] = 2024987596;
            iArr[10] = 1809983544;
            iArr[11] = -1719030981;
            iArr[12] = 1296289744;
            iArr[13] = -1087877933;
            iArr[14] = -1401372889;
            iArr[15] = 1578318884;
            iArr[16] = 274646895;
            iArr[17] = -499825556;
            iArr[18] = -244992104;
            iArr[19] = 51262619;
            iArr[20] = -675000208;
            iArr[21] = 632279923;
            iArr[22] = 922689671;
            iArr[23] = -996891772;
            iArr[24] = -1702387808;
            iArr[25] = 1760304291;
            iArr[26] = 2075979607;
            iArr[27] = -1982370732;
            iArr[28] = 1562183871;
            iArr[29] = -1351185476;
            iArr[30] = -1138329528;
            iArr[31] = 1313733451;
            iArr[32] = 549293790;
            iArr[33] = -757723683;
            iArr[34] = -1048117719;
            iArr[35] = 871202090;
            iArr[36] = -416867903;
            iArr[37] = 357341890;
            iArr[38] = 102525238;
            iArr[39] = -193467851;
            iArr[40] = -1436232175;
            iArr[41] = 1477399826;
            iArr[42] = 1264559846;
            iArr[43] = -1187764763;
            iArr[44] = 1845379342;
            iArr[45] = -1617575411;
            iArr[46] = -1933233671;
            iArr[47] = 2125378298;
            iArr[48] = 820201905;
            iArr[49] = -1031222606;
            iArr[50] = -774358714;
            iArr[51] = 598981189;
            iArr[52] = -143008082;
            iArr[53] = 85089709;
            iArr[54] = 373468761;
            iArr[55] = -467063462;
            iArr[56] = -1170599554;
            iArr[57] = 1213305469;
            iArr[58] = 1526817161;
            iArr[59] = -1452612982;
            iArr[60] = 2107672161;
            iArr[61] = -1882520222;
            iArr[62] = -1667500394;
            iArr[63] = 1861252501;
            iArr[64] = 1098587580;
            iArr[65] = -1290756417;
            iArr[66] = -1606390453;
            iArr[67] = 1378610760;
            iArr[68] = -2032039261;
            iArr[69] = 1955203488;
            iArr[70] = 1742404180;
            iArr[71] = -1783531177;
            iArr[72] = -878557837;
            iArr[73] = 969524848;
            iArr[74] = 714683780;
            iArr[75] = -655182201;
            iArr[76] = 205050476;
            iArr[77] = -28094097;
            iArr[78] = -318528869;
            iArr[79] = 526918040;
            iArr[80] = 1361435347;
            iArr[81] = -1555146288;
            iArr[82] = -1340167644;
            iArr[83] = 1114974503;
            iArr[84] = -1765847604;
            iArr[85] = 1691668175;
            iArr[86] = 2005155131;
            iArr[87] = -2047885768;
            iArr[88] = -604208612;
            iArr[89] = 697762079;
            iArr[90] = 986182379;
            iArr[91] = -928222744;
            iArr[92] = 476452099;
            iArr[93] = -301099520;
            iArr[94] = -44210700;
            iArr[95] = 255256311;
            iArr[96] = 1640403810;
            iArr[97] = -1817374623;
            iArr[98] = -2130844779;
            iArr[99] = 1922457750;
            iArr[100] = -1503918979;
            iArr[101] = 1412925310;
            iArr[102] = 1197962378;
            iArr[103] = -1257441399;
            iArr[104] = -350237779;
            iArr[105] = 427051182;
            iArr[106] = 170179418;
            iArr[107] = -129025959;
            iArr[108] = 746937522;
            iArr[109] = -554770511;
            iArr[110] = -843174843;
            iArr[111] = 1070968646;
            iArr[112] = 1905808397;
            iArr[113] = -2081171698;
            iArr[114] = -1868356358;
            iArr[115] = 1657317369;
            iArr[116] = -1241332974;
            iArr[117] = 1147748369;
            iArr[118] = 1463399397;
            iArr[119] = -1521340186;
            iArr[120] = -79622974;
            iArr[121] = 153784257;
            iArr[122] = 444234805;
            iArr[123] = -401473738;
            iArr[124] = 1021025245;
            iArr[125] = -827320098;
            iArr[126] = -572462294;
            iArr[127] = 797665321;
            iArr[128] = -2097792136;
            iArr[129] = 1889384571;
            iArr[130] = 1674398607;
            iArr[131] = -1851340660;
            iArr[132] = 1164749927;
            iArr[133] = -1224265884;
            iArr[134] = -1537745776;
            iArr[135] = 1446797203;
            iArr[136] = 137323447;
            iArr[137] = -96149324;
            iArr[138] = -384560320;
            iArr[139] = 461344835;
            iArr[140] = -810158936;
            iArr[141] = 1037989803;
            iArr[142] = 781091935;
            iArr[143] = -588970148;
            iArr[144] = -1834419177;
            iArr[145] = 1623424788;
            iArr[146] = 1939049696;
            iArr[147] = -2114449437;
            iArr[148] = 1429367560;
            iArr[149] = -1487280117;
            iArr[150] = -1274471425;
            iArr[151] = 1180866812;
            iArr[152] = 410100952;
            iArr[153] = -367384613;
            iArr[154] = -112536529;
            iArr[155] = 186734380;
            iArr[156] = -538233913;
            iArr[157] = 763408580;
            iArr[158] = 1053836080;
            iArr[159] = -860110797;
            iArr[160] = -1572096602;
            iArr[161] = 1344288421;
            iArr[162] = 1131464017;
            iArr[163] = -1323612590;
            iArr[164] = 1708204729;
            iArr[165] = -1749376582;
            iArr[166] = -2065018290;
            iArr[167] = 1988219213;
            iArr[168] = 680717673;
            iArr[169] = -621187478;
            iArr[170] = -911630946;
            iArr[171] = 1002577565;
            iArr[172] = -284657034;
            iArr[173] = 493091189;
            iArr[174] = 238226049;
            iArr[175] = -61306494;
            iArr[176] = -1307217207;
            iArr[177] = 1082061258;
            iArr[178] = 1395524158;
            iArr[179] = -1589280451;
            iArr[180] = 1972364758;
            iArr[181] = -2015074603;
            iArr[182] = -1800104671;
            iArr[183] = 1725896226;
            iArr[184] = 952904198;
            iArr[185] = -894981883;
            iArr[186] = -638100751;
            iArr[187] = 731699698;
            iArr[188] = -11092711;
            iArr[189] = 222117402;
            iArr[190] = 510512622;
            iArr[191] = -335130899;
            iArr[192] = -1014159676;
            iArr[193] = 837199303;
            iArr[194] = 582374963;
            iArr[195] = -790768336;
            iArr[196] = 68661723;
            iArr[197] = -159632680;
            iArr[198] = -450051796;
            iArr[199] = 390545967;
            iArr[200] = 1230274059;
            iArr[201] = -1153434360;
            iArr[202] = -1469116676;
            iArr[203] = 1510247935;
            iArr[204] = -1899042540;
            iArr[205] = 2091215383;
            iArr[206] = 1878366691;
            iArr[207] = -1650582816;
            iArr[208] = -741088853;
            iArr[209] = 565732008;
            iArr[210] = 854102364;
            iArr[211] = -1065151905;
            iArr[212] = 340358836;
            iArr[213] = -433916489;
            iArr[214] = -177076669;
            iArr[215] = 119113024;
            iArr[216] = 1493875044;
            iArr[217] = -1419691417;
            iArr[218] = -1204696685;
            iArr[219] = 1247431312;
            iArr[220] = -1634718085;
            iArr[221] = 1828433272;
            iArr[222] = 2141937292;
            iArr[223] = -1916740209;
            iArr[224] = -483350502;
            iArr[225] = 291187481;
            iArr[226] = 34330861;
            iArr[227] = -262120466;
            iArr[228] = 615137029;
            iArr[229] = -691946490;
            iArr[230] = -980332558;
            iArr[231] = 939183345;
            iArr[232] = 1776939221;
            iArr[233] = -1685949482;
            iArr[234] = -1999470558;
            iArr[235] = 2058945313;
            iArr[236] = -1368168502;
            iArr[237] = 1545135305;
            iArr[238] = 1330124605;
            iArr[239] = -1121741762;
            iArr[240] = -210866315;
            iArr[241] = 17165430;
            iArr[242] = 307568514;
            iArr[243] = -532767615;
            iArr[244] = 888469610;
            iArr[245] = -962626711;
            iArr[246] = -707819363;
            iArr[247] = 665062302;
            iArr[248] = 2042050490;
            iArr[249] = -1948470087;
            iArr[250] = -1735637171;
            iArr[251] = 1793573966;
            iArr[252] = -1104306011;
            iArr[253] = 1279665062;
            iArr[254] = 1595330642;
            iArr[255] = -1384295599;
            CRC_TABLE = iArr;
        }

        @Override // com.google.common.hash.AbstractByteHasher
        public void update(byte b) {
            this.crc ^= -1;
            this.crc = ((this.crc >>> 8) ^ CRC_TABLE[(this.crc ^ b) & ProcReader.PROC_TERM_MASK]) ^ -1;
        }

        @Override // com.google.common.hash.Hasher
        public HashCode hash() {
            return HashCode.fromInt(this.crc);
        }
    }
}