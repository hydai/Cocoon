#include<stdio.h>
int main(){
    int t, i, c;
    char s[21];
    while(scanf("%d", &t) > 0)
        while(t--){
            scanf("%d", &c);
            for(i = 1; i <= c; i++){
                scanf("%s", s);
                printf("%20s", s);
                if(i % 4 == 0)
                    putchar('\n');
            }
            if(c % 4 != 0)
                putchar('\n');
            putchar('\n');
        }
    return 0;
}
