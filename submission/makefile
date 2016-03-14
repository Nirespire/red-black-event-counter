JFLAGS = -g
JC = javac
.SUFFIXES: .java .class
.java.class:
	$(JC) $(JFLAGS) $*.java

CLASSES = \
	bbst.java \
	redBlackBST/Node.java \
	redBlackBST/Tree.java \
	util/Color.java \
	util/Pair.java \
	util/Util.java

default: classes

classes: $(CLASSES:.java=.class)

clean:
	$(RM) *.class