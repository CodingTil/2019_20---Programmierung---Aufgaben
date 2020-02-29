% LIST

userDefinedList(nil).
userDefinedList(cons(_, X)) :- userDefinedList(X).

asPrologList(nil, []).
asPrologList(cons(N, X), [N|Y]) :- asPrologList(X, Y).

flatten([[]], []).
flatten([[]|Tail], Result) :- flatten(Tail, Result).
flatten([[H1|T1]|Tail], [H1|Rest]) :- flatten([T1|Tail], Rest).




% TREE

tree(Value, Children) :- isTree(tree(Value, Children)).
isTree(tree(_, Children)) :- isChildren(Children).
isChildren([]).
isChildren([Head|Rest]) :- isTree(Head), isChildren(Rest).

append([], List, List).
append([Head|Tail], List, [Head|Result]) :- append(Tail, List, Result).

flattenTree(tree(nil, []), []).
flattenTree(tree(nil, [Head|Tail]), Result) :- flattenTree(Head, X), flattenTree(tree(nil, Tail), Y), append(X, Y, Result).
flattenTree(tree(Value, []), Value).
flattenTree(tree(Value, [Head|Tail]), Result) :- flattenTree(Head, X), flattenTree(tree(nil, Tail), Y), append(Value, X, Temp), append(Temp, Y, Result).




% ARITHMETIC

prime(N) :- N > 1, listOfDividors(N, 2, [N]).

listOfDividors(N, N, [N]).
listOfDividors(N, X, [X|Tail]) :- N > X, 0 is N mod X, Xnew is X+1, listOfDividors(N, Xnew, Tail).
listOfDividors(N, X, List) :- N > X, 0 < N mod X, Xnew is X+1, listOfDividors(N, Xnew, List).

only_primes([]).
only_primes([Head|Tail]) :- prime(Head), only_primes(Tail).