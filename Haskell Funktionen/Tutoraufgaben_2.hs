data Mobile a = Stern | Seepferdchen | Elefant (Mobile a) | Kaenguru a (Mobile a) (Mobile a) deriving Show

mobileLinks :: Mobile Int
mobileLinks = Kaenguru 1
                  (Elefant (Kaenguru 2
				        Stern
						(Kaenguru 3
						      Seepferdchen
							  Stern
					    )
				   )
			       Seepferdchen
				   
mobileRechts :: Mobile Int
mobileRechts = Elefant (Kaenguru 1
                   (Elefant Stern)
				   (Elefant Seepferdchen)
			       )
				   
count :: Mobile a -> Int
count Stern = 1
count Seepferdchen = 1
count (Elefant m) = 1 + count m
count (Kaenguru _ m1 m2) = 1 + (count m1) + (count m2)

liste :: Mobile a -> [a]
liste Stern = []
liste Seepferdchen = []
liste (Elefant m) = liste m
liste (Kaenguru inhalt m1 m2) = inhalt : liste m1 ++ liste m2

greife :: Mobile a -> Int -> Mobile a
greife x 1 = x
greife (Elefant m) x = greife m (x-1)
greife (Kaenguru _ m1 m2) x | (x-1) <= count m1 = greife m1 (x-1)
                            | otherwise = greife m2 (x-1 - (count m1))





data Tree = Nil | Node Int Tree Tree deriving Show

foldTree :: (Int -> a -> a -> a) -> a -> Tree -> a
foldTree f c Nil = c
foldTree f c (Node v l r) = f v (foldTree f c l) (foldTree f c r)

prodTree = foldTree (\x y z -> x * y * z) 1 

incTree = foldTree (\x y z -> Node (x+1) y z) Nil





drop_mult :: Int -> [Int] -> [Int]
drop_mult x xs = filter (\y -> mod y x /= 0) xs

dropall :: [Int] -> [Int]
dropall (x:xs) = x : dropall (drop_mult x xs)

from :: Int -> [Int]
from x = x : from (x+1)

primes :: [Int]
primes = dropall (from 2)

odds :: [Int]
odds = 1 : map (+ 2) odds

primeFactors :: Int -> [Int]
primeFactors = pHelper primes 
     where
	 pHelper (x:xs) y | y == 1 = []
	                  | rem y x == 0 = x : pHelper (x:xs) (div y x)
					  | otherwise  = pHelper xs y