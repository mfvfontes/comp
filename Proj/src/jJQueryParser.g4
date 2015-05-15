parser grammar jJQueryParser;

options {
    tokenVocab = jJQueryLexer;
}

jQueryBlock : OPENTAG (in | out | expr | COMMENT_INSIDE)* CLOSETAG;


in : IN ID J_SEMI;
out : OUT ID J_SEMI;
expr : ID J_ASSIGN selector J_SEMI;


logical_expr    : logical_expr l_op logical_expr
                | J_LPAREN logical_expr J_RPAREN
                | selector_expr;

selector : openSelect ID logical_expr ID? closeSelect;

openSelect : J_DOLLAR J_LPAREN J_QUOTE;
closeSelect : J_QUOTE J_RPAREN;

left            : logical_expr;
right           : logical_expr;


l_op            : L_AND
                | L_OR;

selector_expr : all_expr
                | attr_expr
                | class_expr
                | eq_expr
                | gt_expr
                | lt_expr
                | even_expr
                | odd_expr
                | not_expr ;

all_expr:  MUL ;
attr_expr : J_LBRACK attribute op J_PRIME evaluator J_PRIME J_RBRACK ;
class_expr: J_DOT ID ;
eq_expr: J_COLON EQ_EXPR J_LPAREN evaluator J_RPAREN ;
gt_expr: J_COLON GT_EXPR J_LPAREN evaluator J_RPAREN ;
lt_expr: J_COLON LT_EXPR J_LPAREN evaluator J_RPAREN ;
even_expr: J_COLON EVEN_EXPR J_LPAREN J_RPAREN ;
odd_expr: J_COLON ODD_EXPR J_LPAREN J_RPAREN ;
not_expr : J_COLON NOT_EXPR J_LPAREN (selector_expr | logical_expr) J_RPAREN ;

attribute : ID;
evaluator : INTEGER | ID;

op :  ASSIGN
    | CONTAINS_OP
    | NOT_CONTAINS_OP
    | BEGINS_OP
    | ENDS_OP
    | INCLUDES_OP
    | PREFIX_OP
    ;

// starting point for parsing a java file
compilationUnit
    :   packageDeclaration? importDeclaration* typeDeclaration* EOF
    ;

packageDeclaration
    :   annotation* PACKAGE qualifiedName SEMI
    ;

importDeclaration
    :   IMPORT STATIC? qualifiedName (DOT MUL)? SEMI
    ;

typeDeclaration
    :   classOrInterfaceModifier* classDeclaration
    |   classOrInterfaceModifier* enumDeclaration
    |   classOrInterfaceModifier* interfaceDeclaration
    |   classOrInterfaceModifier* annotationTypeDeclaration
    |   SEMI
    ;

modifier
    :   classOrInterfaceModifier
    |   (   NATIVE
        |   SYNCHRONIZED
        |   TRANSIENT
        |   VOLATILE
        )
    ;

classOrInterfaceModifier
    :   annotation       // class or interface
    |   (   PUBLIC     // class or interface
        |   PROTECTED  // class or interface
        |   PRIVATE    // class or interface
        |   STATIC     // class or interface
        |   ABSTRACT   // class or interface
        |   FINAL      // class only -- does not apply to interfaces
        |   STRICTFP   // class or interface
        )
    ;

variableModifier
    :   FINAL
    |   annotation
    ;

classDeclaration
    :   CLASS Identifier typeParameters?
        (EXTENDS type)?
        (IMPLEMENTS typeList)?
        classBody
    ;

typeParameters
    :   LT typeParameter (COMMA typeParameter)* GT
    ;

typeParameter
    :   Identifier (EXTENDS typeBound)?
    ;

typeBound
    :   type (BITAND type)*
    ;

enumDeclaration
    :   ENUM Identifier (IMPLEMENTS typeList)?
        LBRACE enumConstants? COMMA? enumBodyDeclarations? RBRACE
    ;

enumConstants
    :   enumConstant (COMMA enumConstant)*
    ;

