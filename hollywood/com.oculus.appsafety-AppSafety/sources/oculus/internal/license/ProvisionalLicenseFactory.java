package oculus.internal.license;

import com.google.gson.Gson;
import com.oculus.license.Category;
import com.oculus.license.FilterClass;
import com.oculus.license.FilterConfig;
import com.oculus.license.PackageFilter;
import com.oculus.license.Rule;
import com.oculus.license.SecurityPrincipal;
import com.oculus.license.Signer;
import com.oculus.os.PackageMetadata;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.OutputStreamWriter;
import java.nio.ByteBuffer;
import java.nio.ByteOrder;
import java.nio.charset.StandardCharsets;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.security.SignatureException;
import java.security.cert.Certificate;
import java.security.cert.X509Certificate;
import java.util.Arrays;
import java.util.List;
import java.util.Map;
import java.util.Random;
import java.util.function.Supplier;
import java.util.stream.Collectors;
import oculus.internal.BuildCompat;
import oculus.internal.license.parsing.CodableLicense;
import oculus.internal.license.parsing.LicenseParserImpl;
import oculus.internal.license.signing.LicenseSignature;
import oculus.internal.license.signing.LicenseSigner;
import org.json.JSONException;
import org.json.JSONObject;

public class ProvisionalLicenseFactory {
    Supplier<Long> fbidGenerator;
    Gson gson = LicenseParserImpl.gson();
    LicenseSigner licenseSigner;

    public static ProvisionalLicenseFactory create(LicenseSigner licenseSigner2) {
        return new ProvisionalLicenseFactory(licenseSigner2, new Supplier() {
            /* class oculus.internal.license.$$Lambda$ProvisionalLicenseFactory$MtCrYgLmD73nURPTerwMA8KAjLI */

            @Override // java.util.function.Supplier
            public final Object get() {
                return Long.valueOf(Long.MIN_VALUE | Random.this.nextLong());
            }
        });
    }

    public ProvisionalLicenseFactory(LicenseSigner licenseSigner2, Supplier<Long> fbidGenerator2) {
        this.licenseSigner = licenseSigner2;
        this.fbidGenerator = fbidGenerator2;
    }

    public byte[] generate(PackageMetadata packageMetadata, String requester) throws IOException, SignatureException {
        byte[] encoded = encodeLicense(formatLicense(packageMetadata, requester));
        return formatBlob(encoded, this.licenseSigner.sign(encoded));
    }

    /* access modifiers changed from: package-private */
    public CodableLicense formatLicense(PackageMetadata packageMetadata, String requester) {
        LicenseBuilder id = new LicenseBuilder().setId(this.fbidGenerator.get().longValue());
        return CodableLicense.fromLicense(id.setComment("Provisional license for " + packageMetadata.packageIdentifier).setIssuer(getDeviceIssuer()).setIssued(System.currentTimeMillis() / 1000).setExpires(0).setRequester(requester).setCategory(Category.Provisional).setPackageFilters(formatPackageFilters(packageMetadata)).setRules(formatRules()).buildAndValidate());
    }

    private String getDeviceIssuer() {
        Certificate certificate = this.licenseSigner.getCertificate();
        if (certificate instanceof X509Certificate) {
            return ((X509Certificate) certificate).getSubjectX500Principal().getName();
        }
        return new BuildCompat().getSerial();
    }

    private byte[] getDeviceCertificateFingerprint() {
        try {
            return MessageDigest.getInstance("MD5").digest(this.licenseSigner.getCertificate().getPublicKey().getEncoded());
        } catch (NoSuchAlgorithmException e) {
            throw new RuntimeException("Unable to compute certificate fingerprint", e);
        }
    }

    private List<PackageFilter> formatPackageFilters(PackageMetadata packageMetadata) {
        Map<Boolean, List<PackageMetadata.Signature>> signaturesByScheme = (Map) Arrays.stream(packageMetadata.signatures).collect(Collectors.partitioningBy($$Lambda$ProvisionalLicenseFactory$mFqonkH_byHXV8RfVvcJX1wvAkE.INSTANCE));
        List<PackageMetadata.Signature> candidates = signaturesByScheme.get(Boolean.FALSE);
        if (candidates == null || candidates.size() == 0) {
            candidates = signaturesByScheme.get(Boolean.TRUE);
        }
        if (candidates == null || candidates.size() == 0) {
            throw new IllegalArgumentException("package metadata contains no signatures");
        }
        PackageMetadata.Signature chosen = candidates.get(0);
        Signer.Digest digest = new Signer.Digest(PackageMetadata.Signature.Algorithm.extractDigestName(chosen.algorithmName), chosen.digest);
        Signer signer = new Signer(chosen.certificate, Arrays.asList(digest));
        return Arrays.asList(new PackageFilter(packageMetadata.packageIdentifier, Arrays.asList(signer)));
    }

    static /* synthetic */ boolean lambda$formatPackageFilters$1(PackageMetadata.Signature s) {
        return s.scheme.value < 2;
    }

    private List<Rule> formatRules() {
        List<String> capabilities = Arrays.asList("launch");
        try {
            return Arrays.asList(new Rule(Rule.Disposition.grant_or_skip, capabilities, Arrays.asList(new FilterConfig(FilterClass.SecurityPrincipalFilter, new JSONObject(String.format("{\"security_principals\": [\"%s\"], \"logical_test\": \"all\"}", SecurityPrincipal.getDeviceSerialSecurityPrincipal()))))));
        } catch (JSONException e) {
            throw new RuntimeException("Error formatting license json", e);
        }
    }

    private byte[] encodeLicense(CodableLicense license) throws IOException {
        ByteArrayOutputStream out = new ByteArrayOutputStream();
        OutputStreamWriter writer = new OutputStreamWriter(out, StandardCharsets.UTF_8);
        this.gson.toJson(license, writer);
        writer.flush();
        return out.toByteArray();
    }

    private byte[] formatBlob(byte[] license, LicenseSignature signature) {
        ByteBuffer bb = ByteBuffer.allocate(signature.signature.length + 24 + 4 + license.length);
        return bb.order(ByteOrder.BIG_ENDIAN).put(new byte[]{79, 67, 76, 83}).putShort(1).putShort(signature.scheme.value).put(getDeviceCertificateFingerprint()).put(signature.signature).putInt(license.length).put(license).array();
    }
}
