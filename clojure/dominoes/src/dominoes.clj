(ns dominoes)
 
(defn can-chain? [doms] 
    (cond (= 0 (count doms)) true 
          (= 1 (count doms)) (let [[f l] (first doms)]  (= f l))
        ))


 
