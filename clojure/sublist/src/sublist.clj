(ns sublist)

(defn sub [a b]
     (and (< (count a)(count b)) (some (partial = a) (partition (count a) 1 b))))

(defn classify [a b]
   (cond
      (= a b) :equal
      (sub a b) :sublist
      (sub b a) :superlist
      :else :unequal))

