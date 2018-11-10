(ns nucleotide-count)

(def size count)

(defn valid-nuc [c]
   (contains? #{\G \C \T \A} c))

(defn count [nucleotide strand] 
   (if (not (valid-nuc nucleotide))
        (throw (Throwable. ""))
        (size (filter (partial = nucleotide) strand))))

(defn nucleotide-counts [strand] 
    (if (not (every? valid-nuc strand))
        (throw (Throwable. ""))
        (reduce (fn [m c] (assoc m c (inc (m c 0)))) {\A 0 \G 0 \T 0 \C 0} strand)))
