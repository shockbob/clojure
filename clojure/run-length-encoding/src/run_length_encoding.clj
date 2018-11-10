(ns run-length-encoding)

(defn run-length-encode
  "encodes a string with run-length-encoding"
  [plain-text]
  (apply str 
       (mapcat 
           (fn [[k v]] (if (= 1 k) [v] [k v])) 
                             (map 
                                (juxt count first) 
                                (partition-by identity plain-text))))) 

(defn rept [[c s]] 
     (apply str (repeat (Integer/parseInt c) (first s))))

(defn fix [a c e]
     (if (empty? c)
         (str a e)
         (str a (apply str (repeat (Integer/parseInt c) e)))))

(defn run-length-decode [s]
     (first (reduce (fn [[a c] e] (if (Character/isDigit e)
                               [a (str c e)]
                               [(fix a c e) ""])) ["" ""] s)))
  
