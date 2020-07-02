#include<stdio.h>
#include <stdbool.h>
#include <stdlib.h>
#include <string.h>


int *multiply(int pol1[2], int pol2[2]) {
    int i, j;
    int sizePoly1 = 2;
    int sizePoly2 = 2;

    int a[2];
    int b[2];
    static int prod[4];

    for (i = 0;i < sizePoly1;i++)
    {
        a[i] = pol1[i];
    }

    for (i = 0;i < sizePoly2;i++)
    {
        b[i] = pol2[i];
    }
    for (i = 0;i < sizePoly1 + sizePoly2;i++)
    {
        prod[i] = 0;
    }

    for (i = 0;i < sizePoly1;i++)
    {
        for (j = 0;j < sizePoly2;j++)
        {
            if (a[i] != 0 && b[j] != 0)
                prod[i + j] += a[i] * b[j];
        }
    }

    return prod;
}

int main() {
	//writing id
	int id = 250201022;
	//p1~p2~p3 dots
	int p0[2] = { 2,5 };
	int p1[2] = { 2,2 };
	int p2[2] = { 0,2 };
    printf("P0 (%d,%d), P1(%d,%d), P2(%d,%d)\n",p0[0],p0[1],p1[0],p1[1],p2[0],p2[1]);
    
    //This is part 3 calculations, We are doing this operations here because it can get division by zero and jump.
    double midPoint[2] = {((double)(p0[0]-p1[0])/2),((double)(p0[1]-p1[1])/2),};
    double x[4]={(double)p0[0],midPoint[0],midPoint[0],(double)p1[0]},y[4]={(double)p0[1],midPoint[1],midPoint[1],(double)p1[1]};
    double bx = 3*(x[1]-x[0]), cx = 3*(x[2]-x[1]) - bx, dx = x[3] - x[0] - bx - cx;
    double by = 3*(y[1]-y[0]), cy = 3*(y[2]-y[1]) - by, dy = y[3] - y[0] - by - cy;    

    //  y1/(x1-x2)(x1-x3)
    if((p0[0]-p1[0])==0 || (p0[0]-p2[0])==0 || (p1[0]-p2[0]==0)){
        printf("\nDivision by zero!");
        goto DBZ;
    }
    double part1_value = ((double)p0[1] / ((p0[0] - p1[0]) * (p0[0] - p2[0])));

    // (x-x2) => {1,-p1[0]}
    int part11[2] = { 1,-p1[0] };
    // (x-x3) => {1,-p2[0]}
    int part12[2] = { 1,-p2[0] };
    // multiply both of them
    int part1_list[4] = {0};
    int* temp = multiply(part11, part12);
    for(int i=0;i<4;i++)
        part1_list[i]=temp[i];



    //         y2/(x2-x1)(x2-x3)
    double part2_value = ((double)p1[1] / ((p1[0] - p0[0]) * (p1[0] - p2[0])));

    // (x-x1) => {1,-p0[0]}
    int part21[2] = { 1,-p0[0] };
    // (x-x3) => {1,-p2[0]}
    int part22[2] = { 1,-p2[0] };
    // multiply both of them
    int part2_list[4] = {0};
    int* temp1 = multiply(part21, part22);
    for(int i=0;i<4;i++)
        part2_list[i]=temp1[i];



    //         y3/(x3-x1)(x3-x2)
    double part3_value = ((double)p2[1] / ((p2[0] - p0[0]) * (p2[0] - p1[0])));

    // (x-x1) => {1,-p0[0]}
    int part31[2] = { 1,-p0[0] };
    // (x-x2) => {1,-p1[0]}
    int part32[2] = { 1,-p1[0] };
    // multiply both of them
    int part3_list[4] = {0};
    int* temp2 = multiply(part31, part32);
    for(int i=0;i<4;i++)
        part3_list[i]=temp2[i];

    double a0 = (double)(part1_list[0] * part1_value) + (part2_list[0] * part2_value) + (part3_list[0] * part3_value);
    double a1 = (double)(part1_list[1] * part1_value) + (part2_list[1] * part2_value) + (part3_list[1] * part3_value);
    double a2 = (double)(part1_list[2] * part1_value) + (part2_list[2] * part2_value) + (part3_list[2] * part3_value);

    printf("\na0 that is coefficient of x^2: %f", a0);
    printf("\na1 that is coefficient of x: %f", a1);
    printf("\na2 that is constant value: %f", a2);

      {
    DBZ:
    printf("\n\nx(t) = %1.0f + %1.1ft + %1.1ft^2 + %1.1ft^3",x[0],bx,cx,dx);
    printf("\ny(t) = %1.0f + %1.1ft + %1.1ft^2 + %1.1ft^3\n\n",y[0],by,cy,dy);

    }

	return 0;
}