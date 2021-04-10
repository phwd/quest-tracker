package libcore.reflect;

import java.lang.reflect.Constructor;
import java.lang.reflect.GenericDeclaration;
import java.lang.reflect.GenericSignatureFormatError;
import java.lang.reflect.Method;
import java.lang.reflect.Type;
import java.lang.reflect.TypeVariable;
import libcore.util.EmptyArray;

public final class GenericSignatureParser {
    char[] buffer;
    private boolean eof;
    public ListOfTypes exceptionTypes;
    public TypeVariable[] formalTypeParameters;
    GenericDeclaration genericDecl;
    String identifier;
    public ListOfTypes interfaceTypes;
    public ClassLoader loader;
    public ListOfTypes parameterTypes;
    int pos;
    public Type returnType;
    public Type superclassType;
    char symbol;

    static boolean isStopSymbol(char c) {
        if (c == '.' || c == '/') {
            return true;
        }
        switch (c) {
            case ':':
            case ';':
            case '<':
                return true;
            default:
                return false;
        }
    }

    public GenericSignatureParser(ClassLoader classLoader) {
        this.loader = classLoader;
    }

    /* access modifiers changed from: package-private */
    public void setInput(GenericDeclaration genericDeclaration, String str) {
        if (str != null) {
            this.genericDecl = genericDeclaration;
            this.buffer = str.toCharArray();
            this.eof = false;
            scanSymbol();
            return;
        }
        this.eof = true;
    }

    public void parseForClass(GenericDeclaration genericDeclaration, String str) {
        setInput(genericDeclaration, str);
        if (!this.eof) {
            parseClassSignature();
        } else if (genericDeclaration instanceof Class) {
            Class cls = (Class) genericDeclaration;
            this.formalTypeParameters = EmptyArray.TYPE_VARIABLE;
            this.superclassType = cls.getSuperclass();
            Class[] interfaces = cls.getInterfaces();
            if (interfaces.length == 0) {
                this.interfaceTypes = ListOfTypes.EMPTY;
            } else {
                this.interfaceTypes = new ListOfTypes(interfaces);
            }
        } else {
            this.formalTypeParameters = EmptyArray.TYPE_VARIABLE;
            this.superclassType = Object.class;
            this.interfaceTypes = ListOfTypes.EMPTY;
        }
    }

    public void parseForMethod(GenericDeclaration genericDeclaration, String str, Class[] clsArr) {
        setInput(genericDeclaration, str);
        if (!this.eof) {
            parseMethodTypeSignature(clsArr);
            return;
        }
        Method method = (Method) genericDeclaration;
        this.formalTypeParameters = EmptyArray.TYPE_VARIABLE;
        Class[] parameterTypes2 = method.getParameterTypes();
        if (parameterTypes2.length == 0) {
            this.parameterTypes = ListOfTypes.EMPTY;
        } else {
            this.parameterTypes = new ListOfTypes(parameterTypes2);
        }
        Class[] exceptionTypes2 = method.getExceptionTypes();
        if (exceptionTypes2.length == 0) {
            this.exceptionTypes = ListOfTypes.EMPTY;
        } else {
            this.exceptionTypes = new ListOfTypes(exceptionTypes2);
        }
        this.returnType = method.getReturnType();
    }

    public void parseForConstructor(GenericDeclaration genericDeclaration, String str, Class[] clsArr) {
        setInput(genericDeclaration, str);
        if (!this.eof) {
            parseMethodTypeSignature(clsArr);
            return;
        }
        Constructor constructor = (Constructor) genericDeclaration;
        this.formalTypeParameters = EmptyArray.TYPE_VARIABLE;
        Class[] parameterTypes2 = constructor.getParameterTypes();
        if (parameterTypes2.length == 0) {
            this.parameterTypes = ListOfTypes.EMPTY;
        } else {
            this.parameterTypes = new ListOfTypes(parameterTypes2);
        }
        Class[] exceptionTypes2 = constructor.getExceptionTypes();
        if (exceptionTypes2.length == 0) {
            this.exceptionTypes = ListOfTypes.EMPTY;
        } else {
            this.exceptionTypes = new ListOfTypes(exceptionTypes2);
        }
    }

