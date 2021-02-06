module control(in,rt_field,regdest,alusrc,memtoreg,regwrite,memread,memwrite,branch,brancheq,jump,jandl,aluop3,aluop1,aluop2);//why I am taking eq signal because it is going to control the bgez and blez 
input [5:0] in;
input [4:0] rt_field;//--//I added rt field of I-Type because derived branch operations' rt fields are continuation of opcodes
output regdest,alusrc,memtoreg,regwrite,memread,memwrite,branch,brancheq,aluop3,aluop1,aluop2,jump,jandl;
wire rformat,lw,sw,beq,addi,andi,ori,bne,bgez,bgtz,blez,bltz,j,jal;//--//
assign rformat=~|in;
assign lw=in[5]& (~in[4])&(~in[3])&(~in[2])&in[1]&in[0];
assign sw=in[5]& (~in[4])&in[3]&(~in[2])&in[1]&in[0];
assign beq = ~in[5]& (~in[4])&(~in[3])&in[2]&(~in[1])&(~in[0]);//--//
assign bne = ~in[5]& (~in[4])&(~in[3])&in[2]&(~in[1])&in[0];//--//000101
assign addi = ~in[5]& (~in[4])&in[3]&(~in[2])&(~in[1])&(~in[0]);//--//001000
assign andi = ~in[5]& (~in[4])&in[3]&in[2]&(~in[1])&(~in[0]);//--//001100
assign ori = ~in[5]& (~in[4])&in[3]&in[2]&(~in[1])&in[0];//--//001101
assign bgez = (~in[5])& (~in[4])&(~in[3])&(~in[2])&(~in[1])&in[0]&(~rt_field[4])&(~rt_field[3])&(~rt_field[2])&(~rt_field[1])&rt_field[0];//--//000001-00001
assign bgtz = (~in[5])& (~in[4])&(~in[3])&in[2]&in[1]&in[0]&(~rt_field[4])&(~rt_field[3])&(~rt_field[2])&(~rt_field[1])&(~rt_field[0]);//--//000111-00000
assign blez = (~in[5])& (~in[4])&(~in[3])&in[2]&in[1]&(~in[0])&(~rt_field[4])&(~rt_field[3])&(~rt_field[2])&(~rt_field[1])&(~rt_field[0]);//--//000110-00000
assign bltz = (~in[5])& (~in[4])&(~in[3])&(~in[2])&(~in[1])&in[0]&(~rt_field[4])&(~rt_field[3])&(~rt_field[2])&(~rt_field[1])&(~rt_field[0]);//--//000001-00000
assign j = (~in[5])&(~in[4])&(~in[3])&(~in[2])&in[1]&(~in[0]);//--//000010
assign jal = (~in[5])&(~in[4])&(~in[3])&(~in[2])&in[1]&in[0];//--//000011
assign regdest=rformat;
assign alusrc=lw|sw|addi|andi|ori;//--//Alusrc is the sign extended version of theirs
assign memtoreg=lw;
assign regwrite=rformat|lw|addi|andi|ori;//--/we are writing to registers in addi andi and ori
assign memread=lw;
assign memwrite=sw;
assign jump=j|jal;//jal and j is same logic, only difference we are copying pc to ra in jal
assign jandl = jal;//jal
assign brancheq = blez|bgez;//for alu to check zero equality
assign branch=beq|bne|bgez|bgtz|blez|bltz; //branch operations //3'bxyz
assign aluop1=rformat|andi|bgez|bgtz|blez|bltz; //x<Y>z all this aluops explained in alucont.v
assign aluop2=beq|andi|bne|bgez|bgtz; //xy<Z>
assign aluop3=ori|bne|bgez|bgtz|blez|bltz;  //<X>yz
endmodule
