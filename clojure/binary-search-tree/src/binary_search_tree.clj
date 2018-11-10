(ns binary-search-tree)

(defrecord Node [el left right])

(defn value [{:keys [el left right]}] 
   el)

(defn singleton [value] 
   (Node. value nil nil)
)

(defn insert [value {:keys [el left right] :as tree}  ] 
       (cond 
           (nil? tree) (singleton value) 
           (<= value el ) (Node. el (insert value left) right) 
           :else (Node. el left (insert value right))))

(defn left [{:keys [el left right] }]   
   left)

(defn right  [{:keys [el left right] }]  
  right) 

(defn to-list  [{:keys [el left right] :as tree }]  
    (if (nil? tree)
    nil
    (filter identity (flatten (vector (to-list left) [el] (to-list right))))))


(defn from-list [lis] 
   (reduce (fn [t e] (insert e t)) (singleton (first lis)) (rest lis))) 