    /* access modifiers changed from: package-private */
    public void parseClassSignature() {
        parseOptFormalTypeParameters();
        this.superclassType = parseClassTypeSignature();
        this.interfaceTypes = new ListOfTypes(16);
        while (this.symbol > 0) {
            this.interfaceTypes.add(parseClassTypeSignature());
        }
    }

    /* access modifiers changed from: package-private */
    public void parseOptFormalTypeParameters() {
        ListOfVariables listOfVariables = new ListOfVariables();
        if (this.symbol == '<') {
            scanSymbol();
            listOfVariables.add(parseFormalTypeParameter());
            while (true) {
                char c = this.symbol;
                if (c == '>' || c <= 0) {
                    expect('>');
                } else {
                    listOfVariables.add(parseFormalTypeParameter());
                }
            }
            expect('>');
        }
        this.formalTypeParameters = listOfVariables.getArray();
    }

    /* access modifiers changed from: package-private */
    public TypeVariableImpl parseFormalTypeParameter() {
        scanIdentifier();
        String intern = this.identifier.intern();
        ListOfTypes listOfTypes = new ListOfTypes(8);
        expect(':');
        char c = this.symbol;
        if (c == 'L' || c == '[' || c == 'T') {
            listOfTypes.add(parseFieldTypeSignature());
        }
        while (this.symbol == ':') {
            scanSymbol();
            listOfTypes.add(parseFieldTypeSignature());
        }
        return new TypeVariableImpl(this.genericDecl, intern, listOfTypes);
    }

    /* access modifiers changed from: package-private */
    public Type parseFieldTypeSignature() {
        char c = this.symbol;
        if (c == 'L') {
            return parseClassTypeSignature();
        }
        if (c == 'T') {
            return parseTypeVariableSignature();
        }
        if (c == '[') {
            scanSymbol();
            return new GenericArrayTypeImpl(parseTypeSignature());
        }
        throw new GenericSignatureFormatError();
    }

    /* access modifiers changed from: package-private */
    public Type parseClassTypeSignature() {
        expect('L');
        StringBuilder sb = new StringBuilder();
        scanIdentifier();
        while (this.symbol == '/') {
            scanSymbol();
            sb.append(this.identifier);
            sb.append(".");
            scanIdentifier();
        }
        sb.append(this.identifier);
        ParameterizedTypeImpl parameterizedTypeImpl = new ParameterizedTypeImpl(null, sb.toString(), parseOptTypeArguments(), this.loader);
        ParameterizedTypeImpl parameterizedTypeImpl2 = parameterizedTypeImpl;
        while (this.symbol == '.') {
            scanSymbol();
            scanIdentifier();
            sb.append("$");
            sb.append(this.identifier);
            parameterizedTypeImpl2 = new ParameterizedTypeImpl(parameterizedTypeImpl, sb.toString(), parseOptTypeArguments(), this.loader);
        }
        expect(';');
        return parameterizedTypeImpl2;
    }

    /* access modifiers changed from: package-private */
    public ListOfTypes parseOptTypeArguments() {
        ListOfTypes listOfTypes = new ListOfTypes(8);
        if (this.symbol == '<') {
            scanSymbol();
            listOfTypes.add(parseTypeArgument());
            while (true) {
                char c = this.symbol;
                if (c == '>' || c <= 0) {
                    expect('>');
                } else {
                    listOfTypes.add(parseTypeArgument());
                }
            }
            expect('>');
        }
        return listOfTypes;
    }

    /* access modifiers changed from: package-private */
    public Type parseTypeArgument() {
        ListOfTypes listOfTypes = new ListOfTypes(1);
        ListOfTypes listOfTypes2 = new ListOfTypes(1);
        char c = this.symbol;
        if (c == '*') {
            scanSymbol();
            listOfTypes.add(Object.class);
            return new WildcardTypeImpl(listOfTypes, listOfTypes2);
        } else if (c == '+') {
            scanSymbol();
            listOfTypes.add(parseFieldTypeSignature());
            return new WildcardTypeImpl(listOfTypes, listOfTypes2);
        } else if (c != '-') {
            return parseFieldTypeSignature();
        } else {
            scanSymbol();
            listOfTypes2.add(parseFieldTypeSignature());
            listOfTypes.add(Object.class);
            return new WildcardTypeImpl(listOfTypes, listOfTypes2);
        }
    }

