#include <stdio.h>
#include <stdlib.h>

int main()
{
    unsigned long val;
    unsigned long cou;
    while(EOF!=scanf("%ld",&val)){
        cou=0;
        while(val!=1){
            if(val%2==0) val=val/2;
            else val=3*val+1;
            cou++;
        }
        printf("%ld\n",cou);
    }
    return 0;
}
