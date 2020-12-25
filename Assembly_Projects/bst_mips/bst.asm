###############################################
#Arif Burak Demiray - burakdemiray09@gmail.com#
###############################################

#Here is the datas for the visuality of the menu
.data
addPrompt: .asciiz "Press the key 1 if you want to add a number to the array that is going to be built for making tree (1)\n"
buildPrompt: .asciiz "Press the key 2 if you want to build the tree (2)\n"
insertPrompt: .asciiz "Press the key 3 if you want to insert a number to the tree (3)\n"
findPrompt: .asciiz "Press the key 4 if you want to find a number from the tree (4)\n"
printPrompt: .asciiz "Press the key 5 if you want to print the BST like a tree(5)\n"
exitPrompt: .asciiz "Press the key 6 if you want to exit from program (6)\n"
welcome: .asciiz  "Welcome to the BST MIPS edition program, please choose what you want to do :)\n"
#for getting prompt from user
input: .asciiz "Your choice: "
escape: .asciiz "\n"
word: .asciiz "X"
space: .asciiz "   "
seperator: .asciiz "-"
chooseError: .asciiz "You should press related keys that mentioned above!\n"
goodBye: .asciiz "Leaving from program...\n"
createErrorPrompt: .asciiz "You have already created the list, you can only add numbers when BST is created!!\n"
addNum: .asciiz "The number: "
finishAdding: .asciiz "If you want to leave from adding type -9999\n"
leaveAdd: .asciiz "Leaving from adding...\n"
addOverflowError: .asciiz "The list is overflowed. You can not add number to the list anymore.(Supported size is 100 numbers!!)\n"
leavePrint: .asciiz "Leaving from print...\n"
printError: .asciiz "The BST can not be printed because it has not been created!!\n"
buildError: .asciiz "You can not build the BST because you have not added any number to the list!!\n"
leaveBuild: .asciiz "Leaving from build...\n"
buildSucces: .asciiz "The BST created succesfully\n"
addSucces: .asciiz "The number list created succesfully\n"
insertError: .asciiz "The number can not be inserted because the BST has not been created!!\n"
insertSucces: .asciiz "The number is inserted succesfully\n"
leaveInsert: .asciiz "Leaving from insert...\n"
findError: .asciiz "The number can not be found because the BST has not been created!!\n"
findSucces: .asciiz "The number is found succesfully\n"
leaveFind: .asciiz "Leaving from find...\n"
findError2: .asciiz "The number you searched is not in the BST!!\n"
returnFindPrompt: .asciiz "The returned value from find is: "
buildError2: .asciiz "You have created the BST already. You can not build it again!!\n"
addError: .asciiz "You can not create list with the number -9999 because it is leave input!!\n"
valueFromFind: .asciiz "Returned $v0 value from find procedure: "
addressFromFind: .asciiz "Returned address from find procedure: "
insertedAddress: .asciiz "Returned address from insert procedure: "
insertedValue: .asciiz "Returned value from insert procedure: "
rootAdress: .asciiz "The address of the BST: "
rootValue: .asciiz "The value of the root: "
listAddress: .asciiz "The address of the list: "
numberNum: .asciiz "The number of numbers in the BST: "
tree: .word 0  #tree's address is going to be stored here

.text
.globl main

main:
	la $a0,welcome 		#in main it prints a welcome message,prints the prompts then it looks for the input from user
	li $v0,4
	syscall
	jal printPrompts
	j lookForOperation
	
printPrompts: 		#prints the supported prompts
	la $a0,addPrompt
	li $v0,4
	syscall
	la $a0,buildPrompt
	li $v0,4
	syscall
	la $a0,insertPrompt
	li $v0,4
	syscall
	la $a0,findPrompt
	li $v0,4
	syscall
	la $a0,printPrompt
	li $v0,4
	syscall
	la $a0,exitPrompt
	li $v0,4
	syscall
	jr $ra

lookForOperation: 		#in this procedure it gets input from user until user writes a related number
	la $a0,input 		#for visuality
	li $v0,4
	syscall
	li $v0,5 #input
	syscall
	beq,$v0,6,exit
	beq,$v0,1,createList
	beq,$v0,2,buildBST
	beq,$v0,3,insertBST
	beq,$v0,4,findBST
	beq,$v0,5,printBST
	la $a0,chooseError 		#if user wrote a unsupported number it asks it again until it can get a supported input
	li $v0,4
	syscall
	j lookForOperation 		#go back for asking again

