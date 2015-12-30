# Grando Alessandro's Lexical Analyzer
# syntax --> regular expression

Lexical Analyzer that recognizes and returns tokens defined by following grammar:

prog		::== [decl;]* stmt EOF

decl		::== var ID [,ID]* : type

type		::== integer | boolean

expr		::== andExpr [|| andExpr]*

andExpr		::== relExpr [&& relExpr]*

relExpr		::== addExpr [== addExpr | <> addExpr | <= addExpr | >= addExpr | < addExpr | > addExpr]?

addExpr		::== mulExpr [+ mulExpr | - mulExpr]*

mulExpr		::== unExpr [* unExpr | / unExpr]*

unExpr		::== not unExpr | primary

primary		::== ( expr ) | ID | NUM | true | false

stmt		::== ID := expr | print ( expr ) | stmt [; stmt]*