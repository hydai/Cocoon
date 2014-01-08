#include <stdio.h>

int main(){
	int n, p, max;
	while(scanf("%d", &n), n){
		int t[1001] = {};
		max = 0;
		while(n--){
			scanf("%d", &p);
			t[p]++;
		}
		for(p = 1000; p > 0; p--)
			if(t[max] <= t[p])
				max = p;
		printf("%d\n", max);
	}
	return 0;
}
