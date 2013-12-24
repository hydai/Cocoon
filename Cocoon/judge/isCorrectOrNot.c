#include <stdio.h>
#include <stdlib.h>
int main(int argc, char *argv[]) {
   FILE *result, *answer;
   result = fopen("result", "w");
   if (argc < 2) {
      fprintf(result, "CE");
      return 1;
   }
   else {
      answer = fopen(argv[1], "r");
      char a;
      if ((a = fgetc(answer)) != EOF) 
         fprintf(result, "Wrong Answer\n");
      else
         fprintf(result, "Accept\n");
      fclose(answer);
      fclose(result);
   }
   return 0;
}
