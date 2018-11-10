(ns hexadecimal)

(def hex-map {\0 0 \1 1 \2 2 \3 3 \4 4 \5 5 \6 6 \7 7 \8 8 \9 9 \A 10 \B 11 \C 12 \D 13 \E 14 \F 15}) 

(defn do-hex [[f & r] sum]
   (if (nil? f)
       sum
       (let [sum (+ (* 16 sum) (hex-map f))]
           (do-hex r sum))))

(defn hex-to-int [s] 
   (let [s (.toUpperCase s)]
        (if (not (every? hex-map s))
            0
            (do-hex (vec s) 0))))
   
  
