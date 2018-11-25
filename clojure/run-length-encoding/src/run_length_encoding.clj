(ns run-length-encoding
 (:require [clojure.string :as str]))

(defn- fix [a c e]
     (if (empty? c)
         (str a e)
         (str a (str/join (repeat (Integer/parseInt c) e)))))


(defn run-length-encode
  "encodes a string with run-length-encoding"
  [plain-text]
  (->> (partition-by identity plain-text) 
       (map (juxt count first)) 
       (mapcat (fn [[k v]] (if (= 1 k) [v] [k v]))) 
       (str/join))) 

(defn run-length-decode [s]
     (first (reduce (fn [[a c] e] (if (Character/isDigit e)
                                       [a (str c e)]
                                       [(fix a c e) ""])) 
                     ["" ""] 
                     s)))
  