enumConstant
    :   annotation* Identifier arguments? classBody?
    ;

enumBodyDeclarations
    :   SEMI classBodyDeclaration*
    ;

interfaceDeclaration
    :   INTERFACE Identifier typeParameters? (EXTENDS typeList)? interfaceBody
    ;

typeList
    :   type (COMMA type)*
    ;

classBody
    :   LBRACE classBodyDeclaration* RBRACE
    ;

interfaceBody
    :   LBRACE interfaceBodyDeclaration* RBRACE
    ;

classBodyDeclaration
    :   SEMI
    |   STATIC? block
    |   modifier* memberDeclaration
    ;

memberDeclaration
    :   methodDeclaration
    |   genericMethodDeclaration
    |   fieldDeclaration
    |   constructorDeclaration
    |   genericConstructorDeclaration
    |   interfaceDeclaration
    |   annotationTypeDeclaration
    |   classDeclaration
    |   enumDeclaration
    ;

/* We use rule this even for void methods which cannot have [] after parameters.
   This simplifies grammar and we can consider void to be a type, which
   renders the [] matching as a context-sensitive issue or a semantic check
   for invalid return type after parsing.
 */
methodDeclaration
    :   (type|VOID) Identifier formalParameters (LBRACK RBRACK)*
        (THROWS qualifiedNameList)?
        (   methodBody
        |   SEMI
        )
    ;

genericMethodDeclaration
    :   typeParameters methodDeclaration
    ;

constructorDeclaration
    :   Identifier formalParameters (THROWS qualifiedNameList)?
        constructorBody
    ;

genericConstructorDeclaration
    :   typeParameters constructorDeclaration
    ;

fieldDeclaration
    :   type variableDeclarators SEMI
    ;

interfaceBodyDeclaration
    :   modifier* interfaceMemberDeclaration
    |   SEMI
    ;

interfaceMemberDeclaration
    :   constDeclaration
    |   interfaceMethodDeclaration
    |   genericInterfaceMethodDeclaration
    |   interfaceDeclaration
    |   annotationTypeDeclaration
    |   classDeclaration
    |   enumDeclaration
    ;

constDeclaration
    :   type constantDeclarator (COMMA constantDeclarator)* SEMI
    ;

constantDeclarator
    :   Identifier (LBRACK RBRACK)* ASSIGN variableInitializer
    ;

// see matching of [] comment in methodDeclaratorRest
interfaceMethodDeclaration
    :   (type|VOID) Identifier formalParameters (LBRACK RBRACK)*
        (THROWS qualifiedNameList)?
        SEMI
    ;

genericInterfaceMethodDeclaration
    :   typeParameters interfaceMethodDeclaration
    ;

variableDeclarators
    :   variableDeclarator (COMMA variableDeclarator)*
    ;

variableDeclarator
    :   variableDeclaratorId (ASSIGN variableInitializer)?
    ;

variableDeclaratorId
    :   Identifier (LBRACK RBRACK)*
    ;

variableInitializer
    :   arrayInitializer
    |   expression
    ;

arrayInitializer
    :   LBRACE (variableInitializer (COMMA variableInitializer)* (COMMA)? )? RBRACE
    ;

enumConstantName
    :   Identifier
    ;

type
    :   classOrInterfaceType (LBRACK RBRACK)*
    |   primitiveType (LBRACK RBRACK)*
    ;

classOrInterfaceType
    :   Identifier typeArguments? (DOT Identifier typeArguments? )*
    ;

primitiveType
    :   BOOLEAN
    |   CHAR
    |   BYTE
    |   SHORT
    |   INT
    |   LONG
    |   FLOAT
    |   DOUBLE
    ;

typeArguments
    :   LT typeArgument (COMMA typeArgument)* GT
    ;

typeArgument
    :   type
    |   QUESTION ((EXTENDS | SUPER) type)?
    ;

qualifiedNameList
    :   qualifiedName (COMMA qualifiedName)*
    ;

formalParameters
    :   LPAREN formalParameterList? RPAREN
    ;

