(ns series)

(defn slices [string length] ;; <- arglist goes here
   (if (zero? length)
   [""]
   (map (fn [arr] (apply str arr)) (partition length 1 string))))
