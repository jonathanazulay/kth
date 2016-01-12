package se.kth.jazulay.project2;

public class QueryExpression {
    public Operator operator;
    public String value = null;
    public QueryExpression left = null;
    public QueryExpression right = null;

    public QueryExpression (Operator operator, QueryExpression left, QueryExpression right) {
        this.operator = operator;
        this.left = left;
        this.right = right;
    }

    public QueryExpression (String value) {
        this.value = value;
    }

    public String infix () {
        if (this.value != null) {
            return this.value;
        }
        return '(' + this.left.infix() + ' ' + this.operator + ' ' + this.right.infix() + ')';
    }

    public String prefix () {
        if (this.value != null) {
            return this.value;
        }
        return "" + this.operator + " " + this.left.prefix() + " " + this.right.prefix();
    }

    public String postfix () {
        if (this.value != null) {
            return this.value;
        }
        return this.left.postfix() + " " + this.right.postfix() + " " + this.operator;
    }

    @Override
    public String toString () {
        return this.infix();
    }
}
