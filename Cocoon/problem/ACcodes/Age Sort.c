#include<stdio.h>
int main(){
	int a,ct;
	while(scanf("%d",&a)>0,a){
		int i, j, age[120] = {0};
		while(a--)
			scanf("%d",&i), age[i]++;
		for(i = 0,ct = 0; i < 120; i++)
			for(j = 0; j < age[i]; j++)
				if(ct ==0)
					printf("%d",i),ct++;
				else
					printf(" %d",i);
		puts("");
	}
	return 0;
}
