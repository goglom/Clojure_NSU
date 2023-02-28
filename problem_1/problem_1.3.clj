(defn my-map
  ([func coll]
   (reverse (reduce
             (fn [lst x] (cons (func x) lst))
             (list)
             coll))))

(my-map (fn [x] (* x x)) (list 1 2 3 4))


(defn my-filter
  ([pred coll]
   (reverse
    (reduce
     (fn [lst x]
       (if (pred x) (cons x lst) lst))
     (list)
     coll))))

(my-filter (fn [n] (= 0 (mod n 3))) (range 1 21))