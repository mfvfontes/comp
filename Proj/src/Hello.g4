grammar Hello;


start : '/*@jQ' content'*/' ;
content : (in | out | expr)+ ;

in : 'in' ID   ';';
out : 'out' ID   ';';

expr : attr;

attr : ID   '='   '$("'   ID '[' ID attr_op;

attr_op : attr_equals;

attr_equals : '=' '$("' ID '[' ID '=' '\''ID '\']'ID'")' ';';



ID: ('A'..'Z' | 'a'..'z')+;
Int: ('0'..'9')+;

WS: (' ' | '\t')+ -> channel(HIDDEN)
;

NL: ('\n')+ -> channel(HIDDEN)
;