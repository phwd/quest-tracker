package oculus.internal.gson;

import com.google.gson.JsonDeserializationContext;
import com.google.gson.JsonDeserializer;
import com.google.gson.JsonElement;
import com.google.gson.JsonParseException;
import com.google.gson.JsonPrimitive;
import com.google.gson.JsonSerializationContext;
import com.google.gson.JsonSerializer;
import java.io.ByteArrayInputStream;
import java.lang.reflect.Type;
import java.security.cert.Certificate;
import java.security.cert.CertificateEncodingException;
import java.security.cert.CertificateException;
import java.security.cert.CertificateFactory;

public final class CertificateAdapter implements JsonDeserializer<Certificate>, JsonSerializer<Certificate> {
    private static final String CERT_FOOTER = "-----END CERTIFICATE-----";
    private static final String CERT_HEADER = "-----BEGIN CERTIFICATE-----";
    private static final String INVALID_FORMAT = "Invalid certificate format.";
    private boolean encodePem;
    private boolean strictPemDecode;

    public CertificateAdapter(boolean strictPemDecode2, boolean encodePem2) {
        this.strictPemDecode = strictPemDecode2;
        this.encodePem = encodePem2;
    }

    public CertificateAdapter() {
        this(true, true);
    }

    @Override // com.google.gson.JsonDeserializer
    public Certificate deserialize(JsonElement json, Type typeOfT, JsonDeserializationContext context) {
        String pemString = json.getAsString().trim();
        boolean startsWithHeader = pemString.startsWith(CERT_HEADER);
        boolean endsWithFooter = pemString.endsWith(CERT_FOOTER);
        if (!this.strictPemDecode || (startsWithHeader && endsWithFooter)) {
            int length = startsWithHeader ? CERT_HEADER.length() : 0;
            int length2 = pemString.length();
            if (endsWithFooter) {
                length2 -= CERT_FOOTER.length();
            }
            try {
                return CertificateFactory.getInstance("X509").generateCertificate(new ByteArrayInputStream(Base64EncodedData.toBytes(pemString.substring(length, length2).trim())));
            } catch (CertificateException e) {
                throw new JsonParseException(INVALID_FORMAT, e);
            }
        } else {
            throw new JsonParseException(INVALID_FORMAT);
        }
    }

    public JsonElement serialize(Certificate certificate, Type typeOfT, JsonSerializationContext context) {
        String output;
        try {
            byte[] encoded = certificate.getEncoded();
            if (this.encodePem) {
                output = String.format("%s\n%s\n%s", CERT_HEADER, Base64EncodedData.getEncodedString(encoded, 0), CERT_FOOTER);
            } else {
                output = Base64EncodedData.getEncodedString(encoded, 2);
            }
            return new JsonPrimitive(output);
        } catch (CertificateEncodingException e) {
            throw new RuntimeException("could not encode certificate", e);
        }
    }
}
