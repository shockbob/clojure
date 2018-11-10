(ns grains)

(defn square [n]
   (if (zero? (dec n))
       1
       (*' 2 (square (dec n)))))

(defn total [] 
    (apply +' (map square (range 1 65))))  
