(ns rna-transcription)

(def map-to-rna {\G \C \C \G \T \A \A \U})
(defn to-rna [dna] 
   (cond (not (every? map-to-rna dna)) (throw (AssertionError. "")) 
         :else (apply str (map (fn [c] (map-to-rna c)) dna))))

