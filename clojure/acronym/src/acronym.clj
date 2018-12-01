(ns acronym
   (:require [clojure.string :as str]))

(defn convert-ch [c]
   (if (or (Character/isAlphabetic (int c))(= \space c))
       c
       \space))

(defn is-cap? [c]
   (<= (int \A) (int c) (int \Z)))

(defn fix-word [word]
   (if (every? is-cap? word)
       (str (first word))
       word))

(defn my-capitalize [st]
   (str (.toUpperCase (.substring st 0 1)) (.substring st 1)))

(defn split-it[s] (str/split s #" "))

(defn acronym [st] 
   (->> st
        (map convert-ch)
        (apply str)
        (split-it)
        (remove empty?)
        (map fix-word)
        (map my-capitalize)
        (apply str)
        (filter is-cap?)
        (apply str)))
