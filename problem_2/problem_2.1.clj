(import clojure.math.log clojure.math.round)



; TODO! Choose better name
(defn foo [coll i n]
  (reduce
   (fn [l r] (assoc l r false))
   coll
   (range (* i i) n i)))

(foo (vec (repeat 10 true)) 2 10)


; TODO! Choose better name
(defn bar [n]
  (reduce
   (fn [l r] (foo l r n))
   (vec (repeat n true))
   (take-while
    (fn [i] (<= (* i i) n))
    (iterate inc 2))))

(bar 25)


(defn prime-upper-bound [n]
  (cond
    (= n 1) 2
    (= n 2) 3
    (= n 3) 5
    (= n 4) 7
    (= n 5) 11
    (>= n 6) (-> n
              (log)
              (+ (-> n (log) (log)))
              (* n)
              (round))
    :else (assert false "Expected num greater than 0")))


(println
(prime-upper-bound 1)
(prime-upper-bound 2)
(prime-upper-bound 3)
(prime-upper-bound 4)
(prime-upper-bound 5)
(prime-upper-bound 6)
(prime-upper-bound 40)
)

(prime-upper-bound 0)

; TODO! Choose better name
(defn primes-sequence [n]
  (let
   [upper-bound (prime-upper-bound n)
    arr (bar upper-bound)]
    (reduce
     (fn [l r] (if (nth arr r)
                 (conj l r)
                 l))
     []
     (range 2 upper-bound))))


(let [n 40
      res (primes-sequence n)]
  (println n "number: " (nth res (- n 1)))
  (println "Calculated number count: " (count res)))

(defn prime-nth [n]
  (nth (primes-sequence n) (- n 1)))

(prime-nth 501)

