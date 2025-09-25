public class EvalVisitorWeird extends ExprWeirdBaseVisitor<Double> {
    @Override
    public Double visitSumHigh(ExprWeirdParser.SumHighContext ctx) {
        double l = visit(ctx.sumExpr());
        double r = visit(ctx.prodExpr());
        return ctx.op.getText().equals("+") ? l + r : l - r;
    }

    @Override
    public Double visitPassToProd(ExprWeirdParser.PassToProdContext ctx) {
        return visit(ctx.prodExpr());
    }

    @Override
    public Double visitProdLow(ExprWeirdParser.ProdLowContext ctx) {
        double l = visit(ctx.prodExpr());
        double r = visit(ctx.powExpr());
        return ctx.op.getText().equals("*") ? l * r : l / r;
    }

    @Override
    public Double visitPassToPow(ExprWeirdParser.PassToPowContext ctx) {
        return visit(ctx.powExpr());
    }

    @Override
    public Double visitPowerLeft(ExprWeirdParser.PowerLeftContext ctx) {
        double l = visit(ctx.powExpr());
        double r = visit(ctx.unaryExpr());
        return Math.pow(l, r);
    }

    @Override
    public Double visitPassToUnary(ExprWeirdParser.PassToUnaryContext ctx) {
        return visit(ctx.unaryExpr());
    }

    @Override
    public Double visitUnary(ExprWeirdParser.UnaryContext ctx) {
        double v = visit(ctx.unaryExpr());
        return ctx.op.getText().equals("+") ? v : -v;
    }

    @Override
    public Double visitParen(ExprWeirdParser.ParenContext ctx) {
        return visit(ctx.expr());
    }

    @Override
    public Double visitNumber(ExprWeirdParser.NumberContext ctx) {
        return Double.valueOf(ctx.getText());
    }
}