
(defn append-char
  ([alpahbet input-str result-l]
   (if (> (count alpahbet) 0)
     (if (= (first alpahbet) (last input-str))
       (recur (rest alpahbet) input-str result-l)
       (recur (rest alpahbet) input-str (cons (str input-str (first alpahbet)) result-l)))
     result-l))
  ([alpahbet input-str]
   (append-char alpahbet input-str ())))


(defn multistring-append-char
  ([alphabet str-l result-l]
   (if (> (count str-l) 0)
     (recur alphabet (rest str-l) (concat (append-char alphabet (first str-l)) result-l))
     result-l))
  ([alphabet str-l] (multistring-append-char alphabet str-l ())))


(defn main
  ([alphabet num result-l]
   (if (> num 0)
     (recur alphabet (- num 1) (multistring-append-char alphabet result-l))
     result-l))
  ([alphabet num] (main alphabet num (list ""))))

(def alpha `(\a, \b, \c))

(append-char alpha "ab")

(multistring-append-char alpha `("ab"))

(main alpha 3)