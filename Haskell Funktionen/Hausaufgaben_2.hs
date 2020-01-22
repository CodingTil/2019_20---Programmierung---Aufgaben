import Text.Show.Functions

data BinTree a b = Leaf b | Node a (BinTree a b) (BinTree a b) deriving Show

example :: BinTree (Int -> Bool) Char
example = Node (\x -> x > 4) (Node (\x -> x * x == x) (Leaf 'g') (Node (\x -> x == 0) (Leaf 'u') (Leaf 'l'))) (Node (\x -> x >= 7) (Leaf 'f') (Leaf 'i'))

countInnerNodes :: (BinTree a b) -> Int
countInnerNodes (Leaf _) = 0
countInnerNodes (Node _ ba bb) = 1 + (countInnerNodes ba) + (countInnerNodes bb)

decodeInt :: (BinTree (Int -> Bool) b) -> Int -> b
decodeInt (Leaf b) _ = b
decodeInt (Node f ba bb) x = if (f x) then decodeInt bb x else decodeInt ba x

decode :: (BinTree (Int -> Bool) b) -> [Int] -> [b]
decode bin xs = map (decodeInt bin) xs

mapTree :: (b -> c) -> (BinTree a b) -> (BinTree a c)
mapTree f (Leaf b) = Leaf (f b)
mapTree f (Node a ba bb) = Node a (mapTree f ba) (mapTree f bb)





data List a = Nil | Cons a (List a) deriving Show

list :: List Int
list = Cons (-3) (Cons 14 (Cons (-6) (Cons 7 (Cons 1 Nil))))

blist :: List Int
blist = Cons 1 (Cons 1 (Cons 0 (Cons 0 Nil)))

filterList :: (a -> Bool) -> List a -> List a
filterList f Nil = Nil
filterList f (Cons a l) | f a = Cons a (filterList f l)
                        | otherwise = filterList f l

devisibleBy :: Int -> List Int -> List Int
devisibleBy a l = filterList (\x -> rem x a == 0) l

foldList :: (a -> b -> b) -> b -> List a -> b
foldList f c Nil = c
foldList f c (Cons a l) = f a (foldList f c l)

listMaximum :: List Int -> Int
listMaximum l = foldList (\x y -> if x > y then x else y) minBound l

mapList :: (a -> b) -> List a -> List b
mapList f l = foldList (\x y -> Cons (f x) y) Nil l

zipLists :: (a -> b -> c) -> List a -> List b -> List c
zipLists _ Nil _ = Nil
zipLists _ _ Nil = Nil
zipLists f (Cons a la) (Cons b lb) = Cons (f a b) (zipLists f la lb)

skalarprodukt :: List Int -> List Int -> Int
skalarprodukt la lb = foldList (\x y -> x + y) 0 (zipLists (\x y -> x * y) la lb)