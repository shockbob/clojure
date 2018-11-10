(ns allergies)
(def pows2 (iterate #(* 2 %1) 1))
(def allergens [:eggs :peanuts :shellfish :strawberries :tomatoes :chocolate :pollen :cats])
(def allergen-to-bit (zipmap allergens pows2))

(defn bit-match [bit num]
   (= (bit-and bit num) bit))

(defn allergies [num] 
         (filter (fn [allergen] (bit-match (allergen-to-bit allergen) num)) 
                 allergens))    

(defn allergic-to? [num allergen]  
   (let [bit (allergen-to-bit allergen)]
      (bit-match bit num)))
