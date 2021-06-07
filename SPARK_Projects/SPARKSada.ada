----------------------------sparksADA----------------------------
procedure dice_rolling (in loop: int,
						out results: int[6])
		global dice=: int[1,2,3,4,5,6]
		pre => results = 0
				loop /= 0
		post => results /= 0
		
		for i 1...loop:
			rand_num=:rand_num()
			if rand_num=1;
				results[1] := results[1] + 1
			else if rand_num=2
				results[2] := results[2] + 1
			else if rand_num=3
				results[3] := results[3] + 1
			else if rand_num=4
				results[4] := results[4] + 1
			else if rand_num=5
				results[5] := results[5] + 1
			else rand_num=1
				results[6] := results[6] + 1
			end if;
			
procedure random(out number:int)
	global => out rand_num int
	global => dice =: int[1,2,3,4,5,6]
	pre: rand_num = 0 
		dice /= 0
	post: rand_num /= 0
	rand_num := pick from dice random 

procedure print_randoms(in results: int[6])
	global=>null
	pre=>result /= 0
	post => null
	for i 1...7:
		print_out("The ",i,"th result is"resulst[i])
----------------------------sparksADA----------------------------
procedure find_double(in n:int,in a:int,in b:int
						out fak:int[50],out mult:int)
	global=> mult:=1;
	global=> fak:= 0;
	global=> level_sum:=0;
	pre:  n /=0,a/=0,b/=0;
	post: fact/=null, mult/= 0
	for i 0...50:
		for j 1...n:
			level_sum := level_sum + exp(a,2*j+1) * exp(b,j);
		mult := mult * level_sum
		if(a > b)
			fak[i] := fact(level_sum);
		level_sum :=0
		
procedure exp(in num:int, in pow:int, out result:int)
	global => result:=0;
	pre => result=0,num/=0;
	post => result /=0
	for i 0...pow;
		result := result + num;
		
procedure fact(in num:int, out fact:int)
	pre=> fact=1, num/=0
	post=> fact/=0
	num_c := num ,int
	while num_c /= 1;
		fact = fact * num_c
		num_c = num_c - 1
----------------------------sparksADA----------------------------
procedure finder(in a:int[],in b:int[],in c:int[],in loop:int,
				out kac_tane:int, out ucuncu:int,
				out max:int,out min:int)
	pre=>a>0,b>0,loop=size(a,b,c),a b ve c nin boyutları aynı olması beklenir
	post=> ucuncu>0
	Y := 0 int;
	kac_tane = 0
	liste:=int[loop]
	for i 0...loop:
		if(c>0)
			x := exp(a[i],b[i]) -1
			Y = a[i] + a[i]*b[i] + 3*x;
			if(div(Y)=1)
				kac_tane = kac_tane +1
		else if(c<0)
			x := a[i]*b[i] + 2
			Y = a[i] + a[i]*b[i] + 3*x;
			liste[i]:=Y
		else
			x := a[i] - 2*b[i] + 3
			Y = a[i] + a[i]*b[i] + 3*x;
			liste[i]:=Y
		end if;
	ucuncu := 0
	counter:=3 int
	min = liste[0]
	max = liste[0]
	for i 0...loop:
		if(counter=0)
			ucuncu := liste[loop-i]
			break the loop
		else
			if(liste[loop-i]>0)
				counter = counter - 1;
			end if;
		end if;
	for i 0...loop:
		if(liste[i]>max)
			max:=liste[i]
		else if(liste[i]<min)
			min:=liste[i]
		end if;
		
procedure exp(in num:int, in pow:int, out result:int)
	global => result:=0;
	pre => result=0,num/=0;
	post => result /=0
	for i 0...pow;
		result := result + num;
		
procedure div(in num:int, out res: int)
	pre=>num/=0
	post => res:=0 or res:=1 
	division = num/4
	res := 0
	while(division<1)
		if(division = 1)
			res:=1
		end if;
		divison = division/4
----------------------------sparksADA----------------------------