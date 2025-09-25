import org.antlr.v4.runtime.*;
import org.antlr.v4.runtime.tree.*;
import java.io.*;

public class MainWeird {
    public static void main(String[] args) throws Exception {
        InputStream in = System.in;
        CharStream input = CharStreams.fromStream(in);
        ExprWeirdLexer lexer = new ExprWeirdLexer(input);
        CommonTokenStream tokens = new CommonTokenStream(lexer);
        ExprWeirdParser parser = new ExprWeirdParser(tokens);
        ParseTree tree = parser.prog();
        EvalVisitorWeird eval = new EvalVisitorWeird();
        Double result = eval.visit(tree);
        System.out.println(result);
    }
}