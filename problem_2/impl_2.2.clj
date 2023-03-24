
(defn has-devisors
  [num coll] (some
              #(= (mod num %) 0)
              coll))

(has-devisors 14 [2, 7, 5])
(has-devisors 14 [3, 5])

(defn filter-primes-in-range
  ([primes numbers]
   (let [max-num (last numbers)
         sub-primes (take-while #(<= (* % %) max-num) primes)]
     (filter #(not (has-devisors % sub-primes)) numbers))))

(filter-primes-in-range [2, 3, 5, 7] [10, 11, 12, 13, 14, 15, 16, 17, 18])


(def STEP 100)

(defn primes
  ; Must fill first prime numbers no less than (1 + sqrt(1 + 4 * STEP)) / 2 
  ; For STEP = 100, its equals ~10.512.. (2, 3, 5, 7, 11)
  ; For STEP = 1000: ~32.126.. (2, 3, 5, 7, 11, 13, 17, 19, 23, 29, 31, 37) 
  ([] (let [start-seq '(2, 3, 5, 7, 11)]
        (lazy-cat start-seq (primes (+ 1 (last start-seq))))))
  ([lower-b] (let [upper-b (+ lower-b STEP)]
               (lazy-cat
                (filter-primes-in-range (primes) (range lower-b upper-b))
                (primes upper-b)))))

(nth (primes) 100000)
