(ns etl)

(defn transform [source] 
   (reduce 
       (fn [m [k coll]] (reduce 
                           (fn [mm coll-ell] (assoc mm (.toLowerCase coll-ell) k)) 
                           m 
                           coll)) 
       {} 
       source))  