createList: 		#in this procedure it created the list via getting input from user
	bne $s2,$zero,createError 		#if the list has created already it gives error
	la $a0,finishAdding 		#if user inputs -9999 adding operations finishes
	li $v0,4
	syscall
	move $t0,$zero 		#for index operations
	li $a0,404 		#in assignment paper is mentioned that max numbers can be 100 the last index for MAXINT 
	li $v0,9                                                              		 #which is -9999 (from the test.asm)
	syscall
	move $s2,$v0 		#the input list is stored at $s2 register
	addContinue:  		#after above operations, this flag continue to add number until number is -9999 or list overflow
		beq $t0,100,addOverflow 		#checks for overflow
		sll $t1,$t0,2 		#indexing
		add $t2,$s2,$t1 		#next empty place in the list
		la $a0,addNum		 #for visuality
		li $v0,4
		syscall
		li $v0,5
		syscall	
		beq $t0,0,checkBigFirst 		#the user can not input -9999 firstly, this procedure checks it
		contoadd: 		#if first entry is not -9999 comes to here
		sw $v0,0($t2) 		#stores the entry to its location
		beq $v0,-9999,addSuccessful 		#if entry is -9999 add operation must finish
		addi $t0,$t0,1 		#indexing
		j addContinue 		#for continue to loop 
	createError:
		la $a0,createErrorPrompt 		# just prints why it is not doing it then leaves from adding number
		li $v0,4
		syscall
	leaveFromAdd:		 #leave message and go back to operation menu
		la $a0,leaveAdd
		li $v0,4
		syscall
		j lookForOperation
		addSuccessful:		#if we added succesfully the list, it prints out its address. Then goes to leaves from add
			la $a0,listAddress
			li $v0,4
			syscall
			move $a0,$s2
			li $v0,1
			syscall
			la $a0,escape
			li $v0,4
			syscall
			la $a0,addSucces
			li $v0,4
			syscall	
			j leaveFromAdd
	addOverflow: 		#if we reached index of 100 it gives and overflow error and adds -9999 to the last index
		sll $t1,$t0,2
		add $t2,$s2,$t1
		li $t3,-9999
		sw $t3,0($t2)
		la $a0,addOverflowError
		li $v0,4
		syscall
		j leaveFromAdd
	checkBigFirst: 		#If first entry is -9999, user must input another number other than -9999
		bne $v0,-9999,contoadd
		la $a0,addError
		li $v0,4
		syscall	
		j addContinue
		
buildBST: 		#build bst procedure
	la $s0,tree #for loading the tree's adress to it
	lw $t0,0($s0) #for checking tree is empty or not
	bne $t0,$zero,buildErrorJ2 		#if tree is not zero it means bst created already it goes that error and leaves from build
	beq $s2,$zero,buildErrorJ 		#If list is zero it means user has not created the list, user must created the list in order to create BST
	jal build
	la $a0,rootAdress 		#prints out the root adress, root value and the element number in the BST
	li $v0,4
	syscall
	move $a0,$s0
	li $v0,1
	syscall
	la $a0,escape
	li $v0,4
	syscall
	la $a0,rootValue
	li $v0,4
	syscall
	lw $a0,0($s0)
	li $v0,1
	syscall
	la $a0,escape
	li $v0,4
	syscall
	la $a0,numberNum
	li $v0,4
	syscall
	move $a0,$s7 		#numbers stored in #s7 register
	li $v0,1
	syscall
	la $a0,escape
	li $v0,4
	syscall
	la $a0,buildSucces
	li $v0,4
	syscall
	j leaveFromBuild 
	buildErrorJ:     		#prints out the error and leaves from build
		la $a0,buildError
		li $v0,4
		syscall
	leaveFromBuild:
		la $a0,leaveBuild
		li $v0,4
		syscall
		j lookForOperation
	buildErrorJ2:   		 #prints out the error and leaves from build
		la $a0,buildError2
		li $v0,4
		syscall
		j leaveFromBuild
	
