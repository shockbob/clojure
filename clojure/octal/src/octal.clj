(ns octal)

(def seven (int \7))
(def zero (int \0))
(defn valid-octal [c] (>= seven (int c) zero))
(defn to-decimal
 ([s] (to-decimal s 0)) 
 ([[f & r] a]
   (if (nil? f) 
       a
      (if (not (valid-octal f))
      0
      (to-decimal r  (+ (- (int f) 48)(* 8 a )))))))
