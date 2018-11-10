(ns proverb
  (:require [clojure.string :as str]))

(defn calc [words]
    (let [segments (partition 2 1 words)
          phrases (map (fn [[a b]] (str "For want of a " a " the " b " was lost.")) segments)
          phrases (concat phrases [(str "And all for the want of a " (first words) ".")])]
         (str/join "\n" phrases ))) 
 

(def proverb (calc ["nail" "shoe" "horse" "rider" "message" "battle" "kingdom"])) 
