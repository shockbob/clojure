(ns sum-of-multiples)

(defn is-evenly-divisible? [divs val]
   (some 
       (fn [div] (zero? (mod val div))) 
       divs))
   
(defn sum-of-multiples [divs num] 
   (apply + (filter 
                (partial is-evenly-divisible? divs) 
                (range 1 num))))

