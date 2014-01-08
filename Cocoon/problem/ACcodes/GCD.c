#include <stdio.h>
int GCD(int a, int b) {
	while (a > 0 && b > 0 && (a%=b) && (b%=a));
	return a+b;
}

int main() {
	int a, b, t;
	scanf("%d", &t);
	while(t--) {
		scanf("%d%d", &a, &b);
		printf("%d\n", GCD(a, b));
	}
	return 0;
}
