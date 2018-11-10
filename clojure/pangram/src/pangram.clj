(ns pangram)

(defn pangram? [st] 
    (let [st (filter (fn [x] (Character/isAlphabetic (int x))) (.toLowerCase st))]
       (= 26 (count (distinct st)))))
           
