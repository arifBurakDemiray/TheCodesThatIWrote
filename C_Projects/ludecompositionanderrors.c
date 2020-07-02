#include<stdio.h>
int main()
{
    // Your number is ABCDEFGHI
    // Row 1 A  D    G
    // Row 2 B E+0.5 H
    // Row 3 C  F    I
    float A[3][3]= {{2,2,0},{5,0.5,2},{0,1,2}};
    float L[3][3]= {0}, U[3][3];
    float B[3]= {5,12,8}, Xa[3]={1,1,1}, X[3]= {0},c[3]= {0};
    int i,j,k;
    int n = 3;
    //finding u and l
    for(j=0; j<n; j++){
        for(i=0; i<n; i++){
            if(i<=j){
                U[i][j]=A[i][j];
                for(k=0; k<=i-1; k++)
                    U[i][j]-=L[i][k]*U[k][j];
                if(i==j)
                    L[i][j]=1;
                else
                    L[i][j]=0;
            }
            else{
                L[i][j]=A[i][j];
                for(k=0; k<=j-1; k++)
                    L[i][j]-=L[i][k]*U[k][j];
                L[i][j]/=U[j][j];
                U[i][j]=0;
            }
        }
    }
    printf("[A]: \n");
    for(i=0; i<n; i++){
        for(j=0; j<n; j++)
            printf("%9.4f",A[i][j]);
        printf("\n");
    }
    printf("\n\n[B]: \n");
    for(i=0; i<n; i++){
        printf("%9.4f",B[i]);
    }
    printf("\n\n[L]: \n");
    for(i=0; i<n; i++){
        for(j=0; j<n; j++)
            printf("%9.4f",L[i][j]);
        printf("\n");
    }
    printf("\n\n[U]: \n");
    for(i=0; i<n; i++){
        for(j=0; j<n; j++)
            printf("%9.4f",U[i][j]);
        printf("\n");
    }
    for(i=0; i<n; i++){
        c[i]=B[i];
        for(j=0; j<i; j++)
        {
            c[i]-=L[i][j]*c[j];
        }
    }
    printf("\n\n[c]: \n");
    for(i=0; i<n; i++){
        printf("%9.4f",c[i]);
    }
    for(i=n-1; i>=0; i--){
        X[i]= c[i];
        for(j=i+1; j<n; j++)
        {
            X[i]-=U[i][j]*X[j];
        }
        X[i]/=U[i][i];
    }
    printf("\n\n[X]: \n");
    for(i=0; i<n; i++){
        printf("%9.4f",X[i]);
    }
    printf("\n\n[Xa]: \n");
    for(i=0; i<n; i++){
        printf("%9.4f",Xa[i]);
    }
    //this part is for finding error part
    float maxA=0,maxX=0,maxB=0,maxR=0;
    for(i=0;i<n;i++){
        float temp=0,temp1=0,tempB=0,tempR=0,rowTotal=0;
        if(X[i]-Xa[i]<0)
            temp = (-1)*(X[i]-Xa[i]);
        else
            temp = X[i]-Xa[i];
        if(temp > maxA)
            maxA = temp;
        if(X[i]>0)
            temp1 = X[i];
        else
            temp1= -1 * X[i];
        if(temp1>maxX)
            maxX = temp1; 
        for(j=0;j<n;j++)
            rowTotal+=A[i][j]*Xa[j];
        if(B[i]-rowTotal>0)
            tempR = B[i]-rowTotal;
        else
            tempR = -1*(B[i]-rowTotal);
        if(tempR>maxR)
            maxR = tempR;
        if(B[i]>0)
            tempB = B[i];
        else
            tempB= -1 * B[i];
        if(tempB>maxB)
            maxB = tempB;
    }
    float rf = maxA / maxX;
    float rb = maxR / maxB;
    float emf = rf / rb;
    printf("\n\n Relative Forward Error: %9.4f",rf);
    printf("\n\n Relative Backward Error: %9.4f",rb);
    printf("\n\n Error Magnification Factor: %9.4f",emf);
    return 0;
}
