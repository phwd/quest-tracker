package com.android.org.bouncycastle.asn1;

import java.io.IOException;

public class BERTaggedObjectParser implements ASN1TaggedObjectParser {
    private boolean _constructed;
    private ASN1StreamParser _parser;
    private int _tagNumber;

    BERTaggedObjectParser(boolean constructed, int tagNumber, ASN1StreamParser parser) {
        this._constructed = constructed;
        this._tagNumber = tagNumber;
        this._parser = parser;
    }

    public boolean isConstructed() {
        return this._constructed;
    }

    @Override // com.android.org.bouncycastle.asn1.ASN1TaggedObjectParser
    public int getTagNo() {
        return this._tagNumber;
    }

    @Override // com.android.org.bouncycastle.asn1.ASN1TaggedObjectParser
    public ASN1Encodable getObjectParser(int tag, boolean isExplicit) throws IOException {
        if (!isExplicit) {
            return this._parser.readImplicit(this._constructed, tag);
        }
        if (this._constructed) {
            return this._parser.readObject();
        }
        throw new IOException("Explicit tags must be constructed (see X.690 8.14.2)");
    }

    @Override // com.android.org.bouncycastle.asn1.InMemoryRepresentable
    public ASN1Primitive getLoadedObject() throws IOException {
        return this._parser.readTaggedObject(this._constructed, this._tagNumber);
    }

    @Override // com.android.org.bouncycastle.asn1.ASN1Encodable
    public ASN1Primitive toASN1Primitive() {
        try {
            return getLoadedObject();
        } catch (IOException e) {
            throw new ASN1ParsingException(e.getMessage());
        }
    }
}