printBST:  		#print procedure 
	la $t0,tree
	lw $t0,0($t0) #for checking tree is empty or not
	beq $t0,$zero,printErrorJ 		#if bst has not created yet, it can not print the BST
	la $a0,escape  		#it prints the escape because of visuality of the tree
	li $v0,4
	syscall
	la $a0,tree
	jal printTree
	la $a0,escape 		# double new line for visuality
	li $v0,4
	syscall
	la $a0,escape
	li $v0,4
	syscall
	j leaveFromPrint		 # it printed it, we can leave now.
	printErrorJ:
		la $a0,printError
		li $v0,4
		syscall
	leaveFromPrint:
		la $a0,leavePrint
		li $v0,4
		syscall
		j lookForOperation

insertBST: 		#insert procedure that takes input from user
	la $t0,tree
	lw $t0,0($t0) #for checking tree is empty or not
	beq $t0,$zero,insertErrorJ 		# if bst is not created we can not insert the number 
	la $a0,addNum 		#visuality
	li $v0,4
	syscall
	li $v0,5
	syscall
	move $a0,$v0 #input 
	la $a1,tree #tree
	jal insert
	addi $sp,$sp,-4
	sw $v0,0($sp)		 #for saving result before printing text
	la $a0,insertedAddress
	li $v0,4
	syscall
	lw $a0,0($sp)		 #prints inserted number's address and value
	li $v0,1
	syscall
	la $a0,escape
	li $v0,4
	syscall
	la $a0,insertedValue
	li $v0,4
	syscall
	lw $t0,0($sp)
	lw $a0,0($t0)
	addi $sp,$sp,4		 #frees the stack
	li $v0,1
	syscall
	la $a0,escape
	li $v0,4
	syscall
	la $a0,insertSucces 
	li $v0,4
	syscall
	leaveFromInsert:
		la $a0,leaveInsert
		li $v0,4
		syscall
		j lookForOperation
	insertErrorJ:
		la $a0,insertError
		li $v0,4
		syscall
		j leaveFromInsert

findBST: 		#find proceddure that takes input from user
	la $t0,tree
	lw $t0,0($t0) #for checking tree is empty or not
	beq $t0,$zero,findErrorJ 		#if bst is not created it can not find number
	la $a0,addNum
	li $v0,4
	syscall
	li $v0,5
	syscall
	move $a0,$v0
	la $a1,tree   #address of the tree
	move $v1,$zero		 #free the v1 for the result
	jal find
	move $t9,$v0		 #storing result before printing operations
	la $a0,valueFromFind		 #printing the values from v0 and v1
	li $v0,4
	syscall
	move $a0,$t9
	li $v0,1
	syscall
	la $a0,escape
	li $v0,4
	syscall
	la $a0,addressFromFind
	li $v0,4
	syscall
	move $a0,$v1
	li $v0,1
	syscall
	la $a0,escape
	li $v0,4
	syscall
	move $v0,$t9
	beq $v0,1,findErrorJ2		 # if v0 1 it means could not found the input
	la $a0,returnFindPrompt 		# prints the value from found address
	li $v0,4
	syscall
	lw $a0,0($v1)
	li $v0,1
	syscall
	la $a0,escape
	li $v0,4
	syscall
	la $a0,findSucces
	li $v0,4
	syscall
	leaveFromFind:		 #leaving from find
		la $a0,leaveFind
		li $v0,4
		syscall
		j lookForOperation
	findErrorJ:
		la $a0,findError
		li $v0,4
		syscall
		j leaveFromFind
	findErrorJ2:
		la $a0,findError2
		li $v0,4
		syscall
		j leaveFromFind
		
#here is the core procedures for above procedures

build:
	move $s7,$zero  		#this s7 register stores the quantity of the numbers on the BST
	move $t0,$s2    		#this is the list created from user inputs
	move $t1,$zero  		#this is the counter for our list
	buildContinue:  		#when initial operations finish, it comes to here, not build beacuse I do not want to reset s7 and t0 register
		sll $t3,$t1,2 		#indexing operations
		add $t2,$t0,$t3
		lw $a1,0($t2) 		#takes element from list and stores it in a1
		beq $a1,-9999,return 		#means end of the list
		bne $t1,$zero,childs 		#means the root is empty. Firstly we should create the root. If not means root is created we should
		beq $t1,$zero,makeRoot 		#if t1 zero it goes to make the root                     add next elements as children of the root
		childs:
			lw $t6,0($t7) 		#the reason why I am storing parents in t7 is that. I can reach them easily
			ble $a1,$t6,setLeft		 #if element is less than or equal to it should be left child otherwise it should be right child
			j setRight    
		continue:
			addi $t1,$t1,1		 #incrementing index by one
			j buildContinue 		#continue to add numbers to the BST from the list

