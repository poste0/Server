# The size of shared memory block size
NVCC = nvcc 
CFLAGS = -g -G -O0 -lcublas -lcurand -lcurand_static -lculibos
Add: mainGPU.cpp
	$(NVCC) $(CFLAGS) $< -o $@
