(ns hamming)

(defn distance [strand1 strand2] 
   (cond
      (= strand1 strand2) 0
      (not= (count strand1)(count strand2)) nil
      :else (apply + (map (fn [a b] (if (= a b) 0 1)) strand1 strand2))))
