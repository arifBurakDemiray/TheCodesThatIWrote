module alucont(aluop2,aluop1,aluop0,f3,f2,f1,f0,gout,jr,notor);//Figure 4.12 
input aluop2,aluop1,aluop0,f3,f2,f1,f0;
output [2:0] gout;
output reg jr,notor;	//--//jr signal to copy to ra register unit, and notor signal to get the not orred version of or
reg [2:0] gout;
always @(aluop2 or aluop1 or aluop0 or f3 or f2 or f1 or f0)
begin
jr=0; //initialize
notor=0; //initialize
if((~(aluop1|aluop0))&(~aluop2))  gout=3'b010;		//if aluops 000 It is LW,SW and also ADDI
else if(aluop2&(~aluop1)&aluop0) gout=3'b011; 		//if aluops 101 means BNE
else if(aluop2&aluop1&aluop0) gout=3'b100; 			//if aluops 111 means BGTZ and BGEZ
else if(aluop2&aluop1&(~aluop0)) gout=3'b101;  		//if aluops 110 means BLTZ and BLEZ
else if(aluop1&aluop0) gout=3'b000; 					//if aluops x11 means ANDI
else if(aluop2) gout=3'b001; 							//if aluops 1xx means ORI
else if(aluop0&(~aluop1))gout=3'b110; 					//if aluops x01 means BEQ
else if(aluop1&(~aluop0))									//if aluops x10 means R-type 
begin		//jr signal are checked here because it is r type instruction
	if (f3&(~f2)&(~f1)&(~f0))  			//jr func code is xx1000
	begin 
		jr = 1;
		gout =3'b000;	//just making it initialize
	end
	else if ((~f3)&f2&f1&f0) begin gout=3'b001; notor = 1; end //--//func code 0111 NOR means Not Or so I give a nor signal to alu
	else if (~(f3|f2|f1|f0))gout=3'b010; 	//function code=0000,ALU control=010 (add)
	else if (f1&f3)gout=3'b111;			//function code=1x1x,ALU control=111 (set on less than)
	else if (f1&~(f3))gout=3'b110;		//function code=0x10,ALU control=110 (sub)
	else if (f2&f0)gout=3'b001;			//function code=x1x1,ALU control=001 (or)
	else if (f2&~(f0))gout=3'b000;		//function code=x1x0,ALU control=000 (and)
end
end
endmodule
