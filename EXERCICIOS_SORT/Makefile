CC      = gcc
CFLAGS  = -O2 -Wall -DBENCHMARK_BUILD
JAVAC   = javac
JAVA    = java

C_SRCS    = benchmark.c Insertion.c Selection.c
JAVA_SRCS = bobble.java Quick.java
JAVA_CLS  = $(JAVA_SRCS:.java=.class)

all: benchmark $(JAVA_CLS)

benchmark: $(C_SRCS)
	$(CC) $(CFLAGS) -o $@ $(C_SRCS)

%.class: %.java
	$(JAVAC) $<

run: all
	./benchmark

clean:
	rm -f benchmark *.class comparacao.txt

.PHONY: all run clean
