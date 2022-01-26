SRC_FILES := $(shell find ./src/* -name '*.java')
TEST_FILES := $(shell find ./test/* -name '*java')
SRC_CLASSES := $(shell find ./src/* -name '*.class')
TEST_CLASSES := $(shell find ./test/* -name '*.class')
LIB := ./lib/*
CLASSPATH := $(LIB):src:test


.PHONY: compile
# Compile all java files
compile:
	@javac -cp $(LIB):. $(SRC_FILES) $(TEST_FILES)

.PHONY: clean
# Remove java class files
clean:
	@rm $(SRC_CLASSES) $(TEST_CLASSES)

.PHONY: test
# Run JUnit tests
test:
	@java -cp $(CLASSPATH):. org.junit.runner.JUnitCore TestSuite


.PHONY: help
# Found here: https://stackoverflow.com/a/35730928/12168211
# Print available commands
help:
	@echo "=============================================================="
	@echo "Available Make commands ======================================"
	@echo "=============================================================="
	@awk '/^#/{c=substr($$0,3);next}c&&/^[[:alpha:]][[:alnum:]_-]+:/{print substr($$1,1,index($$1,":")),c}1{c=0}' $(MAKEFILE_LIST) | column -s: -t