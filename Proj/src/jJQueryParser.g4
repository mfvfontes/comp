parser grammar jJQueryParser;

options {
    language = Java;
    tokenVocab = jJQueryLexer;
}

start : (JAVA | jQueryBlock)+ EOF;

jQueryBlock : OPENTAG (in | out | expr)* CLOSETAG;

in : IN ID SEMICOLON;
out : OUT ID SEMICOLON;
expr : ID EQUALS selector;

selector : DOLL_MARK OPEN QUOT_MARK ID (selector_expr|multiple_expr) ID? QUOT_MARK CLOSE SEMICOLON ;

multiple_expr : selector_expr (COMMA selector_expr)+ ;

selector_expr : all_expr
                | attr_expr
                | class_expr
                | eq_expr
                | gt_expr
                | lt_expr
                | even_expr
                | odd_expr
                | not_expr ;

all_expr:  STAR ;
attr_expr : OPEN_SQ ID op PRIME ID PRIME CLOSE_SQ ;
class_expr: DOT ID ;
eq_expr: COLON EQ_EXPR OPEN INT CLOSE ;
gt_expr: COLON GT_EXPR OPEN INT CLOSE ;
lt_expr: COLON LT_EXPR OPEN INT CLOSE ;
even_expr: COLON EVEN_EXPR OPEN CLOSE ;
odd_expr: COLON ODD_EXPR OPEN CLOSE ;
not_expr : COLON NOT_EXPR OPEN selector_expr CLOSE ;

op :  EQUAL_OP
    | CONTAINS_OP
    | NOT_CONTAINS_OP
    | BEGINS_OP
    | ENDS_OP
    | INCLUDES_OP
    | PREFIX_OP
    ;