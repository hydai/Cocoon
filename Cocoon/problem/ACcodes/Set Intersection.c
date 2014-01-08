#include <stdio.h>
#include <stdlib.h>

char data[1000001];
int ans[1000001];

int cmp(const void *a, const void *b){
	return *(int *)a - *(int *)b;
}

int main(){
	int n, m, i, x, tmp;
	while(scanf("%d", &n), n){
		x = 0;
		while(n--){
			scanf("%d", &tmp);
			data[tmp] = 1;
		}
		scanf("%d", &m);
		while(m--){
			scanf("%d", &tmp);
			if(data[tmp])
				ans[x++] = tmp;
		}
		if(x){
			qsort(ans, x, 4, cmp);
			printf("%d", ans[0]);
			for(i = 1; i < x; i++)
				printf(" %d", ans[i]);
			putchar(10);
		}
		else
			puts("empty");
		for(i = 0; i < 1000001; i++)
			data[i] = 0;
	}
	return 0;
}
