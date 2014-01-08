#include<stdio.h>
void swap(int s[], int n){
    int i, j, temp;
    for(i = 0, j = n; i < j; i++, j--)
        temp = s[i], s[i] = s[j], s[j] = temp;
}
int main(){
    int t;
    while(scanf("%d", &t) > 0)while(t--){
        int n1, n2, i, max, min;
        scanf("%d%d", &n1, &n2);
        int s1[10000] = {0}, s2[10000] = {0};
        for(i = 0; i <= n1; i++)
            scanf("%d", s1 + i);
        for(i = 0; i <= n2; i++)
            scanf("%d", s2 + i);
        swap(s1, n1);
        swap(s2, n2);
        min = n1, max = n1;
        if(max < n2)
            max = n2;
        if(min > n2)
            min = n2;
        int ans[10000] = {0};
        for(i = 0; i <= max; i++)
            ans[i] = s1[i] + s2[i];
        for(i = max; i >= 0; i--)
            if(i == max)
                printf("%d", ans[i]);
            else
                printf(" %d", ans[i]);
        putchar(10);
    }
    return 0;
}
