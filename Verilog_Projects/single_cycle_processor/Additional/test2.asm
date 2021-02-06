#This program test the sub,and,add,lw,bltz,bgtz,bne
.text
.globl main
main:
	li $a0,16
	li $t6,311
	li $t5,5
	#####################
	LoadUpAgain:
	sub $t3,$a0,$t5
	bltz $t3,Bye
	LoadAgain:
	move $t5,$t6#in verilog I loaded s0 with 1c to go 28. line of data memory, I am doing lw in there
	bne $t5,$t6,Bye
	bgtz $t5,LoadUpAgain
	Bye:
		add $t4,$t5,$t6
		and $t2,$t4,$t3
	######################
	Goodbye:
		li $v0,10
		syscall