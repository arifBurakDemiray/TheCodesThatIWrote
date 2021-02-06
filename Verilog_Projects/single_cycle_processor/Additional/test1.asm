#This program test the nor,andi,addi,j,jr,jal,ori
.text
.globl main
main:
	li $a0,16
	#####################
	j Start
	Exit:
		jr $ra
	Start:
		addi $t7,$a0,250
		andi $t8,$a0,201
		ori $t9,$a0,22
		addi $s0,$a0,-2502
		jal Exit
		nor $t6,$t8,$t9
	######################
	Goodbye:
		li $v0,10
		syscall