#include <stdio.h>
#include <string.h>

int main(){
	int i;
	char a[1000001];
	while(gets(a) > 0){
		for(i = strlen(a) - 1; i >= 0; i--)
			putchar(a[i]);
		putchar(10);
	}
	return 0;
}
