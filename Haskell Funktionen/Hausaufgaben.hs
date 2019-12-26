fibInit :: Int -> Int -> Int -> Int
fibInit a0 a1 n | a0 < 0 = 0
                | a1 < 0 = 0
                | n < 0 = 0
                | n == 0 = a0
                | n == 1 = a1
                | otherwise = (fibInit a0 a1 (n - 2)) + (fibInit a0 a1 (n - 1))

fibInitL :: Int -> Int -> Int -> [Int]
fibInitL a0 a1 n | a0 < 0 = []
                 | a1 < 0 = []
                 | n < 0 = []
                 | n == 0 = [a0]
                 | n == 1 = [a0, a1]
                 | otherwise = addFib n [a0, a1]

addFib :: Int -> [Int] -> [Int]
addFib n list | (n + 1) == (length list) = list
              | otherwise = addFib n (list ++ [(list !! ((length list) - 2)) + (list !! ((length list) - 1))])

fibInit2 :: Int -> Int -> Int -> Int
fibInit2 a0 a1 n | a0 < 0 = 0
                 | a1 < 0 = 0
                 | n < 0 = 0
                 | n == 0 = a0
                 | n == 1 = a1
                 | otherwise = list !! ((length list) - 1) where list = (fibInitL a0 a1 n)

normalize :: [Int] -> [Int]
normalize xs = normalizeSmallest (selectSmallest (maxBound) xs) xs

normalizeSmallest :: Int -> [Int] -> [Int]
normalizeSmallest _ [] = []
normalizeSmallest smallest (x : xs) = (x - smallest) : normalizeSmallest smallest xs


selectSmallest :: Int -> [Int] -> Int
selectSmallest smallest [] = smallest
selectSmallest smallest [x] | x < smallest = selectSmallest x []
                            | otherwise = selectSmallest smallest []
selectSmallest smallest (x : xs) | x < smallest = selectSmallest x xs
                                 | otherwise = selectSmallest smallest xs

sumMaxs :: [Int] -> Int
sumMaxs [] = 0
sumMaxs (x:xs) = addList x (selectMaxList x xs)

addList :: Int -> [Int] -> Int
addList a [] = a
addList a (x:xs) = addList (a + x) xs

selectMaxList :: Int -> [Int] -> [Int]
selectMaxList max [] = []
selectMaxList max (x:xs) | x > max = x : selectMaxList x xs
                         | otherwise = selectMaxList max xs

sumNonMins :: [Int] -> Int
sumNonMins [] = 0
sumNonMins (x:xs) = addList 0 (selectNonMinList x xs)

selectNonMinList :: Int -> [Int] -> [Int]
selectNonMinList a [] = []
selectNonMinList a (x : xs) | x <= a = selectNonMinList x xs
                            | otherwise = x : selectNonMinList a xs

primeTwins :: Int -> (Int, Int)
primeTwins x | prime ((primeGreaterThan x) + 2) = (primeGreaterThan x, (primeGreaterThan x) + 2)
             | otherwise = primeTwins (primeGreaterThan x)

primeGreaterThan :: Int -> Int
primeGreaterThan x | prime (x + 1) == True = (x + 1)
                   | otherwise = primeGreaterThan (x + 1)

prime :: Int -> Bool
prime 0 = False
prime 1 = False
prime 2 = True
prime n = primeTest n (n-1)

primeTest :: Int -> Int -> Bool
primeTest n m | m == 1 = True
              | (rem n m == 0) = False
              | otherwise = primeTest n (m-1)

multiples :: [Int] -> Int -> Int -> [Int]
multiples [] _ _ = []
multiples xs a b = removeDoubles (bubbleSort (selectMultiples xs a b))

selectMultiples :: [Int] -> Int -> Int -> [Int]
selectMultiples [] _ _ = []
selectMultiples (x:xs) a b = (multiplesOfNumber x x a b) ++ (selectMultiples xs a b)

multiplesOfNumber :: Int -> Int -> Int -> Int -> [Int]
multiplesOfNumber x z a b | x < a = multiplesOfNumber (x + z) z a b
                        | x >= a && x <= b = x : multiplesOfNumber (x + z) z a b
                        | otherwise = []

bubbleSort :: [Int] -> [Int]
bubbleSort [] = []
bubbleSort [x] = [x]
bubbleSort (x:y:xs) | sorted (x:y:xs) = (x:y:xs)
                    | otherwise = bubbleSort (a : bubbleSort (b:xs))
                      where a = selectMin x y
                            b = selectMax x y

selectMin :: Int -> Int -> Int
selectMin a b | a < b = a
              | otherwise = b

selectMax :: Int -> Int -> Int
selectMax a b | a < b = b
              | otherwise = a

sorted :: [Int] -> Bool
sorted [] = True
sorted [x] = True
sorted (x:y:xs) | x <= y = sorted (y:xs)
                | otherwise = False

removeDoubles :: [Int] -> [Int]
removeDoubles [] = []
removeDoubles [x] = [x]
removeDoubles (x:y:xs) | x == y = removeDoubles (x:xs)
                      | otherwise = x : removeDoubles (y:xs)