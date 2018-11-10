(ns protein-translation)

(def codon->protein { "AUG"  "Methionine" "UUU" "Phenylalanine" "UUC" "Phenylalanine" "UUA" "Leucine"
"UUG" "Leucine" "UCU" "Serine" "UCC" "Serine" "UCA" "Serine" "UCG" "Serine" "UAU" "Tyrosine"
"UAC" "Tyrosine" "UGU" "Cysteine" "UGC" "Cysteine" "UGG" "Tryptophan" "UAA" "STOP" "UAG" "STOP" "UGA" "STOP"})

(defn translate-codon [codon]
   (codon->protein codon))

(defn translate-rna [rna] 
   (let [codons (map (partial apply str) (partition 3 rna))
         proteins (map codon->protein codons)
         proteins (take-while (fn [x] (not= "STOP" x)) proteins) ]
    proteins))

