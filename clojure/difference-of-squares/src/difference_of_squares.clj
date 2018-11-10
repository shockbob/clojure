(ns difference-of-squares)

(defn sum-of-squares [n] 
   (apply + (map (fn [x] (* x x)) (range (inc n)))))

(defn square-of-sum [n] 
  (let [sum (apply + (range (inc n)))]
    (* sum sum))) 

(defn difference [n] 
   (- (square-of-sum n)(sum-of-squares n) ))

