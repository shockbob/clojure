(ns change)

(defn dochange [pennies [f & r] output]
   (if (nil? f)
       output
       (let [dv (quot pennies f)
             rm (mod pennies f)]
           (dochange rm r (concat (repeat dv f) output )))))

(defn get-coins [pennies coins] 
    (let [coins (sort-by - coins)
          all-coins (take-while first (iterate rest coins)) 
          results (map (fn [c] (dochange pennies c [])) all-coins)
          srt (sort-by count results)]
      (first srt)))

(defn issue [pennies coins] 
    (cond 
        (zero? pennies) []
        (not (some (fn [c] (< c pennies)) coins)) (throw (IllegalArgumentException."cannot change"))
        (neg? pennies) (throw (IllegalArgumentException."cannot change"))
        :else (get-coins pennies coins)))

