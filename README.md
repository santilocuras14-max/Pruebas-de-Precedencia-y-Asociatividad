# ANTLR — Pruebas de Precedencia y Asociatividad (Visitor)

## Requisitos
- Java 11+
- ANTLR 4.13.1 (descarga el JAR y define variable de entorno `ANTLR4` con la ruta).

Opcional (alias):
```bash
export ANTLR4=/ruta/a/antlr-4.13.1-complete.jar
alias antlr4='java -jar $ANTLR4'
```

## Compilar y ejecutar (clásico)
```bash
cd classic
antlr4 -Dlanguage=Java Expr.g4
javac -cp .:$ANTLR4 *.java
echo "2+3*4^2-5" | java -cp .:$ANTLR4 Main
```

## Compilar y ejecutar (rediseñado)
```bash
cd redesigned
antlr4 -Dlanguage=Java ExprWeird.g4
javac -cp .:$ANTLR4 *.java
echo "2+3*4^2-5" | java -cp .:$ANTLR4 MainWeird
```

## Pruebas
Usa `tests/casos_puros.txt` para pasar varias expresiones por stdin:

```bash
cd classic
antlr4 -Dlanguage=Java Expr.g4
javac -cp .:$ANTLR4 *.java
while read -r line; do echo "$line" | java -cp .:$ANTLR4 Main; done < ../tests/casos_puros.txt

cd ../redesigned
antlr4 -Dlanguage=Java ExprWeird.g4
javac -cp .:$ANTLR4 *.java
while read -r line; do echo "$line" | java -cp .:$ANTLR4 MainWeird; done < ../tests/casos_puros.txt
```

## Casos de comparación esperados (resumen)
- `2+3*4` → clásico: **14** | rediseñado: **20**
- `2^3^2` → clásico: **512** (derecha) | rediseñado: **64** (izquierda)
- `2+3*4^2-5` → clásico: **45** | rediseñado: **75**
- `3-1-1` → ambos: **1**
- `8/4/2` → ambos: **1**
- `-2^2` → ambos: **-4** (potencia antes que unario)
```

## Estructura
```
precedencia-antlr/
├─ classic/
│  ├─ Expr.g4
│  ├─ EvalVisitor.java
│  └─ Main.java
├─ redesigned/
│  ├─ ExprWeird.g4
│  ├─ EvalVisitorWeird.java
│  └─ MainWeird.java
└─ tests/
   ├─ casos.txt
   └─ casos_puros.txt
```
