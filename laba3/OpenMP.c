#include <omp.h>
#include <iostream>
#include <stdlib.h>
using namespace std;

int main(int argc, char** argv){
	srand (time(NULL));
	int countOfThreads;
	double* a , *sum , *b;
	int arraySize;
	int type;
	cout << "Please enter count of threads" << endl;
	cin >> countOfThreads;
	cout << "Please enter size of the array" << endl;
	cin >> arraySize;
	cout << "Choose schedule type:\n1 - static\n 2 - dynamic\n3 guided" << endl;
	cin >> type;
	cout<<type<<endl;
	a = new double[arraySize];
	sum = new double[arraySize];
	b = new double[arraySize];
	omp_set_num_threads(countOfThreads);
	for(int i=0; i < arraySize; i++){
		a[i]=rand();
		b[i]=rand();

	}


	double st_time, end_time;
	st_time = omp_get_wtime();
	if(type == 1){
		cout << "Static" << endl; 
		#pragma omp for schedule(static)
		for (int i=0; i < arraySize ; i++) {
		sum[i]=a[i]+b[i];
		}
	}
	else if(type == 2){
		cout << "Dynamic" << endl; 
		#pragma omp for schedule(dynamic)
		for (int i=0; i < arraySize ; i++) {
		sum[i]=a[i]+b[i];
		}
	}
	else if(type == 3){
		cout << "Guided" << endl; 
		#pragma omp for schedule(guided)
		for (int i=0; i < arraySize ; i++) {
		sum[i]=a[i]+b[i];
		}
	}
	else{
		cout << "None" << endl;
		for (int i=0; i < arraySize ; i++) {
		sum[i]=a[i]+b[i];
		} 
	}
	
	end_time = omp_get_wtime ();
	cout << "Time = " << end_time - st_time << endl;
	for (int i = 0; i < arraySize; i++){
		cout << sum[i] << endl;
	}
	delete[] a;
	delete[] b;
	delete[] sum;
	return 0;
}
