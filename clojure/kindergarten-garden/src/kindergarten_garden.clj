(ns kindergarten-garden)
(def students [:alice :bob :charlie :david :eve :fred :ginny :harriet :ileana :joseph 
               :kincaid :larry :patricia :roger :samantha :xander] )
(def student-names ["Alice" "Bob" "Charlie" "David" "Eve" "Fred" "Ginny" "Harriet" "Ilena" "Joseph" 
                    "Kincaid" "Larry" "Patricia" "Roger" "Samantha" "Xander"]) 
(def plants { \V :violets \R :radishes \C :clover \G :grass})  
(def student-map (zipmap student-names students))

(defn garden ([plot] (garden plot student-names))
   ([plot names] 
    (let [students-in (map student-map (sort names))
          plots (vec (.split plot "\n"))
          plot-map-0 (zipmap students-in (partition 2 (plots 0)))
          plot-map-1 (zipmap students-in (partition 2 (plots 1))) 
          plot-map (merge-with concat plot-map-0 plot-map-1)
          plot-map (reduce (fn [m [k v]] (assoc m k (map plants v))) {} plot-map) ] 
      plot-map)))