makeRoot:  		#requesting space for root
	li $v0, 9
	li $a0,16
	syscall
	sw $a1,0($v0)		 #storing its value
	sw $zero,4($v0)		 #initially left node,right node and parent zero
	sw $zero,8($v0)
	sw $zero,12($v0)
	sw $v0,0($s0) 		#storing the root address in the tree
	move $s0,$v0 		#then moving root to the s0
	move $t7,$s0 		#for moving easily storing it in t7 register
	addi $s7,$s7,1		 #incrementing quantity by one
	j continue 		# go back to where we were
	
setLeft:
	beq $t9,2,changeLeftFind		 #t9 = 2 means we are using build operation for find operation we should not add new nodes
	lw $t5,4($t7)		 #looking for parents left child 										we just only look for equality
	bne $zero,$t5,changeLeft		 #if parent's left child is not empty we should give parentery to its left child
	li $v0, 9 		#requesting new place for left child
	li $a0,16
	syscall
	move $t4,$v0 		 #storing returned address in a temporary register
	sw $a1,0($t4) 		#adding its value
	sw $zero,4($t4) 		#childs are empty because we created it just now
	sw $zero,8($t4)
	sw $t7,12($t4)		 #adding its parent
	sw $t4,4($t7)		 #and fulling parents left child
	move $t7,$s0 		#making parent root again because we put the child to its place. we can not add next child to this parent.
	addi $s7,$s7,1		 #incrementing element quantity by one                     If we add, bst can not be created in a correct way.
	beq,$t9,1,comeBack		 #t9 = 1 means we are using build procedure for insert procedure,when we inserted it we should exit from insert
	j continue 		#if t9 not 1 we should continue to process the list
	changeLeft:		 #if left child is full, it gives parentery to its left child
		lw $t7,4($t7) 		#we cannot add existing number to the BST we should continue to change parent until find the appropriate place
		j childs		 #goes back to looking for a parent
	changeLeftFind: 		#we should look for next childs to continue or not find procedure
		lw $t5,4($t7) 		#looking next left child
		beq $a1,$t6,returnFind 		#looking the parent's value, If it is same as input finish the find process
		beq $t5,$zero,returnLFind		 #if it not same and its next left child zero that means we could not found it
		lw $t7,4($t7)		 #if its left child not zero we should move on to look for it
		j childs 		#go back to looking
		returnFind:
			move $v1,$t7  		#means we found it, We should store it in v1 and make v0 = 0
			add $v0,$zero,$zero
			j comeBack 		#go back to return
		returnLFind: 
			addi $v0,$zero,1   		#making v0 = 1 means can not found input and go back to return
			j comeBack
		
setRight:
    beq $t9,2,changeRightFind 		#this set right procedure has same algorithm as set left except find procedure
	lw $t5,8($t7)                                                #I am not looking for if the values are same because we built tree
	bne $zero,$t5,changeRight                                    #in a way that equal values is always left node so we do not have to look for equality
	li $v0, 9                                                    #in find procedure for right childs. We just need to change parentery to its child.
	li $a0,16
	syscall
	move $t4,$v0
	sw $a1,0($t4)
	sw $zero,4($t4)
	sw $zero,8($t4)
	sw $t7,12($t4)
	sw $t4,8($t7)
	move $t7,$s0
	addi $s7,$s7,1
	beq,$t9,1,comeBack
	j continue
	changeRight:
		lw $t7,8($t7)
		j childs
	changeRightFind:
		lw $t5,8($t7)
		beq $t5,$zero,returnRFind 		 #If the next right child is empty, it means we can not find the input
		lw $t7,8($t7)
		j childs
		returnRFind: 
			addi $v0,$zero,1 		#make v0 = 1 to say that input is not in the BST
			j comeBack

#I implemented insert procedure through build procedure 	
insert:
	lw $t7,0($a1)  		#loading necessary elements for build
	move $a1,$a0  
	addi $t9,$zero,1 		#t9=1 means we are going to use build function for inserting a value
	j childs         		#we have our value we just need to find a location for it so jumping childs is enough for it
	comeBack:        		#we should reload inputted elements to its locations and return
		move $a0,$a1
		la $a1,tree
		j return

