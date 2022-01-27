SRC_FILES := $(shell find ./src/* -name '*.java')
TEST_FILES := $(shell find ./test/* -name '*java')
LIB := ./lib/*
CLASSPATH := $(LIB):src:test
STACSCHECK := /cs/studres/CS5011/Practicals/A1/Tests


.PHONY: compile
# Compile all java files
compile:
	@javac -cp $(LIB):. $(SRC_FILES) $(TEST_FILES)

.PHONY: clean
# Remove java class files
clean:
	find ./src/* -name '*.class' -delete
	find ./test/* -name '*.class' -delete


.PHONY: test
# Run JUnit tests
test:
	@java -cp $(CLASSPATH):. org.junit.runner.JUnitCore TestSuite

.PHONY: stacscheck
# Run stacscheck checks (only on school server)
stacscheck:
	@stacscheck $(STACSCHECK)


.PHONY: help
# Found here: https://stackoverflow.com/a/35730928/12168211
# Print available commands
help:
	@echo "=============================================================="
	@echo "Available Make commands ======================================"
	@echo "=============================================================="
	@awk '/^#/{c=substr($$0,3);next}c&&/^[[:alpha:]][[:alnum:]_-]+:/{print substr($$1,1,index($$1,":")),c}1{c=0}' $(MAKEFILE_LIST) | column -s: -t