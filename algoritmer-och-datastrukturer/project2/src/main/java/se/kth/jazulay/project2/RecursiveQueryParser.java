package se.kth.jazulay.project2;

public class RecursiveQueryParser {
    public Query parse (String query) {
        return new Query(this.recursiveParse(new UnparsedExpression(query)), "relevance", "asc");
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
