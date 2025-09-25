grammar Expr;

prog : expr EOF ;

expr
    : expr op=('+'|'-') expr              # addSub
    | expr op=('*'|'/') expr              # mulDiv
    | <assoc=right> expr '^' expr         # powerRight
    | op=('+'|'-') expr                   # unary
    | '(' expr ')'                        # paren
    | NUMBER                              # number
    ;

NUMBER : [0-9]+ ('.' [0-9]+)? ;
WS     : [ \t\r\n]+ -> skip ;