formalParameterList
    :   formalParameter (COMMA formalParameter)* (COMMA lastFormalParameter)?
    |   lastFormalParameter
    ;

formalParameter
    :   variableModifier* type variableDeclaratorId
    ;

lastFormalParameter
    :   variableModifier* type ELLIPSIS variableDeclaratorId
    ;

methodBody
    :   block
    ;

constructorBody
    :   block
    ;

qualifiedName
    :   Identifier (DOT Identifier)*
    ;

literal
    :   IntegerLiteral
    |   CharacterLiteral
    |   StringLiteral
    |   BooleanLiteral
    |   NullLiteral
    ;

// ANNOTATIONS

annotation
    :   AT annotationName ( LPAREN ( elementValuePairs | elementValue )? RPAREN )?
    ;

annotationName : qualifiedName ;

elementValuePairs
    :   elementValuePair (COMMA elementValuePair)*
    ;

elementValuePair
    :   Identifier ASSIGN elementValue
    ;

elementValue
    :   expression
    |   annotation
    |   elementValueArrayInitializer
    ;

elementValueArrayInitializer
    :   LBRACE (elementValue (COMMA elementValue)*)? (COMMA)? RBRACE
    ;

annotationTypeDeclaration
    :   AT INTERFACE Identifier annotationTypeBody
    ;

annotationTypeBody
    :   LBRACE (annotationTypeElementDeclaration)* RBRACE
    ;

annotationTypeElementDeclaration
    :   modifier* annotationTypeElementRest
    |   SEMI // this is not allowed by the grammar, but apparently allowed by the actual compiler
    ;

annotationTypeElementRest
    :   type annotationMethodOrConstantRest SEMI
    |   classDeclaration SEMI?
    |   interfaceDeclaration SEMI?
    |   enumDeclaration SEMI?
    |   annotationTypeDeclaration SEMI?
    ;

annotationMethodOrConstantRest
    :   annotationMethodRest
    |   annotationConstantRest
    ;

annotationMethodRest
    :   Identifier LPAREN RPAREN defaultValue?
    ;

annotationConstantRest
    :   variableDeclarators
    ;

defaultValue
    :   DEFAULT elementValue
    ;

// STATEMENTS / BLOCKS

block
    :   LBRACE blockStatement* RBRACE
    ;

blockStatement
    :   localVariableDeclarationStatement
    |   statement
    |   typeDeclaration
    |   jQueryBlock
    ;

localVariableDeclarationStatement
    :    localVariableDeclaration SEMI
    ;

localVariableDeclaration
    :   variableModifier* type variableDeclarators
    ;

statement
    :   block
    |   ASSERT expression (COLON expression)? SEMI
    |   IF parExpression statement (ELSE statement)?
    |   FOR LPAREN forControl RPAREN statement
    |   WHILE parExpression statement
    |   DO statement WHILE parExpression SEMI
    |   TRY block (catchClause+ finallyBlock? | finallyBlock)
    |   TRY resourceSpecification block catchClause* finallyBlock?
    |   SWITCH parExpression LBRACE switchBlockStatementGroup* switchLabel* RBRACE
    |   SYNCHRONIZED parExpression block
    |   RETURN expression? SEMI
    |   THROW expression SEMI
    |   BREAK Identifier? SEMI
    |   CONTINUE Identifier? SEMI
    |   SEMI
    |   statementExpression SEMI
    |   Identifier COLON statement
    ;

catchClause
    :   CATCH LPAREN variableModifier* catchType Identifier RPAREN block
    ;

catchType
    :   qualifiedName (BITOR qualifiedName)*
    ;

finallyBlock
    :   FINALLY block
    ;

resourceSpecification
    :   LPAREN resources SEMI? RPAREN
    ;

resources
    :   resource (SEMI resource)*
    ;

resource
    :   variableModifier* classOrInterfaceType variableDeclaratorId ASSIGN expression
    ;

/** Matches cases then statements, both of which are mandatory.
 *  To handle empty cases at the end, we add switchLabel* to statement.
 */
