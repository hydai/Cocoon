#include<stdio.h>
int pas[1001][1001] = {0};
int main(){
    int i, j;
    pas[1][0] = 1, pas[1][1] = 1;
    for(i = 1; i < 1001; i++)
        pas[i][0] = 1;
    for(i = 2; i < 1001; i++)
        for(j = 1; j <= i; j++)
            pas[i][j] = (pas[i-1][j] + pas[i-1][j-1]) % 1000007;
    while(scanf("%d",&j)>0){
        printf("%d",pas[j][0]);
        for(i = 1; i <= j; i++)
            printf(" %d",pas[j][i]);
        putchar('\n');
    }
    return 0;
}
