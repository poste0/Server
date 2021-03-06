#include <stdlib.h>

#include <iostream>

#include "mpi.h"

using namespace std;


main(int argc, char* argv[])

{

	int N = 256;
	double* a, *b , *c , j, w;

	double *a_loc, *b_loc, *c_loc;

	int ProcRank, ProcNum, N; 

	MPI_Status Status;

	double st_time, end_time, time=0;

	MPI_Init(&argc,&argv);

	MPI_Comm_size(MPI_COMM_WORLD,&ProcNum);

	MPI_Comm_rank(MPI_COMM_WORLD,&ProcRank);

	int count=N/ProcNum+((N%ProcNum==0)?0:1);
	int d = count*ProcNum;



	if (ProcRank == 0)

{
		
		a=new double[d];

		b=new double[d];

		c=new double[d];

		for(i=0; i<N; i++){

		a[i]=rand();

		b[i]=rand();

}
	

		for(int i=N; i<d; i++){

			a[i]=0;
			b[i]=0;
}

}
		



	
	a_loc = new double[count];

	b_loc = new double[count];

	c_loc = new double[count];

	st_time = MPI_Wtime();

	MPI_Scatter(a, count, MPI_INT, a_loc, count, MPI_INT, 0, MPI_COMM_WORLD);

	MPI_Scatter(b, count, MPI_INT, b_loc, count, MPI_INT, 0, MPI_COMM_WORLD);

	if (ProcRank == 0)

{

	delete[] a;
	delete[] b;

}


	for(int i = 0; i<count; i++){

		c_loc[i] = a_loc[i] + b_loc[i];

}

	delete[] a_loc;
	delete[] b_loc;



	MPI_Gather(c_loc, count, MPI_INT, c, count, MPI_INT, 0, MPI_COMM_WORLD);

	delete[] c_loc;

	end_time = MPI_Wtime();

	time = end_time - st_time;
		
	if ( ProcRank == 0 )

{

	cout << "Time " << time << endl;

	delete[] c;

}

	MPI_Finalize();

	return 0;

}
