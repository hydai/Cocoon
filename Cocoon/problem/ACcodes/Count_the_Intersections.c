#include <stdio.h>

int main(){
	long long int n;
	while(scanf("%lld", &n), n){
		printf("%lld\n", n * (n - 1) / 2 % 10000);
	}
	return 0;
}
