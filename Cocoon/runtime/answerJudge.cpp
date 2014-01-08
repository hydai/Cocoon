#include <cstdio>
#include <string>
#include <cstring>
#include <cctype>
#include <algorithm>
#define MAX_LEN 1000001
using namespace std;

char ans_str[MAX_LEN+1], output_str[MAX_LEN+1];

void print_diff(char ans_str[], char output_str[], bool is_strict){
	int ans_len = strlen(ans_str), output_len = strlen(output_str);

	/*if(is_strict){
		if(ans_str[ans_len-1] == '\n')
			ans_str[ans_len-1] = 0;
		if(output_str[output_len-1] == '\n')
			output_str[output_len-1] = 0;
		ans_len = strlen(ans_str);
		output_len = strlen(output_str);
		min_len = min(ans_len, output_len);
		max_len = max(ans_len, output_len);
	}*/
	if(ans_str[ans_len-1] == 10 && output_str[output_len-1] == 10){
		ans_str[ans_len-1] = output_str[output_len-1] = 0;
		ans_len--;
		output_len--;
	}
	int min_len = (ans_len < output_len ? ans_len : output_len);
	int max_len = (ans_len > output_len ? ans_len : output_len);
	//printf("in:%d\nout:%d\n", ans_len, output_len);
	//printf("%d %d\n", min_len, max_len);
	puts("Answer:");
	puts(ans_str);
	puts("Your output:");
	puts(output_str);
	for(int i = 0; i < min_len; i++)
		if(ans_str[i] != output_str[i])
			putchar('^');
		else
			putchar(' ');
	for(int i = min_len; i < max_len; i++)
		putchar('^');
	putchar('\n');
}

bool compare_line(char ans_str[], char output_str[], bool is_strict){
	if(is_strict){
		return !strcmp(ans_str, output_str);
	}
	else{
		int ans_len = strlen(ans_str), output_len = strlen(output_str);
		while(ans_len >= 0 && !isgraph(ans_str[ans_len]))
			ans_len--;
		while(output_len >= 0 && !isgraph(output_str[output_len]))
			output_len--;
		ans_str[ans_len+1] = '\0';
		output_str[output_len+1] = '\0';
		return !strcmp(ans_str, output_str);
	}
}

void judge(FILE *ans, FILE *output, bool is_strict){
	int line = 0;
	char *f1, *f2;
	while((f1 = fgets(ans_str, MAX_LEN, ans)) > 0 && (f2 = fgets(output_str, MAX_LEN, output)) > 0){
		++line;
		if(!compare_line(ans_str, output_str, is_strict)){
			puts("WrongAnswer");
			printf("Line %d:\n", line);
			print_diff(ans_str, output_str, is_strict);
			return;
		}
	}
	f2 = fgets(output_str, MAX_LEN, output);
	if((f1 == 0) ^ (f2 == 0)){
		puts("WrongAnswer");
		printf("Incompatible lines");
		if(f1 == 0)
			puts(" (output more lines than answer)");
		else
			puts(" (output less lines than answer)");
		return;
	}
	puts("Accept");
}

int main(int argc, char **argv){
	FILE *ans, *output;
	if(argc == 4 && (!strcmp(argv[1], "-s") || !strcmp(argv[1], "-t"))){
		ans = fopen(argv[2], "r");
		output = fopen(argv[3], "r");
		if(ans == NULL || output == NULL){
			puts("Can't open file");
			return 1;
		}
		if(!strcmp(argv[1], "-s")){
			judge(ans, output, true);
		}
		else if(!strcmp(argv[1], "-t")){
			judge(ans, output, false);
		}
	}
	else{
		puts("Usage: [-s|-t] answer output");
		return 1;
	}
	return 0;
}
