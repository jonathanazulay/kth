package se.kth.jazulay.project2;

public class RecursiveQueryParser {
    public Query parse (String query) {
        String[] strings = query.split("\\s+");
        if (strings.length >= 4 && strings[strings.length - 3].equals("orderby")) {
            StringBuilder restOfExp = new StringBuilder();
            for (int i = 0; i < strings.length - 3; i += 1) {
                restOfExp.append(strings[i] + " ");
            }

            return new Query(
                this.recursiveParse(
                    new UnparsedExpression(restOfExp.toString())),
                    strings[strings.length - 2], strings[strings.length - 1]
            );
        } else {
            return new Query(
                this.recursiveParse(
                    new UnparsedExpression(query)), "popularity", "asc"
                );
        }

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
