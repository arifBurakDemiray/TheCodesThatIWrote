module shiftjump(out,in,pcin);//it takes instructor [25:0] and pc+4 [31:28] and gives 2 shifted version
output [31:0] out;
input [25:0] in;
input [3:0] pcin;
wire [27:0] shifted;
assign shifted=in<<2;//shifts by 2
assign out = {pcin,shifted};//concatenates
endmodule