#I implemented find procedure through build procedure 
find:
	lw $t7,0($a1)  		#moving necessary elements for build
	move $a1,$a0 
	addi $t9,$zero,2 		#t9=2 means we are going to use build function for finding a value
	j childs  		#we have our value we just need to find a parent that is same as the value
	#from setLeft and setRight it comes back to comeBack and reloads given elements
	
#I implemented the printing tree via queue implementation. The queue implemented via linked list.
printTree:
	addi $sp,$sp,-8 		 #for saving the ra register's value and reloading a0's value at the end
	sw $ra,0($sp)
	sw $a0,4($sp)
	li $t2,2    		#this is the expected count of elements in a row. Initially 2 because first row is just root
	li $t4,2   	 		#this is just a const value for multiplying and dividing operations
	li $s6,1 		   #s6 = 1 means we are at the root now, Whe should not do some operations for root. The reasons are going to be explained below 
	move $t0,$zero		  #this is the count of elements in a row
	move $s4,$zero 		 #this is the count of elements in BST for breaking the loop
	lw $t7,0($a0)   		 #this is for reaching easily to parents
	jal queue  		 #create queue with root
	cont: 		 #above was initial declarations now loop part is below
		beq $s4,$s7,comeFromCheck 		  #if we printed all elements in a BST we just need to print X we do not have to look for a newline
		j checkEscape  		#checks we need newline or not. How is it knows? I am storing expected and counted elements in a row. If they are same means we should go for a newline.
		comeFromCheck:  		#comes from check
		beq $s4,$s7,returnFromPrint  		 #if the counted element in a BST is equal to actual size we should finish the printing except...
		contoprintx:
		lw $t1,0($s3) 		#head of queue
		lw $t7,0($t1) 
		beq $t7,-9999,printXWordBuffer 		#If it's value is -9999 we should add queue a X
		jal write 		#prints the value which is head of queue
		j passWritinX 		#if we printed the value we should not print X
		printXWordBuffer:
			jal printXWord		 #If it is -9999 we should print X
		passWritinX:
		bne $s6,1,pass  		 #if s6 = 1 means we are at the root. we should print newline and If it entered to checkEscape initial expected would be resetted
		jal printEscape                                                                                 #I do not want to reset the initial expected
		pass:
			lw $t7,4($t1)  		#firslty left child
			beq $t7,$zero,printX 		 #if it is zero we should enqueue X
			jal enque		 #otherwise we can enqeue left childs
			right:
				lw $t7,8($t1)		 #loading right child of the head
				beq $t7,$zero,printXR 		#if it is zero, we should add X to the tail
				jal enque 		#otherwise, enque the right child
			deq:
				jal deque 		#now we should deque the printed head and make new head as the next one in the queue
			j cont 		#and continue to loop until it breaks
	returnFromPrint:
		div $t0,$t2 		#...we should look for expected count of elements in a row.If expected and counted's division is not 1 we should print the rest X
		mflo $t3   
		bne $t3,1,contoprintx		 #returns back
		lw $a0,4($sp) 		 #If we printed all required X. we can get back a0 and ra initial values from stack and free the stack 
		lw $ra,0($sp)
		addi $sp,$sp,8
		j return 		 #then return

#for the left child adds X to the tail and enques it then goes to the right child check
printX:
	jal pushNull
	jal enque
	j right

#for the right child same logic except we looked both child we should deque existing head
printXR:
	jal pushNull
	jal enque
	j deq

#This procedure prints X
printXWord:
	addi $t0,$t0,1  		#incrementing by one the count of elements in a row
	la $a0,word  		 #printing it
	li $v0,4
	syscall         		#below same design as write procedure
	addi $sp,$sp,-4  		#saving ra for return
	sw $ra 0($sp)
	jal printExtra 		 #checking for extra
	lw $ra,0($sp)
	addi $sp,$sp,4
	jr $ra

#adds X value to the tail
pushNull:
	li $a0,16 		#requries space for X value
	li $v0,9
	syscall
	move $t7,$v0 		#moves it to t7
	li $a0,-9999		 #loads -9999 to flag it as X
	sw $a0,0($t7) 		#and its other attributes are same because it does not have any child, its children must be also X.
	sw $t7,4($t7)
	sw $t7,8($t7)
	jr $ra #and return

