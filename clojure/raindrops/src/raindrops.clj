(ns raindrops)

(defn str-output [num div st]
     (if (zero? (mod num div))
         (str "Pl" st "ng") 
         ""))
    

(defn convert [num] 
    (let [output (apply str (map (partial str-output num) [3 5 7] ["i" "a" "o"]))]
          (if (empty? output)
              (str num)
              output)))
