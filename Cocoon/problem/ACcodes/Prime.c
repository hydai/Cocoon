#include <stdio.h>
#include <string.h>

int main(){
	char a[10001];
	int i, j, t, c, b, ans;
	memset(a, 1, sizeof(a));
	a[0] = 0, a[1] = 0;
	for(i = 2; i < 10001; i++){
		if(a[i])
			for(j = i * i; j < 10001; j += i)
				a[j] = 0;
	}
	scanf("%d", &t);
	while(t--){
		ans = 0;
		scanf("%d %d", &c, &b);
		for(i = c; i <= b; i++)
			if(a[i])
				ans++;
		printf("%d\n", ans);
	}
	return 0;
}
