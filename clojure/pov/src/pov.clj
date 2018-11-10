(ns pov)


(defn buildmap [[f & l :as r] p level]
      (map #(if (coll? %)
          (buildmap % f (inc level))
          {:i % :level level :c (concat (map first l) [p]) :parent p})
        r))

(defn find-node [n treemap] 
    (first (filter 
            #(= (:i %) n) 
            treemap)))
 
(defn find-node-path-to-root ([node treemap] (find-node-path-to-root node treemap []))
   ([node treemap path]
      (if (nil? node)
          path
          (let [path (concat path [node])
               tree-node (find-node node treemap)]
               (find-node-path-to-root (tree-node :parent) treemap path)))))

(defn find-node-path [a b treemap]
   (let [a-path (find-node-path-to-root a treemap)
        b-path (find-node-path-to-root b treemap)
        size (min (count a-path)(count b-path))
        a-rev (vec (reverse a-path))
        b-rev (vec (reverse b-path))
        taken (take-while (fn [i] (= (a-rev i)(b-rev i))) (range size))
        full-path (concat a-path (drop (count taken) b-rev)) ]
        (cond (= b (last a-path)) a-path
              (= a (last b-path)) b-path
              :elase full-path))) 
              
             

(defn path-from-to [a b tree] 
    (let [treemap (flatten (buildmap tree nil 0))
          a-node (find-node a treemap)
          b-node (find-node b treemap)]
        (if (or (nil? a-node)(nil? b-node))
            nil
            (find-node-path a b treemap))))
    

(defn of [n t] 
  (let [
    treemap (flatten (buildmap t nil 0))
    reparent (fn reparent [last-node n]
       (if (not (nil? n))
         (let [{pc :c} (find-node n treemap)]
           (if (nil? pc)
           nil
           (concat [n]
              (keep 
                identity 
                   (map 
                     (fn [e] (if (not= last-node e)
                                 (reparent n e)))
                    pc)))))))]
    (reparent nil n)))
