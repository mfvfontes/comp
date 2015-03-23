grammar jQuery;

start : '/*@jQ' content '*/';

content : (in | out | expr) content | /*EPSILON*/;


in : 'in' ID   ';';
out : 'out' ID   ';';

expr : attr_expr;

attr_expr : ID '=' '$''(''"' ID '[' ID op '\'' ID '\''  ']' ID '"'')'';' ;

op :  EQUAL_OP
    | CONTAINS_OP
    ;

EQUAL_OP : '=';
CONTAINS_OP : '*=';

INT: ('0'..'9')+;
ID: ('A'..'Z' | 'a'..'z')+;

WS: (' ' | '\t')+ -> channel(HIDDEN);

NL: ('\n')+ -> channel(HIDDEN);