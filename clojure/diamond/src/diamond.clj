(ns diamond
   (:require [clojure.string :as str]))

(def alphas "ABCDEFGHIJKLMNOPQRSTUVWXYZ")

(defn spaces [n]
   (str/join (repeat n \space)))

(defn get-level [ch width]
       (let [offset (- (int ch) (int \A))
             indent-count (- (quot width 2) offset)
             indent (spaces indent-count)
             center (spaces (- width (* 2 indent-count) 2))]
       (if (= ch \A) 
           (str indent ch indent)
           (str indent ch center ch indent))))

(defn diamond [ch] 
   (let [level (- (int ch)(int \A))
         width (inc (* level 2))
         top (map (fn [c] (get-level c width)) (take (inc level) alphas))]
     (concat top (rest (reverse top)))))
