(ns grade-school)

(defn grade [school grade] 
    (school grade [])) 

(defn add [school name grade] 
    (assoc school grade (conj (school grade []) name))) 

(defn sorted [school]  
      (reduce (fn [m grade] (assoc m grade (sort (school grade)))) 
              (sorted-map)  
              (keys school))) 
