package com.android.org.bouncycastle.math.field;

import com.android.org.bouncycastle.util.Integers;
import java.math.BigInteger;

/* access modifiers changed from: package-private */
public class GenericPolynomialExtensionField implements PolynomialExtensionField {
    protected final Polynomial minimalPolynomial;
    protected final FiniteField subfield;

    GenericPolynomialExtensionField(FiniteField subfield2, Polynomial polynomial) {
        this.subfield = subfield2;
        this.minimalPolynomial = polynomial;
    }

    @Override // com.android.org.bouncycastle.math.field.FiniteField
    public BigInteger getCharacteristic() {
        return this.subfield.getCharacteristic();
    }

    @Override // com.android.org.bouncycastle.math.field.FiniteField
    public int getDimension() {
        return this.subfield.getDimension() * this.minimalPolynomial.getDegree();
    }

    @Override // com.android.org.bouncycastle.math.field.ExtensionField
    public FiniteField getSubfield() {
        return this.subfield;
    }

    @Override // com.android.org.bouncycastle.math.field.ExtensionField
    public int getDegree() {
        return this.minimalPolynomial.getDegree();
    }

    @Override // com.android.org.bouncycastle.math.field.PolynomialExtensionField
    public Polynomial getMinimalPolynomial() {
        return this.minimalPolynomial;
    }

    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (!(obj instanceof GenericPolynomialExtensionField)) {
            return false;
        }
        GenericPolynomialExtensionField other = (GenericPolynomialExtensionField) obj;
        if (!this.subfield.equals(other.subfield) || !this.minimalPolynomial.equals(other.minimalPolynomial)) {
            return false;
        }
        return true;
    }

    public int hashCode() {
        return this.subfield.hashCode() ^ Integers.rotateLeft(this.minimalPolynomial.hashCode(), 16);
    }
}
