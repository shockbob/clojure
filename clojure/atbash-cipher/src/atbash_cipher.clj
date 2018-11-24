(ns atbash-cipher
    (:require [clojure.string :as str]))  

(def zz (int \z))
(def aa (int \a))
(defn isdigit [ch] (Character/isDigit  ch))
(defn isalpha [ch] (Character/isAlphabetic (int ch)))
(defn encode [st]
   (->> (.toLowerCase st)
        (filter (fn [x] (or (isdigit x) (isalpha x))))
        (map (fn [x] (if (isdigit x) x  (char (+ aa (- zz (int x)))))))
        (partition-all 5)
        (map str/join)
        (str/join " ")))
