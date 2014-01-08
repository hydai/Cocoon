#include<stdio.h>
int returnvalue(char c){
    switch(c){
        case '{':
            return 1;
        case '[':
            return 2;
        case '(':
            return 3;
        case '<':
            return 4;
        case '}':
            return 5;
        case ']':
            return 6;
        case ')':
            return 7;
        case '>':
            return 8;
        default:
            return 0;
    }
}
int main(){
    int a, i, j, cti = 0;
    char s[1001];
    while(scanf("%d", &a) > 0)
        for(i = 1, getchar(); i <= a; i++){
            cti++;
            char ct[1001] = {0}, fg = 1, top = 0;
            gets(s);
            j = 0;
            while(s[j]){
                int ret = returnvalue(s[j]);
                if(ret > 4){
                    int k;
                    if(ct[--top] != ret - 4){
                        fg = 0;
                        break;
                    }
                    j++;
                    continue;
                }
                if(!ret){
                    j++;
                    continue;
                }
                else{
                    ct[top++] = ret;
                    j++;
                }
            }
            printf("Case %d: ", cti);
            if(fg && !top)
                puts("Yes");
            else
                puts("No");
        }
    return 0;
}
