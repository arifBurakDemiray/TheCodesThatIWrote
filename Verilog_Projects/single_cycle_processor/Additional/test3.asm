#This program test the bgez,blez,addi,sw,beq,ori
.text
.globl main
main:
	#####################
	addi $t0,$zero,28
	come:
		blez $t0,Reset
		addi $s0,$zero,12
		move $s1,$t0#here ı did sw in verilog but in qtspim there is an error about bad address so ı did a move here
		bgez $t0,reset2
	Reset:
		ori $t1,$t0,311
		j Goodbye
	reset2:
		addi $t0,$zero,0
		beq $t0,$zero,come
	######################
	Goodbye:
		li $v0,10
		syscall