
(defn append-char
  ([alpahbet input-str result-l]
   (if (> (count alpahbet) 0)
     (if (= (first alpahbet) (subs input-str (- (.length input-str) 1)))
       (append-char (rest alpahbet) input-str result-l)
       (append-char (rest alpahbet) input-str (cons (.concat input-str (first alpahbet)) result-l)))
     result-l))
  ([alpahbet input-str]
   (append-char alpahbet input-str ())))


(defn multistring-append-char
  ([alphabet str-l result-l]
   (if (> (count str-l) 0)
     (multistring-append-char
      alphabet (rest str-l) (concat (append-char alphabet (first str-l)) result-l))
     result-l))
  ([alphabet str-l] (multistring-append-char
                     alphabet str-l ())))


(defn main
  ([alphabet num result-l]
   (if (> num 1)
     (main alphabet (- num 1) (multistring-append-char
                               alphabet result-l))
     result-l))
  ([alphabet num] (main alphabet num alphabet)))

(def alpha `("a", "b", "c"))

(append-char alpha "ab")

(multistring-append-char alpha `("ab"))

(main alpha 2)