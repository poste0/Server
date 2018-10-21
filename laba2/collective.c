#include <math.h>
#include <stdio.h>
#include <stdlib.h>
#include "mpi.h"

int main(int argc, char* argv[])
{
	double TotalSum, ProcSum = 0.0;
	int ProcRank, ProcNum, N=4s;
	MPI_Status Status;

	double st_time, end_time;
	
	MPI_Init(&argc,&argv);
	MPI_Comm_size(MPI_COMM_WORLD,&ProcNum);
	MPI_Comm_rank(MPI_COMM_WORLD,&ProcRank);
	double* a;
	if (ProcRank==0)
	{
	a = new double[N];	
	for(int i=0; i<N; i++){
	a[i] = rand();
	}
	
	}
	
	double* b = new double[N / ProcNum];
	MPI_Scatter(a, N / ProcNum, MPI_DOUBLE, b, N / ProcNum, MPI_DOUBLE, 0, MPI_COMM_WORLD);

	st_time = MPI_Wtime();

	for ( i = 0; i < N/ProcNum; i++ )
		ProcSum = ProcSum + b[i];
	MPI_Reduce(&ProcSum, &TotalSum, 1, MPI_DOUBLE, MPI_SUM, 0, MPI_COMM_WORLD);

	MPI_Barrier(MPI_COMM_WORLD);

	end_time = MPI_Wtime();
	end_time = end_time - st_time;

	if ( ProcRank == 0 )
	{
		cout << "Sum " << TotalSum << endl;
		cout << "Time " << end_time << endl;
	}
	delete[] a;
	delete[] b;
	MPI_Finalize();
	return 0;
}
