(ns prime-factors)

(defn prime? [n]
   (nil? (some 
      (fn [x] (zero? (mod n x))) 
      (range 2 (inc (int (Math/sqrt n)))))))

(defn nextprime [n]
    (first (filter prime? (iterate inc (inc  n)))))

(defn of 
  ([n] (of n 2 []))
  ([n p c]
     (if (>= 1 n)
        c
     (if (zero? (mod n p))
        (recur (/ n p) p (conj c p))
        (recur n (nextprime p) c)))))
