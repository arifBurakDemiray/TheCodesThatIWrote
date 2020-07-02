#include<stdio.h>
#include<stdbool.h>
int main(){

    //gireceginiz sayiyi buraya yazin
    int real_x = 1046;
    //x in tam sayi yerini aliyorum
    int n = real_x / 100;
    //x in float kismini aliyorum
    int y = (real_x % 100) * 10 + 3;
    printf("Y value is %d",n);
    printf(".%d", y);
    printf("\n\n");
    
    
    
    //float kismi icin 52 bitlik yer aciyorum
    int bnr[52];  
    int bnr2[4];

    //x in tam sayi yerini binary yapiyorum
    for (int i = 3;i >= 0;i--) {
        bnr2[i] = n % 2;
        n /= 2;

    }
    //x in float kismini binary yapiyorum
    for (int i = 0;i < 52;i++) {
        y *= 2;
        if (y < 1000)
            bnr[i] = 0;
        else {
            bnr[i] = 1;
            y -= 1000;
        }

    };
    //PRINTING THE NUMBER
    printf("Y as a binary number: ");
    for (int i = 0;i < 4;i++) {
        printf("%d", bnr2[i]);
    };
    printf(".");
    for (int i = 0;i < 49;i++) {
        printf("%d", bnr[i]);
    };
    printf("\n\n");

    bool round_up = false;
    //53. bit 0 ise 52. biti 0 yapiyorum
    if (bnr[49] == 0) {
        bnr[48] = 0;
    }
    else {
        //53 ten sonrakilerin hepsi sifirsa
        if (bnr[50] == 0 && bnr[51] == 0) {
            //ama 52. bit 1 ise round up yapiyorum
            if (bnr[48] == 1) {
                round_up = true;
            }//degilse bir sey yapmiyorum
            else {
                round_up = false;
            }
        }//53 ten sonrakiler 0 dan farkli ise round up yapiyorum
        else {
            round_up = true;
        }
    }

    //Round up dogru ise yapiyorm 52. bitten geriye dogru gelerek
    if (round_up) {
        for (int i = 48;i >= 0;i--) {
            if (bnr[i] == 1) {
                bnr[i] = 0;
            }
            else {
                bnr[i] = 1;
                break;
            }
        }
    }

    //PRINTING THE NUMBER
    printf("Rounded binary number: ");
    for (int i = 0;i < 4;i++) {
        printf("%d", bnr2[i]);
    };
    printf(".");
    for (int i = 0;i < 49;i++) {
        //printf("\n%d", i+4);
        printf("%d", bnr[i]);
    };
    return 0;
}