switchBlockStatementGroup
    :   switchLabel+ blockStatement+
    ;

switchLabel
    :   CASE constantExpression COLON
    |   CASE enumConstantName COLON
    |   DEFAULT COLON
    ;

forControl
    :   enhancedForControl
    |   forInit? SEMI expression? SEMI forUpdate?
    ;

forInit
    :   localVariableDeclaration
    |   expressionList
    ;

enhancedForControl
    :   variableModifier* type variableDeclaratorId COLON expression
    ;

forUpdate
    :   expressionList
    ;

// EXPRESSIONS

parExpression
    :   LPAREN expression RPAREN
    ;

expressionList
    :   expression (COMMA expression)*
    ;

statementExpression
    :   expression
    ;

constantExpression
    :   expression
    ;

expression
    :   primary
    |   expression DOT Identifier
    |   expression DOT THIS
    |   expression DOT NEW nonWildcardTypeArguments? innerCreator
    |   expression DOT SUPER superSuffix
    |   expression DOT explicitGenericInvocation
    |   expression LBRACK expression RBRACK
    |   expression LPAREN expressionList? RPAREN
    |   NEW creator
    |   LPAREN type RPAREN expression
    |   expression (INC | DEC)
    |   (ADD|SUB|INC|DEC) expression
    |   (TILDE|BANG) expression
    |   expression (MUL|DIV|MOD) expression
    |   expression (ADD|SUB) expression
    |   expression (LT LT | GT GT GT | GT GT) expression
    |   expression (LE | GE | GT | LT) expression
    |   expression INSTANCEOF type
    |   expression (EQUAL | NOTEQUAL) expression
    |   expression BITAND expression
    |   expression CARET expression
    |   expression BITOR expression
    |   expression AND expression
    |   expression OR expression
    |   expression QUESTION expression COLON expression
    |   <assoc=right> expression
        (   ASSIGN
        |   ADD_ASSIGN
        |   SUB_ASSIGN
        |   MUL_ASSIGN
        |   DIV_ASSIGN
        |   AND_ASSIGN
        |   OR_ASSIGN
        |   XOR_ASSIGN
        |   RSHIFT_ASSIGN
        |   URSHIFT_ASSIGN
        |   LSHIFT_ASSIGN
        |   MOD_ASSIGN
        )
        expression
    ;

primary
    :   LPAREN expression RPAREN
    |   THIS
    |   SUPER
    |   literal
    |   Identifier
    |   type DOT CLASS
    |   VOID DOT CLASS
    |   nonWildcardTypeArguments (explicitGenericInvocationSuffix | THIS arguments)
    ;

creator
    :   nonWildcardTypeArguments createdName classCreatorRest
    |   createdName (arrayCreatorRest | classCreatorRest)
    ;

createdName
    :   Identifier typeArgumentsOrDiamond? (DOT Identifier typeArgumentsOrDiamond?)*
    |   primitiveType
    ;

innerCreator
    :   Identifier nonWildcardTypeArgumentsOrDiamond? classCreatorRest
    ;

arrayCreatorRest
    :   LBRACK
        (   RBRACK (LBRACK RBRACK)* arrayInitializer
        |   expression RBRACK (LBRACK expression RBRACK)* (LBRACK RBRACK)*
        )
    ;

classCreatorRest
    :   arguments classBody?
    ;

explicitGenericInvocation
    :   nonWildcardTypeArguments explicitGenericInvocationSuffix
    ;

nonWildcardTypeArguments
    :   LT typeList GT
    ;

typeArgumentsOrDiamond
    :   LT GT
    |   typeArguments
    ;

nonWildcardTypeArgumentsOrDiamond
    :   LT GT
    |   nonWildcardTypeArguments
    ;

superSuffix
    :   arguments
    |   DOT Identifier arguments?
    ;

explicitGenericInvocationSuffix
    :   SUPER superSuffix
    |   Identifier arguments
    ;

arguments
    :   LPAREN expressionList? RPAREN
    ;