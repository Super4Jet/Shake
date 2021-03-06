package com.github.nsc.de.compiler.parser.node.functions;

import com.github.nsc.de.compiler.parser.node.AccessDescriber;
import com.github.nsc.de.compiler.parser.node.Tree;
import com.github.nsc.de.compiler.parser.node.ValuedNode;
import com.github.nsc.de.compiler.parser.node.VariableType;

import java.util.Arrays;

public class FunctionDeclarationNode implements ValuedNode {

    private final String name;
    private final Tree body;
    private final FunctionArgumentNode[] args;
    private final VariableType type;
    private final AccessDescriber access;
    private final boolean isInClass;
    private final boolean isStatic;
    private final boolean isFinal;

    public FunctionDeclarationNode(String name, Tree body, FunctionArgumentNode[] args, VariableType type) {
        this(name, body, args, type, AccessDescriber.PACKAGE, false, false, false);
    }

    public FunctionDeclarationNode(String name, Tree body, FunctionArgumentNode[] args) {
        this(name, body, args, VariableType.DYNAMIC);
    }


    public FunctionDeclarationNode(String name, Tree body, FunctionArgumentNode[] args, AccessDescriber access, boolean isInClass, boolean isStatic, boolean isFinal) {
        this(name, body, args, VariableType.DYNAMIC, access, isInClass, isStatic, isFinal);
    }


    public FunctionDeclarationNode(String name, Tree body, FunctionArgumentNode[] args, VariableType type, AccessDescriber access, boolean isInClass, boolean isStatic, boolean isFinal) {
        this.name = name;
        this.body = body;
        this.args = args;
        this.type = type;
        this.access = access;
        this.isInClass = isInClass;
        this.isStatic = isStatic;
        this.isFinal = isFinal;
    }

    public String getName() {
        return name;
    }
    public Tree getBody() {
        return body;
    }
    public FunctionArgumentNode[] getArgs() {
        return args;
    }
    public VariableType getType() { return type; }
    public AccessDescriber getAccess() { return access; }
    public boolean isStatic() { return isStatic; }
    public boolean isInClass() { return isInClass; }
    public boolean isFinal() { return isFinal; }

    @Override
    public String toString() {
        return "FunctionDeclarationNode{" +
                "name='" + name + '\'' +
                ", body=" + body +
                ", args=" + Arrays.toString(args) +
                ", type=" + type +
                '}';
    }
}
