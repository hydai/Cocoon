#include <cstdio>
#include <unistd>
#include <cstdlib>
#include <iostream>
#include <cstring>
#include <string>
using namespace std;

const int SUCCESS = 0;
const int FITAL = 1;
const int TLE = 2;
const int MLE = 3;
const int RE = 4;


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
		exit(
	}


}
