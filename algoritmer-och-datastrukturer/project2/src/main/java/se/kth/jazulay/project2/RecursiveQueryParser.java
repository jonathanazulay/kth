package se.kth.jazulay.project2;

public class RecursiveQueryParser {
    public enum Operator {
        PLUS, OR, MINUS;

        public char get () {
            if (this == Operator.MINUS) {
                return '-';
            } else if (this == Operator.OR) {
                return '|';
            } else if (this == Operator.PLUS) {
                return '+';
            }
            return 0;
        }

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

        public String getInfix () {
            return this.expression.toString();
        }

        public String getProperty () {
            return this.property;
        }

        public String getDirection () {
            return this.direction;
        }

        @Override
        public String toString() {
            return "Query" + this.getInfix() + " ORDER BY BLA BLA";
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

        @Override
        public String toString() {
            if (this.value != null) { return this.value; }
            else {
                // Postfix
                //return this.left.toString() + " " + this.right.toString() + " " + this.operator.get();
                // Prefix
                //return "" + this.operator.get() + " " + this.left.toString() + " " + this.right.toString();
                // Infix
                return '(' + this.left.toString() + ' ' + this.operator.get() + ' ' + this.right.toString() + ')';
            }
        }
    }

    public Query parse (String query) {
        return new Query(this.recursiveParse(new StringBuilder(query)), null, null);
    }

    private QueryExpression recursiveParse (StringBuilder query) {
        // Ignore spaces
        char c;
        do {
            c = query.charAt(0);
            query.delete(0, 1);
        } while (c == ' ');

        Operator op = Operator.get(c);

        QueryExpression newExp;
        if (op != null) {
            newExp = new QueryExpression(
                op,
                this.recursiveParse(query),
                this.recursiveParse(query)
            );
        } else {
            // A word starts here, find next terminator (space)
            int indexOfFirstSpace = this.indexOfTerminator(query.toString());
            String word = query.substring(0, indexOfFirstSpace);
            query.delete(0, indexOfFirstSpace);
            newExp = new QueryExpression(c + word);
        }

        return newExp;
    }

    private int indexOfTerminator (String expression) {
        int indexOfFirstSpace = 0;
        for (char c : expression.toCharArray()) {
            if (c == ' ' || c == '-' || c == '|' || c == '+') {
                return indexOfFirstSpace;
            }
            indexOfFirstSpace += 1;
        }
        return expression.length();
    }
}
