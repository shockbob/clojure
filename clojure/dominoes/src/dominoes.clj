(ns dominoes)

(defn reversed [key dom]
  {:place reverse :key key :dom dom})

(defn standard [key dom]
  {:place identity :key key :dom dom})

(defn get-match [num key dom]
    (cond (= (first dom) num) (standard key dom) 
          (= (last dom) num) (reversed key dom))) 

(defn remove-from-remaining [match remaining]
   (dissoc remaining (:key match)))

(defn find-matchers [chain remaining]
   (if (empty? chain)
       (let [standards (map (fn [[key dom]] (standard key dom)) remaining)
             reversed (map (fn [[key dom]] (reversed key dom)) remaining)]
            (concat standards reversed))
       (let [num (last (last chain))]
            (filter identity (map (fn [[key dom]] (get-match num key dom )) remaining)))))


(defn add-to-chain [chain match]
        (conj chain ((:place match) (:dom match))))

(defn chainer [chain remaining]
    (if (empty? remaining)
        (= (first (first chain)) (last (last chain)))
        (let [matchers (find-matchers chain remaining)]
             (if (empty? matchers)
                 false
                 (not (empty? (filter 
                              (fn [match] (chainer (add-to-chain chain match) (remove-from-remaining match remaining)))
                              matchers))))))) 
            
(defn can-chain? [doms] 
    (cond (= 0 (count doms)) true 
          (= 1 (count doms)) (let [[f l] (first doms)]  (= f l))
          :else (chainer [] (zipmap (range) doms))))

