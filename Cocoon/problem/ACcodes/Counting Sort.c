#include <stdio.h>
#include <string.h>

int count[10001];

int main(){
	int n, i, t, x;
	while(scanf("%d", &n) > 0){
		memset(count, 0, sizeof(count));
		while(n--){
			scanf("%d", &x);
			count[x]++;
		}
		t = 0;
		for(i = 0; i < 10001; i++){
			if(count[i]){
				if(t++) putchar(' ');
				printf("%d %d", count[i], i);
			}
		}
		putchar(10);
	}
	return 0;
}