    /* access modifiers changed from: package-private */
    public TypeVariableImpl parseTypeVariableSignature() {
        expect('T');
        scanIdentifier();
        expect(';');
        return new TypeVariableImpl(this.genericDecl, this.identifier);
    }

    /* access modifiers changed from: package-private */
    public Type parseTypeSignature() {
        char c = this.symbol;
        if (c == 'F') {
            scanSymbol();
            return Float.TYPE;
        } else if (c == 'S') {
            scanSymbol();
            return Short.TYPE;
        } else if (c == 'Z') {
            scanSymbol();
            return Boolean.TYPE;
        } else if (c == 'I') {
            scanSymbol();
            return Integer.TYPE;
        } else if (c != 'J') {
            switch (c) {
                case 'B':
                    scanSymbol();
                    return Byte.TYPE;
                case 'C':
                    scanSymbol();
                    return Character.TYPE;
                case 'D':
                    scanSymbol();
                    return Double.TYPE;
                default:
                    return parseFieldTypeSignature();
            }
        } else {
            scanSymbol();
            return Long.TYPE;
        }
    }

    /* access modifiers changed from: package-private */
    public void parseMethodTypeSignature(Class[] clsArr) {
        parseOptFormalTypeParameters();
        this.parameterTypes = new ListOfTypes(16);
        expect('(');
        while (true) {
            char c = this.symbol;
            if (c == ')' || c <= 0) {
                expect(')');
                this.returnType = parseReturnType();
            } else {
                this.parameterTypes.add(parseTypeSignature());
            }
        }
        expect(')');
        this.returnType = parseReturnType();
        if (this.symbol == '^') {
            this.exceptionTypes = new ListOfTypes(8);
            do {
                scanSymbol();
                if (this.symbol == 'T') {
                    this.exceptionTypes.add(parseTypeVariableSignature());
                } else {
                    this.exceptionTypes.add(parseClassTypeSignature());
                }
            } while (this.symbol == '^');
        } else if (clsArr != null) {
            this.exceptionTypes = new ListOfTypes(clsArr);
        } else {
            this.exceptionTypes = new ListOfTypes(0);
        }
    }

    /* access modifiers changed from: package-private */
    public Type parseReturnType() {
        if (this.symbol != 'V') {
            return parseTypeSignature();
        }
        scanSymbol();
        return Void.TYPE;
    }

    /* access modifiers changed from: package-private */
    public void scanSymbol() {
        if (!this.eof) {
            int i = this.pos;
            char[] cArr = this.buffer;
            if (i < cArr.length) {
                this.symbol = cArr[i];
                this.pos = i + 1;
                return;
            }
            this.symbol = 0;
            this.eof = true;
            return;
        }
        throw new GenericSignatureFormatError();
    }

    /* access modifiers changed from: package-private */
    public void expect(char c) {
        if (this.symbol == c) {
            scanSymbol();
            return;
        }
        throw new GenericSignatureFormatError();
    }

    /* access modifiers changed from: package-private */
    public void scanIdentifier() {
        if (!this.eof) {
            StringBuilder sb = new StringBuilder(32);
            if (!isStopSymbol(this.symbol)) {
                sb.append(this.symbol);
                do {
                    char c = this.buffer[this.pos];
                    if ((c < 'a' || c > 'z') && ((c < 'A' || c > 'Z') && isStopSymbol(c))) {
                        this.identifier = sb.toString();
                        scanSymbol();
                        return;
                    }
                    sb.append(c);
                    this.pos++;
                } while (this.pos != this.buffer.length);
                this.identifier = sb.toString();
                this.symbol = 0;
                this.eof = true;
                return;
            }
            this.symbol = 0;
            this.eof = true;
            throw new GenericSignatureFormatError();
        }
        throw new GenericSignatureFormatError();
    }
}
