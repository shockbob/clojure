(ns bracket-push)

(defn remove-bad [st] 
    (apply str (keep #{\[ \] \{ \} \( \)} st)))

(defn remove-matches [st]
   (if (empty? st)
       true
       (let [st-new (.replace st "[]"  "")
             st-new (.replace st-new "{}" "")
             st-new (.replace st-new "()" "")]
          (if (= st st-new)
              false
              (remove-matches st-new)))))

(defn valid? [st] 
    (remove-matches (remove-bad st)))
