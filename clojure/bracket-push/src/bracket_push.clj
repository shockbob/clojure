(ns bracket-push)
(def brackets ["[]" "{}" "()"])
(def opens (map first brackets))
(def closes (map second brackets))

(def opens-set (set opens)) 
(def opens-and-closes (set (concat opens closes))) 
(def opens-to-closes (zipmap opens closes)) 

(defn- brackets-match? [stack [f & r]]
     (if (nil? f)
         (empty? stack)
         (if (contains? opens-set f)
            (brackets-match? (concat [f] stack) r) 
            (if (= (opens-to-closes (first stack)) f)
                (brackets-match? (rest stack) r) 
                false)))) 

(defn valid? [string] 
   (->> string
        (keep opens-and-closes)
        (brackets-match? [])))
