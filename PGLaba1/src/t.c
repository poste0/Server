 #include <malloc.h>
int cmpfunc (const void * a, const void * b) {
    return *(char*)a - *(char*)b;
}

int main(){
	char* s = "abvc";
	char* s1 = malloc(4);
	char* s2 = "avcb";
	char* s3 = malloc(4);
	for(int i = 0 ; i < 4 ; i++){s1[i] = s[i]; s3[i] = s2[i];}
	qsort(s1 , 4 , sizeof(char) , cmpfunc);
	qsort(s3 , 4 , sizeof(char) , cmpfunc);
	printf(s1);
	printf(s3);

}

