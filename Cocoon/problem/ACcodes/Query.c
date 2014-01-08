#include<stdio.h>
#include<stdlib.h>
int s[1000001];
int compare(const void *v, const void *o){
    return *(int*)v - *(int*) o;
}
int main(){
    int ct, i, qct, q, fg = 0;
    while(scanf("%d", &ct) > 0, ct){
        if(fg)
            puts("");
        else
            fg++;
        for(i = 0; i < ct ; i++)
            scanf("%d", s + i);
        qsort(s, ct, sizeof(int), compare);
        scanf("%d", &qct);
        while(qct--){
            scanf("%d", &q);
            if((int*)bsearch(&q,s, ct, sizeof(int), compare))
                puts("Yes");
            else
                puts("No");
        }
    }
    return 0;
}
