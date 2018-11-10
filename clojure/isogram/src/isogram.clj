(ns isogram)

(defn isogram? [iso] 
   (let [letters (filter (fn [c] (Character/isAlphabetic (int c))) (.toLowerCase iso))]
      (= (count letters) (count (distinct letters))))) 
         
