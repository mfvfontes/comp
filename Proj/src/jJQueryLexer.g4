lexer grammar jJQueryLexer;

OPENTAG : '/*@jQ'      -> mode(JQUERY);

JAVA : ~'/'+;

COMMENT_OUTSIDE
   :   ( '//' ~[\r\n]* '\r'? '\n'
       | '/*' ~'@' .*? '*/'
       )
    ;

mode JQUERY ;
CLOSETAG : '*/'        -> mode(DEFAULT_MODE);

IN : 'in';
OUT : 'out';

EQUALS : '=';

DOT : '.';
COMMA : ',';
STAR : '*';
COLON : ':';
SEMICOLON : ';';
QUOT_MARK : '"';
DOLL_MARK : '$';
PRIME : '\'';

OPEN : '(';
CLOSE : ')';
OPEN_SQ : '[';
CLOSE_SQ : ']';

EQ_EXPR : 'eq';
GT_EXPR : 'gt';
LT_EXPR : 'lt';
EVEN_EXPR : 'even';
ODD_EXPR : 'odd';
NOT_EXPR : 'not';

EQUAL_OP : '=';             /* has attribute with value val */
CONTAINS_OP : '*=';         /* attribute includes val */
NOT_CONTAINS_OP : '!=';     /* does not have attribute with value val */
BEGINS_OP : '^=';           /* attribute begins with val */
ENDS_OP : '$=';             /* attribute ends with val */
INCLUDES_OP : '~=';         /* attribute includes val as a word */
PREFIX_OP : '|=';           /* attribute begins with val and optional hyphen */

INT: ('0'..'9')+;
ID: ('A'..'Z' | 'a'..'z' | '0'..'9')+;


COMMENT_INSIDE
   :   ( '//' ~[\r\n]* '\r'? '\n'
       | '/*' ~'@' .*? '*/'
       )
    ;

WS: (' ' | '\t')+ -> channel(HIDDEN);
NL: ('\n' | '\r\n')+ -> channel(HIDDEN);