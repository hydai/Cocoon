#include <cstdio>
#include <unistd>
#include <cstdlib>
#include <iostream>
#include <cstring>
#include <string>
using namespace std;

void parserOpt(int argc, char *argv[]);
void showHelp();
int main(int argc, char *argv[]) {
	parserOpt(argc, argv);
	return 0;
}

void parserOpt(int argc, char *argv[]) {
	char c;

	// no input data, show help message and exit
	if (argc < 2) {
		showHelp();
	}
	else {
		while( (c = get_opt(argc, argv, "t:P:T:I:s")) != -1) {
			switch (c) {
				
			}
		}
	}
}

void showHelp() {
	printf("Usage: judge [Option] -P problemID -T sourceType -I *.c/*.cpp\n");
	printf("Option:\n");
	printf("    -t  time limit (default 1000, unit ms)\n");
	printf("    -s  use strict judge; otherwise, use tolorent judge\n");
	printf("Output:\n");
	printf("    1. Compiler Error with error message\n");
	printf("    2. Runtime Error\n");
	printf("    3. Time Limit Exceeded\n");
	printf("    4. Memery Limit Exceeded\n");
	printf("    5. Wrong Answer with error message\n");
	printf("    6. Accept\n");
}
