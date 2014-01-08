#include<stdio.h>
#include<string.h>
#include<ctype.h>
char s[10][1001], temp[1001];
int main(){
    int a, b, i, j, tot;
    while(scanf("%d %d", &a, &b) > 0){
        getchar();
        for(i = 0; i < a; i++){
            gets(s[i]);
            j = -1;
            while(s[i][++j])
                if(isupper(s[i][j]))
                    s[i][j] = s[i][j] - 'A' + 'a';
        }
        for(i = 0; i < b; i++){
            scanf("%s", temp), printf("%s:", temp);
            j = -1;
            while(temp[++j])
                if(isupper(temp[j]))
                    temp[j] = temp[j] - 'A' + 'a';
            tot = 0;
            for(j = 0; j < a; j++){
                char *p = strstr(s[j], temp);
                while(p)
                    p++, p = strstr(p, temp), tot++;
            }
            printf("%d\n", tot);
        }
        putchar(10);
    }
    return 0;
}
