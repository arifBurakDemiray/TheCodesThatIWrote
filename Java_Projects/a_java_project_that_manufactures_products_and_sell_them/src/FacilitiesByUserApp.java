
import java.util.InputMismatchException;
import java.util.Scanner;

import Program.IPreProgram;
import Program.PreProgram;


/**
 * 
 * @author Arif Burak Demiray 
 *
 */




public class FacilitiesByUserApp {

	public static void main(String[] args) {
		IPreProgram prgm = new PreProgram();
		prgm.program();
		@SuppressWarnings("resource")
		Scanner s1 = new Scanner(System.in);
		int bolum = 0;
		int sayı = 0;
		
        try{
            int num1 = s1.nextInt();
            int num2 = s1.nextInt();
            bolum = num1/num2;
            
        }
        catch(ArithmeticException e){
            System.out.println("java.lang.ArithmeticException: / by zero");
            sayı = 1;

        }
        catch(InputMismatchException e){
            System.out.println("java.util.InputMismatchException");
            sayı  = 1;

        }
        if(sayı == 0) {
        	System.out.println(bolum);
        }
        
        

	}

}
