(ns collatz-conjecture)

(defn collatz 
   ([n] (collatz n 0))
   ([n c] 
      (cond
         (= n 1) c
         (odd? n) (collatz (inc ( * 3 n)) (inc c))
         :else (collatz (/ n 2) (inc c)))))

