#include <stdio.h>
#include <stdlib.h>
int main(int argc, char *argv[]) {
   FILE *result, *output, *answer;
   if (argc < 3) {
      printf("Usage: ./isCorrect output answer");
      return 1;
   }
   output = fopen(argv[1], "r");
   answer = fopen(argv[2], "r");
   result = fopen("result", "w");

   char a, b;
   while((a = fgetc(output)) == (b = fgetc(answer)));
   if (a != b)
      fprintf(result, "Wrong Answer\n");
   else
      fprintf(result, "Accept\n");
   fclose(output);
   fclose(answer);
   fclose(result);

   return 0;
}
