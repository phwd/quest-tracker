package com.facebook.oxygen.common.verification;

import android.content.pm.PackageManager;
import androidx.annotation.VisibleForTesting;
import com.facebook.common.fragmentconstants.FragmentConstants;
import com.facebook.infer.annotation.Nullsafe;
import com.facebook.oxygen.common.verification.FbSignatureVerifier;
import java.io.File;
import java.io.IOException;
import java.security.GeneralSecurityException;

@Nullsafe(Nullsafe.Mode.LOCAL)
public class OzoneSignatureVerifier {
    public static final byte[] OZONE_PUBLIC_KEY;
    public static final String SIG_ALG = "SHA256withRSA";
    private SignatureVerifiersConfig mSignatureVerifiersConfig;
    private FbSignatureVerifier mVerifier;

    static {
        byte[] bArr = new byte[FragmentConstants.ContentFragmentType.GETQUOTE_FORM_BUILDER];
        // fill-array-data instruction
        bArr[0] = 48;
        bArr[1] = -126;
        bArr[2] = 1;
        bArr[3] = 34;
        bArr[4] = 48;
        bArr[5] = 13;
        bArr[6] = 6;
        bArr[7] = 9;
        bArr[8] = 42;
        bArr[9] = -122;
        bArr[10] = 72;
        bArr[11] = -122;
        bArr[12] = -9;
        bArr[13] = 13;
        bArr[14] = 1;
        bArr[15] = 1;
        bArr[16] = 1;
        bArr[17] = 5;
        bArr[18] = 0;
        bArr[19] = 3;
        bArr[20] = -126;
        bArr[21] = 1;
        bArr[22] = 15;
        bArr[23] = 0;
        bArr[24] = 48;
        bArr[25] = -126;
        bArr[26] = 1;
        bArr[27] = 10;
        bArr[28] = 2;
        bArr[29] = -126;
        bArr[30] = 1;
        bArr[31] = 1;
        bArr[32] = 0;
        bArr[33] = -93;
        bArr[34] = -19;
        bArr[35] = -103;
        bArr[36] = 121;
        bArr[37] = 77;
        bArr[38] = 57;
        bArr[39] = 76;
        bArr[40] = -22;
        bArr[41] = 106;
        bArr[42] = -94;
        bArr[43] = -20;
        bArr[44] = -12;
        bArr[45] = -61;
        bArr[46] = 42;
        bArr[47] = -113;
        bArr[48] = -118;
        bArr[49] = 111;
        bArr[50] = -37;
        bArr[51] = 99;
        bArr[52] = 123;
        bArr[53] = -19;
        bArr[54] = -100;
        bArr[55] = 15;
        bArr[56] = Byte.MAX_VALUE;
        bArr[57] = 87;
        bArr[58] = -53;
        bArr[59] = 121;
        bArr[60] = -72;
        bArr[61] = 63;
        bArr[62] = 121;
        bArr[63] = -28;
        bArr[64] = -35;
        bArr[65] = 64;
        bArr[66] = -4;
        bArr[67] = -100;
        bArr[68] = 78;
        bArr[69] = 63;
        bArr[70] = -59;
        bArr[71] = -82;
        bArr[72] = 19;
        bArr[73] = -77;
        bArr[74] = 108;
        bArr[75] = 116;
        bArr[76] = -17;
        bArr[77] = 7;
        bArr[78] = 36;
        bArr[79] = -13;
        bArr[80] = 101;
        bArr[81] = -117;
        bArr[82] = 91;
        bArr[83] = 87;
        bArr[84] = -21;
        bArr[85] = 126;
        bArr[86] = -28;
        bArr[87] = 46;
        bArr[88] = 58;
        bArr[89] = -29;
        bArr[90] = 77;
        bArr[91] = -73;
        bArr[92] = -3;
        bArr[93] = 115;
        bArr[94] = -116;
        bArr[95] = -54;
        bArr[96] = 54;
        bArr[97] = 88;
        bArr[98] = -41;
        bArr[99] = -29;
        bArr[100] = -119;
        bArr[101] = 116;
        bArr[102] = 92;
        bArr[103] = 80;
        bArr[104] = -89;
        bArr[105] = 60;
        bArr[106] = -80;
        bArr[107] = -98;
        bArr[108] = 42;
        bArr[109] = -22;
        bArr[110] = 6;
        bArr[111] = -53;
        bArr[112] = -33;
        bArr[113] = -46;
        bArr[114] = -93;
        bArr[115] = -64;
        bArr[116] = 71;
        bArr[117] = -28;
        bArr[118] = 117;
        bArr[119] = -113;
        bArr[120] = 73;
        bArr[121] = 93;
        bArr[122] = 119;
        bArr[123] = -111;
        bArr[124] = -50;
        bArr[125] = -122;
        bArr[126] = 78;
        bArr[127] = 47;
        bArr[128] = -32;
        bArr[129] = -99;
        bArr[130] = 86;
        bArr[131] = -45;
        bArr[132] = 110;
        bArr[133] = -47;
        bArr[134] = -88;
        bArr[135] = Byte.MIN_VALUE;
        bArr[136] = 60;
        bArr[137] = -75;
        bArr[138] = 5;
        bArr[139] = 81;
        bArr[140] = 18;
        bArr[141] = -67;
        bArr[142] = 58;
        bArr[143] = -47;
        bArr[144] = 124;
        bArr[145] = 122;
        bArr[146] = 104;
        bArr[147] = 125;
        bArr[148] = -12;
        bArr[149] = 37;
        bArr[150] = 38;
        bArr[151] = 71;
        bArr[152] = 16;
        bArr[153] = 120;
        bArr[154] = -72;
        bArr[155] = 119;
        bArr[156] = -112;
        bArr[157] = 27;
        bArr[158] = 110;
        bArr[159] = 38;
        bArr[160] = 18;
        bArr[161] = -30;
        bArr[162] = 30;
        bArr[163] = 25;
        bArr[164] = -1;
        bArr[165] = 29;
        bArr[166] = 120;
        bArr[167] = 35;
        bArr[168] = -19;
        bArr[169] = 98;
        bArr[170] = -78;
        bArr[171] = 37;
        bArr[172] = -105;
        bArr[173] = -121;
        bArr[174] = 83;
        bArr[175] = -18;
        bArr[176] = -11;
        bArr[177] = -88;
        bArr[178] = -26;
        bArr[179] = 90;
        bArr[180] = 60;
        bArr[181] = -107;
        bArr[182] = -48;
        bArr[183] = 92;
        bArr[184] = 88;
        bArr[185] = -21;
        bArr[186] = -85;
        bArr[187] = 11;
        bArr[188] = -51;
        bArr[189] = 23;
        bArr[190] = -117;
        bArr[191] = 25;
        bArr[192] = -67;
        bArr[193] = 92;
        bArr[194] = 79;
        bArr[195] = 75;
        bArr[196] = 22;
        bArr[197] = -127;
        bArr[198] = 75;
        bArr[199] = 36;
        bArr[200] = -119;
        bArr[201] = -9;
        bArr[202] = 64;
        bArr[203] = 106;
        bArr[204] = 101;
        bArr[205] = -11;
        bArr[206] = -116;
        bArr[207] = -32;
        bArr[208] = -101;
        bArr[209] = -70;
        bArr[210] = 110;
        bArr[211] = -23;
        bArr[212] = -33;
        bArr[213] = 96;
        bArr[214] = 102;
        bArr[215] = -16;
        bArr[216] = -73;
        bArr[217] = -45;
        bArr[218] = 53;
        bArr[219] = -76;
        bArr[220] = 72;
        bArr[221] = 75;
        bArr[222] = 5;
        bArr[223] = 32;
        bArr[224] = -23;
        bArr[225] = 104;
        bArr[226] = 45;
        bArr[227] = -61;
        bArr[228] = -103;
        bArr[229] = -122;
        bArr[230] = 122;
        bArr[231] = -53;
        bArr[232] = 106;
        bArr[233] = 76;
        bArr[234] = 40;
        bArr[235] = 119;
        bArr[236] = -105;
        bArr[237] = 20;
        bArr[238] = -32;
        bArr[239] = 82;
        bArr[240] = 102;
        bArr[241] = 25;
        bArr[242] = 45;
        bArr[243] = -74;
        bArr[244] = -37;
        bArr[245] = -49;
        bArr[246] = -76;
        bArr[247] = -119;
        bArr[248] = 89;
        bArr[249] = -14;
        bArr[250] = 118;
        bArr[251] = 122;
        bArr[252] = 49;
        bArr[253] = 3;
        bArr[254] = 62;
        bArr[255] = -19;
        bArr[256] = -27;
        bArr[257] = -105;
        bArr[258] = 34;
        bArr[259] = -116;
        bArr[260] = -123;
        bArr[261] = -102;
        bArr[262] = 32;
        bArr[263] = 13;
        bArr[264] = 116;
        bArr[265] = -7;
        bArr[266] = 39;
        bArr[267] = 119;
        bArr[268] = 19;
        bArr[269] = 2;
        bArr[270] = -57;
        bArr[271] = 85;
        bArr[272] = -105;
        bArr[273] = -37;
        bArr[274] = -23;
        bArr[275] = -49;
        bArr[276] = 74;
        bArr[277] = 88;
        bArr[278] = 5;
        bArr[279] = 104;
        bArr[280] = 76;
        bArr[281] = 27;
        bArr[282] = -116;
        bArr[283] = -45;
        bArr[284] = -83;
        bArr[285] = -102;
        bArr[286] = 13;
        bArr[287] = 62;
        bArr[288] = -119;
        bArr[289] = 2;
        bArr[290] = 3;
        bArr[291] = 1;
        bArr[292] = 0;
        bArr[293] = 1;
        OZONE_PUBLIC_KEY = bArr;
    }

