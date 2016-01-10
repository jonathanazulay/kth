package se.kth.jazulay.project2;

import java.util.Stack;

public class QueryParser {
    public enum Operator {
        PLUS, OR, MINUS
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
            return "";
        }

        public String getProperty () {
            return this.property;
        }

        public String getDirection () {
            return this.direction;
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
    }

    public Query parse (String query) {
        Stack<QueryExpression> expressions = new Stack();
        Query root = null;

        for (char c : query.toCharArray()) {
            Operator op = this.getOperator(c);
            if (c == ' ') { continue; }

            QueryExpression newExpression;
            if (op != null) { // if is operator
                newExpression = new QueryExpression(op, null, null);
            } else { // if is operand
                newExpression = new QueryExpression(c + "");
            }

            if (!expressions.isEmpty()) {
                QueryExpression expression = expressions.peek();
                if (!expression.hasAllOperands()) {
                    expression.setNextOperand(newExpression);
                }

                if (expression.hasAllOperands()) {
                    expressions.pop();
                }
            } else {
                root = new Query(newExpression, null, null);
            }

            if (op != null) {
                expressions.push(newExpression);
            }
        }

        return root;
    }

    private Operator getOperator (char c) {
        switch (c) {
            case '-': return Operator.MINUS;
            case '+': return Operator.PLUS;
            case '|': return Operator.OR;
        }
        return null;
    }
}
