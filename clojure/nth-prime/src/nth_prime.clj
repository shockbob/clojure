(ns nth-prime)

(defn is-prime? [n]
   (not (some (fn [div] (zero? (mod n div))) 
               (range 2 (inc (int (Math/sqrt n)))))))

(defn nexter [n]
   (first (filter is-prime? (iterate inc (inc n)))))

(def primes (iterate nexter 2))

(defn nth-prime [n] 
   (if (zero? n)
       (throw (.IllegalArgumentException ""))
       (nth primes (dec n))))
