% Arif Burak Demiray 
%Firstly I need a count predicate
%If there are no two occurences it gives it.
count([],_,0).
count([N|T],N,Ec):- %Ec is ex count, N is number,C is count,T tail of list
    count(T,N,C), Ec is 1+C.
count([H1|T],N,C):- 
    H1\=N,count(T,N,C).
%This predicate looks for irregular element.
%cause of taking double list is one list goes through the elements, other list remain same.
check_oc([],[]).
check_oc([H|T],L):-
    count(L,H,C),
    ( C \= 2 ->  write(H),nl,fail ; check_oc(T,L)).
%This predicate is my main predicate.
main([]).
main(L) :-
    write("In this list "),write(L),write(" sought irregular number is "),
    check_oc(L,L).
% GOAL
% main([2,3,2,4,5,5,1,6,1,3,6]).
% Result: "In this list [2, 3, 2, 4, 5, 5, 1, 6, 1, 3, 6] sought irregular number is 4"