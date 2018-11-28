(ns protein-translation
 (:require [clojure.string :as str]))

(def codon->protein { "AUG"  "Methionine" "UUU" "Phenylalanine" "UUC" "Phenylalanine" "UUA" "Leucine"
"UUG" "Leucine" "UCU" "Serine" "UCC" "Serine" "UCA" "Serine" "UCG" "Serine" "UAU" "Tyrosine"
"UAC" "Tyrosine" "UGU" "Cysteine" "UGC" "Cysteine" "UGG" "Tryptophan" "UAA" "STOP" "UAG" "STOP" "UGA" "STOP"})

(defn translate-codon [codon]
   (codon->protein codon))

(defn translate-rna [rna] 
   (->> rna
        (partition 3)
        (map str/join) 
        (map codon->protein)
        (take-while #(not= "STOP" %))))
