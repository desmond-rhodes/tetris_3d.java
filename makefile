JC := javac
JVM := java.exe

tetris_3d.class:

.PHONY: run
run: tetris_3d.class
	$(JVM) tetris_3d

%.class: %.java
	javac $<

.PHONY: clean
clean:
	rm -f *.class
