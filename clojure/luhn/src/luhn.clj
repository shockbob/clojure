(ns luhn)

(defn todig [c] 
   (- (int c) (int \0)))

(defn dub [n]
    (let [db (* 2 n)]
       (if (> db 9) 
           (- db 9) 
           db)))

(defn dubbers [col]
     (map (fn [d i] (if (odd? i) (dub d) d)) col (range)))

(defn invalids-found [st]
   (or (= "0" (.trim st))
       (not (every? (fn [c] (or (Character/isDigit c) (= c \space))) st)))) 

(defn valid? [st] 
   (if (invalids-found st) 
      false
      (let [onlydigs (filter (fn [c] (Character/isDigit c)) st)
            digs (reverse (map todig onlydigs))
            sum  (apply + (dubbers digs))]
           (zero? (mod sum 10)))))
   
