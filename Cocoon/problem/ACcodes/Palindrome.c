include<stdio.h>
#include<string.h>

int main(void)
{
	char s[100005];
	while(gets(s) != NULL) {
		int i, l = strlen(s), flag = 1, m = l >> 1;
		for(i = 0;i < m;i++) if(s[i] != s[l - i - 1]) { flag = 0; break; }
		if(flag) printf("Yes\n"); else printf("No\n");
	}
	return 0;
}
