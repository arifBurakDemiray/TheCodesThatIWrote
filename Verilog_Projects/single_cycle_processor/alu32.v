module alu32(brancheq,sum,a,b,zout,gin,notor);//ALU operation according to the ALU control line values
output [31:0] sum;
input [31:0] a,b; 
input [2:0] gin;//ALU control line
input brancheq,notor;//this control signal is for blez and bgez
reg [31:0] sum;
reg [31:0] less;
output zout;
reg zout;
always @(a or b or gin)
begin
	case(gin)
	3'b010: sum=a+b; 		//ALU control line=010, ADD
	3'b110: sum=a+1+(~b);	//ALU control line=110, SUB
	3'b111: begin less=a+1+(~b);	//ALU control line=111, set on less than
			if (less[31]) sum=1;	
			else sum=0;
			end
	3'b000: sum=a & b;	//ALU control line=000, AND
	3'b001: sum = notor ? ~(a|b): a|b;		//ALU control line=001, OR and NOTOR notor = 1
	3'b011: sum=a+1+(~b);  //AlU control line=011 BNE
	3'b100: begin  //AlU control line=100 branceq=0 BGTZ, line=100 branceq=1 BGEZ
			zout= brancheq ? (a==0 ? 1:((~a[31]) ? 1:0)):((~a[31]) ? (a==0 ? 0:1):0); //Why I am checking only 32nd bit?
			sum = a; end //For initializing sum										//because 32nd bit is sign bit it tells me	
	3'b101: begin //AlU control line=101 branceq=0 BLTZ, line=101 branceq=1 BLEZ 	whether it is below zero or above zero
			zout= brancheq ? (a==0 ? 1:(a[31] ? 1:0)):(a[31] ? (a==0 ? 0:1):0);  //why I am checkink equality in not equal conditions
			sum = a; end //For initializing sum									//because I do not want it to be equal in brancheq=0 condition
	default: sum=31'bx;	
	endcase
//if signal is 011, which is bne, it gives orred version of sum
//if signals are 100 or 101 it do not changes zout
zout= gin!=3'b011 ? (gin==3'b100 ? zout:(gin==3'b101 ? zout : ~(|sum))):(|sum);
end
endmodule
