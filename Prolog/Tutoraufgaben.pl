increment(leaf(X), leaf(s(X))).
increment(node(NodeLeft1, X, NodeRight1), node(NodeLeft2, s(X), NodeRight2)) :- increment(NodeLeft1, NodeLeft2), increment(NodeRight1, NodeRight2).

append([], List, List).
append([Head|Tail], List, [Head|Result]) :- append(Tail, List, Result).

inorder(leaf(X), [X]).
inorder(node(NodeLeft, X, NodeRight), Result) :- inorder(NodeLeft, Left), inorder(NodeRight, Right), append(Left, [X|Right], Result).

squares(1, [1]).
squares(X, [Y,T2|Rest]) :- X > 1, T is X-1, squares(T, [T2|Rest]), Y is T2 + 2*T + 1.