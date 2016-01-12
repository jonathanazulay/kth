package se.kth.jazulay.project2;

public class RecursiveQueryParser {
    public enum Operator {
        PLUS, OR, MINUS;

        public static Operator get (char c) {
            if (c == '-') {
                return Operator.MINUS;
            } else if (c == '|') {
                return Operator.OR;
            } else if (c == '+') {
                return Operator.PLUS;
            }
            return null;
        }

        @Override
        public String toString () {
            if (this == Operator.MINUS) {
                return "-";
            } else if (this == Operator.OR) {
                return "|";
            } else if (this == Operator.PLUS) {
                return "+";
            }
            return null;
        }
    }

    private class UnparsedExpression {
        private int pos = 0;
        private String expression;

        public UnparsedExpression (String expression) {
            this.expression = expression;
            this.trim();
        }

        private void trim () {
            while (!this.reachedEnd() && this.expression.charAt(pos) == ' ') {
                pos += 1;
            }
        }

        private boolean isSpace () {
            if (this.reachedEnd()) { return false; }
            return this.expression.charAt(pos) == ' ';
        }

        public boolean isOperand () {
            if (this.reachedEnd()) { return false; }
            return !this.isOperator() && !this.isSpace();
        }

        public boolean isOperator () {
            if (this.reachedEnd()) { return false; }
            Operator operator = Operator.get(this.expression.charAt(pos));
            return operator != null;
        }

        public String parseNextOperand () {
            if (!this.isOperand()) { return null; }
            String returnVal = "";
            while (this.isOperand()) {
                returnVal += this.expression.charAt(pos);
                pos += 1;
            }
            this.trim();
            return returnVal;
        }

        public Operator parseNextOperator () {
            Operator returnVal = null;
            if (this.isOperator()) {
                returnVal = Operator.get(this.expression.charAt(pos));
                pos += 1;
                this.trim();
            }
            return returnVal;
        }

        private boolean reachedEnd () {
            return pos >= this.expression.length();
        }
    }

    public class Query {
        QueryExpression expression;
        private String property;
        private String direction;

        public Query (QueryExpression ex, String property, String direction) {
            this.expression = ex;
            this.property = property;
            this.direction = direction;
        }

        public String getProperty () {
            return this.property;
        }

        public String getDirection () {
            return this.direction;
        }

        public String infix () {
            return this.expression.infix();
        }

        public String prefix () {
            return this.expression.prefix();
        }

        public String postfix () {
            return this.expression.postfix();
        }

        @Override
        public String toString() {
            return this.infix()+ " ORDER BY BLA BLA";
        }
    }

    public class QueryExpression {
        public Operator operator;
        public String value = null;
        public QueryExpression left = null;
        public QueryExpression right = null;

        public QueryExpression (
            Operator operator,
            QueryExpression left,
            QueryExpression right
        ) {
            this.operator = operator;
            this.left = left;
            this.right = right;
        }

        public QueryExpression (String value) {
            this.value = value;
        }

        public boolean hasAllOperands () {
            return this.left != null && this.right != null;
        }

        public void setNextOperand (QueryExpression exp) {
            if (this.left == null) { this.left = exp; }
            else if (this.right == null) { this.right = exp; }
            else { throw new Error("all operands has already been set"); }
        }

        public String infix () {
            if (this.value != null) { return this.value; }
            return '(' + this.left.infix()+ ' ' + this.operator + ' ' + this.right.infix() + ')';
        }

        public String prefix () {
            if (this.value != null) { return this.value; }
            return "" + this.operator + " " + this.left.prefix() + " " + this.right.prefix();
        }

        public String postfix () {
            if (this.value != null) { return this.value; }
            return this.left.postfix()+ " " + this.right.postfix()+ " " + this.operator;
        }

        @Override
        public String toString() {
            return this.infix();
        }
    }

    public Query parse (String query) {
        return new Query(this.recursiveParse(new UnparsedExpression(query)), null, null);
    }

    private QueryExpression recursiveParse (UnparsedExpression query) {
        QueryExpression newExp;

        if (query.isOperator()) {
            newExp = new QueryExpression(
                query.parseNextOperator(),
                this.recursiveParse(query),
                this.recursiveParse(query)
            );
        } else {
            newExp = new QueryExpression(query.parseNextOperand());
        }

        return newExp;
    }
}
