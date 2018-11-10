(ns armstrong-numbers)

(defn todigs [num] 
    (map (fn [d] (- (int d) 48)) (str num)))

(defn pow [c d] (if (zero? d) 1 (* c (pow c (dec d)))))
(defn armstrong? [num] ;; <- arglist goes here
    (let [digs (todigs num)
          ct (count digs)]
      (= num (apply + (map (fn [d] (pow d ct)) digs)))))
