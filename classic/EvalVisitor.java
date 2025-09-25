import java.util.*;

public class EvalVisitor extends ExprBaseVisitor<Double> {
    @Override
    public Double visitAddSub(ExprParser.AddSubContext ctx) {
        double l = visit(ctx.expr(0));
        double r = visit(ctx.expr(1));
        return ctx.op.getText().equals("+") ? l + r : l - r;
    }

    @Override
    public Double visitMulDiv(ExprParser.MulDivContext ctx) {
        double l = visit(ctx.expr(0));
        double r = visit(ctx.expr(1));
        return ctx.op.getText().equals("*") ? l * r : l / r;
    }

    @Override
    public Double visitPowerRight(ExprParser.PowerRightContext ctx) {
        double l = visit(ctx.expr(0));
        double r = visit(ctx.expr(1));
        return Math.pow(l, r);
    }

    @Override
    public Double visitUnary(ExprParser.UnaryContext ctx) {
        double v = visit(ctx.expr());
        return ctx.op.getText().equals("+") ? v : -v;
    }

    @Override
    public Double visitParen(ExprParser.ParenContext ctx) {
        return visit(ctx.expr());
    }

    @Override
    public Double visitNumber(ExprParser.NumberContext ctx) {
        return Double.valueOf(ctx.getText());
    }
}