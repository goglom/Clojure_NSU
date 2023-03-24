(ns impl_2.1
  (:gen-class)
  (:import clojure.math.log clojure.math.round))


(defn mark-nonprime [coll i n]
  (reduce
   (fn [l r] (assoc l r false))
   coll
   (range (* i i) n i)))


(defn get-primes-bit-array [n]
  (reduce
   (fn [l r] (mark-nonprime l r n))
   (vec (repeat (+ n 1) true))
   (take-while
    (fn [i] (<= (* i i) n))
    (iterate inc 2))))


(defn prime-nth-upper-bound
  "Return upper bound of n-th prime number"
  [n]
  (cond
    (= n 1) 2
    (= n 2) 3
    (= n 3) 5
    (= n 4) 7
    (= n 5) 11
    ; Base on estimating: p_n < n * (log(n) + log(log(n)))
    (>= n 6) (-> n
                 (log)
                 (+ (-> n (log) (log)))
                 (* n)
                 (round))
    :else (assert false "Expected num greater than 0")))


(defn primes-sequence [n]
  (let
   [upper-bound (prime-nth-upper-bound n)
    arr (get-primes-bit-array upper-bound)]
    (reduce
     (fn [l r] (if (nth arr r)
                 (conj l r)
                 l))
     []
     (range 2 (+ upper-bound 1)))))


(mark-nonprime (vec (repeat 10 true)) 2 10)

(get-primes-bit-array 25)

(println
 (prime-nth-upper-bound 1)
 (prime-nth-upper-bound 2)
 (prime-nth-upper-bound 6)
 (prime-nth-upper-bound 40))

(prime-nth-upper-bound 0)

(let [n 40
      res (primes-sequence n)]
  (println n "number: " (nth res (- n 1)))
  (println "Calculated number count: " (count res)))


(defn prime-nth [n]
  (nth (primes-sequence n) (- n 1)))

(get-primes-bit-array 2)
(primes-sequence 10)

(assert
 (every? true?
         (list
          (= 2    (prime-nth 1))
          (= 3    (prime-nth 2))
          (= 5    (prime-nth 3))
          (= 7    (prime-nth 4))
          (= 11   (prime-nth 5))
          (= 13   (prime-nth 6))
          (= 17   (prime-nth 7))
          (= 113  (prime-nth 30))
          (= 541  (prime-nth 100))
          (= 1223 (prime-nth 200))
          (= 3571 (prime-nth 500))
          (= 7919 (prime-nth 1000))))
 "Failed test")


(time (prime-nth 100000))