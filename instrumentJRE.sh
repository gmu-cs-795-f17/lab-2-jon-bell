#!/bin/sh
if [ -z "$1" ]; then
	OUTPUT_DIR="target/jre-inst"
else
	OUTPUT_DIR="$1"
fi
if [ -z "$INST_HOME" ]; then
	if [ -d "$JAVA_HOME/jre" ]; then
		INST_HOME="$JAVA_HOME/jre"
	else
		INST_HOME=$JAVA_HOME
	fi
fi
if [ -z "$JAVA_HOME" ]; then
	echo "Error: Please set \$JAVA_HOME";
else
	echo "Ensuring instrumented JREs exist for tests... $OUTPUT_DIR\n";
	if [ ! -d "$OUTPUT_DIR" ]; then
		echo "Creating instrumented JRE\n";
		java -Xmx6g -jar target/cs795-lab2-0.0.1-SNAPSHOT.jar $INST_HOME $OUTPUT_DIR;
		chmod +x $OUTPUT_DIR/jre/bin/*;
		chmod +x $OUTPUT_DIR/jre/lib/*;
		chmod +x $OUTPUT_DIR/bin/*;
		chmod +x $OUTPUT_DIR/lib/*;
	else
		echo "Not regenerating instrumented JRE\n";
	fi
fi
