(ns secret-handshake)

(def pows2 (iterate #(* 2 %1) 1))

(def shakes ["wink" "double blink" "close your eyes" "jump"])

(def shake-to-bit (zipmap shakes pows2))

(defn bit-match [bit num]
   (= (bit-and bit num) bit))

(defn commands [num] 
   (let [coms (filter (fn [shake] (bit-match (shake-to-bit shake) num)) shakes)]
       (if (bit-match 16 num)
          (reverse coms)
          coms)))
