#include<stdio.h>
#include<ctype.h>
char tolower_(char c){
    if(isupper(c))
        return c - 'A' + 'a';
    return c;
}
int main(){
    int t;
    char s1[10001] = {0}, s2[10001] = {0};
    while(scanf("%s%s", s1, s2) > 0){
            int i = 0;
            while(s1[i] == s2[i] && i < 10001)
                i++;
            if(isalpha(s1[i]) && isalpha(s2[i])){
                if(tolower_(s1[i]) == tolower_(s2[i])){
                    if(s1[i] > s2[i])
                        printf("%s %s\n", s2, s1);
                    else
                        printf("%s %s\n", s1, s2);
                }
                else if(tolower_(s1[i]) > tolower_(s2[i]))
                    printf("%s %s\n", s2, s1);
                else
                    printf("%s %s\n", s1, s2);
            }
            else
                if(s1[i] > s2[i])
                    printf("%s %s\n", s2, s1);
                else
                    printf("%s %s\n", s1, s2);
        }
    return 0;
}
