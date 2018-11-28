__global__ void addKernel(int *c,  int *a,  int *b, unsigned int size)
{
	// ��� ������� ����
int i = blockIdx.x *blockDim.x + threadIdx.x;
c[i] = a[i] + b[i];
}

#define kernel addKernel
#include "mainGPU.h"