#This procedure prints the value of the head element
write:
	addi $s4,$s4,1   #increments by one the count of elements in a BST
	addi $t0,$t0,1   #increments by one the count of elements in a row
	lw $a0,0($t1)    #loading head's value to a0 for printing
	li $v0,1
	syscall
	beq $s6,1,contoreturn 		#If s6 is 1 means we printed the root. We do not have to do below operations
	addi $sp,$sp,-4 		#saving ra for returning
	sw $ra 0($sp)
	jal printExtra 		#we should print extra if it is not root
	lw $ra,0($sp)  		#loading it again and free the stack
	addi $sp,$sp,4
	contoreturn:
		jr $ra 		#and return

#this procedure prints a newline
printEscape:
	beq $s6,1,pass1 		#s6 means we are at the root we do not have to multiply operation
	mult $t2,$t4  		#if s6 not 1 means we should print newline so we should update our expected quantity of elements in a row
	mflo $t2  		#For updating it just multiply with 2 and took LO value. It means our expected count is double of old one
	pass1: 		#simply free quantity of row and s6 for not entering again root conditions
		move $s6,$zero
		move $t0,$zero		 #make quantity of elements in a row zero because we are entering in a new row
		la $a0,escape
		li $v0,4
		syscall
		jr $ra  #return

#Here looks for should we print newline or not
checkEscape:
	div $t0,$t2 		 #dividing row quantity by expected row quantity else means they are same so we should print a newline
	mflo $t3 		 # we simply need 1.0. The HI value should be 0
	bne $t3,1,writeEscapeBuffer 		#if it is 1, we should print new line
	jal printEscape 		#print newline
	writeEscapeBuffer: 		#if it is not 1 it jumps here
		j comeFromCheck
		
#enqueue simply adds newcomers to tail of the root
enque:
	li $v0, 9   	#requesting space for next
	li $a0,8
	syscall
	move $t8,$v0    #moving its adress to temporary register
	sw $t7,0($t8)   #putting its value to parent's address
	sw $zero,4($t8) #making next zero because we are adding to the tail
	sw $t8,4($t9)   #adding adding previous one's tail to this one
	move $t9,$t8    #making new tail the last added element
	jr $ra  #return

#this procedure removes head and makes new head next
deque:
	lw $t5,4($s3)  		 #loading next element
	beq $t5,$zero,makeEmpty 		#if it is zero means there is none in the list
	move $s3,$t5   		#if next is not empty makes new head next
	jr $ra 		 #and returns
	makeEmpty:  		#makes the head zero and returns
		move $s3,$zero
		jr $ra

#here it creates the queue's head 
queue:
	li $v0, 9   		#we need 2 index. one for existing element the other is for next element
	li $a0,8
	syscall
	move $s3,$v0 		#s3 is the head of the queue
	sw $t7,0($s3)       #in t7 we have our existing parent which is root for now. for creating the queue the head is root initially.
	sw $zero,4($s3)     #the next one is zero because there is no one in the next initially
	move $t9,$s3        #for easily access the tail of the queue I store the elements at t9. For now it is just root.
	jr $ra     			#and return where we were
	
#this procedure looks for should we print seperator or blank. Blank if existing quantity of row even number. 
printExtra:                                                   #Seperator if existing quantity of row odd number.
	div $t0,$t4  		#here dividing row number by 2.
	mfhi $s1    		 #taking from hi if its 1, means odd else,0 means even.
	beq $s1,1,printseperator 		#if 1 print seperaor
	beq $s1,0,printspace 		#if 0 print blank
	jr $ra 		#if nothing just return
	printseperator:
		la $a0,seperator
		li $v0,4
		syscall
		jr $ra
	printspace:
		la $a0,space
		li $v0,4
		syscall
		jr $ra

#return procedure that frees all temporary registers and returns to ra
return:
	move $t9,$zero
	move $t8,$zero
	move $t7,$zero
	move $t6,$zero
	move $t5,$zero
	move $t4,$zero
	move $t3,$zero
	move $t2,$zero
	move $t1,$zero
	move $t0,$zero
	jr $ra

#here is the exit procedure that frees some s and v registers
exit:
	la $a0,goodBye
	li $v0,4
	syscall
	move $s3,$zero
	move $s4,$zero
	move $v1,$zero
	li $v0,10
	syscall
