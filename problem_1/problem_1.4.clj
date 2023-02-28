
(defn append-char
  ([alpahbet input-str]
   (reduce
    (fn [res x] (cons (str input-str x) res))
    (list)
    (filter (fn [x] (not= x (last input-str))) alpahbet))))

(defn multi-append-char
  ([alphabet str-l]
   (reduce 
    (fn [res x] (concat (append-char alphabet x) res))
    (list)
    str-l)))

(defn main
  ([alphabet num]
   (reduce
    (fn [res x] (multi-append-char alphabet res))
    (list "")
    (range 0 num))))

(def alpha `(\a, \b, \c))

(append-char alpha "ab")

(multi-append-char alpha `(""))

(main alpha 2)