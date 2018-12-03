#include<mpi.h>
#include<stdio.h>
#include<stdlib.h>
#include <time.h>
#include<iostream>
double* init(int);
double* initZero(int);
void print(double* , double* , double* , int);
void printOne(double* , int , int);
double* toLoc(double* , int , int , int n);
int left(int , int);
int right(int , int);
void mul(double* , double* , double* , int , int);
void toGlobal(double* , double* , int , int , int);
using namespace std;
int main(int argc,char **argv){
	srand(time(NULL));
	int n = 2;
	double* A = init(n);
	double* B = init(n);
	double* C = initZero(n);
	//print(A , B , C , 2);
	//mul(A , B , C , 2 , 2);
	//print(A , B , C , 2);
	double st_time, end_time;
	int status = 0;
	MPI_Status Status;
	int ProcRank;
	int ProcNum;
	//print(A , B , C , n);
	MPI_Init(&argc, &argv);
	MPI_Comm_size(MPI_COMM_WORLD, &ProcNum);
	MPI_Comm_rank(MPI_COMM_WORLD, &ProcRank);
	int p = n / ProcNum;
	//printf("%d" , ProcRank);	
	double* A_loc = toLoc(A , ProcRank*p , ProcRank*p + p  , n);
	double* B_loc = toLoc(B , ProcRank*p , ProcRank*p + p  , n);
	double* C_loc = toLoc(C , ProcRank*p , ProcRank*p + p  , n);
	st_time = MPI_Wtime();
	if(ProcNum == 1){
	st_time = MPI_Wtime();
	mul(A , B , C , n , n);
	end_time = MPI_Wtime();
	cout << "Time "<< end_time - st_time<<endl;
	cout<<"S";
	return 0;
}
	for(int i = 0 ; i < p ; i++){
		//if (i != p - 1) {
		//cout<<"Sending "<<ProcRank<<" "<<right(ProcRank , ProcNum)<<" " << left(ProcRank , ProcNum)<<endl;
		MPI_Send(B_loc , p * n , MPI_DOUBLE , right(ProcRank , ProcNum) , 0 , MPI_COMM_WORLD);
	//cout<<"Receiving "<<ProcRank<<" "<<right(ProcRank , ProcNum)<<" " << left(ProcRank , ProcNum)<<endl;
		//printf("Receiving\n");
		MPI_Recv(B_loc , p * n , MPI_DOUBLE , left(ProcRank , ProcNum) , 0 , MPI_COMM_WORLD , &Status);
		//}
		mul(A_loc , B_loc , C_loc , n , p);
}
	end_time = MPI_Wtime();
	cout << "Time "<< end_time - st_time<<endl;
	//printOne(A_loc , p , n);
	toGlobal(C_loc , C , ProcRank*p , ProcRank*p + p , n);
	printOne(C_loc , p , n);
	if(ProcRank == 0){
		print(A , B , C , n);
		
	}
	
	MPI_Finalize();
	//cout<<"S"<<endl;
	
		//print(A , B , C , n);
		
	
	//printOne(C_loc , p , n);
	
	return 0;
}

double* init(int n){
	double* result = (double*)malloc(n * n * sizeof(double));
	for(int i = 0 ; i < n ; i ++){
		for(int j = 0 ; j < n ; j ++){
			result[i * n + j] = rand() % 10;
		}
	}
	return result;
}
double* initZero(int n){
	double* result = (double*)malloc(n * n * sizeof(double));
	for(int i = 0 ; i < n ; i ++){
		for(int j = 0 ; j < n ; j ++){
			result[i * n + j] = 0;
		}
	}
	return result;
}
void print(double* A , double* B , double* C , int n){
	printf("A : \n");
	for(int i = 0 ; i < n ; i ++){
		for(int j = 0 ; j < n ; j ++){
			printf("%f" , A[i * n + j]);
			printf(" ");
		}
		printf("\n");
	}
	printf("B : \n");
	for(int i = 0 ; i < n ; i ++){
		for(int j = 0 ; j < n ; j ++){
			printf("%f" , B[i * n + j]);
			printf(" ");
		}
		printf("\n");
	}
	printf("C : \n");
	for(int i = 0 ; i < n ; i ++){
		for(int j = 0 ; j < n ; j ++){
			printf("%f" , C[i * n + j]);
			printf(" ");
		}
		printf("\n");
	}
}
void printOne(double* A , int m , int n){
	printf("R : \n");
	for(int i = 0 ; i < m ; i ++){
		for(int j = 0 ; j < n ; j ++){
			printf("%f" , A[i * n + j]);
			printf(" ");
		}
		printf("\n");
	}
}
double* toLoc(double* A , int from , int to , int n){
	//printf("%d" , from);
	//printf(" TO ");
	//printf("%d" , to);
	double* result = (double*)malloc((to - from) * n * sizeof(double));
	for(int i = 0 ; i < to - from ; i ++){
		for(int j = 0 ; j < n ; j++){
			result[i*n + j] = A[from*n + i*n + j];
}
}
	return result;
}
int left(int x , int p){
	x--;
if (x < 0) {
x = p + x;
}
return x % p;
}
int right(int x, int p ){
	return (x + 1) % p;
}
void mul(double* A , double* B , double* C , int n , int p){
	//cout<<n << " "<<p;
	for(int i = 0 ; i < p ; i++){
		for(int j = 0 ; j < n ; j ++){
			for(int k = 0 ; k < p ; k++){
				C[i*n + j] += A[i*n + k]*B[k*n +j];
}
}
}
}
void toGlobal(double* A , double* C , int from , int to , int n){
	for(int i = 0 ; i < to - from ; i ++){
		for(int j = 0 ; j < n ; j++){
			C[i*n + j] = A[from*n + i*n + j];
}
}
}