    public OzoneSignatureVerifier(PackageManager packageManager, SignatureVerifiersConfig signatureVerifiersConfig) {
        this.mVerifier = new FbSignatureVerifier(packageManager, new FbOzoneSigVerifierKillSwitchProvider(), OZONE_PUBLIC_KEY, "SHA256withRSA");
        this.mSignatureVerifiersConfig = signatureVerifiersConfig;
    }

    public boolean isKillSwitchEnabled() {
        return this.mSignatureVerifiersConfig.isOzoneKillSwitchEnabled();
    }

    public ExtSigVerificationResult verifyOzoneSignature(File file, byte[] bArr) throws IOException, GeneralSecurityException {
        return this.mVerifier.verifySignature(file, bArr);
    }

    public void enforceOzoneSignature(File file, byte[] bArr) throws IOException, GeneralSecurityException, SecurityException {
        this.mVerifier.enforceSignature(file, bArr);
    }

    /* access modifiers changed from: package-private */
    @VisibleForTesting
    public FbSignatureVerifier getVerifier() {
        return this.mVerifier;
    }

    private class FbOzoneSigVerifierKillSwitchProvider implements FbSignatureVerifier.FbSigVerifierKillSwitchProvider {
        private FbOzoneSigVerifierKillSwitchProvider() {
        }

        @Override // com.facebook.oxygen.common.verification.FbSignatureVerifier.FbSigVerifierKillSwitchProvider
        public boolean isKillSwitchEnabled() {
            return OzoneSignatureVerifier.this.isKillSwitchEnabled();
        }
    }
}
