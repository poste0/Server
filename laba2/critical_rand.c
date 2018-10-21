#include <omp.h>
#include <iostream>
#include <stdlib.h>
using namespace std;

int main(int argc, char* argv[]){
	srand (time(NULL));
	int countOfThreads;
	double* a;
	double sum;
	int arraySize;
	cout << "Please enter count of threads" << endl;
	cin >> countOfThreads;
	cout << "Please enter size of the array" << endl;
	cin >> arraySize;
	a = new double[arraySize];
	omp_set_num_threads(countOfThreads);
	for (int i = 0; i < arraySize; i++)
	{
		a[i] = rand(); 
	}
	double st_time, end_time;
	st_time = omp_get_wtime();
	sum = 0;
	#pragma omp parallel for shared(a) private(i)
	for (int i = 0; i < arraySize; i++){
		#pragma omp critical 
		sum = sum + a[i];
	}
	delete[] a;
	end_time = omp_get_wtime ();
	end_time = end_time - st_time;
	cout << "Sum = " << sum << endl;
	cout << "Time = " << end_time << endl;
	return 0;
}
