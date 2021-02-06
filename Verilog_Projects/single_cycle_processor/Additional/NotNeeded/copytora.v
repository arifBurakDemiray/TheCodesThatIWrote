module copytora(jal,pc,ra);
input [31:0] pc,ra;//takes program counter and ra register
input jal; //control signal
assign ra = jal ? pc:ra; //stores pc in ra if signal is 1
endmodule
