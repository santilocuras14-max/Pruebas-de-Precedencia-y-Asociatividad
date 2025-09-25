grammar ExprWeird;

prog : expr EOF ;

expr        : sumExpr ;

// 1) Más alto: + y -
sumExpr
    : sumExpr op=('+'|'-') prodExpr   # sumHigh
    | prodExpr                        # passToProd
    ;

// 2) Luego * y /
prodExpr
    : prodExpr op=('*'|'/') powExpr   # prodLow
    | powExpr                         # passToPow
    ;

// 3) Potencia ^ (asociativa a IZQUIERDA)
powExpr
    : powExpr '^' unaryExpr           # powerLeft
    | unaryExpr                       # passToUnary
    ;

// 4) Unarios y átomos
unaryExpr
    : op=('+'|'-') unaryExpr          # unary
    | '(' expr ')'                    # paren
    | NUMBER                          # number
    ;

NUMBER : [0-9]+ ('.' [0-9]+)? ;
WS     : [ \t\r\n]+ -> skip ;