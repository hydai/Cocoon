#include <stdio.h>

long long int sum[100001];

int main(){
	int t, n, i, q, tmp, a, b;
	scanf("%d", &t);
	while(t--){
		scanf("%d", &n);
		for(i = 1; i <= n; i++){
			scanf("%d", &tmp);
			sum[i] = sum[i - 1] + tmp;
		}
		scanf("%d", &q);
		while(q--){
			scanf("%d %d", &a, &b);
			printf("%lld\n", sum[b] - sum[a - 1]);
		}
	}
	return 0;
}
