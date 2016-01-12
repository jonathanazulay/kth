package se.kth.jazulay.project2;

class UnparsedExpression {
    private int pos = 0;
    private String expression;

    public UnparsedExpression (String expression) {
        this.expression = expression;
        this.trim();
    }

    public boolean isOperand () {
        if (this.reachedEnd()) {
            return false;
        }
        return !this.isOperator() && !this.isSpace();
    }

    public boolean isOperator () {
        if (this.reachedEnd()) {
            return false;
        }
        Operator operator = Operator.get(this.expression.charAt(pos));
        return operator != null;
    }

    public String parseNextOperand () {
        if (!this.isOperand()) {
            return null;
        }
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

    private void trim () {
        while (!this.reachedEnd() && this.expression.charAt(pos) == ' ') {
            pos += 1;
        }
    }

    private boolean isSpace () {
        if (this.reachedEnd()) {
            return false;
        }
        return this.expression.charAt(pos) == ' ';
    }

    private boolean reachedEnd () {
        return pos >= this.expression.length();
    }

}
