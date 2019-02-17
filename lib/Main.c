#include <jni.h>
#include "Main.h"
#include <stdbool.h>
#include <stdio.h>
#include <stdlib.h>
 #include <malloc.h>
int cmpfunc (const void * a, const void * b) {
    return *(char*)a - *(char*)b;
}

JNIEXPORT jboolean JNICALL Java_Main_square(
    JNIEnv *env, jobject obj, jstring str1 , jstring str2 , jint l1 , jint l2) {
	char* s1;
	s1 = (*env) -> GetStringUTFChars(env , str1 , NULL);
	char* s2;
	s2 = (*env) -> GetStringUTFChars(env , str2 , NULL);
	char* n1 = malloc(l1);
	char* n2 = malloc(l2);
	if(l1 != l2){
		return false;		
		}
	else{
		for(int i = 0 ; i < l1 ; i++){
			n1[i] = s1[i];
			n2[i] = s2[i];
}
		qsort(s1 , l1 , sizeof(char) , cmpfunc);
		qsort(s2 , l2 , sizeof(char) , cmpfunc);
		for(int i = 0 ; i < l1 ; i++){

			if(s1[i] != s2[i]){
				return false;
				}
			}
		return true;
		}
	
}
