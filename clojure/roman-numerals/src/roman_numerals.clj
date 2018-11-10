(ns roman-numerals
 (:require [clojure.string :as str]))

(defn numerals [roman]
  (let [roman (map (fn [d] (- (int d) 48)) (str roman)) 
        i "IXCM" 
        v "VLD" 
        x "XCM"
        ms  [nil [i]     [i i]     [i i i]
             [i v]   [v]       [v i]
             [v i i] [v i i i] [i x]]
        powers (range (dec (count roman)) -1 -1)]
        (apply str 
            (mapcat (fn [power digit] (map #((vec %) power) (ms digit))) 
            powers 
            roman))))
