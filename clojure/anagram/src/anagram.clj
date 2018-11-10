(ns anagram)

(defn fixit [s] 
  (apply str (sort (.toLowerCase s))))

(defn anagrams-for [word prospect-list] 
  (let [fixed-word (fixit word)]
     (filter (fn [wrd] (and (not= (.toLowerCase word)(.toLowerCase wrd)) 
                            (= fixed-word (fixit wrd)))) 
              prospect-list))) 

