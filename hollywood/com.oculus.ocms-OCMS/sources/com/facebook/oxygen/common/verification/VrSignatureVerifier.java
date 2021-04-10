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
public class VrSignatureVerifier {
    public static final byte[] OCULUS_VR_PUBLIC_KEY;
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
        bArr[33] = -97;
        bArr[34] = 111;
        bArr[35] = -47;
        bArr[36] = -6;
        bArr[37] = 0;
        bArr[38] = 102;
        bArr[39] = -81;
        bArr[40] = 93;
        bArr[41] = 121;
        bArr[42] = 98;
        bArr[43] = 49;
        bArr[44] = -32;
        bArr[45] = 82;
        bArr[46] = -100;
        bArr[47] = -25;
        bArr[48] = -116;
        bArr[49] = -106;
        bArr[50] = 71;
        bArr[51] = 65;
        bArr[52] = 116;
        bArr[53] = -56;
        bArr[54] = -115;
        bArr[55] = -42;
        bArr[56] = -127;
        bArr[57] = 58;
        bArr[58] = -105;
        bArr[59] = 49;
        bArr[60] = 0;
        bArr[61] = -53;
        bArr[62] = -62;
        bArr[63] = 73;
        bArr[64] = 103;
        bArr[65] = -42;
        bArr[66] = 95;
        bArr[67] = -119;
        bArr[68] = 101;
        bArr[69] = 95;
        bArr[70] = -40;
        bArr[71] = 28;
        bArr[72] = 89;
        bArr[73] = -39;
        bArr[74] = 92;
        bArr[75] = -11;
        bArr[76] = -59;
        bArr[77] = -23;
        bArr[78] = -102;
        bArr[79] = -28;
        bArr[80] = 81;
        bArr[81] = 93;
        bArr[82] = -60;
        bArr[83] = 69;
        bArr[84] = -114;
        bArr[85] = -57;
        bArr[86] = 78;
        bArr[87] = -29;
        bArr[88] = 34;
        bArr[89] = 59;
        bArr[90] = 11;
        bArr[91] = 23;
        bArr[92] = 35;
        bArr[93] = -85;
        bArr[94] = 64;
        bArr[95] = -81;
        bArr[96] = -98;
        bArr[97] = -75;
        bArr[98] = 77;
        bArr[99] = 30;
        bArr[100] = 121;
        bArr[101] = -8;
        bArr[102] = 0;
        bArr[103] = 33;
        bArr[104] = Byte.MIN_VALUE;
        bArr[105] = 38;
        bArr[106] = 4;
        bArr[107] = -99;
        bArr[108] = 113;
        bArr[109] = -106;
        bArr[110] = 96;
        bArr[111] = -106;
        bArr[112] = -96;
        bArr[113] = 87;
        bArr[114] = Byte.MAX_VALUE;
        bArr[115] = 76;
        bArr[116] = -15;
        bArr[117] = -31;
        bArr[118] = -121;
        bArr[119] = -34;
        bArr[120] = 81;
        bArr[121] = -86;
        bArr[122] = 72;
        bArr[123] = 12;
        bArr[124] = -127;
        bArr[125] = -58;
        bArr[126] = 47;
        bArr[127] = -92;
        bArr[128] = -121;
        bArr[129] = 122;
        bArr[130] = 126;
        bArr[131] = -44;
        bArr[132] = -42;
        bArr[133] = 21;
        bArr[134] = 1;
        bArr[135] = 0;
        bArr[136] = 124;
        bArr[137] = -81;
        bArr[138] = -56;
        bArr[139] = 98;
        bArr[140] = 115;
        bArr[141] = 124;
        bArr[142] = 113;
        bArr[143] = 99;
        bArr[144] = -11;
        bArr[145] = 72;
        bArr[146] = -104;
        bArr[147] = -94;
        bArr[148] = -50;
        bArr[149] = 1;
        bArr[150] = -51;
        bArr[151] = 14;
        bArr[152] = -112;
        bArr[153] = -65;
        bArr[154] = -16;
        bArr[155] = 30;
        bArr[156] = 80;
        bArr[157] = -4;
        bArr[158] = 115;
        bArr[159] = 94;
        bArr[160] = -73;
        bArr[161] = 64;
        bArr[162] = 53;
        bArr[163] = 46;
        bArr[164] = 61;
        bArr[165] = 5;
        bArr[166] = -23;
        bArr[167] = -76;
        bArr[168] = Byte.MAX_VALUE;
        bArr[169] = 47;
        bArr[170] = -114;
        bArr[171] = 30;
        bArr[172] = -6;
        bArr[173] = 1;
        bArr[174] = -28;
        bArr[175] = 36;
        bArr[176] = 74;
        bArr[177] = Byte.MAX_VALUE;
        bArr[178] = -108;
        bArr[179] = -77;
        bArr[180] = -30;
        bArr[181] = 124;
        bArr[182] = -22;
        bArr[183] = 54;
        bArr[184] = 28;
        bArr[185] = -38;
        bArr[186] = 5;
        bArr[187] = 48;
        bArr[188] = -27;
        bArr[189] = 45;
        bArr[190] = -98;
        bArr[191] = 125;
        bArr[192] = -64;
        bArr[193] = -50;
        bArr[194] = 88;
        bArr[195] = -118;
        bArr[196] = 71;
        bArr[197] = 88;
        bArr[198] = -7;
        bArr[199] = 15;
        bArr[200] = -13;
        bArr[201] = -122;
        bArr[202] = -28;
        bArr[203] = 61;
        bArr[204] = 65;
        bArr[205] = -40;
        bArr[206] = -42;
        bArr[207] = -11;
        bArr[208] = -93;
        bArr[209] = 89;
        bArr[210] = 20;
        bArr[211] = 19;
        bArr[212] = -38;
        bArr[213] = -60;
        bArr[214] = -78;
        bArr[215] = -115;
        bArr[216] = 94;
        bArr[217] = 7;
        bArr[218] = Byte.MIN_VALUE;
        bArr[219] = 88;
        bArr[220] = -16;
        bArr[221] = 28;
        bArr[222] = -82;
        bArr[223] = -87;
        bArr[224] = 41;
        bArr[225] = -99;
        bArr[226] = -66;
        bArr[227] = 96;
        bArr[228] = Byte.MIN_VALUE;
        bArr[229] = 39;
        bArr[230] = 3;
        bArr[231] = -106;
        bArr[232] = -110;
        bArr[233] = 11;
        bArr[234] = 58;
        bArr[235] = 89;
        bArr[236] = -62;
        bArr[237] = 22;
        bArr[238] = -24;
        bArr[239] = -58;
        bArr[240] = 118;
        bArr[241] = 55;
        bArr[242] = -21;
        bArr[243] = -27;
        bArr[244] = -42;
        bArr[245] = 104;
        bArr[246] = 92;
        bArr[247] = -87;
        bArr[248] = -102;
        bArr[249] = 96;
        bArr[250] = 116;
        bArr[251] = 92;
        bArr[252] = -16;
        bArr[253] = -114;
        bArr[254] = 15;
        bArr[255] = 122;
        bArr[256] = -30;
        bArr[257] = -7;
        bArr[258] = 76;
        bArr[259] = -89;
        bArr[260] = -20;
        bArr[261] = 4;
        bArr[262] = 14;
        bArr[263] = -55;
        bArr[264] = 110;
        bArr[265] = -40;
        bArr[266] = 55;
        bArr[267] = 78;
        bArr[268] = 123;
        bArr[269] = -7;
        bArr[270] = 64;
        bArr[271] = -20;
        bArr[272] = -69;
        bArr[273] = Byte.MAX_VALUE;
        bArr[274] = 102;
        bArr[275] = -65;
        bArr[276] = -100;
        bArr[277] = -63;
        bArr[278] = -103;
        bArr[279] = -78;
        bArr[280] = 77;
        bArr[281] = -31;
        bArr[282] = -77;
        bArr[283] = 53;
        bArr[284] = -26;
        bArr[285] = -87;
        bArr[286] = 20;
        bArr[287] = 58;
        bArr[288] = -61;
        bArr[289] = 2;
        bArr[290] = 3;
        bArr[291] = 1;
        bArr[292] = 0;
        bArr[293] = 1;
        OCULUS_VR_PUBLIC_KEY = bArr;
    }

    public VrSignatureVerifier(PackageManager packageManager, SignatureVerifiersConfig signatureVerifiersConfig) {
        this.mVerifier = new FbSignatureVerifier(packageManager, new FbOculusVrSigVerifierKillSwitchProvider(), OCULUS_VR_PUBLIC_KEY, "SHA256withRSA");
        this.mSignatureVerifiersConfig = signatureVerifiersConfig;
    }

    public boolean isKillSwitchEnabled() {
        return this.mSignatureVerifiersConfig.isOculusVrKillSwitchEnabled();
    }

    public ExtSigVerificationResult verifyVrSignature(File file) throws IOException, GeneralSecurityException {
        return this.mVerifier.verifySignature(file, null);
    }

    public void enforceVrSignature(File file) throws IOException, GeneralSecurityException, SecurityException {
        this.mVerifier.enforceSignature(file, null);
    }

    /* access modifiers changed from: package-private */
    @VisibleForTesting
    public FbSignatureVerifier getVerifier() {
        return this.mVerifier;
    }

    private class FbOculusVrSigVerifierKillSwitchProvider implements FbSignatureVerifier.FbSigVerifierKillSwitchProvider {
        private FbOculusVrSigVerifierKillSwitchProvider() {
        }

        @Override // com.facebook.oxygen.common.verification.FbSignatureVerifier.FbSigVerifierKillSwitchProvider
        public boolean isKillSwitchEnabled() {
            return VrSignatureVerifier.this.isKillSwitchEnabled();
        }
    }
}
