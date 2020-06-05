package com.github.nsc.de.compiler.parser;

import com.github.nsc.de.compiler.parser.node.expression.AddNode;
import com.github.nsc.de.compiler.parser.node.expression.MulNode;
import com.github.nsc.de.compiler.parser.node.expression.NumberNode;
import com.github.nsc.de.compiler.parser.node.logical.*;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;
import static com.github.nsc.de.compiler.TestUtil.*;
import static com.github.nsc.de.compiler.parser.ParserTestUtil.*;

public class LogicalTests {

    @Test
    public void testTrue() {
        assertNotNull(parseSingle("<LogicalTrueTest>","true", LogicalTrueNode.class));
    }

    @Test
    public void testFalse() {
        assertNotNull(parseSingle("<LogicalFalseTest>","false", LogicalFalseNode.class));
    }

    @Test
    public void testEqEquals() {
        testBasic("10==10", 10, 10, LogicalEqEqualsNode.class);
    }

    @Test
    public void testBiggerEquals() {
        testBasic("10>=10", 10, 10, LogicalBiggerEqualsNode.class);
    }

    @Test
    public void testSmallerEquals() {
        testBasic("10<=10", 10, 10, LogicalSmallerEqualsNode.class);
    }

    @Test
    public void testBigger() {
        testBasic("10>10", 10, 10, LogicalBiggerNode.class);
    }

    @Test
    public void testSmaller() {
        testBasic("10<10", 10, 10, LogicalSmallerNode.class);
    }

    @Test
    public void testAnd() {

        LogicalAndNode node = parseSingle("<LogicalAndTest>", "true && false", LogicalAndNode.class);
        assertNotNull(node.getLeft());
        assertType(LogicalTrueNode.class, node.getLeft());
        assertNotNull(node.getRight());
        assertType(LogicalFalseNode.class, node.getRight());

    }

    @Test
    public void testOr() {

        LogicalOrNode node = parseSingle("<LogicalOrTest>", "true || false", LogicalOrNode.class);
        assertNotNull(node.getLeft());
        assertType(LogicalTrueNode.class, node.getLeft());
        assertNotNull(node.getRight());
        assertType(LogicalFalseNode.class, node.getRight());

    }

    @Test
    public void testBrackets() {

        LogicalAndNode node = parseSingle("<LogicalBracketTest>", "true && (false || true)", LogicalAndNode.class);
        assertNotNull(node.getLeft());
        assertType(LogicalTrueNode.class, node.getLeft());

        assertNotNull(node.getRight());
        assertType(LogicalOrNode.class, node.getRight());

        LogicalOrNode or = (LogicalOrNode) node.getRight();
        assertNotNull(or.getLeft());
        assertType(LogicalFalseNode.class, or.getLeft());
        assertNotNull(or.getRight());
        assertType(LogicalTrueNode.class, or.getRight());

    }

    @Test
    public void testExpr() {

        LogicalBiggerEqualsNode node = parseSingle("<LogicalExpressionTest>", "10 >= 5 + 9 * 2", LogicalBiggerEqualsNode.class);
        assertNotNull(node.getLeft());
        assertType(NumberNode.class, node.getLeft());
        assertEquals(10, ((NumberNode) node.getLeft()).getNumber());
        assertNotNull(node.getRight());
        assertType(AddNode.class, node.getRight());

        AddNode add = (AddNode) node.getRight();
        assertNotNull(add.getLeft());
        assertType(NumberNode.class, add.getLeft());
        assertEquals(5, ((NumberNode) add.getLeft()).getNumber());
        assertNotNull(add.getRight());
        assertType(MulNode.class, add.getRight());

        MulNode mul = (MulNode) add.getRight();
        assertNotNull(mul.getLeft());
        assertType(NumberNode.class, mul.getLeft());
        assertEquals(9, ((NumberNode) mul.getLeft()).getNumber());
        assertNotNull(mul.getRight());
        assertType(NumberNode.class, mul.getRight());
        assertEquals(2, ((NumberNode) mul.getRight()).getNumber());

    }



    private <T extends LogicalCompareNode> void testBasic(String input, double left, double right, Class<T> type) {

        T node = parseSingle('<'+type.getSimpleName().substring(type.getSimpleName().length() - 4)+"Test>", input, type);
        assertNotNull(node.getLeft());
        assertType(NumberNode.class, node.getLeft());
        assertEquals(left, ((NumberNode) node.getLeft()).getNumber());
        assertNotNull(node.getRight());
        assertType(NumberNode.class, node.getRight());
        assertEquals(right, ((NumberNode) node.getRight()).getNumber());

    }
}
