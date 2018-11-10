(ns perfect-numbers)

(defn divs [n]  (filter (fn [x] (zero? (mod n x))) (range 2 n)))
(defn sumdiv [n] 
   (inc (apply + (divs n))))

(defn classify [n] 
    (if (< n 0)
       (throw (IllegalArgumentException. ""))
    (let [sd (sumdiv n)]
       (cond 
          (= sd n) :perfect
          (< sd n) :deficient
          :else :abundant))))
